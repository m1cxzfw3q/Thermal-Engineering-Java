package TEMod.content;

import mindustry.world.meta.Attribute;

public class TEAttribute {
    public static Attribute sporeWall;

    public static void load() {
        sporeWall = Attribute.add("spore-wall");
    }
}
