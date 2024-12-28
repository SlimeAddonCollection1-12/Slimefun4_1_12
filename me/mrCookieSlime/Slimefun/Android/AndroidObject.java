/*    */ package me.mrCookieSlime.Slimefun.Android;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class AndroidObject
/*    */ {
/*    */   ProgrammableAndroid android;
/*    */   Block b;
/*    */   
/*    */   public AndroidObject(ProgrammableAndroid android, Block b) {
/* 11 */     this.android = android;
/* 12 */     this.b = b;
/*    */   }
/*    */   
/*    */   public ProgrammableAndroid getAndroid() {
/* 16 */     return this.android;
/*    */   }
/*    */   
/*    */   public Block getBlock() {
/* 20 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Android\AndroidObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */