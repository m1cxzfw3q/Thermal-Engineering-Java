package TEMLib;

import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import mindustry.entities.bullet.BulletType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;
import org.jetbrains.annotations.NotNull;

public class PayloadLauncher extends Turret {//awa
    public @NotNull ObjectMap<Block, BulletType> ammoTypes = new OrderedMap<>();

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
        stats.add(Stat.ammo, StatValues.ammo(ammoTypes));
    }
}
