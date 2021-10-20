package com.carltonwu.flightrail.commands;

import com.carltonwu.flightrail.factory.FlightRailItemFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class M4Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage("Console");
            return true;
        } else {
            player.getInventory().addItem(FlightRailItemFactory.M4());
        }

        return true;
    }
}
