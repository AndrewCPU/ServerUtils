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
public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = Bukkit.getPlayer(sender.getName());
        p.sendMessage(ChatColor.GOLD + "Set spawn.");
        p.getWorld().setSpawnLocation((int)p.getLocation().getX(),(int)p.getLocation().getY(),(int)p.getLocation().getZ());
        return true;
    }
}
