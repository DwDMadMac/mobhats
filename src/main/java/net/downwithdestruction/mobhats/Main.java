package net.downwithdestruction.mobhats;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.downwithdestruction.mobhats.commands.Mobhat;
import net.downwithdestruction.mobhats.listeners.PlayerListener;
import net.downwithdestruction.mobhats.listeners.PlayerMobHatSelected;
import net.downwithdestruction.mobhats.packets.PigPackets;
import net.downwithdestruction.mobhats.tasks.BatAbility;
import net.downwithdestruction.mobhats.tasks.CreeperAbility;
import net.downwithdestruction.mobhats.tasks.OcelotAbility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by madmac on 10/18/15.
 *
 * TODO: Add check for mobhat Entity Drops
 * TODO: Add check for mobhat player Entity Damage
 * TODO: Fix player not being able to teleport when Mob Hat is on
 *
 */
public class Main extends JavaPlugin {
    public static Main instance;
    private PluginManager pm = Bukkit.getPluginManager();
    private ProtocolManager protocolManager;
    private PigPackets pigpackets;
    private CreeperAbility creeperAbility;

    public Main(){
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        protocolManager = ProtocolLibrary.getProtocolManager();

        // Packets Package
        pigpackets = new PigPackets(getServer());
        pigpackets.onMobHatPigSound(protocolManager, this);

        // Listeners Package
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new PlayerMobHatSelected(this), this);

        // Tasks Package
        pm.registerEvents(new BatAbility(this), this);
        pm.registerEvents(new OcelotAbility(this), this);

        registerCommand();

    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

        // Packets Package
        pigpackets.onMobHatPigSoundRemoval(protocolManager, this); //Old method

        getServer().getScheduler().cancelTasks(this);
    }

    public void registerCommand() {
        this.getCommand("mobhat").setExecutor(new Mobhat(this));
    }

    public ProtocolManager getProtocolLib() {
        return protocolManager;
    }
}
