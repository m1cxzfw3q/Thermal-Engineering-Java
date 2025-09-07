package TEMod.TECustom;

import arc.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

import static mindustry.Vars.content;

public class MultiCrafter extends GenericCrafter {
    // 存储所有可用配方
    public Seq<Recipe> recipes = new Seq<>();

    // 修改：存储必需流体及其消耗量
    public Seq<LiquidStack> requiredLiquids = new Seq<>();
    public static float uniCraftTime;

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;

        // 关键修复：启用物品输出和传送带连接
        hasItems = true;

        if (uniCraftTime > 0) craftTime = uniCraftTime;

        config(Integer.class, (MultiCrafterBuild build, Integer value) -> {
            if(recipes.size > 0) {
                int newRecipe = value % recipes.size;
                // 仅在配方改变时执行清理
                if(build.currentRecipe != newRecipe) {
                    build.switchRecipe(newRecipe);
                }
            }
        });
    }

    // 修改：添加必需流体及其消耗量
    public void requiresLiquid(Liquid liquid, float amount) {
        requiredLiquids.add(new LiquidStack(liquid, amount));
        consumeLiquid(liquid, amount);
    }

    // 关键修复：重写输出连接方法
    @Override
    public boolean outputsItems() {
        return true; // 确保工厂被识别为物品输出源
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.output);
        // 显示所有配方
        for(int i = 0; i < recipes.size; i++) {
            Recipe recipe = recipes.get(i);
            int finalI = i;
            stats.add(Stat.output, t -> {
                t.row();
                t.table(Styles.grayPanel, tab -> {
                    tab.add("[accent]" + (finalI + 1) + ". " + recipe.localizedName()).left().row();
                    if (recipe.inputItems != null) {
                        tab.add(Core.bundle.format("misc.multicraft.inputItem") + "[");
                        tab.row();
                        for (ItemStack input : recipe.inputItems) {
                            tab.add("     " + input.item.emoji() + " " + input.amount).left();
                            tab.row();
                        }
                        tab.row();
                        tab.add("]");
                    }
                    if (recipe.outputItems != null){
                        tab.add(Core.bundle.format("misc.multicraft.outputItem") + "[");
                        for (ItemStack output : recipe.outputItems) {
                            tab.add("     " + output.item.emoji() + " " + output.amount).left();
                            tab.row();
                        }
                        tab.row();
                        tab.add("]");
                    }
                    if (recipe.inputLiquids != null){
                        tab.add(Core.bundle.format("misc.multicraft.inputLiquid") + "[");
                        for (LiquidStack input : recipe.inputLiquids) {
                            tab.add("     " + input.liquid.emoji() + " " + input.amount).left();
                            tab.row();
                        }
                        tab.row();
                        tab.add("]");
                    }
                    if (recipe.outputLiquids != null){
                        tab.add(Core.bundle.format("misc.multicraft.outputLiquid") + "[");
                        for (LiquidStack output : recipe.outputLiquids) {
                            tab.add("     " + output.liquid.emoji() + " " + output.amount).left();
                            tab.row();
                        }
                        tab.row();
                        tab.add("]");
                    }
                }).padTop(8);
            });
        }
    }

    public class MultiCrafterBuild extends GenericCrafterBuild {
        public int currentRecipe = 0;
        private @Nullable Recipe lastRecipe;
        private float partialProgress = 0f; // 新增：跟踪部分进度


        public void switchRecipe(int newRecipe) {
            Recipe nextRecipe = recipes.get(newRecipe);
            Seq<Item> neededItems = new Seq<>();

            // 收集新配方所需物品
            for(ItemStack stack : nextRecipe.inputItems) {
                neededItems.add(stack.item);
            }

            // 完全清理非新配方需要的物品（包括产物）
            for(int i = 0; i < items.length(); i++) {
                Item item = content.item(i);
                int currentAmount = items.get(i);
                if(currentAmount > 0 && !neededItems.contains(item)) {
                    // 直接输出全部多余物品
                    items.set(item, 0); // 使用索引正确设置数量
                    for (int j = 0; j < currentAmount; j++) {
                        offload(item);
                    }
                }
            }

            // 重置生产状态
            currentRecipe = newRecipe;
            progress = 0;
            lastRecipe = null;
        }

        @Override
        public void updateTile() {
            // 先尝试输出物品和液体
            dumpOutputs();

            Recipe recipe = getCurrentRecipe();

            // 配方无效时停止生产
            if(recipe == null) {
                progress = 0;
                partialProgress = 0f; // 重置部分进度
                return;
            }

            // 配方发生变化时重置进度
            if(lastRecipe != recipe) {
                progress = 0;
                partialProgress = 0f; // 重置部分进度
                lastRecipe = recipe;
            }

            // 设置当前配方的合成时间
            craftTime = recipe.craftTime;
            if (uniCraftTime > 0) craftTime = uniCraftTime;

            // 关键修复：使用部分进度跟踪，防止过度生产
            if(shouldConsume() && enabled) {
                // 修复：使用 efficiency 字段而不是 efficiency() 方法
                float progressDelta = efficiency * edelta();

                // 累计部分进度
                partialProgress += progressDelta;

                // 当部分进度达到完整生产周期时执行生产
                if(partialProgress >= craftTime) {
                    int productionCycles = (int)(partialProgress / craftTime);
                    partialProgress %= craftTime; // 保留剩余进度

                    // 执行生产循环
                    for(int i = 0; i < productionCycles; i++) {
                        if(shouldConsume()) { // 每次生产前都检查条件
                            craft();
                        } else {
                            break; // 条件不满足时停止生产
                        }
                    }
                }
            } else {
                partialProgress = 0f; // 条件不满足时重置进度
            }
        }

        // 关键修复：自动输出方法 - 只输出产品，不输出原料
        public void dumpOutputs() {
            Recipe recipe = getCurrentRecipe();
            if(recipe == null) return;

            // 只输出配方中的产品物品
            for(ItemStack out : recipe.outputItems) {
                if(items.get(out.item) > 0) {
                    dump(out.item);
                }
            }

            // 输出所有液体（包括产品液体）- 修复：使用正确的液体量检查方法
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

            // 关键修复：检查必需流体是否足够 - 增加空安全检查
            if(requiredLiquids != null) {
                for(LiquidStack required : ((MultiCrafter)block).requiredLiquids) {
                    if(required != null && liquids != null) {
                        if(liquids.get(required.liquid) < required.amount) {
                            return false;
                        }
                    }
                }
            }

            // 验证物品输入
            for(ItemStack in : recipe.inputItems) {
                if(items.get(in.item) < in.amount) return false;
            }

            // 验证液体输入 - 增加空安全检查
            if(recipe.inputLiquids != null && liquids != null) {
                for(LiquidStack in : recipe.inputLiquids) {
                    if(in != null && liquids.get(in.liquid) < in.amount) {
                        return false;
                    }
                }
            }

            // 关键修复：检查输出空间是否足够
            // 检查物品输出空间 - 更严格的检查
            for(ItemStack out : recipe.outputItems) {
                // 考虑当前库存和本次产出
                int currentAmount = items.get(out.item);
                if(currentAmount + out.amount > itemCapacity) {
                    return false;
                }
            }

            // 检查液体输出空间 - 更严格的检查（修复：使用正确的液体量检查方法）
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

            // 修复：消耗必需流体
            MultiCrafter block = (MultiCrafter) this.block;
            if(block.requiredLiquids != null && liquids != null) {
                for(LiquidStack required : block.requiredLiquids) {
                    if(required != null) {
                        liquids.remove(required.liquid, required.amount);
                    }
                }
            }

            // 消耗物品
            for(ItemStack in : recipe.inputItems) {
                items.remove(in.item, in.amount);
            }

            // 消耗液体
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

            // 检查物品是否是当前配方需要的
            for (ItemStack input : recipe.inputItems) {
                if (input.item == item) {
                    // 检查物品数量是否达到容量上限
                    return items.get(item) < itemCapacity;
                }
            }

            return false;
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            // 修改：首先检查必需流体 - 增加空安全检查
            if(requiredLiquids != null && liquids != null) {
                for(LiquidStack required : ((MultiCrafter)block).requiredLiquids) {
                    if(required != null && required.liquid == liquid) {
                        return liquids.get(liquid) + required.amount <= liquidCapacity;
                    }
                }
            }

            // 然后检查当前配方需要的流体
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

            // 增强：严格检查输出空间
            boolean canProduce = true;

            // 检查物品输出空间
            for(ItemStack out : recipe.outputItems) {
                // 修复：考虑堆叠限制
                if(items.get(out.item) + out.amount > itemCapacity) {
                    canProduce = false;
                    break;
                }
            }

            // 检查液体输出空间
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

            // 关键修复：再次检查输入物品是否足够（防御性检查）
            for(ItemStack in : recipe.inputItems) {
                if(items.get(in.item) < in.amount) {
                    return;
                }
            }

            consume();

            // 添加产出物品 - 确保只添加配方设定的数量
            for(ItemStack out : recipe.outputItems) {
                items.add(out.item, out.amount);
            }

            // 添加产出液体
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
                if(current.inputLiquids != null) {
                    for(LiquidStack in : current.inputLiquids) {
                        if(in != null) {
                            t.image(in.liquid.uiIcon).size(24).padRight(4);
                            t.add(in.amount + "").left().padRight(8);
                        }
                    }
                }
                t.row();

                // 显示输出
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

                // 新增：显示必需流体及消耗量 - 增加空安全检查
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

        // 重建配置界面
        private void rebuildConfig(Table table) {
            table.clear();
            buildConfiguration(table);
        }

        private @Nullable Recipe getCurrentRecipe() {
            if(recipes == null || recipes.isEmpty()) return null;
            return recipes.get(currentRecipe % recipes.size);
        }

        // 关键修复：允许液体输出
        @Override
        public boolean canDumpLiquid(Building to, Liquid liquid) {
            return true;
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
            if(outputItems != null && outputItems.length > 0 && outputItems[0] != null) {
                return outputItems[0].item.localizedName;
            } else if(outputLiquids != null && outputLiquids.length > 0 && outputLiquids[0] != null) {
                return outputLiquids[0].liquid.localizedName;
            }
            return "未知配方";
        }
    }
}