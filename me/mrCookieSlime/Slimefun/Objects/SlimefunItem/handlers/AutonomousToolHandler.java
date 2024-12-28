/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Dispenser;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ 
/*    */ public abstract class AutonomousToolHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public String toCodename() {
/* 11 */     return "AutonomousToolHandler";
/*    */   }
/*    */   
/*    */   public abstract boolean onBlockDispense(BlockDispenseEvent paramBlockDispenseEvent, Block paramBlock1, Dispenser paramDispenser, Block paramBlock2, Block paramBlock3, int paramInt);
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\AutonomousToolHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */