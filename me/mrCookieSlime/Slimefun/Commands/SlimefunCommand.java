/*     */ package me.mrCookieSlime.Slimefun.Commands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.CommandHelp;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.Players;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.TitleBuilder;
/*     */ import me.mrCookieSlime.Slimefun.GPS.Elevator;
/*     */ import me.mrCookieSlime.Slimefun.GPS.GPSNetwork;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunGuide;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlimefunCommand
/*     */   implements CommandExecutor, Listener
/*     */ {
/*     */   public SlimefunStartup plugin;
/*  44 */   public static List<String> arguments = new ArrayList<>();
/*  45 */   public static List<String> descriptions = new ArrayList<>();
/*  46 */   public static List<String> tabs = new ArrayList<>();
/*     */   
/*     */   public SlimefunCommand(SlimefunStartup plugin) {
/*  49 */     this.plugin = plugin;
/*     */     
/*  51 */     arguments.add("/sf help");
/*  52 */     tabs.add("help");
/*  53 */     descriptions.add(Messages.local.getTranslation("commands.help").get(0));
/*     */     
/*  55 */     arguments.add("/sf versions");
/*  56 */     tabs.add("versions");
/*  57 */     descriptions.add(Messages.local.getTranslation("commands.versions").get(0));
/*     */     
/*  59 */     arguments.add("/sf cheat");
/*  60 */     tabs.add("cheat");
/*  61 */     descriptions.add(Messages.local.getTranslation("commands.cheat").get(0));
/*     */     
/*  63 */     arguments.add("/sf give");
/*  64 */     tabs.add("give");
/*  65 */     descriptions.add(Messages.local.getTranslation("commands.give").get(0));
/*     */     
/*  67 */     arguments.add("/sf research");
/*  68 */     tabs.add("research");
/*  69 */     descriptions.add(Messages.local.getTranslation("commands.research.desc").get(0));
/*     */     
/*  71 */     arguments.add("/sf guide");
/*  72 */     tabs.add("guide");
/*  73 */     descriptions.add(Messages.local.getTranslation("commands.guide").get(0));
/*     */     
/*  75 */     arguments.add("/sf stats");
/*  76 */     tabs.add("stats");
/*  77 */     descriptions.add(Messages.local.getTranslation("commands.stats").get(0));
/*     */     
/*  79 */     arguments.add("/sf timings");
/*  80 */     tabs.add("timings");
/*  81 */     descriptions.add(Messages.local.getTranslation("commands.timings").get(0));
/*     */     
/*  83 */     arguments.add("/sf teleporter");
/*  84 */     tabs.add("teleporter");
/*  85 */     descriptions.add(Messages.local.getTranslation("commands.teleporter").get(0));
/*     */     
/*  87 */     arguments.add("/sf open_guide");
/*  88 */     tabs.add("open_guide");
/*  89 */     descriptions.add(Messages.local.getTranslation("commands.open_guide").get(0));
/*     */     
/*  91 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  97 */     if (args.length == 0) {
/*  98 */       sendHelp(sender);
/*     */     }
/* 100 */     else if (args.length > 0) {
/* 101 */       if (args[0].equalsIgnoreCase("cheat")) {
/* 102 */         if (sender instanceof Player)
/* 103 */         { if (sender.hasPermission("slimefun.cheat.items")) { SlimefunGuide.openCheatMenu((Player)sender); }
/* 104 */           else { Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]); }
/*     */            }
/* 106 */         else { Messages.local.sendTranslation(sender, "messages.only-players", true, new Variable[0]); }
/*     */       
/* 108 */       } else if (args[0].equalsIgnoreCase("guide")) {
/* 109 */         if (sender instanceof Player)
/* 110 */         { if (sender.hasPermission("slimefun.command.guide")) { ((Player)sender).getInventory().addItem(new ItemStack[] { SlimefunGuide.getItem(SlimefunStartup.getCfg().getBoolean("guide.default-view-book")) }); }
/* 111 */           else { Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]); }
/*     */            }
/* 113 */         else { Messages.local.sendTranslation(sender, "messages.only-players", true, new Variable[0]); }
/*     */       
/* 115 */       } else if (args[0].equalsIgnoreCase("open_guide")) {
/* 116 */         if (sender instanceof Player)
/* 117 */         { if (((Player)sender).isSleeping()) {
/* 118 */             sender.sendMessage("§c你不能在睡觉时使用这个命令");
/* 119 */             return true;
/*     */           } 
/* 121 */           if (sender.hasPermission("slimefun.command.open_guide")) { SlimefunGuide.openGuide((Player)sender, SlimefunStartup.getCfg().getBoolean("guide.default-view-book")); }
/* 122 */           else { Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]); }
/*     */            }
/* 124 */         else { Messages.local.sendTranslation(sender, "messages.only-players", true, new Variable[0]); }
/*     */       
/* 126 */       } else if (args[0].equalsIgnoreCase("debug_fish")) {
/* 127 */         if (sender instanceof Player && sender.isOp()) {
/* 128 */           ((Player)sender).getInventory().addItem(new ItemStack[] { SlimefunItems.DEBUG_FISH });
/*     */         } else {
/* 130 */           Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]);
/*     */         } 
/* 132 */       } else if (args[0].equalsIgnoreCase("stats")) {
/* 133 */         if (args.length > 1)
/* 134 */         { if (sender.hasPermission("slimefun.stats.others") || sender instanceof org.bukkit.command.ConsoleCommandSender)
/* 135 */           { if (Players.isOnline(args[1])) { Research.sendStats(sender, Bukkit.getPlayer(args[1])); }
/* 136 */             else { Messages.local.sendTranslation(sender, "messages.not-online", true, new Variable[] { new Variable("%player%", args[1]) }); }
/*     */              }
/* 138 */           else { Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]); }
/*     */            }
/* 140 */         else if (sender instanceof Player) { Research.sendStats(sender, (Player)sender); }
/* 141 */         else { Messages.local.sendTranslation(sender, "messages.only-players", true, new Variable[0]); }
/*     */       
/* 143 */       } else if (args[0].equalsIgnoreCase("elevator")) {
/* 144 */         if (sender instanceof Player && args.length == 4) {
/* 145 */           double x = Integer.parseInt(args[1]) + 0.5D;
/* 146 */           double y = Integer.parseInt(args[2]) + 0.4D;
/* 147 */           double z = Integer.parseInt(args[3]) + 0.5D;
/*     */           
/* 149 */           if (BlockStorage.getLocationInfo(((Player)sender).getWorld().getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).getLocation(), "floor") != null) {
/* 150 */             Elevator.ignored.add(((Player)sender).getUniqueId());
/* 151 */             float yaw = ((Player)sender).getEyeLocation().getYaw() + 180.0F;
/* 152 */             if (yaw > 180.0F) yaw = -180.0F + yaw - 180.0F; 
/* 153 */             ((Player)sender).teleport(new Location(((Player)sender).getWorld(), x, y, z, yaw, ((Player)sender).getEyeLocation().getPitch()));
/*     */             try {
/* 155 */               TitleBuilder title = (TitleBuilder)(new TitleBuilder(20, 60, 20)).addText("&r" + ChatColor.translateAlternateColorCodes('&', BlockStorage.getLocationInfo(((Player)sender).getWorld().getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).getLocation(), "floor")));
/* 156 */               TitleBuilder subtitle = (TitleBuilder)(new TitleBuilder(20, 60, 20)).addText(" ");
/*     */               
/* 158 */               title.send(TitleBuilder.TitleType.TITLE, new Player[] { (Player)sender });
/* 159 */               subtitle.send(TitleBuilder.TitleType.SUBTITLE, new Player[] { (Player)sender });
/* 160 */             } catch (Exception x1) {
/* 161 */               x1.printStackTrace();
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 166 */       } else if (args[0].equalsIgnoreCase("timings")) {
/* 167 */         if (sender.hasPermission("slimefun.command.timings") || sender instanceof org.bukkit.command.ConsoleCommandSender) {
/* 168 */           SlimefunStartup.ticker.info(sender);
/*     */         } else {
/* 170 */           Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]);
/*     */         } 
/* 172 */       } else if (args[0].equalsIgnoreCase("versions")) {
/* 173 */         if (sender.hasPermission("slimefun.command.versions") || sender instanceof org.bukkit.command.ConsoleCommandSender) {
/* 174 */           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + Bukkit.getName() + " &2" + ReflectionUtils.getVersion()));
/* 175 */           sender.sendMessage("");
/* 176 */           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCS-CoreLib &2v" + CSCoreLib.getLib().getDescription().getVersion()));
/* 177 */           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSlimefun &2v" + this.plugin.getDescription().getVersion()));
/* 178 */           sender.sendMessage("");
/*     */           
/* 180 */           List<String> addons = new ArrayList<>();
/*     */           
/* 182 */           for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
/* 183 */             if (plugin.getDescription().getDepend().contains("Slimefun") || plugin.getDescription().getSoftDepend().contains("Slimefun")) {
/* 184 */               if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
/* 185 */                 addons.add(ChatColor.translateAlternateColorCodes('&', " &a" + plugin.getName() + " &2v" + plugin.getDescription().getVersion()));
/*     */               } else {
/*     */                 
/* 188 */                 addons.add(ChatColor.translateAlternateColorCodes('&', " &c" + plugin.getName() + " &4v" + plugin.getDescription().getVersion()));
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 193 */           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7已安装的扩展 &8(" + addons.size() + ")"));
/*     */           
/* 195 */           for (String addon : addons) {
/* 196 */             sender.sendMessage(addon);
/*     */           }
/*     */         } else {
/*     */           
/* 200 */           Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]);
/*     */         }
/*     */       
/* 203 */       } else if (args[0].equalsIgnoreCase("give")) {
/* 204 */         if (sender.hasPermission("slimefun.cheat.items") || !(sender instanceof Player))
/* 205 */         { if (args.length == 3)
/* 206 */           { if (Players.isOnline(args[1]))
/* 207 */             { if (Slimefun.listIDs().contains(args[2].toUpperCase())) {
/* 208 */                 Messages.local.sendTranslation((CommandSender)Bukkit.getPlayer(args[1]), "messages.given-item", true, new Variable[] { new Variable("%item%", SlimefunItem.getByID(args[2].toUpperCase()).getItem().getItemMeta().getDisplayName()), new Variable("%amount%", "1") });
/* 209 */                 Bukkit.getPlayer(args[1]).getInventory().addItem(new ItemStack[] { SlimefunItem.getByID(args[2].toUpperCase()).getItem() });
/* 210 */                 Messages.local.sendTranslation(sender, "messages.give-item", true, new Variable[] { new Variable("%player%", args[1]), new Variable("%item%", SlimefunItem.getByID(args[2].toUpperCase()).getItem().getItemMeta().getDisplayName()), new Variable("%amount%", "1") });
/*     */               } else {
/* 212 */                 Messages.local.sendTranslation(sender, "messages.not-valid-item", true, new Variable[] { new Variable("%item%", args[2]) });
/*     */               }  }
/* 214 */             else { Messages.local.sendTranslation(sender, "messages.not-online", true, new Variable[] { new Variable("%player%", args[1]) }); }
/*     */              }
/* 216 */           else if (args.length == 4)
/* 217 */           { if (Players.isOnline(args[1]))
/* 218 */             { if (Slimefun.listIDs().contains(args[2].toUpperCase())) {
/*     */                 try {
/* 220 */                   int amount = Integer.parseInt(args[3]);
/*     */                   
/* 222 */                   if (amount > 0)
/* 223 */                   { Messages.local.sendTranslation((CommandSender)Bukkit.getPlayer(args[1]), "messages.given-item", true, new Variable[] { new Variable("%item%", SlimefunItem.getByID(args[2].toUpperCase()).getItem().getItemMeta().getDisplayName()), new Variable("%amount%", String.valueOf(amount)) });
/* 224 */                     Bukkit.getPlayer(args[1]).getInventory().addItem(new ItemStack[] { (ItemStack)new CustomItem(SlimefunItem.getByID(args[2].toUpperCase()).getItem(), amount) });
/* 225 */                     Messages.local.sendTranslation(sender, "messages.give-item", true, new Variable[] { new Variable("%player%", args[1]), new Variable("%item%", SlimefunItem.getByID(args[2].toUpperCase()).getItem().getItemMeta().getDisplayName()), new Variable("%amount%", String.valueOf(amount)) }); }
/*     */                   else
/* 227 */                   { Messages.local.sendTranslation(sender, "messages.not-valid-amount", true, new Variable[] { new Variable("%amount%", String.valueOf(amount)) }); } 
/* 228 */                 } catch (NumberFormatException e) {
/* 229 */                   Messages.local.sendTranslation(sender, "messages.not-valid-amount", true, new Variable[] { new Variable("%amount%", args[3]) });
/*     */                 } 
/*     */               } else {
/* 232 */                 Messages.local.sendTranslation(sender, "messages.not-valid-item", true, new Variable[] { new Variable("%item%", args[2]) });
/*     */               }  }
/* 234 */             else { Messages.local.sendTranslation(sender, "messages.not-online", true, new Variable[] { new Variable("%player%", args[1]) }); }
/*     */              }
/* 236 */           else { Messages.local.sendTranslation(sender, "messages.usage", true, new Variable[] { new Variable("%usage%", "/sf give <玩家名> <SF物品名> [数量]") }); }
/*     */            }
/* 238 */         else { Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]); }
/*     */       
/* 240 */       } else if (args[0].equalsIgnoreCase("teleporter")) {
/* 241 */         if (args.length == 2)
/* 242 */         { if (sender.hasPermission("slimefun.command.teleporter") && sender instanceof Player)
/* 243 */           { OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
/* 244 */             if (player.getName() != null) {
/*     */               try {
/* 246 */                 GPSNetwork.openTeleporterGUI((Player)sender, player.getUniqueId(), ((Player)sender).getLocation().getBlock().getRelative(BlockFace.DOWN), 999999999);
/* 247 */               } catch (Exception e) {
/* 248 */                 e.printStackTrace();
/*     */               } 
/*     */             } else {
/* 251 */               sender.sendMessage("&4未知玩家: &c" + args[1]);
/*     */             }  }
/* 253 */           else { Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]); }
/*     */            }
/* 255 */         else { Messages.local.sendTranslation(sender, "messages.usage", true, new Variable[] { new Variable("%usage%", "/sf teleporter <玩家名>") }); }
/*     */       
/* 257 */       } else if (args[0].equalsIgnoreCase("research")) {
/* 258 */         if (args.length == 3) {
/* 259 */           if (sender.hasPermission("slimefun.cheat.researches") || !(sender instanceof Player)) {
/* 260 */             if (Players.isOnline(args[1])) {
/* 261 */               Player p = Bukkit.getPlayer(args[1]);
/* 262 */               if (args[2].equalsIgnoreCase("all")) {
/* 263 */                 for (Research res : Research.list()) {
/* 264 */                   if (!res.hasUnlocked(p)) Messages.local.sendTranslation(sender, "messages.give-research", true, new Variable[] { new Variable("%player%", p.getName()), new Variable("%research%", res.getName()) }); 
/* 265 */                   res.unlock(p, true);
/*     */                 }
/*     */               
/* 268 */               } else if (args[2].equalsIgnoreCase("reset")) {
/* 269 */                 for (Research res : Research.list()) {
/* 270 */                   res.lock(p);
/*     */                 }
/* 272 */                 Messages.local.sendTranslation((CommandSender)p, "commands.research.reset", true, new Variable[] { new Variable("%player%", args[1]) });
/*     */               } else {
/*     */                 
/* 275 */                 Research research = null;
/* 276 */                 for (Research res : Research.list()) {
/* 277 */                   if (res.getName().toUpperCase().replace(" ", "_").equalsIgnoreCase(args[2])) {
/* 278 */                     research = res;
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */                 } 
/* 283 */                 if (research != null) {
/* 284 */                   research.unlock(p, true);
/* 285 */                   Messages.local.sendTranslation(sender, "messages.give-research", true, new Variable[] { new Variable("%player%", p.getName()), new Variable("%research%", research.getName()) });
/*     */                 } else {
/*     */                   
/* 288 */                   Messages.local.sendTranslation(sender, "messages.not-valid-research", true, new Variable[] { new Variable("%research%", args[2]) });
/*     */                 } 
/*     */               } 
/*     */             } else {
/*     */               
/* 293 */               Messages.local.sendTranslation(sender, "messages.not-online", true, new Variable[] { new Variable("%player%", args[1]) });
/*     */             } 
/*     */           } else {
/* 296 */             Messages.local.sendTranslation(sender, "messages.no-permission", true, new Variable[0]);
/*     */           } 
/*     */         } else {
/* 299 */           Messages.local.sendTranslation(sender, "messages.usage", true, new Variable[] { new Variable("%usage%", "/sf research <玩家名> <all/reset/Research>") });
/*     */         } 
/*     */       } else {
/*     */         
/* 303 */         sendHelp(sender);
/*     */       } 
/*     */     } 
/* 306 */     return true;
/*     */   }
/*     */   
/*     */   private void sendHelp(CommandSender sender) {
/* 310 */     sender.sendMessage("");
/* 311 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSlimefun &2v" + this.plugin.getDescription().getVersion()));
/* 312 */     sender.sendMessage("");
/* 313 */     for (int i = 0; i < arguments.size(); i++) {
/* 314 */       sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3" + (String)arguments.get(i) + " &b") + (String)descriptions.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onCommand(PlayerCommandPreprocessEvent e) {
/* 320 */     if (e.getMessage().equalsIgnoreCase("/help slimefun")) {
/* 321 */       CommandHelp.sendCommandHelp((CommandSender)e.getPlayer(), (Plugin)this.plugin, arguments, descriptions);
/* 322 */       e.setCancelled(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Commands\SlimefunCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */