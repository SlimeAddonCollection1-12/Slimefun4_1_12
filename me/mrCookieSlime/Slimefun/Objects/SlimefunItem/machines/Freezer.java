/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class Freezer
/*    */   extends AContainer
/*    */ {
/*    */   public Freezer(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 14 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 19 */     registerRecipe(2, new ItemStack[] { new ItemStack(Material.WATER_BUCKET) }, new ItemStack[] { new ItemStack(Material.BUCKET), new ItemStack(Material.ICE) });
/* 20 */     registerRecipe(8, new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) }, new ItemStack[] { new ItemStack(Material.BUCKET), new ItemStack(Material.OBSIDIAN) });
/* 21 */     registerRecipe(4, new ItemStack[] { new ItemStack(Material.ICE) }, new ItemStack[] { new ItemStack(Material.PACKED_ICE) });
/* 22 */     registerRecipe(6, new ItemStack[] { new ItemStack(Material.PACKED_ICE) }, new ItemStack[] { SlimefunItems.REACTOR_COOLANT_CELL });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 27 */     return "FREEZER";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\Freezer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */