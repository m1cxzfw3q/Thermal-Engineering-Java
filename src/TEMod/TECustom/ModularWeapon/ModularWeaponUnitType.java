package TEMod.TECustom.ModularWeapon;

import TEMod.TECustom.TEUnitType;
import arc.struct.Seq;

public class ModularWeaponUnitType extends TEUnitType {
    public ModularWeaponUnitType(String name, boolean flying) {
        super(name, flying);
    }
    public static Seq<WeaponBase> weapons = new Seq<>();

    public static class WeaponBase {
        public static float x, y;
        public WeaponTYPE typee;

        public WeaponBase(WeaponTYPE type, float x, float y) {
            WeaponBase.x = x;
            WeaponBase.y = y;
            this.typee = type;
        }
    }
}

