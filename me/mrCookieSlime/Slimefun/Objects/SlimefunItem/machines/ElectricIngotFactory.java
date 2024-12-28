/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class ElectricIngotFactory
/*    */   extends AContainer
/*    */ {
/*    */   public ElectricIngotFactory(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 15 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 20 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.ALUMINUM_DUST }, new ItemStack[] { SlimefunItems.ALUMINUM_INGOT }));
/* 21 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.COPPER_DUST }, new ItemStack[] { SlimefunItems.COPPER_INGOT }));
/* 22 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.GOLD_DUST }, new ItemStack[] { SlimefunItems.GOLD_4K }));
/* 23 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.IRON_DUST }, new ItemStack[] { new ItemStack(Material.IRON_INGOT) }));
/* 24 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.LEAD_DUST }, new ItemStack[] { SlimefunItems.LEAD_INGOT }));
/* 25 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.MAGNESIUM_DUST }, new ItemStack[] { SlimefunItems.MAGNESIUM_INGOT }));
/* 26 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.SILVER_DUST }, new ItemStack[] { SlimefunItems.SILVER_INGOT }));
/* 27 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.TIN_DUST }, new ItemStack[] { SlimefunItems.TIN_INGOT }));
/* 28 */     registerRecipe(new MachineRecipe(8, new ItemStack[] { SlimefunItems.ZINC_DUST }, new ItemStack[] { SlimefunItems.ZINC_INGOT }));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 33 */     return "ELECTRIC_INGOT_FACTORY";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\ElectricIngotFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */