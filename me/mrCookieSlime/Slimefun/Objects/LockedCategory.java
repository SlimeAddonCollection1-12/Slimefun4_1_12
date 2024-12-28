/*     */ package me.mrCookieSlime.Slimefun.Objects;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.entity.Player;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LockedCategory
/*     */   extends Category
/*     */ {
/*     */   private List<Category> parents;
/*     */   
/*     */   public LockedCategory(ItemStack item, Category... parents) {
/*  43 */     super(item);
/*  44 */     this.parents = Arrays.asList(parents);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public LockedCategory(ItemStack item, int tier, Category... parents) {
/*  61 */     super(item, tier);
/*  62 */     this.parents = Arrays.asList(parents);
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
/*     */   public List<Category> getParents() {
/*  75 */     return this.parents;
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
/*     */   public void addParent(Category category) {
/*  88 */     if (category == this) throw new IllegalArgumentException("Category '" + getItem().getItemMeta().getDisplayName() + "' cannot be a parent of itself."); 
/*  89 */     this.parents.add(category);
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
/*     */   public void removeParent(Category category) {
/* 102 */     this.parents.remove(category);
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
/*     */   public boolean hasUnlocked(Player p) {
/* 114 */     for (Category category : this.parents) {
/* 115 */       for (SlimefunItem item : category.getItems()) {
/* 116 */         if (Slimefun.isEnabled(p, item.getItem(), false) && Slimefun.hasPermission(p, item, false) && 
/* 117 */           item.getResearch() != null && 
/* 118 */           !item.getResearch().hasUnlocked(p)) return false;
/*     */       
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\LockedCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */