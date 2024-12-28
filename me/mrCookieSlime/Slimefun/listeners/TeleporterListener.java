/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.GPS.Elevator;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.Teleporter;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class TeleporterListener
/*    */   implements Listener {
/* 18 */   BlockFace[] faces = new BlockFace[] { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };
/*    */   
/*    */   public TeleporterListener(SlimefunStartup plugin) {
/* 21 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void onStarve(PlayerInteractEvent e) {
/* 26 */     if (!e.getAction().equals(Action.PHYSICAL))
/*    */       return; 
/* 28 */     if (e.getClickedBlock() == null)
/* 29 */       return;  SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 30 */     if (item == null)
/*    */       return; 
/* 32 */     if (item.getID().equals("GPS_ACTIVATION_DEVICE_SHARED")) {
/* 33 */       SlimefunItem teleporter = BlockStorage.check(e.getClickedBlock().getRelative(BlockFace.DOWN));
/*    */       
/* 35 */       if (teleporter instanceof Teleporter) {
/* 36 */         for (BlockFace face : this.faces) {
/* 37 */           if (!BlockStorage.check(e.getClickedBlock().getRelative(BlockFace.DOWN).getRelative(face), "GPS_TELEPORTER_PYLON"))
/*    */             return; 
/*    */         } 
/*    */         try {
/* 41 */           ((Teleporter)teleporter).onInteract(e.getPlayer(), e.getClickedBlock().getRelative(BlockFace.DOWN));
/* 42 */         } catch (Exception x) {
/* 43 */           x.printStackTrace();
/*    */         }
/*    */       
/*    */       } 
/* 47 */     } else if (item.getID().equals("GPS_ACTIVATION_DEVICE_PERSONAL")) {
/* 48 */       if (BlockStorage.getLocationInfo(e.getClickedBlock().getLocation(), "owner").equals(e.getPlayer().getUniqueId().toString())) {
/* 49 */         SlimefunItem teleporter = BlockStorage.check(e.getClickedBlock().getRelative(BlockFace.DOWN));
/*    */         
/* 51 */         if (teleporter instanceof Teleporter) {
/* 52 */           for (BlockFace face : this.faces) {
/* 53 */             if (!BlockStorage.check(e.getClickedBlock().getRelative(BlockFace.DOWN).getRelative(face), "GPS_TELEPORTER_PYLON"))
/*    */               return; 
/*    */           } 
/*    */           try {
/* 57 */             ((Teleporter)teleporter).onInteract(e.getPlayer(), e.getClickedBlock().getRelative(BlockFace.DOWN));
/* 58 */           } catch (Exception x) {
/* 59 */             x.printStackTrace();
/*    */           } 
/*    */         } 
/*    */       } else {
/* 63 */         e.setCancelled(true);
/*    */       } 
/* 65 */     } else if (item.getID().equals("ELEVATOR_PLATE")) {
/* 66 */       Elevator.openDialogue(e.getPlayer(), e.getClickedBlock());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\TeleporterListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */