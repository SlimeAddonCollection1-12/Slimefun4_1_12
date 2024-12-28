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
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public abstract class AutoAnvil
/*     */   extends AContainer
/*     */ {
/*     */   public AutoAnvil(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  27 */     super(category, item, name, recipeType, recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  32 */     return "自动铁砧";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  37 */     return new ItemStack(Material.IRON_PICKAXE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */ 
/*     */   
/*     */   public int getSpeed() {
/*  45 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/*  50 */     return "AUTO_ANVIL";
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getRepairFactor();
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/*  58 */     if (isProcessing(b)) {
/*  59 */       int timeleft = ((Integer)progress.get(b)).intValue();
/*  60 */       if (timeleft > 0) {
/*  61 */         ItemStack item = getProgressBar().clone();
/*  62 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  63 */         ItemMeta im = item.getItemMeta();
/*  64 */         im.setDisplayName(" ");
/*  65 */         List<String> lore = new ArrayList<>();
/*  66 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  67 */         lore.add("");
/*  68 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/*  69 */         im.setLore(lore);
/*  70 */         item.setItemMeta(im);
/*     */         
/*  72 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/*  74 */         if (ChargableBlock.isChargable(b)) {
/*  75 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  76 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*  77 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/*  79 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*     */       } else {
/*  82 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/*  83 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/*  85 */         progress.remove(b);
/*  86 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/*  90 */       MachineRecipe r = null;
/*     */       
/*  92 */       for (int slot : getInputSlots()) {
/*  93 */         ItemStack target = BlockStorage.getInventory(b).getItemInSlot((slot == getInputSlots()[0]) ? getInputSlots()[1] : getInputSlots()[0]);
/*  94 */         ItemStack item = BlockStorage.getInventory(b).getItemInSlot(slot);
/*  95 */         if (item != null && 
/*  96 */           item.getType().getMaxDurability() > 0 && item.getDurability() > 0) {
/*  97 */           if (SlimefunManager.isItemSimiliar(target, SlimefunItems.DUCT_TAPE, true)) {
/*  98 */             ItemStack newItem = item.clone();
/*  99 */             short durability = (short)(newItem.getDurability() - item.getType().getMaxDurability() / getRepairFactor());
/* 100 */             if (durability < 0) durability = 0; 
/* 101 */             newItem.setDurability(durability);
/* 102 */             r = new MachineRecipe(30, new ItemStack[] { target, item }, new ItemStack[] { newItem });
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 109 */       if (r != null) {
/* 110 */         if (!fits(b, r.getOutput()))
/* 111 */           return;  for (int slot : getInputSlots()) {
/* 112 */           BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/*     */         }
/* 114 */         processing.put(b, r);
/* 115 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\AutoAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */