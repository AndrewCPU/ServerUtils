package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class MOTD implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args){
        List<String> joinMessages = new ArrayList<>();
        String swordLeft = ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "---" + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "[-";
        String swordRight = ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-]" + ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "---";
        joinMessages.add(swordLeft + ChatColor.GOLD + " Welcome " + swordRight);
        joinMessages.add(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------------------------");
        joinMessages.add(ChatColor.GOLD + "Welcome to " + ChatColor.RED + Main.SERVER_NAME);
        joinMessages.add(ChatColor.GOLD + "Spawn created by " + ChatColor.RED + "baileyboo910, sharkattack231, and Icarus101");
        joinMessages.add(ChatColor.GOLD + "Plugins developed by " + ChatColor.RED + "Andrewcpu");
        joinMessages.add(ChatColor.GOLD + "Our Website: " + ChatColor.RED + "http://andrewcpu.net/");
        joinMessages.add(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------------------------");
        joinMessages.add(swordLeft + ChatColor.GOLD + " Welcome " + swordRight);
        for(String s : joinMessages)
        {
            sender.sendMessage(s);
        }
        return true;
    }

}

