package net.downwithdestruction.mobhats.listeners;

import net.downwithdestruction.mobhats.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by madmac on 10/18/15.
 *
 * FIXME: Mob Hats get damaged when in block
 * FIXME: Player does not get damaged every time by other entities with Mob Hat attached
 * FIXME: Mobhat detaches when player goes into water
 *
 * TODO: Check for Pig Saddle
 *
 */
public class PlayerListener implements Listener {
    private Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByHat(EntityDamageByEntityEvent e){
        Entity attacker = e.getDamager();
        Entity entity = e.getEntity();

        /* Inside instanceof Player statement
        // Check if other entity attackers OR Shooters a player's Mob Hat
        if(!(entity.getVehicle() == null) && entity.getVehicle().getType().equals(EntityType.PIG)){
            e.setCancelled(true);
            return; // Other entity attacked/shot passenger (Mob Hat)
        }

        // Check if player attacks own Mob Hat
        if(!(attacker.getPassenger() == null) && attacker.getPassenger().getPassenger() == entity){
            e.setCancelled(true);
            return; // Player attacked passenger (Mob Hat)
        }
        */

        // Check for non-player attacks
        if (!(attacker instanceof Player)) {
            // check if attacker is the hat entity.
            if (attacker.getVehicle() != null && attacker.getVehicle().getType().equals(EntityType.PIG)) {
                e.setCancelled(true);
                return; // stop hats from harming any entity
            }

            if (!(entity instanceof Player)) {
            /* Stops Hostile Mobs from destroying your Mob Hat */
                if (!(entity.getVehicle() == null) && entity.getVehicle().getType().equals(EntityType.PIG)) {
                    e.setCancelled(true);
                    return; // Other entity attacked/shot passenger (Mob Hat)
                }
            }
        }

        // Check for the shootings
        if(attacker instanceof Projectile){
            Projectile projectile = (Projectile) attacker;
            Entity shooter = (Entity) projectile.getShooter();

            // Check if player shoots his own Mob Hat
            if (!(shooter.getPassenger() == null) && shooter.getPassenger().getPassenger() == entity) {
                e.setCancelled(true);
                return; // Player shot passenger (Mob Hat), canceled
            }
        }

        // Check for player attacks
        if (attacker instanceof Player) {
            Player player = (Player) attacker;

            if (!(player.getPassenger() == null) && player.getPassenger().getType().equals(EntityType.PIG)) {
                e.setCancelled(true);
                return; // Player attacked invisible pig Vehicle
            }

            if (!(player.getPassenger() == null) && player.getPassenger().getPassenger() == entity) {
                e.setCancelled(true);
                // Player attacked passenger (Mob Hat)
            }
        }
    }

    /*
     * Cancel mobhat damage (prevents mobhat from getting hurt/damaged)
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onMobHatDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if (entity == null) {
            return; // Billy's sanity check
        }

        Entity vehicle = entity.getVehicle();

        if (vehicle == null) {
            return; // Not a mobhat!
        }

        if (!(vehicle.getType().equals(EntityType.PIG))) {
            return; // Entity not on a pig
        }

        Entity possiblePlayer = vehicle.getVehicle();

        if (possiblePlayer == null) {
            return; // Pig not on anything
        }

        if (!(possiblePlayer instanceof Player)) {
            return; // Pig not on a player!
        }

        e.setCancelled(true); // This is a mobhat. cancel damage!
    }

    /*
     * Cancel invisible pg damage (prevents mobhat from detaching from player and running away)
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onMobHatPigFallDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if (entity == null) {
            return; // Billys Sanity check ^_^
        }

        if (!(entity.getType().equals(EntityType.PIG))) {
            return; // Not a pig passenger, ignore.
        }

        Entity vehicle = entity.getVehicle();

        if (!(vehicle instanceof Player)) {
            return; // pig not on players head. no mob hat.
        }

        Entity passenger = entity.getPassenger();

        if (passenger == null) {
            return; // No Mob Hat on pig
        }

        e.setCancelled(true); // This pig is part of a mobhat. cancel damage.
    }

    @EventHandler
    public void onPlayerHatTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Entity ent1 = player.getPassenger();

        if (ent1 == null) {
            return; // no entity found on head (pig)
        }

        Entity ent2 = ent1.getPassenger();
        if (ent2 == null) {
            return; // no entity found on entity found on head (hat)
        }

        System.out.println("Hat ejecting");

        ent2.eject();
        ent1.eject();
        player.eject();

        new ReattachHat(player, ent1, ent2).runTaskLater(plugin, 5); // wait 5 ticks (1/4 second) to reattach the hat
    }

    @EventHandler
    public void onHatPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (player.getPassenger() != null && player.getPassenger().getPassenger() != null) {
            player.getPassenger().getPassenger().remove();
            player.getPassenger().remove();
        }

        if (player.getPassenger() != null) {
            player.getPassenger().remove();
        }
    }

    @EventHandler
    public void onMobHatPlayereKick(PlayerKickEvent e) {
        Player player = e.getPlayer();

        if (player.getPassenger() != null && player.getPassenger().getPassenger() != null) {
            player.getPassenger().getPassenger().remove();
            player.getPassenger().remove();
        }

        if (player.getPassenger() != null) {
            player.getPassenger().remove();
        }
    }

    @EventHandler
    public void onPlayerHatDeath(PlayerDeathEvent e) {
        if ((e.getEntity().getPassenger() != null) && (!(e.getEntity().getPassenger() instanceof Player))) {
            e.getEntity().getPassenger().getPassenger().remove();
            e.getEntity().getPassenger().remove();
            e.getDrops().clear();
        }
    }

    public class ReattachHat extends BukkitRunnable {
        private final Player player;
        private final Entity ent1;
        private final Entity ent2;


        public ReattachHat(Player player, Entity ent1, Entity ent2) {
            this.player = player;
            this.ent1 = ent1;
            this.ent2 = ent2;
        }

        public void run() {
            player.setPassenger(ent1);
            ent1.setPassenger(ent2);

            System.out.println("Hat re-attached");
        }

    }
}
