package TEMod.content;

import TEMod.TECustom.MultiChargeTurret;
import TEMod.TECustom.MultiCrafter;
import TEMod.TECustom.PortableCoreBlock;
import arc.graphics.Color;
import arc.math.Interp;
import arc.util.Log;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.campaign.LaunchPad;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.environment.AirBlock;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.logic.LogicDisplay;
import mindustry.world.blocks.logic.MemoryBlock;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.payloads.PayloadRouter;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.production.SolidPump;
import mindustry.world.blocks.production.WallCrafter;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.BuildVisibility;
import mindustry.world.meta.Env;

import static TEMod.ThermalEngineeringCore.isComplete;
import static mindustry.content.Fx.none;
import static mindustry.content.StatusEffects.shocked;
import static mindustry.content.StatusEffects.unmoving;
import static mindustry.type.ItemStack.with;

public class TEBlocks {
    public static OreBlock oreUranium, oreSphularite;//矿石
    //基础方块(S)
    public static Block highEfficiencyDisassembler; //高效解离机
    public static Block missileLauncher; //导弹发射井
    public static Block nuclearFuelRodManufacturingMachine; //核燃料棒制造机
    public static Block oreSmeltingFurnace; //矿石熔炼炉
    public static Block oreCrusher; //矿石粉碎机
    public static Block cryofluidMixerLarge; //大型冷冻液混合机
    public static Block advancedOverdriveDome; //高级超速穹顶
    //特殊
    public static Block surpluoIcon, erekirIcon, kepplerIcon; //星球图标
    public static Block primaryLaboratory, advancedLaboratory, specialLaboratory; //实验室
    public static Block chipManufacturingMachine, chipPrinter; //芯片制造机
    //电力
    public static Block nuclearReactor; //核反应堆
    public static Block advancedPowerNode; //高级电力节点
    //物流
    public static Block simpleStorage; //简易储存器
    public static Block highSpeedUnloader; //高速装卸器
    public static Block itemQuantumTransmissionLightBridge, liquidQuantumTransmissionLightBridge; //量子传输光桥
    //核心
    public static Block coreExplore; //探索核心
    //炮台
    public static Block machineCannon; //机炮
    public static Block prism; //棱镜 //重名了
    public static Block railGun; //轨道炮
    //发射台
    public static Block UnitLauncher, advancedUnitLauncher; //废稿之单位发射台
    public static Block advancedLaunchPad; //高级发射台
    public static Block unitStorageVault, unitStorageVaultLarge; //单位储存仓
    //逻辑
    public static Block terminalProcessor; //终端处理器
    public static Block hugeLogicDisplay; //巨型逻辑显示屏
    public static Block memoryBankLarge; //大型内存库
    //单位
    public static Block IllustratedReconstructor; //虚数级单位重构厂 //鸽
    //载荷
    public static Block payloadConveyorLarge, payloadConveyorHuge, payloadConveyorGigantic; //载荷传送带
    public static Block payloadRouterLarge, payloadRouterHuge, payloadRouterGigantic; //载荷路由器
    //护盾
    public static Block shieldGenerator, shieldGeneratorLarge, shieldGeneratorHuge, sectorShieldGenerator; //护盾发生器
    //挖掘
    public static Block advancedWaterExtractor; //抽水机
    public static Block smallCliffCrusher; //小墙钻

    //基础方块(E)
    public static Block reinforcedPowerNode; //E电力节点

    //基础方块(TEMod)
    public static Block pyratiteHeater; //硫热

