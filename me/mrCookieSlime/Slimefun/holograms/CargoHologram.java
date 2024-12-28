/*    */ package me.mrCookieSlime.Slimefun.holograms;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.ArmorStandFactory;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class CargoHologram
/*    */ {
/*    */   public static void update(final Block b, final String name) {
/* 16 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 20 */             ArmorStand hologram = CargoHologram.getArmorStand(b);
/* 21 */             hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public static void remove(Block b) {
/* 27 */     ArmorStand hologram = getArmorStand(b);
/* 28 */     hologram.remove();
/*    */   }
/*    */   
/*    */   private static ArmorStand getArmorStand(Block b) {
/* 32 */     Location l = new Location(b.getWorld(), b.getX() + 0.5D, (b.getY() - 0.7F), b.getZ() + 0.5D);
/*    */     
/* 34 */     for (Entity n : l.getChunk().getEntities()) {
/* 35 */       if (n instanceof ArmorStand && 
/* 36 */         n.getCustomName() != null && l.distanceSquared(n.getLocation()) < 0.4D) return (ArmorStand)n;
/*    */     
/*    */     } 
/*    */     
/* 40 */     ArmorStand hologram = ArmorStandFactory.createHidden(l);
/* 41 */     return hologram;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\holograms\CargoHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */