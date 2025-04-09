package TEMod.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.draw.DrawTurret;

import static mindustry.content.StatusEffects.shocked;
import static mindustry.content.StatusEffects.unmoving;

public class Blocks {
    public static Block machine_Cannon;

    public static void load() {
        machine_Cannon = new ItemTurret("Machine Cannon") {
            {
                this.requirements(Category.turret, ItemStack.with(Items.copper, 200, Items.lead, 160, Items.graphite, 80));
                this.ammo(Items.copper, new BasicBulletType(8.0F, 27.0F) {
                    {
                        this.pierceCap = 1;
                        this.knockback = 0.2F;
                        this.width = 2.0F;
                        this.height = 5.0F;
                        this.lifetime = 30.0F;
                        this.ammoMultiplier = 10.0F;
                    }
                }, Items.lead, new BasicBulletType(8.0F, 27.0F) {
                    {
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
                                this.width = 1.0F;
                                this.height = 1.0F;
                                this.pierceCap = 2;
                            }
                        };
                    }
                }, Items.surgeAlloy, new BasicBulletType(8.0F, 200.0F) {
                    {
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
                this.drawer = new DrawTurret() {
                    {
                        for(int i = 0; i < 2; ++i) {
                            int finalI = i;
                            this.parts.add(new RegionPart("-barrel-" + (finalI == 0 ? "l" : "r")) {
                                {
                                    this.progress = PartProgress.recoil;
                                    this.recoilIndex = finalI;
                                    this.under = true;
                                    this.moveY = -1.5F;
                                }
                            });
                        }

                    }
                };
                this.maxAmmo = 300;
                this.recoil = 0.3F;
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
    }
}
