/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.CargoNet;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class AdvancedCargoOutputNode
/*     */   extends SlimefunItem {
/*  29 */   private static final int[] border = new int[] { 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 17, 18, 22, 23, 24, 26, 27, 31, 32, 33, 34, 35, 36, 40, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*     */   
/*     */   public AdvancedCargoOutputNode(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/*  32 */     super(category, item, name, recipeType, recipe, recipeOutput);
/*     */     
/*  34 */     new BlockMenuPreset(name, "&c输出节点")
/*     */       {
/*     */         public void init()
/*     */         {
/*  38 */           AdvancedCargoOutputNode.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*     */           try {
/*  45 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-type") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-type").equals("whitelist")) {
/*  46 */               menu.replaceExistingItem(15, (ItemStack)new CustomItem(new MaterialData(Material.WOOL), "&7类型: &r白名单", new String[] { "", "&e> 点击修改为黑名单" }));
/*  47 */               menu.addMenuClickHandler(15, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  51 */                       BlockStorage.addBlockInfo(b, "filter-type", "blacklist");
/*  52 */                       AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/*  53 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/*  58 */               menu.replaceExistingItem(15, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)15), "&7类型: &8黑名单", new String[] { "", "&e> 点击修改为白名单" }));
/*  59 */               menu.addMenuClickHandler(15, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  63 */                       BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
/*  64 */                       AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/*  65 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/*  70 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-durability") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-durability").equals("false")) {
/*  71 */               menu.replaceExistingItem(16, (ItemStack)new CustomItem(new MaterialData(Material.STONE_SWORD, (byte)20), "&7需要匹配的 子ID/耐久值: &4✘", new String[] { "", "&e> 点击修改需要匹配的耐久值" }));
/*  72 */               menu.addMenuClickHandler(16, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  76 */                       BlockStorage.addBlockInfo(b, "filter-durability", "true");
/*  77 */                       AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/*  78 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/*  83 */               menu.replaceExistingItem(16, (ItemStack)new CustomItem(new MaterialData(Material.GOLD_SWORD, (byte)20), "&7需要匹配的 子ID/耐久值: &2✔", new String[] { "", "&e> 点击修改需要匹配的耐久值" }));
/*  84 */               menu.addMenuClickHandler(16, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  88 */                       BlockStorage.addBlockInfo(b, "filter-durability", "false");
/*  89 */                       AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/*  90 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/*  95 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-lore") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-lore").equals("true")) {
/*  96 */               menu.replaceExistingItem(25, (ItemStack)new CustomItem(new MaterialData(Material.EMPTY_MAP), "&7需要匹配的 说明(Lore): &2✔", new String[] { "", "&e> 点击修改需要匹配的Lore" }));
/*  97 */               menu.addMenuClickHandler(25, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 101 */                       BlockStorage.addBlockInfo(b, "filter-lore", "false");
/* 102 */                       AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/* 103 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/* 108 */               menu.replaceExistingItem(25, (ItemStack)new CustomItem(new MaterialData(Material.EMPTY_MAP), "&7需要匹配的 说明(Lore): &4✘", new String[] { "", "&e> 点击修改需要匹配的Lore" }));
/* 109 */               menu.addMenuClickHandler(25, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 113 */                       BlockStorage.addBlockInfo(b, "filter-lore", "true");
/* 114 */                       AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/* 115 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/* 120 */             menu.replaceExistingItem(41, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjI1OTliZDk4NjY1OWI4Y2UyYzQ5ODg1MjVjOTRlMTlkZGQzOWZhZDA4YTM4Mjg0YTE5N2YxYjcwNjc1YWNjIn19fQ=="), "&b频段号", new String[] { "", "&e> 点击 -1 频段号" }));
/* 121 */             menu.addMenuClickHandler(41, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/* 125 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) - 1;
/* 126 */                     if (channel < 0)
/* 127 */                       if (CargoNet.EXTRA_CHANNELS) { channel = 16; }
/* 128 */                       else { channel = 15; }
/*     */                        
/* 130 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/* 131 */                     AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/* 132 */                     return false;
/*     */                   }
/*     */                 });
/*     */             
/* 136 */             int channel = (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "frequency") == null) ? 0 : Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency"));
/*     */             
/* 138 */             if (channel == 16) {
/* 139 */               menu.replaceExistingItem(42, (ItemStack)new CustomItem(SlimefunItems.CHEST_TERMINAL, "&b频段 ID: &3" + (channel + 1)));
/* 140 */               menu.addMenuClickHandler(42, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 144 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/* 149 */               menu.replaceExistingItem(42, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)channel), "&b频段 ID: &3" + (channel + 1), new String[0]));
/* 150 */               menu.addMenuClickHandler(42, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 154 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/* 159 */             menu.replaceExistingItem(43, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJmOTEwYzQ3ZGEwNDJlNGFhMjhhZjZjYzgxY2Y0OGFjNmNhZjM3ZGFiMzVmODhkYjk5M2FjY2I5ZGZlNTE2In19fQ=="), "&b频段号", new String[] { "", "&e> 点击 +1 频段号" }));
/* 160 */             menu.addMenuClickHandler(43, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/* 164 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) + 1;
/*     */                     
/* 166 */                     if (CargoNet.EXTRA_CHANNELS)
/* 167 */                     { if (channel > 16) channel = 0;
/*     */                        }
/*     */                     
/* 170 */                     else if (channel > 15) { channel = 0; }
/*     */ 
/*     */                     
/* 173 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/* 174 */                     AdvancedCargoOutputNode.null.this.newInstance(menu, b);
/* 175 */                     return false;
/*     */                   }
/*     */                 });
/*     */           }
/* 179 */           catch (Exception e) {
/* 180 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 186 */           boolean open = (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b) || p.hasPermission("slimefun.cargo.bypass"));
/* 187 */           if (!open) {
/* 188 */             Messages.local.sendTranslation((CommandSender)p, "inventory.no-access", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */           }
/* 190 */           return open;
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 195 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/* 199 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/* 203 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/* 204 */             BlockStorage.addBlockInfo(b, "index", "0");
/* 205 */             BlockStorage.addBlockInfo(b, "frequency", "0");
/* 206 */             BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
/* 207 */             BlockStorage.addBlockInfo(b, "filter-lore", "true");
/* 208 */             BlockStorage.addBlockInfo(b, "filter-durability", "false");
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 213 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 214 */             if (inv != null) {
/* 215 */               for (int slot : AdvancedCargoOutputNode.this.getInputSlots()) {
/* 216 */                 if (inv.getItemInSlot(slot) != null) {
/* 217 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 218 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/* 222 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 229 */     for (int i : border) {
/* 230 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 235 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 241 */     preset.addItem(2, (ItemStack)new CustomItem(new MaterialData(Material.PAPER), "&3物品", new String[] { "", "&b将你想要的所有物品放入", "&b黑名单/白名单" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 246 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 253 */     return new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\AdvancedCargoOutputNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */