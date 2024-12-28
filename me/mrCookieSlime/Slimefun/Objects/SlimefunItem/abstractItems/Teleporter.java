/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;
/*    */ 
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class Teleporter
/*    */   extends SlimefunItem
/*    */ {
/*    */   public Teleporter(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
/* 17 */     super(category, item, id, recipeType, recipe);
/*    */     
/* 19 */     SlimefunItem.registerBlockHandler(id, new SlimefunBlockHandler()
/*    */         {
/*    */           public void onPlace(Player p, Block b, SlimefunItem item)
/*    */           {
/* 23 */             BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
/*    */           }
/*    */ 
/*    */           
/*    */           public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
/* 28 */             return true;
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public abstract void onInteract(Player paramPlayer, Block paramBlock) throws Exception;
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\abstractItems\Teleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */