/*     */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.network.Network;
/*     */ import me.mrCookieSlime.Slimefun.holograms.CargoHologram;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class CargoNet
/*     */   extends Network
/*     */ {
/*     */   public static boolean EXTRA_CHANNELS = false;
/*     */   private static final int RANGE = 5;
/*  42 */   public static List<BlockFace> faces = Arrays.asList(new BlockFace[] { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST });
/*  43 */   public static Map<Location, Integer> round_robin = new HashMap<>();
/*  44 */   public static Set<ItemRequest> requests = new HashSet<>();
/*     */   
/*  46 */   private static int[] slots = new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
/*     */ 
/*     */   
/*  49 */   private static final ChestTerminalSorter sorter = new ChestTerminalSorter();
/*  50 */   public static final int[] terminal_slots = new int[] { 0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42 };
/*  51 */   private static final ItemStack terminal_noitem_item = (ItemStack)new CustomItem(new MaterialData(Material.BARRIER), "&4No Item cached", new String[0]);
/*  52 */   private static final ChestMenu.MenuClickHandler terminal_noitem_handler = new ChestMenu.MenuClickHandler()
/*     */     {
/*     */       public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action)
/*     */       {
/*  56 */         return false;
/*     */       }
/*     */     };
/*     */   
/*     */   public static CargoNet getNetworkFromLocation(Location l) {
/*  61 */     return (CargoNet)getNetworkFromLocation(l, CargoNet.class);
/*     */   }
/*     */   
/*     */   public static CargoNet getNetworkFromLocationOrCreate(Location l) {
/*  65 */     CargoNet cargo_network = getNetworkFromLocation(l);
/*  66 */     if (cargo_network == null) {
/*  67 */       cargo_network = new CargoNet(l);
/*  68 */       registerNetwork(cargo_network);
/*     */     } 
/*  70 */     return cargo_network;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static boolean isConnected(Block b) {
/*  75 */     return (getNetworkFromLocation(b.getLocation()) != null);
/*     */   }
/*     */   
/*  78 */   private Set<Location> inputNodes = new HashSet<>();
/*  79 */   private Set<Location> outputNodes = new HashSet<>();
/*  80 */   private Set<Location> advancedOutputNodes = new HashSet<>();
/*     */ 
/*     */ 
/*     */   
/*  84 */   final Set<Location> terminals = new HashSet<>();
/*  85 */   final Set<Location> imports = new HashSet<>();
/*  86 */   final Set<Location> exports = new HashSet<>();
/*     */   
/*     */   protected CargoNet(Location l) {
/*  89 */     super(l);
/*     */   }
/*     */   
/*     */   public int getRange() {
/*  93 */     return 5;
/*     */   }
/*     */   
/*     */   public Network.Component classifyLocation(Location l) {
/*  97 */     String id = BlockStorage.checkID(l);
/*  98 */     if (id == null) return null; 
/*  99 */     switch (id) {
/*     */       case "CARGO_MANAGER":
/* 101 */         return Network.Component.REGULATOR;
/*     */       case "CARGO_NODE":
/* 103 */         return Network.Component.CONNECTOR;
/*     */       case "CARGO_NODE_INPUT":
/*     */       case "CARGO_NODE_OUTPUT":
/*     */       case "CARGO_NODE_OUTPUT_ADVANCED":
/*     */       case "CT_IMPORT_BUS":
/*     */       case "CT_EXPORT_BUS":
/*     */       case "CHEST_TERMINAL":
/* 110 */         return Network.Component.TERMINUS;
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void locationClassificationChange(Location l, Network.Component from, Network.Component to) {
/* 117 */     if (from == Network.Component.TERMINUS) {
/* 118 */       this.inputNodes.remove(l);
/* 119 */       this.outputNodes.remove(l);
/* 120 */       this.advancedOutputNodes.remove(l);
/* 121 */       this.terminals.remove(l);
/* 122 */       this.imports.remove(l);
/* 123 */       this.exports.remove(l);
/*     */     } 
/* 125 */     if (to == Network.Component.TERMINUS)
/* 126 */       switch (BlockStorage.checkID(l)) {
/*     */         case "CARGO_NODE_INPUT":
/* 128 */           this.inputNodes.add(l);
/*     */           break;
/*     */         case "CARGO_NODE_OUTPUT":
/* 131 */           this.outputNodes.add(l);
/*     */           break;
/*     */         case "CARGO_NODE_OUTPUT_ADVANCED":
/* 134 */           this.advancedOutputNodes.add(l);
/*     */           break;
/*     */         case "CHEST_TERMINAL":
/* 137 */           this.terminals.add(l);
/*     */           break;
/*     */         case "CT_IMPORT_BUS":
/* 140 */           this.imports.add(l);
/*     */           break;
/*     */         case "CT_EXPORT_BUS":
/* 143 */           this.exports.add(l);
/*     */           break;
/*     */       }  
/*     */   }
/*     */   
/*     */   public void tick(final Block b) {
/*     */     final Set<Location> destinations;
/* 150 */     if (!this.regulator.equals(b.getLocation())) {
/* 151 */       CargoHologram.update(b, "&4多个货运节点相连");
/*     */       return;
/*     */     } 
/* 154 */     tick();
/* 155 */     if (this.connectorNodes.isEmpty() && this.terminusNodes.isEmpty()) {
/* 156 */       CargoHologram.update(b, "&7状态: &4&l离线");
/*     */       
/*     */       return;
/*     */     } 
/* 160 */     CargoHologram.update(b, "&7状态: &a&l在线");
/*     */ 
/*     */     
/* 163 */     final Map<Integer, List<Location>> output = new HashMap<>();
/*     */ 
/*     */     
/* 166 */     for (Location outputNode : this.outputNodes) {
/* 167 */       Integer frequency = Integer.valueOf(getFrequency(outputNode));
/* 168 */       if (!output.containsKey(frequency)) {
/* 169 */         output.put(frequency, new ArrayList<>());
/*     */       }
/* 171 */       ((List<Location>)output.get(frequency)).add(outputNode);
/*     */     } 
/* 173 */     for (Location outputNode : this.advancedOutputNodes) {
/* 174 */       Integer frequency = Integer.valueOf(getFrequency(outputNode));
/* 175 */       if (!output.containsKey(frequency)) {
/* 176 */         output.put(frequency, new ArrayList<>());
/*     */       }
/* 178 */       ((List<Location>)output.get(frequency)).add(outputNode);
/*     */     } 
/*     */ 
/*     */     
/* 182 */     final Set<Location> providers = new HashSet<>();
/*     */     
/* 184 */     if (output.containsKey(Integer.valueOf(16))) {
/* 185 */       destinations = new HashSet<>(output.get(Integer.valueOf(16)));
/*     */     } else {
/* 187 */       destinations = new HashSet<>();
/*     */     } 
/* 189 */     for (Location inputNode : this.inputNodes) {
/* 190 */       int frequency = getFrequency(inputNode);
/* 191 */       if (frequency == 16) {
/* 192 */         providers.add(inputNode);
/*     */       }
/*     */     } 
/*     */     
/* 196 */     final CargoNet self = this;
/* 197 */     final BlockStorage storage = BlockStorage.getStorage(b.getWorld());
/* 198 */     SlimefunStartup.instance.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 202 */             if (BlockStorage.getLocationInfo(b.getLocation(), "visualizer") == null) {
/* 203 */               self.display();
/*     */             }
/*     */             
/* 206 */             if (CargoNet.EXTRA_CHANNELS) {
/* 207 */               for (Location bus : CargoNet.this.imports) {
/* 208 */                 BlockMenu menu = BlockStorage.getInventory(bus);
/*     */                 
/* 210 */                 if (menu.getItemInSlot(17) == null) {
/* 211 */                   Block target = CargoNet.getAttachedBlock(bus.getBlock());
/* 212 */                   ItemSlot stack = CargoManager.withdraw(bus.getBlock(), storage, target, -1);
/*     */                   
/* 214 */                   if (stack != null) {
/* 215 */                     menu.replaceExistingItem(17, stack.getItem());
/*     */                   }
/*     */                 } 
/*     */                 
/* 219 */                 if (menu.getItemInSlot(17) != null) {
/* 220 */                   CargoNet.requests.add(new ItemRequest(bus, 17, menu.getItemInSlot(17), ItemTransportFlow.INSERT));
/*     */                 }
/*     */               } 
/*     */               
/* 224 */               for (Location bus : CargoNet.this.exports) {
/* 225 */                 BlockMenu menu = BlockStorage.getInventory(bus);
/*     */                 
/* 227 */                 if (menu.getItemInSlot(17) != null) {
/* 228 */                   Block target = CargoNet.getAttachedBlock(bus.getBlock());
/*     */                   
/* 230 */                   menu.replaceExistingItem(17, CargoManager.insert(bus.getBlock(), storage, target, menu.getItemInSlot(17), -1));
/*     */                 } 
/*     */                 
/* 233 */                 if (menu.getItemInSlot(17) == null) {
/* 234 */                   List<ItemStack> items = new ArrayList<>();
/* 235 */                   for (int slot : CargoNet.slots) {
/* 236 */                     ItemStack template = menu.getItemInSlot(slot);
/* 237 */                     if (template != null) items.add(new CustomItem(template, 1));
/*     */                   
/*     */                   } 
/* 240 */                   if (!items.isEmpty()) {
/* 241 */                     int index = Integer.parseInt(BlockStorage.getLocationInfo(bus, "index"));
/*     */                     
/* 243 */                     index++;
/* 244 */                     if (index > items.size() - 1) index = 0;
/*     */                     
/* 246 */                     BlockStorage.addBlockInfo(bus, "index", String.valueOf(index));
/*     */                     
/* 248 */                     CargoNet.requests.add(new ItemRequest(bus, 17, items.get(index), ItemTransportFlow.WITHDRAW));
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/* 253 */               Iterator<ItemRequest> iterator = CargoNet.requests.iterator();
/* 254 */               while (iterator.hasNext()) {
/* 255 */                 ItemRequest request = iterator.next();
/* 256 */                 if (CargoNet.this.terminals.contains(request.getTerminal()) || CargoNet.this.imports.contains(request.getTerminal()) || CargoNet.this.exports.contains(request.getTerminal())) {
/* 257 */                   ItemStack stack; int slot; ItemStack prevStack, itemStack1; CustomItem customItem; ItemStack requested; BlockMenu menu = BlockStorage.getInventory(request.getTerminal());
/*     */                   
/* 259 */                   switch (request.getDirection()) {
/*     */                     case INSERT:
/* 261 */                       stack = request.getItem();
/*     */                       
/* 263 */                       for (Location l : destinations) {
/* 264 */                         Block target = CargoNet.getAttachedBlock(l.getBlock());
/* 265 */                         stack = CargoManager.insert(l.getBlock(), storage, target, stack, -1);
/* 266 */                         if (stack == null) {
/* 267 */                           menu.replaceExistingItem(request.getSlot(), null);
/*     */                           
/*     */                           break;
/*     */                         } 
/*     */                       } 
/* 272 */                       if (stack != null) {
/* 273 */                         menu.replaceExistingItem(request.getSlot(), stack);
/*     */                       }
/*     */                       
/* 276 */                       iterator.remove();
/*     */ 
/*     */                     
/*     */                     case WITHDRAW:
/* 280 */                       slot = request.getSlot();
/* 281 */                       prevStack = menu.getItemInSlot(slot);
/* 282 */                       if (prevStack != null && (prevStack.getAmount() + request.getItem().getAmount() > prevStack.getMaxStackSize() || !SlimefunManager.isItemSimiliar(prevStack, (ItemStack)new CustomItem(request.getItem(), 1), true, SlimefunManager.DataType.ALWAYS))) {
/* 283 */                         iterator.remove();
/*     */                         
/*     */                         continue;
/*     */                       } 
/* 287 */                       itemStack1 = null;
/* 288 */                       requested = request.getItem();
/*     */                       
/* 290 */                       for (Location l : providers) {
/* 291 */                         Block target = CargoNet.getAttachedBlock(l.getBlock());
/* 292 */                         ItemStack is = CargoManager.withdraw(l.getBlock(), storage, target, requested);
/* 293 */                         if (is != null) {
/* 294 */                           if (itemStack1 == null) {
/* 295 */                             itemStack1 = is;
/*     */                           } else {
/*     */                             
/* 298 */                             customItem = new CustomItem(itemStack1, itemStack1.getAmount() + is.getAmount());
/*     */                           } 
/*     */                           
/* 301 */                           if (is.getAmount() == requested.getAmount()) {
/*     */                             break;
/*     */                           }
/*     */                           
/* 305 */                           CustomItem customItem1 = new CustomItem(requested, requested.getAmount() - is.getAmount());
/*     */                         } 
/*     */                       } 
/*     */ 
/*     */                       
/* 310 */                       if (customItem != null) {
/* 311 */                         ItemStack prev = menu.getItemInSlot(slot);
/*     */                         
/* 313 */                         if (prev == null) { menu.replaceExistingItem(slot, (ItemStack)customItem); }
/* 314 */                         else { menu.replaceExistingItem(slot, (ItemStack)new CustomItem((ItemStack)customItem, customItem.getAmount() + prev.getAmount())); }
/*     */                       
/*     */                       } 
/* 317 */                       iterator.remove();
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 } 
/*     */               } 
/*     */             } 
/* 328 */             for (Location input : CargoNet.this.inputNodes) {
/* 329 */               Integer frequency = Integer.valueOf(CargoNet.getFrequency(input));
/* 330 */               if (frequency.intValue() < 0 || frequency.intValue() > 15) {
/*     */                 continue;
/*     */               }
/* 333 */               Block inputTarget = CargoNet.getAttachedBlock(input.getBlock());
/* 334 */               ItemStack stack = null;
/* 335 */               int previousSlot = -1;
/*     */               
/* 337 */               boolean roundrobin = BlockStorage.getLocationInfo(input, "round-robin").equals("true");
/*     */               
/* 339 */               if (inputTarget != null) {
/* 340 */                 ItemSlot slot = CargoManager.withdraw(input.getBlock(), storage, inputTarget, Integer.parseInt(BlockStorage.getLocationInfo(input, "index")));
/* 341 */                 if (slot != null) {
/* 342 */                   stack = slot.getItem();
/* 343 */                   previousSlot = slot.getSlot();
/*     */                 } 
/*     */               } 
/*     */               
/* 347 */               if (stack != null && output.containsKey(frequency)) {
/* 348 */                 List<Location> outputlist = new ArrayList<>((Collection<? extends Location>)output.get(frequency));
/*     */                 
/* 350 */                 if (roundrobin) {
/* 351 */                   if (!CargoNet.round_robin.containsKey(input)) {
/* 352 */                     CargoNet.round_robin.put(input, Integer.valueOf(0));
/*     */                   }
/*     */                   
/* 355 */                   int c_index = ((Integer)CargoNet.round_robin.get(input)).intValue();
/*     */                   
/* 357 */                   if (c_index < outputlist.size()) {
/* 358 */                     for (int i = 0; i < c_index; i++) {
/* 359 */                       Location temp = outputlist.get(0);
/* 360 */                       outputlist.remove(temp);
/* 361 */                       outputlist.add(temp);
/*     */                     } 
/* 363 */                     c_index++;
/*     */                   } else {
/* 365 */                     c_index = 1;
/*     */                   } 
/* 367 */                   CargoNet.round_robin.put(input, Integer.valueOf(c_index));
/*     */                 } 
/*     */ 
/*     */                 
/* 371 */                 for (Location out : outputlist) {
/* 372 */                   Block target = CargoNet.getAttachedBlock(out.getBlock());
/* 373 */                   if (target != null) {
/* 374 */                     stack = CargoManager.insert(out.getBlock(), storage, target, stack, -1);
/* 375 */                     if (stack == null)
/*     */                       break; 
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 380 */               if (stack != null && previousSlot > -1) {
/* 381 */                 if (storage.hasUniversalInventory(inputTarget)) {
/* 382 */                   UniversalBlockMenu menu = storage.getUniversalInventory(inputTarget);
/* 383 */                   menu.replaceExistingItem(previousSlot, stack); continue;
/*     */                 } 
/* 385 */                 if (storage.hasInventory(inputTarget.getLocation())) {
/* 386 */                   BlockMenu menu = BlockStorage.getInventory(inputTarget.getLocation());
/* 387 */                   menu.replaceExistingItem(previousSlot, stack); continue;
/*     */                 } 
/* 389 */                 if (inputTarget.getState() instanceof InventoryHolder) {
/* 390 */                   Inventory inv = ((InventoryHolder)inputTarget.getState()).getInventory();
/* 391 */                   inv.setItem(previousSlot, stack);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 396 */             if (CargoNet.EXTRA_CHANNELS) {
/* 397 */               List<StoredItem> items = new ArrayList<>();
/* 398 */               for (Location l : providers) {
/* 399 */                 Block target = CargoNet.getAttachedBlock(l.getBlock());
/* 400 */                 if (storage.hasUniversalInventory(target)) {
/* 401 */                   UniversalBlockMenu menu = storage.getUniversalInventory(target);
/* 402 */                   for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/* 403 */                     ItemStack is = menu.getItemInSlot(slot);
/* 404 */                     if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
/* 405 */                       boolean add = true;
/* 406 */                       for (StoredItem item : items) {
/* 407 */                         if (SlimefunManager.isItemSimiliar(is, item.getItem(), true, SlimefunManager.DataType.ALWAYS)) {
/* 408 */                           add = false;
/* 409 */                           item.add(is.getAmount());
/*     */                         } 
/*     */                       } 
/*     */                       
/* 413 */                       if (add)
/* 414 */                         items.add(new StoredItem((ItemStack)new CustomItem(is, 1), is.getAmount())); 
/*     */                     } 
/*     */                   } 
/*     */                   continue;
/*     */                 } 
/* 419 */                 if (storage.hasInventory(target.getLocation())) {
/* 420 */                   BlockMenu menu = BlockStorage.getInventory(target.getLocation());
/* 421 */                   if (BlockStorage.checkID(target.getLocation()).startsWith("BARREL_") && BlockStorage.getLocationInfo(target.getLocation(), "storedItems") != null) {
/* 422 */                     int stored = Integer.valueOf(BlockStorage.getLocationInfo(target.getLocation(), "storedItems")).intValue();
/* 423 */                     for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/* 424 */                       ItemStack is = menu.getItemInSlot(slot);
/* 425 */                       if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
/* 426 */                         boolean add = true;
/* 427 */                         for (StoredItem item : items) {
/* 428 */                           if (SlimefunManager.isItemSimiliar(is, item.getItem(), true, SlimefunManager.DataType.ALWAYS)) {
/* 429 */                             add = false;
/* 430 */                             item.add(is.getAmount() + stored);
/*     */                           } 
/*     */                         } 
/*     */                         
/* 434 */                         if (add) {
/* 435 */                           items.add(new StoredItem((ItemStack)new CustomItem(is, 1), is.getAmount() + stored));
/*     */                         }
/*     */                       } 
/*     */                     } 
/*     */                     continue;
/*     */                   } 
/* 441 */                   for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/* 442 */                     ItemStack is = menu.getItemInSlot(slot);
/* 443 */                     if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
/* 444 */                       boolean add = true;
/* 445 */                       for (StoredItem item : items) {
/* 446 */                         if (SlimefunManager.isItemSimiliar(is, item.getItem(), true, SlimefunManager.DataType.ALWAYS)) {
/* 447 */                           add = false;
/* 448 */                           item.add(is.getAmount());
/*     */                         } 
/*     */                       } 
/*     */                       
/* 452 */                       if (add) {
/* 453 */                         items.add(new StoredItem((ItemStack)new CustomItem(is, 1), is.getAmount()));
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                   continue;
/*     */                 } 
/* 459 */                 if (target.getState() instanceof InventoryHolder) {
/* 460 */                   Inventory inv = ((InventoryHolder)target.getState()).getInventory();
/* 461 */                   for (ItemStack is : inv.getContents()) {
/* 462 */                     if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
/* 463 */                       boolean add = true;
/* 464 */                       for (StoredItem item : items) {
/* 465 */                         if (SlimefunManager.isItemSimiliar(is, item.getItem(), true, SlimefunManager.DataType.ALWAYS)) {
/* 466 */                           add = false;
/* 467 */                           item.add(is.getAmount());
/*     */                         } 
/*     */                       } 
/*     */                       
/* 471 */                       if (add) {
/* 472 */                         items.add(new StoredItem((ItemStack)new CustomItem(is, 1), is.getAmount()));
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/* 479 */               Collections.sort(items, CargoNet.sorter);
/*     */               
/* 481 */               for (Location l : CargoNet.this.terminals) {
/* 482 */                 BlockMenu menu = BlockStorage.getInventory(l);
/* 483 */                 int page = Integer.parseInt(BlockStorage.getLocationInfo(l, "page"));
/* 484 */                 if (!items.isEmpty() && items.size() < (page - 1) * CargoNet.terminal_slots.length + 1) {
/* 485 */                   page = 1;
/* 486 */                   BlockStorage.addBlockInfo(l, "page", String.valueOf(1));
/*     */                 } 
/*     */                 
/* 489 */                 for (int i = 0; i < CargoNet.terminal_slots.length; i++) {
/* 490 */                   int slot = CargoNet.terminal_slots[i];
/* 491 */                   if (items.size() > i + CargoNet.terminal_slots.length * (page - 1)) {
/* 492 */                     final StoredItem item = items.get(i + CargoNet.terminal_slots.length * (page - 1));
/*     */                     
/* 494 */                     ItemStack stack = item.getItem().clone();
/* 495 */                     ItemMeta im = stack.getItemMeta();
/* 496 */                     List<String> lore = new ArrayList<>();
/* 497 */                     lore.add("");
/* 498 */                     lore.add(ChatColor.translateAlternateColorCodes('&', "&7已存储物品: &r" + DoubleHandler.getFancyDouble(item.getAmount())));
/* 499 */                     if (stack.getMaxStackSize() > 1) { lore.add(ChatColor.translateAlternateColorCodes('&', "&7<左键点击: 请求 1 个 | 右键点击: 请求 " + ((item.getAmount() > stack.getMaxStackSize()) ? stack.getMaxStackSize() : item.getAmount()) + " 个>")); }
/* 500 */                     else { lore.add(ChatColor.translateAlternateColorCodes('&', "&7<左键点击: 请求 1 个>")); }
/* 501 */                      lore.add("");
/* 502 */                     if (im.hasLore()) {
/* 503 */                       for (String line : im.getLore()) {
/* 504 */                         lore.add(line);
/*     */                       }
/*     */                     }
/* 507 */                     im.setLore(lore);
/* 508 */                     stack.setItemMeta(im);
/* 509 */                     menu.replaceExistingItem(slot, stack);
/* 510 */                     menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*     */                         {
/*     */                           public boolean onClick(Player p, int slot, ItemStack is, ClickAction action)
/*     */                           {
/* 514 */                             CargoNet.requests.add(new ItemRequest(l, 44, (ItemStack)new CustomItem(item.getItem(), action.isRightClicked() ? ((item.getAmount() > item.getItem().getMaxStackSize()) ? item.getItem().getMaxStackSize() : item.getAmount()) : 1), ItemTransportFlow.WITHDRAW));
/* 515 */                             return false;
/*     */                           }
/*     */                         });
/*     */                   }
/*     */                   else {
/*     */                     
/* 521 */                     menu.replaceExistingItem(slot, CargoNet.terminal_noitem_item);
/* 522 */                     menu.addMenuClickHandler(slot, CargoNet.terminal_noitem_handler);
/*     */                   } 
/*     */                 } 
/*     */                 
/* 526 */                 ItemStack sent_item = menu.getItemInSlot(17);
/* 527 */                 if (sent_item != null) {
/* 528 */                   CargoNet.requests.add(new ItemRequest(l, 17, sent_item, ItemTransportFlow.INSERT));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Block getAttachedBlock(Block block) {
/* 541 */     if (block.getData() == 2) {
/* 542 */       return block.getRelative(BlockFace.SOUTH);
/*     */     }
/* 544 */     if (block.getData() == 3) {
/* 545 */       return block.getRelative(BlockFace.NORTH);
/*     */     }
/* 547 */     if (block.getData() == 4) {
/* 548 */       return block.getRelative(BlockFace.EAST);
/*     */     }
/* 550 */     if (block.getData() == 5) {
/* 551 */       return block.getRelative(BlockFace.WEST);
/*     */     }
/* 553 */     return null;
/*     */   }
/*     */   
/*     */   private static int getFrequency(Location l) {
/* 557 */     int freq = 0;
/*     */     try {
/* 559 */       freq = Integer.parseInt(BlockStorage.getLocationInfo(l).getString("frequency"));
/* 560 */     } catch (Exception exception) {}
/* 561 */     return freq;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\CargoNet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */