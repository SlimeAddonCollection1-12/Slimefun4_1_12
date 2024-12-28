/*     */ package me.mrCookieSlime.Slimefun.GPS;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.TitleBuilder;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TeleportationSequence
/*     */ {
/*  22 */   public static Set<UUID> players = new HashSet<>();
/*     */   
/*     */   public static void start(UUID uuid, int complexity, Location source, Location destination, boolean resistance) {
/*  25 */     players.add(uuid);
/*     */     
/*  27 */     updateProgress(uuid, getSpeed(complexity, source, destination), 1, source, destination, resistance);
/*     */   }
/*     */   
/*     */   public static int getSpeed(int complexity, Location source, Location destination) {
/*  31 */     int speed = complexity / 200;
/*  32 */     if (speed > 50) speed = 50; 
/*  33 */     speed -= distance(source, destination) / 200;
/*     */     
/*  35 */     return (speed < 1) ? 1 : speed;
/*     */   }
/*     */   
/*     */   private static int distance(Location source, Location destination) {
/*  39 */     if (source.getWorld().getName().equals(destination.getWorld().getName())) {
/*  40 */       int distance = (int)source.distance(destination);
/*  41 */       return (distance > 8000) ? 8000 : distance;
/*     */     } 
/*  43 */     return 8000;
/*     */   }
/*     */   
/*     */   private static boolean isValid(Player p, Location source) {
/*  47 */     if (p == null) return false; 
/*  48 */     if (p.getLocation().distance(source) > 1.4D) return false; 
/*  49 */     return true;
/*     */   }
/*     */   
/*     */   private static void cancel(UUID uuid, Player p) {
/*  53 */     players.remove(uuid);
/*  54 */     if (p != null) {
/*     */       try {
/*  56 */         TitleBuilder title = (TitleBuilder)(new TitleBuilder(20, 60, 20)).addText(ChatColor.translateAlternateColorCodes('&', "&4传送取消"));
/*  57 */         TitleBuilder subtitle = (TitleBuilder)(new TitleBuilder(20, 60, 20)).addText(ChatColor.translateAlternateColorCodes('&', "&40%"));
/*     */         
/*  59 */         title.send(TitleBuilder.TitleType.TITLE, new Player[] { p });
/*  60 */         subtitle.send(TitleBuilder.TitleType.SUBTITLE, new Player[] { p });
/*  61 */       } catch (Exception x) {
/*  62 */         x.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void updateProgress(final UUID uuid, final int speed, final int progress, final Location source, final Location destination, final boolean resistance) {
/*  68 */     Player p = Bukkit.getPlayer(uuid);
/*  69 */     if (isValid(p, source)) {
/*     */       try {
/*  71 */         if (progress > 99) {
/*  72 */           TitleBuilder title = (TitleBuilder)(new TitleBuilder(20, 60, 20)).addText(ChatColor.translateAlternateColorCodes('&', "&3成功传送!"));
/*  73 */           TitleBuilder subtitle = (TitleBuilder)(new TitleBuilder(20, 60, 20)).addText(ChatColor.translateAlternateColorCodes('&', "&b100%"));
/*     */           
/*  75 */           title.send(TitleBuilder.TitleType.TITLE, new Player[] { p });
/*  76 */           subtitle.send(TitleBuilder.TitleType.SUBTITLE, new Player[] { p });
/*     */           
/*  78 */           p.teleport(destination);
/*     */           
/*  80 */           if (resistance) {
/*  81 */             p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 20));
/*  82 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l你获得了30s的无敌状态!"));
/*     */           } 
/*     */           
/*  85 */           ParticleEffect.PORTAL.display(new Location(destination.getWorld(), destination.getX(), destination.getY() + 1.0D, destination.getZ()), 0.2F, 0.8F, 0.2F, 1.0F, progress * 2);
/*  86 */           destination.getWorld().playSound(destination, Sound.ENTITY_BLAZE_DEATH, 2.0F, 1.4F);
/*  87 */           players.remove(uuid);
/*     */         } else {
/*     */           
/*  90 */           TitleBuilder title = (TitleBuilder)(new TitleBuilder(0, 60, 0)).addText(ChatColor.translateAlternateColorCodes('&', "&3传送中..."));
/*  91 */           TitleBuilder subtitle = (TitleBuilder)(new TitleBuilder(0, 60, 0)).addText(ChatColor.translateAlternateColorCodes('&', "&b" + progress + "%"));
/*     */           
/*  93 */           title.send(TitleBuilder.TitleType.TITLE, new Player[] { p });
/*  94 */           subtitle.send(TitleBuilder.TitleType.SUBTITLE, new Player[] { p });
/*     */           
/*  96 */           ParticleEffect.PORTAL.display(source, 0.2F, 0.8F, 0.2F, 1.0F, progress * 2);
/*  97 */           source.getWorld().playSound(source, Sound.UI_BUTTON_CLICK, 1.7F, 0.6F);
/*     */           
/*  99 */           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */               {
/*     */                 public void run()
/*     */                 {
/* 103 */                   TeleportationSequence.updateProgress(uuid, speed, progress + speed, source, destination, resistance);
/*     */                 }
/*     */               }10L);
/*     */         } 
/* 107 */       } catch (Exception e) {
/* 108 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 111 */       cancel(uuid, p);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GPS\TeleportationSequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */