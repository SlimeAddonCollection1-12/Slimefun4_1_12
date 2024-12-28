/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.Variables;
/*     */ import me.mrCookieSlime.Slimefun.api.Backpacks;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ public class BackpackListener
/*     */   implements Listener
/*     */ {
/*     */   public BackpackListener(SlimefunStartup plugin) {
/*  34 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onClose(InventoryCloseEvent e) {
/*  39 */     if (Variables.enchanting.containsKey(e.getPlayer().getUniqueId())) Variables.enchanting.remove(e.getPlayer().getUniqueId()); 
/*  40 */     if (Variables.backpack.containsKey(e.getPlayer().getUniqueId())) {
/*  41 */       ((Player)e.getPlayer()).playSound(e.getPlayer().getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/*  42 */       Backpacks.saveBackpack(e.getInventory(), (ItemStack)Variables.backpack.get(e.getPlayer().getUniqueId()));
/*  43 */       Variables.backpack.remove(e.getPlayer().getUniqueId());
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onItemDrop(PlayerDropItemEvent e) {
/*  49 */     if (Variables.backpack.containsKey(e.getPlayer().getUniqueId())) {
/*  50 */       ItemStack item = e.getItemDrop().getItemStack();
/*  51 */       SlimefunItem sfItem = SlimefunItem.getByItem(item);
/*  52 */       if (sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBackpack) e.setCancelled(true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onClick(InventoryClickEvent e) {
/*  58 */     if (Variables.backpack.containsKey(e.getWhoClicked().getUniqueId())) {
/*  59 */       ItemStack item = (ItemStack)Variables.backpack.get(e.getWhoClicked().getUniqueId());
/*  60 */       if (e.getClick() == ClickType.NUMBER_KEY) {
/*  61 */         ItemStack hotbarItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
/*  62 */         SlimefunItem sfItem = SlimefunItem.getByItem(hotbarItem);
/*  63 */         if (hotbarItem != null && hotbarItem.getType().toString().contains("SHULKER_BOX")) { e.setCancelled(true); }
/*  64 */         else if (sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBackpack) { e.setCancelled(true); }
/*     */       
/*     */       } else {
/*  67 */         SlimefunItem sfItem = SlimefunItem.getByItem(e.getCurrentItem());
/*  68 */         if (SlimefunManager.isItemSimiliar(item, SlimefunItem.getItem("COOLER"), false))
/*  69 */         { if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR) && 
/*  70 */             !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Juice)) e.setCancelled(true);
/*     */            }
/*  72 */         else if (e.getCurrentItem() != null && e.getCurrentItem().getType().toString().contains("SHULKER_BOX")) { e.setCancelled(true); }
/*  73 */         else if (sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBackpack) { e.setCancelled(true); }
/*  74 */         else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_SMALL, false)) { e.setCancelled(true); }
/*  75 */         else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_MEDIUM, false)) { e.setCancelled(true); }
/*  76 */         else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_BIG, false)) { e.setCancelled(true); }
/*  77 */         else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_LARGE, false)) { e.setCancelled(true); }
/*  78 */         else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.BOUND_VOIDBAG, false)) { e.setCancelled(true); }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @EventHandler
/*     */   public void onInteract(PlayerInteractEvent e) {
/*  85 */     if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*  86 */       ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
/*  87 */       Player p = e.getPlayer();
/*  88 */       if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BACKPACK_SMALL, false)) {
/*  89 */         e.setCancelled(true);
/*  90 */         if (Slimefun.hasUnlocked(p, SlimefunItems.BACKPACK_SMALL, true)) {
/*  91 */           if (item.getAmount() == 1)
/*  92 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/*  93 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/*  94 */                 ItemMeta im = item.getItemMeta();
/*  95 */                 List<String> lore = im.getLore();
/*  96 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 9)));
/*  97 */                 im.setLore(lore);
/*  98 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 102 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 104 */               Backpacks.openBackpack(p, item);
/* 105 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 106 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 108 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 110 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */         
/*     */         }
/* 113 */       } else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BACKPACK_MEDIUM, false)) {
/* 114 */         e.setCancelled(true);
/* 115 */         if (Slimefun.hasUnlocked(p, SlimefunItems.BACKPACK_MEDIUM, true)) {
/* 116 */           if (item.getAmount() == 1)
/* 117 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/* 118 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 119 */                 ItemMeta im = item.getItemMeta();
/* 120 */                 List<String> lore = im.getLore();
/* 121 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 18)));
/* 122 */                 im.setLore(lore);
/* 123 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 127 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 129 */               Backpacks.openBackpack(p, item);
/* 130 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 131 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 133 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 135 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */         
/*     */         }
/* 138 */       } else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BACKPACK_LARGE, false)) {
/* 139 */         e.setCancelled(true);
/* 140 */         if (Slimefun.hasUnlocked(p, SlimefunItems.BACKPACK_LARGE, true)) {
/* 141 */           if (item.getAmount() == 1)
/* 142 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/* 143 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 144 */                 ItemMeta im = item.getItemMeta();
/* 145 */                 List<String> lore = im.getLore();
/* 146 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 27)));
/* 147 */                 im.setLore(lore);
/* 148 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 152 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 154 */               Backpacks.openBackpack(p, item);
/* 155 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 156 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 158 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 160 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */         
/*     */         }
/* 163 */       } else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.WOVEN_BACKPACK, false)) {
/* 164 */         e.setCancelled(true);
/* 165 */         if (Slimefun.hasUnlocked(p, SlimefunItems.WOVEN_BACKPACK, true)) {
/* 166 */           if (item.getAmount() == 1)
/* 167 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/* 168 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 169 */                 ItemMeta im = item.getItemMeta();
/* 170 */                 List<String> lore = im.getLore();
/* 171 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 36)));
/* 172 */                 im.setLore(lore);
/* 173 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 177 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 179 */               Backpacks.openBackpack(p, item);
/* 180 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 181 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 183 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 185 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */         
/*     */         }
/* 188 */       } else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.GILDED_BACKPACK, false)) {
/* 189 */         e.setCancelled(true);
/* 190 */         if (Slimefun.hasUnlocked(p, SlimefunItems.GILDED_BACKPACK, true)) {
/* 191 */           if (item.getAmount() == 1)
/* 192 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/* 193 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 194 */                 ItemMeta im = item.getItemMeta();
/* 195 */                 List<String> lore = im.getLore();
/* 196 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 45)));
/* 197 */                 im.setLore(lore);
/* 198 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 202 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 204 */               Backpacks.openBackpack(p, item);
/* 205 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 206 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 208 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 210 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */         
/*     */         }
/* 213 */       } else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BOUND_BACKPACK, false)) {
/* 214 */         e.setCancelled(true);
/* 215 */         if (Slimefun.hasUnlocked(p, SlimefunItems.BOUND_BACKPACK, true)) {
/* 216 */           if (item.getAmount() == 1)
/* 217 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/* 218 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 219 */                 ItemMeta im = item.getItemMeta();
/* 220 */                 List<String> lore = im.getLore();
/* 221 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 36)));
/* 222 */                 im.setLore(lore);
/* 223 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 227 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 229 */               Backpacks.openBackpack(p, item);
/* 230 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 231 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 233 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 235 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */         
/*     */         }
/* 238 */       } else if (SlimefunManager.isItemSimiliar(item, SlimefunItems.COOLER, false)) {
/* 239 */         e.setCancelled(true);
/* 240 */         if (Slimefun.hasUnlocked(p, SlimefunItems.COOLER, true))
/* 241 */           if (item.getAmount() == 1)
/* 242 */           { for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
/* 243 */               if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 244 */                 ItemMeta im = item.getItemMeta();
/* 245 */                 List<String> lore = im.getLore();
/* 246 */                 lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 27)));
/* 247 */                 im.setLore(lore);
/* 248 */                 item.setItemMeta(im);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 252 */             if (!Variables.backpack.containsValue(item)) {
/*     */               
/* 254 */               Backpacks.openBackpack(p, item);
/* 255 */               p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
/* 256 */               Variables.backpack.put(p.getUniqueId(), item);
/*     */             } else {
/* 258 */               Messages.local.sendTranslation((CommandSender)p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */             }  }
/* 260 */           else { Messages.local.sendTranslation((CommandSender)p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*     */            
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\BackpackListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */