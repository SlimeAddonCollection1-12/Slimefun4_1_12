/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
/*     */ import me.mrCookieSlime.Slimefun.GEO.OreGenSystem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public abstract class ADrill
/*     */   extends AContainer {
/*  31 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44, 9, 10, 11, 12, 18, 21, 27, 28, 29, 30, 19, 20 };
/*  32 */   private static final int[] border_out = new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ADrill(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/*  40 */     super(category, item, id, recipeType, recipe);
/*     */     
/*  42 */     new BlockMenuPreset(id, getInventoryTitle())
/*     */       {
/*     */         public void init()
/*     */         {
/*  46 */           constructMenu(this);
/*     */         }
/*     */ 
/*     */         
/*     */         private void constructMenu(BlockMenuPreset preset) {
/*  51 */           for (int i : ADrill.border) {
/*  52 */             preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)7), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   
/*     */                   public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  57 */                     return false;
/*     */                   }
/*     */                 });
/*     */           } 
/*     */           
/*  62 */           for (int i : ADrill.border_out) {
/*  63 */             preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */                 {
/*     */                   
/*     */                   public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                   {
/*  68 */                     return false;
/*     */                   }
/*     */                 });
/*     */           } 
/*     */ 
/*     */           
/*  74 */           preset.addItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */               {
/*     */                 
/*     */                 public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */                 {
/*  79 */                   return false;
/*     */                 }
/*     */               });
/*     */ 
/*     */           
/*  84 */           for (int i : ADrill.this.getOutputSlots()) {
/*  85 */             preset.addMenuClickHandler(i, (ChestMenu.MenuClickHandler)new ChestMenu.AdvancedMenuClickHandler()
/*     */                 {
/*     */                   public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action)
/*     */                   {
/*  89 */                     return false;
/*     */                   }
/*     */ 
/*     */                   
/*     */                   public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
/*  94 */                     return (cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR);
/*     */                   }
/*     */                 });
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/* 106 */           if (!p.hasPermission("slimefun.inventory.bypass") && !CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true)) {
/* 107 */             return false;
/*     */           }
/* 109 */           if (!ProtectionUtils.canAccessItem(p, b)) {
/* 110 */             return false;
/*     */           }
/*     */           
/* 113 */           if (!OreGenSystem.wasResourceGenerated(ADrill.this.getOreGenResource(), b.getChunk())) {
/* 114 */             Messages.local.sendTranslation((CommandSender)p, "gps.geo.scan-required", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
/* 115 */             return false;
/*     */           } 
/* 117 */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 122 */           if (flow.equals(ItemTransportFlow.INSERT)) return ADrill.this.getInputSlots(); 
/* 123 */           return ADrill.this.getOutputSlots();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getInputSlots() {
/* 130 */     return new int[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerDefaultRecipes() {}
/*     */ 
/*     */   
/*     */   protected void tick(Block b) {
/* 138 */     if (isProcessing(b)) {
/* 139 */       int timeleft = ((Integer)progress.get(b)).intValue();
/* 140 */       if (timeleft > 0) {
/* 141 */         ItemStack item = getProgressBar().clone();
/* 142 */         item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 143 */         ItemMeta im = item.getItemMeta();
/* 144 */         im.setDisplayName(" ");
/* 145 */         List<String> lore = new ArrayList<>();
/* 146 */         lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe)processing.get(b)).getTicks()));
/* 147 */         lore.add("");
/* 148 */         lore.add(MachineHelper.getTimeLeft(timeleft / 2));
/* 149 */         im.setLore(lore);
/* 150 */         item.setItemMeta(im);
/*     */         
/* 152 */         BlockStorage.getInventory(b).replaceExistingItem(22, item);
/*     */         
/* 154 */         if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/* 155 */           return;  ChargableBlock.addCharge(b, -getEnergyConsumption());
/*     */         
/* 157 */         progress.put(b, Integer.valueOf(timeleft - 1));
/*     */       } else {
/*     */         
/* 160 */         BlockStorage.getInventory(b).replaceExistingItem(22, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]));
/* 161 */         pushItems(b, ((MachineRecipe)processing.get(b)).getOutput());
/*     */         
/* 163 */         progress.remove(b);
/* 164 */         processing.remove(b);
/*     */       }
/*     */     
/* 167 */     } else if (OreGenSystem.getSupplies(getOreGenResource(), b.getChunk(), false) > 0) {
/* 168 */       MachineRecipe r = new MachineRecipe(getProcessingTime() / getSpeed(), new ItemStack[0], getOutputItems());
/* 169 */       if (!fits(b, r.getOutput()))
/* 170 */         return;  processing.put(b, r);
/* 171 */       progress.put(b, Integer.valueOf(r.getTicks()));
/* 172 */       OreGenSystem.setSupplies(getOreGenResource(), b.getChunk(), OreGenSystem.getSupplies(getOreGenResource(), b.getChunk(), false) - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract OreGenResource getOreGenResource();
/*     */   
/*     */   public abstract ItemStack[] getOutputItems();
/*     */   
/*     */   public abstract int getProcessingTime();
/*     */   
/*     */   public abstract int getSpeed();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\ADrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */