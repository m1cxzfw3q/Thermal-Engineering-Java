package eui;

import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.mod.Mod;
import arc.Events;
import arc.Core;
import mindustry.Vars;

public class core extends Mod {
    public core() {
        Events.on(EventType.GameOverEvent.class, e -> {
            if (Core.settings.getBool("eui-autoSendGG", false)) Call.sendChatMessage("gg");
        });

        Events.on(EventType.WinEvent.class, e -> {
            if (Core.settings.getBool("eui-autoSendGG", false)) Call.sendChatMessage("gg");
        });

        Events.on(EventType.LoseEvent.class, e -> {
            if (Core.settings.getBool("eui-autoSendGG", false)) Call.sendChatMessage("gg");
        });

        Events.on(EventType.ClientLoadEvent.class, e -> Vars.ui.settings.addCategory("@eui.name", contentTable -> {
                contentTable.checkPref("eui-showPowerBar", true);
                contentTable.checkPref("eui-showFactoryProgress", true);
                contentTable.checkPref("eui-showUnitBar", true);
                contentTable.checkPref("eui-ShowUnitTable", true);
                contentTable.checkPref("eui-ShowBlockInfo", true);
                contentTable.checkPref("eui-ShowAlerts", true);
                contentTable.checkPref("eui-ShowAlertsBottom", false);
                contentTable.checkPref("eui-ShowResourceRate", false);
                contentTable.checkPref("eui-ShowSchematicsTable", true);
                contentTable.checkPref("eui-ShowSchematicsPreview", true);
                contentTable.sliderPref("eui-SchematicsTableRows", 4, 2, 20, 1, String::valueOf);
                contentTable.sliderPref("eui-SchematicsTableColumns", 5, 4, 16, 1, String::valueOf);
                contentTable.sliderPref("eui-SchematicsTableButtonSize", 30, 20, 80, 2, String::valueOf);
                contentTable.checkPref("eui-ShowEfficiency", false);
                contentTable.sliderPref("eui-EfficiencyTimer", 15, 10, 180, 5, String::valueOf);
                contentTable.checkPref("eui-TrackPlayerCursor", false);
                contentTable.sliderPref("eui-playerCursorStyle", 7, 1, 7, 1, String::valueOf);
                contentTable.checkPref("eui-ShowOwnCursor", false);
                contentTable.checkPref("eui-TrackLogicControl", false);
                contentTable.sliderPref("eui-maxZoom", 10, 1, 10, 1, String::valueOf);
                contentTable.checkPref("eui-makeMineble", false);
                contentTable.checkPref("eui-showInteractSettings", true);
                contentTable.sliderPref("eui-action-delay", 500, 0, 3000, 25, i -> i + " ms");
                if (!Vars.mobile) {
                    contentTable.checkPref("eui-DragBlock", false);
                    contentTable.checkPref("eui-DragPathfind", false);
                }
                contentTable.checkPref("eui-autoSendGG", true);
        }));
    }

    @Override
    public void loadContent() {

    }
}