/*     */ package me.mrCookieSlime.Slimefun.api.item_transport;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CargoManager
/*     */ {
/*     */   public static ItemStack withdraw(Block node, BlockStorage storage, Block target, ItemStack template) {
/*  22 */     if (storage.hasUniversalInventory(target)) {
/*  23 */       UniversalBlockMenu menu = storage.getUniversalInventory(target);
/*  24 */       for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/*  25 */         ItemStack is = menu.getItemInSlot(slot);
/*  26 */         if (SlimefunManager.isItemSimiliar(is, template, true, SlimefunManager.DataType.ALWAYS) && matchesFilter(node, is, -1)) {
/*  27 */           if (is.getAmount() > template.getAmount()) {
/*  28 */             menu.replaceExistingItem(slot, (ItemStack)new CustomItem(is, is.getAmount() - template.getAmount()));
/*  29 */             return template;
/*     */           } 
/*     */           
/*  32 */           menu.replaceExistingItem(slot, null);
/*  33 */           return is.clone();
/*     */         }
/*     */       
/*     */       }
/*     */     
/*  38 */     } else if (storage.hasInventory(target.getLocation())) {
/*  39 */       BlockMenu menu = BlockStorage.getInventory(target.getLocation());
/*  40 */       for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/*  41 */         ItemStack is = menu.getItemInSlot(slot);
/*  42 */         if (SlimefunManager.isItemSimiliar(is, template, true, SlimefunManager.DataType.ALWAYS) && matchesFilter(node, is, -1)) {
/*  43 */           if (is.getAmount() > template.getAmount()) {
/*  44 */             menu.replaceExistingItem(slot, (ItemStack)new CustomItem(is, is.getAmount() - template.getAmount()));
/*  45 */             return template;
/*     */           } 
/*     */           
/*  48 */           menu.replaceExistingItem(slot, null);
/*  49 */           return is.clone();
/*     */         }
/*     */       
/*     */       }
/*     */     
/*  54 */     } else if (target.getState() instanceof InventoryHolder) {
/*  55 */       Inventory inv = ((InventoryHolder)target.getState()).getInventory();
/*  56 */       for (int slot = 0; slot < (inv.getContents()).length; slot++) {
/*  57 */         ItemStack is = inv.getContents()[slot];
/*  58 */         if (SlimefunManager.isItemSimiliar(is, template, true, SlimefunManager.DataType.ALWAYS) && matchesFilter(node, is, -1)) {
/*  59 */           if (is.getAmount() > template.getAmount()) {
/*  60 */             inv.setItem(slot, ChestManipulator.trigger(target, slot, is, (ItemStack)new CustomItem(is, is.getAmount() - template.getAmount())));
/*  61 */             return template;
/*     */           } 
/*     */           
/*  64 */           inv.setItem(slot, ChestManipulator.trigger(target, slot, is, (ItemStack)new CustomItem(is, is.getAmount() - template.getAmount())));
/*  65 */           return is.clone();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     return null;
/*     */   }
/*     */   
/*     */   public static ItemSlot withdraw(Block node, BlockStorage storage, Block target, int index) {
/*  74 */     if (storage.hasUniversalInventory(target)) {
/*  75 */       UniversalBlockMenu menu = storage.getUniversalInventory(target);
/*  76 */       for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/*  77 */         ItemStack is = menu.getItemInSlot(slot);
/*  78 */         if (matchesFilter(node, is, index)) {
/*  79 */           menu.replaceExistingItem(slot, null);
/*  80 */           return new ItemSlot(is.clone(), slot);
/*     */         }
/*     */       
/*     */       } 
/*  84 */     } else if (storage.hasInventory(target.getLocation())) {
/*  85 */       BlockMenu menu = BlockStorage.getInventory(target.getLocation());
/*  86 */       for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
/*  87 */         ItemStack is = menu.getItemInSlot(slot);
/*  88 */         if (matchesFilter(node, is, index)) {
/*  89 */           menu.replaceExistingItem(slot, null);
/*  90 */           return new ItemSlot(is.clone(), slot);
/*     */         }
/*     */       
/*     */       } 
/*  94 */     } else if (target.getState() instanceof InventoryHolder) {
/*  95 */       Inventory inv = ((InventoryHolder)target.getState()).getInventory();
/*  96 */       for (int slot = 0; slot < (inv.getContents()).length; slot++) {
/*  97 */         ItemStack is = inv.getContents()[slot];
/*  98 */         if (matchesFilter(node, is, index)) {
/*  99 */           inv.setItem(slot, ChestManipulator.trigger(target, slot, is, null));
/* 100 */           return new ItemSlot(is.clone(), slot);
/*     */         } 
/*     */       } 
/*     */     } 
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   public static ItemStack insert(Block node, BlockStorage storage, Block target, ItemStack stack, int index) {
/* 108 */     if (!matchesFilter(node, stack, index)) return stack; 
/* 109 */     if (storage.hasUniversalInventory(target)) {
/* 110 */       UniversalBlockMenu menu = storage.getUniversalInventory(target);
/* 111 */       for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.INSERT, stack)) {
/* 112 */         ItemStack is = (menu.getItemInSlot(slot) == null) ? null : menu.getItemInSlot(slot).clone();
/* 113 */         if (is == null) {
/* 114 */           menu.replaceExistingItem(slot, stack.clone());
/* 115 */           return null;
/*     */         } 
/* 117 */         if (SlimefunManager.isItemSimiliar((ItemStack)new CustomItem(is, 1), (ItemStack)new CustomItem(stack, 1), true, SlimefunManager.DataType.ALWAYS) && is.getAmount() < is.getType().getMaxStackSize()) {
/* 118 */           int amount = is.getAmount() + stack.getAmount();
/*     */           
/* 120 */           if (amount > is.getType().getMaxStackSize()) {
/* 121 */             is.setAmount(is.getType().getMaxStackSize());
/* 122 */             stack.setAmount(amount - is.getType().getMaxStackSize());
/*     */           } else {
/*     */             
/* 125 */             is.setAmount(amount);
/* 126 */             stack = null;
/*     */           } 
/*     */           
/* 129 */           menu.replaceExistingItem(slot, is);
/* 130 */           return stack;
/*     */         }
/*     */       
/*     */       } 
/* 134 */     } else if (storage.hasInventory(target.getLocation())) {
/* 135 */       BlockMenu menu = BlockStorage.getInventory(target.getLocation());
/* 136 */       for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.INSERT, stack)) {
/* 137 */         ItemStack is = (menu.getItemInSlot(slot) == null) ? null : menu.getItemInSlot(slot).clone();
/* 138 */         if (is == null) {
/* 139 */           menu.replaceExistingItem(slot, stack.clone());
/* 140 */           return null;
/*     */         } 
/* 142 */         if (SlimefunManager.isItemSimiliar((ItemStack)new CustomItem(is, 1), (ItemStack)new CustomItem(stack, 1), true, SlimefunManager.DataType.ALWAYS) && is.getAmount() < is.getType().getMaxStackSize()) {
/* 143 */           int amount = is.getAmount() + stack.getAmount();
/*     */           
/* 145 */           if (amount > is.getType().getMaxStackSize()) {
/* 146 */             is.setAmount(is.getType().getMaxStackSize());
/* 147 */             stack.setAmount(amount - is.getType().getMaxStackSize());
/*     */           } else {
/*     */             
/* 150 */             is.setAmount(amount);
/* 151 */             stack = null;
/*     */           } 
/*     */           
/* 154 */           menu.replaceExistingItem(slot, is);
/* 155 */           return stack;
/*     */         }
/*     */       
/*     */       } 
/* 159 */     } else if (target.getState() instanceof InventoryHolder) {
/* 160 */       Inventory inv = ((InventoryHolder)target.getState()).getInventory();
/*     */       
/* 162 */       for (int slot = 0; slot < (inv.getContents()).length; slot++) {
/* 163 */         ItemStack is = inv.getContents()[slot];
/* 164 */         if (is == null) {
/* 165 */           inv.setItem(slot, ChestManipulator.trigger(target, slot, null, stack.clone()));
/* 166 */           return null;
/*     */         } 
/* 168 */         if (SlimefunManager.isItemSimiliar((ItemStack)new CustomItem(is, 1), (ItemStack)new CustomItem(stack, 1), true, SlimefunManager.DataType.ALWAYS) && is.getAmount() < is.getType().getMaxStackSize()) {
/* 169 */           ItemStack prev = is.clone();
/* 170 */           int amount = is.getAmount() + stack.getAmount();
/*     */           
/* 172 */           if (amount > is.getType().getMaxStackSize()) {
/* 173 */             is.setAmount(is.getType().getMaxStackSize());
/* 174 */             stack.setAmount(amount - is.getType().getMaxStackSize());
/*     */           } else {
/*     */             
/* 177 */             is.setAmount(amount);
/* 178 */             stack = null;
/*     */           } 
/*     */           
/* 181 */           inv.setItem(slot, ChestManipulator.trigger(target, slot, prev, is));
/* 182 */           return stack;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     return stack;
/*     */   }
/*     */   
/* 190 */   private static int[] slots = new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
/*     */   
/*     */   public static boolean matchesFilter(Block block, ItemStack item, int index) {
/* 193 */     if (item == null) return false;
/*     */     
/* 195 */     String id = BlockStorage.checkID(block);
/* 196 */     if (id.equals("CARGO_NODE_OUTPUT")) return true;
/*     */     
/* 198 */     Config blockInfo = BlockStorage.getLocationInfo(block.getLocation());
/*     */     
/* 200 */     BlockMenu menu = BlockStorage.getInventory(block.getLocation());
/* 201 */     boolean lore = blockInfo.getString("filter-lore").equals("true");
/* 202 */     boolean data = blockInfo.getString("filter-durability").equals("true");
/*     */     
/* 204 */     if (blockInfo.getString("filter-type").equals("whitelist")) {
/* 205 */       List<ItemStack> items = new ArrayList<>();
/* 206 */       for (int slot : slots) {
/* 207 */         ItemStack template = menu.getItemInSlot(slot);
/* 208 */         if (template != null) items.add(new CustomItem(template, 1));
/*     */       
/*     */       } 
/* 211 */       if (items.isEmpty()) {
/* 212 */         return false;
/*     */       }
/*     */       
/* 215 */       if (index >= 0) {
/* 216 */         index++;
/* 217 */         if (index > items.size() - 1) index = 0;
/*     */         
/* 219 */         BlockStorage.addBlockInfo(block, "index", String.valueOf(index));
/*     */         
/* 221 */         return SlimefunManager.isItemSimiliar(item, items.get(index), lore, data ? SlimefunManager.DataType.ALWAYS : SlimefunManager.DataType.NEVER);
/*     */       } 
/*     */       
/* 224 */       for (ItemStack stack : items) {
/* 225 */         if (SlimefunManager.isItemSimiliar(item, stack, lore, data ? SlimefunManager.DataType.ALWAYS : SlimefunManager.DataType.NEVER)) return true; 
/*     */       } 
/* 227 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 231 */     for (int slot : slots) {
/* 232 */       if (menu.getItemInSlot(slot) != null && SlimefunManager.isItemSimiliar(item, (ItemStack)new CustomItem(menu.getItemInSlot(slot), 1), lore, data ? SlimefunManager.DataType.ALWAYS : SlimefunManager.DataType.NEVER)) {
/* 233 */         return false;
/*     */       }
/*     */     } 
/* 236 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\item_transport\CargoManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */