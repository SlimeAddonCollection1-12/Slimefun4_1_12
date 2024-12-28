/*    */ package me.mrCookieSlime.Slimefun.Objects.tasks;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class MagnetTask
/*    */   implements Runnable
/*    */ {
/*    */   UUID uuid;
/*    */   int id;
/*    */   
/*    */   public MagnetTask(Player p) {
/* 17 */     this.uuid = p.getUniqueId();
/*    */   }
/*    */   
/*    */   public void setID(int id) {
/* 21 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 26 */     if (Bukkit.getPlayer(this.uuid) == null) { Bukkit.getScheduler().cancelTask(this.id); }
/* 27 */     else if (Bukkit.getPlayer(this.uuid).isDead()) { Bukkit.getScheduler().cancelTask(this.id); }
/* 28 */     else if (!Bukkit.getPlayer(this.uuid).isSneaking()) { Bukkit.getScheduler().cancelTask(this.id); }
/*    */     else
/* 30 */     { for (Entity item : Bukkit.getPlayer(this.uuid).getNearbyEntities(6.0D, 6.0D, 6.0D)) {
/* 31 */         if (item instanceof Item && 
/* 32 */           !item.hasMetadata("no_pickup") && ((Item)item).getPickupDelay() <= 0) {
/* 33 */           item.teleport(Bukkit.getPlayer(this.uuid).getEyeLocation());
/* 34 */           Bukkit.getPlayer(this.uuid).getWorld().playSound(Bukkit.getPlayer(this.uuid).getEyeLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 5.0F, 2.0F);
/*    */         } 
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\tasks\MagnetTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */