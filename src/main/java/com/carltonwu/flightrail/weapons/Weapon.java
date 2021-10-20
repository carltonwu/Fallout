package com.carltonwu.flightrail.weapons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Weapon {

    private int magazineSize = 0;
    private int magazine = 0;

    public Weapon() {
    }

    public int getMagazine() {
        return magazine;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazine(int num) {
        magazine = num;
    }

    public void setMagazineSize(int num) {
        magazineSize = num;
    }

    public boolean hasAmmo() {
        if (magazine > 0)
            return true;
        else
            return false;
    }

    public void decrementMagazine() {
        magazine--;
    }

    public void reload(Player player, int count) {
        magazine = count;
        player.getInventory().removeItem(new ItemStack(Material.ARROW, count - 1));
    }

    public boolean canReload(Player player) {
        if (player.getInventory().contains(Material.ARROW))
            return true;
        else
            return false;
    }

    public String toString() {
        return "weap";
    }
}
