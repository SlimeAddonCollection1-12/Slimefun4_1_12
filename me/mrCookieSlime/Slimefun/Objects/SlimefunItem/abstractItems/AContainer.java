/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
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
/*     */ public abstract class AContainer
/*     */   extends SlimefunItem
/*     */ {
/*  41 */   public static Map<Block, MachineRecipe> processing = new HashMap<>();
/*  42 */   public static Map<Block, Integer> progress = new HashMap<>();
/*     */   
/*  44 */   protected List<MachineRecipe> recipes = new ArrayList<>();
/*     */   
/*  46 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
/*  47 */   private static final int[] border_in = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
/*  48 */   private static final int[] border_out = new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
/*     */   
/*     */   public AContainer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/*  51 */     super(category, item, id, recipeType, recipe);
/*     */     
/*  53 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  57 */           AContainer.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  66 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*  67 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  72 */           if (flow.equals(ItemTransportFlow.INSERT)) return AContainer.this.getInputSlots(); 
/*  73 */           return AContainer.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */     
/*  77 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  86 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  87 */             if (inv != null) {
/*  88 */               for (int slot : AContainer.this.getInputSlots()) {
/*  89 */                 if (inv.getItemInSlot(slot) != null) {
/*  90 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  91 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*  94 */               for (int slot : AContainer.this.getOutputSlots()) {
/*  95 */                 if (inv.getItemInSlot(slot) != null) {
/*  96 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  97 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 101 */             AContainer.progress.remove(b);
/* 102 */             AContainer.processing.remove(b);
/* 103 */             return true;
/*     */           }
/*     */         });
/*     */     
/* 107 */     registerDefaultRecipes();
/*     */   }
/*     */   
/*     */   public AContainer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/* 111 */     super(category, item, id, recipeType, recipe, recipeOutput);
/*     */     
/* 113 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/* 117 */           AContainer.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 126 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/* 127 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 132 */           if (flow.equals(ItemTransportFlow.INSERT)) return AContainer.this.getInputSlots(); 
/* 133 */           return AContainer.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */     
/* 137 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 146 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 147 */             if (inv != null) {
/* 148 */               for (int slot : AContainer.this.getInputSlots()) {
/* 149 */                 if (inv.getItemInSlot(slot) != null) {
/* 150 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 151 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 154 */               for (int slot : AContainer.this.getOutputSlots()) {
/* 155 */                 if (inv.getItemInSlot(slot) != null) {
/* 156 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 157 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 161 */             AContainer.processing.remove(b);
/* 162 */             AContainer.progress.remove(b);
/* 163 */             return true;
/*     */           }
/*     */         });
/*     */     
/* 167 */     registerDefaultRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 172 */     for (int i : border) {
/* 173 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 178 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 183 */     for (int i : border_in) {
/* 184 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 189 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 194 */     for (int i : border_out) {
/* 195 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 200 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 206 */     preset.addItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 211 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 216 */     for (int i : getOutputSlots()) {
/* 217 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */             {
/* 221 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 226 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract String getInventoryTitle();
/*     */   
/*     */   public abstract ItemStack getProgressBar();
/*     */   
/*     */   public abstract void registerDefaultRecipes();
/*     */   
/*     */   public int[] getInputSlots() {
/* 240 */     return new int[] { 19, 20 };
/*     */   } public abstract int getEnergyConsumption(); public abstract int getSpeed();
/*     */   public abstract String getMachineIdentifier();
/*     */   public int[] getOutputSlots() {
/* 244 */     return new int[] { 24, 25 };
/*     */   }
/*     */   
/*     */   public MachineRecipe getProcessing(Block b) {
/* 248 */     return processing.get(b);
/*     */   }
/*     */   
/*     */   public boolean isProcessing(Block b) {
/* 252 */     return (getProcessing(b) != null);
/*     */   }
/*     */   
/*     */   public void registerRecipe(MachineRecipe recipe) {
/* 256 */     recipe.setTicks(recipe.getTicks() / getSpeed());
/* 257 */     this.recipes.add(recipe);
/*     */   }
/*     */   
/*     */   public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
/* 261 */     registerRecipe(new MachineRecipe(seconds, input, output));
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/* 265 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 266 */     Inventory inv = Bukkit.createInventory(null, size);
/* 267 */     for (int i = 0; i < size; i++) {
/* 268 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 270 */     for (int slot : getOutputSlots()) {
/* 271 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 273 */     return inv;
/*     */   }
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 277 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushItems(Block b, ItemStack[] items) {
/* 281 */     Inventory inv = inject(b);
/* 282 */     inv.addItem(items);
/*     */     
/* 284 */     for (int slot : getOutputSlots()) {
/* 285 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 291 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 295 */               AContainer.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 304 */               return false;
/*     */             }
/*     */           } });
/*     */     
/* 308 */     super.register(slimefun);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/* 313 */     if (isProcessing(b)) {
/* 314 */       int timeleft = ((Integer)progress.get(b)).intValue();
/* 315 */       if (timeleft > 0) {
/* 316 */         ItemStack item = getProgressBar().clone();
/* 317 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 318 */         ItemMeta im = item.getItemMeta();
/* 319 */         im.setDisplayName(" ");
/* 320 */         List<String> lore = new ArrayList<>();
/* 321 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 322 */         lore.add("");
/* 323 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 324 */         im.setLore(lore);
/* 325 */         item.setItemMeta(im);
/*     */         
/* 327 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/* 329 */         if (ChargableBlock.isChargable(b)) {
/* 330 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 331 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 332 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/* 334 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*     */       } else {
/* 337 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/* 338 */         pushItems(b, (ItemStack[])((MachineRecipe)processing.get(b)).getOutput().clone());
/*     */         
/* 340 */         progress.remove(b);
/* 341 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/* 345 */       MachineRecipe r = null;
/* 346 */       Map<Integer, Integer> found = new HashMap<>();
/*     */       
/* 348 */       for (MachineRecipe recipe : this.recipes) {
/* 349 */         for (ItemStack input : recipe.getInput()) {
/*     */           
/* 351 */           for (int slot : getInputSlots()) {
/* 352 */             if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), input, true)) {
/* 353 */               found.put(Integer.valueOf(slot), Integer.valueOf(input.getAmount()));
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 358 */         if (found.size() == (recipe.getInput()).length) {
/* 359 */           r = recipe;
/*     */           break;
/*     */         } 
/* 362 */         found.clear();
/*     */       } 
/*     */       
/* 365 */       if (r != null) {
/* 366 */         if (!fits(b, r.getOutput()))
/* 367 */           return;  for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
/* 368 */           BlockStorage.getInventory(b).replaceExistingItem(((Integer)entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(((Integer)entry.getKey()).intValue()), ((Integer)entry.getValue()).intValue()));
/*     */         }
/* 370 */         processing.put(b, r);
/* 371 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\AContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */