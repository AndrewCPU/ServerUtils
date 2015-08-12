package net.Andrewcpu.UTILS.Commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Notifier {
    public static void notify(Object[] permissions, String message)
    {
        List<UUID> notified = new ArrayList<>();
        for(Object s : permissions)
        {
            for(Player p : Bukkit.getOnlinePlayers())
            {
                if(!notified.contains(p.getUniqueId()))
                {
                    if(p.hasPermission((String)s))
                    {
                        p.sendMessage(message);
                        notified.add(p.getUniqueId());
                    }
                }
            }
        }
    }
}
