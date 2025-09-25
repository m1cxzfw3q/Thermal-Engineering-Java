package TEMLib;

import arc.math.Mathf;
import mindustry.entities.Lightning;
import mindustry.entities.bullet.*;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class MultiChargeTurret extends PowerTurret {
    public int maxChargeTier = 3;
    public float chargeUseTime;
    public BulletType[] bullets = {};
    public BulletType notCharge, overdriveCharge, superOverdriveCharge;

    public MultiChargeTurret(String name) {
        super(name);
    }

    public void MultiCharge(BulletType notCharge, BulletType overdriveCharge, BulletType superOverdriveCharge, BulletType[] bullets) {
        this.notCharge = notCharge;
        this.overdriveCharge = overdriveCharge;
        this.superOverdriveCharge = superOverdriveCharge;
        this.bullets = bullets;
    }

    public class MultiChargeTurretBuild extends PowerTurretBuild {
        public int chargeTier = 0;
        public float chargeProgress = 0f;
        protected int lasterLog = 0;

        public void ArcExplosion(float radius, float damage, int lightnings) {
            Call.soundAt(Sounds.spark, x, y, 1, 1);
            Call.logicExplosion(Team.derelict, x, y, radius, damage, true, true, false, false);//byd这么长还让不让人活了
            for (int i = 0; i < lightnings; i++) {
                Lightning.create(Team.derelict, Pal.lancerLaser, damage / 10, x, y, Mathf.random(360f), (int) (radius * 0.8));
            }
        }

        @Override
        public void updateTile() {
            super.updateTile();
        }

        protected void resetCharge() {
            chargeProgress = 0f;
            chargeTier = 0;
        }
    }
}
