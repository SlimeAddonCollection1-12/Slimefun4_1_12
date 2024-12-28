/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.SkullItem;
/*     */ import me.mrCookieSlime.EmeraldEnchants.EmeraldEnchants;
/*     */ import me.mrCookieSlime.EmeraldEnchants.ItemEnchantment;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Talisman;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import me.mrCookieSlime.Slimefun.api.Soul;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Skeleton;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DamageListener
/*     */   implements Listener
/*     */ {
/*     */   private SimpleDateFormat format;
/*     */   
/*     */   public DamageListener(SlimefunStartup plugin) {
/*  42 */     this.format = new SimpleDateFormat("(MMM d, yyyy @ hh:mm)");
/*     */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   } @EventHandler
/*     */   public void onDamage(EntityDeathEvent e) {
/*  46 */     if (e.getEntity() instanceof Player) {
/*  47 */       Player p = (Player)e.getEntity();
/*  48 */       if (p.getInventory().containsAtLeast(SlimefunItems.GPS_EMERGENCY_TRANSMITTER, 1)) {
/*  49 */         Slimefun.getGPSNetwork().addWaypoint(p, "&4死亡点 &7" + this.format.format(new Date()), p.getLocation().getBlock().getLocation());
/*     */       }
/*  51 */       Iterator<ItemStack> drops = e.getDrops().iterator();
/*  52 */       while (drops.hasNext()) {
/*  53 */         ItemStack item = drops.next();
/*  54 */         if (item != null) {
/*  55 */           if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BOUND_BACKPACK, false)) {
/*  56 */             Soul.storeItem(e.getEntity().getUniqueId(), item);
/*  57 */             drops.remove(); continue;
/*  58 */           }  if (SlimefunItem.getByItem(removeEnchantments(item)) != null && 
/*  59 */             SlimefunItem.getByItem(removeEnchantments(item)) instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SoulboundItem) {
/*  60 */             Soul.storeItem(e.getEntity().getUniqueId(), item);
/*  61 */             drops.remove();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  68 */     if (e.getEntity().getKiller() instanceof Player) {
/*  69 */       Player p = e.getEntity().getKiller();
/*  70 */       ItemStack item = p.getInventory().getItemInMainHand();
/*     */       
/*  72 */       if (SlimefunManager.drops.containsKey(e.getEntity().getType())) {
/*  73 */         for (ItemStack drop : SlimefunManager.drops.get(e.getEntity().getType())) {
/*  74 */           if (Slimefun.hasUnlocked(p, item, true)) {
/*  75 */             e.getDrops().add(drop);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/*  80 */       if (item != null && 
/*  81 */         Slimefun.hasUnlocked(p, item, true) && 
/*  82 */         SlimefunManager.isItemSimiliar(item, SlimefunItem.getItem("SWORD_OF_BEHEADING"), true)) {
/*  83 */         if (e.getEntity() instanceof org.bukkit.entity.Zombie) {
/*  84 */           if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("SWORD_OF_BEHEADING", "chance.ZOMBIE")).intValue())) {
/*  85 */             e.getDrops().add(new CustomItem(Material.SKULL_ITEM, 2));
/*     */           }
/*  87 */         } else if (e.getEntity() instanceof Skeleton) {
/*  88 */           switch (((Skeleton)e.getEntity()).getSkeletonType()) {
/*     */             case NORMAL:
/*  90 */               if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("SWORD_OF_BEHEADING", "chance.SKELETON")).intValue())) {
/*  91 */                 e.getDrops().add(new CustomItem(Material.SKULL_ITEM, 0));
/*     */               }
/*     */               break;
/*     */             case WITHER:
/*  95 */               if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("SWORD_OF_BEHEADING", "chance.WITHER_SKELETON")).intValue())) {
/*  96 */                 e.getDrops().add(new CustomItem(Material.SKULL_ITEM, 1));
/*     */               }
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/* 102 */         } else if (e.getEntity() instanceof org.bukkit.entity.Creeper) {
/* 103 */           if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("SWORD_OF_BEHEADING", "chance.CREEPER")).intValue())) {
/* 104 */             e.getDrops().add(new CustomItem(Material.SKULL_ITEM, 4));
/*     */           }
/* 106 */         } else if (e.getEntity() instanceof Player && 
/* 107 */           SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("SWORD_OF_BEHEADING", "chance.PLAYER")).intValue())) {
/* 108 */           e.getDrops().add(new SkullItem(((Player)e.getEntity()).getName()));
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       if (!e.getEntity().getCanPickupItems() && Talisman.checkFor((Event)e, SlimefunItem.getByID("HUNTER_TALISMAN")) && !(e.getEntity() instanceof Player)) {
/* 116 */         List<ItemStack> newDrops = new ArrayList<>();
/* 117 */         for (ItemStack drop : e.getDrops()) {
/* 118 */           newDrops.add(drop);
/*     */         }
/* 120 */         for (ItemStack drop : newDrops) {
/* 121 */           e.getDrops().add(drop);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onArrowHit(EntityDamageEvent e) {
/* 129 */     if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL && 
/* 130 */       Variables.damage.containsKey(e.getEntity().getUniqueId())) {
/* 131 */       e.setCancelled(true);
/* 132 */       Variables.damage.remove(e.getEntity().getUniqueId());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onRespawn(PlayerRespawnEvent e) {
/* 139 */     Soul.retrieveItems(e.getPlayer());
/*     */   }
/*     */   
/*     */   private ItemStack removeEnchantments(ItemStack itemStack) {
/* 143 */     ItemStack strippedItem = itemStack.clone();
/*     */     
/* 145 */     for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
/* 146 */       strippedItem.removeEnchantment(enchantment);
/*     */     }
/*     */     
/* 149 */     if (Slimefun.isEmeraldEnchantsInstalled()) {
/* 150 */       for (ItemEnchantment enchantment : EmeraldEnchants.getInstance().getRegistry().getEnchantments(itemStack)) {
/* 151 */         EmeraldEnchants.getInstance().getRegistry().applyEnchantment(strippedItem, enchantment.getEnchantment(), 0);
/*     */       }
/*     */     }
/* 154 */     return strippedItem;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\DamageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */