package TEMod;

import TEMod.content.*;
import TEMod.content.Kepler.*;
import TEMod.systems.TechTreeConfig;
import TEMod.systems.TechTreeExtension;
import TEMod.systems.TechTreeMonitor;

import arc.util.Log;
import mindustry.mod.Mod;

public class TECore extends Mod {
    public TECore() {

    }
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        KeplerPlanet.load();
        KeplerSectorPresets.load();
        TEStatusEffects.load();
        //TEUnitTypes.load();
        TEFix.load();

        TechTreeConfig.load();//TechTreeExt
        TechTreeExtension.updateAllBlocksVisibility();
        TechTreeExtension.updateAllItemsVisibility();
        TechTreeExtension.updateAllLiquidsVisibility();

        isComplete(TECore.class);
        TETechTree.load();
    }

    @Override
    public void init() {
        TechTreeExtension.init();
        TechTreeMonitor.init();
    }

    public static void isComplete(Object obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
