package net.Andrewcpu.UTILS.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class TeleportHere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
    {
        if(args.length==0)
        {
            sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " [player]");
            return true;
        }
        else if(args.length==1)
        {
            Player p = Bukkit.getPlayer(args[0]);
            if(p!=null)
            {
                p.teleport(((Player)sender).getLocation());
                sender.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.RED + p.getName() + ChatColor.GOLD + " to you.");
                return true;
            }
            else
            {
                //not online
                sender.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Invalid arguments.");
        }
        return true;
    }
}
