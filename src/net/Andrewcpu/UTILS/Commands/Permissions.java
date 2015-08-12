package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Managers.Permissions.Group;
import net.Andrewcpu.UTILS.Main;
import net.Andrewcpu.UTILS.Managers.Permissions.PermissionPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Permissions implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
    {
        Main main = Main.main;
        if(args[0].equalsIgnoreCase("group"))
        {
            if(args[1].equalsIgnoreCase("create"))
            {
                if((main.permissionManager.getGroup(args[2]))==null)
                {
                    main.setupGroup(args[2]);
                    Group group = main.permissionManager.createGroup(args[2]);
                    main.groups.add(group);
                    sender.sendMessage(ChatColor.GREEN + "Created the group " + ChatColor.RED + args[2]);
                    List<String> groups = main.getConfig().getStringList("Groups");
                    main.groups.add(main.permissionManager.createGroup(args[2]));
                    main.getConfig().set("Groups",groups);
                    main.saveConfig();
                    main.reloadConfig();

                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "That group already exists.");
                }

            }
            else if(args[1].equalsIgnoreCase("add"))
            {
                String group = args[2];
                String permission = args[3];
                Group g = main.permissionManager.getGroup(group);
                g.addPermission(permission);
                sender.sendMessage(ChatColor.GREEN + "Added " + ChatColor.RED + permission + ChatColor.GREEN +" to " + ChatColor.RED + g.getName());
            }
            else if(args[1].equalsIgnoreCase("remove"))
            {
                String group = args[2];
                String permission = args[3];
                Group g = main.permissionManager.getGroup(group);
                g.removePermission(permission);
                sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.RED + permission + ChatColor.GREEN +" from " + ChatColor.RED + g.getName());
            }
            else if(args[1].equalsIgnoreCase("prefix"))
            {
                String group = args[2];
                String prefix = args[3];
                Group g = main.permissionManager.getGroup(group);
                g.setPrefix(prefix);
                sender.sendMessage(ChatColor.GREEN +"Set the prefix to "  + ChatColor.translateAlternateColorCodes('&',prefix) + ChatColor.GREEN +  " of the group " + ChatColor.RED + g.getName());
            }
            else if(args[1].equalsIgnoreCase("suffix"))
            {
                String group = args[2];
                String prefix = args[3];
                Group g = main.permissionManager.getGroup(group);
                g.setSuffix(prefix);
                sender.sendMessage(ChatColor.GREEN +"Set the suffix to "  + ChatColor.translateAlternateColorCodes('&',prefix) + ChatColor.GREEN +  " of the group " + ChatColor.RED + g.getName());
            }
            else if(args[1].equalsIgnoreCase("color"))
            {
                String color = args[3];
                String group = args[2];
                Group g = main.permissionManager.getGroup(group);
                g.setColor(color.toUpperCase());
                //  PermissionPlayer permmy = main.permissionManager.getPlayer(Bukkit.getPlayer(player).getUniqueId());
                // permmy.setColor(color.toUpperCase());
                sender.sendMessage(ChatColor.GREEN + "You set the group of " + ChatColor.RED + g.getName() + "'s " + ChatColor.GREEN + " text to look like this: " + ChatColor.translateAlternateColorCodes('&',g.getColor()) + "The quick brown fox jumps over a lazy dog.");
            }
        }
        else if(args[0].equalsIgnoreCase("player"))
        {
            if(args[1].equalsIgnoreCase("add"))
            {
                PermissionPlayer player = main.permissionManager.getPlayer(Bukkit.getPlayer(args[2]).getUniqueId());
                player.addPermission(args[3]);
                sender.sendMessage(ChatColor.GREEN + "Added " + ChatColor.RED + args[3] + ChatColor.GREEN +" to " + ChatColor.RED + Bukkit.getPlayer(args[2]).getName());
            }
            else if(args[1].equalsIgnoreCase("remove"))
            {
                PermissionPlayer player = main.permissionManager.getPlayer(Bukkit.getPlayer(args[2]).getUniqueId());
                player.removePermission(args[3]);
                sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.RED + args[3] + ChatColor.GREEN +" from " + ChatColor.RED + Bukkit.getPlayer(args[2]).getName());
            }
            else if(args[1].equalsIgnoreCase("prefix"))
            {
                PermissionPlayer player = main.permissionManager.getPlayer(Bukkit.getPlayer(args[2]).getUniqueId());
                player.setPrefix(args[3]);
                sender.sendMessage(ChatColor.GREEN + "Set " + ChatColor.RED + Bukkit.getPlayer(args[2]).getName() + ChatColor.GREEN + "'s prefix to " + ChatColor.RED + args[3]);
            }
            else if(args[1].equalsIgnoreCase("suffix"))
            {
                PermissionPlayer player = main.permissionManager.getPlayer(Bukkit.getPlayer(args[2]).getUniqueId());
                player.setSuffix(args[3]);
                sender.sendMessage(ChatColor.GREEN + "Set " + ChatColor.RED + Bukkit.getPlayer(args[2]).getName() + ChatColor.GREEN + "'s suffix to " + ChatColor.RED + args[3]);
            }
            else if(args[1].equalsIgnoreCase("group"))
            {
                String group = args[2];
                String player = args[3];
                PermissionPlayer permissionPlayer = main.permissionManager.getPlayer(Bukkit.getPlayer(player).getUniqueId());
                permissionPlayer.setGroup(
                        main.permissionManager
                                .getGroup(group));
                sender.sendMessage(ChatColor.GREEN + "Set " + ChatColor.RED + Bukkit.getPlayer(player).getName() + "'s " + ChatColor.GREEN + " group to " + ChatColor.RED + permissionPlayer.getGroup().getName());
            }
            else if(args[1].equalsIgnoreCase("groupget"))
            {
                sender.sendMessage(main.permissionManager.getPlayer(Bukkit.getPlayer(args[2]).getUniqueId()).getGroup().getName());
            }
            else if(args[1].equalsIgnoreCase("color"))
            {
                String color = args[3];
                String player = args[2];
                PermissionPlayer permmy = main.permissionManager.getPlayer(Bukkit.getPlayer(player).getUniqueId());
                permmy.setColor(color.toUpperCase());
                sender.sendMessage(ChatColor.GREEN + "You set " + ChatColor.RED + Bukkit.getPlayer(player).getName() + "'s " + ChatColor.GREEN + " text to look like this: " + ChatColor.translateAlternateColorCodes('&',permmy.getColor()) + "The quick brown fox jumps over a lazy dog.");
            }
            else if(args[1].equalsIgnoreCase("testperm"))
            {
                String perm = args[2];
                if(Bukkit.getPlayer(args[3]).hasPermission(perm))
                {
                    sender.sendMessage(ChatColor.GREEN +"YAY");
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "NO");
                }

            }

        }
        return true;
    }
}
