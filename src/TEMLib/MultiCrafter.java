package TEMLib;

import arc.Core;
import arc.scene.ui.layout.Table;
import arc.struct.*;
import arc.graphics.Color;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.Sorter;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

import java.util.Arrays;

public class MultiCrafter extends Block {
    public Seq<Recipe> recipes = new Seq<>();

    public static float uniCraftTime;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        hasItems = true;
        config(Item.class, (Sorter.SorterBuild tile, Item item) -> tile.sortItem = item);
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

    /// 等待重写
    public static class MultiCrafterBuild extends Building {
        //public static int recipeId = 0;

        @Override
        public void buildConfiguration(Table table) {

        }
    }

    public static class Recipe {
        public ItemStack[] inputItems = {}, outputItems = {};
        public float craftTime = 60f;
        public LiquidStack[] inputLiquids = {}, outputLiquids = {};

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
            if(outputItems != null && outputItems.length > 0 && outputItems[0] != null) {
                return outputItems[0].item.localizedName + " [white]" + outputItems[0].item.emoji() + "[]";
            } else if(outputLiquids != null && outputLiquids.length > 0 && outputLiquids[0] != null) {
                return outputLiquids[0].liquid.localizedName + " [white]" + outputLiquids[0].liquid.emoji() + "[]";
            }
            return "未知配方";
        }
    }
}