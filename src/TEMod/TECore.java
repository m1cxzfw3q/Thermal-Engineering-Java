package TEMod;

import TEMod.content.*;
import TEMod.content.Kepler.*;
import TEMod.systems.TechTreeConfig;
import TEMod.systems.TechTreeExtension;
import TEMod.systems.TechTreeMonitor;

import arc.Core;
import arc.Events;
import arc.util.Log;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class TECore extends Mod {
    public TECore() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            BaseDialog awa = new BaseDialog("[red]削减版提示");
            awa.add("[red]当前版本为削减版，完整版请手动前往该模组github查看！");
            awa.addCloseButton();
            awa.button("前往github下载mod完整版", Icon.github, () -> Core.app.setClipboardText("https://github.com/m1cxzfw3q/Thermal-Engineering-Java/"));
        });
    }
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        KeplerPlanet.load();
        KeplerSectorPresets.load();
        TEStatusEffects.load();
        //TEUnitTypes.load();
        TEFix.load();

        TechTreeConfig.load();//TechTreeExt
        TechTreeExtension.updateAllBlocksVisibility();
        TechTreeExtension.updateAllItemsVisibility();
        TechTreeExtension.updateAllLiquidsVisibility();

        isComplete(TECore.class);
        TETechTree.load();
    }

    @Override
    public void init() {
        TechTreeExtension.init();
        TechTreeMonitor.init();
    }

    public static void isComplete(Object obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
