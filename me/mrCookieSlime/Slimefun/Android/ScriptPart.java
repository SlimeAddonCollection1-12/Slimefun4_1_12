/*    */ package me.mrCookieSlime.Slimefun.Android;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ScriptPart
/*    */ {
/* 11 */   START(AndroidType.NONE, "&2启动脚本", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGFlMjk0MjJkYjQwNDdlZmRiOWJhYzJjZGFlNWEwNzE5ZWI3NzJmY2NjODhhNjZkOTEyMzIwYjM0M2MzNDEifX19"),
/* 12 */   REPEAT(AndroidType.NONE, "&9循环脚本", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM4ZGVmNjdhMTI2MjJlYWQxZGVjZDNkODkzNjQyNTdiNTMxODk2ZDg3ZTQ2OTgxMzEzMWNhMjM1YjVjNyJ9fX0="),
/* 13 */   WAIT(AndroidType.NONE, "&e等待 0.5s", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVlMTc0ZjQxZTU5NGU2NGVhMzE0MWMwN2RhZjdhY2YxZmEwNDVjMjMwYjJiMGIwZmIzZGExNjNkYjIyZjQ1NSJ9fX0="),
/*    */ 
/*    */   
/* 16 */   GO_FORWARD(AndroidType.NON_FIGHTER, "&7向前移动", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDliZjZkYjRhZWRhOWQ4ODIyYjlmNzM2NTM4ZThjMThiOWE0ODQ0Zjg0ZWI0NTUwNGFkZmJmZWU4N2ViIn19fQ=="),
/* 17 */   GO_UP(AndroidType.NON_FIGHTER, "&7向上移动", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTA1YTJjYWI4YjY4ZWE1N2UzYWY5OTJhMzZlNDdjOGZmOWFhODdjYzg3NzYyODE5NjZmOGMzY2YzMWEzOCJ9fX0="),
/* 18 */   GO_DOWN(AndroidType.NON_FIGHTER, "&7向下移动", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAxNTg2ZTM5ZjZmZmE2M2I0ZmIzMDFiNjVjYTdkYThhOTJmNzM1M2FhYWI4OWQzODg2NTc5MTI1ZGZiYWY5In19fQ=="),
/*    */ 
/*    */   
/* 21 */   TURN_LEFT(AndroidType.NONE, "&7左转", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE4NWM5N2RiYjgzNTNkZTY1MjY5OGQyNGI2NDMyN2I3OTNhM2YzMmE5OGJlNjdiNzE5ZmJlZGFiMzVlIn19fQ=="),
/* 22 */   TURN_RIGHT(AndroidType.NONE, "&7右转", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFjMGVkZWRkNzExNWZjMWIyM2Q1MWNlOTY2MzU4YjI3MTk1ZGFmMjZlYmI2ZTQ1YTY2YzM0YzY5YzM0MDkxIn19fQ=="),
/*    */ 
/*    */   
/* 25 */   DIG_UP(AndroidType.MINER, "&b向上挖", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU2Y2UwMTFhYzlhN2E3NWIyZmNkNDA4YWQyMWEzYWMxNzIyZjZlMmVlZDg3ODFjYWZkMTI1NTIyODJiODgifX19"),
/* 26 */   DIG_FORWARD(AndroidType.MINER, "&b向前挖", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZlYTIxMzU4Mzg0NjE1MzQzNzJmMmRhNmM4NjJkMjFjZDVmM2QyYzcxMTlmMmJiNjc0YmJkNDI3OTEifX19"),
/* 27 */   DIG_DOWN(AndroidType.MINER, "&b向下挖", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4NjIwMjQxMDhjNzg1YmMwZWY3MTk5ZWM3N2M0MDJkYmJmY2M2MjRlOWY0MWY4M2Q4YWVkOGIzOWZkMTMifX19"),
/*    */   
/* 29 */   MOVE_AND_DIG_UP(AndroidType.MINER, "&b挖掘上方并向上移动", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU2Y2UwMTFhYzlhN2E3NWIyZmNkNDA4YWQyMWEzYWMxNzIyZjZlMmVlZDg3ODFjYWZkMTI1NTIyODJiODgifX19"),
/* 30 */   MOVE_AND_DIG_FORWARD(AndroidType.MINER, "&b向前挖并向前移动", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZlYTIxMzU4Mzg0NjE1MzQzNzJmMmRhNmM4NjJkMjFjZDVmM2QyYzcxMTlmMmJiNjc0YmJkNDI3OTEifX19"),
/* 31 */   MOVE_AND_DIG_DOWN(AndroidType.MINER, "&b向下挖并向下移动", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4NjIwMjQxMDhjNzg1YmMwZWY3MTk5ZWM3N2M0MDJkYmJmY2M2MjRlOWY0MWY4M2Q4YWVkOGIzOWZkMTMifX19"),
/*    */ 
/*    */   
/* 34 */   ATTACK_MOBS_ANIMALS(AndroidType.FIGHTER, "&4攻击&c(敌对生物 & 动物)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdlNmM0MGY2OGI3NzVmMmVmY2Q3YmQ5OTE2YjMyNzg2OWRjZjI3ZTI0Yzg1NWQwYTE4ZTA3YWMwNGZlMSJ9fX0="),
/* 35 */   ATTACK_MOBS(AndroidType.FIGHTER, "&4攻击&c(敌对生物)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdlNmM0MGY2OGI3NzVmMmVmY2Q3YmQ5OTE2YjMyNzg2OWRjZjI3ZTI0Yzg1NWQwYTE4ZTA3YWMwNGZlMSJ9fX0="),
/* 36 */   ATTACK_ANIMALS(AndroidType.FIGHTER, "&4攻击&c(动物)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdlNmM0MGY2OGI3NzVmMmVmY2Q3YmQ5OTE2YjMyNzg2OWRjZjI3ZTI0Yzg1NWQwYTE4ZTA3YWMwNGZlMSJ9fX0="),
/* 37 */   ATTACK_ANIMALS_ADULT(AndroidType.FIGHTER, "&4攻击&c(动物 &7[已成年]&c)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdlNmM0MGY2OGI3NzVmMmVmY2Q3YmQ5OTE2YjMyNzg2OWRjZjI3ZTI0Yzg1NWQwYTE4ZTA3YWMwNGZlMSJ9fX0="),
/*    */ 
/*    */   
/* 40 */   CHOP_TREE(AndroidType.WOODCUTTER, "&c砍伐并补种", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRiYTQ5Mzg0ZGJhN2I3YWNkYjRmNzBlOTM2MWU2ZDU3Y2JiY2JmNzIwY2Y0ZjE2YzJiYjgzZTQ1NTcifX19"),
/*    */ 
/*    */   
/* 43 */   CATCH_FISH(AndroidType.FISHERMAN, "&捕鱼", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQ0ZmRlNTExZjQ0NTQxMDFlNGEyYTcyYmM4NmYxMjk4NWRmY2RhNzZiNjRiYjI0ZGM2M2E5ZmE5ZTNhMyJ9fX0="),
/*    */ 
/*    */   
/* 46 */   FARM_FORWARD(AndroidType.FARMER, "&b收获并补种", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRlOWE1MjJjM2Q5ZTdkODVmM2Q4MmMzNzVkYzM3ZmVjYzg1NmRiZDgwMWViM2JjZWRjMTE2NTE5OGJmIn19fQ=="),
/* 47 */   FARM_DOWN(AndroidType.FARMER, "&b收获并补种 &7(方块下方)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ0Mjk2YjMzM2QyNTMxOWFmM2YzMzA1MTc5N2Y5ZTZkODIxY2QxOWEwMTRmYjcxMzdiZWI4NmE0ZTllOTYifX19"),
/*    */ 
/*    */   
/* 50 */   FARM_EXOTIC_FORWARD(AndroidType.ADVANCED_FARMER, "&b高级收获并补种", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRlOWE1MjJjM2Q5ZTdkODVmM2Q4MmMzNzVkYzM3ZmVjYzg1NmRiZDgwMWViM2JjZWRjMTE2NTE5OGJmIn19fQ=="),
/* 51 */   FARM_EXOTIC_DOWN(AndroidType.ADVANCED_FARMER, "&b高级收获并补种 &7(方块下方)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ0Mjk2YjMzM2QyNTMxOWFmM2YzMzA1MTc5N2Y5ZTZkODIxY2QxOWEwMTRmYjcxMzdiZWI4NmE0ZTllOTYifX19"),
/*    */ 
/*    */   
/* 54 */   INTERFACE_ITEMS(AndroidType.NONE, "&9移动机器人中的物品到面对方向的容器中", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBhNGRiZjY2MjVjNDJiZTU3YThiYTJjMzMwOTU0YTc2YmRmMjI3ODU1NDBlODdhNWM5NjcyNjg1MjM4ZWMifX19"),
/* 55 */   INTERFACE_FUEL(AndroidType.NONE, "&c获取免费方向容器中的燃料", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjQzMmY1MjgyYTUwNzQ1YjkxMmJlMTRkZWRhNTgxYmQ0YTA5Yjk3N2EzYzMyZDdlOTU3ODQ5MWZlZThmYTcifX19");
/*    */   
/*    */   private ItemStack item;
/*    */   
/*    */   private AndroidType type;
/*    */   
/*    */   ScriptPart(AndroidType type, String name, String texture) {
/*    */     try {
/* 63 */       this.type = type;
/* 64 */       this.item = (ItemStack)new CustomItem(CustomSkull.getItem(texture), name);
/* 65 */     } catch (Exception x) {
/* 66 */       x.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public ItemStack toItemStack() {
/* 71 */     return this.item;
/*    */   }
/*    */   
/*    */   public AndroidType getRequiredType() {
/* 75 */     return this.type;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Android\ScriptPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */