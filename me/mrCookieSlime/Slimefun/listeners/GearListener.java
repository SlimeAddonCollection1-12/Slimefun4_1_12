/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.JetBoots;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Jetpack;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.tasks.JetBootsTask;
/*    */ import me.mrCookieSlime.Slimefun.Objects.tasks.JetpackTask;
/*    */ import me.mrCookieSlime.Slimefun.Objects.tasks.MagnetTask;
/*    */ import me.mrCookieSlime.Slimefun.Objects.tasks.ParachuteTask;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class GearListener
/*    */   implements Listener {
/*    */   public GearListener(SlimefunStartup plugin) {
/* 22 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onToggleSneak(PlayerToggleSneakEvent e) {
/* 27 */     if (e.isSneaking()) {
/* 28 */       Player p = e.getPlayer();
/* 29 */       if (SlimefunItem.getByItem(p.getInventory().getChestplate()) != null) {
/* 30 */         SlimefunItem item = SlimefunItem.getByItem(p.getInventory().getChestplate());
/* 31 */         if (item instanceof Jetpack) {
/* 32 */           if (Slimefun.hasUnlocked(p, item.getItem(), true)) {
/* 33 */             double thrust = ((Jetpack)item).getThrust();
/* 34 */             if (thrust > 0.2D) {
/* 35 */               JetpackTask task = new JetpackTask(p, thrust);
/* 36 */               task.setID(Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)SlimefunStartup.instance, (Runnable)task, 0L, 3L));
/*    */             }
/*    */           
/*    */           } 
/* 40 */         } else if (item.isItem(SlimefunItem.getItem("PARACHUTE")) && 
/* 41 */           Slimefun.hasUnlocked(p, SlimefunItem.getItem("PARACHUTE"), true)) {
/* 42 */           ParachuteTask task = new ParachuteTask(p);
/* 43 */           task.setID(Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)SlimefunStartup.instance, (Runnable)task, 0L, 3L));
/*    */         } 
/*    */       } 
/*    */       
/* 47 */       if (SlimefunItem.getByItem(p.getInventory().getBoots()) != null) {
/* 48 */         SlimefunItem item = SlimefunItem.getByItem(p.getInventory().getBoots());
/* 49 */         if (item instanceof JetBoots && 
/* 50 */           Slimefun.hasUnlocked(p, item.getItem(), true)) {
/* 51 */           double speed = ((JetBoots)item).getSpeed();
/* 52 */           if (speed > 0.2D) {
/* 53 */             JetBootsTask task = new JetBootsTask(p, speed);
/* 54 */             task.setID(Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)SlimefunStartup.instance, (Runnable)task, 0L, 2L));
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 59 */       if (p.getInventory().containsAtLeast(SlimefunItem.getItem("INFUSED_MAGNET"), 1)) {
/* 60 */         MagnetTask task = new MagnetTask(p);
/* 61 */         task.setID(Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)SlimefunStartup.instance, (Runnable)task, 0L, 8L));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\GearListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */