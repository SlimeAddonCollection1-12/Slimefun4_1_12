/*    */ package me.mrCookieSlime.Slimefun.AncientAltar;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class AltarRecipe
/*    */ {
/*    */   ItemStack catalyst;
/*    */   List<ItemStack> input;
/*    */   ItemStack output;
/*    */   
/*    */   public AltarRecipe(List<ItemStack> input, ItemStack output) {
/* 15 */     this.catalyst = input.get(4);
/* 16 */     this.input = new ArrayList<>();
/* 17 */     for (int i = 0; i < input.size(); i++) {
/* 18 */       if (i != 4) this.input.add(input.get(i)); 
/*    */     } 
/* 20 */     this.output = output;
/*    */     
/* 22 */     Pedestals.recipes.add(this);
/*    */   }
/*    */   
/*    */   public ItemStack getCatalyst() {
/* 26 */     return this.catalyst;
/*    */   }
/*    */   
/*    */   public ItemStack getOutput() {
/* 30 */     return this.output;
/*    */   }
/*    */   
/*    */   public List<ItemStack> getInput() {
/* 34 */     return this.input;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\AncientAltar\AltarRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */