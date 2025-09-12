package TEMod.content;

import TEMod.TECustom.ModularWeapon.ModularWeaponUnitType;
import TEMod.TECustom.ModularWeapon.WeaponTYPE;

public class TEUnitTypes {
    public static ModularWeaponUnitType testUnit;

    public static void load() {
        testUnit = new ModularWeaponUnitType("test-unit", true) {{
            weapons.addAll(
                    new weaponBase(WeaponTYPE.LARGE, 0, 0)
            );
        }};
    }
}
