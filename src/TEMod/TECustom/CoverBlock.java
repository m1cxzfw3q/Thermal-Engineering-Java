package TEMod.TECustom;

import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

import static mindustry.Vars.world;

public class CoverBlock extends Block {
    public static Floor[] replacementFloor, requireFloor;

    public CoverBlock(String name) {
        super(name);
        solid = false;
        update = true;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        Tile floorTile = world.tile(tile.x, tile.y);
        for (int i = 0; i < requireFloor.length; i++) {//别换增强for 不然秒炸
            if (floorTile.floor() == requireFloor[i] && floorTile.floor() != null) {
                return true;
            }
        }
        return false;
    }

    public class CoverBlockBuild extends Building {
        @Override
        public void updateTile() {
            super.updateTile();
            Tile floorTile = world.tile(tile.x, tile.y);
            for (int i = 0; i < requireFloor.length; i++) {//别换增强for 不然秒炸
                if (floorTile.floor() == requireFloor[i] && floorTile.floor() != null) {
                    floorTile.setFloor(replacementFloor[i]);
                    kill();
                }
            }
        }
    }
}
