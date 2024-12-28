/*    */ package me.mrCookieSlime.Slimefun.api.machine;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*    */ 
/*    */ public class MachineConfig
/*    */   extends Config
/*    */ {
/*    */   MachineSettings global;
/*    */   Map<String, MachineSettings> children;
/*    */   
/*    */   public MachineConfig(String id) {
/* 15 */     super("plugins/Slimefun/machines/" + id + ".yml");
/*    */     
/* 17 */     this.global = new MachineSettings(this);
/* 18 */     this.children = new HashMap<>();
/*    */   }
/*    */   
/*    */   public MachineSettings getGlobalSettings() {
/* 22 */     return this.global;
/*    */   }
/*    */   
/*    */   public MachineSettings getSettings(AContainer item) {
/* 26 */     if (!this.children.containsKey(item.getID())) {
/* 27 */       this.children.put(item.getID(), new MachineSettings(this, item));
/*    */     }
/*    */     
/* 30 */     return this.children.get(item.getID());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\machine\MachineConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */