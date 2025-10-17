package TEMod.content;

import arc.Core;
import mindustry.content.Items;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import arc.graphics.Color;

import static TEMod.TECore.isComplete;

public class TEItems {
    /** 基础物品 **/
    public static Item
            nuclearFuelRod,
            zinc, sphularite, uranium, iron, rawIron,
            plasticAlloy, steel;
    /** 一堆自动生成的粉末 **/
    public static Item copperPowder = PowderGen(Items.copper),
            leadPowder = PowderGen(Items.lead),
            titaniumPowder = PowderGen(Items.titanium),
            thoriumPowder = PowderGen(Items.thorium),
            zincPowder, ironPowder;
    /** 协议与芯片 **/
    public static Item
            primaryWarAgreement, advancedWarAgreement, specialWarAgreement,
            highSpeedTransmissionProtocol, ultraRemoteTransmissionProtocol,
            primaryProductionAgreement, advancedProductionAgreement, specialProductionAgreement,
            primaryChip, advancedChip, specialChip;
    /** 一堆小东西 **/
    public static Item primaryEnergyComponent, intermediateEnergyComponent, advancedEnergyComponent, specialEnergyComponent, //能量组件
        primaryEnergyStorageComponent, intermediateEnergyStorageComponent,
            advancedEnergyStorageComponent, specialEnergyStorageComponent;//储能组件

    public static Item stone; //石！

    public static Item PowderGen(Item sourceItem) { //粉末生成器
        return new Item((sourceItem.name).replace("temod-", "") + "-powder", sourceItem.color) {{
            localizedName = sourceItem.localizedName + Core.bundle.format("items.powder-gen.name");
            description = Core.bundle.format("items.powder-gen.description").replace("T", sourceItem.localizedName);
            details = sourceItem.details + Core.bundle.format("items.powder-gen.details");
            radioactivity = sourceItem.radioactivity;
            explosiveness = sourceItem.explosiveness;
            flammability = sourceItem.radioactivity;
            charge = sourceItem.charge;
        }};
    }

    public static void load() {
        uranium = new Item("uranium", Color.valueOf("617270")) {{//这个mod从Json开始的第一个物品
            hardness = 5;
            cost = 1.2F;
            radioactivity = 1.5F;
            alwaysUnlocked = false;
        }};

        nuclearFuelRod = new Item("nuclear-fuel-rod", Color.valueOf("4a6949")) {{
            hardness = 0;
            radioactivity = 2F;
            alwaysUnlocked = false;
        }};

        primaryWarAgreement = new Item("primary-war-agreement", Color.valueOf("bff1ff"));
        advancedWarAgreement = new Item("advanced-war-agreement", Color.valueOf("ffff42"));
        specialWarAgreement = new Item("special-war-agreement", Color.valueOf("e2583d"));

        highSpeedTransmissionProtocol = new Item("high-speed-transmission-protocol", Color.valueOf("ffff42")) {{
            alwaysUnlocked = false;
        }};

        ultraRemoteTransmissionProtocol = new Item("ultra-remote-transmission-protocol", Color.valueOf("e2583d")) {{
            alwaysUnlocked = false;
        }};

        primaryProductionAgreement = new Item("primary-production-agreement", Color.valueOf("bff1ff"));
        advancedProductionAgreement = new Item("advanced-production-agreement", Color.valueOf("ffff42"));
        specialProductionAgreement = new Item("special-production-agreement", Color.valueOf("e2583d"));

        primaryChip = new Item("primary-chip", Color.valueOf("636363")) {{cost = 0.4f;}};

        advancedChip = new Item("advanced-chip", Color.valueOf("cdad2d")) {{cost = 0.5f;}};

        specialChip = new Item("special-chip", Color.valueOf("bf4833")) {{cost = 0.6f;}};

        sphularite = new Item("sphularite", Color.valueOf("BFCDBC")) {{hardness = 3;}};

        zinc = new Item("zinc", Color.valueOf("BFCDBC")) {{cost = 0.6f;}};

        steel = new Item("steel", Color.valueOf("94949a")) {{
            cost = 0.9f;
        }};

        iron = new Item("iron", Color.valueOf("94949a")) {{
            cost = 0.8f;
        }};
        ironPowder = PowderGen(iron);
        zincPowder = PowderGen(zinc);

        rawIron = new Item("raw-iron", Color.valueOf("94949a")) {{
            hardness = 2;
        }};

        plasticAlloy = new Item("plastic-alloy", Color.valueOf("81b54d")) {{
            explosiveness = 0.1f;
            cost = 1.5f;
            flammability = 0.2f;
        }};

        primaryEnergyComponent = new Item("primary-energy-component", Pal.lancerLaser) {{
            explosiveness = 2f;
            charge = 5f;
            cost = 4f;
        }};
        intermediateEnergyComponent = new Item("intermediate-energy-component", Pal.lancerLaser) {{
            explosiveness = 5f;
            charge = 12f;
            cost = 5f;
        }};
        advancedEnergyComponent = new Item("advanced-energy-component", Pal.lancerLaser) {{
            explosiveness = 12f;
            charge = 20f;
            cost = 6f;
        }};
        specialEnergyComponent = new Item("special-energy-component", Pal.lancerLaser) {{
            explosiveness = 22f;
            charge = 35f;
            cost = 7f;
        }};

        primaryEnergyStorageComponent = new Item("primary-energy-storage-component", Pal.ammo) {{
            cost = 5f;
        }};
        intermediateEnergyStorageComponent = new Item("intermediate-energy-storage-component", Pal.ammo) {{
            cost = 6f;
        }};
        advancedEnergyStorageComponent = new Item("advanced-energy-storage-component", Pal.ammo) {{
            cost = 7f;
        }};
        specialEnergyStorageComponent = new Item("special-energy-storage-component", Pal.ammo) {{
            cost = 8f;
        }};

        stone = new Item("stone", Color.valueOf("94949a")) {{//石头！
            hardness = 1;// 石头！！！！\o/
            cost = 0.2f;
        }};

        isComplete(TEItems.class);
    }
}
