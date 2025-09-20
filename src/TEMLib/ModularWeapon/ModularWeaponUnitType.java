package TEMLib.ModularWeapon;

import TEMLib.TEUnitType;
import arc.struct.Seq;

public class ModularWeaponUnitType extends TEUnitType {
    public ModularWeaponUnitType(String name, boolean flying) {
        super(name, flying);
    }
    public static Seq<WeaponBase> weapons = new Seq<>();

    public static class WeaponBase {
        public static float x, y;

        public WeaponBase(int tier, float x, float y) {
            WeaponBase.x = x;
            WeaponBase.y = y;
            ModularWeapon a = new EmptyModularWeapon(tier) {{
                x = WeaponBase.x;
                y = WeaponBase.y;
            }};
        }
    }
}

