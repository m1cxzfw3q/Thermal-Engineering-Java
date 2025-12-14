package TEMLib;

import arc.Core;
import arc.scene.ui.layout.Table;
import arc.struct.*;
import arc.graphics.Color;
import arc.util.Nullable;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.ConsumeItemDynamic;
import mindustry.world.consumers.ConsumeLiquidsDynamic;
import mindustry.world.meta.*;

public class MultiCrafter extends GenericCrafter {
    public @Nullable Seq<Recipe> recipes = new Seq<>();

    public static float uniCraftTime;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        config(Integer.class, (MultiCrafterBuild e, Integer i) -> {});

        consume(new ConsumeItemDynamic(
                (MultiCrafterBuild e) -> {
                    hasItems = e.currentRecipe.inputItems != ItemStack.empty;
                    return e.currentRecipeId != -1 ? recipes.get(Math.min(e.currentRecipeId, recipes.size - 1)).inputItems
                            : ItemStack.empty;
                }
        ));
        consume(new ConsumeLiquidsDynamic(
                (MultiCrafterBuild e) -> {
                    hasLiquids = e.currentRecipe.inputLiquids != LiquidStack.empty;
                    return e.currentRecipeId != -1 ? recipes.get(Math.min(e.currentRecipeId, recipes.size - 1)).inputLiquids
                            : LiquidStack.empty;
                }
        ));
    }

    @Override
    public void load(){
        super.load();
    }

    @Override
    public boolean outputsItems() {
        return true;
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.output);
        stats.remove(Stat.productionTime);
        stats.add(Stat.productionTime, uniCraftTime / 60f, StatUnit.seconds);
        stats.add(Stat.output, table -> {
            table.row();

            final int[] i = {0};
            for (Recipe recipe : recipes) {
                table.table(Styles.grayPanel, t -> {
                    t.left();
                    t.add("[#ffd37f][" + i[0] + "][]");
                    i[0]++;
                });
                lib.itemsDisplay(recipe.inputItems, table, recipe.craftTime);
                lib.liquidsDisplay(recipe.inputLiquids, table);
                table.table(Styles.grayPanel, t -> t.image(Icon.right).color(Pal.darkishGray).size(40).pad(5f)).fill();
                lib.itemsDisplay(recipe.outputItems, table, recipe.craftTime);
                lib.liquidsDisplay(recipe.outputLiquids, table);
                table.row();
            }
        });
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("recipe", e -> new Bar(
                () -> Core.bundle.get("bar.recipe"),
                () -> Color.valueOf("4169e1"),
                () -> 1
        ));
    }

    /// 正在重写
    public static class MultiCrafterBuild extends Building {
        public int currentRecipeId = -1;
        public @Nullable Recipe currentRecipe = getCurrentRecipe(currentRecipeId);

        @Override
        public void buildConfiguration(Table table) {

        }

        @Override
        public boolean shouldConsume() {
            return this.enabled;
        }

        @Override
        public void updateTile() {
            currentRecipe = getCurrentRecipe(currentRecipeId);
        }

        public Recipe getCurrentRecipe(int id) {
            if (id == -1) return null;
            return ((MultiCrafter) block).recipes.get(id);
        }
    }

    public static class Recipe {
        public ItemStack[] inputItems = {}, outputItems = {};
        public float craftTime = 60f;
        public LiquidStack[] inputLiquids = {}, outputLiquids = {};

        public Recipe() {}

        public Recipe(StackItemLiquid input, StackItemLiquid output) {
            inputItems = input.items;
            outputItems = output.items;
            inputLiquids = input.liquids;
            outputLiquids = output.liquids;
        }
        public Recipe(StackItemLiquid input, StackItemLiquid output, float craftTime) {
            inputItems = input.items;
            outputItems = output.items;
            inputLiquids = input.liquids;
            outputLiquids = output.liquids;
            this.craftTime = craftTime;
        }

        public String localizedName() {
            if(outputItems != null && outputItems.length > 0 && outputItems[0] != null) {
                return outputItems[0].item.localizedName + " [white]" + outputItems[0].item.emoji() + "[]";
            } else if(outputLiquids != null && outputLiquids.length > 0 && outputLiquids[0] != null) {
                return outputLiquids[0].liquid.localizedName + " [white]" + outputLiquids[0].liquid.emoji() + "[]";
            }
            return "未知配方";
        }
    }
}