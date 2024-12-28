/*     */ package me.mrCookieSlime.Slimefun.Setup;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.Categories;
/*     */ import me.mrCookieSlime.Slimefun.Lists.NarItems;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.narMachines.CreatorMachine;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.narMachines.UUMachine;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class NarItemSetup {
/*     */   public static void setupItems() {
/*  17 */     (new UUMachine(Categories.QUANTUM_MACHINES, NarItems.UU_MACHINE, "UU_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.PLASTIC_SHEET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.PLASTIC_SHEET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS })
/*     */       {
/*     */ 
/*     */         
/*     */         public int getUUFull()
/*     */         {
/*  23 */           return 100000;
/*     */         }
/*     */ 
/*     */         
/*     */         public ItemStack[] getOutput() {
/*  28 */           return new ItemStack[] { NarItems.UU };
/*     */         }
/*     */ 
/*     */         
/*     */         public String getInventoryTitle() {
/*  33 */           return "§d元物质分离机";
/*     */         }
/*     */ 
/*     */         
/*     */         public ItemStack getProgressBar() {
/*  38 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*     */         }
/*     */ 
/*     */         
/*     */         public void registerDefaultRecipes() {
/*  43 */           registerRecipe(5, new ItemStack[] { SlimefunItems.STONE_CHUNK }, 210);
/*  44 */           registerRecipe(5, new ItemStack[] { new ItemStack(Material.BONE) }, 200);
/*  45 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER2 }, 250);
/*  46 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER3 }, 250);
/*  47 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER4 }, 250);
/*  48 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER5 }, 250);
/*  49 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER6 }, 250);
/*  50 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER7 }, 250);
/*  51 */           registerRecipe(4, new ItemStack[] { SlimefunItems.FERTILIZER8 }, 250);
/*  52 */           registerRecipe(3, new ItemStack[] { new ItemStack(Material.DIAMOND) }, 500);
/*  53 */           registerRecipe(3, new ItemStack[] { new ItemStack(Material.EMERALD) }, 500);
/*     */         }
/*     */ 
/*     */         
/*     */         public int getEnergyConsumption() {
/*  58 */           return 400;
/*     */         }
/*     */ 
/*     */         
/*     */         public int getLevel() {
/*  63 */           return 1;
/*     */         }
/*     */ 
/*     */         
/*     */         public String getMachineIdentifier() {
/*  68 */           return "UU_MACHINE";
/*     */         }
/*  70 */       }).registerChargeableBlock(true, 12800);
/*     */     
/*  72 */     (new SlimefunItem(Categories.MISC, NarItems.UU, "UU", new RecipeType(NarItems.UU_MACHINE), new ItemStack[] { null, null, null, null, (ItemStack)new CustomItem(Material.GHAST_TEAR, "§e通过元物质分离机收集", 0), null, null, null, null
/*     */         
/*  74 */         })).register(true);
/*     */     
/*  76 */     (new SlimefunItem(Categories.RESOURCES, NarItems.IRIDIUM, "IRIDIUM", new RecipeType(NarItems.ITEM_CREATOR), new ItemStack[] { null, null, null, null, (ItemStack)new CustomItem(NarItems.ITEM_CREATOR, "&d物质制造机", new String[] { "", "§e通过物质制造机制作" }), null, null, null, null
/*     */         
/*  78 */         })).register(true);
/*     */     
/*  80 */     (new CreatorMachine(Categories.QUANTUM_MACHINES, NarItems.ITEM_CREATOR, "ITEM_CREATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { NarItems.UU, SlimefunItems.CARBONADO, NarItems.UU, SlimefunItems.NETHER_ICE_COOLANT_CELL, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.WITHER_PROOF_GLASS })
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*     */         public String getInventoryTitle()
/*     */         {
/*  87 */           return "&d物质制造机";
/*     */         }
/*     */ 
/*     */         
/*     */         public ItemStack getProgressBar() {
/*  92 */           return new ItemStack(Material.FLINT_AND_STEEL);
/*     */         }
/*     */ 
/*     */         
/*     */         public void registerDefaultRecipes() {
/*  97 */           registerRecipe(36, new ItemStack[] { NarItems.UU }, new ItemStack[] { NarItems.IRIDIUM });
/*  98 */           registerRecipe(36, new ItemStack[] { NarItems.UU }, new ItemStack[] { SlimefunItems.NEPTUNIUM });
/*  99 */           registerRecipe(36, new ItemStack[] { NarItems.UU }, new ItemStack[] { SlimefunItems.PLUTONIUM });
/*     */         }
/*     */ 
/*     */         
/*     */         public int getEnergyConsumption() {
/* 104 */           return 768;
/*     */         }
/*     */ 
/*     */         
/*     */         public String getMachineIdentifier() {
/* 109 */           return "ITEM_CREATOR";
/*     */         }
/* 111 */       }).registerChargeableBlock(true, 65535);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Setup\NarItemSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */