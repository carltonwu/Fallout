package com.carltonwu.flightrail.listeners;

import com.carltonwu.flightrail.FlightRail;
import com.carltonwu.flightrail.entities.FlightRailPlayer;
import com.carltonwu.flightrail.factory.FlightRailItemFactory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;


public class PlayerListener implements Listener {

    public static HashMap<String, FlightRailPlayer> flightRailPlayers = new HashMap<String, FlightRailPlayer>();
    public static Random rand = new Random();

    @EventHandler(priority = EventPriority.NORMAL)
    public void useItemListener(PlayerInteractEvent event) {

        if (event.hasItem() && event.getItem().getType().equals(Material.CROSSBOW)) {
            Player player = event.getPlayer();
            FlightRailPlayer frPlayer = flightRailPlayers.get(player.getUniqueId().toString());
            ItemStack item = frPlayer.getCurrentItem();
            CrossbowMeta meta = (CrossbowMeta) item.getItemMeta();
            frPlayer.setCurrentItem(player.getInventory().getItemInMainHand());
            NamespacedKey timer = new NamespacedKey(FlightRail.instance, "timer");

            if (event.getAction().equals(Action.LEFT_CLICK_AIR) && frPlayer.hasAmmo() ||
            event.getAction().equals(Action.LEFT_CLICK_BLOCK) && frPlayer.hasAmmo()) {
                meta.setChargedProjectiles(null);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                player.getInventory().addItem(new ItemStack(Material.ARROW, frPlayer.getAmmo()));
                frPlayer.setAmmo(0);
                frPlayer.createFlightRailPlayerScoreboard();
            } else if (frPlayer.hasAmmo() && item.getItemMeta().getLore().equals(FlightRailItemFactory.AWP().getItemMeta().getLore()) && frPlayer.getTimer() <= 0) {
                frPlayer.setAmmo(frPlayer.getAmmo() - 1);
                frPlayer.setTimer(frPlayer.getMaxTimer());
                frPlayer.createFlightRailPlayerScoreboard();
                meta = (CrossbowMeta) item.getItemMeta();
                meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                Location location = player.getLocation();
                player.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 2, 2);
                player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 2, 2);
                player.playSound(location, Sound.BLOCK_STONE_BREAK, 2, 2);
                player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 2, 2);
                player.playSound(location, Sound.ENTITY_GHAST_SHOOT, 2, 2);

            } else if (frPlayer.hasAmmo() && item.getItemMeta().getLore().equals(FlightRailItemFactory.M97().getItemMeta().getLore()) && frPlayer.getTimer() <= 0) {
                frPlayer.setAmmo(frPlayer.getAmmo() - 1);
                frPlayer.setTimer(frPlayer.getMaxTimer());
                meta = (CrossbowMeta) item.getItemMeta();
                frPlayer.createFlightRailPlayerScoreboard();
                Location location = player.getLocation();
                location.add(0, 1.5, 0);
                meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                player.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 2, 2);
                for (int i = 0; i < 8; i++) {
                    Arrow arrow = player.getWorld().spawnArrow(location, location.getDirection(), 5, 2);
                    arrow.setShooter(player);
                    Vector vec = player.getLocation().getDirection();
                    arrow.setVelocity(new Vector(vec.getX(), vec.getY(), vec.getZ()).add(new Vector(rand.nextDouble() * .1 - .05, rand.nextDouble() * .1 - .05, rand.nextDouble() * .1 - .05)));
                    arrow.setVelocity(arrow.getVelocity().multiply(5.5));
                    player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 2, 2);
                    player.playSound(location, Sound.ENTITY_BLAZE_HURT, 2, 2);
                    player.playSound(location, Sound.BLOCK_STONE_BREAK, 2, 2);
                    player.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 2, 2);
                    player.playSound(location, Sound.ENTITY_GHAST_SHOOT, 2, 2);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void shootArrowListener(EntityShootBowEvent event) {

        //=============================================================================================================
        // Takes the arrow entity and changes it's velocity.
        //=============================================================================================================

        Entity entity = event.getEntity();
        Float speed = event.getForce();
        Entity arrow = event.getProjectile();

        if (entity.getType().equals(EntityType.PLAYER)) {
            Player player = (Player) entity;
            Vector vec = player.getLocation().getDirection();
            if (event.getBow().getItemMeta().getLore().equals(FlightRailItemFactory.AWP().getItemMeta().getLore())) {
                arrow.setVelocity(new Vector(vec.getX() * speed * 50, vec.getY() * speed * 50, vec.getZ() * speed * 50));
                arrow.setGravity(false);
            } else
                arrow.setVelocity(new Vector(vec.getX() * speed * 6.5, vec.getY() * speed * 6, vec.getZ() * speed * 6.5));

        }
        //=============================================================================================================
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void playerJoinEvent(PlayerJoinEvent event) {

        FlightRailPlayer frPlayer = new FlightRailPlayer(event.getPlayer());
        flightRailPlayers.put(event.getPlayer().getUniqueId().toString(), frPlayer);
        frPlayer.createFlightRailPlayerScoreboard();

    }

    /*
    @EventHandler(priority = EventPriority.NORMAL)
    public void onProjectileLaunch(ThrownPotion event) {
        Vector vec = event.getLocation().getDirection();
        event.setVelocity(new Vector(vec.getX() * 5, vec.getY()  * 4.5, vec.getZ() * 5));
    }
    */

    @EventHandler(priority = EventPriority.NORMAL)
    public void onArrowHit(ProjectileHitEvent event) {
        event.getEntity().remove();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemSwitch(PlayerItemHeldEvent event) {

        if (flightRailPlayers.get(event.getPlayer().getUniqueId().toString()) != null) {
            Inventory inv = event.getPlayer().getInventory();

            int slotId = event.getNewSlot();
            if (slotId >= 0 && slotId < inv.getSize()) {
                ItemStack stack = inv.getItem(slotId);

                flightRailPlayers.get(event.getPlayer().getUniqueId().toString()).setCurrentItem(stack);
            }
            flightRailPlayers.get(event.getPlayer().getUniqueId().toString()).createFlightRailPlayerScoreboard();
        }

    }
}
