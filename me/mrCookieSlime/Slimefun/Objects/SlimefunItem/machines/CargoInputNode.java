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
/*     */ public class CargoInputNode
/*     */   extends SlimefunItem {
/*  29 */   private static final int[] border = new int[] { 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 17, 18, 22, 23, 26, 27, 31, 32, 33, 34, 35, 36, 40, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*     */   
/*     */   public CargoInputNode(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/*  32 */     super(category, item, name, recipeType, recipe, recipeOutput);
/*     */     
/*  34 */     new BlockMenuPreset(name, "&3输入接口")
/*     */       {
/*     */         public void init()
/*     */         {
/*  38 */           CargoInputNode.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*     */           try {
/*  45 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-type") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-type").equals("whitelist")) {
/*  46 */               menu.replaceExistingItem(15, (ItemStack)new CustomItem(new MaterialData(Material.WOOL), "&7类型: &r白名单", new String[] { "", "&e> 点击切换为黑名单" }));
/*  47 */               menu.addMenuClickHandler(15, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  51 */                       BlockStorage.addBlockInfo(b, "filter-type", "blacklist");
/*  52 */                       CargoInputNode.null.this.newInstance(menu, b);
/*  53 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/*  58 */               menu.replaceExistingItem(15, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)15), "&7类型: &8黑名单", new String[] { "", "&e> 点击切换为白名单" }));
/*  59 */               menu.addMenuClickHandler(15, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  63 */                       BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
/*  64 */                       CargoInputNode.null.this.newInstance(menu, b);
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
/*  77 */                       CargoInputNode.null.this.newInstance(menu, b);
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
/*  89 */                       CargoInputNode.null.this.newInstance(menu, b);
/*  90 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/*  95 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "round-robin") == null || BlockStorage.getLocationInfo(b.getLocation(), "round-robin").equals("false")) {
/*  96 */               menu.replaceExistingItem(24, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&7循环模式: &4✘", new String[] { "", "&e> 点击激活循环模式", "&e(物品将在频段中被均分)" }));
/*  97 */               menu.addMenuClickHandler(24, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 101 */                       BlockStorage.addBlockInfo(b, "round-robin", "true");
/* 102 */                       CargoInputNode.null.this.newInstance(menu, b);
/* 103 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/* 108 */               menu.replaceExistingItem(24, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&7循环模式: &2✔", new String[] { "", "&e> 点击关闭循环模式", "&e(物品将在频段中被均分)" }));
/* 109 */               menu.addMenuClickHandler(24, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 113 */                       BlockStorage.addBlockInfo(b, "round-robin", "false");
/* 114 */                       CargoInputNode.null.this.newInstance(menu, b);
/* 115 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/* 120 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-lore") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-lore").equals("true")) {
/* 121 */               menu.replaceExistingItem(25, (ItemStack)new CustomItem(new MaterialData(Material.EMPTY_MAP), "&7需要匹配的 说明(Lore): &2✔", new String[] { "", "&e> 点击修改需要匹配的Lore" }));
/* 122 */               menu.addMenuClickHandler(25, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 126 */                       BlockStorage.addBlockInfo(b, "filter-lore", "false");
/* 127 */                       CargoInputNode.null.this.newInstance(menu, b);
/* 128 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/* 133 */               menu.replaceExistingItem(25, (ItemStack)new CustomItem(new MaterialData(Material.EMPTY_MAP), "&7需要匹配的 说明(Lore): &4✘", new String[] { "", "&e> 点击修改需要匹配的Lore" }));
/* 134 */               menu.addMenuClickHandler(25, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 138 */                       BlockStorage.addBlockInfo(b, "filter-lore", "true");
/* 139 */                       CargoInputNode.null.this.newInstance(menu, b);
/* 140 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/* 145 */             menu.replaceExistingItem(41, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjI1OTliZDk4NjY1OWI4Y2UyYzQ5ODg1MjVjOTRlMTlkZGQzOWZhZDA4YTM4Mjg0YTE5N2YxYjcwNjc1YWNjIn19fQ=="), "&b频段号", new String[] { "", "&e> 点击 -1 频段号" }));
/* 146 */             menu.addMenuClickHandler(41, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/* 150 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) - 1;
/* 151 */                     if (channel < 0)
/* 152 */                       if (CargoNet.EXTRA_CHANNELS) { channel = 16; }
/* 153 */                       else { channel = 15; }
/*     */                        
/* 155 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/* 156 */                     CargoInputNode.null.this.newInstance(menu, b);
/* 157 */                     return false;
/*     */                   }
/*     */                 });
/*     */             
/* 161 */             int channel = (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "frequency") == null) ? 0 : Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency"));
/*     */             
/* 163 */             if (channel == 16) {
/* 164 */               menu.replaceExistingItem(42, (ItemStack)new CustomItem(SlimefunItems.CHEST_TERMINAL, "&b频段 ID: &3" + (channel + 1)));
/* 165 */               menu.addMenuClickHandler(42, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 169 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/* 174 */               menu.replaceExistingItem(42, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)channel), "&b频段 ID: &3" + (channel + 1), new String[0]));
/* 175 */               menu.addMenuClickHandler(42, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 179 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/* 184 */             menu.replaceExistingItem(43, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJmOTEwYzQ3ZGEwNDJlNGFhMjhhZjZjYzgxY2Y0OGFjNmNhZjM3ZGFiMzVmODhkYjk5M2FjY2I5ZGZlNTE2In19fQ=="), "&bChannel", new String[] { "", "&b频段号", "", "&e> 点击 +1 频段号" }));
/* 185 */             menu.addMenuClickHandler(43, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/* 189 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) + 1;
/*     */                     
/* 191 */                     if (CargoNet.EXTRA_CHANNELS)
/* 192 */                     { if (channel > 16) channel = 0;
/*     */                        }
/*     */                     
/* 195 */                     else if (channel > 15) { channel = 0; }
/*     */ 
/*     */                     
/* 198 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/* 199 */                     CargoInputNode.null.this.newInstance(menu, b);
/* 200 */                     return false;
/*     */                   }
/*     */                 });
/*     */           }
/* 204 */           catch (Exception e) {
/* 205 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 211 */           boolean open = (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b) || p.hasPermission("slimefun.cargo.bypass"));
/* 212 */           if (!open) {
/* 213 */             Messages.local.sendTranslation((CommandSender)p, "inventory.no-access", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */           }
/* 215 */           return open;
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 220 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/* 224 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/* 228 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/* 229 */             BlockStorage.addBlockInfo(b, "index", "0");
/* 230 */             BlockStorage.addBlockInfo(b, "frequency", "0");
/* 231 */             BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
/* 232 */             BlockStorage.addBlockInfo(b, "filter-lore", "true");
/* 233 */             BlockStorage.addBlockInfo(b, "filter-durability", "false");
/* 234 */             BlockStorage.addBlockInfo(b, "round-robin", "false");
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 239 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 240 */             if (inv != null) {
/* 241 */               for (int slot : CargoInputNode.this.getInputSlots()) {
/* 242 */                 if (inv.getItemInSlot(slot) != null) {
/* 243 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 244 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/* 248 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 255 */     for (int i : border) {
/* 256 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 261 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 267 */     preset.addItem(2, (ItemStack)new CustomItem(new MaterialData(Material.PAPER), "&3物品", new String[] { "", "&b将你想要的所有物品放入", "&b黑名单/白名单" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 272 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 279 */     return new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\CargoInputNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */