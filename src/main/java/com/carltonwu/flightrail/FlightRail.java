package com.carltonwu.flightrail;

import com.carltonwu.flightrail.commands.*;
import com.carltonwu.flightrail.entities.FlightRailPlayer;
import com.carltonwu.flightrail.factory.FlightRailItemFactory;
import com.carltonwu.flightrail.listeners.PlayerListener;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.logging.Level;


public class FlightRail extends JavaPlugin {

    public static FlightRail instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().log(Level.INFO, "FlightRail starting.");

        instance = this;

        //Listener
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        //Commands
        this.getCommand("m4").setExecutor(new M4Command());
        this.getCommand("kit").setExecutor(new KitCommand());
        this.getCommand("awp").setExecutor(new AwpCommand());
        this.getCommand("m97").setExecutor(new M97Command());
        this.getCommand("topaz").setExecutor(new TopazCommand());
        this.getCommand("ammo").setExecutor(new AmmoCommand());
        //this.getCommand("test").setExecutor(new TestCommand());

        //Sets up flightRailPlayers if server is reloaded
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            FlightRailPlayer frPlayer = new FlightRailPlayer(player);
            PlayerListener.flightRailPlayers.put(player.getUniqueId().toString(), frPlayer);
            frPlayer.createFlightRailPlayerScoreboard();
        }

        //Checks if a crossbow is ready to be reloaded
        Bukkit.getScheduler().runTaskTimer(this, new Runnable(){

            @Override
            public void run() {


                for(Player player : Bukkit.getServer().getOnlinePlayers()) {

                    FlightRailPlayer frPlayer = PlayerListener.flightRailPlayers.get(player.getUniqueId().toString());
                    if (frPlayer != null) {
                        if (frPlayer.getCurrentItem() != null) {
                            ItemStack item = frPlayer.getCurrentItem();

                            if (item.getItemMeta().getLore() != null) {
                                if (item.getItemMeta().getLore().equals(FlightRailItemFactory.M4().getItemMeta().getLore()) || item.getItemMeta().getLore().equals(FlightRailItemFactory.AWP().getItemMeta().getLore()) || item.getItemMeta().getLore().equals(FlightRailItemFactory.M97().getItemMeta().getLore()) || item.getItemMeta().getLore().equals(FlightRailItemFactory.Topaz().getItemMeta().getLore())) {
                                    CrossbowMeta meta = (CrossbowMeta) item.getItemMeta();

                                    if (frPlayer.hasAmmo() && !meta.hasChargedProjectiles() && item.getItemMeta().getLore().equals(FlightRailItemFactory.M4().getItemMeta().getLore())) {
                                        frPlayer.setAmmo(frPlayer.getAmmo() - 1);
                                        Location location = player.getLocation();
                                        player.playSound(location, Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 2);
                                        player.playSound(location, Sound.BLOCK_STONE_BREAK, 2, 2);
                                        player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST_FAR, 1, 2);
                                        player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 2);
                                        player.playSound(location, Sound.ENTITY_EGG_THROW, 1, 2);

                                        if (frPlayer.getAmmo() != 0) {
                                            meta = (CrossbowMeta) item.getItemMeta();
                                            meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
                                            player.getInventory().getItemInMainHand().setItemMeta(meta);
                                        }
                                        frPlayer.createFlightRailPlayerScoreboard();
                                    } else if (frPlayer.hasAmmo() && !meta.hasChargedProjectiles() && item.getItemMeta().getLore().equals(FlightRailItemFactory.Topaz().getItemMeta().getLore())) {
                                        frPlayer.setAmmo(frPlayer.getAmmo() - 1);
                                        Location location = player.getLocation();
                                        location.add(0, 1.5, 0);
                                        if (frPlayer.getAmmo() != 0) {
                                            meta = (CrossbowMeta) item.getItemMeta();
                                            meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
                                            player.getInventory().getItemInMainHand().setItemMeta(meta);
                                        }
                                        player.playSound(location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 2, 2);

                                        for (int i = 0; i < 8; i++) {
                                            player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 2, 2);
                                            player.playSound(location, Sound.ENTITY_BLAZE_HURT, 2, 2);
                                            player.playSound(location, Sound.BLOCK_STONE_BREAK, 2, 2);
                                            player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 2, 2);
                                            player.playSound(location, Sound.ENTITY_GHAST_SHOOT, 2, 2);
                                            Arrow arrow = player.getWorld().spawnArrow(location, location.getDirection(), 2, 1);
                                            arrow.setShooter(player);
                                            Vector vec = player.getLocation().getDirection();
                                            arrow.setVelocity( new Vector (vec.getX(), vec.getY(),vec.getZ()).add(new Vector (PlayerListener.rand.nextDouble() *.05 - .025, PlayerListener.rand.nextDouble() *.05 - .025, PlayerListener.rand.nextDouble() *.05 - .025)));
                                            arrow.setVelocity(arrow.getVelocity().multiply(6.5));
                                            player.playSound(location, Sound.ENTITY_ARROW_SHOOT, 2, 2);
                                        }
                                        frPlayer.createFlightRailPlayerScoreboard();
                                    } else if (!frPlayer.hasAmmo() && meta.hasChargedProjectiles() && frPlayer.canReload()) {
                                            int count = 0;
                                            for (ItemStack stack : player.getInventory().getContents()) {
                                                if (stack != null && stack.getType() == Material.ARROW)
                                                    count += stack.getAmount();
                                            }
                                            if (count >= frPlayer.getMaxAmmo() - 1)
                                                frPlayer.reload(frPlayer.getMaxAmmo());
                                            else
                                                frPlayer.reload(count + 1);
                                            frPlayer.createFlightRailPlayerScoreboard();
                                        }

                                        if (item.getItemMeta().getLore().equals(FlightRailItemFactory.AWP().getItemMeta().getLore()) || item.getItemMeta().getLore().equals(FlightRailItemFactory.M97().getItemMeta().getLore())) {
                                            if (frPlayer.getTimer() > 0) {
                                                frPlayer.setTimer(frPlayer.getTimer() - 2);
                                            }


                                        }
                                }
                            }
                        }
                    }
                }
            }

        }, 0, 2L);

        Bukkit.getLogger().log(Level.INFO, "Fallout finished starting.");
    }


    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "Fallout stopping.");
    }

}
