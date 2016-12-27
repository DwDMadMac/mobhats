package net.downwithdestruction.mobhats.tasks;

import net.downwithdestruction.mobhats.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.pl3x.bukkit.cities.manager.TitleManager.*;

/**
 * Created by madmac on 10/29/15.
 * <p>
 * TODO: Create Ability, Flashing Colors (Rainbow)
 *
 * INFO: jeb_ for rainbow sheep as name
 * INFO: Dinnerbone for mobs go upside down as name
 */
public class SheepAbility {
    private static Pig pig;
    private static Sheep sheep;
    private static ChatColor GD = ChatColor.GOLD;
    private static ChatColor DR = ChatColor.DARK_RED;
    private static String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";

    public static void attachedMobHatSheep(Player player) {
        if ((player.hasPermission("mobhats.command.mobhat.sheep")) || (player.hasPermission("mobhats.command.mobhat.all"))) {
            pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            sheep = (Sheep) player.getWorld().spawnEntity(player.getLocation(), EntityType.SHEEP);
            Utils.setNoAI(sheep);
            pig.setPassenger(sheep);
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

    public static void detachedMobHatSheep(Player player) {

    }
}
