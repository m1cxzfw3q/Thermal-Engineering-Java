package TEMLib;

import mindustry.content.Blocks;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Call;

public class ExplosionLaserBulletType extends LaserBulletType {
    public float explosionDamage = 100f;
    public float explosionRadius = 5f;

    public ExplosionLaserBulletType(float damage) {
        super(damage);
    }

    public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct) {
        super.hitTile(b, build, x, y, initialHealth, direct);

        if (build.block == Blocks.plastaniumWall) ArcExplosions(x, y);
        else if (build.block == Blocks.plastaniumWallLarge) ArcExplosions(x, y);
    }

    protected void ArcExplosions(float x, float y) {
        Call.logicExplosion(Team.get(0), x, y, explosionRadius, explosionDamage, true, true, true, false);
    }
}
