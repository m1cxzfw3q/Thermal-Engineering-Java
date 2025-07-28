package TEMod.TECustom;

import arc.Core;
import arc.graphics.Color;
import arc.scene.Element;
import arc.struct.ObjectMap;
import arc.util.Log;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class MultiChargeTurret extends PowerTurret {
    public MultiChargeTurret(String name) {
        super(name);
    }

    public class MultiChargeTurretBuild extends PowerTurretBuild {
        public int maxChargeTier = 3;
        public int chargeTier = 0;
        public float chargeTime = 1.4f * 60f; /// 每阶段充能时间
        public float chargeProgress = 0f;
        public float overdriveChargeProgress = 0f;
        public float overdriveChargeTime = 2f * 60f;
        public int overdriveChargeTier = 0;
        public int maxOverdriveChargeTier = 1;
        public float superOverdriveChargeProgress = 0f;
        public float superOverdriveChargeTime = 2f * 60f;
        public int superOverdriveChargeTier = 0;
        public int LasterLog = 0;

        public Color[] chargeColors = {
                Color.valueOf("FFFF00"),
                Color.valueOf("00FF61"),
                Color.valueOf("00A9FF"),
                Color.valueOf("FF0000"),
                Color.valueOf("ffffff")
        };

        public void ArcExplosion(float explosionDamage, float radius) {
            shoot(
                    new ExplosionBulletType(explosionDamage, radius) {{
                        killShooter = false;
                        status = StatusEffects.shocked;
                        statusDuration = 10f;
                        lightningDamage = 20F;
                        lightning = 10;
                        lightningLength = 8 * 2;
                        lightningColor = Color.valueOf("ab99d3ff");
                    }}
            );
            Sounds.spark.at(x, y);
            Call.logicExplosion(Team.get(0), x, y, radius, explosionDamage, true, true, true, false);
            Log.info("ArcExplosion -> @,@",x ,y);
        }

        @Override public void updateTile() {
            super.updateTile();

            if (hasAmmo() && enabled && isShooting()) {
                /// 充能逻辑
                if (chargeProgress < chargeTime * (maxChargeTier + 1)) {
                    chargeProgress += delta() * efficiency;
                    chargeProgress = Math.min(chargeProgress, chargeTime * (maxChargeTier + 1));
                }

                /// 计算当前阶段
                chargeTier = Math.min((int) (chargeProgress / chargeTime), maxChargeTier);

                if (chargeTier == 3) {
                    if (overdriveChargeProgress < overdriveChargeTime * (maxOverdriveChargeTier + 1)) {
                        overdriveChargeProgress += delta() * efficiency;
                        overdriveChargeProgress = Math.min(overdriveChargeProgress, overdriveChargeTime * (maxOverdriveChargeTier + 1));
                    }

                    overdriveChargeTier = Math.min((int) (overdriveChargeProgress / overdriveChargeTime), maxOverdriveChargeTier);

                    if (overdriveChargeTier == 1) {
                        if (superOverdriveChargeProgress < superOverdriveChargeTime * (maxOverdriveChargeTier + 1)) {
                            superOverdriveChargeProgress += delta() * efficiency;
                            superOverdriveChargeProgress = Math.min(superOverdriveChargeProgress, superOverdriveChargeTime * (maxOverdriveChargeTier + 1));
                        }

                        superOverdriveChargeTier = Math.min((int) (superOverdriveChargeProgress / superOverdriveChargeTime), maxOverdriveChargeTier);
                    }
                }
                /// 日志输出防刷屏
                LasterLog++;
                if (LasterLog >= 90) {
                    Log.info("ChargeTier: @", chargeTier);
                    Log.info("ChargeProgress: @", chargeProgress);
                    Log.info("OverdriveChargeTier: @", overdriveChargeTier);
                    Log.info("OverdriveChargeProgress: @", overdriveChargeProgress);
                    Log.info("SuperOverdriveChargeTier: @", superOverdriveChargeTier);
                    Log.info("SuperOverdriveChargeProgress: @", superOverdriveChargeProgress);
                    LasterLog = 0;
                }
            }

            /// 发射逻辑
            if (chargeTier >= 0 && !isShooting() && chargeProgress > 20f) {
                if (chargeTier == 1) {shoot(Tier1Charge());Sounds.laser.at(x, y);Log.info("AmmoShoot: Tier1Charge");}
                else if (chargeTier == 2) {shoot(Tier2Charge());Sounds.laser.at(x, y);Log.info("AmmoShoot: Tier2Charge");}
                else if (chargeTier == 3) {shoot(Tier3Charge());Sounds.laser.at(x, y);Log.info("AmmoShoot: Tier3Charge");}
                else if (overdriveChargeTier == 1) {shoot(OverdriveCharge());ArcExplosion(health / 0.5f, 45f);Sounds.laserblast.at(x, y);Log.info("AmmoShoot: OverdriveCharge");}
                else if (chargeProgress >= 56f) {shoot(NotCharge());Sounds.spark.at(x, y);Log.info("AmmoShoot: NotCharge");}
                resetCharge();
            } else if(superOverdriveChargeTier == 1) {
                shoot(SuperOverdriveCharge());ArcExplosion(health, 56f);Sounds.laserblast.at(x, y);Log.info("AmmoShoot: SuperOverdriveCharge");
                resetCharge();
            }
        }

        protected void resetCharge() {
            chargeProgress = 0f;
            chargeTier = 0;
            overdriveChargeProgress = 0;
            overdriveChargeTier = 0;
            superOverdriveChargeProgress = 0;
            superOverdriveChargeTier = 0;
            Sounds.none.at(x, y);
            Log.info("ResetCharge");
        }

        protected BulletType NotCharge() {
            return new LightningBulletType() {{
                shootEffect = Fx.lightningShoot;
                damage = 20;
                lightningLength = 25;
                ammoMultiplier = 1f;
                buildingDamageMultiplier = 0.5f;
                shootSound = Sounds.spark;
                lightningType = new BulletType(0.0001f, 0f){{
                    lifetime = Fx.lightning.lifetime;
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    lightColor = Color.white;
                    buildingDamageMultiplier = 0.5f;
                }};
            }};
        }

        protected BulletType Tier1Charge() {
            return new LaserBulletType(200) {{
                colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
                buildingDamageMultiplier = 0.5f;
                shootEffect = Fx.lancerLaserShoot;
                shootSound = Sounds.laser;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 16f;
                drawSize = 50 * 8f;
                length = 26.625f * 8f;
                ammoMultiplier = 1f;
                pierceCap = 12;
            }};
        }

        protected BulletType Tier2Charge() {
            return new LaserBulletType(550) {{
                colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
                buildingDamageMultiplier = 0.5f;
                shootEffect = Fx.lancerLaserShoot;
                shootSound = Sounds.laser;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 16f;
                drawSize = 50 * 8f;
                length = 51.625f * 8f;
                ammoMultiplier = 1f;
                pierceCap = 23;
            }};
        }

        protected BulletType Tier3Charge() {
            return new LaserBulletType(980) {{
                colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
                buildingDamageMultiplier = 0.5f;
                shootEffect = Fx.lancerLaserShoot;
                shootSound = Sounds.laser;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 16f;
                drawSize = 50 * 8f;
                length = 76.625f * 8f;
                ammoMultiplier = 1f;
                pierceCap = 35;
                pierceBuilding = true;
            }};
        }

        protected BulletType OverdriveCharge() {
            return new ExplosionLaserBulletType(1560) {{
                colors = new Color[]{
                        Pal.lancerLaser.cpy().a(0.4f),
                        Pal.lancerLaser, Color.white
                };
                buildingDamageMultiplier = 0.5f;
                shootEffect = Fx.lancerLaserShoot;
                shootSound = Sounds.laserblast;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 65f;
                drawSize = 75f * 8f;
                length = 126.625f * 8f;
                ammoMultiplier = 1f;
                pierceBuilding = true;
                pierceCap = 50;
                ArcExplosion(health / 0.5f, 45);
                explosionDamage = 2160f;
                explosionRadius = 30f;
                width = 32f;
            }};
        }

        protected BulletType SuperOverdriveCharge() {
            return new ExplosionLaserBulletType(2560) {{
                colors = new Color[]{
                        Pal.lancerLaser.cpy().a(0.4f),
                        Pal.lancerLaser, Color.white
                };
                buildingDamageMultiplier = 0.5f;
                shootEffect = Fx.lancerLaserShoot;
                shootSound = Sounds.laserblast;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 65f;
                drawSize = 100f * 8f;
                length = 151.625f * 8f;
                ammoMultiplier = 1f;
                pierce = pierceBuilding = pierceArmor = true;
                ArcExplosion(health, 56);
                explosionDamage = 3840f;
                explosionRadius = 30f;
                width = 32f;
            }};
        }
    }
}
