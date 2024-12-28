/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ChargingBench
/*     */   extends AContainer
/*     */ {
/*     */   public ChargingBench(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  22 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  24 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  28 */           ChargingBench.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  37 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  42 */           if (flow.equals(ItemTransportFlow.INSERT)) return ChargingBench.this.getInputSlots(); 
/*  43 */           return ChargingBench.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  50 */     return "&3充电架";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  55 */     return new ItemStack(Material.GOLD_PICKAXE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnergyConsumption() {
/*  60 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */   
/*     */   protected void tick(Block b) {
/*  67 */     if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*     */       return; 
/*  69 */     for (int slot : getInputSlots()) {
/*  70 */       ItemStack stack = BlockStorage.getInventory(b).getItemInSlot(slot);
/*  71 */       if (ItemEnergy.getMaxEnergy(stack) > 0.0F) {
/*  72 */         if (ItemEnergy.getStoredEnergy(stack) < ItemEnergy.getMaxEnergy(stack)) {
/*     */           
/*  74 */           ChargableBlock.addCharge(b, -getEnergyConsumption());
/*  75 */           float rest = ItemEnergy.addStoredEnergy(stack, (getEnergyConsumption() / 2));
/*  76 */           if (rest > 0.0F) {
/*  77 */             if (fits(b, new ItemStack[] { stack })) {
/*  78 */               pushItems(b, new ItemStack[] { stack });
/*  79 */               BlockStorage.getInventory(b).replaceExistingItem(slot, null);
/*     */             } else {
/*     */               
/*  82 */               BlockStorage.getInventory(b).replaceExistingItem(slot, stack);
/*     */             } 
/*     */           } else {
/*     */             
/*  86 */             BlockStorage.getInventory(b).replaceExistingItem(slot, stack);
/*     */           }
/*     */         
/*  89 */         } else if (fits(b, new ItemStack[] { stack })) {
/*  90 */           pushItems(b, new ItemStack[] { stack });
/*  91 */           BlockStorage.getInventory(b).replaceExistingItem(slot, null);
/*     */         } else {
/*     */           
/*  94 */           BlockStorage.getInventory(b).replaceExistingItem(slot, stack);
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpeed() {
/* 103 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 108 */     return "CHARGING_BENCH";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ChargingBench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */