/*    */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*    */ 
/*    */ public class RecipeSorter
/*    */   implements Comparator<Integer>
/*    */ {
/*    */   BlockMenu menu;
/*    */   
/*    */   public RecipeSorter(BlockMenu menu) {
/* 12 */     this.menu = menu;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compare(Integer slot1, Integer slot2) {
/* 17 */     return this.menu.getItemInSlot(slot1.intValue()).getAmount() - this.menu.getItemInSlot(slot2.intValue()).getAmount();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\RecipeSorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */