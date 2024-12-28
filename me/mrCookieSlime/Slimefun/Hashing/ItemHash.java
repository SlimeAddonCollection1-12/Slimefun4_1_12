/*    */ package me.mrCookieSlime.Slimefun.Hashing;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class ItemHash
/*    */ {
/*    */   public static MessageDigest digest;
/*    */   public static int LENGTH;
/* 18 */   public static Map<String, SlimefunItem> map = new HashMap<>();
/*    */   
/*    */   static {
/*    */     try {
/* 22 */       digest = MessageDigest.getInstance("SHA");
/* 23 */       LENGTH = hash("The Busy Biscuit").length();
/* 24 */     } catch (NoSuchAlgorithmException e) {
/* 25 */       System.out.println("FATAL Security ERROR - Slimefun was disabled.");
/* 26 */       Bukkit.getPluginManager().disablePlugin((Plugin)SlimefunStartup.instance);
/* 27 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String hash(String input) {
/* 32 */     digest.update(input.getBytes());
/* 33 */     byte[] hash = digest.digest();
/* 34 */     return (new BigInteger(1, hash)).toString(16);
/*    */   }
/*    */   
/*    */   public static String toString(SlimefunItem item) {
/* 38 */     StringBuilder builder = new StringBuilder(LENGTH * 2);
/*    */     
/* 40 */     for (char c : item.getHash().toCharArray()) {
/* 41 */       builder.append('§');
/* 42 */       builder.append(c);
/*    */     } 
/*    */     
/* 45 */     return builder.toString();
/*    */   }
/*    */   public static SlimefunItem fromString(String input) {
/* 48 */     if (input == null || input.length() != LENGTH * 2) return null;
/*    */     
/* 50 */     String hex = input.replaceAll("§", "");
/*    */     
/* 52 */     if (hex.length() != LENGTH || !map.containsKey(hex)) return null;
/*    */     
/* 54 */     return map.get(hex);
/*    */   }
/*    */   
/*    */   public static void register(SlimefunItem item) {
/* 58 */     String hash = hash(item.getID());
/*    */     
/* 60 */     if (map.containsKey(hash) && !item.getID().equals(((SlimefunItem)map.get(hash)).getHash())) {
/* 61 */       System.out.println("FATAL Security ERROR - Slimefun was disabled.");
/* 62 */       Bukkit.getPluginManager().disablePlugin((Plugin)SlimefunStartup.instance);
/* 63 */       throw new IllegalStateException("Hash Collision: " + hash);
/*    */     } 
/*    */     
/* 66 */     item.setHash(hash);
/* 67 */     map.put(hash, item);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Hashing\ItemHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */