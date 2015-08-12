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
public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
    {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "Healed " + ChatColor.RED + sender.getName() + ChatColor.GOLD + ".");

            ((Player) sender).setHealth(20);
            ((Player) sender).setSaturation(20);
            return true;
        } else {
            String pla = args[0];
            Player p = Bukkit.getPlayer(pla);
            if (p == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }
            p.setSaturation(20);
            p.setHealth(20);
            p.sendMessage(ChatColor.GOLD + "You have been healed.");
            sender.sendMessage(ChatColor.GOLD + "Healed " + ChatColor.RED + p.getName() + ChatColor.GOLD + ".");
            return true;
        }
    }
}
