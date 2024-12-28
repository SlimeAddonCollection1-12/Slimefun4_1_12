/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Dispenser;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ 
/*    */ 
/*    */ public abstract class AutonomousMachineHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public String toCodename() {
/* 13 */     return "AutonomousMachineHandler";
/*    */   }
/*    */   
/*    */   public abstract boolean onBlockDispense(BlockDispenseEvent paramBlockDispenseEvent, Block paramBlock1, Dispenser paramDispenser, Block paramBlock2, Block paramBlock3, SlimefunItem paramSlimefunItem);
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\AutonomousMachineHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */