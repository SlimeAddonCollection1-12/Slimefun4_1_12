/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class MultiTool
/*    */   extends DamagableChargableItem
/*    */ {
/*    */   List<Integer> modes;
/*    */   
/*    */   public MultiTool(ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, String[] keys, Object[] values) {
/* 17 */     super(Categories.TECH, item, id, recipeType, recipe, "Multi Tool", keys, values);
/*    */   }
/*    */ 
/*    */   
/*    */   public void create() {
/* 22 */     List<Integer> list = new ArrayList<>();
/* 23 */     for (int i = 0; i < 50; i++) {
/* 24 */       if (Slimefun.getItemValue(getID(), "mode." + i + ".enabled") != null && (
/* 25 */         (Boolean)Slimefun.getItemValue(getID(), "mode." + i + ".enabled")).booleanValue()) list.add(Integer.valueOf(i));
/*    */     
/*    */     } 
/* 28 */     this.modes = list;
/*    */   }
/*    */   
/*    */   public List<Integer> getModes() {
/* 32 */     return this.modes;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\MultiTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */