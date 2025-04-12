package TEMod.content;

import mindustry.type.SectorPreset;

import static TEMod.content.KepplerPlanet.*;

public class TESectorPresets {
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
