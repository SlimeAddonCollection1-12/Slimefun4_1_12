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
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.RecipeSorter;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public abstract class HeatedPressureChamber
/*     */   extends AContainer {
/*     */   public HeatedPressureChamber(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  40 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  42 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  46 */           HeatedPressureChamber.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  55 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  60 */           return new int[0];
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/*  65 */           if (flow.equals(ItemTransportFlow.WITHDRAW)) return HeatedPressureChamber.this.getOutputSlots();
/*     */           
/*  67 */           List<Integer> slots = new ArrayList<>();
/*     */           
/*  69 */           for (int slot : HeatedPressureChamber.this.getInputSlots()) {
/*  70 */             if (SlimefunManager.isItemSimiliar(menu.getItemInSlot(slot), item, true)) {
/*  71 */               slots.add(Integer.valueOf(slot));
/*     */             }
/*     */           } 
/*     */           
/*  75 */           if (slots.isEmpty()) {
/*  76 */             return HeatedPressureChamber.this.getInputSlots();
/*     */           }
/*     */           
/*  79 */           Collections.sort(slots, (Comparator<? super Integer>)new RecipeSorter(menu));
/*  80 */           return ArrayUtils.toPrimitive(slots.<Integer>toArray(new Integer[slots.size()]));
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*  85 */     registerDefaultRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {
/*  90 */     registerRecipe(45, new ItemStack[] { SlimefunItems.BUCKET_OF_OIL }, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.PLASTIC_SHEET, 8) });
/*  91 */     registerRecipe(30, new ItemStack[] { SlimefunItems.GOLD_24K, SlimefunItems.URANIUM }, new ItemStack[] { SlimefunItems.BLISTERING_INGOT });
/*  92 */     registerRecipe(30, new ItemStack[] { SlimefunItems.BLISTERING_INGOT, SlimefunItems.CARBONADO }, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_2 });
/*  93 */     registerRecipe(60, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_2, new ItemStack(Material.NETHER_STAR) }, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_3 });
/*  94 */     registerRecipe(90, new ItemStack[] { SlimefunItems.PLUTONIUM, SlimefunItems.URANIUM }, new ItemStack[] { SlimefunItems.BOOSTED_URANIUM });
/*  95 */     registerRecipe(60, new ItemStack[] { SlimefunItems.NETHER_ICE, SlimefunItems.PLUTONIUM }, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.ENRICHED_NETHER_ICE, 4) });
/*  96 */     registerRecipe(45, new ItemStack[] { SlimefunItems.ENRICHED_NETHER_ICE }, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.NETHER_ICE_COOLANT_CELL, 8) });
/*     */   }
/*     */   
/*     */   public String getInventoryTitle() {
/* 100 */     return "&c热压力室";
/*     */   }
/*     */   
/*     */   public ItemStack getProgressBar() {
/* 104 */     return new ItemStack(Material.FLINT_AND_STEEL);
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 108 */     return new int[] { 19, 20 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 112 */     return new int[] { 24, 25 };
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 117 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/* 121 */               HeatedPressureChamber.this.tick(b);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 130 */               return false;
/*     */             }
/*     */           } });
/*     */     
/* 134 */     super.register(slimefun);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/* 139 */     if (isProcessing(b)) {
/* 140 */       int timeleft = ((Integer)progress.get(b)).intValue();
/* 141 */       if (timeleft > 0) {
/* 142 */         ItemStack item = getProgressBar().clone();
/* 143 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 144 */         ItemMeta im = item.getItemMeta();
/* 145 */         im.setDisplayName(" ");
/* 146 */         List<String> lore = new ArrayList<>();
/* 147 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 148 */         lore.add("");
/* 149 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 150 */         im.setLore(lore);
/* 151 */         item.setItemMeta(im);
/*     */         
/* 153 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/* 155 */         if (ChargableBlock.isChargable(b)) {
/* 156 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 157 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 158 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/* 160 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*     */       } else {
/* 163 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/* 164 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/* 166 */         progress.remove(b);
/* 167 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/* 171 */       MachineRecipe r = null;
/* 172 */       Map<Integer, Integer> found = new HashMap<>();
/*     */       
/* 174 */       for (MachineRecipe recipe : this.recipes) {
/* 175 */         for (ItemStack input : recipe.getInput()) {
/*     */           
/* 177 */           for (int slot : getInputSlots()) {
/* 178 */             if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), input, true)) {
/* 179 */               found.put(Integer.valueOf(slot), Integer.valueOf(input.getAmount()));
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 184 */         if (found.size() == (recipe.getInput()).length) {
/* 185 */           r = recipe;
/*     */           break;
/*     */         } 
/* 188 */         found.clear();
/*     */       } 
/*     */       
/* 191 */       if (r != null) {
/* 192 */         if (!fits(b, r.getOutput()))
/* 193 */           return;  for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
/* 194 */           BlockStorage.getInventory(b).replaceExistingItem(((Integer)entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(((Integer)entry.getKey()).intValue()), ((Integer)entry.getValue()).intValue()));
/*     */         }
/* 196 */         processing.put(b, r);
/* 197 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 204 */     return "HEATED_PRESSURE_CHAMBER";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\HeatedPressureChamber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */