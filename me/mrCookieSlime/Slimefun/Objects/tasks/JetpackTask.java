/*    */ package me.mrCookieSlime.Slimefun.Objects.tasks;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*    */ import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ public class JetpackTask
/*    */   implements Runnable
/*    */ {
/*    */   UUID uuid;
/*    */   double thrust;
/*    */   int id;
/*    */   
/*    */   public JetpackTask(Player p, double thrust) {
/* 21 */     this.uuid = p.getUniqueId();
/* 22 */     this.thrust = thrust;
/*    */   }
/*    */   
/*    */   public void setID(int id) {
/* 26 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 31 */     if (Bukkit.getPlayer(this.uuid) == null) { Bukkit.getScheduler().cancelTask(this.id); }
/* 32 */     else if (Bukkit.getPlayer(this.uuid).isDead()) { Bukkit.getScheduler().cancelTask(this.id); }
/* 33 */     else if (!Bukkit.getPlayer(this.uuid).isSneaking()) { Bukkit.getScheduler().cancelTask(this.id); }
/*    */     else
/* 35 */     { Player p = Bukkit.getPlayer(this.uuid);
/* 36 */       float cost = 0.08F;
/* 37 */       float charge = ItemEnergy.getStoredEnergy(p.getInventory().getChestplate());
/* 38 */       if (charge >= cost) {
/* 39 */         p.getInventory().setChestplate(ItemEnergy.chargeItem(p.getInventory().getChestplate(), -cost));
/* 40 */         PlayerInventory.update(p);
/*    */         
/* 42 */         p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.25F, 1.0F);
/* 43 */         p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 1, 1);
/* 44 */         p.setFallDistance(0.0F);
/* 45 */         Vector vector = new Vector(0, 1, 0);
/* 46 */         vector.multiply(this.thrust);
/* 47 */         vector.add(p.getEyeLocation().getDirection().multiply(0.2F));
/*    */         
/* 49 */         p.setVelocity(vector);
/*    */       } else {
/* 51 */         Bukkit.getScheduler().cancelTask(this.id);
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\tasks\JetpackTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */