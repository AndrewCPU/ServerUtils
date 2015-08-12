package net.Andrewcpu.UTILS.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
    {
        if(args.length==0)
        {
            sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " C1:[player] C2:[player1 player2]");
            return true;
        }
        else if(args.length==1)
        {
            Player p = Bukkit.getPlayer(args[0]);
            if(p!=null)
            {
                sender.sendMessage(ChatColor.GOLD + "Teleporting...");
                ((Player)sender).teleport(p.getLocation());
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "That player is not online.");
            }
            return true;
        }
        else if(args.length==2)
        {
            Player p1 = Bukkit.getPlayer(args[0]);
            Player p2 = Bukkit.getPlayer(args[1]);

            if(p1!=null && p2!=null)
            {
                p1.teleport(p2);
                sender.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.RED + p1.getName() + ChatColor.YELLOW + " to " + ChatColor.RED + p2.getName());
                return true;
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }

        }
        else if(args.length==3)
        {
            try {
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);

                Location location = new Location(((Player)sender).getWorld(),x,y,z);
                ((Player)sender).teleport(location);
                sender.sendMessage(ChatColor.GOLD + "Teleporting...");


            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid arguments.");
            }
            return true;
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Invalid arguments.");
        }
        return true;
    }
}
