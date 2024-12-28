/*    */ package me.mrCookieSlime.Slimefun.holograms;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.MenuHelper;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.ArmorStandFactory;
/*    */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Projector
/*    */ {
/*    */   public static ArmorStand getArmorStand(Block projector) {
/* 27 */     String nametag = ChatColor.translateAlternateColorCodes('&', BlockStorage.getLocationInfo(projector.getLocation(), "text"));
/* 28 */     double offset = Double.valueOf(BlockStorage.getLocationInfo(projector.getLocation(), "offset")).doubleValue();
/* 29 */     Location l = new Location(projector.getWorld(), projector.getX() + 0.5D, projector.getY() + offset, projector.getZ() + 0.5D);
/*    */     
/* 31 */     for (Entity n : l.getChunk().getEntities()) {
/* 32 */       if (n instanceof ArmorStand && 
/* 33 */         n.getCustomName() != null && n.getCustomName().equals(nametag) && l.distanceSquared(n.getLocation()) < 0.4D) return (ArmorStand)n;
/*    */     
/*    */     } 
/*    */     
/* 37 */     ArmorStand hologram = ArmorStandFactory.createHidden(l);
/* 38 */     hologram.setCustomName(nametag);
/* 39 */     return hologram;
/*    */   }
/*    */   
/*    */   public static void openEditor(Player p, final Block projector) {
/* 43 */     ChestMenu menu = new ChestMenu("全息设置");
/*    */     
/* 45 */     menu.addItem(0, (ItemStack)new CustomItem(new MaterialData(Material.NAME_TAG), "&7文本 &e(点击修改)", new String[] { "", "&r" + ChatColor.translateAlternateColorCodes('&', BlockStorage.getLocationInfo(projector.getLocation(), "text")) }));
/* 46 */     menu.addMenuClickHandler(0, new ChestMenu.MenuClickHandler()
/*    */         {
/*    */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*    */           {
/* 50 */             p.closeInventory();
/* 51 */             Messages.local.sendTranslation((CommandSender)p, "machines.HOLOGRAM_PROJECTOR.enter-text", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 52 */             MenuHelper.awaitChatInput(p, new MenuHelper.ChatHandler()
/*    */                 {
/*    */                   public boolean onChat(Player p, String message)
/*    */                   {
/* 56 */                     ArmorStand hologram = Projector.getArmorStand(projector);
/*    */                     
/* 58 */                     String holoMsg = ChatColor.translateAlternateColorCodes('&', message);
/* 59 */                     if (ChatColor.stripColor(holoMsg).length() > 30) {
/* 60 */                       p.sendMessage("§c你输入的文本超长");
/* 61 */                       return false;
/*    */                     } 
/* 63 */                     hologram.setCustomName(holoMsg);
/* 64 */                     BlockStorage.addBlockInfo(projector, "text", message);
/* 65 */                     Projector.openEditor(p, projector);
/* 66 */                     return false;
/*    */                   }
/*    */                 });
/* 69 */             return false;
/*    */           }
/*    */         });
/*    */     
/* 73 */     menu.addItem(1, (ItemStack)new CustomItem(new MaterialData(Material.WATCH), "&7偏移量: &e" + DoubleHandler.fixDouble(Double.valueOf(BlockStorage.getLocationInfo(projector.getLocation(), "offset")).doubleValue() + 1.0D), new String[] { "", "&r左键点击: &7+0.1", "&r右键点击: &7-0.1" }));
/* 74 */     menu.addMenuClickHandler(1, new ChestMenu.MenuClickHandler()
/*    */         {
/*    */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*    */           {
/* 78 */             double offset = DoubleHandler.fixDouble(Double.valueOf(BlockStorage.getLocationInfo(projector.getLocation(), "offset")).doubleValue() + (arg3.isRightClicked() ? -0.1F : 0.1F));
/* 79 */             ArmorStand hologram = Projector.getArmorStand(projector);
/* 80 */             Location l = new Location(projector.getWorld(), projector.getX() + 0.5D, projector.getY() + offset, projector.getZ() + 0.5D);
/*    */             
/* 82 */             if (l.distance(projector.getLocation()) > 3.0D) {
/* 83 */               p.sendMessage("§c你设置的全息新位置离投射装置太远了！");
/* 84 */               return false;
/*    */             } 
/* 86 */             hologram.teleport(l);
/* 87 */             BlockStorage.addBlockInfo(projector, "offset", String.valueOf(offset));
/* 88 */             Projector.openEditor(p, projector);
/* 89 */             return false;
/*    */           }
/*    */         });
/*    */     
/* 93 */     menu.open(new Player[] { p });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\holograms\Projector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */