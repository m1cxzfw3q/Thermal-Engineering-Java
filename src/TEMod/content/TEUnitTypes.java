package TEMod.content;

import TEMLib.ModularWeapon.ModularWeaponUnitType;
import TEMLib.ModularWeapon.WeaponTYPE;

public class TEUnitTypes {
    public static ModularWeaponUnitType testUnit;

    public static void load() {
        testUnit = new ModularWeaponUnitType("test-unit", true) {{
            weapons.addAll(
                    new WeaponBase(WeaponTYPE.LARGE, 0, 0)
            );
        }};
    }
}
