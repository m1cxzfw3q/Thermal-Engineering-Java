package TEMod.TECustom.ModularWeapon;

import arc.struct.Seq;
import mindustry.type.UnitType;

public class ModularWeaponUnitType extends UnitType {
    public Seq<ModularWeaponBase> weapons = new Seq<>();

    public ModularWeaponUnitType(String name) {
        super(name);
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

