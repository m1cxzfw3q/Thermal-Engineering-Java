package TEMLib;

import arc.graphics.Color;
import mindustry.type.Item;
import mindustry.world.modules.ItemModule;
import mindustry.world.modules.LiquidModule;

//TODO 实现这个
public class HardDriveItem extends Item {
    public DriveStorageType storageType = DriveStorageType.items;
    public ItemModule items;
    public LiquidModule liquids;

    public HardDriveItem(String name, Color color) {
        super(name, color);
    }

    @Override
    public String toString() {
        return super.toString() + "{storageType= " + storageType.getName() + "}";
    }

    public enum DriveStorageType {
        items("Items"), liquids("Liquids");

        public final String name;
        DriveStorageType(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
