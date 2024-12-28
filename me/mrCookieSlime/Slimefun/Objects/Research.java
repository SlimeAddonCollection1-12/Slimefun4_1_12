/*     */ package me.mrCookieSlime.Slimefun.Objects;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.FireworkShow;
/*     */ import me.mrCookieSlime.Slimefun.Events.ResearchUnlockEvent;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Research
/*     */ {
/*     */   public static boolean enabled;
/*  48 */   public static List<Research> list = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static List<UUID> researching = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean creative_research = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int id;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<SlimefunItem> items;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int cost;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Research(int id, String name, int cost) {
/*  84 */     this.id = id;
/*  85 */     this.name = name;
/*  86 */     this.cost = cost;
/*  87 */     this.items = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getID() {
/*  98 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 109 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getLevel() {
/* 122 */     return this.cost;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setLevel(int level) {
/* 135 */     this.cost = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCost() {
/* 145 */     return this.cost;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCost(int cost) {
/* 156 */     this.cost = cost;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItems(SlimefunItem... items) {
/* 167 */     for (SlimefunItem item : items) {
/* 168 */       if (item != null) item.bindToResearch(this);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SlimefunItem> getEffectedItems() {
/* 180 */     return this.items;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUnlocked(Player p) {
/* 193 */     return hasUnlocked(p.getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUnlocked(UUID uuid) {
/* 206 */     if (!enabled) return true; 
/* 207 */     if (!SlimefunStartup.getResearchCfg().getBoolean(this.id + ".enabled")) return true; 
/* 208 */     return (new Config(new File("data-storage/Slimefun/Players/" + uuid.toString() + ".yml"))).contains("researches." + this.id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUnlock(Player p) {
/* 220 */     if (!enabled) return true; 
/* 221 */     if (!SlimefunStartup.getResearchCfg().getBoolean(this.id + ".enabled")) return true; 
/* 222 */     return ((p.getGameMode() == GameMode.CREATIVE && creative_research) || p.getLevel() >= this.cost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lock(Player p) {
/* 233 */     Config cfg = new Config(new File("data-storage/Slimefun/Players/" + p.getUniqueId() + ".yml"));
/* 234 */     cfg.setValue("researches." + this.id, null);
/* 235 */     cfg.save();
/* 236 */     Messages.local.sendTranslation((CommandSender)p, "commands.research.reset-target", true, new Variable[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlock(final Player p, boolean instant) {
/* 248 */     if (!hasUnlocked(p)) {
/* 249 */       ResearchUnlockEvent event = new ResearchUnlockEvent(p, this);
/* 250 */       Bukkit.getPluginManager().callEvent((Event)event);
/* 251 */       if (!event.isCancelled()) {
/* 252 */         final int research = this.id;
/* 253 */         if (instant) {
/* 254 */           Config cfg = new Config(new File("data-storage/Slimefun/Players/" + p.getUniqueId() + ".yml"));
/* 255 */           cfg.setValue("researches." + research, Boolean.valueOf(true));
/* 256 */           cfg.save();
/* 257 */           Messages.local.sendTranslation((CommandSender)p, "messages.unlocked", true, new Variable[] { new Variable("%research%", getName()) });
/* 258 */           if (SlimefunStartup.getCfg().getBoolean("options.research-give-fireworks")) FireworkShow.launchRandom(p, 1);
/*     */         
/* 260 */         } else if (!researching.contains(p.getUniqueId())) {
/* 261 */           researching.add(p.getUniqueId());
/* 262 */           Messages.local.sendTranslation((CommandSender)p, "messages.research.start", true, new Variable[] { new Variable("%research%", getName()) });
/* 263 */           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */               {
/*     */                 public void run()
/*     */                 {
/* 267 */                   p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 1.0F);
/* 268 */                   Messages.local.sendTranslation((CommandSender)p, "messages.research.progress", true, new Variable[] { new Variable("%research%", this.this$0.getName()), new Variable("%progress%", "23%") });
/* 269 */                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                       {
/*     */                         public void run()
/*     */                         {
/* 273 */                           p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 1.0F);
/* 274 */                           Messages.local.sendTranslation((CommandSender)p, "messages.research.progress", true, new Variable[] { new Variable("%research%", this.this$1.this$0.getName()), new Variable("%progress%", "44%") });
/* 275 */                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                               {
/*     */                                 public void run()
/*     */                                 {
/* 279 */                                   p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 1.0F);
/* 280 */                                   Messages.local.sendTranslation((CommandSender)p, "messages.research.progress", true, new Variable[] { new Variable("%research%", this.this$2.this$1.this$0.getName()), new Variable("%progress%", "57%") });
/* 281 */                                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                                       {
/*     */                                         public void run()
/*     */                                         {
/* 285 */                                           p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 1.0F);
/* 286 */                                           Messages.local.sendTranslation((CommandSender)p, "messages.research.progress", true, new Variable[] { new Variable("%research%", this.this$3.this$2.this$1.this$0.getName()), new Variable("%progress%", "92%") });
/* 287 */                                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                                               {
/*     */                                                 public void run()
/*     */                                                 {
/* 291 */                                                   Config cfg = new Config(new File("data-storage/Slimefun/Players/" + p.getUniqueId() + ".yml"));
/* 292 */                                                   cfg.setValue("researches." + research, Boolean.valueOf(true));
/* 293 */                                                   cfg.save();
/* 294 */                                                   Messages.local.sendTranslation((CommandSender)p, "messages.unlocked", true, new Variable[] { new Variable("%research%", this.this$4.this$3.this$2.this$1.this$0.getName()) });
/* 295 */                                                   if (SlimefunStartup.getCfg().getBoolean("options.research-unlock-fireworks")) FireworkShow.launchRandom(p, 1); 
/* 296 */                                                   Research.researching.remove(p.getUniqueId());
/*     */                                                 }
/*     */                                               }20L);
/*     */                                         }
/*     */                                       }20L);
/*     */                                 }
/*     */                               }20L);
/*     */                         }
/*     */                       }20L);
/*     */                 }
/*     */               }20L);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register() {
/* 318 */     SlimefunStartup.getResearchCfg().setDefaultValue("enable-researching", Boolean.valueOf(true));
/*     */     
/* 320 */     if (SlimefunStartup.getResearchCfg().contains(getID() + ".enabled") && !SlimefunStartup.getResearchCfg().getBoolean(getID() + ".enabled")) {
/* 321 */       Iterator<SlimefunItem> iterator = this.items.iterator();
/* 322 */       while (iterator.hasNext()) {
/* 323 */         SlimefunItem item = iterator.next();
/* 324 */         if (item != null) item.bindToResearch(null); 
/* 325 */         iterator.remove();
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 330 */     SlimefunStartup.getResearchCfg().setDefaultValue(getID() + ".name", getName());
/* 331 */     SlimefunStartup.getResearchCfg().setDefaultValue(getID() + ".cost", Integer.valueOf(getCost()));
/* 332 */     SlimefunStartup.getResearchCfg().setDefaultValue(getID() + ".enabled", Boolean.valueOf(true));
/*     */     
/* 334 */     this.name = SlimefunStartup.getResearchCfg().getString(getID() + ".name");
/* 335 */     this.cost = SlimefunStartup.getResearchCfg().getInt(getID() + ".cost");
/*     */     
/* 337 */     list.add(this);
/* 338 */     if (SlimefunStartup.getCfg().getBoolean("options.print-out-loading")) System.out.println("[Slimefun] Loaded Research \"" + getName() + "\"");
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Research> list() {
/* 350 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isResearching(Player p) {
/* 362 */     return researching.contains(p.getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendStats(CommandSender sender, Player p) {
/* 375 */     List<Research> researched = new ArrayList<>();
/* 376 */     int levels = 0;
/* 377 */     for (Research r : list()) {
/* 378 */       if (r.hasUnlocked(p)) {
/* 379 */         researched.add(r);
/* 380 */         levels += r.getLevel();
/*     */       } 
/*     */     } 
/* 383 */     String progress = String.valueOf(Math.round(researched.size() * 100.0F / list().size() * 100.0F) / 100.0F);
/* 384 */     if (Float.parseFloat(progress) < 16.0F) { progress = "&4" + progress + " &r% "; }
/* 385 */     else if (Float.parseFloat(progress) < 32.0F) { progress = "&c" + progress + " &r% "; }
/* 386 */     else if (Float.parseFloat(progress) < 48.0F) { progress = "&6" + progress + " &r% "; }
/* 387 */     else if (Float.parseFloat(progress) < 64.0F) { progress = "&e" + progress + " &r% "; }
/* 388 */     else if (Float.parseFloat(progress) < 80.0F) { progress = "&2" + progress + " &r% "; }
/* 389 */     else { progress = "&a" + progress + " &r% "; }
/*     */     
/* 391 */     sender.sendMessage("");
/* 392 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Statistics for Player: &b" + p.getName()));
/* 393 */     sender.sendMessage("");
/* 394 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Title: &b" + getTitle(p, researched)));
/* 395 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Research Progress: " + progress + "&e(" + researched.size() + " / " + list().size() + ")"));
/* 396 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Total XP Levels spent: &b" + levels));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTitle(Player p, List<Research> researched) {
/* 410 */     int index = Math.round(Float.valueOf(String.valueOf(Math.round(researched.size() * 100.0F / list().size() * 100.0F) / 100.0F)).floatValue() / 100.0F) * SlimefunStartup.getCfg().getStringList("research-ranks").size();
/* 411 */     if (index > 0) index--; 
/* 412 */     return SlimefunStartup.getCfg().getStringList("research-ranks").get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Research getByID(int id) {
/* 424 */     for (Research research : list) {
/* 425 */       if (research.getID() == id) return research; 
/*     */     } 
/* 427 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Research> getResearches(UUID uuid) {
/* 440 */     List<Research> researched = new ArrayList<>();
/* 441 */     for (Research r : list()) {
/* 442 */       if (r.hasUnlocked(uuid)) researched.add(r); 
/*     */     } 
/* 444 */     return researched;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Research> getResearches(String uuid) {
/* 458 */     return getResearches(UUID.fromString(uuid));
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\Research.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */