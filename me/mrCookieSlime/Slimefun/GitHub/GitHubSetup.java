/*     */ package me.mrCookieSlime.Slimefun.GitHub;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunGuide;
/*     */ 
/*     */ 
/*     */ public class GitHubSetup
/*     */ {
/*     */   public static void setup() {
/*  12 */     new GitHubConnector()
/*     */       {
/*     */         public void onSuccess(JsonElement element)
/*     */         {
/*  16 */           SlimefunGuide.contributors.clear();
/*  17 */           JsonArray array = element.getAsJsonArray();
/*     */           
/*  19 */           for (int i = 0; i < array.size(); i++) {
/*  20 */             JsonObject object = array.get(i).getAsJsonObject();
/*     */             
/*  22 */             String name = object.get("login").getAsString();
/*  23 */             String job = "&cAuthor";
/*  24 */             int commits = object.get("contributions").getAsInt();
/*  25 */             String profile = object.get("html_url").getAsString();
/*     */             
/*  27 */             if (!name.equals("invalid-email-address")) {
/*  28 */               Contributor contributor = new Contributor(name, job, commits);
/*  29 */               contributor.setProfile(profile);
/*  30 */               SlimefunGuide.contributors.add(contributor);
/*     */             } 
/*     */           } 
/*  33 */           SlimefunGuide.contributors.add(new Contributor("AquaLazuryt", "&6Lead Head Artist", 0));
/*     */         }
/*     */ 
/*     */         
/*     */         public void onFailure() {
/*  38 */           SlimefunGuide.contributors.clear();
/*  39 */           SlimefunGuide.contributors.add(new Contributor("TheBusyBiscuit", "&cAuthor", 3));
/*  40 */           SlimefunGuide.contributors.add(new Contributor("John000708", "&cAuthor", 2));
/*  41 */           SlimefunGuide.contributors.add(new Contributor("AquaLazuryt", "&6Lead Head Artist", 0));
/*     */         }
/*     */ 
/*     */         
/*     */         public String getRepository() {
/*  46 */           return "TheBusyBiscuit/Slimefun4";
/*     */         }
/*     */ 
/*     */         
/*     */         public String getFileName() {
/*  51 */           return "contributors";
/*     */         }
/*     */ 
/*     */         
/*     */         public String getURLSuffix() {
/*  56 */           return "/contributors";
/*     */         }
/*     */       };
/*     */     
/*  60 */     new GitHubConnector()
/*     */       {
/*     */         public void onSuccess(JsonElement element)
/*     */         {
/*  64 */           JsonObject object = element.getAsJsonObject();
/*  65 */           SlimefunGuide.issues = object.get("open_issues_count").getAsInt();
/*  66 */           SlimefunGuide.forks = object.get("forks").getAsInt();
/*  67 */           SlimefunGuide.stars = object.get("stargazers_count").getAsInt();
/*  68 */           SlimefunGuide.last_update = IntegerFormat.parseGitHubDate(object.get("pushed_at").getAsString());
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void onFailure() {}
/*     */ 
/*     */         
/*     */         public String getRepository() {
/*  77 */           return "TheBusyBiscuit/Slimefun4";
/*     */         }
/*     */ 
/*     */         
/*     */         public String getFileName() {
/*  82 */           return "repo";
/*     */         }
/*     */ 
/*     */         
/*     */         public String getURLSuffix() {
/*  87 */           return "";
/*     */         }
/*     */       };
/*     */     
/*  91 */     new GitHubConnector()
/*     */       {
/*     */         public void onSuccess(JsonElement element)
/*     */         {
/*  95 */           JsonObject object = element.getAsJsonObject();
/*  96 */           SlimefunGuide.code_bytes = object.get("Java").getAsInt();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void onFailure() {}
/*     */ 
/*     */         
/*     */         public String getRepository() {
/* 105 */           return "TheBusyBiscuit/Slimefun4";
/*     */         }
/*     */ 
/*     */         
/*     */         public String getFileName() {
/* 110 */           return "languages";
/*     */         }
/*     */ 
/*     */         
/*     */         public String getURLSuffix() {
/* 115 */           return "/languages";
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GitHub\GitHubSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */