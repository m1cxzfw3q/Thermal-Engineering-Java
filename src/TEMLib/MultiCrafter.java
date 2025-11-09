package TEMLib;

import arc.scene.ui.layout.Table;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.distribution.Sorter;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

public class MultiCrafter extends GenericCrafter {
    public Seq<Recipe> recipes = new Seq<>();
    public Seq<LiquidStack> requiredLiquids = new Seq<>();

    public static float uniCraftTime;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        hasItems = true;
        config(Item.class, (Sorter.SorterBuild tile, Item item) -> tile.sortItem = item);
    }

    public void requiresLiquid(Liquid liquid, float amount) {
        requiredLiquids.add(new LiquidStack(liquid, amount));
        consumeLiquid(liquid, amount);
    }

    @Override
    public boolean outputsItems() {
        return true;
    }

    @Override
    public void setStats() {
        int i = 0;
        super.setStats();
        stats.remove(Stat.output);
        stats.remove(Stat.productionTime);
        stats.add(Stat.productionTime, uniCraftTime / 60f, StatUnit.seconds);

        int finalI = i;
        stats.add(Stat.output, table -> {//GUIDE
            table.row();

            table.table(Styles.grayPanel, t -> {
                t.left();
                t.add("[#ffd37f][" + finalI + "][]");
            });
            for (Recipe recipe : recipes) {
                try {
                    for (ItemStack it : recipe.inputItems) {
                        table.table(Styles.grayPanel, t -> {
                            t.left();
                            t.add(StatValues.displayItem(it.item, it.amount, recipe.craftTime, true)).pad(5f);
                        });
                    }
                } catch (Exception ignored) {}

                try {
                    for (LiquidStack it : recipe.inputLiquids) {
                        table.table(Styles.grayPanel, t -> {
                            t.left();
                            t.add(StatValues.displayLiquid(it.liquid, it.amount, true)).pad(5f);
                        });
                    }
                } catch (Exception ignored) {}
                
                table.table(Styles.grayPanel, t -> t.image(Icon.right).color(Pal.darkishGray).size(40).pad(5f)).fill();

                try {
                    for (ItemStack it : recipe.outputItems) {
                        table.table(Styles.grayPanel, t -> {
                            t.left();
                            t.add(StatValues.displayItem(it.item, it.amount, recipe.craftTime, true)).pad(5f);
                        });
                    }
                } catch (Exception ignored) {}

                try {
                    for (LiquidStack it : recipe.outputLiquids) {
                        table.table(Styles.grayPanel, t -> {
                            t.left();
                            t.add(StatValues.displayLiquid(it.liquid, it.amount, true)).pad(5f);
                        });
                    }
                } catch (Exception ignored) {}

                table.row();
            }
        });
        i++;
    }

    /// 等待重写
    public class MultiCrafterBuild extends GenericCrafterBuild {
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