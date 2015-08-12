package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Managers.WarpManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class SetWarp implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        String warp = args[0];
        Player p = Bukkit.getPlayer(sender.getName());
        WarpManager.setWarpLocation(warp, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch(), p.getWorld());
        sender.sendMessage(ChatColor.GREEN + "Set warp " + ChatColor.RED + warp.toLowerCase());
        return true;
    }
}
