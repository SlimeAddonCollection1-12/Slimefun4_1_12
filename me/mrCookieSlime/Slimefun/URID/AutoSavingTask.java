/*    */ package me.mrCookieSlime.Slimefun.URID;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoSavingTask
/*    */   implements Runnable
/*    */ {
/*    */   public void run() {
/* 16 */     Set<BlockStorage> worlds = new HashSet<>();
/*    */     
/* 18 */     for (World world : Bukkit.getWorlds()) {
/* 19 */       if (BlockStorage.isWorldRegistered(world.getName())) {
/* 20 */         BlockStorage storage = BlockStorage.getStorage(world);
/* 21 */         storage.computeChanges();
/*    */         
/* 23 */         if (storage.getChanges() > 0) {
/* 24 */           worlds.add(storage);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 29 */     if (!worlds.isEmpty()) {
/* 30 */       System.out.println("[远古工艺] 自动保存数据中... (距下一次自动保存: " + SlimefunStartup.getCfg().getInt("options.auto-save-delay-in-minutes") + "m)");
/*    */       
/* 32 */       for (BlockStorage storage : worlds)
/* 33 */         storage.save(false); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\URID\AutoSavingTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */