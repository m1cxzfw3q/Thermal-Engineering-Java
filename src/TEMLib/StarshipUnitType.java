package TEMLib;

import arc.math.geom.Rect;
import arc.scene.ui.layout.Table;
import mindustry.gen.*;
import mindustry.type.UnitType;

public class StarshipUnitType extends UnitType {
    public float sizeX;
    public float sizeY;

    public StarshipUnitType(String name) {
        super(name);

        constructor = StarshipUnitEntity::new;
    }

    @Override
    public void display(Unit unit, Table table) {
        super.display(unit, table);


    }

    public class StarshipUnitEntity extends UnitEntity {
        @Override
        public void hitbox(Rect rect) {
            rect.setCentered(x, y, sizeX != -1 ? sizeX : hitSize, sizeY != -1 ? sizeY : hitSize);
        }

        @Override
        public void hitboxTile(Rect rect) {
            rect.setCentered(x, y, Math.min(sizeX != -1 ? sizeX : hitSize * 0.66f, 7.8f), Math.min(sizeY != -1 ? sizeY : hitSize * 0.66f, 7.8f));
        }
    }
}
