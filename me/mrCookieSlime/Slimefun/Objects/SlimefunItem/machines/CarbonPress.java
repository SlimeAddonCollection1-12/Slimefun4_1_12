/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ public abstract class CarbonPress
/*    */   extends AContainer
/*    */ {
/*    */   public CarbonPress(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 16 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerDefaultRecipes() {
/* 22 */     registerRecipe(15, new ItemStack[] { (ItemStack)new CustomItem((new MaterialData(Material.COAL, (byte)1)).toItemStack(1), 4) }new ItemStack[] { new ItemStack(Material.COAL) });
/* 23 */     registerRecipe(20, new ItemStack[] { (ItemStack)new CustomItem(new ItemStack(Material.COAL), 8) }new ItemStack[] { SlimefunItems.CARBON });
/* 24 */     registerRecipe(30, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.CARBON, 4) }new ItemStack[] { SlimefunItems.COMPRESSED_CARBON });
/* 25 */     registerRecipe(60, new ItemStack[] { SlimefunItems.CARBON_CHUNK, SlimefunItems.SYNTHETIC_DIAMOND }, new ItemStack[] { SlimefunItems.RAW_CARBONADO });
/* 26 */     registerRecipe(60, new ItemStack[] { SlimefunItems.CARBON_CHUNK }, new ItemStack[] { SlimefunItems.SYNTHETIC_DIAMOND });
/* 27 */     registerRecipe(90, new ItemStack[] { SlimefunItems.RAW_CARBONADO }, new ItemStack[] { SlimefunItems.CARBONADO });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 32 */     return "CARBON_PRESS";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\CarbonPress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */