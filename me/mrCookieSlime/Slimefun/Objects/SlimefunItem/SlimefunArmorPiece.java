/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ public class SlimefunArmorPiece
/*    */   extends SlimefunItem
/*    */ {
/*    */   PotionEffect[] effects;
/*    */   
/*    */   public SlimefunArmorPiece(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, PotionEffect[] effects) {
/* 14 */     super(category, item, id, recipeType, recipe);
/* 15 */     this.effects = effects;
/*    */   }
/*    */   
/*    */   public SlimefunArmorPiece(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, PotionEffect[] effects, String[] keys, Object[] values) {
/* 19 */     super(category, item, id, recipeType, recipe, keys, values);
/* 20 */     this.effects = effects;
/*    */   }
/*    */   public PotionEffect[] getEffects() {
/* 23 */     return this.effects;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\SlimefunArmorPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */