/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public abstract class ElectricDustWasher
/*     */   extends AContainer
/*     */ {
/*     */   public ElectricDustWasher(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  28 */     super(category, item, name, recipeType, recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  33 */     return "&b电力洗粉机";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  38 */     return new ItemStack(Material.GOLD_SPADE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */   
/*     */   public static boolean legacy_dust_washer = false;
/*     */   
/*     */   public abstract int getSpeed();
/*     */   
/*     */   protected void tick(Block b) {
/*  49 */     if (isProcessing(b)) {
/*  50 */       int timeleft = ((Integer)progress.get(b)).intValue();
/*  51 */       if (timeleft > 0 && getSpeed() < 10) {
/*  52 */         ItemStack item = getProgressBar().clone();
/*  53 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  54 */         ItemMeta im = item.getItemMeta();
/*  55 */         im.setDisplayName(" ");
/*  56 */         List<String> lore = new ArrayList<>();
/*  57 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  58 */         lore.add("");
/*  59 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/*  60 */         im.setLore(lore);
/*  61 */         item.setItemMeta(im);
/*     */         
/*  63 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/*  65 */         if (ChargableBlock.isChargable(b)) {
/*  66 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  67 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*  68 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/*  70 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*  72 */       } else if (ChargableBlock.isChargable(b)) {
/*  73 */         if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  74 */           return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*     */         
/*  76 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/*  77 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/*  79 */         progress.remove(b);
/*  80 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/*  84 */       for (int slot : getInputSlots()) {
/*  85 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.SIFTED_ORE, true)) {
/*  86 */           if (!legacy_dust_washer) {
/*  87 */             boolean empty_slot = false;
/*  88 */             for (int output_slot : getOutputSlots()) {
/*  89 */               if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
/*  90 */                 empty_slot = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/*  94 */             if (!empty_slot)
/*     */               return; 
/*     */           } 
/*  97 */           ItemStack adding = SlimefunItems.IRON_DUST;
/*  98 */           if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.GOLD_DUST; }
/*  99 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.ALUMINUM_DUST; }
/* 100 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.COPPER_DUST; }
/* 101 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.ZINC_DUST; }
/* 102 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.TIN_DUST; }
/* 103 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.MAGNESIUM_DUST; }
/* 104 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.LEAD_DUST; }
/* 105 */           else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.SILVER_DUST; }
/*     */           
/* 107 */           MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { adding });
/* 108 */           if (legacy_dust_washer && !fits(b, r.getOutput()))
/* 109 */             return;  BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 110 */           processing.put(b, r);
/* 111 */           progress.put(b, Integer.valueOf(r.getTicks()));
/*     */           break;
/*     */         } 
/* 114 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.PULVERIZED_ORE, true)) {
/* 115 */           MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { SlimefunItems.PURE_ORE_CLUSTER });
/* 116 */           if (!fits(b, r.getOutput()))
/* 117 */             return;  BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 118 */           processing.put(b, r);
/* 119 */           progress.put(b, Integer.valueOf(r.getTicks()));
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 128 */     return "ELECTRIC_DUST_WASHER";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ElectricDustWasher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */