/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class ArmorListener
/*    */   implements Listener
/*    */ {
/*    */   public ArmorListener(SlimefunStartup plugin) {
/* 32 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler(priority = EventPriority.MONITOR)
/*    */   public void onDamage(EntityDamageEvent e) {
/* 38 */     if (e.getEntity() instanceof Player && !e.isCancelled()) {
/* 39 */       Player p = (Player)e.getEntity();
/* 40 */       for (ItemStack armor : p.getInventory().getArmorContents()) {
/* 41 */         if (armor != null && SlimefunItem.getByItem(armor) != null) {
/* 42 */           if (SlimefunItem.getByItem(armor).isItem(SlimefunItems.ENDER_BOOTS) && Slimefun.hasUnlocked(p, SlimefunItems.ENDER_BOOTS, true))
/* 43 */           { if (e instanceof EntityDamageByEntityEvent && (
/* 44 */               (EntityDamageByEntityEvent)e).getDamager() instanceof org.bukkit.entity.EnderPearl) e.setCancelled(true);
/*    */              }
/*    */           
/* 47 */           else if (SlimefunItem.getByItem(armor).isItem(SlimefunItems.SLIME_BOOTS) && Slimefun.hasUnlocked(p, SlimefunItems.SLIME_BOOTS, true))
/* 48 */           { if (e.getCause() == EntityDamageEvent.DamageCause.FALL) e.setCancelled(true);
/*    */              }
/* 50 */           else if (SlimefunItem.getByItem(armor).isItem(SlimefunItems.BOOTS_OF_THE_STOMPER) && Slimefun.hasUnlocked(p, SlimefunItems.BOOTS_OF_THE_STOMPER, true))
/* 51 */           { if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
/* 52 */               e.setCancelled(true);
/* 53 */               p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 2.0F, 2.0F);
/* 54 */               p.setVelocity(new Vector(0.0D, 0.7D, 0.0D));
/* 55 */               for (Entity n : p.getNearbyEntities(4.0D, 4.0D, 4.0D)) {
/* 56 */                 if (n instanceof LivingEntity && !n.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
/* 57 */                   n.setVelocity(n.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.4D));
/* 58 */                   if (p.getWorld().getPVP()) {
/* 59 */                     EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)p, n, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e.getDamage() / 2.0D);
/* 60 */                     Bukkit.getPluginManager().callEvent((Event)event);
/* 61 */                     if (!event.isCancelled()) ((LivingEntity)n).damage(e.getDamage() / 2.0D); 
/*    */                   } 
/*    */                 } 
/*    */               } 
/* 65 */               for (int i = 0; i < 2; i++) {
/* 66 */                 for (BlockFace face : BlockFace.values()) {
/* 67 */                   p.getWorld().playEffect(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(face).getLocation(), Effect.STEP_SOUND, p.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(face).getType());
/*    */                 }
/*    */               }
/*    */             
/*    */             }  }
/* 72 */           else if (SlimefunItem.getByItem(armor).isItem(SlimefunItems.SLIME_BOOTS_STEEL) && Slimefun.hasUnlocked(p, SlimefunItems.SLIME_BOOTS_STEEL, true) && 
/* 73 */             e.getCause() == EntityDamageEvent.DamageCause.FALL) { e.setCancelled(true); }
/*    */         
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onTrample(PlayerInteractEvent e) {
/* 82 */     if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL && 
/* 83 */       SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getBoots(), SlimefunItem.getItem("FARMER_SHOES"), true)) e.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\ArmorListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */