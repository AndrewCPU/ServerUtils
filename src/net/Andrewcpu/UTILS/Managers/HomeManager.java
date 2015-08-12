package net.Andrewcpu.UTILS.Managers;

import net.Andrewcpu.UTILS.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class HomeManager {
    public static boolean hasHome(Player p)
    {
        return Main.main.getConfig().isSet("Home." + p.getUniqueId().toString() + ".X");
    }
    public static Location getPlayerHome(Player p)
    {
        Main main = Main.main;
        String l = "Home." + p.getUniqueId().toString() + ".";
        double x = Double.parseDouble(main.getConfig().getString(l + "X"));
        double y = Double.parseDouble(main.getConfig().getString(l + "Y"));
        double z = Double.parseDouble(main.getConfig().getString(l + "Z"));
        float YAW = Float.parseFloat(main.getConfig().getString(l + "YAW"));
        float PITCH = Float.parseFloat(main.getConfig().getString(l + "PITCH"));
        String world = main.getConfig().getString(l + "WORLD");
        Location location = new Location(Bukkit.getWorld(world),x,y,z,YAW,PITCH);
        return location;
    }
    public static void setPlayerHome(UUID uuid, double x, double y, double z, float YAW, float PITCH, World world)
    {
        Main main = Main.main;
        main.getConfig().set("Home." + uuid.toString() + ".X", x + "");
        main.getConfig().set("Home." + uuid.toString() + ".Y", y + "");
        main.getConfig().set("Home." + uuid.toString() + ".Z", z + "");
        main.getConfig().set("Home." + uuid.toString() + ".YAW", YAW + "");
        main.getConfig().set("Home." + uuid.toString() + ".PITCH", PITCH + "");
        main.getConfig().set("Home." + uuid.toString() + ".WORLD", world.getName() + "");
        main.saveConfig();
        main.reloadConfig();
    }
}
