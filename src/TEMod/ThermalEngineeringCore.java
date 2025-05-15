package TEMod;

import TEMod.content.*;
import TEMod.content.Keppler.*;
import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.ui.dialogs.BaseDialog;

public class ThermalEngineeringCore extends Mod {
    public ThermalEngineeringCore() {
        Events.on(EventType.ClientLoadEvent.class, e-> Time.run(10F, () -> {
            BaseDialog dialog = new BaseDialog("Welcome to use ThermalEngineering");
            dialog.cont.image(Core.atlas.find("ModLogo")).pad(20f).row();
            dialog.cont.add("test").row();
            dialog.addCloseButton();
            dialog.show();
        }));
    }
    @Override
    public void loadContent() {
        TEItems.load();
        TEBlocks.load();
        KepplerPlanet.load();
        KepplerSectorPresets.load();
        TEStatusEffects.load();
        //TEUnitTypes.load();
        //TechTree
        KepplerTechTree.load();
        //end
        Log.info("[Thermal-Engineering] Project loading is completed");
    }
}
