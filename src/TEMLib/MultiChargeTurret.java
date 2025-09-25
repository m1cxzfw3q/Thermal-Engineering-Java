package TEMLib;

import mindustry.entities.bullet.*;
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
