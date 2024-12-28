/*    */ package me.mrCookieSlime.Slimefun.Objects;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MultiBlock
/*    */ {
/* 13 */   public static List<MultiBlock> list = new ArrayList<>();
/*    */   
/*    */   Material[] blocks;
/*    */   Material trigger;
/*    */   
/*    */   public MultiBlock(Material[] build, Material trigger) {
/* 19 */     this.blocks = build;
/* 20 */     this.trigger = trigger;
/*    */   }
/*    */   
/*    */   public Material[] getBuild() {
/* 24 */     return this.blocks;
/*    */   }
/*    */   
/*    */   public Material getTriggerBlock() {
/* 28 */     return this.trigger;
/*    */   }
/*    */   
/*    */   public void register() {
/* 32 */     list.add(this);
/*    */   }
/*    */   
/*    */   public static List<MultiBlock> list() {
/* 36 */     return list;
/*    */   }
/*    */   
/*    */   public boolean isMultiBlock(SlimefunItem machine) {
/* 40 */     if (machine == null) return false; 
/* 41 */     if (!(machine instanceof SlimefunMachine)) return false; 
/* 42 */     if (machine instanceof SlimefunMachine) {
/* 43 */       MultiBlock mb = ((SlimefunMachine)machine).toMultiBlock();
/* 44 */       if (this.trigger == mb.getTriggerBlock()) {
/* 45 */         for (int i = 0; i < (mb.getBuild()).length; i++) {
/* 46 */           if (mb.getBuild()[i] != null)
/* 47 */             if (mb.getBuild()[i] == Material.LOG)
/* 48 */             { if (!this.blocks[i].toString().contains("LOG")) return false;
/*    */                }
/* 50 */             else if (mb.getBuild()[i] != this.blocks[i]) { return false; }
/*    */              
/*    */         } 
/* 53 */         return true;
/*    */       } 
/* 55 */       return false;
/*    */     } 
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isMultiBlock(MultiBlock mb) {
/* 61 */     if (mb == null) return false; 
/* 62 */     if (this.trigger == mb.getTriggerBlock()) {
/* 63 */       for (int i = 0; i < (mb.getBuild()).length; i++) {
/* 64 */         if (mb.getBuild()[i] != null)
/* 65 */           if (mb.getBuild()[i] == Material.LOG)
/* 66 */           { if (!this.blocks[i].toString().contains("LOG")) return false;
/*    */              }
/* 68 */           else if (mb.getBuild()[i] != this.blocks[i]) { return false; }
/*    */            
/*    */       } 
/* 71 */       return true;
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\MultiBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */