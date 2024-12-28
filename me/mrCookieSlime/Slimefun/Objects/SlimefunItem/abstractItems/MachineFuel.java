/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MachineFuel
/*    */ {
/*    */   int seconds;
/*    */   ItemStack fuel;
/*    */   ItemStack output;
/*    */   
/*    */   public MachineFuel(int seconds, ItemStack fuel) {
/* 12 */     this.seconds = seconds * 2;
/* 13 */     this.fuel = fuel;
/* 14 */     this.output = null;
/*    */   }
/*    */   
/*    */   public MachineFuel(int seconds, ItemStack fuel, ItemStack output) {
/* 18 */     this.seconds = seconds * 2;
/* 19 */     this.fuel = fuel;
/* 20 */     this.output = output;
/*    */   }
/*    */   
/*    */   public ItemStack getInput() {
/* 24 */     return this.fuel;
/*    */   }
/*    */   
/*    */   public ItemStack getOutput() {
/* 28 */     return this.output;
/*    */   }
/*    */   
/*    */   public int getTicks() {
/* 32 */     return this.seconds;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\MachineFuel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */