package net.Andrewcpu.UTILS.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
    {
        if(args.length==0)
        {
            sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " [player] (gamemode)");
        }
        else {
            if (args.length == 1) {
                GameMode gameMode = null;
                if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
                    gameMode = GameMode.CREATIVE;
                } else if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("Survival") || args[0].equalsIgnoreCase("0")) {
                    gameMode = GameMode.SURVIVAL;
                } else if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
                    gameMode = GameMode.ADVENTURE;
                } else if (args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                    gameMode = GameMode.SPECTATOR;
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid GameMode.");
                    return true;
                }
                ((Player) sender).setGameMode(gameMode);
                sender.sendMessage(ChatColor.GOLD + "Set " + ChatColor.RED + sender.getName() + "'s " + ChatColor.GOLD + " GameMode to " + ChatColor.RED + gameMode.toString().toLowerCase());
                return true;
            } else if (args.length == 2) {


                Player p = Bukkit.getPlayer(args[0]);
                if (p == null) {
                    sender.sendMessage(ChatColor.RED + "That player is not online.");
                    return true;
                }
                GameMode gameMode = null;
                if (args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("1")) {
                    gameMode = GameMode.CREATIVE;
                } else if (args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("Survival") || args[1].equalsIgnoreCase("0")) {
                    gameMode = GameMode.SURVIVAL;
                } else if (args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("adventure") || args[1].equalsIgnoreCase("2")) {
                    gameMode = GameMode.ADVENTURE;
                } else if (args[1].equalsIgnoreCase("sp") || args[1].equalsIgnoreCase("spectator") || args[1].equalsIgnoreCase("3")) {
                    gameMode = GameMode.SPECTATOR;
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid GameMode.");
                    return true;
                }
                ((Player) p).setGameMode(gameMode);
                sender.sendMessage(ChatColor.GOLD + "Set " + ChatColor.RED + p.getName() + "'s " + ChatColor.GOLD + " GameMode to " + ChatColor.RED + gameMode.toString().toLowerCase());
                return true;
            }
        }
        return true;
    }
}
