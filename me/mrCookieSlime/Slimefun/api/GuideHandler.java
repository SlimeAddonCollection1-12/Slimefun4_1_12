/*    */ package me.mrCookieSlime.Slimefun.api;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.PlayerRunnable;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunGuide;
/*    */ import me.mrCookieSlime.Slimefun.URID.URID;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class GuideHandler
/*    */ {
/* 17 */   URID urid = URID.nextURID(this, false);
/*    */ 
/*    */   
/*    */   public URID getURID() {
/* 21 */     return this.urid;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void addEntry(List<String> paramList1, List<String> paramList2);
/*    */ 
/*    */   
/*    */   public abstract PlayerRunnable getRunnable();
/*    */ 
/*    */   
/*    */   public PlayerRunnable getRunnable(boolean book) {
/* 32 */     return getRunnable();
/*    */   } public abstract int getTier(); public abstract boolean trackHistory();
/*    */   public abstract int next(Player paramPlayer, int paramInt, ChestMenu paramChestMenu);
/*    */   public void run(Player p, boolean survival, boolean book) {
/* 36 */     getRunnable(book).run(p);
/*    */     
/* 38 */     if (survival && trackHistory())
/* 39 */       SlimefunGuide.addToHistory(p, getURID()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\GuideHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */