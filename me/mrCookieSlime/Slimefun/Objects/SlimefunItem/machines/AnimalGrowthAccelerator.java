/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
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
/*     */ import org.bukkit.entity.Ageable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class AnimalGrowthAccelerator
/*     */   extends SlimefunItem {
/*  35 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
/*     */   
/*     */   public AnimalGrowthAccelerator(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  38 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  40 */     new BlockMenuPreset(name, "&b动物成长加速器")
/*     */       {
/*     */         public void init()
/*     */         {
/*  44 */           AnimalGrowthAccelerator.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  53 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  58 */           if (flow.equals(ItemTransportFlow.INSERT)) return AnimalGrowthAccelerator.this.getInputSlots(); 
/*  59 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/*  63 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  72 */             me.mrCookieSlime.Slimefun.holograms.AnimalGrowthAccelerator.getArmorStand(b).remove();
/*  73 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  74 */             if (inv != null) {
/*  75 */               for (int slot : AnimalGrowthAccelerator.this.getInputSlots()) {
/*  76 */                 if (inv.getItemInSlot(slot) != null) {
/*  77 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  78 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/*  82 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/*  89 */     for (int i : border) {
/*  90 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/*  95 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnergyConsumption() {
/* 103 */     return 14;
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 107 */     return new int[] { 10, 11, 12, 13, 14, 15, 16 };
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 112 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/*     */               try {
/* 117 */                 AnimalGrowthAccelerator.this.tick(b);
/* 118 */               } catch (Exception e) {
/* 119 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 129 */               return true;
/*     */             }
/*     */           } });
/*     */     
/* 133 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   protected void tick(Block b) throws Exception {
/* 137 */     for (Entity n : me.mrCookieSlime.Slimefun.holograms.AnimalGrowthAccelerator.getArmorStand(b).getNearbyEntities(3.0D, 3.0D, 3.0D)) {
/* 138 */       if (n instanceof Ageable && !((Ageable)n).isAdult())
/* 139 */         for (int slot : getInputSlots()) {
/* 140 */           if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.ORGANIC_FOOD, false)) {
/* 141 */             if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 142 */               return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 143 */             BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 144 */             ((Ageable)n).setAge(((Ageable)n).getAge() + 2000);
/* 145 */             if (((Ageable)n).getAge() > 0) ((Ageable)n).setAge(0); 
/* 146 */             ParticleEffect.VILLAGER_HAPPY.display(((LivingEntity)n).getEyeLocation(), 0.2F, 0.2F, 0.2F, 0.0F, 8);
/*     */             return;
/*     */           } 
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\AnimalGrowthAccelerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */