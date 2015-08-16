package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Commands.utils.Notifier;
import net.Andrewcpu.UTILS.Managers.MuteManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class TeleportAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;
        for(Player player : Bukkit.getOnlinePlayers())
        {
            player.teleport(p);
        }
        List<String> perms = new ArrayList<>();
        perms.add("utils.tpall");
        perms.add("utils.tpall.view");
        Notifier.notify(perms.toArray(), ChatColor.RED + sender.getName() + " " + ChatColor.GOLD + " has teleported all players to them");
       // MuteManager.mute(p);

        return true;
    }
}
