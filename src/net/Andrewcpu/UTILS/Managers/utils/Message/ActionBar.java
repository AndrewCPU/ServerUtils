package net.Andrewcpu.UTILS.Managers.utils.Message;

/**
 * Created by andrewpstein on 8/13/15.
 */

import net.md_5.bungee.api.ChatColor;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    Player p;
    String msg;

    public ActionBar(Player p, String msg) {
        this.p = p;
        this.msg = msg;
    }
    public void sendPlayerAnnouncement() {
        String s = ChatColor.translateAlternateColorCodes('&', msg);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s +
                "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
}
