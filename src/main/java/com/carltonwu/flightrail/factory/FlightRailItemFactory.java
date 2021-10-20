package com.carltonwu.flightrail.factory;

import com.carltonwu.flightrail.FlightRail;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class FlightRailItemFactory {

    private static NamespacedKey ammo = new NamespacedKey(FlightRail.instance, "ammo");
    private static NamespacedKey maxAmmo = new NamespacedKey(FlightRail.instance, "maxAmmo");
    private static NamespacedKey timer = new NamespacedKey(FlightRail.instance, "timer");
    private static NamespacedKey maxTimer = new NamespacedKey(FlightRail.instance, "maxTimer");

    public static ItemStack M4() {

        ItemStack M4 = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = M4.getItemMeta();
        meta.getPersistentDataContainer().set(ammo, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxAmmo, PersistentDataType.INTEGER, 30);
        meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.GRAY + "Semi Automatic Rifle"));
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "M-4");
        meta.setUnbreakable(true);

        M4.setItemMeta(meta);
        return M4;
    }

    public static ItemStack AWP() {

        ItemStack AWP = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = AWP.getItemMeta();
        meta.getPersistentDataContainer().set(ammo, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxAmmo, PersistentDataType.INTEGER, 5);
        meta.getPersistentDataContainer().set(timer, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxTimer, PersistentDataType.INTEGER, 15);
        meta.setLore(Arrays.asList("Sniper Rifle"));
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "AWP");
        meta.setUnbreakable(true);

        AWP.setItemMeta(meta);
        return AWP;
    }

    public static ItemStack M97() {

        ItemStack M97 = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = M97.getItemMeta();
        meta.getPersistentDataContainer().set(ammo, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxAmmo, PersistentDataType.INTEGER, 5);
        meta.getPersistentDataContainer().set(timer, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxTimer, PersistentDataType.INTEGER, 5);
        meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.GRAY + "Pump-action Shotgun"));
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "M97");
        meta.setUnbreakable(true);

        M97.setItemMeta(meta);
        return M97;

    }

    public static ItemStack giveSaiga12() {

        ItemStack M97 = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = M97.getItemMeta();
        NamespacedKey ammo = new NamespacedKey(FlightRail.instance, "ammo");
        NamespacedKey maxAmmo = new NamespacedKey(FlightRail.instance, "maxAmmo");
        meta.getPersistentDataContainer().set(ammo, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxAmmo, PersistentDataType.INTEGER, 8);
        meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.GRAY + "Pump-action Shotgun"));
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "M97");
        meta.setUnbreakable(true);

        M97.setItemMeta(meta);
        return M97;

    }

    public static ItemStack Topaz() {

        ItemStack Topaz = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = Topaz.getItemMeta();
        NamespacedKey ammo = new NamespacedKey(FlightRail.instance, "ammo");
        NamespacedKey maxAmmo = new NamespacedKey(FlightRail.instance, "maxAmmo");
        meta.getPersistentDataContainer().set(ammo, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(maxAmmo, PersistentDataType.INTEGER, 8);
        meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.GRAY + "OP"));
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Toz");
        meta.setUnbreakable(true);

        Topaz.setItemMeta(meta);
        return Topaz;

    }
}
