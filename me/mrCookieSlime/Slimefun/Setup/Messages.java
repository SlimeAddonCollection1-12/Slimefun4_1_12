/*     */ package me.mrCookieSlime.Slimefun.Setup;
/*     */ 
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Localization;
/*     */ 
/*     */ public class Messages
/*     */ {
/*     */   public static Localization local;
/*     */   
/*     */   public static void setup() {
/*  10 */     local.setPrefix("&aSlimefun &7> ");
/*  11 */     local.setDefault("messages.not-researched", new String[] { "&4You do not have enough Knowledge to understand this" });
/*  12 */     local.setDefault("messages.not-enough-xp", new String[] { "&4You do not have enough XP to unlock this" });
/*  13 */     local.setDefault("messages.unlocked", new String[] { "&bYou have unlocked &7\"%research%\"" });
/*  14 */     local.setDefault("messages.fortune-cookie", new String[] { "&7Help me, I am trapped in a Fortune Cookie Factory!", "&7You will die tomorrow...     by a Creeper", "&7At some Point in your Life something bad will happen!!!", "&7Next week you will notice that this is not the real World, you are in a kind of \"Matrix\" or lets call it Computer game. Yes you are in a Computer Game", "&7This Cookie will taste good in a few seconds", "&7You will die soon and the last word you will hear is gonna be \"EXTERMINATE!!!\"", "&7Whatever you do, do not hug a Creeper... I tried it. It feels good, but it's not worth it." });
/*  15 */     local.setDefault("commands.help", new String[] { "Displays this Help Screen" });
/*  16 */     local.setDefault("commands.cheat", new String[] { "Allows you to cheat Items" });
/*  17 */     local.setDefault("commands.give", new String[] { "Give somebody some Slimefun Items" });
/*  18 */     local.setDefault("commands.research.desc", new String[] { "Unlock a Research for a Player" });
/*  19 */     local.setDefault("commands.guide", new String[] { "Gives yourself a Slimefun Guide" });
/*  20 */     local.setDefault("commands.timings", new String[] { "Lag-Info about your Server" });
/*  21 */     local.setDefault("commands.teleporter", new String[] { "See other Player's Waypoints" });
/*  22 */     local.setDefault("commands.versions", new String[] { "Lists all installed Addons" });
/*  23 */     local.setDefault("commands.open_guide", new String[] { "Opens Slimefun's guide without using the book" });
/*     */     
/*  25 */     local.setDefault("messages.only-players", new String[] { "&4This Command is only for Players" });
/*  26 */     local.setDefault("messages.no-permission", new String[] { "&4You do not have the required Permission to do this" });
/*  27 */     local.setDefault("messages.usage", new String[] { "&4Usage: &c%usage%" });
/*  28 */     local.setDefault("messages.not-online", new String[] { "&4%player% &cis not online!" });
/*  29 */     local.setDefault("messages.not-valid-item", new String[] { "&4%item% &cis not a valid Item!" });
/*  30 */     local.setDefault("messages.not-valid-amount", new String[] { "&4%amount% &cis not a valid amount : it must be higher than 0!" });
/*  31 */     local.setDefault("messages.given-item", new String[] { "&bYou have been given &a%amount% &7\"%item%&7\"" });
/*  32 */     local.setDefault("messages.give-item", new String[] { "&bYou have given %player% &a%amount% &7\"%item%&7\"" });
/*  33 */     local.setDefault("messages.not-valid-research", new String[] { "&4%research% &cis not a valid Research!" });
/*  34 */     local.setDefault("messages.give-research", new String[] { "&bYou have given %player% the Research &7\"%research%&7\"" });
/*  35 */     local.setDefault("messages.battery.add", new String[] { "&2+ &7%charge% J &8(%machine%)" });
/*  36 */     local.setDefault("messages.battery.remove", new String[] { "&4- &7%charge% J &8(%machine%)" });
/*  37 */     local.setDefault("messages.hungry", new String[] { "&cYou are too hungry to do that!" });
/*  38 */     local.setDefault("messages.mode-change", new String[] { "&b%device% Mode changed to: &9%mode%" });
/*  39 */     local.setDefault("messages.disabled-in-world", new String[] { "&4&lThis Item has been disabled in this World" });
/*  40 */     local.setDefault("messages.talisman.anvil", new String[] { "&a&oYour Talisman saved your Tool from breaking" });
/*  41 */     local.setDefault("messages.talisman.miner", new String[] { "&a&oYour Talisman just doubled your Drops" });
/*  42 */     local.setDefault("messages.talisman.hunter", new String[] { "&a&oYour Talisman just doubled your Drops" });
/*  43 */     local.setDefault("messages.talisman.lava", new String[] { "&a&oYour Talisman saved you from burning to death" });
/*  44 */     local.setDefault("messages.talisman.water", new String[] { "&a&oYour Talisman saved you from drowning" });
/*  45 */     local.setDefault("messages.talisman.angel", new String[] { "&a&oYour Talisman saved you from taking Fall Damage" });
/*  46 */     local.setDefault("messages.talisman.fire", new String[] { "&a&oYour Talisman saved you from burning to death" });
/*  47 */     local.setDefault("messages.talisman.magician", new String[] { "&a&oYour Talisman gave you an additional Enchantment" });
/*  48 */     local.setDefault("messages.talisman.traveller", new String[] { "&a&oYour Talisman gave you a Speed Boost" });
/*  49 */     local.setDefault("messages.talisman.warrior", new String[] { "&a&oYour Talisman has improved your Strength for a While" });
/*  50 */     local.setDefault("messages.talisman.knight", new String[] { "&a&oYour Talisman gave you 5 Seconds of Regeneration" });
/*  51 */     local.setDefault("messages.talisman.whirlwind", new String[] { "&a&oYour Talisman reflected the Projectile" });
/*  52 */     local.setDefault("messages.talisman.wizard", new String[] { "&a&oYour Talisman has given you a better Fortune Level but maybe also lowered some other Enchantment Levels" });
/*  53 */     local.setDefault("messages.broken-leg", new String[] { "&c&oSeems like you broke your Leg, maybe a Splint could help?" });
/*  54 */     local.setDefault("messages.fixed-leg", new String[] { "&a&oThe Splint helps. It feels better now." });
/*  55 */     local.setDefault("messages.start-bleeding", new String[] { "&c&oYou started to bleed. Maybe a Bandage could help?" });
/*  56 */     local.setDefault("messages.stop-bleeding", new String[] { "&a&oThe Bandage helps. The Bleeding has stopped!" });
/*  57 */     local.setDefault("messages.disabled-item", new String[] { "&4&lThis Item has been disabled! How did you even get that?" });
/*  58 */     local.setDefault("messages.research.start", new String[] { "&7The Ancient Spirits whisper mysterious Words into your Ear!" });
/*  59 */     local.setDefault("messages.research.progress", new String[] { "&7You start to wonder about &b%research% &e(%progress%)" });
/*  60 */     local.setDefault("commands.stats", new String[] { "Shows some Stats about a Player" });
/*  61 */     local.setDefault("messages.fire-extinguish", new String[] { "&7You have extinguished yourself" });
/*  62 */     local.setDefault("machines.pattern-not-found", new String[] { "&eSorry, I could not recognize this Pattern. Please place the Items in the correct Pattern into the Dispenser." });
/*  63 */     local.setDefault("machines.unknown-material", new String[] { "&eSorry, I could not recognize the Item in my Dispenser. Please put something in that I know." });
/*  64 */     local.setDefault("machines.wrong-item", new String[] { "&eSorry, I could not recognize the Item you right clicked me with. Check the Recipes and see which Items you can use." });
/*  65 */     local.setDefault("machines.full-inventory", new String[] { "&eSorry, my Inventory is too full!" });
/*  66 */     local.setDefault("miner.no-ores", new String[] { "&eSorry, I could not find any Ores nearby!" });
/*  67 */     local.setDefault("backpack.already-open", new String[] { "&cSorry, this backpack is open somewhere else!" });
/*  68 */     local.setDefault("backpack.no-stack", new String[] { "&cYou cannot stack Backpacks" });
/*  69 */     local.setDefault("workbench.not-enhanced", new String[] { "&4You cannot use Slimefun Items in a normal Workbench" });
/*  70 */     local.setDefault("anvil.not-working", new String[] { "&4You cannot use Slimefun Items in an Anvil" });
/*  71 */     local.setDefault("commands.research.reset", new String[] { "&cYou have reset %player%'s Knowledge" });
/*  72 */     local.setDefault("commands.research.reset-target", new String[] { "&cYour Knowledge has been reset" });
/*  73 */     local.setDefault("machines.in-use", new String[] { "&cThis Block's Inventory is currently opened by a different Player." });
/*  74 */     local.setDefault("machines.ignition-chamber-no-flint", new String[] { "&cIgnition Chamber is missing Flint and Steel." });
/*  75 */     local.setDefault("messages.cannot-place", new String[] { "&cYou cannot place that block there!" });
/*     */     
/*  77 */     local.setDefault("gps.waypoint.new", new String[] { "&ePlease type in a Name for your new Waypoint in the Chat. &7(Color Codes supported!)" });
/*  78 */     local.setDefault("gps.waypoint.added", new String[] { "&aSuccessfully added a new Waypoint" });
/*  79 */     local.setDefault("gps.waypoint.max", new String[] { "&4You have reached the Maximum Amount of Waypoints" });
/*  80 */     local.setDefault("gps.insufficient-complexity", new String[] { "&4Insufficient GPS Network Complexity: &c%complexity%", "&4a) You do not have a GPS Network setup yet", "&4b) Your GPS Network is not complex enough" });
/*  81 */     local.setDefault("gps.geo.scan-required", new String[] { "&4GEO-Scan required! &cScan this Chunk using a GEO-Scanner first!" });
/*     */     
/*  83 */     local.setDefault("robot.started", new String[] { "&7Your Robot resumed running its Script" });
/*  84 */     local.setDefault("robot.stopped", new String[] { "&7Your Robot has paused its Script" });
/*  85 */     local.setDefault("inventory.no-access", new String[] { "&4You are not permitted to access this Block" });
/*     */     
/*  87 */     local.setDefault("machines.ANCIENT_ALTAR.not-enough-pedestals", new String[] { "&4The Altar is not surrounded by the needed Amount of Pedestals &c(%pedestals% / 8)" });
/*  88 */     local.setDefault("machines.ANCIENT_ALTAR.unknown-catalyst", new String[] { "&4Unknown Catalyst! &cUse the correct Recipe instead!" });
/*  89 */     local.setDefault("machines.ANCIENT_ALTAR.unknown-recipe", new String[] { "&4Unknown Recipe! &cUse the correct Recipe instead!" });
/*  90 */     local.setDefault("machines.ANCIENT_PEDESTAL.obstructed", new String[] { "&4Pedestal is obstructed! &cRemove anything above the pedestal!" });
/*  91 */     local.setDefault("machines.HOLOGRAM_PROJECTOR.enter-text", new String[] { "&7Please enter your desired Hologram Text in your Chat. &r(Color Codes are supported!)" });
/*  92 */     local.setDefault("machines.ELEVATOR.no-destinations", new String[] { "&4No Destinations found" });
/*  93 */     local.setDefault("machines.CARGO_NODES.must-be-placed", new String[] { "&4Must be placed onto a Chest or Machine" });
/*     */     
/*  95 */     local.setDefault("android.scripts.already-uploaded", new String[] { "&4This Script has already been uploaded." });
/*  96 */     local.setDefault("android.scripts.enter-name", new String[] { "", "&ePlease type in a Name for your Script", "" });
/*  97 */     local.setDefault("android.scripts.uploaded", new String[] { "&bUploading...", "&aSuccessfully uploaded your Script!" });
/*  98 */     local.setDefault("android.scripts.rating.own", new String[] { "&4You cannot rate your own Script!" });
/*  99 */     local.setDefault("android.scripts.rating.already", new String[] { "&4You have already left a Rating for this Script!" });
/*     */     
/* 101 */     local.save();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Setup\Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */