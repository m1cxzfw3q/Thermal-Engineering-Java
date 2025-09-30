package TEMLib.other;

import arc.struct.*;
import arc.util.io.*;
import mindustry.world.blocks.power.*;
import mindustry.world.modules.BlockModule;

public class HighVoltagePowerModule extends BlockModule {
    public float status = 0.0f;
    public boolean init;
    public PowerGraph graph = new PowerGraph();
    public IntSeq links = new IntSeq();

    @Override
    public void write(Writes write){
        write.s(links.size);
        for(int i = 0; i < links.size; i++){
            write.i(links.get(i));
        }
        write.f(status);
    }

    @Override
    public void read(Reads read){
        links.clear();
        short amount = read.s();
        for(int i = 0; i < amount; i++){
            links.add(read.i());
        }
        status = read.f();
        if(Float.isNaN(status) || Float.isInfinite(status)) status = 0f;
    }
}
