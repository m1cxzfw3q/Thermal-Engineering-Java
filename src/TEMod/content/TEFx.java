package TEMod.content;

import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;

public class TEFx {
    public static final Effect
    shootMini = new Effect(7, e -> {
        color(Pal.lighterOrange, Pal.lightOrange, e.fin());
        float w = 1f + 5 * e.fout();
        Drawf.tri(e.x, e.y, w, 5f * e.fout(), e.rotation);
        Drawf.tri(e.x, e.y, w, 2f * e.fout(), e.rotation + 180f);
    });
}
