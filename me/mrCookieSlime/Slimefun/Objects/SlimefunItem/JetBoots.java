/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class JetBoots
/*    */   extends DamagableChargableItem
/*    */ {
/*    */   double speed;
/*    */   
/*    */   public JetBoots(ItemStack item, String id, ItemStack[] recipe, double speed) {
/* 13 */     super(Categories.TECH, item, id, RecipeType.ENHANCED_CRAFTING_TABLE, recipe, "Jet Boots");
/* 14 */     this.speed = speed;
/*    */   }
/*    */   
/*    */   public double getSpeed() {
/* 18 */     return this.speed;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\JetBoots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */