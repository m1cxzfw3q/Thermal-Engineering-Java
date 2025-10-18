package TEMLib;

import mindustry.type.ItemStack;
import mindustry.world.Block;

public class lib {//没什么用的lib
    public static void noop() {}

    public static ItemStack[] sizeWith(ItemStack[] stacks, Block source) {
        return ItemStack.mult(stacks, (float) Math.pow(source.size, 2));
    }
}
