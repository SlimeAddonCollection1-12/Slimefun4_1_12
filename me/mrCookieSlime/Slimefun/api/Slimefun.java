/*     */ package me.mrCookieSlime.Slimefun.api;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.Slimefun.GPS.GPSNetwork;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Slimefun
/*     */ {
/*  29 */   public static Map<Integer, List<GuideHandler>> guide_handlers = new HashMap<>();
/*  30 */   public static List<GuideHandler> guide_handlers2 = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private static GPSNetwork gps = new GPSNetwork();
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean emeraldenchants = false;
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static List<Category> current_categories = new ArrayList<>();
/*     */   
/*     */   public static void registerGuideHandler(GuideHandler handler) {
/*  46 */     List<GuideHandler> handlers = new ArrayList<>();
/*  47 */     if (guide_handlers.containsKey(Integer.valueOf(handler.getTier()))) handlers = guide_handlers.get(Integer.valueOf(handler.getTier())); 
/*  48 */     handlers.add(handler);
/*  49 */     guide_handlers.put(Integer.valueOf(handler.getTier()), handlers);
/*  50 */     guide_handlers2.add(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GPSNetwork getGPSNetwork() {
/*  59 */     return gps;
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
/*     */   public static Object getItemValue(String id, String key) {
/*  72 */     return getItemConfig().getValue(id + "." + key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setItemVariable(String id, String key, Object value) {
/*  83 */     getItemConfig().setDefaultValue(id + "." + key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Config getItemConfig() {
/*  94 */     return SlimefunStartup.getItemCfg();
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
/*     */   public static void registerResearch(Research research, ItemStack... items) {
/* 116 */     for (ItemStack item : items) {
/* 117 */       research.addItems(new SlimefunItem[] { SlimefunItem.getByItem(item) });
/*     */     } 
/* 119 */     research.register();
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
/*     */   public static boolean hasUnlocked(Player p, ItemStack item, boolean message) {
/* 133 */     SlimefunItem sfItem = SlimefunItem.getByItem(item);
/* 134 */     SlimefunItem.State state = SlimefunItem.getState(item);
/* 135 */     if (sfItem == null) {
/* 136 */       if (state != SlimefunItem.State.ENABLED) {
/* 137 */         if (message && state != SlimefunItem.State.VANILLA) Messages.local.sendTranslation((CommandSender)p, "messages.disabled-item", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 138 */         return false;
/*     */       } 
/* 140 */       return true;
/*     */     } 
/* 142 */     if (isEnabled(p, item, message) && hasPermission(p, sfItem, message)) {
/* 143 */       if (sfItem.getResearch() == null) return true; 
/* 144 */       if (sfItem.getResearch().hasUnlocked(p)) return true;
/*     */       
/* 146 */       if (message && !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.VanillaItem)) Messages.local.sendTranslation((CommandSender)p, "messages.not-researched", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 147 */       return false;
/*     */     } 
/*     */     
/* 150 */     return false;
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
/*     */   public static boolean hasUnlocked(Player p, SlimefunItem sfItem, boolean message) {
/* 164 */     if (isEnabled(p, sfItem, message) && hasPermission(p, sfItem, message)) {
/* 165 */       if (sfItem.getResearch() == null) return true; 
/* 166 */       if (sfItem.getResearch().hasUnlocked(p)) return true;
/*     */       
/* 168 */       if (message && !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.VanillaItem)) Messages.local.sendTranslation((CommandSender)p, "messages.not-researched", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 169 */       return false;
/*     */     } 
/*     */     
/* 172 */     return false;
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
/*     */   public static boolean hasPermission(Player p, SlimefunItem item, boolean message) {
/* 186 */     if (item == null) return true; 
/* 187 */     if (item.getPermission().equalsIgnoreCase("")) return true; 
/* 188 */     if (p.hasPermission(item.getPermission())) return true;
/*     */     
/* 190 */     if (message) Messages.local.sendTranslation((CommandSender)p, "messages.no-permission", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 191 */     return false;
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
/*     */   public static boolean isEnabled(Player p, ItemStack item, boolean message) {
/* 206 */     String world = p.getWorld().getName();
/* 207 */     SlimefunItem sfItem = SlimefunItem.getByItem(item);
/* 208 */     if (sfItem == null) return !SlimefunItem.isDisabled(item); 
/* 209 */     if (SlimefunStartup.getWhitelist().contains(world + ".enabled")) {
/* 210 */       if (SlimefunStartup.getWhitelist().getBoolean(world + ".enabled")) {
/* 211 */         if (!SlimefunStartup.getWhitelist().contains(world + ".enabled-items." + sfItem.getID())) SlimefunStartup.getWhitelist().setDefaultValue(world + ".enabled-items." + sfItem.getID(), Boolean.valueOf(true)); 
/* 212 */         if (SlimefunStartup.getWhitelist().getBoolean(world + ".enabled-items." + sfItem.getID())) return true;
/*     */         
/* 214 */         if (message) Messages.local.sendTranslation((CommandSender)p, "messages.disabled-in-world", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 215 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 219 */       if (message) Messages.local.sendTranslation((CommandSender)p, "messages.disabled-in-world", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 220 */       return false;
/*     */     } 
/*     */     
/* 223 */     return true;
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
/*     */   public static boolean isEnabled(Player p, SlimefunItem sfItem, boolean message) {
/* 237 */     String world = p.getWorld().getName();
/* 238 */     if (SlimefunStartup.getWhitelist().contains(world + ".enabled")) {
/* 239 */       if (SlimefunStartup.getWhitelist().getBoolean(world + ".enabled")) {
/* 240 */         if (!SlimefunStartup.getWhitelist().contains(world + ".enabled-items." + sfItem.getID())) SlimefunStartup.getWhitelist().setDefaultValue(world + ".enabled-items." + sfItem.getID(), Boolean.valueOf(true)); 
/* 241 */         if (SlimefunStartup.getWhitelist().getBoolean(world + ".enabled-items." + sfItem.getID())) return true;
/*     */         
/* 243 */         if (message) Messages.local.sendTranslation((CommandSender)p, "messages.disabled-in-world", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 244 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 248 */       if (message) Messages.local.sendTranslation((CommandSender)p, "messages.disabled-in-world", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); 
/* 249 */       return false;
/*     */     } 
/*     */     
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> listIDs() {
/* 261 */     List<String> ids = new ArrayList<>();
/* 262 */     for (SlimefunItem item : SlimefunItem.list()) {
/* 263 */       ids.add(item.getID());
/*     */     }
/* 265 */     return ids;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<ItemStack> listCategories() {
/* 275 */     List<ItemStack> items = new ArrayList<>();
/* 276 */     for (Category c : Category.list()) {
/* 277 */       items.add(c.getItem());
/*     */     }
/* 279 */     return items;
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
/*     */   public static void addDescription(String id, String... description) {
/* 292 */     getItemConfig().setDefaultValue(id + ".description", Arrays.asList(description));
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
/*     */   public static void addHint(String id, String... hint) {
/* 304 */     getItemConfig().setDefaultValue(id + ".hint", Arrays.asList(hint));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addYoutubeVideo(String id, String link) {
/* 314 */     getItemConfig().setDefaultValue(id + ".youtube", link);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addWikiPage(String id, String link) {
/* 324 */     getItemConfig().setDefaultValue(id + ".wiki", link);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addOfficialWikiPage(String id, String page) {
/* 334 */     addWikiPage(id, "https://github.com/TheBusyBiscuit/Slimefun4/wiki/" + page);
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
/*     */   public static boolean isEmeraldEnchantsInstalled() {
/* 346 */     return emeraldenchants;
/*     */   }
/*     */   
/*     */   public static List<GuideHandler> getGuideHandlers(int tier) {
/* 350 */     return guide_handlers.containsKey(Integer.valueOf(tier)) ? guide_handlers.get(Integer.valueOf(tier)) : new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\Slimefun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */