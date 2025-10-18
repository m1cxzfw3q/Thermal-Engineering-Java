package TEMod;

import TEMod.content.*;
import TEMod.content.Kepler.*;
import arc.Core;
import arc.Events;
import arc.math.Mathf;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.mod.Mod;

public class TECore extends Mod {
    public int i = 0;

    public TECore() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            Vars.ui.settings.addCategory("@temod.settingTable", Icon.box, T -> {
                T.checkPref("temod.settingTable.tips", true);
            });
            if (Core.settings.getBool("temod.settingTable.tips")) {
                String aTipStr = Core.bundle.format("misc.tips") + "\n" + Core.bundle.format("misc.tips-" + (Mathf.random(9) + 1));
                Vars.ui.content.add(aTipStr).left();
                Vars.ui.settings.add(aTipStr).left();
                Vars.ui.database.add(aTipStr).left();
                Vars.ui.join.add(aTipStr).left();
                Vars.ui.host.add(aTipStr).left();
                Vars.ui.maps.add(aTipStr).left();
                Vars.ui.schematics.add(aTipStr).left();
                Vars.ui.bans.add(aTipStr).left();
                Vars.ui.admins.add(aTipStr).left();
                Vars.ui.load.add(aTipStr).left();
                Vars.ui.logic.add(aTipStr).left();
                Vars.ui.campaignComplete.add(aTipStr).left();
                Vars.ui.language.add(aTipStr).left();
            }
        });
    }

    @Override
    public void loadContent() {//究极防崩以及神秘日志输出
        tryCatch(TEAttribute::load);
        tryCatch(TEItems::load);
        tryCatch(TEBlocks::load);
        tryCatch(KeplerPlanet::load);
        tryCatch(KeplerSectorPresets::load);
        tryCatch(TEStatusEffects::load);
        //tryCatch(TEModularWeapons::load);
        //tryCatch(TEUnitTypes::load);

        isComplete(TECore.class);
        tryCatch(TETechTree::load);
        tryCatch(TEV8::load);
        tryCatch(TEFix::load);
        if (i == 9) {
            Log.err("[Thermal-Enginerring] Due to certain issues, the mod has completely crashed. Please contact the author for repairs.");
        } else if (i > 0) {
            Log.info("[Thermal-Enginerring] This mod has errors in a total of @ classes, please contact the author for fixes.", i);
        } else Log.info("[Thermal-Enginerring] Mod loaded successfully.");
    }

    public void tryCatch(Runnable run) {
        try {
            run.run();
        } catch (Exception e) {
            Log.err(
                    "[Thermal-Enginerring] The client encountered a crash involving some classes during loading, please contact the author."
                    + "\n" + e
            );
            i++;
        }
    }

    public static void isComplete(Class<?> obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
