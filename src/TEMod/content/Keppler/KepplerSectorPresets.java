package TEMod.content.Keppler;

import mindustry.type.SectorPreset;

import static TEMod.content.Keppler.KepplerPlanet.*;

public class KepplerSectorPresets {
    public static SectorPreset Landing_area;

    public static void load(){
        Landing_area = new SectorPreset("landingArea", keppler, 53) {{
            alwaysUnlocked = true;
            addStartingItems = true;
            captureWave = 60;
            difficulty = 1;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 4f;
        }};
    }
}
