package TEMod;

import TEMLib.other.BoostDiode;
import TEMLib.other.HighVoltageDiode;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;

public class testNew {
    public static Block boostDiode, highVoltageDiode;

    public static void load() {
        boostDiode = new BoostDiode("boost-diode") {{
            powerRequirement = 80f;
            highVoltageOutput = 40f;
            requirements(Category.power, BuildVisibility.shown, ItemStack.with(
                    Items.copper, 6,
                    Items.lead, 4,
                    Items.silicon, 3,
                    Items.graphite, 2
            ));
            size = 1;
        }};

        highVoltageDiode = new HighVoltageDiode("high-voltage-diode") {{
            powerProduction = 15f;
            voltageThreshold = 80f;
            requirements(Category.power, BuildVisibility.shown, ItemStack.with(
                    Items.copper, 5,
                    Items.lead, 3,
                    Items.silicon, 2
            ));
            size = 1;
        }};
    }
}
