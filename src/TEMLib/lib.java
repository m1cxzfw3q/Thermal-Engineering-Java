package TEMLib;

import mindustry.type.ItemStack;
import mindustry.world.Block;

public class lib {
    public static void noop() {}

    public static ItemStack[] sizeWith(ItemStack[] stacks, Block source) {
        return ItemStack.mult(stacks, (float) Math.pow(source.size, 2));
    }

    public static ItemStack[] newWith(ItemStack[] origin, ItemStack[] addItems) {
        ItemStack[] out = new ItemStack[origin.length + addItems.length + 2];
        System.arraycopy(origin, 0, out, 0, origin.length);
        System.arraycopy(addItems, 0, out, origin.length + 1, addItems.length);
        return out;
    }
}
