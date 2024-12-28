/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Misc.BookDesign;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.MultiTool;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemInteractionHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunGuide;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Hopper;
/*     */ import org.bukkit.block.Skull;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*     */ import org.bukkit.event.inventory.CraftItemEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryMoveItemEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemListener
/*     */   implements Listener
/*     */ {
/*     */   public ItemListener(SlimefunStartup plugin) {
/*  65 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onIgnitionChamberItemMove(InventoryMoveItemEvent e) {
/*  75 */     if (e.getInitiator().getHolder() instanceof Hopper && 
/*  76 */       BlockStorage.check(((Hopper)e.getInitiator().getHolder()).getBlock(), "IGNITION_CHAMBER")) {
/*  77 */       e.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void debug(PlayerInteractEvent e) {
/*  85 */     if (e.getAction().equals(Action.PHYSICAL) || !e.getHand().equals(EquipmentSlot.HAND))
/*  86 */       return;  Player p = e.getPlayer();
/*  87 */     if (SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInMainHand(), SlimefunItems.DEBUG_FISH, true) || SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInOffHand(), SlimefunItems.DEBUG_FISH, true)) {
/*  88 */       e.setCancelled(true);
/*  89 */       if (p.isOp()) {
/*  90 */         switch (e.getAction()) {
/*     */           case CHEST:
/*  92 */             if (p.isSneaking()) {
/*  93 */               if (BlockStorage.hasBlockInfo(e.getClickedBlock()))
/*  94 */                 BlockStorage.clearBlockInfo(e.getClickedBlock()); 
/*     */               break;
/*     */             } 
/*  97 */             e.setCancelled(false);
/*     */             break;
/*     */           
/*     */           case ENDER_CHEST:
/* 101 */             if (p.isSneaking()) {
/* 102 */               Block b = e.getClickedBlock().getRelative(e.getBlockFace());
/* 103 */               b.setType(Material.SKULL);
/*     */               try {
/* 105 */                 CustomSkull.setSkull(b, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTllYjlkYTI2Y2YyZDMzNDEzOTdhN2Y0OTEzYmEzZDM3ZDFhZDEwZWFlMzBhYjI1ZmEzOWNlYjg0YmMifX19");
/* 106 */               } catch (Exception e1) {
/* 107 */                 e1.printStackTrace();
/*     */               }  break;
/*     */             } 
/* 110 */             if (BlockStorage.hasBlockInfo(e.getClickedBlock())) {
/* 111 */               p.sendMessage(" ");
/* 112 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d" + e.getClickedBlock().getType() + ":" + e.getClickedBlock().getData() + " &e@ X: " + e.getClickedBlock().getX() + " Y: " + e.getClickedBlock().getY() + " Z: " + e.getClickedBlock().getZ()));
/* 113 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dID: &e" + BlockStorage.checkID(e.getClickedBlock())));
/* 114 */               if (e.getClickedBlock().getState() instanceof Skull) {
/* 115 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dSkull: &2✔"));
/* 116 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dRotation: &e" + ((Skull)e.getClickedBlock().getState()).getRotation().toString()));
/*     */               } 
/* 118 */               if (BlockStorage.getStorage(e.getClickedBlock().getWorld()).hasInventory(e.getClickedBlock().getLocation())) {
/* 119 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dInventory: &2✔"));
/*     */               } else {
/*     */                 
/* 122 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dInventory: &4✘"));
/*     */               } 
/* 124 */               if (BlockStorage.check(e.getClickedBlock()).isTicking()) {
/* 125 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTicking: &2✔"));
/* 126 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dAsync: &e" + (BlockStorage.check(e.getClickedBlock()).getTicker().isSynchronized() ? "&4✘" : "&2✔")));
/* 127 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dTimings: &e" + SlimefunStartup.ticker.getTimings(e.getClickedBlock()) + "ms"));
/* 128 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dTotal Timings: &e" + SlimefunStartup.ticker.getTimings(BlockStorage.checkID(e.getClickedBlock())) + "ms"));
/* 129 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dChunk Timings: &e" + SlimefunStartup.ticker.getTimings(e.getClickedBlock().getChunk()) + "ms"));
/*     */               }
/* 131 */               else if (BlockStorage.check(e.getClickedBlock()).getEnergyTicker() != null) {
/* 132 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTicking: &b~ &3(Indirect)"));
/* 133 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dTimings: &e" + SlimefunStartup.ticker.getTimings(e.getClickedBlock()) + "ms"));
/* 134 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dChunk Timings: &e" + SlimefunStartup.ticker.getTimings(e.getClickedBlock().getChunk()) + "ms"));
/*     */               } else {
/*     */                 
/* 137 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTicking: &4✘"));
/* 138 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTicking: &4✘"));
/*     */               } 
/* 140 */               if (ChargableBlock.isChargable(e.getClickedBlock())) {
/* 141 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dChargable: &2✔"));
/* 142 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &dEnergy: &e" + ChargableBlock.getCharge(e.getClickedBlock()) + " / " + ChargableBlock.getMaxCharge(e.getClickedBlock())));
/*     */               } else {
/*     */                 
/* 145 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dChargable: &4✘"));
/*     */               } 
/* 147 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + BlockStorage.getBlockInfoAsJson(e.getClickedBlock())));
/* 148 */               p.sendMessage(" ");
/*     */             } 
/*     */             break;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.NORMAL)
/*     */   public void onRightClick(ItemUseEvent e) {
/* 163 */     if (e.getParentEvent() != null && !e.getParentEvent().getHand().equals(EquipmentSlot.HAND)) {
/*     */       return;
/*     */     }
/* 166 */     Player p = e.getPlayer();
/* 167 */     ItemStack item = e.getItem();
/* 168 */     if (SlimefunManager.isItemSimiliar(item, SlimefunGuide.getItem(BookDesign.BOOK), true)) {
/*     */       
/* 170 */       if (checkMenuClick(e)) {
/* 171 */         e.setCancelled(true);
/*     */         return;
/*     */       } 
/* 174 */       if (p.isSneaking()) { SlimefunGuide.openSettings(p, item); }
/* 175 */       else { SlimefunGuide.openGuide(p, true); }
/*     */     
/* 177 */     } else if (SlimefunManager.isItemSimiliar(item, SlimefunGuide.getItem(BookDesign.CHEST), true)) {
/* 178 */       if (checkMenuClick(e)) {
/* 179 */         e.setCancelled(true);
/*     */         return;
/*     */       } 
/* 182 */       if (p.isSneaking()) { SlimefunGuide.openSettings(p, item); }
/* 183 */       else { SlimefunGuide.openGuide(p, false); }
/*     */     
/* 185 */     } else if (SlimefunManager.isItemSimiliar(item, SlimefunGuide.getItem(BookDesign.CHEAT_SHEET), true)) {
/* 186 */       if (checkMenuClick(e)) {
/* 187 */         e.setCancelled(true);
/*     */         return;
/*     */       } 
/* 190 */       if (p.isSneaking()) { SlimefunGuide.openSettings(p, item); }
/* 191 */       else { p.chat("/sf cheat"); }
/*     */     
/* 193 */     } else if (SlimefunManager.isItemSimiliar(item, SlimefunGuide.getDeprecatedItem(true), true)) {
/* 194 */       item = SlimefunGuide.getItem(true);
/* 195 */       p.getInventory().setItemInMainHand(item);
/* 196 */       PlayerInventory.update(p);
/*     */       
/* 198 */       if (p.isSneaking()) { SlimefunGuide.openSettings(p, item); }
/* 199 */       else { SlimefunGuide.openGuide(p, true); }
/*     */     
/* 201 */     } else if (SlimefunManager.isItemSimiliar(item, SlimefunGuide.getDeprecatedItem(false), true)) {
/* 202 */       item = SlimefunGuide.getItem(false);
/* 203 */       p.getInventory().setItemInMainHand(item);
/* 204 */       PlayerInventory.update(p);
/*     */       
/* 206 */       if (p.isSneaking()) { SlimefunGuide.openSettings(p, item); }
/* 207 */       else { SlimefunGuide.openGuide(p, false); }
/*     */     
/* 209 */     } else if (!SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInMainHand(), SlimefunItems.DEBUG_FISH, true) && !SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInOffHand(), SlimefunItems.DEBUG_FISH, true)) {
/*     */       
/* 211 */       if (Slimefun.hasUnlocked(p, item, true)) {
/* 212 */         for (ItemHandler handler : SlimefunItem.getHandlers("ItemInteractionHandler")) {
/* 213 */           if (((ItemInteractionHandler)handler).onRightClick(e, p, item))
/*     */             return; 
/* 215 */         }  if (SlimefunManager.isItemSimiliar(item, SlimefunItems.DURALUMIN_MULTI_TOOL, false) || SlimefunManager.isItemSimiliar(item, SlimefunItems.SOLDER_MULTI_TOOL, false) || SlimefunManager.isItemSimiliar(item, SlimefunItems.BILLON_MULTI_TOOL, false) || SlimefunManager.isItemSimiliar(item, SlimefunItems.STEEL_MULTI_TOOL, false) || SlimefunManager.isItemSimiliar(item, SlimefunItems.DAMASCUS_STEEL_MULTI_TOOL, false) || SlimefunManager.isItemSimiliar(item, SlimefunItems.REINFORCED_ALLOY_MULTI_TOOL, false) || SlimefunManager.isItemSimiliar(item, SlimefunItems.CARBONADO_MULTI_TOOL, false))
/* 216 */         { e.setCancelled(true);
/* 217 */           ItemStack tool = null;
/* 218 */           for (ItemStack mTool : new ItemStack[] { SlimefunItems.DURALUMIN_MULTI_TOOL, SlimefunItems.SOLDER_MULTI_TOOL, SlimefunItems.BILLON_MULTI_TOOL, SlimefunItems.STEEL_MULTI_TOOL, SlimefunItems.DAMASCUS_STEEL_MULTI_TOOL, SlimefunItems.REINFORCED_ALLOY_MULTI_TOOL, SlimefunItems.CARBONADO_MULTI_TOOL }) {
/* 219 */             if (((String)mTool.getItemMeta().getLore().get(0)).equalsIgnoreCase(item.getItemMeta().getLore().get(0))) {
/* 220 */               tool = mTool;
/*     */               break;
/*     */             } 
/*     */           } 
/* 224 */           if (tool != null) {
/* 225 */             List<Integer> modes = ((MultiTool)SlimefunItem.getByItem(tool)).getModes();
/* 226 */             int index = 0;
/* 227 */             if (Variables.mode.containsKey(p.getUniqueId())) index = ((Integer)Variables.mode.get(p.getUniqueId())).intValue();
/*     */             
/* 229 */             if (!p.isSneaking()) {
/* 230 */               float charge = ItemEnergy.getStoredEnergy(item);
/* 231 */               float cost = 0.3F;
/* 232 */               if (charge >= cost) {
/* 233 */                 p.setItemInHand(ItemEnergy.chargeItem(item, -cost));
/* 234 */                 Bukkit.getPluginManager().callEvent((Event)new ItemUseEvent(e.getParentEvent(), SlimefunItem.getByID((String)Slimefun.getItemValue(SlimefunItem.getByItem(tool).getID(), "mode." + modes.get(index) + ".item")).getItem(), e.getClickedBlock()));
/*     */               } 
/*     */             } else {
/*     */               
/* 238 */               index++;
/* 239 */               if (index == modes.size()) index = 0; 
/* 240 */               Messages.local.sendTranslation((CommandSender)p, "messages.mode-change", true, new Variable[] { new Variable("%device%", "Multi Tool"), new Variable("%mode%", (String)Slimefun.getItemValue(SlimefunItem.getByItem(tool).getName(), "mode." + modes.get(index) + ".name")) });
/* 241 */               Variables.mode.put(p.getUniqueId(), Integer.valueOf(index));
/*     */             }
/*     */           
/*     */           }  }
/* 245 */         else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.HEAVY_CREAM, true)) { e.setCancelled(true); }
/*     */         
/* 247 */         if (e.getClickedBlock() != null && BlockStorage.hasBlockInfo(e.getClickedBlock())) {
/* 248 */           String id = BlockStorage.checkID(e.getClickedBlock());
/* 249 */           if (BlockMenuPreset.isInventory(id) && (
/* 250 */             !canPlaceBlock(p, e.getClickedBlock().getRelative(e.getParentEvent().getBlockFace())) || !SlimefunManager.isItemSimiliar(item, SlimefunItems.CARGO_INPUT, true)) && (
/* 251 */             !canPlaceBlock(p, e.getClickedBlock().getRelative(e.getParentEvent().getBlockFace())) || !SlimefunManager.isItemSimiliar(item, SlimefunItems.CARGO_OUTPUT, true)) && (
/* 252 */             !canPlaceBlock(p, e.getClickedBlock().getRelative(e.getParentEvent().getBlockFace())) || !SlimefunManager.isItemSimiliar(item, SlimefunItems.CARGO_OUTPUT_ADVANCED, true)) && (
/* 253 */             !canPlaceBlock(p, e.getClickedBlock().getRelative(e.getParentEvent().getBlockFace())) || !SlimefunManager.isItemSimiliar(item, SlimefunItems.CT_IMPORT_BUS, true)) && (
/* 254 */             !canPlaceBlock(p, e.getClickedBlock().getRelative(e.getParentEvent().getBlockFace())) || !SlimefunManager.isItemSimiliar(item, SlimefunItems.CT_EXPORT_BUS, true))) {
/*     */             
/* 256 */             e.setCancelled(true);
/* 257 */             BlockStorage storage = BlockStorage.getStorage(e.getClickedBlock().getWorld());
/*     */             
/* 259 */             if (storage.hasUniversalInventory(id)) {
/* 260 */               UniversalBlockMenu menu = storage.getUniversalInventory(id);
/* 261 */               if (menu.canOpen(e.getClickedBlock(), p)) menu.open(new Player[] { p });
/*     */             
/* 263 */             } else if (storage.hasInventory(e.getClickedBlock().getLocation())) {
/* 264 */               BlockMenu menu = BlockStorage.getInventory(e.getClickedBlock().getLocation());
/* 265 */               if (menu.canOpen(e.getClickedBlock(), p)) menu.open(new Player[] { p });
/*     */             
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 271 */         e.setCancelled(true);
/*     */       } 
/*     */     } 
/*     */   } private boolean checkMenuClick(ItemUseEvent e) {
/* 275 */     if (e.getClickedBlock() != null) {
/* 276 */       if (e.getClickedBlock().getType().equals(Material.BED_BLOCK)) {
/* 277 */         return true;
/*     */       }
/* 279 */       if (isContainer(e.getClickedBlock())) {
/* 280 */         return true;
/*     */       }
/*     */     } 
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   private boolean canPlaceBlock(Player p, Block relative) {
/* 287 */     return (p.isSneaking() && relative.getType().equals(Material.AIR));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEat(PlayerItemConsumeEvent e) {
/* 292 */     if (e.getItem() != null) {
/* 293 */       final Player p = e.getPlayer();
/* 294 */       ItemStack item = e.getItem();
/* 295 */       if (Slimefun.hasUnlocked(p, item, true)) {
/* 296 */         if (SlimefunManager.isItemSimiliar(item, SlimefunItems.MONSTER_JERKY, true))
/* 297 */         { e.setCancelled(true);
/* 298 */           if (SlimefunManager.isItemSimiliar(p.getInventory().getItemInOffHand(), SlimefunItems.MONSTER_JERKY, true)) {
/* 299 */             p.getInventory().setItemInOffHand(InvUtils.decreaseItem(p.getInventory().getItemInOffHand(), 1));
/*     */           } else {
/*     */             
/* 302 */             p.getInventory().setItemInMainHand(InvUtils.decreaseItem(p.getInventory().getItemInMainHand(), 1));
/*     */           } 
/* 304 */           PlayerInventory.update(p);
/* 305 */           p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 0)); }
/*     */         
/* 307 */         else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.FORTUNE_COOKIE, true)) { p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.local.getTranslation("messages.fortune-cookie").get(CSCoreLib.randomizer().nextInt(Messages.local.getTranslation("messages.fortune-cookie").size())))); }
/* 308 */         else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BEEF_JERKY, true)) { p.setSaturation(((Integer)Slimefun.getItemValue("BEEF_JERKY", "Saturation")).intValue()); }
/* 309 */         else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.MEDICINE, true))
/* 310 */         { if (p.hasPotionEffect(PotionEffectType.POISON)) p.removePotionEffect(PotionEffectType.POISON); 
/* 311 */           if (p.hasPotionEffect(PotionEffectType.WITHER)) p.removePotionEffect(PotionEffectType.WITHER); 
/* 312 */           if (p.hasPotionEffect(PotionEffectType.SLOW)) p.removePotionEffect(PotionEffectType.SLOW); 
/* 313 */           if (p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) p.removePotionEffect(PotionEffectType.SLOW_DIGGING); 
/* 314 */           if (p.hasPotionEffect(PotionEffectType.WEAKNESS)) p.removePotionEffect(PotionEffectType.WEAKNESS); 
/* 315 */           if (p.hasPotionEffect(PotionEffectType.CONFUSION)) p.removePotionEffect(PotionEffectType.CONFUSION); 
/* 316 */           if (p.hasPotionEffect(PotionEffectType.BLINDNESS)) p.removePotionEffect(PotionEffectType.BLINDNESS); 
/* 317 */           p.setFireTicks(0); }
/*     */         
/* 319 */         else if (item.getType() == Material.POTION)
/* 320 */         { SlimefunItem sfItem = SlimefunItem.getByItem(item);
/* 321 */           if (sfItem != null && sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Juice) {
/*     */             
/* 323 */             if (!ReflectionUtils.getVersion().startsWith("v1_9_") && !ReflectionUtils.getVersion().startsWith("v1_10_")) {
/* 324 */               for (PotionEffect effect : ((PotionMeta)item.getItemMeta()).getCustomEffects()) {
/* 325 */                 if (effect.getType().equals(PotionEffectType.SATURATION)) {
/* 326 */                   p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, effect.getDuration(), effect.getAmplifier()));
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             }
/*     */             
/* 333 */             int mode = 0;
/* 334 */             if (SlimefunManager.isItemSimiliar(item, p.getInventory().getItemInMainHand(), true)) {
/* 335 */               if (p.getInventory().getItemInMainHand().getAmount() == 1) {
/* 336 */                 mode = 0;
/*     */               } else {
/*     */                 
/* 339 */                 mode = 2;
/*     */               }
/*     */             
/* 342 */             } else if (SlimefunManager.isItemSimiliar(item, p.getInventory().getItemInOffHand(), true)) {
/* 343 */               if (p.getInventory().getItemInOffHand().getAmount() == 1) {
/* 344 */                 mode = 1;
/*     */               } else {
/*     */                 
/* 347 */                 mode = 2;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 352 */             final int m = mode;
/*     */             
/* 354 */             Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */                 {
/*     */                   public void run()
/*     */                   {
/* 358 */                     if (m == 0) {
/* 359 */                       p.getInventory().setItemInMainHand(null);
/*     */                     }
/* 361 */                     else if (m == 1) {
/* 362 */                       p.getInventory().setItemInOffHand(null);
/*     */                     }
/* 364 */                     else if (m == 2) {
/* 365 */                       p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GLASS_BOTTLE, 1) });
/*     */                     } 
/*     */                   }
/*     */                 }1L);
/*     */           }  }
/*     */       
/*     */       } else {
/*     */         
/* 373 */         e.setCancelled(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @EventHandler
/*     */   public void onCraft(CraftItemEvent e) {
/* 379 */     for (ItemStack item : e.getInventory().getContents()) {
/* 380 */       if (SlimefunItem.getByItem(item) != null && !SlimefunItem.getByItem(item).isReplacing()) {
/* 381 */         e.setCancelled(true);
/* 382 */         Messages.local.sendTranslation((CommandSender)e.getWhoClicked(), "workbench.not-enhanced", true, new Variable[0]);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void onEntityChangeBlock(EntityChangeBlockEvent e) {
/* 390 */     if (e.getEntity() instanceof org.bukkit.entity.FallingBlock) {
/* 391 */       if (Variables.blocks.contains(e.getEntity().getUniqueId())) {
/* 392 */         e.setCancelled(true);
/* 393 */         e.getEntity().remove();
/*     */       }
/*     */     
/* 396 */     } else if (e.getEntity() instanceof org.bukkit.entity.Wither) {
/* 397 */       SlimefunItem item = BlockStorage.check(e.getBlock());
/* 398 */       if (item != null) {
/* 399 */         if (item.getID().equals("WITHER_PROOF_OBSIDIAN")) e.setCancelled(true); 
/* 400 */         if (item.getID().equals("WITHER_PROOF_GLASS")) e.setCancelled(true); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onAnvil(InventoryClickEvent e) {
/* 407 */     if (e.getRawSlot() == 2 && e.getWhoClicked() instanceof Player && e.getInventory().getType() == InventoryType.ANVIL) {
/* 408 */       if (SlimefunManager.isItemSimiliar(e.getInventory().getContents()[0], SlimefunItems.ELYTRA, true))
/*     */         return; 
/* 410 */       if (SlimefunItem.getByItem(e.getInventory().getContents()[0]) != null && !SlimefunItem.isDisabled(e.getInventory().getContents()[0])) {
/* 411 */         e.setCancelled(true);
/* 412 */         Messages.local.sendTranslation((CommandSender)e.getWhoClicked(), "anvil.not-working", true, new Variable[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void onPreBrew(InventoryClickEvent e) {
/* 419 */     Inventory inventory = e.getInventory();
/* 420 */     if (inventory instanceof org.bukkit.inventory.BrewerInventory && inventory.getHolder() instanceof org.bukkit.block.BrewingStand && 
/* 421 */       e.getRawSlot() < inventory.getSize()) e.setCancelled((SlimefunItem.getByItem(e.getCursor()) != null));
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isContainer(Block block) {
/* 427 */     if (block.getType().toString().endsWith("SHULKER_BOX")) {
/* 428 */       return true;
/*     */     }
/* 430 */     switch (block.getType()) {
/*     */       case CHEST:
/*     */       case ENDER_CHEST:
/*     */       case DISPENSER:
/*     */       case HOPPER:
/*     */       case TRAPPED_CHEST:
/*     */       case BREWING_STAND:
/*     */       case FURNACE:
/*     */       case BURNING_FURNACE:
/*     */       case WORKBENCH:
/*     */       case ENCHANTMENT_TABLE:
/*     */       case WALL_SIGN:
/*     */       case SIGN:
/*     */       case SIGN_POST:
/* 444 */         return true;
/*     */     } 
/* 446 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\ItemListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */