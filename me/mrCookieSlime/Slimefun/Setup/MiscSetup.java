/*     */ package me.mrCookieSlime.Slimefun.Setup;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.Colors;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItemSerializer;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Misc.PostSlimefunLoadingHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Research;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.machines.AutomatedCraftingChamber;
/*     */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*     */ import me.mrCookieSlime.Slimefun.api.Slimefun;
/*     */ import me.mrCookieSlime.Slimefun.api.SlimefunRecipes;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MiscSetup
/*     */ {
/*  32 */   public static List<PostSlimefunLoadingHandler> post_handlers = new ArrayList<>();
/*     */   
/*     */   public static void setupMisc() {
/*  35 */     if (SlimefunItem.getByID("COMMON_TALISMAN") != null && ((Boolean)Slimefun.getItemValue("COMMON_TALISMAN", "recipe-requires-nether-stars")).booleanValue()) {
/*  36 */       SlimefunItem.getByID("COMMON_TALISMAN").setRecipe(new ItemStack[] { SlimefunItems.MAGIC_LUMP_2, SlimefunItems.GOLD_8K, SlimefunItems.MAGIC_LUMP_2, null, new ItemStack(Material.NETHER_STAR), null, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.GOLD_8K, SlimefunItems.MAGIC_LUMP_2 });
/*     */     }
/*  38 */     SlimefunItem.setRadioactive(SlimefunItems.URANIUM);
/*  39 */     SlimefunItem.setRadioactive(SlimefunItems.SMALL_URANIUM);
/*  40 */     SlimefunItem.setRadioactive(SlimefunItems.BLISTERING_INGOT);
/*  41 */     SlimefunItem.setRadioactive(SlimefunItems.BLISTERING_INGOT_2);
/*  42 */     SlimefunItem.setRadioactive(SlimefunItems.BLISTERING_INGOT_3);
/*  43 */     SlimefunItem.setRadioactive(SlimefunItems.NETHER_ICE);
/*  44 */     SlimefunItem.setRadioactive(SlimefunItems.ENRICHED_NETHER_ICE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadItems() {
/*  49 */     Iterator<SlimefunItem> iterator = SlimefunItem.items.iterator();
/*  50 */     while (iterator.hasNext()) {
/*  51 */       SlimefunItem item = iterator.next();
/*  52 */       if (item == null) {
/*  53 */         System.err.println("[远古工艺] Removed bugged Item ('NULL?')");
/*  54 */         iterator.remove(); continue;
/*     */       } 
/*  56 */       if (item.getItem() == null) {
/*  57 */         System.err.println("[远古工艺] Removed bugged Item ('" + item.getID() + "')");
/*  58 */         iterator.remove();
/*     */       } 
/*     */     } 
/*     */     
/*  62 */     List<SlimefunItem> pre = new ArrayList<>();
/*  63 */     List<SlimefunItem> init = new ArrayList<>();
/*  64 */     List<SlimefunItem> post = new ArrayList<>();
/*     */     
/*  66 */     for (SlimefunItem item : SlimefunItem.list()) {
/*  67 */       if (item instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Alloy || item instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ReplacingAlloy) { pre.add(item); continue; }
/*  68 */        if (item instanceof SlimefunMachine) { init.add(item); continue; }
/*  69 */        post.add(item);
/*     */     } 
/*     */     
/*  72 */     for (SlimefunItem item : pre) {
/*  73 */       item.load();
/*     */     }
/*  75 */     for (SlimefunItem item : init) {
/*  76 */       item.load();
/*     */     }
/*  78 */     for (SlimefunItem item : post) {
/*  79 */       item.load();
/*     */     }
/*     */     
/*  82 */     AutomatedCraftingChamber crafter = (AutomatedCraftingChamber)SlimefunItem.getByID("AUTOMATED_CRAFTING_CHAMBER");
/*     */     
/*  84 */     if (crafter != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 105 */       for (ItemStack[] inputs : RecipeType.getRecipeInputList(SlimefunItem.getByID("ENHANCED_CRAFTING_TABLE"))) {
/* 106 */         StringBuilder builder = new StringBuilder();
/* 107 */         int i = 0;
/* 108 */         for (ItemStack item : inputs) {
/* 109 */           if (i > 0) {
/* 110 */             builder.append(" </slot> ");
/*     */           }
/*     */           
/* 113 */           builder.append(CustomItemSerializer.serialize(item, new CustomItemSerializer.ItemFlag[] { CustomItemSerializer.ItemFlag.DATA, CustomItemSerializer.ItemFlag.ITEMMETA_DISPLAY_NAME, CustomItemSerializer.ItemFlag.ITEMMETA_LORE, CustomItemSerializer.ItemFlag.MATERIAL }));
/*     */           
/* 115 */           i++;
/*     */         } 
/*     */         
/* 118 */         AutomatedCraftingChamber.recipes.put(builder.toString(), RecipeType.getRecipeOutputList(SlimefunItem.getByID("ENHANCED_CRAFTING_TABLE"), inputs));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 123 */     SlimefunItem grinder = SlimefunItem.getByID("GRIND_STONE");
/* 124 */     if (grinder != null) {
/* 125 */       ItemStack[] input = null;
/* 126 */       for (ItemStack[] recipe : ((SlimefunMachine)grinder).getRecipes()) {
/* 127 */         if (input == null) { input = recipe; continue; }
/*     */         
/* 129 */         if (input[0] != null && recipe[0] != null) {
/* 130 */           SlimefunRecipes.registerMachineRecipe("ELECTRIC_ORE_GRINDER", 4, new ItemStack[] { input[0] }, new ItemStack[] { recipe[0] });
/*     */         }
/* 132 */         input = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 137 */     SlimefunItem crusher = SlimefunItem.getByID("ORE_CRUSHER");
/* 138 */     if (crusher != null) {
/* 139 */       ItemStack[] input = null;
/* 140 */       for (ItemStack[] recipe : ((SlimefunMachine)crusher).getRecipes()) {
/* 141 */         if (input == null) { input = recipe; continue; }
/*     */         
/* 143 */         if (input[0] != null && recipe[0] != null) {
/* 144 */           SlimefunRecipes.registerMachineRecipe("ELECTRIC_ORE_GRINDER", 4, new ItemStack[] { input[0] }, new ItemStack[] { recipe[0] });
/*     */         }
/* 146 */         input = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 151 */     SlimefunItem smeltery = SlimefunItem.getByID("SMELTERY");
/* 152 */     if (smeltery != null) {
/* 153 */       ItemStack[] input = null;
/* 154 */       for (ItemStack[] recipe : ((SlimefunMachine)smeltery).getRecipes()) {
/* 155 */         if (input == null) { input = recipe; continue; }
/*     */         
/* 157 */         if (input[0] != null && recipe[0] != null) {
/* 158 */           List<ItemStack> inputs = new ArrayList<>();
/* 159 */           boolean dust = false;
/* 160 */           for (ItemStack i : input) {
/* 161 */             if (i != null) {
/* 162 */               inputs.add(i);
/* 163 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.ALUMINUM_DUST, true)) dust = true; 
/* 164 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.COPPER_DUST, true)) dust = true; 
/* 165 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.GOLD_DUST, true)) dust = true; 
/* 166 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.IRON_DUST, true)) dust = true; 
/* 167 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.LEAD_DUST, true)) dust = true; 
/* 168 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.MAGNESIUM_DUST, true)) dust = true; 
/* 169 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.SILVER_DUST, true)) dust = true; 
/* 170 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.TIN_DUST, true)) dust = true; 
/* 171 */               if (SlimefunManager.isItemSimiliar(i, SlimefunItems.ZINC_DUST, true)) dust = true;
/*     */             
/*     */             } 
/*     */           } 
/* 175 */           if (!dust || inputs.size() != 1)
/*     */           {
/*     */ 
/*     */             
/* 179 */             SlimefunRecipes.registerMachineRecipe("ELECTRIC_SMELTERY", 12, inputs.<ItemStack>toArray(new ItemStack[inputs.size()]), new ItemStack[] { recipe[0] });
/*     */           }
/*     */         } 
/* 182 */         input = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 187 */     ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
/* 188 */     ChatColor color = Colors.getRandom();
/*     */     
/* 190 */     for (PostSlimefunLoadingHandler handler : post_handlers) {
/* 191 */       handler.run(pre, init, post);
/*     */     }
/*     */     
/* 194 */     consoleCommandSender.sendMessage(color + "###################### - 远古工艺 - ######################");
/* 195 */     consoleCommandSender.sendMessage(color + "        成功加载了 " + SlimefunItem.list().size() + " 个物品 (" + Research.list().size() + " 项研究)");
/* 196 */     consoleCommandSender.sendMessage(color + "    ( " + SlimefunItem.vanilla + " 个物品来自原生远古工艺, " + (SlimefunItem.list().size() - SlimefunItem.vanilla) + " 个物品来自扩展 )");
/* 197 */     consoleCommandSender.sendMessage(color + "##########################################################");
/* 198 */     SlimefunStartup.getItemCfg().save();
/* 199 */     SlimefunStartup.getResearchCfg().save();
/* 200 */     SlimefunStartup.getWhitelist().save();
/*     */   }
/*     */   
/*     */   public static void setupItemSettings() {
/* 204 */     for (World world : Bukkit.getWorlds()) {
/* 205 */       SlimefunStartup.getWhitelist().setDefaultValue(world.getName() + ".enabled-items.SLIMEFUN_GUIDE", Boolean.valueOf(true));
/*     */     }
/* 207 */     Slimefun.setItemVariable("ORE_CRUSHER", "double-ores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static void loadDescriptions() {
/* 211 */     Slimefun.addYoutubeVideo("ANCIENT_ALTAR", "https://youtu.be/mx2Y5DP8uZI");
/* 212 */     Slimefun.addYoutubeVideo("ANCIENT_PEDESTAL", "https://youtu.be/mx2Y5DP8uZI");
/*     */     
/* 214 */     Slimefun.addYoutubeVideo("BLISTERING_INGOT", "https://youtu.be/mPhKUv4JR_Y");
/* 215 */     Slimefun.addYoutubeVideo("BLISTERING_INGOT_2", "https://youtu.be/mPhKUv4JR_Y");
/* 216 */     Slimefun.addYoutubeVideo("BLISTERING_INGOT_3", "https://youtu.be/mPhKUv4JR_Y");
/*     */     
/* 218 */     Slimefun.addYoutubeVideo("INFERNAL_BONEMEAL", "https://youtu.be/gKxWqMlJDXY");
/*     */     
/* 220 */     Slimefun.addYoutubeVideo("RAINBOW_WOOL", "https://youtu.be/csvb0CxofdA");
/* 221 */     Slimefun.addYoutubeVideo("RAINBOW_GLASS", "https://youtu.be/csvb0CxofdA");
/* 222 */     Slimefun.addYoutubeVideo("RAINBOW_CLAY", "https://youtu.be/csvb0CxofdA");
/* 223 */     Slimefun.addYoutubeVideo("RAINBOW_GLASS_PANE", "https://youtu.be/csvb0CxofdA");
/*     */     
/* 225 */     Slimefun.addYoutubeVideo("RAINBOW_WOOL_XMAS", "https://youtu.be/l4pKk4SDE");
/* 226 */     Slimefun.addYoutubeVideo("RAINBOW_GLASS_XMAS", "https://youtu.be/l4pKk4SDE");
/* 227 */     Slimefun.addYoutubeVideo("RAINBOW_CLAY_XMAS", "https://youtu.be/l4pKk4SDE");
/* 228 */     Slimefun.addYoutubeVideo("RAINBOW_GLASS_PANE_XMAS", "https://youtu.be/l4pKk4SDE");
/*     */     
/* 230 */     Slimefun.addYoutubeVideo("OIL_PUMP", "https://youtu.be/_XmJ6hrv9uY");
/* 231 */     Slimefun.addYoutubeVideo("GPS_GEO_SCANNER", "https://youtu.be/_XmJ6hrv9uY");
/* 232 */     Slimefun.addYoutubeVideo("REFINERY", "https://youtu.be/_XmJ6hrv9uY");
/* 233 */     Slimefun.addYoutubeVideo("BUCKET_OF_OIL", "https://youtu.be/_XmJ6hrv9uY");
/* 234 */     Slimefun.addYoutubeVideo("BUCKET_OF_FUEL", "https://youtu.be/_XmJ6hrv9uY");
/*     */     
/* 236 */     Slimefun.addYoutubeVideo("GPS_TELEPORTER_PYLON", "https://youtu.be/ZnEhG8Kw6zU");
/* 237 */     Slimefun.addYoutubeVideo("GPS_TELEPORTATION_MATRIX", "https://youtu.be/ZnEhG8Kw6zU");
/* 238 */     Slimefun.addYoutubeVideo("GPS_TELEPORTER_PYLON", "https://youtu.be/ZnEhG8Kw6zU");
/*     */     
/* 240 */     Slimefun.addYoutubeVideo("PROGRAMMABLE_ANDROID_WOODCUTTER", "https://youtu.be/AGLsWSMs6A0");
/* 241 */     Slimefun.addYoutubeVideo("PROGRAMMABLE_ANDROID_BUTCHER", "https://youtu.be/G-re3qV-LJQ");
/* 242 */     Slimefun.addYoutubeVideo("PROGRAMMABLE_ANDROID_2_BUTCHER", "https://youtu.be/G-re3qV-LJQ");
/*     */     
/* 244 */     Slimefun.addYoutubeVideo("INFUSED_HOPPER", "https://youtu.be/_H2HGwkfBh8");
/*     */     
/* 246 */     Slimefun.addYoutubeVideo("ELEVATOR_PLATE", "https://youtu.be/OdKMjo6vNIs");
/*     */     
/* 248 */     Slimefun.addYoutubeVideo("ENERGY_REGULATOR", "https://youtu.be/QvSUfBYagXk");
/* 249 */     Slimefun.addYoutubeVideo("COMBUSTION_REACTOR", "https://youtu.be/QvSUfBYagXk");
/* 250 */     Slimefun.addYoutubeVideo("MULTIMETER", "https://youtu.be/QvSUfBYagXk");
/*     */     
/* 252 */     Slimefun.addYoutubeVideo("FOOD_FABRICATOR", "https://youtu.be/qJdFfvTGOmI");
/* 253 */     Slimefun.addYoutubeVideo("AUTO_BREEDER", "https://youtu.be/qJdFfvTGOmI");
/* 254 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_MELON", "https://youtu.be/qJdFfvTGOmI");
/* 255 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_WHEAT", "https://youtu.be/qJdFfvTGOmI");
/* 256 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_APPLE", "https://youtu.be/qJdFfvTGOmI");
/* 257 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_CARROT", "https://youtu.be/qJdFfvTGOmI");
/* 258 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_SEEDS", "https://youtu.be/qJdFfvTGOmI");
/* 259 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_BEETROOT", "https://youtu.be/qJdFfvTGOmI");
/* 260 */     Slimefun.addYoutubeVideo("ORGANIC_FOOD_POTATO", "https://youtu.be/qJdFfvTGOmI");
/* 261 */     Slimefun.addYoutubeVideo("ANIMAL_GROWTH_ACCELERATOR", "https://youtu.be/bV4wEaSxXFw");
/*     */ 
/*     */     
/* 264 */     Slimefun.addYoutubeVideo("FOOD_COMPOSTER", "https://youtu.be/LjzUlFKAHCI");
/* 265 */     Slimefun.addYoutubeVideo("FERTILIZER_WHEAT", "https://youtu.be/LjzUlFKAHCI");
/* 266 */     Slimefun.addYoutubeVideo("FERTILIZER_APPLE", "https://youtu.be/LjzUlFKAHCI");
/* 267 */     Slimefun.addYoutubeVideo("FERTILIZER_POTATO", "https://youtu.be/LjzUlFKAHCI");
/* 268 */     Slimefun.addYoutubeVideo("FERTILIZER_CARROT", "https://youtu.be/LjzUlFKAHCI");
/* 269 */     Slimefun.addYoutubeVideo("FERTILIZER_SEEDS", "https://youtu.be/LjzUlFKAHCI");
/* 270 */     Slimefun.addYoutubeVideo("FERTILIZER_BEETROOT", "https://youtu.be/LjzUlFKAHCI");
/* 271 */     Slimefun.addYoutubeVideo("FERTILIZER_MELON", "https://youtu.be/LjzUlFKAHCI");
/* 272 */     Slimefun.addYoutubeVideo("CROP_GROWTH_ACCELERATOR", "https://youtu.be/LjzUlFKAHCI");
/*     */     
/* 274 */     Slimefun.addYoutubeVideo("XP_COLLECTOR", "https://youtu.be/fHtJDPeLMlg");
/*     */     
/* 276 */     Slimefun.addYoutubeVideo("ELECTRIC_ORE_GRINDER", "https://youtu.be/A6OuK7sfnaI");
/* 277 */     Slimefun.addYoutubeVideo("ELECTRIC_GOLD_PAN", "https://youtu.be/A6OuK7sfnaI");
/* 278 */     Slimefun.addYoutubeVideo("ELECTRIC_DUST_WASHER", "https://youtu.be/A6OuK7sfnaI");
/* 279 */     Slimefun.addYoutubeVideo("ELECTRIC_INGOT_FACTORY", "https://youtu.be/A6OuK7sfnaI");
/*     */     
/* 281 */     Slimefun.addYoutubeVideo("AUTOMATED_CRAFTING_CHAMBER", "https://youtu.be/FZj7nu9sOYA");
/*     */     
/* 283 */     Slimefun.addYoutubeVideo("CARGO_MANAGER", "https://youtu.be/Lt2aGw5lQPI");
/* 284 */     Slimefun.addYoutubeVideo("CARGO_NODE_INPUT", "https://youtu.be/Lt2aGw5lQPI");
/* 285 */     Slimefun.addYoutubeVideo("CARGO_NODE_OUTPUT", "https://youtu.be/Lt2aGw5lQPI");
/*     */     
/* 287 */     Slimefun.addYoutubeVideo("GPS_CONTROL_PANEL", "https://youtu.be/kOopBkiRzjs");
/*     */     
/* 289 */     Slimefun.addYoutubeVideo("GPS_TRANSMITTER", "https://youtu.be/kOopBkiRzjs");
/* 290 */     Slimefun.addYoutubeVideo("GPS_TRANSMITTER_2", "https://youtu.be/kOopBkiRzjs");
/* 291 */     Slimefun.addYoutubeVideo("GPS_TRANSMITTER_3", "https://youtu.be/kOopBkiRzjs");
/* 292 */     Slimefun.addYoutubeVideo("GPS_TRANSMITTER_4", "https://youtu.be/kOopBkiRzjs");
/*     */     
/* 294 */     Slimefun.addYoutubeVideo("SOLAR_GENERATOR", "https://youtu.be/kOopBkiRzjs");
/* 295 */     Slimefun.addYoutubeVideo("SOLAR_GENERATOR_2", "https://youtu.be/kOopBkiRzjs");
/* 296 */     Slimefun.addYoutubeVideo("SOLAR_GENERATOR_3", "https://youtu.be/kOopBkiRzjs");
/* 297 */     Slimefun.addYoutubeVideo("SOLAR_GENERATOR_4", "https://youtu.be/kOopBkiRzjs");
/*     */     
/* 299 */     WikiSetup.setup();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Setup\MiscSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */