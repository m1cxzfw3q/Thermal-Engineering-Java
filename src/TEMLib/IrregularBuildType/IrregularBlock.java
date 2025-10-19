package TEMLib.IrregularBuildType;

import arc.math.geom.Vec2;
import mindustry.world.Block;
import org.jetbrains.annotations.NotNull;

///如果写成了 NH的不规则建筑就不再是独一家了()
public class IrregularBlock extends Block {
    /** 建筑的额外碰撞箱(神秘)  也可以变为主要碰撞箱(removeOriginHittable()) **/
    public Vec2 @NotNull [] blockHittable = {};

    public IrregularBlock(String name) {
        super(name);
        update = true;
        rotate = true;
    }

    /** 用于删除原碰撞箱 **/
    public static void removeOriginHittable() {

    }
}
