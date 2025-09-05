package TEMod.systems;

import arc.struct.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.Block;

import static TEMod.content.Kepler.KeplerPlanet.kepler;
import static mindustry.Vars.content;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;

public class TechTreeExtension {
    private static final ObjectSet<Block> keplerBlocks = new ObjectSet<>();
    private static final ObjectSet<Item> keplerItems = new ObjectSet<>();
    private static final ObjectSet<Liquid> keplerLiquids = new ObjectSet<>();

    // 注册需要在Kepler星球显示的初始内容
    public static void registerKeplerContent() {
        // 在这里注册你希望在Kepler星球显示的基础内容
        keplerItems.addAll(
                scrap, copper, lead, graphite, coal, titanium, thorium, silicon, plastanium,
                phaseFabric, surgeAlloy, sporePod, Items.sand, blastCompound, pyratite, metaglass,
                beryllium, tungsten, oxide, carbide
        );

        keplerLiquids.addAll(
                Liquids.water, Liquids.slag, oil, Liquids.cryofluid,
                arkycite, neoplasm,
                ozone, hydrogen, nitrogen, cyanogen
        );

        keplerBlocks.addAll(
                //crafting
                siliconSmelter, siliconCrucible, kiln, graphitePress, plastaniumCompressor, multiPress, phaseWeaver, surgeSmelter, pyratiteMixer, blastMixer, cryofluidMixer,
                melter, separator, disassembler, sporePress, pulverizer, incinerator, coalCentrifuge,

                //crafting - erekir
                siliconArcFurnace, electrolyzer, oxidationChamber, atmosphericConcentrator, electricHeater, slagHeater, phaseHeater, heatRedirector, smallHeatRedirector, heatRouter, slagIncinerator,
                carbideCrucible, slagCentrifuge, surgeCrucible, cyanogenSynthesizer, phaseSynthesizer, heatReactor,

                //defense
                copperWall, copperWallLarge, titaniumWall, titaniumWallLarge, plastaniumWall, plastaniumWallLarge, thoriumWall, thoriumWallLarge, door, doorLarge,
                phaseWall, phaseWallLarge, surgeWall, surgeWallLarge,

                //walls - erekir
                berylliumWall, berylliumWallLarge, tungstenWall, tungstenWallLarge, blastDoor, reinforcedSurgeWall, reinforcedSurgeWallLarge, carbideWall, carbideWallLarge,
                shieldedWall,

                mender, mendProjector, overdriveProjector, overdriveDome, forceProjector, shockMine,
                scrapWall, scrapWallLarge, scrapWallHuge, scrapWallGigantic, thruster, //ok, these names are getting ridiculous, but at least I don't have humongous walls yet

                //defense - erekir
                radar,
                buildTower,
                regenProjector, shockwaveTower,

                //transport
                conveyor, titaniumConveyor, plastaniumConveyor, armoredConveyor, distributor, junction, itemBridge, phaseConveyor, sorter, invertedSorter, router,
                overflowGate, underflowGate, massDriver,

                //transport - alternate
                duct, armoredDuct, ductRouter, overflowDuct, underflowDuct, ductBridge, ductUnloader,
                surgeConveyor, surgeRouter,

                unitCargoLoader, unitCargoUnloadPoint,

                //liquid
                mechanicalPump, rotaryPump, impulsePump, conduit, pulseConduit, platedConduit, liquidRouter, liquidContainer, liquidTank, liquidJunction, bridgeConduit, phaseConduit,

                //liquid - reinforced
                reinforcedPump, reinforcedConduit, reinforcedLiquidJunction, reinforcedBridgeConduit, reinforcedLiquidRouter, reinforcedLiquidContainer, reinforcedLiquidTank,

                //power
                combustionGenerator, thermalGenerator, steamGenerator, differentialGenerator, rtgGenerator, solarPanel, largeSolarPanel, thoriumReactor,
                impactReactor, battery, batteryLarge, powerNode, powerNodeLarge, surgeTower, diode,

                //power - erekir
                turbineCondenser, ventCondenser, chemicalCombustionChamber, pyrolysisGenerator, fluxReactor, neoplasiaReactor,
                beamNode, beamTower, beamLink,

                //production
                mechanicalDrill, pneumaticDrill, laserDrill, blastDrill, waterExtractor, oilExtractor, cultivator,
                cliffCrusher, largeCliffCrusher, plasmaBore, largePlasmaBore, impactDrill, eruptionDrill,

                //storage
                coreShard, coreFoundation, coreNucleus, vault, container, unloader,
                //storage - erekir
                coreBastion, coreCitadel, coreAcropolis, reinforcedContainer, reinforcedVault,

                //turrets
                duo, scatter, scorch, hail, arc, wave, lancer, swarmer, salvo, fuse, ripple, cyclone, foreshadow, spectre, meltdown, segment, parallax, tsunami,

                //turrets - erekir
                breach, diffuse, sublimate, titan, disperse, afflict, lustre, scathe, smite, malign,

                //units
                groundFactory, airFactory, navalFactory,
                additiveReconstructor, multiplicativeReconstructor, exponentialReconstructor, tetrativeReconstructor,
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
                canvas, reinforcedMessage
        );
    }

    // 检查建筑是否满足显示条件
    public static boolean shouldShowOnKepler(Block block) {
        // 检查建筑本身是否在其他科技树中解锁
        return isBlockUnlockedElsewhere(block);
    }

    // 检查建筑是否在其他科技树中解锁
    private static boolean isBlockUnlockedElsewhere(Block block) {
        // 这里实现检查逻辑，可以检查默认星球或其他模组的科技树
        // 简化版：假设所有非Kepler专属建筑都在其他科技树中解锁
        return !keplerBlocks.contains(block) && !TechTreeConfig.isKeplerBlock(block);
    }

    // 检查物品是否在其他科技树中解锁
    private static boolean isItemUnlockedElsewhere(Item item) {
        // 这里实现检查逻辑
        // 简化版：假设所有非Kepler专属物品都在其他科技树中解锁
        return !keplerItems.contains(item) && !TechTreeConfig.isKeplerItem(item);
    }

    // 检查液体是否在其他科技树中解锁
    private static boolean isLiquidUnlockedElsewhere(Liquid liquid) {
        // 这里实现检查逻辑
        // 简化版：假设所有非Kepler专属液体都在其他科技树中解锁
        return !keplerLiquids.contains(liquid) && !TechTreeConfig.isKeplerLiquid(liquid);
    }

    // 更新所有建筑的显示状态
    public static void updateAllBlocksVisibility() {
        for (Block block : content.blocks()) {
            if (shouldShowOnKepler(block)) {
                // 添加到Kepler星球的显示列表
                if (!block.shownPlanets.contains(kepler)) {
                    block.shownPlanets.add(kepler);
                }
            } else {
                // 从Kepler星球的显示列表中移除
                block.shownPlanets.remove(kepler);
            }
        }
    }

    // 更新所有物品的显示状态
    public static void updateAllItemsVisibility() {
        for (Item item : content.items()) {
            if (isItemUnlockedElsewhere(item)) {
                if (!item.shownPlanets.contains(kepler)) {
                    item.shownPlanets.add(kepler);
                }
            } else {
                item.shownPlanets.remove(kepler);
            }
        }
    }

    // 更新所有液体的显示状态
    public static void updateAllLiquidsVisibility() {
        for (Liquid liquid : content.liquids()) {
            if (isLiquidUnlockedElsewhere(liquid)) {
                if (!liquid.shownPlanets.contains(kepler)) {
                    liquid.shownPlanets.add(kepler);
                }
            } else {
                liquid.shownPlanets.remove(kepler);
            }
        }
    }

    // 初始化系统
    public static void init() {
        registerKeplerContent();
        updateAllBlocksVisibility();
        updateAllItemsVisibility();
        updateAllLiquidsVisibility();
    }
}