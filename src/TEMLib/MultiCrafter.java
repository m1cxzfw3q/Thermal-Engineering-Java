package TEMLib;

import arc.*;
import arc.scene.Element;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

import static mindustry.Vars.content;

public class MultiCrafter extends GenericCrafter {
    public Seq<Recipe> recipes = new Seq<>();
    public Seq<LiquidStack> requiredLiquids = new Seq<>();

    public static float uniCraftTime;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        hasItems = true;
        config(Integer.class, (MultiCrafterBuild build, Integer value) -> {
            if(recipes.size > 0) {
                int newRecipe = value % recipes.size;
                if(build.currentRecipe != newRecipe) {
                    build.switchRecipe(newRecipe);
                }
            }
        });
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
        super.setStats();
        stats.remove(Stat.output);
        stats.remove(Stat.productionTime);
        stats.add(Stat.productionTime, uniCraftTime / 60f, StatUnit.seconds);

        stats.add(Stat.output, table -> {//GUIDE
            table.row();
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
    }

    public class MultiCrafterBuild extends GenericCrafterBuild {
        public int currentRecipe = 0;
        private @Nullable Recipe lastRecipe;
        private float partialProgress = 0f;


        public void switchRecipe(int newRecipe) {
            Recipe nextRecipe = recipes.get(newRecipe);
            Seq<Item> neededItems = new Seq<>();

            for(ItemStack stack : nextRecipe.inputItems) {
                neededItems.add(stack.item);
            }

            for(int i = 0; i < items.length(); i++) {
                Item item = content.item(i);
                int currentAmount = items.get(i);
                if(currentAmount > 0 && !neededItems.contains(item)) {
                    items.set(item, 0);
                    for (int j = 0; j < currentAmount; j++) {
                        offload(item);
                    }
                }
            }

            currentRecipe = newRecipe;
            progress = 0;
            lastRecipe = null;
        }

        @Override
        public void updateTile() {
            dumpOutputs();

            Recipe recipe = getCurrentRecipe();

            if(recipe == null) {
                progress = 0;
                partialProgress = 0f;
                return;
            }

            if(lastRecipe != recipe) {
                progress = 0;
                partialProgress = 0f;
                lastRecipe = recipe;
            }

            if (uniCraftTime > 0) craftTime = uniCraftTime;
            else craftTime = recipe.craftTime;

            if(shouldConsume() && enabled) {
                float progressDelta = efficiency * edelta();

                partialProgress += progressDelta;
                progress = partialProgress / craftTime;

                if(partialProgress >= craftTime) {
                    int productionCycles = (int)(partialProgress / craftTime);
                    partialProgress %= craftTime;

                    for(int i = 0; i < productionCycles; i++) {
                        if(shouldConsume()) {
                            craft();
                        } else break;
                    }
                }
            } else {
                partialProgress = 0f;
                progress = 0;
            }
        }

        public void dumpOutputs() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return;

            for(ItemStack out : recipe.outputItems) {
                if(items.get(out.item) > 0) {
                    dump(out.item);
                }
            }

            for(LiquidStack out : recipe.outputLiquids) {
                if(out.liquid != null && out.amount > 0.001f) {
                    dumpLiquid(out.liquid);
                }
            }
        }

        @Override
        public boolean shouldConsume() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return false;

            if(requiredLiquids != null) {
                for(LiquidStack required : ((MultiCrafter)block).requiredLiquids) {
                    if(required != null && liquids != null) {
                        if(liquids.get(required.liquid) < required.amount) {
                            return false;
                        }
                    }
                }
            }

            for(ItemStack in : recipe.inputItems) {
                if(items.get(in.item) < in.amount) return false;
            }

            if(recipe.inputLiquids != null && liquids != null) {
                for(LiquidStack in : recipe.inputLiquids) {
                    if(in != null && liquids.get(in.liquid) < in.amount) {
                        return false;
                    }
                }
            }

            for(ItemStack out : recipe.outputItems) {
                int currentAmount = items.get(out.item);
                if(currentAmount + out.amount > itemCapacity) {
                    return false;
                }
            }

            if(liquids != null) {
                float currentLiquidAmount = liquids.currentAmount();
                float totalOutput = 0f;

                if(recipe.outputLiquids != null) {
                    for(LiquidStack out : recipe.outputLiquids) {
                        if(out != null) {
                            totalOutput += out.amount;
                        }
                    }
                }

                if(currentLiquidAmount + totalOutput > liquidCapacity) {
                    return false;
                }
            }

            return enabled;
        }

        @Override
        public void consume() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return;

            MultiCrafter block = (MultiCrafter) this.block;
            if(block.requiredLiquids != null && liquids != null) {
                for(LiquidStack required : block.requiredLiquids) {
                    if(required != null) {
                        liquids.remove(required.liquid, required.amount);
                    }
                }
            }

            for(ItemStack in : recipe.inputItems) {
                items.remove(in.item, in.amount);
            }

