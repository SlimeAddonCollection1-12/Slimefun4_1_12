/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.AutonomousMachineHandler;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.AutonomousToolHandler;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.Dispenser;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class AutonomousToolsListener
/*    */   implements Listener {
/*    */   public AutonomousToolsListener(SlimefunStartup plugin) {
/* 21 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockDispensing(BlockDispenseEvent e) {
/* 27 */     Block dispenser = e.getBlock();
/* 28 */     if (dispenser.getType() == Material.DISPENSER) {
/* 29 */       Dispenser d = (Dispenser)dispenser.getState();
/* 30 */       BlockFace face = BlockFace.DOWN;
/*    */       
/* 32 */       if (dispenser.getData() == 8) { face = BlockFace.DOWN; }
/* 33 */       else if (dispenser.getData() == 9) { face = BlockFace.UP; }
/* 34 */       else if (dispenser.getData() == 10) { face = BlockFace.NORTH; }
/* 35 */       else if (dispenser.getData() == 11) { face = BlockFace.SOUTH; }
/* 36 */       else if (dispenser.getData() == 12) { face = BlockFace.WEST; }
/* 37 */       else if (dispenser.getData() == 13) { face = BlockFace.EAST; }
/*    */       
/* 39 */       Block block = dispenser.getRelative(face);
/* 40 */       Block chest = dispenser.getRelative(face.getOppositeFace());
/* 41 */       SlimefunItem machine = BlockStorage.check(dispenser);
/*    */       
/* 43 */       if (machine != null) {
/* 44 */         for (ItemHandler handler : SlimefunItem.getHandlers("AutonomousMachineHandler")) {
/* 45 */           if (((AutonomousMachineHandler)handler).onBlockDispense(e, dispenser, d, block, chest, machine))
/*    */             break; 
/*    */         } 
/*    */       } else {
/* 49 */         for (int i = 0; i < (d.getInventory().getContents()).length; i++) {
/* 50 */           for (ItemHandler handler : SlimefunItem.getHandlers("AutonomousToolHandler")) {
/* 51 */             if (((AutonomousToolHandler)handler).onBlockDispense(e, dispenser, d, block, chest, i))
/*    */               break; 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\AutonomousToolsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */