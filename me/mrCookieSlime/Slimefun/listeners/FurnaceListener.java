/*    */ package me.mrCookieSlime.Slimefun.listeners;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Recipe.RecipeCalculator;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.EnhancedFurnace;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.block.Furnace;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.FurnaceBurnEvent;
/*    */ import org.bukkit.event.inventory.FurnaceSmeltEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class FurnaceListener
/*    */   implements Listener {
/*    */   public FurnaceListener(SlimefunStartup plugin) {
/* 20 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void onBurn(FurnaceBurnEvent e) {
/* 25 */     if (BlockStorage.check(e.getBlock()) != null && BlockStorage.check(e.getBlock()) instanceof EnhancedFurnace) {
/* 26 */       EnhancedFurnace furnace = (EnhancedFurnace)BlockStorage.check(e.getBlock());
/* 27 */       if (furnace.getFuelEfficiency() > 0) e.setBurnTime((int)((1.0D + 0.2D * furnace.getFuelEfficiency()) * e.getBurnTime())); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void onSmelt(FurnaceSmeltEvent e) {
/* 33 */     if (BlockStorage.check(e.getBlock()) != null && BlockStorage.check(e.getBlock()) instanceof EnhancedFurnace) {
/* 34 */       EnhancedFurnace furnace = (EnhancedFurnace)BlockStorage.check(e.getBlock());
/* 35 */       Furnace f = (Furnace)e.getBlock().getState();
/* 36 */       int amount = f.getInventory().getSmelting().getType().toString().endsWith("_ORE") ? furnace.getOutput() : 1;
/* 37 */       ItemStack output = RecipeCalculator.getSmeltedOutput(f.getInventory().getSmelting().getType());
/* 38 */       ItemStack result = f.getInventory().getResult();
/* 39 */       if (result != null) result = result.clone(); 
/* 40 */       f.getInventory().setResult(null);
/* 41 */       if (result != null) { f.getInventory().setResult((ItemStack)new CustomItem(result, (result.getAmount() + amount > result.getMaxStackSize()) ? result.getMaxStackSize() : (result.getAmount() + amount))); }
/* 42 */       else { f.getInventory().setResult((ItemStack)new CustomItem(output, (output.getAmount() + amount > output.getType().getMaxStackSize()) ? output.getType().getMaxStackSize() : (output.getAmount() + amount))); }
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\FurnaceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */