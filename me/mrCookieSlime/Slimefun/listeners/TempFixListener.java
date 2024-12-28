/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Maps;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*    */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*    */ import org.bukkit.event.block.BlockPistonRetractEvent;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TempFixListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onPistonExtend(BlockPistonExtendEvent event) {
/* 37 */     for (Block block : event.getBlocks()) {
/* 38 */       Block relative = block.getRelative(event.getDirection(), 1);
/* 39 */       if (BlockStorage.check(relative) != null) {
/* 40 */         event.setCancelled(true);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPistonRetract(BlockPistonRetractEvent event) {
/* 48 */     for (Block block : event.getBlocks()) {
/* 49 */       Block relative = block.getRelative(event.getDirection(), 1);
/* 50 */       if (BlockStorage.check(relative) != null) {
/* 51 */         event.setCancelled(true);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   @EventHandler
/*    */   public void onBreakSlimefunBlock(BlockBreakEvent event) {
/*    */     BlockMenu blockMenu;
/* 59 */     Block block = event.getBlock();
/* 60 */     if (block == null) {
/*    */       return;
/*    */     }
/* 63 */     if (block.getLocation() == null) {
/*    */       return;
/*    */     }
/* 66 */     if (BlockStorage.check(block) == null) {
/*    */       return;
/*    */     }
/* 69 */     if (block.getLocation().getWorld() == null) {
/*    */       return;
/*    */     }
/*    */     
/*    */     try {
/* 74 */       blockMenu = BlockStorage.getInventory(block);
/* 75 */     } catch (NullPointerException e) {
/*    */       return;
/*    */     } 
/* 78 */     if (blockMenu == null) {
/*    */       return;
/*    */     }
/* 81 */     for (Player player : block.getWorld().getPlayers()) {
/* 82 */       ChestMenu chestMenu = (ChestMenu)(Maps.getInstance()).menus.get(player.getUniqueId());
/* 83 */       if (chestMenu == null) {
/*    */         continue;
/*    */       }
/* 86 */       if (Arrays.equals((Object[])blockMenu.getContents(), (Object[])chestMenu.getContents())) {
/* 87 */         event.setCancelled(true);
/* 88 */         event.getPlayer().sendMessage("§a远古工艺 §7> §c有其他玩家打开界面时无法拆除机器！§8(若无其他玩家打开时仍无法拆除，请向机器内放入任意物品或清空再试)");
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlaceNoPermSfItem(BlockPlaceEvent event) {
/* 96 */     if (!Slimefun.hasUnlocked(event.getPlayer(), event.getPlayer().getInventory().getItemInOffHand(), true))
/* 97 */       event.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\TempFixListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */