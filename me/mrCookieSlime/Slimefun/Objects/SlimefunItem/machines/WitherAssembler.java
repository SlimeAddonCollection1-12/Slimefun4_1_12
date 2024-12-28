/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class WitherAssembler
/*     */   extends SlimefunItem {
/*  36 */   private static int lifetime = 0;
/*     */   
/*  38 */   private static final int[] border = new int[] { 0, 2, 3, 4, 5, 6, 8, 12, 14, 21, 23, 30, 32, 39, 40, 41 };
/*  39 */   private static final int[] border_1 = new int[] { 9, 10, 11, 18, 20, 27, 29, 36, 37, 38 };
/*  40 */   private static final int[] border_2 = new int[] { 15, 16, 17, 24, 26, 33, 35, 42, 43, 44 };
/*     */   
/*     */   public WitherAssembler(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  43 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  45 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  49 */           WitherAssembler.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*     */           try {
/*  55 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "enabled") == null || BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals("false")) {
/*  56 */               menu.replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.SULPHUR), "&7激活状态: &4✘", new String[] { "", "&e> 点击激活这个机器" }));
/*  57 */               menu.addMenuClickHandler(22, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  61 */                       BlockStorage.addBlockInfo(b, "enabled", "true");
/*  62 */                       WitherAssembler.null.this.newInstance(menu, b);
/*  63 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/*  68 */               menu.replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.REDSTONE), "&7激活状态: &2✔", new String[] { "", "&e> 点击停止这个机器" }));
/*  69 */               menu.addMenuClickHandler(22, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  73 */                       BlockStorage.addBlockInfo(b, "enabled", "false");
/*  74 */                       WitherAssembler.null.this.newInstance(menu, b);
/*  75 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/*  80 */             double offset = (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "offset") == null) ? 3.0D : Double.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "offset")).doubleValue();
/*     */             
/*  82 */             menu.replaceExistingItem(31, (ItemStack)new CustomItem(new MaterialData(Material.PISTON_BASE), "&7偏移: &3" + offset + " 方块", new String[] { "", "&r左键点击: &7+0.1", "&r右键点击: &7-0.1" }));
/*  83 */             menu.addMenuClickHandler(31, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  87 */                     double offset = DoubleHandler.fixDouble(Double.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "offset")).doubleValue() + (arg3.isRightClicked() ? -0.1F : 0.1F));
/*  88 */                     BlockStorage.addBlockInfo(b, "offset", String.valueOf(offset));
/*  89 */                     WitherAssembler.null.this.newInstance(menu, b);
/*  90 */                     return false;
/*     */                   }
/*     */                 });
/*  93 */           } catch (Exception x) {
/*  94 */             x.printStackTrace();
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 100 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 105 */           if (flow.equals(ItemTransportFlow.INSERT)) return WitherAssembler.this.getInputSlots(); 
/* 106 */           return new int[0];
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/* 111 */           if (flow.equals(ItemTransportFlow.INSERT)) {
/* 112 */             if (SlimefunManager.isItemSimiliar(item, new ItemStack(Material.SOUL_SAND), true)) return WitherAssembler.this.getSoulSandSlots(); 
/* 113 */             return WitherAssembler.this.getWitherSkullSlots();
/*     */           } 
/* 115 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/* 119 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/* 123 */             BlockStorage.addBlockInfo(b, "offset", "3.0");
/* 124 */             BlockStorage.addBlockInfo(b, "enabled", "false");
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 129 */             if (reason.equals(UnregisterReason.EXPLODE)) return false; 
/* 130 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 131 */             if (inv != null) {
/* 132 */               for (int slot : WitherAssembler.this.getSoulSandSlots()) {
/* 133 */                 if (inv.getItemInSlot(slot) != null) {
/* 134 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 135 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 138 */               for (int slot : WitherAssembler.this.getWitherSkullSlots()) {
/* 139 */                 if (inv.getItemInSlot(slot) != null) {
/* 140 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 141 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 145 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 152 */     for (int i : border) {
/* 153 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 158 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 164 */     for (int i : border_1) {
/* 165 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 170 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 176 */     for (int i : border_2) {
/* 177 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)12), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 182 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 188 */     preset.addItem(1, (ItemStack)new CustomItem(new MaterialData(Material.SKULL_ITEM, (byte)1), "&7凋零头颅槽", new String[] { "", "&r这个槽位用于放置凋零头颅" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 193 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 198 */     preset.addItem(7, (ItemStack)new CustomItem(new MaterialData(Material.SOUL_SAND), "&7灵魂沙槽", new String[] { "", "&r这个槽位用于放置灵魂沙" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 203 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 208 */     preset.addItem(13, (ItemStack)new CustomItem(new MaterialData(Material.WATCH), "&7冷却: &b30 秒", new String[] { "", "&r这个机器需要半分钟的时间来作运转准备", "&r请耐心等待!" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 213 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/* 220 */     return "&5凋零组装机";
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 224 */     return new int[] { 19, 28, 25, 34 };
/*     */   }
/*     */   
/*     */   public int[] getWitherSkullSlots() {
/* 228 */     return new int[] { 19, 28 };
/*     */   }
/*     */   
/*     */   public int[] getSoulSandSlots() {
/* 232 */     return new int[] { 25, 34 };
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 237 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             
/*     */             public void tick(final Block b, SlimefunItem sf, Config data)
/*     */             {
/* 242 */               if (BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals("false"))
/* 243 */                 return;  if (WitherAssembler.lifetime % 60 == 0) {
/* 244 */                 if (ChargableBlock.getCharge(b) < WitherAssembler.this.getEnergyConsumption())
/*     */                   return; 
/* 246 */                 int soulsand = 0;
/* 247 */                 int skulls = 0;
/*     */                 
/* 249 */                 for (int slot : WitherAssembler.this.getSoulSandSlots()) {
/* 250 */                   if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.SOUL_SAND), true, SlimefunManager.DataType.ALWAYS)) {
/* 251 */                     soulsand += BlockStorage.getInventory(b).getItemInSlot(slot).getAmount();
/* 252 */                     if (soulsand > 3) {
/* 253 */                       soulsand = 4;
/*     */                       
/*     */                       break;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 259 */                 for (int slot : WitherAssembler.this.getWitherSkullSlots()) {
/* 260 */                   if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), (new MaterialData(Material.SKULL_ITEM, (byte)1)).toItemStack(1), true, SlimefunManager.DataType.ALWAYS)) {
/* 261 */                     skulls += BlockStorage.getInventory(b).getItemInSlot(slot).getAmount();
/* 262 */                     if (skulls > 2) {
/* 263 */                       skulls = 3;
/*     */                       
/*     */                       break;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 269 */                 if (soulsand > 3 && skulls > 2) {
/* 270 */                   for (int slot : WitherAssembler.this.getSoulSandSlots()) {
/* 271 */                     if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.SOUL_SAND), true, SlimefunManager.DataType.ALWAYS)) {
/* 272 */                       int amount = BlockStorage.getInventory(b).getItemInSlot(slot).getAmount();
/* 273 */                       if (amount >= soulsand) {
/* 274 */                         BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), soulsand));
/*     */                         
/*     */                         break;
/*     */                       } 
/* 278 */                       soulsand -= amount;
/* 279 */                       BlockStorage.getInventory(b).replaceExistingItem(slot, null);
/*     */                     } 
/*     */                   } 
/*     */ 
/*     */                   
/* 284 */                   for (int slot : WitherAssembler.this.getWitherSkullSlots()) {
/* 285 */                     if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), (new MaterialData(Material.SKULL_ITEM, (byte)1)).toItemStack(1), true, SlimefunManager.DataType.ALWAYS)) {
/* 286 */                       int amount = BlockStorage.getInventory(b).getItemInSlot(slot).getAmount();
/* 287 */                       if (amount >= skulls) {
/* 288 */                         BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), skulls));
/*     */                         
/*     */                         break;
/*     */                       } 
/* 292 */                       skulls -= amount;
/* 293 */                       BlockStorage.getInventory(b).replaceExistingItem(slot, null);
/*     */                     } 
/*     */                   } 
/*     */ 
/*     */                   
/* 298 */                   ChargableBlock.addCharge(b, -WitherAssembler.this.getEnergyConsumption());
/*     */                   
/* 300 */                   final double offset = Double.parseDouble(BlockStorage.getLocationInfo(b.getLocation(), "offset"));
/*     */                   
/* 302 */                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                       {
/*     */                         public void run()
/*     */                         {
/* 306 */                           b.getWorld().spawnEntity(new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + offset, b.getZ() + 0.5D), EntityType.WITHER);
/*     */                         }
/*     */                       });
/*     */                   return;
/*     */                 } 
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {
/*     */               WitherAssembler.lifetime++;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 322 */               return false;
/*     */             }
/*     */           } });
/*     */     
/* 326 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   public int getEnergyConsumption() {
/* 330 */     return 4096;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\WitherAssembler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */