/*    */ package me.mrCookieSlime.Slimefun.Objects.tasks;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.UUID;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ public class JetBootsTask
/*    */   implements Runnable
/*    */ {
/*    */   UUID uuid;
/*    */   double speed;
/*    */   int id;
/*    */   
/*    */   public JetBootsTask(Player p, double speed) {
/* 23 */     this.uuid = p.getUniqueId();
/* 24 */     this.speed = speed;
/*    */   }
/*    */   
/*    */   public void setID(int id) {
/* 28 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 33 */     if (Bukkit.getPlayer(this.uuid) == null) { Bukkit.getScheduler().cancelTask(this.id); }
/* 34 */     else if (Bukkit.getPlayer(this.uuid).isDead()) { Bukkit.getScheduler().cancelTask(this.id); }
/* 35 */     else if (!Bukkit.getPlayer(this.uuid).isSneaking()) { Bukkit.getScheduler().cancelTask(this.id); }
/*    */     else
/* 37 */     { Player p = Bukkit.getPlayer(this.uuid);
/* 38 */       float cost = 0.075F;
/* 39 */       float charge = ItemEnergy.getStoredEnergy(p.getInventory().getBoots());
/* 40 */       double accuracy = Double.valueOf((new DecimalFormat("##.##")).format(this.speed - 0.7D).replace(",", ".")).doubleValue();
/* 41 */       if (charge >= cost) {
/* 42 */         p.getInventory().setBoots(ItemEnergy.chargeItem(p.getInventory().getBoots(), -cost));
/* 43 */         PlayerInventory.update(p);
/*    */         
/* 45 */         p.getWorld().playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 0.25F, 1.0F);
/* 46 */         p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 1, 1);
/* 47 */         p.setFallDistance(0.0F);
/* 48 */         double gravity = 0.04D;
/* 49 */         double offset = SlimefunStartup.chance(100, 50) ? accuracy : -accuracy;
/* 50 */         Vector vector = new Vector(p.getEyeLocation().getDirection().getX() * this.speed + offset, gravity, p.getEyeLocation().getDirection().getZ() * this.speed - offset);
/*    */         
/* 52 */         p.setVelocity(vector);
/*    */       } else {
/* 54 */         Bukkit.getScheduler().cancelTask(this.id);
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\tasks\JetBootsTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */