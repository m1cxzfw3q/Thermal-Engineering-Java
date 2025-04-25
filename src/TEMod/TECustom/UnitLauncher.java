package TEMod.TECustom;

import arc.*;
import arc.graphics.g2d.Draw;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.struct.EnumSet;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.BuildVisibility;

public class UnitLauncher extends Block {
    public float launchDelay = 60f; // 发射准备时间（帧）
    public Item costItem = Items.silicon; // 消耗物品
    public int costAmount = 25;      // 每次发射消耗量

    public UnitLauncher(String name) {
        super(name);

        inEditor = false;
        hasPower = true;
        hasItems = true;
        solid = true;
        itemCapacity = 50;
        update = true;

        flags = EnumSet.of(BlockFlag.launchPad);
        buildVisibility = BuildVisibility.campaignOnly;
    }

    public class UnitLauncherBuild extends Building {
        public @Nullable Unit loadedUnit; // 装载的单位
        public float launchProgress;      // 发射进度
        public Vec2 targetPos;            // 目标坐标

        @Override
        public void updateTile() {
            super.updateTile();

            // 持续电力消耗
            if(consValid()) {
                progressLaunch();
            } else {
                interruptLaunch();
            }
        }

        public UnitLauncherBuild() {
        }

        // 处理单位进入
        @Override
        public void unitOn(Unit unit) {
            if(canLoadUnit(unit)) {
                loadUnit(unit);
            }
        }

        // 装载单位逻辑
        public void loadUnit(Unit unit) {
            if(loadedUnit == null && items.has(costItem, costAmount)) {
                loadedUnit = unit;
                unit.rotation(90f); // 调整单位方向
                unit.trns(x, y);    // 移动单位到发射台中心
                unit.add();
            }
        }

        // 开始发射流程
        public void beginLaunch(Vec2 target) {
            if(loadedUnit != null && consValid()) {
                targetPos = target.cpy();
                launchProgress = 0f;
            }
        }

        // 发射进度更新
        public void progressLaunch() {
            if(targetPos == null || loadedUnit == null) return;

            launchProgress += delta() / launchDelay;
            if(launchProgress >= 1f){
                completeLaunch();
            }

            // 显示进度效果
            loadedUnit.elevation = Mathf.curve(launchProgress, 0, 0.8f);
        }

        // 完成发射
        public void completeLaunch() {
            if(loadedUnit == null) return;

            // 扣除成本
            items.remove(costItem, costAmount);

            // 传送单位
            teleportUnit(loadedUnit, targetPos);

            // 重置状态
            loadedUnit = null;
            launchProgress = 0f;
            targetPos = null;

            // 播放特效
            Fx.teleportActivate.at(x, y);
        }

        // 单位传送实现
        public void teleportUnit(Unit unit, Vec2 target) {
            // 设置无敌状态
            unit.apply(StatusEffects.invincible, 30f);

            // 执行传送
            unit.set(target);
            unit.vel().isZero();

            // 目标点特效
            Fx.teleport.at(target.x, target.y);
        }

        // 验证发射条件
        public boolean consValid() {
            return enabled &&
                    power.status >= 0.99f &&
                    items.has(costItem, costAmount);
        }

        // 中断发射
        public void interruptLaunch() {
            if(launchProgress > 0) {
                // 退回部分资源
                items.add(costItem, (int)(costAmount * launchProgress));
                launchProgress = 0f;
            }
        }

        // 判断可装载单位类型
        public boolean canLoadUnit(Unit unit) {
            // 允许装载所有友方地面单位
            return unit.team == team &&
                    !unit.isFlying() &&
                    unit.type != UnitTypes.block;
        }

        // 修正后的绘图方法
        @Override
        public void draw() {
            super.draw();

            if(loadedUnit != null) {
                loadedUnit.draw();
            }

            if(launchProgress > 0) {
                Draw.color(Pal.accent);
                Draw.rect(Core.atlas.find("launch-progress"),
                        x, y + 6f,
                        20f * launchProgress, 3f);
                Draw.reset();
            }
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(launchProgress);
            write.bool(loadedUnit != null);
            if(loadedUnit != null) {
                write.i(loadedUnit.id);
            }
        }

        @Override
        public void read(Reads read) {
            super.read(read);
            launchProgress = read.f();
            if(read.bool()) {
                int unitId = read.i();
                loadedUnit = Groups.unit.getByID(unitId);
            }
        }

        @Override
        public byte version() {
            return 1; // 版本号用于存档兼容
        }
    }
}
