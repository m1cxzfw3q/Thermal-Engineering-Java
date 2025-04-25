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
    }

    public boolean canBreak(Tile tile) {
        return !Vars.state.isEditor();
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if (tile == null) {
            return false;
        } else if (Vars.state.isEditor()) {
            return true;
        } else if (!Vars.state.isEditor()) {
            return true;
        } else {
            CoreBuild core = team.core();
            tile.getLinkedTilesAs(this, tempTiles);
            if (!tempTiles.contains((o) -> !o.floor().allowCorePlacement || o.block() instanceof CoreBlock)) {
                return true;
            } else if (core != null && (Vars.state.rules.infiniteResources || core.items.has(requirements, Vars.state.rules.buildCostMultiplier))) {
                return tile.block() instanceof CoreBlock && size > tile.block().size && (!requiresCoreZone || tempTiles.allMatch((o) -> o.floor().allowCorePlacement));
            } else {
                return false;
            }
        }
    }
}