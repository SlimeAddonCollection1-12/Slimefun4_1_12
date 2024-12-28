/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MachineRecipe
/*    */ {
/*    */   int ticks;
/*    */   ItemStack[] input;
/*    */   ItemStack[] output;
/*    */   
/*    */   public MachineRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
/* 12 */     this.ticks = seconds * 2;
/* 13 */     this.input = input;
/* 14 */     this.output = output;
/*    */   }
/*    */   
/*    */   public ItemStack[] getInput() {
/* 18 */     return this.input;
/*    */   }
/*    */   
/*    */   public ItemStack[] getOutput() {
/* 22 */     return this.output;
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


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\MachineRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */