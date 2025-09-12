package TEMod.TECustom.ModularWeapon;

import arc.struct.Seq;
import mindustry.type.ItemStack;
import mindustry.type.Weapon;

public class ModularWeapon extends Weapon {
    public static Seq<ItemStack> requirements = new Seq<>();
    public boolean isFirst = false, destory = true;
    public static WeaponTYPE typee;

    public ModularWeapon(String name) {
        super(name);
    }

    public ModularWeapon() {}

    public static void requirements(Seq<ItemStack> require) {
        requirements.add(require);
    }


}
