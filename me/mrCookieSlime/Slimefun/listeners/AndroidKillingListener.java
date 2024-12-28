/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*    */ import me.mrCookieSlime.Slimefun.Android.AndroidObject;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class AndroidKillingListener
/*    */   implements Listener
/*    */ {
/*    */   public AndroidKillingListener(SlimefunStartup plugin) {
/* 29 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onDeath(final EntityDeathEvent e) {
/* 34 */     if (e.getEntity().hasMetadata("android_killer")) {
/* 35 */       Iterator<MetadataValue> iterator = e.getEntity().getMetadata("android_killer").iterator(); if (iterator.hasNext()) { MetadataValue value = iterator.next();
/* 36 */         final AndroidObject obj = (AndroidObject)value.value();
/* 37 */         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*    */             {
/*    */               
/*    */               public void run()
/*    */               {
/* 42 */                 List<ItemStack> items = new ArrayList<>();
/* 43 */                 for (Entity n : e.getEntity().getNearbyEntities(0.5D, 0.5D, 0.5D)) {
/* 44 */                   if (n instanceof Item && !n.hasMetadata("no_pickup")) {
/* 45 */                     items.add(((Item)n).getItemStack());
/* 46 */                     n.remove();
/*    */                   } 
/*    */                 } 
/*    */                 
/* 50 */                 switch (e.getEntityType()) {
/*    */                   case BLAZE:
/* 52 */                     items.add(new ItemStack(Material.BLAZE_ROD, 1 + CSCoreLib.randomizer().nextInt(2)));
/*    */                     break;
/*    */                   
/*    */                   case PIG_ZOMBIE:
/* 56 */                     items.add(new ItemStack(Material.GOLD_NUGGET, 1 + CSCoreLib.randomizer().nextInt(3)));
/*    */                     break;
/*    */                   
/*    */                   case WITHER_SKELETON:
/* 60 */                     if (CSCoreLib.randomizer().nextInt(250) < 2) items.add((new MaterialData(Material.SKULL_ITEM, (byte)1)).toItemStack(1));
/*    */                     
/*    */                     break;
/*    */                 } 
/*    */ 
/*    */ 
/*    */                 
/* 67 */                 obj.getAndroid().addItems(obj.getBlock(), items.<ItemStack>toArray(new ItemStack[items.size()]));
/* 68 */                 ExperienceOrb exp = (ExperienceOrb)e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.EXPERIENCE_ORB);
/* 69 */                 exp.setExperience(1 + CSCoreLib.randomizer().nextInt(6));
/*    */               }
/*    */             }1L);
/*    */         return; }
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\AndroidKillingListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */