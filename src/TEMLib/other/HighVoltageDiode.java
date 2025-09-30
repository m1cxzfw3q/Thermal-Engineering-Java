package TEMLib.other;

import arc.graphics.Color;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;

public class HighVoltageDiode extends Block {
    public float powerProduction = 10f;
    public float voltageThreshold = 100f;

    public HighVoltageDiode(String name) {
        super(name);
        hasPower = true;
        outputsPower = true;
        consumesPower = true;
        insulated = true;
    }

    public class HighVoltageDiodeBuild extends Building {
        public float efficiency = 1f;

        @Override
        public void updateTile() {
            if(power.status > voltageThreshold) {
                efficiency = 0.5f;
            } else {
                efficiency = 0f;
            }
        }

        @Override
        public float getPowerProduction() {
            if(power.status > voltageThreshold) {
                return powerProduction * efficiency;
            }
            return 0f;
        }

        @Override
        public void draw() {
            super.draw();
            if(efficiency > 0) {
                Drawf.light(x, y, 10f, Color.scarlet, 0.8f * efficiency);
            }
        }
    }
}