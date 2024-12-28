/*   */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*   */ 
/*   */ import java.util.Comparator;
/*   */ 
/*   */ public class ChestTerminalSorter
/*   */   implements Comparator<StoredItem>
/*   */ {
/*   */   public int compare(StoredItem item1, StoredItem item2) {
/* 9 */     return item2.getAmount() - item1.getAmount();
/*   */   }
/*   */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\ChestTerminalSorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */