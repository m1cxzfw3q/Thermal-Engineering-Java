package TEMod;

import TEMod.content.*;
import TEMod.content.Keppler.*;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.ui.dialogs.BaseDialog;

public class ThermalEngineeringCore extends Mod {
    public ThermalEngineeringCore() {
        Events.on(EventType.ClientLoadEvent.class, e->Time.run(10F, () -> {
                BaseDialog dialog = new BaseDialog("Welcome to use ThermalEngineering");
                dialog.cont.add("test");
                dialog.addCloseButton();
                dialog.show();
        }));
    }
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        TEUnitTypes.load();
        KepplerPlanet.load();
        KepplerSectorPresets.load();
        KepplerTechTree.load();
        //end
        Log.info("[Themal-Enginerring] Project loading is completed");
    }
}
