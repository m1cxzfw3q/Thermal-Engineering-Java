package TEMod.content;

import mindustry.content.Liquids;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.world.Block;

import static TEMod.content.Kepler.KeplerPlanet.kepler;
import static mindustry.Vars.content;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.Planets.erekir;
import static mindustry.content.Planets.serpulo;
import static mindustry.content.UnitTypes.*;

public class TEV8 {
    public static void load() {
        UnitType[] serpuloUnits = {
                //ground units
                dagger, mace, fortress, scepter, reign,
                nova, pulsar, quasar, vela, corvus,
                crawler, atrax, spiroct, arkyid, toxopid,
                //air units
                flare, horizon, zenith, antumbra, eclipse,
                mono, poly, mega, quad, oct,
                //core unit
                alpha, beta, gamma
        };

        UnitType[] erekirUnits = {
                //tank units
                stell, locus, precept, vanquish, conquer,
                //crawl units
                merui, cleroi, anthicus, tecta, collaris,
                //flying units
                elude, avert, obviate, quell, disrupt,
                //core unit
                evoke, incite, emanate
        };

        for (Item it : serpuloItems) {
            it.shownPlanets.addAll(kepler, erekir);
        }

        for (Item it : erekirItems) {
            it.shownPlanets.addAll(kepler, serpulo);
        }

        for (UnitType it : serpuloUnits) {
            it.shownPlanets.addAll(kepler, erekir);
        }

        for (UnitType it : erekirUnits) {
            it.shownPlanets.addAll(kepler, serpulo);
        }

        oil.shownPlanets.addAll(kepler, erekir);
        Liquids.cryofluid.shownPlanets.addAll(kepler, erekir);

        neoplasm.shownPlanets.addAll(kepler, serpulo);
        arkycite.shownPlanets.addAll(kepler, serpulo);
        ozone.shownPlanets.addAll(kepler, serpulo);
        hydrogen.shownPlanets.addAll(kepler, serpulo);
        nitrogen.shownPlanets.addAll(kepler, serpulo);
        cyanogen.shownPlanets.addAll(kepler, serpulo);

        for (Block it : content.blocks()) {
            if (!it.shownPlanets.isEmpty()){
                if (it.shownPlanets.contains(serpulo)) {
                    it.shownPlanets.add(erekir);
                } else if (it.shownPlanets.contains(erekir)) {
                    it.shownPlanets.add(serpulo);
                }
                it.shownPlanets.add(kepler);
            }
        }
    }
}
