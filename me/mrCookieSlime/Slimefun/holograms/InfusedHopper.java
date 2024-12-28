/*    */ package me.mrCookieSlime.Slimefun.holograms;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.ArmorStandFactory;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class InfusedHopper
/*    */ {
/*    */   private static final double offset = 1.2D;
/*    */   
/*    */   public static ArmorStand getArmorStand(Block hopper, boolean createIfNoneFound) {
/* 15 */     Location l = new Location(hopper.getWorld(), hopper.getX() + 0.5D, hopper.getY() + 1.2D, hopper.getZ() + 0.5D);
/*    */     
/* 17 */     for (Entity n : l.getChunk().getEntities()) {
/* 18 */       if (n instanceof ArmorStand && 
/* 19 */         n.getCustomName() == null && l.distanceSquared(n.getLocation()) < 0.4D) return (ArmorStand)n;
/*    */     
/*    */     } 
/*    */     
/* 23 */     if (!createIfNoneFound) {
/* 24 */       return null;
/*    */     }
/*    */     
/* 27 */     ArmorStand hologram = ArmorStandFactory.createHidden(l);
/* 28 */     hologram.setCustomNameVisible(false);
/* 29 */     hologram.setCustomName(null);
/* 30 */     return hologram;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\holograms\InfusedHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */