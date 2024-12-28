/*    */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class StoredItem
/*    */ {
/*    */   public int amount;
/*    */   public ItemStack item;
/*    */   
/*    */   public StoredItem(ItemStack item, int amount) {
/* 11 */     this.amount = amount;
/* 12 */     this.item = item;
/*    */   }
/*    */   
/*    */   public int getAmount() {
/* 16 */     return this.amount;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 20 */     return this.item;
/*    */   }
/*    */   
/*    */   public void add(int amount) {
/* 24 */     this.amount += amount;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\StoredItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */