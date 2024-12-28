/*     */ package me.mrCookieSlime.Slimefun.api.network;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public abstract class Network
/*     */ {
/*  16 */   private static List<Network> NETWORK_LIST = new ArrayList<>(); protected Location regulator;
/*     */   public static <T extends Network> T getNetworkFromLocation(Location l, Class<T> type) {
/*  18 */     for (Network n : NETWORK_LIST) {
/*  19 */       if (type.isInstance(n) && n.connectsTo(l)) {
/*  20 */         return type.cast(n);
/*     */       }
/*     */     } 
/*  23 */     return null;
/*     */   }
/*     */   
/*     */   public static <T extends Network> List<T> getNetworksFromLocation(Location l, Class<T> type) {
/*  27 */     List<T> ret = new ArrayList<>();
/*  28 */     for (Network n : NETWORK_LIST) {
/*  29 */       if (type.isInstance(n) && n.connectsTo(l)) {
/*  30 */         ret.add(type.cast(n));
/*     */       }
/*     */     } 
/*  33 */     return ret;
/*     */   }
/*     */   
/*     */   public static void registerNetwork(Network n) {
/*  37 */     NETWORK_LIST.add(n);
/*     */   }
/*     */   
/*     */   public static void unregisterNetwork(Network n) {
/*  41 */     NETWORK_LIST.remove(n);
/*     */   }
/*     */   
/*     */   public static void handleAllNetworkLocationUpdate(Location l) {
/*  45 */     for (Network n : getNetworksFromLocation(l, Network.class))
/*  46 */       n.handleLocationUpdate(l); 
/*     */   }
/*     */   
/*     */   public enum Component
/*     */   {
/*  51 */     CONNECTOR,
/*  52 */     REGULATOR,
/*  53 */     TERMINUS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private Queue<Location> nodeQueue = new ArrayDeque<>();
/*     */   
/*  64 */   protected Set<Location> connectedLocations = new HashSet<>();
/*  65 */   protected Set<Location> regulatorNodes = new HashSet<>();
/*  66 */   protected Set<Location> connectorNodes = new HashSet<>();
/*  67 */   protected Set<Location> terminusNodes = new HashSet<>();
/*     */   public abstract int getRange();
/*     */   protected Network(Location regulator) {
/*  70 */     this.regulator = regulator;
/*  71 */     this.connectedLocations.add(regulator);
/*  72 */     this.nodeQueue.add(regulator.clone());
/*     */   } public abstract Component classifyLocation(Location paramLocation);
/*     */   public abstract void locationClassificationChange(Location paramLocation, Component paramComponent1, Component paramComponent2);
/*     */   protected void addLocationToNetwork(Location l) {
/*  76 */     if (this.connectedLocations.contains(l)) {
/*     */       return;
/*     */     }
/*  79 */     this.connectedLocations.add(l.clone());
/*  80 */     handleLocationUpdate(l);
/*     */   }
/*     */   
/*     */   public void handleLocationUpdate(Location l) {
/*  84 */     if (this.regulator.equals(l)) {
/*  85 */       unregisterNetwork(this);
/*     */       return;
/*     */     } 
/*  88 */     this.nodeQueue.add(l.clone());
/*     */   }
/*     */   
/*     */   public boolean connectsTo(Location l) {
/*  92 */     return this.connectedLocations.contains(l);
/*     */   }
/*     */   
/*     */   private Component getCurrentClassification(Location l) {
/*  96 */     if (this.regulatorNodes.contains(l))
/*  97 */       return Component.REGULATOR; 
/*  98 */     if (this.connectorNodes.contains(l))
/*  99 */       return Component.CONNECTOR; 
/* 100 */     if (this.terminusNodes.contains(l)) {
/* 101 */       return Component.TERMINUS;
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   private void discoverStep() {
/* 107 */     int steps = 0;
/* 108 */     while (this.nodeQueue.peek() != null) {
/* 109 */       Location l = this.nodeQueue.poll();
/* 110 */       Component currentAssignment = getCurrentClassification(l);
/* 111 */       Component classification = classifyLocation(l);
/* 112 */       if (classification != currentAssignment) {
/* 113 */         if (currentAssignment == Component.REGULATOR || currentAssignment == Component.CONNECTOR) {
/*     */           
/* 115 */           unregisterNetwork(this); return;
/*     */         } 
/* 117 */         if (currentAssignment == Component.TERMINUS) {
/* 118 */           this.terminusNodes.remove(l);
/*     */         }
/* 120 */         if (classification == Component.REGULATOR) {
/* 121 */           this.regulatorNodes.add(l);
/* 122 */           discoverNeighbors(l);
/* 123 */         } else if (classification == Component.CONNECTOR) {
/* 124 */           this.connectorNodes.add(l);
/* 125 */           discoverNeighbors(l);
/* 126 */         } else if (classification == Component.TERMINUS) {
/* 127 */           this.terminusNodes.add(l);
/*     */         } 
/* 129 */         locationClassificationChange(l, currentAssignment, classification);
/*     */       } 
/* 131 */       steps++;
/*     */ 
/*     */       
/* 134 */       if (steps == 500)
/*     */         break; 
/*     */     } 
/*     */   }
/*     */   private void discoverNeighbors(Location l, int xDiff, int yDiff, int zDiff) {
/* 139 */     for (int i = getRange() + 1; i > 0; i--) {
/* 140 */       Location new_location = l.clone().add((i * xDiff), (i * yDiff), (i * zDiff));
/* 141 */       addLocationToNetwork(new_location);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void discoverNeighbors(Location l) {
/* 146 */     discoverNeighbors(l, 1, 0, 0);
/* 147 */     discoverNeighbors(l, -1, 0, 0);
/* 148 */     discoverNeighbors(l, 0, 1, 0);
/* 149 */     discoverNeighbors(l, 0, -1, 0);
/* 150 */     discoverNeighbors(l, 0, 0, 1);
/* 151 */     discoverNeighbors(l, 0, 0, -1);
/*     */   }
/*     */   
/*     */   public void display() {
/* 155 */     SlimefunStartup.instance.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)SlimefunStartup.instance, new Runnable()
/*     */         {
/*     */           public void run() {
/* 158 */             for (Location l : Network.this.connectedLocations) {
/*     */               try {
/* 160 */                 ParticleEffect.REDSTONE.display(l.clone().add(0.5D, 0.5D, 0.5D), 0.0F, 0.0F, 0.0F, 0.0F, 1);
/* 161 */               } catch (Exception exception) {}
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 170 */     discoverStep();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\network\Network.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */