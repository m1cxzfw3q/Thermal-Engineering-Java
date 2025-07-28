package TEMod.TECustom;

import mindustry.Vars;
import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

public class PortableCoreBlock extends CoreBlock {
    public PortableCoreBlock(String name) {
        super(name);
        canBreak(null);
        canPlaceOn(null, Team.get(1), 0);
        buildCostMultiplier = 0.8f;
    }

    public boolean canBreak(Tile tile) {
        return true;
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if (tile == null) {
            return true;
        } else if (Vars.state.isEditor()) {
            return true;
        } else if (!Vars.state.isEditor()) {
            return true;
        } else return true;
    }
}