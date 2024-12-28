/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.RecipeSorter;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public abstract class ElectricSmeltery
/*     */   extends AContainer
/*     */ {
/*  38 */   public static Map<Block, MachineRecipe> processing = new HashMap<>();
/*  39 */   public static Map<Block, Integer> progress = new HashMap<>();
/*     */   
/*  41 */   protected List<MachineRecipe> recipes = new ArrayList<>();
/*     */   
/*  43 */   private static final int[] border = new int[] { 4, 5, 6, 7, 8, 13, 31, 40, 41, 42, 43, 44 };
/*  44 */   private static final int[] border_in = new int[] { 0, 1, 2, 3, 9, 12, 18, 21, 27, 30, 36, 37, 38, 39 };
/*  45 */   private static final int[] border_out = new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
/*     */   
/*     */   public ElectricSmeltery(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  48 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  50 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  54 */           ElectricSmeltery.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  63 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  68 */           return new int[0];
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/*  73 */           if (flow.equals(ItemTransportFlow.WITHDRAW)) return ElectricSmeltery.this.getOutputSlots();
/*     */           
/*  75 */           List<Integer> slots = new ArrayList<>();
/*     */           
/*  77 */           for (int slot : ElectricSmeltery.this.getInputSlots()) {
/*  78 */             if (SlimefunManager.isItemSimiliar(menu.getItemInSlot(slot), item, true)) {
/*  79 */               slots.add(Integer.valueOf(slot));
/*     */             }
/*     */           } 
/*     */           
/*  83 */           if (slots.isEmpty()) {
/*  84 */             return ElectricSmeltery.this.getInputSlots();
/*     */           }
/*     */           
/*  87 */           Collections.sort(slots, (Comparator<? super Integer>)new RecipeSorter(menu));
/*  88 */           return ArrayUtils.toPrimitive(slots.<Integer>toArray(new Integer[slots.size()]));
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*  93 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 102 */             BlockMenu inv = BlockStorage.getInventory(b);
/* 103 */             if (inv != null) {
/* 104 */               for (int slot : ElectricSmeltery.this.getInputSlots()) {
/* 105 */                 if (inv.getItemInSlot(slot) != null) {
/* 106 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 107 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/* 110 */               for (int slot : ElectricSmeltery.this.getOutputSlots()) {
/* 111 */                 if (inv.getItemInSlot(slot) != null) {
/* 112 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/* 113 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 117 */             ElectricSmeltery.progress.remove(b.getLocation());
/* 118 */             ElectricSmeltery.processing.remove(b.getLocation());
/* 119 */             return true;
/*     */           }
/*     */         });
/*     */     
/* 123 */     registerDefaultRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 128 */     for (int i : border) {
/* 129 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 134 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 139 */     for (int i : border_in) {
/* 140 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 145 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 150 */     for (int i : border_out) {
/* 151 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 156 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 162 */     preset.addItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */         {
/*     */           
/*     */           public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */           {
/* 167 */             return false;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 172 */     for (int i : getOutputSlots()) {
/* 173 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */             {
/* 177 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 182 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/* 190 */     return "&c电力冶炼机";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/* 195 */     return new ItemStack(Material.FLINT_AND_STEEL);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 200 */     return new int[] { 10, 11, 19, 20, 28, 29 };
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getOutputSlots() {
/* 205 */     return new int[] { 24, 25 };
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 210 */     return "ELECTRIC_SMELTERY";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ElectricSmeltery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */