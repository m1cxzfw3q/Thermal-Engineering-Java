package TEMod.content.Keppler;

import TEMod.content.TEBlocks;
import TEMod.content.TEItems;
import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static TEMod.content.Keppler.KepplerSectorPresets.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.SectorPresets.craters;

public class KepplerTechTree {
    private static TechTree.TechNode context = null;

    public static Seq<TechTree.TechNode> all = new Seq<>();
    public static Seq<TechTree.TechNode> roots = new Seq<>();

    public static void load() {
        KepplerPlanet.keppler.techTree = nodeRoot("kepplerTechTree", coreNucleus, () -> {
            node(TEBlocks.surpluoIcon, () -> {
                node(TEBlocks.machineCannon,
                        ItemStack.with(
                                Items.copper, 6000,
                                Items.lead, 4000,
                                Items.graphite, 3500
                                ),
                        Seq.with(
                                new Objectives.SectorComplete(LandingArea),
                                new Objectives.Research(Blocks.scorch),
                                new Objectives.Research(Blocks.hail),
                                new Objectives.Research(TEItems.primaryWarAgreement)
                        ),
                        () -> {
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
                                TEItems.advancedProductionAgreement, 1
                        ),
                        Seq.with(
                                new Objectives.Research(disassembler),
                                new Objectives.Research(surgeSmelter),
                                new Objectives.Research(phaseWeaver),
                                new Objectives.Research(oilExtractor),
                                new Objectives.Research(TEItems.advancedProductionAgreement)
                        ),
                        () -> {
                        });
            });

            node(TEBlocks.kepplerIcon, () -> {
                nodeProduce(TEItems.uranium, () -> {
                    nodeProduce(TEItems.nuclearFuelRod, () -> {
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
                    });
                });
                nodeProduce(TEItems.ultraRemoteTransmissionProtocol, () -> {
                });
            });

            node(TEBlocks.erekirIcon, () -> {
            });

            node(LandingArea, Seq.with(
                    new Objectives.SectorComplete(craters),
                    new Objectives.Research(kiln),
                    new Objectives.Research(logicProcessor),
                    new Objectives.Research(logicDisplay),
                    new Objectives.Research(memoryCell),
                    new Objectives.Research(multiPress),
                    new Objectives.Research(liquidTank),
                    new Objectives.Research(bridgeConduit),
                    new Objectives.Research(titaniumConveyor),
                    new Objectives.Research(laserDrill),
                    new Objectives.Research(steamGenerator),
                    new Objectives.Research(swarmer),
                    new Objectives.Research(fuse),
                    new Objectives.Research(segment),
                    new Objectives.Research(tsunami),
                    new Objectives.Research(lancer),
                    new Objectives.SectorComplete(ravine),
                    new Objectives.Research(shipRefabricator)
            ), () -> {
            });
        });
    }

    public static void addToNext(UnlockableContent content, Runnable run) {
        context = TechTree.all.find(KepplerTechNode -> KepplerTechNode.content == content);
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
