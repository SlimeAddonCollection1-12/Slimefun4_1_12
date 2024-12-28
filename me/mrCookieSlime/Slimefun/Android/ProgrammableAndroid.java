/*      */ package me.mrCookieSlime.Slimefun.Android;
/*      */ import java.io.File;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.UUID;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.MenuHelper;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*      */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*      */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*      */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*      */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*      */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*      */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*      */ import me.mrCookieSlime.Slimefun.holograms.AndroidStatusHologram;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.ChatColor;
/*      */ import org.bukkit.Effect;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.OfflinePlayer;
/*      */ import org.bukkit.Sound;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.block.Dispenser;
/*      */ import org.bukkit.block.Skull;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.inventory.Inventory;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.material.MaterialData;
/*      */ import org.bukkit.metadata.FixedMetadataValue;
/*      */ import org.bukkit.metadata.MetadataValue;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ 
/*      */ public abstract class ProgrammableAndroid extends SlimefunItem {
/*   52 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 24, 25, 26, 27, 33, 35, 36, 42, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*      */   
/*   54 */   private static final int[] border_out = new int[] { 10, 11, 12, 13, 14, 19, 23, 28, 32, 37, 38, 39, 40, 41 };
/*      */ 
/*      */   
/*   57 */   private static final ItemStack[] fish = new ItemStack[] { (new MaterialData(Material.RAW_FISH, (byte)0))
/*   58 */       .toItemStack(1), (new MaterialData(Material.RAW_FISH, (byte)1)).toItemStack(1), (new MaterialData(Material.RAW_FISH, (byte)2))
/*   59 */       .toItemStack(1), (new MaterialData(Material.RAW_FISH, (byte)3)).toItemStack(1), new ItemStack(Material.STRING), new ItemStack(Material.BONE), new ItemStack(Material.STICK) };
/*      */ 
/*      */   
/*   62 */   private static final List<BlockFace> directions = Arrays.asList(new BlockFace[] { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST });
/*      */   
/*   64 */   private static final List<Material> blockblacklist = new ArrayList<>();
/*      */   
/*      */   static {
/*   67 */     blockblacklist.add(Material.BEDROCK);
/*   68 */     blockblacklist.add(Material.BARRIER);
/*   69 */     blockblacklist.add(Material.ENDER_PORTAL_FRAME);
/*   70 */     blockblacklist.add(Material.COMMAND);
/*   71 */     blockblacklist.add(Material.COMMAND_CHAIN);
/*   72 */     blockblacklist.add(Material.COMMAND_REPEATING);
/*      */   }
/*      */   
/*   75 */   private Set<MachineFuel> recipes = new HashSet<>();
/*      */   
/*      */   public String getInventoryTitle() {
/*   78 */     return "可编程机器人";
/*      */   }
/*      */   
/*      */   public int[] getOutputSlots() {
/*   82 */     return new int[] { 20, 21, 22, 29, 30, 31 };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProgrammableAndroid(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*   92 */     super(category, item, name, recipeType, recipe);
/*      */ 
/*      */     
/*   95 */     if (getTier() == 1) {
/*   96 */       registerFuel(new MachineFuel(80, (new MaterialData(Material.COAL, (byte)0))
/*   97 */             .toItemStack(1)));
/*   98 */       registerFuel(new MachineFuel(80, (new MaterialData(Material.COAL, (byte)1))
/*   99 */             .toItemStack(1)));
/*  100 */       registerFuel(new MachineFuel(800, new ItemStack(Material.COAL_BLOCK)));
/*  101 */       registerFuel(new MachineFuel(45, new ItemStack(Material.BLAZE_ROD)));
/*      */ 
/*      */       
/*  104 */       registerFuel(new MachineFuel(4, (new MaterialData(Material.LOG, (byte)0)).toItemStack(1)));
/*  105 */       registerFuel(new MachineFuel(4, (new MaterialData(Material.LOG, (byte)1)).toItemStack(1)));
/*  106 */       registerFuel(new MachineFuel(4, (new MaterialData(Material.LOG, (byte)2)).toItemStack(1)));
/*  107 */       registerFuel(new MachineFuel(4, (new MaterialData(Material.LOG, (byte)3)).toItemStack(1)));
/*  108 */       registerFuel(new MachineFuel(4, (new MaterialData(Material.LOG_2, (byte)0))
/*  109 */             .toItemStack(1)));
/*  110 */       registerFuel(new MachineFuel(4, (new MaterialData(Material.LOG_2, (byte)1))
/*  111 */             .toItemStack(1)));
/*      */ 
/*      */       
/*  114 */       registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)0)).toItemStack(1)));
/*  115 */       registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)1)).toItemStack(1)));
/*  116 */       registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)2)).toItemStack(1)));
/*  117 */       registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)3)).toItemStack(1)));
/*  118 */       registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)4)).toItemStack(1)));
/*  119 */       registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)5)).toItemStack(1)));
/*      */     }
/*  121 */     else if (getTier() == 2) {
/*  122 */       registerFuel(new MachineFuel(100, new ItemStack(Material.LAVA_BUCKET)));
/*  123 */       registerFuel(new MachineFuel(200, SlimefunItems.BUCKET_OF_OIL));
/*  124 */       registerFuel(new MachineFuel(500, SlimefunItems.BUCKET_OF_FUEL));
/*      */     } else {
/*      */       
/*  127 */       registerFuel(new MachineFuel(2500, SlimefunItems.URANIUM));
/*  128 */       registerFuel(new MachineFuel(1200, SlimefunItems.NEPTUNIUM));
/*  129 */       registerFuel(new MachineFuel(3000, SlimefunItems.BOOSTED_URANIUM));
/*      */     } 
/*      */     
/*  132 */     new BlockMenuPreset(name, getInventoryTitle())
/*      */       {
/*      */         public void init()
/*      */         {
/*      */           try {
/*  137 */             ProgrammableAndroid.this.constructMenu(this);
/*  138 */           } catch (Exception e) {
/*  139 */             e.printStackTrace();
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean canOpen(Block b, Player p) {
/*  146 */           boolean open = (BlockStorage.getLocationInfo(b.getLocation(), "owner").equals(p.getUniqueId().toString()) || p.hasPermission("slimefun.android.bypass"));
/*  147 */           if (!open) {
/*  148 */             Messages.local.sendTranslation((CommandSender)p, "inventory.no-access", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */           }
/*  150 */           return open;
/*      */         }
/*      */ 
/*      */         
/*      */         public void newInstance(BlockMenu menu, final Block b) {
/*      */           try {
/*  156 */             menu.replaceExistingItem(15, (ItemStack)new CustomItem(
/*  157 */                   CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTAxYzdiNTcyNjE3ODk3NGIzYjNhMDFiNDJhNTkwZTU0MzY2MDI2ZmQ0MzgwOGYyYTc4NzY0ODg0M2E3ZjVhIn19fQ=="), "&a开始/继续"));
/*  158 */             menu.addMenuClickHandler(15, new ChestMenu.MenuClickHandler()
/*      */                 {
/*      */                   
/*      */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */                   {
/*  163 */                     Messages.local.sendTranslation((CommandSender)p, "robot.started", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  164 */                     BlockStorage.addBlockInfo(b, "paused", "false");
/*  165 */                     p.closeInventory();
/*  166 */                     return false;
/*      */                   }
/*      */                 });
/*      */             
/*  170 */             menu.replaceExistingItem(17, (ItemStack)new CustomItem(
/*  171 */                   CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYxMzlmZDFjNTY1NGU1NmU5ZTRlMmM4YmU3ZWIyYmQ1YjQ5OWQ2MzM2MTY2NjNmZWVlOTliNzQzNTJhZDY0In19fQ=="), "&4暂停"));
/*  172 */             menu.addMenuClickHandler(17, new ChestMenu.MenuClickHandler()
/*      */                 {
/*      */                   
/*      */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */                   {
/*  177 */                     BlockStorage.addBlockInfo(b, "paused", "true");
/*  178 */                     Messages.local.sendTranslation((CommandSender)p, "robot.stopped", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  179 */                     return false;
/*      */                   }
/*      */                 });
/*      */             
/*  183 */             menu.replaceExistingItem(16, (ItemStack)new CustomItem(
/*  184 */                   CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&b记忆核心", new String[] { "", "&8⇨ &7点击打开脚本编辑器" }));
/*  185 */             menu.addMenuClickHandler(16, new ChestMenu.MenuClickHandler()
/*      */                 {
/*      */                   
/*      */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */                   {
/*      */                     try {
/*  191 */                       BlockStorage.addBlockInfo(b, "paused", "true");
/*  192 */                       Messages.local.sendTranslation((CommandSender)p, "robot.stopped", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                       
/*  194 */                       ProgrammableAndroid.this.openScriptEditor(p, b);
/*  195 */                     } catch (Exception e) {
/*  196 */                       e.printStackTrace();
/*      */                     } 
/*  198 */                     return false;
/*      */                   }
/*      */                 });
/*  201 */           } catch (Exception e) {
/*  202 */             e.printStackTrace();
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*      */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  208 */           return new int[0];
/*      */         }
/*      */       };
/*      */     
/*  212 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/*  216 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*  217 */             BlockStorage.addBlockInfo(b, "script", "START-TURN_LEFT-REPEAT");
/*  218 */             BlockStorage.addBlockInfo(b, "index", "0");
/*  219 */             BlockStorage.addBlockInfo(b, "fuel", "0");
/*  220 */             BlockStorage.addBlockInfo(b, "rotation", "NORTH");
/*  221 */             BlockStorage.addBlockInfo(b, "paused", "true");
/*  222 */             b.setData((byte)1);
/*  223 */             Skull skull = (Skull)b.getState();
/*  224 */             skull.setRotation(BlockFace.NORTH);
/*  225 */             skull.update(true, false);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  232 */             boolean allow = (reason.equals(UnregisterReason.PLAYER_BREAK) && (BlockStorage.getLocationInfo(b.getLocation(), "owner").equals(p.getUniqueId().toString()) || p.hasPermission("slimefun.android.bypass")));
/*      */             
/*  234 */             if (allow) {
/*  235 */               BlockMenu inv = BlockStorage.getInventory(b);
/*  236 */               if (inv != null) {
/*  237 */                 if (inv.getItemInSlot(43) != null) {
/*  238 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv
/*  239 */                       .getItemInSlot(43));
/*  240 */                   inv.replaceExistingItem(43, null);
/*      */                 } 
/*  242 */                 for (int slot : ProgrammableAndroid.this.getOutputSlots()) {
/*  243 */                   if (inv.getItemInSlot(slot) != null) {
/*  244 */                     b.getWorld().dropItemNaturally(b.getLocation(), inv
/*  245 */                         .getItemInSlot(slot));
/*  246 */                     inv.replaceExistingItem(slot, null);
/*      */                   } 
/*      */                 } 
/*      */               } 
/*  250 */               AndroidStatusHologram.remove(b);
/*      */             } 
/*      */             
/*  253 */             return allow;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   protected void tick(Block b) {
/*      */     try {
/*  261 */       if (!(b.getState() instanceof Skull)) {
/*      */         return;
/*      */       }
/*  264 */     } catch (NullPointerException x) {
/*      */       return;
/*      */     } 
/*      */     
/*  268 */     if (BlockStorage.getLocationInfo(b.getLocation(), "paused").equals("false")) {
/*  269 */       float fuel = Float.parseFloat(BlockStorage.getLocationInfo(b.getLocation(), "fuel"));
/*  270 */       if (fuel == 0.0F) {
/*  271 */         ItemStack item = BlockStorage.getInventory(b).getItemInSlot(43);
/*  272 */         if (item != null) {
/*  273 */           for (MachineFuel recipe : this.recipes) {
/*  274 */             if (SlimefunManager.isItemSimiliar(item, recipe.getInput(), true)) {
/*      */               
/*  276 */               BlockStorage.getInventory(b).replaceExistingItem(43, 
/*  277 */                   InvUtils.decreaseItem(item, 1));
/*  278 */               if (getTier() == 2) {
/*  279 */                 pushItems(b, new ItemStack[] { new ItemStack(Material.BUCKET) });
/*      */               }
/*  281 */               BlockStorage.addBlockInfo(b, "fuel", String.valueOf(
/*  282 */                     (int)(recipe.getTicks() * getFuelEfficiency())));
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } else {
/*  290 */         String[] script = BlockStorage.getLocationInfo(b.getLocation(), "script").split("-");
/*  291 */         int index = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "index")) + 1;
/*      */         
/*  293 */         if (index >= script.length) index = 0; 
/*  294 */         boolean refresh = true;
/*  295 */         BlockStorage.addBlockInfo(b, "fuel", String.valueOf(fuel - 1.0F));
/*  296 */         ScriptPart part = ScriptPart.valueOf(script[index]);
/*      */         
/*  298 */         if (getAndroidType().isType(part.getRequiredType())) {
/*  299 */           int rotIndex; Block block2; BlockFace blockFace2; Block block1; BlockFace blockFace1; Block block; BlockFace face; double damage; BlockFace dir; Block block3; Skull skull; switch (part) {
/*      */             
/*      */             case CROPS:
/*      */               try {
/*  303 */                 BlockFace blockFace = BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  304 */                 Block block4 = b.getRelative(BlockFace.DOWN);
/*  305 */                 move(b, blockFace, block4);
/*  306 */               } catch (Exception e) {
/*  307 */                 e.printStackTrace();
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case POTATO:
/*      */               try {
/*  314 */                 BlockFace blockFace = BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  315 */                 Block block4 = b.getRelative(blockFace);
/*  316 */                 move(b, blockFace, block4);
/*  317 */               } catch (Exception e) {
/*  318 */                 e.printStackTrace();
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case CARROT:
/*      */               try {
/*  325 */                 BlockFace blockFace = BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  326 */                 Block block4 = b.getRelative(BlockFace.UP);
/*  327 */                 move(b, blockFace, block4);
/*  328 */               } catch (Exception e) {
/*  329 */                 e.printStackTrace();
/*      */               } 
/*      */               break;
/*      */             
/*      */             case BEETROOT_BLOCK:
/*  334 */               BlockStorage.addBlockInfo(b, "index", String.valueOf(0));
/*      */               break;
/*      */             
/*      */             case COCOA:
/*  338 */               rotIndex = directions.indexOf(
/*  339 */                   BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "rotation"))) - 1;
/*  340 */               if (rotIndex < 0) rotIndex = directions.size() - 1; 
/*  341 */               dir = directions.get(rotIndex);
/*  342 */               skull = (Skull)b.getState();
/*  343 */               skull.setRotation(dir);
/*  344 */               skull.update(true, false);
/*  345 */               BlockStorage.addBlockInfo(b, "rotation", dir.toString());
/*      */               break;
/*      */             
/*      */             case NETHER_WARTS:
/*  349 */               rotIndex = directions.indexOf(
/*  350 */                   BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "rotation"))) + 1;
/*  351 */               if (rotIndex == directions.size()) rotIndex = 0; 
/*  352 */               dir = directions.get(rotIndex);
/*  353 */               skull = (Skull)b.getState();
/*  354 */               skull.setRotation(dir);
/*  355 */               skull.update(true, false);
/*  356 */               BlockStorage.addBlockInfo(b, "rotation", dir.toString());
/*      */               break;
/*      */             
/*      */             case null:
/*  360 */               block2 = b.getRelative(
/*  361 */                   BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "rotation")));
/*  362 */               mine(b, block2);
/*      */               break;
/*      */             
/*      */             case null:
/*  366 */               block2 = b.getRelative(BlockFace.UP);
/*  367 */               mine(b, block2);
/*      */               break;
/*      */             
/*      */             case null:
/*  371 */               block2 = b.getRelative(BlockFace.DOWN);
/*  372 */               mine(b, block2);
/*      */               break;
/*      */             
/*      */             case null:
/*  376 */               block2 = b.getRelative(BlockFace.DOWN);
/*  377 */               if (block2.getType().equals(Material.STATIONARY_WATER)) {
/*  378 */                 block2.getWorld().playSound(block2.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */ 
/*      */ 
/*      */                 
/*  382 */                 ItemStack drop = fish[CSCoreLib.randomizer().nextInt(fish.length)];
/*  383 */                 if (CSCoreLib.randomizer().nextInt(100) < 10 * getTier() && fits(b, new ItemStack[] { drop })) pushItems(b, new ItemStack[] { drop });
/*      */               
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case null:
/*  390 */               blockFace2 = BlockFace.valueOf(
/*  391 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  392 */               block3 = b.getRelative(blockFace2);
/*  393 */               movedig(b, blockFace2, block3);
/*      */               break;
/*      */             
/*      */             case null:
/*  397 */               blockFace2 = BlockFace.valueOf(
/*  398 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  399 */               block3 = b.getRelative(BlockFace.UP);
/*  400 */               movedig(b, blockFace2, block3);
/*      */               break;
/*      */             
/*      */             case null:
/*  404 */               blockFace2 = BlockFace.valueOf(
/*  405 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  406 */               block3 = b.getRelative(BlockFace.DOWN);
/*  407 */               movedig(b, blockFace2, block3);
/*      */               break;
/*      */             
/*      */             case null:
/*  411 */               blockFace2 = BlockFace.valueOf(
/*  412 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  413 */               block3 = b.getRelative(blockFace2);
/*  414 */               if (BlockStorage.check(block3, "ANDROID_INTERFACE_ITEMS") && block3
/*  415 */                 .getState() instanceof Dispenser) {
/*  416 */                 Dispenser d = (Dispenser)block3.getState();
/*  417 */                 for (int slot : getOutputSlots()) {
/*      */                   
/*  419 */                   ItemStack stack = BlockStorage.getInventory(b).getItemInSlot(slot);
/*  420 */                   if (stack != null) {
/*      */                     
/*  422 */                     Map<Integer, ItemStack> items = d.getInventory().addItem(new ItemStack[] { stack });
/*  423 */                     if (items.isEmpty()) {
/*  424 */                       BlockStorage.getInventory(b).replaceExistingItem(slot, null);
/*      */                     } else {
/*      */                       
/*  427 */                       Iterator<Map.Entry<Integer, ItemStack>> iterator = items.entrySet().iterator(); if (iterator.hasNext()) { Map.Entry<Integer, ItemStack> entry = iterator.next();
/*      */                         
/*  429 */                         BlockStorage.getInventory(b).replaceExistingItem(slot, entry.getValue()); }
/*      */                     
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case null:
/*  439 */               blockFace2 = BlockFace.valueOf(
/*  440 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  441 */               block3 = b.getRelative(blockFace2);
/*  442 */               if (BlockStorage.check(block3, "ANDROID_INTERFACE_FUEL") && block3
/*  443 */                 .getState() instanceof Dispenser) {
/*  444 */                 Dispenser d = (Dispenser)block3.getState();
/*  445 */                 for (int slot = 0; slot < 9; slot++) {
/*  446 */                   ItemStack item = d.getInventory().getItem(slot);
/*  447 */                   if (item != null) {
/*      */                     
/*  449 */                     if (BlockStorage.getInventory(b).getItemInSlot(43) == null) {
/*      */                       
/*  451 */                       BlockStorage.getInventory(b).replaceExistingItem(43, item);
/*  452 */                       d.getInventory().setItem(slot, null);
/*      */                       
/*      */                       break;
/*      */                     } 
/*      */                     
/*  457 */                     if (SlimefunManager.isItemSimiliar(item, BlockStorage.getInventory(b).getItemInSlot(43), true)) {
/*      */                       
/*  459 */                       int rest = item.getType().getMaxStackSize() - BlockStorage.getInventory(b).getItemInSlot(43).getAmount();
/*  460 */                       if (rest > 0) {
/*      */                         
/*  462 */                         int amt = (item.getAmount() > rest) ? rest : item.getAmount();
/*      */                         
/*  464 */                         BlockStorage.getInventory(b).replaceExistingItem(43, (ItemStack)new CustomItem(item, BlockStorage.getInventory(b).getItemInSlot(43).getAmount() + amt));
/*      */                         
/*  466 */                         d.getInventory()
/*  467 */                           .setItem(slot, InvUtils.decreaseItem(item, amt));
/*      */                       } 
/*      */                       break;
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */               break;
/*      */             
/*      */             case null:
/*  477 */               blockFace2 = BlockFace.valueOf(
/*  478 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  479 */               block3 = b.getRelative(blockFace2);
/*  480 */               farm(b, block3);
/*      */               break;
/*      */             
/*      */             case null:
/*  484 */               block1 = b.getRelative(BlockFace.DOWN);
/*  485 */               farm(b, block1);
/*      */               break;
/*      */             
/*      */             case null:
/*  489 */               blockFace1 = BlockFace.valueOf(
/*  490 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  491 */               block3 = b.getRelative(blockFace1);
/*  492 */               exoticFarm(b, block3);
/*      */               break;
/*      */             
/*      */             case null:
/*  496 */               block = b.getRelative(BlockFace.DOWN);
/*  497 */               exoticFarm(b, block);
/*      */               break;
/*      */             
/*      */             case null:
/*  501 */               face = BlockFace.valueOf(
/*  502 */                   BlockStorage.getLocationInfo(b.getLocation(), "rotation"));
/*  503 */               block3 = b.getRelative(face);
/*  504 */               if (block3.getType().equals(Material.LOG) || block3.getType()
/*  505 */                 .equals(Material.LOG_2)) {
/*  506 */                 List<Location> list = new ArrayList<>();
/*  507 */                 list.add(block3.getLocation());
/*  508 */                 TreeCalculator.getTree(block3.getLocation(), block3.getLocation(), list);
/*      */                 
/*  510 */                 if (!list.isEmpty()) {
/*  511 */                   refresh = false;
/*  512 */                   Block log = ((Location)list.get(list.size() - 1)).getBlock();
/*  513 */                   Collection<ItemStack> drops = log.getDrops();
/*  514 */                   log.getWorld().playEffect(log.getLocation(), Effect.STEP_SOUND, log
/*  515 */                       .getType());
/*  516 */                   if (!drops.isEmpty() && 
/*  517 */                     CSCoreLib.getLib().getProtectionManager().canBuild(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), log)) {
/*  518 */                     ItemStack[] items = drops.<ItemStack>toArray(
/*  519 */                         new ItemStack[drops.size()]);
/*  520 */                     if (fits(b, items)) {
/*  521 */                       pushItems(b, items);
/*  522 */                       log.getWorld()
/*  523 */                         .playEffect(log.getLocation(), Effect.STEP_SOUND, log.getType());
/*  524 */                       if (log.getY() == block3.getY()) {
/*  525 */                         byte data = log.getData();
/*  526 */                         if (log.getType() == Material.LOG_2)
/*  527 */                           data = (byte)(data + 4); 
/*  528 */                         log.setType(Material.SAPLING);
/*  529 */                         log.setData(data); break;
/*      */                       } 
/*  531 */                       log.setType(Material.AIR);
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case null:
/*  540 */               damage = (getTier() < 2) ? 20.0D : (4.0D * getTier());
/*      */ 
/*      */               
/*  543 */               for (Entity n : AndroidStatusHologram.getNearbyEntities(b, 4.0D + 
/*  544 */                   getTier())) {
/*  545 */                 switch (BlockFace.valueOf(
/*  546 */                     BlockStorage.getLocationInfo(b.getLocation(), "rotation"))) {
/*      */                   case CROPS:
/*  548 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  549 */                       .getLocation().getZ() < b.getZ()) {
/*  550 */                       if (n.hasMetadata("android_killer"))
/*  551 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  552 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  555 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case POTATO:
/*  561 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  562 */                       .getLocation().getX() > b.getX()) {
/*  563 */                       if (n.hasMetadata("android_killer"))
/*  564 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  565 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  568 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case CARROT:
/*  574 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  575 */                       .getLocation().getZ() > b.getZ()) {
/*  576 */                       if (n.hasMetadata("android_killer"))
/*  577 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  578 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  581 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case BEETROOT_BLOCK:
/*  587 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  588 */                       .getLocation().getX() < b.getX()) {
/*  589 */                       if (n.hasMetadata("android_killer"))
/*  590 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  591 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  594 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */                 } 
/*      */ 
/*      */ 
/*      */               
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case null:
/*  606 */               damage = (getTier() < 2) ? 20.0D : (4.0D * getTier());
/*      */ 
/*      */               
/*  609 */               for (Entity n : AndroidStatusHologram.getNearbyEntities(b, 4.0D + 
/*  610 */                   getTier())) {
/*  611 */                 if (n instanceof org.bukkit.entity.Animals)
/*  612 */                   continue;  switch (BlockFace.valueOf(
/*  613 */                     BlockStorage.getLocationInfo(b.getLocation(), "rotation"))) {
/*      */                   case CROPS:
/*  615 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  616 */                       .getLocation().getZ() < b.getZ()) {
/*  617 */                       if (n.hasMetadata("android_killer"))
/*  618 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  619 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  622 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case POTATO:
/*  628 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  629 */                       .getLocation().getX() > b.getX()) {
/*  630 */                       if (n.hasMetadata("android_killer"))
/*  631 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  632 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  635 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case CARROT:
/*  641 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  642 */                       .getLocation().getZ() > b.getZ()) {
/*  643 */                       if (n.hasMetadata("android_killer"))
/*  644 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  645 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  648 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case BEETROOT_BLOCK:
/*  654 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  655 */                       .getLocation().getX() < b.getX()) {
/*  656 */                       if (n.hasMetadata("android_killer"))
/*  657 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  658 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  661 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */                 } 
/*      */ 
/*      */ 
/*      */               
/*      */               } 
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case null:
/*  674 */               damage = (getTier() < 2) ? 20.0D : (4.0D * getTier());
/*      */ 
/*      */               
/*  677 */               for (Entity n : AndroidStatusHologram.getNearbyEntities(b, 4.0D + 
/*  678 */                   getTier())) {
/*  679 */                 if (n instanceof org.bukkit.entity.Monster)
/*  680 */                   continue;  switch (BlockFace.valueOf(
/*  681 */                     BlockStorage.getLocationInfo(b.getLocation(), "rotation"))) {
/*      */                   case CROPS:
/*  683 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  684 */                       .getLocation().getZ() < b.getZ()) {
/*  685 */                       if (n.hasMetadata("android_killer"))
/*  686 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  687 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  690 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case POTATO:
/*  696 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  697 */                       .getLocation().getX() > b.getX()) {
/*  698 */                       if (n.hasMetadata("android_killer"))
/*  699 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  700 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  703 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case CARROT:
/*  709 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  710 */                       .getLocation().getZ() > b.getZ()) {
/*  711 */                       if (n.hasMetadata("android_killer"))
/*  712 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  713 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  716 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case BEETROOT_BLOCK:
/*  722 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  723 */                       .getLocation().getX() < b.getX()) {
/*  724 */                       if (n.hasMetadata("android_killer"))
/*  725 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  726 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  729 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */                 } 
/*      */ 
/*      */ 
/*      */               
/*      */               } 
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case null:
/*  742 */               damage = (getTier() < 2) ? 20.0D : (4.0D * getTier());
/*      */ 
/*      */               
/*  745 */               for (Entity n : AndroidStatusHologram.getNearbyEntities(b, 4.0D + 
/*  746 */                   getTier())) {
/*  747 */                 if (n instanceof org.bukkit.entity.Monster || (
/*  748 */                   n instanceof Ageable && !((Ageable)n).isAdult()))
/*      */                   continue; 
/*  750 */                 switch (BlockFace.valueOf(
/*  751 */                     BlockStorage.getLocationInfo(b.getLocation(), "rotation"))) {
/*      */                   case CROPS:
/*  753 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  754 */                       .getLocation().getZ() < b.getZ()) {
/*  755 */                       if (n.hasMetadata("android_killer"))
/*  756 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  757 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  760 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case POTATO:
/*  766 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  767 */                       .getLocation().getX() > b.getX()) {
/*  768 */                       if (n.hasMetadata("android_killer"))
/*  769 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  770 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  773 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case CARROT:
/*  779 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  780 */                       .getLocation().getZ() > b.getZ()) {
/*  781 */                       if (n.hasMetadata("android_killer"))
/*  782 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  783 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  786 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */ 
/*      */                   
/*      */                   case BEETROOT_BLOCK:
/*  792 */                     if (n instanceof LivingEntity && !(n instanceof org.bukkit.entity.ArmorStand) && !(n instanceof Player) && n
/*  793 */                       .getLocation().getX() < b.getX()) {
/*  794 */                       if (n.hasMetadata("android_killer"))
/*  795 */                         n.removeMetadata("android_killer", (Plugin)SlimefunStartup.instance); 
/*  796 */                       n.setMetadata("android_killer", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, new AndroidObject(this, b)));
/*      */ 
/*      */                       
/*  799 */                       ((LivingEntity)n).damage(damage);
/*      */                       break;
/*      */                     } 
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*  814 */         if (refresh) BlockStorage.addBlockInfo(b, "index", String.valueOf(index));
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void move(Block b, BlockFace face, Block block) throws Exception {
/*  821 */     if (block.getY() < 0 || block.getY() > block.getWorld().getMaxHeight())
/*      */       return; 
/*  823 */     if (block.getType() == Material.AIR) {
/*  824 */       block.setType(Material.SKULL);
/*  825 */       block.setData((byte)1);
/*      */       
/*  827 */       Skull skull = (Skull)block.getState();
/*  828 */       skull.setRotation(face);
/*  829 */       skull.update(true, false);
/*  830 */       CustomSkull.setSkull(block, CustomSkull.getTexture(getItem()));
/*  831 */       b.setType(Material.AIR);
/*  832 */       BlockStorage.moveBlockInfo(b.getLocation(), block.getLocation());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mine(Block b, Block block) {
/*  837 */     Collection<ItemStack> drops = block.getDrops();
/*  838 */     UUID owner = UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner"));
/*  839 */     if (!blockblacklist.contains(block.getType()) && !drops.isEmpty() && 
/*  840 */       CSCoreLib.getLib().getProtectionManager().canBuild(owner, block) && 
/*  841 */       ProtectionUtils.canBuild(Bukkit.getPlayer(owner), block, false)) {
/*      */       
/*  843 */       SlimefunItem item = BlockStorage.check(block);
/*  844 */       if (item != null) {
/*      */         
/*  846 */         if (item.getID().equals("ANCIENT_PEDESTAL")) {
/*      */           return;
/*      */         }
/*  849 */         if (fits(b, new ItemStack[] { item.getItem()
/*  850 */             }) && SlimefunItem.blockhandler.containsKey(item.getID()) && (
/*  851 */           (SlimefunBlockHandler)SlimefunItem.blockhandler.get(item.getID())).onBreak(null, block, item, UnregisterReason.ANDROID_DIG))
/*      */         {
/*  853 */           pushItems(b, new ItemStack[] { BlockStorage.retrieve(block) });
/*  854 */           if (SlimefunItem.blockhandler.containsKey(item.getID()))
/*  855 */             ((SlimefunBlockHandler)SlimefunItem.blockhandler.get(item.getID())).onBreak(null, block, item, UnregisterReason.ANDROID_DIG); 
/*  856 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block
/*  857 */               .getType());
/*  858 */           block.setType(Material.AIR);
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  864 */         ItemStack[] items = drops.<ItemStack>toArray(new ItemStack[drops.size()]);
/*  865 */         if (fits(b, items)) {
/*  866 */           pushItems(b, items);
/*  867 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block
/*  868 */               .getType());
/*  869 */           block.setType(Material.AIR);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void movedig(Block b, BlockFace face, Block block) {
/*  877 */     Collection<ItemStack> drops = block.getDrops();
/*  878 */     UUID owner = UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner"));
/*  879 */     if (!blockblacklist.contains(block.getType()) && !drops.isEmpty() && 
/*  880 */       CSCoreLib.getLib().getProtectionManager().canBuild(owner, block) && 
/*  881 */       ProtectionUtils.canBuild(Bukkit.getPlayer(owner), block, false)) {
/*      */       
/*      */       try {
/*  884 */         SlimefunItem item = BlockStorage.check(block);
/*  885 */         if (item != null) {
/*      */           
/*  887 */           if (item.getID().equals("ANCIENT_PEDESTAL")) {
/*      */             return;
/*      */           }
/*  890 */           if (fits(b, new ItemStack[] { item.getItem()
/*  891 */               }) && SlimefunItem.blockhandler.containsKey(item.getID()) && (
/*  892 */             (SlimefunBlockHandler)SlimefunItem.blockhandler.get(item.getID()))
/*  893 */             .onBreak(null, block, item, UnregisterReason.ANDROID_DIG)) {
/*  894 */             pushItems(b, new ItemStack[] { BlockStorage.retrieve(block) });
/*      */             
/*  896 */             block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block
/*  897 */                 .getType());
/*      */             
/*  899 */             block.setType(Material.SKULL);
/*  900 */             block.setData((byte)1);
/*      */             
/*  902 */             Skull skull = (Skull)block.getState();
/*  903 */             skull.setRotation(face);
/*  904 */             skull.update(true, false);
/*  905 */             CustomSkull.setSkull(block, 
/*  906 */                 CustomSkull.getTexture(getItem()));
/*  907 */             b.setType(Material.AIR);
/*  908 */             BlockStorage.moveBlockInfo(b.getLocation(), block
/*  909 */                 .getLocation());
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  915 */           ItemStack[] items = drops.<ItemStack>toArray(new ItemStack[drops.size()]);
/*  916 */           if (fits(b, items)) {
/*  917 */             pushItems(b, items);
/*  918 */             block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block
/*  919 */                 .getType());
/*  920 */             block.setType(Material.SKULL);
/*  921 */             block.setData((byte)1);
/*      */             
/*  923 */             Skull skull = (Skull)block.getState();
/*  924 */             skull.setRotation(face);
/*  925 */             skull.update(true, false);
/*  926 */             CustomSkull.setSkull(block, CustomSkull.getTexture(getItem()));
/*  927 */             b.setType(Material.AIR);
/*  928 */             BlockStorage.moveBlockInfo(b.getLocation(), block.getLocation());
/*      */           } 
/*      */         } 
/*  931 */       } catch (Exception x) {
/*  932 */         x.printStackTrace();
/*      */       } 
/*      */     } else {
/*      */       
/*      */       try {
/*  937 */         move(b, face, block);
/*  938 */       } catch (Exception e) {
/*  939 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void farm(Block b, Block block) {
/*      */     ItemStack drop;
/*  946 */     switch (block.getType()) {
/*      */       
/*      */       case CROPS:
/*  949 */         drop = new ItemStack(Material.WHEAT, CSCoreLib.randomizer().nextInt(3) + 1);
/*  950 */         if (block.getData() >= 7 && fits(b, new ItemStack[] { drop })) {
/*  951 */           pushItems(b, new ItemStack[] { drop });
/*  952 */           block.setData((byte)0);
/*  953 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case POTATO:
/*  960 */         drop = new ItemStack(Material.POTATO_ITEM, CSCoreLib.randomizer().nextInt(3) + 1);
/*  961 */         if (block.getData() >= 7 && fits(b, new ItemStack[] { drop })) {
/*  962 */           pushItems(b, new ItemStack[] { drop });
/*  963 */           block.setData((byte)0);
/*  964 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case CARROT:
/*  971 */         drop = new ItemStack(Material.CARROT_ITEM, CSCoreLib.randomizer().nextInt(3) + 1);
/*  972 */         if (block.getData() >= 7 && fits(b, new ItemStack[] { drop })) {
/*  973 */           pushItems(b, new ItemStack[] { drop });
/*  974 */           block.setData((byte)0);
/*  975 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case BEETROOT_BLOCK:
/*  982 */         drop = new ItemStack(Material.BEETROOT, CSCoreLib.randomizer().nextInt(3) + 1);
/*  983 */         if (block.getData() >= 3 && fits(b, new ItemStack[] { drop })) {
/*  984 */           pushItems(b, new ItemStack[] { drop });
/*  985 */           block.setData((byte)0);
/*  986 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case COCOA:
/*  994 */         drop = (new MaterialData(Material.INK_SACK, (byte)3)).toItemStack(CSCoreLib.randomizer().nextInt(3) + 1);
/*  995 */         if (block.getData() >= 8 && fits(b, new ItemStack[] { drop })) {
/*  996 */           pushItems(b, new ItemStack[] { drop });
/*  997 */           block.setData((byte)(block.getData() - 8));
/*  998 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case NETHER_WARTS:
/* 1005 */         drop = new ItemStack(Material.NETHER_STALK, CSCoreLib.randomizer().nextInt(3) + 1);
/* 1006 */         if (block.getData() >= 3 && fits(b, new ItemStack[] { drop })) {
/* 1007 */           pushItems(b, new ItemStack[] { drop });
/* 1008 */           block.setData((byte)0);
/* 1009 */           block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void exoticFarm(Block b, Block block) {
/* 1020 */     farm(b, block);
/* 1021 */     if (SlimefunStartup.instance.isExoticGardenInstalled()) {
/* 1022 */       ItemStack drop = ExoticGarden.harvestPlant(block);
/* 1023 */       if (drop != null && fits(b, new ItemStack[] { drop })) {
/* 1024 */         pushItems(b, new ItemStack[] { drop });
/* 1025 */         block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void constructMenu(BlockMenuPreset preset) throws Exception {
/* 1032 */     for (int i : border) {
/* 1033 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), "", new String[0]), new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/* 1038 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */     
/* 1043 */     for (int i : border_out) {
/* 1044 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), "", new String[0]), new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/* 1049 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */ 
/*      */     
/* 1055 */     for (int i : getOutputSlots()) {
/* 1056 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*      */             {
/* 1060 */               return false;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 1066 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*      */             }
/*      */           });
/*      */     } 
/*      */ 
/*      */     
/* 1072 */     if (getTier() == 1) {
/* 1073 */       preset.addItem(34, (ItemStack)new CustomItem(
/* 1074 */             CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&8⇩ &c燃料输入槽 &8⇩", new String[] { "", "&r需要固体燃料", "&r例如: 煤炭, 木炭等..." }), new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             
/*      */             public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */             {
/* 1079 */               return false;
/*      */             }
/*      */           });
/*      */     }
/* 1083 */     else if (getTier() == 2) {
/* 1084 */       preset.addItem(34, (ItemStack)new CustomItem(
/* 1085 */             CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&8⇩ &c燃料输入槽 &8⇩", new String[] { "", "&r需要液体燃料\", \"&r例如: 岩浆, 石油等..." }), new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             
/*      */             public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */             {
/* 1090 */               return false;
/*      */             }
/*      */           });
/*      */     } else {
/*      */       
/* 1095 */       preset.addItem(34, (ItemStack)new CustomItem(
/* 1096 */             CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&8⇩ &c燃料输入槽 &8⇩", new String[] { "", "&r需要放射性燃料", "&r例如: 铀, 镎, 活性铀" }), new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             
/*      */             public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */             {
/* 1101 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */   
/*      */   public void openScriptEditor(Player p, final Block b) throws Exception {
/* 1108 */     ChestMenu menu = new ChestMenu("&e脚本编辑器");
/*      */     
/* 1110 */     menu.addItem(2, (ItemStack)new CustomItem(
/* 1111 */           CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDliZjZkYjRhZWRhOWQ4ODIyYjlmNzM2NTM4ZThjMThiOWE0ODQ0Zjg0ZWI0NTUwNGFkZmJmZWU4N2ViIn19fQ=="), "&2> 编辑脚本", new String[] { "", "&a编辑当前脚本" }), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */           {
/*      */             try {
/* 1117 */               ProgrammableAndroid.this.openScript(p, b, BlockStorage.getLocationInfo(b.getLocation(), "script"));
/* 1118 */             } catch (Exception e) {
/* 1119 */               e.printStackTrace();
/*      */             } 
/* 1121 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1125 */     menu.addItem(4, (ItemStack)new CustomItem(
/* 1126 */           CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTcxZDg5NzljMTg3OGEwNTk4N2E3ZmFmMjFiNTZkMWI3NDRmOWQwNjhjNzRjZmZjZGUxZWExZWRhZDU4NTIifX19"), "&4> 创建新脚本", new String[] { "", "&c删除当前脚本", "&c并创建一个空白脚本" }), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */           {
/*      */             try {
/* 1132 */               ProgrammableAndroid.this.openScript(p, b, "START-TURN_LEFT-REPEAT");
/* 1133 */             } catch (Exception e) {
/* 1134 */               e.printStackTrace();
/*      */             } 
/* 1136 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1140 */     menu.addItem(6, (ItemStack)new CustomItem(
/* 1141 */           CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAxNTg2ZTM5ZjZmZmE2M2I0ZmIzMDFiNjVjYTdkYThhOTJmNzM1M2FhYWI4OWQzODg2NTc5MTI1ZGZiYWY5In19fQ=="), "&6> 下载脚本", new String[] { "", "&e从云端下载一个脚本", "&e你可以对下载的脚本进行编辑或直接使用" }), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */           {
/*      */             try {
/* 1147 */               ProgrammableAndroid.this.openScriptDownloader(p, b, 1);
/* 1148 */             } catch (Exception e) {
/* 1149 */               e.printStackTrace();
/*      */             } 
/* 1151 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1155 */     menu.open(new Player[] { p });
/*      */   }
/*      */   
/*      */   public void openScript(final Player p, final Block b, final String script) throws Exception {
/* 1159 */     ChestMenu menu = new ChestMenu("&e脚本编辑器");
/* 1160 */     final String[] commands = script.split("-");
/*      */     
/* 1162 */     menu.addItem(0, ScriptPart.START.toItemStack());
/* 1163 */     menu.addMenuClickHandler(0, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1167 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1171 */     for (int i = 1; i < commands.length; i++) {
/* 1172 */       final int index = i;
/* 1173 */       if (i == commands.length - 1) {
/* 1174 */         int additional = (commands.length == 54) ? 0 : 1;
/*      */         
/* 1176 */         if (additional == 1) {
/* 1177 */           menu.addItem(i, (ItemStack)new CustomItem(
/* 1178 */                 CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTcxZDg5NzljMTg3OGEwNTk4N2E3ZmFmMjFiNTZkMWI3NDRmOWQwNjhjNzRjZmZjZGUxZWExZWRhZDU4NTIifX19"), "&7> 添加新命令"));
/* 1179 */           menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */               {
/*      */                 
/*      */                 public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */                 {
/*      */                   try {
/* 1185 */                     ProgrammableAndroid.this.openScriptComponentEditor(p, b, script, index);
/* 1186 */                   } catch (Exception e) {
/* 1187 */                     e.printStackTrace();
/*      */                   } 
/* 1189 */                   return false;
/*      */                 }
/*      */               });
/*      */         } 
/*      */         
/* 1194 */         menu.addItem(i + additional, ScriptPart.REPEAT.toItemStack());
/* 1195 */         menu.addMenuClickHandler(i + additional, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */               {
/* 1200 */                 return false;
/*      */               }
/*      */             });
/*      */       } else {
/*      */         
/* 1205 */         ItemStack stack = ScriptPart.valueOf(commands[i]).toItemStack();
/* 1206 */         menu.addItem(i, (ItemStack)new CustomItem(stack, stack.getItemMeta().getDisplayName(), new String[] { "", "&7⇨ &e左键点击 &e进行编辑", "&7⇨ &e右键点击 &7进行删除", "&7⇨ &eShift + 右键点击 &7复制" }));
/*      */         
/* 1208 */         menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */             {
/*      */               
/*      */               public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction action)
/*      */               {
/* 1213 */                 if (action.isRightClicked() && action.isShiftClicked()) {
/* 1214 */                   if (commands.length == 54) return false;
/*      */                   
/* 1216 */                   int i = 0;
/* 1217 */                   StringBuilder builder = new StringBuilder("START-");
/* 1218 */                   for (String command : commands) {
/* 1219 */                     if (i > 0)
/* 1220 */                       if (i == index) {
/* 1221 */                         builder.append(commands[i] + "-");
/* 1222 */                         builder.append(commands[i] + "-");
/*      */                       }
/* 1224 */                       else if (i < commands.length - 1) {
/* 1225 */                         builder.append(command + "-");
/*      */                       }  
/* 1227 */                     i++;
/*      */                   } 
/* 1229 */                   builder.append("REPEAT");
/* 1230 */                   BlockStorage.addBlockInfo(b, "script", builder.toString());
/*      */                   
/*      */                   try {
/* 1233 */                     ProgrammableAndroid.this.openScript(p, b, builder.toString());
/* 1234 */                   } catch (Exception e) {
/* 1235 */                     e.printStackTrace();
/*      */                   }
/*      */                 
/* 1238 */                 } else if (action.isRightClicked()) {
/* 1239 */                   int i = 0;
/* 1240 */                   StringBuilder builder = new StringBuilder("START-");
/* 1241 */                   for (String command : commands) {
/* 1242 */                     if (i != index && i > 0 && i < commands.length - 1)
/* 1243 */                       builder.append(command + "-"); 
/* 1244 */                     i++;
/*      */                   } 
/* 1246 */                   builder.append("REPEAT");
/* 1247 */                   BlockStorage.addBlockInfo(b, "script", builder.toString());
/*      */                   try {
/* 1249 */                     ProgrammableAndroid.this.openScript(p, b, builder.toString());
/* 1250 */                   } catch (Exception e) {
/* 1251 */                     e.printStackTrace();
/*      */                   } 
/*      */                 } else {
/*      */                   
/*      */                   try {
/* 1256 */                     ProgrammableAndroid.this.openScriptComponentEditor(p, b, script, index);
/* 1257 */                   } catch (Exception e) {
/* 1258 */                     e.printStackTrace();
/*      */                   } 
/*      */                 } 
/* 1261 */                 return false;
/*      */               }
/*      */             });
/*      */       } 
/*      */     } 
/*      */     
/* 1267 */     menu.open(new Player[] { p });
/*      */   }
/*      */ 
/*      */   
/*      */   private void openScriptDownloader(final Player p, final Block b, final int page) throws Exception {
/* 1272 */     ChestMenu menu = new ChestMenu("远古工艺向导");
/*      */     
/* 1274 */     menu.addMenuOpeningHandler(new ChestMenu.MenuOpeningHandler()
/*      */         {
/*      */           public void onOpen(Player p)
/*      */           {
/* 1278 */             p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HAT, 0.7F, 0.7F);
/*      */           }
/*      */         });
/*      */     
/* 1282 */     List<Config> scripts = getUploadedScripts();
/*      */     
/* 1284 */     int index = 0;
/* 1285 */     final int pages = scripts.size() / 45 + 1;
/*      */     
/* 1287 */     for (int i = 45; i < 54; i++) {
/* 1288 */       menu.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), "", new String[0]));
/* 1289 */       menu.addMenuClickHandler(i, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/* 1293 */               return false;
/*      */             }
/*      */           });
/*      */     } 
/*      */     
/* 1298 */     menu.addItem(46, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), "&r⇦上一页", new String[] { "", "&7(" + page + " / " + pages + ")" }));
/* 1299 */     menu.addMenuClickHandler(46, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1303 */             int next = page - 1;
/* 1304 */             if (next < 1) next = pages; 
/* 1305 */             if (next != page) {
/*      */               try {
/* 1307 */                 ProgrammableAndroid.this.openScriptDownloader(p, b, next);
/* 1308 */               } catch (Exception e) {
/* 1309 */                 e.printStackTrace();
/*      */               } 
/*      */             }
/* 1312 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1316 */     menu.addItem(49, (ItemStack)new CustomItem(
/* 1317 */           CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTA1YTJjYWI4YjY4ZWE1N2UzYWY5OTJhMzZlNDdjOGZmOWFhODdjYzg3NzYyODE5NjZmOGMzY2YzMWEzOCJ9fX0="), "&e上传脚本", new String[] { "", "&6点击 &7上传你机器人的脚本至云端", "&7分享给他人使用" }));
/* 1318 */     menu.addMenuClickHandler(49, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1322 */             final String code = BlockStorage.getLocationInfo(b.getLocation(), "script");
/* 1323 */             int num = 1;
/*      */             
/* 1325 */             for (Config script : ProgrammableAndroid.this.getUploadedScripts()) {
/* 1326 */               if (script.getString("author").equals(p.getUniqueId().toString())) num++; 
/* 1327 */               if (script.getString("code").equals(code)) {
/* 1328 */                 Messages.local.sendTranslation((CommandSender)p, "android.scripts.already-uploaded", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 1329 */                 return false;
/*      */               } 
/*      */             } 
/*      */             
/* 1333 */             final int id = num;
/*      */             
/* 1335 */             p.closeInventory();
/* 1336 */             Messages.local.sendTranslation((CommandSender)p, "android.scripts.enter-name", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */             
/* 1338 */             MenuHelper.awaitChatInput(p, new MenuHelper.ChatHandler()
/*      */                 {
/*      */                   
/*      */                   public boolean onChat(Player p, String message)
/*      */                   {
/* 1343 */                     Config script = new Config("plugins/Slimefun/scripts/" + ProgrammableAndroid.this.getAndroidType().toString() + "/" + p.getName() + " " + id + ".sfs");
/*      */                     
/* 1345 */                     script.setValue("author", p.getUniqueId().toString());
/* 1346 */                     script.setValue("author_name", p.getName());
/* 1347 */                     script.setValue("name", 
/* 1348 */                         ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', message)));
/* 1349 */                     script.setValue("code", code);
/* 1350 */                     script.setValue("downloads", Integer.valueOf(0));
/* 1351 */                     script.setValue("android", ProgrammableAndroid.this.getAndroidType().toString());
/* 1352 */                     script.setValue("rating.positive", new ArrayList());
/* 1353 */                     script.setValue("rating.negative", new ArrayList());
/* 1354 */                     script.save();
/*      */                     
/*      */                     try {
/* 1357 */                       Messages.local.sendTranslation((CommandSender)p, "android.uploaded", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                       
/* 1359 */                       ProgrammableAndroid.this.openScriptDownloader(p, b, page);
/* 1360 */                     } catch (Exception e) {
/* 1361 */                       e.printStackTrace();
/*      */                     } 
/*      */                     
/* 1364 */                     return false;
/*      */                   }
/*      */                 });
/* 1367 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1371 */     menu.addItem(52, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), "&r下一页⇨", new String[] { "", "&7(" + page + " / " + pages + ")" }));
/* 1372 */     menu.addMenuClickHandler(52, new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1376 */             int next = page + 1;
/* 1377 */             if (next > pages) next = 1; 
/* 1378 */             if (next != page) {
/*      */               try {
/* 1380 */                 ProgrammableAndroid.this.openScriptDownloader(p, b, next);
/* 1381 */               } catch (Exception e) {
/* 1382 */                 e.printStackTrace();
/*      */               } 
/*      */             }
/* 1385 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1389 */     int category_index = 45 * (page - 1);
/* 1390 */     for (int j = 0; j < 45; j++) {
/* 1391 */       int target = category_index + j;
/* 1392 */       if (target >= scripts.size())
/*      */         break; 
/* 1394 */       final Config script = scripts.get(target);
/*      */       
/* 1396 */       OfflinePlayer op = Bukkit.getOfflinePlayer(script.getUUID("author"));
/*      */       
/* 1398 */       String author = (op != null && op.getName() != null) ? op.getName() : script.getString("author_name");
/*      */       
/* 1400 */       if (script.getString("author").equals(p.getUniqueId().toString())) {
/* 1401 */         menu.addItem(index, (ItemStack)new CustomItem(getItem(), "&b" + script
/* 1402 */               .getString("name"), new String[] { "&7by &r" + author, "", "&7Downloads: &r" + script.getInt("downloads"), "&7Rating: " + 
/* 1403 */                 getScriptRatingPercentage(script), "&a" + getScriptRating(script, true) + " ☺ &7- &4☹ " + 
/* 1404 */                 getScriptRating(script, false), "", "&e左键点击 &r下载这个脚本", "&4(这将覆盖你当前的脚本)" }));
/*      */       } else {
/*      */         
/* 1407 */         menu.addItem(index, (ItemStack)new CustomItem(getItem(), "&b" + script
/* 1408 */               .getString("name"), new String[] { "&7by &r" + author, "", "&7Downloads: &r" + script.getInt("downloads"), "&7Rating: " + 
/* 1409 */                 getScriptRatingPercentage(script), "&a" + getScriptRating(script, true) + " ☺ &7- &4☹ " + 
/* 1410 */                 getScriptRating(script, false), "", "&e左键点击 &r下载这个脚本", "&4(这将覆盖你当前的脚本)", "&eShift + 左键点击 &r给脚本点赞", "&eShift + 右键点击 &r差评这个脚本" }));
/*      */       } 
/*      */       
/* 1413 */       menu.addMenuClickHandler(index, new ChestMenu.MenuClickHandler()
/*      */           {
/*      */             
/*      */             public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*      */             {
/* 1418 */               Config script2 = new Config(script.getFile());
/*      */               
/* 1420 */               if (action.isShiftClicked()) {
/* 1421 */                 if (script2.getString("author").equals(p
/* 1422 */                     .getUniqueId().toString())) {
/* 1423 */                   Messages.local.sendTranslation((CommandSender)p, "android.scripts.rating.own", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 
/*      */                 }
/* 1426 */                 else if (action.isRightClicked()) {
/*      */                   
/* 1428 */                   if (!script2.getStringList("rating.negative").contains(p.getUniqueId().toString()) && 
/* 1429 */                     !script2.getStringList("rating.positive").contains(p.getUniqueId().toString())) {
/*      */                     
/* 1431 */                     List<String> list = script2.getStringList("rating.negative");
/* 1432 */                     list.add(p.getUniqueId().toString());
/*      */                     
/* 1434 */                     script2.setValue("rating.negative", list);
/* 1435 */                     script2.save();
/*      */                     
/*      */                     try {
/* 1438 */                       ProgrammableAndroid.this.openScriptDownloader(p, b, page);
/* 1439 */                     } catch (Exception e) {
/* 1440 */                       e.printStackTrace();
/*      */                     } 
/*      */                   } else {
/*      */                     
/* 1444 */                     Messages.local.sendTranslation((CommandSender)p, "android.scripts.rating.already", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                   
/*      */                   }
/*      */ 
/*      */                 
/*      */                 }
/* 1450 */                 else if (!script2.getStringList("rating.negative").contains(p.getUniqueId().toString()) && 
/* 1451 */                   !script2.getStringList("rating.positive").contains(p.getUniqueId().toString())) {
/*      */                   
/* 1453 */                   List<String> list = script2.getStringList("rating.positive");
/* 1454 */                   list.add(p.getUniqueId().toString());
/*      */                   
/* 1456 */                   script2.setValue("rating.positive", list);
/* 1457 */                   script2.save();
/*      */                   
/*      */                   try {
/* 1460 */                     ProgrammableAndroid.this.openScriptDownloader(p, b, page);
/* 1461 */                   } catch (Exception e) {
/* 1462 */                     e.printStackTrace();
/*      */                   } 
/*      */                 } else {
/*      */                   
/* 1466 */                   Messages.local.sendTranslation((CommandSender)p, "android.scripts.rating.already", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/* 1471 */               else if (!action.isRightClicked()) {
/*      */                 try {
/* 1473 */                   script2.setValue("downloads", Integer.valueOf(script2
/* 1474 */                         .getInt("downloads") + 1));
/* 1475 */                   script2.save();
/*      */                   
/* 1477 */                   BlockStorage.addBlockInfo(b, "script", script2
/* 1478 */                       .getString("code"));
/* 1479 */                   ProgrammableAndroid.this.openScriptEditor(p, b);
/* 1480 */                 } catch (Exception e) {
/* 1481 */                   e.printStackTrace();
/*      */                 } 
/*      */               } 
/* 1484 */               return false;
/*      */             }
/*      */           });
/*      */       
/* 1488 */       index++;
/*      */     } 
/*      */ 
/*      */     
/* 1492 */     menu.open(new Player[] { p });
/*      */   }
/*      */   
/*      */   public float getScriptRating(Config script) {
/* 1496 */     return Math.round((getScriptRating(script, true) * 100.0F / getScriptRating(script, true) + 
/* 1497 */         getScriptRating(script, false)) * 100.0F) / 100.0F;
/*      */   }
/*      */   
/*      */   private int getScriptRating(Config script, boolean positive) {
/* 1501 */     if (positive) return script.getStringList("rating.positive").size(); 
/* 1502 */     return script.getStringList("rating.negative").size();
/*      */   }
/*      */   
/*      */   private String getScriptRatingPercentage(Config script) {
/* 1506 */     String progress = String.valueOf(getScriptRating(script));
/* 1507 */     if (Float.parseFloat(progress) < 16.0F) { progress = "&4" + progress + "&r% "; }
/* 1508 */     else if (Float.parseFloat(progress) < 32.0F) { progress = "&c" + progress + "&r% "; }
/* 1509 */     else if (Float.parseFloat(progress) < 48.0F) { progress = "&6" + progress + "&r% "; }
/* 1510 */     else if (Float.parseFloat(progress) < 64.0F) { progress = "&e" + progress + "&r% "; }
/* 1511 */     else if (Float.parseFloat(progress) < 80.0F) { progress = "&2" + progress + "&r% "; }
/* 1512 */     else { progress = "&a" + progress + "&r% "; }
/*      */     
/* 1514 */     return progress;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void openScriptComponentEditor(Player p, final Block b, String script, final int index) throws Exception {
/* 1520 */     ChestMenu menu = new ChestMenu("&e脚本编辑器");
/*      */     
/* 1522 */     final String[] commands = script.split("-");
/*      */     
/* 1524 */     menu.addItem(0, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1529 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1534 */     menu.addItem(1, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1539 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1544 */     menu.addItem(2, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1549 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1554 */     menu.addItem(3, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1559 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1564 */     menu.addItem(4, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1569 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1574 */     menu.addItem(5, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1579 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1584 */     menu.addItem(6, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1589 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1594 */     menu.addItem(7, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1599 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1604 */     menu.addItem(8, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1609 */             return false;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1614 */     menu.addItem(9, (ItemStack)new CustomItem(
/* 1615 */           CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYxMzlmZDFjNTY1NGU1NmU5ZTRlMmM4YmU3ZWIyYmQ1YjQ5OWQ2MzM2MTY2NjNmZWVlOTliNzQzNTJhZDY0In19fQ=="), "&r待命"), new ChestMenu.MenuClickHandler()
/*      */         {
/*      */           
/*      */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */           {
/* 1620 */             int i = 0;
/* 1621 */             StringBuilder builder = new StringBuilder("START-");
/* 1622 */             for (String command : commands) {
/* 1623 */               if (i != index && i > 0 && i < commands.length - 1) builder.append(command + "-");
/*      */               
/* 1625 */               i++;
/*      */             } 
/* 1627 */             builder.append("REPEAT");
/* 1628 */             BlockStorage.addBlockInfo(b, "script", builder.toString());
/*      */             try {
/* 1630 */               ProgrammableAndroid.this.openScript(p, b, builder.toString());
/* 1631 */             } catch (Exception e) {
/* 1632 */               e.printStackTrace();
/*      */             } 
/* 1634 */             return false;
/*      */           }
/*      */         });
/*      */     
/* 1638 */     int i = 10;
/* 1639 */     for (ScriptPart part : getAccessibleScriptParts()) {
/* 1640 */       menu.addItem(i, part.toItemStack(), new ChestMenu.MenuClickHandler()
/*      */           {
/*      */ 
/*      */             
/*      */             public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*      */             {
/* 1646 */               int i = 0;
/* 1647 */               StringBuilder builder = new StringBuilder("START-");
/* 1648 */               for (String command : commands) {
/* 1649 */                 if (i > 0)
/* 1650 */                   if (i == index) { builder
/* 1651 */                       .append(part.toString() + "-"); }
/* 1652 */                   else if (i < commands.length - 1)
/* 1653 */                   { builder.append(command + "-"); }
/*      */                    
/* 1655 */                 i++;
/*      */               } 
/* 1657 */               builder.append("REPEAT");
/* 1658 */               BlockStorage.addBlockInfo(b, "script", builder.toString());
/*      */               
/*      */               try {
/* 1661 */                 ProgrammableAndroid.this.openScript(p, b, builder.toString());
/* 1662 */               } catch (Exception e) {
/* 1663 */                 e.printStackTrace();
/*      */               } 
/* 1665 */               return false;
/*      */             }
/*      */           });
/* 1668 */       i++;
/*      */     } 
/*      */     
/* 1671 */     menu.open(new Player[] { p });
/*      */   }
/*      */   
/*      */   private Inventory inject(Block b) {
/* 1675 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 1676 */     Inventory inv = Bukkit.createInventory(null, size);
/* 1677 */     for (int i = 0; i < size; i++) {
/* 1678 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*      */     }
/* 1680 */     for (int slot : getOutputSlots()) {
/* 1681 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*      */     }
/* 1683 */     return inv;
/*      */   }
/*      */   
/*      */   protected boolean fits(Block b, ItemStack... items) {
/* 1687 */     return inject(b).addItem(items).isEmpty();
/*      */   }
/*      */   
/*      */   protected void pushItems(Block b, ItemStack... items) {
/* 1691 */     Inventory inv = inject(b);
/* 1692 */     inv.addItem(items);
/*      */     
/* 1694 */     for (int slot : getOutputSlots()) {
/* 1695 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*      */     }
/*      */   }
/*      */   
/*      */   public void addItems(Block b, ItemStack... items) {
/* 1700 */     pushItems(b, items);
/*      */   }
/*      */ 
/*      */   
/*      */   public void register(boolean slimefun) {
/* 1705 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void tick(Block b, SlimefunItem sf, Config data)
/*      */             {
/* 1709 */               if (b != null) ProgrammableAndroid.this.tick(b);
/*      */             
/*      */             }
/*      */ 
/*      */             
/*      */             public void uniqueTick() {}
/*      */ 
/*      */             
/*      */             public boolean isSynchronized() {
/* 1718 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 1722 */     super.register(slimefun);
/*      */   }
/*      */   
/*      */   public void registerFuel(MachineFuel fuel) {
/* 1726 */     this.recipes.add(fuel);
/*      */   }
/*      */   
/*      */   public List<Config> getUploadedScripts() {
/* 1730 */     List<Config> scripts = new ArrayList<>();
/*      */     
/* 1732 */     File directory = new File("plugins/Slimefun/scripts/" + getAndroidType().toString());
/* 1733 */     if (!directory.exists()) directory.mkdirs();
/*      */     
/* 1735 */     for (File script : directory.listFiles()) {
/* 1736 */       if (script.getName().endsWith("sfs")) scripts.add(new Config(script));
/*      */     
/*      */     } 
/* 1739 */     if (!getAndroidType().equals(AndroidType.NONE)) {
/* 1740 */       File directory2 = new File("plugins/Slimefun/scripts/NONE");
/* 1741 */       if (!directory2.exists()) directory2.mkdirs();
/*      */       
/* 1743 */       for (File script : directory2.listFiles()) {
/* 1744 */         if (script.getName().endsWith("sfs")) scripts.add(new Config(script));
/*      */       
/*      */       } 
/*      */     } 
/* 1748 */     Collections.sort(scripts, (Comparator<? super Config>)new ScriptReputationSorter(this));
/*      */     
/* 1750 */     return scripts;
/*      */   }
/*      */   
/*      */   public List<ScriptPart> getAccessibleScriptParts() {
/* 1754 */     List<ScriptPart> list = new ArrayList<>();
/*      */     
/* 1756 */     for (ScriptPart part : ScriptPart.values()) {
/* 1757 */       if (!part.equals(ScriptPart.START) && !part.equals(ScriptPart.REPEAT) && 
/* 1758 */         getAndroidType().isType(part.getRequiredType())) {
/* 1759 */         list.add(part);
/*      */       }
/*      */     } 
/*      */     
/* 1763 */     return list;
/*      */   }
/*      */   
/*      */   public abstract AndroidType getAndroidType();
/*      */   
/*      */   public abstract float getFuelEfficiency();
/*      */   
/*      */   public abstract int getTier();
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Android\ProgrammableAndroid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */