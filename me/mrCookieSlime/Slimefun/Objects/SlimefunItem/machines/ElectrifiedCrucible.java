/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class ElectrifiedCrucible
/*    */   extends AContainer
/*    */ {
/*    */   public ElectrifiedCrucible(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 13 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 18 */     registerRecipe(10, new ItemStack[] { new ItemStack(Material.BUCKET), new ItemStack(Material.COBBLESTONE, 16) }new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
/* 19 */     registerRecipe(8, new ItemStack[] { new ItemStack(Material.BUCKET), new ItemStack(Material.HARD_CLAY, 12) }new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
/* 20 */     registerRecipe(10, new ItemStack[] { new ItemStack(Material.BUCKET), new ItemStack(Material.LEAVES, 16) }new ItemStack[] { new ItemStack(Material.WATER_BUCKET) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 25 */     return "ELECTRIFIED_CRUCIBLE";
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getProgressBar() {
/* 30 */     return new ItemStack(Material.FLINT_AND_STEEL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInventoryTitle() {
/* 35 */     return "&4电力坩埚";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ElectrifiedCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */