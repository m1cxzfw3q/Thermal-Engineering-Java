package TEMod.content;

import arc.graphics.Color;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.FlakBulletType;
import mindustry.type.Category;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class TEFix {
    public static void load() {
        Items.graphite.hardness = 2;

        UnitTypes.alpha.buildSpeed = 1;
        UnitTypes.alpha.speed = 4;
        UnitTypes.alpha.weapons.get(0).reload = 10;

        UnitTypes.beta.buildSpeed = 1.5f;
        UnitTypes.beta.speed = 4.5f;
        UnitTypes.beta.weapons.get(0).reload = 15;

        UnitTypes.gamma.buildSpeed = 2;
        UnitTypes.gamma.speed = 4.66f;
        UnitTypes.gamma.weapons.get(0).reload = 10;

        Blocks.sporeWall.attributes.set(TEAttribute.sporeWalls, 1);
        Blocks.sporePine.attributes.set(TEAttribute.sporeWalls, 1);
        Blocks.whiteTree.attributes.set(TEAttribute.sporeWalls, 1);

        Blocks.oxidationChamber.canOverdrive = true;

        //666石头科技
        ((ItemTurret) Blocks.duo).ammoTypes.putAll(
                TEItems.stone, new BasicBulletType(2.6f, 15) {{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    reloadMultiplier = 0.7f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = frontColor = TEItems.stone.color;
                }},
                TEItems.iron, new BasicBulletType(3.1f, 22) {{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    reloadMultiplier = 1.1f;
                    pierceCap = 1;
                    rangeChange = 16f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = frontColor = TEItems.iron.color;
                }}
        );
        ((ItemTurret) Blocks.scatter).ammoTypes.put(
                TEItems.stone, new FlakBulletType(4f, 5) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    reloadMultiplier = 0.7f;
                    shootEffect = Fx.shootSmall;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Color.valueOf("94949a");
                    frontColor = Color.valueOf("94949a");
                    collidesGround = true;

                    fragBullets = 6;
                    fragBullet = new BasicBulletType(3f, 5) {{
                        width = 5f;
                        height = 12f;
                        shrinkY = 1f;
                        lifetime = 20f;
                        backColor = trailColor = Color.valueOf("94949a");
                        hitColor = frontColor = Color.valueOf("94949a");
                        despawnEffect = Fx.none;
                    }};
                }}
        );
        ((ItemTurret) Blocks.salvo).ammoTypes.putAll(
                TEItems.stone, new BasicBulletType(3f, 15) {{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    reloadMultiplier = 0.7f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Color.valueOf("94949a");
                    frontColor = Color.valueOf("94949a");
                }},
                TEItems.iron, new BasicBulletType(3.2f, 22) {{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    reloadMultiplier = 1.1f;
                    pierceCap = 1;
                    rangeChange = 10f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = frontColor = TEItems.iron.color;
                }}
        );
        ((ItemTurret) Blocks.spectre).ammoTypes.put(
                TEItems.stone, new BasicBulletType(7.5f, 30){{
                    hitSize = 4.8f;
                    width = 15f;
                    height = 21f;
                    shootEffect = Fx.shootBig;
                    reloadMultiplier = 1.2f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Color.valueOf("94949a");
                    frontColor = Color.valueOf("94949a");
                    fragBullets = 3;
                    fragBullet = new BasicBulletType(3f, 20f) {{
                        width = 5f;
                        height = 12f;
                        lifetime = 20f;
                        backColor = trailColor = Color.valueOf("94949a");
                        hitColor = frontColor = Color.valueOf("94949a");
                        despawnEffect = Fx.none;
                    }};
                }}
        );

        Blocks.stone.itemDrop = Blocks.craters.itemDrop = Blocks.charr.itemDrop =
                Blocks.dacite.itemDrop = Blocks.basalt.itemDrop = TEItems.stone;

        Blocks.stone.playerUnmineable = Blocks.dacite.playerUnmineable = Blocks.craters.playerUnmineable =
                Blocks.basalt.playerUnmineable = Blocks.charr.playerUnmineable = true;

        Blocks.boulder.instantDeconstruct = Blocks.snowBoulder.instantDeconstruct = Blocks.sandBoulder.instantDeconstruct =
                Blocks.daciteBoulder.instantDeconstruct = Blocks.basaltBoulder.instantDeconstruct =
                        Blocks.ferricBoulder.instantDeconstruct = Blocks.shaleBoulder.instantDeconstruct = false;

        Blocks.boulder.buildTime = Blocks.snowBoulder.buildTime = Blocks.sandBoulder.buildTime = Blocks.daciteBoulder.buildTime =
                Blocks.basaltBoulder.buildTime = Blocks.ferricBoulder.buildTime = Blocks.shaleBoulder.buildTime = 0f;

        Blocks.boulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 2));
        Blocks.snowBoulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 2));
        Blocks.sandBoulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 1, Items.sand ,1));
        Blocks.daciteBoulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 2));
        Blocks.basaltBoulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 2));
        Blocks.ferricBoulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 2));
        Blocks.shaleBoulder.requirements(Category.distribution, BuildVisibility.hidden, with(TEItems.stone, 2));
    }
}
