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
public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
    {
        sender.sendMessage(ChatColor.GOLD + "Teleporting in " + ChatColor.RED + Main.COOLDOWN + " seconds.");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
            @Override
            public void run() {
                ((Player)sender).teleport((Player)((Player) sender).getWorld().getSpawnLocation());
                sender.sendMessage(ChatColor.GOLD + "Teleporting...");
            }
        }, Main.COOLDOWN * 20);
        return true;
    }
}
