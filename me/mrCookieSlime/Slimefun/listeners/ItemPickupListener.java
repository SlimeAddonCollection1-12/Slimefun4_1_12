/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*    */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPickupListener
/*    */   implements Listener
/*    */ {
/*    */   public ItemPickupListener(SlimefunStartup plugin) {
/* 20 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPickup(PlayerPickupItemEvent e) {
/* 25 */     if (e.getItem().hasMetadata("no_pickup")) { e.setCancelled(true); }
/* 26 */     else if (!e.getItem().hasMetadata("no_pickup") && e.getItem().getItemStack().hasItemMeta() && e.getItem().getItemStack().getItemMeta().hasDisplayName() && e.getItem().getItemStack().getItemMeta().getDisplayName().startsWith(ChatColor.translateAlternateColorCodes('&', "&5&d祭坛 &3灵柱 - &e")))
/* 27 */     { e.setCancelled(true);
/* 28 */       e.getItem().remove(); }
/*    */   
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onMinecartPickup(InventoryPickupItemEvent e) {
/* 34 */     if (e.getItem().hasMetadata("no_pickup")) { e.setCancelled(true); }
/* 35 */     else if (!e.getItem().hasMetadata("no_pickup") && e.getItem().getItemStack().hasItemMeta() && e.getItem().getItemStack().getItemMeta().hasDisplayName() && e.getItem().getItemStack().getItemMeta().getDisplayName().startsWith(ChatColor.translateAlternateColorCodes('&', "&5&d祭坛 &3灵柱 - &e")))
/* 36 */     { e.setCancelled(true);
/* 37 */       e.getItem().remove(); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\ItemPickupListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */