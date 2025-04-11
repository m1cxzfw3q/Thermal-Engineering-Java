package TEMod.content;

import mindustry.type.Item;
import arc.graphics.Color;

public class TEItems {
    public static Item uranium; //铀
    public static Item nuclearFuelRod; //核燃料棒

    public static void load() {
        uranium = new Item("uranium", Color.valueOf("d99d73")){{
            hardness = 1;
            cost = 2F;
            radioactivity = 1.5F;
        }};

        nuclearFuelRod = new Item("nuclearFuelRod", Color.valueOf("d99d73")){{
            hardness = 0;
            radioactivity = 2F;
        }};
    }
}
