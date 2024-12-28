/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.inventory.FurnaceRecipe;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ 
/*    */ public abstract class ElectricFurnace
/*    */   extends AContainer
/*    */ {
/*    */   public ElectricFurnace(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 17 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 22 */     Iterator<Recipe> iterator = Bukkit.recipeIterator();
/* 23 */     while (iterator.hasNext()) {
/* 24 */       Recipe r = iterator.next();
/* 25 */       if (r instanceof FurnaceRecipe) {
/* 26 */         registerRecipe(4, new ItemStack[] { ((FurnaceRecipe)r).getInput() }, new ItemStack[] { r.getResult() });
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 33 */     return "ELECTRIC_FURNACE";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ElectricFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */