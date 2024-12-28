/*    */ package me.mrCookieSlime.Slimefun.holograms;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
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
/*    */ public class EnergyHologram
/*    */ {
/*    */   public static void update(Block b, double supply, double demand) {
/* 17 */     update(b, (demand > supply) ? ("&4&l- &c" + DoubleHandler.getFancyDouble(Math.abs(supply - demand)) + " &7J &e⚡") : ("&2&l+ &a" + DoubleHandler.getFancyDouble(supply - demand) + " &7J &e⚡"));
/*    */   }
/*    */   
/*    */   public static void update(final Block b, final String name) {
/* 21 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 25 */             ArmorStand hologram = EnergyHologram.getArmorStand(b);
/* 26 */             hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public static void remove(final Block b) {
/* 32 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 36 */             ArmorStand hologram = EnergyHologram.getArmorStand(b);
/* 37 */             hologram.remove();
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   private static ArmorStand getArmorStand(Block b) {
/* 43 */     Location l = new Location(b.getWorld(), b.getX() + 0.5D, (b.getY() - 0.7F), b.getZ() + 0.5D);
/*    */     
/* 45 */     for (Entity n : l.getChunk().getEntities()) {
/* 46 */       if (n instanceof ArmorStand && 
/* 47 */         n.getCustomName() != null && l.distanceSquared(n.getLocation()) < 0.4D) return (ArmorStand)n;
/*    */     
/*    */     } 
/*    */     
/* 51 */     ArmorStand hologram = ArmorStandFactory.createHidden(l);
/* 52 */     return hologram;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\holograms\EnergyHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */