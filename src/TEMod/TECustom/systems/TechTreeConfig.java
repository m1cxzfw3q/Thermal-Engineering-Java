package TEMod.TECustom.systems;

import arc.files.Fi;
import arc.util.serialization.Jval;
import mindustry.Vars;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;

public class TechTreeConfig {
    private static Fi configFile;
    private static Jval config;

    public static void load() {
        configFile = Vars.mods.getMod("temod").root.child("techTree-config.json");

        if (configFile.exists()) {
            config = Jval.read(configFile.reader());
        } else {
            // 创建默认配置
            config = Jval.newObject();
            config.put("keplerItems", Jval.newArray());
            config.put("keplerLiquids", Jval.newArray());
            config.put("keplerBlocks", Jval.newArray());
            save();
        }
    }

    public static void save() {
        configFile.writeString(config.toString(Jval.Jformat.formatted));
    }

    public static boolean isKeplerItem(Item item) {
        Jval items = config.get("keplerItems");
        return items.asArray().contains(jval -> jval.asString().equals(item.name));
    }

    public static boolean isKeplerLiquid(Liquid liquid) {
        Jval liquids = config.get("keplerLiquids");
        return liquids.asArray().contains(jval -> jval.asString().equals(liquid.name));
    }

    public static boolean isKeplerBlock(Block block) {
        Jval blocks = config.get("keplerBlocks");
        return blocks.asArray().contains(jval -> jval.asString().equals(block.name));
    }
}