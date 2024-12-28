/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Furnace;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class EnhancedFurnace
/*    */   extends SlimefunItem {
/*    */   int speed;
/*    */   
/*    */   public EnhancedFurnace(int speed, int efficiency, int fortune, ItemStack item, String id, ItemStack[] recipe) {
/* 18 */     super(Categories.MACHINES_1, item, id, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
/*    */     
/* 20 */     this.speed = speed - 1;
/* 21 */     this.efficiency = efficiency - 1;
/* 22 */     this.fortune = fortune - 1;
/*    */     
/* 24 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*    */           {
/*    */             public void tick(Block b, SlimefunItem item, Config data) {
/* 27 */               if (b.getState() instanceof Furnace && (
/* 28 */                 (Furnace)b.getState()).getCookTime() > 0) {
/* 29 */                 Furnace furnace = (Furnace)b.getState();
/* 30 */                 furnace.setCookTime((short)(furnace.getCookTime() + EnhancedFurnace.this.getSpeed() * 10));
/* 31 */                 furnace.update(true, false);
/*    */               } 
/*    */             }
/*    */ 
/*    */ 
/*    */             
/*    */             public void uniqueTick() {}
/*    */ 
/*    */             
/*    */             public boolean isSynchronized() {
/* 41 */               return true;
/*    */             }
/*    */           } });
/*    */   }
/*    */   int efficiency; int fortune;
/*    */   public int getSpeed() {
/* 47 */     return this.speed;
/*    */   }
/*    */   
/*    */   public int getFuelEfficiency() {
/* 51 */     return this.speed;
/*    */   }
/*    */   
/*    */   public int getOutput() {
/* 55 */     int fortune = this.fortune;
/* 56 */     fortune = SlimefunStartup.randomize(fortune + 2) - 1;
/* 57 */     if (fortune <= 0) fortune = 0; 
/* 58 */     fortune++;
/* 59 */     return fortune;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\EnhancedFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */