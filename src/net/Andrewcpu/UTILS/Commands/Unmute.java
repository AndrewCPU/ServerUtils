package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Commands.utils.Notifier;
import net.Andrewcpu.UTILS.Managers.MuteManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Unmute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        if(p==null)
        {
            sender.sendMessage(ChatColor.RED + "That player is not online.");
            return true;
        }
        List<String> perms = new ArrayList<>();
        perms.add("utils.mute");
        perms.add("utils.mute.view");
        Notifier.notify(perms.toArray(), ChatColor.RED + sender.getName() + " " + ChatColor.GOLD + " has unmuted " + ChatColor.RED + p.getName());
        MuteManager.unmute(p);

        return true;
    }
}
