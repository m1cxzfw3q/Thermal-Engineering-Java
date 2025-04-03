package TEMod;

import arc.Events;
import arc.util.Time;
import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.ui.dialogs.BaseDialog;

import TEMod.content.Items;
import TEMod.content.Blocks;

public class ThermalEngineeringCore extends Mod {
    public ThermalEngineeringCore() {
        Events.on(EventType.ClientLoadEvent.class, e->{
            Time.run(10F,()->{
                BaseDialog dialog = new BaseDialog("TEMod test");
                dialog.cont.add("test text");
                Time.run(100F, dialog::addCloseButton);
                dialog.show();
            });
        });
    }
    @Override
    public void loadContent() {
        Items.load();
        Blocks.load();
    }
}
