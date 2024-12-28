/*    */ package me.mrCookieSlime.Slimefun.holograms;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.ArmorStandFactory;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AndroidStatusHologram
/*    */ {
/*    */   private static final double offset = 1.2D;
/*    */   
/*    */   public static void update(Block b, String name) {
/* 17 */     ArmorStand hologram = getArmorStand(b);
/* 18 */     hologram.setCustomName(name);
/*    */   }
/*    */   
/*    */   public static void remove(Block b) {
/* 22 */     ArmorStand hologram = getArmorStand(b);
/* 23 */     hologram.remove();
/*    */   }
/*    */   
/*    */   public static List<Entity> getNearbyEntities(Block b, double radius) {
/* 27 */     ArmorStand hologram = getArmorStand(b);
/* 28 */     return hologram.getNearbyEntities(radius, 1.0D, radius);
/*    */   }
/*    */   
/*    */   public static List<Entity> getNearbyEntities(Block b, double radius, double y) {
/* 32 */     ArmorStand hologram = getArmorStand(b);
/* 33 */     return hologram.getNearbyEntities(radius, y, radius);
/*    */   }
/*    */   
/*    */   private static ArmorStand getArmorStand(Block b) {
/* 37 */     Location l = new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + 1.2D, b.getZ() + 0.5D);
/*    */     
/* 39 */     for (Entity n : l.getChunk().getEntities()) {
/* 40 */       if (n instanceof ArmorStand && 
/* 41 */         n.getCustomName() == null && l.distanceSquared(n.getLocation()) < 0.4D) return (ArmorStand)n;
/*    */     
/*    */     } 
/*    */     
/* 45 */     ArmorStand hologram = ArmorStandFactory.createHidden(l);
/* 46 */     hologram.setCustomNameVisible(false);
/* 47 */     hologram.setCustomName(null);
/* 48 */     return hologram;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\holograms\AndroidStatusHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */