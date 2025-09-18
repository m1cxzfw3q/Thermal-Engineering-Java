package TEMod.content;

import TELib.ModularWeapon.ModularWeaponUnitType;
import TELib.ModularWeapon.WeaponTYPE;

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
