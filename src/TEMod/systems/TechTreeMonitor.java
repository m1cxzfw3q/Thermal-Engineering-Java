package TEMod.systems;

import arc.Events;
import mindustry.game.EventType;

public class TechTreeMonitor {
    public static void init() {
        // 监听研究完成事件
        Events.on(EventType.ResearchEvent.class, event -> {
            // 研究完成后更新所有可见性
            TechTreeExtension.updateAllBlocksVisibility();
            TechTreeExtension.updateAllItemsVisibility();
            TechTreeExtension.updateAllLiquidsVisibility();
        });

        // 监听世界加载事件
        Events.on(EventType.WorldLoadEvent.class, event -> {
            // 世界加载后更新所有可见性
            TechTreeExtension.updateAllBlocksVisibility();
            TechTreeExtension.updateAllItemsVisibility();
            TechTreeExtension.updateAllLiquidsVisibility();
        });
    }
}