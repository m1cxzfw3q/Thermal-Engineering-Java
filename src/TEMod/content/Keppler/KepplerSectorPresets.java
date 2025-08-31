package TEMod.content.Keppler;

import mindustry.type.SectorPreset;

import static TEMod.TECore.isComplete;
import static TEMod.content.Keppler.KepplerPlanet.*;

public class KepplerSectorPresets {
    public static SectorPreset LandingArea;//降落区
    public static SectorPreset ResearchAreaNo47;//47号研究区

    public static void load(){
        LandingArea = new SectorPreset("landing-area", keppler, 53) {{
            alwaysUnlocked = false;
            addStartingItems = true;;
            difficulty = 3;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 4f;
            rules = rules1 -> {
                rules1.attackMode = true;
            };
        }};

        ResearchAreaNo47 = new SectorPreset("research-area-no-47", keppler, 472) {{
            alwaysUnlocked = false;
            addStartingItems = true;
            captureWave = 81;
            difficulty = 4;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 4f;
        }};

        isComplete(KepplerSectorPresets.class);
    }
}
