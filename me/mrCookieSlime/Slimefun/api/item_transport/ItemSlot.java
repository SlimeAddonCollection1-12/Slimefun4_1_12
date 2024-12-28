/*    */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ItemSlot
/*    */ {
/*    */   public int slot;
/*    */   public ItemStack item;
/*    */   
/*    */   public ItemSlot(ItemStack item, int slot) {
/* 11 */     this.slot = slot;
/* 12 */     this.item = item;
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 16 */     return this.slot;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 20 */     return this.item;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\ItemSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */