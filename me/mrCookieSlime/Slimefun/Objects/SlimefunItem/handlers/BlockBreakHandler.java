/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public abstract class BlockBreakHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public abstract boolean onBlockBreak(BlockBreakEvent paramBlockBreakEvent, ItemStack paramItemStack, int paramInt, List<ItemStack> paramList);
/*    */   
/*    */   public String toCodename() {
/* 14 */     return "BlockBreakHandler";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\BlockBreakHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */