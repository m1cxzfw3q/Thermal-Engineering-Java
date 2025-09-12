package TEMod.content;

import TEMod.TECustom.ModularWeapon.ModularWeaponUnitType;

public class TEUnitTypes {
    public static ModularWeaponUnitType testUnit;

    public static void load() {
        testUnit = new ModularWeaponUnitType("test-unit", true) {{

        }};
    }
}
