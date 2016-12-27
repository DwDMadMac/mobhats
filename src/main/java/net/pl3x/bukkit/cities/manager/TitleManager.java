package net.pl3x.bukkit.cities.manager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.downwithdestruction.mobhats.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TitleManager {
    private static Main plugin;

    public TitleManager(Main plugin) {
        TitleManager.plugin = plugin;
    }

    private static void sendPacket(Player player, PacketContainer packet) {
        try {
            plugin.getProtocolLib().sendServerPacket(player, packet, true);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void sendTitleAndSubtitle(Player player, int in, int stay, int out, String title, String subtitle) {
        sendTitle(player, in, stay, out, title);
        sendSubtitle(player, in, stay, out, subtitle);
    }

    public static void sendTitle(Player player, int in, int stay, int out, String title) {
        if (title == null || title.isEmpty()) {
            return;
        }

        PacketContainer packet = plugin.getProtocolLib().createPacket(PacketType.Play.Server.TITLE);

        // send timings
        packet.getTitleActions().write(0, EnumWrappers.TitleAction.TIMES);
        packet.getIntegers().write(0, in).write(1, stay).write(2, out);
        sendPacket(player, packet);

        // send title
        packet.getTitleActions().write(0, EnumWrappers.TitleAction.TITLE);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', title)));
        sendPacket(player, packet);
    }

    private static void sendSubtitle(Player player, int in, int stay, int out, String subtitle) {
        if (subtitle == null || subtitle.isEmpty()) {
            return;
        }

        PacketContainer packet = plugin.getProtocolLib().createPacket(PacketType.Play.Server.TITLE);

        // send timings
        packet.getTitleActions().write(0, EnumWrappers.TitleAction.TIMES);
        packet.getIntegers().write(0, in).write(1, stay).write(2, out);
        sendPacket(player, packet);

        // send subtitle
        packet.getTitleActions().write(0, EnumWrappers.TitleAction.SUBTITLE);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', subtitle)));
        sendPacket(player, packet);
    }

    public static void sendActionBarMessage(Player player, String message) {
        if (message == null || ChatColor.stripColor(message).equals("")) {
            return;
        }

        PacketContainer packet = plugin.getProtocolLib().createPacket(PacketType.Play.Server.CHAT);

        packet.getBytes().write(0, (byte) 2);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', message)));
        sendPacket(player, packet);
    }
}