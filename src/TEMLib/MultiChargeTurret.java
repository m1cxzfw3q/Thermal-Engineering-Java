package TEMLib;

import arc.Core;
import arc.math.Mathf;
import arc.struct.ObjectMap;
import mindustry.entities.Lightning;
import mindustry.entities.bullet.*;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;
import org.jetbrains.annotations.NotNull;

import static TEMLib.lib.noop;

public class MultiChargeTurret extends PowerTurret {
    public int maxChargeTier = 3;
    public @NotNull ChargeTier[] tiers = {};
    public @NotNull ChargeTier notCharge = empty(),
            overdriveCharge = empty(),
            superOverdriveCharge = empty();

    public float overdriveChargeExplodeRadius, superOverdriveChargeExplodeRadius,
            overdriveChargeExplodeDamage, superOverdriveChargeExplodeDamage;
    public int superOverdriveChargeExplodeLightnings;//一坨

    public MultiChargeTurret(String name) {
        super(name);
    }

    public ChargeTier empty() {
        return new ChargeTier(-1, new BulletType());
    }

    public void MultiCharge(ChargeTier notCharge, ChargeTier overdriveCharge, ChargeTier superOverdriveCharge, ChargeTier[] tiers) {
        this.notCharge = notCharge;
        this.overdriveCharge = overdriveCharge;
        this.superOverdriveCharge = superOverdriveCharge;
        this.tiers = tiers;
    }

    @Override
    public void setStats() {
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(Core.bundle.format("misc.multicharge.notCharge-1"), notCharge.bullet)));
        for (int i = 0; i < tiers.length; i++) {
            stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(Core.bundle.format("misc.multicharge.tier").replace('T', (char) i), tiers[i].bullet)));
        }
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(
                Core.bundle.format("misc.multicharge.overdrive-charge"), overdriveCharge.bullet,
                Core.bundle.format("misc.multicharge.super-ov-charge"), superOverdriveCharge.bullet
        )));
    }

    @Override
    public void setBars() {
        /*
        addBar("", (MultiChargeTurretBuild entity) ->
                new Bar(
                        entity.chargeTier < 1 ? Core.bundle.format("misc.multicharge.notCharge-0") :
                                entity.chargeTier >= 1 + maxChargeTier ? Core.bundle.format("misc.multicharge.overdrive-charge") :
                                        entity.chargeTier >= 1 ? (Core.bundle.format("misc.multicharge.tier") + entity.chargeTier),
                        entity.chargeTier >= 1 + maxChargeTier ? Pal.health : Pal.ammo,
                        () -> entity.chargeProgress - entity.chargeTier
                )
        );
         *///TODO Bar
    }

    public class MultiChargeTurretBuild extends PowerTurretBuild {
        public int chargeTier = 0;
        public float chargeProgress = 0f;

        public void ArcExplosion(float radius, float damage, int lightnings) {
            Call.logicExplosion(Team.derelict, x, y, radius, damage, true, true, false, false);//byd这么长还让不让人活了
            for (int i = 0; i < 30; i++) {//史
                noop();
            }
            for (int i = 0; i < lightnings; i++) {
                Lightning.create(Team.derelict, Pal.lancerLaser, damage / 10, x, y, Mathf.random(360f), (int) (radius * 0.8));
                Call.soundAt(Sounds.spark, x, y, 1, 1);
            }
        }

        @Override
        public void updateTile() {
            super.updateTile();
            chargeTier = (int) Math.floor(chargeProgress);//神秘
            int nextChargeTier = chargeTier++;

            if (isShooting()) {//屎
                if (chargeProgress < 1f) {
                    chargeProgress += (notCharge.chargeUseTime / 60);
                } else if (chargeTier == 1 + maxChargeTier) {
                    chargeProgress += (overdriveCharge.chargeUseTime / 60);
                } else if (chargeTier == 2 + maxChargeTier) {
                    resetCharge();
                    ArcExplosion(superOverdriveChargeExplodeRadius, superOverdriveChargeExplodeDamage, superOverdriveChargeExplodeLightnings);
                } else chargeProgress += (tiers[nextChargeTier].chargeUseTime / 60);
            }

            if (!isShooting() && chargeProgress != 0) {
                if (chargeProgress >= 0.7f && chargeTier != 1) {
                    shoot(notCharge.bullet);
                }
            }
        }

        protected void resetCharge() {
            chargeProgress = 0f;
            chargeTier = 0;
        }
    }

    public static class ChargeTier {
        public float chargeUseTime;
        public @NotNull BulletType bullet;

        public ChargeTier(float chargeTime, @NotNull BulletType bullet) {
            chargeUseTime = chargeTime;
            this.bullet = bullet;
        }
    }
}
