package TEMod.content;

import TEMLib.ModularWeapon.ModularWeapon;
import TEMod.TECore;

public class TEModularWeapons {
    public static ModularWeapon testWeapon;
    public static void load() {
        testWeapon = new ModularWeapon();

        TECore.isComplete(TEModularWeapons.class);
    }
}
