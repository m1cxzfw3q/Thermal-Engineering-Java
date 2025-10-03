package TEMod.content;

import mindustry.world.meta.Attribute;

public class TEAttribute {
    public static Attribute sporeWalls;

    public static void load() {
        sporeWalls = Attribute.add("spore-walls");
    }
}
