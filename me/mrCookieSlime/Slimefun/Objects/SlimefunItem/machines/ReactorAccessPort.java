/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class ReactorAccessPort
/*     */   extends SlimefunItem
/*     */ {
/*  32 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 12, 13, 14, 21, 23 };
/*  33 */   private static final int[] border_1 = new int[] { 9, 10, 11, 18, 20, 27, 29, 36, 38, 45, 46, 47 };
/*  34 */   private static final int[] border_2 = new int[] { 15, 16, 17, 24, 26, 33, 35, 42, 44, 51, 52, 53 };
/*  35 */   private static final int[] border_3 = new int[] { 30, 31, 32, 39, 41, 48, 49, 50 };
/*     */   
/*     */   public ReactorAccessPort(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  38 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  40 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  44 */           ReactorAccessPort.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  53 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  58 */           if (flow.equals(ItemTransportFlow.INSERT)) return ReactorAccessPort.this.getInputSlots(); 
/*  59 */           return ReactorAccessPort.getOutputSlots();
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/*  64 */           if (flow.equals(ItemTransportFlow.INSERT)) {
/*  65 */             if (SlimefunManager.isItemSimiliar(item, SlimefunItems.REACTOR_COOLANT_CELL, true)) return ReactorAccessPort.this.getCoolantSlots(); 
/*  66 */             return ReactorAccessPort.this.getFuelSlots();
/*     */           } 
/*  68 */           return ReactorAccessPort.getOutputSlots();
/*     */         }
/*     */       };
/*     */     
/*  72 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  81 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  82 */             if (inv != null) {
/*  83 */               for (int slot : ReactorAccessPort.this.getFuelSlots()) {
/*  84 */                 if (inv.getItemInSlot(slot) != null) {
/*  85 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  86 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*  89 */               for (int slot : ReactorAccessPort.this.getCoolantSlots()) {
/*  90 */                 if (inv.getItemInSlot(slot) != null) {
/*  91 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  92 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*  95 */               for (int slot : ReactorAccessPort.getOutputSlots()) {
/*  96 */                 if (inv.getItemInSlot(slot) != null) {
/*  97 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  98 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 102 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 109 */     for (int i : border) {
/* 110 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 115 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 121 */     for (int i : border_1) {
/* 122 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 127 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 133 */     for (int i : border_2) {
/* 134 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 139 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 145 */     for (int i : border_3) {
/* 146 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)13), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 151 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 157 */     preset.addItem(1, (ItemStack)new CustomItem(SlimefunItems.URANIUM, "&7燃料槽", new String[] { "", "&r这个燃料槽可以放置放射性燃料:", "&2铀 &r或 &a镎" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 162 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 167 */     preset.addItem(22, (ItemStack)new CustomItem(SlimefunItems.PLUTONIUM, "&7副产品槽", new String[] { "", "&r这个槽位将收集放射性副产品", "&r例如 &a镎 &r或 &7钚" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 172 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 177 */     preset.addItem(7, (ItemStack)new CustomItem(SlimefunItems.REACTOR_COOLANT_CELL, "&b冷却槽", new String[] { "", "&r这个槽位用于放置冷却单元", "&4如果你任性地不放冷却单元", "&4那么你的反应堆就会BOOOOOM" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 182 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 187 */     preset.addItem(7, (ItemStack)new CustomItem(SlimefunItems.REACTOR_COOLANT_CELL, "&b冷却槽", new String[] { "", "&r这个槽位用于放置冷却单元", "&4如果你任性地不放冷却单元", "&4那么你的反应堆就会BOOOOOM" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 192 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/* 199 */     return "&2反应堆交互接口";
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 203 */     return new int[] { 19, 28, 37, 25, 34, 43 };
/*     */   }
/*     */   
/*     */   public int[] getFuelSlots() {
/* 207 */     return new int[] { 19, 28, 37 };
/*     */   }
/*     */   
/*     */   public int[] getCoolantSlots() {
/* 211 */     return new int[] { 25, 34, 43 };
/*     */   }
/*     */   
/*     */   public static int[] getOutputSlots() {
/* 215 */     return new int[] { 40 };
/*     */   }
/*     */   
/*     */   private static Inventory inject(Location l) {
/* 219 */     int size = BlockStorage.getInventory(l).toInventory().getSize();
/* 220 */     Inventory inv = Bukkit.createInventory(null, size);
/* 221 */     for (int i = 0; i < size; i++) {
/* 222 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 224 */     for (int slot : getOutputSlots()) {
/* 225 */       inv.setItem(slot, BlockStorage.getInventory(l).getItemInSlot(slot));
/*     */     }
/* 227 */     return inv;
/*     */   }
/*     */   
/*     */   public static ItemStack pushItems(Location l, ItemStack item) {
/* 231 */     Inventory inv = inject(l);
/* 232 */     Map<Integer, ItemStack> map = inv.addItem(new ItemStack[] { item });
/*     */     
/* 234 */     for (int slot : getOutputSlots()) {
/* 235 */       BlockStorage.getInventory(l).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */     
/* 238 */     Iterator<Map.Entry<Integer, ItemStack>> iterator = map.entrySet().iterator(); if (iterator.hasNext()) { Map.Entry<Integer, ItemStack> entry = iterator.next();
/* 239 */       return entry.getValue(); }
/*     */     
/* 241 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ReactorAccessPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */