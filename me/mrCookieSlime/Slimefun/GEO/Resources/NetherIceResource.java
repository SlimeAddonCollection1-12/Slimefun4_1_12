/*    */ package me.mrCookieSlime.Slimefun.GEO.Resources;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import org.bukkit.block.Biome;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class NetherIceResource
/*    */   implements OreGenResource
/*    */ {
/*    */   public int getDefaultSupply(Biome biome) {
/* 13 */     switch (biome) {
/*    */       case HELL:
/* 15 */         return 32;
/*    */     } 
/*    */     
/* 18 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 25 */     return "下界玄冰";
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getIcon() {
/* 30 */     return SlimefunItems.NETHER_ICE.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMeasurementUnit() {
/* 35 */     return "块";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GEO\Resources\NetherIceResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */