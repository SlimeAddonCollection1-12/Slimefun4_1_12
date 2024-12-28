/*    */ package me.mrCookieSlime.Slimefun.Lists;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class NarItems {
/*  8 */   public static ItemStack UU = null;
/*  9 */   public static ItemStack UU_MACHINE = null;
/* 10 */   public static ItemStack ITEM_COPER = null;
/* 11 */   public static ItemStack ITEM_CREATOR = null;
/* 12 */   public static ItemStack IRIDIUM = null;
/*    */   
/*    */   static {
/*    */     try {
/* 16 */       UU = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGYxNGYzMTc5Yjg2ZjY5YjNlZmE3NDcyZGFjYWViMjMzOWY2MjkwZDJkODE3MzYyNzkzMzQ4YWJkOThlMDIxIn19fQ=="), "&d元物质", new String[] { "", "§7这堆元物质被特殊的力场禁锢而得以保存", "§7据研究元物质是万物的最基本组成粒子", "§7珍贵的元物质有非常广泛的用途" });
/*    */       
/* 18 */       UU_MACHINE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY4NGY0YTZlZDE0Mjg2NWRiMDkzOGU0ODc2NzY4NDlhNTRkNjQzNzhlMmU5ZTdmNzEzYjliMWU5ZDA0MSJ9fX0="), "&d元物质分离机", new String[] { "", "&7能够将特定的物质分离为的元物质", "&7并将这些元物质收集加以力场保存", "§7元物质用途广泛且极为珍贵", "", "§7▷▷ §b耗电: §e400 J/s", "§7▷▷ §b缓存: §e12800 J" });
/*    */       
/* 20 */       ITEM_COPER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ2MTIwNDk5YjMyNDYzOWNkOWExNzc5OWVhMWRmZmYzNzNlNzlhZmUxZjBkOGI1MzI4Y2Y0Nzg5NmM0Nzc1In19fQ=="), "&d物质复构机", new String[] { "", "&7能够将特定的物质进行复制制造", "&7但需要使用珍贵的元物质", "§7并且极为耗能", "", "§7▷▷ §b耗电: §e1280 J/s", "§7▷▷ §b缓存: §e65535 J" });
/*    */       
/* 22 */       ITEM_CREATOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE3YjNiMTZlNWQwYzRjZmQyMWM0ZWI5MTMzZTk2OWFhZDdjYzczMDNjY2RmMzE3NTEyZTI2YTQ4NzliNTEifX19"), "&d物质制造机", new String[] { "", "&7通过手动解析物质编码", "&7使用元物质制造出想要的新物质", "§7甚至制作出自然界不存在的元素物质", "", "§7▷▷ §b耗电: §e768 J/s", "§7▷▷ §b缓存: §e65535 J" });
/*    */       
/* 24 */       IRIDIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNmNWI4ZGViM2Q3YWZlMGY0ZmZhNjI3N2RlZjk3NjYxOWE2N2NjMTYzMTUzMTk4M2U4NTkwODI2ZWRlNjQ2MCJ9fX0="), "&d铱", new String[] { "", "&7一种稀有的贵重金属", "&7可以用于制作一些高级装备" });
/*    */     }
/* 26 */     catch (Exception e) {
/* 27 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Lists\NarItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */