package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Managers.HomeManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
    {
        sender.sendMessage(ChatColor.GOLD + "Set home.");
        Location loc = ((Player)sender).getLocation();
        HomeManager.setPlayerHome(((Player)sender).getUniqueId(), loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch(),loc.getWorld());
        return true;
    }
}
