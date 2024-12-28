/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class BlockPlaceHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public abstract boolean onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent, ItemStack paramItemStack);
/*    */   
/*    */   public String toCodename() {
/* 12 */     return "BlockPlaceHandler";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\BlockPlaceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */