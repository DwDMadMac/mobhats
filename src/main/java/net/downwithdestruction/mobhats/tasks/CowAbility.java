package net.downwithdestruction.mobhats.tasks;

import net.downwithdestruction.mobhats.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.pl3x.bukkit.cities.manager.TitleManager.*;

/**
 * Created by madmac on 10/29/15.
 *
 * TODO: Create Ability
 */
public class CowAbility {
    private static Pig pig;
    private static Cow cow;
    private static ChatColor GD = ChatColor.GOLD;
    private static ChatColor DR = ChatColor.DARK_RED;
    private static String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";

    public static void attachedMobHatCow(Player player) {
        if ((player.hasPermission("mobhats.command.mobhat.cow")) || (player.hasPermission("mobhats.command.mobhat.all"))) {
            pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            cow = (Cow) player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
            Utils.setNoAI(cow);
            pig.setPassenger(cow);
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
                    60,
                    60,
                    GD + "Sorry",
                    DR + "This Mob Hat is restricted, try again!"
            );
            player.sendMessage(DwD + GD + "sorry, you are not allowed to choose this mob as a hat!");
        }
    }

    public static void detachedMobHatIronGolem(Player player) {

    }
}
