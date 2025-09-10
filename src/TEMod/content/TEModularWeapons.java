package TEMod.content;

import TEMod.TECustom.ModularWeapon.ModularWeapon;
import TEMod.TECustom.ModularWeapon.WeaponTYPE;
import mindustry.entities.bullet.BulletType;

public class TEModularWeapons {
    public static ModularWeapon EMPTY;
    public static void load() {
        EMPTY = new ModularWeapon() {{//空武器，炮台基座使用的类型
            bullet = new BulletType(0, 0) {{
                lifetime = 0;
            }};
            display = useAmmo = mirror = top = aiControllable = controllable = predictTarget = useAttackRange = destory = false;
            rotateSpeed = recoil = shootCone = -1;
            reload = Float.POSITIVE_INFINITY;
            noAttack = isFirst = true;
            typee = WeaponTYPE.ANY;
        }};
    }
}
