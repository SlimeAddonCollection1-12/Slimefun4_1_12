/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*    */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*    */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ public class TrashCan
/*    */   extends SlimefunItem {
/* 24 */   private static final int[] border = new int[] { 0, 1, 2, 3, 5, 4, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
/*    */   
/*    */   public TrashCan(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
/* 27 */     super(category, item, name, recipeType, recipe);
/*    */     
/* 29 */     new BlockMenuPreset(name, getInventoryTitle())
/*    */       {
/*    */         public void init()
/*    */         {
/* 33 */           TrashCan.this.constructMenu(this);
/*    */         }
/*    */ 
/*    */ 
/*    */         
/*    */         public void newInstance(BlockMenu menu, Block b) {}
/*    */ 
/*    */         
/*    */         public boolean canOpen(Block b, Player p) {
/* 42 */           return true;
/*    */         }
/*    */ 
/*    */         
/*    */         public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
/* 47 */           if (flow.equals(ItemTransportFlow.INSERT)) return TrashCan.this.getInputSlots(); 
/* 48 */           return new int[0];
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   private void constructMenu(BlockMenuPreset preset) {
/* 55 */     for (int i : border) {
/* 56 */       preset.addItem(i, (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)14), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*    */           {
/*    */             
/*    */             public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3)
/*    */             {
/* 61 */               return false;
/*    */             }
/*    */           });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInventoryTitle() {
/* 69 */     return "&4垃圾桶";
/*    */   }
/*    */   
/*    */   public int[] getInputSlots() {
/* 73 */     return new int[] { 10, 11, 12, 13, 14, 15, 16 };
/*    */   }
/*    */ 
/*    */   
/*    */   public void register(boolean slimefun) {
/* 78 */     addItemHandler(new ItemHandler[] { (ItemHandler)new BlockTicker()
/*    */           {
/*    */             public void uniqueTick() {}
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             public void tick(Block b, SlimefunItem item, Config data) {
/* 86 */               BlockMenu menu = BlockStorage.getInventory(b);
/* 87 */               for (int slot : TrashCan.this.getInputSlots()) {
/* 88 */                 menu.replaceExistingItem(slot, null);
/*    */               }
/*    */             }
/*    */ 
/*    */             
/*    */             public boolean isSynchronized() {
/* 94 */               return false;
/*    */             }
/*    */           } });
/*    */     
/* 98 */     super.register(slimefun);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\machines\TrashCan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */