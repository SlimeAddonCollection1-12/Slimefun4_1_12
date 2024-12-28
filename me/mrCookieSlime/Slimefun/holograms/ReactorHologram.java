/*    */ package me.mrCookieSlime.Slimefun.holograms;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.ArmorStandFactory;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class ReactorHologram
/*    */ {
/*    */   public static ArmorStand getArmorStand(Location reactor) {
/* 16 */     Location l = new Location(reactor.getWorld(), reactor.getX() + 0.5D, reactor.getY(), reactor.getZ() + 0.5D);
/*    */     
/* 18 */     for (Entity n : l.getChunk().getEntities()) {
/* 19 */       if (n instanceof ArmorStand && 
/* 20 */         l.distanceSquared(n.getLocation()) < 0.4D) return (ArmorStand)n;
/*    */     
/*    */     } 
/*    */     
/* 24 */     ArmorStand hologram = ArmorStandFactory.createHidden(l);
/* 25 */     hologram.setCustomNameVisible(false);
/* 26 */     hologram.setCustomName(null);
/* 27 */     return hologram;
/*    */   }
/*    */   
/*    */   public static void update(final Location l, final String name) {
/* 31 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 35 */             ArmorStand hologram = ReactorHologram.getArmorStand(l);
/* 36 */             if (!hologram.isCustomNameVisible()) hologram.setCustomNameVisible(true); 
/* 37 */             hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public static void remove(Location l) {
/* 43 */     ArmorStand hologram = getArmorStand(l);
/* 44 */     hologram.remove();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\holograms\ReactorHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */