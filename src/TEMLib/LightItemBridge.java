package TEMLib;

import mindustry.world.blocks.distribution.ItemBridge;

public class LightItemBridge extends ItemBridge {
    public float TimeScale;

    public LightItemBridge(String name) {
        super(name);
    }

    public class LightItemBridgeBuild extends ItemBridgeBuild {
        public LightItemBridgeBuild() {
            timeScale = TimeScale;//神秘
        }
    }
}
