package net.Andrewcpu.UTILS.Managers;

import net.Andrewcpu.UTILS.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class VanishManager {
    //todo this
    private static Main main = Main.main;
    public static boolean isVanished(Player p)
    {
        return Main.main.vanished.contains(p.getUniqueId());
    }
    public static void vanish(Player p)
    {
        main.vanished.add(p.getUniqueId());
        for(Player usr : Bukkit.getOnlinePlayers())
        {
            if(!(usr.getUniqueId()==p.getUniqueId()))
            {
                usr.hidePlayer(p);
            }
        }
    }
    public static void unVanish(Player p)
    {
        main.vanished.remove(p.getUniqueId());
        for(Player usr : Bukkit.getOnlinePlayers())
        {
            if(!(usr.getUniqueId()==p.getUniqueId()))
            {
                usr.showPlayer(p);
            }
        }
    }
}
