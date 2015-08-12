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
public class InventorySee implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        if(p==null)
        {
            sender.sendMessage(ChatColor.RED + "That player is not online.");
            return true;
        }
        Player usr = (Player)sender;
        usr.openInventory(p.getInventory());
        sender.sendMessage(ChatColor.GREEN + "Viewing " + ChatColor.RED + p.getName() + "'s " + ChatColor.GREEN + " inventory.");
        return true;
    }
}
