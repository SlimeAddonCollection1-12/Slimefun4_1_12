/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
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
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public abstract class AFarm
/*     */   extends SlimefunItem
/*     */ {
/*  34 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
/*  35 */   private static final int[] border_out = new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 };
/*     */   
/*     */   public AFarm(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/*  38 */     super(category, item, id, recipeType, recipe);
/*     */     
/*  40 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  44 */           AFarm.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  53 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*  54 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  59 */           if (flow.equals(ItemTransportFlow.WITHDRAW)) return AFarm.this.getOutputSlots(); 
/*  60 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/*  64 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  73 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  74 */             if (inv != null) {
/*  75 */               for (int slot : AFarm.this.getOutputSlots()) {
/*  76 */                 if (inv.getItemInSlot(slot) != null) {
/*  77 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  78 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/*  82 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public AFarm(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/*  88 */     super(category, item, id, recipeType, recipe, recipeOutput);
/*     */     
/*  90 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  94 */           AFarm.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 103 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/* 104 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 109 */           if (flow.equals(ItemTransportFlow.WITHDRAW)) return AFarm.this.getOutputSlots(); 
/* 110 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/* 114 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 123 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 124 */             if (inv != null) {
/* 125 */               for (int slot : AFarm.this.getOutputSlots()) {
/* 126 */                 if (inv.getItemInSlot(slot) != null) {
/* 127 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 128 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/* 132 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 139 */     for (int i : border) {
/* 140 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 145 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 150 */     for (int i : border_out) {
/* 151 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 156 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 162 */     preset.addItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 167 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 172 */     for (int i : getOutputSlots()) {
/* 173 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */             {
/* 177 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 182 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getOutputSlots() {
/* 195 */     return new int[] { 19, 20, 21, 22, 23, 24, 25 };
/*     */   }
/*     */   
/*     */   protected void tick(Block b) {
/* 199 */     if (ChargableBlock.isChargable(b)) {
/* 200 */       if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 201 */         return;  int i = getSize() / 2;
/*     */       
/* 203 */       for (int x = -i; x <= i; x++) {
/* 204 */         for (int z = -i; z <= i; z++) {
/* 205 */           Block block = (new Location(b.getWorld(), (b.getX() + x), (b.getY() + 2), (b.getZ() + z))).getBlock();
/* 206 */           if (canHarvest(block)) {
/* 207 */             ItemStack item = harvest(block);
/* 208 */             if (!fits(block, new ItemStack[] { item }))
/* 209 */               // Byte code: goto -> 167  pushItems(b, new ItemStack[] { item });
/* 210 */             ChargableBlock.addCharge(b, -getEnergyConsumption());
/*     */             // Byte code: goto -> 167
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 220 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 224 */               AFarm.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 233 */               return true;
/*     */             }
/*     */           } });
/*     */     
/* 237 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/* 241 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 242 */     Inventory inv = Bukkit.createInventory(null, size);
/* 243 */     for (int i = 0; i < size; i++) {
/* 244 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 246 */     for (int slot : getOutputSlots()) {
/* 247 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 249 */     return inv;
/*     */   }
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 253 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushItems(Block b, ItemStack[] items) {
/* 257 */     Inventory inv = inject(b);
/* 258 */     inv.addItem(items);
/*     */     
/* 260 */     for (int slot : getOutputSlots())
/* 261 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot)); 
/*     */   }
/*     */   
/*     */   public abstract String getInventoryTitle();
/*     */   
/*     */   public abstract int getEnergyConsumption();
/*     */   
/*     */   public abstract boolean canHarvest(Block paramBlock);
/*     */   
/*     */   public abstract ItemStack harvest(Block paramBlock);
/*     */   
/*     */   public abstract int getSize();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\AFarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */