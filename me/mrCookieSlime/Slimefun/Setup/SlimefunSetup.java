/*      */ package me.mrCookieSlime.Slimefun.Setup;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Block.TreeCalculator;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Block.Vein;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.SkullItem;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.FireworkShow;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.Recipe.RecipeCalculator;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
/*      */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*      */ import me.mrCookieSlime.Slimefun.Android.AndroidType;
/*      */ import me.mrCookieSlime.Slimefun.Android.ProgrammableAndroid;
/*      */ import me.mrCookieSlime.Slimefun.GPS.Elevator;
/*      */ import me.mrCookieSlime.Slimefun.GPS.GPSNetwork;
/*      */ import me.mrCookieSlime.Slimefun.GPS.NetworkStatus;
/*      */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*      */ import me.mrCookieSlime.Slimefun.Lists.NarItems;
/*      */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*      */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*      */ import me.mrCookieSlime.Slimefun.Misc.PostSlimefunLoadingHandler;
/*      */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*      */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*      */ import me.mrCookieSlime.Slimefun.Objects.MultiBlock;
/*      */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Alloy;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.EnhancedFurnace;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ExcludedSoulboundTool;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.JetBoots;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Jetpack;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Juice;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.MultiTool;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ReplacingAlloy;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ReplacingItem;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunArmorPiece;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBackpack;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBow;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunGadget;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SolarHelmet;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SoulboundBackpack;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SoulboundItem;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Talisman;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.VanillaItem;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AReactor;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.Teleporter;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.AutonomousMachineHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockBreakHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockPlaceHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BowShootHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemInteractionHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.MultiBlockInteractionHandler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AdvancedCargoOutputNode;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AnimalGrowthAccelerator;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AutoAnvil;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AutoBreeder;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AutoDisenchanter;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AutoEnchanter;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AutomatedCraftingChamber;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.CarbonPress;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.CargoInputNode;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.CargoOutputNode;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ChargingBench;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.CropGrowthAccelerator;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectricDustWasher;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectricFurnace;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectricGoldPan;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectricIngotFactory;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectricSmeltery;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ElectrifiedCrucible;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.FluidPump;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.FoodComposter;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.FoodFabricator;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.Freezer;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.HeatedPressureChamber;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.NetherDrill;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.OilPump;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.ReactorAccessPort;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.Refinery;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.TrashCan;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.WitherAssembler;
/*      */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.XPCollector;
/*      */ import me.mrCookieSlime.Slimefun.Objects.tasks.RainbowTicker;
/*      */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*      */ import me.mrCookieSlime.Slimefun.Variables;
/*      */ import me.mrCookieSlime.Slimefun.api.Backpacks;
/*      */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*      */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*      */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*      */ import me.mrCookieSlime.Slimefun.api.energy.EnergyNet;
/*      */ import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
/*      */ import me.mrCookieSlime.Slimefun.api.item_transport.CargoNet;
/*      */ import me.mrCookieSlime.Slimefun.holograms.CargoHologram;
/*      */ import me.mrCookieSlime.Slimefun.holograms.EnergyHologram;
/*      */ import me.mrCookieSlime.Slimefun.holograms.InfusedHopper;
/*      */ import me.mrCookieSlime.Slimefun.holograms.Projector;
/*      */ import me.mrCookieSlime.Slimefun.holograms.ReactorHologram;
/*      */ import me.mrCookieSlime.Slimefun.listeners.AncientAltarListener;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.ChatColor;
/*      */ import org.bukkit.Effect;
/*      */ import org.bukkit.GameMode;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.Sound;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.block.Chest;
/*      */ import org.bukkit.block.CreatureSpawner;
/*      */ import org.bukkit.block.Dispenser;
/*      */ import org.bukkit.block.Hopper;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.enchantments.Enchantment;
/*      */ import org.bukkit.entity.ArmorStand;
/*      */ import org.bukkit.entity.Arrow;
/*      */ import org.bukkit.entity.Bat;
/*      */ import org.bukkit.entity.EnderPearl;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.FallingBlock;
/*      */ import org.bukkit.entity.HumanEntity;
/*      */ import org.bukkit.entity.Item;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.entity.Projectile;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.block.BlockBreakEvent;
/*      */ import org.bukkit.event.block.BlockDispenseEvent;
/*      */ import org.bukkit.event.block.BlockPlaceEvent;
/*      */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*      */ import org.bukkit.event.entity.EntityDamageEvent;
/*      */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*      */ import org.bukkit.inventory.Inventory;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.bukkit.material.MaterialData;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.potion.PotionEffect;
/*      */ import org.bukkit.potion.PotionEffectType;
/*      */ import org.bukkit.projectiles.ProjectileSource;
/*      */ import org.bukkit.scheduler.BukkitRunnable;
/*      */ import org.bukkit.util.Vector;
/*      */ 
/*      */ public class SlimefunSetup {
/*      */   public static void setupItems() throws Exception {
/*  165 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.GRANDMAS_WALKING_STICK, "GRANDMAS_WALKING_STICK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.LOG), null, null, new ItemStack(Material.LOG), null, null, new ItemStack(Material.LOG), null
/*      */         
/*  167 */         })).register(true);
/*      */     
/*  169 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.GRANDMAS_WALKING_STICK, "GRANDMAS_WALKING_STICK2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.LOG_2), null, null, new ItemStack(Material.LOG_2), null, null, new ItemStack(Material.LOG_2), null }, true))
/*      */       
/*  171 */       .register(true);
/*      */     
/*  173 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.GRANDPAS_WALKING_STICK, "GRANDPAS_WALKING_STICK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.LEATHER), new ItemStack(Material.LOG), new ItemStack(Material.LEATHER), null, new ItemStack(Material.LOG), null, null, new ItemStack(Material.LOG), null
/*      */         
/*  175 */         })).register(true);
/*      */     
/*  177 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.GRANDPAS_WALKING_STICK, "GRANDPAS_WALKING_STICK2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.LEATHER), new ItemStack(Material.LOG_2), new ItemStack(Material.LEATHER), null, new ItemStack(Material.LOG_2), null, null, new ItemStack(Material.LOG_2), null }, true))
/*      */       
/*  179 */       .register(true);
/*      */     
/*  181 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.PORTABLE_CRAFTER, "PORTABLE_CRAFTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.BOOK), new ItemStack(Material.WORKBENCH), null, null, null, null, null, null, null
/*      */         
/*  183 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/*  187 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.PORTABLE_CRAFTER, true)) {
/*  188 */                 p.openWorkbench(p.getLocation(), true);
/*  189 */                 p.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.0F);
/*  190 */                 return true;
/*      */               } 
/*  192 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  196 */     (new SlimefunItem(Categories.FOOD, SlimefunItems.FORTUNE_COOKIE, "FORTUNE_COOKIE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.COOKIE), new ItemStack(Material.PAPER), null, null, null, null, null, null, null
/*      */         
/*  198 */         })).register(true);
/*      */     
/*  200 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.ENHANCED_CRAFTING_TABLE, "ENHANCED_CRAFTING_TABLE", new ItemStack[] { null, null, null, null, new ItemStack(Material.WORKBENCH), null, null, new ItemStack(Material.DISPENSER), null }, new ItemStack[0], Material.WORKBENCH))
/*      */ 
/*      */       
/*  203 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/*  208 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("ENHANCED_CRAFTING_TABLE");
/*      */               
/*  210 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  211 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  212 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  213 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/*      */                   
/*  215 */                   Inventory inv = disp.getInventory();
/*  216 */                   List<ItemStack[]> inputs = RecipeType.getRecipeInputList((SlimefunItem)machine);
/*      */                   
/*  218 */                   for (int i = 0; i < inputs.size(); i++) {
/*  219 */                     boolean craft = true;
/*  220 */                     for (int j = 0; j < (inv.getContents()).length; j++) {
/*  221 */                       if (!SlimefunManager.isItemSimiliar(inv.getContents()[j], ((ItemStack[])inputs.get(i))[j], true)) {
/*  222 */                         if (SlimefunItem.getByItem(((ItemStack[])inputs.get(i))[j]) instanceof SlimefunBackpack) {
/*  223 */                           if (!SlimefunManager.isItemSimiliar(inv.getContents()[j], ((ItemStack[])inputs.get(i))[j], false)) {
/*  224 */                             craft = false;
/*      */                             
/*      */                             break;
/*      */                           } 
/*      */                         } else {
/*  229 */                           craft = false;
/*      */                           
/*      */                           break;
/*      */                         } 
/*      */                       }
/*      */                     } 
/*  235 */                     if (craft) {
/*  236 */                       ItemStack adding = RecipeType.getRecipeOutputList((SlimefunItem)machine, inputs.get(i)).clone();
/*  237 */                       if (Slimefun.hasUnlocked(p, adding, true)) {
/*  238 */                         Inventory inv2 = Bukkit.createInventory(null, 9, "test");
/*  239 */                         for (int k = 0; k < (inv.getContents()).length; k++) {
/*  240 */                           inv2.setItem(k, (inv.getContents()[k] != null) ? ((inv.getContents()[k].getAmount() > 1) ? (ItemStack)new CustomItem(inv.getContents()[k], inv.getContents()[k].getAmount() - 1) : null) : null);
/*      */                         }
/*  242 */                         if (InvUtils.fits(inv2, adding)) {
/*  243 */                           SlimefunItem sfItem = SlimefunItem.getByItem(adding);
/*      */                           
/*  245 */                           if (sfItem instanceof SlimefunBackpack) {
/*  246 */                             ItemStack backpack = null;
/*      */                             
/*  248 */                             for (int n = 0; n < 9; n++) {
/*  249 */                               if (inv.getContents()[n] != null && 
/*  250 */                                 inv.getContents()[n].getType() != Material.AIR && 
/*  251 */                                 SlimefunItem.getByItem(inv.getContents()[n]) instanceof SlimefunBackpack) {
/*  252 */                                 backpack = inv.getContents()[n];
/*      */                                 
/*      */                                 break;
/*      */                               } 
/*      */                             } 
/*      */                             
/*  258 */                             String id = "";
/*  259 */                             int size = ((SlimefunBackpack)sfItem).size;
/*      */                             
/*  261 */                             if (backpack != null) {
/*  262 */                               for (String line : backpack.getItemMeta().getLore()) {
/*  263 */                                 if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
/*  264 */                                   id = line.replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
/*  265 */                                   Config cfg = new Config(new File("data-storage/Slimefun/Players/" + id.split("#")[0] + ".yml"));
/*  266 */                                   cfg.setValue("backpacks." + id.split("#")[1] + ".size", Integer.valueOf(size));
/*  267 */                                   cfg.save();
/*      */                                   
/*      */                                   break;
/*      */                                 } 
/*      */                               } 
/*      */                             }
/*  273 */                             if (id.equals("")) {
/*  274 */                               for (int line = 0; line < adding.getItemMeta().getLore().size(); line++) {
/*  275 */                                 if (((String)adding.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/*  276 */                                   ItemMeta im = adding.getItemMeta();
/*  277 */                                   List<String> lore = im.getLore();
/*  278 */                                   lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, size)));
/*  279 */                                   im.setLore(lore);
/*  280 */                                   adding.setItemMeta(im);
/*      */                                   
/*      */                                   break;
/*      */                                 } 
/*      */                               } 
/*      */                             } else {
/*  286 */                               for (int line = 0; line < adding.getItemMeta().getLore().size(); line++) {
/*  287 */                                 if (((String)adding.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/*  288 */                                   ItemMeta im = adding.getItemMeta();
/*  289 */                                   List<String> lore = im.getLore();
/*  290 */                                   lore.set(line, ((String)lore.get(line)).replace("<ID>", id));
/*  291 */                                   im.setLore(lore);
/*  292 */                                   adding.setItemMeta(im);
/*      */                                   
/*      */                                   break;
/*      */                                 } 
/*      */                               } 
/*      */                             } 
/*      */                           } 
/*      */                           
/*  300 */                           for (int m = 0; m < 9; m++) {
/*  301 */                             if (inv.getContents()[m] != null && 
/*  302 */                               inv.getContents()[m].getType() != Material.AIR) {
/*  303 */                               if (inv.getContents()[m].getType().toString().endsWith("_BUCKET")) { inv.setItem(m, new ItemStack(Material.BUCKET)); }
/*  304 */                               else if (inv.getContents()[m].getAmount() > 1) { inv.setItem(m, (ItemStack)new CustomItem(inv.getContents()[m], inv.getContents()[m].getAmount() - 1)); }
/*  305 */                               else { inv.setItem(m, null); }
/*      */                             
/*      */                             }
/*      */                           } 
/*  309 */                           p.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.0F);
/*      */                           
/*  311 */                           inv.addItem(new ItemStack[] { adding });
/*      */                         } else {
/*  313 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                         } 
/*  315 */                       }  return true;
/*      */                     } 
/*      */                   } 
/*  318 */                   Messages.local.sendTranslation((CommandSender)p, "machines.pattern-not-found", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  321 */                 return true;
/*      */               } 
/*  323 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  327 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.PORTABLE_DUSTBIN, "PORTABLE_DUSTBIN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)
/*      */         
/*  329 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/*  333 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.PORTABLE_DUSTBIN, true)) {
/*  334 */                 e.setCancelled(true);
/*  335 */                 p.openInventory(Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "删除物品"));
/*  336 */                 p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
/*  337 */                 return true;
/*      */               } 
/*  339 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  343 */     (new SlimefunItem(Categories.FOOD, SlimefunItems.BEEF_JERKY, "BEEF_JERKY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SALT, new ItemStack(Material.COOKED_BEEF), null, null, null, null, null, null, null }, new String[] { "饱食" }, (Object[])new Integer[] {
/*  344 */           Integer.valueOf(20)
/*  345 */         })).register(true);
/*      */     
/*  347 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.GRIND_STONE, "GRIND_STONE", new ItemStack[] { null, null, null, null, new ItemStack(Material.FENCE), null, null, (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝上)", 0), null }new ItemStack[] { new ItemStack(Material.BLAZE_ROD), new ItemStack(Material.BLAZE_POWDER, 4), new ItemStack(Material.BONE), (ItemStack)new CustomItem(Material.INK_SACK, 15, 4), new ItemStack(Material.GRAVEL), new ItemStack(Material.FLINT), new ItemStack(Material.NETHER_STALK), (ItemStack)new CustomItem(SlimefunItems.MAGIC_LUMP_1, 2), new ItemStack(Material.EYE_OF_ENDER), (ItemStack)new CustomItem(SlimefunItems.ENDER_LUMP_1, 2), new ItemStack(Material.COBBLESTONE), new ItemStack(Material.GRAVEL), new ItemStack(Material.WHEAT), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.DIRT), SlimefunItems.STONE_CHUNK }Material.FENCE))
/*      */ 
/*      */ 
/*      */       
/*  351 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/*  356 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("GRIND_STONE");
/*      */               
/*  358 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  359 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  360 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  361 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/*  362 */                   Inventory inv = disp.getInventory();
/*  363 */                   for (ItemStack current : inv.getContents()) {
/*  364 */                     for (ItemStack convert : RecipeType.getRecipeInputs((SlimefunItem)machine)) {
/*  365 */                       if (convert != null && SlimefunManager.isItemSimiliar(current, convert, true)) {
/*  366 */                         ItemStack output = RecipeType.getRecipeOutput((SlimefunItem)machine, convert);
/*  367 */                         if (InvUtils.fits(inv, output)) {
/*  368 */                           ItemStack removing = current.clone();
/*  369 */                           removing.setAmount(1);
/*  370 */                           inv.removeItem(new ItemStack[] { removing });
/*  371 */                           inv.addItem(new ItemStack[] { output });
/*  372 */                           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.0F);
/*      */                         } else {
/*  374 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  375 */                         }  return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*  379 */                   Messages.local.sendTranslation((CommandSender)p, "machines.unknown-material", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  382 */                 return true;
/*      */               } 
/*  384 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  388 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.ARMOR_FORGE, "ARMOR_FORGE", new ItemStack[] { null, null, null, null, new ItemStack(Material.ANVIL), null, null, (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝上)", 0), null }new ItemStack[0], Material.ANVIL))
/*      */ 
/*      */ 
/*      */       
/*  392 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(final Player p, MultiBlock mb, Block b)
/*      */             {
/*  397 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("ARMOR_FORGE");
/*      */               
/*  399 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  400 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  401 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  402 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/*  403 */                   final Inventory inv = disp.getInventory();
/*  404 */                   List<ItemStack[]> inputs = RecipeType.getRecipeInputList((SlimefunItem)machine);
/*      */                   
/*  406 */                   for (int i = 0; i < inputs.size(); i++) {
/*  407 */                     boolean craft = true;
/*  408 */                     for (int j = 0; j < (inv.getContents()).length; j++) {
/*  409 */                       if (!SlimefunManager.isItemSimiliar(inv.getContents()[j], ((ItemStack[])inputs.get(i))[j], true)) {
/*  410 */                         craft = false;
/*      */                         
/*      */                         break;
/*      */                       } 
/*      */                     } 
/*  415 */                     if (craft) {
/*  416 */                       final ItemStack adding = RecipeType.getRecipeOutputList((SlimefunItem)machine, inputs.get(i)).clone();
/*  417 */                       if (Slimefun.hasUnlocked(p, adding, true))
/*  418 */                         if (InvUtils.fits(inv, adding)) {
/*  419 */                           for (ItemStack removing : (ItemStack[])inputs.get(i)) {
/*  420 */                             if (removing != null) inv.removeItem(new ItemStack[] { removing }); 
/*      */                           } 
/*  422 */                           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
/*  423 */                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                               {
/*      */                                 public void run()
/*      */                                 {
/*  427 */                                   p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 2.0F);
/*  428 */                                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                       {
/*      */                                         public void run()
/*      */                                         {
/*  432 */                                           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 2.0F);
/*  433 */                                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                               {
/*      */                                                 public void run()
/*      */                                                 {
/*  437 */                                                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/*  438 */                                                   inv.addItem(new ItemStack[] { this.this$2.this$1.val$adding });
/*      */                                                 }
/*      */                                               }20L);
/*      */                                         }
/*      */                                       }20L);
/*      */                                 }
/*      */                               }20L);
/*      */                         } else {
/*  446 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                         }  
/*  448 */                       return true;
/*      */                     } 
/*      */                   } 
/*  451 */                   Messages.local.sendTranslation((CommandSender)p, "machines.pattern-not-found", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  454 */                 return true;
/*      */               } 
/*  456 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  460 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.ORE_CRUSHER, "ORE_CRUSHER", new ItemStack[] { null, null, null, null, new ItemStack(Material.NETHER_FENCE), null, new ItemStack(Material.IRON_FENCE), (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝上)", 0), new ItemStack(Material.IRON_FENCE) }new ItemStack[] { new ItemStack(Material.IRON_ORE), (ItemStack)new CustomItem(SlimefunItems.IRON_DUST, 
/*      */             
/*  462 */             ((Boolean)Slimefun.getItemValue("ORE_CRUSHER", "double-ores")).booleanValue() ? 2 : 1), new ItemStack(Material.GOLD_ORE), (ItemStack)new CustomItem(SlimefunItems.GOLD_DUST, ((Boolean)Slimefun.getItemValue("ORE_CRUSHER", "double-ores")).booleanValue() ? 2 : 1), new ItemStack(Material.NETHERRACK, 16), SlimefunItems.SULFATE, SlimefunItems.SIFTED_ORE, SlimefunItems.CRUSHED_ORE, SlimefunItems.CRUSHED_ORE, SlimefunItems.PULVERIZED_ORE, SlimefunItems.PURE_ORE_CLUSTER, SlimefunItems.TINY_URANIUM, new ItemStack(Material.COBBLESTONE, 8), new ItemStack(Material.SAND, 1), new ItemStack(Material.GOLD_INGOT), SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_4K, SlimefunItems.GOLD_DUST }Material.NETHER_FENCE))
/*      */       
/*  464 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/*  469 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("ORE_CRUSHER");
/*      */               
/*  471 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  472 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  473 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  474 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/*  475 */                   Inventory inv = disp.getInventory();
/*  476 */                   for (ItemStack current : inv.getContents()) {
/*  477 */                     for (ItemStack convert : RecipeType.getRecipeInputs((SlimefunItem)machine)) {
/*  478 */                       if (convert != null && SlimefunManager.isItemSimiliar(current, convert, true)) {
/*  479 */                         ItemStack adding = RecipeType.getRecipeOutput((SlimefunItem)machine, convert);
/*  480 */                         if (InvUtils.fits(inv, adding)) {
/*  481 */                           ItemStack removing = current.clone();
/*  482 */                           removing.setAmount(convert.getAmount());
/*  483 */                           inv.removeItem(new ItemStack[] { removing });
/*  484 */                           inv.addItem(new ItemStack[] { adding });
/*  485 */                           p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 1);
/*      */                         } else {
/*  487 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  488 */                         }  return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*  492 */                   Messages.local.sendTranslation((CommandSender)p, "machines.unknown-material", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  495 */                 return true;
/*      */               } 
/*  497 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  501 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.COMPRESSOR, "COMPRESSOR", new ItemStack[] { null, null, null, null, new ItemStack(Material.NETHER_FENCE), null, new ItemStack(Material.PISTON_BASE), (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝上)", 0), new ItemStack(Material.PISTON_BASE) }new ItemStack[] { new ItemStack(Material.COAL, 8), SlimefunItems.CARBON, (ItemStack)new CustomItem(SlimefunItems.STEEL_INGOT, 8), SlimefunItems.STEEL_PLATE, (ItemStack)new CustomItem(SlimefunItems.CARBON, 4), SlimefunItems.COMPRESSED_CARBON, (ItemStack)new CustomItem(SlimefunItems.STONE_CHUNK, 4), new ItemStack(Material.COBBLESTONE), (ItemStack)new CustomItem(SlimefunItems.REINFORCED_ALLOY_INGOT, 8), SlimefunItems.REINFORCED_PLATE }Material.NETHER_FENCE))
/*      */ 
/*      */ 
/*      */       
/*  505 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(final Player p, MultiBlock mb, Block b)
/*      */             {
/*  510 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("COMPRESSOR");
/*      */               
/*  512 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  513 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  514 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  515 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/*  516 */                   final Inventory inv = disp.getInventory();
/*  517 */                   for (ItemStack current : inv.getContents()) {
/*  518 */                     for (ItemStack convert : RecipeType.getRecipeInputs((SlimefunItem)machine)) {
/*  519 */                       if (convert != null && SlimefunManager.isItemSimiliar(current, convert, true)) {
/*  520 */                         final ItemStack adding = RecipeType.getRecipeOutput((SlimefunItem)machine, convert);
/*  521 */                         if (InvUtils.fits(inv, adding)) {
/*  522 */                           ItemStack removing = current.clone();
/*  523 */                           removing.setAmount(convert.getAmount());
/*  524 */                           inv.removeItem(new ItemStack[] { removing });
/*  525 */                           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0F, 1.0F);
/*  526 */                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                               {
/*      */                                 public void run()
/*      */                                 {
/*  530 */                                   p.getWorld().playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1.0F, 2.0F);
/*  531 */                                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                       {
/*      */                                         public void run()
/*      */                                         {
/*  535 */                                           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0F, 2.0F);
/*  536 */                                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                               {
/*      */                                                 public void run()
/*      */                                                 {
/*  540 */                                                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/*  541 */                                                   inv.addItem(new ItemStack[] { this.this$2.this$1.val$adding });
/*      */                                                 }
/*      */                                               }20L);
/*      */                                         }
/*      */                                       }20L);
/*      */                                 }
/*      */                               }20L);
/*      */                         } else {
/*  549 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  550 */                         }  return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*  554 */                   Messages.local.sendTranslation((CommandSender)p, "machines.unknown-material", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  557 */                 return true;
/*      */               } 
/*  559 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  563 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.MAGIC_LUMP_1, "MAGIC_LUMP_1", RecipeType.GRIND_STONE, new ItemStack[] { null, null, null, null, new ItemStack(Material.NETHER_STALK), null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.MAGIC_LUMP_1, 2)))
/*      */       
/*  565 */       .register(true);
/*      */     
/*  567 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.MAGIC_LUMP_2, "MAGIC_LUMP_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.MAGIC_LUMP_1, SlimefunItems.MAGIC_LUMP_1, null, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.MAGIC_LUMP_1, null, null, null, null
/*      */         
/*  569 */         })).register(true);
/*      */     
/*  571 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.MAGIC_LUMP_3, "MAGIC_LUMP_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.MAGIC_LUMP_2, SlimefunItems.MAGIC_LUMP_2, null, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.MAGIC_LUMP_2, null, null, null, null
/*      */         
/*  573 */         })).register(true);
/*      */     
/*  575 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.ENDER_LUMP_1, "ENDER_LUMP_1", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, null, new ItemStack(Material.EYE_OF_ENDER), null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.ENDER_LUMP_1, 2)))
/*      */       
/*  577 */       .register(true);
/*      */     
/*  579 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.ENDER_LUMP_2, "ENDER_LUMP_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.ENDER_LUMP_1, SlimefunItems.ENDER_LUMP_1, null, SlimefunItems.ENDER_LUMP_1, SlimefunItems.ENDER_LUMP_1, null, null, null, null
/*      */         
/*  581 */         })).register(true);
/*      */     
/*  583 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.ENDER_LUMP_3, "ENDER_LUMP_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.ENDER_LUMP_2, SlimefunItems.ENDER_LUMP_2, null, SlimefunItems.ENDER_LUMP_2, SlimefunItems.ENDER_LUMP_2, null, null, null, null
/*      */         
/*  585 */         })).register(true);
/*      */     
/*  587 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.ENDER_BACKPACK, "ENDER_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.ENDER_LUMP_2, new ItemStack(Material.LEATHER), SlimefunItems.ENDER_LUMP_2, new ItemStack(Material.LEATHER), new ItemStack(Material.CHEST), new ItemStack(Material.LEATHER), SlimefunItems.ENDER_LUMP_2, new ItemStack(Material.LEATHER), SlimefunItems.ENDER_LUMP_2
/*      */         
/*  589 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/*  593 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.ENDER_BACKPACK, true)) {
/*  594 */                 e.setCancelled(true);
/*  595 */                 p.openInventory(p.getEnderChest());
/*  596 */                 p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
/*  597 */                 return true;
/*      */               } 
/*  599 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  603 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.ENDER_HELMET, "ENDER_HELMET", RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.ENDER_LUMP_1, new ItemStack(Material.EYE_OF_ENDER), SlimefunItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN), null, null, null
/*      */         
/*  605 */         })).register(true);
/*      */     
/*  607 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.ENDER_CHESTPLATE, "ENDER_CHESTPLATE", RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.ENDER_LUMP_1, null, SlimefunItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.EYE_OF_ENDER), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN)
/*      */         
/*  609 */         })).register(true);
/*      */     
/*  611 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.ENDER_LEGGINGS, "ENDER_LEGGINGS", RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.ENDER_LUMP_1, new ItemStack(Material.EYE_OF_ENDER), SlimefunItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN)
/*      */         
/*  613 */         })).register(true);
/*      */     
/*  615 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.ENDER_BOOTS, "ENDER_BOOTS", RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, SlimefunItems.ENDER_LUMP_1, null, SlimefunItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN)
/*      */         
/*  617 */         })).register(true);
/*      */     
/*  619 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.MAGIC_EYE_OF_ENDER, "MAGIC_EYE_OF_ENDER", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.ENDER_LUMP_2, new ItemStack(Material.ENDER_PEARL), SlimefunItems.ENDER_LUMP_2, new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.EYE_OF_ENDER), new ItemStack(Material.ENDER_PEARL), SlimefunItems.ENDER_LUMP_2, new ItemStack(Material.ENDER_PEARL), SlimefunItems.ENDER_LUMP_2
/*      */         
/*  621 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/*  625 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.MAGIC_EYE_OF_ENDER, true)) {
/*  626 */                 e.getParentEvent().setCancelled(true);
/*  627 */                 PlayerInventory.update(p);
/*  628 */                 if (p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null && 
/*  629 */                   SlimefunManager.isItemSimiliar(p.getInventory().getHelmet(), SlimefunItems.ENDER_HELMET, true) && SlimefunManager.isItemSimiliar(p.getInventory().getChestplate(), SlimefunItems.ENDER_CHESTPLATE, true) && SlimefunManager.isItemSimiliar(p.getInventory().getLeggings(), SlimefunItems.ENDER_LEGGINGS, true) && SlimefunManager.isItemSimiliar(p.getInventory().getBoots(), SlimefunItems.ENDER_BOOTS, true)) {
/*  630 */                   p.launchProjectile(EnderPearl.class);
/*  631 */                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
/*      */                 } 
/*      */                 
/*  634 */                 return true;
/*      */               } 
/*  636 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  640 */     (new SlimefunItem(Categories.FOOD, SlimefunItems.MAGIC_SUGAR, "MAGIC_SUGAR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.SUGAR), new ItemStack(Material.REDSTONE), new ItemStack(Material.GLOWSTONE_DUST), null, null, null, null, null, null }, new String[] { "effects.SPEED" }, (Object[])new Integer[] {
/*  641 */           Integer.valueOf(4)
/*  642 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/*  646 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.MAGIC_SUGAR, true)) {
/*  647 */                 PlayerInventory.consumeItemInHand(p);
/*  648 */                 p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
/*  649 */                 p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, ((Integer)Slimefun.getItemValue("MAGIC_SUGAR", "effects.SPEED")).intValue()));
/*  650 */                 return true;
/*      */               } 
/*  652 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  656 */     (new SlimefunItem(Categories.FOOD, SlimefunItems.MONSTER_JERKY, "MONSTER_JERKY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SALT, new ItemStack(Material.ROTTEN_FLESH), null, null, null, null, null, null, null
/*      */         
/*  658 */         })).register(true);
/*      */     
/*  660 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_HELMET, "SLIME_HELMET", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), null, null, null
/*      */         
/*  662 */         })).register(true);
/*      */     
/*  664 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_CHESTPLATE, "SLIME_CHESTPLATE", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT)
/*      */         
/*  666 */         })).register(true);
/*      */     
/*  668 */     (new SlimefunArmorPiece(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_LEGGINGS, "SLIME_LEGGINGS", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT) }, new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, 300, 2)
/*      */ 
/*      */         
/*  671 */         })).register(true);
/*      */     
/*  673 */     (new SlimefunArmorPiece(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_BOOTS, "SLIME_BOOTS", RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT) }, new PotionEffect[] { new PotionEffect(PotionEffectType.JUMP, 300, 5)
/*      */ 
/*      */         
/*  676 */         })).register(true);
/*      */     
/*  678 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.SWORD_OF_BEHEADING, "SWORD_OF_BEHEADING", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.EMERALD), null, SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.EMERALD), SlimefunItems.MAGIC_LUMP_2, null, new ItemStack(Material.BLAZE_ROD), null }, new String[] { "chance.PLAYER", "chance.SKELETON", "chance.WITHER_SKELETON", "chance.ZOMBIE", "chance.CREEPER" }, (Object[])new Integer[] {
/*  679 */           Integer.valueOf(70), Integer.valueOf(40), Integer.valueOf(25), Integer.valueOf(40), Integer.valueOf(40)
/*  680 */         })).register(true);
/*      */     
/*  682 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.MAGICAL_BOOK_COVER, "MAGICAL_BOOK_COVER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.MAGIC_LUMP_2, null, SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.BOOK), SlimefunItems.MAGIC_LUMP_2, null, SlimefunItems.MAGIC_LUMP_2, null
/*      */         
/*  684 */         })).register(true);
/*      */     
/*  686 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.BASIC_CIRCUIT_BOARD, "BASIC_CIRCUIT_BOARD", RecipeType.MOB_DROP, new ItemStack[] { null, null, null, null, (ItemStack)new CustomItem(Material.MONSTER_EGG, "&a&oIron Golem", 99), null, null, null, null
/*      */         
/*  688 */         })).register(true);
/*      */     
/*  690 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.ADVANCED_CIRCUIT_BOARD, "ADVANCED_CIRCUIT_BOARD", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK)
/*      */         
/*  692 */         })).register(true);
/*      */     
/*  694 */     (new SlimefunGadget(Categories.TOOLS, SlimefunItems.GOLD_PAN, "GOLD_PAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.STONE), new ItemStack(Material.BOWL), new ItemStack(Material.STONE), new ItemStack(Material.STONE), new ItemStack(Material.STONE), new ItemStack(Material.STONE) }, new ItemStack[] { new ItemStack(Material.GRAVEL), new ItemStack(Material.FLINT), new ItemStack(Material.GRAVEL), new ItemStack(Material.CLAY_BALL), new ItemStack(Material.GRAVEL), SlimefunItems.SIFTED_ORE }, new String[] { "chance.FLINT", "chance.CLAY", "chance.SIFTED_ORE" }, (Object[])new Integer[] {
/*      */ 
/*      */           
/*  697 */           Integer.valueOf(47), Integer.valueOf(28), Integer.valueOf(15)
/*  698 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/*  702 */               if (!SlimefunManager.isItemSimiliar(item, SlimefunItems.GOLD_PAN, true)) {
/*  703 */                 return false;
/*      */               }
/*  705 */               e.setCancelled(true);
/*  706 */               if (e.getClickedBlock() == null || !e.getClickedBlock().getType().equals(Material.GRAVEL)) {
/*  707 */                 return true;
/*      */               }
/*  709 */               if (!CSCoreLib.getLib().getProtectionManager().canBuild(p.getUniqueId(), e.getClickedBlock(), true)) {
/*  710 */                 return true;
/*      */               }
/*  712 */               if (ProtectionUtils.canBuild(p, e.getClickedBlock())) {
/*  713 */                 List<ItemStack> drops = new ArrayList<>();
/*  714 */                 if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("GOLD_PAN", "chance.SIFTED_ORE")).intValue())) { drops.add(SlimefunItems.SIFTED_ORE); }
/*  715 */                 else if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("GOLD_PAN", "chance.CLAY")).intValue())) { drops.add(new ItemStack(Material.CLAY_BALL)); }
/*  716 */                 else if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("GOLD_PAN", "chance.FLINT")).intValue())) { drops.add(new ItemStack(Material.FLINT)); }
/*      */                 
/*  718 */                 e.getClickedBlock().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.STEP_SOUND, e.getClickedBlock().getType());
/*  719 */                 e.getClickedBlock().setType(Material.AIR);
/*  720 */                 for (ItemStack drop : drops) {
/*  721 */                   e.getClickedBlock().getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), drop);
/*      */                 }
/*      */               } 
/*  724 */               return true;
/*      */             }
/*      */           } });
/*      */     
/*  728 */     (new SlimefunItem(Categories.MISC, SlimefunItems.SIFTED_ORE, "SIFTED_ORE", RecipeType.GOLD_PAN, new ItemStack[] { new ItemStack(Material.GRAVEL), null, null, null, null, null, null, null, null
/*      */         
/*  730 */         })).register(true);
/*      */     
/*  732 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.SMELTERY, "SMELTERY", new ItemStack[] { null, new ItemStack(Material.NETHER_FENCE), null, new ItemStack(Material.NETHER_BRICK), (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝上)", 0), new ItemStack(Material.NETHER_BRICK), null, new ItemStack(Material.FLINT_AND_STEEL), null }new ItemStack[] { SlimefunItems.IRON_DUST, new ItemStack(Material.IRON_INGOT) }, Material.NETHER_FENCE, new String[] { "chance.fireBreak" }, (Object[])new Integer[] {
/*      */ 
/*      */           
/*  735 */           Integer.valueOf(34)
/*  736 */         })).register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/*  741 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("SMELTERY");
/*      */               
/*  743 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  744 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  745 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  746 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/*  747 */                   Inventory inv = disp.getInventory();
/*  748 */                   List<ItemStack[]> inputs = RecipeType.getRecipeInputList((SlimefunItem)machine);
/*      */                   
/*  750 */                   for (int i = 0; i < inputs.size(); i++) {
/*  751 */                     boolean craft = true;
/*  752 */                     for (ItemStack converting : (ItemStack[])inputs.get(i)) {
/*  753 */                       if (converting != null)
/*  754 */                         for (int j = 0; j < (inv.getContents()).length; j++) {
/*  755 */                           if (j == (inv.getContents()).length - 1 && !SlimefunManager.isItemSimiliar(converting, inv.getContents()[j], true, SlimefunManager.DataType.ALWAYS)) {
/*  756 */                             craft = false;
/*      */                             break;
/*      */                           } 
/*  759 */                           if (SlimefunManager.isItemSimiliar(inv.getContents()[j], converting, true, SlimefunManager.DataType.ALWAYS)) {
/*      */                             break;
/*      */                           }
/*      */                         }  
/*      */                     } 
/*  764 */                     if (craft) {
/*  765 */                       ItemStack adding = RecipeType.getRecipeOutputList((SlimefunItem)machine, inputs.get(i)).clone();
/*  766 */                       if (Slimefun.hasUnlocked(p, adding, true))
/*  767 */                         if (InvUtils.fits(inv, adding)) {
/*  768 */                           for (ItemStack removing : (ItemStack[])inputs.get(i)) {
/*  769 */                             if (removing != null) inv.removeItem(new ItemStack[] { removing }); 
/*      */                           } 
/*  771 */                           inv.addItem(new ItemStack[] { adding });
/*  772 */                           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*  773 */                           p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
/*  774 */                           Block raw_disp = b.getRelative(BlockFace.DOWN);
/*  775 */                           Hopper chamber = null;
/*  776 */                           if (BlockStorage.check(raw_disp.getRelative(BlockFace.EAST).getState().getBlock(), "IGNITION_CHAMBER")) {
/*  777 */                             chamber = (Hopper)raw_disp.getRelative(BlockFace.EAST).getState();
/*  778 */                           } else if (BlockStorage.check(raw_disp.getRelative(BlockFace.WEST).getState().getBlock(), "IGNITION_CHAMBER")) {
/*  779 */                             chamber = (Hopper)raw_disp.getRelative(BlockFace.WEST).getState();
/*  780 */                           } else if (BlockStorage.check(raw_disp.getRelative(BlockFace.NORTH).getState().getBlock(), "IGNITION_CHAMBER")) {
/*  781 */                             chamber = (Hopper)raw_disp.getRelative(BlockFace.NORTH).getState();
/*  782 */                           } else if (BlockStorage.check(raw_disp.getRelative(BlockFace.SOUTH).getState().getBlock(), "IGNITION_CHAMBER")) {
/*  783 */                             chamber = (Hopper)raw_disp.getRelative(BlockFace.SOUTH).getState();
/*      */                           } 
/*      */                           
/*  786 */                           if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("SMELTERY", "chance.fireBreak")).intValue())) {
/*  787 */                             if (chamber != null) {
/*  788 */                               if (chamber.getInventory().contains(Material.FLINT_AND_STEEL)) {
/*  789 */                                 ItemStack item = chamber.getInventory().getItem(chamber.getInventory().first(Material.FLINT_AND_STEEL));
/*  790 */                                 item.setDurability((short)(item.getDurability() + 1));
/*  791 */                                 if (item.getDurability() >= item.getType().getMaxDurability()) {
/*  792 */                                   item.setAmount(0);
/*  793 */                                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/*      */                                 } 
/*  795 */                                 p.getWorld().playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.0F);
/*      */                               } else {
/*      */                                 
/*  798 */                                 Messages.local.sendTranslation((CommandSender)p, "machines.ignition-chamber-no-flint", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                                 
/*  800 */                                 Block fire = b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
/*  801 */                                 fire.getWorld().playEffect(fire.getLocation(), Effect.STEP_SOUND, fire.getType());
/*  802 */                                 fire.setType(Material.AIR);
/*      */                               } 
/*      */                             } else {
/*      */                               
/*  806 */                               Block fire = b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
/*  807 */                               fire.getWorld().playEffect(fire.getLocation(), Effect.STEP_SOUND, fire.getType());
/*  808 */                               fire.setType(Material.AIR);
/*      */                             } 
/*      */                           }
/*      */                         } else {
/*  812 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                         }  
/*  814 */                       return true;
/*      */                     } 
/*      */                   } 
/*  817 */                   Messages.local.sendTranslation((CommandSender)p, "machines.pattern-not-found", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  820 */                 return true;
/*      */               } 
/*  822 */               return false;
/*      */             }
/*      */           } });
/*      */ 
/*      */     
/*  827 */     (new SlimefunItem(Categories.MACHINES_1, SlimefunItems.IGNITION_CHAMBER, "IGNITION_CHAMBER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.STEEL_PLATE, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.STEEL_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_PLATE, SlimefunItems.ELECTRIC_MOTOR, null, new ItemStack(Material.HOPPER), null
/*      */         
/*  829 */         })).register(true);
/*      */     
/*  831 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.PRESSURE_CHAMBER, "PRESSURE_CHAMBER", new ItemStack[] { new ItemStack(Material.STEP), (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝下)", 0), new ItemStack(Material.STEP), new ItemStack(Material.PISTON_BASE), new ItemStack(Material.GLASS), new ItemStack(Material.PISTON_BASE), new ItemStack(Material.PISTON_BASE), new ItemStack(Material.CAULDRON_ITEM), new ItemStack(Material.PISTON_BASE) }new ItemStack[] { SlimefunItems.CARBON_CHUNK, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.RAW_CARBONADO, SlimefunItems.CARBONADO }, Material.CAULDRON))
/*      */ 
/*      */ 
/*      */       
/*  835 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(final Player p, MultiBlock mb, final Block b)
/*      */             {
/*  840 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("PRESSURE_CHAMBER");
/*      */               
/*  842 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/*  843 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/*  844 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/*  845 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getState();
/*  846 */                   final Inventory inv = disp.getInventory();
/*  847 */                   for (ItemStack current : inv.getContents()) {
/*  848 */                     for (ItemStack convert : RecipeType.getRecipeInputs((SlimefunItem)machine)) {
/*  849 */                       if (convert != null && SlimefunManager.isItemSimiliar(current, convert, true)) {
/*  850 */                         final ItemStack adding = RecipeType.getRecipeOutput((SlimefunItem)machine, convert);
/*  851 */                         if (InvUtils.fits(inv, adding)) {
/*  852 */                           ItemStack removing = current.clone();
/*  853 */                           removing.setAmount(convert.getAmount());
/*  854 */                           inv.removeItem(new ItemStack[] { removing });
/*  855 */                           p.getWorld().playSound(b.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
/*  856 */                           p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  857 */                           p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  858 */                           p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  859 */                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                               {
/*      */                                 public void run()
/*      */                                 {
/*  863 */                                   p.getWorld().playSound(b.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
/*  864 */                                   p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  865 */                                   p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  866 */                                   p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  867 */                                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                       {
/*      */                                         public void run()
/*      */                                         {
/*  871 */                                           p.getWorld().playSound(b.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
/*  872 */                                           p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  873 */                                           p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  874 */                                           p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  875 */                                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                               {
/*      */                                                 public void run()
/*      */                                                 {
/*  879 */                                                   p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  880 */                                                   p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  881 */                                                   p.getWorld().playEffect(b.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 4);
/*  882 */                                                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/*  883 */                                                   inv.addItem(new ItemStack[] { this.this$2.this$1.val$adding });
/*      */                                                 }
/*      */                                               }20L);
/*      */                                         }
/*      */                                       }20L);
/*      */                                 }
/*      */                               }20L);
/*      */                         } else {
/*  891 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  892 */                         }  return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*  896 */                   Messages.local.sendTranslation((CommandSender)p, "machines.unknown-material", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/*  899 */                 return true;
/*      */               } 
/*  901 */               return false;
/*      */             }
/*      */           } });
/*      */     
/*  905 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.BATTERY, "BATTERY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.REDSTONE), null, SlimefunItems.ZINC_INGOT, SlimefunItems.SULFATE, SlimefunItems.COPPER_INGOT, SlimefunItems.ZINC_INGOT, SlimefunItems.SULFATE, SlimefunItems.COPPER_INGOT
/*      */         
/*  907 */         })).register(true);
/*      */     
/*  909 */     SlimefunManager.registerArmorSet(new ItemStack(Material.GLOWSTONE), new ItemStack[] { SlimefunItems.GLOWSTONE_HELMET, SlimefunItems.GLOWSTONE_CHESTPLATE, SlimefunItems.GLOWSTONE_LEGGINGS, SlimefunItems.GLOWSTONE_BOOTS }, "GLOWSTONE", new PotionEffect[][] { { new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0) }, { new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0) }, { new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0) }, { new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0) } }true, true);
/*      */ 
/*      */     
/*  912 */     SlimefunManager.registerArmorSet(SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_HELMET, SlimefunItems.DAMASCUS_STEEL_CHESTPLATE, SlimefunItems.DAMASCUS_STEEL_LEGGINGS, SlimefunItems.DAMASCUS_STEEL_BOOTS }, "DAMASCUS_STEEL", true, false);
/*      */     
/*  914 */     SlimefunManager.registerArmorSet(SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack[] { SlimefunItems.REINFORCED_ALLOY_HELMET, SlimefunItems.REINFORCED_ALLOY_CHESTPLATE, SlimefunItems.REINFORCED_ALLOY_LEGGINGS, SlimefunItems.REINFORCED_ALLOY_BOOTS }, "REINFORCED_ALLOY", true, false);
/*      */     
/*  916 */     SlimefunManager.registerArmorSet(new ItemStack(Material.CACTUS), new ItemStack[] { SlimefunItems.CACTUS_HELMET, SlimefunItems.CACTUS_CHESTPLATE, SlimefunItems.CACTUS_LEGGINGS, SlimefunItems.CACTUS_BOOTS }, "CACTUS", true, false);
/*      */     
/*  918 */     (new Alloy(SlimefunItems.REINFORCED_ALLOY_INGOT, "REINFORCED_ALLOY_INGOT", new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.SOLDER_INGOT, SlimefunItems.BILLON_INGOT, SlimefunItems.GOLD_24K, null, null, null
/*      */         
/*  920 */         })).register(true);
/*      */     
/*  922 */     (new Alloy(SlimefunItems.HARDENED_METAL_INGOT, "HARDENED_METAL_INGOT", new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.ALUMINUM_BRONZE_INGOT, null, null, null, null, null
/*      */         
/*  924 */         })).register(true);
/*      */     
/*  926 */     (new Alloy(SlimefunItems.DAMASCUS_STEEL_INGOT, "DAMASCUS_STEEL_INGOT", new ItemStack[] { SlimefunItems.STEEL_INGOT, SlimefunItems.IRON_DUST, SlimefunItems.CARBON, new ItemStack(Material.IRON_INGOT), null, null, null, null, null
/*      */         
/*  928 */         })).register(true);
/*      */     
/*  930 */     (new Alloy(SlimefunItems.STEEL_INGOT, "STEEL_INGOT", new ItemStack[] { SlimefunItems.IRON_DUST, SlimefunItems.CARBON, new ItemStack(Material.IRON_INGOT), null, null, null, null, null, null
/*      */         
/*  932 */         })).register(true);
/*      */     
/*  934 */     (new Alloy(SlimefunItems.BRONZE_INGOT, "BRONZE_INGOT", new ItemStack[] { SlimefunItems.COPPER_DUST, SlimefunItems.TIN_DUST, SlimefunItems.COPPER_INGOT, null, null, null, null, null, null
/*      */         
/*  936 */         })).register(true);
/*      */     
/*  938 */     (new Alloy(SlimefunItems.DURALUMIN_INGOT, "DURALUMIN_INGOT", new ItemStack[] { SlimefunItems.ALUMINUM_DUST, SlimefunItems.COPPER_DUST, SlimefunItems.ALUMINUM_INGOT, null, null, null, null, null, null
/*      */         
/*  940 */         })).register(true);
/*      */     
/*  942 */     (new Alloy(SlimefunItems.BILLON_INGOT, "BILLON_INGOT", new ItemStack[] { SlimefunItems.SILVER_DUST, SlimefunItems.COPPER_DUST, SlimefunItems.SILVER_INGOT, null, null, null, null, null, null
/*      */         
/*  944 */         })).register(true);
/*      */     
/*  946 */     (new Alloy(SlimefunItems.BRASS_INGOT, "BRASS_INGOT", new ItemStack[] { SlimefunItems.COPPER_DUST, SlimefunItems.ZINC_DUST, SlimefunItems.COPPER_INGOT, null, null, null, null, null, null
/*      */         
/*  948 */         })).register(true);
/*      */     
/*  950 */     (new Alloy(SlimefunItems.ALUMINUM_BRASS_INGOT, "ALUMINUM_BRASS_INGOT", new ItemStack[] { SlimefunItems.ALUMINUM_DUST, SlimefunItems.BRASS_INGOT, SlimefunItems.ALUMINUM_INGOT, null, null, null, null, null, null
/*      */         
/*  952 */         })).register(true);
/*      */     
/*  954 */     (new Alloy(SlimefunItems.ALUMINUM_BRONZE_INGOT, "ALUMINUM_BRONZE_INGOT", new ItemStack[] { SlimefunItems.ALUMINUM_DUST, SlimefunItems.BRONZE_INGOT, SlimefunItems.ALUMINUM_INGOT, null, null, null, null, null, null
/*      */         
/*  956 */         })).register(true);
/*      */     
/*  958 */     (new Alloy(SlimefunItems.CORINTHIAN_BRONZE_INGOT, "CORINTHIAN_BRONZE_INGOT", new ItemStack[] { SlimefunItems.SILVER_DUST, SlimefunItems.GOLD_DUST, SlimefunItems.COPPER_DUST, SlimefunItems.BRONZE_INGOT, null, null, null, null, null
/*      */         
/*  960 */         })).register(true);
/*      */     
/*  962 */     (new Alloy(SlimefunItems.SOLDER_INGOT, "SOLDER_INGOT", new ItemStack[] { SlimefunItems.LEAD_DUST, SlimefunItems.TIN_DUST, SlimefunItems.LEAD_INGOT, null, null, null, null, null, null
/*      */         
/*  964 */         })).register(true);
/*      */     
/*  966 */     (new ReplacingAlloy(SlimefunItems.SYNTHETIC_SAPPHIRE, "SYNTHETIC_SAPPHIRE", new ItemStack[] { SlimefunItems.ALUMINUM_DUST, new ItemStack(Material.GLASS), new ItemStack(Material.THIN_GLASS), SlimefunItems.ALUMINUM_INGOT, (new MaterialData(Material.INK_SACK, (byte)4))
/*  967 */           .toItemStack(1), null, null, null, null
/*  968 */         })).register(true);
/*      */     
/*  970 */     (new ReplacingItem(Categories.RESOURCES, SlimefunItems.SYNTHETIC_DIAMOND, "SYNTHETIC_DIAMOND", RecipeType.PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.CARBON_CHUNK, null, null, null, null, null, null, null, null
/*      */         
/*  972 */         })).register(true);
/*      */     
/*  974 */     (new Alloy(SlimefunItems.RAW_CARBONADO, "RAW_CARBONADO", new ItemStack[] { SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.CARBON_CHUNK, new ItemStack(Material.THIN_GLASS), null, null, null, null, null, null
/*      */         
/*  976 */         })).register(true);
/*      */     
/*  978 */     (new Alloy(SlimefunItems.NICKEL_INGOT, "NICKEL_INGOT", new ItemStack[] { SlimefunItems.IRON_DUST, new ItemStack(Material.IRON_INGOT), SlimefunItems.COPPER_DUST, null, null, null, null, null, null
/*      */         
/*  980 */         })).register(true);
/*      */     
/*  982 */     (new Alloy(SlimefunItems.COBALT_INGOT, "COBALT_INGOT", new ItemStack[] { SlimefunItems.IRON_DUST, SlimefunItems.COPPER_DUST, SlimefunItems.NICKEL_INGOT, null, null, null, null, null, null
/*      */         
/*  984 */         })).register(true);
/*      */     
/*  986 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.CARBONADO, "CARBONADO", RecipeType.PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.RAW_CARBONADO, null, null, null, null, null, null, null, null
/*      */         
/*  988 */         })).register(true);
/*      */     
/*  990 */     (new Alloy(SlimefunItems.FERROSILICON, "FERROSILICON", new ItemStack[] { new ItemStack(Material.IRON_INGOT), SlimefunItems.IRON_DUST, SlimefunItems.SILICON, null, null, null, null, null, null
/*      */         
/*  992 */         })).register(true);
/*      */     
/*  994 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.IRON_DUST, "IRON_DUST", RecipeType.ORE_CRUSHER, new ItemStack[] { new ItemStack(Material.IRON_ORE), null, null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.IRON_DUST, 
/*  995 */           ((Boolean)Slimefun.getItemValue("ORE_CRUSHER", "double-ores")).booleanValue() ? 2 : 1)))
/*  996 */       .register(true);
/*      */     
/*  998 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_DUST, "GOLD_DUST", RecipeType.ORE_CRUSHER, new ItemStack[] { new ItemStack(Material.GOLD_ORE), null, null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.GOLD_DUST, 
/*  999 */           ((Boolean)Slimefun.getItemValue("ORE_CRUSHER", "double-ores")).booleanValue() ? 2 : 1)))
/* 1000 */       .register(true);
/*      */     
/* 1002 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.COPPER_DUST, "COPPER_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1004 */         })).register(true);
/*      */     
/* 1006 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.TIN_DUST, "TIN_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1008 */         })).register(true);
/*      */     
/* 1010 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.LEAD_DUST, "LEAD_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1012 */         })).register(true);
/*      */     
/* 1014 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.SILVER_DUST, "SILVER_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1016 */         })).register(true);
/*      */     
/* 1018 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.ALUMINUM_DUST, "ALUMINUM_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1020 */         })).register(true);
/*      */     
/* 1022 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.ZINC_DUST, "ZINC_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1024 */         })).register(true);
/*      */     
/* 1026 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.MAGNESIUM_DUST, "MAGNESIUM_DUST", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1028 */         })).register(true);
/*      */     
/* 1030 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.COPPER_INGOT, "COPPER_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.COPPER_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1032 */         })).register(true);
/*      */     
/* 1034 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.TIN_INGOT, "TIN_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.TIN_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1036 */         })).register(true);
/*      */     
/* 1038 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.SILVER_INGOT, "SILVER_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.SILVER_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1040 */         })).register(true);
/*      */     
/* 1042 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.LEAD_INGOT, "LEAD_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.LEAD_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1044 */         })).register(true);
/*      */     
/* 1046 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.ALUMINUM_INGOT, "ALUMINUM_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.ALUMINUM_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1048 */         })).register(true);
/*      */     
/* 1050 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.ZINC_INGOT, "ZINC_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.ZINC_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1052 */         })).register(true);
/*      */     
/* 1054 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.MAGNESIUM_INGOT, "MAGNESIUM_INGOT", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.MAGNESIUM_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1056 */         })).register(true);
/*      */     
/* 1058 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.SULFATE, "SULFATE", RecipeType.ORE_CRUSHER, new ItemStack[] { new ItemStack(Material.NETHERRACK, 16), null, null, null, null, null, null, null, null
/*      */         
/* 1060 */         })).register(true);
/*      */     
/* 1062 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.CARBON, "CARBON", RecipeType.COMPRESSOR, new ItemStack[] { new ItemStack(Material.COAL, 8), null, null, null, null, null, null, null, null
/*      */         
/* 1064 */         })).register(true);
/*      */     
/* 1066 */     (new SlimefunItem(Categories.MISC, SlimefunItems.WHEAT_FLOUR, "WHEAT_FLOUR", RecipeType.GRIND_STONE, new ItemStack[] { null, null, null, null, new ItemStack(Material.WHEAT), null, null, null, null
/*      */         
/* 1068 */         })).register(true);
/*      */     
/* 1070 */     (new SlimefunItem(Categories.MISC, SlimefunItems.STEEL_PLATE, "STEEL_PLATE", RecipeType.COMPRESSOR, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.STEEL_INGOT, 8), null, null, null, null, null, null, null, null
/*      */         
/* 1072 */         })).register(true);
/*      */     
/* 1074 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.COMPRESSED_CARBON, "COMPRESSED_CARBON", RecipeType.COMPRESSOR, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.CARBON, 4), null, null, null, null, null, null, null, null
/*      */         
/* 1076 */         })).register(true);
/*      */     
/* 1078 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.CARBON_CHUNK, "CARBON_CHUNK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, new ItemStack(Material.FLINT), SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON
/*      */         
/* 1080 */         })).register(true);
/*      */     
/* 1082 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.STEEL_THRUSTER, "STEEL_THRUSTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.REDSTONE), null, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.STEEL_PLATE, new ItemStack(Material.FIREBALL), SlimefunItems.STEEL_PLATE
/*      */         
/* 1084 */         })).register(true);
/*      */     
/* 1086 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.POWER_CRYSTAL, "POWER_CRYSTAL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.REDSTONE), SlimefunItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.REDSTONE), SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.REDSTONE), SlimefunItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.REDSTONE)
/*      */         
/* 1088 */         })).register(true);
/*      */     
/* 1090 */     (new Jetpack(SlimefunItems.DURALUMIN_JETPACK, "DURALUMIN_JETPACK", new ItemStack[] { SlimefunItems.DURALUMIN_INGOT, NarItems.IRIDIUM, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.35D))
/*      */ 
/*      */       
/* 1093 */       .register(true);
/*      */     
/* 1095 */     (new Jetpack(SlimefunItems.SOLDER_JETPACK, "SOLDER_JETPACK", new ItemStack[] { SlimefunItems.SOLDER_INGOT, NarItems.IRIDIUM, SlimefunItems.SOLDER_INGOT, SlimefunItems.SOLDER_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.SOLDER_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.4D))
/*      */ 
/*      */       
/* 1098 */       .register(true);
/*      */     
/* 1100 */     (new Jetpack(SlimefunItems.BILLON_JETPACK, "BILLON_JETPACK", new ItemStack[] { SlimefunItems.BILLON_INGOT, NarItems.IRIDIUM, SlimefunItems.BILLON_INGOT, SlimefunItems.BILLON_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.BILLON_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.45D))
/*      */ 
/*      */       
/* 1103 */       .register(true);
/*      */     
/* 1105 */     (new Jetpack(SlimefunItems.STEEL_JETPACK, "STEEL_JETPACK", new ItemStack[] { SlimefunItems.STEEL_INGOT, NarItems.IRIDIUM, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.5D))
/*      */ 
/*      */       
/* 1108 */       .register(true);
/*      */     
/* 1110 */     (new Jetpack(SlimefunItems.DAMASCUS_STEEL_JETPACK, "DAMASCUS_STEEL_JETPACK", new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_INGOT, NarItems.IRIDIUM, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.55D))
/*      */ 
/*      */       
/* 1113 */       .register(true);
/*      */     
/* 1115 */     (new Jetpack(SlimefunItems.REINFORCED_ALLOY_JETPACK, "REINFORCED_ALLOY_JETPACK", new ItemStack[] { SlimefunItems.REINFORCED_ALLOY_INGOT, NarItems.IRIDIUM, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.6D))
/*      */ 
/*      */       
/* 1118 */       .register(true);
/*      */     
/* 1120 */     (new Jetpack(SlimefunItems.CARBONADO_JETPACK, "CARBONADO_JETPACK", new ItemStack[] { SlimefunItems.CARBON_CHUNK, NarItems.IRIDIUM, SlimefunItems.CARBON_CHUNK, SlimefunItems.CARBONADO, SlimefunItems.POWER_CRYSTAL, SlimefunItems.CARBONADO, SlimefunItems.STEEL_THRUSTER, SlimefunItems.LARGE_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.7D))
/*      */ 
/*      */       
/* 1123 */       .register(true);
/*      */     
/* 1125 */     (new SlimefunItem(Categories.TECH, SlimefunItems.PARACHUTE, "PARACHUTE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CHAIN, null, SlimefunItems.CHAIN, null, null, null
/*      */         
/* 1127 */         })).register(true);
/*      */     
/* 1129 */     (new SlimefunItem(Categories.MISC, SlimefunItems.CHAIN, "CHAIN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHAIN, 8)))
/*      */       
/* 1131 */       .register(true);
/*      */     
/* 1133 */     (new SlimefunItem(Categories.MISC, SlimefunItems.HOOK, "HOOK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, null, null, null
/*      */         
/* 1135 */         })).register(true);
/*      */     
/* 1137 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.GRAPPLING_HOOK, "GRAPPLING_HOOK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.HOOK, SlimefunItems.HOOK, null, SlimefunItems.CHAIN, SlimefunItems.HOOK, SlimefunItems.CHAIN, null, null
/*      */         
/* 1139 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1143 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.GRAPPLING_HOOK, true)) {
/* 1144 */                 if (e.getClickedBlock() == null && !Variables.jump.containsKey(p.getUniqueId())) {
/* 1145 */                   e.setCancelled(true);
/* 1146 */                   if (p.getInventory().getItemInOffHand().getType().equals(Material.BOW))
/*      */                   {
/* 1148 */                     return false;
/*      */                   }
/* 1150 */                   Variables.jump.put(p.getUniqueId(), Boolean.valueOf((p.getInventory().getItemInMainHand().getType() != Material.SHEARS)));
/* 1151 */                   if (p.getInventory().getItemInMainHand().getType() == Material.LEASH) PlayerInventory.consumeItemInHand(p);
/*      */                   
/* 1153 */                   Vector direction = p.getEyeLocation().getDirection().multiply(2.0D);
/* 1154 */                   Projectile projectile = (Projectile)p.getWorld().spawn(p.getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), Arrow.class);
/* 1155 */                   projectile.setShooter((ProjectileSource)p);
/* 1156 */                   projectile.setVelocity(direction);
/* 1157 */                   Arrow arrow = (Arrow)projectile;
/* 1158 */                   Bat b = (Bat)p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
/* 1159 */                   b.setCanPickupItems(false);
/* 1160 */                   b.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 100000));
/* 1161 */                   b.setLeashHolder((Entity)arrow);
/*      */                   
/* 1163 */                   Variables.damage.put(p.getUniqueId(), Boolean.valueOf(true));
/* 1164 */                   Variables.remove.put(p.getUniqueId(), new Entity[] { (Entity)b, (Entity)arrow });
/*      */                 } 
/* 1166 */                 return true;
/*      */               } 
/* 1168 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1172 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.MAGIC_WORKBENCH, "MAGIC_WORKBENCH", new ItemStack[] { null, null, null, null, null, null, new ItemStack(Material.BOOKSHELF), new ItemStack(Material.WORKBENCH), new ItemStack(Material.DISPENSER) }, new ItemStack[0], Material.WORKBENCH))
/*      */ 
/*      */       
/* 1175 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(final Player p, MultiBlock mb, final Block b)
/*      */             {
/* 1180 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("MAGIC_WORKBENCH");
/*      */               
/* 1182 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/* 1183 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/* 1184 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/* 1185 */                   Dispenser disp = null;
/*      */                   
/* 1187 */                   if (b.getRelative(1, 0, 0).getType() == Material.DISPENSER) { disp = (Dispenser)b.getRelative(1, 0, 0).getState(); }
/* 1188 */                   else if (b.getRelative(0, 0, 1).getType() == Material.DISPENSER) { disp = (Dispenser)b.getRelative(0, 0, 1).getState(); }
/* 1189 */                   else if (b.getRelative(-1, 0, 0).getType() == Material.DISPENSER) { disp = (Dispenser)b.getRelative(-1, 0, 0).getState(); }
/* 1190 */                   else if (b.getRelative(0, 0, -1).getType() == Material.DISPENSER) { disp = (Dispenser)b.getRelative(0, 0, -1).getState(); }
/*      */                   
/* 1192 */                   final Inventory inv = disp.getInventory();
/* 1193 */                   List<ItemStack[]> inputs = RecipeType.getRecipeInputList((SlimefunItem)machine);
/*      */                   
/* 1195 */                   for (int i = 0; i < inputs.size(); i++) {
/* 1196 */                     boolean craft = true;
/* 1197 */                     for (int j = 0; j < (inv.getContents()).length; j++) {
/* 1198 */                       if (!SlimefunManager.isItemSimiliar(inv.getContents()[j], ((ItemStack[])inputs.get(i))[j], true)) {
/* 1199 */                         if (SlimefunItem.getByItem(((ItemStack[])inputs.get(i))[j]) instanceof SlimefunBackpack) {
/* 1200 */                           if (!SlimefunManager.isItemSimiliar(inv.getContents()[j], ((ItemStack[])inputs.get(i))[j], false)) {
/* 1201 */                             craft = false;
/*      */                             
/*      */                             break;
/*      */                           } 
/*      */                         } else {
/* 1206 */                           craft = false;
/*      */                           
/*      */                           break;
/*      */                         } 
/*      */                       }
/*      */                     } 
/* 1212 */                     if (craft) {
/* 1213 */                       final ItemStack adding = RecipeType.getRecipeOutputList((SlimefunItem)machine, inputs.get(i)).clone();
/* 1214 */                       if (Slimefun.hasUnlocked(p, adding, true)) {
/* 1215 */                         Inventory inv2 = Bukkit.createInventory(null, 9, "test");
/* 1216 */                         for (int k = 0; k < (inv.getContents()).length; k++) {
/* 1217 */                           inv2.setItem(k, (inv.getContents()[k] != null) ? ((inv.getContents()[k].getAmount() > 1) ? (ItemStack)new CustomItem(inv.getContents()[k], inv.getContents()[k].getAmount() - 1) : null) : null);
/*      */                         }
/* 1219 */                         if (InvUtils.fits(inv2, adding)) {
/* 1220 */                           SlimefunItem sfItem = SlimefunItem.getByItem(adding);
/*      */                           
/* 1222 */                           if (sfItem instanceof SlimefunBackpack) {
/* 1223 */                             ItemStack backpack = null;
/*      */                             
/* 1225 */                             for (int n = 0; n < 9; n++) {
/* 1226 */                               if (inv.getContents()[n] != null && 
/* 1227 */                                 inv.getContents()[n].getType() != Material.AIR && 
/* 1228 */                                 SlimefunItem.getByItem(inv.getContents()[n]) instanceof SlimefunBackpack) {
/* 1229 */                                 backpack = inv.getContents()[n];
/*      */                                 
/*      */                                 break;
/*      */                               } 
/*      */                             } 
/*      */                             
/* 1235 */                             String id = "";
/* 1236 */                             int size = ((SlimefunBackpack)sfItem).size;
/*      */                             
/* 1238 */                             if (backpack != null) {
/* 1239 */                               for (String line : backpack.getItemMeta().getLore()) {
/* 1240 */                                 if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
/* 1241 */                                   id = line.replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
/* 1242 */                                   Config cfg = new Config(new File("data-storage/Slimefun/Players/" + id.split("#")[0] + ".yml"));
/* 1243 */                                   cfg.setValue("backpacks." + id.split("#")[1] + ".size", Integer.valueOf(size));
/* 1244 */                                   cfg.save();
/*      */                                   
/*      */                                   break;
/*      */                                 } 
/*      */                               } 
/*      */                             }
/* 1250 */                             if (id.equals("")) {
/* 1251 */                               for (int line = 0; line < adding.getItemMeta().getLore().size(); line++) {
/* 1252 */                                 if (((String)adding.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 1253 */                                   ItemMeta im = adding.getItemMeta();
/* 1254 */                                   List<String> lore = im.getLore();
/* 1255 */                                   lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, size)));
/* 1256 */                                   im.setLore(lore);
/* 1257 */                                   adding.setItemMeta(im);
/*      */                                   
/*      */                                   break;
/*      */                                 } 
/*      */                               } 
/*      */                             } else {
/* 1263 */                               for (int line = 0; line < adding.getItemMeta().getLore().size(); line++) {
/* 1264 */                                 if (((String)adding.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
/* 1265 */                                   ItemMeta im = adding.getItemMeta();
/* 1266 */                                   List<String> lore = im.getLore();
/* 1267 */                                   lore.set(line, ((String)lore.get(line)).replace("<ID>", id));
/* 1268 */                                   im.setLore(lore);
/* 1269 */                                   adding.setItemMeta(im);
/*      */                                   
/*      */                                   break;
/*      */                                 } 
/*      */                               } 
/*      */                             } 
/*      */                           } 
/* 1276 */                           for (int m = 0; m < 9; m++) {
/* 1277 */                             if (inv.getContents()[m] != null && 
/* 1278 */                               inv.getContents()[m].getType() != Material.AIR) {
/* 1279 */                               if (inv.getContents()[m].getAmount() > 1) { inv.setItem(m, (ItemStack)new CustomItem(inv.getContents()[m], inv.getContents()[m].getAmount() - 1)); }
/* 1280 */                               else { inv.setItem(m, null); }
/*      */                             
/*      */                             }
/*      */                           } 
/* 1284 */                           p.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.0F);
/* 1285 */                           p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
/* 1286 */                           p.getWorld().playEffect(b.getLocation(), Effect.ENDER_SIGNAL, 1);
/* 1287 */                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                               {
/*      */                                 public void run()
/*      */                                 {
/* 1291 */                                   p.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.0F);
/* 1292 */                                   p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
/* 1293 */                                   p.getWorld().playEffect(b.getLocation(), Effect.ENDER_SIGNAL, 1);
/* 1294 */                                   Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                       {
/*      */                                         public void run()
/*      */                                         {
/* 1298 */                                           p.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.0F);
/* 1299 */                                           p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
/* 1300 */                                           p.getWorld().playEffect(b.getLocation(), Effect.ENDER_SIGNAL, 1);
/* 1301 */                                           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                               {
/*      */                                                 public void run()
/*      */                                                 {
/* 1305 */                                                   p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
/* 1306 */                                                   p.getWorld().playEffect(b.getLocation(), Effect.ENDER_SIGNAL, 1);
/* 1307 */                                                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/* 1308 */                                                   inv.addItem(new ItemStack[] { this.this$2.this$1.val$adding });
/*      */                                                 }
/*      */                                               }20L);
/*      */                                         }
/*      */                                       }20L);
/*      */                                 }
/*      */                               }20L);
/*      */                         } else {
/* 1316 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                         } 
/* 1318 */                       }  return true;
/*      */                     } 
/*      */                   } 
/* 1321 */                   Messages.local.sendTranslation((CommandSender)p, "machines.pattern-not-found", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/* 1324 */                 return true;
/*      */               } 
/* 1326 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1330 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.STAFF_ELEMENTAL, "STAFF_ELEMENTAL", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, SlimefunItems.MAGICAL_BOOK_COVER, SlimefunItems.MAGIC_LUMP_3, null, new ItemStack(Material.STICK), SlimefunItems.MAGICAL_BOOK_COVER, SlimefunItems.MAGIC_LUMP_3, null, null
/*      */         
/* 1332 */         })).register(true);
/*      */     
/* 1334 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.STAFF_WIND, "STAFF_ELEMENTAL_WIND", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, new ItemStack(Material.FEATHER), SlimefunItems.ENDER_LUMP_3, null, SlimefunItems.STAFF_ELEMENTAL, new ItemStack(Material.FEATHER), SlimefunItems.STAFF_ELEMENTAL, null, null
/*      */         
/* 1336 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1340 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.STAFF_WIND, true)) {
/* 1341 */                 if (p.getFoodLevel() >= 2) {
/* 1342 */                   if (p.getInventory().getItemInMainHand().getType() != Material.SHEARS && p.getGameMode() != GameMode.CREATIVE) {
/* 1343 */                     FoodLevelChangeEvent event = new FoodLevelChangeEvent((HumanEntity)p, p.getFoodLevel() - 2);
/* 1344 */                     Bukkit.getPluginManager().callEvent((Event)event);
/* 1345 */                     p.setFoodLevel(event.getFoodLevel());
/*      */                   } 
/* 1347 */                   p.setVelocity(p.getEyeLocation().getDirection().multiply(4));
/* 1348 */                   p.getWorld().playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
/* 1349 */                   p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 1);
/* 1350 */                   p.setFallDistance(0.0F);
/*      */                 } else {
/*      */                   
/* 1353 */                   Messages.local.sendTranslation((CommandSender)p, "messages.hungry", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/* 1355 */                 return true;
/*      */               } 
/* 1357 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1361 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.STAFF_WATER, "STAFF_ELEMENTAL_WATER", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, new ItemStack(Material.WATER_LILY), SlimefunItems.MAGIC_LUMP_2, null, SlimefunItems.STAFF_ELEMENTAL, new ItemStack(Material.WATER_LILY), SlimefunItems.STAFF_ELEMENTAL, null, null
/*      */         
/* 1363 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1367 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.STAFF_WATER, true)) {
/* 1368 */                 p.setFireTicks(0);
/* 1369 */                 Messages.local.sendTranslation((CommandSender)p, "messages.fire-extinguish", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 1370 */                 return true;
/*      */               } 
/* 1372 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1376 */     (new MultiTool(SlimefunItems.DURALUMIN_MULTI_TOOL, "DURALUMIN_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item" }, new Object[] {
/*      */           
/* 1378 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK"
/* 1379 */         })).register(true);
/*      */     
/* 1381 */     (new MultiTool(SlimefunItems.SOLDER_MULTI_TOOL, "SOLDER_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SOLDER_INGOT, null, SlimefunItems.SOLDER_INGOT, SlimefunItems.SOLDER_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.SOLDER_INGOT, null, SlimefunItems.SOLDER_INGOT, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item" }, new Object[] {
/*      */           
/* 1383 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK"
/* 1384 */         })).register(true);
/*      */     
/* 1386 */     (new MultiTool(SlimefunItems.BILLON_MULTI_TOOL, "BILLON_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BILLON_INGOT, null, SlimefunItems.BILLON_INGOT, SlimefunItems.BILLON_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.BILLON_INGOT, null, SlimefunItems.BILLON_INGOT, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item" }, new Object[] {
/*      */           
/* 1388 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK"
/* 1389 */         })).register(true);
/*      */     
/* 1391 */     (new MultiTool(SlimefunItems.STEEL_MULTI_TOOL, "STEEL_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item" }, new Object[] {
/*      */           
/* 1393 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK"
/* 1394 */         })).register(true);
/*      */     
/* 1396 */     (new MultiTool(SlimefunItems.DAMASCUS_STEEL_MULTI_TOOL, "DAMASCUS_STEEL_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_INGOT, null, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.DAMASCUS_STEEL_INGOT, null, SlimefunItems.DAMASCUS_STEEL_INGOT, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item" }, new Object[] {
/*      */           
/* 1398 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK"
/* 1399 */         })).register(true);
/*      */     
/* 1401 */     (new MultiTool(SlimefunItems.REINFORCED_ALLOY_MULTI_TOOL, "REINFORCED_ALLOY_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.REINFORCED_ALLOY_INGOT, null, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.REINFORCED_ALLOY_INGOT, null, SlimefunItems.REINFORCED_ALLOY_INGOT, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item" }, new Object[] {
/*      */           
/* 1403 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK"
/* 1404 */         })).register(true);
/*      */     
/* 1406 */     (new MultiTool(SlimefunItems.CARBONADO_MULTI_TOOL, "CARBONADO_MULTI_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CARBONADO, null, SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, SlimefunItems.LARGE_CAPACITOR, SlimefunItems.CARBONADO, null, SlimefunItems.CARBONADO, null }, new String[] { "mode.0.enabled", "mode.0.name", "mode.0.item", "mode.1.enabled", "mode.1.name", "mode.1.item", "mode.2.enabled", "mode.2.name", "mode.2.item", "mode.3.enabled", "mode.3.name", "mode.3.item", "mode.4.enabled", "mode.4.name", "mode.4.item" }, new Object[] {
/*      */           
/* 1408 */           Boolean.valueOf(true), "Portable Crafter", "PORTABLE_CRAFTER", Boolean.valueOf(true), "Magic Eye of Ender", "MAGIC_EYE_OF_ENDER", Boolean.valueOf(true), "Wind Staff", "STAFF_ELEMENTAL_WIND", Boolean.valueOf(true), "Grappling Hook", "GRAPPLING_HOOK", Boolean.valueOf(true), "Gold Pan", "GOLD_PAN"
/* 1409 */         })).register(true);
/*      */     
/* 1411 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.ORE_WASHER, "ORE_WASHER", new ItemStack[] { null, new ItemStack(Material.DISPENSER), null, null, new ItemStack(Material.FENCE), null, null, new ItemStack(Material.CAULDRON_ITEM), null }, new ItemStack[] { SlimefunItems.SIFTED_ORE, SlimefunItems.IRON_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.GOLD_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.COPPER_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.TIN_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.ZINC_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.ALUMINUM_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.LEAD_DUST, SlimefunItems.SIFTED_ORE, SlimefunItems.SILVER_DUST }, Material.FENCE))
/*      */ 
/*      */ 
/*      */       
/* 1415 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/* 1420 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("ORE_WASHER");
/*      */               
/* 1422 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/* 1423 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/* 1424 */                   Slimefun.hasUnlocked(p, machine.getItem(), true)) {
/* 1425 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.UP).getState();
/* 1426 */                   Inventory inv = disp.getInventory();
/* 1427 */                   for (ItemStack current : inv.getContents()) {
/* 1428 */                     if (current != null) {
/* 1429 */                       if (SlimefunManager.isItemSimiliar(current, SlimefunItems.SIFTED_ORE, true)) {
/* 1430 */                         ItemStack adding = SlimefunItems.IRON_DUST;
/* 1431 */                         if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.GOLD_DUST; }
/* 1432 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.ALUMINUM_DUST; }
/* 1433 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.COPPER_DUST; }
/* 1434 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.ZINC_DUST; }
/* 1435 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.TIN_DUST; }
/* 1436 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.MAGNESIUM_DUST; }
/* 1437 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.LEAD_DUST; }
/* 1438 */                         else if (SlimefunStartup.chance(100, 25)) { adding = SlimefunItems.SILVER_DUST; }
/*      */                         
/* 1440 */                         if (inv.firstEmpty() != -1 || (SlimefunSetup.legacy_ore_washer && InvUtils.fits(inv, adding))) {
/* 1441 */                           ItemStack removing = current.clone();
/* 1442 */                           removing.setAmount(1);
/* 1443 */                           inv.removeItem(new ItemStack[] { removing });
/* 1444 */                           inv.addItem(new ItemStack[] { adding });
/* 1445 */                           p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/* 1446 */                           p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.WATER);
/* 1447 */                           if (InvUtils.fits(inv, SlimefunItems.STONE_CHUNK)) inv.addItem(new ItemStack[] { SlimefunItems.STONE_CHUNK }); 
/*      */                         } else {
/* 1449 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 1450 */                         }  return true;
/*      */                       } 
/* 1452 */                       if (SlimefunManager.isItemSimiliar(current, new ItemStack(Material.SAND, 4), false)) {
/* 1453 */                         ItemStack adding = SlimefunItems.SALT;
/* 1454 */                         if (InvUtils.fits(inv, adding)) {
/* 1455 */                           ItemStack removing = current.clone();
/* 1456 */                           removing.setAmount(4);
/* 1457 */                           inv.removeItem(new ItemStack[] { removing });
/* 1458 */                           inv.addItem(new ItemStack[] { adding });
/* 1459 */                           p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.WATER);
/* 1460 */                           p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                         } else {
/* 1462 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 1463 */                         }  return true;
/*      */                       } 
/* 1465 */                       if (SlimefunManager.isItemSimiliar(current, SlimefunItems.PULVERIZED_ORE, true)) {
/* 1466 */                         ItemStack adding = SlimefunItems.PURE_ORE_CLUSTER;
/* 1467 */                         if (InvUtils.fits(inv, adding)) {
/* 1468 */                           ItemStack removing = current.clone();
/* 1469 */                           removing.setAmount(1);
/* 1470 */                           inv.removeItem(new ItemStack[] { removing });
/* 1471 */                           inv.addItem(new ItemStack[] { adding });
/* 1472 */                           p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.WATER);
/* 1473 */                           p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                         } else {
/* 1475 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 1476 */                         }  return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 1480 */                   Messages.local.sendTranslation((CommandSender)p, "machines.unknown-material", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/* 1483 */                 return true;
/*      */               } 
/* 1485 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1489 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_24K, "GOLD_24K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_22K, null, null, null, null, null, null, null
/*      */         
/* 1491 */         })).register(true);
/*      */     
/* 1493 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_22K, "GOLD_22K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_20K, null, null, null, null, null, null, null
/*      */         
/* 1495 */         })).register(true);
/*      */     
/* 1497 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_20K, "GOLD_20K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_18K, null, null, null, null, null, null, null
/*      */         
/* 1499 */         })).register(true);
/*      */     
/* 1501 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_18K, "GOLD_18K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_16K, null, null, null, null, null, null, null
/*      */         
/* 1503 */         })).register(true);
/*      */     
/* 1505 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_16K, "GOLD_16K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_14K, null, null, null, null, null, null, null
/*      */         
/* 1507 */         })).register(true);
/*      */     
/* 1509 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_14K, "GOLD_14K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_12K, null, null, null, null, null, null, null
/*      */         
/* 1511 */         })).register(true);
/*      */     
/* 1513 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_12K, "GOLD_12K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_10K, null, null, null, null, null, null, null
/*      */         
/* 1515 */         })).register(true);
/*      */     
/* 1517 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_10K, "GOLD_10K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_8K, null, null, null, null, null, null, null
/*      */         
/* 1519 */         })).register(true);
/*      */     
/* 1521 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_8K, "GOLD_8K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_6K, null, null, null, null, null, null, null
/*      */         
/* 1523 */         })).register(true);
/*      */     
/* 1525 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_6K, "GOLD_6K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, SlimefunItems.GOLD_4K, null, null, null, null, null, null, null
/*      */         
/* 1527 */         })).register(true);
/*      */     
/* 1529 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.GOLD_4K, "GOLD_4K", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.GOLD_DUST, null, null, null, null, null, null, null, null
/*      */         
/* 1531 */         })).register(true);
/*      */     
/* 1533 */     (new SlimefunItem(Categories.MISC, SlimefunItems.STONE_CHUNK, "STONE_CHUNK", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1535 */         })).register(true);
/*      */     
/* 1537 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.SILICON, "SILICON", RecipeType.SMELTERY, new ItemStack[] { new ItemStack(Material.QUARTZ_BLOCK), null, null, null, null, null, null, null, null
/*      */         
/* 1539 */         })).register(true);
/*      */     
/* 1541 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.SOLAR_PANEL, "SOLAR_PANEL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), SlimefunItems.SILICON, SlimefunItems.SILICON, SlimefunItems.SILICON, SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON
/*      */         
/* 1543 */         })).register(true);
/*      */     
/* 1545 */     (new SolarHelmet(Categories.TECH, SlimefunItems.SOLAR_HELMET, "SOLAR_HELMET", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.SOLAR_PANEL, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, null, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.MEDIUM_CAPACITOR, null, SlimefunItems.MEDIUM_CAPACITOR }, new String[] { "charge-amount" }, (Object[])new Double[] {
/*      */           
/* 1547 */           Double.valueOf(0.1D)
/* 1548 */         })).register(true);
/*      */     
/* 1550 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.LAVA_CRYSTAL, "LAVA_CRYSTAL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.BLAZE_POWDER), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.BLAZE_POWDER), SlimefunItems.RUNE_FIRE, new ItemStack(Material.BLAZE_POWDER), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.BLAZE_POWDER), SlimefunItems.MAGIC_LUMP_1
/*      */         
/* 1552 */         })).register(true);
/*      */     
/* 1554 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.STAFF_FIRE, "STAFF_ELEMENTAL_FIRE", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, null, SlimefunItems.LAVA_CRYSTAL, null, SlimefunItems.STAFF_ELEMENTAL, null, SlimefunItems.STAFF_ELEMENTAL, null, null
/*      */         
/* 1556 */         })).register(true);
/*      */     
/* 1558 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.AUTO_SMELT_PICKAXE, "SMELTERS_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LAVA_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, null, SlimefunItems.FERROSILICON, null, null, SlimefunItems.FERROSILICON, null
/*      */         
/* 1560 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockBreakHandler()
/*      */           {
/*      */             public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops)
/*      */             {
/* 1564 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.AUTO_SMELT_PICKAXE, true)) {
/* 1565 */                 if (e.getBlock().getType().equals(Material.SKULL)) return true;
/*      */                 
/* 1567 */                 int j = -1;
/* 1568 */                 List<ItemStack> dropsList = (List<ItemStack>)e.getBlock().getDrops();
/* 1569 */                 for (int i = 0; i < dropsList.size(); i++) {
/* 1570 */                   if (dropsList.get(i) != null) {
/* 1571 */                     j++;
/* 1572 */                     drops.add(e.getBlock().getType().toString().endsWith("_ORE") ? (ItemStack)new CustomItem(dropsList.get(i), fortune) : dropsList.get(i));
/* 1573 */                     if (RecipeCalculator.getSmeltedOutput(((ItemStack)drops.get(i)).getType()) != null) {
/* 1574 */                       e.getBlock().getWorld().playEffect(e.getBlock().getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
/* 1575 */                       drops.set(j, new CustomItem(RecipeCalculator.getSmeltedOutput(((ItemStack)drops.get(i)).getType()), ((ItemStack)drops.get(i)).getAmount()));
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */                 
/* 1580 */                 return true;
/*      */               } 
/* 1582 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1586 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.TALISMAN, "COMMON_TALISMAN", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.MAGIC_LUMP_2, SlimefunItems.GOLD_8K, SlimefunItems.MAGIC_LUMP_2, null, new ItemStack(Material.EMERALD), null, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.GOLD_8K, SlimefunItems.MAGIC_LUMP_2 }, new String[] { "recipe-requires-nether-stars" }, (Object[])new Boolean[] {
/*      */           
/* 1588 */           Boolean.valueOf(false)
/* 1589 */         })).register(true);
/*      */     
/* 1591 */     (new Talisman(SlimefunItems.TALISMAN_ANVIL, "ANVIL_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.ANVIL), SlimefunItems.TALISMAN, new ItemStack(Material.ANVIL), SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, true, false, "anvil", new PotionEffect[0]))
/*      */ 
/*      */       
/* 1594 */       .register(true);
/*      */     
/* 1596 */     (new Talisman(SlimefunItems.TALISMAN_MINER, "MINER_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.TALISMAN, SlimefunItems.SIFTED_ORE, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, false, false, "miner", 20, new PotionEffect[0]))
/*      */ 
/*      */       
/* 1599 */       .register(true);
/*      */     
/* 1601 */     (new Talisman(SlimefunItems.TALISMAN_HUNTER, "HUNTER_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.TALISMAN, SlimefunItems.MONSTER_JERKY, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, false, false, "hunter", 20, new PotionEffect[0]))
/*      */ 
/*      */       
/* 1604 */       .register(true);
/*      */     
/* 1606 */     (new Talisman(SlimefunItems.TALISMAN_LAVA, "LAVA_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.TALISMAN, new ItemStack(Material.LAVA_BUCKET), SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, true, true, "lava", new PotionEffect[] { new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 4)
/*      */ 
/*      */         
/* 1609 */         })).register(true);
/*      */     
/* 1611 */     (new Talisman(SlimefunItems.TALISMAN_WATER, "WATER_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.WATER_BUCKET), SlimefunItems.TALISMAN, new ItemStack(Material.FISHING_ROD), SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, true, true, "water", new PotionEffect[] { new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 4)
/*      */ 
/*      */         
/* 1614 */         })).register(true);
/*      */     
/* 1616 */     (new Talisman(SlimefunItems.TALISMAN_ANGEL, "ANGEL_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.FEATHER), SlimefunItems.TALISMAN, new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, false, true, "angel", 75, new PotionEffect[0]))
/*      */ 
/*      */       
/* 1619 */       .register(true);
/*      */     
/* 1621 */     (new Talisman(SlimefunItems.TALISMAN_FIRE, "FIRE_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.TALISMAN, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, true, true, "fire", new PotionEffect[] { new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 4)
/*      */ 
/*      */         
/* 1624 */         })).register(true);
/*      */     
/* 1626 */     (new Talisman(SlimefunItems.TALISMAN_MAGICIAN, "MAGICIAN_TALISMAN", new ItemStack[] { SlimefunItems.ENDER_LUMP_3, null, SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.ENCHANTMENT_TABLE), SlimefunItems.TALISMAN, new ItemStack(Material.ENCHANTMENT_TABLE), SlimefunItems.ENDER_LUMP_3, null, SlimefunItems.ENDER_LUMP_3 }, false, false, "magician", 80, new PotionEffect[0]))
/*      */ 
/*      */       
/* 1629 */       .register(true);
/*      */     
/* 1631 */     for (Enchantment e : Enchantment.values()) {
/* 1632 */       for (int i = 1; i <= e.getMaxLevel(); i++) {
/* 1633 */         Slimefun.setItemVariable("MAGICIAN_TALISMAN", "allow-enchantments." + e.getName() + ".level." + i, Boolean.valueOf(true));
/*      */       }
/*      */     } 
/*      */     
/* 1637 */     (new Talisman(SlimefunItems.TALISMAN_TRAVELLER, "TRAVELLER_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.STAFF_WIND, SlimefunItems.TALISMAN_ANGEL, SlimefunItems.STAFF_WIND, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, false, false, "traveller", 60, new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, 3600, 2)
/*      */ 
/*      */         
/* 1640 */         })).register(true);
/*      */     
/* 1642 */     (new Talisman(SlimefunItems.TALISMAN_WARRIOR, "WARRIOR_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.TALISMAN, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, true, true, "warrior", new PotionEffect[] { new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 2)
/*      */ 
/*      */         
/* 1645 */         })).register(true);
/*      */     
/* 1647 */     (new Talisman(SlimefunItems.TALISMAN_KNIGHT, "KNIGHT_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.GILDED_IRON, SlimefunItems.TALISMAN_WARRIOR, SlimefunItems.GILDED_IRON, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, "knight", 30, new PotionEffect[] { new PotionEffect(PotionEffectType.REGENERATION, 100, 3)
/*      */ 
/*      */         
/* 1650 */         })).register(true);
/*      */     
/* 1652 */     (new Alloy(SlimefunItems.GILDED_IRON, "GILDED_IRON", new ItemStack[] { SlimefunItems.GOLD_24K, SlimefunItems.IRON_DUST, null, null, null, null, null, null, null
/*      */         
/* 1654 */         })).register(true);
/*      */     
/* 1656 */     (new ReplacingAlloy(SlimefunItems.SYNTHETIC_EMERALD, "SYNTHETIC_EMERALD", new ItemStack[] { SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.ALUMINUM_DUST, SlimefunItems.ALUMINUM_INGOT, new ItemStack(Material.THIN_GLASS), null, null, null, null, null
/*      */         
/* 1658 */         })).register(true);
/*      */     
/* 1660 */     SlimefunManager.registerArmorSet(SlimefunItems.CHAIN, new ItemStack[] { new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.CHAINMAIL_BOOTS) }, "CHAIN", true, true);
/*      */     
/* 1662 */     (new Talisman(SlimefunItems.TALISMAN_WHIRLWIND, "WHIRLWIND_TALISMAN", new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.STAFF_WIND, SlimefunItems.TALISMAN_TRAVELLER, SlimefunItems.STAFF_WIND, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3 }, false, true, "whirlwind", 60, new PotionEffect[0]))
/*      */ 
/*      */       
/* 1665 */       .register(true);
/*      */     
/* 1667 */     (new Talisman(SlimefunItems.TALISMAN_WIZARD, "WIZARD_TALISMAN", new ItemStack[] { SlimefunItems.ENDER_LUMP_3, null, SlimefunItems.ENDER_LUMP_3, SlimefunItems.MAGIC_EYE_OF_ENDER, SlimefunItems.TALISMAN_MAGICIAN, SlimefunItems.MAGIC_EYE_OF_ENDER, SlimefunItems.ENDER_LUMP_3, null, SlimefunItems.ENDER_LUMP_3 }, false, false, "wizard", 60, new PotionEffect[0]))
/*      */ 
/*      */       
/* 1670 */       .register(true);
/*      */     
/* 1672 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.LUMBER_AXE, "LUMBER_AXE", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_DIAMOND, null, SlimefunItems.SYNTHETIC_EMERALD, SlimefunItems.GILDED_IRON, null, null, SlimefunItems.GILDED_IRON, null
/*      */         
/* 1674 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockBreakHandler()
/*      */           {
/*      */             public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops)
/*      */             {
/* 1678 */               if (SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInMainHand(), SlimefunItems.LUMBER_AXE, true)) {
/* 1679 */                 if (e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.LOG_2) {
/* 1680 */                   List<Location> logs = new ArrayList<>();
/* 1681 */                   TreeCalculator.getTree(e.getBlock().getLocation(), e.getBlock().getLocation(), logs);
/*      */                   
/* 1683 */                   if (logs.contains(e.getBlock())) logs.remove(e.getBlock()); 
/* 1684 */                   for (Location b : logs) {
/* 1685 */                     if (CSCoreLib.getLib().getProtectionManager().canBuild(e.getPlayer().getUniqueId(), b.getBlock())) {
/* 1686 */                       b.getWorld().playEffect(b, Effect.STEP_SOUND, b.getBlock().getType());
/* 1687 */                       for (ItemStack drop : b.getBlock().getDrops()) {
/* 1688 */                         b.getWorld().dropItemNaturally(b, drop);
/*      */                       }
/* 1690 */                       b.getBlock().setType(Material.AIR);
/*      */                     } 
/*      */                   } 
/*      */                 } 
/* 1694 */                 return true;
/*      */               } 
/* 1696 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1700 */     (new SlimefunItem(Categories.MISC, SlimefunItems.SALT, "SALT", RecipeType.ORE_WASHER, new ItemStack[] { null, null, null, null, new ItemStack(Material.SAND, 4), null, null, null, null
/*      */         
/* 1702 */         })).register(true);
/*      */     
/* 1704 */     (new SlimefunItem(Categories.MISC, SlimefunItems.HEAVY_CREAM, "HEAVY_CREAM", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.MILK_BUCKET), null, null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.HEAVY_CREAM, 2)))
/*      */       
/* 1706 */       .register(true);
/*      */     
/* 1708 */     (new SlimefunItem(Categories.MISC, SlimefunItems.CHEESE, "CHEESE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.MILK_BUCKET), SlimefunItems.SALT, null, null, null, null, null, null, null
/*      */         
/* 1710 */         })).register(true);
/*      */     
/* 1712 */     (new SlimefunItem(Categories.MISC, SlimefunItems.BUTTER, "BUTTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HEAVY_CREAM, SlimefunItems.SALT, null, null, null, null, null, null, null
/*      */         
/* 1714 */         })).register(true);
/*      */     
/* 1716 */     SlimefunManager.registerArmorSet(SlimefunItems.GILDED_IRON, new ItemStack[] { SlimefunItems.GILDED_IRON_HELMET, SlimefunItems.GILDED_IRON_CHESTPLATE, SlimefunItems.GILDED_IRON_LEGGINGS, SlimefunItems.GILDED_IRON_BOOTS }, "GILDED_IRON", true, false);
/*      */     
/* 1718 */     (new SlimefunArmorPiece(Categories.ARMOR, SlimefunItems.SCUBA_HELMET, "SCUBA_HELMET", RecipeType.ARMOR_FORGE, new ItemStack[] { (new MaterialData(Material.WOOL, (byte)1))
/* 1719 */           .toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), new ItemStack(Material.THIN_GLASS), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), null, null, null }new PotionEffect[] { new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 1)
/*      */         
/* 1721 */         })).register(true);
/*      */     
/* 1723 */     (new SlimefunArmorPiece(Categories.ARMOR, SlimefunItems.HAZMATSUIT_CHESTPLATE, "HAZMAT_CHESTPLATE", RecipeType.ARMOR_FORGE, new ItemStack[] { (new MaterialData(Material.WOOL, (byte)1))
/* 1724 */           .toItemStack(1), null, (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1) }new PotionEffect[] { new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)
/*      */         
/* 1726 */         })).register(true);
/*      */     
/* 1728 */     (new SlimefunItem(Categories.ARMOR, SlimefunItems.HAZMATSUIT_LEGGINGS, "HAZMAT_LEGGINGS", RecipeType.ARMOR_FORGE, new ItemStack[] { (new MaterialData(Material.WOOL, (byte)15))
/* 1729 */           .toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), null, (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1), null, (new MaterialData(Material.WOOL, (byte)1)).toItemStack(1)
/* 1730 */         })).register(true);
/*      */     
/* 1732 */     (new SlimefunItem(Categories.ARMOR, SlimefunItems.RUBBER_BOOTS, "RUBBER_BOOTS", RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, (new MaterialData(Material.WOOL, (byte)15))
/* 1733 */           .toItemStack(1), null, (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1), null, (new MaterialData(Material.WOOL, (byte)15)).toItemStack(1)
/* 1734 */         })).register(true);
/*      */     
/* 1736 */     (new SlimefunItem(Categories.MISC, SlimefunItems.CRUSHED_ORE, "CRUSHED_ORE", RecipeType.ORE_CRUSHER, new ItemStack[] { SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1738 */         })).register(true);
/*      */     
/* 1740 */     (new SlimefunItem(Categories.MISC, SlimefunItems.PULVERIZED_ORE, "PULVERIZED_ORE", RecipeType.ORE_CRUSHER, new ItemStack[] { SlimefunItems.CRUSHED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1742 */         })).register(true);
/*      */     
/* 1744 */     (new SlimefunItem(Categories.MISC, SlimefunItems.PURE_ORE_CLUSTER, "PURE_ORE_CLUSTER", RecipeType.ORE_WASHER, new ItemStack[] { SlimefunItems.PULVERIZED_ORE, null, null, null, null, null, null, null, null
/*      */         
/* 1746 */         })).register(true);
/*      */     
/* 1748 */     (new SlimefunItem(Categories.MISC, SlimefunItems.TINY_URANIUM, "TINY_URANIUM", RecipeType.ORE_CRUSHER, new ItemStack[] { SlimefunItems.PURE_ORE_CLUSTER, null, null, null, null, null, null, null, null
/*      */         
/* 1750 */         })).register(true);
/*      */     
/* 1752 */     (new SlimefunItem(Categories.MISC, SlimefunItems.SMALL_URANIUM, "SMALL_URANIUM", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM, SlimefunItems.TINY_URANIUM
/*      */         
/* 1754 */         })).register(true);
/*      */     
/* 1756 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.URANIUM, "URANIUM", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SMALL_URANIUM, SlimefunItems.SMALL_URANIUM, null, SlimefunItems.SMALL_URANIUM, SlimefunItems.SMALL_URANIUM, null, null, null, null
/*      */         
/* 1758 */         })).register(true);
/*      */     
/* 1760 */     (new Alloy(SlimefunItems.REDSTONE_ALLOY, "REDSTONE_ALLOY", new ItemStack[] { new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.FERROSILICON, SlimefunItems.HARDENED_METAL_INGOT, null, null, null, null, null
/*      */         
/* 1762 */         })).register(true);
/*      */     
/* 1764 */     SlimefunManager.registerArmorSet(SlimefunItems.GOLD_12K, new ItemStack[] { SlimefunItems.GOLD_HELMET, SlimefunItems.GOLD_CHESTPLATE, SlimefunItems.GOLD_LEGGINGS, SlimefunItems.GOLD_BOOTS }, "GOLD_12K", true, false);
/*      */     
/* 1766 */     (new SlimefunItem(Categories.MISC, SlimefunItems.CLOTH, "CLOTH", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.WOOL), null, null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CLOTH, 8)))
/*      */       
/* 1768 */       .register(true);
/*      */     
/* 1770 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.RAG, "RAG", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH, new ItemStack(Material.STRING), null, new ItemStack(Material.STRING), SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH
/*      */         
/* 1772 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1776 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.RAG, true)) {
/* 1777 */                 PlayerInventory.consumeItemInHand(p);
/* 1778 */                 p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.WOOL);
/* 1779 */                 p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));
/* 1780 */                 p.setFireTicks(0);
/* 1781 */                 return true;
/*      */               } 
/* 1783 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1787 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.BANDAGE, "BANDAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.RAG, new ItemStack(Material.STRING), SlimefunItems.RAG, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.BANDAGE, 4), new String[] { "enable-bleeding" }, (Object[])new Boolean[] {
/*      */           
/* 1789 */           Boolean.valueOf(true)
/* 1790 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1794 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BANDAGE, true)) {
/* 1795 */                 PlayerInventory.consumeItemInHand(p);
/* 1796 */                 p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.WOOL);
/* 1797 */                 p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
/* 1798 */                 p.setFireTicks(0);
/* 1799 */                 return true;
/*      */               } 
/* 1801 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1805 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.SPLINT, "SPLINT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.STICK), new ItemStack(Material.STICK), new ItemStack(Material.STICK), null, new ItemStack(Material.IRON_INGOT), null }, (ItemStack)new CustomItem(SlimefunItems.SPLINT, 4), new String[] { "enable-broken-legs" }, (Object[])new Boolean[] {
/*      */           
/* 1807 */           Boolean.valueOf(true)
/* 1808 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1812 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.SPLINT, true)) {
/* 1813 */                 PlayerInventory.consumeItemInHand(p);
/* 1814 */                 p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HURT, 1.0F, 1.0F);
/* 1815 */                 p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));
/* 1816 */                 return true;
/*      */               } 
/* 1818 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1822 */     (new SlimefunItem(Categories.MISC, SlimefunItems.CAN, "TIN_CAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, null, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT }, (ItemStack)new CustomItem(SlimefunItems.CAN, 4)))
/*      */       
/* 1824 */       .register(true);
/*      */     
/* 1826 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.VITAMINS, "VITAMINS", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.APPLE), new ItemStack(Material.RED_MUSHROOM), new ItemStack(Material.SUGAR), null, null, null, null, null
/*      */         
/* 1828 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 1832 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.VITAMINS, true)) {
/* 1833 */                 PlayerInventory.consumeItemInHand(p);
/* 1834 */                 p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
/* 1835 */                 if (p.hasPotionEffect(PotionEffectType.POISON)) p.removePotionEffect(PotionEffectType.POISON); 
/* 1836 */                 if (p.hasPotionEffect(PotionEffectType.WITHER)) p.removePotionEffect(PotionEffectType.WITHER); 
/* 1837 */                 if (p.hasPotionEffect(PotionEffectType.SLOW)) p.removePotionEffect(PotionEffectType.SLOW); 
/* 1838 */                 if (p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) p.removePotionEffect(PotionEffectType.SLOW_DIGGING); 
/* 1839 */                 if (p.hasPotionEffect(PotionEffectType.WEAKNESS)) p.removePotionEffect(PotionEffectType.WEAKNESS); 
/* 1840 */                 if (p.hasPotionEffect(PotionEffectType.CONFUSION)) p.removePotionEffect(PotionEffectType.CONFUSION); 
/* 1841 */                 if (p.hasPotionEffect(PotionEffectType.BLINDNESS)) p.removePotionEffect(PotionEffectType.BLINDNESS); 
/* 1842 */                 p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2));
/* 1843 */                 p.setFireTicks(0);
/* 1844 */                 e.setCancelled(true);
/* 1845 */                 return true;
/*      */               } 
/* 1847 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1851 */     (new SlimefunItem(Categories.PORTABLE, SlimefunItems.MEDICINE, "MEDICINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.VITAMINS, new ItemStack(Material.GLASS_BOTTLE), SlimefunItems.HEAVY_CREAM, null, null, null, null, null, null
/*      */         
/* 1853 */         })).register(true);
/*      */     
/* 1855 */     (new SlimefunArmorPiece(Categories.TECH, SlimefunItems.NIGHT_VISION_GOGGLES, "NIGHT_VISION_GOGGLES", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), (new MaterialData(Material.STAINED_GLASS_PANE, (byte)5))
/* 1856 */           .toItemStack(1), new ItemStack(Material.COAL_BLOCK), (new MaterialData(Material.STAINED_GLASS_PANE, (byte)5)).toItemStack(1), new ItemStack(Material.COAL_BLOCK), null, new ItemStack(Material.COAL_BLOCK) }new PotionEffect[] { new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 20)
/*      */         
/* 1858 */         })).register(true);
/*      */     
/* 1860 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.PICKAXE_OF_CONTAINMENT, "PICKAXE_OF_CONTAINMENT", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON, null, SlimefunItems.GILDED_IRON, null, null, SlimefunItems.GILDED_IRON, null
/*      */         
/* 1862 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockBreakHandler()
/*      */           {
/*      */             public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops)
/*      */             {
/* 1866 */               if (SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInMainHand(), SlimefunItems.PICKAXE_OF_CONTAINMENT, true)) {
/* 1867 */                 if (e.getBlock().getType() != Material.MOB_SPAWNER) return true; 
/* 1868 */                 ItemStack spawner = SlimefunItems.BROKEN_SPAWNER.clone();
/* 1869 */                 ItemMeta im = spawner.getItemMeta();
/* 1870 */                 List<String> lore = im.getLore();
/* 1871 */                 for (int i = 0; i < lore.size(); i++) {
/* 1872 */                   if (((String)lore.get(i)).contains("<Type>")) lore.set(i, ((String)lore.get(i)).replace("<Type>", StringUtils.format(((CreatureSpawner)e.getBlock().getState()).getSpawnedType().toString()))); 
/*      */                 } 
/* 1874 */                 im.setLore(lore);
/* 1875 */                 spawner.setItemMeta(im);
/* 1876 */                 e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), spawner);
/* 1877 */                 e.setExpToDrop(0);
/* 1878 */                 return true;
/*      */               } 
/* 1880 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1884 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.HERCULES_PICKAXE, "HERCULES_PICKAXE", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, null, SlimefunItems.FERROSILICON, null, null, SlimefunItems.FERROSILICON, null
/*      */         
/* 1886 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockBreakHandler()
/*      */           {
/*      */             public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops)
/*      */             {
/* 1890 */               if (SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInMainHand(), SlimefunItems.HERCULES_PICKAXE, true) && e.getBlock().getType().toString().endsWith("_ORE")) {
/* 1891 */                 if (e.getBlock().getType() == Material.IRON_ORE) { drops.add(new CustomItem(SlimefunItems.IRON_DUST, 2)); }
/* 1892 */                 else if (e.getBlock().getType() == Material.GOLD_ORE) { drops.add(new CustomItem(SlimefunItems.GOLD_DUST, 2)); }
/*      */                 else
/* 1894 */                 { for (ItemStack drop : e.getBlock().getDrops()) {
/* 1895 */                     drops.add(new CustomItem(drop, 2));
/*      */                   } }
/*      */                 
/* 1898 */                 return true;
/*      */               } 
/* 1900 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1904 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.SAW_MILL, "SAW_MILL", new ItemStack[] { null, null, null, new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG), new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG), new ItemStack(Material.WORKBENCH), new ItemStack(Material.LOG) }, new ItemStack[0], Material.WORKBENCH))
/*      */ 
/*      */       
/* 1907 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/* 1911 */               if (mb.isMultiBlock(SlimefunItem.getByID("SAW_MILL"))) {
/* 1912 */                 if (CSCoreLib.getLib().getProtectionManager().canBuild(p.getUniqueId(), b.getRelative(BlockFace.UP), true) && 
/* 1913 */                   Slimefun.hasUnlocked(p, SlimefunItems.SAW_MILL, true) && (
/* 1914 */                   b.getRelative(BlockFace.UP).getType() == Material.LOG || b.getRelative(BlockFace.UP).getType() == Material.LOG_2)) {
/* 1915 */                   Block log = b.getRelative(BlockFace.UP);
/* 1916 */                   if (!BlockStorage.hasBlockInfo(log)) {
/* 1917 */                     CustomItem customItem = (log.getType() == Material.LOG) ? new CustomItem(Material.WOOD, log.getData() % 4, 8) : new CustomItem(Material.WOOD, log.getData() % 2 + 4, 8);
/* 1918 */                     log.getWorld().dropItemNaturally(log.getLocation(), (ItemStack)customItem);
/* 1919 */                     log.getWorld().playEffect(log.getLocation(), Effect.STEP_SOUND, log.getType());
/* 1920 */                     log.setType(Material.AIR);
/*      */                   } 
/*      */                 } 
/*      */ 
/*      */                 
/* 1925 */                 return true;
/*      */               } 
/* 1927 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 1931 */     (new SlimefunMachine(Categories.MACHINES_1, (ItemStack)new CustomItem(Material.FIRE, "&4Phantom Item", 0), "SAW_MILL2", new ItemStack[] { null, null, null, new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG_2), new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG), new ItemStack(Material.WORKBENCH), new ItemStack(Material.LOG) }, new ItemStack[0], Material.WORKBENCH, true))
/*      */ 
/*      */       
/* 1934 */       .register(true);
/*      */     
/* 1936 */     (new SlimefunMachine(Categories.MACHINES_1, (ItemStack)new CustomItem(Material.FIRE, "&4Phantom Item", 0), "SAW_MILL3", new ItemStack[] { null, null, null, new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG), new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG_2), new ItemStack(Material.WORKBENCH), new ItemStack(Material.LOG_2) }, new ItemStack[0], Material.WORKBENCH, true))
/*      */ 
/*      */       
/* 1939 */       .register(true);
/*      */     
/* 1941 */     (new SlimefunMachine(Categories.MACHINES_1, (ItemStack)new CustomItem(Material.FIRE, "&4Phantom Item", 0), "SAW_MILL4", new ItemStack[] { null, null, null, new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG_2), new ItemStack(Material.IRON_FENCE), new ItemStack(Material.LOG_2), new ItemStack(Material.WORKBENCH), new ItemStack(Material.LOG_2) }, new ItemStack[0], Material.WORKBENCH, true))
/*      */ 
/*      */       
/* 1944 */       .register(true);
/*      */     
/* 1946 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_HELMET_STEEL, "SLIME_STEEL_HELMET", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.SLIME_BALL), SlimefunItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), null, null, null
/*      */         
/* 1948 */         })).register(true);
/*      */     
/* 1950 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_CHESTPLATE_STEEL, "SLIME_STEEL_CHESTPLATE", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), SlimefunItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL)
/*      */         
/* 1952 */         })).register(true);
/*      */     
/* 1954 */     (new SlimefunArmorPiece(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_LEGGINGS_STEEL, "SLIME_STEEL_LEGGINGS", RecipeType.ARMOR_FORGE, new ItemStack[] { new ItemStack(Material.SLIME_BALL), SlimefunItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL) }, new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, 300, 2)
/*      */ 
/*      */         
/* 1957 */         })).register(true);
/*      */     
/* 1959 */     (new SlimefunArmorPiece(Categories.MAGIC_ARMOR, SlimefunItems.SLIME_BOOTS_STEEL, "SLIME_STEEL_BOOTS", RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), SlimefunItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL) }, new PotionEffect[] { new PotionEffect(PotionEffectType.JUMP, 300, 5)
/*      */ 
/*      */         
/* 1962 */         })).register(true);
/*      */     
/* 1964 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.BLADE_OF_VAMPIRES, "BLADE_OF_VAMPIRES", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, (new MaterialData(Material.SKULL_ITEM, (byte)1))
/* 1965 */           .toItemStack(1), null, null, (new MaterialData(Material.SKULL_ITEM, (byte)1)).toItemStack(1), null, null, new ItemStack(Material.BLAZE_ROD), null
/* 1966 */         })).register(true);
/*      */     
/* 1968 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.DIGITAL_MINER, "DIGITAL_MINER", new ItemStack[] { SlimefunItems.SOLAR_PANEL, new ItemStack(Material.CHEST), SlimefunItems.SOLAR_PANEL, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.DISPENSER), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.HOPPER), new ItemStack(Material.IRON_BLOCK) }, new ItemStack[0], Material.DISPENSER))
/*      */ 
/*      */       
/* 1971 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             public boolean onInteract(final Player p, MultiBlock mb, final Block b)
/*      */             {
/* 1975 */               if (mb.isMultiBlock(SlimefunItem.getByID("DIGITAL_MINER"))) {
/* 1976 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/* 1977 */                   Slimefun.hasUnlocked(p, SlimefunItems.DIGITAL_MINER, true)) {
/* 1978 */                   Block chestBlock = b.getRelative(BlockFace.UP);
/*      */                   
/* 1980 */                   if ((!BlockStorage.check(chestBlock.getRelative(BlockFace.WEST), "SOLAR_PANEL") || !BlockStorage.check(chestBlock.getRelative(BlockFace.EAST), "SOLAR_PANEL")) && (
/* 1981 */                     !BlockStorage.check(chestBlock.getRelative(BlockFace.NORTH), "SOLAR_PANEL") || !BlockStorage.check(chestBlock.getRelative(BlockFace.SOUTH), "SOLAR_PANEL"))) {
/* 1982 */                     return false;
/*      */                   }
/*      */                   
/* 1985 */                   Chest chest = (Chest)chestBlock.getState();
/* 1986 */                   final Inventory inv = chest.getInventory();
/* 1987 */                   List<Location> ores = new ArrayList<>();
/* 1988 */                   for (int x = b.getX() - 4; x < b.getX() + 4; x++) {
/* 1989 */                     for (int z = b.getZ() - 4; z < b.getZ() + 4; z++) {
/* 1990 */                       for (int y = b.getY(); y > 0; y--) {
/* 1991 */                         if (b.getWorld().getBlockAt(x, y, z).getType().toString().endsWith("_ORE")) {
/* 1992 */                           ores.add(b.getWorld().getBlockAt(x, y, z).getLocation());
/*      */                         }
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 1997 */                   if (!ores.isEmpty())
/* 1998 */                   { final Material ore = ((Location)ores.get(0)).getBlock().getType();
/* 1999 */                     final ItemStack adding = new ItemStack(ore);
/* 2000 */                     ((Location)ores.get(0)).getBlock().setType(Material.AIR);
/* 2001 */                     ores.clear();
/* 2002 */                     if (InvUtils.fits(inv, adding)) {
/* 2003 */                       b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2004 */                       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                           {
/*      */                             public void run()
/*      */                             {
/* 2008 */                               b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2009 */                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                   {
/*      */                                     public void run()
/*      */                                     {
/* 2013 */                                       b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2014 */                                       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                           {
/*      */                                             public void run()
/*      */                                             {
/* 2018 */                                               b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2019 */                                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                   {
/*      */                                                     public void run()
/*      */                                                     {
/* 2023 */                                                       b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2024 */                                                       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                           {
/*      */                                                             public void run()
/*      */                                                             {
/* 2028 */                                                               b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2029 */                                                               p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/* 2030 */                                                               inv.addItem(new ItemStack[] { this.this$4.this$3.this$2.this$1.val$adding });
/*      */                                                             }
/*      */                                                           }20L);
/*      */                                                     }
/*      */                                                   }20L);
/*      */                                             }
/*      */                                           }20L);
/*      */                                     }
/*      */                                   }20L);
/*      */                             }
/*      */                           }20L);
/*      */                     } else {
/* 2042 */                       Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                     }  }
/* 2044 */                   else { Messages.local.sendTranslation((CommandSender)p, "miner.no-ores", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*      */                 
/*      */                 } 
/* 2047 */                 return true;
/*      */               } 
/* 2049 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2053 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.ADVANCED_DIGITAL_MINER, "ADVANCED_DIGITAL_MINER", new ItemStack[] { SlimefunItems.SOLAR_PANEL, new ItemStack(Material.CHEST), SlimefunItems.SOLAR_PANEL, SlimefunItems.GOLD_24K_BLOCK, new ItemStack(Material.DISPENSER), SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.GOLD_24K_BLOCK, new ItemStack(Material.HOPPER), SlimefunItems.GOLD_24K_BLOCK }, new ItemStack[0], Material.DISPENSER))
/*      */ 
/*      */       
/* 2056 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             public boolean onInteract(final Player p, MultiBlock mb, final Block b)
/*      */             {
/* 2060 */               if (mb.isMultiBlock(SlimefunItem.getByID("ADVANCED_DIGITAL_MINER"))) {
/* 2061 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/* 2062 */                   Slimefun.hasUnlocked(p, SlimefunItems.ADVANCED_DIGITAL_MINER, true)) {
/* 2063 */                   Block chestBlock = b.getRelative(BlockFace.UP);
/*      */                   
/* 2065 */                   if ((!BlockStorage.check(chestBlock.getRelative(BlockFace.WEST), "SOLAR_PANEL") || !BlockStorage.check(chestBlock.getRelative(BlockFace.EAST), "SOLAR_PANEL")) && (
/* 2066 */                     !BlockStorage.check(chestBlock.getRelative(BlockFace.NORTH), "SOLAR_PANEL") || !BlockStorage.check(chestBlock.getRelative(BlockFace.SOUTH), "SOLAR_PANEL"))) {
/* 2067 */                     return false;
/*      */                   }
/*      */                   
/* 2070 */                   Chest chest = (Chest)chestBlock.getState();
/* 2071 */                   final Inventory inv = chest.getInventory();
/* 2072 */                   List<Location> ores = new ArrayList<>();
/* 2073 */                   for (int x = b.getX() - 6; x < b.getX() + 6; x++) {
/* 2074 */                     for (int z = b.getZ() - 6; z < b.getZ() + 6; z++) {
/* 2075 */                       for (int y = b.getY(); y > 0; y--) {
/* 2076 */                         if (b.getWorld().getBlockAt(x, y, z).getType().toString().endsWith("_ORE")) {
/* 2077 */                           ores.add(b.getWorld().getBlockAt(x, y, z).getLocation());
/*      */                         }
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 2082 */                   if (!ores.isEmpty())
/* 2083 */                   { CustomItem customItem1; final Material ore = ((Location)ores.get(0)).getBlock().getType();
/* 2084 */                     ItemStack drop = new ItemStack(ore);
/* 2085 */                     if (ore == Material.COAL_ORE) { customItem1 = new CustomItem(new ItemStack(Material.COAL), 4); }
/* 2086 */                     else if (ore == Material.IRON_ORE) { customItem1 = new CustomItem(SlimefunItems.IRON_DUST, 2); }
/* 2087 */                     else if (ore == Material.GOLD_ORE) { customItem1 = new CustomItem(SlimefunItems.GOLD_DUST, 2); }
/* 2088 */                     else if (ore == Material.REDSTONE_ORE) { customItem1 = new CustomItem(new ItemStack(Material.REDSTONE), 8); }
/* 2089 */                     else if (ore == Material.QUARTZ_ORE) { customItem1 = new CustomItem(new ItemStack(Material.QUARTZ), 4); }
/* 2090 */                     else if (ore == Material.LAPIS_ORE) { customItem1 = new CustomItem((new MaterialData(Material.INK_SACK, (byte)4)).toItemStack(1), 12); }
/*      */                     else
/* 2092 */                     { for (ItemStack drops : ((Location)ores.get(0)).getBlock().getDrops()) {
/* 2093 */                         if (!drops.getType().isBlock()) customItem1 = new CustomItem(drops, 2); 
/*      */                       }  }
/*      */                     
/* 2096 */                     final CustomItem adding = customItem1;
/* 2097 */                     ((Location)ores.get(0)).getBlock().setType(Material.AIR);
/* 2098 */                     ores.clear();
/* 2099 */                     if (InvUtils.fits(inv, (ItemStack)customItem2)) {
/* 2100 */                       b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2101 */                       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                           {
/*      */                             public void run()
/*      */                             {
/* 2105 */                               b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2106 */                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                   {
/*      */                                     public void run()
/*      */                                     {
/* 2110 */                                       b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2111 */                                       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                           {
/*      */                                             public void run()
/*      */                                             {
/* 2115 */                                               b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2116 */                                               Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                   {
/*      */                                                     public void run()
/*      */                                                     {
/* 2120 */                                                       b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, ore);
/* 2121 */                                                       p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/* 2122 */                                                       inv.addItem(new ItemStack[] { this.this$3.this$2.this$1.val$adding });
/*      */                                                     }
/*      */                                                   }20L);
/*      */                                             }
/*      */                                           }20L);
/*      */                                     }
/*      */                                   }20L);
/*      */                             }
/*      */                           }20L);
/*      */                     } else {
/* 2132 */                       Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                     }  }
/* 2134 */                   else { Messages.local.sendTranslation((CommandSender)p, "miner.no-ores", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*      */                 
/*      */                 } 
/* 2137 */                 return true;
/*      */               } 
/* 2139 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2143 */     (new SlimefunItem(Categories.MISC, SlimefunItems.GOLD_24K_BLOCK, "GOLD_24K_BLOCK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K
/*      */         
/* 2145 */         })).register(true);
/*      */     
/* 2147 */     (new SlimefunGadget(Categories.MACHINES_1, SlimefunItems.COMPOSTER, "COMPOSTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.WOOD_STEP), null, new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), null, new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.CAULDRON_ITEM), new ItemStack(Material.WOOD_STEP) }, new ItemStack[] { (ItemStack)new CustomItem(Material.LEAVES, 0, 8), new ItemStack(Material.DIRT), (ItemStack)new CustomItem(Material.LEAVES_2, 0, 8), new ItemStack(Material.DIRT), (ItemStack)new CustomItem(Material.SAPLING, 0, 8), new ItemStack(Material.DIRT), new ItemStack(Material.STONE, 4), new ItemStack(Material.NETHERRACK), new ItemStack(Material.SAND, 2), new ItemStack(Material.SOUL_SAND), new ItemStack(Material.WHEAT, 4), new ItemStack(Material.NETHER_STALK)
/*      */ 
/*      */         
/* 2150 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, final Player p, ItemStack item)
/*      */             {
/* 2154 */               if (e.getClickedBlock() != null) {
/* 2155 */                 SlimefunItem machine = BlockStorage.check(e.getClickedBlock());
/* 2156 */                 if (machine != null && machine.getID().equals("COMPOSTER")) {
/* 2157 */                   if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), e.getClickedBlock(), true)) {
/* 2158 */                     final ItemStack input = p.getInventory().getItemInMainHand();
/* 2159 */                     final Block b = e.getClickedBlock();
/* 2160 */                     for (ItemStack convert : RecipeType.getRecipeInputs(machine)) {
/* 2161 */                       if (convert != null && SlimefunManager.isItemSimiliar(input, convert, true)) {
/* 2162 */                         ItemStack removing = input.clone();
/* 2163 */                         removing.setAmount(convert.getAmount());
/* 2164 */                         p.getInventory().removeItem(new ItemStack[] { removing });
/* 2165 */                         final ItemStack adding = RecipeType.getRecipeOutput(machine, convert);
/*      */                         
/* 2167 */                         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                             {
/*      */                               public void run()
/*      */                               {
/* 2171 */                                 if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2172 */                                 else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2173 */                                  Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                     {
/*      */                                       public void run()
/*      */                                       {
/* 2177 */                                         if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2178 */                                         else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2179 */                                          Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                             {
/*      */                                               public void run()
/*      */                                               {
/* 2183 */                                                 if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2184 */                                                 else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2185 */                                                  Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                     {
/*      */                                                       public void run()
/*      */                                                       {
/* 2189 */                                                         if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2190 */                                                         else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2191 */                                                          Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                             {
/*      */                                                               public void run()
/*      */                                                               {
/* 2195 */                                                                 if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2196 */                                                                 else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2197 */                                                                  Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                     {
/*      */                                                                       public void run()
/*      */                                                                       {
/* 2201 */                                                                         if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2202 */                                                                         else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2203 */                                                                          Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                             {
/*      */                                                                               public void run()
/*      */                                                                               {
/* 2207 */                                                                                 if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2208 */                                                                                 else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2209 */                                                                                  Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                                     {
/*      */                                                                                       public void run()
/*      */                                                                                       {
/* 2213 */                                                                                         if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2214 */                                                                                         else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2215 */                                                                                          Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                                             {
/*      */                                                                                               public void run()
/*      */                                                                                               {
/* 2219 */                                                                                                 if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2220 */                                                                                                 else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2221 */                                                                                                  Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                                                     {
/*      */                                                                                                       public void run()
/*      */                                                                                                       {
/* 2225 */                                                                                                         if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2226 */                                                                                                         else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2227 */                                                                                                          Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                                                             {
/*      */                                                                                                               public void run()
/*      */                                                                                                               {
/* 2231 */                                                                                                                 if (input.getType().isBlock()) { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, input.getType()); }
/* 2232 */                                                                                                                 else { b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK); }
/* 2233 */                                                                                                                  p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/* 2234 */                                                                                                                 b.getWorld().dropItemNaturally(b.getRelative(BlockFace.UP).getLocation(), adding);
/*      */                                                                                                               }
/*      */                                                                                                             }30L);
/*      */                                                                                                       }
/*      */                                                                                                     }30L);
/*      */                                                                                               }
/*      */                                                                                             }30L);
/*      */                                                                                       }
/*      */                                                                                     }30L);
/*      */                                                                               }
/*      */                                                                             }30L);
/*      */                                                                       }
/*      */                                                                     }30L);
/*      */                                                               }
/*      */                                                             }30L);
/*      */                                                       }
/*      */                                                     }30L);
/*      */                                               }
/*      */                                             }30L);
/*      */                                       }
/*      */                                     }30L);
/*      */                               }
/*      */                             }30L);
/* 2257 */                         return true;
/*      */                       } 
/*      */                     } 
/* 2260 */                     Messages.local.sendTranslation((CommandSender)p, "machines.wrong-item", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 2261 */                     return true;
/*      */                   } 
/* 2263 */                   return true;
/*      */                 } 
/*      */               } 
/* 2266 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2270 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.FARMER_SHOES, "FARMER_SHOES", RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, new ItemStack(Material.HAY_BLOCK), null, new ItemStack(Material.HAY_BLOCK), new ItemStack(Material.HAY_BLOCK), null, new ItemStack(Material.HAY_BLOCK)
/*      */         
/* 2272 */         })).register(true);
/*      */ 
/*      */     
/* 2275 */     (new String[5])[0] = "BEDROCK"; (new String[5])[1] = "BARRIER"; (new String[5])[2] = "COMMAND"; (new String[5])[3] = "COMMAND_CHAIN"; (new String[5])[4] = "COMMAND_REPEATING"; final String[] explosiveblacklist = (Slimefun.getItemValue("EXPLOSIVE_PICKAXE", "unbreakable-blocks") != null) ? (String[])((List)Slimefun.getItemValue("EXPLOSIVE_PICKAXE", "unbreakable-blocks")).toArray((Object[])new String[((List)Slimefun.getItemValue("EXPLOSIVE_PICKAXE", "unbreakable-blocks")).size()]) : new String[5];
/*      */     
/* 2277 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.EXPLOSIVE_PICKAXE, "EXPLOSIVE_PICKAXE", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.TNT), SlimefunItems.SYNTHETIC_DIAMOND, new ItemStack(Material.TNT), null, SlimefunItems.FERROSILICON, null, null, SlimefunItems.FERROSILICON, null }, new String[] { "unbreakable-blocks" }, new Object[] {
/*      */           
/* 2279 */           Arrays.asList(new String[] { "BEDROCK", "BARRIER", "COMMAND", "COMMAND_CHAIN", "COMMAND_REPEATING" })
/* 2280 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockBreakHandler()
/*      */           {
/*      */             public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops)
/*      */             {
/* 2284 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.EXPLOSIVE_PICKAXE, true)) {
/* 2285 */                 e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 0.0F);
/* 2286 */                 e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
/* 2287 */                 for (int x = -1; x <= 1; x++) {
/* 2288 */                   for (int y = -1; y <= 1; y++) {
/* 2289 */                     for (int z = -1; z <= 1; z++) {
/* 2290 */                       Block b = e.getBlock().getRelative(x, y, z);
/* 2291 */                       if (b.getType() != Material.AIR && !StringUtils.equals(b.getType().toString(), explosiveblacklist) && 
/* 2292 */                         CSCoreLib.getLib().getProtectionManager().canBuild(e.getPlayer().getUniqueId(), b) && 
/* 2293 */                         ProtectionUtils.canBuild(e.getPlayer(), b)) {
/* 2294 */                         if (SlimefunStartup.instance.isCoreProtectInstalled()) SlimefunStartup.instance.getCoreProtectAPI().logRemoval(e.getPlayer().getName(), b.getLocation(), b.getType(), b.getData()); 
/* 2295 */                         b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
/* 2296 */                         SlimefunItem sfItem = BlockStorage.check(b);
/* 2297 */                         boolean allow = true;
/* 2298 */                         if (sfItem != null && !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock)) {
/* 2299 */                           if (SlimefunItem.blockhandler.containsKey(sfItem.getName())) {
/* 2300 */                             allow = ((SlimefunBlockHandler)SlimefunItem.blockhandler.get(sfItem.getName())).onBreak(e.getPlayer(), e.getBlock(), sfItem, UnregisterReason.PLAYER_BREAK);
/*      */                           }
/* 2302 */                           if (allow) {
/* 2303 */                             drops.add(BlockStorage.retrieve(e.getBlock()));
/*      */                           }
/*      */                         }
/* 2306 */                         else if (b.getType().equals(Material.SKULL)) {
/* 2307 */                           b.breakNaturally();
/*      */                         }
/* 2309 */                         else if (b.getType().name().endsWith("_SHULKER_BOX")) {
/* 2310 */                           b.breakNaturally();
/*      */                         } else {
/*      */                           
/* 2313 */                           for (ItemStack drop : b.getDrops()) {
/* 2314 */                             b.getWorld().dropItemNaturally(b.getLocation(), (b.getType().toString().endsWith("_ORE") && !b.getType().equals(Material.IRON_ORE) && !b.getType().equals(Material.GOLD_ORE)) ? (ItemStack)new CustomItem(drop, fortune) : drop);
/*      */                           }
/* 2316 */                           b.setType(Material.AIR);
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */                 
/* 2323 */                 return true;
/*      */               } 
/* 2325 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2329 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.AUTOMATED_PANNING_MACHINE, "AUTOMATED_PANNING_MACHINE", new ItemStack[] { null, null, null, null, new ItemStack(Material.TRAP_DOOR), null, null, new ItemStack(Material.CAULDRON_ITEM), null }, new ItemStack[] { new ItemStack(Material.GRAVEL), new ItemStack(Material.FLINT), new ItemStack(Material.GRAVEL), new ItemStack(Material.CLAY_BALL), new ItemStack(Material.GRAVEL), SlimefunItems.SIFTED_ORE }, Material.TRAP_DOOR))
/*      */ 
/*      */       
/* 2332 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             public boolean onInteract(final Player p, MultiBlock mb, final Block b)
/*      */             {
/* 2336 */               if (mb.isMultiBlock(SlimefunItem.getByID("AUTOMATED_PANNING_MACHINE"))) {
/* 2337 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true)) {
/* 2338 */                   ItemStack input = p.getInventory().getItemInMainHand();
/* 2339 */                   ItemStack output = null;
/* 2340 */                   if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("GOLD_PAN", "chance.SIFTED_ORE")).intValue())) { output = SlimefunItems.SIFTED_ORE; }
/* 2341 */                   else if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("GOLD_PAN", "chance.CLAY")).intValue())) { output = new ItemStack(Material.CLAY_BALL); }
/* 2342 */                   else if (SlimefunStartup.chance(100, ((Integer)Slimefun.getItemValue("GOLD_PAN", "chance.FLINT")).intValue())) { output = new ItemStack(Material.FLINT); }
/* 2343 */                    final ItemStack drop = output;
/* 2344 */                   if (input != null && 
/* 2345 */                     input.getType() == Material.GRAVEL) {
/* 2346 */                     PlayerInventory.consumeItemInHand(p);
/* 2347 */                     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                         {
/*      */                           public void run()
/*      */                           {
/* 2351 */                             b.getWorld().playEffect(b.getRelative(BlockFace.DOWN).getLocation(), Effect.STEP_SOUND, Material.GRAVEL);
/* 2352 */                             Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                 {
/*      */                                   public void run()
/*      */                                   {
/* 2356 */                                     b.getWorld().playEffect(b.getRelative(BlockFace.DOWN).getLocation(), Effect.STEP_SOUND, Material.GRAVEL);
/* 2357 */                                     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                         {
/*      */                                           public void run()
/*      */                                           {
/* 2361 */                                             b.getWorld().playEffect(b.getRelative(BlockFace.DOWN).getLocation(), Effect.STEP_SOUND, Material.GRAVEL);
/* 2362 */                                             Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                 {
/*      */                                                   public void run()
/*      */                                                   {
/* 2366 */                                                     b.getWorld().playEffect(b.getRelative(BlockFace.DOWN).getLocation(), Effect.STEP_SOUND, Material.GRAVEL);
/* 2367 */                                                     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                         {
/*      */                                                           public void run()
/*      */                                                           {
/* 2371 */                                                             b.getWorld().playEffect(b.getRelative(BlockFace.DOWN).getLocation(), Effect.STEP_SOUND, Material.GRAVEL);
/* 2372 */                                                             Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                 {
/*      */                                                                   public void run()
/*      */                                                                   {
/* 2376 */                                                                     b.getWorld().playEffect(b.getRelative(BlockFace.DOWN).getLocation(), Effect.STEP_SOUND, Material.GRAVEL);
/* 2377 */                                                                     if (drop != null) b.getWorld().dropItemNaturally(b.getLocation(), drop); 
/* 2378 */                                                                     p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
/*      */                                                                   }
/*      */                                                                 }30L);
/*      */                                                           }
/*      */                                                         }30L);
/*      */                                                   }
/*      */                                                 }30L);
/*      */                                           }
/*      */                                         }30L);
/*      */                                   }
/*      */                                 }30L);
/*      */                           }
/*      */                         }30L);
/* 2391 */                     return true;
/*      */                   } 
/*      */                   
/* 2394 */                   Messages.local.sendTranslation((CommandSender)p, "machines.wrong-item", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 2395 */                   return true;
/*      */                 } 
/* 2397 */                 return true;
/*      */               } 
/* 2399 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2403 */     (new SlimefunItem(Categories.MAGIC_ARMOR, SlimefunItems.BOOTS_OF_THE_STOMPER, "BOOTS_OF_THE_STOMPER", RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, new ItemStack(Material.WOOL), null, new ItemStack(Material.WOOL), new ItemStack(Material.PISTON_BASE), null, new ItemStack(Material.PISTON_BASE)
/*      */         
/* 2405 */         })).register(true);
/*      */     
/* 2407 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.PICKAXE_OF_THE_SEEKER, "PICKAXE_OF_THE_SEEKER", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.COMPASS), SlimefunItems.SYNTHETIC_DIAMOND, new ItemStack(Material.COMPASS), null, SlimefunItems.FERROSILICON, null, null, SlimefunItems.FERROSILICON, null
/*      */         
/* 2409 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 2413 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.PICKAXE_OF_THE_SEEKER, true)) {
/* 2414 */                 Block closest = null;
/*      */                 
/* 2416 */                 for (int x = -4; x <= 4; x++) {
/* 2417 */                   for (int y = -4; y <= 4; y++) {
/* 2418 */                     for (int z = -4; z <= 4; z++) {
/* 2419 */                       if (p.getLocation().getBlock().getRelative(x, y, z).getType().toString().endsWith("_ORE")) {
/* 2420 */                         if (closest == null) { closest = p.getLocation().getBlock().getRelative(x, y, z); }
/* 2421 */                         else if (p.getLocation().distance(closest.getLocation()) < p.getLocation().distance(p.getLocation().getBlock().getRelative(x, y, z).getLocation())) { closest = p.getLocation().getBlock().getRelative(x, y, z); }
/*      */                       
/*      */                       }
/*      */                     } 
/*      */                   } 
/*      */                 } 
/* 2427 */                 if (closest == null) { Messages.local.sendTranslation((CommandSender)p, "miner.no-ores", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*      */                 else
/* 2429 */                 { float yaw; double l = closest.getX() + 0.5D - p.getLocation().getX();
/* 2430 */                   double w = closest.getZ() + 0.5D - p.getLocation().getZ();
/*      */                   
/* 2432 */                   double c = Math.sqrt(l * l + w * w);
/* 2433 */                   double alpha1 = -Math.asin(l / c) / Math.PI * 180.0D;
/* 2434 */                   double alpha2 = Math.acos(w / c) / Math.PI * 180.0D;
/* 2435 */                   if (alpha2 > 90.0D) { yaw = (float)(180.0D - alpha1); }
/* 2436 */                   else { yaw = (float)alpha1; }
/* 2437 */                    float pitch = (float)(-Math.atan((closest.getY() - 0.5D - p.getLocation().getY()) / Math.sqrt(l * l + w * w)) * 180.0D / Math.PI);
/*      */                   
/* 2439 */                   p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), yaw, pitch)); }
/*      */ 
/*      */                 
/* 2442 */                 if (e.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.DURABILITY)) {
/* 2443 */                   if (SlimefunStartup.randomize(100) <= 60 + 40 / (e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY) + 1)) PlayerInventory.damageItemInHand(e.getPlayer()); 
/*      */                 } else {
/* 2445 */                   PlayerInventory.damageItemInHand(e.getPlayer());
/*      */                 } 
/* 2447 */                 PlayerInventory.update(e.getPlayer());
/* 2448 */                 return true;
/*      */               } 
/* 2450 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2454 */     (new SlimefunBackpack(9, Categories.PORTABLE, SlimefunItems.BACKPACK_SMALL, "SMALL_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), SlimefunItems.GOLD_6K, new ItemStack(Material.CHEST), SlimefunItems.GOLD_6K, new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)
/*      */         
/* 2456 */         })).register(true);
/*      */     
/* 2458 */     (new SlimefunBackpack(18, Categories.PORTABLE, SlimefunItems.BACKPACK_MEDIUM, "MEDIUM_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), SlimefunItems.GOLD_10K, SlimefunItems.BACKPACK_SMALL, SlimefunItems.GOLD_10K, new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)
/*      */         
/* 2460 */         })).register(true);
/*      */     
/* 2462 */     (new SlimefunBackpack(27, Categories.PORTABLE, SlimefunItems.BACKPACK_LARGE, "LARGE_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), SlimefunItems.GOLD_14K, SlimefunItems.BACKPACK_MEDIUM, SlimefunItems.GOLD_14K, new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)
/*      */         
/* 2464 */         })).register(true);
/*      */     
/* 2466 */     (new SlimefunBackpack(36, Categories.PORTABLE, SlimefunItems.WOVEN_BACKPACK, "WOVEN_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CLOTH, null, SlimefunItems.CLOTH, SlimefunItems.GOLD_16K, SlimefunItems.BACKPACK_LARGE, SlimefunItems.GOLD_16K, SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH
/*      */         
/* 2468 */         })).register(true);
/*      */     
/* 2470 */     (new SlimefunGadget(Categories.MACHINES_1, SlimefunItems.CRUCIBLE, "CRUCIBLE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.HARD_CLAY), null, new ItemStack(Material.HARD_CLAY), new ItemStack(Material.HARD_CLAY), null, new ItemStack(Material.HARD_CLAY), new ItemStack(Material.HARD_CLAY), new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.HARD_CLAY) }, new ItemStack[] { new ItemStack(Material.COBBLESTONE, 16), new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.LEAVES, 16), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.HARD_CLAY, 12), new ItemStack(Material.LAVA_BUCKET)
/*      */ 
/*      */         
/* 2473 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 2477 */               if (e.getClickedBlock() != null) {
/* 2478 */                 SlimefunItem machine = BlockStorage.check(e.getClickedBlock());
/* 2479 */                 if (machine != null && machine.getID().equals("CRUCIBLE")) {
/* 2480 */                   if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), e.getClickedBlock(), true)) {
/* 2481 */                     final ItemStack input = p.getInventory().getItemInMainHand();
/* 2482 */                     final Block block = e.getClickedBlock().getRelative(BlockFace.UP);
/* 2483 */                     for (ItemStack convert : RecipeType.getRecipeInputs(machine)) {
/* 2484 */                       if (input != null && 
/* 2485 */                         SlimefunManager.isItemSimiliar(input, convert, true)) {
/* 2486 */                         e.setCancelled(true);
/* 2487 */                         ItemStack removing = input.clone();
/* 2488 */                         removing.setAmount(convert.getAmount());
/* 2489 */                         p.getInventory().removeItem(new ItemStack[] { removing });
/*      */                         
/* 2491 */                         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                             {
/*      */                               public void run()
/*      */                               {
/* 2495 */                                 if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2496 */                                   block.setType(Material.LAVA);
/* 2497 */                                   block.setData((byte)7);
/* 2498 */                                   block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                 }
/* 2500 */                                 else if (input.getType() == Material.LEAVES) {
/* 2501 */                                   block.setType(Material.WATER);
/* 2502 */                                   block.setData((byte)7);
/* 2503 */                                   block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                 } 
/* 2505 */                                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                     {
/*      */                                       public void run()
/*      */                                       {
/* 2509 */                                         if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2510 */                                           block.setType(Material.LAVA);
/* 2511 */                                           block.setData((byte)6);
/* 2512 */                                           block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                         }
/* 2514 */                                         else if (input.getType() == Material.LEAVES) {
/* 2515 */                                           block.setType(Material.WATER);
/* 2516 */                                           block.setData((byte)6);
/* 2517 */                                           block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                         } 
/* 2519 */                                         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                             {
/*      */                                               public void run()
/*      */                                               {
/* 2523 */                                                 if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2524 */                                                   block.setType(Material.LAVA);
/* 2525 */                                                   block.setData((byte)5);
/* 2526 */                                                   block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                                 }
/* 2528 */                                                 else if (input.getType() == Material.LEAVES) {
/* 2529 */                                                   block.setType(Material.WATER);
/* 2530 */                                                   block.setData((byte)5);
/* 2531 */                                                   block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                                 } 
/* 2533 */                                                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                     {
/*      */                                                       public void run()
/*      */                                                       {
/* 2537 */                                                         if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2538 */                                                           block.setType(Material.LAVA);
/* 2539 */                                                           block.setData((byte)4);
/* 2540 */                                                           block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                                         }
/* 2542 */                                                         else if (input.getType() == Material.LEAVES) {
/* 2543 */                                                           block.setType(Material.WATER);
/* 2544 */                                                           block.setData((byte)4);
/* 2545 */                                                           block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                                         } 
/* 2547 */                                                         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                             {
/*      */                                                               public void run()
/*      */                                                               {
/* 2551 */                                                                 if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2552 */                                                                   block.setType(Material.LAVA);
/* 2553 */                                                                   block.setData((byte)3);
/* 2554 */                                                                   block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                                                 }
/* 2556 */                                                                 else if (input.getType() == Material.LEAVES) {
/* 2557 */                                                                   block.setType(Material.WATER);
/* 2558 */                                                                   block.setData((byte)3);
/* 2559 */                                                                   block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                                                 } 
/* 2561 */                                                                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                     {
/*      */                                                                       public void run()
/*      */                                                                       {
/* 2565 */                                                                         if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2566 */                                                                           block.setType(Material.LAVA);
/* 2567 */                                                                           block.setData((byte)2);
/* 2568 */                                                                           block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                                                         }
/* 2570 */                                                                         else if (input.getType() == Material.LEAVES) {
/* 2571 */                                                                           block.setType(Material.WATER);
/* 2572 */                                                                           block.setData((byte)2);
/* 2573 */                                                                           block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                                                         } 
/* 2575 */                                                                         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                             {
/*      */                                                                               public void run()
/*      */                                                                               {
/* 2579 */                                                                                 if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2580 */                                                                                   block.setType(Material.LAVA);
/* 2581 */                                                                                   block.setData((byte)1);
/* 2582 */                                                                                   block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                                                                 }
/* 2584 */                                                                                 else if (input.getType() == Material.LEAVES) {
/* 2585 */                                                                                   block.setType(Material.WATER);
/* 2586 */                                                                                   block.setData((byte)1);
/* 2587 */                                                                                   block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                                                                 } 
/* 2589 */                                                                                 Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                                                                                     {
/*      */                                                                                       public void run()
/*      */                                                                                       {
/* 2593 */                                                                                         if (input.getType() == Material.COBBLESTONE || input.getType() == Material.HARD_CLAY) {
/* 2594 */                                                                                           block.setType(Material.STATIONARY_LAVA);
/* 2595 */                                                                                           block.setData((byte)0);
/* 2596 */                                                                                           block.getWorld().playSound(block.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
/*      */                                                                                         }
/* 2598 */                                                                                         else if (input.getType() == Material.LEAVES) {
/* 2599 */                                                                                           block.setType(Material.WATER);
/* 2600 */                                                                                           block.setData((byte)0);
/* 2601 */                                                                                           block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/*      */                                                                                         } 
/*      */                                                                                       }
/*      */                                                                                     }50L);
/*      */                                                                               }
/*      */                                                                             }50L);
/*      */                                                                       }
/*      */                                                                     }50L);
/*      */                                                               }
/*      */                                                             }50L);
/*      */                                                       }
/*      */                                                     }50L);
/*      */                                               }
/*      */                                             }50L);
/*      */                                       }
/*      */                                     }50L);
/*      */                               }
/*      */                             }50L);
/* 2619 */                         return true;
/*      */                       } 
/*      */                     } 
/*      */                     
/* 2623 */                     Messages.local.sendTranslation((CommandSender)p, "machines.wrong-item", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 2624 */                     return true;
/*      */                   } 
/* 2626 */                   return true;
/*      */                 } 
/*      */               } 
/* 2629 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2633 */     (new SlimefunBackpack(45, Categories.PORTABLE, SlimefunItems.GILDED_BACKPACK, "GILDED_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GOLD_22K, null, SlimefunItems.GOLD_22K, new ItemStack(Material.LEATHER), SlimefunItems.WOVEN_BACKPACK, new ItemStack(Material.LEATHER), SlimefunItems.GOLD_22K, null, SlimefunItems.GOLD_22K
/*      */         
/* 2635 */         })).register(true);
/*      */     
/* 2637 */     (new Alloy(Categories.TECH_MISC, SlimefunItems.MAGNET, "MAGNET", new ItemStack[] { SlimefunItems.NICKEL_INGOT, SlimefunItems.ALUMINUM_DUST, SlimefunItems.IRON_DUST, SlimefunItems.COBALT_INGOT, null, null, null, null, null
/*      */         
/* 2639 */         })).register(true);
/*      */     
/* 2641 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.INFUSED_MAGNET, "INFUSED_MAGNET", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.ENDER_LUMP_2, SlimefunItems.MAGNET, SlimefunItems.ENDER_LUMP_2, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3
/*      */         
/* 2643 */         })).register(true);
/*      */     
/* 2645 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.COBALT_PICKAXE, "COBALT_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.COBALT_INGOT, SlimefunItems.COBALT_INGOT, SlimefunItems.COBALT_INGOT, null, SlimefunItems.NICKEL_INGOT, null, null, SlimefunItems.NICKEL_INGOT, null
/*      */         
/* 2647 */         })).register(true);
/*      */     
/* 2649 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.NECROTIC_SKULL, "NECROTIC_SKULL", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3, null, (new MaterialData(Material.SKULL_ITEM, (byte)1))
/* 2650 */           .toItemStack(1), null, SlimefunItems.MAGIC_LUMP_3, null, SlimefunItems.MAGIC_LUMP_3
/* 2651 */         })).register(true);
/*      */     
/* 2653 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.ESSENCE_OF_AFTERLIFE, "ESSENCE_OF_AFTERLIFE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.ENDER_LUMP_3, SlimefunItems.RUNE_AIR, SlimefunItems.ENDER_LUMP_3, SlimefunItems.RUNE_EARTH, SlimefunItems.NECROTIC_SKULL, SlimefunItems.RUNE_FIRE, SlimefunItems.ENDER_LUMP_3, SlimefunItems.RUNE_WATER, SlimefunItems.ENDER_LUMP_3
/*      */         
/* 2655 */         })).register(true);
/*      */     
/* 2657 */     (new SoulboundBackpack(36, Categories.PORTABLE, SlimefunItems.BOUND_BACKPACK, "BOUND_BACKPACK", new ItemStack[] { SlimefunItems.ENDER_LUMP_2, null, SlimefunItems.ENDER_LUMP_2, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.GILDED_BACKPACK, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.ENDER_LUMP_2, null, SlimefunItems.ENDER_LUMP_2
/*      */         
/* 2659 */         })).register(true);
/*      */     
/* 2661 */     (new JetBoots(SlimefunItems.DURALUMIN_JETBOOTS, "DURALUMIN_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.35D))
/*      */ 
/*      */       
/* 2664 */       .register(true);
/*      */     
/* 2666 */     (new JetBoots(SlimefunItems.SOLDER_JETBOOTS, "SOLDER_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.SOLDER_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.SOLDER_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.4D))
/*      */ 
/*      */       
/* 2669 */       .register(true);
/*      */     
/* 2671 */     (new JetBoots(SlimefunItems.BILLON_JETBOOTS, "BILLON_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.BILLON_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.BILLON_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.45D))
/*      */ 
/*      */       
/* 2674 */       .register(true);
/*      */     
/* 2676 */     (new JetBoots(SlimefunItems.STEEL_JETBOOTS, "STEEL_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.5D))
/*      */ 
/*      */       
/* 2679 */       .register(true);
/*      */     
/* 2681 */     (new JetBoots(SlimefunItems.DAMASCUS_STEEL_JETBOOTS, "DAMASCUS_STEEL_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.55D))
/*      */ 
/*      */       
/* 2684 */       .register(true);
/*      */     
/* 2686 */     (new JetBoots(SlimefunItems.REINFORCED_ALLOY_JETBOOTS, "REINFORCED_ALLOY_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.STEEL_THRUSTER, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.6D))
/*      */ 
/*      */       
/* 2689 */       .register(true);
/*      */     
/* 2691 */     (new JetBoots(SlimefunItems.CARBONADO_JETBOOTS, "CARBONADO_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.CARBONADO, SlimefunItems.POWER_CRYSTAL, SlimefunItems.CARBONADO, SlimefunItems.STEEL_THRUSTER, SlimefunItems.LARGE_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.7D))
/*      */ 
/*      */       
/* 2694 */       .register(true);
/*      */     
/* 2696 */     (new JetBoots(SlimefunItems.ARMORED_JETBOOTS, "ARMORED_JETBOOTS", new ItemStack[] { null, null, null, SlimefunItems.STEEL_PLATE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_PLATE, SlimefunItems.STEEL_THRUSTER, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.STEEL_THRUSTER }, 0.45D))
/*      */ 
/*      */       
/* 2699 */       .register(true);
/*      */     
/* 2701 */     (new SlimefunItem(Categories.WEAPONS, SlimefunItems.SEISMIC_AXE, "SEISMIC_AXE", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, null, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.STAFF_ELEMENTAL, null, null, SlimefunItems.STAFF_ELEMENTAL, null
/*      */         
/* 2703 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 2707 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.SEISMIC_AXE, true)) {
/* 2708 */                 List<Block> blocks = p.getLineOfSight((HashSet)null, 10); int i;
/* 2709 */                 for (i = 0; i < blocks.size(); i++) {
/* 2710 */                   Block b = blocks.get(i);
/* 2711 */                   Location ground = b.getLocation();
/* 2712 */                   if (b.getType() == null || b.getType() == Material.AIR) {
/* 2713 */                     for (int y = ground.getBlockY(); y > 0; y--) {
/* 2714 */                       if (b.getWorld().getBlockAt(b.getX(), y, b.getZ()) != null && 
/* 2715 */                         b.getWorld().getBlockAt(b.getX(), y, b.getZ()).getType() != null && 
/* 2716 */                         b.getWorld().getBlockAt(b.getX(), y, b.getZ()).getType() != Material.AIR) {
/* 2717 */                         ground = new Location(b.getWorld(), b.getX(), y, b.getZ());
/*      */                         
/*      */                         break;
/*      */                       } 
/*      */                     } 
/*      */                   }
/*      */                   
/* 2724 */                   b.getWorld().playEffect(ground, Effect.STEP_SOUND, ground.getBlock().getType());
/* 2725 */                   if (ground.getBlock().getRelative(BlockFace.UP).getType() == null || ground.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR) {
/* 2726 */                     FallingBlock block = ground.getWorld().spawnFallingBlock(ground.getBlock().getRelative(BlockFace.UP).getLocation(), ground.getBlock().getType(), ground.getBlock().getData());
/* 2727 */                     block.setDropItem(false);
/* 2728 */                     block.setVelocity(new Vector(0.0D, 0.4D + i * 0.01D, 0.0D));
/* 2729 */                     Variables.blocks.add(block.getUniqueId());
/*      */                   } 
/* 2731 */                   for (Entity n : ground.getChunk().getEntities()) {
/* 2732 */                     if (n instanceof LivingEntity && 
/* 2733 */                       n.getLocation().distance(ground) <= 2.0D && n.getUniqueId() != p.getUniqueId()) {
/* 2734 */                       Vector vector = n.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.4D);
/* 2735 */                       vector.setY(0.9D);
/* 2736 */                       n.setVelocity(vector);
/* 2737 */                       if (p.getWorld().getPVP()) {
/* 2738 */                         EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)p, n, EntityDamageEvent.DamageCause.ENTITY_ATTACK, 6.0D);
/* 2739 */                         Bukkit.getPluginManager().callEvent((Event)event);
/* 2740 */                         if (!event.isCancelled()) ((LivingEntity)n).damage(6.0D);
/*      */                       
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */                 
/* 2747 */                 for (i = 0; i < 4; i++) {
/* 2748 */                   if (e.getPlayer().getInventory().getItemInMainHand() != null)
/* 2749 */                     if (e.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.DURABILITY)) {
/* 2750 */                       if (SlimefunStartup.randomize(100) <= 60 + 40 / (e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY) + 1)) PlayerInventory.damageItemInHand(e.getPlayer()); 
/*      */                     } else {
/* 2752 */                       PlayerInventory.damageItemInHand(e.getPlayer());
/*      */                     }  
/*      */                 } 
/* 2755 */                 return true;
/*      */               } 
/* 2757 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2761 */     (new SlimefunItem(Categories.TOOLS, SlimefunItems.PICKAXE_OF_VEIN_MINING, "PICKAXE_OF_VEIN_MINING", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.EMERALD_ORE), SlimefunItems.SYNTHETIC_DIAMOND, new ItemStack(Material.EMERALD_ORE), null, SlimefunItems.GILDED_IRON, null, null, SlimefunItems.GILDED_IRON, null
/*      */         
/* 2763 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockBreakHandler()
/*      */           {
/*      */             public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops)
/*      */             {
/* 2767 */               if (SlimefunManager.isItemSimiliar(e.getPlayer().getInventory().getItemInMainHand(), SlimefunItems.PICKAXE_OF_VEIN_MINING, true)) {
/* 2768 */                 if (e.getBlock().getType().toString().endsWith("_ORE")) {
/* 2769 */                   List<Location> blocks = new ArrayList<>();
/* 2770 */                   Vein.calculate(e.getBlock().getLocation(), e.getBlock().getLocation(), blocks, 16);
/* 2771 */                   for (Location block : blocks) {
/* 2772 */                     Block b = block.getBlock();
/* 2773 */                     b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
/* 2774 */                     for (ItemStack drop : b.getDrops()) {
/* 2775 */                       b.getWorld().dropItemNaturally(b.getLocation(), drop.getType().isBlock() ? drop : (ItemStack)new CustomItem(drop, fortune));
/*      */                     }
/* 2777 */                     b.setType(Material.AIR);
/*      */                   } 
/*      */                 } 
/* 2780 */                 return true;
/*      */               } 
/* 2782 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2786 */     (new SoulboundItem(Categories.WEAPONS, SlimefunItems.SOULBOUND_SWORD, "SOULBOUND_SWORD", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_SWORD), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2788 */         })).register(true);
/*      */     
/* 2790 */     (new SoulboundItem(Categories.WEAPONS, SlimefunItems.SOULBOUND_BOW, "SOULBOUND_BOW", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.BOW), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2792 */         })).register(true);
/*      */     
/* 2794 */     (new SoulboundItem(Categories.TOOLS, SlimefunItems.SOULBOUND_PICKAXE, "SOULBOUND_PICKAXE", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_PICKAXE), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2796 */         })).register(true);
/*      */     
/* 2798 */     (new SoulboundItem(Categories.TOOLS, SlimefunItems.SOULBOUND_AXE, "SOULBOUND_AXE", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_AXE), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2800 */         })).register(true);
/*      */     
/* 2802 */     (new ExcludedSoulboundTool(Categories.TOOLS, SlimefunItems.SOULBOUND_SHOVEL, "SOULBOUND_SHOVEL", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_SPADE), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2804 */         })).register(true);
/*      */     
/* 2806 */     (new ExcludedSoulboundTool(Categories.TOOLS, SlimefunItems.SOULBOUND_HOE, "SOULBOUND_HOE", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_HOE), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2808 */         })).register(true);
/*      */     
/* 2810 */     (new SoulboundItem(Categories.MAGIC_ARMOR, SlimefunItems.SOULBOUND_HELMET, "SOULBOUND_HELMET", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_HELMET), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2812 */         })).register(true);
/*      */     
/* 2814 */     (new SoulboundItem(Categories.MAGIC_ARMOR, SlimefunItems.SOULBOUND_CHESTPLATE, "SOULBOUND_CHESTPLATE", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_CHESTPLATE), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2816 */         })).register(true);
/*      */     
/* 2818 */     (new SoulboundItem(Categories.MAGIC_ARMOR, SlimefunItems.SOULBOUND_LEGGINGS, "SOULBOUND_LEGGINGS", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_LEGGINGS), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2820 */         })).register(true);
/*      */     
/* 2822 */     (new SoulboundItem(Categories.MAGIC_ARMOR, SlimefunItems.SOULBOUND_BOOTS, "SOULBOUND_BOOTS", new ItemStack[] { null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_BOOTS), null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 2824 */         })).register(true);
/*      */     
/* 2826 */     (new SlimefunMachine(Categories.MACHINES_1, SlimefunItems.JUICER, "JUICER", new ItemStack[] { null, new ItemStack(Material.GLASS), null, null, new ItemStack(Material.NETHER_FENCE), null, null, (ItemStack)new CustomItem(Material.DISPENSER, "发射器 (口朝上)", 0), null }new ItemStack[] { new ItemStack(Material.APPLE), SlimefunItems.APPLE_JUICE, new ItemStack(Material.MELON), SlimefunItems.MELON_JUICE, new ItemStack(Material.CARROT_ITEM), SlimefunItems.CARROT_JUICE, new ItemStack(Material.PUMPKIN), SlimefunItems.PUMPKIN_JUICE }, Material.NETHER_FENCE))
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2834 */       .register(true, new ItemHandler[] { (ItemHandler)new MultiBlockInteractionHandler()
/*      */           {
/*      */             public boolean onInteract(Player p, MultiBlock mb, Block b)
/*      */             {
/* 2838 */               SlimefunMachine machine = (SlimefunMachine)SlimefunItem.getByID("JUICER");
/*      */               
/* 2840 */               if (mb.isMultiBlock((SlimefunItem)machine)) {
/* 2841 */                 if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true) && 
/* 2842 */                   Slimefun.hasUnlocked(p, SlimefunItems.JUICER, true)) {
/* 2843 */                   Dispenser disp = (Dispenser)b.getRelative(BlockFace.DOWN).getState();
/* 2844 */                   Inventory inv = disp.getInventory();
/* 2845 */                   for (ItemStack current : inv.getContents()) {
/* 2846 */                     for (ItemStack convert : RecipeType.getRecipeInputs((SlimefunItem)machine)) {
/* 2847 */                       if (convert != null && SlimefunManager.isItemSimiliar(current, convert, true)) {
/* 2848 */                         ItemStack adding = RecipeType.getRecipeOutput((SlimefunItem)machine, convert);
/* 2849 */                         if (InvUtils.fits(inv, adding)) {
/* 2850 */                           ItemStack removing = current.clone();
/* 2851 */                           removing.setAmount(1);
/* 2852 */                           inv.removeItem(new ItemStack[] { removing });
/* 2853 */                           inv.addItem(new ItemStack[] { adding });
/* 2854 */                           p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1.0F, 1.0F);
/* 2855 */                           p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.HAY_BLOCK);
/*      */                         } else {
/* 2857 */                           Messages.local.sendTranslation((CommandSender)p, "machines.full-inventory", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 2858 */                         }  return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 2862 */                   Messages.local.sendTranslation((CommandSender)p, "machines.unknown-material", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*      */                 } 
/*      */                 
/* 2865 */                 return true;
/*      */               } 
/* 2867 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2871 */     (new Juice(Categories.FOOD, SlimefunItems.APPLE_JUICE, "APPLE_JUICE", RecipeType.JUICER, new ItemStack[] { null, null, null, null, new ItemStack(Material.APPLE), null, null, null, null
/*      */         
/* 2873 */         })).register(true);
/*      */     
/* 2875 */     (new Juice(Categories.FOOD, SlimefunItems.CARROT_JUICE, "CARROT_JUICE", RecipeType.JUICER, new ItemStack[] { null, null, null, null, new ItemStack(Material.CARROT_ITEM), null, null, null, null
/*      */         
/* 2877 */         })).register(true);
/*      */     
/* 2879 */     (new Juice(Categories.FOOD, SlimefunItems.MELON_JUICE, "MELON_JUICE", RecipeType.JUICER, new ItemStack[] { null, null, null, null, new ItemStack(Material.MELON), null, null, null, null
/*      */         
/* 2881 */         })).register(true);
/*      */     
/* 2883 */     (new Juice(Categories.FOOD, SlimefunItems.PUMPKIN_JUICE, "PUMPKIN_JUICE", RecipeType.JUICER, new ItemStack[] { null, null, null, null, new ItemStack(Material.PUMPKIN), null, null, null, null
/*      */         
/* 2885 */         })).register(true);
/*      */     
/* 2887 */     (new Juice(Categories.FOOD, SlimefunItems.GOLDEN_APPLE_JUICE, "GOLDEN_APPLE_JUICE", RecipeType.JUICER, new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE), null, null, null, null, null, null, null, null
/*      */         
/* 2889 */         })).register(true);
/*      */     
/* 2891 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.BROKEN_SPAWNER, "BROKEN_SPAWNER", new RecipeType(SlimefunItems.PICKAXE_OF_CONTAINMENT), new ItemStack[] { null, null, null, null, new ItemStack(Material.MOB_SPAWNER), null, null, null, null
/*      */         
/* 2893 */         })).register(true);
/*      */     
/* 2895 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.REPAIRED_SPAWNER, "REINFORCED_SPAWNER", RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.RUNE_ENDER, (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0), SlimefunItems.ESSENCE_OF_AFTERLIFE, (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0), SlimefunItems.BROKEN_SPAWNER, (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0), SlimefunItems.ESSENCE_OF_AFTERLIFE, (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0), SlimefunItems.RUNE_ENDER
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2900 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockPlaceHandler()
/*      */           {
/*      */             public boolean onBlockPlace(BlockPlaceEvent e, ItemStack item)
/*      */             {
/* 2904 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.REPAIRED_SPAWNER, false)) {
/* 2905 */                 EntityType type = null;
/* 2906 */                 for (String line : item.getItemMeta().getLore()) {
/* 2907 */                   if (ChatColor.stripColor(line).startsWith("Type: ")) type = EntityType.valueOf(ChatColor.stripColor(line).replace("Type: ", "").replace(" ", "_").toUpperCase()); 
/*      */                 } 
/* 2909 */                 if (type != null) {
/* 2910 */                   CreatureSpawner spawner = (CreatureSpawner)e.getBlock().getState();
/* 2911 */                   spawner.setSpawnedType(type);
/* 2912 */                   spawner.update(true, false);
/*      */                 } 
/* 2914 */                 return true;
/*      */               } 
/* 2916 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 2920 */     (new EnhancedFurnace(1, 1, 1, SlimefunItems.ENHANCED_FURNACE, "ENHANCED_FURNACE", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, new ItemStack(Material.FURNACE), SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2922 */         })).register(true);
/*      */     
/* 2924 */     (new EnhancedFurnace(2, 1, 1, SlimefunItems.ENHANCED_FURNACE_2, "ENHANCED_FURNACE_2", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2926 */         })).register(true);
/*      */     
/* 2928 */     (new EnhancedFurnace(2, 2, 1, SlimefunItems.ENHANCED_FURNACE_3, "ENHANCED_FURNACE_3", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_2, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2930 */         })).register(true);
/*      */     
/* 2932 */     (new EnhancedFurnace(3, 2, 1, SlimefunItems.ENHANCED_FURNACE_4, "ENHANCED_FURNACE_4", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_3, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2934 */         })).register(true);
/*      */     
/* 2936 */     (new EnhancedFurnace(3, 2, 2, SlimefunItems.ENHANCED_FURNACE_5, "ENHANCED_FURNACE_5", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_4, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2938 */         })).register(true);
/*      */     
/* 2940 */     (new EnhancedFurnace(3, 3, 2, SlimefunItems.ENHANCED_FURNACE_6, "ENHANCED_FURNACE_6", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_5, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2942 */         })).register(true);
/*      */     
/* 2944 */     (new EnhancedFurnace(4, 3, 2, SlimefunItems.ENHANCED_FURNACE_7, "ENHANCED_FURNACE_7", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_6, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2946 */         })).register(true);
/*      */     
/* 2948 */     (new EnhancedFurnace(4, 4, 2, SlimefunItems.ENHANCED_FURNACE_8, "ENHANCED_FURNACE_8", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_7, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2950 */         })).register(true);
/*      */     
/* 2952 */     (new EnhancedFurnace(5, 4, 2, SlimefunItems.ENHANCED_FURNACE_9, "ENHANCED_FURNACE_9", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_8, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2954 */         })).register(true);
/*      */     
/* 2956 */     (new EnhancedFurnace(5, 5, 2, SlimefunItems.ENHANCED_FURNACE_10, "ENHANCED_FURNACE_10", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_9, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2958 */         })).register(true);
/*      */     
/* 2960 */     (new EnhancedFurnace(5, 5, 3, SlimefunItems.ENHANCED_FURNACE_11, "ENHANCED_FURNACE_11", new ItemStack[] { null, SlimefunItems.BASIC_CIRCUIT_BOARD, null, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_10, SlimefunItems.HEATING_COIL, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 2962 */         })).register(true);
/*      */     
/* 2964 */     (new EnhancedFurnace(10, 10, 3, SlimefunItems.REINFORCED_FURNACE, "REINFORCED_FURNACE", new ItemStack[] { SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.ENHANCED_FURNACE_11, SlimefunItems.HEATING_COIL, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT
/*      */         
/* 2966 */         })).register(true);
/*      */     
/* 2968 */     (new EnhancedFurnace(20, 10, 3, SlimefunItems.CARBONADO_EDGED_FURNACE, "CARBONADO_EDGED_FURNACE", new ItemStack[] { SlimefunItems.CARBONADO, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARBONADO, SlimefunItems.HEATING_COIL, SlimefunItems.REINFORCED_FURNACE, SlimefunItems.HEATING_COIL, SlimefunItems.CARBONADO, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARBONADO
/*      */         
/* 2970 */         })).register(true);
/*      */     
/* 2972 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.ELECTRO_MAGNET, "ELECTRO_MAGNET", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.NICKEL_INGOT, SlimefunItems.MAGNET, SlimefunItems.COBALT_INGOT, null, SlimefunItems.BATTERY, null, null, null, null
/*      */         
/* 2974 */         })).register(true);
/*      */     
/* 2976 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.ELECTRIC_MOTOR, "ELECTRIC_MOTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, null, SlimefunItems.ELECTRO_MAGNET, null, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT
/*      */         
/* 2978 */         })).register(true);
/*      */     
/* 2980 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.HEATING_COIL, "HEATING_COIL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT
/*      */         
/* 2982 */         })).register(true);
/*      */ 
/*      */     
/* 2985 */     (new String[1])[0] = "STRUCTURE_BLOCK"; final String[] blockPlacerBlacklist = (Slimefun.getItemValue("BLOCK_PLACER", "unplaceable-blocks") != null) ? (String[])((List)Slimefun.getItemValue("BLOCK_PLACER", "unplaceable-blocks")).toArray((Object[])new String[((List)Slimefun.getItemValue("BLOCK_PLACER", "unplaceable-blocks")).size()]) : new String[1];
/*      */     
/* 2987 */     (new SlimefunItem(Categories.MACHINES_1, SlimefunItems.BLOCK_PLACER, "BLOCK_PLACER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GOLD_4K, new ItemStack(Material.PISTON_BASE), SlimefunItems.GOLD_4K, new ItemStack(Material.IRON_INGOT), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.IRON_INGOT), SlimefunItems.GOLD_4K, new ItemStack(Material.PISTON_BASE), SlimefunItems.GOLD_4K }, new String[] { "unplaceable-blocks" }, new Object[] {
/*      */           
/* 2989 */           Arrays.asList(new String[] { "STRUCTURE_BLOCK" })
/* 2990 */         })).register(true, new ItemHandler[] { (ItemHandler)new AutonomousMachineHandler()
/*      */           {
/*      */             public boolean onBlockDispense(final BlockDispenseEvent e, Block dispenser, final Dispenser d, Block block, Block chest, SlimefunItem machine)
/*      */             {
/* 2994 */               if (machine.getID().equalsIgnoreCase("BLOCK_PLACER")) {
/* 2995 */                 e.setCancelled(true);
/* 2996 */                 if ((block.getType() == null || block.getType() == Material.AIR) && e.getItem().getType().isBlock()) {
/* 2997 */                   for (String blockType : blockPlacerBlacklist) {
/* 2998 */                     if (e.getItem().getType().toString().equals(blockType)) {
/* 2999 */                       return false;
/*      */                     }
/*      */                   } 
/*      */                   
/* 3003 */                   SlimefunItem sfItem = SlimefunItem.getByItem(e.getItem());
/* 3004 */                   if (sfItem != null) {
/* 3005 */                     if (!SlimefunItem.blockhandler.containsKey(sfItem.getName())) {
/* 3006 */                       block.setType(e.getItem().getType());
/* 3007 */                       block.setData(e.getItem().getData().getData());
/* 3008 */                       BlockStorage.store(block, sfItem.getName());
/* 3009 */                       block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, e.getItem().getType());
/* 3010 */                       if (d.getInventory().containsAtLeast(e.getItem(), 2)) { d.getInventory().removeItem(new ItemStack[] { (ItemStack)new CustomItem(e.getItem(), 1) }); }
/*      */                       else
/* 3012 */                       { Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                             {
/*      */                               public void run() {
/* 3015 */                                 d.getInventory().removeItem(new ItemStack[] { this.val$e.getItem() }, );
/*      */                               }
/*      */                             }, 
/*      */                             2L); }
/*      */                     
/*      */                     } 
/*      */                   } else {
/* 3022 */                     block.setType(e.getItem().getType());
/* 3023 */                     block.setData(e.getItem().getData().getData());
/* 3024 */                     block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, e.getItem().getType());
/* 3025 */                     if (d.getInventory().containsAtLeast(e.getItem(), 2)) { d.getInventory().removeItem(new ItemStack[] { (ItemStack)new CustomItem(e.getItem(), 1) }); }
/*      */                     else
/* 3027 */                     { Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */                           {
/*      */                             public void run() {
/* 3030 */                               d.getInventory().removeItem(new ItemStack[] { this.val$e.getItem() }, );
/*      */                             }
/*      */                           },  2L); }
/*      */                   
/*      */                   } 
/*      */                 } 
/* 3036 */                 return true;
/*      */               } 
/* 3038 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3042 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.SCROLL_OF_DIMENSIONAL_TELEPOSITION, "SCROLL_OF_DIMENSIONAL_TELEPOSITION", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, SlimefunItems.ENDER_LUMP_3, SlimefunItems.MAGIC_EYE_OF_ENDER, SlimefunItems.ENDER_LUMP_3, SlimefunItems.MAGICAL_BOOK_COVER, SlimefunItems.ENDER_LUMP_3, SlimefunItems.MAGIC_EYE_OF_ENDER, SlimefunItems.ENDER_LUMP_3, null
/*      */         
/* 3044 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 3048 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.SCROLL_OF_DIMENSIONAL_TELEPOSITION, true)) {
/* 3049 */                 for (Entity n : p.getNearbyEntities(10.0D, 10.0D, 10.0D)) {
/* 3050 */                   if (n instanceof LivingEntity && !(n instanceof ArmorStand) && n.getUniqueId() != p.getUniqueId()) {
/* 3051 */                     float yaw = n.getLocation().getYaw() + 180.0F;
/* 3052 */                     if (yaw > 360.0F) yaw -= 360.0F; 
/* 3053 */                     n.teleport(new Location(n.getWorld(), n.getLocation().getX(), n.getLocation().getY(), n.getLocation().getZ(), yaw, n.getLocation().getPitch()));
/*      */                   } 
/*      */                 } 
/* 3056 */                 return true;
/*      */               } 
/* 3058 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3062 */     (new SlimefunBow(SlimefunItems.EXPLOSIVE_BOW, "EXPLOSIVE_BOW", new ItemStack[] { null, new ItemStack(Material.STICK), new ItemStack(Material.SULPHUR), SlimefunItems.STAFF_FIRE, null, SlimefunItems.SULFATE, null, new ItemStack(Material.STICK), new ItemStack(Material.SULPHUR)
/*      */         
/* 3064 */         })).register(true, new ItemHandler[] { (ItemHandler)new BowShootHandler()
/*      */           {
/*      */             public boolean onHit(EntityDamageByEntityEvent e, LivingEntity n)
/*      */             {
/* 3068 */               if (SlimefunManager.isItemSimiliar((ItemStack)Variables.arrows.get(e.getDamager().getUniqueId()), SlimefunItems.EXPLOSIVE_BOW, true)) {
/* 3069 */                 Vector vector = n.getVelocity();
/* 3070 */                 vector.setY(0.6D);
/* 3071 */                 n.setVelocity(vector);
/* 3072 */                 n.getWorld().createExplosion(n.getLocation(), 0.0F);
/* 3073 */                 n.getWorld().playSound(n.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
/* 3074 */                 return true;
/*      */               } 
/* 3076 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3080 */     (new SlimefunBow(SlimefunItems.ICY_BOW, "ICY_BOW", new ItemStack[] { null, new ItemStack(Material.STICK), new ItemStack(Material.ICE), SlimefunItems.STAFF_WATER, null, new ItemStack(Material.PACKED_ICE), null, new ItemStack(Material.STICK), new ItemStack(Material.ICE)
/*      */         
/* 3082 */         })).register(true, new ItemHandler[] { (ItemHandler)new BowShootHandler()
/*      */           {
/*      */             public boolean onHit(EntityDamageByEntityEvent e, LivingEntity n)
/*      */             {
/* 3086 */               if (SlimefunManager.isItemSimiliar((ItemStack)Variables.arrows.get(e.getDamager().getUniqueId()), SlimefunItems.ICY_BOW, true)) {
/* 3087 */                 n.getWorld().playEffect(n.getLocation(), Effect.STEP_SOUND, Material.ICE);
/* 3088 */                 n.getWorld().playEffect(n.getEyeLocation(), Effect.STEP_SOUND, Material.ICE);
/* 3089 */                 n.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 10));
/* 3090 */                 n.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, -10));
/* 3091 */                 return true;
/*      */               } 
/* 3093 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3097 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.TOME_OF_KNOWLEDGE_SHARING, "TOME_OF_KNOWLEDGE_SHARING", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, new ItemStack(Material.FEATHER), null, new ItemStack(Material.INK_SACK), SlimefunItems.MAGICAL_BOOK_COVER, new ItemStack(Material.GLASS_BOTTLE), null, new ItemStack(Material.BOOK_AND_QUILL), null
/*      */         
/* 3099 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 3103 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.TOME_OF_KNOWLEDGE_SHARING, true)) {
/* 3104 */                 List<String> lore = item.getItemMeta().getLore();
/* 3105 */                 lore.set(0, ChatColor.translateAlternateColorCodes('&', "&7Owner: &b" + p.getName()));
/* 3106 */                 lore.set(1, ChatColor.BLACK + "" + p.getUniqueId());
/* 3107 */                 ItemMeta im = item.getItemMeta();
/* 3108 */                 im.setLore(lore);
/* 3109 */                 item.setItemMeta(im);
/* 3110 */                 p.setItemInHand(item);
/* 3111 */                 p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
/* 3112 */                 return true;
/*      */               } 
/* 3114 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.TOME_OF_KNOWLEDGE_SHARING, false)) {
/* 3115 */                 List<Research> researches = Research.getResearches(ChatColor.stripColor(item.getItemMeta().getLore().get(1)));
/* 3116 */                 for (Research research : researches) {
/* 3117 */                   research.unlock(p, true);
/*      */                 }
/* 3119 */                 PlayerInventory.consumeItemInHand(p);
/* 3120 */                 return true;
/*      */               } 
/* 3122 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3126 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.FLASK_OF_KNOWLEDGE, "FLASK_OF_KNOWLEDGE", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, null, null, SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.THIN_GLASS), SlimefunItems.MAGIC_LUMP_2, null, SlimefunItems.MAGIC_LUMP_2, null }, (ItemStack)new CustomItem(SlimefunItems.FLASK_OF_KNOWLEDGE, 8)))
/*      */       
/* 3128 */       .register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 3132 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.FLASK_OF_KNOWLEDGE, true) && p.getLevel() >= 1) {
/* 3133 */                 p.setLevel(p.getLevel() - 1);
/* 3134 */                 p.getInventory().addItem(new ItemStack[] { (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0) });
/* 3135 */                 PlayerInventory.consumeItemInHand(p);
/* 3136 */                 PlayerInventory.update(p);
/* 3137 */                 return true;
/*      */               } 
/* 3139 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3143 */     (new SlimefunItem((Category)Categories.BIRTHDAY, (ItemStack)new CustomItem(new MaterialData(Material.CAKE), "&b生日蛋糕", new String[0]), "BIRTHDAY_CAKE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.TORCH), null, new ItemStack(Material.SUGAR), new ItemStack(Material.CAKE), new ItemStack(Material.SUGAR), null, null, null
/*      */         
/* 3145 */         })).register(true);
/*      */     
/* 3147 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_MILK, "CHRISTMAS_MILK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.GLASS_BOTTLE), null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_MILK, 4)))
/*      */       
/* 3149 */       .register(true);
/*      */     
/* 3151 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_CHOCOLATE_MILK, "CHRISTMAS_CHOCOLATE_MILK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CHRISTMAS_MILK, (new MaterialData(Material.INK_SACK, (byte)3))
/* 3152 */           .toItemStack(1), null, null, null, null, null, null, null }(ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_CHOCOLATE_MILK, 2)))
/* 3153 */       .register(true);
/*      */     
/* 3155 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_EGG_NOG, "CHRISTMAS_EGG_NOG", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CHRISTMAS_MILK, new ItemStack(Material.EGG), null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_EGG_NOG, 2)))
/*      */       
/* 3157 */       .register(true);
/*      */     
/* 3159 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_APPLE_CIDER, "CHRISTMAS_APPLE_CIDER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.APPLE_JUICE, new ItemStack(Material.SUGAR), null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_APPLE_CIDER, 2)))
/*      */       
/* 3161 */       .register(true);
/*      */     
/* 3163 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_COOKIE, "CHRISTMAS_COOKIE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.COOKIE), new ItemStack(Material.SUGAR), (new MaterialData(Material.INK_SACK, (byte)10))
/* 3164 */           .toItemStack(1), null, null, null, null, null, null }(ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_COOKIE, 16)))
/* 3165 */       .register(true);
/*      */     
/* 3167 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_FRUIT_CAKE, "CHRISTMAS_FRUIT_CAKE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.EGG), new ItemStack(Material.APPLE), new ItemStack(Material.MELON), new ItemStack(Material.SUGAR), null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_FRUIT_CAKE, 4)))
/*      */       
/* 3169 */       .register(true);
/*      */     
/* 3171 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_APPLE_PIE, "CHRISTMAS_APPLE_PIE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.SUGAR), new ItemStack(Material.APPLE), new ItemStack(Material.EGG), null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_APPLE_PIE, 2)))
/*      */       
/* 3173 */       .register(true);
/*      */     
/* 3175 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_HOT_CHOCOLATE, "CHRISTMAS_HOT_CHOCOLATE", RecipeType.SMELTERY, new ItemStack[] { SlimefunItems.CHRISTMAS_CHOCOLATE_MILK, null, null, null, null, null, null, null, null }, SlimefunItems.CHRISTMAS_HOT_CHOCOLATE))
/*      */       
/* 3177 */       .register(true);
/*      */     
/* 3179 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_CAKE, "CHRISTMAS_CAKE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.MILK_BUCKET), null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_CAKE, 4)))
/*      */       
/* 3181 */       .register(true);
/*      */     
/* 3183 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_CARAMEL, "CHRISTMAS_CARAMEL", RecipeType.SMELTERY, new ItemStack[] { new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), null, null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_CARAMEL, 4)))
/*      */       
/* 3185 */       .register(true);
/*      */     
/* 3187 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_CARAMEL_APPLE, "CHRISTMAS_CARAMEL_APPLE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.SUGAR), null, null, new ItemStack(Material.APPLE), null, null, new ItemStack(Material.STICK), null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_CARAMEL_APPLE, 2)))
/*      */       
/* 3189 */       .register(true);
/*      */     
/* 3191 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_CHOCOLATE_APPLE, "CHRISTMAS_CHOCOLATE_APPLE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, (ItemStack)new CustomItem(Material.INK_SACK, 3), null, null, new ItemStack(Material.APPLE), null, null, new ItemStack(Material.STICK), null }(ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_CARAMEL_APPLE, 2)))
/*      */       
/* 3193 */       .register(true);
/*      */     
/* 3195 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.CHRISTMAS_PRESENT, "CHRISTMAS_PRESENT", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, new ItemStack(Material.NAME_TAG), null, (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)14), 1), (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)13), 1), (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)14), 1), (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)14), 1), (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)13), 1), (ItemStack)new CustomItem(new MaterialData(Material.WOOL, (byte)14), 1)
/*      */         
/* 3197 */         })).register(true);
/*      */     
/* 3199 */     (new SlimefunItem((Category)Categories.EASTER, SlimefunItems.EASTER_CARROT_PIE, "EASTER_CARROT_PIE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.SUGAR), new ItemStack(Material.CARROT_ITEM), new ItemStack(Material.EGG), null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.EASTER_CARROT_PIE, 2)))
/*      */       
/* 3201 */       .register(true);
/*      */     
/* 3203 */     (new SlimefunItem((Category)Categories.EASTER, SlimefunItems.CHRISTMAS_APPLE_PIE, "EASTER_APPLE_PIE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.SUGAR), new ItemStack(Material.APPLE), new ItemStack(Material.EGG), null, null, null, null, null, null }, (ItemStack)new CustomItem(SlimefunItems.CHRISTMAS_APPLE_PIE, 2)))
/*      */       
/* 3205 */       .register(true);
/*      */     
/* 3207 */     (new SlimefunItem((Category)Categories.EASTER, SlimefunItems.EASTER_EGG, "EASTER_EGG", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, (new MaterialData(Material.INK_SACK, (byte)10))
/* 3208 */           .toItemStack(1), new ItemStack(Material.EGG), (new MaterialData(Material.INK_SACK, (byte)13)).toItemStack(1), null, null, null }(ItemStack)new CustomItem(SlimefunItems.EASTER_EGG, 2)))
/* 3209 */       .register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 3213 */               if (SlimefunManager.isItemSimiliar(item, SlimefunItems.EASTER_EGG, true)) {
/* 3214 */                 e.setCancelled(true);
/* 3215 */                 PlayerInventory.consumeItemInHand(e.getPlayer());
/* 3216 */                 FireworkShow.launchRandom(e.getPlayer(), 2);
/*      */                 
/* 3218 */                 List<ItemStack> gifts = new ArrayList<>();
/* 3219 */                 for (int i = 0; i < 2; i++) {
/* 3220 */                   gifts.add(new CustomItem(SlimefunItems.EASTER_CARROT_PIE, 4));
/* 3221 */                   gifts.add(new CustomItem(SlimefunItems.CHRISTMAS_APPLE_PIE, 4));
/* 3222 */                   gifts.add(new CustomItem(SlimefunItems.CARROT_JUICE, 1));
/*      */                 } 
/*      */                 
/* 3225 */                 gifts.add(new SkullItem("mrCookieSlime"));
/* 3226 */                 gifts.add(new SkullItem("timtower"));
/* 3227 */                 gifts.add(new SkullItem("bwfcwalshy"));
/* 3228 */                 gifts.add(new SkullItem("jadedcat"));
/* 3229 */                 gifts.add(new SkullItem("ZeldoKavira"));
/* 3230 */                 gifts.add(new SkullItem("eyamaz"));
/* 3231 */                 gifts.add(new SkullItem("Kaelten"));
/* 3232 */                 gifts.add(new SkullItem("Myrathi"));
/*      */                 
/* 3234 */                 p.getWorld().dropItemNaturally(p.getLocation(), gifts.get(SlimefunStartup.randomize(gifts.size())));
/* 3235 */                 return true;
/*      */               } 
/* 3237 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3241 */     (new SlimefunItem(Categories.MISC, SlimefunItems.REINFORCED_PLATE, "REINFORCED_PLATE", RecipeType.COMPRESSOR, new ItemStack[] { (ItemStack)new CustomItem(SlimefunItems.REINFORCED_ALLOY_INGOT, 8), null, null, null, null, null, null, null, null
/*      */         
/* 3243 */         })).register(true);
/*      */     
/* 3245 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.HARDENED_GLASS, "HARDENED_GLASS", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), SlimefunItems.REINFORCED_PLATE, new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS) }, (ItemStack)new CustomItem(SlimefunItems.HARDENED_GLASS, 16)))
/*      */ 
/*      */       
/* 3248 */       .register(true);
/*      */     
/* 3250 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.SOLAR_ARRAY, "SOLAR_ARRAY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS
/*      */         
/* 3252 */         })).register(true);
/*      */     
/* 3254 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.COOLING_UNIT, "COOLING_UNIT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.ICE), new ItemStack(Material.ICE), new ItemStack(Material.ICE), SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ALUMINUM_INGOT, new ItemStack(Material.ICE), new ItemStack(Material.ICE), new ItemStack(Material.ICE)
/*      */         
/* 3256 */         })).register(true);
/*      */     
/* 3258 */     (new SlimefunBackpack(27, Categories.PORTABLE, SlimefunItems.COOLER, "COOLER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.COOLING_UNIT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT
/*      */         
/* 3260 */         })).register(true);
/*      */     
/* 3262 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.WITHER_PROOF_OBSIDIAN, "WITHER_PROOF_OBSIDIAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, new ItemStack(Material.OBSIDIAN), SlimefunItems.LEAD_INGOT, new ItemStack(Material.OBSIDIAN), SlimefunItems.HARDENED_GLASS, new ItemStack(Material.OBSIDIAN), SlimefunItems.LEAD_INGOT, new ItemStack(Material.OBSIDIAN), SlimefunItems.LEAD_INGOT }, (ItemStack)new CustomItem(SlimefunItems.WITHER_PROOF_OBSIDIAN, 4)))
/*      */ 
/*      */       
/* 3265 */       .register(true);
/*      */     
/* 3267 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.ANCIENT_PEDESTAL, "ANCIENT_PEDESTAL", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.OBSIDIAN), SlimefunItems.GOLD_8K, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.STONE), null, new ItemStack(Material.OBSIDIAN), SlimefunItems.GOLD_8K, new ItemStack(Material.OBSIDIAN) }, (ItemStack)new CustomItem(SlimefunItems.ANCIENT_PEDESTAL, 4)))
/*      */       
/* 3269 */       .register(true);
/*      */     
/* 3271 */     SlimefunItem.registerBlockHandler("ANCIENT_PEDESTAL", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 3279 */             Item stack = AncientAltarListener.findItem(b);
/* 3280 */             if (stack != null) {
/* 3281 */               p.sendMessage("§c请先拿走物品再拆除祭坛灵柱");
/* 3282 */               return false;
/*      */             } 
/* 3284 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 3288 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.ANCIENT_ALTAR, "ANCIENT_ALTAR", RecipeType.MAGIC_WORKBENCH, new ItemStack[] { null, new ItemStack(Material.ENCHANTMENT_TABLE), null, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.GOLD_8K, SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.OBSIDIAN), SlimefunItems.GOLD_8K, new ItemStack(Material.OBSIDIAN)
/*      */         
/* 3290 */         })).register(true);
/*      */ 
/*      */ 
/*      */     
/* 3294 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.ENERGY_REGULATOR, "ENERGY_REGULATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SILVER_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.SILVER_INGOT
/*      */         
/* 3296 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public boolean isSynchronized()
/*      */             {
/* 3300 */               return false;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void uniqueTick() {}
/*      */ 
/*      */             
/*      */             public void tick(Block b, SlimefunItem item, Config data) {
/* 3309 */               EnergyNet.getNetworkFromLocationOrCreate(b.getLocation()).tick(b);
/*      */             }
/*      */           } });
/*      */     
/* 3313 */     SlimefunItem.registerBlockHandler("ENERGY_REGULATOR", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 3321 */             EnergyHologram.remove(b);
/* 3322 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 3326 */     (new SlimefunItem(Categories.MISC, SlimefunItems.DUCT_TAPE, "DUCT_TAPE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.ALUMINUM_DUST, SlimefunItems.ALUMINUM_DUST, SlimefunItems.ALUMINUM_DUST, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.WOOL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.PAPER), new ItemStack(Material.PAPER), new ItemStack(Material.PAPER) }, (ItemStack)new CustomItem(SlimefunItems.DUCT_TAPE, 2)))
/*      */       
/* 3328 */       .register(true);
/*      */     
/* 3330 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.SMALL_CAPACITOR, "SMALL_CAPACITOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.DURALUMIN_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.DURALUMIN_INGOT, new ItemStack(Material.REDSTONE), SlimefunItems.SULFATE, new ItemStack(Material.REDSTONE), SlimefunItems.DURALUMIN_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.DURALUMIN_INGOT
/*      */         
/* 3332 */         })).registerDistibutingCapacitor(true, 128);
/*      */     
/* 3334 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.MEDIUM_CAPACITOR, "MEDIUM_CAPACITOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BILLON_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BILLON_INGOT, new ItemStack(Material.REDSTONE), SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.REDSTONE), SlimefunItems.BILLON_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BILLON_INGOT
/*      */         
/* 3336 */         })).registerDistibutingCapacitor(true, 512);
/*      */     
/* 3338 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.BIG_CAPACITOR, "BIG_CAPACITOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.STEEL_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.STEEL_INGOT, new ItemStack(Material.REDSTONE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.REDSTONE), SlimefunItems.STEEL_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.STEEL_INGOT
/*      */         
/* 3340 */         })).registerDistibutingCapacitor(true, 1024);
/*      */     
/* 3342 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.LARGE_CAPACITOR, "LARGE_CAPACITOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.REDSTONE), SlimefunItems.BIG_CAPACITOR, new ItemStack(Material.REDSTONE), SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REINFORCED_ALLOY_INGOT
/*      */         
/* 3344 */         })).registerDistibutingCapacitor(true, 8192);
/*      */     
/* 3346 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.CARBONADO_EDGED_CAPACITOR, "CARBONADO_EDGED_CAPACITOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CARBONADO, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.CARBONADO, new ItemStack(Material.REDSTONE), SlimefunItems.LARGE_CAPACITOR, new ItemStack(Material.REDSTONE), SlimefunItems.CARBONADO, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.CARBONADO
/*      */         
/* 3348 */         })).registerDistibutingCapacitor(true, 65536);
/*      */     
/* 3350 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.SOLAR_GENERATOR, "SOLAR_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT, null
/*      */         
/* 3352 */         })).register(true, new ItemHandler[] { (ItemHandler)new EnergyTicker()
/*      */           {
/*      */             public double generateEnergy(Location l, SlimefunItem item, Config data)
/*      */             {
/* 3356 */               if (!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4) || l.getBlock().getLightFromSky() != 15) return 0.0D; 
/* 3357 */               if (l.getWorld().getTime() < 12300L || l.getWorld().getTime() > 23850L) return 2.0D; 
/* 3358 */               return 0.0D;
/*      */             }
/*      */ 
/*      */             
/*      */             public boolean explode(Location l) {
/* 3363 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3367 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.SOLAR_GENERATOR_2, "SOLAR_GENERATOR_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SOLAR_GENERATOR, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SOLAR_GENERATOR, SlimefunItems.ALUMINUM_INGOT, new ItemStack(Material.REDSTONE), SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SOLAR_GENERATOR, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SOLAR_GENERATOR
/*      */         
/* 3369 */         })).register(true, new ItemHandler[] { (ItemHandler)new EnergyTicker()
/*      */           {
/*      */             public double generateEnergy(Location l, SlimefunItem item, Config data)
/*      */             {
/* 3373 */               if (!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4) || l.getBlock().getLightFromSky() != 15) return 0.0D; 
/* 3374 */               if (l.getWorld().getTime() < 12300L || l.getWorld().getTime() > 23850L) return 8.0D; 
/* 3375 */               return 0.0D;
/*      */             }
/*      */ 
/*      */             
/*      */             public boolean explode(Location l) {
/* 3380 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3384 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.SOLAR_GENERATOR_3, "SOLAR_GENERATOR_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.CARBONADO, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SOLAR_GENERATOR_2
/*      */         
/* 3386 */         })).register(true, new ItemHandler[] { (ItemHandler)new EnergyTicker()
/*      */           {
/*      */             public double generateEnergy(Location l, SlimefunItem item, Config data)
/*      */             {
/* 3390 */               if (!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4) || l.getBlock().getLightFromSky() != 15) return 0.0D; 
/* 3391 */               if (l.getWorld().getTime() < 12300L || l.getWorld().getTime() > 23850L) return 32.0D; 
/* 3392 */               return 0.0D;
/*      */             }
/*      */ 
/*      */             
/*      */             public boolean explode(Location l) {
/* 3397 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3401 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.SOLAR_GENERATOR_4, "SOLAR_GENERATOR_4", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SOLAR_GENERATOR_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.SOLAR_GENERATOR_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.SOLAR_GENERATOR_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.SOLAR_GENERATOR_3
/*      */         
/* 3403 */         })).register(true, new ItemHandler[] { (ItemHandler)new EnergyTicker()
/*      */           {
/*      */             public double generateEnergy(Location l, SlimefunItem item, Config data)
/*      */             {
/* 3407 */               if (!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4) || l.getBlock().getLightFromSky() != 15) return 0.0D; 
/* 3408 */               if (l.getWorld().getTime() < 12300L || l.getWorld().getTime() > 23850L) return 128.0D; 
/* 3409 */               return 64.0D;
/*      */             }
/*      */ 
/*      */             
/*      */             public boolean explode(Location l) {
/* 3414 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 3418 */     (new ChargingBench((Category)Categories.ELECTRICITY, SlimefunItems.CHARGING_BENCH, "CHARGING_BENCH", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.ELECTRO_MAGNET, null, SlimefunItems.BATTERY, new ItemStack(Material.WORKBENCH), SlimefunItems.BATTERY, null, SlimefunItems.SMALL_CAPACITOR, null
/*      */         
/* 3420 */         })).registerChargeableBlock(true, 128);
/*      */     
/* 3422 */     (new ElectricFurnace((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_FURNACE, "ELECTRIC_FURNACE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.FURNACE), null, SlimefunItems.GILDED_IRON, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3427 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3432 */           return "&b电力熔炉";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3437 */           return 2;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3442 */           return 1;
/*      */         }
/* 3445 */       }).registerChargeableBlock(true, 64);
/*      */     
/* 3447 */     (new ElectricFurnace((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_FURNACE_2, "ELECTRIC_FURNACE_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.GILDED_IRON, SlimefunItems.ELECTRIC_FURNACE, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3452 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3457 */           return "&b电力熔炉";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3462 */           return 3;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3467 */           return 2;
/*      */         }
/* 3470 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3472 */     (new ElectricFurnace((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_FURNACE_3, "ELECTRIC_FURNACE_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.STEEL_INGOT, SlimefunItems.ELECTRIC_FURNACE_2, SlimefunItems.STEEL_INGOT, SlimefunItems.GILDED_IRON, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3477 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3482 */           return "&b电力熔炉";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3487 */           return 5;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3492 */           return 4;
/*      */         }
/* 3495 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3497 */     (new ElectricGoldPan((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_GOLD_PAN, "ELECTRIC_GOLD_PAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GOLD_PAN, null, new ItemStack(Material.FLINT), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.FLINT), SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3502 */           return 1;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3507 */           return 1;
/*      */         }
/* 3509 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3511 */     (new ElectricGoldPan((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_GOLD_PAN_2, "ELECTRIC_GOLD_PAN_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GOLD_PAN, null, new ItemStack(Material.IRON_INGOT), SlimefunItems.ELECTRIC_GOLD_PAN, new ItemStack(Material.IRON_INGOT), SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DURALUMIN_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3516 */           return 2;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3521 */           return 3;
/*      */         }
/* 3523 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3525 */     (new ElectricGoldPan((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_GOLD_PAN_3, "ELECTRIC_GOLD_PAN_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GOLD_PAN, null, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_GOLD_PAN_2, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COBALT_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.COBALT_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3530 */           return 7;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3535 */           return 10;
/*      */         }
/* 3537 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 3539 */     (new ElectricDustWasher((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_DUST_WASHER, "ELECTRIC_DUST_WASHER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.WATER_BUCKET), null, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRIC_GOLD_PAN, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT, SlimefunItems.COPPER_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3544 */           return 3;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3549 */           return 1;
/*      */         }
/* 3551 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3553 */     (new ElectricDustWasher((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_DUST_WASHER_2, "ELECTRIC_DUST_WASHER_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.WATER_BUCKET), null, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRIC_DUST_WASHER, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3558 */           return 5;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3563 */           return 2;
/*      */         }
/* 3565 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3567 */     (new ElectricDustWasher((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_DUST_WASHER_3, "ELECTRIC_DUST_WASHER_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.WATER_BUCKET), null, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRIC_DUST_WASHER_2, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.CORINTHIAN_BRONZE_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3572 */           return 15;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3577 */           return 10;
/*      */         }
/* 3579 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 3581 */     (new ElectricIngotFactory((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_INGOT_FACTORY, "ELECTRIC_INGOT_FACTORY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.FLINT_AND_STEEL), null, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_DUST_WASHER, SlimefunItems.HEATING_COIL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DAMASCUS_STEEL_INGOT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3586 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3591 */           return "&c电力制锭机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3596 */           return 4;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3601 */           return 1;
/*      */         }
/* 3604 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 3606 */     (new ElectricIngotFactory((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_INGOT_FACTORY_2, "ELECTRIC_INGOT_FACTORY_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GILDED_IRON, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.GILDED_IRON, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_INGOT_FACTORY, SlimefunItems.HEATING_COIL, SlimefunItems.BRASS_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BRASS_INGOT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3611 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3616 */           return "&c电力制锭机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3621 */           return 7;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3626 */           return 2;
/*      */         }
/* 3629 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 3631 */     (new ElectricIngotFactory((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_INGOT_FACTORY_3, "ELECTRIC_INGOT_FACTORY_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GILDED_IRON, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.GILDED_IRON, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_INGOT_FACTORY_2, SlimefunItems.HEATING_COIL, SlimefunItems.BRASS_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BRASS_INGOT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3636 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3641 */           return "&c电力制锭机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3646 */           return 20;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3651 */           return 8;
/*      */         }
/* 3654 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 3656 */     (new ElectrifiedCrucible((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIFIED_CRUCIBLE, "ELECTRIFIED_CRUCIBLE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.CRUCIBLE, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LARGE_CAPACITOR, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3661 */           return 24;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3666 */           return 1;
/*      */         }
/* 3669 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 3671 */     (new ElectrifiedCrucible((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIFIED_CRUCIBLE_2, "ELECTRIFIED_CRUCIBLE_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.ELECTRIFIED_CRUCIBLE, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3676 */           return 40;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3681 */           return 2;
/*      */         }
/* 3684 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 3686 */     (new ElectrifiedCrucible((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIFIED_CRUCIBLE_3, "ELECTRIFIED_CRUCIBLE_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.ELECTRIFIED_CRUCIBLE_2, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.STEEL_PLATE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_PLATE, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 3691 */           return 60;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3696 */           return 4;
/*      */         }
/* 3699 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 3701 */     (new AContainer((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_ORE_GRINDER, "ELECTRIC_ORE_GRINDER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.DIAMOND_PICKAXE), null, SlimefunItems.GILDED_IRON, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         public void registerDefaultRecipes() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 3710 */           return new ItemStack(Material.STONE_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3715 */           return "&b电力碎矿机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3720 */           return 6;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3725 */           return 1;
/*      */         }
/*      */ 
/*      */         
/*      */         public String getMachineIdentifier() {
/* 3730 */           return "ELECTRIC_ORE_GRINDER";
/*      */         }
/* 3733 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3735 */     (new AContainer((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_ORE_GRINDER_2, "ELECTRIC_ORE_GRINDER_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.DIAMOND_PICKAXE), null, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_ORE_GRINDER, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         public void registerDefaultRecipes() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 3744 */           return new ItemStack(Material.DIAMOND_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3749 */           return "&b电力碎矿机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3754 */           return 15;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3759 */           return 4;
/*      */         }
/*      */ 
/*      */         
/*      */         public String getMachineIdentifier() {
/* 3764 */           return "ELECTRIC_ORE_GRINDER";
/*      */         }
/* 3767 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 3769 */     (new HeatedPressureChamber((Category)Categories.ELECTRICITY, SlimefunItems.HEATED_PRESSURE_CHAMBER, "HEATED_PRESSURE_CHAMBER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, new ItemStack(Material.GLASS), SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3774 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3779 */           return 5;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3784 */           return 1;
/*      */         }
/* 3787 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3789 */     (new HeatedPressureChamber((Category)Categories.ELECTRICITY, SlimefunItems.HEATED_PRESSURE_CHAMBER_2, "HEATED_PRESSURE_CHAMBER_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.HEATED_PRESSURE_CHAMBER, SlimefunItems.LEAD_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.REINFORCED_ALLOY_INGOT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 3794 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3799 */           return 22;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3804 */           return 5;
/*      */         }
/* 3807 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 3809 */     (new AContainer((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_INGOT_PULVERIZER, "ELECTRIC_INGOT_PULVERIZER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.ELECTRIC_ORE_GRINDER, null, SlimefunItems.LEAD_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         public String getInventoryTitle()
/*      */         {
/* 3813 */           return "&b电力碎锭机";
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 3818 */           return new ItemStack(Material.IRON_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public void registerDefaultRecipes() {
/* 3823 */           registerRecipe(3, new ItemStack[] { SlimefunItems.ALUMINUM_INGOT }, new ItemStack[] { SlimefunItems.ALUMINUM_DUST });
/* 3824 */           registerRecipe(3, new ItemStack[] { SlimefunItems.COPPER_INGOT }, new ItemStack[] { SlimefunItems.COPPER_DUST });
/* 3825 */           registerRecipe(3, new ItemStack[] { SlimefunItems.GOLD_4K }, new ItemStack[] { SlimefunItems.GOLD_DUST });
/* 3826 */           registerRecipe(3, new ItemStack[] { new ItemStack(Material.IRON_INGOT) }, new ItemStack[] { SlimefunItems.IRON_DUST });
/* 3827 */           registerRecipe(3, new ItemStack[] { SlimefunItems.LEAD_INGOT }, new ItemStack[] { SlimefunItems.LEAD_DUST });
/* 3828 */           registerRecipe(3, new ItemStack[] { SlimefunItems.MAGNESIUM_INGOT }, new ItemStack[] { SlimefunItems.MAGNESIUM_DUST });
/* 3829 */           registerRecipe(3, new ItemStack[] { SlimefunItems.SILVER_INGOT }, new ItemStack[] { SlimefunItems.SILVER_DUST });
/* 3830 */           registerRecipe(3, new ItemStack[] { SlimefunItems.TIN_INGOT }, new ItemStack[] { SlimefunItems.TIN_DUST });
/* 3831 */           registerRecipe(3, new ItemStack[] { SlimefunItems.ZINC_INGOT }, new ItemStack[] { SlimefunItems.ZINC_DUST });
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3838 */           return 7;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 3843 */           return 1;
/*      */         }
/*      */ 
/*      */         
/*      */         public String getMachineIdentifier() {
/* 3848 */           return "ELECTRIC_INGOT_PULVERIZER";
/*      */         }
/* 3850 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 3852 */     (new AGenerator((Category)Categories.ELECTRICITY, SlimefunItems.COAL_GENERATOR, "COAL_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HEATING_COIL, new ItemStack(Material.FURNACE), SlimefunItems.HEATING_COIL, SlimefunItems.NICKEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.NICKEL_INGOT, null, SlimefunItems.NICKEL_INGOT, null })
/*      */       {
/*      */         
/*      */         public void registerDefaultRecipes()
/*      */         {
/* 3857 */           registerFuel(new MachineFuel(8, (new MaterialData(Material.COAL, (byte)0)).toItemStack(1)));
/* 3858 */           registerFuel(new MachineFuel(8, (new MaterialData(Material.COAL, (byte)1)).toItemStack(1)));
/* 3859 */           registerFuel(new MachineFuel(80, new ItemStack(Material.COAL_BLOCK)));
/* 3860 */           registerFuel(new MachineFuel(12, new ItemStack(Material.BLAZE_ROD)));
/*      */ 
/*      */           
/* 3863 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LOG, (byte)0)).toItemStack(1)));
/* 3864 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LOG, (byte)1)).toItemStack(1)));
/* 3865 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LOG, (byte)2)).toItemStack(1)));
/* 3866 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LOG, (byte)3)).toItemStack(1)));
/* 3867 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LOG_2, (byte)0)).toItemStack(1)));
/* 3868 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LOG_2, (byte)1)).toItemStack(1)));
/*      */ 
/*      */           
/* 3871 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)0)).toItemStack(1)));
/* 3872 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)1)).toItemStack(1)));
/* 3873 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)2)).toItemStack(1)));
/* 3874 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)3)).toItemStack(1)));
/* 3875 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)4)).toItemStack(1)));
/* 3876 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.WOOD, (byte)5)).toItemStack(1)));
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 3881 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3886 */           return "&c煤炭发电机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyProduction() {
/* 3891 */           return 8;
/*      */         }
/* 3894 */       }).registerUnrechargeableBlock(true, 64);
/*      */     
/* 3896 */     (new AGenerator((Category)Categories.ELECTRICITY, SlimefunItems.BIO_REACTOR, "BIO_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HEATING_COIL, SlimefunItems.COMPOSTER, SlimefunItems.HEATING_COIL, SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ALUMINUM_BRASS_INGOT, null, SlimefunItems.ALUMINUM_BRASS_INGOT, null })
/*      */       {
/*      */         
/*      */         public void registerDefaultRecipes()
/*      */         {
/* 3901 */           registerFuel(new MachineFuel(2, new ItemStack(Material.ROTTEN_FLESH)));
/* 3902 */           registerFuel(new MachineFuel(2, new ItemStack(Material.SPIDER_EYE)));
/* 3903 */           registerFuel(new MachineFuel(2, new ItemStack(Material.BONE)));
/* 3904 */           registerFuel(new MachineFuel(3, new ItemStack(Material.APPLE)));
/* 3905 */           registerFuel(new MachineFuel(3, new ItemStack(Material.MELON)));
/* 3906 */           registerFuel(new MachineFuel(27, new ItemStack(Material.MELON_BLOCK)));
/* 3907 */           registerFuel(new MachineFuel(3, new ItemStack(Material.PUMPKIN)));
/* 3908 */           registerFuel(new MachineFuel(3, new ItemStack(Material.PUMPKIN_SEEDS)));
/* 3909 */           registerFuel(new MachineFuel(3, new ItemStack(Material.MELON_SEEDS)));
/* 3910 */           registerFuel(new MachineFuel(3, new ItemStack(Material.WHEAT)));
/* 3911 */           registerFuel(new MachineFuel(3, new ItemStack(Material.SEEDS)));
/* 3912 */           registerFuel(new MachineFuel(3, new ItemStack(Material.CARROT_ITEM)));
/* 3913 */           registerFuel(new MachineFuel(3, new ItemStack(Material.POTATO_ITEM)));
/* 3914 */           registerFuel(new MachineFuel(3, new ItemStack(Material.SUGAR_CANE)));
/* 3915 */           registerFuel(new MachineFuel(3, new ItemStack(Material.NETHER_STALK)));
/* 3916 */           registerFuel(new MachineFuel(2, new ItemStack(Material.YELLOW_FLOWER)));
/* 3917 */           registerFuel(new MachineFuel(2, new ItemStack(Material.RED_ROSE)));
/* 3918 */           registerFuel(new MachineFuel(2, new ItemStack(Material.RED_MUSHROOM)));
/* 3919 */           registerFuel(new MachineFuel(2, new ItemStack(Material.BROWN_MUSHROOM)));
/* 3920 */           registerFuel(new MachineFuel(2, new ItemStack(Material.VINE)));
/* 3921 */           registerFuel(new MachineFuel(2, new ItemStack(Material.CACTUS)));
/* 3922 */           registerFuel(new MachineFuel(2, new ItemStack(Material.WATER_LILY)));
/*      */ 
/*      */           
/* 3925 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LEAVES, (byte)0)).toItemStack(1)));
/* 3926 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LEAVES, (byte)1)).toItemStack(1)));
/* 3927 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LEAVES, (byte)2)).toItemStack(1)));
/* 3928 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LEAVES, (byte)3)).toItemStack(1)));
/* 3929 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LEAVES_2, (byte)0)).toItemStack(1)));
/* 3930 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.LEAVES_2, (byte)1)).toItemStack(1)));
/*      */ 
/*      */           
/* 3933 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.SAPLING, (byte)0)).toItemStack(1)));
/* 3934 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.SAPLING, (byte)1)).toItemStack(1)));
/* 3935 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.SAPLING, (byte)2)).toItemStack(1)));
/* 3936 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.SAPLING, (byte)3)).toItemStack(1)));
/* 3937 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.SAPLING, (byte)4)).toItemStack(1)));
/* 3938 */           registerFuel(new MachineFuel(1, (new MaterialData(Material.SAPLING, (byte)5)).toItemStack(1)));
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 3943 */           return new ItemStack(Material.GOLD_HOE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 3948 */           return "&2生化发电机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyProduction() {
/* 3953 */           return 4;
/*      */         }
/* 3956 */       }).registerUnrechargeableBlock(true, 128);
/*      */     
/* 3958 */     (new AutoEnchanter((Category)Categories.ELECTRICITY, SlimefunItems.AUTO_ENCHANTER, "AUTO_ENCHANTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.ENCHANTMENT_TABLE), null, SlimefunItems.CARBONADO, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARBONADO, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN
/*      */         
/* 3960 */         })).registerChargeableBlock(true, 128);
/*      */     
/* 3962 */     (new AutoDisenchanter((Category)Categories.ELECTRICITY, SlimefunItems.AUTO_DISENCHANTER, "AUTO_DISENCHANTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.REDSTONE), new ItemStack(Material.ANVIL), new ItemStack(Material.REDSTONE), SlimefunItems.CARBONADO, SlimefunItems.AUTO_ENCHANTER, SlimefunItems.CARBONADO, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN
/*      */         
/* 3964 */         })).registerChargeableBlock(true, 128);
/*      */     
/* 3966 */     (new AutoAnvil((Category)Categories.ELECTRICITY, SlimefunItems.AUTO_ANVIL, "AUTO_ANVIL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.ANVIL), null, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK) })
/*      */       {
/*      */         
/*      */         public int getRepairFactor()
/*      */         {
/* 3971 */           return 10;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3976 */           return 12;
/*      */         }
/* 3979 */       }).registerChargeableBlock(true, 128);
/*      */     
/* 3981 */     (new AutoAnvil((Category)Categories.ELECTRICITY, SlimefunItems.AUTO_ANVIL_2, "AUTO_ANVIL_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.AUTO_ANVIL, null, SlimefunItems.STEEL_PLATE, SlimefunItems.HEATING_COIL, SlimefunItems.STEEL_PLATE, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK) })
/*      */       {
/*      */         
/*      */         public int getRepairFactor()
/*      */         {
/* 3986 */           return 4;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 3991 */           return 16;
/*      */         }
/* 3994 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 3996 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.MULTIMETER, "MULTIMETER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.COPPER_INGOT, null, SlimefunItems.COPPER_INGOT, null, SlimefunItems.REDSTONE_ALLOY, null, null, SlimefunItems.GOLD_6K, null
/*      */         
/* 3998 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 4002 */               if (SlimefunManager.isItemSimiliar(e.getItem(), SlimefunItems.MULTIMETER, true)) {
/* 4003 */                 if (e.getClickedBlock() != null && ChargableBlock.isChargable(e.getClickedBlock())) {
/* 4004 */                   e.setCancelled(true);
/* 4005 */                   p.sendMessage("");
/* 4006 */                   p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b存储的电力: &3" + DoubleHandler.getFancyDouble(ChargableBlock.getCharge(e.getClickedBlock())) + " J"));
/* 4007 */                   p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b缓存: &3" + DoubleHandler.getFancyDouble(ChargableBlock.getMaxCharge(e.getClickedBlock())) + " J"));
/* 4008 */                   p.sendMessage("");
/*      */                 } 
/* 4010 */                 return true;
/*      */               } 
/* 4012 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 4016 */     (new SlimefunItem(Categories.MISC, SlimefunItems.PLASTIC_SHEET, "PLASTIC_SHEET", RecipeType.HEATED_PRESSURE_CHAMBER, new ItemStack[] { null, null, null, null, SlimefunItems.BUCKET_OF_OIL, null, null, null, null
/*      */         
/* 4018 */         })).register(true);
/*      */     
/* 4020 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ANDROID_MEMORY_CORE, "ANDROID_MEMORY_CORE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BRASS_INGOT, (new MaterialData(Material.STAINED_GLASS, (byte)1))
/* 4021 */           .toItemStack(1), SlimefunItems.BRASS_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.TIN_DUST, SlimefunItems.POWER_CRYSTAL, SlimefunItems.BRASS_INGOT, (new MaterialData(Material.STAINED_GLASS, (byte)1)).toItemStack(1), SlimefunItems.BRASS_INGOT
/* 4022 */         })).register(true);
/*      */     
/* 4024 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_TRANSMITTER, "GPS_TRANSMITTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.STEEL_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_INGOT
/*      */         
/* 4026 */         })).registerChargeableBlock(true, 16, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void tick(Block b, SlimefunItem item, Config data)
/*      */             {
/* 4030 */               int charge = ChargableBlock.getCharge(b);
/* 4031 */               if (charge > 0) {
/* 4032 */                 Slimefun.getGPSNetwork().updateTransmitter(b, UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.ONLINE);
/* 4033 */                 ChargableBlock.setCharge(b, charge - 1);
/*      */               } else {
/* 4035 */                 Slimefun.getGPSNetwork().updateTransmitter(b, UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*      */             public void uniqueTick() {}
/*      */ 
/*      */             
/*      */             public boolean isSynchronized() {
/* 4044 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 4048 */     SlimefunItem.registerBlockHandler("GPS_TRANSMITTER", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4052 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4057 */             Slimefun.getGPSNetwork().updateTransmitter(b, UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/* 4058 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4062 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_TRANSMITTER_2, "GPS_TRANSMITTER_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GPS_TRANSMITTER, SlimefunItems.BRONZE_INGOT, SlimefunItems.GPS_TRANSMITTER, SlimefunItems.BRONZE_INGOT, SlimefunItems.CARBON, SlimefunItems.BRONZE_INGOT, SlimefunItems.GPS_TRANSMITTER, SlimefunItems.BRONZE_INGOT, SlimefunItems.GPS_TRANSMITTER
/*      */         
/* 4064 */         })).registerChargeableBlock(true, 64, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void tick(Block b, SlimefunItem item, Config data)
/*      */             {
/* 4068 */               int charge = ChargableBlock.getCharge(b);
/* 4069 */               if (charge > 2) {
/* 4070 */                 Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 4 + 100), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.ONLINE);
/* 4071 */                 ChargableBlock.setCharge(b, charge - 3);
/*      */               } else {
/*      */                 
/* 4074 */                 Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 4 + 100), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/*      */               } 
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void uniqueTick() {}
/*      */ 
/*      */             
/*      */             public boolean isSynchronized() {
/* 4084 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 4088 */     SlimefunItem.registerBlockHandler("GPS_TRANSMITTER_2", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4092 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4097 */             Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 4 + 100), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/* 4098 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4102 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_TRANSMITTER_3, "GPS_TRANSMITTER_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GPS_TRANSMITTER_2, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.GPS_TRANSMITTER_2, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.CARBONADO, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.GPS_TRANSMITTER_2, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.GPS_TRANSMITTER_2
/*      */         
/* 4104 */         })).registerChargeableBlock(true, 256, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void tick(Block b, SlimefunItem item, Config data)
/*      */             {
/* 4108 */               int charge = ChargableBlock.getCharge(b);
/* 4109 */               if (charge > 10) {
/* 4110 */                 Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 16 + 500), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.ONLINE);
/* 4111 */                 ChargableBlock.setCharge(b, charge - 11);
/*      */               } else {
/*      */                 
/* 4114 */                 Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 16 + 500), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/*      */               } 
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void uniqueTick() {}
/*      */ 
/*      */             
/*      */             public boolean isSynchronized() {
/* 4124 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 4128 */     SlimefunItem.registerBlockHandler("GPS_TRANSMITTER_3", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4132 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4137 */             Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 16 + 500), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/* 4138 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4142 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_TRANSMITTER_4, "GPS_TRANSMITTER_4", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GPS_TRANSMITTER_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.GPS_TRANSMITTER_3, SlimefunItems.NICKEL_INGOT, SlimefunItems.CARBONADO, SlimefunItems.NICKEL_INGOT, SlimefunItems.GPS_TRANSMITTER_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.GPS_TRANSMITTER_3
/*      */         
/* 4144 */         })).registerChargeableBlock(true, 1024, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void tick(Block b, SlimefunItem item, Config data)
/*      */             {
/* 4148 */               int charge = ChargableBlock.getCharge(b);
/* 4149 */               if (charge > 45) {
/* 4150 */                 Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 64 + 800), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.ONLINE);
/* 4151 */                 ChargableBlock.setCharge(b, charge - 46);
/*      */               } else {
/*      */                 
/* 4154 */                 Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 64 + 800), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/*      */               } 
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void uniqueTick() {}
/*      */ 
/*      */             
/*      */             public boolean isSynchronized() {
/* 4164 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 4168 */     SlimefunItem.registerBlockHandler("GPS_TRANSMITTER_4", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4172 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4177 */             Slimefun.getGPSNetwork().updateTransmitter((new Location(b.getWorld(), b.getX(), (b.getY() * 64 + 800), b.getZ())).getBlock(), UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), NetworkStatus.OFFLINE);
/* 4178 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4182 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_CONTROL_PANEL, "GPS_CONTROL_PANEL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.COBALT_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COBALT_INGOT, SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ALUMINUM_BRASS_INGOT
/*      */         
/* 4184 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack stack)
/*      */             {
/* 4188 */               if (e.getClickedBlock() == null) return false; 
/* 4189 */               SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 4190 */               if (item == null || !item.getName().equals("GPS_CONTROL_PANEL")) return false; 
/* 4191 */               e.setCancelled(true);
/*      */               try {
/* 4193 */                 Slimefun.getGPSNetwork().openTransmitterControlPanel(p);
/* 4194 */               } catch (Exception e1) {
/* 4195 */                 e1.printStackTrace();
/*      */               } 
/* 4197 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 4201 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_MARKER_TOOL, "GPS_MARKER_TOOL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, SlimefunItems.ELECTRO_MAGNET, (new MaterialData(Material.INK_SACK, (byte)4))
/* 4202 */           .toItemStack(1), SlimefunItems.BASIC_CIRCUIT_BOARD, (new MaterialData(Material.INK_SACK, (byte)4)).toItemStack(1), new ItemStack(Material.REDSTONE), SlimefunItems.REDSTONE_ALLOY, new ItemStack(Material.REDSTONE)
/* 4203 */         })).register(true);
/*      */     
/* 4205 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_EMERGENCY_TRANSMITTER, "GPS_EMERGENCY_TRANSMITTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.ELECTRO_MAGNET, null, null, SlimefunItems.GPS_TRANSMITTER, null, null, SlimefunItems.ESSENCE_OF_AFTERLIFE, null
/*      */         
/* 4207 */         })).register(true);
/*      */     
/* 4209 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID, "PROGRAMMABLE_ANDROID", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.PLASTIC_SHEET, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.PLASTIC_SHEET, SlimefunItems.COAL_GENERATOR, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.CHEST), SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4214 */           return AndroidType.NONE;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4219 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4224 */           return 1;
/*      */         }
/* 4228 */       }).register(true);
/*      */     
/* 4230 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_MINER, "PROGRAMMABLE_ANDROID_MINER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.DIAMOND_PICKAXE), SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_PICKAXE), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4235 */           return AndroidType.MINER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4240 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4245 */           return 1;
/*      */         }
/* 4249 */       }).register(true);
/*      */     
/* 4251 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_FARMER, "PROGRAMMABLE_ANDROID_FARMER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.DIAMOND_HOE), SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_HOE), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4256 */           return AndroidType.FARMER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4261 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4266 */           return 1;
/*      */         }
/* 4270 */       }).register(true);
/*      */     
/* 4272 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_WOODCUTTER, "PROGRAMMABLE_ANDROID_WOODCUTTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.DIAMOND_AXE), SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_AXE), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4277 */           return AndroidType.WOODCUTTER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4282 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4287 */           return 1;
/*      */         }
/* 4291 */       }).register(true);
/*      */     
/* 4293 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_FISHERMAN, "PROGRAMMABLE_ANDROID_FISHERMAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.FISHING_ROD), SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.FISHING_ROD), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4298 */           return AndroidType.FISHERMAN;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4303 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4308 */           return 1;
/*      */         }
/* 4312 */       }).register(true);
/*      */     
/* 4314 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_BUTCHER, "PROGRAMMABLE_ANDROID_BUTCHER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GPS_TRANSMITTER, null, new ItemStack(Material.DIAMOND_SWORD), SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_SWORD), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4319 */           return AndroidType.FIGHTER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4324 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4329 */           return 1;
/*      */         }
/* 4333 */       }).register(true);
/*      */     
/* 4335 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.ANDROID_INTERFACE_ITEMS, "ANDROID_INTERFACE_ITEMS", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.BASIC_CIRCUIT_BOARD, (new MaterialData(Material.STAINED_GLASS, (byte)11))
/* 4336 */           .toItemStack(1), SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
/* 4337 */         })).register(true);
/*      */     
/* 4339 */     (new SlimefunItem((Category)Categories.ELECTRICITY, SlimefunItems.ANDROID_INTERFACE_FUEL, "ANDROID_INTERFACE_FUEL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, (new MaterialData(Material.STAINED_GLASS, (byte)14))
/* 4340 */           .toItemStack(1), SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
/* 4341 */         })).register(true);
/*      */ 
/*      */     
/* 4344 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_2, "PROGRAMMABLE_ANDROID_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.PLASTIC_SHEET, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.PLASTIC_SHEET, SlimefunItems.COMBUSTION_REACTOR, SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.CHEST), SlimefunItems.PLASTIC_SHEET, SlimefunItems.POWER_CRYSTAL, SlimefunItems.PLASTIC_SHEET })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4349 */           return AndroidType.NONE;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4354 */           return 1.5F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4359 */           return 2;
/*      */         }
/* 4363 */       }).register(true);
/*      */     
/* 4365 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_2_FISHERMAN, "PROGRAMMABLE_ANDROID_2_FISHERMAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.FISHING_ROD), SlimefunItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.FISHING_ROD), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4370 */           return AndroidType.FISHERMAN;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4375 */           return 1.5F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4380 */           return 2;
/*      */         }
/* 4384 */       }).register(true);
/*      */     
/* 4386 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_2_BUTCHER, "PROGRAMMABLE_ANDROID_2_BUTCHER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GPS_TRANSMITTER, null, new ItemStack(Material.DIAMOND_SWORD), SlimefunItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.DIAMOND_SWORD), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4391 */           return AndroidType.FIGHTER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4396 */           return 1.5F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4401 */           return 2;
/*      */         }
/* 4405 */       }).register(true);
/*      */     
/* 4407 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_2_FARMER, "PROGRAMMABLE_ANDROID_2_FARMER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GPS_TRANSMITTER, null, new ItemStack(Material.DIAMOND_HOE), SlimefunItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.DIAMOND_HOE), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4412 */           return AndroidType.ADVANCED_FARMER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4417 */           return 1.5F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4422 */           return 2;
/*      */         }
/* 4426 */       }).register(true);
/*      */ 
/*      */     
/* 4429 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_3, "PROGRAMMABLE_ANDROID_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.PLASTIC_SHEET, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.PLASTIC_SHEET, SlimefunItems.NUCLEAR_REACTOR, SlimefunItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.CHEST), SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.POWER_CRYSTAL, SlimefunItems.BLISTERING_INGOT_3 })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4434 */           return AndroidType.NONE;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4439 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4444 */           return 3;
/*      */         }
/* 4448 */       }).register(true);
/*      */     
/* 4450 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_3_FISHERMAN, "PROGRAMMABLE_ANDROID_3_FISHERMAN", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, new ItemStack(Material.FISHING_ROD), SlimefunItems.PROGRAMMABLE_ANDROID_3, new ItemStack(Material.FISHING_ROD), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4455 */           return AndroidType.FISHERMAN;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4460 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4465 */           return 3;
/*      */         }
/* 4469 */       }).register(true);
/*      */     
/* 4471 */     (new ProgrammableAndroid((Category)Categories.ELECTRICITY, SlimefunItems.PROGRAMMABLE_ANDROID_3_BUTCHER, "PROGRAMMABLE_ANDROID_3_BUTCHER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GPS_TRANSMITTER_3, null, new ItemStack(Material.DIAMOND_SWORD), SlimefunItems.PROGRAMMABLE_ANDROID_3, new ItemStack(Material.DIAMOND_SWORD), null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public AndroidType getAndroidType()
/*      */         {
/* 4476 */           return AndroidType.FIGHTER;
/*      */         }
/*      */ 
/*      */         
/*      */         public float getFuelEfficiency() {
/* 4481 */           return 1.0F;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getTier() {
/* 4486 */           return 3;
/*      */         }
/* 4490 */       }).register(true);
/*      */     
/* 4492 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.BLANK_RUNE, "BLANK_RUNE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.STONE), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.STONE), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.OBSIDIAN), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.STONE), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.STONE)
/*      */         
/* 4494 */         })).register(true);
/*      */     
/* 4496 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.RUNE_AIR, "ANCIENT_RUNE_AIR", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.FEATHER), new ItemStack(Material.GHAST_TEAR), SlimefunItems.BLANK_RUNE, new ItemStack(Material.GHAST_TEAR), new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.FEATHER) }, (ItemStack)new CustomItem(SlimefunItems.RUNE_AIR, 4)))
/*      */       
/* 4498 */       .register(true);
/*      */     
/* 4500 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.RUNE_EARTH, "ANCIENT_RUNE_EARTH", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.DIRT), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.STONE), new ItemStack(Material.OBSIDIAN), SlimefunItems.BLANK_RUNE, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.STONE), SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.DIRT) }, (ItemStack)new CustomItem(SlimefunItems.RUNE_EARTH, 4)))
/*      */       
/* 4502 */       .register(true);
/*      */     
/* 4504 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.RUNE_FIRE, "ANCIENT_RUNE_FIRE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.FIREBALL), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.FIREBALL), new ItemStack(Material.BLAZE_POWDER), SlimefunItems.RUNE_EARTH, new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.FIREBALL), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.FIREBALL) }, (ItemStack)new CustomItem(SlimefunItems.RUNE_FIRE, 4)))
/*      */       
/* 4506 */       .register(true);
/*      */     
/* 4508 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.RUNE_WATER, "ANCIENT_RUNE_WATER", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.RAW_FISH), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.SAND), SlimefunItems.BLANK_RUNE, new ItemStack(Material.SAND), new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.RAW_FISH) }, (ItemStack)new CustomItem(SlimefunItems.RUNE_WATER, 4)))
/*      */       
/* 4510 */       .register(true);
/*      */     
/* 4512 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.RUNE_ENDER, "ANCIENT_RUNE_ENDER", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.ENDER_PEARL), SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.EYE_OF_ENDER), SlimefunItems.BLANK_RUNE, new ItemStack(Material.EYE_OF_ENDER), new ItemStack(Material.ENDER_PEARL), SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.ENDER_PEARL) }, (ItemStack)new CustomItem(SlimefunItems.RUNE_ENDER, 6)))
/*      */       
/* 4514 */       .register(true);
/*      */     
/* 4516 */     (new SlimefunItem(Categories.LUMPS_AND_MAGIC, SlimefunItems.RUNE_RAINBOW, "ANCIENT_RUNE_RAINBOW", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4517 */           .toItemStack(1), SlimefunItems.MAGIC_LUMP_3, (new MaterialData(Material.INK_SACK, (byte)9)).toItemStack(1), new ItemStack(Material.WOOL), SlimefunItems.RUNE_ENDER, new ItemStack(Material.WOOL), (new MaterialData(Material.INK_SACK, (byte)11)).toItemStack(1), SlimefunItems.ENDER_LUMP_3, (new MaterialData(Material.INK_SACK, (byte)10)).toItemStack(1)
/* 4518 */         })).register(true);
/*      */     
/* 4520 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.INFERNAL_BONEMEAL, "INFERNAL_BONEMEAL", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.NETHER_STALK), SlimefunItems.RUNE_EARTH, new ItemStack(Material.NETHER_STALK), SlimefunItems.MAGIC_LUMP_2, (new MaterialData(Material.INK_SACK, (byte)15))
/* 4521 */           .toItemStack(1), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.NETHER_STALK), new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.NETHER_STALK) }(ItemStack)new CustomItem(SlimefunItems.INFERNAL_BONEMEAL, 8)))
/* 4522 */       .register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
/*      */             {
/* 4526 */               if (SlimefunManager.isItemSimiliar(e.getItem(), SlimefunItems.INFERNAL_BONEMEAL, true)) {
/* 4527 */                 if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.NETHER_WARTS) && 
/* 4528 */                   e.getClickedBlock().getData() < 3) {
/* 4529 */                   e.getClickedBlock().setData((byte)(e.getClickedBlock().getData() + 1));
/* 4530 */                   e.getClickedBlock().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
/* 4531 */                   PlayerInventory.consumeItemInHand(p);
/*      */                 } 
/*      */                 
/* 4534 */                 return true;
/*      */               } 
/* 4536 */               return false;
/*      */             }
/*      */           } });
/*      */     
/* 4540 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.ELYTRA_SCALE, "ELYTRA_SCALE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.ENDER_LUMP_3, SlimefunItems.RUNE_AIR, SlimefunItems.ENDER_LUMP_3, SlimefunItems.RUNE_ENDER, new ItemStack(Material.FEATHER), SlimefunItems.RUNE_ENDER, SlimefunItems.ENDER_LUMP_3, SlimefunItems.RUNE_AIR, SlimefunItems.ENDER_LUMP_3
/*      */         
/* 4542 */         })).register(true);
/*      */     
/* 4544 */     (new VanillaItem(Categories.MAGIC, SlimefunItems.ELYTRA, "ELYTRA", RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.ELYTRA_SCALE, SlimefunItems.RUNE_AIR, SlimefunItems.ELYTRA_SCALE, SlimefunItems.RUNE_AIR, new ItemStack(Material.LEATHER_CHESTPLATE), SlimefunItems.RUNE_AIR, SlimefunItems.ELYTRA_SCALE, SlimefunItems.RUNE_AIR, SlimefunItems.ELYTRA_SCALE
/*      */         
/* 4546 */         })).register(true);
/*      */     
/* 4548 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.INFUSED_ELYTRA, "INFUSED_ELYTRA", RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ELYTRA, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.FLASK_OF_KNOWLEDGE
/*      */         
/* 4550 */         })).register(true);
/*      */     
/* 4552 */     (new SoulboundItem(Categories.MAGIC, SlimefunItems.SOULBOUND_ELYTRA, "SOULBOUND_ELYTRA", RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.ELYTRA, SlimefunItems.ELYTRA_SCALE, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.FLASK_OF_KNOWLEDGE
/*      */         
/* 4554 */         })).register(true);
/*      */     
/* 4556 */     RainbowTicker rainbow = new RainbowTicker();
/*      */     
/* 4558 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.RAINBOW_WOOL, "RAINBOW_WOOL", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.WOOL), new ItemStack(Material.WOOL), new ItemStack(Material.WOOL), new ItemStack(Material.WOOL), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.WOOL), new ItemStack(Material.WOOL), new ItemStack(Material.WOOL), new ItemStack(Material.WOOL) }, (ItemStack)new CustomItem(SlimefunItems.RAINBOW_WOOL, 8)))
/*      */       
/* 4560 */       .register(true, new ItemHandler[] { (ItemHandler)rainbow });
/*      */     
/* 4562 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.RAINBOW_GLASS, "RAINBOW_GLASS", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.STAINED_GLASS), new ItemStack(Material.STAINED_GLASS), new ItemStack(Material.STAINED_GLASS), new ItemStack(Material.STAINED_GLASS), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_GLASS), new ItemStack(Material.STAINED_GLASS), new ItemStack(Material.STAINED_GLASS), new ItemStack(Material.STAINED_GLASS) }, (ItemStack)new CustomItem(SlimefunItems.RAINBOW_GLASS, 8)))
/*      */       
/* 4564 */       .register(true, new ItemHandler[] { (ItemHandler)rainbow });
/*      */     
/* 4566 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.RAINBOW_GLASS_PANE, "RAINBOW_GLASS_PANE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.STAINED_GLASS_PANE), new ItemStack(Material.STAINED_GLASS_PANE), new ItemStack(Material.STAINED_GLASS_PANE), new ItemStack(Material.STAINED_GLASS_PANE), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_GLASS_PANE), new ItemStack(Material.STAINED_GLASS_PANE), new ItemStack(Material.STAINED_GLASS_PANE), new ItemStack(Material.STAINED_GLASS_PANE) }, (ItemStack)new CustomItem(SlimefunItems.RAINBOW_GLASS_PANE, 8)))
/*      */       
/* 4568 */       .register(true, new ItemHandler[] { (ItemHandler)rainbow });
/*      */     
/* 4570 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.RAINBOW_CLAY, "RAINBOW_CLAY", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.STAINED_CLAY), new ItemStack(Material.STAINED_CLAY), new ItemStack(Material.STAINED_CLAY), new ItemStack(Material.STAINED_CLAY), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_CLAY), new ItemStack(Material.STAINED_CLAY), new ItemStack(Material.STAINED_CLAY), new ItemStack(Material.STAINED_CLAY) }, (ItemStack)new CustomItem(SlimefunItems.RAINBOW_CLAY, 8)))
/*      */       
/* 4572 */       .register(true, new ItemHandler[] { (ItemHandler)rainbow });
/*      */     
/* 4574 */     RainbowTicker xmas = new RainbowTicker(new int[] { 13, 14 });
/*      */     
/* 4576 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.RAINBOW_WOOL_XMAS, "RAINBOW_WOOL_XMAS", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4577 */           .toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.WOOL), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.WOOL), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_WOOL_XMAS, 2)))
/* 4578 */       .register(true, new ItemHandler[] { (ItemHandler)xmas });
/*      */     
/* 4580 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.RAINBOW_GLASS_XMAS, "RAINBOW_GLASS_XMAS", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4581 */           .toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.STAINED_GLASS), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_GLASS), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_GLASS_XMAS, 2)))
/* 4582 */       .register(true, new ItemHandler[] { (ItemHandler)xmas });
/*      */     
/* 4584 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.RAINBOW_GLASS_PANE_XMAS, "RAINBOW_GLASS_PANE_XMAS", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4585 */           .toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.STAINED_GLASS_PANE), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_GLASS_PANE), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_GLASS_PANE_XMAS, 2)))
/* 4586 */       .register(true, new ItemHandler[] { (ItemHandler)xmas });
/*      */     
/* 4588 */     (new SlimefunItem((Category)Categories.CHRISTMAS, SlimefunItems.RAINBOW_CLAY_XMAS, "RAINBOW_CLAY_XMAS", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4589 */           .toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.STAINED_CLAY), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_CLAY), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), SlimefunItems.CHRISTMAS_COOKIE, (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_CLAY_XMAS, 2)))
/* 4590 */       .register(true, new ItemHandler[] { (ItemHandler)xmas });
/*      */     
/* 4592 */     RainbowTicker valentine = new RainbowTicker(new int[] { 2, 6, 10 });
/*      */     
/* 4594 */     (new SlimefunItem((Category)Categories.VALENTINES_DAY, SlimefunItems.RAINBOW_WOOL_VALENTINE, "RAINBOW_WOOL_VALENTINE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4595 */           .toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.WOOL), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.WOOL), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_WOOL_VALENTINE, 2)))
/* 4596 */       .register(true, new ItemHandler[] { (ItemHandler)valentine });
/*      */     
/* 4598 */     (new SlimefunItem((Category)Categories.VALENTINES_DAY, SlimefunItems.RAINBOW_GLASS_VALENTINE, "RAINBOW_GLASS_VALENTINE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4599 */           .toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.STAINED_GLASS), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_GLASS), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_GLASS_VALENTINE, 2)))
/* 4600 */       .register(true, new ItemHandler[] { (ItemHandler)valentine });
/*      */     
/* 4602 */     (new SlimefunItem((Category)Categories.VALENTINES_DAY, SlimefunItems.RAINBOW_GLASS_PANE_VALENTINE, "RAINBOW_GLASS_PANE_VALENTINE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4603 */           .toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.STAINED_GLASS_PANE), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_GLASS_PANE), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_GLASS_PANE_VALENTINE, 2)))
/* 4604 */       .register(true, new ItemHandler[] { (ItemHandler)valentine });
/*      */     
/* 4606 */     (new SlimefunItem((Category)Categories.VALENTINES_DAY, SlimefunItems.RAINBOW_CLAY_VALENTINE, "RAINBOW_CLAY_VALENTINE", RecipeType.ANCIENT_ALTAR, new ItemStack[] { (new MaterialData(Material.INK_SACK, (byte)1))
/* 4607 */           .toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.STAINED_CLAY), SlimefunItems.RUNE_RAINBOW, new ItemStack(Material.STAINED_CLAY), (new MaterialData(Material.INK_SACK, (byte)2)).toItemStack(1), new ItemStack(Material.RED_ROSE), (new MaterialData(Material.INK_SACK, (byte)1)).toItemStack(1) }(ItemStack)new CustomItem(SlimefunItems.RAINBOW_CLAY_VALENTINE, 2)))
/* 4608 */       .register(true, new ItemHandler[] { (ItemHandler)valentine });
/*      */     
/* 4610 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.WITHER_PROOF_GLASS, "WITHER_PROOF_GLASS", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.LEAD_INGOT, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.HARDENED_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.LEAD_INGOT, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.LEAD_INGOT }, (ItemStack)new CustomItem(SlimefunItems.WITHER_PROOF_GLASS, 4)))
/*      */ 
/*      */       
/* 4613 */       .register(true);
/*      */ 
/*      */     
/* 4616 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_GEO_SCANNER, "GPS_GEO_SCANNER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, SlimefunItems.ELECTRO_MAGNET, null, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET
/*      */         
/* 4618 */         })).register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack stack)
/*      */             {
/* 4622 */               if (e.getClickedBlock() == null) return false; 
/* 4623 */               SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 4624 */               if (item == null || !item.getName().equals("GPS_GEO_SCANNER")) return false; 
/* 4625 */               e.setCancelled(true);
/*      */               try {
/* 4627 */                 Slimefun.getGPSNetwork().scanChunk(p, e.getClickedBlock().getChunk());
/* 4628 */               } catch (Exception e1) {
/* 4629 */                 e1.printStackTrace();
/*      */               } 
/* 4631 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 4635 */     (new OilPump((Category)Categories.GPS, SlimefunItems.OIL_PUMP, "OIL_PUMP", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.STEEL_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_INGOT, null, new ItemStack(Material.BUCKET), null })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 4640 */           return 14;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 4645 */           return 1;
/*      */         }
/* 4648 */       }).registerChargeableBlock(true, 200);
/*      */     
/* 4650 */     (new NetherDrill((Category)Categories.GPS, SlimefunItems.NETHER_DRILL, "NETHER_DRILL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.LEAD_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.OIL_PUMP, SlimefunItems.REINFORCED_PLATE, SlimefunItems.LEAD_INGOT, SlimefunItems.BIG_CAPACITOR, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         public int getSpeed()
/*      */         {
/* 4654 */           return 1;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 4659 */           return 51;
/*      */         }
/* 4661 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 4663 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.BUCKET_OF_OIL, "BUCKET_OF_OIL", new RecipeType(SlimefunItems.OIL_PUMP), new ItemStack[] { null, null, null, null, new ItemStack(Material.BUCKET), null, null, null, null
/*      */         
/* 4665 */         })).register(true);
/*      */     
/* 4667 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.BUCKET_OF_FUEL, "BUCKET_OF_FUEL", new RecipeType(SlimefunItems.REFINERY), new ItemStack[] { null, null, null, null, SlimefunItems.BUCKET_OF_OIL, null, null, null, null
/*      */         
/* 4669 */         })).register(true);
/*      */     
/* 4671 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.NETHER_ICE, "NETHER_ICE", new RecipeType(SlimefunItems.NETHER_DRILL), new ItemStack[] { null, null, null, null, null, null, null, null
/*      */         
/* 4673 */         })).register(true);
/*      */     
/* 4675 */     (new Refinery((Category)Categories.ELECTRICITY, SlimefunItems.REFINERY, "REFINERY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HARDENED_GLASS, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_GLASS, new ItemStack(Material.PISTON_BASE), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.PISTON_BASE) })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 4680 */           return 16;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 4685 */           return 1;
/*      */         }
/* 4688 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 4690 */     (new AGenerator((Category)Categories.ELECTRICITY, SlimefunItems.LAVA_GENERATOR, "LAVA_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GOLD_16K, null, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.HEATING_COIL })
/*      */       {
/*      */         
/*      */         public void registerDefaultRecipes()
/*      */         {
/* 4695 */           registerFuel(new MachineFuel(40, new ItemStack(Material.LAVA_BUCKET)));
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 4700 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 4705 */           return "&4岩浆发电机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyProduction() {
/* 4710 */           return 10;
/*      */         }
/* 4713 */       }).registerUnrechargeableBlock(true, 512);
/*      */     
/* 4715 */     (new AGenerator((Category)Categories.ELECTRICITY, SlimefunItems.COMBUSTION_REACTOR, "COMBUSTION_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.STEEL_INGOT, SlimefunItems.HEATING_COIL })
/*      */       {
/*      */         
/*      */         public void registerDefaultRecipes()
/*      */         {
/* 4720 */           registerFuel(new MachineFuel(30, SlimefunItems.BUCKET_OF_OIL));
/* 4721 */           registerFuel(new MachineFuel(90, SlimefunItems.BUCKET_OF_FUEL));
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 4726 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 4731 */           return "&c石油发电机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyProduction() {
/* 4736 */           return 12;
/*      */         }
/* 4739 */       }).registerUnrechargeableBlock(true, 256);
/*      */     
/* 4741 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_TELEPORTER_PYLON, "GPS_TELEPORTER_PYLON", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.ZINC_INGOT, new ItemStack(Material.GLASS), SlimefunItems.ZINC_INGOT, new ItemStack(Material.GLASS), SlimefunItems.HEATING_COIL, new ItemStack(Material.GLASS), SlimefunItems.ZINC_INGOT, new ItemStack(Material.GLASS), SlimefunItems.ZINC_INGOT }, (ItemStack)new CustomItem(SlimefunItems.GPS_TELEPORTER_PYLON, 8)))
/*      */       
/* 4743 */       .register(true, new ItemHandler[] { (ItemHandler)new RainbowTicker(new int[] { 9, 10 }) });
/*      */     
/* 4745 */     (new Teleporter((Category)Categories.GPS, SlimefunItems.GPS_TELEPORTATION_MATRIX, "GPS_TELEPORTATION_MATRIX", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GPS_TELEPORTER_PYLON, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.GPS_TELEPORTER_PYLON, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.GPS_CONTROL_PANEL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.GPS_TELEPORTER_PYLON, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.GPS_TELEPORTER_PYLON })
/*      */       {
/*      */ 
/*      */         
/*      */         public void onInteract(Player p, Block b) throws Exception
/*      */         {
/* 4751 */           GPSNetwork.openTeleporterGUI(p, UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), b, Slimefun.getGPSNetwork().getNetworkComplexity(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner"))));
/*      */         }
/* 4755 */       }).register(true);
/*      */     
/* 4757 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_ACTIVATION_DEVICE_SHARED, "GPS_ACTIVATION_DEVICE_SHARED", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.STONE_PLATE), null, new ItemStack(Material.REDSTONE), SlimefunItems.GPS_TRANSMITTER, new ItemStack(Material.REDSTONE), SlimefunItems.BILLON_INGOT, SlimefunItems.BILLON_INGOT, SlimefunItems.BILLON_INGOT
/*      */         
/* 4759 */         })).register(true);
/*      */     
/* 4761 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.GPS_ACTIVATION_DEVICE_PERSONAL, "GPS_ACTIVATION_DEVICE_PERSONAL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.LEAD_INGOT, null, SlimefunItems.COBALT_INGOT, SlimefunItems.GPS_ACTIVATION_DEVICE_SHARED, SlimefunItems.COBALT_INGOT, null, SlimefunItems.LEAD_INGOT, null
/*      */         
/* 4763 */         })).register(true);
/*      */     
/* 4765 */     SlimefunItem.registerBlockHandler("GPS_ACTIVATION_DEVICE_PERSONAL", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4769 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4774 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4778 */     (new SlimefunItem(Categories.TECH, SlimefunItems.HOLOGRAM_PROJECTOR, "HOLOGRAM_PROJECTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.POWER_CRYSTAL, null, SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ALUMINUM_BRASS_INGOT, null, SlimefunItems.ALUMINUM_BRASS_INGOT, null }, (ItemStack)new CustomItem(SlimefunItems.HOLOGRAM_PROJECTOR, 3)))
/*      */       
/* 4780 */       .register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack stack)
/*      */             {
/* 4784 */               if (e.getClickedBlock() == null) return false; 
/* 4785 */               SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 4786 */               if (item == null || !item.getName().equals("HOLOGRAM_PROJECTOR")) return false; 
/* 4787 */               e.setCancelled(true);
/*      */               
/* 4789 */               if (BlockStorage.getBlockInfo(e.getClickedBlock(), "owner").equals(p.getUniqueId().toString())) {
/* 4790 */                 Projector.openEditor(p, e.getClickedBlock());
/*      */               }
/*      */               
/* 4793 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 4797 */     SlimefunItem.registerBlockHandler("HOLOGRAM_PROJECTOR", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4801 */             BlockStorage.addBlockInfo(b, "text", "&b在这里写全息文字内容");
/* 4802 */             BlockStorage.addBlockInfo(b, "offset", "-0.5");
/* 4803 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */             
/* 4805 */             Projector.getArmorStand(b);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4810 */             Projector.getArmorStand(b).remove();
/* 4811 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4815 */     (new SlimefunItem(Categories.MAGIC, SlimefunItems.INFUSED_HOPPER, "INFUSED_HOPPER", RecipeType.ANCIENT_ALTAR, new ItemStack[] { new ItemStack(Material.OBSIDIAN), SlimefunItems.RUNE_EARTH, new ItemStack(Material.HOPPER), SlimefunItems.RUNE_ENDER, SlimefunItems.INFUSED_MAGNET, SlimefunItems.RUNE_ENDER, new ItemStack(Material.HOPPER), SlimefunItems.RUNE_EARTH, new ItemStack(Material.OBSIDIAN)
/*      */         
/* 4817 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void uniqueTick() {}
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void tick(Block b, SlimefunItem item, Config data) {
/* 4825 */               if (b.getType() != Material.HOPPER) {
/*      */                 return;
/*      */               }
/*      */               
/* 4829 */               ArmorStand hologram = InfusedHopper.getArmorStand(b, true);
/* 4830 */               boolean sound = false;
/* 4831 */               for (Entity n : hologram.getNearbyEntities(3.5D, 3.5D, 3.5D)) {
/* 4832 */                 if (n instanceof Item && !n.hasMetadata("no_pickup") && n.getLocation().distance(hologram.getLocation()) > 0.4D) {
/* 4833 */                   n.setVelocity(new Vector(0.0D, 0.1D, 0.0D));
/* 4834 */                   n.teleport((Entity)hologram);
/* 4835 */                   sound = true;
/*      */                 } 
/*      */               } 
/* 4838 */               if (sound) b.getWorld().playSound(b.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 5.0F, 2.0F);
/*      */             
/*      */             }
/*      */             
/*      */             public boolean isSynchronized() {
/* 4843 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 4847 */     SlimefunItem.registerBlockHandler("INFUSED_HOPPER", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4851 */             InfusedHopper.getArmorStand(b, true);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4856 */             ArmorStand hologram = InfusedHopper.getArmorStand(b, false);
/* 4857 */             if (hologram != null) {
/* 4858 */               hologram.remove();
/*      */             }
/* 4860 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4864 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.BLISTERING_INGOT, "BLISTERING_INGOT", RecipeType.HEATED_PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.GOLD_24K, SlimefunItems.URANIUM, null, null, null, null, null, null, null
/*      */         
/* 4866 */         })).register(true);
/*      */     
/* 4868 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.BLISTERING_INGOT_2, "BLISTERING_INGOT_2", RecipeType.HEATED_PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.BLISTERING_INGOT, SlimefunItems.CARBONADO, null, null, null, null, null, null, null
/*      */         
/* 4870 */         })).register(true);
/*      */     
/* 4872 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.BLISTERING_INGOT_3, "BLISTERING_INGOT_3", RecipeType.HEATED_PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_2, new ItemStack(Material.NETHER_STAR), null, null, null, null, null, null, null
/*      */         
/* 4874 */         })).register(true);
/*      */     
/* 4876 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.ENRICHED_NETHER_ICE, "ENRICHED_NETHER_ICE", RecipeType.HEATED_PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.NETHER_ICE, SlimefunItems.PLUTONIUM, null, null, null, null, null, null, null
/*      */         
/* 4878 */         })).register(true);
/*      */     
/* 4880 */     (new SlimefunItem((Category)Categories.GPS, SlimefunItems.ELEVATOR, "ELEVATOR_PLATE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.STONE_PLATE), null, new ItemStack(Material.PISTON_BASE), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.PISTON_BASE), SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT }, (ItemStack)new CustomItem(SlimefunItems.ELEVATOR, 2)))
/*      */ 
/*      */       
/* 4883 */       .register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack stack)
/*      */             {
/* 4887 */               if (e.getClickedBlock() == null) return false; 
/* 4888 */               SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 4889 */               if (item == null) return false; 
/* 4890 */               if (!item.getName().equals("ELEVATOR_PLATE")) return false;
/*      */               
/* 4892 */               if (BlockStorage.getBlockInfo(e.getClickedBlock(), "owner").equals(p.getUniqueId().toString())) Elevator.openEditor(p, e.getClickedBlock()); 
/* 4893 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 4897 */     SlimefunItem.registerBlockHandler("ELEVATOR_PLATE", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item)
/*      */           {
/* 4901 */             BlockStorage.addBlockInfo(b, "floor", "&r楼层 #0");
/* 4902 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 4907 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 4911 */     (new FoodFabricator((Category)Categories.ELECTRICITY, SlimefunItems.FOOD_FABRICATOR, "FOOD_FABRICATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BILLON_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.BILLON_INGOT, SlimefunItems.CAN, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.CAN, null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 4916 */           return new ItemStack(Material.GOLD_HOE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 4921 */           return "&c食品加工机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 4926 */           return 7;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 4931 */           return 1;
/*      */         }
/* 4934 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 4936 */     (new FoodFabricator((Category)Categories.ELECTRICITY, SlimefunItems.FOOD_FABRICATOR_2, "FOOD_FABRICATOR_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.FOOD_FABRICATOR, SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.ELECTRO_MAGNET, null })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 4941 */           return new ItemStack(Material.DIAMOND_HOE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 4946 */           return "&c食品加工机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 4951 */           return 24;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 4956 */           return 6;
/*      */         }
/* 4959 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 4961 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD2, "ORGANIC_FOOD_WHEAT", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.WHEAT), null, null, null, null, null, null, null
/*      */         
/* 4963 */         })).register(true);
/*      */     
/* 4965 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD3, "ORGANIC_FOOD_CARROT", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.CARROT_ITEM), null, null, null, null, null, null, null
/*      */         
/* 4967 */         })).register(true);
/*      */     
/* 4969 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD4, "ORGANIC_FOOD_POTATO", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.POTATO_ITEM), null, null, null, null, null, null, null
/*      */         
/* 4971 */         })).register(true);
/*      */     
/* 4973 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD5, "ORGANIC_FOOD_SEEDS", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.SEEDS), null, null, null, null, null, null, null
/*      */         
/* 4975 */         })).register(true);
/*      */     
/* 4977 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD6, "ORGANIC_FOOD_BEETROOT", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.BEETROOT), null, null, null, null, null, null, null
/*      */         
/* 4979 */         })).register(true);
/*      */     
/* 4981 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD7, "ORGANIC_FOOD_MELON", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.MELON), null, null, null, null, null, null, null
/*      */         
/* 4983 */         })).register(true);
/*      */     
/* 4985 */     (new SlimefunItem(Categories.MISC, SlimefunItems.ORGANIC_FOOD8, "ORGANIC_FOOD_APPLE", new RecipeType(SlimefunItems.FOOD_FABRICATOR), new ItemStack[] { SlimefunItems.CAN, new ItemStack(Material.APPLE), null, null, null, null, null, null, null
/*      */         
/* 4987 */         })).register(true);
/*      */     
/* 4989 */     (new AutoBreeder((Category)Categories.ELECTRICITY, SlimefunItems.AUTO_BREEDER, "AUTO_BREEDER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.GOLD_18K, SlimefunItems.CAN, SlimefunItems.GOLD_18K, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.HAY_BLOCK), SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.LEAD_INGOT, SlimefunItems.FOOD_FABRICATOR, SlimefunItems.LEAD_INGOT
/*      */         
/* 4991 */         })).registerChargeableBlock(true, 1024);
/*      */     
/* 4993 */     (new AnimalGrowthAccelerator((Category)Categories.ELECTRICITY, SlimefunItems.ANIMAL_GROWTH_ACCELERATOR, "ANIMAL_GROWTH_ACCELERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.BLISTERING_INGOT_3, null, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ORGANIC_FOOD2, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.AUTO_BREEDER, SlimefunItems.REINFORCED_ALLOY_INGOT
/*      */         
/* 4995 */         })).registerChargeableBlock(true, 1024);
/*      */     
/* 4997 */     (new XPCollector((Category)Categories.ELECTRICITY, SlimefunItems.XP_COLLECTOR, "XP_COLLECTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.BLISTERING_INGOT_3, null, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.AUTO_ENCHANTER, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ALUMINUM_BRONZE_INGOT
/*      */         
/* 4999 */         })).registerChargeableBlock(true, 1024);
/*      */     
/* 5001 */     (new FoodComposter((Category)Categories.ELECTRICITY, SlimefunItems.FOOD_COMPOSTER, "FOOD_COMPOSTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.FOOD_FABRICATOR, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.CAN, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.CAN, null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5006 */           return new ItemStack(Material.GOLD_HOE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5011 */           return "&c食品堆肥机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5016 */           return 8;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5021 */           return 1;
/*      */         }
/* 5024 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 5026 */     (new FoodComposter((Category)Categories.ELECTRICITY, SlimefunItems.FOOD_COMPOSTER_2, "FOOD_COMPOSTER_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.FOOD_COMPOSTER, SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.ELECTRO_MAGNET, null })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5031 */           return new ItemStack(Material.DIAMOND_HOE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5036 */           return "&c食品堆肥机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5041 */           return 26;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5046 */           return 10;
/*      */         }
/* 5049 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 5051 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER2, "FERTILIZER_WHEAT", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD2, null, null, null, null, null, null, null, null
/*      */         
/* 5053 */         })).register(true);
/*      */     
/* 5055 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER3, "FERTILIZER_CARROT", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD3, null, null, null, null, null, null, null, null
/*      */         
/* 5057 */         })).register(true);
/*      */     
/* 5059 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER4, "FERTILIZER_POTATO", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD4, null, null, null, null, null, null, null, null
/*      */         
/* 5061 */         })).register(true);
/*      */     
/* 5063 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER5, "FERTILIZER_SEEDS", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD5, null, null, null, null, null, null, null, null
/*      */         
/* 5065 */         })).register(true);
/*      */     
/* 5067 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER6, "FERTILIZER_BEETROOT", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD6, null, null, null, null, null, null, null, null
/*      */         
/* 5069 */         })).register(true);
/*      */     
/* 5071 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER7, "FERTILIZER_MELON", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD7, null, null, null, null, null, null, null, null
/*      */         
/* 5073 */         })).register(true);
/*      */     
/* 5075 */     (new SlimefunItem(Categories.MISC, SlimefunItems.FERTILIZER8, "FERTILIZER_APPLE", new RecipeType(SlimefunItems.FOOD_COMPOSTER), new ItemStack[] { SlimefunItems.ORGANIC_FOOD8, null, null, null, null, null, null, null, null
/*      */         
/* 5077 */         })).register(true);
/*      */     
/* 5079 */     (new CropGrowthAccelerator((Category)Categories.ELECTRICITY, SlimefunItems.CROP_GROWTH_ACCELERATOR, "CROP_GROWTH_ACCELERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.BLISTERING_INGOT_3, null, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.PROGRAMMABLE_ANDROID_FARMER, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ANIMAL_GROWTH_ACCELERATOR, SlimefunItems.ELECTRO_MAGNET })
/*      */       {
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 5085 */           return 25;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getRadius() {
/* 5090 */           return 3;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5095 */           return 3;
/*      */         }
/* 5098 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 5100 */     (new CropGrowthAccelerator((Category)Categories.ELECTRICITY, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, "CROP_GROWTH_ACCELERATOR_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.BLISTERING_INGOT_3, null, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CROP_GROWTH_ACCELERATOR, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELECTRO_MAGNET })
/*      */       {
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 5106 */           return 30;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getRadius() {
/* 5111 */           return 4;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5116 */           return 4;
/*      */         }
/* 5119 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 5121 */     (new Freezer((Category)Categories.ELECTRICITY, SlimefunItems.FREEZER, "FREEZER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.SILVER_INGOT, null, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.PACKED_ICE), SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COOLING_UNIT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.COOLING_UNIT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5126 */           return new ItemStack(Material.GOLD_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5131 */           return "&b制冷机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5136 */           return 9;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5141 */           return 1;
/*      */         }
/* 5144 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 5146 */     (new Freezer((Category)Categories.ELECTRICITY, SlimefunItems.FREEZER_2, "FREEZER_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.SILVER_INGOT, null, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.FREEZER, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.COOLING_UNIT, SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.COOLING_UNIT })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5151 */           return new ItemStack(Material.DIAMOND_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5156 */           return "&b制冷机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5161 */           return 15;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5166 */           return 2;
/*      */         }
/* 5169 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 5171 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.REACTOR_COOLANT_CELL, "REACTOR_COOLANT_CELL", new RecipeType(SlimefunItems.FREEZER), new ItemStack[] { new ItemStack(Material.PACKED_ICE), null, null, null, null, null, null, null, null
/*      */         
/* 5173 */         })).register(true);
/*      */     
/* 5175 */     (new SlimefunItem(Categories.TECH_MISC, SlimefunItems.NETHER_ICE_COOLANT_CELL, "NETHER_ICE_COOLANT_CELL", new RecipeType(SlimefunItems.HEATED_PRESSURE_CHAMBER), new ItemStack[] { SlimefunItems.ENRICHED_NETHER_ICE, null, null, null, null, null, null, null, null
/*      */         
/* 5177 */         })).register(true);
/*      */     
/* 5179 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.NEPTUNIUM, "NEPTUNIUM", new RecipeType(SlimefunItems.NUCLEAR_REACTOR), new ItemStack[] { SlimefunItems.URANIUM, null, null, null, null, null, null, null, null
/*      */         
/* 5181 */         })).register(true);
/*      */     
/* 5183 */     SlimefunItem.setRadioactive(SlimefunItems.NEPTUNIUM);
/*      */     
/* 5185 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.PLUTONIUM, "PLUTONIUM", new RecipeType(SlimefunItems.NUCLEAR_REACTOR), new ItemStack[] { SlimefunItems.NEPTUNIUM, null, null, null, null, null, null, null, null
/*      */         
/* 5187 */         })).register(true);
/*      */     
/* 5189 */     SlimefunItem.setRadioactive(SlimefunItems.PLUTONIUM);
/*      */     
/* 5191 */     (new SlimefunItem(Categories.RESOURCES, SlimefunItems.BOOSTED_URANIUM, "BOOSTED_URANIUM", RecipeType.HEATED_PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.PLUTONIUM, SlimefunItems.URANIUM, null, null, null, null, null, null, null
/*      */         
/* 5193 */         })).register(true);
/*      */     
/* 5195 */     SlimefunItem.setRadioactive(SlimefunItems.BOOSTED_URANIUM);
/*      */     
/* 5197 */     (new AReactor((Category)Categories.ELECTRICITY, SlimefunItems.NUCLEAR_REACTOR, "NUCLEAR_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_PLATE, SlimefunItems.COOLING_UNIT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.LEAD_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.LEAD_INGOT })
/*      */       {
/*      */         
/*      */         public String getInventoryTitle()
/*      */         {
/* 5202 */           return "&2核能发电机";
/*      */         }
/*      */ 
/*      */         
/*      */         public void registerDefaultRecipes() {
/* 5207 */           registerFuel(new MachineFuel(1200, SlimefunItems.URANIUM, SlimefunItems.NEPTUNIUM));
/* 5208 */           registerFuel(new MachineFuel(600, SlimefunItems.NEPTUNIUM, SlimefunItems.PLUTONIUM));
/* 5209 */           registerFuel(new MachineFuel(1500, SlimefunItems.BOOSTED_URANIUM, null));
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyProduction() {
/* 5214 */           return 250;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void extraTick(Location l) {}
/*      */ 
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/*      */           try {
/* 5225 */             return CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNhZDhlZTg0OWVkZjA0ZWQ5YTI2Y2EzMzQxZjYwMzNiZDc2ZGNjNDIzMWVkMWVhNjNiNzU2NTc1MWIyN2FjIn19fQ==");
/* 5226 */           } catch (Exception e) {
/* 5227 */             return new ItemStack(Material.BLAZE_POWDER);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getCoolant() {
/* 5233 */           return SlimefunItems.REACTOR_COOLANT_CELL;
/*      */         }
/* 5236 */       }).registerChargeableBlock(true, 16384);
/*      */     
/* 5238 */     (new AReactor((Category)Categories.ELECTRICITY, SlimefunItems.NETHERSTAR_REACTOR, "NETHERSTAR_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BOOSTED_URANIUM, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.REINFORCED_PLATE, new ItemStack(Material.NETHER_STAR), SlimefunItems.REINFORCED_PLATE, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.CORINTHIAN_BRONZE_INGOT })
/*      */       {
/*      */         
/*      */         public String getInventoryTitle()
/*      */         {
/* 5243 */           return "&f下界之星发电机";
/*      */         }
/*      */ 
/*      */         
/*      */         public void registerDefaultRecipes() {
/* 5248 */           registerFuel(new MachineFuel(1800, new ItemStack(Material.NETHER_STAR)));
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyProduction() {
/* 5253 */           return 512;
/*      */         }
/*      */ 
/*      */         
/*      */         public void extraTick(final Location l) {
/* 5258 */           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new BukkitRunnable()
/*      */               {
/*      */                 public void run() {
/* 5261 */                   for (Entity entity : ReactorHologram.getArmorStand(l).getNearbyEntities(5.0D, 5.0D, 5.0D)) {
/* 5262 */                     if (entity instanceof LivingEntity) {
/* 5263 */                       ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */               }0L);
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getCoolant() {
/* 5272 */           return SlimefunItems.NETHER_ICE_COOLANT_CELL;
/*      */         }
/*      */ 
/*      */         
/*      */         public ItemStack getProgressBar() {
/* 5277 */           return new ItemStack(Material.NETHER_STAR);
/*      */         }
/* 5280 */       }).registerChargeableBlock(true, 32768);
/*      */     
/* 5282 */     (new SlimefunItem(Categories.CARGO, SlimefunItems.CARGO_MOTOR, "CARGO_MOTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.HARDENED_GLASS, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.HARDENED_GLASS, SlimefunItems.SILVER_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.SILVER_INGOT, SlimefunItems.HARDENED_GLASS, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.HARDENED_GLASS }, (ItemStack)new CustomItem(SlimefunItems.CARGO_MOTOR, 4)))
/*      */       
/* 5284 */       .register(true);
/*      */     
/* 5286 */     (new SlimefunItem(Categories.CARGO, SlimefunItems.CARGO_MANAGER, "CARGO_MANAGER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.HOLOGRAM_PROJECTOR, null, SlimefunItems.REINFORCED_PLATE, SlimefunItems.CARGO_MOTOR, SlimefunItems.REINFORCED_PLATE, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.ALUMINUM_BRONZE_INGOT
/*      */         
/* 5288 */         })).register(true, new ItemHandler[] { (ItemHandler)new BlockTicker()
/*      */           {
/*      */             public void uniqueTick() {}
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void tick(Block b, SlimefunItem item, Config data) {
/* 5296 */               CargoNet.getNetworkFromLocationOrCreate(b.getLocation()).tick(b);
/*      */             }
/*      */ 
/*      */             
/*      */             public boolean isSynchronized() {
/* 5301 */               return false;
/*      */             }
/*      */           }, (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack stack)
/*      */             {
/* 5307 */               if (e.getClickedBlock() == null) return false; 
/* 5308 */               SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 5309 */               if (item == null || !item.getName().equals("CARGO_MANAGER")) return false; 
/* 5310 */               e.setCancelled(true);
/*      */               
/* 5312 */               if (BlockStorage.getBlockInfo(e.getClickedBlock(), "visualizer") == null) {
/* 5313 */                 BlockStorage.addBlockInfo(e.getClickedBlock(), "visualizer", "disabled");
/* 5314 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c运输网络可视化: &4✘"));
/*      */               } else {
/*      */                 
/* 5317 */                 BlockStorage.addBlockInfo(e.getClickedBlock(), "visualizer", null);
/* 5318 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c运输网络可视化: &2✔"));
/*      */               } 
/* 5320 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 5324 */     SlimefunItem.registerBlockHandler("CARGO_MANAGER", new SlimefunBlockHandler()
/*      */         {
/*      */           public void onPlace(Player p, Block b, SlimefunItem item) {}
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 5332 */             CargoHologram.remove(b);
/* 5333 */             return true;
/*      */           }
/*      */         });
/*      */     
/* 5337 */     (new SlimefunItem(Categories.CARGO, SlimefunItems.CARGO_NODE, "CARGO_NODE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BRONZE_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.BRONZE_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.CARGO_MOTOR, SlimefunItems.SILVER_INGOT, SlimefunItems.BRONZE_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.BRONZE_INGOT }, (ItemStack)new CustomItem(SlimefunItems.CARGO_NODE, 4)))
/*      */       
/* 5339 */       .register(true, new ItemHandler[] { (ItemHandler)new ItemInteractionHandler()
/*      */           {
/*      */             public boolean onRightClick(ItemUseEvent e, Player p, ItemStack stack)
/*      */             {
/* 5343 */               if (e.getClickedBlock() == null) return false; 
/* 5344 */               SlimefunItem item = BlockStorage.check(e.getClickedBlock());
/* 5345 */               if (item == null) return false; 
/* 5346 */               if (!item.getName().equals("CARGO_NODE")) return false;
/*      */               
/* 5348 */               if (CargoNet.isConnected(e.getClickedBlock())) {
/* 5349 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7已连接: &2✔"));
/*      */               } else {
/*      */                 
/* 5352 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7已连接: &4✘"));
/*      */               } 
/* 5354 */               return true;
/*      */             }
/*      */           } });
/*      */     
/* 5358 */     (new CargoInputNode(Categories.CARGO, SlimefunItems.CARGO_INPUT, "CARGO_NODE_INPUT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.HOPPER), null, SlimefunItems.BILLON_INGOT, SlimefunItems.CARGO_NODE, SlimefunItems.BILLON_INGOT, null, new ItemStack(Material.HOPPER), null }, (ItemStack)new CustomItem(SlimefunItems.CARGO_INPUT, 2)))
/*      */       
/* 5360 */       .register(true);
/*      */     
/* 5362 */     (new CargoOutputNode(Categories.CARGO, SlimefunItems.CARGO_OUTPUT, "CARGO_NODE_OUTPUT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.HOPPER), null, SlimefunItems.BRASS_INGOT, SlimefunItems.CARGO_NODE, SlimefunItems.BRASS_INGOT, null, new ItemStack(Material.HOPPER), null }, (ItemStack)new CustomItem(SlimefunItems.CARGO_OUTPUT, 2)))
/*      */       
/* 5364 */       .register(true);
/*      */     
/* 5366 */     (new AdvancedCargoOutputNode(Categories.CARGO, SlimefunItems.CARGO_OUTPUT_ADVANCED, "CARGO_NODE_OUTPUT_ADVANCED", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.CARGO_MOTOR, null, SlimefunItems.COBALT_INGOT, SlimefunItems.CARGO_OUTPUT, SlimefunItems.COBALT_INGOT, null, SlimefunItems.CARGO_MOTOR, null }, (ItemStack)new CustomItem(SlimefunItems.CARGO_OUTPUT_ADVANCED)))
/*      */       
/* 5368 */       .register(true);
/*      */     
/* 5370 */     (new AutomatedCraftingChamber((Category)Categories.ELECTRICITY, SlimefunItems.AUTOMATED_CRAFTING_CHAMBER, "AUTOMATED_CRAFTING_CHAMBER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.WORKBENCH), null, SlimefunItems.CARGO_MOTOR, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.CARGO_MOTOR, null, SlimefunItems.ELECTRIC_MOTOR, null })
/*      */       {
/*      */         
/*      */         public int getEnergyConsumption()
/*      */         {
/* 5375 */           return 10;
/*      */         }
/* 5377 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 5379 */     (new ReactorAccessPort((Category)Categories.ELECTRICITY, SlimefunItems.REACTOR_ACCESS_PORT, "REACTOR_ACCESS_PORT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.BLISTERING_INGOT_3, null, SlimefunItems.LEAD_INGOT, SlimefunItems.CARGO_MOTOR, SlimefunItems.LEAD_INGOT, null, SlimefunItems.ELECTRIC_MOTOR, null
/*      */         
/* 5381 */         })).register(true);
/*      */     
/* 5383 */     (new FluidPump((Category)Categories.ELECTRICITY, SlimefunItems.FLUID_PUMP, "FLUID_PUMP", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.MEDIUM_CAPACITOR, null, new ItemStack(Material.BUCKET), SlimefunItems.CARGO_MOTOR, new ItemStack(Material.BUCKET), null, SlimefunItems.OIL_PUMP, null
/*      */         
/* 5385 */         })).registerChargeableBlock(true, 512);
/*      */ 
/*      */     
/* 5388 */     (new TrashCan(Categories.CARGO, SlimefunItems.TRASH_CAN, "TRASH_CAN_BLOCK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.PORTABLE_DUSTBIN, null, SlimefunItems.LEAD_INGOT, SlimefunItems.CARGO_MOTOR, SlimefunItems.LEAD_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.ALUMINUM_INGOT
/*      */         
/* 5390 */         })).register(true);
/*      */     
/* 5392 */     (new CarbonPress((Category)Categories.ELECTRICITY, SlimefunItems.CARBON_PRESS, "CARBON_PRESS", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CARBON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.HEATED_PRESSURE_CHAMBER, SlimefunItems.CARBON, SlimefunItems.HEATING_COIL, SlimefunItems.CARBONADO, SlimefunItems.HEATING_COIL })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5397 */           return new ItemStack(Material.DIAMOND_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5402 */           return "&c碳压缩机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5407 */           return 10;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5412 */           return 1;
/*      */         }
/* 5415 */       }).registerChargeableBlock(true, 256);
/*      */     
/* 5417 */     (new CarbonPress((Category)Categories.ELECTRICITY, SlimefunItems.CARBON_PRESS_2, "CARBON_PRESS_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CARBONADO, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARBONADO, SlimefunItems.CARBON, SlimefunItems.CARBON_PRESS, SlimefunItems.CARBON, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.HEATING_COIL })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5422 */           return new ItemStack(Material.DIAMOND_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5427 */           return "&cCarbon Press";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5432 */           return 25;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5437 */           return 3;
/*      */         }
/* 5440 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 5442 */     (new CarbonPress((Category)Categories.ELECTRICITY, SlimefunItems.CARBON_PRESS_3, "CARBON_PRESS_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.CARBONADO, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBON_PRESS_2, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.HEATING_COIL })
/*      */       {
/*      */         
/*      */         public ItemStack getProgressBar()
/*      */         {
/* 5447 */           return new ItemStack(Material.DIAMOND_PICKAXE);
/*      */         }
/*      */ 
/*      */         
/*      */         public String getInventoryTitle() {
/* 5452 */           return "&c碳压缩机";
/*      */         }
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5457 */           return 90;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5462 */           return 15;
/*      */         }
/* 5465 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 5467 */     (new ElectricSmeltery((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_SMELTERY, "ELECTRIC_SMELTERY", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.NETHER_BRICK_ITEM), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.NETHER_BRICK_ITEM), SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_INGOT_FACTORY, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         public void registerDefaultRecipes() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5476 */           return 10;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5481 */           return 1;
/*      */         }
/* 5484 */       }).registerChargeableBlock(true, 512);
/*      */     
/* 5486 */     (new ElectricSmeltery((Category)Categories.ELECTRICITY, SlimefunItems.ELECTRIC_SMELTERY_2, "ELECTRIC_SMELTERY_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_SMELTERY, SlimefunItems.HEATING_COIL, SlimefunItems.GILDED_IRON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GILDED_IRON })
/*      */       {
/*      */         public void registerDefaultRecipes() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public int getEnergyConsumption() {
/* 5495 */           return 20;
/*      */         }
/*      */ 
/*      */         
/*      */         public int getSpeed() {
/* 5500 */           return 3;
/*      */         }
/* 5503 */       }).registerChargeableBlock(true, 1024);
/*      */     
/* 5505 */     (new WitherAssembler((Category)Categories.ELECTRICITY, SlimefunItems.WITHER_ASSEMBLER, "WITHER_ASSEMBLER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.NETHER_STAR), SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO_EDGED_CAPACITOR
/*      */         
/* 5507 */         })).registerChargeableBlock(true, 4096);
/*      */   }
/*      */   public static boolean legacy_ore_washer = false;
/*      */   
/*      */   public static void registerPostHandler(PostSlimefunLoadingHandler handler) {
/* 5512 */     MiscSetup.post_handlers.add(handler);
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Setup\SlimefunSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */