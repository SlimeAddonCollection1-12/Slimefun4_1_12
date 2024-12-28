/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public abstract class AGenerator
/*     */   extends SlimefunItem
/*     */ {
/*  45 */   public static Map<Location, MachineFuel> processing = new HashMap<>();
/*  46 */   public static Map<Location, Integer> progress = new HashMap<>();
/*     */   
/*  48 */   private Set<MachineFuel> recipes = new HashSet<>();
/*     */   
/*  50 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
/*  51 */   private static final int[] border_in = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
/*  52 */   private static final int[] border_out = new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
/*     */   
/*     */   public AGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/*  55 */     super(category, item, id, recipeType, recipe);
/*     */     
/*  57 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  61 */           AGenerator.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  70 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*  71 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  76 */           if (flow.equals(ItemTransportFlow.INSERT)) return AGenerator.this.getInputSlots(); 
/*  77 */           return AGenerator.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */     
/*  81 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  90 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  91 */             if (inv != null) {
/*  92 */               for (int slot : AGenerator.this.getInputSlots()) {
/*  93 */                 if (inv.getItemInSlot(slot) != null) {
/*  94 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  95 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*  98 */               for (int slot : AGenerator.this.getOutputSlots()) {
/*  99 */                 if (inv.getItemInSlot(slot) != null) {
/* 100 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 101 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 105 */             AGenerator.progress.remove(b.getLocation());
/* 106 */             AGenerator.processing.remove(b.getLocation());
/* 107 */             return true;
/*     */           }
/*     */         });
/*     */     
/* 111 */     registerDefaultRecipes();
/*     */   }
/*     */   
/*     */   public AGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/* 115 */     super(category, item, id, recipeType, recipe, recipeOutput);
/*     */     
/* 117 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/* 121 */           AGenerator.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 130 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/* 131 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 136 */           if (flow.equals(ItemTransportFlow.INSERT)) return AGenerator.this.getInputSlots(); 
/* 137 */           return AGenerator.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */     
/* 141 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 150 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 151 */             if (inv != null) {
/* 152 */               for (int slot : AGenerator.this.getInputSlots()) {
/* 153 */                 if (inv.getItemInSlot(slot) != null) {
/* 154 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 155 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 158 */               for (int slot : AGenerator.this.getOutputSlots()) {
/* 159 */                 if (inv.getItemInSlot(slot) != null) {
/* 160 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 161 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 165 */             AGenerator.progress.remove(b.getLocation());
/* 166 */             AGenerator.processing.remove(b.getLocation());
/* 167 */             return true;
/*     */           }
/*     */         });
/*     */     
/* 171 */     registerDefaultRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 176 */     for (int i : border) {
/* 177 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 182 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 187 */     for (int i : border_in) {
/* 188 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 193 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 198 */     for (int i : border_out) {
/* 199 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 204 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 210 */     for (int i : getOutputSlots()) {
/* 211 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */             {
/* 215 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 220 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 225 */     preset.addItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 230 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 242 */     return new int[] { 19, 20 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 246 */     return new int[] { 24, 25 };
/*     */   }
/*     */   
/*     */   public MachineFuel getProcessing(Location l) {
/* 250 */     return processing.get(l);
/*     */   }
/*     */   
/*     */   public boolean isProcessing(Location l) {
/* 254 */     return progress.containsKey(l);
/*     */   }
/*     */   
/*     */   public void registerFuel(MachineFuel fuel) {
/* 258 */     this.recipes.add(fuel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 263 */     addItemHandler(new ItemHandler[] { (ItemHandler)new EnergyTicker()
/*     */           {
/*     */             
/*     */             public double generateEnergy(Location l, SlimefunItem sf, Config data)
/*     */             {
/* 268 */               if (AGenerator.this.isProcessing(l)) {
/* 269 */                 int timeleft = ((Integer)AGenerator.progress.get(l)).intValue();
/* 270 */                 if (timeleft > 0) {
/* 271 */                   ItemStack item = AGenerator.this.getProgressBar().clone();
/* 272 */                   item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineFuel)AGenerator.processing.get(l)).getTicks()));
/* 273 */                   ItemMeta im = item.getItemMeta();
/* 274 */                   im.setDisplayName(" ");
/* 275 */                   List<String> lore = new ArrayList<>();
/* 276 */                   lore.add(MachineHelper.getProgress(timeleft, ((MachineFuel)AGenerator.processing.get(l)).getTicks()));
/* 277 */                   lore.add("");
/* 278 */                   lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 279 */                   im.setLore(lore);
/* 280 */                   item.setItemMeta(im);
/*     */                   
/* 282 */                   BlockStorage.getInventory(l).replaceExistingItem(22, item);
/*     */                   
/* 284 */                   if (ChargableBlock.isChargable(l)) {
/* 285 */                     if (ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l) >= AGenerator.this.getEnergyProduction()) {
/* 286 */                       ChargableBlock.addCharge(l, AGenerator.this.getEnergyProduction());
/* 287 */                       AGenerator.progress.put(l, Integer.valueOf(timeleft - 1));
/* 288 */                       return ChargableBlock.getCharge(l);
/*     */                     } 
/* 290 */                     return 0.0D;
/*     */                   } 
/*     */                   
/* 293 */                   AGenerator.progress.put(l, Integer.valueOf(timeleft - 1));
/* 294 */                   return AGenerator.this.getEnergyProduction();
/*     */                 } 
/*     */ 
/*     */                 
/* 298 */                 ItemStack fuel = ((MachineFuel)AGenerator.processing.get(l)).getInput();
/* 299 */                 if (SlimefunManager.isItemSimiliar(fuel, new ItemStack(Material.LAVA_BUCKET), true)) {
/* 300 */                   AGenerator.this.pushItems(l, new ItemStack[] { new ItemStack(Material.BUCKET) });
/*     */                 }
/* 302 */                 else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_FUEL, true)) {
/* 303 */                   AGenerator.this.pushItems(l, new ItemStack[] { new ItemStack(Material.BUCKET) });
/*     */                 }
/* 305 */                 else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_OIL, true)) {
/* 306 */                   AGenerator.this.pushItems(l, new ItemStack[] { new ItemStack(Material.BUCKET) });
/*     */                 } 
/* 308 */                 BlockStorage.getInventory(l).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/*     */                 
/* 310 */                 AGenerator.progress.remove(l);
/* 311 */                 AGenerator.processing.remove(l);
/* 312 */                 return 0.0D;
/*     */               } 
/*     */ 
/*     */               
/* 316 */               MachineFuel r = null;
/* 317 */               Map<Integer, Integer> found = new HashMap<>();
/*     */               
/* 319 */               label41: for (MachineFuel recipe : AGenerator.this.recipes) {
/* 320 */                 for (int slot : AGenerator.this.getInputSlots()) {
/* 321 */                   if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(l).getItemInSlot(slot), recipe.getInput(), true)) {
/* 322 */                     found.put(Integer.valueOf(slot), Integer.valueOf(recipe.getInput().getAmount()));
/* 323 */                     r = recipe;
/*     */                     
/*     */                     break label41;
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 329 */               if (r != null) {
/* 330 */                 for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
/* 331 */                   BlockStorage.getInventory(l).replaceExistingItem(((Integer)entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(l).getItemInSlot(((Integer)entry.getKey()).intValue()), ((Integer)entry.getValue()).intValue()));
/*     */                 }
/* 333 */                 AGenerator.processing.put(l, r);
/* 334 */                 AGenerator.progress.put(l, Integer.valueOf(r.getTicks()));
/*     */               } 
/* 336 */               return 0.0D;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public boolean explode(Location l) {
/* 342 */               return false;
/*     */             }
/*     */           } });
/*     */     
/* 346 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   public Set<MachineFuel> getFuelTypes() {
/* 350 */     return this.recipes;
/*     */   }
/*     */   
/*     */   private Inventory inject(Location l) {
/* 354 */     int size = BlockStorage.getInventory(l).toInventory().getSize();
/* 355 */     Inventory inv = Bukkit.createInventory(null, size);
/* 356 */     for (int i = 0; i < size; i++) {
/* 357 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 359 */     for (int slot : getOutputSlots()) {
/* 360 */       inv.setItem(slot, BlockStorage.getInventory(l).getItemInSlot(slot));
/*     */     }
/* 362 */     return inv;
/*     */   }
/*     */   
/*     */   protected void pushItems(Location l, ItemStack[] items) {
/* 366 */     Inventory inv = inject(l);
/* 367 */     inv.addItem(items);
/*     */     
/* 369 */     for (int slot : getOutputSlots())
/* 370 */       BlockStorage.getInventory(l).replaceExistingItem(slot, inv.getItem(slot)); 
/*     */   }
/*     */   
/*     */   public abstract String getInventoryTitle();
/*     */   
/*     */   public abstract ItemStack getProgressBar();
/*     */   
/*     */   public abstract void registerDefaultRecipes();
/*     */   
/*     */   public abstract int getEnergyProduction();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\AGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */