/*    */ package me.mrCookieSlime.Slimefun.Events;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class ResearchUnlockEvent
/*    */   extends Event
/*    */   implements Cancellable {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   Player p;
/*    */   Research r;
/*    */   boolean cancelled;
/*    */   
/*    */   public HandlerList getHandlers() {
/* 19 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 23 */     return handlers;
/*    */   }
/*    */   
/*    */   public ResearchUnlockEvent(Player p, Research res) {
/* 27 */     this.p = p;
/* 28 */     this.r = res;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 32 */     return this.p;
/*    */   }
/*    */   
/*    */   public Research getResearch() {
/* 36 */     return this.r;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 41 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 46 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Events\ResearchUnlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */