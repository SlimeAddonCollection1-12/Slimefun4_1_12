/*    */ package me.mrCookieSlime.Slimefun.Setup;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class Files
/*    */ {
/*  7 */   public static File RESEARCHES = new File("plugins/Slimefun/Researches.yml");
/*  8 */   public static File CONFIG = new File("plugins/Slimefun/config.yml");
/*  9 */   public static File ITEMS = new File("plugins/Slimefun/Items.yml");
/* 10 */   public static File DATABASE = new File("data-storage/Slimefun/Players");
/* 11 */   public static File WHITELIST = new File("plugins/Slimefun/whitelist.yml");
/*    */   
/*    */   public static void cleanup() {
/* 14 */     if (!RESEARCHES.exists()) {
/* 15 */       System.err.println("################################################");
/* 16 */       System.err.println("################################################");
/* 17 */       System.err.println("############### = - ERROR - = ##################");
/* 18 */       System.err.println("################################################");
/* 19 */       System.err.println("################################################");
/* 20 */       System.err.println("      ");
/* 21 */       System.err.println("Slimefun Warning:");
/* 22 */       System.err.println("         ");
/* 23 */       System.err.println("Slimefun has detected that your Files are either");
/* 24 */       System.err.println("outdated or do not exist. We generated new Files");
/* 25 */       System.err.println("instead otherwise Slimefun would not work. If you");
/* 26 */       System.err.println("have used Slimefun before, your Settings are now");
/* 27 */       System.err.println("gone. But therefore Slimefun works!");
/* 28 */       System.err.println("        ");
/* 29 */       System.err.println("################################################");
/* 30 */       System.err.println("################################################");
/* 31 */       System.err.println("############### = - ERROR - = ##################");
/* 32 */       System.err.println("################################################");
/* 33 */       System.err.println("################################################");
/* 34 */       delete(new File("plugins/Slimefun"));
/* 35 */       delete(new File("data-storage/Slimefun"));
/*    */     } 
/*    */     
/* 38 */     if (!DATABASE.exists()) {
/* 39 */       DATABASE.mkdirs();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void delete(File folder) {
/* 44 */     File[] files = folder.listFiles();
/* 45 */     if (files != null) {
/* 46 */       for (File current : files) {
/* 47 */         if (current.isDirectory()) {
/* 48 */           delete(current);
/*    */         } else {
/*    */           
/* 51 */           current.delete();
/*    */         } 
/*    */       } 
/*    */     }
/* 55 */     folder.delete();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Setup\Files.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */