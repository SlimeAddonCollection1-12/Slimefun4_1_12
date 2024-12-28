/*    */ package me.mrCookieSlime.Slimefun.api.machine;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ 
/*    */ 
/*    */ public class MachineSettings
/*    */ {
/*    */   MachineConfig cfg;
/* 10 */   String prefix = "global";
/*    */   
/*    */   public MachineSettings(MachineConfig cfg) {
/* 13 */     this.cfg = cfg;
/*    */   }
/*    */   
/*    */   public MachineSettings(MachineConfig cfg, AContainer machine) {
/* 17 */     this.cfg = cfg;
/* 18 */     this.prefix = machine.getID();
/*    */   }
/*    */   
/*    */   public String getString(String path) {
/* 22 */     return this.cfg.getString(this.prefix + "." + path);
/*    */   }
/*    */   
/*    */   public int getInt(String path) {
/* 26 */     return this.cfg.getInt(this.prefix + "." + path);
/*    */   }
/*    */   
/*    */   public List<String> getStringList(String path) {
/* 30 */     return this.cfg.getStringList(this.prefix + "." + path);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\machine\MachineSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */