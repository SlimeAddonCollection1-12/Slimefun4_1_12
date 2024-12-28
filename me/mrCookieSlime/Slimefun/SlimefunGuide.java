/*      */ package me.mrCookieSlime.Slimefun;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.UUID;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.PlayerRunnable;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.CustomBookOverlay;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.SkullItem;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*      */ import me.mrCookieSlime.Slimefun.GitHub.Contributor;
/*      */ import me.mrCookieSlime.Slimefun.GitHub.IntegerFormat;
/*      */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*      */ import me.mrCookieSlime.Slimefun.Misc.BookDesign;
/*      */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*      */ import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
/*      */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SeasonCategory;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunGadget;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AReactor;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
/*      */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*      */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*      */ import me.mrCookieSlime.Slimefun.URID.URID;
/*      */ import me.mrCookieSlime.Slimefun.api.GuideHandler;
/*      */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.ChatColor;
/*      */ import org.bukkit.GameMode;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.Sound;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.inventory.FurnaceRecipe;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.Recipe;
/*      */ import org.bukkit.inventory.ShapedRecipe;
/*      */ import org.bukkit.inventory.ShapelessRecipe;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.bukkit.material.MaterialData;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SlimefunGuide
/*      */ {
/*   61 */   public static Map<UUID, List<URID>> history = new HashMap<>();
/*   62 */   public static int month = 0;
/*      */   
/*   64 */   public static List<Contributor> contributors = new ArrayList<>();
/*   65 */   public static int issues = 0;
/*   66 */   public static int forks = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   71 */   public static int stars = 0;
/*   72 */   public static int code_bytes = 0;
/*   73 */   public static Date last_update = new Date();
/*      */   
/*      */   static boolean all_recipes = true;
/*      */   private static final int category_size = 36;
/*      */   
/*      */   @Deprecated
/*      */   public static ItemStack getItem() {
/*   80 */     return getItem(BookDesign.CHEST);
/*      */   }
/*      */   
/*      */   public static ItemStack getItem(BookDesign design) {
/*   84 */     switch (design) {
/*      */       case BOOK:
/*   86 */         return (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&e远古工艺之书 &7(书本界面)", new String[] { "", "&e右键点击 &8⇨ &7浏览物品", "&eShift + 右键点击 &8⇨ &7打开界面设置菜单" });
/*      */       
/*      */       case CHEAT_SHEET:
/*   89 */         return (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&c远古工艺之书 &4(作弊面板)", new String[] { "", "&4&l仅供管理员使用", "", "&e右键点击 &8⇨ &7浏览物品", "&eShift + 右键点击 &8⇨ &7打开界面设置菜单" });
/*      */       
/*      */       case CHEST:
/*   92 */         return (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&e远古工艺之书 &7(箱子界面)", new String[] { "", "&e右键点击 &8⇨ &7浏览物品", "&eShift + 右键点击 &8⇨ &7打开界面设置菜单" });
/*      */     } 
/*      */     
/*   95 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static ItemStack getItem(boolean book) {
/*  101 */     return getItem(book ? BookDesign.BOOK : BookDesign.CHEST);
/*      */   }
/*      */   
/*      */   public static ItemStack getDeprecatedItem(boolean book) {
/*  105 */     return (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&e远古工艺之书 &8(右键点击打开)", new String[] { book ? "" : "&2", "&7这是远古工艺的使用向导书", "&7书本虽已泛黄, 知识却历久弥新", "&7你可以在书中解锁物品", "&7查看机器搭建方法以及物品制作、合成方法" });
/*      */   }
/*      */   
/*  108 */   private static final int[] slots = new int[] { 0, 2, 3, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 };
/*      */ 
/*      */   
/*      */   public static void openSettings(Player p, final ItemStack guide) {
/*  112 */     ChestMenu menu = new ChestMenu("设置 / 信息");
/*      */     
/*  114 */     menu.setEmptySlotsClickable(false);
/*  115 */     menu.addMenuOpeningHandler(new ChestMenu.MenuOpeningHandler()
/*      */         {
/*      */           public void onOpen(Player p)
/*      */           {
/*  119 */             p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 0.7F, 0.7F);
/*      */           }
/*      */         });
/*      */     
/*  123 */     for (int i : slots) {
/*  124 */       menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  125 */       menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  129 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */ 
/*      */     
/*  135 */     if (SlimefunManager.isItemSimiliar(guide, getItem(BookDesign.CHEST), true)) {
/*  136 */       if (p.hasPermission("slimefun.cheat.items")) {
/*  137 */         menu.addItem(19, (ItemStack)new CustomItem(new MaterialData(Material.CHEST), "&7界面布局: &e箱子界面", new String[] { "", "&a箱子界面", "&7书本界面", "&7作弊面板", "", "&e 点击 &8⇨ &7修改布局" }));
/*  138 */         menu.addMenuClickHandler(19, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  142 */                 p.getInventory().setItemInMainHand(SlimefunGuide.getItem(BookDesign.BOOK));
/*  143 */                 SlimefunGuide.openSettings(p, p.getInventory().getItemInMainHand());
/*  144 */                 return false;
/*      */               }
/*      */             });
/*      */       } else {
/*      */         
/*  149 */         menu.addItem(19, (ItemStack)new CustomItem(new MaterialData(Material.CHEST), "&7界面布局: &e箱子界面", new String[] { "", "&a箱子界面", "&7书本界面", "", "&e 点击 &8⇨ &7修改布局" }));
/*  150 */         menu.addMenuClickHandler(19, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  154 */                 p.getInventory().setItemInMainHand(SlimefunGuide.getItem(BookDesign.BOOK));
/*  155 */                 SlimefunGuide.openSettings(p, p.getInventory().getItemInMainHand());
/*  156 */                 return false;
/*      */               }
/*      */             });
/*      */       }
/*      */     
/*  161 */     } else if (SlimefunManager.isItemSimiliar(guide, getItem(BookDesign.BOOK), true)) {
/*  162 */       if (p.hasPermission("slimefun.cheat.items")) {
/*  163 */         menu.addItem(19, (ItemStack)new CustomItem(new MaterialData(Material.CHEST), "&7界面布局: &e书本界面", new String[] { "", "&7箱子界面", "&a书本界面", "&7作弊面板", "", "&e 点击 &8⇨ &7修改布局" }));
/*  164 */         menu.addMenuClickHandler(19, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  168 */                 p.getInventory().setItemInMainHand(SlimefunGuide.getItem(BookDesign.CHEAT_SHEET));
/*  169 */                 SlimefunGuide.openSettings(p, p.getInventory().getItemInMainHand());
/*  170 */                 return false;
/*      */               }
/*      */             });
/*      */       } else {
/*      */         
/*  175 */         menu.addItem(19, (ItemStack)new CustomItem(new MaterialData(Material.CHEST), "&7界面布局: &e书本界面", new String[] { "", "&7箱子界面", "&a书本界面", "", "&e 点击 &8⇨ &7修改布局" }));
/*  176 */         menu.addMenuClickHandler(19, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  180 */                 p.getInventory().setItemInMainHand(SlimefunGuide.getItem(BookDesign.CHEST));
/*  181 */                 SlimefunGuide.openSettings(p, p.getInventory().getItemInMainHand());
/*  182 */                 return false;
/*      */               }
/*      */             });
/*      */       }
/*      */     
/*  187 */     } else if (SlimefunManager.isItemSimiliar(guide, getItem(BookDesign.CHEAT_SHEET), true)) {
/*  188 */       menu.addItem(19, (ItemStack)new CustomItem(new MaterialData(Material.CHEST), "&7界面布局: &e作弊面板", new String[] { "", "&7箱子界面", "&7书本界面", "&a作弊面板", "", "&e 点击 &8⇨ &7修改布局" }));
/*  189 */       menu.addMenuClickHandler(19, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  193 */               p.getInventory().setItemInMainHand(SlimefunGuide.getItem(BookDesign.CHEST));
/*  194 */               SlimefunGuide.openSettings(p, p.getInventory().getItemInMainHand());
/*  195 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */     
/*  200 */     menu.addItem(1, (ItemStack)new CustomItem(new MaterialData(Material.BOOK_AND_QUILL), "&a版本信息", new String[] { "", "&7版本: &a" + SlimefunStartup.instance.getDescription().getVersion(), "&7贡献者: &e" + contributors.size(), "", "&7⇨ 点击查看Slimefun项目的贡献者们" }));
/*  201 */     menu.addMenuClickHandler(1, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/*  205 */             SlimefunGuide.openCredits(p, guide);
/*  206 */             return false;
/*      */           }
/*      */         });
/*      */     
/*      */     try {
/*  211 */       menu.addItem(4, (ItemStack)new CustomItem(new MaterialData(Material.REDSTONE_COMPARATOR), "&e源码", new String[] { "", "&7代码行数: &6" + IntegerFormat.formatBigNumber(code_bytes), "&7上次更新时间: &a" + IntegerFormat.timeDelta(last_update) + " 之前", "&7Forks: &e" + forks, "&7Stars: &e" + stars, "", "&7&oSlimefun 4 是一个社区项目,", "&7&o源代码可以在GitHub上查看", "&7&o如果你想保持这个项目的活跃,", "&7&o请考虑为这个项目作贡献", "", "&7⇨ 点击前往GitHub" }));
/*  212 */       menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  216 */               p.closeInventory();
/*  217 */               p.sendMessage("");
/*  218 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&ohttps://github.com/TheBusyBiscuit/Slimefun4"));
/*  219 */               p.sendMessage("");
/*  220 */               return false;
/*      */             }
/*      */           });
/*  223 */     } catch (Exception e) {
/*  224 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  227 */     menu.addItem(7, (ItemStack)new CustomItem(new MaterialData(Material.REDSTONE), "&4Bug追踪器", new String[] { "", "&7未解决问题: &a" + issues, "", "&7⇨ 点击前往 Slimefun Bug追踪器" }));
/*  228 */     menu.addMenuClickHandler(7, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/*  232 */             p.closeInventory();
/*  233 */             p.sendMessage("");
/*  234 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&ohttps://github.com/TheBusyBiscuit/Slimefun4/issues"));
/*  235 */             p.sendMessage("");
/*  236 */             return false;
/*      */           }
/*      */         });
/*      */     
/*  240 */     menu.open(new Player[] { p });
/*      */   }
/*      */ 
/*      */   
/*      */   public static void openCredits(Player p, final ItemStack guide) {
/*  245 */     ChestMenu menu = new ChestMenu("鸣谢");
/*      */     
/*  247 */     menu.setEmptySlotsClickable(false);
/*  248 */     menu.addMenuOpeningHandler(new ChestMenu.MenuOpeningHandler()
/*      */         {
/*      */           public void onOpen(Player p)
/*      */           {
/*  252 */             p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 0.7F, 0.7F);
/*      */           }
/*      */         });
/*      */     
/*  256 */     for (int i = 0; i < 9; i++) {
/*  257 */       if (i != 4) {
/*  258 */         menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  259 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  263 */                 return false;
/*      */               }
/*      */             });
/*      */       } else {
/*      */         
/*  268 */         menu.addItem(4, (ItemStack)new CustomItem(new MaterialData(Material.EMERALD), "&7⇦ 返回设置界面", new String[0]));
/*  269 */         menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  273 */                 SlimefunGuide.openSettings(p, guide);
/*  274 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */     } 
/*      */     
/*  280 */     int index = 9;
/*      */     
/*  282 */     double total = 0.0D;
/*      */     
/*  284 */     for (Contributor contributor : contributors) {
/*  285 */       total += contributor.getCommits();
/*      */     }
/*      */     
/*  288 */     for (Contributor contributor : contributors) {
/*  289 */       SkullItem skullItem = new SkullItem("&a" + contributor.getName(), contributor.getName());
/*      */       
/*  291 */       ItemMeta meta = skullItem.getItemMeta();
/*      */       
/*  293 */       if (contributor.getCommits() > 0) {
/*  294 */         double percentage = DoubleHandler.fixDouble(contributor.getCommits() * 100.0D / total, 2);
/*      */         
/*  296 */         meta.setLore(Arrays.asList(new String[] { "", ChatColor.translateAlternateColorCodes('&', "&7负责: &r" + contributor.getJob()), ChatColor.translateAlternateColorCodes('&', "&7贡献次数: &r" + contributor.getCommits() + " commits &7(&r" + percentage + "%&7)"), "", ChatColor.translateAlternateColorCodes('&', "&7⇨ 点击查看我的Github个人信息页面") }));
/*      */       } else {
/*      */         
/*  299 */         meta.setLore(Arrays.asList(new String[] { "", ChatColor.translateAlternateColorCodes('&', "&7负责: &r" + contributor.getJob()) }));
/*      */       } 
/*      */       
/*  302 */       skullItem.setItemMeta(meta);
/*      */       
/*  304 */       menu.addItem(index, (ItemStack)skullItem);
/*  305 */       menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  309 */               if (contributor.getCommits() > 0) {
/*  310 */                 p.closeInventory();
/*  311 */                 p.sendMessage("");
/*  312 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o" + contributor.getProfile()));
/*  313 */                 p.sendMessage("");
/*      */               } 
/*  315 */               return false;
/*      */             }
/*      */           });
/*      */       
/*  319 */       index++;
/*      */     } 
/*      */     
/*  322 */     for (int j = 0; j < 9; j++) {
/*  323 */       menu.addItem(36 + j, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  324 */       menu.addMenuClickHandler(36 + j, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  328 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */     
/*  333 */     menu.open(new Player[] { p });
/*      */   }
/*      */   
/*      */   public static void openCheatMenu(Player p) {
/*  337 */     openMainMenu(p, false, false, 1);
/*      */   }
/*      */   
/*      */   public static void openGuide(Player p, boolean book) {
/*  341 */     if (!SlimefunStartup.getWhitelist().getBoolean(p.getWorld().getName() + ".enabled"))
/*  342 */       return;  if (!SlimefunStartup.getWhitelist().getBoolean(p.getWorld().getName() + ".enabled-items.SLIMEFUN_GUIDE"))
/*  343 */       return;  if (!history.containsKey(p.getUniqueId())) { openMainMenu(p, true, book, 1); }
/*      */     else
/*  345 */     { URID last = getLastEntry(p, false);
/*  346 */       if (URID.decode(last) instanceof Category) { openCategory(p, (Category)URID.decode(last), true, 1, book); }
/*  347 */       else if (URID.decode(last) instanceof SlimefunItem) { displayItem(p, ((SlimefunItem)URID.decode(last)).getItem(), false, book, 0); }
/*  348 */       else if (URID.decode(last) instanceof GuideHandler) { ((GuideHandler)URID.decode(last)).run(p, true, book); }
/*  349 */       else { displayItem(p, (ItemStack)URID.decode(last), false, book, 0); }
/*      */        }
/*      */   
/*      */   }
/*      */   
/*      */   public static void openMainMenu(final Player p, final boolean survival, final boolean book, final int selected_page) {
/*  355 */     clearHistory(p.getUniqueId());
/*      */     
/*  357 */     if (book) {
/*  358 */       List<TellRawMessage> pages = new ArrayList<>();
/*  359 */       List<String> texts = new ArrayList<>();
/*  360 */       List<String> tooltips = new ArrayList<>();
/*  361 */       List<PlayerRunnable> actions = new ArrayList<>();
/*      */       
/*  363 */       int tier = 0;
/*      */       
/*  365 */       for (Category category : Category.list()) {
/*      */         
/*  367 */         boolean locked = true;
/*      */         
/*  369 */         for (SlimefunItem item : category.getItems()) {
/*  370 */           if (Slimefun.isEnabled(p, item, false)) {
/*  371 */             locked = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  376 */         if (locked) {
/*      */           continue;
/*      */         }
/*      */         
/*  380 */         if (tier < category.getTier()) {
/*  381 */           if (survival) {
/*  382 */             for (GuideHandler handler : Slimefun.getGuideHandlers(tier)) {
/*  383 */               handler.addEntry(texts, tooltips);
/*  384 */               actions.add(new PlayerRunnable(2)
/*      */                   {
/*      */                     public void run(Player p)
/*      */                     {
/*  388 */                       handler.run(p, survival, book);
/*      */                     }
/*      */                   });
/*      */             } 
/*      */           }
/*  393 */           tier = category.getTier();
/*  394 */           if (tier > 1) {
/*  395 */             for (int j = 0; j < 10 && 
/*  396 */               texts.size() % 10 != 0; j++) {
/*  397 */               texts.add(" ");
/*  398 */               tooltips.add(null);
/*  399 */               actions.add(null);
/*      */             } 
/*      */           }
/*  402 */           texts.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &6品级 " + tier));
/*  403 */           tooltips.add(null);
/*  404 */           actions.add(null);
/*      */         } 
/*  406 */         if (category instanceof LockedCategory && !((LockedCategory)category).hasUnlocked(p)) {
/*  407 */           StringBuilder parents = new StringBuilder("&4&l未解锁\n\n&7如果想要解锁这个系列,\n&7你需要先解锁所有来自\n&7以下系列的物品:\n");
/*      */           
/*  409 */           for (Category parent : ((LockedCategory)category).getParents()) {
/*  410 */             parents.append(ChatColor.translateAlternateColorCodes('&', "\n&c" + StringUtils.formatItemName(parent.getItem(), false)));
/*      */           }
/*      */           
/*  413 */           texts.add(ChatColor.translateAlternateColorCodes('&', shorten("&c", StringUtils.formatItemName(category.getItem(), false))));
/*  414 */           tooltips.add(parents.toString());
/*  415 */           actions.add(null); continue;
/*      */         } 
/*  417 */         if (category instanceof SeasonCategory) {
/*  418 */           if (((SeasonCategory)category).isUnlocked()) {
/*  419 */             texts.add(ChatColor.translateAlternateColorCodes('&', shorten("&a", StringUtils.formatItemName(category.getItem(), false))));
/*  420 */             tooltips.add("&e点击打开以下系列:\n" + StringUtils.formatItemName(category.getItem(), false));
/*  421 */             actions.add(new PlayerRunnable(1)
/*      */                 {
/*      */                   public void run(final Player p)
/*      */                   {
/*  425 */                     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*      */                         {
/*      */                           public void run()
/*      */                           {
/*  429 */                             SlimefunGuide.openCategory(p, category, survival, 1, book);
/*      */                           }
/*      */                         }1L);
/*      */                   }
/*      */                 });
/*      */           } 
/*      */           continue;
/*      */         } 
/*  437 */         texts.add(ChatColor.translateAlternateColorCodes('&', shorten("&a", StringUtils.formatItemName(category.getItem(), false))));
/*  438 */         tooltips.add("&e点击打开以下系列:\n" + StringUtils.formatItemName(category.getItem(), false));
/*  439 */         actions.add(new PlayerRunnable(1)
/*      */             {
/*      */               public void run(final Player p)
/*      */               {
/*  443 */                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*      */                     {
/*      */                       public void run()
/*      */                       {
/*  447 */                         SlimefunGuide.openCategory(p, category, survival, 1, book);
/*      */                       }
/*      */                     }1L);
/*      */               }
/*      */             });
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  456 */       if (survival) {
/*  457 */         for (GuideHandler handler : Slimefun.getGuideHandlers(tier)) {
/*  458 */           handler.addEntry(texts, tooltips);
/*  459 */           actions.add(new PlayerRunnable(2)
/*      */               {
/*      */                 public void run(Player p)
/*      */                 {
/*  463 */                   handler.run(p, survival, book);
/*      */                 }
/*      */               });
/*      */         } 
/*      */       }
/*      */       int i;
/*  469 */       for (i = 0; i < texts.size(); i += 10) {
/*  470 */         TellRawMessage page = new TellRawMessage();
/*  471 */         page.addText(ChatColor.translateAlternateColorCodes('&', "&b&l- 远古工艺向导 -\n\n"));
/*  472 */         for (int j = i; j < texts.size() && j < i + 10; j++) {
/*  473 */           page.addText((String)texts.get(j) + "\n");
/*  474 */           if (tooltips.get(j) != null) page.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, tooltips.get(j)); 
/*  475 */           if (actions.get(j) != null) page.addClickEvent(actions.get(j));
/*      */         
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  490 */         pages.add(page);
/*      */       } 
/*      */       
/*  493 */       (new CustomBookOverlay("远古工艺向导", "mrCookieSlime", pages.<TellRawMessage>toArray(new TellRawMessage[pages.size()]))).open(p);
/*      */     } else {
/*      */       
/*  496 */       ChestMenu menu = new ChestMenu("远古工艺向导");
/*      */       
/*  498 */       menu.setEmptySlotsClickable(false);
/*  499 */       menu.addMenuOpeningHandler(new ChestMenu.MenuOpeningHandler()
/*      */           {
/*      */             public void onOpen(Player p)
/*      */             {
/*  503 */               p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 0.7F);
/*      */             }
/*      */           });
/*      */       
/*  507 */       List<Category> categories = Slimefun.current_categories;
/*  508 */       List<GuideHandler> handlers = Slimefun.guide_handlers2;
/*      */       
/*  510 */       int index = 9;
/*  511 */       int pages = 1;
/*      */       int i;
/*  513 */       for (i = 0; i < 9; i++) {
/*  514 */         menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  515 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  519 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/*  524 */       for (i = 45; i < 54; i++) {
/*  525 */         menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  526 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  530 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/*  535 */       int target = 36 * (selected_page - 1) - 1;
/*      */       
/*  537 */       while (target < categories.size() + handlers.size() - 1) {
/*  538 */         if (index >= 45) {
/*  539 */           pages++;
/*      */           
/*      */           break;
/*      */         } 
/*  543 */         target++;
/*      */         
/*  545 */         if (target >= categories.size()) {
/*  546 */           if (!survival)
/*  547 */             break;  index = ((GuideHandler)handlers.get(target - categories.size())).next(p, index, menu);
/*      */           continue;
/*      */         } 
/*  550 */         final Category category = categories.get(target);
/*      */         
/*  552 */         boolean locked = true;
/*      */         
/*  554 */         for (SlimefunItem item : category.getItems()) {
/*  555 */           if (Slimefun.isEnabled(p, item, false)) {
/*  556 */             locked = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  561 */         if (locked) {
/*      */           continue;
/*      */         }
/*  564 */         if (!(category instanceof LockedCategory)) {
/*  565 */           if (!(category instanceof SeasonCategory)) {
/*  566 */             menu.addItem(index, category.getItem());
/*  567 */             menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */                 {
/*      */                   public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                   {
/*  571 */                     SlimefunGuide.openCategory(p, category, survival, 1, book);
/*  572 */                     return false;
/*      */                   }
/*      */                 });
/*  575 */             index++;
/*      */             continue;
/*      */           } 
/*  578 */           if (((SeasonCategory)category).isUnlocked()) {
/*  579 */             menu.addItem(index, category.getItem());
/*  580 */             menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */                 {
/*      */                   public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                   {
/*  584 */                     SlimefunGuide.openCategory(p, category, survival, 1, book);
/*  585 */                     return false;
/*      */                   }
/*      */                 });
/*  588 */             index++;
/*      */           } 
/*      */           continue;
/*      */         } 
/*  592 */         if (((LockedCategory)category).hasUnlocked(p)) {
/*  593 */           menu.addItem(index, category.getItem());
/*  594 */           menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                 {
/*  598 */                   SlimefunGuide.openCategory(p, category, survival, 1, book);
/*  599 */                   return false;
/*      */                 }
/*      */               });
/*  602 */           index++;
/*      */           continue;
/*      */         } 
/*  605 */         List<String> parents = new ArrayList<>();
/*  606 */         parents.add("");
/*  607 */         parents.add(ChatColor.translateAlternateColorCodes('&', "&r你需要先解锁所有"));
/*  608 */         parents.add(ChatColor.translateAlternateColorCodes('&', "&r来自以下系列的物品:"));
/*  609 */         parents.add("");
/*  610 */         for (Category parent : ((LockedCategory)category).getParents()) {
/*  611 */           parents.add(parent.getItem().getItemMeta().getDisplayName());
/*      */         }
/*  613 */         menu.addItem(index, (ItemStack)new CustomItem(Material.BARRIER, "&4未解锁 &7- &r" + category.getItem().getItemMeta().getDisplayName(), 0, parents.<String>toArray(new String[parents.size()])));
/*  614 */         menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  618 */                 return false;
/*      */               }
/*      */             });
/*  621 */         index++;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  626 */       final int finalPages = pages;
/*      */       
/*  628 */       menu.addItem(46, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), "&r⇦ 上一页", new String[] { "", "&7(" + selected_page + " / " + pages + ")" }));
/*  629 */       menu.addMenuClickHandler(46, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  633 */               int next = selected_page - 1;
/*  634 */               if (next < 1) next = finalPages; 
/*  635 */               if (next != selected_page) SlimefunGuide.openMainMenu(p, survival, book, next); 
/*  636 */               return false;
/*      */             }
/*      */           });
/*      */       
/*  640 */       menu.addItem(52, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), "&r下一页 ⇨", new String[] { "", "&7(" + selected_page + " / " + pages + ")" }));
/*  641 */       menu.addMenuClickHandler(52, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  645 */               int next = selected_page + 1;
/*  646 */               if (next > finalPages) next = 1; 
/*  647 */               if (next != selected_page) SlimefunGuide.openMainMenu(p, survival, book, next); 
/*  648 */               return false;
/*      */             }
/*      */           });
/*      */       
/*  652 */       menu.open(new Player[] { p });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String shorten(String string, String string2) {
/*  657 */     if (ChatColor.stripColor(string + string2).length() > 19) return (string + ChatColor.stripColor(string2)).substring(0, 18) + "..."; 
/*  658 */     return string + ChatColor.stripColor(string2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void openCategory(final Player p, final Category category, final boolean survival, final int selected_page, final boolean book) {
/*  663 */     if (category == null)
/*      */       return; 
/*  665 */     if (book && category.getItems().size() < 250) {
/*  666 */       final List<TellRawMessage> pages = new ArrayList<>();
/*  667 */       List<String> texts = new ArrayList<>();
/*  668 */       List<String> tooltips = new ArrayList<>();
/*  669 */       List<PlayerRunnable> actions = new ArrayList<>();
/*      */       
/*  671 */       for (SlimefunItem item : category.getItems()) {
/*  672 */         if (Slimefun.hasPermission(p, item, false)) {
/*  673 */           if (Slimefun.isEnabled(p, item, false)) {
/*  674 */             if (survival && !Slimefun.hasUnlocked(p, item, false) && item.getResearch() != null) {
/*  675 */               final Research research = item.getResearch();
/*      */               
/*  677 */               texts.add(ChatColor.translateAlternateColorCodes('&', shorten("&7", StringUtils.formatItemName(item.getItem(), false))));
/*  678 */               tooltips.add(ChatColor.translateAlternateColorCodes('&', StringUtils.formatItemName(item.getItem(), false) + "\n&c&l未解锁\n\n&7需要花费: " + ((p.getLevel() >= research.getCost()) ? "&b" : "&4") + research.getCost() + " 等级\n\n&a> 点击进行解锁"));
/*  679 */               actions.add(new PlayerRunnable(2)
/*      */                   {
/*      */                     public void run(final Player p)
/*      */                     {
/*  683 */                       if (!Research.isResearching(p))
/*  684 */                         if (research.canUnlock(p)) {
/*  685 */                           if (research.hasUnlocked(p)) {
/*  686 */                             SlimefunGuide.openCategory(p, category, true, selected_page, book);
/*      */                           } else {
/*  688 */                             if (p.getGameMode() != GameMode.CREATIVE || !Research.creative_research) {
/*  689 */                               p.setLevel(p.getLevel() - research.getCost());
/*      */                             }
/*      */                             
/*  692 */                             if (p.getGameMode() == GameMode.CREATIVE) {
/*  693 */                               research.unlock(p, true);
/*  694 */                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*      */                                   {
/*      */                                     public void run()
/*      */                                     {
/*  698 */                                       SlimefunGuide.openCategory(p, category, survival, selected_page, book);
/*      */                                     }
/*      */                                   }1L);
/*      */                             } else {
/*  702 */                               research.unlock(p, false);
/*  703 */                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*      */                                   {
/*      */                                     public void run()
/*      */                                     {
/*  707 */                                       SlimefunGuide.openCategory(p, category, survival, selected_page, book); }
/*      */                                   }103L);
/*      */                             } 
/*      */                           } 
/*      */                         } else {
/*  712 */                           Messages.local.sendTranslation((CommandSender)p, "messages.not-enough-xp", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                         }  
/*      */                     }
/*      */                   });
/*      */               continue;
/*      */             } 
/*  718 */             texts.add(ChatColor.translateAlternateColorCodes('&', shorten("&a", StringUtils.formatItemName(item.getItem(), false))));
/*      */             
/*  720 */             StringBuilder tooltip = new StringBuilder();
/*      */             
/*  722 */             tooltip.append(StringUtils.formatItemName(item.getItem(), false));
/*      */             
/*  724 */             if (item.getItem().hasItemMeta() && item.getItem().getItemMeta().hasLore()) {
/*  725 */               for (String line : item.getItem().getItemMeta().getLore()) {
/*  726 */                 tooltip.append("\n" + line);
/*      */               }
/*      */             }
/*      */             
/*  730 */             tooltip.append(ChatColor.translateAlternateColorCodes('&', "\n\n&e&o点击查看详细信息"));
/*      */             
/*  732 */             tooltips.add(tooltip.toString());
/*  733 */             actions.add(new PlayerRunnable(2)
/*      */                 {
/*      */                   public void run(Player p)
/*      */                   {
/*  737 */                     SlimefunGuide.displayItem(p, item.getItem(), true, book, 0);
/*      */                   }
/*      */                 });
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/*  744 */         texts.add(ChatColor.translateAlternateColorCodes('&', shorten("&4", StringUtils.formatItemName(item.getItem(), false))));
/*  745 */         tooltips.add(ChatColor.translateAlternateColorCodes('&', "&c无权限!"));
/*  746 */         actions.add(null);
/*      */       } 
/*      */       
/*      */       int i;
/*  750 */       for (i = 0; i < texts.size(); i += 10) {
/*  751 */         TellRawMessage page = new TellRawMessage();
/*  752 */         page.addText(ChatColor.translateAlternateColorCodes('&', "&b&l- 远古工艺向导 -\n\n"));
/*  753 */         for (int j = i; j < texts.size() && j < i + 10; j++) {
/*  754 */           page.addText((String)texts.get(j) + "\n");
/*  755 */           if (tooltips.get(j) != null) page.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, tooltips.get(j)); 
/*  756 */           if (actions.get(j) != null) page.addClickEvent(actions.get(j)); 
/*      */         } 
/*  758 */         page.addText("\n");
/*  759 */         page.addText(ChatColor.translateAlternateColorCodes('&', "&6⇦ &l返回"));
/*  760 */         page.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, ChatColor.translateAlternateColorCodes('&', "&e点击返回系列项目概览界面"));
/*  761 */         page.addClickEvent(new PlayerRunnable(2)
/*      */             {
/*      */               public void run(final Player p)
/*      */               {
/*  765 */                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*      */                     {
/*      */                       public void run()
/*      */                       {
/*  769 */                         SlimefunGuide.openMainMenu(p, survival, true, 1);
/*      */                       }
/*      */                     }1L);
/*      */               }
/*      */             });
/*  774 */         pages.add(page);
/*      */       } 
/*      */       
/*  777 */       (new CustomBookOverlay("远古工艺向导", "mrCookieSlime", pages.<TellRawMessage>toArray(new TellRawMessage[pages.size()]))).open(p);
/*      */     } else {
/*      */       
/*  780 */       ChestMenu menu = new ChestMenu("远古工艺向导");
/*      */       
/*  782 */       menu.setEmptySlotsClickable(false);
/*  783 */       menu.addMenuOpeningHandler(new ChestMenu.MenuOpeningHandler()
/*      */           {
/*      */             public void onOpen(Player p)
/*      */             {
/*  787 */               p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 0.7F);
/*      */             }
/*      */           });
/*      */       
/*  791 */       int index = 9;
/*  792 */       final int pages = category.getItems().size() / 36 + 1; int i;
/*  793 */       for (i = 0; i < 4; i++) {
/*  794 */         menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  795 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  799 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/*  804 */       menu.addItem(4, (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7⇦ 返回", new String[0]));
/*  805 */       menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  809 */               SlimefunGuide.openMainMenu(p, survival, book, 1);
/*  810 */               return false;
/*      */             }
/*      */           });
/*      */       
/*  814 */       for (i = 5; i < 9; i++) {
/*  815 */         menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  816 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  820 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/*  825 */       for (i = 45; i < 54; i++) {
/*  826 */         menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]));
/*  827 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/*  831 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/*  836 */       menu.addItem(46, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), "&r⇦ 上一页", new String[] { "", "&7(" + selected_page + " / " + pages + ")" }));
/*  837 */       menu.addMenuClickHandler(46, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  841 */               int next = selected_page - 1;
/*  842 */               if (next < 1) next = pages; 
/*  843 */               if (next != selected_page) SlimefunGuide.openCategory(p, category, survival, next, book); 
/*  844 */               return false;
/*      */             }
/*      */           });
/*      */       
/*  848 */       menu.addItem(52, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), "&r下一页 ⇨", new String[] { "", "&7(" + selected_page + " / " + pages + ")" }));
/*  849 */       menu.addMenuClickHandler(52, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/*  853 */               int next = selected_page + 1;
/*  854 */               if (next > pages) next = 1; 
/*  855 */               if (next != selected_page) SlimefunGuide.openCategory(p, category, survival, next, book); 
/*  856 */               return false;
/*      */             }
/*      */           });
/*      */       
/*  860 */       int category_index = 36 * (selected_page - 1);
/*  861 */       for (int j = 0; j < 36; j++) {
/*  862 */         int target = category_index + j;
/*  863 */         if (target >= category.getItems().size())
/*  864 */           break;  SlimefunItem sfitem = category.getItems().get(target);
/*  865 */         if (Slimefun.isEnabled(p, sfitem, false)) {
/*  866 */           if (survival && !Slimefun.hasUnlocked(p, sfitem.getItem(), false) && sfitem.getResearch() != null) {
/*  867 */             if (Slimefun.hasPermission(p, sfitem, false)) {
/*  868 */               final Research research = sfitem.getResearch();
/*  869 */               menu.addItem(index, (ItemStack)new CustomItem(Material.BARRIER, "&r" + StringUtils.formatItemName(sfitem.getItem(), false), 0, new String[] { "&4&l未解锁", "", "&a> 点击解锁", "", "&7消耗: &b" + research.getCost() + " 级" }));
/*  870 */               menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */                   {
/*      */                     public boolean onClick(final Player p, int slot, ItemStack item, ClickAction action)
/*      */                     {
/*  874 */                       if (!Research.isResearching(p))
/*  875 */                         if (research.canUnlock(p)) {
/*  876 */                           if (research.hasUnlocked(p)) {
/*  877 */                             SlimefunGuide.openCategory(p, category, true, selected_page, book);
/*      */                           } else {
/*  879 */                             if (p.getGameMode() != GameMode.CREATIVE || !Research.creative_research) {
/*  880 */                               p.setLevel(p.getLevel() - research.getCost());
/*      */                             }
/*      */                             
/*  883 */                             if (p.getGameMode() == GameMode.CREATIVE) {
/*  884 */                               research.unlock(p, Research.creative_research);
/*  885 */                               SlimefunGuide.openCategory(p, category, survival, selected_page, book);
/*      */                             } else {
/*  887 */                               research.unlock(p, false);
/*  888 */                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*      */                                   {
/*      */                                     public void run()
/*      */                                     {
/*  892 */                                       SlimefunGuide.openCategory(p, category, survival, selected_page, book); }
/*      */                                   }103L);
/*      */                             } 
/*      */                           } 
/*      */                         } else {
/*  897 */                           Messages.local.sendTranslation((CommandSender)p, "messages.not-enough-xp", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                         }  
/*  899 */                       return false;
/*      */                     }
/*      */                   });
/*  902 */               index++;
/*      */             } else {
/*      */               
/*  905 */               menu.addItem(index, (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(sfitem.getItem(), false), 0, new String[] { "", "&r你没有权限", "&r查看这个物品" }));
/*  906 */               menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */                   {
/*      */                     public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */                     {
/*  910 */                       return false;
/*      */                     }
/*      */                   });
/*  913 */               index++;
/*      */             } 
/*      */           } else {
/*      */             
/*  917 */             menu.addItem(index, sfitem.getItem());
/*  918 */             menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */                 {
/*      */                   public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                   {
/*  922 */                     if (survival) { SlimefunGuide.displayItem(p, item, true, book, 0); }
/*  923 */                     else { p.getInventory().addItem(new ItemStack[] { item }); }
/*  924 */                      return false;
/*      */                   }
/*      */                 });
/*  927 */             index++;
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  932 */       menu.open(new Player[] { p });
/*      */     } 
/*      */     
/*  935 */     if (survival) {
/*  936 */       addToHistory(p, category.getURID());
/*      */     }
/*      */   }
/*      */   
/*      */   public static void addToHistory(Player p, URID urid) {
/*  941 */     List<URID> list = new ArrayList<>();
/*  942 */     if (history.containsKey(p.getUniqueId())) list = history.get(p.getUniqueId()); 
/*  943 */     list.add(urid);
/*  944 */     history.put(p.getUniqueId(), list);
/*      */   }
/*      */   
/*      */   private static URID getLastEntry(Player p, boolean remove) {
/*  948 */     List<URID> list = new ArrayList<>();
/*  949 */     if (history.containsKey(p.getUniqueId())) list = history.get(p.getUniqueId()); 
/*  950 */     if (remove && list.size() >= 1) {
/*  951 */       URID urid = list.get(list.size() - 1);
/*  952 */       urid.markDirty();
/*  953 */       list.remove(urid);
/*      */     } 
/*  955 */     if (list.isEmpty()) { history.remove(p.getUniqueId()); }
/*  956 */     else { history.put(p.getUniqueId(), list); }
/*  957 */      return list.isEmpty() ? null : list.get(list.size() - 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void displayItem(Player p, final ItemStack item, boolean addToHistory, final boolean book, final int page) {
/*  962 */     if (item == null || item.getType() == Material.AIR)
/*      */       return; 
/*  964 */     final SlimefunItem sfItem = SlimefunItem.getByItem(item);
/*  965 */     if (sfItem == null && 
/*  966 */       !all_recipes) {
/*      */       return;
/*      */     }
/*  969 */     ItemStack[] recipe = new ItemStack[9];
/*  970 */     ItemStack recipeType = null;
/*  971 */     ItemStack recipeOutput = item;
/*      */     
/*  973 */     ChestMenu menu = new ChestMenu("远古工艺向导");
/*      */     
/*  975 */     menu.setEmptySlotsClickable(false);
/*  976 */     menu.addMenuOpeningHandler(new ChestMenu.MenuOpeningHandler()
/*      */         {
/*      */           public void onOpen(Player p)
/*      */           {
/*  980 */             p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 0.7F);
/*      */           }
/*      */         });
/*      */     
/*  984 */     if (sfItem != null) {
/*  985 */       recipe = sfItem.getRecipe();
/*  986 */       recipeType = sfItem.getRecipeType().toItem();
/*  987 */       recipeOutput = (sfItem.getRecipeOutput() != null) ? sfItem.getRecipeOutput() : sfItem.getItem();
/*      */     } else {
/*      */       
/*  990 */       List<Recipe> recipes = new ArrayList<>();
/*  991 */       Iterator<Recipe> iterator = Bukkit.recipeIterator();
/*  992 */       while (iterator.hasNext()) {
/*  993 */         Recipe recipe1 = iterator.next();
/*  994 */         if (SlimefunManager.isItemSimiliar((ItemStack)new CustomItem(recipe1.getResult(), 1), item, true) && recipe1.getResult().getData().getData() == item.getData().getData()) recipes.add(recipe1);
/*      */       
/*      */       } 
/*  997 */       if (recipes.isEmpty())
/*  998 */         return;  Recipe r = recipes.get(page);
/*      */       
/* 1000 */       if (recipes.size() > page + 1) {
/* 1001 */         menu.addItem(1, (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7下一页 ⇨", new String[] { "", "&e&l! &r这个物品有多重合成方式" }));
/* 1002 */         menu.addMenuClickHandler(1, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */               {
/* 1006 */                 SlimefunGuide.displayItem(p, item, false, book, page + 1);
/* 1007 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/* 1012 */       if (r instanceof ShapedRecipe) {
/* 1013 */         String[] shape = ((ShapedRecipe)r).getShape();
/* 1014 */         for (int i = 0; i < shape.length; i++) {
/* 1015 */           for (int j = 0; j < shape[i].length(); j++) {
/* 1016 */             ItemStack ingredient = (ItemStack)((ShapedRecipe)r).getIngredientMap().get(Character.valueOf(shape[i].charAt(j)));
/* 1017 */             if (ingredient != null) {
/* 1018 */               MaterialData data = ingredient.getData();
/* 1019 */               if (ingredient.getData().getData() < 0) data.setData((byte)0); 
/* 1020 */               ingredient = data.toItemStack(ingredient.getAmount());
/*      */             } 
/* 1022 */             recipe[i * 3 + j] = ingredient;
/*      */           } 
/*      */         } 
/* 1025 */         recipeType = RecipeType.SHAPED_RECIPE.toItem();
/* 1026 */         recipeOutput = r.getResult();
/*      */       }
/* 1028 */       else if (r instanceof ShapelessRecipe) {
/* 1029 */         List<ItemStack> ingredients = ((ShapelessRecipe)r).getIngredientList();
/* 1030 */         for (int i = 0; i < ingredients.size(); i++) {
/* 1031 */           ItemStack ingredient = ingredients.get(i);
/* 1032 */           if (ingredient != null) {
/* 1033 */             MaterialData data = ingredient.getData();
/* 1034 */             if (ingredient.getData().getData() < 0) data.setData((byte)0); 
/* 1035 */             ingredient = data.toItemStack(ingredient.getAmount());
/*      */           } 
/* 1037 */           recipe[i] = ingredient;
/*      */         } 
/* 1039 */         recipeType = RecipeType.SHAPELESS_RECIPE.toItem();
/* 1040 */         recipeOutput = r.getResult();
/*      */       }
/* 1042 */       else if (r instanceof FurnaceRecipe) {
/* 1043 */         ItemStack ingredient = ((FurnaceRecipe)r).getInput();
/* 1044 */         if (ingredient != null) {
/* 1045 */           MaterialData data = ingredient.getData();
/* 1046 */           if (ingredient.getData().getData() < 0) data.setData((byte)0); 
/* 1047 */           ingredient = data.toItemStack(ingredient.getAmount());
/*      */         } 
/* 1049 */         recipe[4] = ingredient;
/*      */         
/* 1051 */         recipeType = RecipeType.FURNACE.toItem();
/* 1052 */         recipeOutput = r.getResult();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1058 */     if (addToHistory) addToHistory(p, (sfItem != null) ? sfItem.getURID() : URID.nextURID(item, true));
/*      */     
/* 1060 */     if (history.containsKey(p.getUniqueId()) && ((List)history.get(p.getUniqueId())).size() > 1) {
/* 1061 */       menu.addItem(0, (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7⇦ 返回", new String[] { "", "&r左键点击: &7返回上一页", "&rShift + 左键点击: &7返回主菜单" }));
/* 1062 */       menu.addMenuClickHandler(0, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */             {
/* 1066 */               if (action.isShiftClicked()) { SlimefunGuide.openMainMenu(p, true, book, 1); }
/*      */               else
/* 1068 */               { URID last = SlimefunGuide.getLastEntry(p, true);
/* 1069 */                 if (URID.decode(last) instanceof Category) { SlimefunGuide.openCategory(p, (Category)URID.decode(last), true, 1, book); }
/* 1070 */                 else if (URID.decode(last) instanceof SlimefunItem) { SlimefunGuide.displayItem(p, ((SlimefunItem)URID.decode(last)).getItem(), false, book, 0); }
/* 1071 */                 else if (URID.decode(last) instanceof GuideHandler) { ((GuideHandler)URID.decode(last)).run(p, true, book); }
/* 1072 */                 else { SlimefunGuide.displayItem(p, (ItemStack)URID.decode(last), false, book, 0); }
/*      */                  }
/* 1074 */                return false;
/*      */             }
/*      */           });
/*      */     } else {
/*      */       
/* 1079 */       menu.addItem(0, (ItemStack)new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7⇦ 返回", new String[] { "", "&r左键点击: &7返回主菜单" }));
/* 1080 */       menu.addMenuClickHandler(0, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */             {
/* 1084 */               SlimefunGuide.openMainMenu(p, true, book, 1);
/* 1085 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */     
/* 1090 */     menu.addItem(3, Slimefun.hasUnlocked(p, recipe[0], false) ? recipe[0] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[0], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[0]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1091 */     menu.addMenuClickHandler(3, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1095 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1096 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1100 */     menu.addItem(4, Slimefun.hasUnlocked(p, recipe[1], false) ? recipe[1] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[1], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[1]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1101 */     menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1105 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1106 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1110 */     menu.addItem(5, Slimefun.hasUnlocked(p, recipe[2], false) ? recipe[2] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[2], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[2]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1111 */     menu.addMenuClickHandler(5, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1115 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1116 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1120 */     if (sfItem != null) {
/* 1121 */       if (Slimefun.getItemConfig().contains(sfItem.getID() + ".wiki")) {
/*      */         try {
/* 1123 */           menu.addItem(8, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY2OTJmOTljYzZkNzgyNDIzMDQxMTA1NTM1ODk0ODQyOThiMmU0YTAyMzNiNzY3NTNmODg4ZTIwN2VmNSJ9fX0="), "&r查看这个物品的介绍百科 &7(Slimefun Wiki)", new String[] { "", "&7⇨ 点击打开" }));
/* 1124 */           menu.addMenuClickHandler(8, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                 {
/* 1128 */                   p.closeInventory();
/* 1129 */                   p.sendMessage("");
/* 1130 */                   p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o" + Slimefun.getItemConfig().getString(sfItem.getID() + ".wiki")));
/* 1131 */                   p.sendMessage("");
/* 1132 */                   return false;
/*      */                 }
/*      */               });
/* 1135 */         } catch (Exception e) {
/* 1136 */           e.printStackTrace();
/*      */         } 
/*      */       }
/* 1139 */       if (Slimefun.getItemConfig().contains(sfItem.getID() + ".youtube")) {
/*      */         try {
/* 1141 */           menu.addItem(7, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzNTNmZDBmODYzMTQzNTM4NzY1ODYwNzViOWJkZjBjNDg0YWFiMDMzMWI4NzJkZjExYmQ1NjRmY2IwMjllZCJ9fX0="), "&r示例视频 &7(Youtube)", new String[] { "", "&7⇨ 点击观看" }));
/* 1142 */           menu.addMenuClickHandler(7, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                 {
/* 1146 */                   p.closeInventory();
/* 1147 */                   p.sendMessage("");
/* 1148 */                   p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o" + Slimefun.getItemConfig().getString(sfItem.getID() + ".youtube")));
/* 1149 */                   p.sendMessage("");
/* 1150 */                   return false;
/*      */                 }
/*      */               });
/* 1153 */         } catch (Exception e) {
/* 1154 */           e.printStackTrace();
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1159 */     menu.addItem(10, recipeType);
/* 1160 */     menu.addMenuClickHandler(10, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1164 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1168 */     menu.addItem(12, Slimefun.hasUnlocked(p, recipe[3], false) ? recipe[3] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[3], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[3]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1169 */     menu.addMenuClickHandler(12, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1173 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1174 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1178 */     menu.addItem(13, Slimefun.hasUnlocked(p, recipe[4], false) ? recipe[4] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[4], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[4]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1179 */     menu.addMenuClickHandler(13, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1183 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1184 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1188 */     menu.addItem(14, Slimefun.hasUnlocked(p, recipe[5], false) ? recipe[5] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[5], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[5]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1189 */     menu.addMenuClickHandler(14, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1193 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1194 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1198 */     menu.addItem(16, recipeOutput);
/* 1199 */     menu.addMenuClickHandler(16, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1203 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1208 */     if (p.isOp() && sfItem != null) {
/* 1209 */       menu.addItem(18, (ItemStack)new CustomItem(Material.BOOK, ChatColor.AQUA + "物品ID: " + ChatColor.YELLOW + sfItem.getID(), 0));
/*      */     }
/*      */     
/* 1212 */     menu.addItem(21, Slimefun.hasUnlocked(p, recipe[6], false) ? recipe[6] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[6], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[6]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1213 */     menu.addMenuClickHandler(21, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1217 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1218 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1222 */     menu.addItem(22, Slimefun.hasUnlocked(p, recipe[7], false) ? recipe[7] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[7], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[7]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1223 */     menu.addMenuClickHandler(22, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1227 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1228 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1232 */     menu.addItem(23, Slimefun.hasUnlocked(p, recipe[8], false) ? recipe[8] : (ItemStack)new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[8], false), 0, new String[] { "&4&l未解锁", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[8]), false) ? "&r需要在其他地方解锁" : "&r无权限" }));
/* 1233 */     menu.addMenuClickHandler(23, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */           {
/* 1237 */             SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1238 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1242 */     if (sfItem != null)
/*      */     {
/* 1244 */       if ((sfItem instanceof SlimefunMachine && ((SlimefunMachine)sfItem).getDisplayRecipes().size() > 0) || (sfItem instanceof SlimefunGadget && ((SlimefunGadget)sfItem).getRecipes().size() > 0)) {
/* 1245 */         for (int i = 27; i < 36; i++) {
/* 1246 */           menu.addItem(i, (ItemStack)new CustomItem(Material.STAINED_GLASS_PANE, (SlimefunItem.getByItem(item) instanceof SlimefunMachine) ? "&7⇩ 合成需要在这个机器中进行 ⇩" : " ", 7));
/* 1247 */           menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */                 {
/* 1251 */                   return false;
/*      */                 }
/*      */               });
/*      */         } 
/*      */         
/* 1256 */         List<ItemStack> recipes = (SlimefunItem.getByItem(item) instanceof SlimefunMachine) ? ((SlimefunMachine)SlimefunItem.getByItem(item)).getDisplayRecipes() : ((SlimefunGadget)SlimefunItem.getByItem(item)).getDisplayRecipes();
/* 1257 */         int recipe_size = recipes.size();
/* 1258 */         if (recipe_size > 18) recipe_size = 18; 
/* 1259 */         int inputs = -1, outputs = -1;
/*      */         
/* 1261 */         for (int j = 0; j < recipe_size; j++) {
/* 1262 */           int slot = 36;
/* 1263 */           if (j % 2 == 1) {
/* 1264 */             slot += 9;
/* 1265 */             outputs++;
/*      */           } else {
/* 1267 */             inputs++;
/*      */           } 
/* 1269 */           int addition = (j % 2 == 0) ? inputs : outputs;
/*      */           
/* 1271 */           menu.addItem(slot + addition, recipes.get(j));
/* 1272 */           menu.addMenuClickHandler(slot + addition, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                 {
/* 1276 */                   SlimefunGuide.displayItem(p, item, true, book, 0);
/* 1277 */                   return false;
/*      */                 }
/*      */               });
/*      */         }
/*      */       
/* 1282 */       } else if (sfItem instanceof AGenerator) {
/* 1283 */         int slot = 27;
/* 1284 */         for (MachineFuel fuel : ((AGenerator)sfItem).getFuelTypes()) {
/* 1285 */           if (slot > 54)
/* 1286 */             break;  ItemStack fItem = fuel.getInput().clone();
/* 1287 */           ItemMeta im = fItem.getItemMeta();
/* 1288 */           List<String> lore = new ArrayList<>();
/* 1289 */           lore.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &7耗时 " + getTimeLeft(fuel.getTicks() / 2)));
/* 1290 */           lore.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &e⚡ &7" + (((AGenerator)sfItem).getEnergyProduction() * 2) + " J/s"));
/* 1291 */           lore.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &e⚡ &7总计能量 " + DoubleHandler.getFancyDouble((fuel.getTicks() * ((AGenerator)sfItem).getEnergyProduction())) + " J"));
/* 1292 */           im.setLore(lore);
/* 1293 */           fItem.setItemMeta(im);
/* 1294 */           menu.addItem(slot, fItem);
/* 1295 */           menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                 {
/* 1299 */                   return false;
/*      */                 }
/*      */               });
/* 1302 */           slot++;
/*      */         }
/*      */       
/* 1305 */       } else if (sfItem instanceof AReactor) {
/* 1306 */         int slot = 27;
/* 1307 */         for (MachineFuel fuel : ((AReactor)sfItem).getFuelTypes()) {
/* 1308 */           if (slot > 54)
/* 1309 */             break;  ItemStack fItem = fuel.getInput().clone();
/* 1310 */           ItemMeta im = fItem.getItemMeta();
/* 1311 */           List<String> lore = new ArrayList<>();
/* 1312 */           lore.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &7耗时 " + getTimeLeft(fuel.getTicks() / 2)));
/* 1313 */           lore.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &e⚡ &7" + (((AReactor)sfItem).getEnergyProduction() * 2) + " J/s"));
/* 1314 */           lore.add(ChatColor.translateAlternateColorCodes('&', "&8⇨ &e⚡ &7总计能量 " + DoubleHandler.getFancyDouble((fuel.getTicks() * ((AReactor)sfItem).getEnergyProduction())) + " J"));
/* 1315 */           im.setLore(lore);
/* 1316 */           fItem.setItemMeta(im);
/* 1317 */           menu.addItem(slot, fItem);
/* 1318 */           menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 public boolean onClick(Player p, int slot, ItemStack item, ClickAction action)
/*      */                 {
/* 1322 */                   return false;
/*      */                 }
/*      */               });
/* 1325 */           slot++;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1330 */     menu.build().open(new Player[] { p });
/*      */   }
/*      */   
/*      */   public static void clearHistory(UUID uuid) {
/* 1334 */     if (!history.containsKey(uuid))
/*      */       return; 
/* 1336 */     for (URID urid : history.get(uuid)) {
/* 1337 */       urid.markDirty();
/*      */     }
/* 1339 */     history.remove(uuid);
/*      */   }
/*      */   
/*      */   private static String getTimeLeft(int l) {
/* 1343 */     String timeleft = "";
/* 1344 */     int minutes = (int)(l / 60L);
/* 1345 */     if (minutes > 0) {
/* 1346 */       timeleft = String.valueOf(timeleft) + minutes + "m ";
/*      */     }
/* 1348 */     l -= minutes * 60;
/* 1349 */     int seconds = l;
/* 1350 */     timeleft = String.valueOf(timeleft) + seconds + "s";
/* 1351 */     return "&7" + timeleft;
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\SlimefunGuide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */