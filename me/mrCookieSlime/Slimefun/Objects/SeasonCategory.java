/*    */ package me.mrCookieSlime.Slimefun.Objects;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SeasonCategory
/*    */   extends Category
/*    */ {
/* 21 */   private int month = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SeasonCategory(int month, int tier, ItemStack item) {
/* 36 */     super(item, tier);
/* 37 */     this.month = month - 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMonth() {
/* 49 */     return this.month;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUnlocked() {
/* 61 */     if (this.month == -1) return true; 
/* 62 */     Calendar calendar = Calendar.getInstance();
/* 63 */     return (this.month == calendar.get(2));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SeasonCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */