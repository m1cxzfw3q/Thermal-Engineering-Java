package TEMod.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.trnsx;
import static arc.math.Angles.trnsy;

public class TEFx {
    public static final Effect
            shootMini = new Effect(7, e -> {
                color(Pal.lighterOrange, Pal.lightOrange, e.fin());
                float w = 1f + 5 * e.fout();
                Drawf.tri(e.x, e.y, w, 7f * e.fout(), e.rotation);
                Drawf.tri(e.x, e.y, w, 2.4f * e.fout(), e.rotation + 180f);
            }),

            casingMini = new Effect(24f, e -> {
                color(Pal.lightOrange, Color.lightGray, Pal.lightishGray, e.fin());
                alpha(e.fout(0.1f));
                float rot = Math.abs(e.rotation) + 90f;
                int i = -Mathf.sign(e.rotation);

                float len = (2f + e.finpow() * 2f) * i;
                float lr = rot + e.fin() * 10f * i;
                Fill.rect(
                        e.x + trnsx(lr, len) + Mathf.randomSeedRange(e.id + i + 7, 3f * e.fin()),
                        e.y + trnsy(lr, len) + Mathf.randomSeedRange(e.id + i + 8, 3f * e.fin()),
                        1.5f, 0.6f, rot + e.fin() * 50f * i
                );
            }).layer(Layer.bullet);
}
