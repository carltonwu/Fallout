package com.carltonwu.flightrail.commands;

import com.carltonwu.flightrail.factory.FlightRailItemFactory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage("Console");
            return true;
        } else {
            player.getInventory().addItem(FlightRailItemFactory.AWP());
            player.getInventory().addItem(FlightRailItemFactory.M4());
            player.getInventory().addItem(FlightRailItemFactory.M97());
            player.getInventory().addItem(FlightRailItemFactory.Topaz());
            ItemStack armor = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemMeta meta = armor.getItemMeta();
            meta.setUnbreakable(true);
            armor.setItemMeta(meta);
            player.getInventory().addItem(armor);

            armor = new ItemStack(Material.ELYTRA, 1);
            meta = armor.getItemMeta();
            meta.setUnbreakable(true);
            armor.setItemMeta(meta);
            player.getInventory().addItem(armor);

            armor = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            meta = armor.getItemMeta();
            meta.setUnbreakable(true);
            armor.setItemMeta(meta);
            player.getInventory().addItem(armor);

            armor = new ItemStack(Material.DIAMOND_BOOTS, 1);
            meta = armor.getItemMeta();
            meta.setUnbreakable(true);
            armor.setItemMeta(meta);
            player.getInventory().addItem(armor);

        }

        return true;
    }
}