package net.Andrewcpu.UTILS.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Update implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "importto UTILS.jar http://andrewcpu.net/download/UTILS.jar");
        sender.sendMessage(ChatColor.RED + "Updated.");
        return true;
    }
}
