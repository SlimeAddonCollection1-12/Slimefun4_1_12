/*     */ package me.mrCookieSlime.Slimefun.api;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.json.simple.parser.JSONParser;
/*     */ import org.json.simple.parser.ParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStorage
/*     */ {
/*     */   private static final String path_blocks = "data-storage/Slimefun/stored-blocks/";
/*     */   private static final String path_chunks = "data-storage/Slimefun/stored-chunks/";
/*  38 */   public static Map<String, BlockStorage> worlds = new HashMap<>();
/*  39 */   public static Map<String, Set<Location>> ticking_chunks = new HashMap<>();
/*  40 */   public static Set<String> loaded_tickers = new HashSet<>();
/*     */   
/*     */   private World world;
/*     */   
/*  44 */   private Map<Location, Config> storage = new HashMap<>();
/*  45 */   private static Map<String, String> map_chunks = new HashMap<>();
/*     */   
/*  47 */   private Map<Location, BlockMenu> inventories = new HashMap<>();
/*  48 */   public static Map<String, UniversalBlockMenu> universal_inventories = new HashMap<>();
/*     */   
/*  50 */   private Map<String, Config> cache_blocks = new HashMap<>();
/*     */   
/*     */   public static int info_delay;
/*     */   
/*     */   public static BlockStorage getStorage(World world) {
/*  55 */     return worlds.get(world.getName());
/*     */   }
/*     */   
/*     */   public static BlockStorage getForcedStorage(World world) {
/*  59 */     return isWorldRegistered(world.getName()) ? worlds.get(world.getName()) : new BlockStorage(world);
/*     */   }
/*     */   
/*     */   private static String serializeLocation(Location l) {
/*  63 */     return l.getWorld().getName() + ";" + l.getBlockX() + ";" + l.getBlockY() + ";" + l.getBlockZ();
/*     */   }
/*     */   
/*     */   private static String serializeChunk(Chunk chunk) {
/*  67 */     return chunk.getWorld().getName() + ";Chunk;" + chunk.getX() + ";" + chunk.getZ();
/*     */   }
/*     */   
/*     */   private static String locationToChunkString(Location l) {
/*  71 */     return l.getWorld().getName() + ";Chunk;" + (l.getBlockX() >> 4) + ";" + (l.getBlockZ() >> 4);
/*     */   }
/*     */   
/*     */   private static Location deserializeLocation(String l) {
/*     */     try {
/*  76 */       World w = Bukkit.getWorld(l.split(";")[0]);
/*  77 */       if (w != null) return new Location(w, Integer.parseInt(l.split(";")[1]), Integer.parseInt(l.split(";")[2]), Integer.parseInt(l.split(";")[3])); 
/*  78 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/*  80 */     return null;
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
/* 179 */   private static int chunk_changes = 0;
/* 180 */   private int changes; public BlockStorage(World w) { this.changes = 0; if (worlds.containsKey(w.getName())) return;  this.world = w; System.out.println("[远古工艺] 正在加载世界中的方块 \"" + w.getName() + "\""); System.out.println("[远古工艺] 可能需要花费一些时间..."); File f = new File("data-storage/Slimefun/stored-blocks/" + w.getName()); if (f.exists()) { long total = (f.listFiles()).length, start = System.currentTimeMillis(); long done = 0L, timestamp = System.currentTimeMillis(), totalBlocks = 0L; try { for (File file : f.listFiles()) { if (file.getName().endsWith(".sfb")) { if (timestamp + info_delay < System.currentTimeMillis()) { System.out.println("[远古工艺] 加载方块中... " + Math.round((float)done * 100.0F / (float)total * 100.0F / 100.0F) + "% 完成 (\"" + w.getName() + "\")"); timestamp = System.currentTimeMillis(); }  YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file); for (String key : yamlConfiguration.getKeys(false)) { Location l = deserializeLocation(key); String chunk_string = locationToChunkString(l); try { totalBlocks++; String json = yamlConfiguration.getString(key); Config blockInfo = parseBlockInfo(l, json); if (blockInfo == null) continue;  this.storage.put(l, blockInfo); if (SlimefunItem.isTicking(file.getName().replace(".sfb", ""))) { Set<Location> locations = ticking_chunks.containsKey(chunk_string) ? ticking_chunks.get(chunk_string) : new HashSet<>(); locations.add(l); ticking_chunks.put(chunk_string, locations); if (!loaded_tickers.contains(chunk_string))
/*     */                     loaded_tickers.add(chunk_string);  }  } catch (Exception x) { System.err.println("[远古工艺] 加载失败 " + file.getName() + "(ERR: " + key + ")"); x.printStackTrace(); }  }  done++; }  }  } finally { long time = System.currentTimeMillis() - start; System.out.println("[远古工艺] 加载方块中... 100% (已完成 - " + time + "ms)"); System.out.println("[远古工艺] 共计加载 " + totalBlocks + " 个方块，位于世界 \"" + this.world.getName() + "\""); if (totalBlocks > 0L)
/*     */           System.out.println("[远古工艺] Avg: " + DoubleHandler.fixDouble(time / totalBlocks, 3) + "ms/方块");  }  } else { f.mkdirs(); }  File chunks = new File("data-storage/Slimefun/stored-chunks/chunks.sfc"); if (chunks.exists()) { YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(chunks); for (String key : yamlConfiguration.getKeys(false)) { try { if (this.world.getName().equals(key.split(";")[0]))
/* 183 */             map_chunks.put(key, yamlConfiguration.getString(key));  } catch (Exception x) { System.err.println("[远古工艺] 加载失败 " + chunks.getName() + " 位于世界 \"" + this.world.getName() + "\" (ERR: " + key + ")"); x.printStackTrace(); }  }  }  worlds.put(this.world.getName(), this); for (File file : (new File("data-storage/Slimefun/stored-inventories")).listFiles()) { if (file.getName().startsWith(w.getName()) && file.getName().endsWith(".sfi")) { Location l = deserializeLocation(file.getName().replace(".sfi", "")); Config cfg = new Config(file); try { if (cfg.getString("preset") != null) { BlockMenuPreset preset = BlockMenuPreset.getPreset(cfg.getString("preset")); this.inventories.put(l, new BlockMenu(preset, l, cfg)); } else { BlockMenuPreset preset = BlockMenuPreset.getPreset(checkID(l)); this.inventories.put(l, new BlockMenu(preset, l, cfg)); }  } catch (Exception exception) {} }  }  for (File file : (new File("data-storage/Slimefun/universal-inventories")).listFiles()) { if (file.getName().endsWith(".sfi")) { Config cfg = new Config(file); BlockMenuPreset preset = BlockMenuPreset.getPreset(cfg.getString("preset")); universal_inventories.put(preset.getID(), new UniversalBlockMenu(preset, cfg)); }  }  } public void computeChanges() { this.changes = this.cache_blocks.size() + chunk_changes;
/*     */     
/* 185 */     Map<Location, BlockMenu> inventories2 = new HashMap<>(this.inventories);
/* 186 */     for (Map.Entry<Location, BlockMenu> entry : inventories2.entrySet()) {
/* 187 */       this.changes += ((BlockMenu)entry.getValue()).changes;
/*     */     }
/*     */     
/* 190 */     Map<String, UniversalBlockMenu> universal_inventories2 = new HashMap<>(universal_inventories);
/* 191 */     for (Map.Entry<String, UniversalBlockMenu> entry : universal_inventories2.entrySet()) {
/* 192 */       this.changes += ((UniversalBlockMenu)entry.getValue()).changes;
/*     */     } }
/*     */ 
/*     */   
/*     */   public int getChanges() {
/* 197 */     return this.changes;
/*     */   }
/*     */   
/*     */   public void save(boolean remove) {
/* 201 */     save(true, remove);
/*     */   }
/*     */   
/*     */   public void save(boolean computeChanges, boolean remove) {
/* 205 */     if (computeChanges) computeChanges();
/*     */     
/* 207 */     if (this.changes == 0)
/*     */       return; 
/* 209 */     System.out.println("[远古工艺] 正在为世界 \"" + this.world.getName() + "\" 保存方块信息 (" + this.changes + " 个改变位于队列中)");
/*     */     
/* 211 */     Map<String, Config> cache = new HashMap<>(this.cache_blocks);
/*     */     
/* 213 */     for (Map.Entry<String, Config> entry : cache.entrySet()) {
/* 214 */       this.cache_blocks.remove(entry.getKey());
/* 215 */       Config cfg = entry.getValue();
/* 216 */       if (cfg.getKeys().isEmpty()) {
/* 217 */         cfg.getFile().delete(); continue;
/*     */       } 
/* 219 */       File tmpFile = new File(cfg.getFile().getParentFile(), cfg.getFile().getName() + ".tmp");
/* 220 */       cfg.save(tmpFile);
/*     */       try {
/* 222 */         Files.move(tmpFile.toPath(), cfg.getFile().toPath(), new CopyOption[] { StandardCopyOption.ATOMIC_MOVE });
/* 223 */       } catch (IOException e) {
/* 224 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 229 */     Map<Location, BlockMenu> inventories2 = new HashMap<>(this.inventories);
/*     */     
/* 231 */     for (Map.Entry<Location, BlockMenu> entry : inventories2.entrySet()) {
/* 232 */       ((BlockMenu)entry.getValue()).save(entry.getKey());
/*     */     }
/*     */     
/* 235 */     Map<String, UniversalBlockMenu> universal_inventories2 = new HashMap<>(universal_inventories);
/*     */     
/* 237 */     for (Map.Entry<String, UniversalBlockMenu> entry : universal_inventories2.entrySet()) {
/* 238 */       ((UniversalBlockMenu)entry.getValue()).save();
/*     */     }
/*     */     
/* 241 */     if (chunk_changes > 0) {
/* 242 */       File chunks = new File("data-storage/Slimefun/stored-chunks/chunks.sfc");
/* 243 */       Config cfg = new Config("data-storage/Slimefun/temp.yml");
/*     */       
/* 245 */       for (Map.Entry<String, String> entry : map_chunks.entrySet()) {
/* 246 */         cfg.setValue(entry.getKey(), entry.getValue());
/*     */       }
/*     */       
/* 249 */       cfg.save(chunks);
/*     */       
/* 251 */       if (remove) {
/* 252 */         worlds.remove(this.world.getName());
/*     */       }
/*     */     } 
/*     */     
/* 256 */     this.changes = 0;
/* 257 */     chunk_changes = 0;
/*     */   }
/*     */   
/*     */   public static void store(Block block, ItemStack item) {
/* 261 */     SlimefunItem sfitem = SlimefunItem.getByItem(item);
/* 262 */     if (sfitem != null) addBlockInfo(block, "id", sfitem.getID(), true); 
/*     */   }
/*     */   
/*     */   public static void store(Block block, String item) {
/* 266 */     addBlockInfo(block, "id", item, true);
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
/*     */   public static ItemStack retrieve(Block block) {
/* 279 */     if (!hasBlockInfo(block)) return null;
/*     */     
/* 281 */     SlimefunItem item = SlimefunItem.getByID(getLocationInfo(block.getLocation(), "id"));
/* 282 */     clearBlockInfo(block);
/* 283 */     if (item == null) return null; 
/* 284 */     return item.getItem();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Config getBlockInfo(Block block) {
/* 290 */     return getLocationInfo(block.getLocation());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Config getBlockInfo(Location l) {
/* 295 */     return getLocationInfo(l);
/*     */   }
/*     */   
/*     */   public static Config getLocationInfo(Location l) {
/* 299 */     BlockStorage storage = getStorage(l.getWorld());
/* 300 */     Config cfg = storage.storage.get(l);
/* 301 */     return (cfg == null) ? new BlockInfoConfig() : cfg;
/*     */   }
/*     */   
/*     */   private static Map<String, String> parseJSON(String json) {
/* 305 */     Map<String, String> map = new HashMap<>();
/*     */     
/* 307 */     if (json != null && json.length() > 2) {
/*     */       try {
/* 309 */         JSONParser parser = new JSONParser();
/* 310 */         JSONObject obj = (JSONObject)parser.parse(json);
/* 311 */         for (Object entry : obj.keySet()) {
/* 312 */           String key = entry.toString();
/* 313 */           String value = obj.get(entry).toString();
/* 314 */           map.put(key, value);
/*     */         }
/*     */       
/* 317 */       } catch (ParseException e) {
/* 318 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 321 */     return map;
/*     */   }
/*     */   
/*     */   private static BlockInfoConfig parseBlockInfo(Location l, String json) {
/*     */     try {
/* 326 */       return new BlockInfoConfig(parseJSON(json));
/* 327 */     } catch (Exception x) {
/* 328 */       System.err.println(x.getClass().getName());
/* 329 */       System.err.println("[远古工艺] Failed to parse BlockInfo for Block @ " + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ());
/* 330 */       System.err.println(json);
/* 331 */       System.err.println("[远古工艺] ");
/* 332 */       System.err.println("[远古工艺] IGNORE THIS ERROR UNLESS IT IS SPAMMING");
/* 333 */       System.err.println("[远古工艺] ");
/* 334 */       x.printStackTrace();
/* 335 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String serializeBlockInfo(Config cfg) {
/* 341 */     JSONObject json = new JSONObject();
/* 342 */     for (String key : cfg.getKeys()) {
/* 343 */       json.put(key, cfg.getString(key));
/*     */     }
/* 345 */     return json.toJSONString();
/*     */   }
/*     */   private static String getJSONData(Chunk chunk) {
/* 348 */     return map_chunks.get(serializeChunk(chunk));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static String getBlockInfo(Block block, String key) {
/* 353 */     return getLocationInfo(block.getLocation(), key);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static String getBlockInfo(Location l, String key) {
/* 358 */     return getLocationInfo(l, key);
/*     */   }
/*     */   
/*     */   public static String getLocationInfo(Location l, String key) {
/* 362 */     return getBlockInfo(l).getString(key);
/*     */   }
/*     */   
/*     */   public static void addBlockInfo(Location l, String key, String value) {
/* 366 */     addBlockInfo(l, key, value, false);
/*     */   }
/*     */   
/*     */   public static void addBlockInfo(Block block, String key, String value) {
/* 370 */     addBlockInfo(block.getLocation(), key, value);
/*     */   }
/*     */   
/*     */   public static void addBlockInfo(Block block, String key, String value, boolean updateTicker) {
/* 374 */     addBlockInfo(block.getLocation(), key, value, updateTicker);
/*     */   }
/*     */   
/*     */   public static void addBlockInfo(Location l, String key, String value, boolean updateTicker) {
/* 378 */     Config cfg = hasBlockInfo(l) ? getLocationInfo(l) : new BlockInfoConfig();
/* 379 */     cfg.setValue(key, value);
/* 380 */     setBlockInfo(l, cfg, updateTicker);
/*     */   }
/*     */   
/*     */   public static boolean hasBlockInfo(Block block) {
/* 384 */     return hasBlockInfo(block.getLocation());
/*     */   }
/*     */   
/*     */   public static boolean hasBlockInfo(Location l) {
/* 388 */     BlockStorage storage = getStorage(l.getWorld());
/* 389 */     return (storage != null && storage.storage.containsKey(l) && getLocationInfo(l, "id") != null);
/*     */   }
/*     */   
/*     */   public static void setBlockInfo(Block block, Config cfg, boolean updateTicker) {
/* 393 */     setBlockInfo(block.getLocation(), cfg, updateTicker);
/*     */   }
/*     */   
/*     */   public static void setBlockInfo(Location l, Config cfg, boolean updateTicker) {
/* 397 */     BlockStorage storage = getStorage(l.getWorld());
/*     */     
/* 399 */     if (storage == null) {
/* 400 */       storage = getForcedStorage(l.getWorld());
/*     */     }
/* 402 */     storage.storage.put(l, cfg);
/* 403 */     if (BlockMenuPreset.isInventory(cfg.getString("id")))
/* 404 */       if (BlockMenuPreset.isUniversalInventory(cfg.getString("id"))) {
/* 405 */         if (!universal_inventories.containsKey(cfg.getString("id"))) storage.loadUniversalInventory(BlockMenuPreset.getPreset(cfg.getString("id")));
/*     */       
/* 407 */       } else if (!storage.hasInventory(l)) {
/* 408 */         File file = new File("data-storage/Slimefun/stored-inventories/" + serializeLocation(l) + ".sfi");
/*     */         
/* 410 */         if (file.exists()) { storage.inventories.put(l, new BlockMenu(BlockMenuPreset.getPreset(cfg.getString("id")), l, new Config(file))); }
/* 411 */         else { storage.loadInventory(l, BlockMenuPreset.getPreset(cfg.getString("id"))); }
/*     */       
/*     */       }  
/* 414 */     refreshCache(getStorage(l.getWorld()), l, cfg.getString("id"), serializeBlockInfo(cfg), updateTicker);
/*     */   }
/*     */   public static void setBlockInfo(Block b, String json, boolean updateTicker) {
/* 417 */     setBlockInfo(b.getLocation(), json, updateTicker);
/*     */   }
/*     */   public static void setBlockInfo(Location l, String json, boolean updateTicker) {
/* 420 */     Config blockInfo = (json == null) ? new BlockInfoConfig() : parseBlockInfo(l, json);
/* 421 */     if (blockInfo == null)
/* 422 */       return;  setBlockInfo(l, blockInfo, updateTicker);
/*     */   }
/*     */   
/*     */   public static void clearBlockInfo(Block block) {
/* 426 */     clearBlockInfo(block.getLocation());
/*     */   }
/*     */   
/*     */   public static void clearBlockInfo(Location l) {
/* 430 */     clearBlockInfo(l, true);
/*     */   }
/*     */   
/*     */   public static void clearBlockInfo(Block b, boolean destroy) {
/* 434 */     clearBlockInfo(b.getLocation(), destroy);
/*     */   }
/*     */   
/*     */   public static void clearBlockInfo(Location l, boolean destroy) {
/* 438 */     SlimefunStartup.ticker.delete.put(l, Boolean.valueOf(destroy));
/*     */   }
/*     */   
/*     */   public static void _integrated_removeBlockInfo(Location l, boolean destroy) {
/* 442 */     BlockStorage storage = getStorage(l.getWorld());
/* 443 */     if (hasBlockInfo(l)) {
/* 444 */       refreshCache(storage, l, getLocationInfo(l).getString("id"), null, destroy);
/* 445 */       storage.storage.remove(l);
/*     */     } 
/*     */     
/* 448 */     if (destroy) {
/* 449 */       if (storage.hasInventory(l)) storage.clearInventory(l); 
/* 450 */       if (storage.hasUniversalInventory(l)) {
/* 451 */         storage.getUniversalInventory(l).close();
/* 452 */         storage.getUniversalInventory(l).save();
/*     */       } 
/* 454 */       String chunk_string = locationToChunkString(l);
/* 455 */       if (ticking_chunks.containsKey(chunk_string)) {
/* 456 */         Set<Location> locations = ticking_chunks.get(chunk_string);
/* 457 */         locations.remove(l);
/* 458 */         if (locations.isEmpty()) {
/* 459 */           ticking_chunks.remove(chunk_string);
/* 460 */           loaded_tickers.remove(chunk_string);
/*     */         } else {
/* 462 */           ticking_chunks.put(chunk_string, locations);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @Deprecated
/*     */   public static void moveBlockInfo(Block block, Block newBlock) {
/* 469 */     moveBlockInfo(block.getLocation(), newBlock.getLocation());
/*     */   }
/*     */   
/*     */   public static void moveBlockInfo(Location from, Location to) {
/* 473 */     SlimefunStartup.ticker.move.put(from, to);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void _integrated_moveBlockInfo(Block block, Block newBlock) {
/* 478 */     _integrated_moveLocationInfo(block.getLocation(), newBlock.getLocation());
/*     */   }
/*     */   
/*     */   public static void _integrated_moveLocationInfo(Location from, Location to) {
/* 482 */     if (!hasBlockInfo(from))
/* 483 */       return;  BlockStorage storage = getStorage(from.getWorld());
/*     */     
/* 485 */     setBlockInfo(to, getLocationInfo(from), true);
/* 486 */     if (storage.inventories.containsKey(from)) {
/* 487 */       BlockMenu menu = storage.inventories.get(from);
/* 488 */       storage.inventories.put(to, menu);
/* 489 */       storage.clearInventory(from);
/* 490 */       menu.move(to);
/*     */     } 
/*     */     
/* 493 */     refreshCache(storage, from, getLocationInfo(from).getString("id"), null, true);
/* 494 */     storage.storage.remove(from);
/*     */     
/* 496 */     String chunk_string = locationToChunkString(from);
/* 497 */     if (ticking_chunks.containsKey(chunk_string)) {
/* 498 */       Set<Location> locations = ticking_chunks.get(chunk_string);
/* 499 */       locations.remove(from);
/* 500 */       if (locations.isEmpty()) {
/* 501 */         ticking_chunks.remove(chunk_string);
/* 502 */         loaded_tickers.remove(chunk_string);
/*     */       } else {
/* 504 */         ticking_chunks.put(chunk_string, locations);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private static void refreshCache(BlockStorage storage, Location l, String key, String value, boolean updateTicker) {
/* 509 */     Config cfg = storage.cache_blocks.containsKey(key) ? storage.cache_blocks.get(key) : new Config("data-storage/Slimefun/stored-blocks/" + l.getWorld().getName() + "/" + key + ".sfb");
/* 510 */     cfg.setValue(serializeLocation(l), value);
/* 511 */     storage.cache_blocks.put(key, cfg);
/*     */     
/* 513 */     if (updateTicker) {
/* 514 */       SlimefunItem item = SlimefunItem.getByID(key);
/* 515 */       if (item != null && item.isTicking()) {
/* 516 */         String chunk_string = locationToChunkString(l);
/* 517 */         if (value != null) {
/* 518 */           Set<Location> locations = ticking_chunks.containsKey(chunk_string) ? ticking_chunks.get(chunk_string) : new HashSet<>();
/* 519 */           locations.add(l);
/* 520 */           ticking_chunks.put(chunk_string, locations);
/* 521 */           if (!loaded_tickers.contains(chunk_string)) loaded_tickers.add(chunk_string); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SlimefunItem check(Block block) {
/* 528 */     return check(block.getLocation());
/*     */   }
/*     */   
/*     */   public static SlimefunItem check(Location l) {
/* 532 */     if (!hasBlockInfo(l)) return null; 
/* 533 */     return SlimefunItem.getByID(getLocationInfo(l, "id"));
/*     */   }
/*     */   
/*     */   public static String checkID(Block block) {
/* 537 */     return checkID(block.getLocation());
/*     */   }
/*     */   
/*     */   public static boolean check(Block block, String slimefunItem) {
/* 541 */     return check(block.getLocation(), slimefunItem);
/*     */   }
/*     */   
/*     */   public static String checkID(Location l) {
/* 545 */     if (!hasBlockInfo(l)) return null; 
/* 546 */     return getLocationInfo(l, "id");
/*     */   }
/*     */   
/*     */   public static boolean check(Location l, String slimefunItem) {
/* 550 */     if (!hasBlockInfo(l)) return false; 
/*     */     try {
/* 552 */       String id = getLocationInfo(l, "id");
/* 553 */       return (id != null && id.equalsIgnoreCase(slimefunItem));
/*     */     }
/* 555 */     catch (NullPointerException x) {
/* 556 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isWorldRegistered(String name) {
/* 561 */     return worlds.containsKey(name);
/*     */   }
/*     */   
/*     */   public static Set<String> getTickingChunks() {
/* 565 */     return new HashSet<>(loaded_tickers);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Set<Block> getTickingBlocks(Chunk chunk) {
/* 570 */     return getTickingBlocks(chunk.toString());
/*     */   }
/*     */   
/*     */   public static Set<Location> getTickingLocations(Chunk chunk) {
/* 574 */     return getTickingLocations(chunk.toString());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Set<Block> getTickingBlocks(String chunk) {
/* 579 */     Set<Block> ret = new HashSet<>();
/* 580 */     for (Location l : getTickingLocations(chunk)) {
/* 581 */       ret.add(l.getBlock());
/*     */     }
/* 583 */     return ret;
/*     */   }
/*     */   
/*     */   public static Set<Location> getTickingLocations(String chunk) {
/* 587 */     return new HashSet<>(ticking_chunks.get(chunk));
/*     */   }
/*     */   
/*     */   public BlockMenu loadInventory(Location l, BlockMenuPreset preset) {
/* 591 */     BlockMenu menu = new BlockMenu(preset, l);
/* 592 */     this.inventories.put(l, menu);
/* 593 */     return menu;
/*     */   }
/*     */   
/*     */   public void loadUniversalInventory(BlockMenuPreset preset) {
/* 597 */     universal_inventories.put(preset.getID(), new UniversalBlockMenu(preset));
/*     */   }
/*     */   
/*     */   public void clearInventory(Location l) {
/* 601 */     BlockMenu menu = getInventory(l);
/*     */     
/* 603 */     for (HumanEntity human : new ArrayList(menu.toInventory().getViewers())) {
/* 604 */       human.closeInventory();
/*     */     }
/*     */     
/* 607 */     ((BlockMenu)this.inventories.get(l)).delete(l);
/* 608 */     this.inventories.remove(l);
/*     */   }
/*     */   
/*     */   public boolean hasInventory(Location l) {
/* 612 */     return this.inventories.containsKey(l);
/*     */   }
/*     */   
/*     */   public boolean hasUniversalInventory(String id) {
/* 616 */     return universal_inventories.containsKey(id);
/*     */   }
/*     */   
/*     */   public UniversalBlockMenu getUniversalInventory(Block block) {
/* 620 */     return getUniversalInventory(block.getLocation());
/*     */   }
/*     */   
/*     */   public UniversalBlockMenu getUniversalInventory(Location l) {
/* 624 */     String id = checkID(l);
/* 625 */     return (id == null) ? null : getUniversalInventory(id);
/*     */   }
/*     */   
/*     */   public UniversalBlockMenu getUniversalInventory(String id) {
/* 629 */     return universal_inventories.get(id);
/*     */   }
/*     */   
/*     */   public static BlockMenu getInventory(Block b) {
/* 633 */     return getInventory(b.getLocation());
/*     */   }
/*     */   
/*     */   public static BlockMenu getInventory(Location l) {
/* 637 */     BlockStorage storage = getStorage(l.getWorld());
/* 638 */     if (storage == null) return null; 
/* 639 */     if (!storage.hasInventory(l)) return storage.loadInventory(l, BlockMenuPreset.getPreset(checkID(l))); 
/* 640 */     return storage.inventories.get(l);
/*     */   }
/*     */   
/*     */   public static JSONParser getParser() {
/* 644 */     return new JSONParser();
/*     */   }
/*     */   
/*     */   public static Config getChunkInfo(Chunk chunk) {
/*     */     try {
/* 649 */       Config cfg = new Config("data-storage/Slimefun/temp.yml");
/* 650 */       if (!map_chunks.containsKey(serializeChunk(chunk))) return cfg;
/*     */       
/* 652 */       for (Map.Entry<String, String> entry : parseJSON(getJSONData(chunk)).entrySet()) {
/* 653 */         cfg.setValue(entry.getKey(), entry.getValue());
/*     */       }
/*     */       
/* 656 */       return cfg;
/* 657 */     } catch (Exception x) {
/* 658 */       System.err.println(x.getClass().getName());
/* 659 */       System.err.println("[远古工艺] Failed to parse ChunkInfo for Chunk @ " + chunk.getX() + ", " + chunk.getZ());
/*     */       try {
/* 661 */         System.err.println(getJSONData(chunk));
/* 662 */       } catch (Exception x2) {
/* 663 */         System.err.println("No Metadata found!");
/*     */       } 
/* 665 */       x.printStackTrace();
/* 666 */       return new Config("data-storage/Slimefun/temp.yml");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean hasChunkInfo(Chunk chunk) {
/* 671 */     return map_chunks.containsKey(serializeChunk(chunk));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setChunkInfo(Chunk chunk, String key, String value) {
/* 676 */     Config cfg = new Config("data-storage/Slimefun/temp.yml");
/* 677 */     if (hasChunkInfo(chunk)) cfg = getChunkInfo(chunk); 
/* 678 */     cfg.setValue(key, value);
/*     */     
/* 680 */     JSONObject json = new JSONObject();
/* 681 */     for (String path : cfg.getKeys()) {
/* 682 */       json.put(path, cfg.getString(path));
/*     */     }
/*     */     
/* 685 */     map_chunks.put(serializeChunk(chunk), json.toJSONString());
/*     */     
/* 687 */     chunk_changes++;
/*     */   }
/*     */   
/*     */   public static String getChunkInfo(Chunk chunk, String key) {
/* 691 */     return getChunkInfo(chunk).getString(key);
/*     */   }
/*     */   
/*     */   public static boolean hasChunkInfo(Chunk chunk, String key) {
/* 695 */     return (getChunkInfo(chunk, key) != null);
/*     */   }
/*     */   
/*     */   public static void clearChunkInfo(Chunk chunk) {
/* 699 */     map_chunks.remove(serializeChunk(chunk));
/*     */   }
/*     */   
/*     */   public static String getBlockInfoAsJson(Block block) {
/* 703 */     return getBlockInfoAsJson(block.getLocation());
/*     */   }
/*     */   
/*     */   public static String getBlockInfoAsJson(Location l) {
/* 707 */     return serializeBlockInfo(getLocationInfo(l));
/*     */   }
/*     */   
/*     */   public boolean hasUniversalInventory(Block block) {
/* 711 */     return hasUniversalInventory(block.getLocation());
/*     */   }
/*     */   
/*     */   public boolean hasUniversalInventory(Location l) {
/* 715 */     String id = checkID(l);
/* 716 */     return (id == null) ? false : hasUniversalInventory(id);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\BlockStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */