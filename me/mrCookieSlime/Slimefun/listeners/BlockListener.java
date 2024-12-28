/*     */ package me.mrCookieSlime.Slimefun.listeners;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Block.BlockAdjacents;
/*     */ import me.mrCookieSlime.Slimefun.Events.MultiBlockInteractEvent;
/*     */ import me.mrCookieSlime.Slimefun.Objects.MultiBlock;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.MultiBlockInteractionHandler;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.FallingBlock;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*     */ import org.bukkit.event.block.BlockPistonRetractEvent;
/*     */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class BlockListener
/*     */   implements Listener {
/*     */   public BlockListener(SlimefunStartup plugin) {
/*  34 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockFall(EntityChangeBlockEvent event) {
/*  40 */     if (event.getEntity() instanceof FallingBlock && 
/*  41 */       BlockStorage.hasBlockInfo(event.getBlock())) {
/*  42 */       event.setCancelled(true);
/*  43 */       FallingBlock fb = (FallingBlock)event.getEntity();
/*  44 */       if (fb.getDropItem()) {
/*  45 */         fb.getWorld().dropItemNaturally(fb.getLocation(), new ItemStack(fb.getMaterial(), 1, (short)fb.getBlockData()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onPistonExtend(BlockPistonExtendEvent e) {
/*  53 */     for (Block b : e.getBlocks()) {
/*  54 */       if (BlockStorage.hasBlockInfo(b)) {
/*  55 */         e.setCancelled(true);
/*     */         return;
/*     */       } 
/*  58 */       if (b.getRelative(e.getDirection()) == null && BlockStorage.hasBlockInfo(b.getRelative(e.getDirection()))) {
/*  59 */         e.setCancelled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPistonRetract(BlockPistonRetractEvent e) {
/*  67 */     if (e.isSticky()) {
/*  68 */       for (Block b : e.getBlocks()) {
/*  69 */         if (BlockStorage.hasBlockInfo(b)) {
/*  70 */           e.setCancelled(true);
/*     */           return;
/*     */         } 
/*  73 */         if (b.getRelative(e.getDirection()) == null && BlockStorage.hasBlockInfo(b.getRelative(e.getDirection()))) {
/*  74 */           e.setCancelled(true);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onRightClick(PlayerInteractEvent e) {
/*  83 */     if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*  84 */       if (!e.getHand().equals(EquipmentSlot.HAND))
/*  85 */         return;  Player p = e.getPlayer();
/*  86 */       Block b = e.getClickedBlock();
/*  87 */       List<MultiBlock> multiblocks = new ArrayList<>();
/*  88 */       for (MultiBlock mb : MultiBlock.list()) {
/*  89 */         if (mb.getTriggerBlock() == b.getType()) {
/*  90 */           Material[] blocks = mb.getBuild();
/*     */           
/*  92 */           if (mb.getTriggerBlock() == blocks[1]) {
/*     */             
/*  94 */             if (BlockAdjacents.hasMaterialOnSide(b, blocks[0]) && 
/*  95 */               BlockAdjacents.hasMaterialOnSide(b, blocks[2]) && 
/*  96 */               BlockAdjacents.isMaterial(b.getRelative(BlockFace.DOWN), blocks[4]) && 
/*  97 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.DOWN), blocks[3]) && 
/*  98 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.DOWN), blocks[5]) && 
/*  99 */               BlockAdjacents.isMaterial(b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN), blocks[7]) && 
/* 100 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN), blocks[6]) && 
/* 101 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN), blocks[8])) {
/*     */               
/* 103 */               if ((blocks[0] != null && blocks[0] == blocks[2] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, 0, 0), blocks[0])) || (
/* 104 */                 blocks[3] != null && blocks[3] == blocks[5] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, -1, 0), blocks[5])) || (
/* 105 */                 blocks[6] != null && blocks[6] == blocks[8] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, -2, 0), blocks[8])))
/* 106 */                 continue;  multiblocks.add(mb);
/*     */             }  continue;
/*     */           } 
/* 109 */           if (mb.getTriggerBlock() == blocks[4]) {
/*     */             
/* 111 */             if (BlockAdjacents.hasMaterialOnSide(b, blocks[3]) && 
/* 112 */               BlockAdjacents.hasMaterialOnSide(b, blocks[5]) && 
/* 113 */               BlockAdjacents.isMaterial(b.getRelative(BlockFace.DOWN), blocks[7]) && 
/* 114 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.DOWN), blocks[6]) && 
/* 115 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.DOWN), blocks[8]) && 
/* 116 */               BlockAdjacents.isMaterial(b.getRelative(BlockFace.UP), blocks[1]) && 
/* 117 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.UP), blocks[0]) && 
/* 118 */               BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.UP), blocks[2])) {
/*     */               
/* 120 */               if ((blocks[0] != null && blocks[0] == blocks[2] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, 1, 0), blocks[0])) || (
/* 121 */                 blocks[3] != null && blocks[3] == blocks[5] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, 0, 0), blocks[5])) || (
/* 122 */                 blocks[6] != null && blocks[6] == blocks[8] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, -1, 0), blocks[8])))
/* 123 */                 continue;  multiblocks.add(mb);
/*     */             }  continue;
/*     */           } 
/* 126 */           if (mb.getTriggerBlock() == blocks[7] && 
/*     */             
/* 128 */             BlockAdjacents.hasMaterialOnSide(b, blocks[6]) && 
/* 129 */             BlockAdjacents.hasMaterialOnSide(b, blocks[8]) && 
/* 130 */             BlockAdjacents.isMaterial(b.getRelative(BlockFace.UP).getRelative(BlockFace.UP), blocks[1]) && 
/* 131 */             BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.UP).getRelative(BlockFace.UP), blocks[0]) && 
/* 132 */             BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.UP).getRelative(BlockFace.UP), blocks[2]) && 
/* 133 */             BlockAdjacents.isMaterial(b.getRelative(BlockFace.UP), blocks[4]) && 
/* 134 */             BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.UP), blocks[3]) && 
/* 135 */             BlockAdjacents.hasMaterialOnSide(b.getRelative(BlockFace.UP), blocks[5])) {
/*     */             
/* 137 */             if ((blocks[0] != null && blocks[0] == blocks[2] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, 2, 0), blocks[0])) || (
/* 138 */               blocks[3] != null && blocks[3] == blocks[5] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, 1, 0), blocks[5])) || (
/* 139 */               blocks[6] != null && blocks[6] == blocks[8] && !BlockAdjacents.hasMaterialOnBothSides(b.getRelative(0, 0, 0), blocks[8])))
/* 140 */               continue;  multiblocks.add(mb);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 146 */       if (!multiblocks.isEmpty()) {
/* 147 */         e.setCancelled(true);
/*     */         
/* 149 */         for (ItemHandler handler : SlimefunItem.getHandlers("MultiBlockInteractionHandler")) {
/* 150 */           if (((MultiBlockInteractionHandler)handler).onInteract(p, multiblocks.get(multiblocks.size() - 1), b))
/*     */             break; 
/*     */         } 
/* 153 */         MultiBlockInteractEvent event = new MultiBlockInteractEvent(p, multiblocks.get(multiblocks.size() - 1), b);
/* 154 */         Bukkit.getPluginManager().callEvent((Event)event);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\listeners\BlockListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */