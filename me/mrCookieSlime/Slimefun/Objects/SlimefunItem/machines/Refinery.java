/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*    */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ 
/*    */ public abstract class Refinery
/*    */   extends AContainer
/*    */ {
/*    */   public Refinery(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 27 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInventoryTitle() {
/* 32 */     return "&c石油精炼器";
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getProgressBar() {
/* 37 */     return new ItemStack(Material.FLINT_AND_STEEL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {}
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 45 */     return "REFINERY";
/*    */   }
/*    */ 
/*    */   
/*    */   protected void tick(Block b) {
/* 50 */     if (isProcessing(b)) {
/* 51 */       int timeleft = ((Integer)progress.get(b)).intValue();
/* 52 */       if (timeleft > 0) {
/* 53 */         ItemStack item = getProgressBar().clone();
/* 54 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 55 */         ItemMeta im = item.getItemMeta();
/* 56 */         im.setDisplayName(" ");
/* 57 */         List<String> lore = new ArrayList<>();
/* 58 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 59 */         lore.add("");
/* 60 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 61 */         im.setLore(lore);
/* 62 */         item.setItemMeta(im);
/*    */         
/* 64 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*    */         
/* 66 */         if (ChargableBlock.isChargable(b)) {
/* 67 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 68 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 69 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*    */         } else {
/* 71 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*    */         } 
/*    */       } else {
/* 74 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/* 75 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*    */         
/* 77 */         progress.remove(b);
/* 78 */         processing.remove(b);
/*    */       } 
/*    */     } else {
/*    */       
/* 82 */       for (int slot : getInputSlots()) {
/* 83 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.BUCKET_OF_OIL, true)) {
/* 84 */           MachineRecipe r = new MachineRecipe(40, new ItemStack[0], new ItemStack[] { SlimefunItems.BUCKET_OF_FUEL });
/* 85 */           if (!fits(b, r.getOutput()))
/* 86 */             return;  BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 87 */           processing.put(b, r);
/* 88 */           progress.put(b, Integer.valueOf(r.getTicks()));
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\Refinery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */