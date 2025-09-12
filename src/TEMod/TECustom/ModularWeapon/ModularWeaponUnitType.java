package TEMod.TECustom.ModularWeapon;

import TEMod.TECustom.TEUnitType;

public class ModularWeaponUnitType extends TEUnitType {
    public ModularWeaponUnitType(String name, boolean flying) {
        super(name, flying);
    }
    public static ModularWeapon[] weapons = {};

    public static class newWeaponBase {
        public static float x, y;
        public WeaponTYPE typee;

        public newWeaponBase(WeaponTYPE type, float x, float y) {
            newWeaponBase.x = x;
            newWeaponBase.y = y;
            this.typee = type;
            weapons[weapons.length - 1] = new EmptyModularWeapon() {{
                x = newWeaponBase.x;
                y = newWeaponBase.y;
            }};
        }
    }
}

