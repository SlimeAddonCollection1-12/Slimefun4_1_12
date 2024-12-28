/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Jetpack
/*    */   extends DamagableChargableItem
/*    */ {
/*    */   double thrust;
/*    */   
/*    */   public Jetpack(ItemStack item, String id, ItemStack[] recipe, double thrust) {
/* 13 */     super(Categories.TECH, item, id, RecipeType.ENHANCED_CRAFTING_TABLE, recipe, "Jetpack");
/* 14 */     this.thrust = thrust;
/*    */   }
/*    */   
/*    */   public double getThrust() {
/* 18 */     return this.thrust;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\Jetpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */