package TEMod.content;

import arc.Core;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;

public class TESpecialContent {
    public static TEContent

    serpluoIcon, erekirIcon, keplerIcon //星球图标
    ;

    public static class TEContent extends UnlockableContent {
        public TEContent(String name) {
            super(name);
            this.localizedName = Core.bundle.get("teContent." + this.name + ".name", this.name);
            this.description = Core.bundle.getOrNull("teContent." + this.name + ".description");
            this.details = Core.bundle.getOrNull("teContent." + this.name + ".details");
            this.unlocked = Core.settings != null && Core.settings.getBool(this.name + "-unlocked", false);
            alwaysUnlocked = true;
            hideDatabase = true;
        }

        @Override
        public ContentType getContentType() {
            return ContentType.error;
        }
    }

    public static void load() {
        serpluoIcon = new TEContent("serpulo-icon");
        erekirIcon = new TEContent("erekir-icon");
        keplerIcon = new TEContent("kepler-icon");
    }
}
