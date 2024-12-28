/*    */ package me.mrCookieSlime.Slimefun.Objects.tasks;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class ParachuteTask
/*    */   implements Runnable
/*    */ {
/*    */   UUID uuid;
/*    */   int id;
/*    */   
/*    */   public ParachuteTask(Player p) {
/* 15 */     this.uuid = p.getUniqueId();
/*    */   }
/*    */   
/*    */   public void setID(int id) {
/* 19 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 24 */     if (Bukkit.getPlayer(this.uuid) == null) { Bukkit.getScheduler().cancelTask(this.id); }
/* 25 */     else if (Bukkit.getPlayer(this.uuid).isDead()) { Bukkit.getScheduler().cancelTask(this.id); }
/* 26 */     else if (!Bukkit.getPlayer(this.uuid).isSneaking()) { Bukkit.getScheduler().cancelTask(this.id); }
/*    */     else
/* 28 */     { Player p = Bukkit.getPlayer(this.uuid);
/* 29 */       Vector vector = new Vector(0, 1, 0);
/* 30 */       vector.multiply(-0.1D);
/* 31 */       p.setVelocity(vector);
/* 32 */       p.setFallDistance(0.0F);
/* 33 */       if (!p.isSneaking()) Bukkit.getScheduler().cancelTask(this.id);  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\tasks\ParachuteTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */