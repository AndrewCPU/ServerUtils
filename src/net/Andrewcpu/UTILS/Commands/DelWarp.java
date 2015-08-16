package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Managers.WarpManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class DelWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length==0)
        {
            sender.sendMessage(ChatColor.RED + "Please specify a warp to delete.");
            return true;
        }
        String warp = args[0].toLowerCase();
        if(!WarpManager.warpExists(warp))
        {
            sender.sendMessage(ChatColor.RED + "That warp doesn't exist.");
            return true;
        }
        WarpManager.deleteWarp(warp);
        sender.sendMessage(ChatColor.RED + "Deleted warp " + ChatColor.RED + warp);

        return true;
    }
}
