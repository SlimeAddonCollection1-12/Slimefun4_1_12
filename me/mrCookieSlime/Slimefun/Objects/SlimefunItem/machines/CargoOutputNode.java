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
/*     */ public class CargoOutputNode
/*     */   extends SlimefunItem {
/*  29 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
/*     */   
/*     */   public CargoOutputNode(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/*  32 */     super(category, item, name, recipeType, recipe, recipeOutput);
/*     */     
/*  34 */     new BlockMenuPreset(name, "&6输出节点")
/*     */       {
/*     */         public void init()
/*     */         {
/*  38 */           CargoOutputNode.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*     */           try {
/*  46 */             menu.replaceExistingItem(12, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjI1OTliZDk4NjY1OWI4Y2UyYzQ5ODg1MjVjOTRlMTlkZGQzOWZhZDA4YTM4Mjg0YTE5N2YxYjcwNjc1YWNjIn19fQ=="), "&b频段", new String[] { "", "&e> 点击 -1 频段号" }));
/*  47 */             menu.addMenuClickHandler(12, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  51 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) - 1;
/*  52 */                     if (channel < 0)
/*  53 */                       if (CargoNet.EXTRA_CHANNELS) { channel = 16; }
/*  54 */                       else { channel = 15; }
/*     */                        
/*  56 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/*  57 */                     CargoOutputNode.null.this.newInstance(menu, b);
/*  58 */                     return false;
/*     */                   }
/*     */                 });
/*     */             
/*  62 */             int channel = (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "frequency") == null) ? 0 : Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency"));
/*     */             
/*  64 */             if (channel == 16) {
/*  65 */               menu.replaceExistingItem(13, (ItemStack)new CustomItem(SlimefunItems.CHEST_TERMINAL, "&b频段 ID: &3" + (channel + 1)));
/*  66 */               menu.addMenuClickHandler(13, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  70 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/*  75 */               menu.replaceExistingItem(13, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)channel), "&b频段 ID: &3" + (channel + 1), new String[0]));
/*  76 */               menu.addMenuClickHandler(13, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  80 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/*     */             
/*  85 */             menu.replaceExistingItem(14, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJmOTEwYzQ3ZGEwNDJlNGFhMjhhZjZjYzgxY2Y0OGFjNmNhZjM3ZGFiMzVmODhkYjk5M2FjY2I5ZGZlNTE2In19fQ=="), "&b频段", new String[] { "", "&e> 点击 +1 频段号1" }));
/*  86 */             menu.addMenuClickHandler(14, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  90 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) + 1;
/*     */                     
/*  92 */                     if (CargoNet.EXTRA_CHANNELS)
/*  93 */                     { if (channel > 16) channel = 0;
/*     */                        }
/*     */                     
/*  96 */                     else if (channel > 15) { channel = 0; }
/*     */ 
/*     */                     
/*  99 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/* 100 */                     CargoOutputNode.null.this.newInstance(menu, b);
/* 101 */                     return false;
/*     */                   }
/*     */                 });
/*     */           }
/* 105 */           catch (Exception e) {
/* 106 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 112 */           boolean open = (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b) || p.hasPermission("slimefun.cargo.bypass"));
/* 113 */           if (!open) {
/* 114 */             Messages.local.sendTranslation((CommandSender)p, "inventory.no-access", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */           }
/* 116 */           return open;
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 121 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/* 125 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/* 129 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/* 130 */             BlockStorage.addBlockInfo(b, "frequency", "0");
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 135 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 142 */     for (int i : border) {
/* 143 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 148 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\CargoOutputNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */