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
import mindustry.world.blocks.production.Separator;
import mindustry.world.draw.*;

import static mindustry.content.StatusEffects.shocked;
import static mindustry.content.StatusEffects.unmoving;
import static mindustry.type.ItemStack.with;

public class TEBlocks {
    public static Block machineCannon; //机炮
    public static Block highEfficiencyDisassembler; //高效解离机

    public static void load() {
        machineCannon = new ItemTurret("machineCannon") {
            {
                this.requirements(Category.turret, ItemStack.with(Items.copper, 200, Items.lead, 160, Items.graphite, 80));
                this.ammo(Items.copper, new BasicBulletType(8.0F, 27.0F) {
                    {
                        pierce = true;
                        this.pierceCap = 1;
                        this.knockback = 0.2F;
                        this.width = 2.0F;
                        this.height = 5.0F;
                        this.lifetime = 30.0F;
                        this.ammoMultiplier = 10.0F;
                    }
                }, Items.lead, new BasicBulletType(8.0F, 27.0F) {
                    {
                        pierce = true;
                        this.pierceCap = 2;
                        this.width = 2.0F;
                        this.height = 5.0F;
                        this.lifetime = 30.0F;
                        this.ammoMultiplier = 10.0F;
                        this.shootEffect = Fx.shootSmall;
                        this.knockback = 0.2F;
                    }
                }, Items.graphite, new BasicBulletType(8.0F, 35.0F) {
                    {
                        pierce = true;
                        this.pierceCap = 4;
                        this.width = 2.0F;
                        this.height = 5.0F;
                        this.reloadMultiplier = 0.7F;
                        this.ammoMultiplier = 10.0F;
                        this.lifetime = 30.0F;
                        this.shootEffect = Fx.shootSmall;
                        this.status = unmoving;
                        this.statusDuration = 2F;
                        this.knockback = 0.2F;
                    }
                }, Items.metaglass, new BasicBulletType(8.0F, 35.0F) {
                    {
                        pierce = true;
                        this.pierceCap = 3;
                        this.width = 2.0F;
                        this.height = 5.0F;
                        this.reloadMultiplier = 0.8F;
                        this.ammoMultiplier = 7.0F;
                        this.lifetime = 30.0F;
                        this.shootEffect = Fx.shootSmall;
                        this.status = unmoving;
                        this.statusDuration = 2F;
                        this.fragBullets = 2;
                        this.fragLifeMin = 0.1F;
                        this.fragRandomSpread = 30F;
                        this.fragSpread = 45F;
                        this.fragVelocityMin = 0.5F;
                        this.fragVelocityMax = 1F;
                        this.knockback = 0.4F;
                        this.fragBullet = new BasicBulletType(0.2F, 6.0F) {
                            {
                                pierce = true;
                                this.width = 1.0F;
                                this.height = 1.0F;
                                this.pierceCap = 2;
                            }
                        };
                    }
                }, Items.surgeAlloy, new BasicBulletType(8.0F, 200.0F) {
                    {
                        pierce = true;
                        this.pierceCap = 1;
                        this.reloadMultiplier = 0.25F;
                        this.width = 2.0F;
                        this.height = 5.0F;
                        this.lifetime = 30.0F;
                        this.ammoMultiplier = 25.0F;
                        this.shootEffect = Fx.shootSmall;
                        this.knockback = 0.5F;
                        this.lightningDamage = 5F;
                        this.lightning = 2;
                        this.lightningLength = 4;
                        this.lightningColor = Color.valueOf("ab99d3ff");
                        this.status = shocked;
                        this.fragBullets = 2;
                        this.fragLifeMin = 0.1F;
                        this.fragRandomSpread = 30F;
                        this.fragSpread = 45F;
                        this.fragVelocityMin = 0.5F;
                        this.fragVelocityMax = 1F;
                        this.fragBullet = new BasicBulletType(0.2F, 40.0F) {
                            {
                                pierce = true;
                                this.width = 1.0F;
                                this.height = 1.0F;
                                this.pierceCap = 1;
                                this.fragBullets = 1;
                                this.fragLifeMin = 0.1F;
                                this.fragRandomSpread = 30F;
                                this.fragSpread = 45F;
                                this.fragVelocityMin = 0.5F;
                                this.fragVelocityMax = 1F;
                                this.fragBullet = new BasicBulletType(0.2F, 20.0F) {
                                    {
                                        this.width = 0.6F;
                                        this.height = 0.6F;
                                        this.fragBullets = 1;
                                        this.fragLifeMin = 0.1F;
                                        this.fragRandomSpread = 30F;
                                        this.fragSpread = 45F;
                                        this.fragVelocityMin = 0.5F;
                                        this.fragVelocityMax = 1F;
                                        this.fragBullet = new BasicBulletType(0.2F, 15.0F) {
                                            {
                                                this.width = 0.2F;
                                                this.height = 0.2F;
                                            }
                                        };
                                    }
                                };
                            }
                        };
                    }
                });
                this.maxAmmo = 300;
                this.recoil = 0.7F;
                this.recoilTime = 2F;
                this.shootY = 3.0F;
                this.reload = 1.0F;
                this.range = 240.0F;
                this.ammoUseEffect = Fx.casing2;
                this.health = 2560;
                this.inaccuracy = 3.0F;
                this.rotateSpeed = 40.0F;
                this.coolantMultiplier = 2F;
                this.coolant = this.consumeCoolant(0.3F);
                this.heatColor = Color.valueOf("ff0000");
                this.limitRange();
            }
        };

        highEfficiencyDisassembler = new Separator("highEfficiencyDisassembler"){{
            requirements(Category.crafting, with(Items.copper, 450, Items.titanium, 200, Items.lead, 300, Items.graphite, 200, Items.thorium, 150, Items.silicon, 200, Items.plastanium, 200, Items.phaseFabric, 80));
            results = with(
                    Items.copper, 3,
                    Items.lead, 3,
                    Items.graphite, 1,
                    Items.titanium, 2,
                    Items.thorium, 1,
                    Items.silicon, 3,
                    Items.metaglass, 2,
                    Items.surgeAlloy, 1,
                    Items.phaseFabric, 1
            );
            hasPower = true;
            craftTime = 5f;
            size = 4;
            health = 2500;
            consumePower(3f);
            consumeLiquid(Liquids.slag, 10f / 60f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawRegion("-spinner", 7, true), new DrawDefault());
        }};
    }
}
