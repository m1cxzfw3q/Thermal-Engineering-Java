package TEMod.TECustom;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.blocks.campaign.LaunchPad;
import mindustry.world.blocks.payloads.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class UnitLauncherNew extends LaunchPad {
    public float unitRotateSpeed = 5f;       // 单位旋转速度
    public float captureTime = 60f;         // 单位捕获时间（帧）
    public float launchPower = 3f;          // 发射推力
    public Item consumeItem = Items.silicon;// 每次发射消耗物品
    public int consumeAmount = 25;          // 消耗数量

    // 区域贴图
    public TextureRegion captureRegion;

    public UnitLauncherNew(String name){
        super(name);
        configurable = true;
        solid = false;
        update = true;
        hasPower = true;
        hasItems = true;
        itemCapacity = 100;
        group = BlockGroup.payloads;
        flags = EnumSet.of(BlockFlag.launchPad);

        config(Vec2.class, UnitLauncherNewBuild::configureTarget);
    }

    @Override
    public void load(){
        super.load();
        captureRegion = Core.atlas.find(name + "-capture");
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.unitType, captureTime / 60f, StatUnit.seconds);
        stats.add(Stat.input, consumeItem);
    }

    public class UnitLauncherNewBuild extends LaunchPadBuild {
        public @Nullable UnitPayload payload;   // 装载的单位
        public Vec2 targetPos;                 // 目标坐标
        public float captureProgress;           // 捕获进度
        public float rotation;                  // 当前旋转角度

        @Override
        public void updateTile(){
            super.updateTile();

            // 持续捕获逻辑
            if(consValid() && payload == null){
                // 扫描范围内的可捕获单位
                Units.nearby(x - size*4f, y - size*4f, size*8f, size*8f, unit -> {
                    if(canCapture(unit) && captureProgress < 1f){
                        captureProgress += delta() / captureTime;
                        if(captureProgress >= 1f){
                            captureUnit(unit);
                        }
                    }
                });
            }

            // 发射准备
            if(payload != null && consValid()){
                // 旋转对准目标
                if(targetPos != null){
                    rotation = Angles.moveToward(rotation, Angles.angle(x, y, targetPos.x, targetPos.y), unitRotateSpeed);
                }

                // 能量充能
                if((launchCounter += edelta()) >= launchTime){
                    launch();
                }
            }
        }

        @Override
        public void draw(){
            super.draw();

            // 绘制捕获进度环
            if(captureProgress > 0){
                Draw.color(Pal.accent);
                Draw.alpha(0.8f);
                Lines.stroke(3f * captureProgress);
                Lines.circle(x, y, size * tilesize * captureProgress);
                Draw.reset();
            }

            // 绘制单位装载指示器
            if(payload != null){
                payload.set(x, y, rotation);
                payload.draw();
            }
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload){
            return this.payload == null &&
                    payload instanceof UnitPayload &&
                    items.has(consumeItem, consumeAmount);
        }

        @Override
        public void handlePayload(Building source, Payload payload){
            if(payload instanceof UnitPayload u){
                this.payload = u;
                items.remove(consumeItem, consumeAmount);
            }
        }

        @Override
        public Payload getPayload(){
            return payload;
        }

        @Override
        public Payload takePayload(){
            Payload p = payload;
            payload = null;
            return p;
        }

        public void configureTarget(Vec2 pos){
            if(pos.dst(x, y) < tilesize * 4f){
                Fx.smeltsmoke.at(x, y);
                return;
            }
            targetPos = pos.cpy();
            Fx.placeBlock.at(pos);
        }

        protected boolean consValid(){
            return enabled &&
                    power.status > 0.99f &&
                    items.has(consumeItem, consumeAmount);
        }

        protected boolean canCapture(Unit unit){
            return unit.team == team &&
                    !unit.isFlying() &&
                    unit.type != UnitTypes.block;
        }

        protected void captureUnit(Unit unit){
            payload = new UnitPayload(unit);
            unit.remove();
            captureProgress = 0f;
        }

        protected void launch(){
            if(payload == null || targetPos == null) return;

            // 计算发射向量
            Vec2 vec = targetPos.cpy().sub(x, y).nor().scl(launchPower * tilesize);

            // 创建发射实体

            // 特效和音效
            Effect.shake(3f, 3f, this);
            Fx.launchPod.at(this);

            payload = null;
            launchCounter = 0f;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(rotation);
            write.f(captureProgress);
            write.f(targetPos == null ? -1 : targetPos.x);
            write.f(targetPos == null ? -1 : targetPos.y);
            Payload.write(payload, write);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            rotation = read.f();
            captureProgress = read.f();
            float tx = read.f(), ty = read.f();
            targetPos = tx < 0 ? null : new Vec2(tx, ty);
            payload = Payload.read(read);
        }
    }

    // 修改后的发射实体类
    @EntityDef static class UnitLaunchEntity extends Entity implements Timedc, Drawc, Positionc {
        // 导入所需组件
        @Import float x, y;
        @Import Unit unit;
        @Import Vec2 velocity;

        // 实现Timedc接口
        @Override
        public void update(){
            if(unit == null || !unit.isValid()){
                remove();
                return;
            }

            // 运动轨迹计算
            unit.set(x, y);
            velocity.scl(0.98f);
            unit.move(velocity.x, velocity.y);

            // 落地检测
            if(velocity.len() < 0.1f || unit.isGrounded()){
                Fx.unitLand.at(x, y);
                unit.add();
                remove();
            }
        }

        @Override
        public void draw(){
            if(unit != null){
                unit.draw();
                Drawf.light(x, y, 30f, Pal.accent, 0.8f);
            }
        }

        // 构建器方法
        public static UnitLaunchEntity create(Unit unit, Vec2 vel){
            return new UnitLaunchEntity() {{
                velocity.set(vel);
                unit.rotation(vel.angle());
            }};
        }
    }
}