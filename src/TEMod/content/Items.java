package TEMod.content;

import mindustry.type.Item;
import arc.graphics.Color;

public class Items {
    public static Item uranium;
    public static Item nuclear_Fuel_Rod;

    public static void load() {
        uranium = new Item("uranium", Color.valueOf("d99d73")){{
            hardness = 1;
            cost = 1F;
            alwaysUnlocked = false;
            radioactivity = 1.5F;
        }};

        nuclear_Fuel_Rod = new Item("nuclear Fuel Rod", Color.valueOf("d99d73")){{
            hardness = 0;
            cost = 1F;
            alwaysUnlocked = false;
            radioactivity = 2F;
        }};
    }
}
