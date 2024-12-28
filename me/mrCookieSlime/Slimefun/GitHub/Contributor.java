/*    */ package me.mrCookieSlime.Slimefun.GitHub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Contributor
/*    */ {
/*    */   private String name;
/*    */   private String job;
/*    */   private String profile;
/*    */   private int commits;
/*    */   
/*    */   public Contributor(String name, String job, int commits) {
/* 16 */     this.name = name;
/* 17 */     this.job = job;
/* 18 */     this.commits = commits;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getJob() {
/* 36 */     return this.job;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getProfile() {
/* 44 */     return this.profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCommits() {
/* 52 */     return this.commits;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setProfile(String profile) {
/* 62 */     this.profile = profile;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GitHub\Contributor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */