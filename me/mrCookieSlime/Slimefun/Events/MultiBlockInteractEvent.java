/*    */ package me.mrCookieSlime.Slimefun.Events;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.MultiBlock;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class MultiBlockInteractEvent
/*    */   extends Event
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   Player p;
/*    */   MultiBlock mb;
/*    */   Block b;
/*    */   boolean cancelled;
/*    */   
/*    */   public HandlerList getHandlers() {
/* 21 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 25 */     return handlers;
/*    */   }
/*    */   
/*    */   public MultiBlockInteractEvent(Player p, MultiBlock mb, Block clicked) {
/* 29 */     this.p = p;
/* 30 */     this.mb = mb;
/* 31 */     this.b = clicked;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 35 */     return this.p;
/*    */   }
/*    */   
/*    */   public MultiBlock getMultiBlock() {
/* 39 */     return this.mb;
/*    */   }
/*    */   
/*    */   public Block getClickedBlock() {
/* 43 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 48 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 53 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Events\MultiBlockInteractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */