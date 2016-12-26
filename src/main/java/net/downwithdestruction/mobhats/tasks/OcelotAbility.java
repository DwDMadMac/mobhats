package net.downwithdestruction.mobhats.tasks;

import net.downwithdestruction.mobhats.Main;
import net.downwithdestruction.mobhats.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static net.pl3x.bukkit.cities.manager.TitleManager.*;

/**
 * Created by madmac on 10/20/15.
 */
public class OcelotAbility implements Listener {
    private static Pig pig;
    private static Ocelot ocelot;
    private static ChatColor GD = ChatColor.GOLD;
    private static ChatColor DR = ChatColor.DARK_RED;
    private static String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";
    private Main plugin;

    public OcelotAbility(Main plugin) {
        this.plugin = plugin;
    }

    public static void attachedMobHatOcelot(Player player) {
        if ((player.hasPermission("mobhats.command.mobhat.ocelot")) || (player.hasPermission("mobhats.command.mobhat.all"))) {
            pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            ocelot = (Ocelot) player.getWorld().spawnEntity(player.getLocation(), EntityType.OCELOT);
            Utils.setNoAI(ocelot);
            pig.setPassenger(ocelot);
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

    public static void detachedMobHatOcelot(Player player) {

    }

    /*
     * Cancel player fall damage if wearing ocelot mobhat
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onMobHatOcelotFall(EntityDamageEvent e) {
        if (!(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
            return; // Player did not fall, ignored!
        }

        Entity entity = e.getEntity();

        if (entity == null) {
            return; // Billys Sanity check ^_^
        }

        if (!(entity instanceof Player)) {
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

        if (hat.getType() != EntityType.OCELOT) {
            return; // Mob Hat is not an ocelot. ignored!
        }

        List<String> worlds = plugin.getConfig().getStringList("abilities.ocelot.worlds");

        for (String w : worlds) {
            World world = Bukkit.getWorld(w);

            if (player.getWorld().equals(world)) {
                e.setCancelled(true);
            }
        }
    }
}
