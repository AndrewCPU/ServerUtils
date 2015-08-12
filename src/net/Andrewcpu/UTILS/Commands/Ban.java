package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Commands.utils.Notifier;
import net.Andrewcpu.UTILS.Commands.utils.UUIDFetcher;
import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Main main = Main.main;


        Player p = (Player) Bukkit.getPlayer(args[0]);
        UUID uuid = null;
        if(p!=null)
        {
            uuid = p.getUniqueId();
        }
        else
        {
            UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(args[0]));
            Map<String, UUID> response = null;
            try {
                response = fetcher.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            uuid = response.get(args[0]);
        }
        String banMsg = "";
        if(args.length==1)
        {
            banMsg = "You have been banned from " + ChatColor.RED + Main.SERVER_NAME;
        }
        else
        {
            for(int i = 1; i<args.length; i++)
            {
                banMsg+=args[i] + " ";
            }
        }
        banMsg = ChatColor.translateAlternateColorCodes('&',banMsg);
        main.getConfig().set("Ban." + uuid.toString(), banMsg);
        main.saveConfig();
        main.reloadConfig();
        if(p!=null)
        {
            p.kickPlayer(banMsg);
        }
        sender.sendMessage(ChatColor.GOLD + "Banned " + ChatColor.RED + (p==null ? args[0] : p.getName()) + ChatColor.YELLOW + " for: " + ChatColor.RESET + banMsg);

        List<String> perms = new ArrayList<>();
        perms.add("utils.ban");
        perms.add("utils.ban.view");

        Notifier.notify(perms.toArray(), ChatColor.RED + sender.getName() + ChatColor.GOLD + " has banned " + ChatColor.RED + (p==null ? args[0] : p.getName()) + ChatColor.YELLOW + " for: " + ChatColor.RESET + banMsg);

        return true;
    }
}
