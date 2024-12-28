/*     */ package me.mrCookieSlime.Slimefun.api;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ 
/*     */ public class BlockInfoConfig
/*     */   extends Config {
/*     */   private Map<String, String> data;
/*     */   
/*     */   public BlockInfoConfig() {
/*  16 */     this(new HashMap<>());
/*     */   }
/*     */   public BlockInfoConfig(Map<String, String> data) {
/*  19 */     super((File)null, (FileConfiguration)null);
/*  20 */     this.data = data;
/*     */   }
/*     */   public Map<String, String> getMap() {
/*  23 */     return this.data;
/*     */   }
/*     */   
/*     */   protected void store(String path, Object value) {
/*  27 */     if (value != null && !(value instanceof String)) {
/*  28 */       throw new UnsupportedOperationException("Can't set \"" + path + "\" to \"" + value + "\" (type: " + value.getClass().getSimpleName() + ") because BlockInfoConfig only supports Strings");
/*     */     }
/*  30 */     checkPath(path);
/*  31 */     if (value == null) {
/*  32 */       this.data.remove(path);
/*     */     } else {
/*  34 */       this.data.put(path, (String)value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkPath(String path) {
/*  40 */     if (path.contains(".")) {
/*  41 */       throw new UnsupportedOperationException("BlockInfoConfig only supports Map<String,String> (path: " + path + ")");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(String path) {
/*  47 */     checkPath(path);
/*  48 */     return this.data.containsKey(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue(String path) {
/*  53 */     checkPath(path);
/*  54 */     return this.data.get(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(String path) {
/*  59 */     checkPath(path);
/*  60 */     return this.data.get(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getKeys() {
/*  65 */     return this.data.keySet();
/*     */   }
/*     */   
/*     */   private UnsupportedOperationException invalidType(String path) {
/*  69 */     return new UnsupportedOperationException("Can't get \"" + path + "\" because BlockInfoConfig only supports String values");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt(String path) {
/*  74 */     throw invalidType(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String path) {
/*  79 */     throw invalidType(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getStringList(String path) {
/*  84 */     throw invalidType(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Integer> getIntList(String path) {
/*  89 */     throw invalidType(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public Double getDouble(String path) {
/*  94 */     throw invalidType(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getKeys(String path) {
/*  99 */     throw invalidType(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public File getFile() {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public FileConfiguration getConfiguration() {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 114 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void save(File file) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void createFile() {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reload() {
/* 129 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\BlockInfoConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */