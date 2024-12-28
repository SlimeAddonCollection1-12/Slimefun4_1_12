/*    */ package me.mrCookieSlime.Slimefun.GPS;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.CustomBookOverlay;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.MenuHelper;
/*    */ import me.mrCookieSlime.Slimefun.Setup.Messages;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Elevator
/*    */ {
/* 28 */   public static List<UUID> ignored = new ArrayList<>();
/*    */   
/*    */   public static void openEditor(Player p, final Block b) {
/* 31 */     ChestMenu menu = new ChestMenu("电梯设置");
/*    */     
/* 33 */     menu.addItem(4, (ItemStack)new CustomItem(new MaterialData(Material.NAME_TAG), "&7楼层名 &e(点击修改)", new String[] { "", "&r" + ChatColor.translateAlternateColorCodes('&', BlockStorage.getLocationInfo(b.getLocation(), "floor")) }));
/* 34 */     menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*    */         {
/*    */           public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3)
/*    */           {
/* 38 */             p.closeInventory();
/* 39 */             p.sendMessage("");
/* 40 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l>> &e请在聊天栏内输入你想要设置的楼层名称!"));
/* 41 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l>> &e(支持颜色代码!"));
/* 42 */             p.sendMessage("");
/*    */             
/* 44 */             MenuHelper.awaitChatInput(p, new MenuHelper.ChatHandler()
/*    */                 {
/*    */                   public boolean onChat(Player p, String message)
/*    */                   {
/* 48 */                     BlockStorage.addBlockInfo(b, "floor", message.replaceAll("&", "&"));
/*    */                     
/* 50 */                     p.sendMessage("");
/* 51 */                     p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l>> &e成功设置楼层名称:"));
/* 52 */                     p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l>> &r" + ChatColor.translateAlternateColorCodes('&', message)));
/* 53 */                     p.sendMessage("");
/*    */                     
/* 55 */                     Elevator.openEditor(p, b);
/*    */                     
/* 57 */                     return false;
/*    */                   }
/*    */                 });
/* 60 */             return false;
/*    */           }
/*    */         });
/*    */     
/* 64 */     menu.open(new Player[] { p });
/*    */   }
/*    */   
/*    */   public static void openDialogue(Player p, Block b) {
/* 68 */     if (ignored.contains(p.getUniqueId())) {
/* 69 */       ignored.remove(p.getUniqueId());
/*    */       return;
/*    */     } 
/* 72 */     TellRawMessage tellraw = new TellRawMessage();
/* 73 */     tellraw.addText("&3- 请选择目的地 -\n\n");
/* 74 */     int index = 1;
/* 75 */     for (int y = b.getWorld().getMaxHeight(); y > 0; y--) {
/* 76 */       Block block = b.getWorld().getBlockAt(b.getX(), y, b.getZ());
/* 77 */       if (BlockStorage.check(block, "ELEVATOR_PLATE")) {
/* 78 */         String floor = ChatColor.translateAlternateColorCodes('&', BlockStorage.getLocationInfo(block.getLocation(), "floor"));
/* 79 */         if (block.getY() == b.getY()) {
/* 80 */           tellraw.addText("&7> " + index + ". &r" + floor + "\n");
/* 81 */           tellraw.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, "\n&e这是你目前所在的楼层:\n&r" + floor + "\n");
/*    */         } else {
/*    */           
/* 84 */           tellraw.addText("&7" + index + ". &r" + floor + "\n");
/* 85 */           tellraw.addHoverEvent(TellRawMessage.HoverAction.SHOW_TEXT, "\n&e点击传送至此楼层\n&r" + floor + "\n");
/* 86 */           tellraw.addClickEvent(TellRawMessage.ClickAction.RUN_COMMAND, "/sf elevator " + block.getX() + " " + block.getY() + " " + block.getZ() + " ");
/*    */         } 
/*    */         
/* 89 */         index++;
/*    */       } 
/*    */     } 
/* 92 */     if (index > 2) { (new CustomBookOverlay("Elevator", "Slimefun", new TellRawMessage[] { tellraw })).open(p); }
/* 93 */     else { Messages.local.sendTranslation((CommandSender)p, "machines.ELEVATOR.no-destinations", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GPS\Elevator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */