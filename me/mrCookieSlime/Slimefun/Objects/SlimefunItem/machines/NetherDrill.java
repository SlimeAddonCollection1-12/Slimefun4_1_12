/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
/*    */ import me.mrCookieSlime.Slimefun.GEO.OreGenSystem;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.ADrill;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class NetherDrill
/*    */   extends ADrill
/*    */ {
/*    */   public NetherDrill(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 16 */     super(category, item, name, recipeType, recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public OreGenResource getOreGenResource() {
/* 21 */     return OreGenSystem.getResource("下界玄冰");
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack[] getOutputItems() {
/* 26 */     return new ItemStack[] { SlimefunItems.NETHER_ICE };
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProcessingTime() {
/* 31 */     return 24;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInventoryTitle() {
/* 36 */     return "&4下界矿钻";
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getProgressBar() {
/* 41 */     return new ItemStack(Material.DIAMOND_PICKAXE);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMachineIdentifier() {
/* 46 */     return "NETHER_DRILL";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\NetherDrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */