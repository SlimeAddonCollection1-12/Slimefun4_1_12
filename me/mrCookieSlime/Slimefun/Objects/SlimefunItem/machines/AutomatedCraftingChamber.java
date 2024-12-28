/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItemSerializer;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.RecipeSorter;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public abstract class AutomatedCraftingChamber
/*     */   extends SlimefunItem
/*     */ {
/*  43 */   private static final int[] border = new int[] { 0, 1, 3, 4, 5, 7, 8, 13, 14, 15, 16, 17, 50, 51, 52, 53 };
/*  44 */   private static final int[] border_in = new int[] { 9, 10, 11, 12, 13, 18, 22, 27, 31, 36, 40, 45, 46, 47, 48, 49 };
/*  45 */   private static final int[] border_out = new int[] { 23, 24, 25, 26, 32, 35, 41, 42, 43, 44 };
/*     */   
/*  47 */   public static Map<String, ItemStack> recipes = new HashMap<>();
/*     */   
/*     */   public AutomatedCraftingChamber(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  50 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  52 */     new BlockMenuPreset(name, "&6自动合成机")
/*     */       {
/*     */         public void init()
/*     */         {
/*  56 */           AutomatedCraftingChamber.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */         
/*     */         public void newInstance(final BlockMenu menu, final Block b) {
/*  61 */           if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "enabled") == null || BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals("false")) {
/*  62 */             menu.replaceExistingItem(6, (ItemStack)new CustomItem(new MaterialData(Material.SULPHUR), "&7启动状态: &4✘", new String[] { "", "&e> 点击激活这个机器" }));
/*  63 */             menu.addMenuClickHandler(6, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  67 */                     BlockStorage.addBlockInfo(b, "enabled", "true");
/*  68 */                     AutomatedCraftingChamber.null.this.newInstance(menu, b);
/*  69 */                     return false;
/*     */                   }
/*     */                 });
/*     */           } else {
/*     */             
/*  74 */             menu.replaceExistingItem(6, (ItemStack)new CustomItem(new MaterialData(Material.REDSTONE), "&7启动状态: &2✔", new String[] { "", "&e> 点击停止这个机器" }));
/*  75 */             menu.addMenuClickHandler(6, new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  79 */                     BlockStorage.addBlockInfo(b, "enabled", "false");
/*  80 */                     AutomatedCraftingChamber.null.this.newInstance(menu, b);
/*  81 */                     return false;
/*     */                   }
/*     */                 });
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  89 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  94 */           return new int[0];
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/*  99 */           if (flow.equals(ItemTransportFlow.WITHDRAW)) return AutomatedCraftingChamber.this.getOutputSlots();
/*     */           
/* 101 */           List<Integer> slots = new ArrayList<>();
/* 102 */           for (int slot : AutomatedCraftingChamber.this.getInputSlots()) {
/* 103 */             if (menu.getItemInSlot(slot) != null) slots.add(Integer.valueOf(slot)); 
/*     */           } 
/* 105 */           Collections.sort(slots, (Comparator<? super Integer>)new RecipeSorter(menu));
/* 106 */           return ArrayUtils.toPrimitive(slots.<Integer>toArray(new Integer[slots.size()]));
/*     */         }
/*     */       };
/*     */     
/* 110 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/* 114 */             BlockStorage.addBlockInfo(b, "enabled", "false");
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 119 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 120 */             if (inv != null) {
/* 121 */               for (int slot : AutomatedCraftingChamber.this.getInputSlots()) {
/* 122 */                 if (inv.getItemInSlot(slot) != null) {
/* 123 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 124 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 127 */               for (int slot : AutomatedCraftingChamber.this.getOutputSlots()) {
/* 128 */                 if (inv.getItemInSlot(slot) != null) {
/* 129 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 130 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 134 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 141 */     for (int i : border) {
/* 142 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 147 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 152 */     for (int i : border_in) {
/* 153 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)11), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 158 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 163 */     for (int i : border_out) {
/* 164 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 169 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 175 */     for (int i : getOutputSlots()) {
/* 176 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */             {
/* 180 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 185 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 190 */     preset.addItem(2, (ItemStack)new CustomItem(new MaterialData(Material.WORKBENCH), "&e合成蓝本", new String[] { "", "&b放入合成方式示例(按合成方式摆放)", "&4只能是强化合成台所属的合成公式" }), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 195 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getEnergyConsumption();
/*     */   
/*     */   public int[] getInputSlots() {
/* 204 */     return new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 208 */     return new int[] { 33, 34 };
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/* 212 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 213 */     Inventory inv = Bukkit.createInventory(null, size);
/* 214 */     for (int i = 0; i < size; i++) {
/* 215 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 217 */     for (int slot : getOutputSlots()) {
/* 218 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 220 */     return inv;
/*     */   }
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 224 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushItems(Block b, ItemStack[] items) {
/* 228 */     Inventory inv = inject(b);
/* 229 */     inv.addItem(items);
/*     */     
/* 231 */     for (int slot : getOutputSlots()) {
/* 232 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 238 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 242 */               AutomatedCraftingChamber.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 251 */               return false;
/*     */             }
/*     */           } });
/*     */     
/* 255 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   protected void tick(Block b) {
/* 259 */     if (BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals("false"))
/* 260 */       return;  if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*     */       return; 
/* 262 */     BlockMenu menu = BlockStorage.getInventory(b);
/*     */     
/* 264 */     StringBuilder builder = new StringBuilder();
/* 265 */     int i = 0;
/* 266 */     for (int j = 0; j < 9; j++) {
/* 267 */       if (i > 0) {
/* 268 */         builder.append(" </slot> ");
/*     */       }
/*     */       
/* 271 */       ItemStack item = menu.getItemInSlot(getInputSlots()[j]);
/*     */       
/* 273 */       if (item != null && item.getAmount() == 1)
/*     */         return; 
/* 275 */       builder.append(CustomItemSerializer.serialize(item, new CustomItemSerializer.ItemFlag[] { CustomItemSerializer.ItemFlag.DATA, CustomItemSerializer.ItemFlag.ITEMMETA_DISPLAY_NAME, CustomItemSerializer.ItemFlag.ITEMMETA_LORE, CustomItemSerializer.ItemFlag.MATERIAL }));
/*     */       
/* 277 */       i++;
/*     */     } 
/*     */     
/* 280 */     String input = builder.toString();
/*     */ 
/*     */     
/* 283 */     ItemStack output = ((ItemStack)recipes.get(input)).clone();
/*     */     
/* 285 */     if (recipes.containsKey(input) && fits(b, new ItemStack[] { output })) {
/* 286 */       pushItems(b, new ItemStack[] { output });
/* 287 */       ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 288 */       for (int k = 0; k < 9; k++) {
/* 289 */         if (menu.getItemInSlot(getInputSlots()[k]) != null) menu.replaceExistingItem(getInputSlots()[k], InvUtils.decreaseItem(menu.getItemInSlot(getInputSlots()[k]), 1)); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\AutomatedCraftingChamber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */