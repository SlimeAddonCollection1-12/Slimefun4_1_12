/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.EntityEvent;
/*     */ import org.bukkit.event.player.PlayerEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Talisman
/*     */   extends SlimefunItem
/*     */ {
/*     */   private String suffix;
/*     */   private boolean consumable = true;
/*     */   private boolean cancel = true;
/*  34 */   private PotionEffect[] effects = new PotionEffect[0];
/*  35 */   private int chance = 100;
/*     */   
/*     */   public Talisman(ItemStack item, String id, ItemStack[] recipe, boolean consumable, boolean cancelEvent, String messageSuffix, PotionEffect... effects) {
/*  38 */     super(Categories.TALISMANS_1, item, id, RecipeType.MAGIC_WORKBENCH, recipe, (ItemStack)new CustomItem(item, consumable ? 4 : 1));
/*  39 */     this.consumable = consumable;
/*  40 */     this.cancel = cancelEvent;
/*  41 */     this.suffix = messageSuffix;
/*  42 */     this.effects = effects;
/*     */   }
/*     */   
/*     */   public Talisman(ItemStack item, String id, ItemStack[] recipe, boolean consumable, boolean cancelEvent, String messageSuffix, int chance, PotionEffect... effects) {
/*  46 */     super(Categories.TALISMANS_1, item, id, RecipeType.MAGIC_WORKBENCH, recipe, (ItemStack)new CustomItem(item, consumable ? 4 : 1));
/*  47 */     this.consumable = consumable;
/*  48 */     this.cancel = cancelEvent;
/*  49 */     this.suffix = messageSuffix;
/*  50 */     this.effects = effects;
/*  51 */     this.chance = chance;
/*     */   }
/*     */   
/*     */   public Talisman(ItemStack item, String id, ItemStack[] recipe, String messageSuffix, int chance, PotionEffect... effects) {
/*  55 */     super(Categories.TALISMANS_1, item, id, RecipeType.MAGIC_WORKBENCH, recipe, item);
/*  56 */     this.suffix = messageSuffix;
/*  57 */     this.effects = effects;
/*  58 */     this.chance = chance;
/*     */   }
/*     */   
/*  61 */   public String getSuffix() { return this.suffix; }
/*  62 */   public boolean isConsumable() { return this.consumable; }
/*  63 */   public boolean isEventCancelled() { return this.cancel; }
/*  64 */   public PotionEffect[] getEffects() { return this.effects; } public int getChance() {
/*  65 */     return this.chance;
/*     */   }
/*     */   public static boolean checkFor(Event e, SlimefunItem talisman) {
/*  68 */     if (talisman != null) {
/*  69 */       if (talisman instanceof Talisman) {
/*  70 */         boolean message = !((Talisman)talisman).getSuffix().equalsIgnoreCase("");
/*  71 */         if (SlimefunStartup.chance(100, ((Talisman)talisman).getChance())) {
/*  72 */           Player p = null;
/*     */           
/*  74 */           if (e instanceof EntityDeathEvent) { p = ((EntityDeathEvent)e).getEntity().getKiller(); }
/*  75 */           else if (e instanceof BlockBreakEvent) { p = ((BlockBreakEvent)e).getPlayer(); }
/*  76 */           else if (e instanceof PlayerEvent) { p = ((PlayerEvent)e).getPlayer(); }
/*  77 */           else if (e instanceof EntityEvent) { p = (Player)((EntityEvent)e).getEntity(); }
/*  78 */           else if (e instanceof EnchantItemEvent) { p = ((EnchantItemEvent)e).getEnchanter(); }
/*     */           
/*  80 */           boolean pass = true;
/*     */           
/*  82 */           for (PotionEffect effect : ((Talisman)talisman).getEffects()) {
/*  83 */             if (effect != null && p.hasPotionEffect(effect.getType())) pass = false;
/*     */           
/*     */           } 
/*  86 */           if (pass) {
/*  87 */             if (p.getInventory().containsAtLeast(talisman.getItem(), 1)) {
/*  88 */               if (Slimefun.hasUnlocked(p, talisman.getItem(), true)) {
/*  89 */                 if (((Talisman)talisman).isConsumable()) p.getInventory().removeItem(new ItemStack[] { talisman.getItem() }); 
/*  90 */                 for (PotionEffect effect : ((Talisman)talisman).getEffects()) {
/*  91 */                   p.addPotionEffect(effect);
/*     */                 }
/*     */                 
/*  94 */                 if (e instanceof Cancellable && ((Talisman)talisman).isEventCancelled()) ((Cancellable)e).setCancelled(true);
/*     */                 
/*  96 */                 if (message) Messages.local.sendTranslation((CommandSender)p, "messages.talisman." + ((Talisman)talisman).getSuffix(), true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */                 
/*  98 */                 return true;
/*     */               } 
/* 100 */               return false;
/*     */             } 
/* 102 */             if (p.getEnderChest().containsAtLeast(((Talisman)talisman).upgrade(), 1)) {
/* 103 */               if (Slimefun.hasUnlocked(p, ((Talisman)talisman).upgrade(), true)) {
/* 104 */                 if (((Talisman)talisman).isConsumable()) p.getEnderChest().removeItem(new ItemStack[] { ((Talisman)talisman).upgrade() }); 
/* 105 */                 for (PotionEffect effect : ((Talisman)talisman).getEffects()) {
/* 106 */                   p.addPotionEffect(effect);
/*     */                 }
/* 108 */                 if (e instanceof Cancellable && ((Talisman)talisman).isEventCancelled()) ((Cancellable)e).setCancelled(true);
/*     */                 
/* 110 */                 if (message) Messages.local.sendTranslation((CommandSender)p, "messages.talisman." + ((Talisman)talisman).getSuffix(), true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*     */                 
/* 112 */                 return true;
/*     */               } 
/* 114 */               return false;
/*     */             } 
/* 116 */             return false;
/*     */           } 
/* 118 */           return false;
/*     */         } 
/* 120 */         return false;
/*     */       } 
/* 122 */       return false;
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack upgrade() {
/* 128 */     List<String> lore = new ArrayList<>();
/* 129 */     lore.add("&7&oEnder Infused");
/* 130 */     lore.add("");
/* 131 */     for (String line : getItem().getItemMeta().getLore()) {
/* 132 */       lore.add(line);
/*     */     }
/* 134 */     return (ItemStack)new CustomItem(getItem().getType(), "&5Ender " + ChatColor.stripColor(getItem().getItemMeta().getDisplayName()), getItem().getDurability(), lore.<String>toArray(new String[lore.size()]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void create() {
/* 140 */     EnderTalisman talisman = new EnderTalisman(this);
/* 141 */     talisman.register(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void install() {
/* 146 */     EnderTalisman talisman = (EnderTalisman)SlimefunItem.getByItem(upgrade());
/* 147 */     Research research = Research.getByID(112);
/* 148 */     if (talisman != null) {
/* 149 */       Slimefun.addOfficialWikiPage(talisman.getID(), "Talismans");
/* 150 */       if (research != null) talisman.bindToResearch(research);
/*     */     
/*     */     } 
/* 153 */     Slimefun.addOfficialWikiPage(getID(), "Talismans");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\Talisman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */