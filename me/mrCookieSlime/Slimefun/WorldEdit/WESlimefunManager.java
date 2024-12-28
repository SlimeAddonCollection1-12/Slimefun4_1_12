/*    */ package me.mrCookieSlime.Slimefun.WorldEdit;
/*    */ 
/*    */ import com.sk89q.worldedit.Vector;
/*    */ import com.sk89q.worldedit.WorldEdit;
/*    */ import com.sk89q.worldedit.blocks.BaseBlock;
/*    */ import com.sk89q.worldedit.event.extent.EditSessionEvent;
/*    */ import com.sk89q.worldedit.extent.Extent;
/*    */ import com.sk89q.worldedit.extent.logging.AbstractLoggingExtent;
/*    */ import com.sk89q.worldedit.util.eventbus.Subscribe;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ 
/*    */ public class WESlimefunManager
/*    */ {
/*    */   public WESlimefunManager() {
/* 19 */     WorldEdit.getInstance().getEventBus().register(this);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void wrapForLogging(final EditSessionEvent event) {
/* 24 */     event.setExtent((Extent)new AbstractLoggingExtent(event.getExtent())
/*    */         {
/*    */           protected void onBlockChange(Vector pos, BaseBlock b)
/*    */           {
/* 28 */             super.onBlockChange(pos, b);
/*    */             
/* 30 */             if (b.getType() == 0) {
/* 31 */               World world = Bukkit.getWorld(event.getWorld().getName());
/*    */               
/* 33 */               if (world != null) {
/* 34 */                 Location l = new Location(world, pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
/* 35 */                 if (BlockStorage.hasBlockInfo(l)) BlockStorage.clearBlockInfo(l); 
/*    */               } 
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\WorldEdit\WESlimefunManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */