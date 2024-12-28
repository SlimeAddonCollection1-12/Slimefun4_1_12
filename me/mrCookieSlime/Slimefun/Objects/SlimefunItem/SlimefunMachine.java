/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.MultiBlock;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlimefunMachine
/*    */   extends SlimefunItem
/*    */ {
/*    */   List<ItemStack[]> recipes;
/*    */   List<ItemStack> shownRecipes;
/*    */   Material trigger;
/*    */   
/*    */   public SlimefunMachine(Category category, ItemStack item, String id, ItemStack[] recipe, ItemStack[] machineRecipes, Material trigger) {
/* 21 */     super(category, item, id, RecipeType.MULTIBLOCK, recipe);
/* 22 */     this.recipes = (List)new ArrayList<>();
/* 23 */     this.shownRecipes = new ArrayList<>();
/* 24 */     for (ItemStack i : machineRecipes) {
/* 25 */       this.shownRecipes.add(i);
/*    */     }
/* 27 */     this.trigger = trigger;
/*    */   }
/*    */   
/*    */   public SlimefunMachine(Category category, ItemStack item, String id, ItemStack[] recipe, ItemStack[] machineRecipes, Material trigger, boolean ghost) {
/* 31 */     super(category, item, id, RecipeType.MULTIBLOCK, recipe, ghost);
/* 32 */     this.recipes = (List)new ArrayList<>();
/* 33 */     this.shownRecipes = new ArrayList<>();
/* 34 */     for (ItemStack i : machineRecipes) {
/* 35 */       this.shownRecipes.add(i);
/*    */     }
/* 37 */     this.trigger = trigger;
/*    */   }
/*    */   
/*    */   public SlimefunMachine(Category category, ItemStack item, String id, ItemStack[] recipe, ItemStack[] machineRecipes, Material trigger, String[] keys, Object[] values) {
/* 41 */     super(category, item, id, RecipeType.MULTIBLOCK, recipe, keys, values);
/* 42 */     this.recipes = (List)new ArrayList<>();
/* 43 */     this.shownRecipes = new ArrayList<>();
/* 44 */     for (ItemStack i : machineRecipes) {
/* 45 */       this.shownRecipes.add(i);
/*    */     }
/* 47 */     this.trigger = trigger;
/*    */   }
/*    */   
/*    */   public List<ItemStack[]> getRecipes() {
/* 51 */     return this.recipes;
/*    */   }
/*    */   
/*    */   public List<ItemStack> getDisplayRecipes() {
/* 55 */     return this.shownRecipes;
/*    */   }
/*    */   
/*    */   public void addRecipe(ItemStack[] input, ItemStack output) {
/* 59 */     this.recipes.add(input);
/* 60 */     this.recipes.add(new ItemStack[] { output });
/*    */   }
/*    */ 
/*    */   
/*    */   public void create() {
/* 65 */     toMultiBlock().register();
/*    */   }
/*    */ 
/*    */   
/*    */   public void install() {
/* 70 */     for (ItemStack i : getDisplayRecipes()) {
/* 71 */       SlimefunItem item = SlimefunItem.getByItem(i);
/* 72 */       if (item == null) { this.recipes.add(new ItemStack[] { i }); continue; }
/* 73 */        if (!SlimefunItem.isDisabled(i)) this.recipes.add(new ItemStack[] { i }); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public MultiBlock toMultiBlock() {
/* 78 */     List<Material> mats = new ArrayList<>();
/* 79 */     for (ItemStack i : getRecipe()) {
/* 80 */       if (i == null) { mats.add(null); }
/* 81 */       else if (i.getType() == Material.CAULDRON_ITEM) { mats.add(Material.CAULDRON); }
/* 82 */       else if (i.getType() == Material.FLINT_AND_STEEL) { mats.add(Material.FIRE); }
/* 83 */       else { mats.add(i.getType()); }
/*    */     
/* 85 */     }  Material[] build = mats.<Material>toArray(new Material[mats.size()]);
/* 86 */     return new MultiBlock(build, this.trigger);
/*    */   }
/*    */   
/*    */   public Iterator<ItemStack[]> recipeIterator() {
/* 90 */     return (Iterator)this.recipes.iterator();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\SlimefunMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */