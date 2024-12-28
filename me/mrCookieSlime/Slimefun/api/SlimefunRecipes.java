/*    */ package me.mrCookieSlime.Slimefun.api;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlimefunRecipes
/*    */ {
/*    */   public static void registerMachineRecipe(String machine, int seconds, ItemStack[] input, ItemStack[] output) {
/* 11 */     for (SlimefunItem item : SlimefunItem.all) {
/* 12 */       if (item instanceof AContainer && (
/* 13 */         (AContainer)item).getMachineIdentifier().equals(machine))
/* 14 */         ((AContainer)item).registerRecipe(seconds, input, output); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\SlimefunRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */