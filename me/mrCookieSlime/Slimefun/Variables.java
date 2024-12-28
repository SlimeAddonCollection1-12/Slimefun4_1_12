/*    */ package me.mrCookieSlime.Slimefun;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class Variables
/*    */ {
/* 16 */   public static Map<UUID, Boolean> jump = new HashMap<>();
/* 17 */   public static Map<UUID, Boolean> damage = new HashMap<>();
/* 18 */   public static Map<UUID, Entity[]> remove = (Map)new HashMap<>();
/* 19 */   public static Map<UUID, Integer> mode = new HashMap<>();
/*    */   
/* 21 */   public static Map<UUID, Integer> enchanting = new HashMap<>();
/* 22 */   public static Map<UUID, ItemStack> backpack = new HashMap<>();
/* 23 */   public static HashSet<Location> altarinuse = new HashSet<>();
/*    */   
/* 25 */   public static Map<UUID, List<ItemStack>> soulbound = new HashMap<>();
/* 26 */   public static List<UUID> blocks = new ArrayList<>();
/* 27 */   public static List<UUID> cancelPlace = new ArrayList<>();
/* 28 */   public static Map<UUID, ItemStack> arrows = new HashMap<>();
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Variables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */