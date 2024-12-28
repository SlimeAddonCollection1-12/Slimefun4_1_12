/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.Backpacks;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.PotionMeta;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ public class CoolerListener
/*    */   implements Listener {
/*    */   public CoolerListener(SlimefunStartup plugin) {
/* 22 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onStarve(FoodLevelChangeEvent e) {
/* 27 */     if (e.getFoodLevel() < ((Player)e.getEntity()).getFoodLevel()) {
/* 28 */       Player p = (Player)e.getEntity();
/* 29 */       for (ItemStack item : p.getInventory().getContents()) {
/* 30 */         if (SlimefunManager.isItemSimiliar(item, SlimefunItem.getItem("COOLER"), false)) {
/* 31 */           Inventory inv = Backpacks.getInventory(p, item);
/* 32 */           if (inv != null) {
/* 33 */             ItemStack drink = null;
/* 34 */             for (ItemStack i : inv.getContents()) {
/* 35 */               if (i != null && i.getType() == Material.POTION && i.hasItemMeta()) {
/* 36 */                 drink = i;
/*    */                 break;
/*    */               } 
/*    */             } 
/* 40 */             if (drink != null) {
/* 41 */               PotionMeta im = (PotionMeta)drink.getItemMeta();
/* 42 */               for (PotionEffect effect : im.getCustomEffects()) {
/* 43 */                 p.addPotionEffect(effect);
/*    */               }
/* 45 */               p.setSaturation(6.0F);
/* 46 */               p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
/* 47 */               inv.removeItem(new ItemStack[] { drink });
/* 48 */               Backpacks.saveBackpack(inv, item);
/*    */               break;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\CoolerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */