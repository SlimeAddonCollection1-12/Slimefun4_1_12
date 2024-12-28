/*     */ package me.mrCookieSlime.Slimefun.api;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Clock;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class TickerTask implements Runnable {
/*  26 */   public Map<Location, Location> move = new HashMap<>(); public boolean HALTED = false;
/*  27 */   public Map<Location, Boolean> delete = new HashMap<>();
/*     */   
/*  29 */   private Set<BlockTicker> tickers = new HashSet<>();
/*     */   
/*  31 */   private int skipped = 0; private int chunks = 0; private int machines = 0;
/*  32 */   private long time = 0L;
/*  33 */   private Map<String, Integer> map_chunk = new HashMap<>();
/*  34 */   private Map<String, Integer> map_machine = new HashMap<>();
/*  35 */   private Map<String, Long> map_machinetime = new HashMap<>();
/*  36 */   private Map<String, Long> map_chunktime = new HashMap<>();
/*  37 */   private Set<String> skipped_chunks = new HashSet<>();
/*     */   
/*  39 */   public static Map<Location, Long> block_timings = new HashMap<>();
/*     */   
/*  41 */   public static Map<Location, Integer> bugged_blocks = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  46 */     long timestamp = System.currentTimeMillis();
/*     */     
/*  48 */     this.skipped = 0;
/*  49 */     this.chunks = 0;
/*  50 */     this.machines = 0;
/*  51 */     this.map_chunk.clear();
/*  52 */     this.map_machine.clear();
/*  53 */     this.time = 0L;
/*  54 */     this.map_chunktime.clear();
/*  55 */     this.skipped_chunks.clear();
/*  56 */     this.map_machinetime.clear();
/*  57 */     block_timings.clear();
/*     */     
/*  59 */     final Map<Location, Integer> bugged = new HashMap<>(bugged_blocks);
/*  60 */     bugged_blocks.clear();
/*     */     
/*  62 */     Map<Location, Boolean> remove = new HashMap<>(this.delete);
/*     */     
/*  64 */     for (Map.Entry<Location, Boolean> entry : remove.entrySet()) {
/*  65 */       BlockStorage._integrated_removeBlockInfo(entry.getKey(), ((Boolean)entry.getValue()).booleanValue());
/*  66 */       this.delete.remove(entry.getKey());
/*     */     } 
/*     */     
/*  69 */     if (!this.HALTED) {
/*  70 */       for (String c : BlockStorage.getTickingChunks()) {
/*  71 */         long timestamp2 = System.currentTimeMillis();
/*  72 */         this.chunks++;
/*     */ 
/*     */         
/*  75 */         for (Location l : BlockStorage.getTickingLocations(c)) {
/*  76 */           if (l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4)) {
/*  77 */             final Block b = l.getBlock();
/*  78 */             final SlimefunItem item = BlockStorage.check(l);
/*  79 */             if (item != null) {
/*  80 */               this.machines++;
/*     */               try {
/*  82 */                 item.getBlockTicker().update();
/*  83 */                 if (item.getBlockTicker().isSynchronized()) {
/*  84 */                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                       {
/*     */                         public void run()
/*     */                         {
/*     */                           try {
/*  89 */                             long timestamp3 = System.currentTimeMillis();
/*  90 */                             item.getBlockTicker().tick(b, item, BlockStorage.getLocationInfo(l));
/*     */                             
/*  92 */                             TickerTask.this.map_machinetime.put(item.getID(), Long.valueOf((TickerTask.this.map_machinetime.containsKey(item.getID()) ? ((Long)TickerTask.this.map_machinetime.get(item.getID())).longValue() : 0L) + System.currentTimeMillis() - timestamp3));
/*  93 */                             TickerTask.this.map_chunk.put(c, Integer.valueOf((TickerTask.this.map_chunk.containsKey(c) ? ((Integer)TickerTask.this.map_chunk.get(c)).intValue() : 0) + 1));
/*  94 */                             TickerTask.this.map_machine.put(item.getID(), Integer.valueOf((TickerTask.this.map_machine.containsKey(item.getID()) ? ((Integer)TickerTask.this.map_machine.get(item.getID())).intValue() : 0) + 1));
/*  95 */                             TickerTask.block_timings.put(l, Long.valueOf(System.currentTimeMillis() - timestamp3));
/*  96 */                           } catch (Exception x) {
/*  97 */                             int errors = 0;
/*  98 */                             if (bugged.containsKey(l)) errors = ((Integer)bugged.get(l)).intValue(); 
/*  99 */                             errors++;
/*     */                             
/* 101 */                             if (errors == 1) {
/* 102 */                               int try_count = 1;
/* 103 */                               File file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + ".err");
/* 104 */                               while (file.exists()) {
/* 105 */                                 try_count++;
/* 106 */                                 file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(" + try_count + ").err");
/*     */                               } 
/*     */                               try {
/* 109 */                                 PrintStream stream = new PrintStream(file);
/* 110 */                                 stream.println();
/* 111 */                                 stream.println("Server Software: " + Bukkit.getName());
/* 112 */                                 stream.println("  Build: " + Bukkit.getVersion());
/* 113 */                                 stream.println("  Minecraft: " + Bukkit.getBukkitVersion());
/* 114 */                                 stream.println();
/* 115 */                                 stream.println("Slimefun Environment:");
/* 116 */                                 stream.println("  CS-CoreLib v" + CSCoreLib.getLib().getDescription().getVersion());
/* 117 */                                 stream.println("  Slimefun v" + SlimefunStartup.instance.getDescription().getVersion());
/* 118 */                                 stream.println();
/*     */                                 
/* 120 */                                 List<String> plugins = new ArrayList<>();
/* 121 */                                 List<String> addons = new ArrayList<>();
/* 122 */                                 for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
/* 123 */                                   if (Bukkit.getPluginManager().isPluginEnabled(p)) {
/* 124 */                                     plugins.add("  + " + p.getName() + " " + p.getDescription().getVersion());
/* 125 */                                     if (p.getDescription().getDepend().contains("Slimefun") || p.getDescription().getSoftDepend().contains("Slimefun")) {
/* 126 */                                       addons.add("  + " + p.getName() + " " + p.getDescription().getVersion());
/*     */                                     }
/*     */                                   } else {
/* 129 */                                     plugins.add("  - " + p.getName() + " " + p.getDescription().getVersion());
/* 130 */                                     if (p.getDescription().getDepend().contains("Slimefun") || p.getDescription().getSoftDepend().contains("Slimefun")) {
/* 131 */                                       addons.add("  - " + p.getName() + " " + p.getDescription().getVersion());
/*     */                                     }
/*     */                                   } 
/*     */                                 } 
/* 135 */                                 stream.println(" Installed Addons (" + addons.size() + ")");
/* 136 */                                 for (String addon : addons) {
/* 137 */                                   stream.println(addon);
/*     */                                 }
/* 139 */                                 stream.println();
/* 140 */                                 stream.println("Installed Plugins (" + plugins.size() + "):");
/* 141 */                                 for (String plugin : plugins) {
/* 142 */                                   stream.println(plugin);
/*     */                                 }
/* 144 */                                 stream.println();
/* 145 */                                 stream.println("Ticked Block:");
/* 146 */                                 stream.println("  World: " + l.getWorld().getName());
/* 147 */                                 stream.println("  X: " + l.getBlockX());
/* 148 */                                 stream.println("  Y: " + l.getBlockY());
/* 149 */                                 stream.println("  Z: " + l.getBlockZ());
/* 150 */                                 stream.println();
/* 151 */                                 stream.println("Slimefun Data:");
/* 152 */                                 stream.println("  ID: " + item.getID());
/* 153 */                                 stream.println("  Inventory: " + BlockStorage.getStorage(l.getWorld()).hasInventory(l));
/* 154 */                                 stream.println("  Data: " + BlockStorage.getBlockInfoAsJson(l));
/* 155 */                                 stream.println();
/* 156 */                                 stream.println("Stacktrace:");
/* 157 */                                 stream.println();
/* 158 */                                 x.printStackTrace(stream);
/*     */                                 
/* 160 */                                 stream.close();
/* 161 */                               } catch (FileNotFoundException e) {
/* 162 */                                 e.printStackTrace();
/*     */                               } 
/*     */                               
/* 165 */                               System.err.println("[Slimefun] Exception caught while ticking a Block:" + x.getClass().getName());
/* 166 */                               System.err.println("[Slimefun] X: " + l.getBlockX() + " Y: " + l.getBlockY() + " Z: " + l.getBlockZ());
/* 167 */                               System.err.println("[Slimefun] Saved as: ");
/* 168 */                               System.err.println("[Slimefun] /plugins/Slimefun/error-reports/" + file.getName());
/* 169 */                               System.err.println("[Slimefun] Please consider sending this File to the developer(s) of Slimefun, sending this Error won't get you any help though.");
/* 170 */                               System.err.println("[Slimefun] ");
/*     */                               
/* 172 */                               TickerTask.bugged_blocks.put(l, Integer.valueOf(errors));
/*     */                             }
/* 174 */                             else if (errors == 4) {
/* 175 */                               System.err.println("[Slimefun] X: " + l.getBlockX() + " Y: " + l.getBlockY() + " Z: " + l.getBlockZ() + "(" + item.getID() + ")");
/* 176 */                               System.err.println("[Slimefun] has thrown 4 Exceptions in the last 4 Ticks, the Block has been terminated.");
/* 177 */                               System.err.println("[Slimefun] Check your /plugins/Slimefun/error-reports/ folder for details.");
/* 178 */                               System.err.println("[Slimefun] ");
/*     */                               
/* 180 */                               BlockStorage._integrated_removeBlockInfo(l, true);
/*     */                               
/* 182 */                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                                   {
/*     */                                     public void run()
/*     */                                     {
/* 186 */                                       l.getBlock().setType(Material.AIR);
/*     */                                     }
/*     */                                   });
/*     */                             }
/*     */                             else {
/*     */                               
/* 192 */                               TickerTask.bugged_blocks.put(l, Integer.valueOf(errors));
/*     */                             } 
/*     */                           } 
/*     */                         }
/*     */                       });
/*     */                 } else {
/*     */                   
/* 199 */                   long timestamp3 = System.currentTimeMillis();
/* 200 */                   item.getBlockTicker().tick(b, item, BlockStorage.getLocationInfo(l));
/*     */                   
/* 202 */                   this.map_machinetime.put(item.getID(), Long.valueOf((this.map_machinetime.containsKey(item.getID()) ? ((Long)this.map_machinetime.get(item.getID())).longValue() : 0L) + System.currentTimeMillis() - timestamp3));
/* 203 */                   this.map_chunk.put(c, Integer.valueOf((this.map_chunk.containsKey(c) ? ((Integer)this.map_chunk.get(c)).intValue() : 0) + 1));
/* 204 */                   this.map_machine.put(item.getID(), Integer.valueOf((this.map_machine.containsKey(item.getID()) ? ((Integer)this.map_machine.get(item.getID())).intValue() : 0) + 1));
/* 205 */                   block_timings.put(l, Long.valueOf(System.currentTimeMillis() - timestamp3));
/*     */                 } 
/* 207 */                 this.tickers.add(item.getBlockTicker());
/* 208 */               } catch (Exception x) {
/*     */                 
/* 210 */                 int errors = 0;
/* 211 */                 if (bugged.containsKey(l)) errors = ((Integer)bugged.get(l)).intValue(); 
/* 212 */                 errors++;
/*     */                 
/* 214 */                 if (errors == 1) {
/* 215 */                   File file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + ".err");
/* 216 */                   if (file.exists()) {
/* 217 */                     file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(2).err");
/* 218 */                     if (file.exists()) {
/* 219 */                       file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(3).err");
/* 220 */                       if (file.exists()) {
/* 221 */                         file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(4).err");
/* 222 */                         if (file.exists()) {
/* 223 */                           file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(5).err");
/* 224 */                           if (file.exists()) {
/* 225 */                             file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(6).err");
/* 226 */                             if (file.exists()) {
/* 227 */                               file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(7).err");
/* 228 */                               if (file.exists()) {
/* 229 */                                 file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(8).err");
/* 230 */                                 if (file.exists()) {
/* 231 */                                   file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(9).err");
/* 232 */                                   if (file.exists()) {
/* 233 */                                     file = new File("plugins/Slimefun/error-reports/" + Clock.getFormattedTime() + "(10).err");
/*     */                                   }
/*     */                                 } 
/*     */                               } 
/*     */                             } 
/*     */                           } 
/*     */                         } 
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                   try {
/* 244 */                     PrintStream stream = new PrintStream(file);
/* 245 */                     stream.println();
/* 246 */                     stream.println("Server Software: " + Bukkit.getName());
/* 247 */                     stream.println("  Build: " + Bukkit.getVersion());
/* 248 */                     stream.println("  Minecraft: " + Bukkit.getBukkitVersion());
/* 249 */                     stream.println();
/* 250 */                     stream.println("Installed Plugins (" + (Bukkit.getPluginManager().getPlugins()).length + ")");
/* 251 */                     for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
/* 252 */                       if (Bukkit.getPluginManager().isPluginEnabled(p)) {
/* 253 */                         stream.println("  + " + p.getName() + " " + p.getDescription().getVersion());
/*     */                       } else {
/*     */                         
/* 256 */                         stream.println("  - " + p.getName() + " " + p.getDescription().getVersion());
/*     */                       } 
/*     */                     } 
/* 259 */                     stream.println();
/* 260 */                     stream.println("Ticked Block:");
/* 261 */                     stream.println("  World: " + l.getWorld().getName());
/* 262 */                     stream.println("  X: " + l.getBlockX());
/* 263 */                     stream.println("  Y: " + l.getBlockY());
/* 264 */                     stream.println("  Z: " + l.getBlockZ());
/* 265 */                     stream.println();
/* 266 */                     stream.println("Slimefun Data:");
/* 267 */                     stream.println("  ID: " + item.getID());
/* 268 */                     stream.println("  Inventory: " + BlockStorage.getStorage(l.getWorld()).hasInventory(l));
/* 269 */                     stream.println("  Data: " + BlockStorage.getBlockInfoAsJson(l));
/* 270 */                     stream.println();
/* 271 */                     stream.println("Stacktrace:");
/* 272 */                     stream.println();
/* 273 */                     x.printStackTrace(stream);
/*     */                     
/* 275 */                     stream.close();
/* 276 */                   } catch (FileNotFoundException e) {
/* 277 */                     e.printStackTrace();
/*     */                   } 
/*     */                   
/* 280 */                   System.err.println("[Slimefun] Exception caught while ticking a Block:" + x.getClass().getName());
/* 281 */                   System.err.println("[Slimefun] X: " + l.getBlockX() + " Y: " + l.getBlockY() + " Z: " + l.getBlockZ());
/* 282 */                   System.err.println("[Slimefun] Saved as: ");
/* 283 */                   System.err.println("[Slimefun] /plugins/Slimefun/error-reports/" + file.getName());
/* 284 */                   System.err.println("[Slimefun] Please consider sending this File to the developer(s) of Slimefun, sending this Error won't get you any help though.");
/* 285 */                   System.err.println("[Slimefun] ");
/*     */                   
/* 287 */                   bugged_blocks.put(l, Integer.valueOf(errors)); continue;
/*     */                 } 
/* 289 */                 if (errors == 4) {
/* 290 */                   System.err.println("[Slimefun] X: " + l.getBlockX() + " Y: " + l.getBlockY() + " Z: " + l.getBlockZ() + "(" + item.getID() + ")");
/* 291 */                   System.err.println("[Slimefun] has thrown 4 Exceptions in the last 4 Ticks, the Block has been terminated.");
/* 292 */                   System.err.println("[Slimefun] Check your /plugins/Slimefun/error-reports/ folder for details.");
/* 293 */                   System.err.println("[Slimefun] ");
/*     */                   
/* 295 */                   BlockStorage._integrated_removeBlockInfo(l, true);
/*     */                   
/* 297 */                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                       {
/*     */                         public void run()
/*     */                         {
/* 301 */                           l.getBlock().setType(Material.AIR);
/*     */                         }
/*     */                       });
/*     */                   
/*     */                   continue;
/*     */                 } 
/* 307 */                 bugged_blocks.put(l, Integer.valueOf(errors));
/*     */               } 
/*     */               continue;
/*     */             } 
/* 311 */             this.skipped++;
/*     */             continue;
/*     */           } 
/* 314 */           this.skipped += BlockStorage.getTickingLocations(c).size();
/* 315 */           this.skipped_chunks.add(c);
/* 316 */           this.chunks--;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 321 */         this.map_chunktime.put(c, Long.valueOf(System.currentTimeMillis() - timestamp2));
/*     */       } 
/*     */     }
/*     */     
/* 325 */     for (Map.Entry<Location, Location> entry : this.move.entrySet()) {
/* 326 */       BlockStorage._integrated_moveLocationInfo(entry.getKey(), entry.getValue());
/*     */     }
/* 328 */     this.move.clear();
/*     */     
/* 330 */     for (BlockTicker ticker : this.tickers) {
/* 331 */       ticker.unique = true;
/*     */     }
/* 333 */     this.tickers.clear();
/*     */     
/* 335 */     this.time = System.currentTimeMillis() - timestamp;
/*     */   }
/*     */   
/*     */   public void info(CommandSender sender) {
/* 339 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2== &aSlimefun Diagnostic Tool &2=="));
/* 340 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Halted: &e&l" + String.valueOf(this.HALTED).toUpperCase()));
/* 341 */     sender.sendMessage("");
/* 342 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Impact: &e" + this.time + "ms / 50-750ms"));
/* 343 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Ticked Chunks: &e" + this.chunks));
/* 344 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Ticked Machines: &e" + this.machines));
/* 345 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Skipped Machines: &e" + this.skipped));
/* 346 */     sender.sendMessage("");
/* 347 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Ticking Machines:"));
/* 348 */     if (sender instanceof Player) {
/* 349 */       TellRawMessage tellraw = new TellRawMessage();
/* 350 */       tellraw.addText("   &7&oHover for more Info");
/* 351 */       StringBuilder hover = new StringBuilder();
/* 352 */       int hidden = 0;
/* 353 */       for (String item : this.map_machine.keySet()) {
/* 354 */         if (((Long)this.map_machinetime.get(item)).longValue() > 0L) { hover.append("\n&c" + item + " - " + this.map_machine.get(item) + "x &7(" + this.map_machinetime.get(item) + "ms)"); continue; }
/* 355 */          hidden++;
/*     */       } 
/* 357 */       hover.append("\n\n&c+ &4" + hidden + " Hidden");
/* 358 */       tellraw.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, hover.toString());
/*     */       try {
/* 360 */         tellraw.send(new Player[] { (Player)sender });
/* 361 */       } catch (Exception e) {
/* 362 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 366 */       int hidden = 0;
/* 367 */       for (String item : this.map_machine.keySet()) {
/* 368 */         if (((Long)this.map_machinetime.get(item)).longValue() > 0L) { sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &e" + item + " - " + this.map_machine.get(item) + "x &7(" + this.map_machinetime.get(item) + "ms)")); continue; }
/* 369 */          hidden++;
/*     */       } 
/* 371 */       sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c+ &4" + hidden + " Hidden"));
/*     */     } 
/* 373 */     sender.sendMessage("");
/* 374 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Ticking Chunks:"));
/* 375 */     if (sender instanceof Player) {
/* 376 */       TellRawMessage tellraw = new TellRawMessage();
/* 377 */       tellraw.addText("   &7&oHover for more Info");
/* 378 */       StringBuilder hover = new StringBuilder();
/* 379 */       int hidden = 0;
/* 380 */       for (String c : this.map_chunktime.keySet()) {
/* 381 */         if (!this.skipped_chunks.contains(c)) {
/* 382 */           if (((Long)this.map_chunktime.get(c)).longValue() > 0L) { hover.append("\n&c" + c.replace("CraftChunk", "") + " - " + (this.map_chunk.containsKey(c) ? ((Integer)this.map_chunk.get(c)).intValue() : 0) + "x &7(" + this.map_chunktime.get(c) + "ms)"); continue; }
/* 383 */            hidden++;
/*     */         } 
/*     */       } 
/* 386 */       hover.append("\n\n&c+ &4" + hidden + " Hidden");
/* 387 */       tellraw.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, hover.toString());
/*     */       try {
/* 389 */         tellraw.send(new Player[] { (Player)sender });
/* 390 */       } catch (Exception e) {
/* 391 */         e.printStackTrace();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 396 */       int hidden = 0;
/* 397 */       for (String c : this.map_chunktime.keySet()) {
/* 398 */         if (!this.skipped_chunks.contains(c)) {
/* 399 */           if (((Long)this.map_chunktime.get(c)).longValue() > 0L) { sender.sendMessage("  &c" + c.replace("CraftChunk", "") + " - " + (this.map_chunk.containsKey(c) ? ((Integer)this.map_chunk.get(c)).intValue() : 0) + "x &7(" + this.map_chunktime.get(c) + "ms)"); continue; }
/* 400 */            hidden++;
/*     */         } 
/*     */       } 
/* 403 */       sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c+ &4" + hidden + " Hidden"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getTimings(Block b) {
/* 408 */     return block_timings.containsKey(b.getLocation()) ? ((Long)block_timings.get(b.getLocation())).longValue() : 0L;
/*     */   }
/*     */   
/*     */   public long getTimings(String item) {
/* 412 */     return this.map_machinetime.containsKey(item) ? ((Long)this.map_machinetime.get(item)).longValue() : 0L;
/*     */   }
/*     */   
/*     */   public long getTimings(Chunk c) {
/* 416 */     return this.map_chunktime.containsKey(c.toString()) ? ((Long)this.map_chunktime.get(c.toString())).longValue() : 0L;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\TickerTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */