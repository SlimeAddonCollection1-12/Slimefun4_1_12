/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class CargoCraftingNode
/*     */   extends SlimefunItem {
/*  27 */   private static final int[] border = new int[] { 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 22, 23, 24, 25, 26, 27, 31, 32, 33, 34, 35, 36, 40, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*     */   
/*     */   public CargoCraftingNode(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  30 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  32 */     new BlockMenuPreset(name, "&3合成输入接口")
/*     */       {
/*     */         public void init()
/*     */         {
/*  36 */           CargoCraftingNode.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*     */           try {
/*  43 */             menu.replaceExistingItem(41, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjI1OTliZDk4NjY1OWI4Y2UyYzQ5ODg1MjVjOTRlMTlkZGQzOWZhZDA4YTM4Mjg0YTE5N2YxYjcwNjc1YWNjIn19fQ=="), "&b频段号", new String[] { "", "&e> 点击 -1 频段号" }));
/*  44 */             menu.addMenuClickHandler(41, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  48 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) - 1;
/*  49 */                     if (channel < 0) channel = 15; 
/*  50 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/*  51 */                     CargoCraftingNode.null.this.newInstance(menu, b);
/*  52 */                     return false;
/*     */                   }
/*     */                 });
/*     */             
/*  56 */             menu.replaceExistingItem(42, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)((!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "frequency") == null) ? 0 : Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")))), "&b频段 ID: &3" + (((!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "frequency") == null) ? 0 : Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency"))) + 1), new String[0]));
/*  57 */             menu.addMenuClickHandler(42, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  61 */                     return false;
/*     */                   }
/*     */                 });
/*     */             
/*  65 */             menu.replaceExistingItem(43, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJmOTEwYzQ3ZGEwNDJlNGFhMjhhZjZjYzgxY2Y0OGFjNmNhZjM3ZGFiMzVmODhkYjk5M2FjY2I5ZGZlNTE2In19fQ=="), "&b频段号", new String[] { "", "&e> 点击 +1 频段号" }));
/*  66 */             menu.addMenuClickHandler(43, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  70 */                     int channel = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "frequency")) + 1;
/*  71 */                     if (channel > 15) channel = 0; 
/*  72 */                     BlockStorage.addBlockInfo(b, "frequency", String.valueOf(channel));
/*  73 */                     CargoCraftingNode.null.this.newInstance(menu, b);
/*  74 */                     return false;
/*     */                   }
/*     */                 });
/*     */           }
/*  78 */           catch (Exception e) {
/*  79 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  85 */           boolean open = (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b) || p.hasPermission("slimefun.cargo.bypass"));
/*  86 */           if (!open) {
/*  87 */             Messages.local.sendTranslation((CommandSender)p, "inventory.no-access", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */           }
/*  89 */           return open;
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  94 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/*  98 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/* 102 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/* 103 */             BlockStorage.addBlockInfo(b, "frequency", "0");
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 108 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 109 */             if (inv != null) {
/* 110 */               for (int slot : CargoCraftingNode.this.getInputSlots()) {
/* 111 */                 if (inv.getItemInSlot(slot) != null) {
/* 112 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 113 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/* 117 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 124 */     for (int i : border) {
/* 125 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 130 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 136 */     preset.addItem(2, (ItemStack)new CustomItem(new MaterialData(Material.WORKBENCH), "&e合成蓝本", new String[] { "", "&b放入合成方式示例(按合成方式摆放)" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 141 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 148 */     return new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\CargoCraftingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */