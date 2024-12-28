/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BowShootHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class BowListener
/*     */   implements Listener {
/*     */   public BowListener(SlimefunStartup plugin) {
/*  26 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBowUse(EntityShootBowEvent e) {
/*  31 */     if (!(e.getEntity() instanceof Player) || !(e.getProjectile() instanceof Arrow))
/*  32 */       return;  if (SlimefunItem.getByItem(e.getBow()) != null) Variables.arrows.put(e.getProjectile().getUniqueId(), e.getBow()); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onArrowHit(ProjectileHitEvent e) {
/*  37 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, () -> { if (!e.getEntity().isValid()) return;  if (Variables.arrows.containsKey(e.getEntity().getUniqueId())) Variables.arrows.remove(e.getEntity().getUniqueId());  if (e.getEntity() instanceof Arrow) handleGrapplingHook((Arrow)e.getEntity());  }4L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleGrapplingHook(Arrow arrow) {
/*  45 */     if (arrow != null && 
/*  46 */       arrow.getShooter() instanceof Player && Variables.jump.containsKey(((Player)arrow.getShooter()).getUniqueId())) {
/*  47 */       final Player p = (Player)arrow.getShooter();
/*  48 */       if (p.getGameMode() != GameMode.CREATIVE && ((Boolean)Variables.jump.get(p.getUniqueId())).booleanValue()) arrow.getWorld().dropItem(arrow.getLocation(), SlimefunItem.getItem("GRAPPLING_HOOK")); 
/*  49 */       if (p.getLocation().distance(arrow.getLocation()) < 3.0D) {
/*  50 */         if (arrow.getLocation().getY() > p.getLocation().getY()) {
/*  51 */           p.setVelocity(new Vector(0.0D, 0.25D, 0.0D));
/*     */         } else {
/*  53 */           p.setVelocity(arrow.getLocation().toVector().subtract(p.getLocation().toVector()));
/*  54 */         }  for (Entity n : (Entity[])Variables.remove.get(p.getUniqueId())) {
/*  55 */           n.remove();
/*     */         }
/*     */         
/*  58 */         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, () -> { Variables.jump.remove(p.getUniqueId()); Variables.remove.remove(p.getUniqueId()); }20L);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  64 */         Location l = p.getLocation();
/*  65 */         l.setY(l.getY() + 0.5D);
/*  66 */         p.teleport(l);
/*     */         
/*  68 */         double g = -0.08D;
/*  69 */         double d = arrow.getLocation().distance(l);
/*  70 */         double t = d;
/*  71 */         double v_x = (1.0D + 0.08000000000000002D * t) * (arrow.getLocation().getX() - l.getX()) / t;
/*  72 */         double v_y = (1.0D + 0.04D * t) * (arrow.getLocation().getY() - l.getY()) / t - 0.5D * g * t;
/*  73 */         double v_z = (1.0D + 0.08000000000000002D * t) * (arrow.getLocation().getZ() - l.getZ()) / t;
/*     */         
/*  75 */         Vector v = p.getVelocity();
/*     */         
/*  77 */         v.setX(v_x);
/*  78 */         v.setY(v_y);
/*  79 */         v.setZ(v_z);
/*     */         
/*  81 */         p.setVelocity(v);
/*     */         
/*  83 */         for (Entity n : (Entity[])Variables.remove.get(p.getUniqueId())) {
/*  84 */           n.remove();
/*     */         }
/*     */         
/*  87 */         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */             {
/*     */               public void run()
/*     */               {
/*  91 */                 Variables.jump.remove(p.getUniqueId());
/*  92 */                 Variables.remove.remove(p.getUniqueId());
/*     */               }
/*     */             },  20L);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onArrowSuccessfulHit(EntityDamageByEntityEvent e) {
/* 102 */     if (e.getDamager() instanceof Arrow) {
/* 103 */       if (Variables.arrows.containsKey(e.getDamager().getUniqueId()) && e.getEntity() instanceof LivingEntity) {
/* 104 */         for (ItemHandler handler : SlimefunItem.getHandlers("BowShootHandler")) {
/* 105 */           if (((BowShootHandler)handler).onHit(e, (LivingEntity)e.getEntity()))
/*     */             break; 
/* 107 */         }  Variables.arrows.remove(e.getDamager().getUniqueId());
/*     */       } 
/*     */       
/* 110 */       handleGrapplingHook((Arrow)e.getDamager());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\BowListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */