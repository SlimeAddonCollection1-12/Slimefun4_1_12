/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnderTalisman
/*    */   extends SlimefunItem
/*    */ {
/*    */   private String suffix;
/*    */   private boolean consumable;
/*    */   private boolean cancel;
/*    */   private PotionEffect[] effects;
/*    */   private int chance;
/*    */   
/*    */   public EnderTalisman(Talisman parent) {
/* 22 */     super((Category)Categories.TALISMANS_2, parent.upgrade(), "ENDER_" + parent.getID(), RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItem.getItem("ENDER_LUMP_3"), null, SlimefunItem.getItem("ENDER_LUMP_3"), null, parent.getItem(), null, SlimefunItem.getItem("ENDER_LUMP_3"), null, SlimefunItem.getItem("ENDER_LUMP_3") }, parent.upgrade());
/* 23 */     this.consumable = parent.isConsumable();
/* 24 */     this.cancel = parent.isEventCancelled();
/* 25 */     this.suffix = parent.getSuffix();
/* 26 */     this.effects = parent.getEffects();
/* 27 */     this.chance = parent.getChance();
/* 28 */     Slimefun.addHint("ENDER_" + parent.getID(), new String[] { "&e末影护身符更加强大", "&e它能在你的末影箱中", "&e为你保驾护航" });
/*    */   }
/*    */   
/* 31 */   public String getSuffix() { return this.suffix; }
/* 32 */   public boolean isConsumable() { return this.consumable; }
/* 33 */   public boolean isEventCancelled() { return this.cancel; }
/* 34 */   public PotionEffect[] getEffects() { return this.effects; } public int getChance() {
/* 35 */     return this.chance;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\EnderTalisman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */