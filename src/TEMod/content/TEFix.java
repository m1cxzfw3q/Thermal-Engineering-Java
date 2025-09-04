package TEMod.content;

import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.world.meta.Attribute;

public class TEFix {
    public static void load() {
        Items.graphite.hardness = 2;
        Blocks.sporeWall.attributes.set(Attribute.spores, 1f);
    }
}
