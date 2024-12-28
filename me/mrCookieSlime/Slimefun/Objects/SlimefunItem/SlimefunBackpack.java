/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class SlimefunBackpack
/*    */   extends SlimefunItem
/*    */ {
/*    */   public int size;
/*    */   
/*    */   public SlimefunBackpack(int size, Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/* 13 */     super(category, item, id, recipeType, recipe);
/*    */     
/* 15 */     this.size = size;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\SlimefunBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */