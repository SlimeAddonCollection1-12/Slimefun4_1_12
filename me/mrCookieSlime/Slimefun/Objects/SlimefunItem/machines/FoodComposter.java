/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class FoodComposter
/*    */   extends AContainer
/*    */ {
/*    */   public FoodComposter(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 13 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 18 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD2 }, new ItemStack[] { SlimefunItems.FERTILIZER2 });
/* 19 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD3 }, new ItemStack[] { SlimefunItems.FERTILIZER3 });
/* 20 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD4 }, new ItemStack[] { SlimefunItems.FERTILIZER4 });
/* 21 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD5 }, new ItemStack[] { SlimefunItems.FERTILIZER5 });
/* 22 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD6 }, new ItemStack[] { SlimefunItems.FERTILIZER6 });
/* 23 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD7 }, new ItemStack[] { SlimefunItems.FERTILIZER7 });
/* 24 */     registerRecipe(30, new ItemStack[] { SlimefunItems.ORGANIC_FOOD8 }, new ItemStack[] { SlimefunItems.FERTILIZER8 });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 29 */     return "FOOD_COMPOSTER";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\FoodComposter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */