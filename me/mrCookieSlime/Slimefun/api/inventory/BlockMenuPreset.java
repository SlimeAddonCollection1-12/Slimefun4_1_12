/*     */ package me.mrCookieSlime.Slimefun.api.inventory;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public abstract class BlockMenuPreset
/*     */   extends ChestMenu {
/*  20 */   public static Map<String, BlockMenuPreset> presets = new HashMap<>();
/*     */   
/*     */   private String title;
/*  23 */   private Set<Integer> occupied = new HashSet<>();
/*     */   private String id;
/*  25 */   private int size = -1;
/*     */   
/*     */   private boolean universal;
/*     */   private ItemManipulationEvent event;
/*     */   
/*     */   public BlockMenuPreset(String id, String title) {
/*  31 */     super(title);
/*  32 */     this.id = id;
/*  33 */     this.title = title;
/*  34 */     init();
/*  35 */     this.universal = false;
/*  36 */     presets.put(id, this);
/*     */   }
/*     */   
/*     */   public void registerEvent(ItemManipulationEvent event) {
/*  40 */     this.event = event;
/*     */   }
/*     */   
/*     */   public BlockMenuPreset(String id, String title, boolean universal) {
/*  44 */     super(title);
/*  45 */     this.id = id;
/*  46 */     this.title = title;
/*  47 */     init();
/*  48 */     this.universal = universal;
/*  49 */     presets.put(id, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void init();
/*     */   
/*     */   public abstract void newInstance(BlockMenu paramBlockMenu, Block paramBlock);
/*     */   
/*     */   public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/*  58 */     return getSlotsAccessedByItemTransport(flow);
/*     */   } public abstract boolean canOpen(Block paramBlock, Player paramPlayer);
/*     */   public abstract int[] getSlotsAccessedByItemTransport(ItemTransportFlow paramItemTransportFlow);
/*     */   public int[] getSlotsAccessedByItemTransport(UniversalBlockMenu menu, ItemTransportFlow flow, ItemStack item) {
/*  62 */     return getSlotsAccessedByItemTransport(flow);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChestMenu addItem(int slot, ItemStack item) {
/*  67 */     this.occupied.add(Integer.valueOf(slot));
/*  68 */     return super.addItem(slot, item);
/*     */   }
/*     */   
/*     */   public ChestMenu setSize(int size) {
/*  72 */     this.size = size;
/*  73 */     return this;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  77 */     return this.size;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  81 */     return this.title;
/*     */   }
/*     */   
/*     */   public Set<Integer> getPresetSlots() {
/*  85 */     return this.occupied;
/*     */   }
/*     */   
/*     */   public Set<Integer> getInventorySlots() {
/*  89 */     Set<Integer> empty = new HashSet<>();
/*  90 */     if (this.size > -1) {
/*  91 */       for (int i = 0; i < this.size; i++) {
/*  92 */         if (!this.occupied.contains(Integer.valueOf(i))) empty.add(Integer.valueOf(i));
/*     */       
/*     */       } 
/*     */     } else {
/*  96 */       for (int i = 0; i < toInventory().getSize(); i++) {
/*  97 */         if (!this.occupied.contains(Integer.valueOf(i))) empty.add(Integer.valueOf(i)); 
/*     */       } 
/*     */     } 
/* 100 */     return empty;
/*     */   }
/*     */   
/*     */   public static BlockMenuPreset getPreset(String id) {
/* 104 */     return presets.get(id);
/*     */   }
/*     */   
/*     */   public static boolean isInventory(String id) {
/* 108 */     return presets.containsKey(id);
/*     */   }
/*     */   
/*     */   public static boolean isUniversalInventory(String id) {
/* 112 */     return (presets.containsKey(id) && ((BlockMenuPreset)presets.get(id)).isUniversal());
/*     */   }
/*     */   
/*     */   public boolean isUniversal() {
/* 116 */     return this.universal;
/*     */   }
/*     */   
/*     */   public void clone(BlockMenu menu) {
/* 120 */     menu.setPlayerInventoryClickable(true);
/*     */     
/* 122 */     for (Iterator<Integer> iterator = this.occupied.iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 123 */       menu.addItem(i, getItemInSlot(i)); }
/*     */ 
/*     */     
/* 126 */     if (this.size > -1) menu.addItem(this.size - 1, null);
/*     */     
/* 128 */     newInstance(menu, menu.getLocation());
/* 129 */     for (int slot = 0; slot < 54; slot++) {
/* 130 */       if (getMenuClickHandler(slot) != null) menu.addMenuClickHandler(slot, getMenuClickHandler(slot));
/*     */     
/*     */     } 
/* 133 */     menu.addMenuOpeningHandler(getMenuOpeningHandler());
/* 134 */     menu.addMenuCloseHandler(getMenuCloseHandler());
/* 135 */     menu.registerEvent(this.event);
/*     */   }
/*     */   
/*     */   public void clone(UniversalBlockMenu menu) {
/* 139 */     menu.setPlayerInventoryClickable(true);
/*     */     
/* 141 */     for (Iterator<Integer> iterator = this.occupied.iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 142 */       menu.addItem(i, getItemInSlot(i)); }
/*     */ 
/*     */     
/* 145 */     if (this.size > -1) menu.addItem(this.size - 1, null); 
/* 146 */     for (int slot = 0; slot < 54; slot++) {
/* 147 */       if (getMenuClickHandler(slot) != null) menu.addMenuClickHandler(slot, getMenuClickHandler(slot));
/*     */     
/*     */     } 
/* 150 */     menu.addMenuOpeningHandler(getMenuOpeningHandler());
/* 151 */     menu.addMenuCloseHandler(getMenuCloseHandler());
/* 152 */     menu.registerEvent(this.event);
/*     */   }
/*     */   
/*     */   public String getID() {
/* 156 */     return this.id;
/*     */   }
/*     */   
/*     */   public void newInstance(final BlockMenu menu, final Location l) {
/* 160 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable() {
/*     */           public void run() {
/* 162 */             BlockMenuPreset.this.newInstance(menu, l.getBlock());
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\inventory\BlockMenuPreset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */