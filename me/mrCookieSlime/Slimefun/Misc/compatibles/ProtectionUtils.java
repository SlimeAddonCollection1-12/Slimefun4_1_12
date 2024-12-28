/*    */ package me.mrCookieSlime.Slimefun.Misc.compatibles;
/*    */ 
/*    */ import com.bekvon.bukkit.residence.Residence;
/*    */ import com.bekvon.bukkit.residence.protection.ClaimedResidence;
/*    */ import com.intellectualcrafters.plot.object.Location;
/*    */ import com.intellectualcrafters.plot.object.Plot;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ProtectionUtils
/*    */ {
/*    */   public static boolean canAccessItem(Player p, Block b) {
/* 14 */     if (p.isOp()) {
/* 15 */       return true;
/*    */     }
/* 17 */     if (SlimefunStartup.instance.isPlotSquaredInstalled()) {
/* 18 */       Location plotLoc = new Location(b.getLocation().getWorld().getName(), b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ());
/* 19 */       Plot plot = Plot.getPlot(plotLoc);
/* 20 */       if (plot != null && 
/* 21 */         !plot.isAdded(p.getUniqueId())) {
/* 22 */         p.sendMessage("§8[§e远古工艺§8] §c抱歉，你不可以在无权使用的地皮上使用机器. 请联系地皮主人给你权限吧！");
/* 23 */         return false;
/*    */       } 
/*    */     } 
/*    */     
/* 27 */     if (SlimefunStartup.instance.isResidenceInstalled()) {
/* 28 */       ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(b.getLocation());
/* 29 */       if (res != null && 
/* 30 */         !p.hasPermission("residence.bypass.use") && 
/* 31 */         !res.getPermissions().playerHas(p.getName(), p.getWorld().getName(), "sf-machines", true) && !res.getPermissions().has("sf-machines", true)) {
/* 32 */         p.sendMessage("§8[§b远古工艺§8] §c你需要这个领地的§esf-machines§c标识§8(flag)§c才能这么做");
/* 33 */         return false;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean canBuild(Player player, Block block) {
/* 42 */     return canBuild(player, block, true);
/*    */   }
/*    */   
/*    */   public static boolean canBuild(Player player, Block block, boolean notify) {
/* 46 */     if (player.isOp()) {
/* 47 */       return true;
/*    */     }
/* 49 */     if (SlimefunStartup.instance.isPlotSquaredInstalled()) {
/* 50 */       Location plotLoc = new Location(block.getLocation().getWorld().getName(), block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());
/* 51 */       Plot plot = Plot.getPlot(plotLoc);
/* 52 */       if (plot != null && 
/* 53 */         !plot.isAdded(player.getUniqueId())) {
/* 54 */         if (notify) {
/* 55 */           player.sendMessage("§8[§e远古工艺§8] §c抱歉，你不可以在这里建造或破坏. 请联系地皮主人给你权限吧！");
/*    */         }
/* 57 */         return false;
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     if (SlimefunStartup.instance.isResidenceInstalled()) {
/* 62 */       ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(block.getLocation());
/* 63 */       if (res != null && 
/* 64 */         !player.hasPermission("residence.bypass.use") && 
/* 65 */         !res.getPermissions().playerHas(player.getName(), player.getWorld().getName(), "destroy", true)) {
/* 66 */         if (notify) {
/* 67 */           player.sendMessage("§8[§b远古工艺§8] §c你不可以在这里建造或破坏");
/*    */         }
/* 69 */         return false;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Misc\compatibles\ProtectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */