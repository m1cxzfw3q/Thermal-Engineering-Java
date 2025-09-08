package TEMod.TECustom;

public class TETips {//幽默的tips      实际内容在翻译文件中，这只是一个调用的
    public static String tips(int tipId) {
        return switch (tipId) {
            case 1 -> "misc.tip-1";
            case 2 -> "misc.tip-2";
            case 3 -> "misc.tip-3";
            case 4 -> "misc.tip-4";
            case 5 -> "misc.tip-5";
            case 6 -> "misc.tip-6";
            case 7 -> "misc.tip-7";
            case 8 -> "misc.tip-8";
            default -> "ERROR";
        };
    }
}
