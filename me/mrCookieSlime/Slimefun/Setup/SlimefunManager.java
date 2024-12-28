/*     */ package me.mrCookieSlime.Slimefun.Setup;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunArmorPiece;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.VanillaItem;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlimefunManager
/*     */ {
/*     */   public static SlimefunStartup plugin;
/*     */   public static String PREFIX;
/*  26 */   public static Map<EntityType, List<ItemStack>> drops = new HashMap<>();
/*     */   
/*     */   public static void registerArmorSet(ItemStack baseComponent, ItemStack[] items, String idSyntax, PotionEffect[][] effects, boolean special, boolean slimefun) {
/*  29 */     String[] components = { "_HELMET", "_CHESTPLATE", "_LEGGINGS", "_BOOTS" };
/*  30 */     Category cat = special ? Categories.MAGIC_ARMOR : Categories.ARMOR;
/*  31 */     List<ItemStack[]> recipes = (List)new ArrayList<>();
/*  32 */     recipes.add(new ItemStack[] { baseComponent, baseComponent, baseComponent, baseComponent, null, baseComponent, null, null, null });
/*  33 */     recipes.add(new ItemStack[] { baseComponent, null, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent });
/*  34 */     recipes.add(new ItemStack[] { baseComponent, baseComponent, baseComponent, baseComponent, null, baseComponent, baseComponent, null, baseComponent });
/*  35 */     recipes.add(new ItemStack[] { null, null, null, baseComponent, null, baseComponent, baseComponent, null, baseComponent });
/*  36 */     for (int i = 0; i < 4; i++) {
/*  37 */       if (effects.length - 1 >= i) if ((effects[i]).length > 0) { (new SlimefunArmorPiece(cat, items[i], idSyntax + components[i], RecipeType.ARMOR_FORGE, recipes.get(i), effects[i])).register(slimefun); }
/*  38 */         else { (new SlimefunItem(cat, items[i], idSyntax + components[i], RecipeType.ARMOR_FORGE, recipes.get(i))).register(slimefun); }
/*     */          
/*     */     } 
/*     */   }
/*     */   public static void registerArmorSet(ItemStack baseComponent, ItemStack[] items, String idSyntax, boolean slimefun, boolean vanilla) {
/*  43 */     String[] components = { "_HELMET", "_CHESTPLATE", "_LEGGINGS", "_BOOTS" };
/*  44 */     Category cat = Categories.ARMOR;
/*  45 */     List<ItemStack[]> recipes = (List)new ArrayList<>();
/*  46 */     recipes.add(new ItemStack[] { baseComponent, baseComponent, baseComponent, baseComponent, null, baseComponent, null, null, null });
/*  47 */     recipes.add(new ItemStack[] { baseComponent, null, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent });
/*  48 */     recipes.add(new ItemStack[] { baseComponent, baseComponent, baseComponent, baseComponent, null, baseComponent, baseComponent, null, baseComponent });
/*  49 */     recipes.add(new ItemStack[] { null, null, null, baseComponent, null, baseComponent, baseComponent, null, baseComponent });
/*  50 */     for (int i = 0; i < 4; i++) {
/*  51 */       if (vanilla) {
/*  52 */         (new VanillaItem(cat, items[i], idSyntax + components[i], RecipeType.ARMOR_FORGE, recipes.get(i))).register(slimefun);
/*     */       } else {
/*  54 */         (new SlimefunItem(cat, items[i], idSyntax + components[i], RecipeType.ARMOR_FORGE, recipes.get(i))).register(slimefun);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*  59 */   public static List<Material> data_safe = Arrays.asList(new Material[] { Material.WOOL, Material.CARPET, Material.STAINED_CLAY, Material.STAINED_GLASS, Material.STAINED_GLASS_PANE, Material.INK_SACK, Material.STONE, Material.COAL, Material.SKULL_ITEM, Material.RAW_FISH, Material.COOKED_FISH });
/*     */   
/*     */   public static boolean isItemSimiliar(ItemStack item, ItemStack SFitem, boolean lore) {
/*  62 */     return isItemSimiliar(item, SFitem, lore, DataType.IF_COLORED);
/*     */   }
/*     */   
/*     */   public enum DataType
/*     */   {
/*  67 */     ALWAYS,
/*  68 */     NEVER,
/*  69 */     IF_COLORED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isItemSimiliar(ItemStack item, ItemStack SFitem, boolean lore, DataType data) {
/*  75 */     if (item == null) return (SFitem == null); 
/*  76 */     if (SFitem == null) return false;
/*     */     
/*  78 */     if (item.getType() == SFitem.getType() && item.getAmount() >= SFitem.getAmount()) {
/*  79 */       if (data.equals(DataType.ALWAYS) || (data.equals(DataType.IF_COLORED) && data_safe.contains(item.getType()))) {
/*  80 */         if (data_safe.contains(item.getType())) {
/*  81 */           if (item.getData().getData() != SFitem.getData().getData() && (
/*  82 */             SFitem.getDurability() != item.getData().getData() || SFitem.getData().getData() != item.getDurability())) return false;
/*     */         
/*     */         }
/*  85 */         else if (data.equals(DataType.ALWAYS) && item.getDurability() != SFitem.getDurability()) {
/*  86 */           return false;
/*     */         } 
/*     */       }
/*     */       
/*  90 */       if (item.hasItemMeta() && SFitem.hasItemMeta()) {
/*  91 */         if (item.getItemMeta().hasDisplayName() && SFitem.getItemMeta().hasDisplayName()) {
/*  92 */           if (item.getItemMeta().getDisplayName().equals(SFitem.getItemMeta().getDisplayName())) {
/*  93 */             if (lore) {
/*  94 */               if (item.getItemMeta().hasLore() && SFitem.getItemMeta().hasLore()) {
/*  95 */                 return equalsLore(item.getItemMeta().getLore(), SFitem.getItemMeta().getLore());
/*     */               }
/*  97 */               return (!item.getItemMeta().hasLore() && !SFitem.getItemMeta().hasLore());
/*     */             } 
/*  99 */             return true;
/*     */           } 
/* 101 */           return false;
/*     */         } 
/* 103 */         if (!item.getItemMeta().hasDisplayName() && !SFitem.getItemMeta().hasDisplayName()) {
/* 104 */           if (lore) {
/* 105 */             if (item.getItemMeta().hasLore() && SFitem.getItemMeta().hasLore()) {
/* 106 */               return equalsLore(item.getItemMeta().getLore(), SFitem.getItemMeta().getLore());
/*     */             }
/* 108 */             return (!item.getItemMeta().hasLore() && !SFitem.getItemMeta().hasLore());
/*     */           } 
/* 110 */           return true;
/*     */         } 
/* 112 */         return false;
/*     */       } 
/* 114 */       return (!item.hasItemMeta() && !SFitem.hasItemMeta());
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean equalsLore(List<String> lore, List<String> lore2) {
/* 120 */     String string1 = "", string2 = "";
/* 121 */     for (String string : lore) {
/* 122 */       if (!string.startsWith("&e&e&7")) string1 = string1 + "-NEW LINE-" + string; 
/*     */     } 
/* 124 */     for (String string : lore2) {
/* 125 */       if (!string.startsWith("&e&e&7")) string2 = string2 + "-NEW LINE-" + string; 
/*     */     } 
/* 127 */     return string1.equals(string2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Setup\SlimefunManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */