package TEMLib;

import mindustry.world.blocks.payloads.PayloadBlock;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Stat;

public class PayloadLauncher extends PayloadBlock {
    public PayloadLauncher(String name) {
        super(name);

        update = true;
        acceptsPayload = true;
        acceptsUnitPayloads = true;
        group = BlockGroup.payloads;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.itemCapacity);
    }
}