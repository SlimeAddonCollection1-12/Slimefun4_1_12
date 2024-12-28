/*     */ package me.mrCookieSlime.Slimefun.AncientAltar;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import me.mrCookieSlime.Slimefun.listeners.AncientAltarListener;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RitualAnimation
/*     */   implements Runnable
/*     */ {
/*     */   List<Block> altars;
/*     */   Block altar;
/*     */   Location l;
/*     */   ItemStack output;
/*     */   List<Block> pedestals;
/*     */   List<ItemStack> items;
/*     */   List<Location> particles;
/*     */   boolean running;
/*     */   int stage;
/*     */   
/*     */   public RitualAnimation(List<Block> altars, Block altar, Location drop, ItemStack output, List<Block> pedestals, List<ItemStack> items) {
/*  35 */     this.l = drop;
/*  36 */     this.altar = altar;
/*  37 */     this.altars = altars;
/*  38 */     this.output = output;
/*  39 */     this.pedestals = pedestals;
/*  40 */     this.items = items;
/*  41 */     this.particles = new ArrayList<>();
/*     */     
/*  43 */     this.running = true;
/*  44 */     this.stage = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  49 */     idle();
/*  50 */     if (this.stage == 36) {
/*  51 */       finish();
/*     */       return;
/*     */     } 
/*  54 */     if (this.stage > 0 && this.stage % 4 == 0) {
/*  55 */       checkPedestal(this.pedestals.get(this.stage / 4 - 1));
/*     */     }
/*  57 */     this.stage++;
/*  58 */     SlimefunStartup.instance.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, this, 8L);
/*     */   }
/*     */   
/*     */   private void idle() {
/*     */     try {
/*  63 */       ParticleEffect.SPELL_WITCH.display(this.l, 1.2F, 0.0F, 1.2F, 0.0F, 16);
/*  64 */       ParticleEffect.FIREWORKS_SPARK.display(this.l, 0.2F, 0.0F, 0.2F, 0.0F, 8);
/*  65 */       for (Location l2 : this.particles) {
/*  66 */         ParticleEffect.ENCHANTMENT_TABLE.display(l2, 0.3F, 0.2F, 0.3F, 0.0F, 16);
/*  67 */         ParticleEffect.CRIT_MAGIC.display(l2, 0.3F, 0.2F, 0.3F, 0.0F, 8);
/*     */       } 
/*  69 */     } catch (Exception e) {
/*  70 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkPedestal(Block pedestal) {
/*  75 */     Item item = AncientAltarListener.findItem(pedestal);
/*  76 */     if (item == null) { abort(); }
/*     */     else
/*  78 */     { this.particles.add(pedestal.getLocation().add(0.5D, 1.5D, 0.5D));
/*  79 */       this.items.add(AncientAltarListener.fixItemStack(item.getItemStack(), item.getCustomName()));
/*  80 */       pedestal.getWorld().playSound(pedestal.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 5.0F, 2.0F);
/*     */       
/*     */       try {
/*  83 */         ParticleEffect.ENCHANTMENT_TABLE.display(pedestal.getLocation().add(0.5D, 1.5D, 0.5D), 0.3F, 0.2F, 0.3F, 0.0F, 16);
/*  84 */         ParticleEffect.CRIT_MAGIC.display(pedestal.getLocation().add(0.5D, 1.5D, 0.5D), 0.3F, 0.2F, 0.3F, 0.0F, 8);
/*  85 */       } catch (Exception e) {
/*  86 */         e.printStackTrace();
/*     */       } 
/*     */       
/*  89 */       item.remove();
/*  90 */       pedestal.removeMetadata("item_placed", (Plugin)SlimefunStartup.instance); }
/*     */   
/*     */   }
/*     */   
/*     */   private void abort() {
/*  95 */     this.running = false;
/*     */     
/*  97 */     this.pedestals.forEach(pblock -> Variables.altarinuse.remove(pblock.getLocation()));
/*     */ 
/*     */ 
/*     */     
/* 101 */     Variables.altarinuse.remove(this.altar.getLocation());
/* 102 */     this.l.getWorld().playSound(this.l, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 5.0F, 1.0F);
/* 103 */     this.altars.remove(this.altar);
/*     */   }
/*     */   
/*     */   private void finish() {
/* 107 */     if (this.running) {
/* 108 */       this.l.getWorld().playSound(this.l, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1.0F, 1.0F);
/* 109 */       this.l.getWorld().playEffect(this.l, Effect.STEP_SOUND, Material.EMERALD_BLOCK);
/* 110 */       this.l.getWorld().dropItemNaturally(this.l.add(0.0D, 1.0D, 0.0D), this.output);
/*     */       
/* 112 */       this.pedestals.forEach(pblock -> Variables.altarinuse.remove(pblock.getLocation()));
/*     */ 
/*     */       
/* 115 */       Variables.altarinuse.remove(this.altar.getLocation());
/* 116 */       this.altars.remove(this.altar);
/*     */     } else {
/*     */       
/* 119 */       this.l.getWorld().playSound(this.l, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\AncientAltar\RitualAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */