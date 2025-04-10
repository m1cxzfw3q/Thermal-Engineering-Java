package TEMod.content;

import mindustry.type.Item;
import arc.graphics.Color;

public class Items {
    public static Item uranium; //铀
    public static Item nuclear_Fuel_Rod; //核燃料棒

    public static void load() {
        uranium = new Item("uranium", Color.valueOf("d99d73")){{
            hardness = 1;
            cost = 2F;
            radioactivity = 1.5F;
        }};

        nuclear_Fuel_Rod = new Item("nuclear Fuel Rod", Color.valueOf("d99d73")){{
            hardness = 0;
            radioactivity = 2F;
        }};
    }
}
