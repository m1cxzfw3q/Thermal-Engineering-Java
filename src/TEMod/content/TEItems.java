package TEMod.content;

import mindustry.type.Item;
import arc.graphics.Color;

public class TEItems {
    //普通物品
    public static Item uranium; //铀
    public static Item nuclearFuelRod; //核燃料棒
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
        uranium = new Item("uranium", Color.valueOf("617270")) {{
            hardness = 5;
            cost = 1.2F;
            radioactivity = 1.5F;
            alwaysUnlocked = false;
            buildable = true;
        }};

        nuclearFuelRod = new Item("nuclearFuelRod", Color.valueOf("4a6949")) {{
            hardness = 0;
            radioactivity = 2F;
            alwaysUnlocked = false;
            buildable = false;
        }};

        primaryWarAgreement = new Item("primaryWarAgreement", Color.valueOf("bff1ff")) {{
            alwaysUnlocked = false;
        }};

        advancedWarAgreement = new Item("advancedWarAgreement", Color.valueOf("ffff42")) {{
            alwaysUnlocked = false;
        }};

        specialWarAgreement = new Item("specialWarAgreement", Color.valueOf("e2583d")) {{
            alwaysUnlocked = false;
        }};

        highSpeedTransmissionProtocol = new Item("highSpeedTransmissionProtocol", Color.valueOf("ffff42")) {{
            alwaysUnlocked = false;
            buildable = false;
        }};

        ultraRemoteTransmissionProtocol = new Item("ultraRemoteTransmissionProtocol", Color.valueOf("e2583d")) {{
            alwaysUnlocked = false;
            buildable = false;
        }};

        primaryProductionAgreement = new Item("primaryProductionAgreement", Color.valueOf("bff1ff")) {{
            alwaysUnlocked = false;
        }};

        advancedProductionAgreement = new Item("advancedProductionAgreement", Color.valueOf("ffff42")) {{
            alwaysUnlocked = false;
        }};

        specialProductionAgreement = new Item("specialProductionAgreement", Color.valueOf("e2583d")) {{
            alwaysUnlocked = false;
        }};

        primaryChip = new Item("primaryChip", Color.valueOf("636363")) {{
            alwaysUnlocked = false;
            cost = 0.4f;
        }};

        advancedChip = new Item("advancedChip", Color.valueOf("cdad2d")) {{
            alwaysUnlocked = false;
            cost = 0.5f;
        }};

        specialChip = new Item("specialChip", Color.valueOf("bf4833")) {{
            alwaysUnlocked = false;
            cost = 0.6f;
        }};
    }
}
