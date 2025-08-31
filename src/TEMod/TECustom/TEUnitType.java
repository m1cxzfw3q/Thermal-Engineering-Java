package TEMod.TECustom;

import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class TEUnitType extends UnitType {
    public TEUnitType(String name, boolean flying) {
        super(name);
        constructor = UnitEntity::create;
        this.flying = flying;
    }

    public void AccDr(float accel, float drag) {
        this.accel = accel;
        this.drag = drag;
    }

    public void HSize(float hitSize) {
        this.hitSize = hitSize * 8;
    }

    public void AmmoType(Item ammoType, int ammoCapacity) {
        this.ammoType = new ItemAmmoType(ammoType);
        this.ammoCapacity = ammoCapacity;
    }

    public void PowerAmmoType(float totalPower, int ammoCapacity) {
        this.ammoType = new PowerAmmoType(totalPower);
        this.ammoCapacity = ammoCapacity;
    }
}
