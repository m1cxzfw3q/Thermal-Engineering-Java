package TEMod.content;

import mindustry.type.Item;
import arc.graphics.Color;

public class TEItems {
    public static Item uranium; //铀
    public static Item nuclearFuelRod; //核燃料棒

    public static void load() {
        uranium = new Item("uranium", Color.valueOf("d99d73")){{
            hardness = 5;
            cost = 1.2F;
            radioactivity = 1.5F;
            alwaysUnlocked = false;
            buildable = true;
        }};

        nuclearFuelRod = new Item("nuclearFuelRod", Color.valueOf("d99d73")){{
            hardness = 0;
            radioactivity = 2F;
            alwaysUnlocked = false;
            buildable = false;
        }};
    }
}
