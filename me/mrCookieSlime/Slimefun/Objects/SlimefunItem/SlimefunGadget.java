/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlimefunGadget
/*    */   extends SlimefunItem
/*    */ {
/*    */   List<ItemStack[]> recipes;
/*    */   List<ItemStack> display_recipes;
/*    */   
/*    */   public SlimefunGadget(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack[] machineRecipes) {
/* 17 */     super(category, item, id, recipeType, recipe);
/* 18 */     this.recipes = (List)new ArrayList<>();
/* 19 */     this.display_recipes = new ArrayList<>();
/* 20 */     for (ItemStack i : machineRecipes) {
/* 21 */       this.recipes.add(new ItemStack[] { i });
/* 22 */       this.display_recipes.add(i);
/*    */     } 
/*    */   }
/*    */   
/*    */   public SlimefunGadget(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, ItemStack[] machineRecipes, String[] keys, Object[] values) {
/* 27 */     super(category, item, id, recipeType, recipe, keys, values);
/* 28 */     this.recipes = (List)new ArrayList<>();
/* 29 */     this.display_recipes = new ArrayList<>();
/* 30 */     for (ItemStack i : machineRecipes) {
/* 31 */       this.recipes.add(new ItemStack[] { i });
/* 32 */       this.display_recipes.add(i);
/*    */     } 
/*    */   }
/*    */   
/*    */   public List<ItemStack[]> getRecipes() {
/* 37 */     return this.recipes;
/*    */   }
/*    */   
/*    */   public List<ItemStack> getDisplayRecipes() {
/* 41 */     return this.display_recipes;
/*    */   }
/*    */   
/*    */   public void addRecipe(ItemStack input, ItemStack output) {
/* 45 */     this.recipes.add(new ItemStack[] { input });
/* 46 */     this.recipes.add(new ItemStack[] { output });
/* 47 */     this.display_recipes.add(input);
/* 48 */     this.display_recipes.add(output);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\SlimefunGadget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */