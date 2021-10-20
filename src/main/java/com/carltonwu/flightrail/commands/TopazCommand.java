package com.carltonwu.flightrail.commands;

import com.carltonwu.flightrail.factory.FlightRailItemFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopazCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage("Console");
            return true;
        } else if (player.getUniqueId().toString().equals("099726ce-ca46-4fd7-b8ab-10557a6759d1")) {
            player.getInventory().addItem(FlightRailItemFactory.Topaz());
        }

        return true;
    }
}