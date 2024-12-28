/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ReplacingAlloy
/*    */   extends ReplacingItem
/*    */ {
/*    */   public ReplacingAlloy(ItemStack item, String id, ItemStack[] recipe) {
/* 12 */     super(Categories.RESOURCES, item, id, RecipeType.SMELTERY, recipe);
/*    */   }
/*    */   
/*    */   public ReplacingAlloy(Category category, ItemStack item, String id, ItemStack[] recipe) {
/* 16 */     super(category, item, id, RecipeType.SMELTERY, recipe);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\ReplacingAlloy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */