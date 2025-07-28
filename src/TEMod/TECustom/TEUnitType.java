package TEMod.TECustom;

import mindustry.gen.UnitEntity;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.ammo.PowerAmmoType;

public class TEUnitType extends UnitType {
    public TEUnitType(String name, boolean flying, float health, float speed, int itemCapacity, float armor) {
        super(name);
        constructor = UnitEntity::create;
        this.flying = flying;
        this.health = health;
        this.speed = speed;
        this.itemCapacity = itemCapacity;
        this.armor = armor;
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
