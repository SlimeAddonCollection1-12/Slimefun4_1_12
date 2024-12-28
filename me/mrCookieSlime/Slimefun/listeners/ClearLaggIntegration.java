/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import me.minebuilders.clearlag.events.EntityRemoveEvent;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class ClearLaggIntegration
/*    */   implements Listener
/*    */ {
/*    */   public ClearLaggIntegration(SlimefunStartup plugin) {
/* 16 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onEntityRemove(EntityRemoveEvent e) {
/* 21 */     Iterator<Entity> iterator = e.getEntityList().iterator();
/* 22 */     while (iterator.hasNext()) {
/* 23 */       Entity n = iterator.next();
/* 24 */       if (n instanceof org.bukkit.entity.Item && 
/* 25 */         n.hasMetadata("no_pickup")) iterator.remove(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\ClearLaggIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */