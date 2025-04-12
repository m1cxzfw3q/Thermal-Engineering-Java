package TEMod.content;

import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootSpread;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class TEUnits {
    public static UnitType discover;

    public static void load(){
        discover = new UnitType("discover") {{
            aiController = BuilderAI::new;
            speed = 4.5f;
            isEnemy = false;
            mineSpeed = 10f;
            mineTier = 3;
            buildSpeed = 1.5f;
            itemCapacity = 100;
            health = 1000f;
            lowAltitude = true;
            rotateSpeed = 15f;

            weapons.add(new Weapon("discover-1") {{
                reload = 17f;
                x = 2.75f;
                y = 1f;
                top = false;
                ejectEffect = Fx.casing2;
                inaccuracy = 1f;
                hitSize = 18f;

                shoot = new ShootSpread(){{
                    shots = 3;
                    shotDelay = 1f;
                    spread = 2f;
                }};

                bullet = new BasicBulletType(4f, 80f){{
                    width = 7f;
                    height = 9f;
                    lifetime = 30f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    buildingDamageMultiplier = 0.1f;
                    pierce = true;
                    pierceCap = 1;
                    homingPower = 0.05f;
                }};
            }});
        }};
    }
}
