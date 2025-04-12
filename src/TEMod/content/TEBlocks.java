package TEMod.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.environment.AirBlock;
import mindustry.world.blocks.production.Separator;
import mindustry.world.draw.*;
import mindustry.world.meta.BuildVisibility;

import static mindustry.content.StatusEffects.shocked;
import static mindustry.content.StatusEffects.unmoving;
import static mindustry.type.ItemStack.with;

public class TEBlocks {
    public static Block machineCannon; //机炮
    public static Block highEfficiencyDisassembler; //高效解离机
    public static Block surpluoIcon;
    public static Block erekirIcon;
    public static Block kepplerIcon;

    public static void load() {
        machineCannon = new ItemTurret("machineCannon") {{
            requirements(
                    Category.turret,
                    ItemStack.with(
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
                fragBullet = new BasicBulletType(1F, 6.0F) {{
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
                fragBullet = new BasicBulletType(1F, 40.0F) {{
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
                    fragBullet = new BasicBulletType(1F, 20.0F) {{
                        width = 0.6F;
                        height = 0.6F;
                        fragBullets = 1;
                        fragLifeMin = 0.1F;
                        fragRandomSpread = 30F;
                        fragSpread = 45F;
                        fragVelocityMin = 0.5F;
                        fragVelocityMax = 1F;
                        fragBullet = new BasicBulletType(1F, 15.0F) {{
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
                homingPower = 0.3f;
                homingRange = 37f;
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
            coolant = this.consumeCoolant(0.3F);
            heatColor = Color.valueOf("ff0000");
            limitRange();
            alwaysUnlocked = false;
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
                            Items.phaseFabric, 80
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
            requirements(Category.effect, BuildVisibility.hidden, with());
            alwaysUnlocked = true;
        }};
        erekirIcon = new AirBlock("erekirIcon") {{
            size = 2;
            requirements(Category.effect, BuildVisibility.hidden, with());
            alwaysUnlocked = true;
        }};
        kepplerIcon = new AirBlock("kepplerIcon") {{
            size = 2;
            requirements(Category.effect, BuildVisibility.hidden, with());
            alwaysUnlocked = true;
        }};
    }
}
