package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Commands.utils.UUIDFetcher;
import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        UUID uuid;
        UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(args[0]));
        Map<String, UUID> response = null;
        try {
            response = fetcher.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        uuid = response.get(args[0]);
        Main main = Main.main;
        if(!main.getConfig().isSet("Ban." + uuid.toString()))
        {
           sender.sendMessage(ChatColor.GOLD + "That player is not banned.");
            return true;
        }

            main.getConfig().set("Ban." + uuid.toString(), null);
            main.saveConfig();
            main.reloadConfig();
            for(Player p : Bukkit.getOnlinePlayers())
            {
                if(p.hasPermission("utils.ban.view") || p.hasPermission("utils.ban"))
                {
                    p.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " has unbanned " + ChatColor.RED + args[0]);
                }
            }
        return true;
    }
}
