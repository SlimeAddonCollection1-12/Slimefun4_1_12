/*     */ package me.mrCookieSlime.Slimefun.api.energy;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChargableBlock
/*     */ {
/*  21 */   public static Map<String, Integer> max_charges = new HashMap<>();
/*  22 */   public static Set<String> rechargeable = new HashSet<>();
/*  23 */   public static Set<String> capacitors = new HashSet<>();
/*     */   
/*     */   public static void registerChargableBlock(String id, int capacity, boolean recharge) {
/*  26 */     max_charges.put(id, Integer.valueOf(capacity));
/*  27 */     if (recharge) rechargeable.add(id); 
/*     */   }
/*     */   
/*     */   public static void registerCapacitor(String id, int capacity) {
/*  31 */     max_charges.put(id, Integer.valueOf(capacity));
/*  32 */     rechargeable.add(id);
/*  33 */     capacitors.add(id);
/*     */   }
/*     */   
/*     */   public static boolean isChargable(Block b) {
/*  37 */     return isChargable(b.getLocation());
/*     */   }
/*     */   
/*     */   public static boolean isChargable(Location l) {
/*  41 */     if (!BlockStorage.hasBlockInfo(l)) return false; 
/*  42 */     return max_charges.containsKey(BlockStorage.checkID(l));
/*     */   }
/*     */   
/*     */   public static boolean isRechargable(Block b) {
/*  46 */     if (!BlockStorage.hasBlockInfo(b)) return false; 
/*  47 */     String id = BlockStorage.checkID(b);
/*  48 */     return (max_charges.containsKey(id) && rechargeable.contains(id));
/*     */   }
/*     */   
/*     */   public static boolean isCapacitor(Block b) {
/*  52 */     return isCapacitor(b.getLocation());
/*     */   }
/*     */   
/*     */   public static boolean isCapacitor(Location l) {
/*  56 */     if (!BlockStorage.hasBlockInfo(l)) return false; 
/*  57 */     return capacitors.contains(BlockStorage.checkID(l));
/*     */   }
/*     */   
/*     */   public static int getDefaultCapacity(Block b) {
/*  61 */     return getDefaultCapacity(b.getLocation());
/*     */   }
/*     */   
/*     */   public static int getDefaultCapacity(Location l) {
/*  65 */     String id = BlockStorage.checkID(l);
/*  66 */     return (id == null) ? 0 : ((Integer)max_charges.get(id)).intValue();
/*     */   }
/*     */   
/*     */   public static int getCharge(Block b) {
/*  70 */     return getCharge(b.getLocation());
/*     */   }
/*     */   
/*     */   public static int getCharge(Location l) {
/*  74 */     String charge = BlockStorage.getLocationInfo(l, "energy-charge");
/*  75 */     if (charge != null) return Integer.parseInt(charge);
/*     */     
/*  77 */     BlockStorage.addBlockInfo(l, "energy-charge", "0", false);
/*  78 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCharge(Block b, int charge) {
/*  83 */     setCharge(b.getLocation(), charge);
/*     */   }
/*     */   
/*     */   public static void setCharge(Location l, int charge) {
/*  87 */     if (charge < 0) { charge = 0; }
/*     */     else
/*  89 */     { int capacity = getMaxCharge(l);
/*  90 */       if (charge > capacity) charge = capacity;  }
/*     */     
/*  92 */     if (charge != getCharge(l)) BlockStorage.addBlockInfo(l, "energy-charge", String.valueOf(charge), false); 
/*     */   }
/*     */   
/*     */   public static void setUnsafeCharge(Location l, int charge, boolean updateTexture) {
/*  96 */     if (charge != getCharge(l)) {
/*  97 */       BlockStorage.addBlockInfo(l, "energy-charge", String.valueOf(charge), false);
/*  98 */       if (updateTexture) {
/*     */         try {
/* 100 */           updateTexture(l);
/* 101 */         } catch (Exception e) {
/* 102 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void updateTexture(final Location l) throws Exception {
/* 109 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*     */             try {
/* 114 */               Block b = l.getBlock();
/* 115 */               int charge = ChargableBlock.getCharge(b), capacity = ChargableBlock.getMaxCharge(b);
/* 116 */               if (b.getState() instanceof org.bukkit.block.Skull)
/* 117 */                 if (charge < (int)(capacity * 0.25D)) { CustomSkull.setSkull(b, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="); }
/* 118 */                 else if (charge < (int)(capacity * 0.5D)) { CustomSkull.setSkull(b, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA1MzIzMzk0YTdkOTFiZmIzM2RmMDZkOTJiNjNjYjQxNGVmODBmMDU0ZDA0NzM0ZWEwMTVhMjNjNTM5In19fQ=="); }
/* 119 */                 else if (charge < (int)(capacity * 0.75D)) { CustomSkull.setSkull(b, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU4NDQzMmFmNmYzODIxNjcxMjAyNThkMWVlZThjODdjNmU3NWQ5ZTQ3OWU3YjBkNGM3YjZhZDQ4Y2ZlZWYifX19"); }
/* 120 */                 else { CustomSkull.setSkull(b, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2EyNTY5NDE1YzE0ZTMxYzk4ZWM5OTNhMmY5OWU2ZDY0ODQ2ZGIzNjdhMTNiMTk5OTY1YWQ5OWM0MzhjODZjIn19fQ=="); }
/*     */                  
/* 122 */             } catch (Exception e) {
/* 123 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static String formatEnergy(Block b) {
/* 130 */     return DoubleHandler.getFancyDouble(getCharge(b)) + " J";
/*     */   }
/*     */   
/*     */   public static int addCharge(Block b, int charge) {
/* 134 */     return addCharge(b.getLocation(), charge);
/*     */   }
/*     */   
/*     */   public static int addCharge(Location l, int charge) {
/* 138 */     int energy = getCharge(l);
/* 139 */     int space = getMaxCharge(l) - energy;
/* 140 */     int rest = charge;
/* 141 */     if (space > 0 && charge > 0) {
/* 142 */       if (space > charge) {
/* 143 */         setCharge(l, energy + charge);
/* 144 */         rest = 0;
/*     */       } else {
/*     */         
/* 147 */         rest = charge - space;
/* 148 */         setCharge(l, getMaxCharge(l));
/*     */       } 
/* 150 */       if (capacitors.contains(BlockStorage.checkID(l))) {
/*     */         try {
/* 152 */           updateTexture(l);
/* 153 */         } catch (Exception e) {
/* 154 */           e.printStackTrace();
/*     */         }
/*     */       
/*     */       }
/* 158 */     } else if (charge < 0 && energy >= -charge) {
/* 159 */       setCharge(l, energy + charge);
/* 160 */       if (capacitors.contains(BlockStorage.checkID(l))) {
/*     */         try {
/* 162 */           updateTexture(l);
/* 163 */         } catch (Exception e) {
/* 164 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 168 */     return rest;
/*     */   }
/*     */   
/*     */   public static int getMaxCharge(Block b) {
/* 172 */     return getMaxCharge(b.getLocation());
/*     */   }
/*     */   
/*     */   public static int getMaxCharge(Location l) {
/* 176 */     Config cfg = BlockStorage.getLocationInfo(l);
/* 177 */     if (!cfg.contains("id")) {
/* 178 */       BlockStorage.clearBlockInfo(l);
/* 179 */       return 0;
/*     */     } 
/* 181 */     if (cfg.contains("energy-capacity")) return Integer.parseInt(cfg.getString("energy-capacity"));
/*     */     
/* 183 */     BlockStorage.addBlockInfo(l, "energy-capacity", String.valueOf(getDefaultCapacity(l)), false);
/* 184 */     return getDefaultCapacity(l);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\energy\ChargableBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */