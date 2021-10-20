package com.carltonwu.flightrail.display;

import com.carltonwu.flightrail.entities.FlightRailPlayer;
import com.carltonwu.flightrail.factory.FlightRailItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.logging.Level;

import static com.carltonwu.flightrail.listeners.PlayerListener.flightRailPlayers;

public class PlayerScoreboardDisplay {

    private Player player;

    public PlayerScoreboardDisplay(Player player) {
        this.player = player;
    }

    public void createBoard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("FalloutDisp1", "dummy", "Player Information");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        FlightRailPlayer frPlayer = flightRailPlayers.get(player.getUniqueId().toString());

        if (frPlayer != null) {
            Score line1 = obj.getScore(ChatColor.DARK_PURPLE + "-=-=-=-=-=-=-=-=-=-");
            line1.setScore(3);
            if (frPlayer.getCurrentItem() != null && frPlayer.getCurrentItem().getItemMeta().getLore() != null) {
                if (frPlayer.getCurrentItem().getItemMeta().getLore().equals(FlightRailItemFactory.M4().getItemMeta().getLore()) ||
                        frPlayer.getCurrentItem().getItemMeta().getLore().equals(FlightRailItemFactory.AWP().getItemMeta().getLore()) ||
                        frPlayer.getCurrentItem().getItemMeta().getLore().equals(FlightRailItemFactory.M97().getItemMeta().getLore()) ||
                        frPlayer.getCurrentItem().getItemMeta().getLore().equals(FlightRailItemFactory.Topaz().getItemMeta().getLore())
                ) {
                    Score line2 = obj.getScore(ChatColor.GOLD + "Current Weapon: " + ChatColor.AQUA + frPlayer.getCurrentItem().getItemMeta().getDisplayName());
                    line2.setScore(2);
                    Score line3 = obj.getScore(ChatColor.GOLD + "Magazine: " + ChatColor.AQUA + frPlayer.getAmmo() + "/" + frPlayer.getMaxAmmo());
                    line3.setScore(1);
                } else {
                    Score line2 = obj.getScore(ChatColor.GOLD + "Current Weapon:" + ChatColor.RED + "" + ChatColor.BOLD + " N/A");
                    line2.setScore(2);
                    Score line3 = obj.getScore(ChatColor.GOLD + "Magazine: " + ChatColor.AQUA + "N/A");
                    line3.setScore(1);
                }
            } else {
                Score line2 = obj.getScore(ChatColor.GOLD + "Current Weapon:" + ChatColor.RED + "" + ChatColor.BOLD + " N/A");
                line2.setScore(2);
                Score line3 = obj.getScore(ChatColor.GOLD + "Magazine: " + ChatColor.AQUA + "N/A");
                line3.setScore(1);
                Bukkit.getLogger().log(Level.INFO, "No weapon");
            }

            Score line4 = obj.getScore(ChatColor.DARK_PURPLE + "=-=-=-=-=-=-=-=-=-=");
            line4.setScore(0);

            player.setScoreboard(board);
        }
    }

}
