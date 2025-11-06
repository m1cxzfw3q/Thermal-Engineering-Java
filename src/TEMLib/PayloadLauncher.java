package TEMLib;

import mindustry.world.blocks.defense.turrets.PowerTurret;

public class PayloadLauncher extends PowerTurret {
    public PayloadLauncher(String name) {
        super(name);

        acceptsPayload = true;
    }


}
