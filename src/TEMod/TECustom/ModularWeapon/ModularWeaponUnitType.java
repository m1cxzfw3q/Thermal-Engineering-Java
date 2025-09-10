package TEMod.TECustom.ModularWeapon;

import TEMod.TECustom.TEUnitType;

public class ModularWeaponUnitType extends TEUnitType {
    public ModularWeaponUnitType(String name, boolean flying) {
        super(name, flying);
    }



    public static class ModularWeaponBase {
        public float x;
        public float y;

        public void newWeaponBase(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}

