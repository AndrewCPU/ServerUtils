package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Managers.VanishManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Vanish implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p  = (Player)sender;
        if(VanishManager.isVanished(p))
        {
            VanishManager.unVanish(p);
            p.sendMessage(ChatColor.GREEN + "You have been unvanished.");
            return true;
        }
        VanishManager.vanish(p);
        p.sendMessage(ChatColor.GREEN + "You have been vanished.");
        return true;
    }
}
