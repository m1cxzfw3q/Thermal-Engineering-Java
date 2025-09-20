package TEMLib.ModularWeapon;

import mindustry.entities.bullet.BulletType;

public class EmptyModularWeapon extends ModularWeapon {
    public static int supportTier;

    public EmptyModularWeapon(int tier) {
        bullet = new BulletType(0, 0) {{
            lifetime = 0;
        }};
        display = useAmmo = mirror = top = aiControllable = controllable = predictTarget = useAttackRange = destory = false;
        rotateSpeed = recoil = shootCone = -1;
        reload = Float.POSITIVE_INFINITY;
        noAttack = isFirst = true;
        name = "";
        supportTier = tier;
    }
}
