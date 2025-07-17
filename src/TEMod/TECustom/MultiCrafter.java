package TEMod.TECustom;

import arc.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

public class MultiCrafter extends GenericCrafter {
    // 存储所有可用配方
    public Seq<Recipe> recipes = new Seq<>();
    // 当前选中的配方索引
    public int currentRecipe = 0;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;

        config(Integer.class, (MultiCrafterBuild build, Integer value) -> {
            if(recipes.size > 0) build.currentRecipe = value % recipes.size;
        });
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
                    tab.add(Core.bundle.format("misc.multicraft.inputItem") + input.item.emoji() + " " + input.amount).left();
                    tab.row();
                }
                // 显示输出物品
                for(ItemStack output : recipe.outputItems) {
                    tab.add(Core.bundle.format("misc.multicraft.outputItem") + output.item.emoji() + " " + output.amount).left();
                    tab.row();
                }
                // 显示输入流体
                for(LiquidStack input : recipe.inputLiquids) {
                    tab.add(Core.bundle.format("misc.multicraft.inputLiquid") + input.liquid.emoji() + " " + input.amount).left();
                    tab.row();
                }
                // 显示输出流体
                for(LiquidStack output : recipe.outputLiquids) {
                    tab.add(Core.bundle.format("misc.multicraft.outputLiquid") + output.liquid.emoji() + " " + output.amount).left();
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

            // 设置当前配方的合成时间
            craftTime = recipe.craftTime;
            super.updateTile();
        }

        @Override
        public boolean shouldConsume() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return false;

            // 验证物品输入
            for(ItemStack in : recipe.inputItems) {
                if(items.get(in.item) < in.amount) return false;
            }

            // 验证液体输入
            for(LiquidStack in : recipe.inputLiquids) {
                if(liquids.get(in.liquid) < in.amount) return false;
            }

            return enabled;
        }

        @Override
        public void consume() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return;

            // 消耗物品
            for(ItemStack in : recipe.inputItems) {
                items.remove(in.item, in.amount);
            }

            // 消耗液体
            for(LiquidStack in : recipe.inputLiquids) {
                liquids.remove(in.liquid, in.amount);
            }
        }

        @Override
        public void craft() {
            Recipe recipe = getCurrentRecipe();
            if(recipe != null) {
                consume();
                // 生成输出物品
                for(ItemStack out : recipe.outputItems) {
                    for(int i = 0; i < out.amount; i++) {
                        offload(out.item);
                    }
                }

                // 生成输出液体
                for(LiquidStack out : recipe.outputLiquids) {
                    liquids.add(out.liquid, out.amount);
                }

                if(wasVisible){
                    craftEffect.at(x, y);
                }
            }
        }

        @Override
        public Object config() {
            return currentRecipe;
        }

        @Override
        public void buildConfiguration(Table table) {
            // 自定义配方选择器
            table.button("\uE835", Styles.defaultt, () -> {
                currentRecipe = (currentRecipe + 1) % recipes.size;
                rebuildConfig(table);
            }).size(60).tooltip(Core.bundle.format("misc.multicraft.select-recipe"));

            table.table(Styles.black5, t -> {
                Recipe current = getCurrentRecipe();
                if(current == null) return;

                t.add(current.localizedName()).left();
                t.row();

                // 显示输入
                t.add(Core.bundle.format("misc.multicraft.input")).left();
                for(ItemStack in : current.inputItems) {
                    t.image(in.item.uiIcon).size(24).padRight(4);
                    t.add(in.amount + "").left().padRight(8);
                }
                for(LiquidStack in : current.inputLiquids) {
                    t.image(in.liquid.uiIcon).size(24).padRight(4);
                    t.add(in.amount + "").left().padRight(8);
                }
                t.row();

                // 显示输出
                t.add(Core.bundle.format("misc.multicraft.output")).left();
                for(ItemStack out : current.outputItems) {
                    t.image(out.item.uiIcon).size(24).padRight(4);
                    t.add(out.amount + "").left().padRight(8);
                }
                for(LiquidStack out : current.outputLiquids) {
                    t.image(out.liquid.uiIcon).size(24).padRight(4);
                    t.add(out.amount + "").left().padRight(8);
                }
            }).padLeft(8);
        }

        // 重建配置界面
        private void rebuildConfig(Table table) {
            table.clear();
            buildConfiguration(table);
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
            } else if(outputLiquids.length > 0) {
                return outputLiquids[0].liquid.localizedName;
            }
            return "未知配方";
        }
    }
}