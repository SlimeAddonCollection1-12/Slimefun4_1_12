/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Talisman;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerItemBreakEvent;
/*     */ import org.bukkit.event.player.PlayerToggleSprintEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class TalismanListener
/*     */   implements Listener
/*     */ {
/*     */   public TalismanListener(SlimefunStartup plugin) {
/*  38 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void onDamageGet(EntityDamageEvent e) {
/*  43 */     if (!e.isCancelled()) {
/*  44 */       if (e instanceof EntityDamageByEntityEvent && (
/*  45 */         (EntityDamageByEntityEvent)e).getDamager() instanceof Player && SlimefunStartup.chance(100, 45) && 
/*  46 */         SlimefunManager.isItemSimiliar(((Player)((EntityDamageByEntityEvent)e).getDamager()).getInventory().getItemInMainHand(), SlimefunItem.getItem("BLADE_OF_VAMPIRES"), true)) {
/*  47 */         ((Player)((EntityDamageByEntityEvent)e).getDamager()).playSound(((EntityDamageByEntityEvent)e).getDamager().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.7F, 0.7F);
/*  48 */         ((Player)((EntityDamageByEntityEvent)e).getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
/*     */       } 
/*     */ 
/*     */       
/*  52 */       if (e.getEntity() instanceof Player && 
/*  53 */         !e.isCancelled()) {
/*  54 */         if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) Talisman.checkFor((Event)e, SlimefunItem.getByID("LAVA_TALISMAN")); 
/*  55 */         if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) Talisman.checkFor((Event)e, SlimefunItem.getByID("WATER_TALISMAN")); 
/*  56 */         if (e.getCause() == EntityDamageEvent.DamageCause.FALL) Talisman.checkFor((Event)e, SlimefunItem.getByID("ANGEL_TALISMAN")); 
/*  57 */         if (e.getCause() == EntityDamageEvent.DamageCause.FIRE) Talisman.checkFor((Event)e, SlimefunItem.getByID("FIRE_TALISMAN")); 
/*  58 */         if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) Talisman.checkFor((Event)e, SlimefunItem.getByID("WARRIOR_TALISMAN")); 
/*  59 */         if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) Talisman.checkFor((Event)e, SlimefunItem.getByID("KNIGHT_TALISMAN")); 
/*  60 */         if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE && 
/*  61 */           Talisman.checkFor((Event)e, SlimefunItem.getByID("WHIRLWIND_TALISMAN")) && (
/*  62 */           (EntityDamageByEntityEvent)e).getDamager() instanceof Projectile) {
/*  63 */           Vector direction = ((Player)e.getEntity()).getEyeLocation().getDirection().multiply(2.0D);
/*  64 */           Projectile projectile = (Projectile)e.getEntity().getWorld().spawnEntity(((LivingEntity)e.getEntity()).getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), ((EntityDamageByEntityEvent)e).getDamager().getType());
/*  65 */           projectile.setVelocity(direction);
/*  66 */           ((EntityDamageByEntityEvent)e).getDamager().remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onItemBreak(PlayerItemBreakEvent e) {
/*  77 */     if (Talisman.checkFor((Event)e, SlimefunItem.getByID("ANVIL_TALISMAN"))) e.getBrokenItem().setAmount(1); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSprint(PlayerToggleSprintEvent e) {
/*  82 */     if (e.isSprinting()) Talisman.checkFor((Event)e, SlimefunItem.getByID("TRAVELLER_TALISMAN")); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEnchant(EnchantItemEvent e) {
/*  87 */     if (Talisman.checkFor((Event)e, SlimefunItem.getByID("MAGICIAN_TALISMAN"))) {
/*  88 */       List<String> enchantments = new ArrayList<>();
/*  89 */       for (Enchantment en : Enchantment.values()) {
/*  90 */         for (int i = 1; i <= en.getMaxLevel(); i++) {
/*  91 */           if (((Boolean)Slimefun.getItemValue("MAGICIAN_TALISMAN", "allow-enchantments." + en.getName() + ".level." + i)).booleanValue() && en.canEnchantItem(e.getItem())) enchantments.add(en.getName() + "-" + i); 
/*     */         } 
/*     */       } 
/*  94 */       String enchant = enchantments.get(SlimefunStartup.randomize(enchantments.size()));
/*  95 */       e.getEnchantsToAdd().put(Enchantment.getByName(enchant.split("-")[0]), Integer.valueOf(Integer.parseInt(enchant.split("-")[1])));
/*     */     } 
/*     */     
/*  98 */     if (!e.getEnchantsToAdd().containsKey(Enchantment.SILK_TOUCH) && Enchantment.LOOT_BONUS_BLOCKS.canEnchantItem(e.getItem()) && 
/*  99 */       Talisman.checkFor((Event)e, SlimefunItem.getByID("WIZARD_TALISMAN"))) {
/* 100 */       if (e.getEnchantsToAdd().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) e.getEnchantsToAdd().remove(Enchantment.LOOT_BONUS_BLOCKS); 
/* 101 */       Set<Enchantment> enchantments = e.getEnchantsToAdd().keySet();
/* 102 */       for (Enchantment en : enchantments) {
/* 103 */         if (SlimefunStartup.chance(100, 40)) e.getEnchantsToAdd().put(en, Integer.valueOf(SlimefunStartup.randomize(3) + 1));
/*     */       
/*     */       } 
/* 106 */       e.getItem().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, SlimefunStartup.randomize(3) + 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent e) {
/* 118 */     List<ItemStack> drops = new ArrayList<>();
/* 119 */     ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
/* 120 */     int fortune = 1;
/*     */     
/* 122 */     if (item != null) {
/* 123 */       if (item.getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS) && !item.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
/* 124 */         fortune = SlimefunStartup.randomize(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 1;
/* 125 */         if (fortune <= 0) fortune = 1; 
/* 126 */         fortune = ((e.getBlock().getType() == Material.LAPIS_ORE) ? (4 + SlimefunStartup.randomize(5)) : 1) * (fortune + 1);
/*     */       } 
/*     */       
/* 129 */       if (!item.getEnchantments().containsKey(Enchantment.SILK_TOUCH) && e.getBlock().getType().toString().endsWith("_ORE") && 
/* 130 */         Talisman.checkFor((Event)e, SlimefunItem.getByID("MINER_TALISMAN"))) {
/* 131 */         if (drops.isEmpty()) drops = (List<ItemStack>)e.getBlock().getDrops(); 
/* 132 */         for (ItemStack drop : new ArrayList(drops)) {
/* 133 */           if (!drop.getType().isBlock()) drops.add(new CustomItem(drop, fortune * 2)); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\TalismanListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */