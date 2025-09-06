package TEMod.content;

import arc.graphics.Color;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.FlakBulletType;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;

public class TEFix {
    public static void load() {
        Items.graphite.hardness = 2;

        //这里是给孢子墙壁粉碎机预留的
        Blocks.sporeWall.attributes.set(Attribute.spores, 1f);
        Blocks.whiteTree.attributes.set(Attribute.spores, 1f);
        Blocks.sporePine.attributes.set(Attribute.spores, 1f);

        //666石头科技
        Blocks.boulder.requirements(Category.distribution, BuildVisibility.hidden, ItemStack.with(TEItems.stone, 2));
        Blocks.sandBoulder.requirements(Category.distribution, BuildVisibility.hidden, ItemStack.with(TEItems.stone, 1, Items.sand, 1));
        Blocks.daciteBoulder.requirements(Category.distribution, BuildVisibility.hidden, ItemStack.with(TEItems.stone, 2));
        Blocks.basaltBoulder.requirements(Category.distribution, BuildVisibility.hidden, ItemStack.with(TEItems.stone, 2));

        ItemTurret duo = (ItemTurret) Blocks.duo;
        ItemTurret scatter = (ItemTurret) Blocks.scatter;
        ItemTurret salvo = (ItemTurret) Blocks.salvo;
        duo.ammoTypes.put(
                TEItems.stone, new BasicBulletType(2.6f, 15){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    reloadMultiplier = 0.7f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Color.valueOf("94949a");
                    frontColor = Color.valueOf("94949a");
                }}
        );
        scatter.ammoTypes.put(
                TEItems.stone, new FlakBulletType(4f, 5){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    reloadMultiplier = 0.7f;
                    shootEffect = Fx.shootSmall;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Color.valueOf("94949a");
                    frontColor = Color.valueOf("94949a");
                }}
        );
        salvo.ammoTypes.put(
                TEItems.stone, new BasicBulletType(3f, 15){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    reloadMultiplier = 0.7f;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Color.valueOf("94949a");
                    frontColor = Color.valueOf("94949a");
                }}
        );

        Blocks.stone.itemDrop = TEItems.stone;
        Blocks.dacite.itemDrop = TEItems.stone;
        Blocks.basalt.itemDrop = TEItems.stone;
        Blocks.stone.playerUnmineable = true;
        Blocks.dacite.playerUnmineable = true;
        Blocks.basalt.playerUnmineable = true;
    }
}
