/*    */ package me.mrCookieSlime.Slimefun.api.energy;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public abstract class EnergyTicker
/*    */   extends ItemHandler
/*    */ {
/*    */   public abstract double generateEnergy(Location paramLocation, SlimefunItem paramSlimefunItem, Config paramConfig);
/*    */   
/*    */   public abstract boolean explode(Location paramLocation);
/*    */   
/*    */   public String toCodename() {
/* 16 */     return "EnergyTicker";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\energy\EnergyTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */