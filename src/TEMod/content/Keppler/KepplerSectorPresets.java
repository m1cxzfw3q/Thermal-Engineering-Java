package TEMod.content.Keppler;

import mindustry.type.SectorPreset;

import static TEMod.content.Keppler.KepplerPlanet.*;

public class KepplerSectorPresets {
    public static SectorPreset LandingArea;//降落区
    public static SectorPreset ResearchAreaNo47;//47号研究区

    public static void load(){
        LandingArea = new SectorPreset("LandingArea", keppler, 53) {{
            alwaysUnlocked = false;
            addStartingItems = false;
            captureWave = 60;
            difficulty = 2;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 4f;
        }};

        ResearchAreaNo47 = new SectorPreset("ResearchAreaNo47", keppler, 472) {{
            alwaysUnlocked = false;
            addStartingItems = false;
            captureWave = 80;
            difficulty = 3;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 4f;
        }};
    }
}
