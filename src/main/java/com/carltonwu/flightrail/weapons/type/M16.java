package com.carltonwu.flightrail.weapons.type;

import com.carltonwu.flightrail.weapons.Weapon;

public class M16 extends Weapon {

    public M16() {
        super.setMagazineSize(30);
        super.setMagazine(0);
    }

    public String toString() {
        return "M-16";
    }

}
