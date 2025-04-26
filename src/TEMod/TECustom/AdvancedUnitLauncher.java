package TEMod.TECustom;

import arc.math.geom.Vec2;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.gen.Unit;

// 添加多方块升级版本
public class AdvancedUnitLauncher extends UnitLauncher {
    public AdvancedUnitLauncher(String name) {
        super(name);
        buildCostMultiplier = 0.8f;
    }

    public class AdvancedLauncherBuild extends UnitLauncherBuild {
        @Override
        public boolean consValid() {
            return super.consValid() &&
                    liquids.get(Liquids.cryofluid) >= 0.1f;
        }

        @Override
        public void teleportUnit(Unit unit, Vec2 target) {
            // 添加冷冻保护
            unit.apply(StatusEffects.invincible, 60f);
            super.teleportUnit(unit, target);
        }
    }
}