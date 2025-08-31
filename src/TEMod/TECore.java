package TEMod;

import TEMod.content.*;
import TEMod.content.Keppler.*;
import arc.util.Log;
import mindustry.mod.Mod;

public class TECore extends Mod {

    public TECore() {

    }
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        KepplerPlanet.load();
        KepplerSectorPresets.load();
        TEStatusEffects.load();
        //TEUnitTypes.load();
        TEV8Compatible.load();
        Log.info("[Thermal-Engineering] isV8()");
        TETechTree.load();//TechTree
        isComplete(TECore.class);
    }

    public static void isComplete(Object obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