    public static void load() {//别问为什么前段写那么屎(让以后的我能看懂的)
        machineCannon = new ItemTurret("machine-cannon") {{//这个mod从Json版本开始的第一个方块，也是梦开始的地方
            requirements(Category.turret, with(Items.copper, 200, Items.lead, 160, Items.graphite, 80));
            ammo(Items.copper, new BasicBulletType(8.0F, 27.0F) {{
                pierceCap = 1;
                knockback = 0.2F;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 10.0F;
            }}, Items.lead, new BasicBulletType(8.0F, 27.0F) {{
                pierceCap = 2;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 10.0F;
                knockback = 0.2F;
            }}, Items.graphite, new BasicBulletType(8.0F, 35.0F) {{
                pierceCap = 4;
                width = 2.0F;
                height = 5.0F;
                reloadMultiplier = 0.7F;
                ammoMultiplier = 10.0F;
                lifetime = 30.0F;
                status = unmoving;
                statusDuration = 2F;
                knockback = 0.2F;
            }}, Items.metaglass, new BasicBulletType(8.0F, 35.0F) {{
                pierceCap = 3;
                width = 2.0F;
                height = 5.0F;
                reloadMultiplier = 0.8F;
                ammoMultiplier = 7.0F;
                lifetime = 30.0F;
                status = unmoving;
                statusDuration = 2F;
                fragBullets = 2;
                fragLifeMin = 0.1F;
                fragRandomSpread = 30F;
                fragSpread = 45F;
                fragVelocityMin = 0.5F;
                fragVelocityMax = 1F;
                knockback = 0.4F;
                fragBullet = new BasicBulletType(2F, 6.0F) {{
                    pierce = true;
                    width = 1.0F;
                    height = 1.0F;
                    pierceCap = 2;
                }};
            }}, Items.surgeAlloy, new BasicBulletType(8.0F, 200.0F) {{
                pierceCap = 1;
                reloadMultiplier = 0.25F;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 25.0F;
                knockback = 0.5F;
                lightningDamage = 5F;
                lightning = 2;
                lightningLength = 6;
                lightningLengthRand = 8;
                lightningColor = Color.valueOf("ab99d3ff");
                status = shocked;
                fragBullets = 2;
                fragLifeMin = 0.1F;
                fragRandomSpread = 30F;
                fragSpread = 45F;
                fragVelocityMin = 0.5F;
                fragVelocityMax = 1F;
                fragBullet = new BasicBulletType(2F, 40.0F) {{
                    pierce = true;
                    width = 1.0F;
                    height = 1.0F;
                    pierceCap = 1;
                    fragBullets = 1;
                    fragLifeMin = 0.1F;
                    fragRandomSpread = 30F;
                    fragSpread = 45F;
                    fragVelocityMin = 0.5F;
                    fragVelocityMax = 1F;
                    lightningLength = 2;
                    fragBullet = new BasicBulletType(2F, 20.0F) {{
                        width = 0.6F;
                        height = 0.6F;
                        fragBullets = 1;
                        fragLifeMin = 0.1F;
                        fragRandomSpread = 30F;
                        fragSpread = 45F;
                        fragVelocityMin = 0.5F;
                        fragVelocityMax = 1F;
                        lightningLength = 1;
                        fragBullet = new BasicBulletType(2F, 15.0F) {{
                            width = 0.2F;
                            height = 0.2F;
                        }};
                    }};
                }};
            }},Items.silicon, new BasicBulletType(8.0F, 27.0F) {{
                pierceCap = 2;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 10.0F;
                knockback = 0.2F;
                homingPower = 0.1f;
                homingRange = 26f;
            }},TEItems.primaryChip, new BasicBulletType(8.0F, 30.0F) {{
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 15.0F;
                homingPower = 0.2f;
                homingRange = 30f;
            }});
            maxAmmo = 300;
            recoil = 0.7F;
            recoilTime = 2F;
            shootY = 5.0F;
            reload = 1.0F;
            range = 240.0F;
            ammoUseEffect = Fx.casing2;
            shootEffect = Fx.shootSmall;
            health = 2560;
            inaccuracy = 3.0F;
            rotateSpeed = 40.0F;
            coolantMultiplier = 2F;
            heatColor = Color.valueOf("ff0000");
            coolant = consume(new ConsumeLiquid(Liquids.water, 6f / 60f));
        }};

        highEfficiencyDisassembler = new Separator("high-efficiency-disassembler"){{
            requirements(Category.crafting, with(Items.copper, 450, Items.titanium, 200, Items.lead, 300, Items.graphite, 200, Items.thorium, 150, Items.silicon, 200, Items.plastanium, 200, Items.phaseFabric, 80, TEItems.advancedChip, 20, TEItems.specialProductionAgreement, 1));
            results = with(
                    Items.copper, 3,
                    Items.lead, 3,
                    Items.graphite, 1,
                    Items.titanium, 2,
                    Items.thorium, 1,
                    Items.silicon, 3,
                    Items.metaglass, 2,
                    Items.surgeAlloy, 1,
                    Items.phaseFabric, 1,
                    Items.plastanium, 1
            );
            craftTime = 5f;
            size = 4;
            health = 2500;
            itemCapacity = 40;
            liquidCapacity = 40;
            consumePower(8f);
            consumeLiquid(Liquids.slag, 20f / 60f);
            consumeItem(Items.scrap, 1);

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(),
                    new DrawRegion("-spinner", 7, true),
                    new DrawDefault()
            );
        }};

        surpluoIcon = new AirBlock("surpluo-icon") {{
            size = 2;
            alwaysUnlocked = true;
            requirements(Category.effect, BuildVisibility.hidden, with());
        }};

        erekirIcon = new AirBlock("erekir-icon") {{
            size = 2;
            alwaysUnlocked = true;
            requirements(Category.effect, BuildVisibility.hidden, with());
        }};

        kepplerIcon = new AirBlock("keppler-icon") {{
            size = 2;
            alwaysUnlocked = true;
            requirements(Category.effect, BuildVisibility.hidden, with());
        }};

        oreUranium = new OreBlock("ore-uranium", TEItems.uranium) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        primaryLaboratory = new MultiCrafter("primary-laboratory") {{
            requirements(Category.crafting, with(Items.copper, 1000, Items.titanium, 400, Items.lead, 1200, Items.graphite, 800, Items.thorium, 650, Items.silicon, 1000, Items.plastanium, 800, Items.phaseFabric, 300));

            recipes.add(new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.primaryProductionAgreement, 1)},
                    180 * 60
            ), new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.primaryWarAgreement, 1)},
                    180 * 60
            ));

            size = 3;
            health = 1000;
            itemCapacity = 10;
            consumePower(40f);
            hasItems = hasPower = true;
            canOverdrive = false;
        }};

        advancedLaboratory = new MultiCrafter("advanced-laboratory") {{
            requirements(Category.crafting, with(Items.copper, 2000, Items.titanium, 800, Items.lead, 2400, Items.graphite, 1600, Items.thorium, 1300, Items.silicon, 6000, Items.plastanium, 3000, Items.phaseFabric, 1000, TEItems.uranium, 400));

            recipes.add(new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.advancedProductionAgreement, 1)},
                    120 * 60
            ), new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.advancedWarAgreement, 1)},
                    120 * 60
            ), new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.highSpeedTransmissionProtocol, 1)},
                    120 * 60
            ));

            size = 5;
            health = 2000;
            itemCapacity = 10;
            consumePower(50f);
            hasItems = hasPower = true;
            canOverdrive = false;
        }};

        specialLaboratory = new MultiCrafter("special-laboratory") {{
            requirements(Category.crafting, with(Items.copper, 1000, Items.titanium, 400, Items.lead, 1200, Items.graphite, 800, Items.thorium, 650, Items.silicon, 1000, Items.plastanium, 800, Items.phaseFabric, 300, TEItems.uranium, 2000));

            recipes.add(new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.specialProductionAgreement, 1)},
                    80 * 60
            ), new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.specialWarAgreement, 1)},
                    80 * 60
            ), new Recipe(
                    new ItemStack[]{},
                    new ItemStack[]{new ItemStack(TEItems.ultraRemoteTransmissionProtocol, 1)},
                    80 * 60
            ));

            size = 7;
            health = 4000;
            itemCapacity = 20;
            consumePower(60f);
            liquidCapacity = 50f;
            requiresLiquid(Liquids.cryofluid, 2f);
            hasLiquids = hasPower = hasItems = true;
            canOverdrive = false;
        }};

        chipManufacturingMachine = new MultiCrafter("chip-manufacturing-machine") {{
            requirements(Category.crafting, with(Items.copper, 700, Items.titanium, 700, Items.lead, 800, Items.graphite, 800, Items.silicon, 8000));
            size = 3;
            health = 800;
            itemCapacity = 30;
            consumePower(5f);
            hasItems = hasPower = true;

            recipes.add(new Recipe(
                    new ItemStack[]{new ItemStack(Items.silicon, 3), new ItemStack(Items.copper, 2), new ItemStack(Items.lead, 1)},
                    new ItemStack[]{new ItemStack(TEItems.primaryChip, 1)},
                    30
            ), new Recipe(
                    new ItemStack[]{new ItemStack(Items.silicon, 4), new ItemStack(Items.copper, 2), new ItemStack(Items.lead, 1)},
                    new ItemStack[]{new ItemStack(TEItems.advancedChip, 1)},
                    40
            ));
        }};

        chipPrinter = new MultiCrafter("chip-printer") {{
            requirements(Category.crafting, with(Items.copper, 2000, Items.titanium, 1000, Items.lead, 2400, Items.graphite, 1500, Items.silicon, 10000, Items.plastanium, 2000, Items.phaseFabric, 800, TEItems.advancedChip, 70, TEItems.advancedProductionAgreement, 1));

            size = 5;
            health = 1000;
            itemCapacity = 40;
            consumePower(6f);
            alwaysUnlocked = false;
            requiresLiquid(Liquids.cryofluid, 0.2f);

            recipes.add(new Recipe(
                    new ItemStack[]{new ItemStack(Items.silicon, 3)},
                    new ItemStack[]{new ItemStack(TEItems.primaryChip, 1)},
                    10
            ), new Recipe(
                    new ItemStack[]{new ItemStack(Items.silicon, 5)},
                    new ItemStack[]{new ItemStack(TEItems.advancedChip, 1)},
                    20
            ), new Recipe(
                    new ItemStack[]{new ItemStack(Items.silicon, 6)},
                    new ItemStack[]{new ItemStack(TEItems.specialChip, 1)},
                    30
            ));

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidRegion() {{
                        drawLiquid = Liquids.cryofluid;
                        suffix = "-cryo";
                    }},
                    new DrawDefault(),
                    new DrawRegion("-chipBuild")
            );
            hasItems = hasPower = hasLiquids = true;
            liquidCapacity = 20f;
        }};

        missileLauncher = new ItemTurret("missile-launcher") {{
            health = 3500;
            size = 5;

            requirements(Category.turret, with(Items.copper, 900, Items.lead, 1100, Items.graphite, 700, Items.metaglass, 300, Items.silicon, 250, TEItems.primaryChip, 20));

            rotateSpeed = 0F;
            range = 145f * 8;
            reload = 65f;
            liquidCapacity = 50f;
            coolantMultiplier = 2f;
            coolant = consume(consumeLiquid(Liquids.water, 13f / 60f));
            maxAmmo = 320;
            ammoPerShot = 40;
            consumePower(6f);
            shootCone = 360;
            minWarmup = 0.8f;
            shootWarmupSpeed = 0.055f;
            warmupMaintainTime = 120;

            shoot = new ShootBarrel() {{
                shots = 4;
                shotDelay = 10f;
                barrels = new float[]{
                        -14, 0, 0,
                        -11, 11, 0,
                        0, 14, 0,
                        11 ,11, 0,
                        14, 0, 0,
                        11, -11, 0,
                        0, -14, 0,
                        -11, -11, 0
                };
            }};
            shootSound = Sounds.missileLaunch;
            recoil = 0f;
            shootY = 0;

            ammo(Items.pyratite, new BasicBulletType(0f, 1f) {{
                ammoMultiplier = 4f;
                spawnUnit = new MissileUnitType("missile-launcher-missile-pyratite") {{
                    speed = 8f;
                    lifetime = 4f * 60f;
                    trailLength = 11;
                    homingPower = 0.1f;
                    homingDelay = 80f;
                    missileAccelTime = 120f;
                    health = 400f;
                    rotateSpeed = 15f;
                    deathSound = Sounds.largeExplosion;
                    weapons.add(new Weapon() {{
                        shootCone = 360f;
                        mirror = false;
                        reload = 1f;
                        x = y = 0;
                        deathExplosionEffect = Fx.massiveExplosion;
                        shootOnDeath = true;
                        shake = 10f;
                        bullet = new ExplosionBulletType(874f, 96f) {{
                            hitColor = Pal.redLight;
                            shootEffect = new MultiEffect(Fx.massiveExplosion, new WaveEffect(){{
                                lifetime = 6f;
                                strokeFrom = 4f;
                                sizeTo = 109f;
                            }});
                            buildingDamageMultiplier = 0.8f;
                            ammoMultiplier = 1f;
                            status = StatusEffects.burning;
                        }};
                    }});
                }};
            }}, Items.blastCompound, new BasicBulletType(0f, 1f) {{
                ammoMultiplier = 4f;
                spawnUnit = new MissileUnitType("missile-launcher-missile-blast-compound") {{
                    speed = 8f;
                    lifetime = 4f * 60f;
                    trailLength = 11;
                    homingPower = 0.1f;
                    homingDelay = 80f;
                    missileAccelTime = 120f;
                    health = 400f;
                    rotateSpeed = 15f;
                    deathSound = Sounds.largeExplosion;
                    weapons.add(new Weapon() {{
                        shootCone = 360f;
                        mirror = false;
                        reload = 1f;
                        x = y = 0;
                        deathExplosionEffect = Fx.massiveExplosion;
                        shootOnDeath = true;
                        shake = 10f;
                        bullet = new ExplosionBulletType(1279f, 126f) {{
                            hitColor = Pal.redLight;
                            shootEffect = new MultiEffect(Fx.massiveExplosion, new WaveEffect(){{
                                lifetime = 6f;
                                strokeFrom = 4f;
                                sizeTo = 168f;
                            }});
                            buildingDamageMultiplier = 0.8f;
                            ammoMultiplier = 1f;
                        }};
                    }});
                }};
            }}, TEItems.nuclearFuelRod, new BasicBulletType(0f, 1f) {{
                ammoMultiplier = 1f;
                reloadMultiplier = 0.01f;
                spawnUnit = new MissileUnitType("missile-launcher-missile-nuclear-fuel-rod") {{
                    speed = 8f;
                    lifetime = 4f * 60f;
                    trailLength = 11;
                    homingPower = 0.1f;
                    homingDelay = 80f;
                    missileAccelTime = 120f;
                    health = 400f;
                    rotateSpeed = 15f;
                    deathSound = Sounds.explosionbig;
                    range = 16f;
                    weapons.add(new Weapon() {{
                        shootCone = 360f;
                        mirror = false;
                        reload = 1f;
                        x = y = 0;
                        deathExplosionEffect = Fx.reactorExplosion;
                        shootOnDeath = true;
                        shake = 10f;
                        range = 16f;
                        bullet = new ExplosionBulletType(45628f, 300f) {{
                            hitColor = Pal.redLight;
                            pierceArmor = true;
                            shootEffect = new MultiEffect(Fx.reactorExplosion, new WaveEffect() {{
                                lifetime = 6f;
                                strokeFrom = 4f;
                                sizeTo = 267f;
                            }});
                            ammoMultiplier = 1f;
                        }};
                    }});
                }};
            }});
        }};

        nuclearReactor = new NuclearReactor("nuclear-reactor") {{
            health = 5000;
            size = 5;
            liquidCapacity = 30;
            itemCapacity = 20;
            hasItems = hasLiquids = outputsPower = true;
            powerProduction = 230f;
            itemDuration = 30f;
            lightColor = Color.valueOf("ffffff");
            explosionShake = 9;
            explosionShakeDuration = 120;
            explosionRadius = 80;
            explosionDamage = 10000;
            explodeSound = Sounds.explosionbig;
            fuelItem = TEItems.nuclearFuelRod;
            heating = 0.06f;
            coolantPower = 2;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            consumeItem(TEItems.nuclearFuelRod, 1);
            consumeLiquid(Liquids.cryofluid, heating / coolantPower).update(false);
            buildCostMultiplier = 3;
            requirements(Category.power, with(Items.lead, 1400, Items.graphite, 890, Items.metaglass, 320, Items.thorium, 750, Items.titanium, 800, Items.surgeAlloy, 260, TEItems.advancedChip, 10, TEItems.zinc, 600));
            explodeEffect = new MultiEffect(
                    new WaveEffect() {{
                        lifetime = 20;
                        sizeFrom = 0;
                        sizeTo = 320;
                        strokeFrom = 18;
                        strokeTo = 0;
                        colorFrom = Color.valueOf("eec591");
                        colorTo = Color.valueOf("ffffff");
                    }},
                    new ParticleEffect() {{
                        particles = 25;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 35;
                        sizeTo = 0;
                        length = 280;
                        baseLength = 0;
                        lifetime = 250;
                        colorFrom = Color.valueOf("dedede70");
                        colorTo = Color.valueOf("dedede70");
                    }},
                    new ParticleEffect() {{
                        particles = 35;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 30;
                        sizeTo = 0;
                        length = 290;
                        baseLength = 0;
                        lifetime = 300;
                        colorFrom = Color.valueOf("dedede70");
                        colorTo = Color.valueOf("dedede70");
                    }},
                    new ParticleEffect() {{
                        particles = 40;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 20;
                        sizeTo = 0;
                        length = 300;
                        baseLength = 0;
                        lifetime = 350;
                        colorFrom = Color.valueOf("dedede70");
                        colorTo = Color.valueOf("dedede70");
                    }},
                    new ParticleEffect() {{
                        particles = 25;
                        line = true;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow3In;
                        strokeFrom = 3;
                        strokeTo = 0;
                        lenFrom = 150;
                        lenTo = 0;
                        length = 220;
                        baseLength = 60;
                        lifetime = 60;
                        colorFrom = Color.valueOf("dedede");
                        colorTo = Color.valueOf("dedede");
                    }}
            );
        }};

        simpleStorage = new StorageBlock("simple-storage") {{
            requirements(Category.effect, with(Items.copper, 200, Items.lead, 260, Items.graphite, 80));
            size = 2;
            itemCapacity = 100;
            coreMerge = false;
            health = 100;
        }};

        highSpeedUnloader = new Unloader("high-speed-unloader") {{
            speed = 0.75f;
            group = BlockGroup.transportation;
            requirements(Category.effect, with(Items.thorium, 20, Items.silicon, 45, Items.titanium, 50, Items.plastanium, 30));
            health = 60;
        }};

        nuclearFuelRodManufacturingMachine = new GenericCrafter("nuclear-fuel-rod-manufacturing-machine") {{
            size = 3;
            health = 1600;
            itemCapacity = 20;
            liquidCapacity = 20;
            hasItems = hasLiquids = hasPower = true;
            craftTime = 30f;
            updateEffect = none;
            consumePower(35f / 60f);
            consumeItem(TEItems.uranium, 5);
            consumeLiquid(Liquids.water, 10f / 60f);
            outputItem = new ItemStack(TEItems.nuclearFuelRod, 1);
            requirements(Category.crafting, with(Items.thorium, 530, Items.titanium, 680, Items.silicon, 580, Items.lead, 680, Items.surgeAlloy, 200, Items.graphite, 790, TEItems.advancedChip, 20));
        }};

        coreExplore = new PortableCoreBlock("core-explore") {{
            health = 3000;
            itemCapacity = 0;
            unitCapModifier = 0;
            thrusterLength = 20 / 4f;
            isFirstTier = alwaysUnlocked = true;
            armor = 18;
            size = 3;
            unitType = UnitTypes.alpha;
            requirements(Category.effect, with(Items.copper, 1000, Items.lead, 1200, Items.graphite, 500, Items.silicon, 400));
        }};

        advancedLaunchPad = new LaunchPad("advanced-launchpad") {{
            requirements(Category.effect, BuildVisibility.campaignOnly, with(Items.copper, 400, Items.silicon, 200, Items.lead, 300, Items.titanium, 200, Items.thorium, 100));
            health = 500;
            size = 4;
            hasPower = true;
            launchTime = 400f;
            consumePower(10f);
            itemCapacity = 200;
            buildCostMultiplier = 0.8f;
        }};

        unitStorageVault = new StorageBlock("unit-storage-vault") {{
            health = 24000;
            unitCapModifier = 24;
            size = 5;
            armor = 25;
            itemCapacity = 0;
            requirements(Category.effect, with(Items.silicon, 8000, Items.thorium, 4000, Items.surgeAlloy, 2000, Items.graphite, 6000, TEItems.advancedChip, 40, Items.titanium, 5000, Items.metaglass, 6000, Items.lead, 8000));
            buildCostMultiplier = 0.8f;
        }};

        unitStorageVaultLarge = new StorageBlock("large-unit-storage-vault") {{
            health = 70000;
            unitCapModifier = 64;
            itemCapacity = 0;
            size = 9;
            armor = 80;
            requirements(Category.effect, with(Items.silicon, 80000, Items.thorium, 40000, Items.surgeAlloy, 20000, Items.graphite, 60000, TEItems.advancedChip, 300, TEItems.specialChip, 70, Items.titanium, 50000, Items.metaglass, 60000, Items.lead, 80000, Items.phaseFabric, 100000));
            buildCostMultiplier = 0.8f;
        }};

        prism = new MultiChargeTurret("prism") {{
            health = 1500;
            size = 1;

            shootType = new LightningBulletType() {{
                damage = 0f;
                ammoMultiplier = 1f;
                hitEffect = Fx.none;
                hitSound = Sounds.none;
                lifetime = 0;
                shootEffect = Fx.none;
                shootSound = Sounds.none;
            }};

            requirements(Category.turret, with(Items.copper, 500, Items.lead, 650, Items.titanium, 350, Items.silicon, 200));

            rotateSpeed = 10f;
            range = 51.625f * 8f;
            reload = 1.5f * 60f;
            consumePower(10f);
            Log.info("Load MultiChargeTurret(prism)");
        }};

//        railGun = new ItemTurret("railGun") {{
//            health = 5120;
//            size = 5;
//            maxAmmo = 50;
//            ammoPerShot = 5;
//            shootSound = Sounds.mediumCannon;
//            range = 48 *8;
//            reload = 120f;
//
//            ammo(
//                    Items.thorium, new BasicBulletType(4, 673f) {{
//                        scaledSplashDamage = true;
//                        pierce = pierceBuilding = true;
//                        pierceCap = 16;
//                        hitEffect = new MultiEffect(Fx.titanExplosion, Fx.titanSmoke);
//                        hitColor = Color.valueOf("f9a3c7");
//                        width = 15.0F;
//                        height = 40.0F;
//                        lifetime = 90.0F;
//                        ammoMultiplier = 2.5F;
//                        splashDamage = 384f;
//                        splashDamageRadius = 8 * 8;
//                    }}, TEItems.uranium, new BasicBulletType(5, 1046f) {{
//                        scaledSplashDamage = true;
//                        pierceCap = 24;
//                        pierce = pierceBuilding = true;
//                        hitEffect = new MultiEffect(Fx.titanExplosion, Fx.titanSmoke);
//                        hitColor = Color.valueOf("617270");
//                        width = 15.0F;
//                        height = 40.0F;
//                        lifetime = 90.0F;
//                        ammoMultiplier = 1.25F;
//                        splashDamage = 512f;
//                        splashDamageRadius = 12 * 8;
//                        rangeChange = 11f * 8f;
//                    }}
//            );
//
//            requirements(Category.turret, with(Items.thorium, 1500, Items.copper, 3000, Items.lead, 4500, Items.silicon, 5000, Items.titanium, 3500, Items.surgeAlloy, 800, Items.plastanium, 1200));
//
//            coolantMultiplier = 0.2f;
//            coolant = consume(consumeLiquid(Liquids.water, 16f / 60f));
//        }};

        oreSmeltingFurnace = new MultiCrafter("ore-smelting-furnace") {{
            health = 1280;
            hasPower = hasItems = true;
            consumePower(8f);
            size = 3;

            recipes.add(new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.copperPowder, 1)},
                    new ItemStack[]{new ItemStack(Items.copper, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.leadPowder, 1)},
                    new ItemStack[]{new ItemStack(Items.lead, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.thoriumPowder, 1)},
                    new ItemStack[]{new ItemStack(Items.thorium, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.titaniumPowder, 1)},
                    new ItemStack[]{new ItemStack(Items.titanium, 1)},
                    40f
            ));
            recipes.add(new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.zincPowder, 1)},
                    new ItemStack[]{new ItemStack(TEItems.zinc, 1)},
                    40f
            ));

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));

            requirements(Category.crafting, with(Items.copper, 500, Items.lead, 700, Items.silicon, 900, Items.titanium, 500));
        }};

        oreSphularite = new OreBlock("ore-sphularite", TEItems.sphularite) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        oreCrusher = new MultiCrafter("ore-crusher") {{
            health = 1000;
            size = 2;

            recipes.add(new Recipe(
                    new ItemStack[]{new ItemStack(Items.copper, 1)},
                    new ItemStack[]{new ItemStack(TEItems.copperPowder, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(Items.lead, 1)},
                    new ItemStack[]{new ItemStack(TEItems.leadPowder, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(Items.thorium, 1)},
                    new ItemStack[]{new ItemStack(TEItems.thoriumPowder, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(Items.titanium, 1)},
                    new ItemStack[]{new ItemStack(TEItems.titaniumPowder, 1)},
                    40f
            ));
            recipes.add(new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.zinc, 1)},
                    new ItemStack[]{new ItemStack(TEItems.zincPowder, 1)},
                    40f
            ), new Recipe(
                    new ItemStack[]{new ItemStack(TEItems.sphularite, 1)},
                    new ItemStack[]{new ItemStack(TEItems.zincPowder, 2)},
                    40f
            ));

            requirements(Category.crafting, with(Items.copper, 400, Items.lead, 650, Items.silicon, 400, Items.titanium, 250));

            hasPower = hasItems = true;
            consumePower(5f);

            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 2f;
            }}, new DrawRegion("-top"));

            craftEffect = Fx.pulverize;
            updateEffect = Fx.pulverizeSmall;
        }};

        cryofluidMixerLarge = new GenericCrafter("large-cryofluid-mixer") {{
            requirements(Category.crafting, with(Items.lead, 650, Items.silicon, 400, Items.titanium, 600, Items.thorium, 350));
            outputLiquid = new LiquidStack(Liquids.cryofluid, 35f / 60f);
            size = 3;
            hasPower = hasLiquids = hasItems = outputsLiquid = solid = true;
            rotate = false;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid){{drawLiquidLight = true;}}, new DrawDefault());
            liquidCapacity = 50f;
            craftTime = 50;
            lightLiquid = Liquids.cryofluid;
            itemCapacity = 30;

            consumePower(7f);
            consumeItem(TEItems.titaniumPowder);
            consumeLiquid(Liquids.water, 30f / 60f);
        }};

        advancedOverdriveDome = new OverdriveProjector("advanced-overdrive-dome") {{
            requirements(Category.effect, with(Items.lead, 1250, Items.titanium, 1450, Items.silicon, 1500, Items.plastanium, 1200, Items.surgeAlloy, 850, TEItems.zinc, 450, TEItems.advancedChip, 450));
            consumePower(14f);
            size = 4;
            range = 400f;
            speedBoost = 5.5f;
            useTime = 200f;
            hasBoost = false;
            itemCapacity = 20;

            consumeItems(with(Items.phaseFabric, 3, Items.silicon, 3, TEItems.zinc, 2));
        }};

        advancedPowerNode = new PowerNode("advanced-power-node") {{
            requirements(Category.power, with(Items.copper, 5, Items.lead, 12, Items.titanium, 15, Items.silicon, 5));
            maxNodes = 20;
            laserRange = 12;
        }};

        terminalProcessor = new LogicBlock("terminal-processor") {{
            requirements(Category.logic, with(Items.lead, 4500, Items.silicon, 3000, Items.thorium, 1000, Items.surgeAlloy, 500, Items.titanium, 2000, TEItems.zinc, 800, Items.phaseFabric, 300, Items.plastanium, 1200, TEItems.specialChip, 400));

            consumeLiquid(Liquids.cryofluid, 0.2f);
            hasLiquids = true;

            instructionsPerTick = 80;
            range = 8 * 100;
            size = 5;
        }};

        hugeLogicDisplay = new LogicDisplay("huge-logic-display") {{
            requirements(Category.logic, with(Items.lead, 2000, Items.silicon, 1500, Items.metaglass, 1000, Items.phaseFabric, 400, Items.surgeAlloy, 300, TEItems.zinc, 500, TEItems.advancedChip, 100, Items.plastanium, 600));

            displaySize = 300;

            size = 10;
        }};

        memoryBankLarge = new MemoryBlock("large-memory-bank") {{
            requirements(Category.logic, with(Items.graphite, 800, Items.silicon, 100, Items.phaseFabric, 400, Items.copper, 800, TEItems.advancedChip, 400, TEItems.zinc, 500, Items.plastanium, 700, Items.surgeAlloy, 200));

            memoryCapacity = 1024;
            size = 3;
        }};



