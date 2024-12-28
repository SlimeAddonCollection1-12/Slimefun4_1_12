/*    */ package me.mrCookieSlime.Slimefun.AncientAltar;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pedestals
/*    */ {
/* 18 */   public static List<AltarRecipe> recipes = new ArrayList<>();
/*    */   
/*    */   public static List<Block> getPedestals(Block altar) {
/* 21 */     List<Block> list = new ArrayList<>();
/*    */     
/* 23 */     if (BlockStorage.check(altar.getRelative(3, 0, 0), "ANCIENT_PEDESTAL")) {
/* 24 */       list.add(altar.getRelative(3, 0, 0));
/*    */     }
/* 26 */     if (BlockStorage.check(altar.getRelative(-3, 0, 0), "ANCIENT_PEDESTAL")) {
/* 27 */       list.add(altar.getRelative(-3, 0, 0));
/*    */     }
/* 29 */     if (BlockStorage.check(altar.getRelative(0, 0, 3), "ANCIENT_PEDESTAL")) {
/* 30 */       list.add(altar.getRelative(0, 0, 3));
/*    */     }
/* 32 */     if (BlockStorage.check(altar.getRelative(0, 0, -3), "ANCIENT_PEDESTAL")) {
/* 33 */       list.add(altar.getRelative(0, 0, -3));
/*    */     }
/* 35 */     if (BlockStorage.check(altar.getRelative(2, 0, 2), "ANCIENT_PEDESTAL")) {
/* 36 */       list.add(altar.getRelative(2, 0, 2));
/*    */     }
/* 38 */     if (BlockStorage.check(altar.getRelative(2, 0, -2), "ANCIENT_PEDESTAL")) {
/* 39 */       list.add(altar.getRelative(2, 0, -2));
/*    */     }
/* 41 */     if (BlockStorage.check(altar.getRelative(-2, 0, 2), "ANCIENT_PEDESTAL")) {
/* 42 */       list.add(altar.getRelative(-2, 0, 2));
/*    */     }
/* 44 */     if (BlockStorage.check(altar.getRelative(-2, 0, -2), "ANCIENT_PEDESTAL")) {
/* 45 */       list.add(altar.getRelative(-2, 0, -2));
/*    */     }
/*    */     
/* 48 */     return list;
/*    */   }
/*    */   
/*    */   public static ItemStack getRecipeOutput(ItemStack catalyst, List<ItemStack> input) {
/* 52 */     if (input.size() != 8) return null; 
/* 53 */     if (SlimefunManager.isItemSimiliar(catalyst, SlimefunItems.BROKEN_SPAWNER, false)) {
/* 54 */       if (checkRecipe(SlimefunItems.BROKEN_SPAWNER, input) == null) return null; 
/* 55 */       ItemStack spawner = SlimefunItems.REPAIRED_SPAWNER.clone();
/* 56 */       ItemMeta im = spawner.getItemMeta();
/* 57 */       im.setLore(Arrays.asList(new String[] { catalyst.getItemMeta().getLore().get(0) }));
/* 58 */       spawner.setItemMeta(im);
/* 59 */       return spawner;
/*    */     } 
/*    */     
/* 62 */     return checkRecipe(catalyst, input);
/*    */   }
/*    */   
/*    */   private static ItemStack checkRecipe(ItemStack catalyst, List<ItemStack> input) {
/* 66 */     AltarRecipe r = null;
/* 67 */     for (AltarRecipe recipe : recipes) {
/* 68 */       if (SlimefunManager.isItemSimiliar(catalyst, recipe.getCatalyst(), true)) {
/* 69 */         r = recipe;
/*    */         
/* 71 */         List<ItemStack> copy = new ArrayList<>(input);
/*    */ 
/*    */         
/* 74 */         for (ItemStack item : recipe.getInput()) {
/* 75 */           Iterator<ItemStack> iterator = copy.iterator();
/* 76 */           boolean match = false;
/*    */ 
/*    */           
/* 79 */           while (iterator.hasNext()) {
/* 80 */             ItemStack altar_item = iterator.next();
/* 81 */             if (SlimefunManager.isItemSimiliar(altar_item, item, true)) {
/* 82 */               match = true;
/* 83 */               iterator.remove();
/*    */               
/*    */               break;
/*    */             } 
/*    */           } 
/* 88 */           if (!match) {
/* 89 */             r = null;
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/* 94 */         if (r != null) return r.getOutput(); 
/*    */       } 
/*    */     } 
/* 97 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\AncientAltar\Pedestals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */