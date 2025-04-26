package TEMod.TECustom;

import arc.*;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
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
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.BuildVisibility;

import static TEMod.content.TEStatusEffects.captured;
import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class UnitLauncher extends Block {
    public float launchDelay = 60f; // 发射准备时间（帧）
    public Item costItem = Items.silicon; // 消耗物品
    public int costAmount = 25;      // 每次发射消耗量
    public float payloadLimit;       //单位载荷空间大小

    public UnitLauncher(String name) {
        super(name);

        inEditor = false;
        hasPower = true;
        hasItems = true;
        solid = false;
        itemCapacity = 50;
        update = true;
        category = Category.effect;
        acceptsPayload = true;

        clipSize = 40f; // 检测范围扩大

        flags = EnumSet.of(BlockFlag.launchPad);
        buildVisibility = BuildVisibility.campaignOnly;

        configurable = true;
        saveConfig = true;
        config(Vec2.class, UnitLauncherBuild::beginLaunch);
        buildCostMultiplier = 0.6f;
    }

    public class UnitLauncherBuild extends Building {
        public @Nullable Unit loadedUnit; // 装载的单位
        public float launchProgress;      // 发射进度
        public Vec2 targetPos;            // 目标坐标

        // 在updateTile中添加自动发射检测
        @Override
        public void updateTile() {
            super.updateTile();

            // 自动寻找核心作为目标（增加空值检查）
            if(loadedUnit != null && targetPos == null){
                CoreBlock.CoreBuild core = team.core();
                if(core != null){
                    Vec2 corePos = core.getCommandPosition();
                    if(corePos != null){ // 确保坐标有效
                        beginLaunch(corePos);
                    }
                }
            }

            // 持续发射流程
            if(consValid() && loadedUnit != null){
                progressLaunch();
            }else{
                interruptLaunch();
            }

            try {
                // 原有更新逻辑...
                if(loadedUnit != null && !loadedUnit.isValid()){
                    // 单位意外消失时的恢复处理
                    loadedUnit = null;
                    items.add(costItem, costAmount); // 返还资源
                }
            } catch(Exception e){
                Log.err("[Thermal-Engineering] Launch pad error: @", e);
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

        // 修改后的装载单位逻辑
        public void loadUnit(Unit unit) {
            if(loadedUnit == null &&
                    items.has(costItem, costAmount) &&
                    unit.within(x, y, size * 12f)) {

                // 应用捕获状态（代替controller置空）
                unit.apply(captured, 99999f);
                unit.vel().isZero();
                unit.set(x, y);
                loadedUnit = unit;

                Log.info("[Thermal-Engineering] Launch pad Unit status: @", loadedUnit.statusBits());
            }
        }

        // 在beginLaunch方法中添加空值防护
        public void beginLaunch(Vec2 target) {
            if(!validTarget(target)) return;
            if(loadedUnit != null && consValid()) {
                if(target == null) {
                    Log.err("发射目标异常：@ 建筑位置：@,@",
                            team, x, y); // 记录上下文信息
                    return;
                }
                Log.info("开始发射至：@,@", target.x, target.y);
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

        // 修改completeLaunch方法
        public void completeLaunch() {
            if(!items.has(costItem, costAmount)) return; // 双重验证

            // 同步扣除资源（防止异步问题）
            items.remove(costItem, -costAmount);

            Log.info("[Thermal-Engineering] Launching complete: @ -> @,@", loadedUnit.type, targetPos.x, targetPos.y);

            // 强制解除状态（双重保险）
            loadedUnit.unapply(captured);

            // 跨区块音效
            Fx.smeltsmoke.at(x, y);
            Fx.teleport.at(targetPos);
        }

        public void teleportUnit(Unit unit, Vec2 target) {
            // 解除捕获状态
            unit.unapply(captured);

            // 设置新位置
            unit.set(target);
            unit.vel().isZero();

            // 播放特效
            Fx.teleportActivate.at(x, y);
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

            if(targetPos != null){
                Draw.color(Pal.accent);
                Lines.stroke(2f);
                Lines.line(x, y, targetPos.x, targetPos.y);
                Draw.reset();
            }

            if(launchProgress > 0) {
                Draw.color(Pal.accent);
                Draw.rect(Core.atlas.find("launch-progress"),
                        x, y + 6f,
                        20f * launchProgress, 3f);
                Draw.reset();
            }
        }

        // 在write/read方法中处理targetPos
        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(targetPos == null ? -1 : targetPos.x);
            write.f(targetPos == null ? -1 : targetPos.y);
        }

        @Override
        public void read(Reads read) {
            super.read(read);
            float x = read.f();
            float y = read.f();
            targetPos = (x < 0 || y < 0) ? null :
                    new Vec2(
                            Mathf.clamp(x, 0, world.width()*tilesize), // 坐标限幅
                            Mathf.clamp(y, 0, world.height()*tilesize)
                    );
        }

        @Override
        public byte version() {
            return 1; // 版本号用于存档兼容
        }

        // 修改validTarget方法，添加空值检查
        private boolean validTarget(Vec2 pos){
            if(pos == null) return false; // 新增空检查
            return pos.x >= 0 &&
                    pos.y >= 0 &&
                    pos.x < world.width()*tilesize &&
                    pos.y < world.height()*tilesize;
        }

        public void onConfigure(Vec2 value) {
            // 安全距离校验（至少距离自身3格）
            if(value.dst(x, y) < tilesize * 3) {
                Fx.smeltsmoke.at(x, y);
                return;
            }
            if(value == null){ // 配置值空检查
                Fx.smeltsmoke.at(x, y);
                return;
            }

            beginLaunch(value);
            Fx.select.at(value); // 显示目标位置标记
        }
    }
}
