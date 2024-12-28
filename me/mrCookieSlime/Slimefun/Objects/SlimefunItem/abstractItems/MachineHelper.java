/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MachineHelper
/*    */ {
/*    */   public static String getTimeLeft(int l) {
/*  9 */     String timeleft = "";
/* 10 */     int minutes = (int)(l / 60L);
/* 11 */     if (minutes > 0) {
/* 12 */       timeleft = String.valueOf(timeleft) + minutes + "m ";
/*    */     }
/* 14 */     l -= minutes * 60;
/* 15 */     int seconds = l;
/* 16 */     timeleft = String.valueOf(timeleft) + seconds + "s";
/* 17 */     return ChatColor.translateAlternateColorCodes('&', "&7剩余 " + timeleft + " 秒");
/*    */   }
/*    */   
/*    */   public static String getProgress(int time, int total) {
/* 21 */     StringBuilder progress = new StringBuilder();
/* 22 */     float percentage = Math.round((total - time) * 100.0F / total * 100.0F / 100.0F);
/*    */     
/* 24 */     if (percentage < 16.0F) { progress.append("&4"); }
/* 25 */     else if (percentage < 32.0F) { progress.append("&c"); }
/* 26 */     else if (percentage < 48.0F) { progress.append("&6"); }
/* 27 */     else if (percentage < 64.0F) { progress.append("&e"); }
/* 28 */     else if (percentage < 80.0F) { progress.append("&2"); }
/* 29 */     else { progress = progress.append("&a"); }
/*    */     
/* 31 */     int rest = 20; int i;
/* 32 */     for (i = (int)percentage; i >= 5; i -= 5) {
/* 33 */       progress.append(":");
/* 34 */       rest--;
/*    */     } 
/*    */     
/* 37 */     progress.append("&7");
/*    */     
/* 39 */     for (i = 0; i < rest; i++) {
/* 40 */       progress.append(":");
/*    */     }
/*    */     
/* 43 */     progress.append(" - " + percentage + "%");
/* 44 */     return ChatColor.translateAlternateColorCodes('&', progress.toString());
/*    */   }
/*    */   
/*    */   public static String getCoolant(int time, int total) {
/* 48 */     int passed = (total - time) % 25;
/* 49 */     StringBuilder progress = new StringBuilder();
/* 50 */     float percentage = Math.round((25 - passed) * 100.0F / 25.0F * 100.0F / 100.0F);
/*    */     
/* 52 */     if (percentage < 33.0F) { progress.append("&9"); }
/* 53 */     else if (percentage < 66.0F) { progress.append("&1"); }
/* 54 */     else { progress = progress.append("&b"); }
/*    */     
/* 56 */     int rest = 20; int i;
/* 57 */     for (i = (int)percentage; i >= 5; i -= 5) {
/* 58 */       progress.append(":");
/* 59 */       rest--;
/*    */     } 
/*    */     
/* 62 */     progress.append("&7");
/*    */     
/* 64 */     for (i = 0; i < rest; i++) {
/* 65 */       progress.append(":");
/*    */     }
/*    */     
/* 68 */     progress.append(" - " + percentage + "%");
/* 69 */     return ChatColor.translateAlternateColorCodes('&', progress.toString());
/*    */   }
/*    */   
/*    */   public static float getPercentage(int time, int total) {
/* 73 */     int passed = (total - time) % 25;
/* 74 */     return Math.round((25 - passed) * 100.0F / 25.0F * 100.0F / 100.0F);
/*    */   }
/*    */   
/*    */   public static short getDurability(ItemStack item, int timeleft, int max) {
/* 78 */     return (short)(item.getType().getMaxDurability() / max * timeleft);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\MachineHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */