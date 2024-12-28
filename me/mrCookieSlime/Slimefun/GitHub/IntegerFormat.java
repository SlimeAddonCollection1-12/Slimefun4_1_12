/*    */ package me.mrCookieSlime.Slimefun.GitHub;
/*    */ 
/*    */ import java.text.NumberFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ 
/*    */ public class IntegerFormat
/*    */ {
/* 11 */   private static SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*    */   
/*    */   public static String formatBigNumber(int i) {
/* 14 */     return NumberFormat.getNumberInstance(Locale.US).format(i);
/*    */   }
/*    */   
/*    */   public static Date parseGitHubDate(String str) {
/*    */     try {
/* 19 */       return date_format.parse(str.replace("T", " ").replace("Z", ""));
/* 20 */     } catch (ParseException e) {
/* 21 */       e.printStackTrace();
/* 22 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String timeDelta(Date date) {
/* 27 */     long timestamp = date.getTime();
/* 28 */     int hours = (int)((System.currentTimeMillis() - timestamp) / 3600000L);
/*    */     
/* 30 */     if (hours == 0) {
/* 31 */       return "> 1h";
/*    */     }
/* 33 */     if (hours / 24 == 0) {
/* 34 */       return (hours % 24) + "h";
/*    */     }
/* 36 */     if (hours % 24 == 0) {
/* 37 */       return (hours / 24) + "d";
/*    */     }
/*    */     
/* 40 */     return (hours / 24) + "d " + (hours % 24) + "h";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GitHub\IntegerFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */