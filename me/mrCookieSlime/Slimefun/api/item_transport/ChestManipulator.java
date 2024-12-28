/*    */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ChestManipulator
/*    */ {
/* 11 */   public static List<CargoTransportEvent> listeners = new ArrayList<>();
/*    */   
/*    */   public static void registerListener(CargoTransportEvent listener) {
/* 14 */     listeners.add(listener);
/*    */   }
/*    */   
/*    */   public static ItemStack trigger(Block b, int slot, ItemStack prev, ItemStack next) {
/* 18 */     for (CargoTransportEvent listener : listeners) {
/* 19 */       next = listener.onEvent(b, slot, prev, next);
/*    */     }
/*    */     
/* 22 */     return next;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\ChestManipulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */