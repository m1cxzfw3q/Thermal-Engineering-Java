package TEMod.content;

import TEMod.MultiCraftLib.MultiCrafter;
import TEMod.MultiCraftLib.Recipe;
import arc.graphics.Color;
import arc.graphics.g2d.TextureAtlas;
import arc.math.Interp;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.environment.AirBlock;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.sandbox.ItemSource;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.BuildVisibility;

import static arc.Core.input;
import static mindustry.content.Fx.none;
import static mindustry.content.StatusEffects.shocked;
import static mindustry.content.StatusEffects.unmoving;
import static mindustry.type.ItemStack.with;
import static mindustry.world.meta.StatUnit.items;

public class TEBlocks {
    //矿石
    public static OreBlock oreUranium;
    //基础方块
    public static Block machineCannon; //机炮
    public static Block highEfficiencyDisassembler; //高效解离机
    public static Block portableMissileLaunchSilo; //便携式导弹发射井
    public static Block missileLauncher; //导弹发射井
    public static Block nuclearFuelRodManufacturingMachine; //核燃料棒制造机
    //特殊
    public static Block surpluoIcon;
    public static Block erekirIcon;
    public static Block kepplerIcon;
    //实验室
    public static Block primaryLaboratory; //初级实验室
    public static Block advancedLaboratory; //高级实验室
    public static Block specialLaboratory; //特级实验室
    //芯片
    public static Block chipManufacturingMachine; //芯片制造机
    public static Block chipPrinter; //芯片打印机
    //发电
    public static Block nuclearReactor; //核反应堆
    //物流
    public static Block simpleStorage; //简易储存器
    public static Block highSpeedUnloader; //高速装卸器

    public static void load() {
        machineCannon = new ItemTurret("machineCannon") {{
            requirements(
                    Category.turret, with(
                            Items.copper, 200,
                            Items.lead, 160,
                            Items.graphite, 80
                    )
            );
            ammo(Items.copper, new BasicBulletType(8.0F, 27.0F) {{
                pierce = true;
                pierceCap = 1;
                knockback = 0.2F;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 10.0F;
            }}, Items.lead, new BasicBulletType(8.0F, 27.0F) {{
                pierce = true;
                pierceCap = 2;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 10.0F;
                shootEffect = Fx.shootSmall;
                knockback = 0.2F;
            }}, Items.graphite, new BasicBulletType(8.0F, 35.0F) {{
                pierce = true;
                pierceCap = 4;
                width = 2.0F;
                height = 5.0F;
                reloadMultiplier = 0.7F;
                ammoMultiplier = 10.0F;
                lifetime = 30.0F;
                shootEffect = Fx.shootSmall;
                status = unmoving;
                statusDuration = 2F;
                knockback = 0.2F;
            }}, Items.metaglass, new BasicBulletType(8.0F, 35.0F) {{
                pierce = true;
                pierceCap = 3;
                width = 2.0F;
                height = 5.0F;
                reloadMultiplier = 0.8F;
                ammoMultiplier = 7.0F;
                lifetime = 30.0F;
                shootEffect = Fx.shootSmall;
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
                pierce = true;
                pierceCap = 1;
                reloadMultiplier = 0.25F;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 25.0F;
                shootEffect = Fx.shootSmall;
                knockback = 0.5F;
                lightningDamage = 5F;
                lightning = 2;
                lightningLength = 4;
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
                    fragBullet = new BasicBulletType(2F, 20.0F) {{
                        width = 0.6F;
                        height = 0.6F;
                        fragBullets = 1;
                        fragLifeMin = 0.1F;
                        fragRandomSpread = 30F;
                        fragSpread = 45F;
                        fragVelocityMin = 0.5F;
                        fragVelocityMax = 1F;
                        fragBullet = new BasicBulletType(2F, 15.0F) {{
                            width = 0.2F;
                            height = 0.2F;
                        }};
                    }};
                }};
            }},Items.silicon, new BasicBulletType(8.0F, 27.0F) {{
                pierce = true;
                pierceCap = 2;
                width = 2.0F;
                height = 5.0F;
                lifetime = 30.0F;
                ammoMultiplier = 10.0F;
                shootEffect = Fx.shootSmall;
                knockback = 0.2F;
                homingPower = 0.1f;
                homingRange = 26f;
            }});
            maxAmmo = 300;
            recoil = 0.7F;
            recoilTime = 2F;
            shootY = 3.0F;
            reload = 1.0F;
            range = 240.0F;
            ammoUseEffect = Fx.casing2;
            health = 2560;
            inaccuracy = 3.0F;
            rotateSpeed = 40.0F;
            coolantMultiplier = 2F;
            heatColor = Color.valueOf("ff0000");
            alwaysUnlocked = false;
            coolant = consume(new ConsumeLiquid(Liquids.water, 6f / 60f));
        }};

        highEfficiencyDisassembler = new Separator("highEfficiencyDisassembler"){{
            requirements(
                    Category.crafting, with(
                            Items.copper, 450,
                            Items.titanium, 200,
                            Items.lead, 300,
                            Items.graphite, 200,
                            Items.thorium, 150,
                            Items.silicon, 200,
                            Items.plastanium, 200,
                            Items.phaseFabric, 80,
                            TEItems.advancedChip, 20,
                            TEItems.specialProductionAgreement, 1
                    ));
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
            consumePower(8f);
            consumeLiquid(Liquids.slag, 20f / 60f);
            consumeItem(Items.scrap, 1);
            alwaysUnlocked = false;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(),
                    new DrawRegion("-spinner", 7, true),
                    new DrawDefault()
            );
        }};

        surpluoIcon = new AirBlock("surpluoIcon") {{
            size = 2;
            requirements(Category.effect, BuildVisibility.shown, with());
            alwaysUnlocked = true;
        }};

        erekirIcon = new AirBlock("erekirIcon") {{
            size = 2;
            requirements(Category.effect, BuildVisibility.shown, with());
            alwaysUnlocked = true;
        }};

        kepplerIcon = new AirBlock("kepplerIcon") {{
            size = 2;
            requirements(Category.effect, BuildVisibility.shown, with());
            alwaysUnlocked = true;
        }};

        oreUranium = new OreBlock("oreUranium", TEItems.uranium) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        primaryLaboratory = new MultiCrafter("primaryLaboratory") {{
            requirements(
                    Category.crafting, with(
                            Items.copper, 1000,
                            Items.titanium, 400,
                            Items.lead, 1200,
                            Items.graphite, 800,
                            Items.thorium, 650,
                            Items.silicon, 1000,
                            Items.plastanium, 800,
                            Items.phaseFabric, 300
                    )
            );

            recipes = Seq.with(//ds给的代码，能跑就行
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("primaryWarAgreement/1"));
                        }});
                        put("craftTime", 10800f);
                        put("icon", "primaryWarAgreement");
                    }},
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("primaryWarAgreement/1"));
                        }});
                        put("craftTime", 10800f);
                        put("icon", "primaryWarAgreement");
                    }}
            );

            size = 3;
            health = 1000;
            itemCapacity = 10;
            consumePower(40f);
            alwaysUnlocked = false;
            hasItems = true;
            isOutputItem = true;
            isConsumePower = true;
            hasPower = true;
        }};

        advancedLaboratory = new MultiCrafter("advancedLaboratory") {{
            requirements(
                    Category.crafting, with(
                            Items.copper, 2000,
                            Items.titanium, 800,
                            Items.lead, 2400,
                            Items.graphite, 1600,
                            Items.thorium, 1300,
                            Items.silicon, 6000,
                            Items.plastanium, 3000,
                            Items.phaseFabric, 1000,
                            TEItems.uranium, 400
                    )
            );

            recipes = Seq.with(//ds给的代码，能跑就行
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("advancedWarAgreement/1"));
                        }});
                        put("craftTime", 10800f);
                        put("icon", "advancedWarAgreement");
                    }},
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("advancedProductionAgreement/1"));
                        }});
                        put("craftTime", 10800f);
                        put("icon", "advancedProductionAgreement");
                    }},
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("highSpeedTransmissionProtocol/1"));
                        }});
                        put("craftTime", 10800f);
                        put("icon", "highSpeedTransmissionProtocol");
                    }}
            );

            size = 5;
            health = 2000;
            itemCapacity = 10;
            consumePower(50f);
            alwaysUnlocked = false;
            hasItems = true;
            isOutputItem = true;
            isConsumePower = true;
            hasPower = true;
        }};

        specialLaboratory = new MultiCrafter("specialLaboratory") {{
            requirements(
                    Category.crafting, with(
                            Items.copper, 1000,
                            Items.titanium, 400,
                            Items.lead, 1200,
                            Items.graphite, 800,
                            Items.thorium, 650,
                            Items.silicon, 1000,
                            Items.plastanium, 800,
                            Items.phaseFabric, 300,
                            TEItems.uranium, 2000
                    )
            );

            recipes = Seq.with(//ds给的代码，能跑就行
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("specialWarAgreement/1"));
                        }});
                        put("craftTime", 7200f);
                        put("icon", "specialWarAgreement");
                    }},
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("specialProductionAgreement/1"));
                        }});
                        put("craftTime", 7200f);
                        put("icon", "specialProductionAgreement");
                    }},
                    new ObjectMap(){{
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("ultraRemoteTransmissionProtocol/1"));
                        }});
                        put("craftTime", 7200f);
                        put("icon", "ultraRemoteTransmissionProtocol");
                    }}
            );

            size = 7;
            health = 4000;
            itemCapacity = 20;
            consumePower(60f);
            consumeLiquid(Liquids.cryofluid, 10f / 60f);
            alwaysUnlocked = false;
            hasLiquids = true;
            hasItems = true;
            isOutputItem = true;
            isConsumeFluid = true;
            isConsumePower = true;
            hasPower = true;
        }};

        chipManufacturingMachine = new MultiCrafter("chipManufacturingMachine") {{
            requirements(
                    Category.crafting, with(
                            Items.copper, 700,
                            Items.titanium, 700,
                            Items.lead, 800,
                            Items.graphite, 800,
                            Items.silicon, 8000
                    ));
            size = 3;
            health = 800;
            itemCapacity = 30;
            consumePower(5f);
            alwaysUnlocked = false;
            hasItems = true;
            isOutputItem = true;
            isConsumeFluid = true;
            isConsumePower = true;
            isConsumeItem = true;
            hasPower = true;

            recipes = Seq.with(//ds给的代码，能跑就行
                    new ObjectMap(){{
                        put("input", new ObjectMap(){{
                            put("items", Seq.with("silicon/2", "lead/1", "copper/1"));
                        }});
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("primaryChip/1"));
                        }});
                        put("craftTime", 120f);
                        put("icon", "primaryChip");
                    }},
                    new ObjectMap(){{
                        put("input", new ObjectMap(){{
                            put("items", Seq.with("silicon/4", "lead/2", "copper/1"));
                        }});
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("advancedChip/1"));
                        }});
                        put("craftTime", 120f);
                        put("icon", "advancedChip");
                    }}
            );
        }};

        chipPrinter = new MultiCrafter("chipPrinter") {{
            requirements(
                    Category.crafting, with(
                            Items.copper, 2000,
                            Items.titanium, 1000,
                            Items.lead, 2400,
                            Items.graphite, 1500,
                            Items.silicon, 10000,
                            Items.plastanium, 2000,
                            Items.phaseFabric, 800,
                            TEItems.advancedChip, 70,
                            TEItems.advancedProductionAgreement, 1
                    ));

            size = 5;
            health = 1000;
            itemCapacity = 40;
            consumePower(6f);
            alwaysUnlocked = false;

            recipes = Seq.with(//ds给的代码，能跑就行
                    new ObjectMap(){{
                        put("input", new ObjectMap(){{
                            put("items", Seq.with("silicon/3"));
                        }});
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("primaryChip/1"));
                        }});
                        put("craftTime", 12f);
                        put("icon", "primaryChip");
                    }},
                    new ObjectMap(){{
                        put("input", new ObjectMap(){{
                            put("items", Seq.with("silicon/4"));
                        }});
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("advancedChip/1"));
                        }});
                        put("craftTime", 12f);
                        put("icon", "advancedChip");
                    }},
                    new ObjectMap(){{
                        put("input", new ObjectMap(){{
                            put("items", Seq.with("silicon/5"));
                        }});
                        put("output", new ObjectMap(){{
                            put("items", Seq.with("specialChip/1"));
                        }});
                        put("craftTime", 12f);
                        put("icon", "specialChip");
                    }}
            );

            drawer = new DrawMulti() {{
                new DrawLiquidRegion() {{
                    drawLiquid = Liquids.cryofluid;
                    suffix = "-cryo";
                }};

                new RegionPart() {{//临时的物品绘制
                    x = y = 0;
                    suffix = "-chipBuild";
                }};
            }};
            isConsumeItem = true;
            liquidCapacity = 20f;
            isOutputItem = true;
            isConsumeFluid = true;
            isConsumePower = true;
            hasLiquids = true;
            hasItems = true;
            hasPower = true;
            consumeLiquid(Liquids.cryofluid, 3f / 60f);
        }};

        portableMissileLaunchSilo = new PowerTurret("portableMissileLaunchSilo") {{
            alwaysUnlocked = false;
            health = 500;
            size = 2;

            requirements(
                    Category.turret, with(
                            Items.copper, 450,
                            Items.lead, 600,
                            Items.graphite, 180,
                            Items.blastCompound, 10,
                            Items.silicon, 80,
                            TEItems.primaryChip, 5
                    )
            );

            rotateSpeed = 0F;
            range = 700f;
            shootCone = 360;
            shootSound = Sounds.missileLaunch;
            recoil = 0f;
            shootY = 0;
            minWarmup = 0.8f;
            shootWarmupSpeed = 0.055f;
            warmupMaintainTime = 120;

            drawer = new DrawTurret() {{
                new RegionPart() {{
                    suffix = "-top";
                    x = y = 0;
                    moveX = 0;
                    moveY = -13;
                }};
            }};

            shootType = new BasicBulletType(0f, 1f) {{
                killShooter = true;
                spawnUnit = new MissileUnitType("portableMissileLaunchSiloMissile") {{
                    speed = 7f;
                    lifetime = 5f * 60f;
                    trailLength = 14;
                    homingPower = 0.1f;
                    homingDelay = 50f;
                    missileAccelTime = 120f;
                    health = 200f;
                    rotateSpeed = 10f;
                    weapons.add(new Weapon() {{
                        shootCone = 360f;
                        mirror = false;
                        reload = 1f;
                        x = y = 0;
                        deathExplosionEffect = Fx.massiveExplosion;
                        shootOnDeath = true;
                        shake = 10f;
                        bullet = new ExplosionBulletType(1638f, 157f) {{
                            hitColor = Pal.redLight;
                            shootEffect = new MultiEffect(Fx.massiveExplosion, new WaveEffect(){{
                                lifetime = 10f;
                                strokeFrom = 4f;
                                sizeTo = 130f;
                            }});
                            buildingDamageMultiplier = 0.8f;
                            ammoMultiplier = 1f;
                        }};
                    }});
                }};
            }};
        }};

        missileLauncher = new ItemTurret("missileLauncher") {{
            health = 3500;
            size = 5;
            alwaysUnlocked = false;

            requirements(
                    Category.turret, with(
                            Items.copper, 900,
                            Items.lead, 1100,
                            Items.graphite, 700,
                            Items.metaglass, 300,
                            Items.silicon, 250,
                            TEItems.primaryChip, 20
                    )
            );

            rotateSpeed = 0F;
            range = 1000f;
            reload = 65f;
            liquidCapacity = 20f;
            coolantMultiplier = 0.5f;
            coolant = consume(new ConsumeLiquid(Liquids.water, 26f / 60f));
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
                spawnUnit = new MissileUnitType("missileLauncherMissile") {{
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
                spawnUnit = new MissileUnitType("missileLauncherMissile1") {{
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
                spawnUnit = new MissileUnitType("missileLauncherMissile2") {{
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

        nuclearReactor = new NuclearReactor("nuclearReactor") {{
            health = 5000;
            size = 5;
            liquidCapacity = 30;
            itemCapacity = 20;
            hasItems = true;
            hasLiquids = true;
            outputsPower = true;
            powerProduction = 230f;
            itemDuration = 30f;
            lightColor = Color.valueOf("ffffff");
            explosionShake = 9;
            explosionShakeDuration = 120;
            explosionRadius = 80;
            explosionDamage = 10000;
            explodeSound = Sounds.explosionbig;
            fuelItem = TEItems.nuclearFuelRod;
            heating = 0.1f;
            coolantPower = 2;
            consumeItem(TEItems.nuclearFuelRod, (int) 1);
            consumeLiquid(Liquids.cryofluid, 0.5f / 60f);
            buildCostMultiplier = 3;
            requirements(
                    Category.power, with(
                    Items.lead, 1400,
                    Items.graphite, 890,
                    Items.metaglass, 320,
                    Items.thorium, 750,
                    Items.titanium, 800,
                    Items.surgeAlloy, 260,
                    TEItems.advancedChip, 10
            ));
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

        simpleStorage = new StorageBlock("simpleStorage") {{
            requirements(
                    Category.effect, with(
                            Items.copper, 200,
                            Items.lead, 260,
                            Items.graphite, 80
                    )
            );
            size = 2;
            itemCapacity = 200;
            scaledHealth = 55;
            coreMerge = false;
        }};

        highSpeedUnloader = new Unloader("highSpeedUnloader") {{
            speed = 1f;
            group = BlockGroup.transportation;
            requirements(
                    Category.effect, with(
                            Items.thorium, 20,
                            Items.silicon, 45,
                            Items.titanium, 50,
                            Items.plastanium, 30
                    )
            );
        }};

        nuclearFuelRodManufacturingMachine = new GenericCrafter("nuclearFuelRodManufacturingMachine") {{
            size = 3;
            health = 1600;
            itemCapacity = 10;
            liquidCapacity = 20;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            craftTime = 30f;
            updateEffect = none;
            consumePower(35f / 60f);
            consumeItem(TEItems.uranium, 5);
            consumeLiquid(Liquids.water, 10f / 60f);
            outputItem = new ItemStack(TEItems.nuclearFuelRod, 1);
            requirements(Category.crafting, with(
                    Items.thorium, 530,
                    Items.titanium, 680,
                    Items.silicon, 580,
                    Items.lead, 680,
                    Items.surgeAlloy, 200,
                    Items.graphite, 790,
                    TEItems.advancedChip, 20
            ));
        }};
    }
}
