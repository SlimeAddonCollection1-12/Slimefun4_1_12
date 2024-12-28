/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public abstract class BlockTicker
/*    */   extends ItemHandler
/*    */ {
/*    */   public boolean unique = true;
/*    */   
/*    */   public void update() {
/* 13 */     if (this.unique) {
/* 14 */       uniqueTick();
/* 15 */       this.unique = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract boolean isSynchronized();
/*    */   
/*    */   public abstract void uniqueTick();
/*    */   
/*    */   public abstract void tick(Block paramBlock, SlimefunItem paramSlimefunItem, Config paramConfig);
/*    */   
/*    */   public String toCodename() {
/* 26 */     return "BlockTicker";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\handlers\BlockTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */