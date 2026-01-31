package TEMod;

import TEMod.content.*;
import TEMLib.*;
import mindustry.mod.ClassMap;

/// 为JSONMOD提供的接口
public class TEJsonInterface {
    static {
        ClassMap.classes.put("TEMOD_Content", TESpecialContent.TEContent.class);
        ClassMap.classes.put("TEMOD_MultiCrafter", MultiCrafter.class); //不建议使用这个多合成系统，容易崩溃且还在重置
        ClassMap.classes.put("TEMOD_Recipe", MultiCrafter.Recipe.class);
        ClassMap.classes.put("TEMOD_PortableCoreBlock", PortableCoreBlock.class);
        ClassMap.classes.put("TEMOD_StarshipUnitType", StarshipUnitType.class); //没做完
        ClassMap.classes.put("TEMOD_NullAI", NullAI.class);
        ClassMap.classes.put("TEMOD_CoverBlock", CoverBlock.class);
        ClassMap.classes.put("TEMOD_CoverExtract", CoverExtract.class); //没做完
        ClassMap.classes.put("TEMOD_CoverLiquidRequireFloor", CoverLiquidRequireFloor.class);
        ClassMap.classes.put("TEMOD_EnvironmentalPollution", EnvironmentalPollution.class); //没做完
        ClassMap.classes.put("TEMOD_ExpandOverdriveProjector", ExpandOverdriveProjector.class); //没做完
        ClassMap.classes.put("TEMOD_ExplosionLaserBulletType", ExplosionLaserBulletType.class); //这是史 不建议用
        ClassMap.classes.put("TEMOD_ExponentiationOverdriveProjector", ExponentiationOverdriveProjector.class); //没做完
        ClassMap.classes.put("TEMOD_HardDriveItem", HardDriveItem.class); //没做完 估计不会做完(这东西对于mdt还是太难了)
        ClassMap.classes.put("TEMOD_LightItemBridge", LightItemBridge.class); //这是史 但能用
        ClassMap.classes.put("TEMOD_MultiChargeTurret", MultiChargeTurret.class); //这是史 不建议用
        ClassMap.classes.put("TEMOD_PayloadLauncher", PayloadLauncher.class); //没做完
        ClassMap.classes.put("TEMOD_Plotline", Plotline.class); //没做完
        ClassMap.classes.put("TEMOD_Plot", Plotline.Plot.class); //没做完
        ClassMap.classes.put("TEMOD_StackItemLiquid", StackItemLiquid.class); //这是史 不建议用
    }
}