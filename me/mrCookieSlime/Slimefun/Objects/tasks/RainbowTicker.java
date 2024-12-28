/*    */ package me.mrCookieSlime.Slimefun.Objects.tasks;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class RainbowTicker
/*    */   extends BlockTicker
/*    */ {
/*    */   public int meta;
/*    */   public int index;
/*    */   public int[] queue;
/*    */   
/*    */   public RainbowTicker() {
/* 16 */     this(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
/*    */   }
/*    */   
/*    */   public RainbowTicker(int... data) {
/* 20 */     this.queue = data;
/* 21 */     this.meta = data[0];
/* 22 */     this.index = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Block b, SlimefunItem item, Config data) {
/* 28 */     b.setData((byte)this.meta, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void uniqueTick() {
/* 33 */     this.index = (this.index == this.queue.length - 1) ? 0 : (this.index + 1);
/* 34 */     this.meta = this.queue[this.index];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSynchronized() {
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\tasks\RainbowTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */