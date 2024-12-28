/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ public abstract class BowShootHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public abstract boolean onHit(EntityDamageByEntityEvent paramEntityDamageByEntityEvent, LivingEntity paramLivingEntity);
/*    */   
/*    */   public String toCodename() {
/* 12 */     return "BowShootHandler";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\BowShootHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */