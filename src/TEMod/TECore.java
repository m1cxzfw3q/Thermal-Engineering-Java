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
    public void loadContent() {
        TEAttribute.load();
        TEItems.load();
        TEBlocks.load();
        KeplerPlanet.load();
        KeplerSectorPresets.load();
        TEStatusEffects.load();
        //TEModularWeapons.load();
        //TEUnitTypes.load();

        isComplete(TECore.class);
        TETechTree.load();
        TEV8.load();
        TEFix.load();
    }

    public static void isComplete(Class<?> obj) {
        Log.info("[Thermal-Engineering] isComplete(" + obj + ")");
    }
}
