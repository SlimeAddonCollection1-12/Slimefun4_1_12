/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class DamagableChargableItem
/*    */   extends SlimefunItem
/*    */ {
/*    */   String chargeType;
/*    */   
/*    */   public DamagableChargableItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, String chargeType) {
/* 13 */     super(category, item, id, recipeType, recipe);
/* 14 */     this.chargeType = chargeType;
/*    */   }
/*    */   
/*    */   public DamagableChargableItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, String chargeType, String[] keys, Object[] values) {
/* 18 */     super(category, item, id, recipeType, recipe, keys, values);
/* 19 */     this.chargeType = chargeType;
/*    */   }
/*    */   public String getChargeType() {
/* 22 */     return this.chargeType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\DamagableChargableItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */