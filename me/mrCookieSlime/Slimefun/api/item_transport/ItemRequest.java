/*    */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ItemRequest
/*    */ {
/*    */   ItemStack item;
/*    */   ItemTransportFlow flow;
/*    */   Location terminal;
/*    */   int slot;
/*    */   
/*    */   public ItemRequest(Location terminal, int slot, ItemStack item, ItemTransportFlow flow) {
/* 14 */     this.terminal = terminal;
/* 15 */     this.item = item;
/* 16 */     this.slot = slot;
/* 17 */     this.flow = flow;
/*    */   }
/*    */   
/*    */   public Location getTerminal() {
/* 21 */     return this.terminal;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 25 */     return this.item;
/*    */   }
/*    */   
/*    */   public ItemTransportFlow getDirection() {
/* 29 */     return this.flow;
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 33 */     return this.slot;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\ItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */