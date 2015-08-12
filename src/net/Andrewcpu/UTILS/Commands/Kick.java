package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Commands.utils.Notifier;
import net.Andrewcpu.UTILS.Main;
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
public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        String msg = "";
        if(args.length==1)
        {
           msg = "You have been kicked from " + ChatColor.RED + Main.SERVER_NAME;
        }
        else
        {
            for(int i = 1; i<args.length; i++)
            {
                msg+=args[i] + " ";
            }
        }
        p.kickPlayer(ChatColor.translateAlternateColorCodes('&',msg));
        List<String> perms = new ArrayList<>();
        perms.add("utils.kick");
        perms.add("utils.kick.view");
        Notifier.notify(perms.toArray(),ChatColor.RED + sender.getName() + " " + ChatColor.GOLD + " has kicked " + ChatColor.RED + p.getName() + ChatColor.GOLD + " for: " + ChatColor.RESET + msg);
        return true;
    }
}
