/*    */ package me.mrCookieSlime.Slimefun.Lists;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunGadget;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
/*    */ import me.mrCookieSlime.Slimefun.api.SlimefunRecipes;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeType
/*    */ {
/* 17 */   public static final RecipeType MULTIBLOCK = new RecipeType((ItemStack)new CustomItem(Material.BRICK, "&b复合型方块机器", 0, new String[] { "", "&a按界面中的上下顺序", "&a使用方块在地上搭建" }));
/* 18 */   public static final RecipeType ARMOR_FORGE = new RecipeType((ItemStack)new CustomItem(Material.ANVIL, "&b护甲锻造器", 0, new String[] { "", "&a使用&e护甲锻造器&a制作" }), "ARMOR_FORGE");
/* 19 */   public static final RecipeType GRIND_STONE = new RecipeType((ItemStack)new CustomItem(Material.DISPENSER, "&b磨石", 0, new String[] { "", "&a使用&e磨石&a制作" }), "GRIND_STONE");
/* 20 */   public static final RecipeType MOB_DROP = new RecipeType((ItemStack)new CustomItem(Material.MONSTER_EGG, "&b生物掉落", 0, new String[] { "", "&a通过杀死特定的生物掉落" }));
/* 21 */   public static final RecipeType SMELTERY = new RecipeType((ItemStack)new CustomItem(Material.FURNACE, "&6冶炼厂", 0, new String[] { "", "&a使用&e冶炼厂&a制作" }), "SMELTERY");
/* 22 */   public static final RecipeType ORE_CRUSHER = new RecipeType((ItemStack)new CustomItem(Material.DISPENSER, "&b碎矿机", 0, new String[] { "", "&a使用&e碎矿机&a进行粉碎" }), "ORE_CRUSHER");
/* 23 */   public static final RecipeType GOLD_PAN = new RecipeType((ItemStack)new CustomItem(Material.BOWL, "&b淘洗盘", 0, new String[] { "", "&a手持&e淘洗盘&a对砂砾进行淘洗" }));
/* 24 */   public static final RecipeType COMPRESSOR = new RecipeType((ItemStack)new CustomItem(Material.PISTON_BASE, "&b压缩机", 0, new String[] { "", "&a使用&e压缩机&a进行压缩" }), "COMPRESSOR");
/* 25 */   public static final RecipeType PRESSURE_CHAMBER = new RecipeType((ItemStack)new CustomItem(Material.GLASS, "&b压力室", 0, new String[] { "", "&a使用&e压缩机&a进行压缩加工" }), "PRESSURE_CHAMBER");
/* 26 */   public static final RecipeType OVEN = new RecipeType((ItemStack)new CustomItem(Material.FURNACE, "&b烤炉", 0, new String[] { "", "&a&o在烤炉里制作" }), "OVEN");
/* 27 */   public static final RecipeType MAGIC_WORKBENCH = new RecipeType((ItemStack)new CustomItem(Material.BOOKSHELF, "&6魔法合成台", 0, new String[] { "", "&a使用&e魔法合成台&a进行制作" }), "MAGIC_WORKBENCH");
/* 28 */   public static final RecipeType ORE_WASHER = new RecipeType((ItemStack)new CustomItem(Material.CAULDRON_ITEM, "&6洗矿机", 0, new String[] { "", "&a使用&e洗矿机&a进行制作" }), "ORE_WASHER");
/* 29 */   public static final RecipeType ENHANCED_CRAFTING_TABLE = new RecipeType((ItemStack)new CustomItem(Material.WORKBENCH, "&e强化合成台", 0, new String[] { "", "&a远古工艺的基础合成台", "&a普通的合成台无法进行远古工艺制作" }), "ENHANCED_CRAFTING_TABLE");
/* 30 */   public static final RecipeType JUICER = new RecipeType((ItemStack)new CustomItem(Material.GLASS_BOTTLE, "&e榨汁机", 0, new String[] { "", "&a来点美味的果汁?" }), "JUICER");
/* 31 */   public static final RecipeType ANCIENT_ALTAR = new RecipeType((ItemStack)new CustomItem(Material.ENCHANTMENT_TABLE, "&4远古祭坛", 0, new String[] { "", "&d你必须通过使用远古祭坛", "&d执行神秘的远古魔法仪式", "&d才能合成出这个物品" }));
/* 32 */   public static final RecipeType HEATED_PRESSURE_CHAMBER = new RecipeType((ItemStack)new CustomItem(Material.STAINED_GLASS, "&c热压力室", 8, new String[] { "", "&a使用&e热压力室", "&a来制作这个物品" }), "HEATED_PRESSURE_CHAMBER");
/*    */   
/* 34 */   public static final RecipeType SHAPED_RECIPE = new RecipeType((ItemStack)new CustomItem(Material.WORKBENCH, "&e有序合成", 0, new String[] { "", "&a这个合成只需使用普通的合成台" }));
/* 35 */   public static final RecipeType SHAPELESS_RECIPE = new RecipeType((ItemStack)new CustomItem(Material.WORKBENCH, "&e无序合成", 0, new String[] { "", "&a这个合成只需使用普通的合成台" }));
/* 36 */   public static final RecipeType FURNACE = new RecipeType((ItemStack)new CustomItem(Material.FURNACE, "&e普通熔炉合成", 0, new String[] { "", "&a&o只需要使用普通的熔炉制作" }));
/* 37 */   public static final RecipeType NULL = new RecipeType(null);
/*    */   
/*    */   ItemStack item;
/*    */   String machine;
/*    */   
/*    */   public RecipeType(ItemStack item) {
/* 43 */     this.item = item;
/* 44 */     this.machine = "";
/*    */   }
/*    */   
/*    */   public RecipeType(ItemStack item, String machine) {
/* 48 */     this.item = item;
/* 49 */     this.machine = machine;
/*    */   }
/*    */   
/*    */   public RecipeType(String machine, int seconds, ItemStack[] input, ItemStack[] output) {
/* 53 */     this.machine = machine;
/* 54 */     SlimefunItem item = getMachine();
/* 55 */     this.item = item.getItem();
/*    */     
/* 57 */     SlimefunRecipes.registerMachineRecipe(machine, seconds, input, output);
/*    */   }
/*    */   
/*    */   public ItemStack toItem() {
/* 61 */     return this.item;
/*    */   }
/*    */   
/*    */   public SlimefunItem getMachine() {
/* 65 */     return SlimefunItem.getByID(this.machine);
/*    */   }
/*    */   
/*    */   public static List<ItemStack> getRecipeInputs(SlimefunItem machine) {
/* 69 */     if (machine == null) return new ArrayList<>(); 
/* 70 */     List<ItemStack[]> recipes = (machine instanceof SlimefunMachine) ? ((SlimefunMachine)machine).getRecipes() : ((SlimefunGadget)machine).getRecipes();
/* 71 */     List<ItemStack> convertable = new ArrayList<>();
/* 72 */     for (int i = 0; i < recipes.size(); i++) {
/* 73 */       if (i % 2 == 0) convertable.add(((ItemStack[])recipes.get(i))[0]); 
/*    */     } 
/* 75 */     return convertable;
/*    */   }
/*    */   
/*    */   public static List<ItemStack[]> getRecipeInputList(SlimefunItem machine) {
/* 79 */     if (machine == null) return (List)new ArrayList<>(); 
/* 80 */     List<ItemStack[]> recipes = (machine instanceof SlimefunMachine) ? ((SlimefunMachine)machine).getRecipes() : ((SlimefunGadget)machine).getRecipes();
/* 81 */     List<ItemStack[]> convertable = (List)new ArrayList<>();
/* 82 */     for (int i = 0; i < recipes.size(); i++) {
/* 83 */       if (i % 2 == 0) convertable.add(recipes.get(i)); 
/*    */     } 
/* 85 */     return convertable;
/*    */   }
/*    */   
/*    */   public static ItemStack getRecipeOutput(SlimefunItem machine, ItemStack input) {
/* 89 */     List<ItemStack[]> recipes = (machine instanceof SlimefunMachine) ? ((SlimefunMachine)machine).getRecipes() : ((SlimefunGadget)machine).getRecipes();
/* 90 */     return ((ItemStack[])recipes.get(getRecipeInputs(machine).indexOf(input) * 2 + 1))[0];
/*    */   }
/*    */   
/*    */   public static ItemStack getRecipeOutputList(SlimefunItem machine, ItemStack[] input) {
/* 94 */     List<ItemStack[]> recipes = (machine instanceof SlimefunMachine) ? ((SlimefunMachine)machine).getRecipes() : ((SlimefunGadget)machine).getRecipes();
/* 95 */     return ((ItemStack[])recipes.get(getRecipeInputList(machine).indexOf(input) * 2 + 1))[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Lists\RecipeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */