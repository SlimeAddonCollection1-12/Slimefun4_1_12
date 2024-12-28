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
/*     */ public class AutoEnchanter
/*     */   extends AContainer
/*     */ {
/*  33 */   public static int max_emerald_enchantments = 2;
/*     */   
/*     */   public AutoEnchanter(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  36 */     super(category, item, name, recipeType, recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  41 */     return "&5自动附魔机";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  46 */     return new ItemStack(Material.GOLD_CHESTPLATE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */ 
/*     */   
/*     */   public int getEnergyConsumption() {
/*  54 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/*  60 */     if (isProcessing(b)) {
/*  61 */       int timeleft = ((Integer)progress.get(b)).intValue();
/*  62 */       if (timeleft > 0) {
/*  63 */         ItemStack item = getProgressBar().clone();
/*  64 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  65 */         ItemMeta im = item.getItemMeta();
/*  66 */         im.setDisplayName(" ");
/*  67 */         List<String> lore = new ArrayList<>();
/*  68 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  69 */         lore.add("");
/*  70 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/*  71 */         im.setLore(lore);
/*  72 */         item.setItemMeta(im);
/*     */         
/*  74 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/*  76 */         if (ChargableBlock.isChargable(b)) {
/*  77 */           if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*  78 */             return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*  79 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } else {
/*  81 */           progress.put(b, Integer.valueOf(timeleft - 1));
/*     */         } 
/*     */       } else {
/*  84 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/*  85 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/*  87 */         progress.remove(b);
/*  88 */         processing.remove(b);
/*     */       } 
/*     */     } else {
/*     */       
/*  92 */       MachineRecipe r = null;
/*     */       
/*  94 */       for (int slot : getInputSlots()) {
/*  95 */         ItemStack target = BlockStorage.getInventory(b).getItemInSlot((slot == getInputSlots()[0]) ? getInputSlots()[1] : getInputSlots()[0]);
/*     */         
/*  97 */         SlimefunItem sfTarget = SlimefunItem.getByItem(target);
/*  98 */         if (sfTarget != null && !sfTarget.isEnchantable())
/*     */           return; 
/* 100 */         ItemStack item = BlockStorage.getInventory(b).getItemInSlot(slot);
/*     */ 
/*     */         
/* 103 */         if (item != null && item.getType() == Material.ENCHANTED_BOOK && target != null) {
/* 104 */           Map<Enchantment, Integer> enchantments = new HashMap<>();
/* 105 */           Set<ItemEnchantment> enchantments2 = new HashSet<>();
/* 106 */           int amount = 0;
/* 107 */           int special_amount = 0;
/* 108 */           EnchantmentStorageMeta meta = (EnchantmentStorageMeta)item.getItemMeta();
/* 109 */           for (Map.Entry<Enchantment, Integer> e : (Iterable<Map.Entry<Enchantment, Integer>>)meta.getStoredEnchants().entrySet()) {
/* 110 */             if (((Enchantment)e.getKey()).canEnchantItem(target)) {
/* 111 */               amount++;
/* 112 */               enchantments.put(e.getKey(), e.getValue());
/*     */             } 
/*     */           } 
/* 115 */           if (Slimefun.isEmeraldEnchantsInstalled()) {
/* 116 */             for (ItemEnchantment enchantment : EmeraldEnchants.getInstance().getRegistry().getEnchantments(item)) {
/* 117 */               if (EmeraldEnchants.getInstance().getRegistry().isApplicable(target, enchantment.getEnchantment()) && EmeraldEnchants.getInstance().getRegistry().getEnchantmentLevel(target, enchantment.getEnchantment().getName()) < enchantment.getLevel()) {
/* 118 */                 amount++;
/* 119 */                 special_amount++;
/* 120 */                 enchantments2.add(enchantment);
/*     */               } 
/*     */             } 
/* 123 */             special_amount += EmeraldEnchants.getInstance().getRegistry().getEnchantments(target).size();
/*     */           } 
/* 125 */           if (amount > 0 && special_amount <= max_emerald_enchantments) {
/* 126 */             ItemStack newItem = target.clone();
/* 127 */             for (Map.Entry<Enchantment, Integer> e : enchantments.entrySet()) {
/* 128 */               newItem.addUnsafeEnchantment(e.getKey(), ((Integer)e.getValue()).intValue());
/*     */             }
/* 130 */             for (ItemEnchantment e : enchantments2) {
/* 131 */               EmeraldEnchants.getInstance().getRegistry().applyEnchantment(newItem, e.getEnchantment(), e.getLevel());
/*     */             }
/* 133 */             r = new MachineRecipe(75 * amount, new ItemStack[] { target, item }, new ItemStack[] { newItem, new ItemStack(Material.BOOK) });
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 139 */       if (r != null) {
/* 140 */         if (!fits(b, r.getOutput()))
/* 141 */           return;  for (int slot : getInputSlots()) {
/* 142 */           BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/*     */         }
/* 144 */         processing.put(b, r);
/* 145 */         progress.put(b, Integer.valueOf(r.getTicks()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpeed() {
/* 152 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/* 157 */     return "AUTO_ENCHANTER";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\AutoEnchanter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */