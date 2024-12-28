/*     */ package me.mrCookieSlime.Slimefun.Lists;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomArmor;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomPotion;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.String.Christmas;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.FireworkEffect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.FireworkEffectMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlimefunItems
/*     */ {
/*  24 */   public static ItemStack PORTABLE_CRAFTER = (ItemStack)new CustomItem(Material.BOOK, "&6便携式合成台", 0, new String[] { "&a随时随地合成物品", "", "&e右键&7 打开" });
/*  25 */   public static ItemStack PORTABLE_DUSTBIN = null;
/*  26 */   public static ItemStack ENDER_BACKPACK = null;
/*  27 */   public static ItemStack MAGIC_EYE_OF_ENDER = (ItemStack)new CustomItem(Material.EYE_OF_ENDER, "&6&l魔法末影眼", 0, new String[] { "&c需要全套的&d末影护甲&c才能使用", "", "&7&e右键点击&7 可以发射一颗末影珍珠" });
/*  28 */   public static ItemStack BROKEN_SPAWNER = (ItemStack)new CustomItem(new MaterialData(Material.MOB_SPAWNER), "&c破损的刷怪笼", new String[] { "&7类型: &b<Type>", "", "&c已损坏, 必须使用远古祭坛修复后方可使用" });
/*  29 */   public static ItemStack REPAIRED_SPAWNER = (ItemStack)new CustomItem(Material.MOB_SPAWNER, "&b强化刷怪笼", 0, new String[] { "&7类型: &b<Type>" });
/*  30 */   public static ItemStack INFERNAL_BONEMEAL = (ItemStack)new CustomItem(new MaterialData(Material.INK_SACK, (byte)15), "&4地狱骨粉", new String[] { "", "&c来自下界的骨粉", "&c可以用于催熟地狱疣" });
/*     */ 
/*     */   
/*  33 */   public static ItemStack GOLD_PAN = (ItemStack)new CustomItem(Material.BOWL, "&6淘洗盘", 0, new String[] { "&a可以将砂砾变废为宝...", "", "&7&e右键点击&7 砂砾对其进行淘洗" });
/*  34 */   public static ItemStack PARACHUTE = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&r&l降落伞", 0, new String[] { "", "&7按住 &eShift&7 使用" }), Color.WHITE);
/*  35 */   public static ItemStack GRAPPLING_HOOK = (ItemStack)new CustomItem(Material.LEASH, "&6抓钩", 0, new String[] { "", "&7&e右键点击&7 以使用" });
/*  36 */   public static ItemStack SOLAR_HELMET = (ItemStack)new CustomItem(Material.IRON_HELMET, "&b太阳能头盔", 0, new String[] { "", "&a为你手持的耗电物品进行充电" });
/*  37 */   public static ItemStack CLOTH = (ItemStack)new CustomItem(Material.PAPER, "&b布", 0);
/*  38 */   public static ItemStack CAN = null;
/*  39 */   public static ItemStack NIGHT_VISION_GOGGLES = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_HELMET, "&a夜视仪", 0, new String[] { "", "&9+ 夜视" }), Color.BLACK);
/*  40 */   public static ItemStack FARMER_SHOES = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&e神农靴", 0, new String[] { "", "&6穿着它, 庄稼再也不会被踩坏" }), Color.YELLOW);
/*  41 */   public static ItemStack INFUSED_MAGNET = null;
/*  42 */   public static ItemStack FLASK_OF_KNOWLEDGE = (ItemStack)new CustomItem(Material.GLASS_BOTTLE, "&c工匠弗拉斯克的神技", 0, new String[] { "", "&r魔法工匠大师弗拉斯克独创的技术", "&r让你你可以把经验存入瓶子", "&7消耗: &a1 级" });
/*  43 */   public static ItemStack RAG = (ItemStack)new CustomItem(Material.PAPER, "&c布片", 0, new String[] { "", "&a等级 I - 医疗补给品", "", "&r恢复 2 ❤", "&r灭火", "", "&7&e右键点击&7 以使用" });
/*  44 */   public static ItemStack BANDAGE = (ItemStack)new CustomItem(Material.PAPER, "&c绷带", 0, new String[] { "", "&a等级 II - 医疗补给品", "", "&r恢复 4 ❤", "&r灭火", "", "&7&e右键点击&7 以使用" });
/*  45 */   public static ItemStack SPLINT = (ItemStack)new CustomItem(Material.STICK, "&c夹板", 0, new String[] { "", "&a等级 I - 医疗补给品", "", "&r恢复 2 ❤", "", "&7&e右键点击&7 以使用" });
/*  46 */   public static ItemStack VITAMINS = (ItemStack)new CustomItem(Material.NETHER_STALK, "&c维他命", 0, new String[] { "", "&a等级 III - 医疗补给品", "", "&r恢复 4 ❤", "&r灭火", "&r可治疗 中毒/凋零/辐射", "", "&7&e右键点击&7 以使用" });
/*  47 */   public static ItemStack MEDICINE = (ItemStack)new CustomItem(Material.POTION, "&c药片", 8229, new String[] { "", "&a等级 III - 医疗补给品", "", "&r恢复 4 ❤", "&r灭火", "&r可治疗 中毒/凋零/辐射", "", "&7&e右键点击&7 饮用" });
/*     */ 
/*     */   
/*  50 */   public static ItemStack BACKPACK_SMALL = null;
/*  51 */   public static ItemStack BACKPACK_MEDIUM = null;
/*  52 */   public static ItemStack BACKPACK_LARGE = null;
/*  53 */   public static ItemStack WOVEN_BACKPACK = null;
/*  54 */   public static ItemStack GILDED_BACKPACK = null;
/*  55 */   public static ItemStack BOUND_BACKPACK = null;
/*  56 */   public static ItemStack COOLER = null;
/*     */   
/*  58 */   public static ItemStack VOIDBAG_SMALL = null;
/*  59 */   public static ItemStack VOIDBAG_MEDIUM = null;
/*  60 */   public static ItemStack VOIDBAG_BIG = null;
/*  61 */   public static ItemStack VOIDBAG_LARGE = null;
/*  62 */   public static ItemStack BOUND_VOIDBAG = null;
/*     */ 
/*     */   
/*  65 */   public static ItemStack DURALUMIN_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eI", new String[] { "", "&8⇨ &7材质: &b硬铝", "&c&o&8⇨ &e⚡ &70 / 20 J", "&8⇨ &7推进力: &c0.35", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  66 */   public static ItemStack SOLDER_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eII", new String[] { "", "&8⇨ &7材质: &b焊材", "&c&o&8⇨ &e⚡ &70 / 30 J", "&8⇨ &7推进力: &c0.4", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  67 */   public static ItemStack BILLON_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eIII", new String[] { "", "&8⇨ &7材质: &b铜锡合金", "&c&o&8⇨ &e⚡ &70 / 45 J", "&8⇨ &7推进力: &c0.45", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  68 */   public static ItemStack STEEL_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eIV", new String[] { "", "&8⇨ &7材质: &b钢", "&c&o&8⇨ &e⚡ &70 / 60 J", "&8⇨ &7推进力: &c0.5", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  69 */   public static ItemStack DAMASCUS_STEEL_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eV", new String[] { "", "&8⇨ &7材质: &b水纹钢", "&c&o&8⇨ &e⚡ &70 / 75 J", "&8⇨ &7推进力: &c0.55", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  70 */   public static ItemStack REINFORCED_ALLOY_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eVI", new String[] { "", "&8⇨ &7材质: &b强化合金", "&c&o&8⇨ &e⚡ &70 / 100 J", "&8⇨ &7推进力: &c0.6", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  71 */   public static ItemStack CARBONADO_JETPACK = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9电力喷气背包 &7- &eVII", new String[] { "", "&8⇨ &7材质: &b黑钻", "&c&o&8⇨ &e⚡ &70 / 150 J", "&8⇨ &7推进力: &c0.7", "", "&7按住 &eShift&7 以使用" }), Color.BLACK);
/*  72 */   public static ItemStack ARMORED_JETPACK = (ItemStack)new CustomItem(new MaterialData(Material.IRON_CHESTPLATE), "&9防护型喷气背包", new String[] { "&8⇨ &7材质: &b钢", "", "&c&o&8⇨ &e⚡ &70 / 50 J", "&8⇨ &7推进力: &c0.45", "", "&7按住 &eShift&7 以使用" });
/*     */ 
/*     */   
/*  75 */   public static ItemStack DURALUMIN_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eI", new String[] { "", "&8⇨ &7材质: &b硬铝", "&c&o&8⇨ &e⚡ &70 / 20 J", "&8⇨ &7速度: &a0.35", "&8⇨ &7精度: &c50%", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  76 */   public static ItemStack SOLDER_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eII", new String[] { "", "&8⇨ &7材质: &b焊材", "&c&o&8⇨ &e⚡ &70 / 30 J", "&8⇨ &7速度: &a0.4", "&8⇨ &7精度: &660%", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  77 */   public static ItemStack BILLON_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eIII", new String[] { "", "&8⇨ &7材质: &b铜锡合金", "&c&o&8⇨ &e⚡ &70 / 40 J", "&8⇨ &7速度: &a0.45", "&8⇨ &7精度: &665%", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  78 */   public static ItemStack STEEL_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eIV", new String[] { "", "&8⇨ &7材质: &b钢", "&c&o&8⇨ &e⚡ &70 / 50 J", "&8⇨ &7速度: &a0.5", "&8⇨ &7精度: &e70%", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  79 */   public static ItemStack DAMASCUS_STEEL_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eV", new String[] { "", "&8⇨ &7材质: &b水纹钢", "&c&o&8⇨ &e⚡ &70 / 75 J", "&8⇨ &7速度: &a0.55", "&8⇨ &7精度: &a75%", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  80 */   public static ItemStack REINFORCED_ALLOY_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eVI", new String[] { "", "&8⇨ &7材质: &b强化合金", "&c&o&8⇨ &e⚡ &70 / 100 J", "&8⇨ &7速度: &a0.6", "&8⇨ &7精度: &c80%", "", "&7按住 &eShift&7 以使用" }), Color.SILVER);
/*  81 */   public static ItemStack CARBONADO_JETBOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9喷气靴 &7- &eVII", new String[] { "", "&8⇨ &7材质: &b黑钻", "&c&o&8⇨ &e⚡ &70 / 125 J", "&8⇨ &7速度: &a0.7", "&8⇨ &7精度: &c99.9%", "", "&7按住 &eShift&7 以使用" }), Color.BLACK);
/*  82 */   public static ItemStack ARMORED_JETBOOTS = (ItemStack)new CustomItem(new MaterialData(Material.IRON_BOOTS), "&9防护型喷气靴", new String[] { "", "&8⇨ &7材质: &b钢", "&c&o&8⇨ &e⚡ &70 / 50 J", "&8⇨ &7速度: &a0.45", "&8⇨ &7精度: &e70%", "", "&7按住 &eShift&7 以使用" });
/*     */ 
/*     */   
/*  85 */   public static ItemStack DURALUMIN_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eI", new String[] { "", "&8⇨ &7材质: &b硬铝", "&c&o&8⇨ &e⚡ &70 / 20 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*  86 */   public static ItemStack SOLDER_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eII", new String[] { "", "&8⇨ &7材质: &b焊材", "&c&o&8⇨ &e⚡ &70 / 30 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*  87 */   public static ItemStack BILLON_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eIII", new String[] { "", "&8⇨ &7材质: &b铜锡合金", "&c&o&8⇨ &e⚡ &70 / 40 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*  88 */   public static ItemStack STEEL_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eIV", new String[] { "", "&8⇨ &7材质: &b钢", "&c&o&8⇨ &e⚡ &70 / 50 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*  89 */   public static ItemStack DAMASCUS_STEEL_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eV", new String[] { "", "&8⇨ &7材质: &b水纹钢", "&c&o&8⇨ &e⚡ &70 / 60 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*  90 */   public static ItemStack REINFORCED_ALLOY_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eVI", new String[] { "", "&8⇨ &7材质: &b强化合金", "&c&o&8⇨ &e⚡ &70 / 75 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*  91 */   public static ItemStack CARBONADO_MULTI_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.SHEARS), "&9多功能工具 &7- &eVII", new String[] { "", "&8⇨ &7材质: &b黑钻", "&c&o&8⇨ &e⚡ &70 / 100 J", "", "&7&e右键点击&7 以使用", "&7按住 &eShift + 右键点击&7 以修改模式" });
/*     */ 
/*     */   
/*  94 */   public static ItemStack FORTUNE_COOKIE = (ItemStack)new CustomItem(Material.COOKIE, "&6幸运曲奇", 0, new String[] { "", "&a幸运?亦或是霉运? :o" });
/*  95 */   public static ItemStack BEEF_JERKY = (ItemStack)new CustomItem(Material.COOKED_BEEF, "&6牛肉干", 0, new String[] { "", "&a肉厚满足" });
/*  96 */   public static ItemStack MAGIC_SUGAR = (ItemStack)new CustomItem(Material.SUGAR, "&6魔法糖", 0, new String[] { "", "&a你感到浑身充满了力量!" });
/*  97 */   public static ItemStack MONSTER_JERKY = (ItemStack)new CustomItem(Material.ROTTEN_FLESH, "&6怪物肉干", 0, new String[] { "", "&a再也不会挨饿了" });
/*  98 */   public static ItemStack APPLE_JUICE = (ItemStack)new CustomPotion("&c苹果汁", 8197, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
/*  99 */   public static ItemStack MELON_JUICE = (ItemStack)new CustomPotion("&c西瓜汁", 8197, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
/* 100 */   public static ItemStack CARROT_JUICE = (ItemStack)new CustomPotion("&6胡萝卜汁", 8195, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
/* 101 */   public static ItemStack PUMPKIN_JUICE = (ItemStack)new CustomPotion("&6南瓜汁", 8195, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
/* 102 */   public static ItemStack GOLDEN_APPLE_JUICE = (ItemStack)new CustomPotion("&b金苹果汁", 8195, new String[0], new PotionEffect(PotionEffectType.ABSORPTION, 400, 0));
/*     */ 
/*     */   
/* 105 */   public static ItemStack CHRISTMAS_MILK = (ItemStack)new CustomPotion("&6瓶装牛奶", 8194, new String[0], new PotionEffect(PotionEffectType.SATURATION, 5, 0));
/* 106 */   public static ItemStack CHRISTMAS_CHOCOLATE_MILK = (ItemStack)new CustomPotion("&6巧克力奶", 8201, new String[0], new PotionEffect(PotionEffectType.SATURATION, 12, 0));
/* 107 */   public static ItemStack CHRISTMAS_EGG_NOG = (ItemStack)new CustomPotion("&a蛋奶酒", 8194, new String[0], new PotionEffect(PotionEffectType.SATURATION, 7, 0));
/* 108 */   public static ItemStack CHRISTMAS_APPLE_CIDER = (ItemStack)new CustomPotion("&c苹果酒", 8197, new String[0], new PotionEffect(PotionEffectType.SATURATION, 14, 0));
/* 109 */   public static ItemStack CHRISTMAS_COOKIE = (ItemStack)new CustomItem(Material.COOKIE, Christmas.color("圣诞曲奇"), 0);
/* 110 */   public static ItemStack CHRISTMAS_FRUIT_CAKE = (ItemStack)new CustomItem(Material.PUMPKIN_PIE, Christmas.color("水果蛋糕"), 0);
/* 111 */   public static ItemStack CHRISTMAS_APPLE_PIE = (ItemStack)new CustomItem(Material.PUMPKIN_PIE, "&r苹果派", 0);
/* 112 */   public static ItemStack CHRISTMAS_HOT_CHOCOLATE = (ItemStack)new CustomPotion("&6热巧克力", 8201, new String[0], new PotionEffect(PotionEffectType.SATURATION, 14, 0));
/* 113 */   public static ItemStack CHRISTMAS_CAKE = (ItemStack)new CustomItem(Material.PUMPKIN_PIE, Christmas.color("圣诞蛋糕"), 0);
/* 114 */   public static ItemStack CHRISTMAS_CARAMEL = (ItemStack)new CustomItem(Material.CLAY_BRICK, "&6焦糖", 0);
/* 115 */   public static ItemStack CHRISTMAS_CARAMEL_APPLE = (ItemStack)new CustomItem(Material.APPLE, "&6焦糖苹果", 0);
/* 116 */   public static ItemStack CHRISTMAS_CHOCOLATE_APPLE = (ItemStack)new CustomItem(Material.APPLE, "&6巧克力苹果", 0);
/* 117 */   public static ItemStack CHRISTMAS_PRESENT = (ItemStack)new CustomItem(Material.CHEST, Christmas.color("圣诞礼物"), 0, new String[] { "&7来自: &emrCookieSlime", "&7给: &e你", "", "&e右键点击&7 以打开" });
/*     */ 
/*     */   
/* 120 */   public static ItemStack EASTER_EGG = (ItemStack)new CustomItem(Material.EGG, "&r复活节彩蛋", 0, new String[] { "&b惊喜! 惊喜!" });
/* 121 */   public static ItemStack EASTER_CARROT_PIE = (ItemStack)new CustomItem(Material.PUMPKIN_PIE, "&6胡萝卜派", 0);
/*     */ 
/*     */   
/* 124 */   public static ItemStack GRANDMAS_WALKING_STICK = (ItemStack)new CustomItem(Material.STICK, "&7祖母的手杖", 0, new String[0], new String[] { "KNOCKBACK-2" });
/* 125 */   public static ItemStack GRANDPAS_WALKING_STICK = (ItemStack)new CustomItem(Material.STICK, "&7祖父的手杖", 0, new String[0], new String[] { "KNOCKBACK-5" });
/* 126 */   public static ItemStack SWORD_OF_BEHEADING = (ItemStack)new CustomItem(Material.IRON_SWORD, "&6斩首剑", 0, new String[] { "&7斩首 II", "", "&r有机会斩下怪物头颅", "&r(也会提高砍下凋零头颅的几率)" });
/* 127 */   public static ItemStack BLADE_OF_VAMPIRES = (ItemStack)new CustomItem(Material.GOLD_SWORD, "&c暗裔之剑", 0, new String[] { "&7生命偷取 I", "", "&r传说是德古拉一族使用的武器", "&r攻击时有45%几率", "&r偷取 1❤" }, new String[] { "FIRE_ASPECT-2", "DURABILITY-4", "DAMAGE_ALL-2" });
/* 128 */   public static ItemStack SEISMIC_AXE = (ItemStack)new CustomItem(Material.IRON_AXE, "&a地震斧", 0, new String[] { "", "&7随时随地震飞敌人...", "", "&7&e右键点击&7 以使用" });
/*     */ 
/*     */   
/* 131 */   public static ItemStack EXPLOSIVE_BOW = (ItemStack)new CustomItem(Material.BOW, "&c爆炸弓", 0, new String[] { "&r用这把弓射出的箭", "&r都会把敌人给炸上天" });
/* 132 */   public static ItemStack ICY_BOW = (ItemStack)new CustomItem(Material.BOW, "&b寒霜弓", 0, new String[] { "&r用这把弓射出的箭", "&r都会将敌人", "&r冻结2秒" });
/*     */ 
/*     */   
/* 135 */   public static ItemStack AUTO_SMELT_PICKAXE = (ItemStack)new CustomItem(Material.DIAMOND_PICKAXE, "&6冶炼之镐", 0, new String[] { "&c&l自动烧炼挖到的矿物", "", "&9免去了使用熔炉烧炼的麻烦" });
/* 136 */   public static ItemStack LUMBER_AXE = (ItemStack)new CustomItem(Material.DIAMOND_AXE, "&6伐木斧", 0, new String[] { "&a一斧子就能砍倒整棵树..." });
/* 137 */   public static ItemStack PICKAXE_OF_CONTAINMENT = (ItemStack)new CustomItem(Material.IRON_PICKAXE, "&c精确之镐", 0, new String[] { "", "&9能够挖下刷怪笼" });
/* 138 */   public static ItemStack HERCULES_PICKAXE = (ItemStack)new CustomItem(Material.IRON_PICKAXE, "&9赫拉克勒斯的镐", 0, new String[] { "", "&r它是如此强力", "&r以至于可以在挖掘任何矿物时", "&r直接将其变为粉末..." }, new String[] { "DURABILITY-2", "DIG_SPEED-4" });
/* 139 */   public static ItemStack EXPLOSIVE_PICKAXE = (ItemStack)new CustomItem(Material.DIAMOND_PICKAXE, "&e爆炸镐", 0, new String[] { "", "&r可以极大提升你的挖掘速度", "&r一次可以挖掘多个方块...", "", "&9Works with Fortune" });
/* 140 */   public static ItemStack PICKAXE_OF_THE_SEEKER = (ItemStack)new CustomItem(Material.DIAMOND_PICKAXE, "&a寻找者之镐", 0, new String[] { "&r使用它可以使你转向在你附近的矿物的方向", "&r但这有几率会造成镐子损坏", "", "&7&e右键点击&7 使用" });
/* 141 */   public static ItemStack COBALT_PICKAXE = (ItemStack)new CustomItem(Material.IRON_PICKAXE, "&9钴镐", 0, new String[0], new String[] { "DURABILITY-3", "DIG_SPEED-6" });
/* 142 */   public static ItemStack PICKAXE_OF_VEIN_MINING = (ItemStack)new CustomItem(Material.DIAMOND_PICKAXE, "&e矿脉镐", 0, new String[] { "", "&r矿脉镐可以一次挖下整块矿脉", "&r提升你的挖矿效率..." });
/*     */ 
/*     */   
/* 145 */   public static ItemStack GLOWSTONE_HELMET = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_HELMET, "&e&l萤石头盔", 0, new String[] { "", "&a使你能如太阳般闪耀!", "", "&9+ 夜视" }), Color.YELLOW);
/* 146 */   public static ItemStack GLOWSTONE_CHESTPLATE = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&e&l萤石胸甲", 0, new String[] { "", "&a使你能如太阳般闪耀!", "", "&9+ 夜视" }), Color.YELLOW);
/* 147 */   public static ItemStack GLOWSTONE_LEGGINGS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_LEGGINGS, "&e&l萤石护腿", 0, new String[] { "", "&a使你能如太阳般闪耀!", "", "&9+ 夜视" }), Color.YELLOW);
/* 148 */   public static ItemStack GLOWSTONE_BOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&e&l萤石靴子", 0, new String[] { "", "&a使你能如太阳般闪耀!", "", "&9+ 夜视" }), Color.YELLOW);
/* 149 */   public static ItemStack ENDER_HELMET = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_HELMET, "&5&l末影头盔", 0, new String[] { "", "&a让你如幻影般移动!" }), Color.fromRGB(28, 25, 112));
/* 150 */   public static ItemStack ENDER_CHESTPLATE = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&5&l末影胸甲", 0, new String[] { "", "&a让你如幻影般移动!" }), Color.fromRGB(28, 25, 112));
/* 151 */   public static ItemStack ENDER_LEGGINGS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_LEGGINGS, "&5&l末影护腿", 0, new String[] { "", "&a让你如幻影般移动!" }), Color.fromRGB(28, 25, 112));
/* 152 */   public static ItemStack ENDER_BOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&5&l末影靴子", 0, new String[] { "", "&a让你如幻影般移动!", "", "&9+ 无传送伤害" }), Color.fromRGB(28, 25, 112));
/* 153 */   public static ItemStack SLIME_HELMET = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_HELMET, "&a&l史莱姆头盔", 0, new String[] { "", "&a感觉..弹弹的.." }), Color.LIME);
/* 154 */   public static ItemStack SLIME_CHESTPLATE = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&a&l史莱姆胸甲", 0, new String[] { "", "&a感觉..弹弹的.." }), Color.LIME);
/* 155 */   public static ItemStack SLIME_LEGGINGS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_LEGGINGS, "&a&l史莱姆护腿", 0, new String[] { "", "&a感觉..弹弹的..", "", "&9+ 速度" }), Color.LIME);
/* 156 */   public static ItemStack SLIME_BOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&a&l史莱姆靴子", 0, new String[] { "", "&a感觉..弹弹的..", "", "&9+ 弹跳", "&9- 掉落伤害" }), Color.LIME);
/* 157 */   public static ItemStack CACTUS_HELMET = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_HELMET, "&2仙人掌头盔", 0, new String[0], new String[] { "THORNS-3", "DURABILITY-5" }), Color.GREEN);
/* 158 */   public static ItemStack CACTUS_CHESTPLATE = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&2仙人掌胸甲", 0, new String[0], new String[] { "THORNS-3", "DURABILITY-5" }), Color.GREEN);
/* 159 */   public static ItemStack CACTUS_LEGGINGS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_LEGGINGS, "&2仙人掌护腿", 0, new String[0], new String[] { "THORNS-3", "DURABILITY-5" }), Color.GREEN);
/* 160 */   public static ItemStack CACTUS_BOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&2仙人掌靴子", 0, new String[0], new String[] { "THORNS-3", "DURABILITY-5" }), Color.GREEN);
/* 161 */   public static ItemStack DAMASCUS_STEEL_HELMET = (ItemStack)new CustomItem(Material.IRON_HELMET, "&7水纹钢头盔", new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4" }, 0);
/* 162 */   public static ItemStack DAMASCUS_STEEL_CHESTPLATE = (ItemStack)new CustomItem(Material.IRON_CHESTPLATE, "&7水纹钢胸甲", new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4" }, 0);
/* 163 */   public static ItemStack DAMASCUS_STEEL_LEGGINGS = (ItemStack)new CustomItem(Material.IRON_LEGGINGS, "&7水纹钢护腿", new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4" }, 0);
/* 164 */   public static ItemStack DAMASCUS_STEEL_BOOTS = (ItemStack)new CustomItem(Material.IRON_BOOTS, "&7水纹钢靴子", new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4" }, 0);
/* 165 */   public static ItemStack REINFORCED_ALLOY_HELMET = (ItemStack)new CustomItem(Material.IRON_HELMET, "&b强化头盔", new String[] { "DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9" }, 0);
/* 166 */   public static ItemStack REINFORCED_ALLOY_CHESTPLATE = (ItemStack)new CustomItem(Material.IRON_CHESTPLATE, "&b强化胸甲", new String[] { "DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9" }, 0);
/* 167 */   public static ItemStack REINFORCED_ALLOY_LEGGINGS = (ItemStack)new CustomItem(Material.IRON_LEGGINGS, "&b强化护腿", new String[] { "DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9" }, 0);
/* 168 */   public static ItemStack REINFORCED_ALLOY_BOOTS = (ItemStack)new CustomItem(Material.IRON_BOOTS, "&b强化靴子", new String[] { "DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9" }, 0);
/* 169 */   public static ItemStack SCUBA_HELMET = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_HELMET, "&c潜水头盔", 0, new String[] { "", "&bAllows you to breathe Underwater", "&4防护服套装的部分" }), Color.ORANGE);
/* 170 */   public static ItemStack HAZMATSUIT_CHESTPLATE = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_CHESTPLATE, "&c防护服", 0, new String[] { "", "&b避免你被火焰烧伤", "&4防护服套装的部分" }), Color.ORANGE);
/* 171 */   public static ItemStack HAZMATSUIT_LEGGINGS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_LEGGINGS, "&c防护裤", 0, new String[] { "", "&4防护服套装的部分" }), Color.ORANGE);
/* 172 */   public static ItemStack RUBBER_BOOTS = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&c橡胶靴", 0, new String[] { "", "&4防护服套装的部分" }), Color.BLACK);
/* 173 */   public static ItemStack GILDED_IRON_HELMET = (ItemStack)new CustomItem(Material.GOLD_HELMET, "&6镀金铁盔", new String[] { "DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8" }, 0);
/* 174 */   public static ItemStack GILDED_IRON_CHESTPLATE = (ItemStack)new CustomItem(Material.GOLD_CHESTPLATE, "&6镀金铁铠", new String[] { "DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8" }, 0);
/* 175 */   public static ItemStack GILDED_IRON_LEGGINGS = (ItemStack)new CustomItem(Material.GOLD_LEGGINGS, "&6镀金铁护腿", new String[] { "DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8" }, 0);
/* 176 */   public static ItemStack GILDED_IRON_BOOTS = (ItemStack)new CustomItem(Material.GOLD_BOOTS, "&6镀金铁靴", new String[] { "DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8" }, 0);
/* 177 */   public static ItemStack GOLD_HELMET = (ItemStack)new CustomItem(Material.GOLD_HELMET, "&6金质头盔", 0, new String[] { "&912-K" }, new String[] { "DURABILITY-10" });
/* 178 */   public static ItemStack GOLD_CHESTPLATE = (ItemStack)new CustomItem(Material.GOLD_CHESTPLATE, "&6金质胸甲", 0, new String[] { "&912-K" }, new String[] { "DURABILITY-10" });
/* 179 */   public static ItemStack GOLD_LEGGINGS = (ItemStack)new CustomItem(Material.GOLD_LEGGINGS, "&6金质护腿", 0, new String[] { "&912-K" }, new String[] { "DURABILITY-10" });
/* 180 */   public static ItemStack GOLD_BOOTS = (ItemStack)new CustomItem(Material.GOLD_BOOTS, "&6金质靴子", 0, new String[] { "&912-K" }, new String[] { "DURABILITY-10" });
/* 181 */   public static ItemStack SLIME_HELMET_STEEL = (ItemStack)new CustomItem(Material.IRON_HELMET, "&a&l史莱姆头盔", 0, new String[] { "&7已强化", "", "&a感觉..弹弹的.." }, new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2" });
/* 182 */   public static ItemStack SLIME_CHESTPLATE_STEEL = (ItemStack)new CustomItem(Material.IRON_CHESTPLATE, "&a&l史莱姆胸甲", 0, new String[] { "&7已强化", "", "&a感觉..弹弹的.." }, new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2" });
/* 183 */   public static ItemStack SLIME_LEGGINGS_STEEL = (ItemStack)new CustomItem(Material.IRON_LEGGINGS, "&a&l史莱姆护腿", 0, new String[] { "&7已强化", "", "&a感觉..弹弹的..", "", "&9+ 速度" }, new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2" });
/* 184 */   public static ItemStack SLIME_BOOTS_STEEL = (ItemStack)new CustomItem(Material.IRON_BOOTS, "&a&l史莱姆靴子", 0, new String[] { "&7已强化", "", "&a感觉..弹弹的..", "", "&9+ 跳跃", "&9- 掉落伤害" }, new String[] { "DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2" });
/* 185 */   public static ItemStack BOOTS_OF_THE_STOMPER = (ItemStack)new CustomArmor((ItemStack)new CustomItem(Material.LEATHER_BOOTS, "&b震地靴", 0, new String[] { "", "&9你所受到的掉落伤害", "&9会同时给予你附近的怪物/玩家", "", "&9- 掉落伤害" }), Color.AQUA);
/* 186 */   public static ItemStack HEAVY_METAL_HELMET = (ItemStack)new CustomItem(Material.IRON_HELMET, "&c重型头盔", 0, new String[] { "", "&9+ 力量", "&9- 速度" }, new String[] { "DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10" });
/* 187 */   public static ItemStack HEAVY_METAL_CHESTPLATE = (ItemStack)new CustomItem(Material.IRON_CHESTPLATE, "&c重型胸甲", 0, new String[] { "", "&9+ 力量", "&9- 速度" }, new String[] { "DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10" });
/* 188 */   public static ItemStack HEAVY_METAL_LEGGINGS = (ItemStack)new CustomItem(Material.IRON_LEGGINGS, "&c重型护腿", 0, new String[] { "", "&9+ 力量", "&9- 速度" }, new String[] { "DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10" });
/* 189 */   public static ItemStack HEAVY_METAL_BOOTS = (ItemStack)new CustomItem(Material.IRON_BOOTS, "&c重型靴子", 0, new String[] { "", "&9+ 力量", "&9- 速度" }, new String[] { "DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10" });
/*     */ 
/*     */   
/* 192 */   public static ItemStack MAGIC_LUMP_1 = (ItemStack)new CustomItem(Material.GOLD_NUGGET, "&6魔法结晶 &7- &eI", 0, new String[] { "", "&c品级: I" });
/* 193 */   public static ItemStack MAGIC_LUMP_2 = (ItemStack)new CustomItem(Material.GOLD_NUGGET, "&6魔法结晶 &7- &eII", 0, new String[] { "", "&c品级: II" });
/* 194 */   public static ItemStack MAGIC_LUMP_3 = (ItemStack)new CustomItem(Material.GOLD_NUGGET, "&6魔法结晶 &7- &eIII", 0, new String[] { "", "&c品级: III" });
/* 195 */   public static ItemStack ENDER_LUMP_1 = (ItemStack)new CustomItem(Material.GOLD_NUGGET, "&5末影结晶 &7- &eI", 0, new String[] { "", "&c品级: I" });
/* 196 */   public static ItemStack ENDER_LUMP_2 = (ItemStack)new CustomItem(Material.GOLD_NUGGET, "&5末影结晶 &7- &eII", 0, new String[] { "", "&c品级: II" });
/* 197 */   public static ItemStack ENDER_LUMP_3 = (ItemStack)new CustomItem(Material.GOLD_NUGGET, "&5末影结晶 &7- &eIII", 0, new String[] { "", "&c品级: III" });
/* 198 */   public static ItemStack MAGICAL_BOOK_COVER = (ItemStack)new CustomItem(Material.PAPER, "&6魔法书的封面", 0, new String[] { "", "&a用于包装各种魔法书" });
/* 199 */   public static ItemStack BASIC_CIRCUIT_BOARD = (ItemStack)new CustomItem(Material.ACTIVATOR_RAIL, "&b基础电路板", 0);
/* 200 */   public static ItemStack ADVANCED_CIRCUIT_BOARD = (ItemStack)new CustomItem(Material.POWERED_RAIL, "&b高级电路板", 0);
/* 201 */   public static ItemStack WHEAT_FLOUR = (ItemStack)new CustomItem(Material.SUGAR, "&r小麦粉", 0);
/* 202 */   public static ItemStack STEEL_PLATE = (ItemStack)new CustomItem(Material.PAPER, "&7&l钢板", 0);
/* 203 */   public static ItemStack COMPRESSED_CARBON = null;
/* 204 */   public static ItemStack BATTERY = null;
/* 205 */   public static ItemStack CARBON_CHUNK = null;
/* 206 */   public static ItemStack STEEL_THRUSTER = (ItemStack)new CustomItem(Material.BUCKET, "&7&l钢制推进器", 0);
/* 207 */   public static ItemStack POWER_CRYSTAL = null;
/* 208 */   public static ItemStack CHAIN = (ItemStack)new CustomItem(Material.STRING, "&b链", 0);
/* 209 */   public static ItemStack HOOK = (ItemStack)new CustomItem(Material.FLINT, "&b钩", 0);
/* 210 */   public static ItemStack SIFTED_ORE = (ItemStack)new CustomItem(Material.SULPHUR, "&6筛选矿", 0);
/* 211 */   public static ItemStack STONE_CHUNK = null;
/* 212 */   public static ItemStack LAVA_CRYSTAL = null;
/* 213 */   public static ItemStack SALT = (ItemStack)new CustomItem(Material.SUGAR, "&r盐", 0);
/* 214 */   public static ItemStack BUTTER = null;
/* 215 */   public static ItemStack CHEESE = null;
/* 216 */   public static ItemStack HEAVY_CREAM = (ItemStack)new CustomItem(Material.SNOW_BALL, "&r重奶油", 0);
/* 217 */   public static ItemStack CRUSHED_ORE = (ItemStack)new CustomItem(Material.SULPHUR, "&6碎矿", 0);
/* 218 */   public static ItemStack PULVERIZED_ORE = (ItemStack)new CustomItem(Material.SULPHUR, "&6粉状矿", 0);
/* 219 */   public static ItemStack PURE_ORE_CLUSTER = (ItemStack)new CustomItem(Material.SULPHUR, "&6纯矿群", 0);
/* 220 */   public static ItemStack TINY_URANIUM = null;
/* 221 */   public static ItemStack SMALL_URANIUM = null;
/* 222 */   public static ItemStack MAGNET = null;
/* 223 */   public static ItemStack NECROTIC_SKULL = (ItemStack)new CustomItem((new MaterialData(Material.SKULL_ITEM, (byte)1)).toItemStack(1), "&c坏死的颅骨");
/* 224 */   public static ItemStack ESSENCE_OF_AFTERLIFE = (ItemStack)new CustomItem(Material.SULPHUR, "&4基础再生材料", 0);
/* 225 */   public static ItemStack ELECTRO_MAGNET = null;
/* 226 */   public static ItemStack HEATING_COIL = null;
/* 227 */   public static ItemStack COOLING_UNIT = null;
/* 228 */   public static ItemStack ELECTRIC_MOTOR = null;
/* 229 */   public static ItemStack CARGO_MOTOR = null;
/* 230 */   public static ItemStack SCROLL_OF_DIMENSIONAL_TELEPOSITION = (ItemStack)new CustomItem(Material.PAPER, "&6坐标传送卷轴", 0, new String[] { "", "&c这个卷轴可以创造", "&c一个临时的传送门", "&c并将其附近的实体", "&c全部传送到另一个传送门出口", "&c并且所有被传送的实体都会反向", "", "&r换句话说: 所有实体都会转180度" });
/* 231 */   public static ItemStack TOME_OF_KNOWLEDGE_SHARING = (ItemStack)new CustomItem(Material.BOOK, "&6知识共享之书", 0, new String[] { "&7所有人: &bNone", "", "&e右键点击&7 将这本书绑定到你自己身上", "", "", "&e右键点击&7 获取所有", "&7来自所有人的所有研究" });
/* 232 */   public static ItemStack HARDENED_GLASS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)8), "&7硬化玻璃", new String[] { "", "&r可以抵御爆炸" });
/* 233 */   public static ItemStack WITHER_PROOF_OBSIDIAN = (ItemStack)new CustomItem(Material.OBSIDIAN, "&5强化黑曜石", 0, new String[] { "", "&r可以抵抗爆炸", "&r防御凋零的破坏" });
/* 234 */   public static ItemStack WITHER_PROOF_GLASS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)15), "&5抗凋零玻璃", new String[] { "", "&r可以抵抗爆炸", "&r防御凋零的破坏" });
/* 235 */   public static ItemStack REINFORCED_PLATE = (ItemStack)new CustomItem(Material.PAPER, "&7强化板", 0);
/* 236 */   public static ItemStack ANCIENT_PEDESTAL = (ItemStack)new CustomItem(Material.DISPENSER, "&d远古基座", 0, new String[] { "", "&5远古祭坛的组件" });
/* 237 */   public static ItemStack ANCIENT_ALTAR = (ItemStack)new CustomItem(Material.ENCHANTMENT_TABLE, "&d远古祭坛", 0, new String[] { "", "&5搭建祭坛后", "&5可以执行仪式制造魔法物品" });
/* 238 */   public static ItemStack DUCT_TAPE = null;
/*     */   
/* 240 */   public static ItemStack RAINBOW_WOOL = (ItemStack)new CustomItem(new MaterialData(Material.WOOL), "&5彩虹羊毛", new String[] { "", "&d如彩虹般多彩的羊毛!" });
/* 241 */   public static ItemStack RAINBOW_GLASS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS), "&5彩虹玻璃", new String[] { "", "&d如彩虹般多彩的玻璃!" });
/* 242 */   public static ItemStack RAINBOW_CLAY = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY), "&5彩虹粘土", new String[] { "", "&d如彩虹般多彩的粘土!" });
/* 243 */   public static ItemStack RAINBOW_GLASS_PANE = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE), "&5彩虹玻璃板", new String[] { "", "&d如彩虹般多彩的玻璃板!" });
/*     */   
/* 245 */   public static ItemStack RAINBOW_WOOL_XMAS = (ItemStack)new CustomItem(new MaterialData(Material.WOOL), "&5彩虹羊毛 &7(圣诞版)", new String[] { "", Christmas.color("< Christmas Edition >") });
/* 246 */   public static ItemStack RAINBOW_GLASS_XMAS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS), "&5彩虹玻璃 &7(圣诞版)", new String[] { "", Christmas.color("< Christmas Edition >") });
/* 247 */   public static ItemStack RAINBOW_CLAY_XMAS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY), "&5彩虹粘土 &7(圣诞版)", new String[] { "", Christmas.color("< Christmas Edition >") });
/* 248 */   public static ItemStack RAINBOW_GLASS_PANE_XMAS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE), "&5彩虹玻璃板 &7(圣诞版)", new String[] { "", Christmas.color("< Christmas Edition >") });
/*     */   
/* 250 */   public static ItemStack RAINBOW_WOOL_VALENTINE = (ItemStack)new CustomItem(new MaterialData(Material.WOOL), "&5彩虹羊毛 &7(情人节版)", new String[] { "", "&d< Valentine's Day Edition >" });
/* 251 */   public static ItemStack RAINBOW_GLASS_VALENTINE = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS), "&5彩虹玻璃 &7(情人节版)", new String[] { "", "&d< Valentine's Day Edition >" });
/* 252 */   public static ItemStack RAINBOW_CLAY_VALENTINE = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY), "&5彩虹粘土 &7(情人节版)", new String[] { "", "&d< Valentine's Day Edition >" });
/* 253 */   public static ItemStack RAINBOW_GLASS_PANE_VALENTINE = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE), "&5彩虹玻璃板 &7(情人节版)", new String[] { "", "&d< Valentine's Day Edition >" });
/*     */ 
/*     */   
/* 256 */   public static ItemStack COPPER_INGOT = (ItemStack)new CustomItem(Material.CLAY_BRICK, "&b铜锭", 0, new String[0]);
/* 257 */   public static ItemStack TIN_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b锡锭", 0, new String[0]);
/* 258 */   public static ItemStack SILVER_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b银锭", 0, new String[0]);
/* 259 */   public static ItemStack ALUMINUM_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b铝锭", 0, new String[0]);
/* 260 */   public static ItemStack LEAD_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b铅锭", 0, new String[0]);
/* 261 */   public static ItemStack ZINC_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b锌锭", 0, new String[0]);
/* 262 */   public static ItemStack MAGNESIUM_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b镁锭", 0, new String[0]);
/*     */ 
/*     */   
/* 265 */   public static ItemStack STEEL_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b钢锭", 0, new String[0]);
/*     */   
/* 267 */   public static ItemStack BRONZE_INGOT = (ItemStack)new CustomItem(Material.CLAY_BRICK, "&b青铜锭", 0, new String[0]);
/*     */   
/* 269 */   public static ItemStack DURALUMIN_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b硬铝锭", 0, new String[0]);
/*     */   
/* 271 */   public static ItemStack BILLON_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b铜锡合金锭", 0, new String[0]);
/*     */   
/* 273 */   public static ItemStack BRASS_INGOT = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&b黄铜锭", 0, new String[0]);
/*     */   
/* 275 */   public static ItemStack ALUMINUM_BRASS_INGOT = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&b铝黄铜锭", 0, new String[0]);
/*     */   
/* 277 */   public static ItemStack ALUMINUM_BRONZE_INGOT = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&b铝青铜锭", 0, new String[0]);
/*     */   
/* 279 */   public static ItemStack CORINTHIAN_BRONZE_INGOT = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&b科林斯青铜锭", 0, new String[0]);
/*     */   
/* 281 */   public static ItemStack SOLDER_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b焊材锭", 0, new String[0]);
/*     */   
/* 283 */   public static ItemStack DAMASCUS_STEEL_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b水纹钢锭", 0, new String[0]);
/*     */   
/* 285 */   public static ItemStack HARDENED_METAL_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b&l坚硬的金属", 0);
/*     */   
/* 287 */   public static ItemStack REINFORCED_ALLOY_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b&l强化合金锭", 0);
/*     */   
/* 289 */   public static ItemStack FERROSILICON = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b含硅铁", 0);
/*     */   
/* 291 */   public static ItemStack GILDED_IRON = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6&l镀金铁", 0);
/*     */   
/* 293 */   public static ItemStack REDSTONE_ALLOY = (ItemStack)new CustomItem(Material.CLAY_BRICK, "&c红石合金锭", 0);
/*     */   
/* 295 */   public static ItemStack NICKEL_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&b镍锭", 0);
/*     */   
/* 297 */   public static ItemStack COBALT_INGOT = (ItemStack)new CustomItem(Material.IRON_INGOT, "&9钴锭", 0);
/*     */ 
/*     */   
/* 300 */   public static ItemStack GOLD_4K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(4-K)", 0);
/* 301 */   public static ItemStack GOLD_6K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(6-K)", 0);
/* 302 */   public static ItemStack GOLD_8K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(8-K)", 0);
/* 303 */   public static ItemStack GOLD_10K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(10-K)", 0);
/* 304 */   public static ItemStack GOLD_12K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(12-K)", 0);
/* 305 */   public static ItemStack GOLD_14K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(14-K)", 0);
/* 306 */   public static ItemStack GOLD_16K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(16-K)", 0);
/* 307 */   public static ItemStack GOLD_18K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(18-K)", 0);
/* 308 */   public static ItemStack GOLD_20K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(20-K)", 0);
/* 309 */   public static ItemStack GOLD_22K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(22-K)", 0);
/* 310 */   public static ItemStack GOLD_24K = (ItemStack)new CustomItem(Material.GOLD_INGOT, "&6金锭 &7(24-K)", 0);
/*     */ 
/*     */   
/* 313 */   public static ItemStack IRON_DUST = (ItemStack)new CustomItem(Material.SULPHUR, "&6铁粉", 0);
/* 314 */   public static ItemStack GOLD_DUST = (ItemStack)new CustomItem(Material.GLOWSTONE_DUST, "&6金粉", 0);
/* 315 */   public static ItemStack TIN_DUST = (ItemStack)new CustomItem(Material.SUGAR, "&6锡粉", 0);
/* 316 */   public static ItemStack COPPER_DUST = (ItemStack)new CustomItem(Material.GLOWSTONE_DUST, "&6铜粉", 0);
/* 317 */   public static ItemStack SILVER_DUST = (ItemStack)new CustomItem(Material.SUGAR, "&6银粉", 0);
/* 318 */   public static ItemStack ALUMINUM_DUST = (ItemStack)new CustomItem(Material.SUGAR, "&6铝粉", 0);
/* 319 */   public static ItemStack LEAD_DUST = (ItemStack)new CustomItem(Material.SULPHUR, "&6铅粉", 0);
/* 320 */   public static ItemStack SULFATE = (ItemStack)new CustomItem(Material.GLOWSTONE_DUST, "&6硫粉", 0);
/* 321 */   public static ItemStack ZINC_DUST = (ItemStack)new CustomItem(Material.SUGAR, "&6锌粉", 0);
/* 322 */   public static ItemStack MAGNESIUM_DUST = (ItemStack)new CustomItem(Material.SUGAR, "&6镁", 0);
/* 323 */   public static ItemStack CARBON = null;
/* 324 */   public static ItemStack SILICON = (ItemStack)new CustomItem(Material.FIREWORK_CHARGE, "&6硅", 0);
/* 325 */   public static ItemStack GOLD_24K_BLOCK = (ItemStack)new CustomItem(Material.GOLD_BLOCK, "&r金块 &7(24-K)", 0);
/*     */ 
/*     */   
/* 328 */   public static ItemStack SYNTHETIC_DIAMOND = (ItemStack)new CustomItem(Material.DIAMOND, "&b人造钻石", 0);
/* 329 */   public static ItemStack SYNTHETIC_SAPPHIRE = (ItemStack)new CustomItem(new MaterialData(Material.INK_SACK, (byte)4), "&b人造刚玉", new String[0]);
/* 330 */   public static ItemStack SYNTHETIC_EMERALD = (ItemStack)new CustomItem(Material.EMERALD, "&b人造翡翠", 0);
/* 331 */   public static ItemStack CARBONADO = null;
/* 332 */   public static ItemStack RAW_CARBONADO = null;
/* 333 */   public static ItemStack URANIUM = null;
/* 334 */   public static ItemStack NEPTUNIUM = null;
/* 335 */   public static ItemStack PLUTONIUM = null;
/* 336 */   public static ItemStack BOOSTED_URANIUM = null;
/*     */ 
/*     */   
/* 339 */   public static ItemStack TALISMAN = (ItemStack)new CustomItem(Material.EMERALD, "&6普通的护身符", 0);
/* 340 */   public static ItemStack TALISMAN_ANVIL = (ItemStack)new CustomItem(Material.EMERALD, "&a铁砧护身符", 0, new String[] { "", "&r铁匠打造的护身符", "&r1能保护工具不会损毁, 但生效之后", "&r护身符会被消耗", "", "&4&l警告:", "&4这个护身符对于", "&4强大的工具无效", "&4因为他们过于复杂了" });
/* 341 */   public static ItemStack TALISMAN_MINER = (ItemStack)new CustomItem(Material.EMERALD, "&a矿工护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有几率获得", "&r20%额外的", "&r你所挖取到的矿物" });
/* 342 */   public static ItemStack TALISMAN_HUNTER = (ItemStack)new CustomItem(Material.EMERALD, "&a猎手护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有几率获得", "&r20%额外的", "&r你所杀死怪物的掉落物" });
/* 343 */   public static ItemStack TALISMAN_LAVA = (ItemStack)new CustomItem(Material.EMERALD, "&a岩浆行走者护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将获得抗火能力", "&r当你落入岩浆时", "&r你会立即获得抗火效果", "&r但护身符会被立即消耗" });
/* 344 */   public static ItemStack TALISMAN_WATER = (ItemStack)new CustomItem(Material.EMERALD, "&a潜水护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有几率获得", "&r在水下呼吸的能力", "&r当你在水中即将溺水时", "&r你会获得水下呼吸", "&r但护身符会被消耗" });
/* 345 */   public static ItemStack TALISMAN_ANGEL = (ItemStack)new CustomItem(Material.EMERALD, "&a天使护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有几率获得", "&r75%几率保护你", "&r免受掉落伤害" });
/* 346 */   public static ItemStack TALISMAN_FIRE = (ItemStack)new CustomItem(Material.EMERALD, "&a火神护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有几率获得", "&r抵抗火焰的能力", "&r当你着火时", "&r护身符也会被消耗" });
/* 347 */   public static ItemStack TALISMAN_MAGICIAN = (ItemStack)new CustomItem(Material.EMERALD, "&a魔术师护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有", "&r80%额外几率获得额外的附魔", "&r有时甚至能获得额外的附魔" });
/* 348 */   public static ItemStack TALISMAN_TRAVELLER = (ItemStack)new CustomItem(Material.EMERALD, "&a旅行者护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有", "&r60%的几率在奔跑时", "&r获得额外的加速效果" });
/* 349 */   public static ItemStack TALISMAN_WARRIOR = (ItemStack)new CustomItem(Material.EMERALD, "&a战士护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将会在", "&r受到攻击时获得力量3", "&r但护身符会被消耗" });
/* 350 */   public static ItemStack TALISMAN_KNIGHT = (ItemStack)new CustomItem(Material.EMERALD, "&a骑士护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将会在", "&r受到攻击时", "&r有30%的几率获得5秒生命恢复", "&r但护身符会被消耗" });
/* 351 */   public static ItemStack TALISMAN_WHIRLWIND = (ItemStack)new CustomItem(Material.EMERALD, "&a风之护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你将有", "&r60%几率免受弓箭伤害" });
/* 352 */   public static ItemStack TALISMAN_WIZARD = (ItemStack)new CustomItem(Material.EMERALD, "&a魔法师护身符", 0, new String[] { "", "&r当你背包中拥有这个护身符时", "&r你会获得增强的时运效果", "&r但需要注意的是", "&r护身符也会降低", "&r你物品上其他的附魔等级" });
/*     */ 
/*     */   
/* 355 */   public static ItemStack STAFF_ELEMENTAL = (ItemStack)new CustomItem(Material.STICK, "&6元素之杖", 0);
/* 356 */   public static ItemStack STAFF_WIND = (ItemStack)new CustomItem(Material.STICK, "&6元素之杖 &7- &b风", 0, new String[] { "", "&7属性: &b风", "", "&7&e右键点击&7 将你吹向天际" }, new String[] { "LUCK-1" });
/* 357 */   public static ItemStack STAFF_FIRE = (ItemStack)new CustomItem(Material.STICK, "&6元素之杖 &7- &c火", 0, new String[] { "", "&7属性: &c火" }, new String[] { "FIRE_ASPECT-5" });
/* 358 */   public static ItemStack STAFF_WATER = (ItemStack)new CustomItem(Material.STICK, "&6元素之杖 &7- &1水", 0, new String[] { "", "&7属性: &1水", "", "&7&e右键点击&7 熄灭你身上的火焰" }, new String[] { "WATER_WORKER-1" });
/*     */ 
/*     */   
/* 361 */   public static ItemStack GRIND_STONE = (ItemStack)new CustomItem(Material.DISPENSER, "&b磨石", 0, new String[] { "", "&a磨碎物品以便加工使用" });
/* 362 */   public static ItemStack ARMOR_FORGE = (ItemStack)new CustomItem(Material.ANVIL, "&6铠甲铸造台", 0, new String[] { "", "&a现在你可以制作更为强大的铠甲了" });
/* 363 */   public static ItemStack SMELTERY = (ItemStack)new CustomItem(Material.FURNACE, "&6冶炼厂", 0, new String[] { "", "&a熔炼金属的高温熔炉" });
/* 364 */   public static ItemStack IGNITION_CHAMBER = (ItemStack)new CustomItem(new ItemStack(Material.HOPPER), "&4自动点火器", new String[] { "&r自动为冶炼厂点火.", "&r需要打火石", "&r必须放置在冶炼厂附近" });
/* 365 */   public static ItemStack ORE_CRUSHER = (ItemStack)new CustomItem(Material.DISPENSER, "&b碎矿机", 0, new String[] { "", "&a加工处理矿石, 能获得双倍矿物" });
/* 366 */   public static ItemStack COMPRESSOR = (ItemStack)new CustomItem(Material.PISTON_BASE, "&b压缩机", 0, new String[] { "", "&a压缩物品" });
/* 367 */   public static ItemStack PRESSURE_CHAMBER = (ItemStack)new CustomItem(Material.GLASS, "&b加压室", 0, new String[] { "", "&a高度压缩物品" });
/* 368 */   public static ItemStack MAGIC_WORKBENCH = (ItemStack)new CustomItem(Material.WORKBENCH, "&6魔法合成台", 0, new String[] { "&a向物品中注入&d魔法能量" });
/* 369 */   public static ItemStack ORE_WASHER = (ItemStack)new CustomItem(Material.CAULDRON_ITEM, "&6洗矿机", 0, new String[] { "", "&a清洗加工过的矿产", "&a并给你些小石块" });
/* 370 */   public static ItemStack SAW_MILL = (ItemStack)new CustomItem(Material.IRON_FENCE, "&6锯木机", 0, new String[] { "", "&a高效的锯木机使你获得双倍的木板" });
/* 371 */   public static ItemStack COMPOSTER = (ItemStack)new CustomItem(Material.CAULDRON_ITEM, "&a堆肥机", 0, new String[] { "", "&a可以将各种原料转化为其他物品..." });
/* 372 */   public static ItemStack ENHANCED_CRAFTING_TABLE = (ItemStack)new CustomItem(Material.WORKBENCH, "&e强化合成台", 0, new String[] { "", "&a普通的合成台", "&a无法承受强化合成台制作时产生的巨大能量..." });
/* 373 */   public static ItemStack CRUCIBLE = (ItemStack)new CustomItem(Material.CAULDRON_ITEM, "&c坩埚", 0, new String[] { "", "&a可以将物品制成流体" });
/* 374 */   public static ItemStack JUICER = (ItemStack)new CustomItem(Material.GLASS_BOTTLE, "&a榨汁机", 0, new String[] { "", "&a有事没事, 来几杯果汁如何?" });
/*     */   
/* 376 */   public static ItemStack SOLAR_PANEL = (ItemStack)new CustomItem(Material.DAYLIGHT_DETECTOR, "&b太阳能板", 0, new String[] { "", "&a将日光转化为能量" });
/* 377 */   public static ItemStack SOLAR_ARRAY = (ItemStack)new CustomItem(Material.DAYLIGHT_DETECTOR, "&b太阳能电池", 0, new String[] { "", "&a将日光转化为能量" });
/* 378 */   public static ItemStack DIGITAL_MINER = (ItemStack)new CustomItem(Material.IRON_PICKAXE, "&b采矿机", 0, new String[] { "", "&a采矿从未如此简单!" });
/* 379 */   public static ItemStack ADVANCED_DIGITAL_MINER = (ItemStack)new CustomItem(Material.DIAMOND_PICKAXE, "&6高级采矿机", 0, new String[] { "", "&a采矿从未如此简单", "&a现在连碎矿都不用自己动手了!" });
/* 380 */   public static ItemStack AUTOMATED_PANNING_MACHINE = (ItemStack)new CustomItem(Material.BOWL, "&a自动筛选机", 0, new String[] { "", "&a自动淘洗放入的矿物" });
/*     */   
/* 382 */   public static ItemStack HOLOGRAM_PROJECTOR = (ItemStack)new CustomItem(new MaterialData(Material.STEP, (byte)7), "&b全息显示器", new String[] { "", "&r显示一个可修改的全息文字" });
/*     */ 
/*     */   
/* 385 */   public static ItemStack ENHANCED_FURNACE = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eI", 0, new String[] { "", "&7熔炼速度: &e1x", "&7燃烧效率: &e1x", "&7产出倍数: &e1x" });
/* 386 */   public static ItemStack ENHANCED_FURNACE_2 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eII", 0, new String[] { "", "&7熔炼速度: &e2x", "&7燃烧效率: &e1x", "&7产出倍数: &e1x" });
/* 387 */   public static ItemStack ENHANCED_FURNACE_3 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eIII", 0, new String[] { "", "&7熔炼速度: &e2x", "&7燃烧效率: &e2x", "&7产出倍数: &e1x" });
/* 388 */   public static ItemStack ENHANCED_FURNACE_4 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eIV", 0, new String[] { "", "&7熔炼速度: &e3x", "&7燃烧效率: &e2x", "&7产出倍数: &e1x" });
/* 389 */   public static ItemStack ENHANCED_FURNACE_5 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eV", 0, new String[] { "", "&7熔炼速度: &e3x", "&7燃烧效率: &e2x", "&7产出倍数: &e2x" });
/* 390 */   public static ItemStack ENHANCED_FURNACE_6 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eVI", 0, new String[] { "", "&7熔炼速度: &e3x", "&7燃烧效率: &e3x", "&7产出倍数: &e2x" });
/* 391 */   public static ItemStack ENHANCED_FURNACE_7 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eVII", 0, new String[] { "", "&7熔炼速度: &e4x", "&7燃烧效率: &e3x", "&7产出倍数: &e2x" });
/* 392 */   public static ItemStack ENHANCED_FURNACE_8 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eVIII", 0, new String[] { "", "&7熔炼速度: &e4x", "&7燃烧效率: &e4x", "&7产出倍数: &e2x" });
/* 393 */   public static ItemStack ENHANCED_FURNACE_9 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eIX", 0, new String[] { "", "&7熔炼速度: &e5x", "&7燃烧效率: &e4x", "&7产出倍数: &e2x" });
/* 394 */   public static ItemStack ENHANCED_FURNACE_10 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eX", 0, new String[] { "", "&7熔炼速度: &e5x", "&7燃烧效率: &e5x", "&7产出倍数: &e2x" });
/* 395 */   public static ItemStack ENHANCED_FURNACE_11 = (ItemStack)new CustomItem(Material.FURNACE, "&7强化熔炉 - &eXI", 0, new String[] { "", "&7熔炼速度: &e5x", "&7燃烧效率: &e5x", "&7产出倍数: &e3x" });
/* 396 */   public static ItemStack REINFORCED_FURNACE = (ItemStack)new CustomItem(Material.FURNACE, "&7高级强化熔炉", 0, new String[] { "", "&7熔炼速度: &e10x", "&7燃烧效率: &e10x", "&7产出倍数: &e3x" });
/* 397 */   public static ItemStack CARBONADO_EDGED_FURNACE = (ItemStack)new CustomItem(Material.FURNACE, "&7黑钻炉", 0, new String[] { "", "&7熔炼速度: &e20x", "&7燃烧效率: &e10x", "&7产出倍数: &e3x" });
/*     */   
/* 399 */   public static ItemStack BLOCK_PLACER = (ItemStack)new CustomItem(Material.DISPENSER, "&a方块放置器", 0, new String[] { "", "&r所有在此发射器机器内的方块", "&r都会被自动放置" });
/*     */ 
/*     */   
/* 402 */   public static ItemStack SOULBOUND_SWORD = (ItemStack)new CustomItem(Material.DIAMOND_SWORD, "&c灵魂绑定剑", 0);
/* 403 */   public static ItemStack SOULBOUND_BOW = (ItemStack)new CustomItem(Material.BOW, "&c灵魂绑定弓", 0);
/* 404 */   public static ItemStack SOULBOUND_PICKAXE = (ItemStack)new CustomItem(Material.DIAMOND_PICKAXE, "&c灵魂绑定镐", 0);
/* 405 */   public static ItemStack SOULBOUND_AXE = (ItemStack)new CustomItem(Material.DIAMOND_AXE, "&c灵魂绑定斧", 0);
/* 406 */   public static ItemStack SOULBOUND_SHOVEL = (ItemStack)new CustomItem(Material.DIAMOND_SPADE, "&c灵魂绑定铲", 0);
/* 407 */   public static ItemStack SOULBOUND_HOE = (ItemStack)new CustomItem(Material.DIAMOND_HOE, "&c灵魂绑定锄", 0);
/*     */   
/* 409 */   public static ItemStack SOULBOUND_HELMET = (ItemStack)new CustomItem(Material.DIAMOND_HELMET, "&c灵魂绑定头盔", 0);
/* 410 */   public static ItemStack SOULBOUND_CHESTPLATE = (ItemStack)new CustomItem(Material.DIAMOND_CHESTPLATE, "&c灵魂绑定胸甲", 0);
/* 411 */   public static ItemStack SOULBOUND_LEGGINGS = (ItemStack)new CustomItem(Material.DIAMOND_LEGGINGS, "&c灵魂绑定护腿", 0);
/* 412 */   public static ItemStack SOULBOUND_BOOTS = (ItemStack)new CustomItem(Material.DIAMOND_BOOTS, "&c灵魂绑定靴", 0);
/*     */ 
/*     */   
/* 415 */   public static ItemStack BLANK_RUNE = null;
/* 416 */   public static ItemStack RUNE_AIR = null;
/* 417 */   public static ItemStack RUNE_WATER = null;
/* 418 */   public static ItemStack RUNE_FIRE = null;
/* 419 */   public static ItemStack RUNE_EARTH = null;
/* 420 */   public static ItemStack RUNE_ENDER = null;
/* 421 */   public static ItemStack RUNE_RAINBOW = null;
/*     */   
/*     */   static {
/* 424 */     ItemStack itemB = new ItemStack(Material.FIREWORK_CHARGE);
/* 425 */     FireworkEffectMeta imB = (FireworkEffectMeta)itemB.getItemMeta();
/* 426 */     imB.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).with(FireworkEffect.Type.BURST).withColor(Color.BLACK).build());
/* 427 */     imB.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8空白符文"));
/* 428 */     itemB.setItemMeta((ItemMeta)imB);
/* 429 */     BLANK_RUNE = itemB;
/*     */     
/* 431 */     ItemStack itemA = new ItemStack(Material.FIREWORK_CHARGE);
/* 432 */     FireworkEffectMeta imA = (FireworkEffectMeta)itemA.getItemMeta();
/* 433 */     imA.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.AQUA).build());
/* 434 */     imA.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7远古符文 &8&l[&b&l空气&8&l]"));
/* 435 */     itemA.setItemMeta((ItemMeta)imA);
/* 436 */     RUNE_AIR = itemA;
/*     */     
/* 438 */     ItemStack itemW = new ItemStack(Material.FIREWORK_CHARGE);
/* 439 */     FireworkEffectMeta imW = (FireworkEffectMeta)itemW.getItemMeta();
/* 440 */     imW.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.BLUE).build());
/* 441 */     imW.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7远古符文 &8&l[&1&l水&8&l]"));
/* 442 */     itemW.setItemMeta((ItemMeta)imW);
/* 443 */     RUNE_WATER = itemW;
/*     */     
/* 445 */     ItemStack itemF = new ItemStack(Material.FIREWORK_CHARGE);
/* 446 */     FireworkEffectMeta imF = (FireworkEffectMeta)itemF.getItemMeta();
/* 447 */     imF.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.RED).build());
/* 448 */     imF.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7远古符文 &8&l[&4&l火&8&l]"));
/* 449 */     itemF.setItemMeta((ItemMeta)imF);
/* 450 */     RUNE_FIRE = itemF;
/*     */     
/* 452 */     ItemStack itemE = new ItemStack(Material.FIREWORK_CHARGE);
/* 453 */     FireworkEffectMeta imE = (FireworkEffectMeta)itemE.getItemMeta();
/* 454 */     imE.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.ORANGE).build());
/* 455 */     imE.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7远古符文 &8&l[&c&l地&8&l]"));
/* 456 */     itemE.setItemMeta((ItemMeta)imE);
/* 457 */     RUNE_EARTH = itemE;
/*     */     
/* 459 */     ItemStack itemN = new ItemStack(Material.FIREWORK_CHARGE);
/* 460 */     FireworkEffectMeta imN = (FireworkEffectMeta)itemN.getItemMeta();
/* 461 */     imN.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.PURPLE).build());
/* 462 */     imN.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7远古符文 &8&l[&5&l末影&8&l]"));
/* 463 */     itemN.setItemMeta((ItemMeta)imN);
/* 464 */     RUNE_ENDER = itemN;
/*     */     
/* 466 */     ItemStack itemR = new ItemStack(Material.FIREWORK_CHARGE);
/* 467 */     FireworkEffectMeta imR = (FireworkEffectMeta)itemR.getItemMeta();
/* 468 */     imR.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.PURPLE).build());
/* 469 */     imR.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7远古符文 &8&l[&d&l彩虹&8&l]"));
/* 470 */     itemR.setItemMeta((ItemMeta)imR);
/* 471 */     RUNE_RAINBOW = itemR;
/*     */ 
/*     */ 
/*     */     
/* 475 */     SOLAR_GENERATOR = (ItemStack)new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&b太阳能发电机", new String[] { "", "&e基础发电机", "&8⇨ &e⚡ &70 J 缓存", "&8⇨ &e⚡ &74 J/s" });
/* 476 */     SOLAR_GENERATOR_2 = (ItemStack)new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&c高级太阳能发电机", new String[] { "", "&a中级发电机", "&8⇨ &e⚡ &70 J 缓存", "&8⇨ &e⚡ &716 J/s" });
/* 477 */     SOLAR_GENERATOR_3 = (ItemStack)new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&4黑钻太阳能发电机", new String[] { "", "&4终极发电机", "&8⇨ &e⚡ &70 J 缓存", "&8⇨ &e⚡ &764 J/s" });
/* 478 */     SOLAR_GENERATOR_4 = (ItemStack)new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&e电辅型太阳能发电机", new String[] { "", "&9可在夜晚发电", "", "&4终极发电机", "&8⇨ &e⚡ &70 J 缓存", "&8⇨ &e⚡ &7256 J/s (白天)", "&8⇨ &e⚡ &7128 J/s (夜晚)" });
/*     */     
/* 480 */     COAL_GENERATOR = null;
/* 481 */     LAVA_GENERATOR = null;
/*     */     
/* 483 */     ELECTRIC_FURNACE = (ItemStack)new CustomItem(new ItemStack(Material.FURNACE), "&c电力熔炉", new String[] { "", "&e基础机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &74 J/s" });
/* 484 */     ELECTRIC_FURNACE_2 = (ItemStack)new CustomItem(new ItemStack(Material.FURNACE), "&c电力熔炉 &7- &eII", new String[] { "", "&a中级机器", "&8⇨ &7速度: 2x", "&8⇨ &e⚡ &76 J/s" });
/* 485 */     ELECTRIC_FURNACE_3 = (ItemStack)new CustomItem(new ItemStack(Material.FURNACE), "&c电力熔炉 &7- &eIII", new String[] { "", "&a中级机器", "&8⇨ &7速度: 4x", "&8⇨ &e⚡ &710 J/s" });
/*     */     
/* 487 */     ELECTRIC_ORE_GRINDER = (ItemStack)new CustomItem(new ItemStack(Material.DROPPER), "&c电力碎矿机", new String[] { "", "&r使用电力实现矿石粉碎和磨石粉碎的功能", "", "&6高级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &712 J/s" });
/* 488 */     ELECTRIC_ORE_GRINDER_2 = (ItemStack)new CustomItem(new ItemStack(Material.DROPPER), "&c电力碎矿机 &7(&eII&7)", new String[] { "", "&r使用电力实现矿石粉碎和磨石粉碎的功能", "", "&4终极机器", "&8⇨ &7速度: 4x", "&8⇨ &e⚡ &730 J/s" });
/* 489 */     ELECTRIC_INGOT_PULVERIZER = (ItemStack)new CustomItem(new ItemStack(Material.DROPPER), "&c电力金属锭粉碎机", new String[] { "", "&r将金属锭粉碎成金属粉", "", "&a中级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &714 J/s" });
/* 490 */     AUTO_ENCHANTER = (ItemStack)new CustomItem(new ItemStack(Material.ENCHANTMENT_TABLE), "&5自动附魔机", new String[] { "", "&a中级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &718 J/s" });
/* 491 */     AUTO_DISENCHANTER = (ItemStack)new CustomItem(new ItemStack(Material.ENCHANTMENT_TABLE), "&5自动去附魔机", new String[] { "", "&a中级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &718 J/s" });
/* 492 */     AUTO_ANVIL = (ItemStack)new CustomItem(new ItemStack(Material.IRON_BLOCK), "&7自动型铁砧", new String[] { "", "&6高级机器", "&8⇨ &77修复率: 10%", "&8⇨ &e⚡ &724 J/s" });
/* 493 */     AUTO_ANVIL_2 = (ItemStack)new CustomItem(new ItemStack(Material.IRON_BLOCK), "&7自动型铁砧 Mk.II", new String[] { "", "&4终极机器", "&8⇨ &7修复率: 25%", "&8⇨ &e⚡ &732 J/s" });
/*     */     
/* 495 */     BIO_REACTOR = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)5), "&2生化反应器", new String[] { "", "&6发电机组", "&8⇨ &e⚡ &7128 J 缓存", "&8⇨ &e⚡ &78 J/s" });
/* 496 */     MULTIMETER = (ItemStack)new CustomItem(new MaterialData(Material.WATCH), "&e万用电表", new String[] { "", "&r用于检测机器的电气信息", "&r右键机器使用" });
/* 497 */     SMALL_CAPACITOR = null; MEDIUM_CAPACITOR = null; BIG_CAPACITOR = null; LARGE_CAPACITOR = null; CARBONADO_EDGED_CAPACITOR = null;
/*     */ 
/*     */     
/* 500 */     PROGRAMMABLE_ANDROID = null;
/* 501 */     PROGRAMMABLE_ANDROID_MINER = null;
/* 502 */     PROGRAMMABLE_ANDROID_BUTCHER = null;
/* 503 */     PROGRAMMABLE_ANDROID_FARMER = null;
/* 504 */     PROGRAMMABLE_ANDROID_WOODCUTTER = null;
/* 505 */     PROGRAMMABLE_ANDROID_FISHERMAN = null;
/*     */     
/* 507 */     PROGRAMMABLE_ANDROID_2 = null;
/* 508 */     PROGRAMMABLE_ANDROID_2_FISHERMAN = null;
/* 509 */     PROGRAMMABLE_ANDROID_2_FARMER = null;
/* 510 */     PROGRAMMABLE_ANDROID_2_BUTCHER = null;
/*     */     
/* 512 */     PROGRAMMABLE_ANDROID_3 = null;
/* 513 */     PROGRAMMABLE_ANDROID_3_FISHERMAN = null;
/* 514 */     PROGRAMMABLE_ANDROID_3_BUTCHER = null;
/*     */ 
/*     */     
/* 517 */     GPS_TRANSMITTER = null;
/* 518 */     GPS_TRANSMITTER_2 = null;
/* 519 */     GPS_TRANSMITTER_3 = null;
/* 520 */     GPS_TRANSMITTER_4 = null;
/*     */     
/* 522 */     GPS_CONTROL_PANEL = null;
/* 523 */     GPS_MARKER_TOOL = (ItemStack)new CustomItem(new MaterialData(Material.REDSTONE_TORCH_ON), "&bGPS 标记工具", new String[] { "", "&r在你放置标记工具的位置", "&r设置一个路径点" });
/* 524 */     GPS_EMERGENCY_TRANSMITTER = null;
/* 525 */     GPS_GEO_SCANNER = null;
/*     */     
/* 527 */     ANDROID_INTERFACE_FUEL = (ItemStack)new CustomItem(new ItemStack(Material.DISPENSER), "&7机器人接口 &c(燃料)", new String[] { "", "&r存储在此接口中的物品", "&r会在机器人脚本发出指令时", "&r被机器人自动抽取到燃料槽中" });
/* 528 */     ANDROID_INTERFACE_ITEMS = (ItemStack)new CustomItem(new ItemStack(Material.DISPENSER), "&7机器人接口 &9(物品)", new String[] { "", "&r存储在机器人中的物品", "&r会在机器人脚本发出指令时", "&r被机器人输入这个接口" });
/*     */     
/* 530 */     BUCKET_OF_OIL = null;
/* 531 */     BUCKET_OF_FUEL = null;
/* 532 */     OIL_PUMP = null;
/*     */     
/* 534 */     REFINERY = (ItemStack)new CustomItem(new ItemStack(Material.PISTON_BASE), "&c石油精炼器", new String[] { "", "&r精炼石油生产液体燃料" });
/* 535 */     COMBUSTION_REACTOR = null;
/* 536 */     ANDROID_MEMORY_CORE = null;
/*     */     
/* 538 */     GPS_TELEPORTER_PYLON = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)10), "&5GPS 传送塔", new String[] { "", "&7传送器组件" });
/* 539 */     GPS_TELEPORTATION_MATRIX = (ItemStack)new CustomItem(new MaterialData(Material.IRON_BLOCK), "&bGPS 传送矩阵", new String[] { "", "&r这是传送器的主要组件", "&r通过使用传送矩阵", "&r你可以选择任意你自己已设置的GPS路径点", "&r进行传送." });
/* 540 */     GPS_ACTIVATION_DEVICE_SHARED = (ItemStack)new CustomItem(new MaterialData(Material.STONE_PLATE), "&rGPS 激活装置 &3(分享型)", new String[] { "", "&r将此装置放在传送矩阵之上", "&r站在此激活装置之上即可", "&r激活传送阵列" });
/* 541 */     GPS_ACTIVATION_DEVICE_PERSONAL = (ItemStack)new CustomItem(new MaterialData(Material.STONE_PLATE), "&rGPS 激活装置 &a(个人型)", new String[] { "", "&r将此装置放在传送矩阵之上", "&r站在此激活装置之上即可", "&r激活传送阵列", "", "&r个人型仅供", "&r放置其的人使用" });
/*     */     
/* 543 */     ELEVATOR = (ItemStack)new CustomItem(new MaterialData(Material.STONE_PLATE), "&b电梯板", new String[] { "", "&r将电梯板放置在每一楼层", "&r然后你就可以利用电梯板在他们之间快速移动了.", "", "&e右键点击这个方块 &7来命名它" });
/*     */     
/* 545 */     INFUSED_HOPPER = (ItemStack)new CustomItem(new MaterialData(Material.HOPPER), "&5吸力漏斗", new String[] { "", "&r以其放置的位置为中心", "&r吸力漏斗会自动吸取 7x7x7 范围内的掉落物." });
/*     */     
/* 547 */     PLASTIC_SHEET = (ItemStack)new CustomItem(new MaterialData(Material.PAPER), "&r塑料板", new String[0]);
/* 548 */     HEATED_PRESSURE_CHAMBER = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)8), "&c热压力室", new String[] { "", "&4终极机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &710 J/s" });
/* 549 */     HEATED_PRESSURE_CHAMBER_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)8), "&c热压力室 &7- &eII", new String[] { "", "&4终极机器", "&8⇨ &7速度: 5x", "&8⇨ &e⚡ &744 J/s" });
/*     */     
/* 551 */     ELECTRIC_SMELTERY = (ItemStack)new CustomItem(new MaterialData(Material.DROPPER), "&c电力熔炼厂", new String[] { "", "&4只能用于烧制强化锭, 不能将普通矿物粉制成锭", "", "&4终极机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &720 J/s" });
/* 552 */     ELECTRIC_SMELTERY_2 = (ItemStack)new CustomItem(new MaterialData(Material.DROPPER), "&c电力熔炼厂 &7- &eII", new String[] { "", "&4只能用于烧制强化锭, 不能将普通矿物粉制成锭", "", "&4终极机器", "&8⇨ &7速度: 3x", "&8⇨ &e⚡ &740 J/s" });
/*     */     
/* 554 */     ELECTRIFIED_CRUCIBLE = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&c电力坩埚", new String[] { "", "&4终极机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &748 J/s" });
/* 555 */     ELECTRIFIED_CRUCIBLE_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&c电力坩埚 &7- &eII", new String[] { "", "&4终极机器", "&8⇨ &7速度: 2x", "&8⇨ &e⚡ &780 J/s" });
/* 556 */     ELECTRIFIED_CRUCIBLE_3 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&c电力坩埚 &7- &eIII", new String[] { "", "&4终极机器", "&8⇨ &7速度: 4x", "&8⇨ &e⚡ &7120 J/s" });
/*     */     
/* 558 */     CARBON_PRESS = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)15), "&c碳压缩机", new String[] { "", "&4终极机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &720 J/s" });
/* 559 */     CARBON_PRESS_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)15), "&c碳压缩机 &7- &eII", new String[] { "", "&4终极机器", "&8⇨ &7速度: 3x", "&8⇨ &e⚡ &750 J/s" });
/* 560 */     CARBON_PRESS_3 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)15), "&c碳压缩机 &7- &eIII", new String[] { "", "&4终极机器", "&8⇨ &7速度: 15x", "&8⇨ &e⚡ &7180 J/s" });
/*     */     
/* 562 */     BLISTERING_INGOT = (ItemStack)new CustomItem(new MaterialData(Material.GOLD_INGOT), "&6发泡锭 &7(33%)", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/* 563 */     BLISTERING_INGOT_2 = (ItemStack)new CustomItem(new MaterialData(Material.GOLD_INGOT), "&6发泡锭 &7(66%)", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/* 564 */     BLISTERING_INGOT_3 = (ItemStack)new CustomItem(new MaterialData(Material.GOLD_INGOT), "&6发泡锭", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/*     */     
/* 566 */     ENERGY_REGULATOR = null;
/* 567 */     DEBUG_FISH = (ItemStack)new CustomItem(new MaterialData(Material.RAW_FISH), "&3这鱼多少一斤?", new String[] { "", "&e右键点击 &r查看任意方块数据信息", "&e左键点击 &r破坏这个方块", "&eShift + 左键点击 &r消除任意方块数据信息", "&eShift + 右键点击 &r放置一个 Placeholder 方块" });
/*     */ 
/*     */     
/* 570 */     NETHER_ICE = null;
/* 571 */     ENRICHED_NETHER_ICE = null;
/* 572 */     NETHER_ICE_COOLANT_CELL = null;
/* 573 */     NETHER_DRILL = (ItemStack)new CustomItem((ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&4下界矿钻", new String[] { "", "&r用于挖掘下界玄冰", "", "&4终极机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &7102 J/s", "", "&c&l! &c仅能在地狱使用!", "&c&l! &c使用前请先扫描想使用的区块" }));
/*     */ 
/*     */     
/* 576 */     CARGO_MANAGER = null;
/* 577 */     CARGO_NODE = null;
/* 578 */     CARGO_INPUT = null;
/* 579 */     CARGO_OUTPUT = null;
/* 580 */     CARGO_OUTPUT_ADVANCED = null;
/*     */     
/* 582 */     AUTO_BREEDER = null;
/*     */     
/* 584 */     ORGANIC_FOOD = null;
/* 585 */     ORGANIC_FOOD2 = null;
/* 586 */     ORGANIC_FOOD3 = null;
/* 587 */     ORGANIC_FOOD4 = null;
/* 588 */     ORGANIC_FOOD5 = null;
/* 589 */     ORGANIC_FOOD6 = null;
/* 590 */     ORGANIC_FOOD7 = null;
/* 591 */     ORGANIC_FOOD8 = null;
/*     */     
/* 593 */     FERTILIZER = null;
/* 594 */     FERTILIZER2 = null;
/* 595 */     FERTILIZER3 = null;
/* 596 */     FERTILIZER4 = null;
/* 597 */     FERTILIZER5 = null;
/* 598 */     FERTILIZER6 = null;
/* 599 */     FERTILIZER7 = null;
/* 600 */     FERTILIZER8 = null;
/*     */     
/* 602 */     ANIMAL_GROWTH_ACCELERATOR = null;
/* 603 */     CROP_GROWTH_ACCELERATOR = null;
/* 604 */     CROP_GROWTH_ACCELERATOR_2 = null;
/*     */     
/* 606 */     FOOD_FABRICATOR = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)13), "&c食品加工机", new String[] { "", "&r生产 &a有机食品", "", "&6高级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &714 J/s" });
/* 607 */     FOOD_FABRICATOR_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)13), "&c食品加工机 &7(&eII&7)", new String[] { "", "&r生产 &a有机食品", "", "&4终极机器", "&8⇨ &7速度: 6x", "&8⇨ &e⚡ &7512 J 缓存", "&8⇨ &e⚡ &748 J/s" });
/*     */     
/* 609 */     FOOD_COMPOSTER = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)13), "&c食品堆肥机", new String[] { "", "&r生产 &a有机肥料", "", "&6高级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &716 J/s" });
/* 610 */     FOOD_COMPOSTER_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)13), "&c食品堆肥机 &7(&eII&7)", new String[] { "", "&r生产 &a有机肥料", "", "&4终极机器", "&8⇨ &7速度: 10x", "&8⇨ &e⚡ &7512 J 缓存", "&8⇨ &e⚡ &752 J/s" });
/*     */     
/* 612 */     XP_COLLECTOR = null;
/* 613 */     REACTOR_COOLANT_CELL = null;
/*     */     
/* 615 */     NUCLEAR_REACTOR = null;
/* 616 */     NETHERSTAR_REACTOR = null;
/* 617 */     REACTOR_ACCESS_PORT = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)9), "&2反应堆交互接口", new String[] { "", "&r允许你直接或使用运输系统", "&r与反应堆进行交互", "&r具有缓存功能", "", "&8⇨ &e反应堆上 &a必须放置 &e3个方块" });
/*     */     
/* 619 */     FREEZER = null;
/* 620 */     FREEZER_2 = null;
/*     */     
/* 622 */     ELECTRIC_GOLD_PAN = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)12), "&6电力淘洗机", new String[] { "", "&e基础机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &72 J/s" });
/* 623 */     ELECTRIC_GOLD_PAN_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)12), "&6电力淘洗机 &7(&eII&7)", new String[] { "", "&e基础机器", "&8⇨ &7速度: 3x", "&8⇨ &e⚡ &74 J/s" });
/* 624 */     ELECTRIC_GOLD_PAN_3 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)12), "&6电力淘洗机 &7(&eIII&7)", new String[] { "", "&4终极机器", "&8⇨ &7速度: 10x", "&8⇨ &e⚡ &714 J/s" });
/*     */     
/* 626 */     ELECTRIC_DUST_WASHER = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)11), "&6电力洗粉机", new String[] { "", "&e基础机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &76 J/s" });
/* 627 */     ELECTRIC_DUST_WASHER_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)11), "&6电力洗粉机 &7(&eII&7)", new String[] { "", "&e基础机器", "&8⇨ &7速度: 2x", "&8⇨ &e⚡ &710 J/s" });
/* 628 */     ELECTRIC_DUST_WASHER_3 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)11), "&6电力洗粉机 &7(&eIII&7)", new String[] { "", "&4终极机器", "&8⇨ &7速度: 10x", "&8⇨ &e⚡ &730 J/s" });
/*     */     
/* 630 */     ELECTRIC_INGOT_FACTORY = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&6电力制锭机", new String[] { "", "&e基础机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &78 J/s" });
/* 631 */     ELECTRIC_INGOT_FACTORY_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&6电力制锭机 &7(&eII&7)", new String[] { "", "&e基础机器", "&8⇨ &7速度: 2x", "&8⇨ &e⚡ &714 J/s" });
/* 632 */     ELECTRIC_INGOT_FACTORY_3 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)14), "&6电力制锭机 &7(&eIII&7)", new String[] { "", "&4终极机器", "&8⇨ &7速度: 8x", "&8⇨ &e⚡ &740 J/s" });
/*     */     
/* 634 */     AUTOMATED_CRAFTING_CHAMBER = (ItemStack)new CustomItem(new MaterialData(Material.WORKBENCH), "&6自动合成机", new String[] { "", "&6高级机器", "&8⇨ &e⚡ &710 J/物品" });
/* 635 */     FLUID_PUMP = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)11), "&9流体泵", new String[] { "", "&6高级机器", "&8⇨ &e⚡ &732 J/Block" });
/* 636 */     CHARGING_BENCH = (ItemStack)new CustomItem(new MaterialData(Material.DROPPER), "&6充电架", new String[] { "", "&r可以为耗电物品充电", "", "&e基础机器", "&8⇨ &e⚡ &7128 J 缓存", "&8⇨ &e⚡ &7能量损耗: &c50%" });
/*     */     
/* 638 */     WITHER_ASSEMBLER = (ItemStack)new CustomItem(new MaterialData(Material.OBSIDIAN), "&5凋零组装机", new String[] { "", "&4终极机器", "&8⇨ &7冷却: &b30 秒", "&8⇨ &e⚡ &74096 J 缓存", "&8⇨ &e⚡ &74096 J/凋零" });
/*     */     
/* 640 */     TRASH_CAN = null;
/*     */     
/* 642 */     ELYTRA = new ItemStack(Material.ELYTRA);
/* 643 */     ELYTRA_SCALE = (ItemStack)new CustomItem(new ItemStack(Material.FEATHER), "&b鞘翅鳞片");
/* 644 */     INFUSED_ELYTRA = (ItemStack)new CustomItem((ItemStack)new CustomItem(ELYTRA, "&5自我修复型鞘翅"), new String[] { "MENDING-1" });
/* 645 */     SOULBOUND_ELYTRA = (ItemStack)new CustomItem(ELYTRA, "&c灵魂绑定鞘翅");
/*     */ 
/*     */ 
/*     */     
/* 649 */     CHEST_TERMINAL = null;
/* 650 */     CT_IMPORT_BUS = null;
/* 651 */     CT_EXPORT_BUS = null;
/*     */ 
/*     */     
/*     */     try {
/* 655 */       PORTABLE_DUSTBIN = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJkNDEwNDJjZTk5MTQ3Y2MzOGNhYzllNDY3NDE1NzZlN2VlNzkxMjgzZTZmYWM4ZDMyOTJjYWUyOTM1ZjFmIn19fQ=="), "便携式垃圾箱", new String[] { "&r你的便携式物品销毁机", "", "&e右键点击&7 以打开" });
/* 656 */       TRASH_CAN = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJkNDEwNDJjZTk5MTQ3Y2MzOGNhYzllNDY3NDE1NzZlN2VlNzkxMjgzZTZmYWM4ZDMyOTJjYWUyOTM1ZjFmIn19fQ=="), "&3垃圾桶", new String[] { "", "&r任何放入的物品都会被摧毁" });
/* 657 */       CAN = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRkYTk3ZjA4MGUzOTViODQyYzRjYzgyYTg0MDgyM2Q0ZGJkOGNhNjg4YTIwNjg1M2U1NzgzZTRiZmRjMDEyIn19fQ=="), "&r锡罐");
/*     */       
/* 659 */       STONE_CHUNK = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2U4ZjVhZGIxNGQ2YzlmNmI4MTBkMDI3NTQzZjFhOGMxZjQxN2UyZmVkOTkzYzk3YmNkODljNzRmNWUyZTgifX19"), "&6石块");
/*     */       
/* 661 */       INFUSED_MAGNET = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&a注磁铁", new String[] { "", "&r被注入了强力磁性的磁铁", "&r能够将你附近的物品", "&r全部吸入你的背包", "", "", "&7按住 &eShift&7 使用注磁铁" });
/* 662 */       MAGNET = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&c磁铁");
/* 663 */       ELECTRO_MAGNET = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&c电磁铁");
/* 664 */       ELECTRIC_MOTOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNiY2EwMTJmNjdlNTRkZTlhZWU3MmZmNDI0ZTA1NmMyYWU1OGRlNWVhY2M5NDlhYjJiY2Q5NjgzY2VjIn19fQ=="), "&c电动马达");
/* 665 */       CARGO_MOTOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNiY2EwMTJmNjdlNTRkZTlhZWU3MmZmNDI0ZTA1NmMyYWU1OGRlNWVhY2M5NDlhYjJiY2Q5NjgzY2VjIn19fQ=="), "&3运输马达");
/*     */       
/* 667 */       BACKPACK_SMALL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e小背包", new String[] { "", "&7容量: &e9", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 668 */       BACKPACK_MEDIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e背包", new String[] { "", "&7容量: &e18", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 669 */       BACKPACK_LARGE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e大背包", new String[] { "", "&7容量: &e27", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 670 */       WOVEN_BACKPACK = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e编织包", new String[] { "", "&7容量: &e36", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 671 */       GILDED_BACKPACK = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e镀金背包", new String[] { "", "&7容量: &e45", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 672 */       BOUND_BACKPACK = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&c灵魂绑定背包", new String[] { "", "&7容量: &e36", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 673 */       COOLER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRjMTU3MjU4NGViNWRlMjI5ZGU5ZjVhNGY3NzlkMGFhY2JhZmZkMzNiY2IzM2ViNDUzNmE2YTJiYzZhMSJ9fX0="), "&b冰箱", new String[] { "&r所有存入冰箱的 果汁/冰沙", "&r都会在你饥饿时", "&r自动消耗补充你的饥饿值", "", "&7容量: &e27", "&7ID: <ID>", "", "&7&e右键点击&7 以打开" });
/* 674 */       ENDER_BACKPACK = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&6末影背包", new String[] { "&a便携式末影箱", "", "&e右键点击&7 以打开" });
/*     */       
/* 676 */       VOIDBAG_SMALL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4小型虚空包", new String[] { "", "&7容量: &e9", "&7ID: <ID>", "", "&7&e左键点击&7 将附近的物品吸入背包", "&7&e右键点击&7 以打开" });
/* 677 */       VOIDBAG_MEDIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4虚空包", new String[] { "", "&7容量: &e18", "&7ID: <ID>", "", "&7&e左键点击&7 将附近的物品吸入背包", "&7&e右键点击&7 以打开" });
/* 678 */       VOIDBAG_BIG = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4中型虚空包", new String[] { "", "&7容量: &e27", "&7ID: <ID>", "", "&7&e左键点击&7 将附近的物品吸入背包", "&7&e右键点击&7 以打开" });
/* 679 */       VOIDBAG_LARGE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4大型虚空包", new String[] { "", "&7容量: &e36", "&7ID: <ID>", "", "&7&e左键点击&7 将附近的物品吸入背包", "&7&e右键点击&7 以打开" });
/* 680 */       BOUND_VOIDBAG = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4灵魂绑定虚空包", new String[] { "", "&7容量: &e36", "&7ID: <ID>", "", "&7&e左键点击&7 将附近的物品吸入背包", "&7&e右键点击&7 以打开" });
/*     */       
/* 682 */       COAL_GENERATOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&c煤炭发电机", new String[] { "", "&6发电机组", "&8⇨ &e⚡ &764 J 缓存", "&8⇨ &e⚡ &716 J/s" });
/* 683 */       LAVA_GENERATOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&4岩浆发电机", new String[] { "", "&6发电机组", "&8⇨ &e⚡ &7512 J 缓存", "&8⇨ &e⚡ &720 J/s" });
/* 684 */       COMBUSTION_REACTOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&c燃烧反应器", new String[] { "", "&6高级发电机", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &724 J/s" });
/*     */       
/* 686 */       NUCLEAR_REACTOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&2核能反应器", new String[] { "", "&r需要冷却!", "&8⇨ &b必须完全浸入水中", "&8⇨ &b必须配备反应器冷却单元", "", "&4终极发电机", "&8⇨ &e⚡ &716384 J 缓存", "&8⇨ &e⚡ &7500 J/s" });
/* 687 */       NETHERSTAR_REACTOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&f下界之星反应器", new String[] { "", "&f依赖下界之星运作", "&8⇨ &b必须完全浸入水中", "&8⇨ &b必须配备下界玄冰冷却单元", "", "&4终极发电机", "&8⇨ &e⚡ &732768 J 缓存", "&8⇨ &e⚡ &71024 J/s", "&8⇨ &4会使周围生物遭受凋零伤害" });
/*     */       
/* 689 */       SMALL_CAPACITOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a小型电容器", new String[] { "", "&e基础电容", "&8⇨ &e⚡ &7128 J 容量" });
/* 690 */       MEDIUM_CAPACITOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a中型电容器", new String[] { "", "&6中型电容", "&8⇨ &e⚡ &7512 J 容量" });
/* 691 */       BIG_CAPACITOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a大型电容器", new String[] { "", "&a大型电容", "&8⇨ &e⚡ &71024 J 容量" });
/* 692 */       LARGE_CAPACITOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a超级电容器", new String[] { "", "&2超级电容", "&8⇨ &e⚡ &78192 J 容量" });
/* 693 */       CARBONADO_EDGED_CAPACITOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a黑钻电容器", new String[] { "", "&4终极电容器", "&8⇨ &e⚡ &765536 J 容量" });
/* 694 */       CHEESE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzRmZWJiYzE1ZDFkNGNjNjJiZWRjNWQ3YTJiNmYwZjQ2Y2Q1YjA2OTZhODg0ZGU3NWUyODllMzVjYmI1M2EwIn19fQ=="), "&r芝士");
/* 695 */       BUTTER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY2YjE5ZjdkNjM1ZDAzNDczODkxZGYzMzAxN2M1NDkzNjMyMDlhOGY2MzI4YTg1NDJjMjEzZDA4NTI1ZSJ9fX0="), "&r黄油");
/* 696 */       DUCT_TAPE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJmYWFjZWFiNjM4NGZmZjVlZDI0YmI0NGE0YWYyZjU4NGViMTM4MjcyOWVjZDkzYTUzNjlhY2ZkNjY1NCJ9fX0="), "&8胶带", new String[] { "", "&r修复物品需要用到这个", "&r需要将其放入自动铁砧" });
/*     */       
/* 698 */       URANIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMjlhZmE2ZDZkYzkyM2UyZTEzMjRiZjgxOTI3NTBmN2JkYmRkYzY4OTYzMmEyYjZjMThkOWZlN2E1ZSJ9fX0="), "&4铀", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/* 699 */       SMALL_URANIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMjlhZmE2ZDZkYzkyM2UyZTEzMjRiZjgxOTI3NTBmN2JkYmRkYzY4OTYzMmEyYjZjMThkOWZlN2E1ZSJ9fX0="), "&c小块铀", new String[] { "", "&e辐射等级: 中", "&4需要穿着防护服使用" });
/* 700 */       TINY_URANIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMjlhZmE2ZDZkYzkyM2UyZTEzMjRiZjgxOTI3NTBmN2JkYmRkYzY4OTYzMmEyYjZjMThkOWZlN2E1ZSJ9fX0="), "&c小撮铀", new String[] { "", "&c辐射等级: 低", "&4无需防护服" });
/*     */       
/* 702 */       NEPTUNIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVkZWE2YmZkMzdlNDlkZTQzZjE1NGZlNmZjYTYxN2Q0MTI5ZTYxYjk1NzU5YTNkNDlhMTU5MzVhMWMyZGNmMCJ9fX0="), "&a镎", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/* 703 */       PLUTONIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjVjZjkxYjczODg2NjVhNmQ3YzFiNjAyNmJkYjIzMjJjNmQyNzg5OTdhNDQ0Nzg2NzdjYmNjMTVmNzYxMjRmIn19fQ=="), "&7钚", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/* 704 */       BOOSTED_URANIUM = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgzN2NhMTJmMjIyZjQ3ODcxOTZhMTdiOGFiNjU2OTg1Zjg0MDRjNTA3NjdhZGJjYjZlN2YxNDI1NGZlZSJ9fX0="), "&2提纯铀", new String[] { "", "&2辐射等级: 高", "&4需要穿着防护服使用" });
/*     */       
/* 706 */       PROGRAMMABLE_ANDROID = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&c可编程机器人 &7(标准版)", new String[] { "", "&8⇨ &7功能: 无", "&8⇨ &7燃料效率: 1.0x" });
/* 707 */       PROGRAMMABLE_ANDROID_FARMER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlkMzMzNTdlODQxODgyM2JmNzgzZGU5MmRlODAyOTFiNGViZDM5MmFlYzg3MDY2OThlMDY4OTZkNDk4ZjYifX19"), "&c可编程机器人 &7(农耕版)", new String[] { "", "&8⇨ &7功能: 农耕", "&8⇨ &7燃料效率: 1.0x" });
/* 708 */       PROGRAMMABLE_ANDROID_MINER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTYzOGEyODU0MWFiM2FlMGE3MjNkNTU3ODczOGUwODc1ODM4OGVjNGMzMzI0N2JkNGNhMTM0ODJhZWYzMzQifX19"), "&c可编程机器人 &7(挖矿版)", new String[] { "", "&8⇨ &7功能: 挖矿", "&8⇨ &7燃料效率: 1.0x" });
/* 709 */       PROGRAMMABLE_ANDROID_WOODCUTTER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDMyYTgxNDUxMDE0MjIwNTE2OWExYWQzMmYwYTc0NWYxOGU5Y2I2YzY2ZWU2NGVjYTJlNjViYWJkZWY5ZmYifX19"), "&c可编程机器人 &7(伐木版)", new String[] { "", "&8⇨ &7功能: 伐木", "&8⇨ &7燃料效率: 1.0x" });
/* 710 */       PROGRAMMABLE_ANDROID_BUTCHER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NzJkZjBhZDlhM2JlODhmMmU1ZDVkNDIyZDAyYjExNmQ2NGQ4ZGYxNDc1ZWQzMmU1NDZhZmM4NGIzMSJ9fX0="), "&c可编程机器人 &7(攻击版)", new String[] { "", "&8⇨ &7功能: 杀死生物", "&8⇨ &7伤害: 4", "&8⇨ &7燃料效率: 1.0x" });
/* 711 */       PROGRAMMABLE_ANDROID_FISHERMAN = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ1ZTg3MzNhNzMxMTQzMzNiOThiMzYwMTc1MTI0MTcyMmY0NzEzZTFhMWE1ZDM2ZmJiMTMyNDkzZjFjNyJ9fX0="), "&c可编程机器人 &7(捕鱼版)", new String[] { "", "&8⇨ &7功能: 捕鱼", "&8⇨ &7成功率: 10%", "&8⇨ &7燃料效率: 1.0x" });
/*     */       
/* 713 */       PROGRAMMABLE_ANDROID_2 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&c高级可编程机器人 &7(标准版)", new String[] { "", "&8⇨ &7功能: 无", "&8⇨ &7燃料效率: 1.5x" });
/* 714 */       PROGRAMMABLE_ANDROID_2_FISHERMAN = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ1ZTg3MzNhNzMxMTQzMzNiOThiMzYwMTc1MTI0MTcyMmY0NzEzZTFhMWE1ZDM2ZmJiMTMyNDkzZjFjNyJ9fX0="), "&c高级可编程机器人 &7(捕鱼版)", new String[] { "", "&8⇨ &7功能: 捕鱼", "&8⇨ &7成功率: 20%", "&8⇨ &7燃料效率: 1.5x" });
/* 715 */       PROGRAMMABLE_ANDROID_2_FARMER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlkMzMzNTdlODQxODgyM2JmNzgzZGU5MmRlODAyOTFiNGViZDM5MmFlYzg3MDY2OThlMDY4OTZkNDk4ZjYifX19"), "&c高级可编程机器人 &7(农耕版)", new String[] { "", "&8⇨ &7功能: 农耕", "&8⇨ &7燃料效率: 1.5x", "&8⇨ &7能收获果树" });
/* 716 */       PROGRAMMABLE_ANDROID_2_BUTCHER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NzJkZjBhZDlhM2JlODhmMmU1ZDVkNDIyZDAyYjExNmQ2NGQ4ZGYxNDc1ZWQzMmU1NDZhZmM4NGIzMSJ9fX0="), "&c高级可编程机器人 &7(攻击版)", new String[] { "", "&8⇨ &7功能: 杀死生物", "&8⇨ &7伤害: 8", "&8⇨ &7燃料效率: 1.5x" });
/*     */       
/* 718 */       PROGRAMMABLE_ANDROID_3 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&e自走型可编程机器人 &7(标准版)", new String[] { "", "&8⇨ &7功能: 无", "&8⇨ &7燃料效率: 3.0x" });
/* 719 */       PROGRAMMABLE_ANDROID_3_FISHERMAN = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ1ZTg3MzNhNzMxMTQzMzNiOThiMzYwMTc1MTI0MTcyMmY0NzEzZTFhMWE1ZDM2ZmJiMTMyNDkzZjFjNyJ9fX0="), "&e自走型可编程机器人 &7(捕鱼版)", new String[] { "", "&8⇨ &7功能: 捕鱼", "&8⇨ &7成功率: 30%", "&8⇨ &7燃料效率: 8.0x" });
/* 720 */       PROGRAMMABLE_ANDROID_3_BUTCHER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NzJkZjBhZDlhM2JlODhmMmU1ZDVkNDIyZDAyYjExNmQ2NGQ4ZGYxNDc1ZWQzMmU1NDZhZmM4NGIzMSJ9fX0="), "&e自走型可编程机器人 &7(攻击版)", new String[] { "", "&8⇨ &7功能: 杀死生物", "&8⇨ &7伤害: 20", "&8⇨ &7燃料效率: 8.0x" });
/*     */       
/* 722 */       GPS_TRANSMITTER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&bGPS信号发射器", new String[] { "", "&8⇨ &e⚡ &716 J 缓存", "&8⇨ &e⚡ &72 J/s" });
/* 723 */       GPS_TRANSMITTER_2 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&c高级GPS信号发射器", new String[] { "", "&8⇨ &e⚡ &764 J 缓存", "&8⇨ &e⚡ &76 J/s" });
/* 724 */       GPS_TRANSMITTER_3 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&4黑钻GPS信号发射器", new String[] { "", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &722 J/s" });
/* 725 */       GPS_TRANSMITTER_4 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&e电力GPS信号发射器", new String[] { "", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &792 J/s" });
/*     */       
/* 727 */       GPS_CONTROL_PANEL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZmJhNThmYWYxZjY0ODQ3ODg0MTExODIyYjY0YWZhMjFkN2ZjNjJkNDQ4MWYxNGYzZjNiY2I2MzMwIn19fQ=="), "&bGPS控制面板", new String[] { "", "&r允许你控制你的卫星", "&r和管理你的路径点" });
/* 728 */       GPS_EMERGENCY_TRANSMITTER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&cGPS紧急信号发射器", new String[] { "", "&r当你死亡时", "&r紧急信号发射器将会自动发射", "&r并在你死亡的地点设置一个路径点." });
/*     */       
/* 730 */       GPS_GEO_SCANNER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFkOGNmZWIzODdhNTZlM2U1YmNmODUzNDVkNmE0MTdiMjQyMjkzODg3ZGIzY2UzYmE5MWZhNDA5YjI1NGI4NiJ9fX0="), "&bGPS 全球扫描器", new String[] { "", "&r扫描一个区块中的天然资源", "&r比如 &8石油" });
/* 731 */       OIL_PUMP = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWZlMWEwNDBhNDI1ZTMxYTQ2ZDRmOWE5Yjk4MDZmYTJmMGM0N2VlODQ3MTFjYzE5MzJmZDhhYjMyYjJkMDM4In19fQ=="), "&r石油泵", new String[] { "", "&7泵出石油并将其收集进桶中", "", "&c&l! &c请在使用前确认你已经对区块进行过扫描" });
/* 732 */       BUCKET_OF_OIL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNlMDRiNDFkMTllYzc5MjdmOTgyYTYzYTk0YTNkNzlmNzhlY2VjMzMzNjMwNTFmZGUwODMxYmZhYmRiZCJ9fX0="), "&r桶装石油");
/* 733 */       BUCKET_OF_FUEL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg0ZGRjYTc2NjcyNWI4Yjk3NDEzZjI1OWMzZjc2NjgwNzBmNmFlNTU0ODNhOTBjOGU1NTI1Mzk0ZjljMDk5In19fQ=="), "&r桶装液体燃料");
/*     */       
/* 735 */       NETHER_ICE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY5NjVlNmE1ODY4NGMyNzdkMTg3MTdjZWM5NTlmMjgzM2E3MmRmYTk1NjYxMDE5ZGJjZGYzZGJmNjZiMDQ4In19fQ=="), "&e下界玄冰", new String[] { "", "&e辐射等级: 中", "&4需要穿着防护服使用" });
/*     */       
/* 737 */       ENRICHED_NETHER_ICE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M4MThhYTEzYWFiYzcyOTQ4MzhkMjFjYWFjMDU3ZTk3YmQ4Yzg5NjQxYTBjMGY4YTU1NDQyZmY0ZTI3In19fQ=="), "&e浓缩下界玄冰", new String[] { "", "&2辐射等级: 极高", "&4需要穿着防护服使用" });
/*     */       
/* 739 */       LAVA_CRYSTAL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNhZDhlZTg0OWVkZjA0ZWQ5YTI2Y2EzMzQxZjYwMzNiZDc2ZGNjNDIzMWVkMWVhNjNiNzU2NTc1MWIyN2FjIn19fQ=="), "&4熔岩水晶");
/* 740 */       ANDROID_MEMORY_CORE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&b机器人记忆核心");
/*     */       
/* 742 */       CARBON = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGIzYTA5NWI2YjgxZTZiOTg1M2ExOTMyNGVlZGYwYmI5MzQ5NDE3MjU4ZGQxNzNiOGVmZjg3YTA4N2FhIn19fQ=="), "&e碳");
/* 743 */       COMPRESSED_CARBON = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzIxZDQ5NTE2NTc0OGQzMTE2Zjk5ZDZiNWJkNWQ0MmViOGJhNTkyYmNkZmFkMzdmZDk1ZjliNmMwNGEzYiJ9fX0="), "&c压缩碳");
/* 744 */       CARBON_CHUNK = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzIxZDQ5NTE2NTc0OGQzMTE2Zjk5ZDZiNWJkNWQ0MmViOGJhNTkyYmNkZmFkMzdmZDk1ZjliNmMwNGEzYiJ9fX0="), "&4碳块");
/*     */       
/* 746 */       CARBONADO = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTJmNGIxNTc3ZjUxNjBjNjg5MzE3MjU3MWM0YTcxZDhiMzIxY2RjZWFhMDMyYzZlMGUzYjYwZTBiMzI4ZmEifX19"), "&b&l黑钻", new String[] { "", "&7\"黑色的钻石\"" });
/* 747 */       RAW_CARBONADO = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWI0OWU2ZWMxMDc3MWU4OTkyMjVhZWE3M2NkOGNmMDM2ODRmNDExZDE0MTVjNzMyM2M5M2NiOTQ3NjIzMCJ9fX0="), "&b生黑钻");
/*     */       
/* 749 */       ENERGY_REGULATOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&6能量调度器", new String[] { "", "&r能源网络的核心组件" });
/*     */       
/* 751 */       CARGO_MANAGER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTUxMGJjODUzNjJhMTMwYTZmZjlkOTFmZjExZDZmYTQ2ZDdkMTkxMmEzNDMxZjc1MTU1OGVmM2M0ZDljMiJ9fX0="), "&6运输调度器", new String[] { "", "&r运输网络的核心组件" });
/* 752 */       CARGO_NODE = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDdiN2VmNmZkNzg2NDg2NWMzMWMxZGM4N2JlZDI0YWI1OTczNTc5ZjVjNjYzOGZlY2I4ZGVkZWI0NDNmZjAifX19"), "&7运输接口 &c(连接)", new String[] { "", "&r运输连接管道" });
/* 753 */       CARGO_INPUT = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZkMWMxYTY5YTNkZTlmZWM5NjJhNzdiZjNiMmUzNzZkZDI1Yzg3M2EzZDhmMTRmMWRkMzQ1ZGFlNGM0In19fQ=="), "&7运输接口 &c(输入)", new String[] { "", "&r运输输入管道" });
/* 754 */       CARGO_OUTPUT = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTViMjFmZDQ4MGMxYzQzYmYzYjlmODQyYzg2OWJkYzNiYzVhY2MyNTk5YmYyZWI2YjhhMWM5NWRjZTk3OGYifX19"), "&7运输接口 &c(输出)", new String[] { "", "&r运输输出管道" });
/* 755 */       CARGO_OUTPUT_ADVANCED = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTViMjFmZDQ4MGMxYzQzYmYzYjlmODQyYzg2OWJkYzNiYzVhY2MyNTk5YmYyZWI2YjhhMWM5NWRjZTk3OGYifX19"), "&6高级运输接口 &c(输出)", new String[] { "", "&r运输输出管道" });
/*     */       
/* 757 */       AUTO_BREEDER = (ItemStack)new CustomItem(new MaterialData(Material.HAY_BLOCK), "&e自动喂养器", new String[] { "", "&r依靠 &a有机食物 &r运作", "", "&4终极机器", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &760 J/动物" });
/* 758 */       ANIMAL_GROWTH_ACCELERATOR = (ItemStack)new CustomItem(new MaterialData(Material.HAY_BLOCK), "&b动物生长催化机", new String[] { "", "&r依靠 &a有机食物 &r运作", "", "&4终极机器", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &728 J/s" });
/* 759 */       CROP_GROWTH_ACCELERATOR = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)5), "&a作物生长催化机", new String[] { "", "&r依靠 &a有机肥料 &r运作", "", "&4终极机器", "&8⇨ &7半径: 7x7", "&8⇨ &7速度: &a3/次", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &750 J/s" });
/* 760 */       CROP_GROWTH_ACCELERATOR_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte)5), "&a作物生长催化机 &7(&eII&7)", new String[] { "", "&r依靠 &a有机肥料 &r运作", "", "&4终极机器", "&8⇨ &7半径: 9x9", "&8⇨ &7速度: &a4/次", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &760 J/s" });
/* 761 */       XP_COLLECTOR = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2MmExNWIwNDY5MmEyZTRiM2ZiMzY2M2JkNGI3ODQzNGRjZTE3MzJiOGViMWM3YTlmN2MwZmJmNmYifX19"), "&a经验收集器", new String[] { "", "&r自动吸取附近的经验球并保存", "", "&4终极机器", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &720 J/s" });
/*     */       
/* 763 */       ORGANIC_FOOD = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9X" });
/* 764 */       ORGANIC_FOOD2 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9小麦" });
/* 765 */       ORGANIC_FOOD3 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9胡萝卜" });
/* 766 */       ORGANIC_FOOD4 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9土豆" });
/* 767 */       ORGANIC_FOOD5 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9种子" });
/* 768 */       ORGANIC_FOOD6 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9甜菜根" });
/* 769 */       ORGANIC_FOOD7 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9西瓜" });
/* 770 */       ORGANIC_FOOD8 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机食物", new String[] { "&7内容物: &9苹果" });
/*     */       
/* 772 */       FERTILIZER = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9X" });
/* 773 */       FERTILIZER2 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9小麦" });
/* 774 */       FERTILIZER3 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9胡萝卜" });
/* 775 */       FERTILIZER4 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9土豆" });
/* 776 */       FERTILIZER5 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9种子" });
/* 777 */       FERTILIZER6 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9甜菜根" });
/* 778 */       FERTILIZER7 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9西瓜" });
/* 779 */       FERTILIZER8 = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a有机肥料", new String[] { "&7内容物: &9苹果" });
/*     */       
/* 781 */       NETHER_ICE_COOLANT_CELL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQzY2Q0MTI1NTVmODk3MDE2MjEzZTVkNmM3NDMxYjQ0OGI5ZTU2NDRlMWIxOWVjNTFiNTMxNmYzNTg0MGUwIn19fQ=="), "&e下界玄冰冷却单元");
/* 782 */       REACTOR_COOLANT_CELL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGU0MDczYmU0MGNiM2RlYjMxMGEwYmU5NTliNGNhYzY4ZTgyNTM3MjcyOGZhZmI2YzI5NzNlNGU3YzMzIn19fQ=="), "&b反应器冷却单元");
/*     */       
/* 784 */       CHEST_TERMINAL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E0NGZmM2E1ZjQ5YzY5Y2FiNjc2YmFkOGQ5OGEwNjNmYTc4Y2ZhNjE5MTZmZGVmM2UyNjc1NTdmZWMxODI4MyJ9fX0="), "&3CT 接入终端", new String[] { "&7如果这个方块与运输网络设备相连", "&7那么它将允许你", "&7与任何接口提供的物品进行交互", "&7并可以将其转入箱子终端" });
/* 785 */       CT_IMPORT_BUS = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTEzZGIyZTdlNzJlYTQ0MzJlZWZiZDZlNThhODVlYWEyNDIzZjgzZTY0MmNhNDFhYmM2YTkzMTc3NTdiODg5In19fQ=="), "&3CT 导入总线", new String[] { "&7如果这个方块与运输网络设备相连", "&7那么它将拉取", "&7与其相连容器中的物品", "&7到运输网络中" });
/* 786 */       CT_EXPORT_BUS = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTEzZGIyZTdlNzJlYTQ0MzJlZWZiZDZlNThhODVlYWEyNDIzZjgzZTY0MmNhNDFhYmM2YTkzMTc3NTdiODg5In19fQ=="), "&3CT 导出总线", new String[] { "&7如果这个方块与运输网络设备相连", "&7那么它将拉取", "&7运输网络中的物品", "&7并导出到与其相连的容器中" });
/*     */       
/* 788 */       FREEZER = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)3), "&b制冷机", new String[] { "", "&6高级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &718 J/s" });
/* 789 */       FREEZER_2 = (ItemStack)new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte)3), "&b制冷机 &7(&eII&7)", new String[] { "", "&4终极机器", "&8⇨ &7速度: 2x", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &730 J/s" });
/*     */       
/* 791 */       BATTERY = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUyZGRhNmVmNjE4NWQ0ZGQ2ZWE4Njg0ZTk3ZDM5YmE4YWIwMzdlMjVmNzVjZGVhNmJkMjlkZjhlYjM0ZWUifX19"), "&6电池");
/*     */       
/* 793 */       HEATING_COIL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzYmM0ODkzYmE0MWEzZjczZWUyODE3NGNkZjRmZWY2YjE0NWU0MWZlNmM4MmNiN2JlOGQ4ZTk3NzFhNSJ9fX0="), "&c加热线圈");
/* 794 */       COOLING_UNIT = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0YmFkODZjOTlkZjc4MGM4ODlhMTA5OGY3NzY0OGVhZDczODVjYzFkZGIwOTNkYTVhN2Q4YzRjMmFlNTRkIn19fQ=="), "&b冷却单元");
/* 795 */       POWER_CRYSTAL = (ItemStack)new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNjMWIwMzZiNmUwMzUxN2IyODVhODExYmQ4NWU3M2Y1YWJmZGFjYzFkZGY5MGRmZjk2MmUxODA5MzRlMyJ9fX0="), "&c&l能量水晶");
/*     */     }
/* 797 */     catch (Exception e) {
/* 798 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ItemStack SOLAR_GENERATOR;
/*     */   public static ItemStack SOLAR_GENERATOR_2;
/*     */   public static ItemStack SOLAR_GENERATOR_3;
/*     */   public static ItemStack SOLAR_GENERATOR_4;
/*     */   public static ItemStack COAL_GENERATOR;
/*     */   public static ItemStack LAVA_GENERATOR;
/*     */   public static ItemStack ELECTRIC_FURNACE;
/*     */   public static ItemStack ELECTRIC_FURNACE_2;
/*     */   public static ItemStack ELECTRIC_FURNACE_3;
/*     */   public static ItemStack ELECTRIC_ORE_GRINDER;
/*     */   public static ItemStack ELECTRIC_ORE_GRINDER_2;
/*     */   public static ItemStack ELECTRIC_INGOT_PULVERIZER;
/*     */   public static ItemStack AUTO_ENCHANTER;
/*     */   public static ItemStack AUTO_DISENCHANTER;
/*     */   public static ItemStack AUTO_ANVIL;
/*     */   public static ItemStack AUTO_ANVIL_2;
/*     */   public static ItemStack BIO_REACTOR;
/*     */   public static ItemStack MULTIMETER;
/*     */   public static ItemStack SMALL_CAPACITOR;
/*     */   public static ItemStack MEDIUM_CAPACITOR;
/*     */   public static ItemStack BIG_CAPACITOR;
/*     */   public static ItemStack LARGE_CAPACITOR;
/*     */   public static ItemStack CARBONADO_EDGED_CAPACITOR;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_MINER;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_BUTCHER;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_FARMER;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_WOODCUTTER;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_FISHERMAN;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_2;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_2_FISHERMAN;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_2_FARMER;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_2_BUTCHER;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_3;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_3_FISHERMAN;
/*     */   public static ItemStack PROGRAMMABLE_ANDROID_3_BUTCHER;
/*     */   public static ItemStack GPS_TRANSMITTER;
/*     */   public static ItemStack GPS_TRANSMITTER_2;
/*     */   public static ItemStack GPS_TRANSMITTER_3;
/*     */   public static ItemStack GPS_TRANSMITTER_4;
/*     */   public static ItemStack GPS_CONTROL_PANEL;
/*     */   public static ItemStack GPS_MARKER_TOOL;
/*     */   public static ItemStack GPS_EMERGENCY_TRANSMITTER;
/*     */   public static ItemStack GPS_GEO_SCANNER;
/*     */   public static ItemStack ANDROID_INTERFACE_FUEL;
/*     */   public static ItemStack ANDROID_INTERFACE_ITEMS;
/*     */   public static ItemStack BUCKET_OF_OIL;
/*     */   public static ItemStack BUCKET_OF_FUEL;
/*     */   public static ItemStack OIL_PUMP;
/*     */   public static ItemStack REFINERY;
/*     */   public static ItemStack COMBUSTION_REACTOR;
/*     */   public static ItemStack ANDROID_MEMORY_CORE;
/*     */   public static ItemStack GPS_TELEPORTER_PYLON;
/*     */   public static ItemStack GPS_TELEPORTATION_MATRIX;
/*     */   public static ItemStack GPS_ACTIVATION_DEVICE_SHARED;
/*     */   public static ItemStack GPS_ACTIVATION_DEVICE_PERSONAL;
/*     */   public static ItemStack ELEVATOR;
/*     */   public static ItemStack INFUSED_HOPPER;
/*     */   public static ItemStack PLASTIC_SHEET;
/*     */   public static ItemStack HEATED_PRESSURE_CHAMBER;
/*     */   public static ItemStack HEATED_PRESSURE_CHAMBER_2;
/*     */   public static ItemStack ELECTRIC_SMELTERY;
/*     */   public static ItemStack ELECTRIC_SMELTERY_2;
/*     */   public static ItemStack ELECTRIFIED_CRUCIBLE;
/*     */   public static ItemStack ELECTRIFIED_CRUCIBLE_2;
/*     */   public static ItemStack ELECTRIFIED_CRUCIBLE_3;
/*     */   public static ItemStack CARBON_PRESS;
/*     */   public static ItemStack CARBON_PRESS_2;
/*     */   public static ItemStack CARBON_PRESS_3;
/*     */   public static ItemStack BLISTERING_INGOT;
/*     */   public static ItemStack BLISTERING_INGOT_2;
/*     */   public static ItemStack BLISTERING_INGOT_3;
/*     */   public static ItemStack ENERGY_REGULATOR;
/*     */   public static ItemStack DEBUG_FISH;
/*     */   public static ItemStack NETHER_ICE;
/*     */   public static ItemStack ENRICHED_NETHER_ICE;
/*     */   public static ItemStack NETHER_ICE_COOLANT_CELL;
/*     */   public static ItemStack NETHER_DRILL;
/*     */   public static ItemStack CARGO_MANAGER;
/*     */   public static ItemStack CARGO_NODE;
/*     */   public static ItemStack CARGO_INPUT;
/*     */   public static ItemStack CARGO_OUTPUT;
/*     */   public static ItemStack CARGO_OUTPUT_ADVANCED;
/*     */   public static ItemStack AUTO_BREEDER;
/*     */   public static ItemStack ORGANIC_FOOD;
/*     */   public static ItemStack ORGANIC_FOOD2;
/*     */   public static ItemStack ORGANIC_FOOD3;
/*     */   public static ItemStack ORGANIC_FOOD4;
/*     */   public static ItemStack ORGANIC_FOOD5;
/*     */   public static ItemStack ORGANIC_FOOD6;
/*     */   public static ItemStack ORGANIC_FOOD7;
/*     */   public static ItemStack ORGANIC_FOOD8;
/*     */   public static ItemStack FERTILIZER;
/*     */   public static ItemStack FERTILIZER2;
/*     */   public static ItemStack FERTILIZER3;
/*     */   public static ItemStack FERTILIZER4;
/*     */   public static ItemStack FERTILIZER5;
/*     */   public static ItemStack FERTILIZER6;
/*     */   public static ItemStack FERTILIZER7;
/*     */   public static ItemStack FERTILIZER8;
/*     */   public static ItemStack ANIMAL_GROWTH_ACCELERATOR;
/*     */   public static ItemStack CROP_GROWTH_ACCELERATOR;
/*     */   public static ItemStack CROP_GROWTH_ACCELERATOR_2;
/*     */   public static ItemStack FOOD_FABRICATOR;
/*     */   public static ItemStack FOOD_FABRICATOR_2;
/*     */   public static ItemStack FOOD_COMPOSTER;
/*     */   public static ItemStack FOOD_COMPOSTER_2;
/*     */   public static ItemStack XP_COLLECTOR;
/*     */   public static ItemStack REACTOR_COOLANT_CELL;
/*     */   public static ItemStack NUCLEAR_REACTOR;
/*     */   public static ItemStack NETHERSTAR_REACTOR;
/*     */   public static ItemStack REACTOR_ACCESS_PORT;
/*     */   public static ItemStack FREEZER;
/*     */   public static ItemStack FREEZER_2;
/*     */   public static ItemStack ELECTRIC_GOLD_PAN;
/*     */   public static ItemStack ELECTRIC_GOLD_PAN_2;
/*     */   public static ItemStack ELECTRIC_GOLD_PAN_3;
/*     */   public static ItemStack ELECTRIC_DUST_WASHER;
/*     */   public static ItemStack ELECTRIC_DUST_WASHER_2;
/*     */   public static ItemStack ELECTRIC_DUST_WASHER_3;
/*     */   public static ItemStack ELECTRIC_INGOT_FACTORY;
/*     */   public static ItemStack ELECTRIC_INGOT_FACTORY_2;
/*     */   public static ItemStack ELECTRIC_INGOT_FACTORY_3;
/*     */   public static ItemStack AUTOMATED_CRAFTING_CHAMBER;
/*     */   public static ItemStack FLUID_PUMP;
/*     */   public static ItemStack CHARGING_BENCH;
/*     */   public static ItemStack WITHER_ASSEMBLER;
/*     */   public static ItemStack TRASH_CAN;
/*     */   public static ItemStack ELYTRA;
/*     */   public static ItemStack ELYTRA_SCALE;
/*     */   public static ItemStack INFUSED_ELYTRA;
/*     */   public static ItemStack SOULBOUND_ELYTRA;
/*     */   public static ItemStack CHEST_TERMINAL;
/*     */   public static ItemStack CT_IMPORT_BUS;
/*     */   public static ItemStack CT_EXPORT_BUS;
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Lists\SlimefunItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */