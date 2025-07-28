package TEMod.content.Keppler;

import TEMod.content.TEBlocks;
import TEMod.content.TEItems;
import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static TEMod.ThermalEngineeringCore.isComplete;
import static TEMod.content.Keppler.KepplerSectorPresets.*;
import static TEMod.content.TEBlocks.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;

public class KepplerTechTree {
    private static TechTree.TechNode context = null;

    public static Seq<TechTree.TechNode> roots = new Seq<>();

    public static void load() {

        KepplerPlanet.keppler.techTree = nodeRoot("kepplerTechTree", coreExplore, () -> {
            
            node(TEBlocks.surpluoIcon, () -> {
                node(TEBlocks.machineCannon,
                        ItemStack.with(
                                Items.copper, 6000,
                                Items.lead, 4000,
                                Items.graphite, 3500
                        ), Seq.with(
                                new Objectives.SectorComplete(KepplerSectorPresets.LandingArea),
                                new Objectives.Research(Blocks.scorch),
                                new Objectives.Research(Blocks.hail),
                                new Objectives.Research(TEItems.primaryWarAgreement)
                        ), () -> {
                    node(TEBlocks.portableMissileLaunchSilo, ItemStack.with(
                            Items.copper, 600,
                            Items.lead, 8000,
                            Items.graphite, 2000,
                            Items.blastCompound, 10,
                            Items.silicon, 1000,
                            TEItems.primaryChip, 100,
                            TEItems.primaryWarAgreement, 1
                    ), Seq.with(), () -> {
                        node(TEBlocks.missileLauncher, ItemStack.with(
                                Items.copper, 10000,
                                Items.lead, 12000,
                                Items.graphite, 9000,
                                Items.metaglass, 4000,
                                Items.silicon, 500,
                                TEItems.primaryChip, 300,
                                TEItems.advancedWarAgreement, 1
                        ), Seq.with(), () -> {});
                    });
                });

                node(TEBlocks.highEfficiencyDisassembler,
                        ItemStack.with(
                                Items.copper, 100000,
                                Items.titanium, 60000,
                                Items.lead, 80000,
                                Items.graphite, 50000,
                                Items.thorium, 30000,
                                Items.silicon, 20000,
                                Items.plastanium, 8000,
                                Items.phaseFabric, 5000,
                                TEItems.specialProductionAgreement, 2,
                                TEItems.advancedChip, 2000
                        ), Seq.with(
                                new Objectives.Research(disassembler),
                                new Objectives.Research(surgeSmelter),
                                new Objectives.Research(phaseWeaver),
                                new Objectives.Research(oilExtractor),
                                new Objectives.Research(TEItems.specialProductionAgreement)
                        ), () -> {
                });

                node(TEBlocks.primaryLaboratory, Seq.with(
                        new Objectives.SectorComplete(ResearchAreaNo47)
                ), () -> {
                    node(TEBlocks.advancedLaboratory, Seq.with(
                            new Objectives.Research(Liquids.cryofluid)
                    ), () -> {
                        node(TEBlocks.specialLaboratory, Seq.with(
                        ), () -> {

                        });
                    });
                });

                nodeProduce(TEItems.primaryWarAgreement, () -> {
                    nodeProduce(TEItems.advancedWarAgreement, () -> {
                        nodeProduce(TEItems.specialWarAgreement, () -> {
                        });
                    });
                });

                nodeProduce(TEItems.primaryProductionAgreement, () -> {
                    nodeProduce(TEItems.advancedProductionAgreement, () -> {
                        nodeProduce(TEItems.specialProductionAgreement, () -> {
                        });

                        nodeProduce(TEItems.ultraRemoteTransmissionProtocol, () -> {
                        });
                    });
                });

                nodeProduce(TEItems.primaryChip, () -> {
                    nodeProduce(TEItems.advancedChip, () -> {
                        nodeProduce(TEItems.specialChip, () -> {
                        });
                    });
                });

                node(TEBlocks.chipManufacturingMachine, ItemStack.with(
                        Items.copper, 15000,
                        Items.titanium, 10000,
                        Items.lead, 18000,
                        Items.graphite, 13000,
                        Items.silicon, 70000,
                        TEItems.primaryProductionAgreement, 1
                ), Seq.with(
                        new Objectives.Research(TEItems.primaryProductionAgreement)
                ), () -> {
                    node(TEBlocks.chipPrinter, ItemStack.with(
                            Items.copper, 26000,
                            Items.titanium, 15000,
                            Items.lead, 30000,
                            Items.graphite, 45000,
                            Items.silicon, 130000,
                            Items.plastanium, 20000,
                            Items.phaseFabric, 18000,
                            TEItems.advancedProductionAgreement, 2,
                            TEItems.advancedChip, 300
                    ), Seq.with(
                            new Objectives.Research(TEItems.advancedProductionAgreement)
                    ), () -> {});
                });

                node(TEBlocks.simpleStorage, ItemStack.with(
                        Items.copper, 2000,
                        Items.lead, 2600,
                        Items.graphite, 1500
                ), Seq.with(
                        new Objectives.Research(bridgeConduit)
                ), () -> {
                    node(TEBlocks.highSpeedUnloader, ItemStack.with(
                            Items.thorium, 600,
                            Items.silicon, 1000,
                            Items.titanium, 1000,
                            Items.plastanium, 600,
                            TEItems.highSpeedTransmissionProtocol, 1
                    ), Seq.with(
                            new Objectives.Research(unloader),
                            new Objectives.Research(vault),
                            new Objectives.Research(TEItems.highSpeedTransmissionProtocol)
                    ), () -> {});
                });
                node(TEBlocks.advancedLaunchPad, ItemStack.with(

                ), Seq.with(
                        new Objectives.Research(launchPad),
                        new Objectives.SectorComplete(LandingArea)
                ), () ->{
                });
            });

            node(TEBlocks.kepplerIcon, () -> {
                node(TEBlocks.nuclearFuelRodManufacturingMachine, ItemStack.with(
                        Items.thorium, 6000,
                        Items.titanium, 7000,
                        Items.silicon, 6000,
                        Items.lead, 7000,
                        Items.surgeAlloy, 2000,
                        Items.graphite, 82000,
                        TEItems.advancedChip, 1000,
                        TEItems.advancedProductionAgreement, 1
                ), Seq.with(
                        new Objectives.Research(TEItems.advancedProductionAgreement),
                        new Objectives.Research(TEItems.uranium)
                ), () -> {});

                node(TEBlocks.nuclearReactor, ItemStack.with(
                        Items.lead, 20000,
                        Items.graphite, 10000,
                        Items.metaglass, 5000,
                        Items.thorium, 8000,
                        Items.titanium, 10000,
                        Items.surgeAlloy, 3500,
                        TEItems.advancedChip, 500,
                        TEItems.advancedProductionAgreement, 1
                ), Seq.with(
                        new Objectives.Research(TEItems.nuclearFuelRod),
                        new Objectives.Research(thoriumReactor)
                ), () -> {});

                nodeProduce(TEItems.uranium, () -> {
                    nodeProduce(TEItems.nuclearFuelRod, () -> {
                    });
                });
            });

            node(TEBlocks.erekirIcon, () -> {
            });

            node(KepplerSectorPresets.LandingArea, Seq.with(
                    new Objectives.SectorComplete(planetaryTerminal),
                    new Objectives.SectorComplete(origin)
            ), () -> {
                node(KepplerSectorPresets.ResearchAreaNo47, Seq.with(
                        new Objectives.SectorComplete(LandingArea),
                        new Objectives.Research(TEBlocks.machineCannon),
                        new Objectives.SectorComplete(stronghold),
                        new Objectives.SectorComplete(stainedMountains)
                ), () ->{});
            });
        });
        isComplete(KepplerTechTree.class);
    }

    public static TechTree.TechNode nodeRoot(String name, UnlockableContent content, Runnable children){
        return nodeRoot(name, content, false, children);
    }

    public static TechTree.TechNode nodeRoot(String name, UnlockableContent content, boolean requireUnlock, Runnable children){
        var root = node(content, content.researchRequirements(), children);
        root.name = name;
        root.requiresUnlock = requireUnlock;
        roots.add(root);
        return root;
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

    public static TechTree.TechNode nodeProduce(UnlockableContent content, Runnable children){
        return nodeProduce(content, new Seq<>(), children);
    }
}
