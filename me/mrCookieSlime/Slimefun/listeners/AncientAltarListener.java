/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
/*     */ import me.mrCookieSlime.Slimefun.AncientAltar.Pedestals;
/*     */ import me.mrCookieSlime.Slimefun.AncientAltar.RitualAnimation;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class AncientAltarListener implements Listener {
/*     */   List<Block> altars;
/*     */   Set<UUID> removed_items;
/*     */   
/*     */   public AncientAltarListener(SlimefunStartup plugin) {
/*  45 */     this.altars = new ArrayList<>();
/*  46 */     this.removed_items = new HashSet<>();
/*     */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   } @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void onInteract(PlayerInteractEvent e) {
/*  50 */     if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
/*  51 */       return;  Block b = e.getClickedBlock();
/*  52 */     SlimefunItem item = BlockStorage.check(b);
/*  53 */     if (item != null) {
/*  54 */       if (item.getID().equals("ANCIENT_PEDESTAL")) {
/*  55 */         if (Variables.altarinuse.contains(b.getLocation())) {
/*  56 */           e.setCancelled(true);
/*     */           return;
/*     */         } 
/*  59 */         e.setCancelled(true);
/*  60 */         Item stack = findItem(b);
/*  61 */         if (stack == null) {
/*  62 */           if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
/*  63 */             return;  if (b.getRelative(0, 1, 0).getType() != Material.AIR) {
/*  64 */             Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.ANCIENT_PEDESTAL.obstructed", true, new Variable[0]);
/*     */             return;
/*     */           } 
/*  67 */           insertItem(e.getPlayer(), b);
/*     */         }
/*  69 */         else if (!this.removed_items.contains(stack.getUniqueId())) {
/*  70 */           final UUID uuid = stack.getUniqueId();
/*  71 */           this.removed_items.add(uuid);
/*     */           
/*  73 */           SlimefunStartup.instance.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */               {
/*     */                 public void run()
/*     */                 {
/*  77 */                   AncientAltarListener.this.removed_items.remove(uuid);
/*     */                 }
/*     */               },  30L);
/*     */           
/*  81 */           stack.remove();
/*  82 */           e.getPlayer().getInventory().addItem(new ItemStack[] { fixItemStack(stack.getItemStack(), stack.getCustomName()) });
/*  83 */           e.getPlayer().playSound(b.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
/*  84 */           PlayerInventory.update(e.getPlayer());
/*     */         }
/*     */       
/*  87 */       } else if (item.getID().equals("ANCIENT_ALTAR")) {
/*  88 */         if (Variables.altarinuse.contains(b.getLocation())) {
/*  89 */           e.setCancelled(true);
/*     */           return;
/*     */         } 
/*  92 */         Variables.altarinuse.add(b.getLocation());
/*  93 */         e.setCancelled(true);
/*     */         
/*  95 */         CustomItem customItem = new CustomItem(e.getPlayer().getInventory().getItemInMainHand(), 1);
/*  96 */         List<Block> pedestals = Pedestals.getPedestals(b);
/*     */         
/*  98 */         if (!this.altars.contains(e.getClickedBlock())) {
/*  99 */           this.altars.add(e.getClickedBlock());
/* 100 */           if (pedestals.size() == 8) {
/* 101 */             pedestals.forEach(pblock -> Variables.altarinuse.add(pblock.getLocation()));
/*     */ 
/*     */             
/* 104 */             if (customItem != null && !customItem.getType().equals(Material.AIR)) {
/* 105 */               List<ItemStack> input = new ArrayList<>();
/* 106 */               for (Block pedestal : pedestals) {
/* 107 */                 Item stack = findItem(pedestal);
/* 108 */                 if (stack != null) input.add(fixItemStack(stack.getItemStack(), stack.getCustomName()));
/*     */               
/*     */               } 
/* 111 */               ItemStack result = Pedestals.getRecipeOutput((ItemStack)customItem, input);
/* 112 */               if (result != null) {
/* 113 */                 List<ItemStack> consumed = new ArrayList<>();
/* 114 */                 consumed.add(customItem);
/* 115 */                 PlayerInventory.consumeItemInHand(e.getPlayer());
/* 116 */                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, (Runnable)new RitualAnimation(this.altars, b, b.getLocation().add(0.5D, 1.3D, 0.5D), result, pedestals, consumed), 10L);
/*     */               } else {
/*     */                 
/* 119 */                 this.altars.remove(e.getClickedBlock());
/* 120 */                 Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.ANCIENT_ALTAR.unknown-recipe", true, new Variable[0]);
/* 121 */                 pedestals.forEach(pblock -> Variables.altarinuse.remove(pblock.getLocation()));
/*     */ 
/*     */                 
/* 124 */                 Variables.altarinuse.remove(b.getLocation());
/*     */               } 
/*     */             } else {
/*     */               
/* 128 */               this.altars.remove(e.getClickedBlock());
/* 129 */               Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.ANCIENT_ALTAR.unknown-catalyst", true, new Variable[0]);
/* 130 */               pedestals.forEach(pblock -> Variables.altarinuse.remove(pblock.getLocation()));
/*     */ 
/*     */               
/* 133 */               Variables.altarinuse.remove(b.getLocation());
/*     */             } 
/*     */           } else {
/*     */             
/* 137 */             this.altars.remove(e.getClickedBlock());
/* 138 */             Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.ANCIENT_ALTAR.not-enough-pedestals", true, new Variable[] { new Variable("%pedestals%", String.valueOf(pedestals.size())) });
/* 139 */             Variables.altarinuse.remove(b.getLocation());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static ItemStack fixItemStack(ItemStack itemStack, String customName) {
/* 147 */     ItemStack stack = itemStack.clone();
/* 148 */     if (customName.equals(StringUtils.formatItemName(itemStack.getData().toItemStack(1), false))) {
/* 149 */       ItemMeta im = stack.getItemMeta();
/* 150 */       im.setDisplayName(null);
/* 151 */       stack.setItemMeta(im);
/*     */     } else {
/*     */       
/* 154 */       ItemMeta im = stack.getItemMeta();
/* 155 */       im.setDisplayName(customName);
/* 156 */       stack.setItemMeta(im);
/*     */     } 
/* 158 */     return stack;
/*     */   }
/*     */   
/*     */   public static Item findItem(Block b) {
/* 162 */     for (Entity n : b.getChunk().getEntities()) {
/* 163 */       if (n instanceof Item && 
/* 164 */         b.getLocation().add(0.5D, 1.2D, 0.5D).distanceSquared(n.getLocation()) < 0.5D && n.getCustomName() != null) return (Item)n;
/*     */     
/*     */     } 
/* 167 */     return null;
/*     */   }
/*     */   
/*     */   private void insertItem(Player p, Block b) {
/* 171 */     ItemStack stack = p.getInventory().getItemInMainHand();
/* 172 */     if (stack != null) {
/* 173 */       PlayerInventory.consumeItemInHand(p);
/* 174 */       String nametag = StringUtils.formatItemName(stack, false);
/* 175 */       Item entity = b.getWorld().dropItem(b.getLocation().add(0.5D, 1.2D, 0.5D), (ItemStack)new CustomItem((ItemStack)new CustomItem(stack, 1), "&5&d祭坛 &3灵柱 - &e" + System.nanoTime()));
/* 176 */       entity.setVelocity(new Vector(0.0D, 0.1D, 0.0D));
/* 177 */       entity.setMetadata("no_pickup", (MetadataValue)new FixedMetadataValue((Plugin)SlimefunStartup.instance, "altar_item"));
/* 178 */       entity.setCustomNameVisible(true);
/* 179 */       entity.setCustomName(nametag);
/* 180 */       p.playSound(b.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.3F, 0.3F);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*     */   public void onBlockPlace(BlockPlaceEvent e) {
/* 186 */     Block b = e.getBlockPlaced().getRelative(0, -1, 0);
/* 187 */     SlimefunItem item = BlockStorage.check(b);
/* 188 */     if (item == null)
/* 189 */       return;  if (item.getID().equalsIgnoreCase("ANCIENT_PEDESTAL")) {
/* 190 */       Messages.local.sendTranslation((CommandSender)e.getPlayer(), "messages.cannot-place", true, new Variable[0]);
/* 191 */       e.setCancelled(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\AncientAltarListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */