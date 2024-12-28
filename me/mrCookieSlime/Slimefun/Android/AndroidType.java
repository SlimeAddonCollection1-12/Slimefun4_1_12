/*    */ package me.mrCookieSlime.Slimefun.Android;
/*    */ 
/*    */ public enum AndroidType
/*    */ {
/*  5 */   NONE,
/*  6 */   MINER,
/*  7 */   FARMER,
/*  8 */   ADVANCED_FARMER,
/*  9 */   WOODCUTTER,
/* 10 */   FIGHTER,
/* 11 */   FISHERMAN,
/* 12 */   NON_FIGHTER;
/*    */   
/*    */   public boolean isType(AndroidType type) {
/* 15 */     return (type.equals(NONE) || type.equals(this) || (type.equals(NON_FIGHTER) && !equals(FIGHTER)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Android\AndroidType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */