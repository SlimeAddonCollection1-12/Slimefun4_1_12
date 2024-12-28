/*     */ package me.mrCookieSlime.Slimefun.api.energy;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemEnergy
/*     */ {
/*     */   public static float getStoredEnergy(ItemStack item) {
/*  19 */     if (item == null || item.getType() == null || item.getType().equals(Material.AIR)) return 0.0F; 
/*  20 */     if (!item.hasItemMeta() || !item.getItemMeta().hasLore()) return 0.0F;
/*     */     
/*  22 */     for (String line : item.getItemMeta().getLore()) {
/*  23 */       if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&c&o&8⇨ &e⚡ &7")) && line.contains(" / ") && line.endsWith(" J")) {
/*  24 */         return Float.valueOf(line.split(" / ")[0].replace(ChatColor.translateAlternateColorCodes('&', "&c&o&8⇨ &e⚡ &7"), "")).floatValue();
/*     */       }
/*     */     } 
/*     */     
/*  28 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static float getMaxEnergy(ItemStack item) {
/*  32 */     if (item == null || item.getType() == null || item.getType().equals(Material.AIR)) return 0.0F; 
/*  33 */     if (!item.hasItemMeta() || !item.getItemMeta().hasLore()) return 0.0F;
/*     */     
/*  35 */     for (String line : item.getItemMeta().getLore()) {
/*  36 */       if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&c&o&8⇨ &e⚡ &7")) && line.contains(" / ") && line.endsWith(" J")) {
/*  37 */         return Float.valueOf(line.split(" / ")[1].replace(" J", "")).floatValue();
/*     */       }
/*     */     } 
/*     */     
/*  41 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static float addStoredEnergy(ItemStack item, float energy) {
/*  45 */     if (item == null || item.getType() == null || item.getType().equals(Material.AIR)) return 0.0F; 
/*  46 */     if (!item.hasItemMeta() || !item.getItemMeta().hasLore()) return 0.0F;
/*     */     
/*  48 */     float rest = 0.0F;
/*  49 */     float capacity = getMaxEnergy(item);
/*     */     
/*  51 */     if (capacity == 0.0F) {
/*  52 */       return rest;
/*     */     }
/*     */     
/*  55 */     float stored = getStoredEnergy(item);
/*     */     
/*  57 */     if (stored + energy > capacity) {
/*  58 */       rest = stored + energy - capacity;
/*  59 */       stored = capacity;
/*     */     }
/*  61 */     else if (stored + energy < 0.0F) {
/*  62 */       stored = 0.0F;
/*     */     } else {
/*     */       
/*  65 */       stored += energy;
/*     */     } 
/*     */     
/*  68 */     List<String> lore = item.getItemMeta().getLore();
/*     */     
/*  70 */     int index = -1;
/*  71 */     for (int i = 0; i < lore.size(); i++) {
/*  72 */       String line = lore.get(i);
/*  73 */       if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&c&o&8⇨ &e⚡ &7")) && line.contains(" / ") && line.endsWith(" J")) {
/*  74 */         index = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  79 */     BigDecimal decimal = (new BigDecimal(stored)).setScale(2, 4);
/*     */     
/*  81 */     lore.set(index, ChatColor.translateAlternateColorCodes('&', "&c&o&8⇨ &e⚡ &7") + decimal.floatValue() + " / " + capacity + " J");
/*     */     
/*  83 */     ItemMeta im = item.getItemMeta();
/*  84 */     im.setLore(lore);
/*  85 */     item.setItemMeta(im);
/*  86 */     return rest;
/*     */   }
/*     */   
/*     */   public static ItemStack chargeItem(ItemStack item, float energy) {
/*  90 */     addStoredEnergy(item, energy);
/*  91 */     return item;
/*     */   }
/*     */   
/*     */   public static void chargeInventory(Player p, float energy) {
/*  95 */     p.getInventory().setItemInMainHand(chargeItem(p.getInventory().getItemInMainHand(), energy));
/*  96 */     p.getInventory().setItemInOffHand(chargeItem(p.getInventory().getItemInOffHand(), energy));
/*  97 */     p.getInventory().setHelmet(chargeItem(p.getInventory().getHelmet(), energy));
/*  98 */     p.getInventory().setChestplate(chargeItem(p.getInventory().getChestplate(), energy));
/*  99 */     p.getInventory().setLeggings(chargeItem(p.getInventory().getLeggings(), energy));
/* 100 */     p.getInventory().setBoots(chargeItem(p.getInventory().getBoots(), energy));
/*     */     
/* 102 */     PlayerInventory.update(p);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\energy\ItemEnergy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */