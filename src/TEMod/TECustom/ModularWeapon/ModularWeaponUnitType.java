package TEMod.TECustom.ModularWeapon;

import TEMod.TECustom.TEUnitType;
import arc.struct.Seq;

public class ModularWeaponUnitType extends TEUnitType {
    public ModularWeaponUnitType(String name, boolean flying) {
        super(name, flying);
    }
    public static Seq<weaponBase> weapons = new Seq<>();

    public static class weaponBase {
        public static float x, y;
        public WeaponTYPE typee;

        public weaponBase(WeaponTYPE type, float x, float y) {
            weaponBase.x = x;
            weaponBase.y = y;
            this.typee = type;
        }
    }
}

