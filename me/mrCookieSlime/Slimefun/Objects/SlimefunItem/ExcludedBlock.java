/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Interfaces.NotPlaceable;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ExcludedBlock
/*    */   extends SlimefunItem
/*    */   implements NotPlaceable {
/*    */   public ExcludedBlock(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/* 12 */     super(category, item, id, recipeType, recipe);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\ExcludedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */