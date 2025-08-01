package TEMod.content;

import mindustry.type.Item;
import arc.graphics.Color;

import static TEMod.ThermalEngineeringCore.isComplete;

public class TEItems {
    //普通物品
    public static Item uranium; //铀
    public static Item nuclearFuelRod; //核燃料棒
    public static Item zinc, sphularite; //锌, 闪锌矿
    public static Item copperPowder, leadPowder, titaniumPowder, thoriumPowder, zincPowder; //铜粉, 铅粉, 钛粉, 钍粉, 锌粉
    //协议-战争
    public static Item primaryWarAgreement; //初级战争协议
    public static Item advancedWarAgreement; //高级战争协议
    public static Item specialWarAgreement; //特级战争协议
    //协议-传输
    public static Item highSpeedTransmissionProtocol; //高速传输协议
    public static Item ultraRemoteTransmissionProtocol; //超远程传输协议
    //协议-生产
    public static Item primaryProductionAgreement; //初级生产协议
    public static Item advancedProductionAgreement; //高级生产协议
    public static Item specialProductionAgreement; //特级生产协议
    //芯片
    public static Item primaryChip; //初级芯片
    public static Item advancedChip; //高级芯片
    public static Item specialChip; //特级芯片

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
            buildable = false;
        }};

        primaryWarAgreement = new Item("primary-war-agreement", Color.valueOf("bff1ff"));
        advancedWarAgreement = new Item("advanced-war-agreement", Color.valueOf("ffff42"));
        specialWarAgreement = new Item("special-war-agreement", Color.valueOf("e2583d"));

        highSpeedTransmissionProtocol = new Item("high-speed-transmission-protocol", Color.valueOf("ffff42")) {{
            alwaysUnlocked = false;
            buildable = false;
        }};

        ultraRemoteTransmissionProtocol = new Item("ultra-remote-transmission-protocol", Color.valueOf("e2583d")) {{
            alwaysUnlocked = false;
            buildable = false;
        }};

        primaryProductionAgreement = new Item("primary-production-agreement", Color.valueOf("bff1ff"));
        advancedProductionAgreement = new Item("advanced-production-agreement", Color.valueOf("ffff42"));
        specialProductionAgreement = new Item("special-production-agreement", Color.valueOf("e2583d"));

        primaryChip = new Item("primary-chip", Color.valueOf("636363")) {{cost = 0.4f;}};

        advancedChip = new Item("advanced-chip", Color.valueOf("cdad2d")) {{cost = 0.5f;}};

        specialChip = new Item("special-chip", Color.valueOf("bf4833")) {{cost = 0.6f;}};

        sphularite = new Item("sphularite", Color.valueOf("BFCDBC")) {{hardness = 3;buildable = false;}};

        zinc = new Item("zinc", Color.valueOf("BFCDBC")) {{cost = 2f;}};

        copperPowder = new Item("copper-powder", Color.valueOf("d99d73")) {{buildable = false;}};

        leadPowder = new Item("lead-powder", Color.valueOf("8c7fa9")) {{buildable = false;}};

        titaniumPowder = new Item("titanium-powder", Color.valueOf("8da1e3")) {{
            buildable = false;
        }};

        thoriumPowder = new Item("thorium-powder", Color.valueOf("f9a3c7")) {{
            buildable = false;
            explosiveness = 0.1f;
            radioactivity = 0.5f;
        }};

        zincPowder = new Item("zinc-powder", Color.valueOf("BFCDBC")) {{
            buildable = false;
        }};

        isComplete(TEItems.class);
    }
}
