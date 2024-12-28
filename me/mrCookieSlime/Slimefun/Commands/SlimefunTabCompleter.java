/*    */ package me.mrCookieSlime.Slimefun.Commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*    */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlimefunTabCompleter
/*    */   implements TabCompleter
/*    */ {
/*    */   public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
/* 17 */     if (args.length == 1) {
/* 18 */       return createReturnList(SlimefunCommand.tabs, args[0]);
/*    */     }
/* 20 */     if (args.length == 3) {
/* 21 */       if (args[0].equalsIgnoreCase("give")) {
/* 22 */         return createReturnList(Slimefun.listIDs(), args[2]);
/*    */       }
/* 24 */       if (args[0].equalsIgnoreCase("research")) {
/* 25 */         List<String> researches = new ArrayList<>();
/* 26 */         for (Research res : Research.list()) {
/* 27 */           researches.add(res.getName().toUpperCase().replace(" ", "_"));
/*    */         }
/* 29 */         researches.add("all");
/* 30 */         return createReturnList(researches, args[2]);
/*    */       } 
/*    */       
/* 33 */       return null;
/*    */     } 
/*    */ 
/*    */     
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private List<String> createReturnList(List<String> list, String string) {
/* 49 */     if (string.equals("")) return list;
/*    */     
/* 51 */     List<String> returnList = new ArrayList<>();
/* 52 */     for (String item : list) {
/* 53 */       if (item.toLowerCase().startsWith(string.toLowerCase())) {
/* 54 */         returnList.add(item);
/*    */       }
/*    */     } 
/* 57 */     return returnList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Commands\SlimefunTabCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */