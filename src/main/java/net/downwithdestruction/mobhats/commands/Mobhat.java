package net.downwithdestruction.mobhats.commands;

import net.downwithdestruction.mobhats.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by madmac on 10/18/15.
 *
 */
public class Mobhat implements CommandExecutor {
    private Main plugin;
    private ChatColor G = ChatColor.GREEN;
    private ChatColor DG = ChatColor.DARK_GREEN;
    private ChatColor GD = ChatColor.GOLD;
    private ChatColor R = ChatColor.RED;
    private ChatColor DR = ChatColor.DARK_RED;
    private String DwD = DR + "[" + ChatColor.DARK_GRAY + "DwD" + DR + "] ";

    public Mobhat(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(DwD + GD + "Only players can use MobHats!");
            return true;
        }

        if(!(commandSender.hasPermission("mobhats.command.mobhat"))){
            commandSender.sendMessage(DwD + GD + "You do not have permission to use /mobhat!");
            return true;
        }

        Player target = (Player) commandSender;

        if(command.getName().equalsIgnoreCase("mobhat")){
            // Create the inventory
            Inventory inv = Bukkit.createInventory(null, 54, DwD + DG + "Mob Hats:");

            // No permission to use Mob Hat
            ArrayList<String> noPerms = new ArrayList<>();
            noPerms.add(ChatColor.RED + "Sorry " + GD + target.getDisplayName() + R + ", you do");
            noPerms.add(ChatColor.RED + "not have permissions to use");
            noPerms.add(ChatColor.RED + "this MobHat!");

            // Cow Egg
            ItemStack cowEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.COW.getTypeId());
            ItemMeta cowMeta = cowEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.cow") || target.hasPermission("mobhats.command.mobhat.all")) {
                cowMeta.setDisplayName(G + "Cow");
            } else {
                cowMeta.setDisplayName(DR + "Cow");
                cowMeta.setLore(noPerms);
            }
            cowEgg.setItemMeta(cowMeta);
            inv.setItem(19, cowEgg);

            // Chicken Egg
            ItemStack chickenEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CHICKEN.getTypeId());
            ItemMeta chickenMeta = chickenEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.chicken") || target.hasPermission("mobhats.command.mobhat.all")) {
                chickenMeta.setDisplayName(G + "Chicken");
            } else {
                chickenMeta.setDisplayName(DR + "Chicken");
                chickenMeta.setLore(noPerms);
            }
            chickenEgg.setItemMeta(chickenMeta);
            inv.setItem(21, chickenEgg);

            // Squid Egg
            ItemStack squidEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SQUID.getTypeId());
            ItemMeta squidMeta = squidEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.squid") || target.hasPermission("mobhats.command.mobhat.all")) {
                squidMeta.setDisplayName(G + "Squid");
            } else {
                squidMeta.setDisplayName(DR + "Squid");
                squidMeta.setLore(noPerms);
            }
            squidEgg.setItemMeta(squidMeta);
            inv.setItem(23, squidEgg);

            // Mooshroom Cow Egg
            ItemStack mooshroomEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.MUSHROOM_COW.getTypeId());
            ItemMeta mooshroomMeta = mooshroomEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.mooshroom") || target.hasPermission("mobhats.command.mobhat.all")) {
                mooshroomMeta.setDisplayName(G + "Mooshroom Cow");
            } else {
                mooshroomMeta.setDisplayName(DR + "Mooshroom Cow");
                mooshroomMeta.setLore(noPerms);
            }
            mooshroomEgg.setItemMeta(mooshroomMeta);
            inv.setItem(25, mooshroomEgg);

            // Sheep Egg
            ItemStack sheepEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SHEEP.getTypeId());
            ItemMeta sheepMeta = sheepEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.sheep") || target.hasPermission("mobhats.command.mobhat.all")) {
                sheepMeta.setDisplayName(G + "Sheep");
            } else {
                sheepMeta.setDisplayName(DR + "Sheep");
                sheepMeta.setLore(noPerms);
            }
            sheepEgg.setItemMeta(sheepMeta);
            inv.setItem(27, sheepEgg);

            // Pig Egg
            ItemStack pigEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.PIG.getTypeId());
            ItemMeta pigMeta = pigEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.pig") || target.hasPermission("mobhats.command.mobhat.all")) {
                pigMeta.setDisplayName(G + "Pig");
            } else {
                pigMeta.setDisplayName(DR + "Pig");
                pigMeta.setLore(noPerms);
            }
            pigEgg.setItemMeta(pigMeta);
            inv.setItem(29, pigEgg);

            // Pigman Egg
            ItemStack pigmanEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.PIG_ZOMBIE.getTypeId());
            ItemMeta pigmanMeta = pigmanEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.pigman") || target.hasPermission("mobhats.command.mobhat.all")) {
                pigmanMeta.setDisplayName(G + "Pigman");
            } else {
                pigmanMeta.setDisplayName(DR + "Pigman");
                pigmanMeta.setLore(noPerms);
            }
            pigmanEgg.setItemMeta(pigmanMeta);
            inv.setItem(31, pigmanEgg);

            // Slime Egg
            ItemStack slimeEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SLIME.getTypeId());
            ItemMeta slimeMeta = slimeEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.slime") || target.hasPermission("mobhats.command.mobhat.all")) {
                slimeMeta.setDisplayName(G + "Slime");
            } else {
                slimeMeta.setDisplayName(DR + "Slime");
                slimeMeta.setLore(noPerms);
            }
            slimeEgg.setItemMeta(slimeMeta);
            inv.setItem(33, slimeEgg);

            // Ocelot Egg
            ItemStack ocelotEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.OCELOT.getTypeId());
            ItemMeta ocelotMeta = ocelotEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.ocelot") || target.hasPermission("mobhats.command.mobhat.all")) {
                ocelotMeta.setDisplayName(G + "Ocelot");
            } else {
                ocelotMeta.setDisplayName(DR + "Ocelot");
                ocelotMeta.setLore(noPerms);
            }
            ocelotEgg.setItemMeta(ocelotMeta);
            inv.setItem(35, ocelotEgg);

            // Cave Spider Egg
            ItemStack caveSpiderEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CAVE_SPIDER.getTypeId());
            ItemMeta caveSpiderMeta = caveSpiderEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.cavespider") || target.hasPermission("mobhats.command.mobhat.all")) {
                caveSpiderMeta.setDisplayName(G + "Cave Spider");
            } else {
                caveSpiderMeta.setDisplayName(DR + "Cave Spider");
                caveSpiderMeta.setLore(noPerms);
            }
            caveSpiderEgg.setItemMeta(caveSpiderMeta);
            inv.setItem(37, caveSpiderEgg);

            // Silver Fish Egg
            ItemStack silverFishEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SILVERFISH.getTypeId());
            ItemMeta silverFishMeta = silverFishEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.silverfish") || target.hasPermission("mobhats.command.mobhat.all")) {
                silverFishMeta.setDisplayName(G + "Silver Fish");
            } else {
                silverFishMeta.setDisplayName(DR + "Silver Fish");
                silverFishMeta.setLore(noPerms);
            }
            silverFishEgg.setItemMeta(silverFishMeta);
            inv.setItem(39, silverFishEgg);

            // Skeleton Egg
            ItemStack skeletonEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SKELETON.getTypeId());
            ItemMeta skeletonMeta = skeletonEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.silverfish") || target.hasPermission("mobhats.command.mobhat.all")) {
                skeletonMeta.setDisplayName(G + "Skeleton");
            } else {
                skeletonMeta.setDisplayName(DR + "Skeleton");
                skeletonMeta.setLore(noPerms);
            }
            skeletonEgg.setItemMeta(skeletonMeta);
            inv.setItem(41, skeletonEgg);

            // Iron Golem Egg
            ItemStack ironGolemEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.IRON_GOLEM.getTypeId());
            ItemMeta ironGolemMeta = ironGolemEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.irongolem") || target.hasPermission("mobhats.command.mobhat.all")) {
                ironGolemMeta.setDisplayName(G + "Iron Golem");
            } else {
                ironGolemMeta.setDisplayName(DR + "Iron Golem");
                ironGolemMeta.setLore(noPerms);
            }
            ironGolemEgg.setItemMeta(ironGolemMeta);
            inv.setItem(47, ironGolemEgg);

            // Zombie Egg
            ItemStack zombieEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ZOMBIE.getTypeId());
            ItemMeta zombieMeta = zombieEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.zombie") || target.hasPermission("mobhats.command.mobhat.all")) {
                zombieMeta.setDisplayName(G + "Zombie");
            } else {
                zombieMeta.setDisplayName(DR + "Zombie");
                zombieMeta.setLore(noPerms);
            }
            zombieEgg.setItemMeta(zombieMeta);
            inv.setItem(43, zombieEgg);

            // Spider Egg
            ItemStack spiderEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SPIDER.getTypeId());
            ItemMeta spiderMeta = spiderEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.spider") || target.hasPermission("mobhats.command.mobhat.all")) {
                spiderMeta.setDisplayName(G + "Spider");
            } else {
                spiderMeta.setDisplayName(DR + "Spider");
                spiderMeta.setLore(noPerms);
            }
            spiderEgg.setItemMeta(spiderMeta);
            inv.setItem(45, spiderEgg);

            // Snowman Egg
            ItemStack snowmanEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SNOWMAN.getTypeId());
            ItemMeta snowmanMeta = snowmanEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.snowman") || target.hasPermission("mobhats.command.mobhat.all")) {
                snowmanMeta.setDisplayName(G + "Snowman");
            } else {
                snowmanMeta.setDisplayName(DR + "Snowman");
                snowmanMeta.setLore(noPerms);
            }
            snowmanEgg.setItemMeta(snowmanMeta);
            inv.setItem(49, snowmanEgg);

            // Bat Egg
            ItemStack batEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.BAT.getTypeId());
            ItemMeta batMeta = batEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.bat") || target.hasPermission("mobhats.command.mobhat.all")) {
                batMeta.setDisplayName(G + "Bat");
                ArrayList<String> batLore = new ArrayList<>();
                batLore.add(ChatColor.GOLD + "Mob Hat Abilities");
                batLore.add(ChatColor.GOLD + "  - Double Jump");
                batLore.add(ChatColor.BLACK + "-*");
                if (target.hasPermission("mobhats.command.fly")) {
                    batLore.add(ChatColor.RED + "/fly will be disabled when");
                    batLore.add(ChatColor.RED + "you use this Mob Hat!");
                }
                batMeta.setLore(batLore);
            } else {
                batMeta.setDisplayName(DR + "Bat");
                batMeta.setLore(noPerms);
            }
            batEgg.setItemMeta(batMeta);
            inv.setItem(51, batEgg);

            // Creeper Egg
            ItemStack creeperEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CREEPER.getTypeId());
            ItemMeta creeperMeta = creeperEgg.getItemMeta();
            if (target.hasPermission("mobhats.command.mobhat.creeper") || target.hasPermission("mobhats.command.mobhat.all")) {
                creeperMeta.setDisplayName(G + "Creeper");
            } else {
                creeperMeta.setDisplayName(DR + "Creeper");
                creeperMeta.setLore(noPerms);
            }
            creeperEgg.setItemMeta(creeperMeta);
            inv.setItem(53, creeperEgg);

            if ((target.getPassenger().getType() == EntityType.PIG) && !(target.getPassenger().getPassenger().isEmpty())) {
                // TnT block (Removes Mob Hat)
                ItemStack tntExit = new ItemStack(Material.TNT, 1);
                ItemMeta tntExitMeta = tntExit.getItemMeta();
                tntExitMeta.setDisplayName(DR + "Remove Your Mob Hat");
                ArrayList<String> tntExitLore = new ArrayList<>();
                tntExitLore.add(ChatColor.GOLD + "Clicking this" + ChatColor.RED + " TNT " + ChatColor.GOLD + "item");
                tntExitLore.add(ChatColor.GOLD + "will remove your Mob Hat.");
                tntExitMeta.setLore(tntExitLore);
                tntExit.setItemMeta(tntExitMeta);
                inv.setItem(0, tntExit);
            }

            // Redstone Block (Closes Mob Hat Menu)
            ItemStack redstoneBlockCloseInv = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta redstoneBlockCloseInvMeta = redstoneBlockCloseInv.getItemMeta();
            redstoneBlockCloseInvMeta.setDisplayName(DR + "Close Mob Hat Menu");
            ArrayList<String> redstoneBlockCloseInvLore = new ArrayList<>();
            redstoneBlockCloseInvLore.add(ChatColor.GOLD + "Quick way of removing");
            redstoneBlockCloseInvLore.add(ChatColor.GOLD + "your Mob Hat menu.");
            redstoneBlockCloseInvMeta.setLore(redstoneBlockCloseInvLore);
            redstoneBlockCloseInv.setItemMeta(redstoneBlockCloseInvMeta);
            inv.setItem(8, redstoneBlockCloseInv);

            target.openInventory(inv);

            return true;
        }
        return true;
    }
}
