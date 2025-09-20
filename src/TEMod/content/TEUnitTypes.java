package TEMod.content;

import TEMLib.ModularWeapon.ModularWeaponUnitType;

public class TEUnitTypes {
    public static ModularWeaponUnitType testUnit;

    public static void load() {
        testUnit = new ModularWeaponUnitType("test-unit", true) {{
            weapons.addAll(
                    new WeaponBase(3, 0, 0)
            );
        }};
    }
}
