package TEMLib;

import mindustry.type.ItemStack;
import mindustry.world.Block;

public class lib {//没什么用的lib
    public static void noop() {}

    public static ItemStack[] sizeWith(ItemStack[] stacks, Block source) {
        return ItemStack.mult(stacks, (float) Math.pow(source.size, 2));
    }

    public static ItemStack[] newWith(ItemStack[] origin, ItemStack[] addItems) {
        ItemStack[] out = new ItemStack[origin.length + addItems.length + 1];
        System.arraycopy(origin, 0, out, 0, origin.length);
        System.arraycopy(addItems, 0, out, origin.length + 1, addItems.length);
        return out;
    }

    public static ItemStack[] newMult(Block block, float amount) {
        return ItemStack.mult(block.requirements, amount);
    }
}
