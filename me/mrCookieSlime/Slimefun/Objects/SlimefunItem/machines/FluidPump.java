/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Block.Vein;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public class FluidPump
/*     */   extends SlimefunItem
/*     */ {
/*  41 */   public static Map<Block, MachineRecipe> processing = new HashMap<>();
/*  42 */   public static Map<Block, Integer> progress = new HashMap<>();
/*     */   
/*  44 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44, 22 };
/*  45 */   private static final int[] border_in = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
/*  46 */   private static final int[] border_out = new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
/*     */   
/*     */   public FluidPump(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  49 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  51 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  55 */           FluidPump.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  64 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  69 */           if (flow.equals(ItemTransportFlow.INSERT)) return FluidPump.this.getInputSlots(); 
/*  70 */           return FluidPump.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/*  77 */     for (int i : border) {
/*  78 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/*  83 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  88 */     for (int i : border_in) {
/*  89 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/*  94 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  99 */     for (int i : border_out) {
/* 100 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 105 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 111 */     for (int i : getOutputSlots()) {
/* 112 */       preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */           {
/*     */             public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */             {
/* 116 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/* 121 */               return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 128 */     return new int[] { 19, 20 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 132 */     return new int[] { 24, 25 };
/*     */   }
/*     */   
/*     */   public String getInventoryTitle() {
/* 136 */     return "&9流体泵";
/*     */   }
/*     */   
/*     */   protected void tick(Block b) {
/* 140 */     Block fluid = b.getRelative(BlockFace.DOWN);
/* 141 */     if (fluid.getType().equals(Material.STATIONARY_LAVA)) {
/* 142 */       for (int slot : getInputSlots()) {
/* 143 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BUCKET), true)) {
/* 144 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*     */             return; 
/* 146 */           ItemStack output = new ItemStack(Material.LAVA_BUCKET);
/*     */           
/* 148 */           if (!fits(b, new ItemStack[] { output }))
/*     */             return; 
/* 150 */           ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 151 */           BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 152 */           pushItems(b, new ItemStack[] { output });
/*     */           
/* 154 */           List<Location> list = new ArrayList<>();
/* 155 */           list.add(fluid.getLocation());
/* 156 */           Vein.calculate(fluid.getLocation(), fluid.getLocation(), list, 64);
/* 157 */           ((Location)list.get(list.size() - 1)).getBlock().setType(Material.AIR);
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 163 */     } else if (fluid.getType().equals(Material.STATIONARY_WATER)) {
/* 164 */       for (int slot : getInputSlots()) {
/* 165 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BUCKET), true)) {
/* 166 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*     */             return; 
/* 168 */           ItemStack output = new ItemStack(Material.WATER_BUCKET);
/*     */           
/* 170 */           if (!fits(b, new ItemStack[] { output }))
/*     */             return; 
/* 172 */           ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 173 */           BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 174 */           pushItems(b, new ItemStack[] { output });
/*     */           
/* 176 */           fluid.setType(Material.AIR);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getEnergyConsumption() {
/* 185 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 190 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 194 */               FluidPump.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 203 */               return true;
/*     */             }
/*     */           } });
/*     */     
/* 207 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/* 211 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 212 */     Inventory inv = Bukkit.createInventory(null, size);
/* 213 */     for (int i = 0; i < size; i++) {
/* 214 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 216 */     for (int slot : getOutputSlots()) {
/* 217 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 219 */     return inv;
/*     */   }
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 223 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushItems(Block b, ItemStack[] items) {
/* 227 */     Inventory inv = inject(b);
/* 228 */     inv.addItem(items);
/*     */     
/* 230 */     for (int slot : getOutputSlots())
/* 231 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\FluidPump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */