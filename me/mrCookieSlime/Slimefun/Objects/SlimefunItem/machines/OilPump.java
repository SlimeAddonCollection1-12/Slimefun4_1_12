/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenSystem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public abstract class OilPump
/*     */   extends AContainer
/*     */ {
/*     */   public OilPump(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  34 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  36 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  40 */           OilPump.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  49 */           if (!p.hasPermission("slimefun.inventory.bypass") && !CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true)) {
/*  50 */             return false;
/*     */           }
/*     */           
/*  53 */           if (!OreGenSystem.wasResourceGenerated(OreGenSystem.getResource("原油"), b.getChunk())) {
/*  54 */             Messages.local.sendTranslation((CommandSender)p, "gps.geo.scan-required", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/*  55 */             return false;
/*     */           } 
/*  57 */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  62 */           if (flow.equals(ItemTransportFlow.INSERT)) return OilPump.this.getInputSlots(); 
/*  63 */           return OilPump.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMachineIdentifier() {
/*  70 */     return "OIL_PUMP";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryTitle() {
/*  75 */     return "&4原油泵";
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getProgressBar() {
/*  80 */     return new ItemStack(Material.DIAMOND_SPADE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/*  88 */     if (isProcessing(b)) {
/*  89 */       int timeleft = ((Integer)progress.get(b)).intValue();
/*  90 */       if (timeleft > 0) {
/*  91 */         ItemStack item = getProgressBar().clone();
/*  92 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  93 */         ItemMeta im = item.getItemMeta();
/*  94 */         im.setDisplayName(" ");
/*  95 */         List<String> lore = new ArrayList<>();
/*  96 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/*  97 */         lore.add("");
/*  98 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/*  99 */         im.setLore(lore);
/* 100 */         item.setItemMeta(im);
/*     */         
/* 102 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/* 104 */         if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 105 */           return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*     */         
/* 107 */         progress.put(b, Integer.valueOf(timeleft - 1));
/*     */       } else {
/*     */         
/* 110 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/* 111 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/* 113 */         progress.remove(b);
/* 114 */         processing.remove(b);
/*     */       }
/*     */     
/* 117 */     } else if (OreGenSystem.getSupplies(OreGenSystem.getResource("原油"), b.getChunk(), false) > 0) {
/* 118 */       for (int slot : getInputSlots()) {
/* 119 */         if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BUCKET), true)) {
/* 120 */           MachineRecipe r = new MachineRecipe(26, new ItemStack[0], new ItemStack[] { SlimefunItems.BUCKET_OF_OIL });
/* 121 */           if (!fits(b, r.getOutput()))
/* 122 */             return;  BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
/* 123 */           processing.put(b, r);
/* 124 */           progress.put(b, Integer.valueOf(r.getTicks()));
/* 125 */           OreGenSystem.setSupplies(OreGenSystem.getResource("原油"), b.getChunk(), OreGenSystem.getSupplies(OreGenSystem.getResource("原油"), b.getChunk(), false) - 1);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\OilPump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */