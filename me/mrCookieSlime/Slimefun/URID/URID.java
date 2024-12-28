/*    */ package me.mrCookieSlime.Slimefun.URID;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class URID
/*    */ {
/*  8 */   public static Map<URID, Object> objects = new HashMap<>();
/*  9 */   public static Map<Integer, URID> ids = new HashMap<>();
/*    */   
/* 11 */   private static int next = 0;
/*    */   private int id;
/*    */   private boolean dirty;
/*    */   
/*    */   public URID(Object object, boolean dirty) {
/* 16 */     this.id = next;
/* 17 */     next++;
/* 18 */     objects.put(this, object);
/* 19 */     ids.put(Integer.valueOf(toInteger()), this);
/*    */   }
/*    */   
/*    */   public int toInteger() {
/* 23 */     return this.id;
/*    */   }
/*    */   
/*    */   public static URID nextURID(Object object, boolean dirty) {
/* 27 */     URID urid = new URID(object, dirty);
/* 28 */     return urid;
/*    */   }
/*    */   
/*    */   public static URID fromInteger(int id) {
/* 32 */     return ids.get(Integer.valueOf(id));
/*    */   }
/*    */   
/*    */   public static Object decode(URID urid) {
/* 36 */     return objects.get(urid);
/*    */   }
/*    */   
/*    */   public void markDirty() {
/* 40 */     if (this.dirty) {
/* 41 */       ids.remove(Integer.valueOf(toInteger()));
/* 42 */       objects.remove(this);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\URID\URID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */