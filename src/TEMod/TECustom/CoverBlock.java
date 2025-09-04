package TEMod.TECustom;

import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.Tile;

public class CoverBlock extends Block {
    public static Block[] placeFloor;

    public CoverBlock(String name) {
        super(name);
        canPlaceOn(null, Team.get(1), 1);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return true;
    }
}
