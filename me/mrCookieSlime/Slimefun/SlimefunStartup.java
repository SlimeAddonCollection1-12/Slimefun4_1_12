/*     */ package me.mrCookieSlime.Slimefun;
/*     */ import com.bekvon.bukkit.residence.protection.FlagPermissions;
/*     */ import java.io.File;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.CSCoreLibSetup.CSCoreLibLoader;
/*     */ import me.mrCookieSlime.Slimefun.Commands.SlimefunCommand;
/*     */ import me.mrCookieSlime.Slimefun.Commands.SlimefunTabCompleter;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenSystem;
/*     */ import me.mrCookieSlime.Slimefun.GEO.Resources.OilResource;
/*     */ import me.mrCookieSlime.Slimefun.GitHub.GitHubConnector;
/*     */ import me.mrCookieSlime.Slimefun.Hashing.ItemHash;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Misc.BookDesign;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunArmorPiece;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectricDustWasher;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Files;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.Setup.MiscSetup;
/*     */ import me.mrCookieSlime.Slimefun.Setup.NarItemSetup;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunSetup;
/*     */ import me.mrCookieSlime.Slimefun.URID.AutoSavingTask;
/*     */ import me.mrCookieSlime.Slimefun.URID.URID;
/*     */ import me.mrCookieSlime.Slimefun.WorldEdit.WESlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import me.mrCookieSlime.Slimefun.api.SlimefunBackup;
/*     */ import me.mrCookieSlime.Slimefun.api.TickerTask;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.EnergyNet;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ChestManipulator;
/*     */ import me.mrCookieSlime.Slimefun.listeners.AncientAltarListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.AutonomousToolsListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.BackpackListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.BlockListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.ClearLaggIntegration;
/*     */ import me.mrCookieSlime.Slimefun.listeners.DamageListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.GearListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.ItemListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.ItemPickupListener_1_12;
/*     */ import me.mrCookieSlime.Slimefun.listeners.NetworkListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.TeleporterListener;
/*     */ import me.mrCookieSlime.Slimefun.listeners.TempFixListener;
/*     */ import net.coreprotect.CoreProtect;
/*     */ import net.coreprotect.CoreProtectAPI;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.world.WorldLoadEvent;
/*     */ import org.bukkit.event.world.WorldUnloadEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class SlimefunStartup extends JavaPlugin {
/*     */   public static SlimefunStartup instance;
/*     */   static PluginUtils utils;
/*     */   static Config researches;
/*     */   static Config items;
/*     */   static Config whitelist;
/*     */   static Config config;
/*     */   public static TickerTask ticker;
/*     */   private CoreProtectAPI coreProtectAPI;
/*     */   private boolean clearlag = false;
/*     */   private boolean exoticGarden = false;
/*     */   private boolean coreProtect = false;
/*     */   private boolean plotSquared = false;
/*     */   private boolean residence = false;
/*  84 */   final String[] supported = new String[] { "v1_9_", "v1_10_", "v1_11_", "v1_12_" };
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  89 */     if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlotSquared")) {
/*  90 */       this.plotSquared = true;
/*     */     }
/*  92 */     if (getServer().getPluginManager().getPlugin("Residence") != null) {
/*  93 */       FlagPermissions.addFlag("sf-machines");
/*  94 */       this.residence = true;
/*     */     } 
/*  96 */     CSCoreLibLoader loader = new CSCoreLibLoader((Plugin)this);
/*  97 */     if (loader.load()) {
/*     */       
/*  99 */       String currentVersion = ReflectionUtils.getVersion();
/*     */       
/* 101 */       if (currentVersion.startsWith("v")) {
/* 102 */         boolean compatibleVersion = false;
/* 103 */         StringBuilder versions = new StringBuilder();
/*     */         
/* 105 */         int i = 0;
/* 106 */         for (String version : this.supported) {
/* 107 */           if (currentVersion.startsWith(version)) {
/* 108 */             compatibleVersion = true;
/*     */           }
/*     */           
/* 111 */           if (i == 0) { versions.append(version.substring(1).replaceFirst("_", ".").replace("_", ".X")); }
/* 112 */           else if (i == this.supported.length - 1) { versions.append(" or " + version.substring(1).replaceFirst("_", ".").replace("_", ".X")); }
/* 113 */           else { versions.append(", " + version.substring(1).replaceFirst("_", ".").replace("_", ".X")); }
/*     */           
/* 115 */           i++;
/*     */         } 
/*     */ 
/*     */         
/* 119 */         if (!compatibleVersion) {
/* 120 */           System.err.println("### 远古工艺加载失败!");
/* 121 */           System.err.println("###");
/* 122 */           System.err.println("### 你当前使用的Minecraft版本Slimefun不支持!!!");
/* 123 */           System.err.println("###");
/* 124 */           System.err.println("### 你正在使用Minecraft " + ReflectionUtils.getVersion());
/* 125 */           System.err.println("### 但Slimefun v" + getDescription().getVersion() + " 只能运行在");
/* 126 */           System.err.println("### Minecraft " + versions.toString());
/* 127 */           System.err.println("###");
/* 128 */           System.err.println("### 请尝试使用旧版并关闭自动更新");
/* 129 */           System.err.println("### 或者更新你的服务器.");
/* 130 */           getServer().getPluginManager().disablePlugin((Plugin)this);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 135 */       instance = this;
/* 136 */       System.out.println("[远古工艺] 加载文件中...");
/* 137 */       Files.cleanup();
/*     */       
/* 139 */       System.out.println("[远古工艺] 加载配置中...");
/*     */       
/* 141 */       utils = new PluginUtils((Plugin)this);
/* 142 */       utils.setupConfig();
/*     */ 
/*     */       
/* 145 */       researches = new Config(Files.RESEARCHES);
/* 146 */       items = new Config(Files.ITEMS);
/* 147 */       whitelist = new Config(Files.WHITELIST);
/*     */ 
/*     */       
/* 150 */       utils.setupUpdater(53485, getFile());
/* 151 */       utils.setupMetrics();
/* 152 */       utils.setupLocalization();
/* 153 */       config = utils.getConfig();
/* 154 */       Messages.local = utils.getLocalization();
/* 155 */       Messages.setup();
/*     */ 
/*     */ 
/*     */       
/* 159 */       if (!(new File("data-storage/Slimefun/blocks")).exists()) (new File("data-storage/Slimefun/blocks")).mkdirs(); 
/* 160 */       if (!(new File("data-storage/Slimefun/stored-blocks")).exists()) (new File("data-storage/Slimefun/stored-blocks")).mkdirs(); 
/* 161 */       if (!(new File("data-storage/Slimefun/stored-inventories")).exists()) (new File("data-storage/Slimefun/stored-inventories")).mkdirs(); 
/* 162 */       if (!(new File("data-storage/Slimefun/stored-chunks")).exists()) (new File("data-storage/Slimefun/stored-chunks")).mkdirs(); 
/* 163 */       if (!(new File("data-storage/Slimefun/universal-inventories")).exists()) (new File("data-storage/Slimefun/universal-inventories")).mkdirs(); 
/* 164 */       if (!(new File("data-storage/Slimefun/waypoints")).exists()) (new File("data-storage/Slimefun/waypoints")).mkdirs(); 
/* 165 */       if (!(new File("data-storage/Slimefun/block-backups")).exists()) (new File("data-storage/Slimefun/block-backups")).mkdirs(); 
/* 166 */       if (!(new File("plugins/Slimefun/scripts")).exists()) (new File("plugins/Slimefun/scripts")).mkdirs(); 
/* 167 */       if (!(new File("plugins/Slimefun/generators")).exists()) (new File("plugins/Slimefun/generators")).mkdirs(); 
/* 168 */       if (!(new File("plugins/Slimefun/error-reports")).exists()) (new File("plugins/Slimefun/error-reports")).mkdirs(); 
/* 169 */       if (!(new File("plugins/Slimefun/cache/github")).exists()) (new File("plugins/Slimefun/cache/github")).mkdirs();
/*     */       
/* 171 */       SlimefunManager.plugin = this;
/*     */       
/* 173 */       System.out.println("[远古工艺] 加载物品中...");
/* 174 */       MiscSetup.setupItemSettings();
/*     */       try {
/* 176 */         SlimefunSetup.setupItems();
/*     */         
/* 178 */         NarItemSetup.setupItems();
/* 179 */       } catch (Exception e1) {
/* 180 */         e1.printStackTrace();
/*     */       } 
/* 182 */       MiscSetup.loadDescriptions();
/*     */       
/* 184 */       System.out.println("[远古工艺] 加载研究中...");
/* 185 */       Research.enabled = getResearchCfg().getBoolean("enable-researching");
/* 186 */       ResearchSetup.setupResearches();
/*     */       
/* 188 */       MiscSetup.setupMisc();
/*     */       
/* 190 */       BlockStorage.info_delay = config.getInt("URID.info-delay");
/*     */       
/* 192 */       System.out.println("[远古工艺] 加载世界生成器中...");
/*     */ 
/*     */       
/* 195 */       OreGenSystem.registerResource((OreGenResource)new OilResource());
/* 196 */       OreGenSystem.registerResource((OreGenResource)new NetherIceResource());
/*     */ 
/*     */ 
/*     */       
/* 200 */       GitHubSetup.setup();
/*     */ 
/*     */       
/* 203 */       new ArmorListener(this);
/* 204 */       new ItemListener(this);
/* 205 */       new BlockListener(this);
/* 206 */       new GearListener(this);
/* 207 */       new AutonomousToolsListener(this);
/* 208 */       new DamageListener(this);
/* 209 */       new BowListener(this);
/* 210 */       new ToolListener(this);
/* 211 */       new FurnaceListener(this);
/* 212 */       new TeleporterListener(this);
/* 213 */       new AndroidKillingListener(this);
/* 214 */       new NetworkListener(this);
/* 215 */       if (currentVersion.startsWith("v1_12_")) { new ItemPickupListener_1_12(this); }
/* 216 */       else { new ItemPickupListener(this); }
/*     */ 
/*     */       
/* 219 */       if (config.getBoolean("items.talismans")) new TalismanListener(this); 
/* 220 */       if (config.getBoolean("items.backpacks")) new BackpackListener(this); 
/* 221 */       if (config.getBoolean("items.coolers")) new CoolerListener(this);
/*     */ 
/*     */ 
/*     */       
/* 225 */       if (config.getBoolean("options.give-guide-on-first-join")) {
/* 226 */         getServer().getPluginManager().registerEvents(new Listener()
/*     */             {
/*     */               @EventHandler
/*     */               public void onJoin(PlayerJoinEvent e) {
/* 230 */                 if (!e.getPlayer().hasPlayedBefore()) {
/* 231 */                   Player p = e.getPlayer();
/* 232 */                   if (!SlimefunStartup.getWhitelist().getBoolean(p.getWorld().getName() + ".enabled"))
/* 233 */                     return;  if (!SlimefunStartup.getWhitelist().getBoolean(p.getWorld().getName() + ".enabled-items.SLIMEFUN_GUIDE"))
/*     */                     return; 
/* 235 */                   if (SlimefunStartup.config.getBoolean("guide.default-view-book")) { p.getInventory().addItem(new ItemStack[] { SlimefunGuide.getItem(BookDesign.BOOK) }, ); }
/* 236 */                   else { p.getInventory().addItem(new ItemStack[] { SlimefunGuide.getItem(BookDesign.CHEST) }, ); }
/*     */                 
/*     */                 } 
/*     */               }
/*     */             },  (Plugin)this);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 245 */       getServer().getPluginManager().registerEvents(new Listener()
/*     */           {
/*     */             @EventHandler
/*     */             public void onWorldLoad(WorldLoadEvent e) {
/* 249 */               BlockStorage.getForcedStorage(e.getWorld());
/*     */               
/* 251 */               SlimefunStartup.getWhitelist().setDefaultValue(e.getWorld().getName() + ".enabled", Boolean.valueOf(true));
/* 252 */               SlimefunStartup.getWhitelist().setDefaultValue(e.getWorld().getName() + ".enabled-items.SLIMEFUN_GUIDE", Boolean.valueOf(true));
/* 253 */               SlimefunStartup.getWhitelist().save();
/*     */             }
/*     */             
/*     */             @EventHandler
/*     */             public void onWorldUnload(WorldUnloadEvent e) {
/* 258 */               BlockStorage storage = BlockStorage.getStorage(e.getWorld());
/* 259 */               if (storage != null) { storage.save(true); }
/* 260 */               else { System.err.println("[远古工艺] Could not save Slimefun Blocks for World \"" + e.getWorld().getName() + "\""); }
/*     */             
/*     */             }
/*     */           }(Plugin)this);
/*     */ 
/*     */ 
/*     */       
/* 267 */       getServer().getPluginManager().registerEvents(new Listener()
/*     */           {
/*     */             @EventHandler
/*     */             public void onDisconnect(PlayerQuitEvent e) {
/* 271 */               SlimefunGuide.history.remove(e.getPlayer().getUniqueId());
/*     */             }
/*     */           },  (Plugin)this);
/*     */ 
/*     */ 
/*     */       
/* 277 */       getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this, () -> { Slimefun.emeraldenchants = getServer().getPluginManager().isPluginEnabled("EmeraldEnchants"); SlimefunGuide.all_recipes = config.getBoolean("options.show-vanilla-recipes-in-guide"); MiscSetup.loadItems(); for (World world : Bukkit.getWorlds()) new BlockStorage(world);  if (SlimefunItem.getByID("ANCIENT_ALTAR") != null) new AncientAltarListener(instance);  }0L);
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
/* 290 */       if (getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
/*     */         try {
/* 292 */           Class.forName("com.sk89q.worldedit.extent.Extent");
/* 293 */           new WESlimefunManager();
/* 294 */           System.out.println("[远古工艺] Successfully hooked into WorldEdit!");
/* 295 */         } catch (Exception x) {
/* 296 */           System.err.println("[远古工艺] Failed to hook into WorldEdit!");
/* 297 */           System.err.println("[远古工艺] Maybe consider updating WorldEdit or Slimefun?");
/*     */         } 
/*     */       }
/*     */       
/* 301 */       getCommand("slimefun").setExecutor((CommandExecutor)new SlimefunCommand(this));
/* 302 */       getCommand("slimefun").setTabCompleter((TabCompleter)new SlimefunTabCompleter());
/*     */ 
/*     */       
/* 305 */       if (config.getBoolean("options.enable-armor-effects")) {
/* 306 */         getServer().getScheduler().runTaskTimer((Plugin)this, () -> { for (Player p : Bukkit.getOnlinePlayers()) { for (ItemStack armor : p.getInventory().getArmorContents()) { if (armor != null && Slimefun.hasUnlocked(p, armor, true)) { if (SlimefunItem.getByItem(armor) instanceof SlimefunArmorPiece) for (PotionEffect effect : ((SlimefunArmorPiece)SlimefunItem.getByItem(armor)).getEffects()) { p.removePotionEffect(effect.getType()); p.addPotionEffect(effect); }   if (SlimefunManager.isItemSimiliar(armor, SlimefunItem.getItem("SOLAR_HELMET"), false) && (p.getWorld().getTime() < 12300L || p.getWorld().getTime() > 23850L) && p.getEyeLocation().getBlock().getLightFromSky() == 15) ItemEnergy.chargeInventory(p, Float.valueOf(String.valueOf(Slimefun.getItemValue("SOLAR_HELMET", "charge-amount"))).floatValue());  }  }  for (ItemStack radioactive : SlimefunItem.radioactive) { if (p.getInventory().containsAtLeast(radioactive, 1) || SlimefunManager.isItemSimiliar(p.getInventory().getItemInOffHand(), radioactive, true)) { if (SlimefunManager.isItemSimiliar(SlimefunItems.SCUBA_HELMET, p.getInventory().getHelmet(), true) && SlimefunManager.isItemSimiliar(SlimefunItems.HAZMATSUIT_CHESTPLATE, p.getInventory().getChestplate(), true) && SlimefunManager.isItemSimiliar(SlimefunItems.HAZMATSUIT_LEGGINGS, p.getInventory().getLeggings(), true) && SlimefunManager.isItemSimiliar(SlimefunItems.RUBBER_BOOTS, p.getInventory().getBoots(), true)) break;  if (Slimefun.isEnabled(p, radioactive, false)) { p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 3)); p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 3)); p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 3)); p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 3)); p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1)); p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 1)); p.setFireTicks(400); }  }  }  }  }0L, config
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
/*     */ 
/*     */             
/* 353 */             .getInt("options.armor-update-interval") * 20L);
/*     */       }
/*     */       
/* 356 */       ticker = new TickerTask();
/*     */ 
/*     */       
/* 359 */       getServer().getScheduler().scheduleAsyncRepeatingTask((Plugin)this, (Runnable)new AutoSavingTask(), 1200L, config.getInt("options.auto-save-delay-in-minutes") * 60L * 20L);
/* 360 */       getServer().getScheduler().scheduleAsyncRepeatingTask((Plugin)this, (Runnable)ticker, 100L, config.getInt("URID.custom-ticker-delay"));
/*     */       
/* 362 */       getServer().getScheduler().scheduleAsyncRepeatingTask((Plugin)this, new Runnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 366 */               for (GitHubConnector connector : GitHubConnector.connectors) {
/* 367 */                 connector.pullFile();
/*     */               }
/*     */             }
/*     */           },  80L, 72000L);
/*     */ 
/*     */       
/* 373 */       getServer().getConsoleSender().sendMessage("§6[远古工艺] 汉化增强版加载完毕，本插件为免费插件!");
/* 374 */       getServer().getConsoleSender().sendMessage("§6[远古工艺] 欢迎加入Slimefun插件交流群:§b 657275313");
/*     */       
/* 376 */       this.clearlag = getServer().getPluginManager().isPluginEnabled("ClearLag");
/*     */       
/* 378 */       this.coreProtect = getServer().getPluginManager().isPluginEnabled("CoreProtect");
/*     */       
/* 380 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this, new BukkitRunnable()
/*     */           {
/*     */             public void run() {
/* 383 */               SlimefunStartup.this.exoticGarden = SlimefunStartup.this.getServer().getPluginManager().isPluginEnabled("ExoticGarden");
/*     */             }
/*     */           },  0L);
/*     */       
/* 387 */       if (this.clearlag) new ClearLaggIntegration(this);
/*     */       
/* 389 */       if (this.coreProtect) this.coreProtectAPI = ((CoreProtect)getServer().getPluginManager().getPlugin("CoreProtect")).getAPI();
/*     */       
/* 391 */       Research.creative_research = config.getBoolean("options.allow-free-creative-research");
/*     */       
/* 393 */       AutoEnchanter.max_emerald_enchantments = config.getInt("options.emerald-enchantment-limit");
/*     */       
/* 395 */       SlimefunSetup.legacy_ore_washer = config.getBoolean("options.legacy-ore-washer");
/* 396 */       ElectricDustWasher.legacy_dust_washer = config.getBoolean("options.legacy-dust-washer");
/*     */ 
/*     */       
/* 399 */       CSCoreLib.getLib().filterLog("([A-Za-z0-9_]{3,16}) issued server command: /sf elevator (.{0,})");
/*     */ 
/*     */       
/* 402 */       getServer().getPluginManager().registerEvents((Listener)new TempFixListener(), (Plugin)this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 408 */     Bukkit.getScheduler().cancelTasks((Plugin)this);
/*     */ 
/*     */     
/* 411 */     ticker.HALTED = true;
/* 412 */     ticker.run();
/*     */     
/*     */     try {
/* 415 */       for (World world : Bukkit.getWorlds()) {
/* 416 */         BlockStorage storage = BlockStorage.getStorage(world);
/* 417 */         if (storage != null) {
/* 418 */           storage.save(true);
/*     */           continue;
/*     */         } 
/* 421 */         System.err.println("[远古工艺] Could not save Slimefun Blocks for World \"" + world.getName() + "\"");
/*     */       } 
/*     */ 
/*     */       
/* 425 */       SlimefunBackup.start();
/* 426 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 429 */     config = null;
/* 430 */     researches = null;
/* 431 */     items = null;
/* 432 */     whitelist = null;
/* 433 */     instance = null;
/* 434 */     Messages.local = null;
/* 435 */     Files.CONFIG = null;
/* 436 */     Files.DATABASE = null;
/* 437 */     Files.ITEMS = null;
/* 438 */     Files.RESEARCHES = null;
/* 439 */     Files.WHITELIST = null;
/* 440 */     MultiBlock.list = null;
/* 441 */     Research.list = null;
/* 442 */     Research.researching = null;
/* 443 */     SlimefunItem.all = null;
/* 444 */     SlimefunItem.items = null;
/* 445 */     SlimefunItem.map_id = null;
/* 446 */     SlimefunItem.handlers = null;
/* 447 */     SlimefunItem.radioactive = null;
/* 448 */     Variables.damage = null;
/* 449 */     Variables.jump = null;
/* 450 */     Variables.mode = null;
/* 451 */     SlimefunGuide.history = null;
/* 452 */     Variables.altarinuse = null;
/* 453 */     Variables.enchanting = null;
/* 454 */     Variables.backpack = null;
/* 455 */     Variables.soulbound = null;
/* 456 */     Variables.blocks = null;
/* 457 */     Variables.cancelPlace = null;
/* 458 */     Variables.arrows = null;
/* 459 */     SlimefunCommand.arguments = null;
/* 460 */     SlimefunCommand.descriptions = null;
/* 461 */     SlimefunCommand.tabs = null;
/* 462 */     URID.objects = null;
/* 463 */     URID.ids = null;
/* 464 */     SlimefunItem.blockhandler = null;
/* 465 */     BlockMenuPreset.presets = null;
/* 466 */     BlockStorage.loaded_tickers = null;
/* 467 */     BlockStorage.ticking_chunks = null;
/* 468 */     BlockStorage.worlds = null;
/* 469 */     ChargableBlock.capacitors = null;
/* 470 */     ChargableBlock.max_charges = null;
/* 471 */     AContainer.processing = null;
/* 472 */     AContainer.progress = null;
/* 473 */     Slimefun.guide_handlers = null;
/* 474 */     Pedestals.recipes = null;
/* 475 */     Elevator.ignored = null;
/* 476 */     EnergyNet.listeners = null;
/* 477 */     EnergyNet.machines_input = null;
/* 478 */     EnergyNet.machines_output = null;
/* 479 */     EnergyNet.machines_storage = null;
/* 480 */     CargoNet.faces = null;
/* 481 */     BlockStorage.universal_inventories = null;
/* 482 */     TickerTask.block_timings = null;
/* 483 */     OreGenSystem.map = null;
/* 484 */     SlimefunGuide.contributors = null;
/* 485 */     GitHubConnector.connectors = null;
/* 486 */     ChestManipulator.listeners = null;
/* 487 */     ItemHash.digest = null;
/* 488 */     ItemHash.map = null;
/*     */     
/* 490 */     for (Player p : Bukkit.getOnlinePlayers()) {
/* 491 */       p.closeInventory();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Config getCfg() {
/* 496 */     return config;
/*     */   }
/*     */   
/*     */   public static Config getResearchCfg() {
/* 500 */     return researches;
/*     */   }
/*     */   
/*     */   public static Config getItemCfg() {
/* 504 */     return items;
/*     */   }
/*     */   
/*     */   public static Config getWhitelist() {
/* 508 */     return whitelist;
/*     */   }
/*     */   
/*     */   public static int randomize(int max) {
/* 512 */     if (max < 1) return 0; 
/* 513 */     return CSCoreLib.randomizer().nextInt(max);
/*     */   }
/*     */   
/*     */   public static boolean chance(int max, int percentage) {
/* 517 */     if (max < 1) return false; 
/* 518 */     return (CSCoreLib.randomizer().nextInt(max) <= percentage);
/*     */   }
/*     */   
/*     */   public boolean isClearLagInstalled() {
/* 522 */     return this.clearlag;
/*     */   }
/*     */   
/*     */   public boolean isExoticGardenInstalled() {
/* 526 */     return this.exoticGarden;
/*     */   }
/*     */   
/*     */   public boolean isCoreProtectInstalled() {
/* 530 */     return this.coreProtect;
/*     */   }
/*     */   
/*     */   public CoreProtectAPI getCoreProtectAPI() {
/* 534 */     return this.coreProtectAPI;
/*     */   }
/*     */   public boolean isPlotSquaredInstalled() {
/* 537 */     return this.plotSquared;
/*     */   } public boolean isResidenceInstalled() {
/* 539 */     return this.residence;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\SlimefunStartup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */