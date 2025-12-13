package TEMod.content;

import TEMod.content.Kepler.KeplerPlanet;
import mindustry.Vars;
import mindustry.content.Planets;
import mindustry.world.meta.BuildVisibility;

public class TEBuildVisibility {
    public static final BuildVisibility
            keplerOnly = new BuildVisibility(() -> Vars.state.rules.planet == KeplerPlanet.kepler),
            serpuloOnly = new BuildVisibility(() -> Vars.state.rules.planet == Planets.serpulo),
            erekirOnly = new BuildVisibility(() -> Vars.state.rules.planet == Planets.erekir);
}
