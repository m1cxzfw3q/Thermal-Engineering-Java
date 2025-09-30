package TEMLib.other;

import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;

public class BoostDiode extends Block {
    public float highVoltageOutput = 50f; // 高压输出功率
    public float powerRequirement = 100f; // 需要的普通电功率
    public float boostEfficiency = 0.6f;

    public BoostDiode(String name) {
        super(name);
        hasPower = true;
        consumesPower = true;
        insulated = true;
    }

    public class BoostDiodeBuild extends Building {
        public boolean isBoosting = false;

        @Override
        public void updateTile() {
            if(power.status >= powerRequirement) {
                isBoosting = true;
                power.status -= powerRequirement * delta();
            } else {
                isBoosting = false;
            }
        }

        @Override
        public boolean shouldConsume() {
            return power.status >= powerRequirement;
        }

        @Override
        public float getPowerProduction() {
            return isBoosting ? highVoltageOutput * boostEfficiency : 0f;
        }

        @Override
        public void draw() {
            super.draw();

            if(isBoosting) {
                Drawf.light(x, y, 10f, Color.scarlet, 0.9f);
                if(Mathf.chance(0.1)) {
                    Drawf.light(x, y, 12f, Color.scarlet, 1f);
                }
            }
        }

        @Override
        public void onProximityAdded() {
            super.onProximityAdded();
            proximity.forEach(b -> {
                if(b.block.insulated) {
                    b.power.graph.add(this);
                }
            });
        }
    }
}