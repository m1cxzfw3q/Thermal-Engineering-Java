package TEMod;

import TEMod.content.*;
import TEMod.content.Kepler.*;

import arc.util.Log;
import mindustry.mod.Mod;

public class TECore extends Mod {
    public TECore() {}
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        KeplerPlanet.load();
        KeplerSectorPresets.load();
        TEStatusEffects.load();
        //TEUnitTypes.load();
        TEFix.load();
        TEV8.load();

        isComplete(TECore.class);
        TETechTree.load();
    }

    @Override
    public void init() {
    }

    public static void isComplete(Object obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
