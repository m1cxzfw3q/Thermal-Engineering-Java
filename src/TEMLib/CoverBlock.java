package TEMLib;

import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

import static mindustry.Vars.world;

public class CoverBlock extends Block {
    public static Floor[][] convertFloor;

    public CoverBlock(String name) {
        super(name);
        solid = false;
        update = true;
        floating = true;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        Tile floorTile = world.tile(tile.x, tile.y);
        for (int i = 0; i < convertFloor.length; i++) {//别换增强for 不然秒炸
            if (floorTile.floor() == convertFloor[i][0] && floorTile.floor() != null) {
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
            for (int i = 0; i < convertFloor.length; i++) {//别换增强for 不然秒炸
                if (floorTile.floor() == convertFloor[i][0] && floorTile.floor() != null) {
                    floorTile.setFloor(convertFloor[i][1]);
                    killed();
                }
            }
            killed();//开创世神放的哥们，你最好有事
        }
    }
}
