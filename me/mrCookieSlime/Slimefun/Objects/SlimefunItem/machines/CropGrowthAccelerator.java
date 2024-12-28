/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public abstract class CropGrowthAccelerator
/*     */   extends SlimefunItem
/*     */ {
/*  35 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
/*     */   
/*  37 */   public static final Map<Material, Integer> crops = new HashMap<>();
/*     */   
/*     */   static {
/*  40 */     crops.put(Material.CROPS, Integer.valueOf(7));
/*  41 */     crops.put(Material.POTATO, Integer.valueOf(7));
/*  42 */     crops.put(Material.CARROT, Integer.valueOf(7));
/*  43 */     crops.put(Material.NETHER_WARTS, Integer.valueOf(3));
/*  44 */     crops.put(Material.BEETROOT_BLOCK, Integer.valueOf(3));
/*  45 */     crops.put(Material.COCOA, Integer.valueOf(8));
/*     */   }
/*     */   
/*     */   public CropGrowthAccelerator(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  49 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  51 */     new BlockMenuPreset(name, "&b植物生长加速器")
/*     */       {
/*     */         public void init()
/*     */         {
/*  55 */           CropGrowthAccelerator.this.constructMenu(this);
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
/*  69 */           if (flow.equals(ItemTransportFlow.INSERT)) return CropGrowthAccelerator.this.getInputSlots(); 
/*  70 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/*  74 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  83 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  84 */             if (inv != null) {
/*  85 */               for (int slot : CropGrowthAccelerator.this.getInputSlots()) {
/*  86 */                 if (inv.getItemInSlot(slot) != null) {
/*  87 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  88 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/*  92 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/*  99 */     for (int i : border) {
/* 100 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 105 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 117 */     return new int[] { 10, 11, 12, 13, 14, 15, 16 };
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 122 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/*     */               try {
/* 127 */                 CropGrowthAccelerator.this.tick(b);
/* 128 */               } catch (Exception e) {
/* 129 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 139 */               return true;
/*     */             }
/*     */           } });
/*     */     
/* 143 */     super.register(slimefun);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tick(Block b) throws Exception {
/* 148 */     int work = 0;
/*     */     int x;
/* 150 */     label44: for (x = -getRadius(); x <= getRadius(); x++) {
/* 151 */       for (int z = -getRadius(); z <= getRadius(); z++) {
/* 152 */         Block block = b.getRelative(x, 0, z);
/* 153 */         if (crops.containsKey(block.getType()) && 
/* 154 */           block.getData() < ((Integer)crops.get(block.getType())).intValue())
/*     */         {
/* 156 */           for (int slot : getInputSlots()) {
/* 157 */             if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.FERTILIZER, false)) {
/* 158 */               if (work > getSpeed() - 1)
/* 159 */                 break label44;  if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 160 */                 break label44;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*     */               
/* 162 */               if (block.getType().equals(Material.COCOA)) {
/* 163 */                 block.setData((byte)(block.getData() + 4));
/*     */               } else {
/*     */                 
/* 166 */                 block.setData((byte)(block.getData() + 1));
/*     */               } 
/*     */               
/* 169 */               ParticleEffect.VILLAGER_HAPPY.display(block.getLocation().add(0.5D, 0.5D, 0.5D), 0.1F, 0.1F, 0.1F, 0.0F, 4);
/* 170 */               work++;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     if (work > 0)
/* 180 */       for (int slot : getInputSlots()) {
/* 181 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.FERTILIZER, false)) {
/* 182 */           BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/*     */           break;
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public abstract int getEnergyConsumption();
/*     */   
/*     */   public abstract int getRadius();
/*     */   
/*     */   public abstract int getSpeed();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\CropGrowthAccelerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */