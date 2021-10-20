package com.carltonwu.flightrail.weapons.type;

import com.carltonwu.flightrail.weapons.Weapon;

public class AWP extends Weapon {
    public AWP() {
        super.setMagazineSize(5);
        super.setMagazine(0);
    }

    public String toString() {
        return "AWP";
    }
}
