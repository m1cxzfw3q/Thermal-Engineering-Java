package TEMod.content;

import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.world.Block;

import static TEMod.content.Kepler.KeplerPlanet.kepler;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.Planets.erekir;
import static mindustry.content.Planets.serpulo;
import static mindustry.content.UnitTypes.*;

public class TEV8 {
    public static void load() {
        Item[] serpuloItems = {
                scrap, copper, lead, graphite, coal, titanium, thorium, silicon, plastanium,
                phaseFabric, surgeAlloy, sporePod, Items.sand, blastCompound, pyratite, metaglass
        };

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

        Block[] blocks = { //直接搬源代码的得了
                //crafting
                siliconSmelter, siliconCrucible, kiln, graphitePress, plastaniumCompressor, multiPress, phaseWeaver, surgeSmelter, pyratiteMixer, blastMixer, cryofluidMixer,
                melter, separator, disassembler, sporePress, pulverizer, incinerator, coalCentrifuge,

                //crafting - erekir
                siliconArcFurnace, electrolyzer, oxidationChamber, atmosphericConcentrator, electricHeater, slagHeater, phaseHeater, heatRedirector, smallHeatRedirector,
                heatRouter, slagIncinerator, carbideCrucible, surgeCrucible, cyanogenSynthesizer, phaseSynthesizer,

                //defense
                copperWall, copperWallLarge, titaniumWall, titaniumWallLarge, plastaniumWall, plastaniumWallLarge, thoriumWall, thoriumWallLarge, door, doorLarge,
                phaseWall, phaseWallLarge, surgeWall, surgeWallLarge,

                //walls - erekir
                berylliumWall, berylliumWallLarge, tungstenWall, tungstenWallLarge, blastDoor, reinforcedSurgeWall, reinforcedSurgeWallLarge, carbideWall, carbideWallLarge,
                shieldedWall,

                mender, mendProjector, overdriveProjector, overdriveDome, forceProjector, shockMine,
                scrapWall, scrapWallLarge, scrapWallHuge, scrapWallGigantic, thruster,

                //defense - erekir
                radar, buildTower, regenProjector, shockwaveTower,

                //transport
                conveyor, titaniumConveyor, plastaniumConveyor, armoredConveyor, distributor, junction, itemBridge, phaseConveyor, sorter, invertedSorter, router,
                overflowGate, underflowGate, massDriver,

                //transport - alternate
                duct, armoredDuct, ductRouter, overflowDuct, underflowDuct, ductBridge, ductUnloader, surgeConveyor, surgeRouter, unitCargoLoader, unitCargoUnloadPoint,

                //liquid
                mechanicalPump, rotaryPump, impulsePump, conduit, pulseConduit, platedConduit, liquidRouter, liquidContainer, liquidTank, liquidJunction, bridgeConduit,
                phaseConduit,

                //liquid - reinforced
                reinforcedPump, reinforcedConduit, reinforcedLiquidJunction, reinforcedBridgeConduit, reinforcedLiquidRouter, reinforcedLiquidContainer, reinforcedLiquidTank,

                //power
                combustionGenerator, thermalGenerator, steamGenerator, differentialGenerator, rtgGenerator, solarPanel, largeSolarPanel, thoriumReactor,
                impactReactor, battery, batteryLarge, powerNode, powerNodeLarge, surgeTower, diode,

                //power - erekir
                turbineCondenser, ventCondenser, chemicalCombustionChamber, pyrolysisGenerator, fluxReactor, neoplasiaReactor, beamNode, beamTower, beamLink,

                //production
                mechanicalDrill, pneumaticDrill, laserDrill, blastDrill, waterExtractor, oilExtractor, cultivator, cliffCrusher, largeCliffCrusher, plasmaBore,
                largePlasmaBore, impactDrill, eruptionDrill,

                //storage
                coreShard, coreFoundation, coreNucleus, vault, container, unloader,
                //storage - erekir
                coreBastion, coreCitadel, coreAcropolis, reinforcedContainer, reinforcedVault,

                //turrets
                duo, scatter, scorch, hail, arc, wave, lancer, swarmer, salvo, fuse, ripple, cyclone, foreshadow, spectre, meltdown, segment, parallax, tsunami,

                //turrets - erekir
                breach, diffuse, sublimate, titan, disperse, afflict, lustre, scathe, smite, malign,

                //units
                groundFactory, airFactory, navalFactory, additiveReconstructor, multiplicativeReconstructor, exponentialReconstructor, tetrativeReconstructor,
                repairPoint, repairTurret,

                //units - erekir
                tankFabricator, shipFabricator, mechFabricator,

                tankRefabricator, shipRefabricator, mechRefabricator,
                primeRefabricator,

                tankAssembler, shipAssembler, mechAssembler,
                basicAssemblerModule,

                unitRepairTower,

                //payloads
                payloadConveyor, payloadRouter, reinforcedPayloadConveyor, reinforcedPayloadRouter, payloadMassDriver, largePayloadMassDriver, smallDeconstructor, deconstructor, constructor, largeConstructor, payloadLoader, payloadUnloader,

                //logic
                message, switchBlock, microProcessor, logicProcessor, hyperProcessor, largeLogicDisplay, logicDisplay, logicDisplayTile, memoryCell, memoryBank,
                canvas, reinforcedMessage,

                //campaign
                advancedLaunchPad, landingPad,
                interplanetaryAccelerator
        };

        for (Item it : serpuloItems) {
            it.shownPlanets.addAll(kepler, erekir);
        }

        beryllium.shownPlanets.addAll(kepler, serpulo);
        tungsten.shownPlanets.addAll(kepler, serpulo);
        oxide.shownPlanets.addAll(kepler, serpulo);
        carbide.shownPlanets.addAll(kepler, serpulo);

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

        for (Block it : blocks) {
            try {
                if (it.shownPlanets.contains(serpulo)) {
                    it.shownPlanets.add(erekir);
                } else if (it.shownPlanets.contains(erekir)) {
                    it.shownPlanets.add(serpulo);
                }
                it.shownPlanets.add(kepler);
            } catch (Exception ignored) {}
        }
    }
}
