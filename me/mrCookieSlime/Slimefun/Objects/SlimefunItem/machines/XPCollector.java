/*     */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class XPCollector
/*     */   extends SlimefunItem
/*     */ {
/*  34 */   private static final int[] border = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
/*     */   
/*     */   public XPCollector(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/*  37 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  39 */     new BlockMenuPreset(name, "&a经验收集器")
/*     */       {
/*     */         public void init()
/*     */         {
/*  43 */           XPCollector.this.constructMenu(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void newInstance(BlockMenu menu, Block b) {}
/*     */ 
/*     */         
/*     */         public boolean canOpen(Block b, Player p) {
/*  52 */           return (p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
/*     */         }
/*     */ 
/*     */         
/*     */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/*  57 */           if (flow.equals(ItemTransportFlow.WITHDRAW)) return XPCollector.this.getOutputSlots(); 
/*  58 */           return new int[0];
/*     */         }
/*     */       };
/*     */     
/*  62 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */         {
/*     */           public void onPlace(Player p, Block b, SlimefunItem item)
/*     */           {
/*  66 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/*  71 */             me.mrCookieSlime.Slimefun.holograms.XPCollector.getArmorStand(b).remove();
/*  72 */             BlockMenu inv = BlockStorage.getInventory(b);
/*  73 */             if (inv != null) {
/*  74 */               for (int slot : XPCollector.this.getOutputSlots()) {
/*  75 */                 if (inv.getItemInSlot(slot) != null) {
/*  76 */                   b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
/*  77 */                   inv.replaceExistingItem(slot, null);
/*     */                 } 
/*     */               } 
/*     */             }
/*  81 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/*  87 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/*  88 */     Inventory inv = Bukkit.createInventory(null, size);
/*  89 */     for (int i = 0; i < size; i++) {
/*  90 */       inv.setItem(i, (ItemStack)new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/*  92 */     for (int slot : getOutputSlots()) {
/*  93 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/*  95 */     return inv;
/*     */   }
/*     */   
/*     */   protected boolean fits(Block b, ItemStack... items) {
/*  99 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushItems(Block b, ItemStack... items) {
/* 103 */     Inventory inv = inject(b);
/* 104 */     inv.addItem(items);
/*     */     
/* 106 */     for (int slot : getOutputSlots()) {
/* 107 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 112 */     return new int[] { 12, 13, 14 };
/*     */   }
/*     */ 
/*     */   
/*     */   protected void constructMenu(BlockMenuPreset preset) {
/* 117 */     for (int i : border) {
/* 118 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)10), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */           {
/*     */             
/*     */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*     */             {
/* 123 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnergyConsumption() {
/* 131 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(boolean slimefun) {
/* 136 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*     */           {
/*     */             public void tick(Block b, SlimefunItem sf, Config data)
/*     */             {
/*     */               try {
/* 141 */                 XPCollector.this.tick(b);
/* 142 */               } catch (Exception e) {
/* 143 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void uniqueTick() {}
/*     */ 
/*     */             
/*     */             public boolean isSynchronized() {
/* 153 */               return true;
/*     */             }
/*     */           } });
/*     */     
/* 157 */     super.register(slimefun);
/*     */   }
/*     */   
/*     */   protected void tick(Block b) throws Exception {
/* 161 */     Iterator<Entity> iterator = me.mrCookieSlime.Slimefun.holograms.XPCollector.getArmorStand(b).getNearbyEntities(4.0D, 4.0D, 4.0D).iterator();
/* 162 */     while (iterator.hasNext()) {
/* 163 */       Entity n = iterator.next();
/* 164 */       if (n instanceof ExperienceOrb) {
/* 165 */         if (ChargableBlock.getCharge(b) < getEnergyConsumption())
/*     */           return; 
/* 167 */         if (n.isValid()) {
/* 168 */           int xp = getEXP(b) + ((ExperienceOrb)n).getExperience();
/*     */           
/* 170 */           ChargableBlock.addCharge(b, -getEnergyConsumption());
/* 171 */           n.remove();
/*     */           
/* 173 */           int withdrawn = 0; int level;
/* 174 */           for (level = 0; level < getEXP(b); level += 10) {
/* 175 */             if (fits(b, new ItemStack[] { (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0) })) {
/* 176 */               withdrawn += 10;
/* 177 */               pushItems(b, new ItemStack[] { (ItemStack)new CustomItem(Material.EXP_BOTTLE, "&a弗拉斯克之瓶", 0) });
/*     */             } 
/*     */           } 
/* 180 */           BlockStorage.addBlockInfo(b, "stored-exp", String.valueOf(xp - withdrawn));
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getEXP(Block b) {
/* 189 */     Config cfg = BlockStorage.getLocationInfo(b.getLocation());
/* 190 */     if (cfg.contains("stored-exp")) return Integer.parseInt(cfg.getString("stored-exp"));
/*     */     
/* 192 */     BlockStorage.addBlockInfo(b, "stored-exp", "0");
/* 193 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\XPCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */