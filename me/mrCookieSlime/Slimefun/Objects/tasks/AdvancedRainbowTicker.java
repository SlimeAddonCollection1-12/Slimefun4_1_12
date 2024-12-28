/*    */ package me.mrCookieSlime.Slimefun.Objects.tasks;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class AdvancedRainbowTicker
/*    */   extends BlockTicker
/*    */ {
/*    */   public int index;
/*    */   public int[] data;
/*    */   
/*    */   public AdvancedRainbowTicker(int... data) {
/* 15 */     this.data = data;
/* 16 */     this.index = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Block b, SlimefunItem item, Config cfg) {
/* 22 */     b.setData((byte)this.data[this.index], false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void uniqueTick() {
/* 27 */     this.index = (this.index == this.data.length - 1) ? 0 : (this.index + 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSynchronized() {
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\tasks\AdvancedRainbowTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */