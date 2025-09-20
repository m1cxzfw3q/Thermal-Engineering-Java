package TEMod;

import TEMod.content.*;
import TEMod.content.Kepler.*;

import arc.Core;
import arc.Events;
import arc.math.Mathf;
import arc.util.Log;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class TECore extends Mod {
    public TECore() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            BaseDialog awa = new BaseDialog(Core.bundle.format("misc.tips"));
            awa.add(Core.bundle.format("misc.tips-" + (Mathf.random(10) - 1)));
            awa.addCloseButton();
            awa.show();
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
        TEV8.load();
        //TEModularWeapons.load();  //毁灭吧 赶紧的

        isComplete(TECore.class);
        TETechTree.load();
    }

    public static void isComplete(Class<?> obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
