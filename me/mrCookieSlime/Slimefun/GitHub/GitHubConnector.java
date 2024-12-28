/*    */ package me.mrCookieSlime.Slimefun.GitHub;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.nio.channels.Channels;
/*    */ import java.nio.channels.ReadableByteChannel;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ 
/*    */ 
/*    */ public abstract class GitHubConnector
/*    */ {
/* 21 */   public static Set<GitHubConnector> connectors = new HashSet<>();
/*    */   
/*    */   private File file;
/*    */   
/*    */   public GitHubConnector() {
/* 26 */     this.file = new File("plugins/Slimefun/cache/github/" + getFileName() + ".json");
/* 27 */     connectors.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract String getFileName();
/*    */ 
/*    */   
/*    */   public abstract String getRepository();
/*    */   
/*    */   public void pullFile() {
/* 37 */     if (SlimefunStartup.getCfg().getBoolean("options.print-out-github-data-retrieving")) System.out.println("[Slimefun - GitHub] Retrieving '" + getFileName() + ".json' from GitHub...");
/*    */     
/*    */     try {
/* 40 */       URL website = new URL("https://api.github.com/repos/" + getRepository() + getURLSuffix());
/*    */       
/* 42 */       URLConnection connection = website.openConnection();
/* 43 */       connection.setConnectTimeout(3000);
/* 44 */       connection.addRequestProperty("User-Agent", "Slimefun 4 GitHub Agent (by TheBusyBiscuit)");
/* 45 */       connection.setDoOutput(true);
/*    */       
/* 47 */       ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
/* 48 */       FileOutputStream fos = new FileOutputStream(this.file);
/* 49 */       fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
/* 50 */       fos.close();
/* 51 */       parseData();
/* 52 */     } catch (IOException e) {
/* 53 */       if (SlimefunStartup.getCfg().getBoolean("options.print-out-github-data-retrieving")) System.err.println("[Slimefun - GitHub] ERROR - Could not connect to GitHub in time.");
/*    */       
/* 55 */       if (hasData()) {
/* 56 */         parseData();
/*    */       } else {
/*    */         
/* 59 */         onFailure();
/*    */       } 
/*    */     } 
/*    */   } public abstract String getURLSuffix(); public abstract void onSuccess(JsonElement paramJsonElement);
/*    */   public abstract void onFailure();
/*    */   public boolean hasData() {
/* 65 */     return getFile().exists();
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 69 */     return this.file;
/*    */   }
/*    */   
/*    */   public void parseData() {
/*    */     try {
/* 74 */       BufferedReader reader = new BufferedReader(new FileReader(getFile()));
/*    */       
/* 76 */       String full = "";
/*    */       
/*    */       String line;
/* 79 */       while ((line = reader.readLine()) != null) {
/* 80 */         full = full + line;
/*    */       }
/*    */       
/* 83 */       reader.close();
/*    */       
/* 85 */       JsonElement element = (new JsonParser()).parse(full);
/*    */       
/* 87 */       onSuccess(element);
/*    */     }
/* 89 */     catch (IOException e) {
/* 90 */       e.printStackTrace();
/* 91 */       onFailure();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GitHub\GitHubConnector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */