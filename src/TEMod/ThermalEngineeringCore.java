package TEMod;

import arc.Events;
import arc.util.Time;
import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.ui.dialogs.BaseDialog;

import TEMod.content.TEItems;
import TEMod.content.TEBlocks;

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
    }
}
