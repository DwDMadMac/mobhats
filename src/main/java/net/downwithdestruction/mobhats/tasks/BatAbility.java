package net.downwithdestruction.mobhats.tasks;

import net.downwithdestruction.mobhats.Main;
import net.downwithdestruction.mobhats.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.pl3x.bukkit.cities.manager.TitleManager.*;

/**
 * Created by madmac on 10/18/15.
 *
 * TODO: Create Ability
 *
 */
public class BatAbility implements Listener {
    private static Pig pig;
    private static Bat bat;
    private static ChatColor GD = ChatColor.GOLD;
    private static ChatColor DR = ChatColor.DARK_RED;
    private static String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";
    private Main plugin;
    private Location location;
    private Block block;

    public BatAbility(Main plugin) {
        this.plugin = plugin;
    }

    public static void attachedMobHatBat(Player player) {
        if ((player.hasPermission("mobhats.command.mobhat.bat")) || (player.hasPermission("mobhats.command.mobhat.all"))) {
            pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
            bat = (Bat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
            Utils.setNoAI(bat);
            pig.setPassenger(bat);
            player.setPassenger(pig);
            player.closeInventory();
            sendTitle(
                    player,
                    60,
                    60,
                    60,
                    "&6Mob Hat Attached"
            );
            sendActionBarMessage(
                    player,
                    "&2&l" + player.getDisplayName() + "&r&2, you have selected Mob Hat" + "&r&6 " + player.getPassenger().getPassenger().getName()
            );
        } else {
            player.closeInventory();
            sendTitleAndSubtitle(
                    player,
                    60,
                    80,
                    60,
                    GD + "Sorry",
                    DR + "This Mob Hat is restricted, try again!"
            );
            player.sendMessage(DwD + GD + "sorry, you are not allowed to choose this mob as a hat!");
        }
    }

    public static void detachedMobHatBat(Player player) {
        player.sendMessage(DwD + GD + "Bat Ability, Double Jump, removed.");
    }

    private void onMobHatBAT(Player player){
        if (player.getPassenger().getType() == EntityType.PIG && player.getPassenger().getPassenger().getType() == EntityType.BAT) {
            location = player.getPlayer().getLocation();
            location = location.subtract(0, 1, 0);

            block = location.getBlock();

            if (!(block.getType().isSolid())) {
                return; // Player is in air, do nothing.
            }

            player.setAllowFlight(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMobHatBATPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player == null) {
            return; // billys sanity check ^_^
        }

        Entity pig = player.getPassenger();

        if (pig == null) {
            return; // No entity on player (Pig), cancel
        }

        if (!(pig.getType() == EntityType.PIG)) {
            return;  // Player passenger is not a pig, cancel
        }

        Entity bat = pig.getPassenger();

        if (bat == null) {
            return; // No Mob Hat BAT is present, cancel
        }

        if (!(bat.getType() == EntityType.BAT)) {
            return; // Mob Hat is not a Bat, cancel
        }

        if (player.getAllowFlight()) {
            return; // Fly is allowed, cancel
        }

        this.onMobHatBAT(player); // Run Double jump method
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMobHatBATPlayerDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if (entity == null) {
            return; // Billys Sanity check ^_^
        }

        if (!(e.getEntity() instanceof Player)) {
            return; // Not a player
        }

        Player player = (Player) entity;
        Entity pig = player.getPassenger();

        if (pig == null) {
            return; // pig not on players head. no mob hat.
        }

        Entity hat = pig.getPassenger();

        if (hat == null) {
            return; // pig doesn't have a Mob Hat on its head. no mobhat.
        }

        if (hat.getType() != EntityType.BAT) {
            return; // Mob Hat is not an bat. ignored!
        }

        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return; // Player did not fall, ignored!
        }

        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMobHatBATPlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();

        if (player == null) {
            return; // Billys sanity check ^_^
        }

        if (player instanceof Vehicle) {
            return; // Player is not a vehicle, sanity check?
        }

        Entity playerPassenger = player.getPassenger();

        if (playerPassenger == null) {
            return; // player has no passenger
        }

        if (!(playerPassenger.getType() == EntityType.PIG)) {
            return; // Player passenger is not a Pig
        }

        Entity mobHat = playerPassenger.getPassenger();

        if (mobHat == null) {
            return; // No mob hat is present, canceled
        }

        if (!(mobHat.getType() == EntityType.BAT)) {
            return; // Mob hat is not a BAT, cancel
        }

        e.setCancelled(true); // Do not let player turn fly on
        player.setAllowFlight(false); // Set fly to off
        player.setVelocity(player.getLocation().getDirection().multiply(1.6D).setY(1.0D)); // Found this, not sure what its for but it is needed!
    }
}
