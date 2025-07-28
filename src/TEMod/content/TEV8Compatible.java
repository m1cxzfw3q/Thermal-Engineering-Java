package TEMod.content;

import arc.util.Log;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.UnitType;
import mindustry.world.Block;

import static TEMod.content.Keppler.KepplerPlanet.keppler;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.UnitTypes.*;

public class TEV8Compatible {//由Anuken的傻逼源代码构成
    public static void load() {
        Item[] items = {
                scrap, copper, lead, graphite, coal, titanium, thorium, silicon, plastanium, phaseFabric, surgeAlloy, sporePod,
                Items.sand, blastCompound, pyratite, metaglass, beryllium, tungsten, oxide, carbide
        };

        UnitType[] unitTypes = {
                mace, dagger, crawler, fortress, scepter, reign, vela, nova, pulsar, quasar, corvus, atrax, merui, cleroi,
                anthicus, tecta, collaris, spiroct, arkyid, toxopid, elude, flare, eclipse, horizon, zenith, antumbra,
                avert, obviate, mono, poly, mega, evoke, incite, emanate, quell, disrupt, quad, oct, alpha, beta, gamma,
                risso, minke, bryde, sei, omura, retusa, oxynoe, cyerce, aegires, navanax, stell, locus, precept, vanquish,
                conquer
        };

        Liquid[] liquids = {
                Liquids.water, Liquids.slag, oil, Liquids.cryofluid, arkycite, gallium, neoplasm, ozone, hydrogen, nitrogen, cyanogen
        };

        Block[] blocks = {
                //crafting
                siliconSmelter, siliconCrucible, kiln, graphitePress, plastaniumCompressor, multiPress, phaseWeaver, surgeSmelter, pyratiteMixer, blastMixer, cryofluidMixer,
                melter, separator, disassembler, sporePress, pulverizer, incinerator, coalCentrifuge,

                //crafting - erekir
                siliconArcFurnace, electrolyzer, oxidationChamber, atmosphericConcentrator, electricHeater, slagHeater, phaseHeater, heatRedirector, smallHeatRedirector,
                heatRouter, slagIncinerator, carbideCrucible, slagCentrifuge, surgeCrucible, cyanogenSynthesizer, phaseSynthesizer, heatReactor,

                //defense
                copperWall, copperWallLarge, titaniumWall, titaniumWallLarge, plastaniumWall, plastaniumWallLarge, thoriumWall, thoriumWallLarge, door, doorLarge,
                phaseWall, phaseWallLarge, surgeWall, surgeWallLarge,

                //walls - erekir
                berylliumWall, berylliumWallLarge, tungstenWall, tungstenWallLarge, blastDoor, reinforcedSurgeWall, reinforcedSurgeWallLarge, carbideWall, carbideWallLarge,
                shieldedWall,

                mender, mendProjector, overdriveProjector, overdriveDome, forceProjector, shockMine, scrapWall, scrapWallLarge, scrapWallHuge, scrapWallGigantic,

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
                combustionGenerator, thermalGenerator, steamGenerator, differentialGenerator, rtgGenerator, solarPanel, largeSolarPanel, thoriumReactor, impactReactor,
                battery, batteryLarge, powerNode, powerNodeLarge, surgeTower, diode,

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
                launchPad, advancedLaunchPad, landingPad,
                interplanetaryAccelerator
        };

        for (Item item : items) {
            item.shownPlanets.add(keppler);
            item.postInit();
            Log.info("[Thermal-Engineering] V8TechTree LoadItem(" + item.name + ")");
        }

        for (UnitType unitType : unitTypes ) {
            unitType.shownPlanets.add(keppler);
            unitType.postInit();
            Log.info("[Thermal-Engineering] V8TechTree LoadUnitType(" + unitType.name + ")");
        }

        for (Liquid liquid : liquids) {
            liquid.shownPlanets.add(keppler);
            liquid.postInit();
            Log.info("[Thermal-Engineering] V8TechTree LoadLiquid(" + liquid.name + ")");
        }

        for (Block block : blocks) {
            if (block != null) {
                block.shownPlanets.add(keppler);
                block.postInit();
                Log.info("[Thermal-Engineering] V8TechTree LoadBlock(" + block.name + ")");
            } else Log.warn("[Thermal-Engineering] V8TechTree isNull()");
        }
    }
}
