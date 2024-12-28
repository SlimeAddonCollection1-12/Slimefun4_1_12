/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.narMachines;
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
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.container.UURecipe;
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
/*     */ 
/*     */ public abstract class UUMachine extends SlimefunItem {
/*  39 */   public static Map<Block, UURecipe> processing = new HashMap<>();
/*  40 */   public static Map<Block, Integer> progress = new HashMap<>();
/*  41 */   protected List<UURecipe> recipes = new ArrayList<>();
/*  42 */   private static final int[] uuBorder = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
/*  43 */   private static final int[] uuInfo = new int[] { 10, 11, 12, 13, 14, 15, 16 };
/*  44 */   private static final int[] border = new int[] { 27, 30, 31, 32, 35, 36, 39, 41, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*  45 */   private static final int[] inputSign = new int[] { 28, 29 };
/*  46 */   private static final int[] outputSign = new int[] { 33, 34 };
/*  47 */   private static final ItemStack uuItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
/*     */   
/*     */   public UUMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  50 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  52 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         
/*     */         public void init()
/*     */         {
/*  57 */           UUMachine.this.constructMenu(this);
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
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  73 */           if (flow.equals(ItemTransportFlow.INSERT)) {
/*  74 */             return UUMachine.this.getInputSlots();
/*     */           }
/*  76 */           return UUMachine.this.getOutputSlots();
/*     */         }
/*     */       };
/*  79 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  87 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  88 */             if (inv != null) {
/*     */               
/*  90 */               for (int slot : UUMachine.this.getInputSlots()) {
/*  91 */                 if (inv.getItemInSlot(slot) != null) {
/*  92 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*     */                 }
/*     */               } 
/*  95 */               for (int slot : UUMachine.this.getOutputSlots()) {
/*  96 */                 if (inv.getItemInSlot(slot) != null) {
/*  97 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*     */                 }
/*     */               } 
/*     */             } 
/* 101 */             UUMachine.progress.remove(b);
/* 102 */             UUMachine.processing.remove(b);
/* 103 */             return true;
/*     */           }
/*     */         });
/* 106 */     registerDefaultRecipes();
/*     */   }
/*     */   
/*     */   public UUMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/* 110 */     super(category, item, name, recipeType, recipe, recipeOutput);
/*     */     
/* 112 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         
/*     */         public void init()
/*     */         {
/* 117 */           UUMachine.this.constructMenu(this);
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
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 133 */           if (flow.equals(ItemTransportFlow.INSERT)) {
/* 134 */             return UUMachine.this.getInputSlots();
/*     */           }
/* 136 */           return UUMachine.this.getOutputSlots();
/*     */         }
/*     */       };
/* 139 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 147 */             for (int slot : UUMachine.this.getInputSlots()) {
/* 148 */               if (BlockStorage.getInventory(b).getItemInSlot(slot) != null) {
/* 149 */                 b.getWorld().dropItemNaturally(b.getLocation(), BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */               }
/*     */             } 
/* 152 */             for (int slot : UUMachine.this.getOutputSlots()) {
/* 153 */               if (BlockStorage.getInventory(b).getItemInSlot(slot) != null) {
/* 154 */                 b.getWorld().dropItemNaturally(b.getLocation(), BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */               }
/*     */             } 
/* 157 */             UUMachine.processing.remove(b);
/* 158 */             UUMachine.progress.remove(b);
/* 159 */             return true;
/*     */           }
/*     */         });
/* 162 */     registerDefaultRecipes();
/*     */   }
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 166 */     for (int i : uuBorder) {
/* 167 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " "), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 171 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 175 */     for (int i : border) {
/* 176 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7), " "), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 180 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 184 */     for (int i : outputSign) {
/* 185 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11), "§b输出槽"), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 189 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 193 */     for (int i : inputSign) {
/* 194 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4), "§e输入槽"), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 198 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 203 */     preset.addItem(40, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " "), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */           {
/* 207 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 211 */     for (int i : getOutputSlots()) {
/* 212 */       preset.addItem(i, null, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack item, ClickAction action) {
/* 215 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent event, Player player, int slot, ItemStack item, ClickAction action) {
/* 220 */               return (item == null || item.getType() == null || item.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 225 */     for (int i : uuInfo) {
/* 226 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7), " "), (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(InventoryClickEvent inventoryClickEvent, Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 229 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 234 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 241 */     return new int[] { 37, 38 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 245 */     return new int[] { 42, 43 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UURecipe getProcessing(Block b) {
/* 266 */     return processing.get(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isProcessing(Block b) {
/* 271 */     return (getProcessing(b) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerRecipe(UURecipe recipe) {
/* 276 */     recipe.setTicks(recipe.getTicks());
/* 277 */     this.recipes.add(recipe);
/*     */   }
/*     */   
/*     */   public void registerRecipe(int seconds, ItemStack[] input, int uuAmount) {
/* 281 */     registerRecipe(new UURecipe(seconds, input, uuAmount));
/*     */   }
/*     */   
/*     */   public List<UURecipe> getUURecipes() {
/* 285 */     return this.recipes;
/*     */   }
/*     */ 
/*     */   
/*     */   private Inventory inject(Block b) {
/* 290 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 291 */     Inventory inv = Bukkit.createInventory(null, size);
/* 292 */     for (int i = 0; i < size; i++) {
/* 293 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 295 */     for (int slot : getOutputSlots()) {
/* 296 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 298 */     return inv;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 303 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void pushMainItems(Block b, ItemStack[] items, int uu) {
/* 308 */     if (BlockStorage.getBlockInfo(b, "uuAmount") == null) {
/* 309 */       BlockStorage.addBlockInfo(b, "uuAmount", "0", false);
/*     */     } else {
/* 311 */       BlockStorage.addBlockInfo(b, "uuAmount", String.valueOf(uu + Integer.valueOf(BlockStorage.getBlockInfo(b, "uuAmount")).intValue()), false);
/*     */     } 
/* 313 */     if (Integer.valueOf(BlockStorage.getBlockInfo(b, "uuAmount")).intValue() >= getUUFull()) {
/* 314 */       BlockStorage.addBlockInfo(b, "uuAmount", "0", false);
/* 315 */       Inventory inv = inject(b);
/* 316 */       inv.addItem(items);
/* 317 */       for (int slot : getOutputSlots()) {
/* 318 */         BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*     */       }
/*     */     } 
/* 321 */     int amount = Integer.valueOf(BlockStorage.getBlockInfo(b, "uuAmount")).intValue();
/* 322 */     for (int i : uuInfo) {
/* 323 */       if ((i - uuInfo[0]) * getUUFull() / 7 <= amount) {
/* 324 */         BlockStorage.getInventory(b).replaceExistingItem(i, (ItemStack)new CustomItem(uuItem, "§7元物质量: §d" + amount + "§7/§c100000"));
/*     */       } else {
/* 326 */         BlockStorage.getInventory(b).replaceExistingItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7), " "));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 334 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 339 */               UUMachine.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 348 */               return false;
/*     */             }
/*     */           } });
/* 351 */     super.register(slimefun);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/* 356 */     if (isProcessing(b)) {
/* 357 */       int timeleft = ((Integer)progress.get(b)).intValue();
/* 358 */       if (timeleft > 0) {
/* 359 */         ItemStack item = getProgressBar().clone();
/* 360 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((UURecipe)processing.get(b)).getTicks()));
/* 361 */         ItemMeta im = item.getItemMeta();
/* 362 */         im.setDisplayName(" ");
/* 363 */         List<String> lore = new ArrayList<>();
/* 364 */         lore.add(MachineHelper.getProgress(timeleft, ((UURecipe)processing.get(b)).getTicks()));
/* 365 */         lore.add("");
/* 366 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 367 */         im.setLore(lore);
/* 368 */         item.setItemMeta(im);
/*     */         
/* 370 */         BlockStorage.getInventory(b).replaceExistingItem(40, item);
/* 371 */         if (ChargableBlock.isChargable(b)) {
/*     */           
/* 373 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption()) {
/*     */             return;
/*     */           }
/* 376 */           ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 377 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/*     */           
/* 380 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*     */       } else {
/*     */         
/* 384 */         BlockStorage.getInventory(b).replaceExistingItem(40, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " "));
/* 385 */         pushMainItems(b, getOutput(), ((UURecipe)processing.get(b)).getUUAmount());
/* 386 */         progress.remove(b);
/* 387 */         processing.remove(b);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 392 */       UURecipe r = null;
/* 393 */       Map<Integer, Integer> found = new HashMap<>();
/* 394 */       for (UURecipe recipe : this.recipes) {
/*     */         
/* 396 */         for (ItemStack input : recipe.getInput()) {
/* 397 */           for (int slot : getInputSlots()) {
/* 398 */             if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), input, true))
/*     */             {
/* 400 */               if (input != null) {
/* 401 */                 found.put(Integer.valueOf(slot), Integer.valueOf(input.getAmount()));
/*     */               }
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 407 */         if (found.size() == (recipe.getInput()).length) {
/*     */           
/* 409 */           r = recipe;
/*     */           break;
/*     */         } 
/* 412 */         found.clear();
/*     */       } 
/* 414 */       if (r != null) {
/*     */         
/* 416 */         if (!fits(b, getOutput())) {
/*     */           return;
/*     */         }
/* 419 */         for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
/* 420 */           BlockStorage.getInventory(b).replaceExistingItem(((Integer)entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(((Integer)entry.getKey()).intValue()), ((Integer)entry.getValue()).intValue()));
/*     */         }
/* 422 */         processing.put(b, r);
/* 423 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract int getUUFull();
/*     */   
/*     */   public abstract ItemStack[] getOutput();
/*     */   
/*     */   public abstract String getInventoryTitle();
/*     */   
/*     */   public abstract ItemStack getProgressBar();
/*     */   
/*     */   public abstract void registerDefaultRecipes();
/*     */   
/*     */   public abstract int getEnergyConsumption();
/*     */   
/*     */   public abstract int getLevel();
/*     */   
/*     */   public abstract String getMachineIdentifier();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\narMachines\UUMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */