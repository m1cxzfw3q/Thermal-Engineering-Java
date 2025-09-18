package TEMLib.ModularWeapon;

import arc.struct.ObjectMap;

public class WeaponTYPE {
    public static WeaponTYPE[] all = {};
    public static ObjectMap<String, WeaponTYPE> map = new ObjectMap<>();

    public static final WeaponTYPE
            SMALL = add("SMALL", 1),
            DEFAULT = add("DEFAULT", 2),
            LARGE = add("LARGE", 3),
            HUGE = add("HUGE", 4);

    public final int id;
    public final String name;
    public final int tier;

    public WeaponTYPE(int id, String name, int tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    @Override
    public String toString(){
        return name;
    }

    public static WeaponTYPE get(String name){
        return map.getThrow(name, () -> new IllegalArgumentException("Unknown WeaponTYPE type: " + name));
    }

    public static WeaponTYPE add(String name, int tier) {
        WeaponTYPE a = new WeaponTYPE(all.length, name, tier);
        WeaponTYPE[] prev = all;
        all = new WeaponTYPE[all.length + 1];
        System.arraycopy(prev, 0, all, 0, a.id);
        all[a.id] = a;
        map.put(name, a);
        return a;
    }
}
