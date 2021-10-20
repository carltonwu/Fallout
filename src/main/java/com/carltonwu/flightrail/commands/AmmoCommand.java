package com.carltonwu.flightrail.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.carltonwu.flightrail.listeners.PlayerListener.flightRailPlayers;

public class AmmoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage("Console");
            return true;
        } else {
            player.sendMessage(flightRailPlayers.get(player.getUniqueId().toString()).getAmmo() + "/" + flightRailPlayers.get(player.getUniqueId().toString()).getMaxAmmo());
        }

        return true;
    }
}
