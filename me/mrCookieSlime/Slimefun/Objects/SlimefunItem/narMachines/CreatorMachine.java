/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.narMachines;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public abstract class CreatorMachine extends SlimefunItem {
/*  39 */   public static Map<Block, MachineRecipe> processing = new HashMap<>();
/*  40 */   public static Map<Block, Integer> progress = new HashMap<>();
/*  41 */   protected List<MachineRecipe> recipes = new ArrayList<>();
/*  42 */   private static final int[] codeBorder = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 23, 24, 25, 26 };
/*  43 */   private static final int[] code = new int[] { 10, 11, 12, 13, 14, 15, 16 };
/*  44 */   private static final int[] border = new int[] { 27, 30, 31, 32, 35, 36, 39, 41, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
/*  45 */   private static final int[] inputSign = new int[] { 28, 29 };
/*  46 */   private static final int[] outputSign = new int[] { 33, 34 };
/*     */   
/*     */   public CreatorMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  49 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  51 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         
/*     */         public void init()
/*     */         {
/*  56 */           CreatorMachine.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*  62 */           if (BlockStorage.getBlockInfo(b, "random-code") == null) {
/*  63 */             Random random = new Random();
/*  64 */             BlockStorage.addBlockInfo(b, "random-code", String.valueOf(random.nextInt(127)));
/*     */           } 
/*  66 */           if (BlockStorage.getBlockInfo(b, "last-code") == null) {
/*  67 */             BlockStorage.addBlockInfo(b, "last-code", "0000000");
/*     */           }
/*  69 */           if (BlockStorage.getBlockInfo(b, "set-code") == null) {
/*  70 */             BlockStorage.addBlockInfo(b, "set-code", "0000000");
/*     */           }
/*  72 */           if (BlockStorage.getBlockInfo(b, "output-item") == null) {
/*  73 */             BlockStorage.addBlockInfo(b, "output-item", "0");
/*     */           }
/*     */           try {
/*  76 */             final char[] setCode = BlockStorage.getBlockInfo(b, "set-code").toCharArray();
/*  77 */             for (int slot : CreatorMachine.code) {
/*  78 */               final int loc = slot - CreatorMachine.code[0];
/*     */               
/*  80 */               if (!BlockStorage.hasBlockInfo(b) || setCode[loc] == '0') {
/*  81 */                 menu.replaceExistingItem(slot, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGViZTdlNTIxNTE2OWE2OTlhY2M2Y2VmYTdiNzNmZGIxMDhkYjg3YmI2ZGFlMjg0OWZiZTI0NzE0YjI3In19fQ=="), "&7编码: &a0", new String[] { "", "&7正确的§e编码组合§7才能产出指定的物质", "§7每当你设置的编码较上一次§e更接近§7正确编码", "§7机器都会发出比较§e悦耳的声音", "&7另外有技巧地设置编码将有效地§e减少生产成本", "§7不过一旦被观测到正确的常数", "§7常数就会被§c重置", "§7这究竟是神的玩笑还是恶魔的诅咒呢", "", "§c> §7使用§d元物质§7进行制作", "§c> §7调整下方的物品修改你想制作的物品", "§6> §b点击此处将编码修改为§e1" }));
/*     */                 
/*  83 */                 menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*     */                     {
/*     */                       public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                       {
/*  87 */                         BlockStorage.addBlockInfo(b, "last-code", String.valueOf(setCode));
/*  88 */                         setCode[loc] = '1';
/*  89 */                         BlockStorage.addBlockInfo(b, "set-code", String.valueOf(setCode));
/*  90 */                         CreatorMachine.null.this.newInstance(menu, b);
/*  91 */                         return false;
/*     */                       }
/*     */                     });
/*     */               } else {
/*  95 */                 menu.replaceExistingItem(slot, (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0="), "&7编码: &e1", new String[] { "", "&7正确的§e编码组合§7才能产出指定的物质", "§7每当你设置的编码较上一次§e更接近§7正确编码", "§7机器都会发出比较§e悦耳的声音", "&7另外有技巧地设置编码将有效地§e减少生产成本", "§7不过一旦被观测到正确的常数", "§7常数就会被§c重置", "§7这究竟是神的玩笑还是恶魔的诅咒呢", "", "§c> §7使用§d元物质§7进行制作", "§c> §7调整下方的物品修改你想制作的物品", "§6> §b点击此处将编码修改为§a0" }));
/*     */                 
/*  97 */                 menu.addMenuClickHandler(slot, new ChestMenu.MenuClickHandler()
/*     */                     {
/*     */                       public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                       {
/* 101 */                         BlockStorage.addBlockInfo(b, "last-code", String.valueOf(setCode));
/* 102 */                         setCode[loc] = '0';
/* 103 */                         BlockStorage.addBlockInfo(b, "set-code", String.valueOf(setCode));
/* 104 */                         CreatorMachine.null.this.newInstance(menu, b);
/* 105 */                         return false;
/*     */                       }
/*     */                     });
/*     */               } 
/*     */             } 
/* 110 */           } catch (Exception exception) {}
/*     */ 
/*     */           
/* 113 */           ItemStack resultItem = ((MachineRecipe)CreatorMachine.this.recipes.get(Integer.valueOf(BlockStorage.getBlockInfo(b, "output-item")).intValue())).getOutput()[0];
/* 114 */           menu.replaceExistingItem(22, (ItemStack)new CustomItem(resultItem, "§7制作: " + resultItem.getItemMeta().getDisplayName()));
/* 115 */           menu.addMenuClickHandler(22, new ChestMenu.MenuClickHandler()
/*     */               {
/*     */                 public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                 {
/* 119 */                   Random random = new Random();
/* 120 */                   BlockStorage.addBlockInfo(b, "random-code", String.valueOf(random.nextInt(127)));
/* 121 */                   int outItem = Integer.valueOf(BlockStorage.getBlockInfo(b, "output-item")).intValue();
/* 122 */                   BlockStorage.addBlockInfo(b, "output-item", (outItem >= CreatorMachine.this.recipes.size() - 1) ? "0" : String.valueOf(++outItem));
/* 123 */                   CreatorMachine.null.this.newInstance(menu, b);
/* 124 */                   return false;
/*     */                 }
/*     */               });
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 131 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/* 132 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 138 */           if (flow.equals(ItemTransportFlow.INSERT)) {
/* 139 */             return CreatorMachine.this.getInputSlots();
/*     */           }
/* 141 */           return CreatorMachine.this.getOutputSlots();
/*     */         }
/*     */       };
/* 144 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 152 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 153 */             if (inv != null) {
/*     */               
/* 155 */               for (int slot : CreatorMachine.this.getInputSlots()) {
/* 156 */                 if (inv.getItemInSlot(slot) != null) {
/* 157 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*     */                 }
/*     */               } 
/* 160 */               for (int slot : CreatorMachine.this.getOutputSlots()) {
/* 161 */                 if (inv.getItemInSlot(slot) != null) {
/* 162 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*     */                 }
/*     */               } 
/*     */             } 
/* 166 */             CreatorMachine.progress.remove(b);
/* 167 */             CreatorMachine.processing.remove(b);
/* 168 */             return true;
/*     */           }
/*     */         });
/* 171 */     registerDefaultRecipes();
/*     */   }
/*     */   
/*     */   public CreatorMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
/* 175 */     super(category, item, name, recipeType, recipe, recipeOutput);
/*     */     
/* 177 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         
/*     */         public void init()
/*     */         {
/* 182 */           CreatorMachine.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 191 */           boolean perm = (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/* 192 */           return (perm && ProtectionUtils.canAccessItem(p, b));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 198 */           if (flow.equals(ItemTransportFlow.INSERT)) {
/* 199 */             return CreatorMachine.this.getInputSlots();
/*     */           }
/* 201 */           return CreatorMachine.this.getOutputSlots();
/*     */         }
/*     */       };
/* 204 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 212 */             for (int slot : CreatorMachine.this.getInputSlots()) {
/* 213 */               if (BlockStorage.getInventory(b).getItemInSlot(slot) != null) {
/* 214 */                 b.getWorld().dropItemNaturally(b.getLocation(), BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */               }
/*     */             } 
/* 217 */             for (int slot : CreatorMachine.this.getOutputSlots()) {
/* 218 */               if (BlockStorage.getInventory(b).getItemInSlot(slot) != null) {
/* 219 */                 b.getWorld().dropItemNaturally(b.getLocation(), BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */               }
/*     */             } 
/* 222 */             CreatorMachine.processing.remove(b);
/* 223 */             CreatorMachine.progress.remove(b);
/* 224 */             return true;
/*     */           }
/*     */         });
/* 227 */     registerDefaultRecipes();
/*     */   }
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset) {
/* 231 */     for (int i : codeBorder) {
/* 232 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " "), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 236 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 240 */     for (int i : border) {
/* 241 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7), " "), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 245 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 249 */     for (int i : outputSign) {
/* 250 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11), "§b输出槽"), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 254 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 258 */     for (int i : inputSign) {
/* 259 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4), "§e输入槽"), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 263 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 268 */     preset.addItem(40, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " "), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */           {
/* 272 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 276 */     for (int i : getOutputSlots()) {
/* 277 */       preset.addItem(i, null, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack item, ClickAction action) {
/* 280 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent event, Player player, int slot, ItemStack item, ClickAction action) {
/* 285 */               return (item == null || item.getType() == null || item.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 290 */     for (int i : code) {
/* 291 */       preset.addItem(i, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7), " "), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction)
/*     */             {
/* 295 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 302 */     return new int[] { 37, 38 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 306 */     return new int[] { 42, 43 };
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
/*     */   public MachineRecipe getProcessing(Block b) {
/* 321 */     return processing.get(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isProcessing(Block b) {
/* 326 */     return (getProcessing(b) != null);
/*     */   }
/*     */   
/*     */   public void registerRecipe(MachineRecipe recipe) {
/* 330 */     recipe.setTicks(recipe.getTicks());
/* 331 */     this.recipes.add(recipe);
/*     */   }
/*     */   
/*     */   public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
/* 335 */     registerRecipe(new MachineRecipe(seconds, input, output));
/*     */   }
/*     */   
/*     */   public List<MachineRecipe> getMachineRecipes() {
/* 339 */     return this.recipes;
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/* 343 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 344 */     Inventory inv = Bukkit.createInventory(null, size);
/* 345 */     for (int i = 0; i < size; i++) {
/* 346 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 348 */     for (int slot : getOutputSlots()) {
/* 349 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 351 */     return inv;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 356 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushMainItems(Block b, ItemStack[] items) {
/* 360 */     if (BlockStorage.getBlockInfo(b, "random-code").equals(String.valueOf(invertBinary(BlockStorage.getBlockInfo(b, "set-code"))))) {
/*     */       
/* 362 */       Random random = new Random();
/* 363 */       BlockStorage.addBlockInfo(b, "random-code", String.valueOf(random.nextInt(127)));
/* 364 */       Inventory inv = inject(b);
/* 365 */       inv.addItem(items);
/* 366 */       for (int slot : getOutputSlots()) {
/* 367 */         BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*     */       }
/* 369 */       b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
/*     */     } else {
/* 371 */       int randomCode = Integer.valueOf(BlockStorage.getBlockInfo(b, "random-code")).intValue();
/* 372 */       if (Math.abs(randomCode - invertBinary(BlockStorage.getBlockInfo(b, "last-code"))) > Math.abs(randomCode - invertBinary(BlockStorage.getBlockInfo(b, "set-code")))) {
/* 373 */         b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/*     */       } else {
/* 375 */         b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 382 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 387 */               CreatorMachine.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 396 */               return false;
/*     */             }
/*     */           } });
/* 399 */     super.register(slimefun);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/* 404 */     if (isProcessing(b)) {
/*     */       
/* 406 */       int timeleft = ((Integer)progress.get(b)).intValue();
/* 407 */       if (timeleft > 0) {
/*     */         
/* 409 */         ItemStack item = getProgressBar().clone();
/* 410 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 411 */         ItemMeta im = item.getItemMeta();
/* 412 */         im.setDisplayName(" ");
/* 413 */         List<String> lore = new ArrayList<>();
/* 414 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 415 */         lore.add("");
/* 416 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 417 */         im.setLore(lore);
/* 418 */         item.setItemMeta(im);
/*     */         
/* 420 */         BlockStorage.getInventory(b).replaceExistingItem(40, item);
/* 421 */         if (ChargableBlock.isChargable(b))
/*     */         {
/* 423 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption()) {
/*     */             return;
/*     */           }
/* 426 */           ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 427 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         }
/*     */         else
/*     */         {
/* 431 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 436 */         BlockStorage.getInventory(b).replaceExistingItem(40, (ItemStack)new CustomItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " "));
/* 437 */         pushMainItems(b, ((MachineRecipe)this.recipes.get(Integer.valueOf(BlockStorage.getBlockInfo(b, "output-item")).intValue())).getOutput());
/* 438 */         progress.remove(b);
/* 439 */         processing.remove(b);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 444 */       MachineRecipe r = null;
/* 445 */       Map<Integer, Integer> found = new HashMap<>();
/* 446 */       for (MachineRecipe recipe : this.recipes) {
/*     */         
/* 448 */         for (ItemStack input : recipe.getInput()) {
/* 449 */           for (int slot : getInputSlots()) {
/* 450 */             if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), input, true))
/*     */             {
/* 452 */               if (input != null) {
/* 453 */                 found.put(Integer.valueOf(slot), Integer.valueOf(input.getAmount()));
/*     */               }
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 459 */         if (found.size() == (recipe.getInput()).length) {
/*     */           
/* 461 */           r = recipe;
/*     */           break;
/*     */         } 
/* 464 */         found.clear();
/*     */       } 
/* 466 */       if (r != null) {
/*     */         
/* 468 */         if (!fits(b, ((MachineRecipe)this.recipes.get(Integer.valueOf(BlockStorage.getBlockInfo(b, "output-item")).intValue())).getOutput())) {
/*     */           return;
/*     */         }
/* 471 */         for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
/* 472 */           BlockStorage.getInventory(b).replaceExistingItem(((Integer)entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(((Integer)entry.getKey()).intValue()), ((Integer)entry.getValue()).intValue()));
/*     */         }
/* 474 */         processing.put(b, r);
/* 475 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int invertBinary(String binary) {
/* 481 */     char[] chars = binary.toCharArray();
/* 482 */     int result = 0;
/* 483 */     int i = 6;
/* 484 */     for (char c : chars) {
/* 485 */       if (c == '1') {
/* 486 */         result = (int)(result + Math.pow(2.0D, i));
/*     */       }
/* 488 */       i--;
/*     */     } 
/* 490 */     return result;
/*     */   }
/*     */   
/*     */   public abstract String getInventoryTitle();
/*     */   
/*     */   public abstract ItemStack getProgressBar();
/*     */   
/*     */   public abstract void registerDefaultRecipes();
/*     */   
/*     */   public abstract int getEnergyConsumption();
/*     */   
/*     */   public abstract String getMachineIdentifier();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\narMachines\CreatorMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */