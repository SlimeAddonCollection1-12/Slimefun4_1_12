/*     */ package me.mrCookieSlime.Slimefun.api.energy;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.TickerTask;
/*     */ import me.mrCookieSlime.Slimefun.api.network.Network;
/*     */ import me.mrCookieSlime.Slimefun.holograms.EnergyHologram;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class EnergyNet extends Network {
/*     */   private static final int RANGE = 6;
/*     */   
/*     */   public enum NetworkComponent {
/*  23 */     SOURCE,
/*  24 */     DISTRIBUTOR,
/*  25 */     CONSUMER,
/*  26 */     NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  31 */   public static Set<String> machines_input = new HashSet<>();
/*  32 */   public static Set<String> machines_storage = new HashSet<>();
/*  33 */   public static Set<String> machines_output = new HashSet<>();
/*     */   
/*  35 */   public static Map<String, EnergyFlowListener> listeners = new HashMap<>();
/*     */   
/*     */   public static NetworkComponent getComponent(Block b) {
/*  38 */     return getComponent(b.getLocation());
/*     */   }
/*     */   
/*     */   public static NetworkComponent getComponent(String id) {
/*  42 */     if (machines_input.contains(id)) return NetworkComponent.SOURCE; 
/*  43 */     if (machines_storage.contains(id)) return NetworkComponent.DISTRIBUTOR; 
/*  44 */     if (machines_output.contains(id)) return NetworkComponent.CONSUMER; 
/*  45 */     return NetworkComponent.NONE;
/*     */   }
/*     */   
/*     */   public static NetworkComponent getComponent(Location l) {
/*  49 */     if (!BlockStorage.hasBlockInfo(l)) return NetworkComponent.NONE; 
/*  50 */     String id = BlockStorage.checkID(l);
/*  51 */     if (machines_input.contains(id)) return NetworkComponent.SOURCE; 
/*  52 */     if (machines_storage.contains(id)) return NetworkComponent.DISTRIBUTOR; 
/*  53 */     if (machines_output.contains(id)) return NetworkComponent.CONSUMER; 
/*  54 */     return NetworkComponent.NONE;
/*     */   }
/*     */   
/*     */   public static void registerComponent(String id, NetworkComponent component) {
/*  58 */     switch (component) {
/*     */       case CONSUMER:
/*  60 */         machines_output.add(id);
/*     */         break;
/*     */       case DISTRIBUTOR:
/*  63 */         machines_storage.add(id);
/*     */         break;
/*     */       case SOURCE:
/*  66 */         machines_input.add(id);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnergyNet getNetworkFromLocation(Location l) {
/*  74 */     return (EnergyNet)getNetworkFromLocation(l, EnergyNet.class);
/*     */   }
/*     */   
/*     */   public static EnergyNet getNetworkFromLocationOrCreate(Location l) {
/*  78 */     EnergyNet energy_network = getNetworkFromLocation(l);
/*  79 */     if (energy_network == null) {
/*  80 */       energy_network = new EnergyNet(l);
/*  81 */       registerNetwork(energy_network);
/*     */     } 
/*  83 */     return energy_network;
/*     */   }
/*     */   
/*  86 */   private Set<Location> input = new HashSet<>();
/*  87 */   private Set<Location> storage = new HashSet<>();
/*  88 */   private Set<Location> output = new HashSet<>();
/*     */   
/*     */   protected EnergyNet(Location l) {
/*  91 */     super(l);
/*     */   }
/*     */   
/*     */   public int getRange() {
/*  95 */     return 6;
/*     */   }
/*     */   
/*     */   public Network.Component classifyLocation(Location l) {
/*  99 */     if (this.regulator.equals(l)) return Network.Component.REGULATOR; 
/* 100 */     switch (getComponent(l)) {
/*     */       case DISTRIBUTOR:
/* 102 */         return Network.Component.CONNECTOR;
/*     */       case CONSUMER:
/*     */       case SOURCE:
/* 105 */         return Network.Component.TERMINUS;
/*     */     } 
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void locationClassificationChange(Location l, Network.Component from, Network.Component to) {
/* 112 */     if (from == Network.Component.TERMINUS) {
/* 113 */       this.input.remove(l);
/* 114 */       this.output.remove(l);
/*     */     } 
/* 116 */     switch (getComponent(l)) {
/*     */       case DISTRIBUTOR:
/* 118 */         if (ChargableBlock.isCapacitor(l)) this.storage.add(l); 
/*     */         break;
/*     */       case CONSUMER:
/* 121 */         this.output.add(l);
/*     */         break;
/*     */       case SOURCE:
/* 124 */         this.input.add(l);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(Block b) {
/* 132 */     if (!this.regulator.equals(b.getLocation())) {
/* 133 */       EnergyHologram.update(b, "&4错误，多个能量核心相连！");
/*     */       return;
/*     */     } 
/* 136 */     tick();
/* 137 */     double supply = 0.0D;
/* 138 */     double demand = 0.0D;
/*     */     
/* 140 */     if (this.connectorNodes.isEmpty() && this.terminusNodes.isEmpty()) {
/* 141 */       EnergyHologram.update(b, "&4错误，未构成能量网络！");
/*     */     } else {
/*     */       
/* 144 */       for (Location source : this.input) {
/* 145 */         long timestamp = System.currentTimeMillis();
/* 146 */         SlimefunItem item = BlockStorage.check(source);
/* 147 */         double energy = item.getEnergyTicker().generateEnergy(source, item, BlockStorage.getLocationInfo(source));
/*     */         
/* 149 */         if (item.getEnergyTicker().explode(source)) {
/* 150 */           BlockStorage.clearBlockInfo(source);
/* 151 */           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */               {
/*     */                 public void run()
/*     */                 {
/* 155 */                   source.getBlock().setType(Material.LAVA);
/* 156 */                   source.getWorld().createExplosion(source, 0.0F, false);
/*     */                 }
/*     */               });
/*     */         } else {
/*     */           
/* 161 */           supply += energy;
/*     */         } 
/* 163 */         TickerTask.block_timings.put(source, Long.valueOf(System.currentTimeMillis() - timestamp));
/*     */       } 
/*     */       
/* 166 */       for (Location battery : this.storage) {
/* 167 */         supply += ChargableBlock.getCharge(battery);
/*     */       }
/*     */       
/* 170 */       int available = (int)DoubleHandler.fixDouble(supply);
/*     */       
/* 172 */       for (Location destination : this.output) {
/* 173 */         int capacity = ChargableBlock.getMaxCharge(destination);
/* 174 */         int charge = ChargableBlock.getCharge(destination);
/* 175 */         if (charge < capacity) {
/* 176 */           int rest = capacity - charge;
/* 177 */           demand += rest;
/* 178 */           if (available > 0) {
/* 179 */             if (available > rest) {
/* 180 */               ChargableBlock.setUnsafeCharge(destination, capacity, false);
/* 181 */               available -= rest;
/*     */               continue;
/*     */             } 
/* 184 */             ChargableBlock.setUnsafeCharge(destination, charge + available, false);
/* 185 */             available = 0;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 191 */       for (Location battery : this.storage) {
/* 192 */         if (available > 0) {
/* 193 */           int capacity = ChargableBlock.getMaxCharge(battery);
/*     */           
/* 195 */           if (available > capacity) {
/* 196 */             ChargableBlock.setUnsafeCharge(battery, capacity, true);
/* 197 */             available -= capacity;
/*     */             continue;
/*     */           } 
/* 200 */           ChargableBlock.setUnsafeCharge(battery, available, true);
/* 201 */           available = 0;
/*     */           continue;
/*     */         } 
/* 204 */         ChargableBlock.setUnsafeCharge(battery, 0, true);
/*     */       } 
/*     */       
/* 207 */       for (Location source : this.input) {
/* 208 */         if (ChargableBlock.isChargable(source)) {
/* 209 */           if (available > 0) {
/* 210 */             int capacity = ChargableBlock.getMaxCharge(source);
/*     */             
/* 212 */             if (available > capacity) {
/* 213 */               ChargableBlock.setUnsafeCharge(source, capacity, false);
/* 214 */               available -= capacity;
/*     */               continue;
/*     */             } 
/* 217 */             ChargableBlock.setUnsafeCharge(source, available, false);
/* 218 */             available = 0;
/*     */             continue;
/*     */           } 
/* 221 */           ChargableBlock.setUnsafeCharge(source, 0, false);
/*     */         } 
/*     */       } 
/*     */       
/* 225 */       EnergyHologram.update(b, supply, demand);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\energy\EnergyNet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */