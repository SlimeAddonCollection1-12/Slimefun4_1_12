/*    */ package me.mrCookieSlime.Slimefun.api;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Backpacks
/*    */ {
/*    */   public static String createBackpack(Player p, int size) {
/* 18 */     List<Integer> ids = new ArrayList<>();
/* 19 */     Config cfg = new Config(new File("data-storage/Slimefun/Players/" + p.getUniqueId() + ".yml"));
/* 20 */     for (int i = 0; i < 1000 && 
/* 21 */       cfg.contains("backpacks." + i + ".size"); i++) ids.add(Integer.valueOf(i));
/*    */ 
/*    */     
/* 24 */     int id = ids.isEmpty() ? 0 : (((Integer)ids.get(ids.size() - 1)).intValue() + 1);
/* 25 */     ids.add(Integer.valueOf(id));
/*    */     
/* 27 */     cfg.setValue("backpacks." + id + ".size", Integer.valueOf(size));
/* 28 */     cfg.save();
/* 29 */     return p.getUniqueId() + "#" + id;
/*    */   }
/*    */   
/*    */   public static void openBackpack(Player p, ItemStack item) {
/* 33 */     Inventory inv = getInventory(p, item);
/* 34 */     if (inv != null) p.openInventory(inv); 
/*    */   }
/*    */   
/*    */   public static Inventory getInventory(Player p, ItemStack item) {
/* 38 */     if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) return null; 
/* 39 */     int id = -1;
/* 40 */     String uuid = "";
/* 41 */     for (String line : item.getItemMeta().getLore()) {
/* 42 */       if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
/*    */         try {
/* 44 */           id = Integer.parseInt(line.split("#")[1]);
/* 45 */           uuid = line.split("#")[0].replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
/* 46 */         } catch (NumberFormatException numberFormatException) {}
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 51 */     if (id >= 0) {
/* 52 */       Config cfg = new Config(new File("data-storage/Slimefun/Players/" + uuid + ".yml"));
/* 53 */       int size = cfg.getInt("backpacks." + id + ".size");
/* 54 */       Inventory inv = Bukkit.createInventory(null, size, "背包 [" + size + " 槽]");
/* 55 */       for (int i = 0; i < size; i++) {
/* 56 */         inv.setItem(i, cfg.getItem("backpacks." + id + ".contents." + i));
/*    */       }
/* 58 */       return inv;
/*    */     } 
/* 60 */     return null;
/*    */   }
/*    */   
/*    */   public static void saveBackpack(Inventory inv, ItemStack item) {
/* 64 */     int id = -1;
/* 65 */     String uuid = "";
/* 66 */     for (String line : item.getItemMeta().getLore()) {
/* 67 */       if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
/*    */         try {
/* 69 */           id = Integer.parseInt(line.split("#")[1]);
/* 70 */           uuid = line.split("#")[0].replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
/* 71 */         } catch (NumberFormatException numberFormatException) {}
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 76 */     if (id >= 0) {
/* 77 */       Config cfg = new Config(new File("data-storage/Slimefun/Players/" + uuid + ".yml"));
/* 78 */       for (int i = 0; i < (inv.getContents()).length; i++) {
/* 79 */         cfg.setValue("backpacks." + id + ".contents." + i, inv.getContents()[i]);
/*    */       }
/* 81 */       cfg.save();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\Backpacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */