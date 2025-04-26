package TEMod.content;

import arc.util.Log;
import mindustry.content.Fx;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

public class TEStatusEffects {
    public static StatusEffect captured; //未知
    public static void load() {
        captured = new StatusEffect("unit-launcher-captured"){{
            // 禁用移动和攻击
            speedMultiplier = 0f;
            damageMultiplier = 0f;
            disarm = true;
            // 可视化效果
            effect = Fx.regenSuppressSeek;
            color = Pal.lancerLaser;
        }};

        //end
        Log.info("[Thermal-Engineering] Loading 'TEStatusEffects'");
    }
}