            if(recipe.inputLiquids != null && liquids != null) {
                for(LiquidStack in : recipe.inputLiquids) {
                    if(in != null) {
                        liquids.remove(in.liquid, in.amount);
                    }
                }
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            Recipe recipe = getCurrentRecipe();
            if (recipe == null) return false;

            for (ItemStack input : recipe.inputItems) {
                if (input.item == item) {
                    return items.get(item) < itemCapacity;
                }
            }

            return false;
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            if(requiredLiquids != null && liquids != null) {
                for(LiquidStack required : ((MultiCrafter)block).requiredLiquids) {
                    if(required != null && required.liquid == liquid) {
                        return liquids.get(liquid) + required.amount <= liquidCapacity;
                    }
                }
            }

            Recipe recipe = getCurrentRecipe();
            if (recipe == null || recipe.inputLiquids == null || liquids == null) return false;

            for (LiquidStack input : recipe.inputLiquids) {
                if (input != null && input.liquid == liquid) {
                    return liquids.get(liquid) + input.amount <= liquidCapacity;
                }
            }

            return false;
        }

        @Override
        public void craft() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return;

            boolean canProduce = true;

            for(ItemStack out : recipe.outputItems) {
                if(items.get(out.item) + out.amount > itemCapacity) {
                    canProduce = false;
                    break;
                }
            }

            if(canProduce && liquids != null) {
                float currentTotal = liquids.currentAmount();
                float outputTotal = 0f;

                if(recipe.outputLiquids != null) {
                    for(LiquidStack out : recipe.outputLiquids) {
                        if(out != null) {
                            outputTotal += out.amount;
                        }
                    }
                }

                if(currentTotal + outputTotal > liquidCapacity) {
                    canProduce = false;
                }
            }

            if(!canProduce) {
                progress = 0;
                return;
            }

            for(ItemStack in : recipe.inputItems) {
                if(items.get(in.item) < in.amount) {
                    return;
                }
            }

            consume();

            for(ItemStack out : recipe.outputItems) {
                items.add(out.item, out.amount);
            }

            if(liquids != null && recipe.outputLiquids != null) {
                for(LiquidStack out : recipe.outputLiquids) {
                    if(out != null) {
                        liquids.add(out.liquid, out.amount);
                    }
                }
            }

            if(wasVisible){
                craftEffect.at(x, y);
            }
        }

        @Override
        public Object config() {
            return currentRecipe;
        }

        @Override
        public void buildConfiguration(Table table) {
            table.button("\uE835", Styles.defaultt, () -> {
                currentRecipe = (currentRecipe + 1) % recipes.size;
                rebuildConfig(table);
            }).size(30).tooltip(Core.bundle.format("misc.multicraft.select-recipe"));

            table.table(Styles.black5, t -> {
                Recipe current = getCurrentRecipe();
                if(current == null) return;

                t.add(current.localizedName()).left();
                t.row();


                t.add(Core.bundle.format("misc.multicraft.input")).left();
                for(ItemStack in : current.inputItems) {
                    t.image(in.item.uiIcon).size(24).padRight(4);
                    t.add(in.amount + "").left().padRight(8);
                }
                if(current.inputLiquids != null) {
                    for(LiquidStack in : current.inputLiquids) {
                        if(in != null) {
                            t.image(in.liquid.uiIcon).size(24).padRight(4);
                            t.add(in.amount + "").left().padRight(8);
                        }
                    }
                }
                t.row();

                t.add(Core.bundle.format("misc.multicraft.output")).left();
                for(ItemStack out : current.outputItems) {
                    t.image(out.item.uiIcon).size(24).padRight(4);
                    t.add(out.amount + "").left().padRight(8);
                }
                if(current.outputLiquids != null) {
                    for(LiquidStack out : current.outputLiquids) {
                        if(out != null) {
                            t.image(out.liquid.uiIcon).size(24).padRight(4);
                            t.add(out.amount + "").left().padRight(8);
                        }
                    }
                }
                t.row();

                MultiCrafter block = (MultiCrafter) this.block;
                if(block.requiredLiquids != null && !block.requiredLiquids.isEmpty()) {
                    t.row();
                    t.add(Core.bundle.format("misc.multicraft.requiredLiquids")).left().row();
                    for(LiquidStack required : block.requiredLiquids) {
                        if(required != null && required.liquid != null) {
                            t.image(required.liquid.uiIcon).size(24).padRight(4);
                            t.add(required.liquid.localizedName + (required.amount * 60) + "/s").left().padRight(8);
                            t.row();
                        }
                    }
                }
            }).padLeft(8);
        }

        private void rebuildConfig(Table table) {
            table.clear();
            buildConfiguration(table);
        }

        private @Nullable Recipe getCurrentRecipe() {
            if(recipes == null || recipes.isEmpty()) return null;
            return recipes.get(currentRecipe % recipes.size);
        }

        @Override
        public boolean canDumpLiquid(Building to, Liquid liquid) {
            return true;
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