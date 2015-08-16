package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class God implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length==0)
        {
            if(Main.main.godMode.contains(((Player)commandSender).getUniqueId()))
            {
                Main.main.godMode.remove(((Player)commandSender).getUniqueId());
            }
            else
            {
                Main.main.godMode.add(((Player)commandSender).getUniqueId());
            }
            commandSender.sendMessage(ChatColor.GOLD + "God Mode: " + ChatColor.RED + Main.main.godMode.contains(((Player)commandSender).getUniqueId()));
        }
        else if(args.length==1)
        {
            Player p = Bukkit.getPlayer(args[0]);
            if(p!=null)
            {
                if(Main.main.godMode.contains(p.getName()))
                {
                    Main.main.godMode.remove(p.getUniqueId());
                }
                else
                {
                    Main.main.godMode.add(p.getUniqueId());
                }
                commandSender.sendMessage(ChatColor.GOLD + "God Mode: " + ChatColor.RED + Main.main.godMode.contains(p.getUniqueId()));
                p.sendMessage(ChatColor.GOLD + "God Mode: " + ChatColor.RED + Main.main.godMode.contains(p.getUniqueId()));
            }
            else
            {
                commandSender.sendMessage(ChatColor.RED + "That player is not online.");
            }
        }
        else
        {
            commandSender.sendMessage(ChatColor.RED + "Invalid arguments.");
        }

        return true;
    }
}
