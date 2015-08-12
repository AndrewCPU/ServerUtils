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
public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
    {
        if (args.length == 0) {
            ((Player) sender).setAllowFlight(!((Player)sender).getAllowFlight());
            sender.sendMessage(ChatColor.GOLD + "Toggled fly for " + ChatColor.RED + sender.getName() + ChatColor.GOLD + ". Flight: " + ChatColor.RED + ((Player)sender).getAllowFlight());

            return true;
        } else {
            String pla = args[0];
            Player p = Bukkit.getPlayer(pla);
            if (p == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }
            p.setSaturation(20);
            p.setAllowFlight(!p.getAllowFlight());
            p.sendMessage(ChatColor.GOLD + "Allowed Flight: " + ChatColor.RED + p.getAllowFlight());
            sender.sendMessage(ChatColor.GOLD + "Toggled flight for " + ChatColor.RED + p.getName() + ChatColor.GOLD + ". Allowed Flight: " + ChatColor.RED + p.getAllowFlight());
            return true;
        }
    }
}
