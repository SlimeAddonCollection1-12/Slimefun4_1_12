/*    */ package me.mrCookieSlime.Slimefun.api;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.mrCookieSlime.Slimefun.Variables;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Soul
/*    */ {
/*    */   public static void storeItem(UUID uuid, ItemStack drop) {
/* 15 */     List<ItemStack> items = new ArrayList<>();
/* 16 */     if (Variables.soulbound.containsKey(uuid)) items = (List<ItemStack>)Variables.soulbound.get(uuid); 
/* 17 */     items.add(drop);
/* 18 */     Variables.soulbound.put(uuid, items);
/*    */   }
/*    */   
/*    */   public static void retrieveItems(Player p) {
/* 22 */     if (Variables.soulbound.containsKey(p.getUniqueId())) {
/* 23 */       for (ItemStack item : Variables.soulbound.get(p.getUniqueId())) {
/* 24 */         if (item.equals(p.getInventory().getHelmet()) || 
/* 25 */           item.equals(p.getInventory().getChestplate()) || 
/* 26 */           item.equals(p.getInventory().getLeggings()) || 
/* 27 */           item.equals(p.getInventory().getBoots()) || 
/* 28 */           item.equals(p.getInventory().getItemInOffHand()))
/*    */           continue; 
/* 30 */         if (!p.getInventory().contains(item)) {
/* 31 */           p.getInventory().addItem(new ItemStack[] { item });
/*    */         }
/*    */       } 
/* 34 */       Variables.soulbound.remove(p.getUniqueId());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\Soul.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */