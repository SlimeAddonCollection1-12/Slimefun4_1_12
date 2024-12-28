/*    */ package me.mrCookieSlime.Slimefun.api.inventory;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class UniversalBlockMenu
/*    */   extends ChestMenu
/*    */ {
/*    */   BlockMenuPreset preset;
/*    */   ItemManipulationEvent event;
/* 19 */   public int changes = 0;
/*    */   
/*    */   public UniversalBlockMenu(BlockMenuPreset preset) {
/* 22 */     super(preset.getTitle());
/* 23 */     this.preset = preset;
/* 24 */     this.changes = 1;
/*    */     
/* 26 */     preset.clone(this);
/*    */     
/* 28 */     save();
/*    */   }
/*    */   
/*    */   public UniversalBlockMenu(BlockMenuPreset preset, Config cfg) {
/* 32 */     super(preset.getTitle());
/* 33 */     this.preset = preset;
/*    */     
/* 35 */     for (int i = 0; i < 54; i++) {
/* 36 */       if (cfg.contains(String.valueOf(i))) addItem(i, cfg.getItem(String.valueOf(i)));
/*    */     
/*    */     } 
/* 39 */     preset.clone(this);
/*    */     
/* 41 */     if (preset.getSize() > -1 && !preset.getPresetSlots().contains(Integer.valueOf(preset.getSize() - 1)) && 
/* 42 */       cfg.contains(String.valueOf(preset.getSize() - 1))) addItem(preset.getSize() - 1, cfg.getItem(String.valueOf(preset.getSize() - 1)));
/*    */ 
/*    */     
/* 45 */     getContents();
/*    */   }
/*    */   
/*    */   public void registerEvent(ItemManipulationEvent event) {
/* 49 */     this.event = event;
/*    */   }
/*    */   
/*    */   public void save() {
/* 53 */     if (this.changes == 0)
/*    */       return; 
/* 55 */     getContents();
/*    */     
/* 57 */     File file = new File("data-storage/Slimefun/universal-inventories/" + this.preset.getID() + ".sfi");
/* 58 */     Config cfg = new Config(file);
/* 59 */     cfg.setValue("preset", this.preset.getID());
/* 60 */     for (Iterator<Integer> iterator = this.preset.getInventorySlots().iterator(); iterator.hasNext(); ) { int slot = ((Integer)iterator.next()).intValue();
/* 61 */       cfg.setValue(String.valueOf(slot), getItemInSlot(slot)); }
/*    */     
/* 63 */     cfg.save();
/*    */     
/* 65 */     this.changes = 0;
/*    */   }
/*    */   
/*    */   public BlockMenuPreset getPreset() {
/* 69 */     return this.preset;
/*    */   }
/*    */   
/*    */   public boolean canOpen(Block b, Player p) {
/* 73 */     return this.preset.canOpen(b, p);
/*    */   }
/*    */ 
/*    */   
/*    */   public void replaceExistingItem(int slot, ItemStack item) {
/* 78 */     replaceExistingItem(slot, item, true);
/*    */   }
/*    */   
/*    */   public void replaceExistingItem(int slot, ItemStack item, boolean event) {
/* 82 */     ItemStack previous = getItemInSlot(slot);
/*    */     
/* 84 */     if (event && this.event != null) {
/* 85 */       item = this.event.onEvent(slot, previous, item);
/*    */     }
/* 87 */     super.replaceExistingItem(slot, item);
/*    */     
/* 89 */     this.changes++;
/*    */   }
/*    */   
/*    */   public void close() {
/* 93 */     for (HumanEntity human : new ArrayList(toInventory().getViewers()))
/* 94 */       human.closeInventory(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\inventory\UniversalBlockMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */