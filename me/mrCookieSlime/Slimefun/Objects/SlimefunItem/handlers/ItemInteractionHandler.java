/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public abstract class ItemInteractionHandler
/*    */   extends ItemHandler
/*    */ {
/*    */   public abstract boolean onRightClick(ItemUseEvent paramItemUseEvent, Player paramPlayer, ItemStack paramItemStack);
/*    */   
/*    */   public String toCodename() {
/* 14 */     return "ItemInteractionHandler";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\ItemInteractionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */