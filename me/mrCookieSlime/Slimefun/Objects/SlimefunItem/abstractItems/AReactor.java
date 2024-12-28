/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ReactorAccessPort;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import me.mrCookieSlime.Slimefun.holograms.ReactorHologram;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public abstract class AReactor extends SlimefunItem {
/*  48 */   public static Map<Location, MachineFuel> processing = new HashMap<>();
/*  49 */   public static Map<Location, Integer> progress = new HashMap<>();
/*     */   
/*  51 */   private static final BlockFace[] cooling = new BlockFace[] { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };
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
/*  63 */   private Set<MachineFuel> recipes = new HashSet<>();
/*     */   
/*  65 */   private static final int[] border = new int[] { 0, 1, 2, 3, 5, 6, 7, 8, 12, 13, 14, 21, 23 };
/*  66 */   private static final int[] border_1 = new int[] { 9, 10, 11, 18, 20, 27, 29, 36, 38, 45, 46, 47 };
/*  67 */   private static final int[] border_2 = new int[] { 15, 16, 17, 24, 26, 33, 35, 42, 44, 51, 52, 53 };
/*  68 */   private static final int[] border_3 = new int[] { 30, 31, 32, 39, 41, 48, 49, 50 };
/*  69 */   private static final int[] border_4 = new int[] { 25, 34, 43 };
/*     */   
/*     */   public AReactor(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/*  72 */     super(category, item, id, recipeType, recipe);
/*     */     
/*  74 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  78 */           AReactor.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*     */           try {
/*  84 */             if (BlockStorage.getLocationInfo(b.getLocation(), "reactor-mode") == null) {
/*  85 */               BlockStorage.addBlockInfo(b, "reactor-mode", "generator");
/*     */             }
/*  87 */             if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "reactor-mode").equals("generator")) {
/*  88 */               menu.replaceExistingItem(4, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&7优先: &e发电", new String[] { "", "&6你的反应器将专注于发电", "&6如果你的能量网络无需能源", "&6它将不会生产任何东西", "", "&7> 点击修改为优先 &e生产" }));
/*  89 */               menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/*  93 */                       BlockStorage.addBlockInfo(b, "reactor-mode", "production");
/*  94 */                       AReactor.null.this.newInstance(menu, b);
/*  95 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } else {
/*     */               
/* 100 */               menu.replaceExistingItem(4, (ItemStack)new CustomItem(SlimefunItems.PLUTONIUM, "&7优先: &e生产", new String[] { "", "&6你的反应器会优先生产产品", "&6如果你的能量网络不需要能源", "&6它将继续运行", "&6并且不会生产任何能源", "", "&7> 点击修改为优先 &e发电" }));
/* 101 */               menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*     */                   {
/*     */                     public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                     {
/* 105 */                       BlockStorage.addBlockInfo(b, "reactor-mode", "generator");
/* 106 */                       AReactor.null.this.newInstance(menu, b);
/* 107 */                       return false;
/*     */                     }
/*     */                   });
/*     */             } 
/* 111 */           } catch (Exception exception) {}
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 117 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/* 118 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 123 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/* 127 */     registerBlockHandler(id, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 136 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 137 */             if (inv != null) {
/* 138 */               for (int slot : AReactor.this.getFuelSlots()) {
/* 139 */                 if (inv.getItemInSlot(slot) != null) {
/* 140 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 141 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 144 */               for (int slot : AReactor.this.getCoolantSlots()) {
/* 145 */                 if (inv.getItemInSlot(slot) != null) {
/* 146 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 147 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 150 */               for (int slot : AReactor.this.getOutputSlots()) {
/* 151 */                 if (inv.getItemInSlot(slot) != null) {
/* 152 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 153 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 157 */             AReactor.progress.remove(b.getLocation());
/* 158 */             AReactor.processing.remove(b.getLocation());
/* 159 */             ReactorHologram.remove(b.getLocation());
/* 160 */             return true;
/*     */           }
/*     */         });
/*     */     
/* 164 */     registerDefaultRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 169 */     for (int i : border) {
/* 170 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 175 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 181 */     for (int i : border_1) {
/* 182 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)5), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 187 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 193 */     for (int i : border_3) {
/* 194 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)13), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 199 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 205 */     preset.addItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 210 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 215 */     preset.addItem(1, (ItemStack)new CustomItem(SlimefunItems.URANIUM, "&7燃料槽", new String[] { "", "&r这里可以放入放射性燃料, 例如:", "&2铀 &r或 &a镎" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 220 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 225 */     for (int i : border_2) {
/* 226 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 231 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 237 */     if (needsCooling()) {
/* 238 */       preset.addItem(7, (ItemStack)new CustomItem(getCoolant(), "&b冷却槽", new String[] { "", "&r这个槽位用于放置反应器冷却单元", "&4如果反应器不配置冷却槽", "&4反应器将会因为过热导致爆炸" }));
/*     */     } else {
/*     */       
/* 241 */       preset.addItem(7, (ItemStack)new CustomItem(new MaterialData(Material.BARRIER), "&b冷却槽", new String[] { "", "&r这个槽位用于放置反应器冷却单元" }));
/*     */       
/* 243 */       for (int i : border_4) {
/* 244 */         preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.BARRIER), "&c无需冷却单元"), new ChestMenu.MenuClickHandler()
/*     */             {
/*     */               
/*     */               public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */               {
/* 249 */                 return false;
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
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
/*     */   public boolean needsCooling() {
/* 267 */     return (getCoolant() != null);
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 271 */     return new int[] { 19, 28, 37, 25, 34, 43 };
/*     */   }
/*     */   
/*     */   public int[] getFuelSlots() {
/* 275 */     return new int[] { 19, 28, 37 };
/*     */   }
/*     */   
/*     */   public int[] getCoolantSlots() {
/* 279 */     (new int[3])[0] = 25; (new int[3])[1] = 34; (new int[3])[2] = 43; return needsCooling() ? new int[3] : new int[0];
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 283 */     return new int[] { 40 };
/*     */   }
/*     */   
/*     */   public MachineFuel getProcessing(Location l) {
/* 287 */     return processing.get(l);
/*     */   }
/*     */   
/*     */   public boolean isProcessing(Location l) {
/* 291 */     return progress.containsKey(l);
/*     */   }
/*     */   
/*     */   public void registerFuel(MachineFuel fuel) {
/* 295 */     this.recipes.add(fuel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 300 */     addItemHandler(new ItemHandler[] { (ItemHandler)new EnergyTicker()
/*     */           {
/* 302 */             Set<Location> explode = new HashSet<>();
/*     */ 
/*     */ 
/*     */             
/*     */             public double generateEnergy(final Location l, SlimefunItem sf, Config data) {
/* 307 */               BlockMenu port = AReactor.this.getAccessPort(l);
/*     */               
/* 309 */               if (AReactor.this.isProcessing(l)) {
/* 310 */                 AReactor.this.extraTick(l);
/* 311 */                 int timeleft = ((Integer)AReactor.progress.get(l)).intValue();
/* 312 */                 if (timeleft > 0) {
/* 313 */                   int produced = AReactor.this.getEnergyProduction();
/* 314 */                   int space = ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l);
/* 315 */                   if (space >= produced) {
/* 316 */                     ChargableBlock.addCharge(l, AReactor.this.getEnergyProduction());
/* 317 */                     space -= produced;
/*     */                   } 
/* 319 */                   if (space >= produced || !BlockStorage.getBlockInfo(l, "reactor-mode").equals("generator")) {
/* 320 */                     AReactor.progress.put(l, Integer.valueOf(timeleft - 1));
/*     */                     
/* 322 */                     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                         {
/*     */                           public void run()
/*     */                           {
/* 326 */                             if (!l.getBlock().getRelative(AReactor.cooling[CSCoreLib.randomizer().nextInt(AReactor.cooling.length)]).isLiquid()) AReactor.null.this.explode.add(l);
/*     */                           
/*     */                           }
/*     */                         });
/*     */                     
/* 331 */                     ItemStack item = AReactor.this.getProgressBar().clone();
/* 332 */                     ItemMeta im = item.getItemMeta();
/* 333 */                     im.setDisplayName(" ");
/* 334 */                     List<String> lore = new ArrayList<>();
/* 335 */                     lore.add(MachineHelper.getProgress(timeleft, ((MachineFuel)AReactor.processing.get(l)).getTicks()));
/* 336 */                     lore.add(MachineHelper.getCoolant(timeleft, ((MachineFuel)AReactor.processing.get(l)).getTicks()));
/* 337 */                     lore.add("");
/* 338 */                     lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 339 */                     im.setLore(lore);
/* 340 */                     item.setItemMeta(im);
/*     */                     
/* 342 */                     BlockStorage.getInventory(l).replaceExistingItem(22, item);
/*     */                     
/* 344 */                     if (AReactor.this.needsCooling()) {
/* 345 */                       boolean coolant = ((((MachineFuel)AReactor.processing.get(l)).getTicks() - timeleft) % 25 == 0);
/*     */                       
/* 347 */                       if (coolant) {
/* 348 */                         if (port != null) {
/* 349 */                           for (int slot : AReactor.this.getCoolantSlots()) {
/* 350 */                             if (SlimefunManager.isItemSimiliar(port.getItemInSlot(slot), AReactor.this.getCoolant(), true)) {
/* 351 */                               port.replaceExistingItem(slot, AReactor.this.pushItems(l, port.getItemInSlot(slot), AReactor.this.getCoolantSlots()));
/*     */                             }
/*     */                           } 
/*     */                         }
/*     */                         
/* 356 */                         boolean explosion = true;
/* 357 */                         for (int slot : AReactor.this.getCoolantSlots()) {
/* 358 */                           if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(l).getItemInSlot(slot), AReactor.this.getCoolant(), true)) {
/* 359 */                             BlockStorage.getInventory(l).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(l).getItemInSlot(slot), 1));
/* 360 */                             ReactorHologram.update(l, "&b❄ &7100%");
/* 361 */                             explosion = false;
/*     */                             
/*     */                             break;
/*     */                           } 
/*     */                         } 
/* 366 */                         if (explosion) {
/* 367 */                           this.explode.add(l);
/* 368 */                           return 0.0D;
/*     */                         } 
/*     */                       } else {
/*     */                         
/* 372 */                         ReactorHologram.update(l, "&b❄ &7" + MachineHelper.getPercentage(timeleft, ((MachineFuel)AReactor.processing.get(l)).getTicks()) + "%");
/*     */                       } 
/*     */                     } 
/*     */                     
/* 376 */                     return ChargableBlock.getCharge(l);
/*     */                   } 
/* 378 */                   return 0.0D;
/*     */                 } 
/*     */                 
/* 381 */                 BlockStorage.getInventory(l).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/* 382 */                 if (((MachineFuel)AReactor.processing.get(l)).getOutput() != null) AReactor.this.pushItems(l, ((MachineFuel)AReactor.processing.get(l)).getOutput());
/*     */                 
/* 384 */                 if (port != null) {
/* 385 */                   for (int slot : AReactor.this.getOutputSlots()) {
/* 386 */                     if (BlockStorage.getInventory(l).getItemInSlot(slot) != null) BlockStorage.getInventory(l).replaceExistingItem(slot, ReactorAccessPort.pushItems(port.getLocation(), BlockStorage.getInventory(l).getItemInSlot(slot)));
/*     */                   
/*     */                   } 
/*     */                 }
/* 390 */                 AReactor.progress.remove(l);
/* 391 */                 AReactor.processing.remove(l);
/* 392 */                 return 0.0D;
/*     */               } 
/*     */ 
/*     */               
/* 396 */               MachineFuel r = null;
/* 397 */               Map<Integer, Integer> found = new HashMap<>();
/*     */               
/* 399 */               if (port != null)
/*     */               {
/* 401 */                 for (int slot : AReactor.this.getFuelSlots()) {
/* 402 */                   for (MachineFuel recipe : AReactor.this.recipes) {
/* 403 */                     if (SlimefunManager.isItemSimiliar(port.getItemInSlot(slot), recipe.getInput(), true) && 
/* 404 */                       AReactor.this.pushItems(l, (ItemStack)new CustomItem(port.getItemInSlot(slot), 1), AReactor.this.getFuelSlots()) == null) {
/* 405 */                       port.replaceExistingItem(slot, InvUtils.decreaseItem(port.getItemInSlot(slot), 1));
/*     */ 
/*     */                       
/*     */                       // Byte code: goto -> 956
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */               
/* 414 */               label92: for (MachineFuel recipe : AReactor.this.recipes) {
/* 415 */                 for (int slot : AReactor.this.getFuelSlots()) {
/* 416 */                   if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(l).getItemInSlot(slot), recipe.getInput(), true)) {
/* 417 */                     found.put(Integer.valueOf(slot), Integer.valueOf(recipe.getInput().getAmount()));
/* 418 */                     r = recipe;
/*     */                     
/*     */                     break label92;
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 424 */               if (r != null) {
/* 425 */                 for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
/* 426 */                   BlockStorage.getInventory(l).replaceExistingItem(((Integer)entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(l).getItemInSlot(((Integer)entry.getKey()).intValue()), ((Integer)entry.getValue()).intValue()));
/*     */                 }
/* 428 */                 AReactor.processing.put(l, r);
/* 429 */                 AReactor.progress.put(l, Integer.valueOf(r.getTicks()));
/*     */               } 
/* 431 */               return 0.0D;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public boolean explode(final Location l) {
/* 437 */               boolean explosion = this.explode.contains(l);
/* 438 */               if (explosion) {
/* 439 */                 BlockStorage.getInventory(l).close();
/*     */                 
/* 441 */                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                     {
/*     */                       public void run() {
/* 444 */                         ReactorHologram.remove(l);
/*     */                       }
/*     */                     },  0L);
/*     */                 
/* 448 */                 this.explode.remove(l);
/* 449 */                 AReactor.processing.remove(l);
/* 450 */                 AReactor.progress.remove(l);
/*     */               } 
/* 452 */               return explosion;
/*     */             }
/*     */           } });
/*     */     
/* 456 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   private Inventory inject(Location l) {
/* 460 */     int size = BlockStorage.getInventory(l).toInventory().getSize();
/* 461 */     Inventory inv = Bukkit.createInventory(null, size);
/* 462 */     for (int i = 0; i < size; i++) {
/* 463 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 465 */     for (int slot : getOutputSlots()) {
/* 466 */       inv.setItem(slot, BlockStorage.getInventory(l).getItemInSlot(slot));
/*     */     }
/* 468 */     return inv;
/*     */   }
/*     */   
/*     */   private Inventory inject(Location l, int[] slots) {
/* 472 */     int size = BlockStorage.getInventory(l).toInventory().getSize();
/* 473 */     Inventory inv = Bukkit.createInventory(null, size);
/* 474 */     for (int i = 0; i < size; i++) {
/* 475 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 477 */     for (int slot : slots) {
/* 478 */       inv.setItem(slot, BlockStorage.getInventory(l).getItemInSlot(slot));
/*     */     }
/* 480 */     return inv;
/*     */   }
/*     */   
/*     */   public void pushItems(Location l, ItemStack item) {
/* 484 */     Inventory inv = inject(l);
/* 485 */     inv.addItem(new ItemStack[] { item });
/*     */     
/* 487 */     for (int slot : getOutputSlots()) {
/* 488 */       BlockStorage.getInventory(l).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack pushItems(Location l, ItemStack item, int[] slots) {
/* 493 */     Inventory inv = inject(l, slots);
/* 494 */     Map<Integer, ItemStack> map = inv.addItem(new ItemStack[] { item });
/*     */     
/* 496 */     for (int slot : slots) {
/* 497 */       BlockStorage.getInventory(l).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */     
/* 500 */     Iterator<Map.Entry<Integer, ItemStack>> iterator = map.entrySet().iterator(); if (iterator.hasNext()) { Map.Entry<Integer, ItemStack> entry = iterator.next();
/* 501 */       return entry.getValue(); }
/*     */ 
/*     */     
/* 504 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<MachineFuel> getFuelTypes() {
/* 510 */     return this.recipes;
/*     */   }
/*     */   
/*     */   public BlockMenu getAccessPort(Location l) {
/* 514 */     Location portL = new Location(l.getWorld(), l.getX(), l.getY() + 3.0D, l.getZ());
/* 515 */     if (BlockStorage.check(portL, "REACTOR_ACCESS_PORT")) return BlockStorage.getInventory(portL); 
/* 516 */     return null;
/*     */   }
/*     */   
/*     */   public abstract String getInventoryTitle();
/*     */   
/*     */   public abstract void registerDefaultRecipes();
/*     */   
/*     */   public abstract int getEnergyProduction();
/*     */   
/*     */   public abstract void extraTick(Location paramLocation);
/*     */   
/*     */   public abstract ItemStack getCoolant();
/*     */   
/*     */   public abstract ItemStack getProgressBar();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\AReactor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */