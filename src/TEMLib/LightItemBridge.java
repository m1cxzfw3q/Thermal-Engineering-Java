package TEMLib;

import mindustry.world.blocks.distribution.ItemBridge;

public class LightItemBridge extends ItemBridge {
    public LightItemBridge(String name) {
        super(name);
    }
    public class LightItemBridgeBuild extends ItemBridgeBuild {
        public void doDump() {
            for (int i = 0; i + 2 > 1; i++) {
                super.doDump();
            }
        }
    }
}
