package net.downwithdestruction.mobhats.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by madmac on 10/18/15.
 *
 */
public class PigPackets {
    private Server server;

    public PigPackets(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void onMobHatPigSound(ProtocolManager protocolManager, JavaPlugin plugin){
        protocolManager.addPacketListener(
                new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                    @Override
                    public void onPacketSending(PacketEvent e) {
                        Player player = e.getPlayer();

                        if(player.getPassenger() == null){
                            return;
                        }

                        if(player.getPassenger().getType() != EntityType.PIG){
                            return;
                        }

                        try{
                            if(e.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT){
                                if(e.getPacket().getStrings().read(0).equalsIgnoreCase("mob.pig.say")){
                                    e.setCancelled(true);
                                }
                            }
                        }catch (FieldAccessException ae){
                            ae.printStackTrace();
                        }
                    }
                }
        );
    }

    public  void onMobHatPigSoundRemoval(ProtocolManager protocolManager, JavaPlugin plugin){
        protocolManager.removePacketListeners(plugin);
    }
}
