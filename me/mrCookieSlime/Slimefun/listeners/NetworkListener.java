/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.network.Network;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class NetworkListener
/*    */   implements Listener {
/*    */   public NetworkListener(SlimefunStartup plugin) {
/* 15 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
/*    */   public void onBlockBreak(BlockBreakEvent e) {
/* 20 */     Network.handleAllNetworkLocationUpdate(e.getBlock().getLocation());
/*    */   }
/*    */   @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
/*    */   public void onPlaceBreak(BlockPlaceEvent e) {
/* 24 */     Network.handleAllNetworkLocationUpdate(e.getBlock().getLocation());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\NetworkListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */