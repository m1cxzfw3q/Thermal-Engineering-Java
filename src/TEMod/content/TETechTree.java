package TEMod.content;

import TEMod.content.Kepler.KeplerPlanet;
import TEMod.content.Kepler.KeplerSectorPresets;
import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static TEMod.TECore.isComplete;
import static TEMod.content.Kepler.KeplerSectorPresets.*;
import static TEMod.content.TEBlocks.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.roots;
import static mindustry.type.ItemStack.with;

public class TETechTree {
    private static TechTree.TechNode context = null;

    public static void load() {
        KeplerPlanet.kepler.techTree = nodeRoot("keplerTechTree", coreExplore, () -> {
            node(TEBlocks.surpluoIcon, () -> {
                node(pyratiteHeater);

                node(TEBlocks.machineCannon,
                        with(Items.copper, 6000, Items.lead, 4000, Items.graphite, 3500),
                        Seq.with(new Objectives.SectorComplete(KeplerSectorPresets.LandingArea), new Objectives.Research(Blocks.scorch),
                                new Objectives.Research(Blocks.hail), new Objectives.Research(TEItems.primaryWarAgreement)
                        ), () -> {
                            node(TEBlocks.missileLauncher,
                                    with(Items.copper, 10000, Items.lead, 12000, Items.graphite, 9000, Items.metaglass, 4000,
                                    Items.silicon, 500, TEItems.primaryChip, 300, TEItems.advancedWarAgreement, 1)
                            );
                });

                node(TEBlocks.primaryLaboratory, Seq.with(), () ->
                        node(TEBlocks.advancedLaboratory,
                                Seq.with(new Objectives.Research(Liquids.cryofluid)), () ->
                                        node(TEBlocks.specialLaboratory)));

                nodeProduce(TEItems.primaryWarAgreement, () ->
                        nodeProduce(TEItems.advancedWarAgreement, () ->
                                nodeProduce(TEItems.specialWarAgreement)));
                nodeProduce(TEItems.primaryProductionAgreement, () -> {
                    nodeProduce(TEItems.advancedProductionAgreement, () ->
                            nodeProduce(TEItems.specialProductionAgreement));
                    nodeProduce(TEItems.highSpeedTransmissionProtocol, () ->
                            nodeProduce(TEItems.ultraRemoteTransmissionProtocol));
                });

                node(shieldGenerator, () ->
                        node(shieldGeneratorLarge, () ->
                                node(shieldGeneratorHuge, () ->
                                        node(sectorShieldGenerator))));

                node(TEBlocks.highEfficiencyDisassembler,
                        with(Items.copper, 100000, Items.titanium, 60000, Items.lead, 80000, Items.graphite, 50000, Items.thorium, 30000,
                                Items.silicon, 20000, Items.plastanium, 8000, Items.phaseFabric, 5000, TEItems.specialProductionAgreement, 2,
                                TEItems.advancedChip, 2000),
                        Seq.with(new Objectives.Research(disassembler), new Objectives.Research(surgeSmelter), new Objectives.Research(phaseWeaver),
                                new Objectives.Research(oilExtractor), new Objectives.Research(TEItems.specialProductionAgreement)
                        )
                );

                node(TEBlocks.simpleStorage,
                        with(Items.copper, 2000, Items.lead, 2600, Items.graphite, 1500),
                        Seq.with(new Objectives.Research(bridgeConduit)),
                        () -> {
                    node(TEBlocks.highSpeedUnloader,
                            with(Items.thorium, 600, Items.silicon, 1000, Items.titanium, 1000, Items.plastanium, 600,
                            TEItems.highSpeedTransmissionProtocol, 1),
                            Seq.with(new Objectives.Research(unloader), new Objectives.Research(vault),
                            new Objectives.Research(TEItems.highSpeedTransmissionProtocol)
                    ));
                });
            });

            node(TEBlocks.keplerIcon, () -> {
                node(TEBlocks.nuclearFuelRodManufacturingMachine,
                        with(Items.thorium, 6000, Items.titanium, 7000, Items.silicon, 6000, Items.lead, 7000, Items.surgeAlloy, 2000,
                        Items.graphite, 82000, TEItems.advancedChip, 1000, TEItems.advancedProductionAgreement, 1
                ), Seq.with(
                        new Objectives.Research(TEItems.advancedProductionAgreement),
                        new Objectives.Research(TEItems.uranium)
                ), () -> {});

                node(TEBlocks.nuclearReactor,
                        with(Items.lead, 20000, Items.graphite, 10000, Items.metaglass, 5000, Items.thorium, 8000, Items.titanium, 10000,
                        Items.surgeAlloy, 3500, TEItems.advancedChip, 500, TEItems.advancedProductionAgreement, 1
                ), Seq.with(
                        new Objectives.Research(TEItems.nuclearFuelRod),
                        new Objectives.Research(thoriumReactor)
                ), () -> {});

                nodeProduce(TEItems.uranium, () ->
                        nodeProduce(TEItems.nuclearFuelRod));
            });

            node(TEBlocks.erekirIcon);

            node(KeplerSectorPresets.LandingArea, Seq.with(), () ->
                    node(KeplerSectorPresets.ResearchAreaNo47, Seq.with(new Objectives.SectorComplete(LandingArea), new Objectives.Research(TEBlocks.machineCannon),
                            new Objectives.SectorComplete(stronghold), new Objectives.SectorComplete(stainedMountains)
                    ), () ->{})
            );
        });

        addToNext(mechanicalDrill, () -> {
            node(mechanicalCliffCrusher);
            node(stoneDrill, with(TEItems.stone, 100, Items.copper, 20));
        });

        addToNext(pneumaticDrill, () ->
                node(pneumaticCliffCrusher));

        addToNext(laserDrill, () ->
                node(laserBore));

        addToNext(copperWall, () ->
                node(TEBlocks.stoneWall, with(TEItems.stone, 30), () ->
                        node(stoneWallLarge, with(TEItems.stone, 120))));

        addToNext(conveyor, () ->
                node(stoneConveyor, with(TEItems.stone, 5)));

        addToNext(pulverizer, () ->
                node(oreCrusher));

        addToNext(kiln, () -> {
            node(oreSmeltingFurnace);
        });

        addToNext(beamNode, () ->
                node(reinforcedPowerNode));

        addToNext(forceProjector, () -> {});
        addToNext(siliconSmelter, () ->
                node(TEBlocks.chipManufacturingMachine,
                        with(Items.copper, 15000, Items.titanium, 10000, Items.lead, 18000, Items.graphite, 13000, Items.silicon, 70000,
                                TEItems.primaryProductionAgreement, 1),
                        Seq.with(new Objectives.Research(TEItems.primaryProductionAgreement)),
                        () -> node(TEBlocks.chipPrinter,
                                with(Items.copper, 26000, Items.titanium, 15000, Items.lead, 30000, Items.graphite, 45000, Items.silicon, 130000,
                                        Items.plastanium, 20000, Items.phaseFabric, 18000, TEItems.advancedProductionAgreement, 2, TEItems.advancedChip, 300),
                                Seq.with(new Objectives.Research(TEItems.advancedProductionAgreement)), () -> {})));

        addToNext(Items.silicon, () ->
                nodeProduce(TEItems.primaryChip, () ->
                        nodeProduce(TEItems.advancedChip, () ->
                                nodeProduce(TEItems.specialChip))));

        addToNext(coreShard, () -> {});
        addToNext(hail, () -> node(TEBlocks.machineCannon,
                with(Items.copper, 6000, Items.lead, 4000, Items.graphite, 3500),
                Seq.with(new Objectives.Research(Blocks.scorch), new Objectives.Research(TEItems.primaryWarAgreement)),
                () -> node(TEBlocks.missileLauncher,
                        with(Items.copper, 10000, Items.lead, 12000, Items.graphite, 9000, Items.metaglass, 4000,
                                Items.silicon, 500, TEItems.primaryChip, 300, TEItems.advancedWarAgreement, 1))));


        isComplete(TETechTree.class);
    }

    private static TechTree.TechNode node(UnlockableContent content, ItemStack[] requirements) {
        return node(content, requirements, () -> {});
    }

    public static void addToNext(UnlockableContent content, Runnable run) {
        context = TechTree.all.find(t -> t.content == content);
        run.run();
    }

    public static TechTree.TechNode nodeRoot(String name, UnlockableContent content, Runnable children){
        return nodeRoot(name, content, false, children);
    }

    public static TechTree.TechNode nodeRoot(String name, UnlockableContent content, boolean requireUnlock, Runnable children){
        var root = node(content, content.researchRequirements(), children);
        root.name = name;
        root.requiresUnlock = requireUnlock;
        roots.add(root);
        return root;//666果然不能直接copy
    }

    public static TechTree.TechNode node(UnlockableContent content, Runnable children){
        return node(content, content.researchRequirements(), children);
    }

    public static TechTree.TechNode node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        return node(content, requirements, null, children);
    }

    public static TechTree.TechNode node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives, Runnable children){
        TechTree.TechNode node = new TechTree.TechNode(context, content, requirements);
        if(objectives != null){
            node.objectives.addAll(objectives);
        }

        TechTree.TechNode prev = context;
        context = node;
        children.run();
        context = prev;

        return node;
    }

    public static TechTree.TechNode node(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        return node(content, content.researchRequirements(), objectives, children);
    }

    public static TechTree.TechNode node(UnlockableContent block){
        return node(block, () -> {});
    }

    public static TechTree.TechNode nodeProduce(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        return node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    public static TechTree.TechNode nodeProduce(UnlockableContent content, Runnable children) {
        return nodeProduce(content, new Seq<>(), children);
    }

    public static TechTree.TechNode nodeProduce(UnlockableContent content) {
        return nodeProduce(content, () -> {});
    }

    public static TechTree.TechNode node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives) {
        return node(content, requirements, objectives, () -> {});
    }
}
