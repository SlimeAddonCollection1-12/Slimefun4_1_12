/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class FoodFabricator
/*    */   extends AContainer
/*    */ {
/*    */   public FoodFabricator(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 14 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 19 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.WHEAT) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD2 });
/* 20 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.CARROT_ITEM) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD3 });
/* 21 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.POTATO_ITEM) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD4 });
/* 22 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.SEEDS) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD5 });
/* 23 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.BEETROOT) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD6 });
/* 24 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.MELON) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD7 });
/* 25 */     registerRecipe(12, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.APPLE) }, new ItemStack[] { SlimefunItems.ORGANIC_FOOD8 });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 30 */     return "FOOD_FABRICATOR";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\FoodFabricator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */