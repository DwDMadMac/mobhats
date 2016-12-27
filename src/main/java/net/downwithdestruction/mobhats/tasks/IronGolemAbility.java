package net.downwithdestruction.mobhats.tasks;

import net.downwithdestruction.mobhats.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.pl3x.bukkit.cities.manager.TitleManager.*;

/**
 * Created by madmac on 10/26/15.
 *
 * TODO: Add check for when mob hat was removed, remove hearts
 *
 * FIXME: Health boost exploit, on attachedMobHatIronGolem
 *
 * INFO: Double 40 = 20 hearts
 * INFO: Double 20 = 10 hearts
 * INFO: Double 10 = 5 hearts
 *
 * INFO: double rounded = Math.round(p.getHealth() * 10) / 10;
 */
public class IronGolemAbility {
    private static Pig pig;
    private static IronGolem ironGolem;
    private static ChatColor GD = ChatColor.GOLD;
    private static ChatColor DR = ChatColor.DARK_RED;
    private static String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";

    @SuppressWarnings("deprecation")
    public static void attachedMobHatIronGolem(Player player) {
        if ((player.hasPermission("mobhats.command.mobhat.irongolem")) || (player.hasPermission("mobhats.command.mobhat.all"))) {
            pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            ironGolem = (IronGolem) player.getWorld().spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
            Utils.setNoAI(ironGolem);
            pig.setPassenger(ironGolem);
            player.setPassenger(pig);
            player.closeInventory();
            player.setMaxHealth(80.0D);
            //player.setHealth(80.0D - (player.getHealth() - 20.0D));
            /*
            if (player.getPassenger().getType() == EntityType.PIG && player.getPassenger().getPassenger().getType() == EntityType.IRON_GOLEM) {
                player.setMaxHealth(80.0D);
                //player.setHealthScale(40.0D);
                player.setHealth(80.0D);
            }
            */
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

    @SuppressWarnings("deprecation")
    public static void detachedMobHatIronGolem(Player player) {
        player.setMaxHealth(20.0D);
        //player.setHealth(20.0D - (player.getHealth() - 80.0D));
        player.sendMessage(DwD + GD + "Iron Golem Ability, Extra Hearts, removed.");
    }
}
