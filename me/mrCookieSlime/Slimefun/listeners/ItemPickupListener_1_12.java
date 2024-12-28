/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*    */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPickupListener_1_12
/*    */   implements Listener
/*    */ {
/*    */   public ItemPickupListener_1_12(SlimefunStartup plugin) {
/* 21 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPickup(EntityPickupItemEvent e) {
/* 26 */     if (e.getItem().hasMetadata("no_pickup")) { e.setCancelled(true); }
/* 27 */     else if (!e.getItem().hasMetadata("no_pickup") && e.getItem().getItemStack().hasItemMeta() && e.getItem().getItemStack().getItemMeta().hasDisplayName() && e.getItem().getItemStack().getItemMeta().getDisplayName().startsWith(ChatColor.translateAlternateColorCodes('&', "&5&d祭坛 &3灵柱 - &e")))
/* 28 */     { e.setCancelled(true);
/* 29 */       e.getItem().remove(); }
/*    */   
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onMinecartPickup(InventoryPickupItemEvent e) {
/* 35 */     if (e.getItem().hasMetadata("no_pickup")) { e.setCancelled(true); }
/* 36 */     else if (!e.getItem().hasMetadata("no_pickup") && e.getItem().getItemStack().hasItemMeta() && e.getItem().getItemStack().getItemMeta().hasDisplayName() && e.getItem().getItemStack().getItemMeta().getDisplayName().startsWith(ChatColor.translateAlternateColorCodes('&', "&5&d祭坛 &3灵柱 - &e")))
/* 37 */     { e.setCancelled(true);
/* 38 */       e.getItem().remove(); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\ItemPickupListener_1_12.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */