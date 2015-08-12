package net.Andrewcpu.UTILS.Managers;

import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class MuteManager {
    private static Main main = Main.main;
    public static void mute(Player p)
    {
        main.getConfig().set("Muted." + p.getUniqueId().toString(), true);
        main.saveConfig();
        main.reloadConfig();
        p.sendMessage(ChatColor.GOLD + "You have been muted.");
    }
    public static void unmute(Player p)
    {
        main.getConfig().set("Muted." + p.getUniqueId().toString(), null);
        main.saveConfig();
        main.reloadConfig();
        p.sendMessage(ChatColor.GOLD + "You have been unmuted.");
    }
    public static boolean isMuted(Player p)
    {
        return main.getConfig().isSet("Muted." + p.getUniqueId().toString());
    }
}
