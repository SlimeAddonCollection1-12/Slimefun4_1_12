/*    */ package me.mrCookieSlime.Slimefun.Lists;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomArmor;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SeasonCategory;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Color;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Categories
/*    */ {
/* 22 */   public static Category WEAPONS = new Category((ItemStack)new CustomItem(Material.GOLD_SWORD, "&7武器", 0, new String[] { "", "&a> 点击打开" }), 1);
/* 23 */   public static Category PORTABLE = null;
/* 24 */   public static Category FOOD = new Category((ItemStack)new CustomItem(Material.APPLE, "&7食物", 0, new String[] { "", "&a> 点击打开" }), 2);
/* 25 */   public static Category MACHINES_1 = null;
/* 26 */   public static LockedCategory ELECTRICITY = null;
/* 27 */   public static LockedCategory GPS = null;
/* 28 */   public static Category ARMOR = new Category((ItemStack)new CustomItem(Material.IRON_CHESTPLATE, "&7护甲", 0, new String[] { "", "&a> 点击打开" }), 2);
/* 29 */   public static Category LUMPS_AND_MAGIC = new Category((ItemStack)new CustomItem(Material.FIREBALL, "&7魔法物品", 0, new String[] { "", "&a> 点击打开" }), 2);
/* 30 */   public static Category MAGIC = new Category((ItemStack)new CustomItem(Material.BLAZE_POWDER, "&7魔法道具", 0, new String[] { "", "&a> 点击打开" }), 3);
/* 31 */   public static Category MISC = null;
/* 32 */   public static Category TECH = new Category((ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&7科技工具", 0, new String[] { "", "&a> 点击打开" }), Color.SILVER), 3);
/* 33 */   public static Category RESOURCES = null;
/* 34 */   public static Category CARGO = null;
/* 35 */   public static Category TECH_MISC = new Category((ItemStack)new CustomItem(Material.REDSTONE_COMPARATOR, "&7科技组件", 0, new String[] { "", "&a> 点击打开" }), 2);
/* 36 */   public static Category MAGIC_ARMOR = new Category((ItemStack)new CustomItem(Material.GOLD_CHESTPLATE, "&7魔法防具", 0, new String[] { "", "&a> 点击打开" }), 2);
/* 37 */   public static Category TALISMANS_1 = new Category((ItemStack)new CustomItem(Material.EMERALD, "&7护身符 - &a等级 I", 0, new String[] { "", "&a> 点击打开" }), 2);
/* 38 */   public static LockedCategory TALISMANS_2 = new LockedCategory((ItemStack)new CustomItem(Material.EMERALD, "&7护身符 - &a等级 II", 0, new String[] { "", "&a> 点击打开" }), 3, new Category[] { TALISMANS_1 });
/* 39 */   public static Category TOOLS = new Category((ItemStack)new CustomItem(Material.GOLD_PICKAXE, "&7工具", 0, new String[] { "", "&a> 点击打开" }), 1);
/* 40 */   public static SeasonCategory CHRISTMAS = new SeasonCategory(12, 1, (ItemStack)new CustomItem(Material.NETHER_STAR, "&a圣诞&c物品", 0, new String[] { "", ChatColor.translateAlternateColorCodes('&', "&c圣诞气氛浓厚哦?"), "&a> 点击打开" }));
/* 41 */   public static SeasonCategory VALENTINES_DAY = new SeasonCategory(2, 2, (ItemStack)new CustomItem(Material.RED_ROSE, "&d情人节", 0, new String[] { "", ChatColor.translateAlternateColorCodes('&', "&c全世界都充满着恋爱的酸臭味"), "&a> 点击打开" }));
/* 42 */   public static SeasonCategory EASTER = new SeasonCategory(4, 2, (ItemStack)new CustomItem(Material.EGG, "&6复活节", 0, new String[] { "", ChatColor.translateAlternateColorCodes('&', "&a搞点彩蛋, 搞点事情"), "&a> 点击打开" }));
/* 43 */   public static SeasonCategory BIRTHDAY = new SeasonCategory(10, 1, (ItemStack)new CustomItem(Material.FIREWORK, "&a&lmrCookieSlime 的生日 &7(10月26日)", 0, new String[] { "", ChatColor.translateAlternateColorCodes('&', "&a为他祝福吧!"), "&a> 点击打开" }));
/*    */   
/* 45 */   public static Category QUANTUM_MACHINES = null;
/*    */   
/*    */   static {
/*    */     try {
/* 49 */       MISC = new Category((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRkYTk3ZjA4MGUzOTViODQyYzRjYzgyYTg0MDgyM2Q0ZGJkOGNhNjg4YTIwNjg1M2U1NzgzZTRiZmRjMDEyIn19fQ=="), "&7杂项", new String[] { "", "&a> 点击打开" }), 2);
/* 50 */       PORTABLE = new Category((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&7道具", new String[] { "", "&a> 点击打开" }), 1);
/* 51 */       MACHINES_1 = new Category((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&7基础机器", new String[] { "", "&a> 点击打开" }), 1);
/* 52 */       ELECTRICITY = new LockedCategory((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU4NDQzMmFmNmYzODIxNjcxMjAyNThkMWVlZThjODdjNmU3NWQ5ZTQ3OWU3YjBkNGM3YjZhZDQ4Y2ZlZWYifX19"), "&b电力与能源", new String[] { "", "&a> 点击打开" }), 4, new Category[] { MACHINES_1 });
/* 53 */       GPS = new LockedCategory((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&bGPS 科技机器", new String[] { "", "&a> 点击打开" }), 4, new Category[] { MACHINES_1 });
/* 54 */       RESOURCES = new Category((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2U4ZjVhZGIxNGQ2YzlmNmI4MTBkMDI3NTQzZjFhOGMxZjQxN2UyZmVkOTkzYzk3YmNkODljNzRmNWUyZTgifX19"), "&7资源", new String[] { "", "&a> 点击打开" }), 1);
/* 55 */       CARGO = (Category)new LockedCategory((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTUxMGJjODUzNjJhMTMwYTZmZjlkOTFmZjExZDZmYTQ2ZDdkMTkxMmEzNDMxZjc1MTU1OGVmM2M0ZDljMiJ9fX0="), "&c运输管理系统", new String[] { "", "&a> 点击打开" }), 4, new Category[] { MACHINES_1 });
/* 56 */       QUANTUM_MACHINES = new Category((ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY4NGY0YTZlZDE0Mjg2NWRiMDkzOGU0ODc2NzY4NDlhNTRkNjQzNzhlMmU5ZTdmNzEzYjliMWU5ZDA0MSJ9fX0="), "&b量子科技&7-&e机器", new String[] { "", "&a> 点击打开" }));
/* 57 */     } catch (Exception e) {
/* 58 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Lists\Categories.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */