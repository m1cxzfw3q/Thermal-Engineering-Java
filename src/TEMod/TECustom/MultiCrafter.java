package TEMod.TECustom;

import arc.*;
import arc.scene.ui.layout.Table;
import arc.struct.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.ui.Styles;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

import static mindustry.Vars.content;

public class MultiCrafter extends GenericCrafter {
    // 存储所有可用配方
    public Seq<Recipe> recipes = new Seq<>();
    // 当前选中的配方索引
    public int currentRecipe = 0;
    private Recipe[] recipe;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;

        config(Integer.class, (MultiCrafterBuild build, Integer value) -> build.currentRecipe = value % recipes.size);
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.output);
        // 显示所有配方
        for(int i = 0; i < recipes.size; i++) {
            Recipe recipe = recipes.get(i);
            int finalI = i;
            stats.add(Stat.output, t -> t.table(Styles.grayPanel, tab -> {
                tab.add("[accent]" + (finalI +1) + ". " + recipe.localizedName()).left().row();
                // 显示输入物品
                for(ItemStack input : recipe.inputItems) {
                    tab.add(Core.bundle.format("misc.inputItem", input.item.emoji() + " " + input.amount)).left();
                    tab.row();
                }
                // 显示输出物品
                for(ItemStack output : recipe.outputItems) {
                    tab.add(Core.bundle.format("misc.outputItem", output.item.emoji() + " " + output.amount)).left();
                    tab.row();
                }
                // 显示输入流体
                for(LiquidStack input : recipe.inputLiquids) {
                    tab.add(Core.bundle.format("misc.inputLiquid", input.liquid.emoji() + " " + input.amount)).left();
                    tab.row();
                }
                // 显示输出流体
                for(LiquidStack output : recipe.outputLiquids) {
                    tab.add(Core.bundle.format("misc.outputLiquid", output.liquid.emoji() + " " + output.amount)).left();
                    tab.row();
                }
            }).padTop(8));
        }
    }

    public class MultiCrafterBuild extends GenericCrafterBuild {
        public int currentRecipe = 0;
        private @Nullable Recipe lastRecipe;

        @Override
        public void updateTile() {
            Recipe recipe = getCurrentRecipe();

            // 配方无效时停止生产
            if(recipe == null) {
                progress = 0;
                return;
            }

            // 配方发生变化时重置进度
            if(lastRecipe != recipe) {
                progress = 0;
                lastRecipe = recipe;
            }

            // 设置当前配方到父类
            MultiCrafter.this.recipe = new Recipe[]{recipe};
            super.updateTile();
        }

        @Override
        public boolean shouldConsume() {
            Recipe recipe = getCurrentRecipe();
            return recipe != null && items.has(recipe.inputItems);
        }

        @Override
        public void craft() {
            Recipe recipe = getCurrentRecipe();
            if(recipe != null) {
                float inc = getProgressIncrease(1f);
                // 消耗输入
                consume();
                // 生成输出
                for (ItemStack output : recipe.outputItems) {
                    for (int i = 0; i < output.amount; i++) {
                        offload(output.item);
                    }
                }
                for (LiquidStack output : recipe.outputLiquids) {
                    for (int i = 0; i < output.amount; i++) {
                        handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
                    }
                }

                if(wasVisible){
                    craftEffect.at(x, y);
                }
                progress %= 1f;
            }
        }

        @Override
        public Object config() {
            return currentRecipe;
        }

        @Override
        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(table, content.items(), () -> {
                Recipe r = getCurrentRecipe();
                return r != null ? r.outputItems[0].item : null;
            }, item -> {
                // 查找包含该物品输出的配方
                int index = recipes.indexOf(r -> r.outputItems[0].item == item);
                if(index >= 0) configure(index);
            });
        }

        private @Nullable Recipe getCurrentRecipe() {
            if(recipes.isEmpty()) return null;
            return recipes.get(currentRecipe % recipes.size);
        }
    }

    // 配方数据结构
    public static class Recipe {
        public ItemStack[] inputItems = {};
        public ItemStack[] outputItems = {};
        public float craftTime = 60f;
        public LiquidStack[] inputLiquids = {};
        public LiquidStack[] outputLiquids = {};

        public Recipe() {}

        public Recipe(ItemStack[] input, ItemStack[] output) {
            inputItems = input;
            outputItems = output;
        }

        public Recipe(LiquidStack[] input, ItemStack[] output) {
            inputLiquids = input;
            outputItems = output;
        }

        public Recipe(LiquidStack[] input, LiquidStack[] output) {
            inputLiquids = input;
            outputLiquids = output;
        }

        public Recipe(ItemStack[] input, LiquidStack[] output) {
            inputItems = input;
            outputLiquids = output;
        }

        public Recipe(LiquidStack[] input, LiquidStack[] output, float craftTime) {
            inputLiquids = input;
            outputLiquids = output;
            this.craftTime = craftTime;
        }

        public Recipe(ItemStack[] input, LiquidStack[] output, float craftTime) {
            inputItems = input;
            outputLiquids = output;
            this.craftTime = craftTime;
        }

        public Recipe(LiquidStack[] input, ItemStack[] output, float craftTime) {
            inputLiquids = input;
            outputItems = output;
            this.craftTime = craftTime;
        }

        public Recipe(ItemStack[] input, ItemStack[] output, float craftTime) {
            inputItems = input;
            outputItems = output;
            this.craftTime = craftTime;
        }

        public String localizedName() {
            if(outputItems.length > 0) {
                return outputItems[0].item.localizedName;
            }
            return "Unknown Recipe";
        }
    }
}