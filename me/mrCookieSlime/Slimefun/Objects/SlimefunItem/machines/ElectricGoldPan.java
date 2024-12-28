/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public abstract class ElectricGoldPan
/*     */   extends AContainer
/*     */ {
/*     */   public ElectricGoldPan(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  28 */     super(category, item, name, recipeType, recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  33 */     return "&6电力淘洗机";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  38 */     return new ItemStack(Material.DIAMOND_SPADE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */ 
/*     */   
/*     */   public abstract int getSpeed();
/*     */   
/*     */   protected void tick(Block b) {
/*  48 */     if (isProcessing(b)) {
/*  49 */       int timeleft = ((Integer)progress.get(b)).intValue();
/*  50 */       if (timeleft > 0 && getSpeed() < 10) {
/*  51 */         ItemStack item = getProgressBar().clone();
/*  52 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  53 */         ItemMeta im = item.getItemMeta();
/*  54 */         im.setDisplayName(" ");
/*  55 */         List<String> lore = new ArrayList<>();
/*  56 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  57 */         lore.add("");
/*  58 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/*  59 */         im.setLore(lore);
/*  60 */         item.setItemMeta(im);
/*     */         
/*  62 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/*  64 */         if (ChargableBlock.isChargable(b)) {
/*  65 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  66 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*  67 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/*  69 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*  71 */       } else if (ChargableBlock.isChargable(b)) {
/*  72 */         if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  73 */           return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*     */         
/*  75 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/*  76 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/*  78 */         progress.remove(b);
/*  79 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/*  83 */       for (int slot : getInputSlots()) {
/*  84 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.GRAVEL), true)) {
/*  85 */           ItemStack output = SlimefunItems.SIFTED_ORE;
/*  86 */           if (CSCoreLib.randomizer().nextInt(100) < 16) output = new ItemStack(Material.FLINT); 
/*  87 */           if (CSCoreLib.randomizer().nextInt(100) < 16) output = new ItemStack(Material.CLAY_BALL);
/*     */           
/*  89 */           MachineRecipe r = new MachineRecipe(3 / getSpeed(), new ItemStack[0], new ItemStack[] { output });
/*  90 */           if (!fits(b, r.getOutput()))
/*  91 */             return;  BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/*  92 */           processing.put(b, r);
/*  93 */           progress.put(b, Integer.valueOf(r.getTicks()));
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 102 */     return "ELECTRIC_GOLD_PAN";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ElectricGoldPan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */