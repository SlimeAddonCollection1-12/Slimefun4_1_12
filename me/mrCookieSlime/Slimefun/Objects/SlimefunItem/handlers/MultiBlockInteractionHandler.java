/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.MultiBlock;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public abstract class MultiBlockInteractionHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public abstract boolean onInteract(Player paramPlayer, MultiBlock paramMultiBlock, Block paramBlock);
/*    */   
/*    */   public String toCodename() {
/* 14 */     return "MultiBlockInteractionHandler";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\MultiBlockInteractionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */