package TEMod;

import TEMod.content.*;
import TEMod.content.Kepler.*;
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
        Log.info("[Thermal-Engineering] isV8()");
        TEFix.load();
        TETechTree.load();//TechTree
        isComplete(TECore.class);
    }

    @Override
    public void init() {
        //SmartTechTree.use();
    }

    public static void isComplete(Object obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
