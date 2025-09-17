package TEMod.TECustom;

import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.Vars.state;

public class PortableCoreBlock extends CoreBlock {
    public int coreCap;

    public PortableCoreBlock(String name) {
        super(name);
        buildCostMultiplier = 0.8f;
    }

    public boolean canBreak(Tile tile) {
        return true;
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return state.teams.cores(tile.team()).size <= coreCap;
    }
}