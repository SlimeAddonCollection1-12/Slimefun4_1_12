/*     */ package me.mrCookieSlime.Slimefun.api.inventory;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class BlockMenu
/*     */   extends ChestMenu
/*     */ {
/*     */   BlockMenuPreset preset;
/*     */   Location l;
/*  20 */   public int changes = 0;
/*     */   
/*     */   private ItemManipulationEvent event;
/*     */   
/*     */   private static String serializeLocation(Location l) {
/*  25 */     return l.getWorld().getName() + ";" + l.getBlockX() + ";" + l.getBlockY() + ";" + l.getBlockZ();
/*     */   }
/*     */   
/*     */   public BlockMenu(BlockMenuPreset preset, Location l) {
/*  29 */     super(preset.getTitle());
/*  30 */     this.preset = preset;
/*  31 */     this.l = l;
/*  32 */     this.changes = 1;
/*     */     
/*  34 */     preset.clone(this);
/*     */     
/*  36 */     getContents();
/*     */   }
/*     */   
/*     */   public BlockMenu(BlockMenuPreset preset, Location l, Config cfg) {
/*  40 */     super(preset.getTitle());
/*  41 */     this.preset = preset;
/*  42 */     this.l = l;
/*     */     
/*  44 */     for (int i = 0; i < 54; i++) {
/*  45 */       if (cfg.contains(String.valueOf(i))) addItem(i, cfg.getItem(String.valueOf(i)));
/*     */     
/*     */     } 
/*  48 */     preset.clone(this);
/*     */     
/*  50 */     if (preset.getSize() > -1 && !preset.getPresetSlots().contains(Integer.valueOf(preset.getSize() - 1)) && 
/*  51 */       cfg.contains(String.valueOf(preset.getSize() - 1))) addItem(preset.getSize() - 1, cfg.getItem(String.valueOf(preset.getSize() - 1)));
/*     */ 
/*     */     
/*  54 */     getContents();
/*     */   }
/*     */   
/*     */   public void registerEvent(ItemManipulationEvent event) {
/*  58 */     this.event = event;
/*     */   }
/*     */   
/*     */   public void save(Location l) {
/*  62 */     if (this.changes == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  67 */     getContents();
/*     */     
/*  69 */     File file = new File("data-storage/Slimefun/stored-inventories/" + serializeLocation(l) + ".sfi");
/*  70 */     Config cfg = new Config(file);
/*  71 */     cfg.setValue("preset", this.preset.getID());
/*  72 */     for (Iterator<Integer> iterator = this.preset.getInventorySlots().iterator(); iterator.hasNext(); ) { int slot = ((Integer)iterator.next()).intValue();
/*  73 */       cfg.setValue(String.valueOf(slot), getItemInSlot(slot)); }
/*     */     
/*  75 */     cfg.save();
/*     */     
/*  77 */     this.changes = 0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void move(Block b) {
/*  82 */     move(b.getLocation());
/*     */   }
/*     */   
/*     */   public void move(Location l) {
/*  86 */     delete(this.l);
/*  87 */     this.l = l;
/*  88 */     this.preset.newInstance(this, l);
/*  89 */     save(l);
/*     */   }
/*     */   
/*     */   public Block getBlock() {
/*  93 */     return this.l.getBlock();
/*     */   }
/*     */   
/*     */   public Location getLocation() {
/*  97 */     return this.l;
/*     */   }
/*     */   
/*     */   public void delete(Location l) {
/* 101 */     (new File("data-storage/Slimefun/stored-inventories/" + serializeLocation(l) + ".sfi")).delete();
/*     */   }
/*     */   
/*     */   public BlockMenuPreset getPreset() {
/* 105 */     return this.preset;
/*     */   }
/*     */   
/*     */   public boolean canOpen(Block b, Player p) {
/* 109 */     return this.preset.canOpen(b, p);
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceExistingItem(int slot, ItemStack item) {
/* 114 */     replaceExistingItem(slot, item, true);
/*     */   }
/*     */   
/*     */   public void replaceExistingItem(int slot, ItemStack item, boolean event) {
/* 118 */     ItemStack previous = getItemInSlot(slot);
/*     */     
/* 120 */     if (event && this.event != null) {
/* 121 */       item = this.event.onEvent(slot, previous, item);
/*     */     }
/* 123 */     super.replaceExistingItem(slot, item);
/*     */     
/* 125 */     this.changes++;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChestMenu addMenuOpeningHandler(ChestMenu.MenuOpeningHandler handler) {
/* 130 */     if (handler instanceof SaveHandler) {
/* 131 */       return super.addMenuOpeningHandler(new SaveHandler(this, ((SaveHandler)handler).handler));
/*     */     }
/*     */     
/* 134 */     return super.addMenuOpeningHandler(new SaveHandler(this, handler));
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 139 */     for (HumanEntity human : new ArrayList(toInventory().getViewers()))
/* 140 */       human.closeInventory(); 
/*     */   }
/*     */   
/*     */   public class SaveHandler
/*     */     implements ChestMenu.MenuOpeningHandler
/*     */   {
/*     */     BlockMenu menu;
/*     */     ChestMenu.MenuOpeningHandler handler;
/*     */     
/*     */     public SaveHandler(BlockMenu menu, ChestMenu.MenuOpeningHandler handler) {
/* 150 */       this.handler = handler;
/* 151 */       this.menu = menu;
/*     */     }
/*     */ 
/*     */     
/*     */     public void onOpen(Player p) {
/* 156 */       this.handler.onOpen(p);
/* 157 */       this.menu.changes++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\inventory\BlockMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */