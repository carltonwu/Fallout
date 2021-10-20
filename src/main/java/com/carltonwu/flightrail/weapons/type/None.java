package com.carltonwu.flightrail.weapons.type;

import com.carltonwu.flightrail.weapons.Weapon;

public class None extends Weapon {
    public None() {
        super.setMagazineSize(0);
        super.setMagazine(0);
    }

    public String toString() {
        return "None";
    }
}
