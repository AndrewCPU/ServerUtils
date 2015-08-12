package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Main;
import net.Andrewcpu.UTILS.Managers.HomeManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Home implements CommandExecutor{
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
    {
        if(HomeManager.hasHome(((Player)sender)))
        {
            sender.sendMessage(ChatColor.GOLD + "Teleporting in " + ChatColor.RED + Main.COOLDOWN + " seconds.");
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
                @Override
                public void run() {
                    ((Player)sender).teleport(HomeManager.getPlayerHome(((Player)sender)));
                }
            }, Main.COOLDOWN * 20);
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "You have not set a home yet.");
        }

        return true;
    }
}