//        IllustratedReconstructor = new Reconstructor("illustrated-reconstructor") {{
//            requirements(Category.units, with(Items.silicon, 55000, Items.plastanium, 35000, Items.surgeAlloy, 12450, Items.phaseFabric, 5000, TEItems.zinc, 35000, TEItems.advancedChip, 5000, TEItems.uranium, 12000, TEItems.specialChip, 2000));
//
//            size = 11;
//            consumePower(75f);
//            consumeItems(with(Items.silicon, 16384, Items.plastanium, 12356, Items.surgeAlloy, 5420, Items.phaseFabric, 3500, TEItems.zinc, 14000, TEItems.advancedChip, 600, TEItems.uranium, 4000, TEItems.specialChip, 100));
//            consumeLiquid(Liquids.cryofluid, 220f / 60f);
//            liquidCapacity = 440f;
//
//            constructTime = 60f * 60f * 12.5f;
//
//            upgrades.addAll(
//                    new UnitType[]{UnitTypes.eclipse, UnitTypes.pulsar},
//                    new UnitType[]{UnitTypes.toxopid, UnitTypes.toxopid},
//                    new UnitType[]{UnitTypes.reign, UnitTypes.reign},
//                    new UnitType[]{UnitTypes.omura, UnitTypes.omura},
//                    new UnitType[]{UnitTypes.oct, UnitTypes.oct},
//                    new UnitType[]{UnitTypes.corvus, UnitTypes.risso},
//                    new UnitType[]{UnitTypes.navanax, UnitTypes.retusa}
//            );
//        }};

        payloadConveyorLarge = new PayloadConveyor("large-payload-conveyor") {{
            requirements(Category.units, with(Items.graphite, 50, Items.copper, 100, Items.silicon, 25));
            size = (int) (payloadLimit = 5);
            canOverdrive = false;
        }};

        payloadConveyorHuge = new PayloadConveyor("huge-payload-conveyor") {{
            requirements(Category.units, with(Items.graphite, 200, Items.copper, 200, Items.silicon, 70));
            size = (int) (payloadLimit = 7);
            canOverdrive = false;
        }};

        payloadConveyorGigantic = new PayloadConveyor("gigantic-payload-conveyor") {{
            requirements(Category.units, with(Items.graphite, 500, Items.copper, 500, Items.silicon, 250, Items.titanium, 150));
            size = (int) (payloadLimit = 9);
            canOverdrive = false;
        }};

        payloadRouterLarge = new PayloadRouter("large-payload-router") {{
            requirements(Category.units, with(Items.graphite, 50, Items.copper, 100, Items.silicon, 25));
            canOverdrive = false;
            size = (int) (payloadLimit = payloadConveyorLarge.size);
        }};

        payloadRouterHuge = new PayloadRouter("huge-payload-router") {{
            requirements(Category.units, with(Items.graphite, 200, Items.copper, 200, Items.silicon, 70));
            size = (int) (payloadLimit = payloadConveyorHuge.size);
            canOverdrive = false;
        }};

        payloadRouterGigantic = new PayloadRouter("gigantic-payload-router") {{
            requirements(Category.units, with(Items.graphite, 500, Items.copper, 500, Items.silicon, 250, Items.titanium, 150));
            size = (int) (payloadLimit = payloadConveyorGigantic.size);
            canOverdrive = false;
        }};

        shieldGenerator = new ForceProjector("shield-generator") {{
            requirements(Category.effect, with(Items.lead, 50, Items.titanium, 45, Items.silicon, 75, Items.copper, 80, TEItems.primaryChip, 5));
            phaseRadiusBoost = 6f * 8f;
            radius = 8f * 8f;
            shieldHealth = 450f;
            cooldownNormal = 1.0f;
            cooldownLiquid = 1.5f;
            cooldownBrokenBase = 5f / 60f;
            phaseShieldBoost = 250f;

            itemConsumer = consumeItem(TEItems.primaryChip, 1).boost();
            consumePower(1f);
        }};

        shieldGeneratorLarge = new ForceProjector("large-shield-generator") {{
            requirements(Category.effect, with(Items.lead, 450, Items.titanium, 230, Items.silicon, 150, Items.copper, 550, TEItems.advancedChip, 10));
            size = 2;
            phaseRadiusBoost = 16f * 8f;
            radius = 25f * 8f;
            shieldHealth = 1200f;
            cooldownNormal = 0.9f;
            cooldownLiquid = 1.5f;
            cooldownBrokenBase = 1.0f;
            phaseShieldBoost = 900f;

            itemConsumer = consumeItem(Items.phaseFabric, 2).boost();
            consumePower(8f);
        }};

        shieldGeneratorHuge = new ForceProjector("huge-shield-generator") {{
            requirements(Category.effect, with(Items.lead, 2400, Items.titanium, 2000, Items.silicon, 2500, Items.copper, 5400, TEItems.specialChip, 50, Items.phaseFabric, 400));
            size = 4;
            phaseRadiusBoost = 45f * 8f;
            radius = 70f * 8f;
            shieldHealth = 3500f;
            cooldownNormal = 0.8f;
            cooldownLiquid = 1.5f;
            cooldownBrokenBase = 120f / 60f;
            phaseShieldBoost = 3500f;

            itemConsumer = consumeItems(with(Items.phaseFabric, 5, TEItems.zinc, 3, TEItems.advancedChip, 2)).boost();
            consumePower(8f);
        }};

        sectorShieldGenerator = new ForceProjector("sector-shield-generator") {{
            requirements(Category.effect, with(Items.lead, 24000, Items.titanium, 16000, Items.silicon, 30000, Items.copper, 25000, TEItems.specialChip, 500, Items.phaseFabric, 2000));
            size = 6;
            radius = 900f * 8f;
            shieldHealth = 50000f;
            cooldownNormal = 3000f / 60f;
            cooldownLiquid = 2f;
            cooldownBrokenBase = 4000f / 60f;
            phaseShieldBoost = 30000f;
            itemCapacity = 20;

            itemConsumer = consumeItems(with(Items.phaseFabric, 10, TEItems.zinc, 6, TEItems.advancedChip, 8, TEItems.specialChip, 5)).boost();
            consumePower(8f);
        }};

        itemQuantumTransmissionLightBridge = new ItemBridge("item-quantum-transmission-light-bridge") {{
            requirements(Category.distribution, with(Items.phaseFabric, 30, Items.silicon, 50, Items.lead, 200, Items.graphite, 100));
            range = 60;
            arrowPeriod = 0.9f;
            arrowTimeScl = 2.75f;
            hasPower = true;
            pulse = true;
            transportTime = 0.001F;
            consumePower(0.9f);
            itemCapacity = 30;
        }};

        liquidQuantumTransmissionLightBridge = new LiquidBridge("liquid-quantum-transmission-light-bridge") {{
            requirements(Category.liquid, with(Items.phaseFabric, 30, Items.silicon, 35, Items.metaglass, 100, Items.titanium, 60));
            range = 60;
            arrowPeriod = 0.9f;
            arrowTimeScl = 2.75f;
            hasPower = true;
            pulse = true;
            consumePower(0.9f);
            liquidCapacity = 200;
        }};

        advancedWaterExtractor = new SolidPump("advanced-water-extractor") {{
            requirements(Category.production, with(Items.metaglass, 200, Items.graphite, 300, Items.lead, 450, Items.copper, 400, TEItems.zinc, 150, TEItems.primaryChip, 20));
            result = Liquids.water;
            pumpAmount = 15f / 60f;
            size = 3;
            liquidCapacity = 60f;
            rotateSpeed = 2.4f;
            attribute = Attribute.water;
            envRequired |= Env.groundWater;

            consumePower(3f);
        }};

        smallCliffCrusher = new WallCrafter("small-cliff-crusher") {{
            health = 120;
            drillTime = 220f;
            consumeLiquid(Liquids.water, 4f / 60f).boost();
            attribute = Attribute.sand;
            output = Items.sand;
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;
            fogRadius = 2;
            requirements(Category.production, with(Items.copper, 50, Items.lead, 35, Items.graphite, 20));
        }};

        //基础方块(E)

        reinforcedPowerNode = new PowerNode("reinforced-power-node") {{
            requirements(Category.power, with(Items.beryllium, 10, Items.tungsten, 6, Items.graphite, 8));
            maxNodes = 6;
            laserRange = 10;
            health = 150;
        }};

        //基础方块(TEMod)

        pyratiteHeater = new HeatProducer("pyratite-heater") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 120, Items.silicon, 200, Items.lead, 150, Items.graphite, 225, Items.titanium, 100));
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            size = 2;
            heatOutput = 6.0F;
            craftTime = 200.0F;
            ambientSound = Sounds.hum;
            consumeItem(Items.pyratite);
        }};

        isComplete(TEBlocks.class);
    }
}