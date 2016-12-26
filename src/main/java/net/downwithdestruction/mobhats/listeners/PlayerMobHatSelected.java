package net.downwithdestruction.mobhats.listeners;

import net.downwithdestruction.mobhats.Main;
import net.downwithdestruction.mobhats.tasks.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by madmac on 10/18/15.
 *
 */
public class PlayerMobHatSelected implements Listener {
    private Main plugin;
    private Pig pig;
    private ChatColor GD = ChatColor.GOLD;
    private ChatColor DR = ChatColor.DARK_RED;
    private String DwD = DR + "[" + ChatColor.GRAY + "DwD" + DR + "] ";

    public PlayerMobHatSelected(Main plugin) {
        this.plugin = plugin;
    }

    private void onMobHatTnTRemoval(Player player) {
        // Cow
        if (player.getPassenger().getPassenger().getType() == EntityType.COW) {
            CowAbility.detachedMobHatIronGolem(player);
        }

        // Chicken
        if (player.getPassenger().getPassenger().getType() == EntityType.CHICKEN) {
            ChickenAbility.detachedMobHatChicken(player);
        }

        // Squid
        if (player.getPassenger().getPassenger().getType() == EntityType.SQUID) {
            SquidAbility.detachedMobhatSquid(player);
        }

        // Mooshroom Cow
        if (player.getPassenger().getPassenger().getType() == EntityType.MUSHROOM_COW) {
            MooshroomCowAbility.detachedMobHatMooshroomCow(player);
        }

        // Pig
        if (player.getPassenger().getPassenger().getType() == EntityType.PIG) {
            PigAbility.detachedMobHatPig(player);
        }

        // Sheep
        if (player.getPassenger().getPassenger().getType() == EntityType.SHEEP) {
            SheepAbility.detachedMobHatSheep(player);
        }

        // PigZombie
        if (player.getPassenger().getPassenger().getType() == EntityType.PIG_ZOMBIE) {
            PigZombieAbility.detachedMobHatPigZombie(player);
        }

        // Ocelot
        if (player.getPassenger().getPassenger().getType() == EntityType.OCELOT) {
            OcelotAbility.detachedMobHatOcelot(player);
        }

        // Skeleton
        if (player.getPassenger().getPassenger().getType() == EntityType.SKELETON) {
            SkeletonAbility.detachedMobHatSkeleton(player);
        }

        // Cave Spider
        if (player.getPassenger().getPassenger().getType() == EntityType.CAVE_SPIDER) {
            CaveSpiderAbility.detachedMobHatCaveSpider(player);
        }

        // Silverfish
        if (player.getPassenger().getPassenger().getType() == EntityType.SILVERFISH) {
            SilverfishAbility.detachedMobHatSilberfish(player);
        }

        // Slime
        if (player.getPassenger().getPassenger().getType() == EntityType.SLIME) {
            SlimeAbility.detachedMobHatSlime(player);
        }

        // Zombie
        if (player.getPassenger().getPassenger().getType() == EntityType.ZOMBIE) {
            ZombieAbility.detachedMobHatZombie(player);
        }

        // Spider
        if (player.getPassenger().getPassenger().getType() == EntityType.SPIDER) {
            SpiderAbility.detachedMobHatSpider(player);
        }

        // Iron Golem
        if (player.getPassenger().getPassenger().getType() == EntityType.IRON_GOLEM) {
            IronGolemAbility.detachedMobHatIronGolem(player);
        }

        // Snowman
        if (player.getPassenger().getPassenger().getType() == EntityType.SNOWMAN) {
            SnowmanAbility.detachedMobHatSnowman(player);
        }

        // Bat
        if (player.getPassenger().getPassenger().getType() == EntityType.BAT) {
            BatAbility.detachedMobHatBat(player);
        }

        // Creeper
        if (player.getPassenger().getPassenger().getType() == EntityType.CREEPER) {
            CreeperAbility.detachedMobHatCreeper(player);
        }

        player.getPassenger().getPassenger().remove();
        player.getPassenger().remove();
        //player.closeInventory();
        player.sendMessage(DwD + GD + "You removed Mob Hat, " + player.getPassenger().getPassenger().getName() + "!");
    }

    @EventHandler
    public void onMobHatInvClick(InventoryClickEvent e){
        if (!(e.getInventory().getName().contains("Mob Hats:"))) {
            return; // Not Mob Hat inventory, canceled!
        }

        if (e.getClick() == null) {
            e.setCancelled(true);
            return; // Click happened outside of inventory and/or inside empty inventory box, canceled.
        }

        if (e.getInventory().getHolder() == null) {
            e.setCancelled(true);
            //return; // Sanity check, nothing owns this inventory, canceled
        }

        Player player = (Player) e.getWhoClicked();

        if (player == null) {
            e.setCancelled(true);
            return; // Billys sanity check xD
        }

        if (!(player.getGameMode() == GameMode.SURVIVAL)) {
            player.closeInventory();
            player.sendMessage(DwD + GD + "Sorry, you can only use Mob Hats in Survival mode!");
            return; // Player can not have mob hat when in creative, spectator or Adventure mode
        }

        if (!(player.getPassenger() == null) && player.getPassenger().getType() == EntityType.PLAYER) {
            player.closeInventory();
            player.sendMessage(DwD + GD + "Sorry, you can not set a Mob Hat while a player is riding you!");
            return; // Player is riding player, canceled
        }

        Material clickedItem = e.getCurrentItem().getType();

        if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
            player.closeInventory();
            return; // Player closed Mob Hats inventory!
        }

        if (!(player.getPassenger() == null) && player.getPassenger().getType() == EntityType.PIG) {

            if (clickedItem == Material.TNT) {
                onMobHatTnTRemoval(player);
                return; // Mob Hat removed, cancel
            }

            player.closeInventory();
            player.sendMessage(
                    DwD + GD + "Sorry, you must remove your existing Mob Hat before using a new Mob Hat!\n \n" +
                            DwD + GD + "Click the" + DR + " TnT " + GD + "inside the /mobhat menu!"
            );
            return; // Already has a Mob Hat, canceled!
        }

        if (!(e.getCurrentItem().hasItemMeta())) {
            e.setCancelled(true);
            return; // No Mob Hats are in inventory, canceled!
        }

        if (e.getCurrentItem().getDurability() == 92) {
            // Cow
            CowAbility.attachedMobHatCow(player);
        } else if (e.getCurrentItem().getDurability() == 93) {
            // Chicken
            ChickenAbility.attachedMobHatChicken(player);
        } else if (e.getCurrentItem().getDurability() == 94) {
            // Squid
            SquidAbility.attachedMobHatSquid(player);
        } else if (e.getCurrentItem().getDurability() == 96) {
            // Mooshroom Cow
            MooshroomCowAbility.attachedMobHatMooshroomCow(player);
        } else if (e.getCurrentItem().getDurability() == 90) {
            // Pig
            PigAbility.attachedMobHatPig(player);
        } else if (e.getCurrentItem().getDurability() == 91) {
            // Sheep
            SheepAbility.attachedMobHatSheep(player);
        } else if (e.getCurrentItem().getDurability() == 57) {
            // Pigman
            PigZombieAbility.attachedMobHatPigZombie(player);
        } else if (e.getCurrentItem().getDurability() == 98) {
            // Ocelot
            OcelotAbility.attachedMobHatOcelot(player);
        } else if (e.getCurrentItem().getDurability() == 51) {
            // Skeleton
            SkeletonAbility.attachedMobHatSkeleton(player);
        } else if (e.getCurrentItem().getDurability() == 59) {
            // Cave Spider
            CaveSpiderAbility.attachedMobHatCaveSpider(player);
        } else if (e.getCurrentItem().getDurability() == 60) {
            // Silver Fish
            SilverfishAbility.attachedMobHatSilverfish(player);
        } else if (e.getCurrentItem().getDurability() == 55) {
            // Slime
            SlimeAbility.attachedMobHatSlime(player);
        } else if (e.getCurrentItem().getDurability() == 54) {
            // Zombie
            ZombieAbility.attachedMobHatZombie(player);
        } else if (e.getCurrentItem().getDurability() == 52) {
            // Spider
            SpiderAbility.attachedMobHatSpider(player);
        } else if (e.getCurrentItem().getDurability() == 99) {
            // Iron Golem
            IronGolemAbility.attachedMobHatIronGolem(player);
        } else if (e.getCurrentItem().getDurability() == 97) {
            // Snowman
            SnowmanAbility.attachedMobHatSnowman(player);
        } else if (e.getCurrentItem().getDurability() == 65) {
            // Bat
            BatAbility.attachedMobHatBat(player);
        } else if (e.getCurrentItem().getDurability() == 50) {
            // Creeper
            CreeperAbility.attachedMobHatCreeper(player);
        }
    }
}
