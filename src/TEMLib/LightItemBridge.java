package TEMLib;

import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.world.blocks.distribution.ItemBridge;

public class LightItemBridge extends ItemBridge {
    public LightItemBridge(String name) {
        super(name);
    }
    public class LightItemBridgeBuild extends ItemBridgeBuild {
        public void updateTransport(Building other){
            transportCounter += edelta();
            while (transportCounter >= transportTime) {
                Item item = items.take();
                if (item != null && other.acceptItem(this, item)) {
                    other.handleItem(this, item);
                    moved = true;
                } else if(item != null) {
                    items.add(item, 1);
                    items.undoFlow(item);
                }
                output(other);
                transportCounter -= transportTime;
            }
        }
        public void output(Building other) {
            Item item = items.take();
            if (item != null && other.acceptItem(this, item)) {
                other.handleItem(this, item);
                moved = true;
            } else if(item != null) {
                items.add(item, 1);
                items.undoFlow(item);
            }
        }
    }
}
