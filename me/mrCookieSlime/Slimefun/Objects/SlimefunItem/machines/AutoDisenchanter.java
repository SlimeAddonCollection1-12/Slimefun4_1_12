/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.EmeraldEnchants.EmeraldEnchants;
/*     */ import me.mrCookieSlime.EmeraldEnchants.ItemEnchantment;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ public class AutoDisenchanter
/*     */   extends AContainer
/*     */ {
/*     */   public AutoDisenchanter(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  35 */     super(category, item, name, recipeType, recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  40 */     return "&5自动卸魔机";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  45 */     return new ItemStack(Material.DIAMOND_CHESTPLATE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */ 
/*     */   
/*     */   public int getEnergyConsumption() {
/*  53 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/*  59 */     if (isProcessing(b)) {
/*  60 */       int timeleft = ((Integer)progress.get(b)).intValue();
/*  61 */       if (timeleft > 0) {
/*  62 */         ItemStack item = getProgressBar().clone();
/*  63 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  64 */         ItemMeta im = item.getItemMeta();
/*  65 */         im.setDisplayName(" ");
/*  66 */         List<String> lore = new ArrayList<>();
/*  67 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  68 */         lore.add("");
/*  69 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/*  70 */         im.setLore(lore);
/*  71 */         item.setItemMeta(im);
/*     */         
/*  73 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/*  75 */         if (ChargableBlock.isChargable(b)) {
/*  76 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  77 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*  78 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/*  80 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*     */       } else {
/*  83 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/*  84 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/*  86 */         progress.remove(b);
/*  87 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/*  91 */       MachineRecipe r = null;
/*  92 */       Map<Enchantment, Integer> enchantments = new HashMap<>();
/*  93 */       Set<ItemEnchantment> enchantments2 = new HashSet<>();
/*     */       
/*  95 */       for (int slot : getInputSlots()) {
/*  96 */         ItemStack target = BlockStorage.getInventory(b).getItemInSlot((slot == getInputSlots()[0]) ? getInputSlots()[1] : getInputSlots()[0]);
/*  97 */         ItemStack item = BlockStorage.getInventory(b).getItemInSlot(slot);
/*     */ 
/*     */         
/* 100 */         SlimefunItem sfItem = null;
/* 101 */         if (item != null && item.getType() != Material.BOOK) {
/* 102 */           sfItem = SlimefunItem.getByItem(item);
/*     */         }
/* 104 */         if (sfItem != null && !sfItem.isDisenchantable()) {
/*     */           return;
/*     */         }
/* 107 */         if (item != null && target != null && target.getType() == Material.BOOK) {
/* 108 */           int amount = 0;
/*     */           
/* 110 */           for (Map.Entry<Enchantment, Integer> e : (Iterable<Map.Entry<Enchantment, Integer>>)item.getEnchantments().entrySet()) {
/* 111 */             enchantments.put(e.getKey(), e.getValue());
/* 112 */             amount++;
/*     */           } 
/* 114 */           if (Slimefun.isEmeraldEnchantsInstalled()) {
/* 115 */             for (ItemEnchantment enchantment : EmeraldEnchants.getInstance().getRegistry().getEnchantments(item)) {
/* 116 */               amount++;
/* 117 */               enchantments2.add(enchantment);
/*     */             } 
/*     */           }
/* 120 */           if (amount > 0) {
/* 121 */             ItemStack newItem = item.clone();
/* 122 */             newItem.setAmount(1);
/* 123 */             ItemStack book = target.clone();
/* 124 */             book.setAmount(1);
/* 125 */             book.setType(Material.ENCHANTED_BOOK);
/* 126 */             EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
/* 127 */             for (Map.Entry<Enchantment, Integer> e : enchantments.entrySet()) {
/* 128 */               newItem.removeEnchantment(e.getKey());
/* 129 */               meta.addStoredEnchant(e.getKey(), ((Integer)e.getValue()).intValue(), true);
/*     */             } 
/* 131 */             book.setItemMeta((ItemMeta)meta);
/*     */             
/* 133 */             for (ItemEnchantment e : enchantments2) {
/* 134 */               EmeraldEnchants.getInstance().getRegistry().applyEnchantment(book, e.getEnchantment(), e.getLevel());
/* 135 */               EmeraldEnchants.getInstance().getRegistry().applyEnchantment(newItem, e.getEnchantment(), 0);
/*     */             } 
/* 137 */             r = new MachineRecipe(100 * amount, new ItemStack[] { target, item }, new ItemStack[] { newItem, book });
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 143 */       if (r != null) {
/* 144 */         if (!fits(b, r.getOutput()))
/* 145 */           return;  for (int slot : getInputSlots()) {
/* 146 */           BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/*     */         }
/* 148 */         processing.put(b, r);
/* 149 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpeed() {
/* 156 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 161 */     return "AUTO_DISENCHANTER";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\AutoDisenchanter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */