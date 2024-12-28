/*    */ package me.mrCookieSlime.Slimefun.GEO.Resources;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*    */ import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import org.bukkit.block.Biome;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class OilResource
/*    */   implements OreGenResource
/*    */ {
/*    */   public int getDefaultSupply(Biome biome) {
/* 14 */     switch (biome) {
/*    */       case COLD_BEACH:
/*    */       case STONE_BEACH:
/*    */       case BEACHES:
/* 18 */         return CSCoreLib.randomizer().nextInt(6) + 2;
/*    */ 
/*    */       
/*    */       case DESERT:
/*    */       case DESERT_HILLS:
/*    */       case MUTATED_DESERT:
/* 24 */         return CSCoreLib.randomizer().nextInt(40) + 19;
/*    */ 
/*    */       
/*    */       case EXTREME_HILLS:
/*    */       case MUTATED_EXTREME_HILLS:
/*    */       case SMALLER_EXTREME_HILLS:
/*    */       case RIVER:
/* 31 */         return CSCoreLib.randomizer().nextInt(14) + 13;
/*    */ 
/*    */       
/*    */       case ICE_MOUNTAINS:
/*    */       case ICE_FLATS:
/*    */       case MUTATED_ICE_FLATS:
/*    */       case FROZEN_OCEAN:
/*    */       case FROZEN_RIVER:
/* 39 */         return CSCoreLib.randomizer().nextInt(11) + 3;
/*    */ 
/*    */       
/*    */       case SKY:
/*    */       case HELL:
/* 44 */         return 0;
/*    */ 
/*    */ 
/*    */       
/*    */       case MESA:
/*    */       case MESA_CLEAR_ROCK:
/*    */       case MESA_ROCK:
/*    */       case MUTATED_MESA:
/*    */       case MUTATED_MESA_CLEAR_ROCK:
/*    */       case MUTATED_MESA_ROCK:
/*    */       case MUSHROOM_ISLAND:
/*    */       case MUSHROOM_ISLAND_SHORE:
/* 56 */         return CSCoreLib.randomizer().nextInt(24) + 14;
/*    */ 
/*    */       
/*    */       case DEEP_OCEAN:
/*    */       case OCEAN:
/* 61 */         return CSCoreLib.randomizer().nextInt(62) + 24;
/*    */ 
/*    */       
/*    */       case SWAMPLAND:
/*    */       case MUTATED_SWAMPLAND:
/* 66 */         return CSCoreLib.randomizer().nextInt(20) + 4;
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return CSCoreLib.randomizer().nextInt(10) + 6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 77 */     return "原油";
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getIcon() {
/* 82 */     return SlimefunItems.BUCKET_OF_OIL.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMeasurementUnit() {
/* 87 */     return "桶";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GEO\Resources\OilResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */