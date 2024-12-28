/*    */ package me.mrCookieSlime.Slimefun.Objects;
/*    */ 
/*    */ public class Charge {
/*    */   double charge;
/*    */   double capacity;
/*    */   
/*    */   public Charge(double charge, double capacity) {
/*  8 */     this.charge = charge;
/*  9 */     this.capacity = capacity;
/*    */   }
/*    */   
/*    */   public double getStoredEnergy() {
/* 13 */     return this.charge;
/*    */   }
/*    */   
/*    */   public double getCapacity() {
/* 17 */     return this.capacity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\Charge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */