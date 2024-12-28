/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.Slimefun.AncientAltar.AltarRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.URID.URID;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.EnergyNet;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlimefunItem
/*     */ {
/*  39 */   public static List<SlimefunItem> items = new ArrayList<>();
/*     */   
/*  41 */   public static Map<String, URID> map_id = new HashMap<>();
/*  42 */   public static List<ItemStack> radioactive = new ArrayList<>();
/*  43 */   public static int vanilla = 0;
/*  44 */   public static Set<String> tickers = new HashSet<>();
/*     */   
/*  46 */   public static List<SlimefunItem> all = new ArrayList<>();
/*  47 */   public static Map<String, Set<ItemHandler>> handlers = new HashMap<>();
/*  48 */   public static Map<String, SlimefunBlockHandler> blockhandler = new HashMap<>();
/*     */   
/*     */   private String id;
/*     */   private URID urid;
/*     */   private String hash;
/*     */   private State state;
/*     */   private ItemStack item;
/*     */   private Category category;
/*     */   private ItemStack[] recipe;
/*     */   private RecipeType recipeType;
/*  58 */   private ItemStack recipeOutput = null;
/*     */   private Research research;
/*  60 */   private int month = -1;
/*     */   private boolean enchantable = true, disenchantable = true;
/*     */   private boolean hidden = false;
/*     */   private boolean replacing = false;
/*     */   private boolean addon = false;
/*  65 */   private String permission = "";
/*  66 */   private Set<ItemHandler> itemhandlers = new HashSet<>();
/*     */   private boolean ticking = false;
/*     */   private BlockTicker blockTicker;
/*     */   private EnergyTicker energyTicker;
/*  70 */   private String[] keys = null;
/*  71 */   private Object[] values = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum State
/*     */   {
/*  82 */     ENABLED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     DISABLED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     VANILLA;
/*     */   }
/*     */   
/*     */   public SlimefunItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/*  96 */     this.item = item;
/*  97 */     this.category = category;
/*  98 */     this.id = id;
/*  99 */     this.recipeType = recipeType;
/* 100 */     this.recipe = recipe;
/*     */     
/* 102 */     this.urid = URID.nextURID(this, false);
/*     */   }
/*     */   
/*     */   public SlimefunItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/* 106 */     this.item = item;
/* 107 */     this.category = category;
/* 108 */     this.id = id;
/* 109 */     this.recipeType = recipeType;
/* 110 */     this.recipe = recipe;
/* 111 */     this.recipeOutput = recipeOutput;
/*     */     
/* 113 */     this.urid = URID.nextURID(this, false);
/*     */   }
/*     */   
/*     */   public SlimefunItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput, String[] keys, Object[] values) {
/* 117 */     this.item = item;
/* 118 */     this.category = category;
/* 119 */     this.id = id;
/* 120 */     this.recipeType = recipeType;
/* 121 */     this.recipe = recipe;
/* 122 */     this.recipeOutput = recipeOutput;
/* 123 */     this.keys = keys;
/* 124 */     this.values = values;
/*     */     
/* 126 */     this.urid = URID.nextURID(this, false);
/*     */   }
/*     */   
/*     */   public SlimefunItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, String[] keys, Object[] values) {
/* 130 */     this.item = item;
/* 131 */     this.category = category;
/* 132 */     this.id = id;
/* 133 */     this.recipeType = recipeType;
/* 134 */     this.recipe = recipe;
/* 135 */     this.keys = keys;
/* 136 */     this.values = values;
/*     */     
/* 138 */     this.urid = URID.nextURID(this, false);
/*     */   }
/*     */   
/*     */   public SlimefunItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, boolean hidden) {
/* 142 */     this.item = item;
/* 143 */     this.category = category;
/* 144 */     this.id = id;
/* 145 */     this.recipeType = recipeType;
/* 146 */     this.recipe = recipe;
/* 147 */     this.hidden = hidden;
/*     */     
/* 149 */     this.urid = URID.nextURID(this, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getName() {
/* 161 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getID() {
/* 169 */     return this.id;
/* 170 */   } public URID getURID() { return this.urid; }
/* 171 */   public String getHash() { return this.hash; }
/* 172 */   public State getState() { return this.state; }
/* 173 */   public ItemStack getItem() { return this.item; }
/* 174 */   public Category getCategory() { return this.category; }
/* 175 */   public ItemStack[] getRecipe() { return this.recipe; } public RecipeType getRecipeType() {
/* 176 */     return this.recipeType;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public ItemStack getCustomOutput() {
/* 182 */     return this.recipeOutput;
/*     */   }
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 186 */     return this.recipeOutput;
/* 187 */   } public Research getResearch() { return this.research; }
/* 188 */   public int getMonth() { return this.month; }
/* 189 */   public boolean isEnchantable() { return this.enchantable; } public boolean isDisenchantable() {
/* 190 */     return this.disenchantable;
/*     */   }
/*     */   
/*     */   public boolean isHidden() {
/* 194 */     return this.hidden;
/* 195 */   } public boolean isReplacing() { return this.replacing; } public boolean isAddonItem() {
/* 196 */     return this.addon;
/*     */   }
/*     */   
/*     */   public String getPermission() {
/* 200 */     return this.permission;
/* 201 */   } public Set<ItemHandler> getHandlers() { return this.itemhandlers; } public boolean isTicking() {
/* 202 */     return this.ticking;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BlockTicker getTicker() {
/* 208 */     return this.blockTicker;
/*     */   }
/*     */   
/*     */   public BlockTicker getBlockTicker() {
/* 212 */     return this.blockTicker;
/* 213 */   } public EnergyTicker getEnergyTicker() { return this.energyTicker; }
/* 214 */   public String[] listKeys() { return this.keys; }
/* 215 */   public Object[] listValues() { return this.values; } public boolean isDisabled() {
/* 216 */     return (this.state != State.ENABLED);
/*     */   }
/*     */   public void register() {
/* 219 */     register(false);
/*     */   }
/*     */   
/*     */   public void register(boolean slimefun) {
/* 223 */     this.addon = !slimefun;
/*     */     try {
/* 225 */       if (map_id.containsKey(this.id)) throw new IllegalArgumentException("ID \"" + this.id + "\" already exists"); 
/* 226 */       if (this.recipe.length < 9) this.recipe = new ItemStack[] { null, null, null, null, null, null, null, null, null }; 
/* 227 */       all.add(this);
/*     */       
/* 229 */       SlimefunStartup.getItemCfg().setDefaultValue(this.id + ".enabled", Boolean.valueOf(true));
/* 230 */       SlimefunStartup.getItemCfg().setDefaultValue(this.id + ".can-be-used-in-workbenches", Boolean.valueOf(this.replacing));
/* 231 */       SlimefunStartup.getItemCfg().setDefaultValue(this.id + ".hide-in-guide", Boolean.valueOf(this.hidden));
/* 232 */       SlimefunStartup.getItemCfg().setDefaultValue(this.id + ".allow-enchanting", Boolean.valueOf(this.enchantable));
/* 233 */       SlimefunStartup.getItemCfg().setDefaultValue(this.id + ".allow-disenchanting", Boolean.valueOf(this.disenchantable));
/* 234 */       SlimefunStartup.getItemCfg().setDefaultValue(this.id + ".required-permission", this.permission);
/* 235 */       if (this.keys != null && this.values != null) {
/* 236 */         for (int i = 0; i < this.keys.length; i++) {
/* 237 */           SlimefunStartup.getItemCfg().setDefaultValue(this.id + "." + this.keys[i], this.values[i]);
/*     */         }
/*     */       }
/*     */       
/* 241 */       for (World world : Bukkit.getWorlds()) {
/* 242 */         SlimefunStartup.getWhitelist().setDefaultValue(world.getName() + ".enabled", Boolean.valueOf(true));
/* 243 */         SlimefunStartup.getWhitelist().setDefaultValue(world.getName() + ".enabled-items." + this.id, Boolean.valueOf(true));
/*     */       } 
/*     */       
/* 246 */       if (this.ticking && !SlimefunStartup.getCfg().getBoolean("URID.enable-tickers")) {
/* 247 */         this.state = State.DISABLED;
/*     */         
/*     */         return;
/*     */       } 
/* 251 */       if (SlimefunStartup.getItemCfg().getBoolean(this.id + ".enabled"))
/* 252 */       { if (!Category.list().contains(this.category)) this.category.register();
/*     */         
/* 254 */         this.state = State.ENABLED;
/*     */         
/* 256 */         this.replacing = SlimefunStartup.getItemCfg().getBoolean(this.id + ".can-be-used-in-workbenches");
/* 257 */         this.hidden = SlimefunStartup.getItemCfg().getBoolean(this.id + ".hide-in-guide");
/* 258 */         this.enchantable = SlimefunStartup.getItemCfg().getBoolean(this.id + ".allow-enchanting");
/* 259 */         this.disenchantable = SlimefunStartup.getItemCfg().getBoolean(this.id + ".allow-disenchanting");
/* 260 */         this.permission = SlimefunStartup.getItemCfg().getString(this.id + ".required-permission");
/* 261 */         items.add(this);
/* 262 */         if (slimefun) vanilla++; 
/* 263 */         map_id.put(this.id, this.urid);
/* 264 */         create();
/* 265 */         for (ItemHandler handler : this.itemhandlers) {
/* 266 */           Set<ItemHandler> handlerset = getHandlers(handler.toCodename());
/* 267 */           handlerset.add(handler);
/* 268 */           handlers.put(handler.toCodename(), handlerset);
/*     */         } 
/*     */         
/* 271 */         if (SlimefunStartup.getCfg().getBoolean("options.print-out-loading")) System.out.println("[Slimefun] Loaded Item \"" + this.id + "\"");
/*     */          }
/* 273 */       else if (this instanceof VanillaItem) { this.state = State.VANILLA; }
/* 274 */       else { this.state = State.DISABLED; }
/*     */     
/* 276 */     } catch (Exception x) {
/* 277 */       System.err.println("[Slimefun] Item Registration failed: " + this.id);
/* 278 */       x.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<SlimefunItem> list() {
/* 283 */     return items;
/*     */   }
/*     */   
/*     */   public void bindToResearch(Research r) {
/* 287 */     if (r != null) r.getEffectedItems().add(this); 
/* 288 */     this.research = r;
/*     */   }
/*     */   
/*     */   public void setHash(String hash) {
/* 292 */     this.hash = hash;
/*     */   }
/*     */   
/*     */   public void setRecipe(ItemStack[] recipe) {
/* 296 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */   public void setRecipeType(RecipeType type) {
/* 300 */     this.recipeType = type;
/*     */   }
/*     */   
/*     */   public void setCategory(Category category) {
/* 304 */     this.category = category;
/*     */   }
/*     */   
/*     */   public void setRecipeOutput(ItemStack output) {
/* 308 */     this.recipeOutput = output;
/*     */   }
/*     */   
/*     */   public void setReplacing(boolean replacing) {
/* 312 */     this.replacing = replacing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static SlimefunItem getByName(String name) {
/* 321 */     return (SlimefunItem)URID.decode(map_id.get(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SlimefunItem getByID(String id) {
/* 328 */     return (SlimefunItem)URID.decode(map_id.get(id));
/*     */   }
/*     */   
/*     */   public static SlimefunItem getByItem(ItemStack item) {
/* 332 */     if (item == null) return null; 
/* 333 */     if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BROKEN_SPAWNER, false)) return getByID("BROKEN_SPAWNER"); 
/* 334 */     if (SlimefunManager.isItemSimiliar(item, SlimefunItems.REPAIRED_SPAWNER, false)) return getByID("REINFORCED_SPAWNER"); 
/* 335 */     for (SlimefunItem sfi : items) {
/* 336 */       if (sfi instanceof ChargableItem && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi; 
/* 337 */       if (sfi instanceof DamagableChargableItem && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi; 
/* 338 */       if (sfi instanceof ChargedItem && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi; 
/* 339 */       if (sfi instanceof SlimefunBackpack && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi; 
/* 340 */       if (SlimefunManager.isItemSimiliar(item, sfi.getItem(), true)) return sfi; 
/*     */     } 
/* 342 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isItem(ItemStack item) {
/* 346 */     if (item == null) return false; 
/* 347 */     if (this instanceof ChargableItem && SlimefunManager.isItemSimiliar(item, this.item, false)) return true; 
/* 348 */     if (this instanceof DamagableChargableItem && SlimefunManager.isItemSimiliar(item, this.item, false)) return true; 
/* 349 */     if (this instanceof ChargedItem && SlimefunManager.isItemSimiliar(item, this.item, false)) return true; 
/* 350 */     return SlimefunManager.isItemSimiliar(item, this.item, true);
/*     */   }
/*     */   
/*     */   public void load() {
/*     */     try {
/* 355 */       if (!this.hidden) this.category.add(this); 
/* 356 */       ItemStack output = this.item.clone();
/* 357 */       if (this.recipeOutput != null) output = this.recipeOutput.clone();
/*     */       
/* 359 */       if (this.recipeType.toItem().isSimilar(RecipeType.MOB_DROP.toItem())) {
/*     */         try {
/* 361 */           EntityType entity = EntityType.valueOf(ChatColor.stripColor(this.recipe[4].getItemMeta().getDisplayName()).toUpperCase().replace(" ", "_"));
/* 362 */           List<ItemStack> dropping = new ArrayList<>();
/* 363 */           if (SlimefunManager.drops.containsKey(entity)) dropping = (List<ItemStack>)SlimefunManager.drops.get(entity); 
/* 364 */           dropping.add(output);
/* 365 */           SlimefunManager.drops.put(entity, dropping);
/* 366 */         } catch (Exception exception) {}
/*     */       
/*     */       }
/* 369 */       else if (this.recipeType.toItem().isSimilar(RecipeType.ANCIENT_ALTAR.toItem())) {
/* 370 */         new AltarRecipe(Arrays.asList(this.recipe), output);
/*     */       }
/* 372 */       else if (this.recipeType.getMachine() != null && getByID(this.recipeType.getMachine().getID()) instanceof SlimefunMachine) {
/* 373 */         ((SlimefunMachine)getByID(this.recipeType.getMachine().getID())).addRecipe(this.recipe, output);
/*     */       } 
/* 375 */       install();
/* 376 */     } catch (Exception x) {
/* 377 */       System.err.println("[Slimefun] Item Initialization failed: " + this.id);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static State getState(ItemStack item) {
/* 382 */     for (SlimefunItem i : all) {
/* 383 */       if (i.isItem(item)) {
/* 384 */         return i.getState();
/*     */       }
/*     */     } 
/* 387 */     return State.ENABLED;
/*     */   }
/*     */   
/*     */   public static boolean isDisabled(ItemStack item) {
/* 391 */     for (SlimefunItem i : all) {
/* 392 */       if (i.isItem(item)) {
/* 393 */         return i.isDisabled();
/*     */       }
/*     */     } 
/* 396 */     return false;
/*     */   }
/*     */   public void install() {}
/*     */   
/*     */   public void create() {}
/*     */   
/*     */   public void addItemHandler(ItemHandler... handler) {
/* 403 */     this.itemhandlers.addAll(Arrays.asList(handler));
/*     */     
/* 405 */     for (ItemHandler h : handler) {
/* 406 */       if (h instanceof BlockTicker) {
/* 407 */         this.ticking = true;
/* 408 */         tickers.add(getID());
/* 409 */         this.blockTicker = (BlockTicker)h;
/*     */       }
/* 411 */       else if (h instanceof EnergyTicker) {
/* 412 */         this.energyTicker = (EnergyTicker)h;
/* 413 */         EnergyNet.registerComponent(getID(), EnergyNet.NetworkComponent.SOURCE);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void register(boolean vanilla, ItemHandler... handlers) {
/* 419 */     addItemHandler(handlers);
/* 420 */     register(vanilla);
/*     */   }
/*     */   
/*     */   public void register(ItemHandler... handlers) {
/* 424 */     addItemHandler(handlers);
/* 425 */     register(false);
/*     */   }
/*     */   
/*     */   public void register(boolean vanilla, SlimefunBlockHandler handler) {
/* 429 */     blockhandler.put(getID(), handler);
/* 430 */     register(vanilla);
/*     */   }
/*     */   
/*     */   public void register(SlimefunBlockHandler handler) {
/* 434 */     blockhandler.put(getID(), handler);
/* 435 */     register(false);
/*     */   }
/*     */   
/*     */   public static Set<ItemHandler> getHandlers(String codeid) {
/* 439 */     if (handlers.containsKey(codeid)) return handlers.get(codeid); 
/* 440 */     return new HashSet<>();
/*     */   }
/*     */   
/*     */   public static void setRadioactive(ItemStack item) {
/* 444 */     radioactive.add(item);
/*     */   }
/*     */   
/*     */   public static ItemStack getItem(String id) {
/* 448 */     SlimefunItem item = getByID(id);
/* 449 */     return (item != null) ? item.getItem() : null;
/*     */   }
/*     */   
/*     */   public static void patchExistingItem(String id, ItemStack stack) {
/* 453 */     SlimefunItem item = getByID(id);
/* 454 */     if (item != null) {
/* 455 */       System.out.println("[Slimefun] WARNING - Patching existing Item - " + id);
/* 456 */       System.out.println("[Slimefun] This might take a while");
/*     */       
/* 458 */       ItemStack old = item.getItem();
/* 459 */       item.setItem(stack);
/* 460 */       for (SlimefunItem sfi : list()) {
/* 461 */         ItemStack[] recipe = sfi.getRecipe();
/* 462 */         for (int i = 0; i < 9; i++) {
/* 463 */           if (SlimefunManager.isItemSimiliar(recipe[i], old, true)) recipe[i] = stack; 
/*     */         } 
/* 465 */         sfi.setRecipe(recipe);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void registerChargeableBlock(int capacity) {
/* 471 */     registerChargeableBlock(false, capacity);
/*     */   }
/*     */   
/*     */   public void registerChargeableBlock(boolean slimefun, int capacity) {
/* 475 */     register(slimefun);
/* 476 */     ChargableBlock.registerChargableBlock(this.id, capacity, true);
/* 477 */     EnergyNet.registerComponent(this.id, EnergyNet.NetworkComponent.CONSUMER);
/*     */   }
/*     */   
/*     */   public void registerUnrechargeableBlock(boolean slimefun, int capacity) {
/* 481 */     register(slimefun);
/* 482 */     ChargableBlock.registerChargableBlock(this.id, capacity, false);
/*     */   }
/*     */   
/*     */   public void registerBlockCapacitor(boolean slimefun, int capacity) {
/* 486 */     register(slimefun);
/* 487 */     ChargableBlock.registerCapacitor(this.id, capacity);
/*     */   }
/*     */   
/*     */   public void registerEnergyDistributor(boolean slimefun) {
/* 491 */     register(slimefun);
/* 492 */     EnergyNet.registerComponent(this.id, EnergyNet.NetworkComponent.DISTRIBUTOR);
/*     */   }
/*     */   
/*     */   public void registerDistibutingCapacitor(boolean slimefun, int capacity) {
/* 496 */     register(slimefun);
/* 497 */     EnergyNet.registerComponent(this.id, EnergyNet.NetworkComponent.DISTRIBUTOR);
/* 498 */     ChargableBlock.registerCapacitor(this.id, capacity);
/*     */   }
/*     */   
/*     */   protected void setItem(ItemStack stack) {
/* 502 */     this.item = stack;
/*     */   }
/*     */   
/*     */   public static boolean isTicking(String item) {
/* 506 */     return tickers.contains(item);
/*     */   }
/*     */   
/*     */   public static void registerBlockHandler(String id, SlimefunBlockHandler handler) {
/* 510 */     blockhandler.put(id, handler);
/*     */   }
/*     */   
/*     */   public void registerChargeableBlock(boolean vanilla, int capacity, ItemHandler... handlers) {
/* 514 */     addItemHandler(handlers);
/* 515 */     registerChargeableBlock(vanilla, capacity);
/*     */   }
/*     */   
/*     */   public BlockMenu getBlockMenu(Block b) {
/* 519 */     return BlockStorage.getInventory(b);
/*     */   }
/*     */   
/*     */   public void addWikipage(String page) {
/* 523 */     Slimefun.addWikiPage(getID(), "https://github.com/TheBusyBiscuit/Slimefun4/wiki/" + page);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\SlimefunItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */