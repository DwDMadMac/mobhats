package net.downwithdestruction.mobhats.tasks;

import net.downwithdestruction.mobhats.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.pl3x.bukkit.cities.manager.TitleManager.*;

/**
 * Created by madmac on 10/29/15.
 * <p>
 * TODO: Cave Spider, See in the dark
 */
public class CaveSpiderAbility {
    private static Pig pig;
    private static CaveSpider caveSpider;
    private static ChatColor GD = ChatColor.GOLD;
    private static ChatColor DR = ChatColor.DARK_RED;
    private static String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";

    public static void attachedMobHatCaveSpider(Player player) {
        if ((player.hasPermission("mobhats.command.mobhat.cavespider")) || (player.hasPermission("mobhats.command.mobhat.all"))) {
            pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            caveSpider = (CaveSpider) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
            Utils.setNoAI(caveSpider);
            pig.setPassenger(caveSpider);
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

    public static void detachedMobHatCaveSpider(Player player) {

    }

}
