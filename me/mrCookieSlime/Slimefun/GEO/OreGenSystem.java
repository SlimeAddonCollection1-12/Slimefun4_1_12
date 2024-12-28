/*    */ package me.mrCookieSlime.Slimefun.GEO;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.Chunk;
/*    */ import org.bukkit.block.Biome;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OreGenSystem
/*    */ {
/* 15 */   public static Map<String, OreGenResource> map = new HashMap<>();
/*    */   
/*    */   public static Collection<OreGenResource> listResources() {
/* 18 */     return map.values();
/*    */   }
/*    */   
/*    */   public static void registerResource(OreGenResource resource) {
/* 22 */     map.put(resource.getName(), resource);
/* 23 */     System.out.println("[远古工艺 - GEO] 正在注册矿物生成器: " + resource.getName());
/*    */     
/* 25 */     Config cfg = new Config("plugins/Slimefun/generators/" + resource.getName() + ".cfg");
/* 26 */     for (Biome biome : Biome.values()) {
/* 27 */       cfg.setDefaultValue(biome.toString(), Integer.valueOf(resource.getDefaultSupply(biome)));
/*    */     }
/* 29 */     cfg.save();
/*    */   }
/*    */   
/*    */   public static OreGenResource getResource(String name) {
/* 33 */     return map.get(name);
/*    */   }
/*    */   
/*    */   private static int getDefault(OreGenResource resource, Biome biome) {
/* 37 */     if (resource == null) return 0; 
/* 38 */     Config cfg = new Config("plugins/Slimefun/generators/" + resource.getName() + ".cfg");
/* 39 */     return cfg.getInt(biome.toString());
/*    */   }
/*    */   
/*    */   public static void setSupplies(OreGenResource resource, Chunk chunk, int amount) {
/* 43 */     if (resource == null)
/* 44 */       return;  BlockStorage.setChunkInfo(chunk, "resources_" + resource.getName().toUpperCase(), String.valueOf(amount));
/*    */   }
/*    */   
/*    */   public static int generateSupplies(OreGenResource resource, Chunk chunk) {
/* 48 */     if (resource == null) return 0; 
/* 49 */     int supplies = getDefault(resource, chunk.getBlock(5, 50, 5).getBiome());
/* 50 */     BlockStorage.setChunkInfo(chunk, "resources_" + resource.getName().toUpperCase(), String.valueOf(supplies));
/* 51 */     return supplies;
/*    */   }
/*    */   
/*    */   public static int getSupplies(OreGenResource resource, Chunk chunk, boolean generate) {
/* 55 */     if (resource == null) return 0; 
/* 56 */     if (BlockStorage.hasChunkInfo(chunk, "resources_" + resource.getName().toUpperCase())) {
/* 57 */       return Integer.parseInt(BlockStorage.getChunkInfo(chunk, "resources_" + resource.getName().toUpperCase()));
/*    */     }
/* 59 */     if (!generate) {
/* 60 */       return 0;
/*    */     }
/*    */     
/* 63 */     return generateSupplies(resource, chunk);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean wasResourceGenerated(OreGenResource resource, Chunk chunk) {
/* 68 */     if (resource == null) return false; 
/* 69 */     return BlockStorage.hasChunkInfo(chunk, "resources_" + resource.getName().toUpperCase());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GEO\OreGenSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */