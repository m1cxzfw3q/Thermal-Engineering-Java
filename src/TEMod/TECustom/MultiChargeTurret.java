package TEMod.TECustom;

import mindustry.content.Fx;
import mindustry.entities.*;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.tilesize;

public class MultiChargeTurret extends PowerTurret {
    //============== 可配置参数 ==============
    /** 最大充能时间（帧数） */
    protected float maxCharge = 3f * 60f;
    /** 各阶段充能阈值（百分比数组） */
    protected float[] chargeThresholds = {0.25f, 0.5f, 0.75f, 1.0f};
    /** 自伤比例 */
    protected float selfDamageRatio = 0.5f;
    /** 过载爆炸伤害 */
    protected float overloadDamage = 350f;
    /** 过载爆炸半径 */
    protected float overloadRadius = 8f;

    //============== 子弹类型 ==============
    protected BulletType[] stageBullets = new BulletType[4];
    protected Effect[] stageEffects = {
            Fx.generatespark, Fx.shootBigSmoke, Fx.massiveExplosion
    };

    public MultiChargeTurret(String name) {
        super(name);
        MultiCharge();
    }

    //============== 初始化方法 ==============

    /** 配置子弹类型（关键扩展点） */
    public void MultiCharge(Object... objects) {
        stageBullets = (BulletType[]) objects;
    }

    //============== 逻辑类 ==============
    public class MultiChargeTurretBuild extends PowerTurretBuild {
        protected float charge = 0f;
        protected int currentStage = 0;
        protected boolean isCharging = false;

        @Override
        public void updateTile() {
            super.updateTile();

            // 仅在启用且满足条件时运行
            if(enabled){
                if(consValid()){
                    handleCharging();
                    handleAutoFire();
                }else{
                    resetSystem();
                }
            }
        }

        //============== 核心逻辑方法 ==============
        /** 更新后的充能处理方法 */
        protected void handleCharging() {
            if(target != null){
                startCharging();
                updateChargeProgress();
                updateStage();
            }else if(isCharging){
                releaseAttack();
            }
        }

        /** 自动开火检测（可重写） */
        protected void handleAutoFire() {
            if(charge >= maxCharge){
                releaseOverload();
                onOverloadReleased();
            }
        }

        //============== 阶段操作方法 ==============
        /** 开始充能时的回调 */
        protected void onChargeStart(){
            // 可添加粒子效果或声音
        }

        /** 阶段变更时的回调 */
        protected void onStageChanged(int prevStage, int newStage){
            // 可添加阶段切换特效
        }

        /** 成功释放过载时的回调 */
        protected void onOverloadReleased(){
            // 可添加额外效果
        }

        //============== 功能方法 ==============
        /** 更新充能进度 */
        protected void updateChargeProgress() {
            charge = Math.min(charge + delta(), maxCharge);
            isCharging = true;
        }

        /** 计算当前阶段 */
        protected void updateStage() {
            int newStage = calculateStage();
            if(newStage != currentStage){
                onStageChanged(currentStage, newStage);
                currentStage = newStage;
            }
        }

        /** 阶段计算算法（可重写） */
        protected int calculateStage() {
            float progress = charge / maxCharge;
            for(int i=0; i<chargeThresholds.length; i++){
                if(progress < chargeThresholds[i]) return i;
            }
            return chargeThresholds.length - 1;
        }

        /** 常规攻击释放 */
        protected void releaseAttack() {
            applySelfDamage();
            createBullet();
            playStageEffect();
            resetSystem();
        }

        /** 过载攻击释放 */
        protected void releaseOverload() {
            Damage.damage(Team.get(0), x, y, overloadRadius * tilesize, overloadDamage);
            createBullet();
            resetSystem();
        }

        /** 创建子弹（可重写子弹生成逻辑） */
        protected void createBullet() {
            shoot(stageBullets[Math.min(currentStage, stageBullets.length-1)]);
        }

        /** 自伤逻辑 */
        protected void applySelfDamage() {
            if(currentStage < chargeThresholds.length-1){
                damage(health * selfDamageRatio);
            }
        }

        /** 播放阶段特效 */
        protected void playStageEffect() {
            stageEffects[Math.min(currentStage, stageEffects.length-1)].at(x, y);
        }

        protected boolean Charging() {
            return charge < maxCharge && isCharging;
        }

        /** 更新后的重置方法 */
        protected void resetSystem() {
            charge = 0;
            currentStage = 0;
            isCharging = false;
        }

        /** 检查是否满足充能条件 */
        protected boolean consValid() {
            // 同时满足电力需求和冷却状态
            return enabled && power.status >= 0.99f && !Charging();
        }

        /** 开始充能时的初始化 */
        protected void startCharging() {
            if(!isCharging){
                isCharging = true;
                onChargeStart(); // 触发回调
            }
        }
    }
}