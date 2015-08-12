package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Choose implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        String choice = args[0];
        int id = Integer.parseInt(args[1]);


        Main main = Main.main;
        if(main.getConfig().getStringList("Survey.Voted.ID" + id).contains(((Player)sender).getUniqueId()))
        {
            sender.sendMessage(ChatColor.RED + "You cant vote again!");
            return true;
        }
        main.getConfig().set("Survey.Scores.ID" + id + "." + choice.toUpperCase(), main.getConfig().getInt("Survey.Scores.ID" + id + "." + choice.toUpperCase()) + 1);
        List<String> players = main.getConfig().getStringList("Survey.Voted.ID" + id);
        players.add(((Player)sender).getUniqueId().toString());
        main.getConfig().set("Survey.Voted.ID" + id, players);
        main.saveConfig();
        main.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Thank you for your input.");
        return true;
    }
}
