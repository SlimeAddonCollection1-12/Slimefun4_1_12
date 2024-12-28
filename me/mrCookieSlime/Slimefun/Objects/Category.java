/*     */ package me.mrCookieSlime.Slimefun.Objects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.URID.URID;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Category
/*     */ {
/*  32 */   public static List<Category> list = new ArrayList<>();
/*     */ 
/*     */   
/*     */   private ItemStack item;
/*     */ 
/*     */   
/*     */   private List<SlimefunItem> items;
/*     */ 
/*     */   
/*     */   private URID urid;
/*     */ 
/*     */   
/*     */   private int tier;
/*     */ 
/*     */   
/*     */   public Category(ItemStack item) {
/*  48 */     this.item = item;
/*  49 */     this.items = new ArrayList<>();
/*  50 */     this.urid = URID.nextURID(this, false);
/*  51 */     this.tier = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Category(ItemStack item, int tier) {
/*  65 */     this.item = item;
/*  66 */     this.items = new ArrayList<>();
/*  67 */     this.urid = URID.nextURID(this, false);
/*  68 */     this.tier = tier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register() {
/*  79 */     list.add(this);
/*  80 */     Collections.sort(list, new CategorySorter());
/*     */     
/*  82 */     if (this instanceof SeasonCategory) {
/*  83 */       if (((SeasonCategory)this).isUnlocked()) Slimefun.current_categories.add(this); 
/*     */     } else {
/*  85 */       Slimefun.current_categories.add(this);
/*  86 */     }  Collections.sort(Slimefun.current_categories, new CategorySorter());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Category> list() {
/*  98 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(SlimefunItem item) {
/* 109 */     this.items.add(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItem() {
/* 120 */     return this.item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SlimefunItem> getItems() {
/* 131 */     return this.items;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Category getByItem(ItemStack item) {
/* 143 */     for (Category c : list) {
/* 144 */       if (c.getItem().isSimilar(item)) return c; 
/*     */     } 
/* 146 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URID getURID() {
/* 157 */     return this.urid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTier() {
/* 168 */     return this.tier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class CategorySorter
/*     */     implements Comparator<Category>
/*     */   {
/*     */     public int compare(Category c1, Category c2) {
/* 181 */       return Integer.compare(c1.getTier(), c2.getTier());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\Category.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */