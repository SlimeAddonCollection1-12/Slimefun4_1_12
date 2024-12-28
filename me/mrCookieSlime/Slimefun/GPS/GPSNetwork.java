/*     */ package me.mrCookieSlime.Slimefun.GPS;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.MenuHelper;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenSystem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GPSNetwork
/*     */ {
/*  39 */   private Map<UUID, Set<Location>> transmitters = new HashMap<>();
/*  40 */   private int[] border = new int[] { 0, 1, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*  41 */   private int[] inventory = new int[] { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43 };
/*     */   
/*     */   public void updateTransmitter(Block b, UUID uuid, NetworkStatus status) {
/*  44 */     Set<Location> set = new HashSet<>();
/*  45 */     if (this.transmitters.containsKey(uuid)) set = this.transmitters.get(uuid); 
/*  46 */     if (status.equals(NetworkStatus.ONLINE)) {
/*  47 */       if (!set.contains(b.getLocation())) {
/*  48 */         set.add(b.getLocation());
/*  49 */         this.transmitters.put(uuid, set);
/*     */       } 
/*     */     } else {
/*     */       
/*  53 */       set.remove(b.getLocation());
/*  54 */       this.transmitters.put(uuid, set);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getNetworkComplexity(UUID uuid) {
/*  59 */     if (!this.transmitters.containsKey(uuid)) return 0; 
/*  60 */     int level = 0;
/*  61 */     for (Location l : this.transmitters.get(uuid)) {
/*  62 */       level += l.getBlockY();
/*     */     }
/*  64 */     return level;
/*     */   }
/*     */   
/*     */   public int countTransmitters(UUID uuid) {
/*  68 */     if (!this.transmitters.containsKey(uuid)) return 0; 
/*  69 */     return ((Set)this.transmitters.get(uuid)).size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void openTransmitterControlPanel(Player p) throws Exception {
/*  74 */     ChestMenu menu = new ChestMenu("&9控制面板");
/*     */     
/*  76 */     for (int slot : this.border) {
/*  77 */       menu.addItem(slot, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/*  82 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/*  88 */     menu.addItem(2, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&7发射器概览 &e(已选择)"));
/*  89 */     menu.addMenuClickHandler(2, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/*  93 */             return false;
/*     */           }
/*     */         });
/*     */     
/*  97 */     menu.addItem(4, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZmJhNThmYWYxZjY0ODQ3ODg0MTExODIyYjY0YWZhMjFkN2ZjNjJkNDQ4MWYxNGYzZjNiY2I2MzMwIn19fQ=="), "&7网络信息", new String[] { "", "&8⇨ &7状态: " + ((getNetworkComplexity(p.getUniqueId()) > 0) ? "&2&l在线" : "&4&l离线"), "&8⇨ &7复杂度: &r" + getNetworkComplexity(p.getUniqueId()) }));
/*  98 */     menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 102 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 106 */     menu.addItem(6, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljODg4MWU0MjkxNWE5ZDI5YmI2MWExNmZiMjZkMDU5OTEzMjA0ZDI2NWRmNWI0MzliM2Q3OTJhY2Q1NiJ9fX0="), "&7&7路径点概览 &r(选择)"));
/* 107 */     menu.addMenuClickHandler(6, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/*     */             try {
/* 112 */               GPSNetwork.this.openWaypointControlPanel(arg0);
/* 113 */             } catch (Exception e) {
/* 114 */               e.printStackTrace();
/*     */             } 
/* 116 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 120 */     int index = 0;
/* 121 */     for (Location l : getTransmitters(p.getUniqueId())) {
/* 122 */       if (index >= this.inventory.length)
/* 123 */         break;  int slot = this.inventory[index];
/*     */       
/* 125 */       menu.addItem(slot, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&bGPS 信号发射器", new String[] { "&8⇨ &7世界: &r" + l.getWorld().getName(), "&8⇨ &7X: &r" + l.getX(), "&8⇨ &7Y: &r" + l.getY(), "&8⇨ &7Z: &r" + l.getZ(), "", "&8⇨ &7信号强度: &r" + l.getBlockY(), "&8⇨ &7延迟: &r" + DoubleHandler.fixDouble(1000.0D / l.getY()) + "ms" }));
/* 126 */       menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 130 */               return false;
/*     */             }
/*     */           });
/*     */       
/* 134 */       index++;
/*     */     } 
/*     */     
/* 137 */     menu.open(new Player[] { p });
/*     */   }
/*     */   
/*     */   public static ItemStack getPlanet(Map.Entry<String, Location> entry) throws Exception {
/* 141 */     Location l = entry.getValue();
/* 142 */     if (((String)entry.getKey()).startsWith("&4死亡点")) {
/* 143 */       return CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFlMzg1NWY5NTJjZDRhMDNjMTQ4YTk0NmUzZjgxMmE1OTU1YWQzNWNiY2I1MjYyN2VhNGFjZDQ3ZDMwODEifX19");
/*     */     }
/* 145 */     if (l.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
/* 146 */       return CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0=");
/*     */     }
/* 148 */     if (l.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
/* 149 */       return CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZjYWM1OWIyYWFlNDg5YWEwNjg3YjVkODAyYjI1NTVlYjE0YTQwYmQ2MmIyMWViMTE2ZmE1NjljZGI3NTYifX19");
/*     */     }
/*     */     
/* 152 */     return CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljODg4MWU0MjkxNWE5ZDI5YmI2MWExNmZiMjZkMDU5OTEzMjA0ZDI2NWRmNWI0MzliM2Q3OTJhY2Q1NiJ9fX0=");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openWaypointControlPanel(Player p) throws Exception {
/* 158 */     ChestMenu menu = new ChestMenu("&9控制面板");
/*     */     
/* 160 */     for (int slot : this.border) {
/* 161 */       menu.addItem(slot, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 166 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 172 */     menu.addItem(2, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&7信号发射器概览 &r(选择)"));
/* 173 */     menu.addMenuClickHandler(2, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/*     */             try {
/* 178 */               GPSNetwork.this.openTransmitterControlPanel(arg0);
/* 179 */             } catch (Exception e) {
/* 180 */               e.printStackTrace();
/*     */             } 
/* 182 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 186 */     menu.addItem(4, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZmJhNThmYWYxZjY0ODQ3ODg0MTExODIyYjY0YWZhMjFkN2ZjNjJkNDQ4MWYxNGYzZjNiY2I2MzMwIn19fQ=="), "&7网络信息", new String[] { "", "&8⇨ &7状态: " + ((getNetworkComplexity(p.getUniqueId()) > 0) ? "&2&l在线" : "&4&l离线"), "&8⇨ &7复杂度: &r" + getNetworkComplexity(p.getUniqueId()) }));
/* 187 */     menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 191 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 195 */     menu.addItem(6, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljODg4MWU0MjkxNWE5ZDI5YmI2MWExNmZiMjZkMDU5OTEzMjA0ZDI2NWRmNWI0MzliM2Q3OTJhY2Q1NiJ9fX0="), "&7路径点概览 &e(已选择)"));
/* 196 */     menu.addMenuClickHandler(6, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 200 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 204 */     int index = 0;
/* 205 */     for (Map.Entry<String, Location> entry : getWaypoints(p.getUniqueId()).entrySet()) {
/* 206 */       if (index >= this.inventory.length)
/* 207 */         break;  int slot = this.inventory[index];
/*     */       
/* 209 */       Location l = entry.getValue();
/* 210 */       ItemStack globe = getPlanet(entry);
/*     */       
/* 212 */       menu.addItem(slot, (ItemStack)new CustomItem(globe, entry.getKey(), new String[] { "&8⇨ &7世界: &r" + l.getWorld().getName(), "&8⇨ &7X: &r" + l.getX(), "&8⇨ &7Y: &r" + l.getY(), "&8⇨ &7Z: &r" + l.getZ(), "", "&8⇨ &c点击删除" }));
/* 213 */       menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 217 */               String id = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', (String)entry.getKey())).toUpperCase().replace(" ", "_");
/* 218 */               Config cfg = new Config("data-storage/Slimefun/waypoints/" + arg0.getUniqueId().toString() + ".yml");
/* 219 */               cfg.setValue(id, null);
/* 220 */               cfg.save();
/* 221 */               arg0.playSound(arg0.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
/*     */               try {
/* 223 */                 GPSNetwork.this.openWaypointControlPanel(arg0);
/* 224 */               } catch (Exception e) {
/* 225 */                 e.printStackTrace();
/*     */               } 
/* 227 */               return false;
/*     */             }
/*     */           });
/*     */       
/* 231 */       index++;
/*     */     } 
/*     */     
/* 234 */     menu.open(new Player[] { p });
/*     */   }
/*     */   
/*     */   public Map<String, Location> getWaypoints(UUID uuid) {
/* 238 */     Map<String, Location> map = new HashMap<>();
/* 239 */     Config cfg = new Config("data-storage/Slimefun/waypoints/" + uuid.toString() + ".yml");
/* 240 */     for (String key : cfg.getKeys()) {
/* 241 */       if (cfg.contains(key + ".world") && Bukkit.getWorld(cfg.getString(key + ".world")) != null) {
/* 242 */         map.put(cfg.getString(key + ".name"), cfg.getLocation(key));
/*     */       }
/*     */     } 
/* 245 */     return map;
/*     */   }
/*     */   
/*     */   public void addWaypoint(Player p, final Location l) {
/* 249 */     if (getWaypoints(p.getUniqueId()).size() + 2 > this.inventory.length) {
/* 250 */       Messages.local.sendTranslation((CommandSender)p, "gps.waypoint.max", true, new Variable[0]);
/*     */       return;
/*     */     } 
/* 253 */     Messages.local.sendTranslation((CommandSender)p, "gps.waypoint.new", true, new Variable[0]);
/* 254 */     p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 0.5F, 1.0F);
/* 255 */     MenuHelper.awaitChatInput(p, new MenuHelper.ChatHandler()
/*     */         {
/*     */           public boolean onChat(Player p, String message)
/*     */           {
/* 259 */             GPSNetwork.this.addWaypoint(p, message, l);
/* 260 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void addWaypoint(Player p, String name, Location l) {
/* 266 */     if (getWaypoints(p.getUniqueId()).size() + 2 > this.inventory.length) {
/* 267 */       Messages.local.sendTranslation((CommandSender)p, "gps.waypoint.max", true, new Variable[0]);
/*     */       return;
/*     */     } 
/* 270 */     Config cfg = new Config("data-storage/Slimefun/waypoints/" + p.getUniqueId().toString() + ".yml");
/* 271 */     String id = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', name)).toUpperCase().replace(" ", "_");
/* 272 */     cfg.setValue(id, l);
/* 273 */     cfg.setValue(id + ".name", name);
/* 274 */     cfg.save();
/* 275 */     p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
/* 276 */     Messages.local.sendTranslation((CommandSender)p, "gps.waypoint.added", true, new Variable[0]);
/*     */   }
/*     */   
/*     */   public Set<Location> getTransmitters(UUID uuid) {
/* 280 */     return this.transmitters.containsKey(uuid) ? this.transmitters.get(uuid) : new HashSet<>();
/*     */   }
/*     */   
/*     */   public void scanChunk(Player p, Chunk chunk) {
/* 284 */     if (getNetworkComplexity(p.getUniqueId()) < 600) {
/* 285 */       Messages.local.sendTranslation((CommandSender)p, "gps.insufficient-complexity", true, new Variable[] { new Variable("%complexity%", String.valueOf(600)) });
/*     */       return;
/*     */     } 
/* 288 */     ChestMenu menu = new ChestMenu("&4扫描结果");
/*     */     
/* 290 */     int index = 0;
/*     */     
/* 292 */     for (OreGenResource resource : OreGenSystem.listResources()) {
/* 293 */       int supply = OreGenSystem.getSupplies(resource, chunk, true);
/*     */       
/* 295 */       menu.addItem(index, (ItemStack)new CustomItem(resource.getIcon(), "&7资源: &e" + resource.getName(), new String[] { "", "&7已扫描区块:", "&8⇨ &7X: " + chunk.getX() + " Z: " + chunk.getZ(), "", "&7结果: &e" + supply + " " + resource.getMeasurementUnit() }), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 300 */               return false;
/*     */             }
/*     */           });
/* 303 */       index++;
/*     */     } 
/*     */     
/* 306 */     menu.open(new Player[] { p });
/*     */   }
/*     */   
/* 309 */   private static final int[] teleporter_border = new int[] { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/* 310 */   private static final int[] teleporter_inventory = new int[] { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43 };
/*     */ 
/*     */   
/*     */   public static void openTeleporterGUI(Player p, UUID uuid, Block b, final int complexity) throws Exception {
/* 314 */     if (TeleportationSequence.players.contains(p.getUniqueId()))
/*     */       return; 
/* 316 */     p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
/* 317 */     TeleportationSequence.players.add(p.getUniqueId());
/*     */     
/* 319 */     ChestMenu menu = new ChestMenu("&3传送器");
/*     */     
/* 321 */     menu.addMenuCloseHandler(new ChestMenu.MenuCloseHandler()
/*     */         {
/*     */           public void onClose(Player p)
/*     */           {
/* 325 */             TeleportationSequence.players.remove(p.getUniqueId());
/*     */           }
/*     */         });
/*     */     
/* 329 */     for (int slot : teleporter_border) {
/* 330 */       menu.addItem(slot, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 335 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 341 */     menu.addItem(4, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljODg4MWU0MjkxNWE5ZDI5YmI2MWExNmZiMjZkMDU5OTEzMjA0ZDI2NWRmNWI0MzliM2Q3OTJhY2Q1NiJ9fX0="), "&7路径点概览 &e(选择一个目标)"));
/* 342 */     menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 346 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 350 */     final Location source = new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + 2.0D, b.getZ() + 0.5D);
/* 351 */     int index = 0;
/* 352 */     for (Map.Entry<String, Location> entry : Slimefun.getGPSNetwork().getWaypoints(uuid).entrySet()) {
/* 353 */       if (index >= teleporter_inventory.length)
/* 354 */         break;  int slot = teleporter_inventory[index];
/*     */       
/* 356 */       final Location l = entry.getValue();
/* 357 */       ItemStack globe = getPlanet(entry);
/*     */       
/* 359 */       menu.addItem(slot, (ItemStack)new CustomItem(globe, entry.getKey(), new String[] { "&8⇨ &7世界: &r" + l.getWorld().getName(), "&8⇨ &7X: &r" + l.getX(), "&8⇨ &7Y: &r" + l.getY(), "&8⇨ &7Z: &r" + l.getZ(), "&8⇨ &7预计传送时间: &r" + (50 / TeleportationSequence.getSpeed(Slimefun.getGPSNetwork().getNetworkComplexity(uuid), source, l)) + "s", "", "&8⇨ &c点击选择" }));
/* 360 */       menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 364 */               p.closeInventory();
/* 365 */               TeleportationSequence.start(p.getUniqueId(), complexity, source, l, false);
/* 366 */               return false;
/*     */             }
/*     */           });
/*     */       
/* 370 */       index++;
/*     */     } 
/*     */     
/* 373 */     menu.open(new Player[] { p });
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GPS\GPSNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */