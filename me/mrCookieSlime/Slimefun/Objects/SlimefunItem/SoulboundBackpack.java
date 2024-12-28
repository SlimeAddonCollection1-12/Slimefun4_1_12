/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class SoulboundBackpack
/*    */   extends SlimefunBackpack
/*    */ {
/*    */   public SoulboundBackpack(int size, Category category, ItemStack item, String id, ItemStack[] recipe) {
/* 11 */     super(size, category, item, id, RecipeType.MAGIC_WORKBENCH, recipe);
/*    */   }
/*    */   public SoulboundBackpack(int size, Category category, ItemStack item, String id, RecipeType type, ItemStack[] recipe) {
/* 14 */     super(size, category, item, id, type, recipe);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\SoulboundBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */