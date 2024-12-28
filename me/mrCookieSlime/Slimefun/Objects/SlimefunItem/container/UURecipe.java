/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.container;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class UURecipe
/*    */ {
/*    */   int ticks;
/*    */   ItemStack[] input;
/*    */   int uu;
/*    */   
/*    */   public UURecipe(int seconds, ItemStack[] input, int uuAmount) {
/* 12 */     this.ticks = seconds * 2;
/* 13 */     this.input = input;
/* 14 */     this.uu = uuAmount;
/*    */   }
/*    */   
/*    */   public ItemStack[] getInput() {
/* 18 */     return this.input;
/*    */   }
/*    */   
/*    */   public int getUUAmount() {
/* 22 */     return this.uu;
/*    */   }
/*    */   
/*    */   public void setTicks(int ticks) {
/* 26 */     this.ticks = ticks;
/*    */   }
/*    */   
/*    */   public int getTicks() {
/* 30 */     return this.ticks;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\container\UURecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */