package com.carltonwu.flightrail.commands;

import com.carltonwu.flightrail.entities.FlightRailPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.carltonwu.flightrail.listeners.PlayerListener.flightRailPlayers;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage("Console");
            return true;
        } else {
            FlightRailPlayer frPlayer = flightRailPlayers.get(player.getUniqueId().toString());

            if (frPlayer.hasAmmo())
                frPlayer.setAmmo(frPlayer.getAmmo() - 1);
            frPlayer.createFlightRailPlayerScoreboard();
        }

    return true;
    }
}