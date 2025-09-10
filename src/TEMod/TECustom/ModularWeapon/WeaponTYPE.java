package TEMod.TECustom.ModularWeapon;

import arc.struct.ObjectMap;

public class WeaponTYPE {
    public static WeaponTYPE[] all = {};
    public static ObjectMap<String, WeaponTYPE> map = new ObjectMap<>();

    public static final WeaponTYPE
            ANY = add("ANY");

    public final int id;
    public final String name;

    public WeaponTYPE(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public static WeaponTYPE get(String name){
        return map.getThrow(name, () -> new IllegalArgumentException("Unknown WeaponTYPE type: " + name));
    }

    public static WeaponTYPE add(String name) {
        WeaponTYPE a = new WeaponTYPE(all.length, name);
        WeaponTYPE[] prev = all;
        all = new WeaponTYPE[all.length + 1];
        System.arraycopy(prev, 0, all, 0, a.id);
        all[a.id] = a;
        map.put(name, a);
        return a;
    }
}
