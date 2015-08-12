package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Main;
import net.Andrewcpu.UTILS.Managers.WarpManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length==0)
        {
            String msg = ChatColor.GOLD + "List of available warps: ";
            for(String s : Main.main.getConfig().getStringList("Warps"))
            {
                msg+=ChatColor.RED + s + ChatColor.GOLD + ", ";
            }
            sender.sendMessage(msg);
        }
        else
        {
            String warp = args[0];
            if(WarpManager.warpExists(warp))
            {
                sender.sendMessage(ChatColor.GOLD +"Teleporting in " + ChatColor.RED + Main.COOLDOWN + " seconds.");
                final Location loc = ((Player)sender).getLocation();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
                    @Override
                    public void run() {

                        ((Player)sender).teleport(WarpManager.getWarpLocation(warp));


                    }
                }, Main.COOLDOWN * 20);
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "That warp does not exist.");
            }

        }



        return true;
    }
}
