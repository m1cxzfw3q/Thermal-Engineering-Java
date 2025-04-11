package TEMod;

import TEMod.content.*;
import arc.Events;
import arc.util.Time;
import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.ui.dialogs.BaseDialog;

public class ThermalEngineeringCore extends Mod {
    public ThermalEngineeringCore() {
        Events.on(EventType.ClientLoadEvent.class, e->Time.run(10F,()->{
                BaseDialog dialog = new BaseDialog("Welcome to use ThermalEngineering");
                dialog.cont.add("test");
                Time.run(100F, dialog::addCloseButton);
                dialog.show();
            }));
    }
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        KepplerTechTree.load();
        KepplerPlanet.load();
        TESectorPresets.load();
    }
}
