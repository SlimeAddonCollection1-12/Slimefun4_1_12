/*    */ package me.mrCookieSlime.Slimefun.Android.ScriptComparators;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Android.ProgrammableAndroid;
/*    */ 
/*    */ public class ScriptReputationSorter
/*    */   implements Comparator<Config>
/*    */ {
/*    */   ProgrammableAndroid android;
/*    */   
/*    */   public ScriptReputationSorter(ProgrammableAndroid programmableAndroid) {
/* 13 */     this.android = programmableAndroid;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compare(Config c1, Config c2) {
/* 18 */     return (int)(this.android.getScriptRating(c2) - this.android.getScriptRating(c1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Android\ScriptComparators\ScriptReputationSorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */