/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.SkullItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.FireworkShow;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockBreakHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockPlaceHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockFromToEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class ToolListener
/*     */   implements Listener
/*     */ {
/*     */   public ToolListener(SlimefunStartup plugin) {
/*  43 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
/*     */   public void onBlockRegister(BlockPlaceEvent e) {
/*  48 */     if (BlockStorage.hasBlockInfo(e.getBlock())) {
/*  49 */       e.setCancelled(true);
/*     */       return;
/*     */     } 
/*  52 */     ItemStack item = e.getItemInHand();
/*  53 */     if (item != null && item.getType() == Material.INK_SACK)
/*  54 */       return;  SlimefunItem sfItem = SlimefunItem.getByItem(item);
/*  55 */     if (sfItem != null && !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Interfaces.NotPlaceable)) {
/*  56 */       BlockStorage.addBlockInfo(e.getBlock(), "id", sfItem.getID(), true);
/*  57 */       if (SlimefunItem.blockhandler.containsKey(sfItem.getID())) {
/*  58 */         ((SlimefunBlockHandler)SlimefunItem.blockhandler.get(sfItem.getID())).onPlace(e.getPlayer(), e.getBlock(), sfItem);
/*     */       }
/*     */     } else {
/*     */       
/*  62 */       for (ItemHandler handler : SlimefunItem.getHandlers("BlockPlaceHandler")) {
/*  63 */         if (((BlockPlaceHandler)handler).onBlockPlace(e, item))
/*     */           break; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent e) {
/*  70 */     ItemStack item = e.getItemInHand();
/*     */     
/*  72 */     if (Variables.cancelPlace.contains(e.getPlayer().getUniqueId())) {
/*  73 */       e.setCancelled(true);
/*  74 */       Variables.cancelPlace.remove(e.getPlayer().getUniqueId());
/*     */     } 
/*  76 */     if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BASIC_CIRCUIT_BOARD, true)) { e.setCancelled(true); }
/*  77 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.ADVANCED_CIRCUIT_BOARD, true)) { e.setCancelled(true); }
/*     */     
/*  79 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BACKPACK_SMALL, false)) { e.setCancelled(true); }
/*  80 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BACKPACK_MEDIUM, false)) { e.setCancelled(true); }
/*  81 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BACKPACK_LARGE, false)) { e.setCancelled(true); }
/*  82 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.WOVEN_BACKPACK, false)) { e.setCancelled(true); }
/*  83 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.GILDED_BACKPACK, false)) { e.setCancelled(true); }
/*  84 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BOUND_BACKPACK, false)) { e.setCancelled(true); }
/*  85 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.COOLER, false)) { e.setCancelled(true); }
/*  86 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.ENDER_BACKPACK, false)) { e.setCancelled(true); }
/*     */     
/*  88 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CARBON, false)) { e.setCancelled(true); }
/*  89 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.COMPRESSED_CARBON, false)) { e.setCancelled(true); }
/*  90 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CARBON_CHUNK, false)) { e.setCancelled(true); }
/*     */     
/*  92 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.ANDROID_MEMORY_CORE, false)) { e.setCancelled(true); }
/*  93 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.LAVA_CRYSTAL, false)) { e.setCancelled(true); }
/*     */     
/*  95 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.TINY_URANIUM, false)) { e.setCancelled(true); }
/*  96 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.SMALL_URANIUM, false)) { e.setCancelled(true); }
/*     */     
/*  98 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BROKEN_SPAWNER, false)) { e.setCancelled(true); }
/*  99 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.GPS_MARKER_TOOL, true))
/* 100 */     { e.setCancelled(true);
/* 101 */       Slimefun.getGPSNetwork().addWaypoint(e.getPlayer(), e.getBlock().getLocation()); }
/*     */     
/* 103 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CHRISTMAS_PRESENT, false))
/* 104 */     { e.setCancelled(true);
/* 105 */       PlayerInventory.consumeItemInHand(e.getPlayer());
/* 106 */       FireworkShow.launchRandom(e.getPlayer(), 3);
/* 107 */       List<ItemStack> gifts = new ArrayList<>();
/* 108 */       for (int i = 0; i < 2; i++) {
/* 109 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_HOT_CHOCOLATE, 1));
/* 110 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_CHOCOLATE_APPLE, 4));
/* 111 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_CARAMEL_APPLE, 4));
/* 112 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_CAKE, 4));
/* 113 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_COOKIE, 8));
/* 114 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_PRESENT, 1));
/* 115 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_EGG_NOG, 1));
/* 116 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_MILK, 1));
/* 117 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_APPLE_CIDER, 1));
/* 118 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_FRUIT_CAKE, 4));
/* 119 */         gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_APPLE_PIE, 4));
/*     */       } 
/* 121 */       gifts.add(new SkullItem("mrCookieSlime"));
/* 122 */       gifts.add(new SkullItem("timtower"));
/* 123 */       gifts.add(new SkullItem("bwfcwalshy"));
/* 124 */       gifts.add(new SkullItem("jadedcat"));
/* 125 */       gifts.add(new SkullItem("ZeldoKavira"));
/* 126 */       gifts.add(new SkullItem("eyamaz"));
/* 127 */       gifts.add(new SkullItem("Kaelten"));
/* 128 */       gifts.add(new SkullItem("ahamling27"));
/* 129 */       gifts.add(new SkullItem("Myrathi"));
/*     */       
/* 131 */       new String("Good day to whoever is just looking through my code.Since it is Christmas, I wanted to add some Christmas flavour to this Plugin.So, I hope you don't mind that I implemented some of your Heads >.>Merry Christmas everyone!- mrCookieSlime");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       e.getBlockPlaced().getWorld().dropItemNaturally(e.getBlockPlaced().getLocation(), gifts.get(SlimefunStartup.randomize(gifts.size()))); }
/*     */     
/* 141 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CARGO_INPUT, false))
/* 142 */     { if (e.getBlock().getY() != e.getBlockAgainst().getY()) {
/* 143 */         Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.CARGO_NODES.must-be-placed", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 144 */         e.setCancelled(true);
/*     */       }
/*     */        }
/* 147 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CARGO_OUTPUT, false))
/* 148 */     { if (e.getBlock().getY() != e.getBlockAgainst().getY()) {
/* 149 */         Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.CARGO_NODES.must-be-placed", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 150 */         e.setCancelled(true);
/*     */       }
/*     */        }
/* 153 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CARGO_OUTPUT_ADVANCED, false))
/* 154 */     { if (e.getBlock().getY() != e.getBlockAgainst().getY()) {
/* 155 */         Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.CARGO_NODES.must-be-placed", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 156 */         e.setCancelled(true);
/*     */       }
/*     */        }
/* 159 */     else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.CT_IMPORT_BUS, false) && 
/* 160 */       e.getBlock().getY() != e.getBlockAgainst().getY())
/* 161 */     { Messages.local.sendTranslation((CommandSender)e.getPlayer(), "machines.CARGO_NODES.must-be-placed", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 162 */       e.setCancelled(true); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
/*     */   public void onBlockBreak(BlockBreakEvent e) {
/* 170 */     boolean allow = true;
/* 171 */     List<ItemStack> drops = new ArrayList<>();
/* 172 */     ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
/* 173 */     int fortune = 1;
/*     */     
/* 175 */     Block block2 = e.getBlock().getRelative(BlockFace.UP);
/* 176 */     if (StringUtils.equals(block2.getType().toString(), new String[] { "SAPLING", "WOOD_PLATE", "STONE_PLATE", "IRON_PLATE", "GOLD_PLATE" })) {
/* 177 */       SlimefunItem slimefunItem = BlockStorage.check(e.getBlock().getRelative(BlockFace.UP));
/* 178 */       if (slimefunItem != null && !(slimefunItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock)) {
/* 179 */         if (SlimefunItem.blockhandler.containsKey(slimefunItem.getID())) {
/* 180 */           allow = ((SlimefunBlockHandler)SlimefunItem.blockhandler.get(slimefunItem.getID())).onBreak(e.getPlayer(), block2, slimefunItem, UnregisterReason.PLAYER_BREAK);
/*     */         }
/* 182 */         if (allow) {
/* 183 */           block2.getWorld().dropItemNaturally(block2.getLocation(), BlockStorage.retrieve(block2));
/* 184 */           block2.setType(Material.AIR);
/*     */         } else {
/*     */           
/* 187 */           e.setCancelled(true);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 193 */     SlimefunItem sfItem = BlockStorage.check(e.getBlock());
/* 194 */     if (sfItem != null && !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock)) {
/* 195 */       if (SlimefunItem.blockhandler.containsKey(sfItem.getID())) {
/* 196 */         allow = ((SlimefunBlockHandler)SlimefunItem.blockhandler.get(sfItem.getID())).onBreak(e.getPlayer(), e.getBlock(), sfItem, UnregisterReason.PLAYER_BREAK);
/*     */       }
/* 198 */       if (allow) {
/* 199 */         drops.add(BlockStorage.retrieve(e.getBlock()));
/*     */       } else {
/*     */         
/* 202 */         e.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 206 */     } else if (item != null) {
/* 207 */       if (item.getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS) && !item.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
/* 208 */         fortune = SlimefunStartup.randomize(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 1;
/* 209 */         if (fortune <= 0) fortune = 1; 
/* 210 */         fortune = ((e.getBlock().getType() == Material.LAPIS_ORE) ? (4 + SlimefunStartup.randomize(5)) : 1) * (fortune + 1);
/*     */       } 
/*     */       
/* 213 */       for (ItemHandler handler : SlimefunItem.getHandlers("BlockBreakHandler")) {
/* 214 */         if (((BlockBreakHandler)handler).onBlockBreak(e, item, fortune, drops))
/*     */           break; 
/*     */       } 
/*     */     } 
/* 218 */     if (!drops.isEmpty()) {
/* 219 */       e.getBlock().setType(Material.AIR);
/* 220 */       for (ItemStack drop : drops) {
/* 221 */         if (drop != null) {
/* 222 */           e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
/*     */   public void onEntityExplode(EntityExplodeEvent e) {
/* 230 */     Iterator<Block> blocks = e.blockList().iterator();
/* 231 */     while (blocks.hasNext()) {
/* 232 */       Block block = blocks.next();
/* 233 */       SlimefunItem item = BlockStorage.check(block);
/* 234 */       if (item != null) {
/* 235 */         blocks.remove();
/* 236 */         if (!item.getID().equalsIgnoreCase("HARDENED_GLASS") && !item.getID().equalsIgnoreCase("WITHER_PROOF_OBSIDIAN") && !item.getID().equalsIgnoreCase("WITHER_PROOF_GLASS") && !item.getID().equalsIgnoreCase("FORCEFIELD_PROJECTOR") && !item.getID().equalsIgnoreCase("FORCEFIELD_RELAY")) {
/* 237 */           boolean success = true;
/* 238 */           if (SlimefunItem.blockhandler.containsKey(item.getID())) {
/* 239 */             success = ((SlimefunBlockHandler)SlimefunItem.blockhandler.get(item.getID())).onBreak(null, block, item, UnregisterReason.EXPLODE);
/*     */           }
/* 241 */           if (success) {
/* 242 */             BlockStorage.clearBlockInfo(block);
/* 243 */             block.setType(Material.AIR);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onLiquidFlow(BlockFromToEvent e) {
/* 253 */     Block block = e.getToBlock();
/* 254 */     SlimefunItem item = BlockStorage.check(block);
/* 255 */     if (item != null) e.setCancelled(true); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\ToolListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */