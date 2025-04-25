package TEMod.TECustom;

import arc.math.geom.Vec2;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.gen.Unit;
import mindustry.type.Category;

import static mindustry.type.ItemStack.with;

// 添加多方块升级版本
public class AdvancedUnitLauncher extends UnitLauncher {
    public AdvancedUnitLauncher(String name) {
        super(name);

        // 增强参数
        size = 5;
        launchDelay = 30f;
        unitCapModifier = 25;

        // 添加液体冷却需求
        consumeLiquid(Liquids.cryofluid, 0.1f);
        consumePower(7f / 60f);

        requirements(
                Category.effect, with(
                        Items.copper, 600,
                        Items.lead, 450,
                        Items.titanium, 300,
                        Items.silicon, 350,
                        Items.thorium, 350,
                        Items.plastanium, 200
                )
        );
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