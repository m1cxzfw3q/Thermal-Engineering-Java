package TEMod;

import TEMod.content.*;
import TEMod.content.Keppler.*;
import arc.util.Log;
import mindustry.mod.Mod;

import static arc.Core.settings;
import static mindustry.Vars.appName;
import static mindustry.Vars.minJavaModGameVersion;

public class ThermalEngineeringCore extends Mod {
    public final String TEVersion = "0.0.2";
    public static final String TEName = "Thermal-Enginerring";

    public ThermalEngineeringCore() {

    }
    @Override
    public void loadContent() {
        settings.setAppName(appName + " " + TEName + "(" + TEVersion + ")");
        TEItems.load();
        TEBlocks.load();
        KepplerPlanet.load();
        KepplerSectorPresets.load();
        TEStatusEffects.load();
        //TEUnitTypes.load();
        //TechTree
        if (minJavaModGameVersion == 147) {
            TEV8Compatible.load();
            Log.info("[Thermal-Engineering] isV8()");
        }
        KepplerTechTree.load();
        isComplete(ThermalEngineeringCore.class);
    }

    public static void isComplete(Object obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
