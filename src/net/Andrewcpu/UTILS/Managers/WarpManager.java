package net.Andrewcpu.UTILS.Managers;

import net.Andrewcpu.UTILS.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewpstein on 8/11/15.
 */
public class WarpManager {
    public static Location getWarpLocation(String warpName)
    {
        Main main = Main.main;
        double x = Double.parseDouble(main.getConfig().getString("Warp." + warpName.toLowerCase() + ".X"));
        double y = Double.parseDouble(main.getConfig().getString("Warp." + warpName.toLowerCase() + ".Y"));
        double z = Double.parseDouble(main.getConfig().getString("Warp." + warpName.toLowerCase() + ".Z"));
        float yaw = Float.parseFloat(main.getConfig().getString("Warp." + warpName.toLowerCase() + ".YAW"));
        float p = Float.parseFloat(main.getConfig().getString("Warp." + warpName.toLowerCase() + ".PITCH"));
        String world = main.getConfig().getString("Warp." + warpName.toLowerCase() + ".WORLD");
        Location location = new Location(Bukkit.getWorld(world),x,y,z,yaw,p);

        return location;
    }
    public static boolean warpExists(String warpName)
    {
        Main main = Main.main;
        List<String> warps = main.getConfig().getStringList("Warps");
        if(warps.contains(warpName.toLowerCase()))
        {
            return true;
        }
        return false;
    }
    public static void setWarpLocation(String warp, double x, double y, double z, float yaw, float pitch, World world)
    {
        Main main = Main.main;
        main.getConfig().set("Warp." + warp.toLowerCase() + ".X", x + "");
        main.getConfig().set("Warp." + warp.toLowerCase() + ".Y", y + "");
        main.getConfig().set("Warp." + warp.toLowerCase() + ".Z", z + "");
        main.getConfig().set("Warp." + warp.toLowerCase() + ".YAW", yaw);
        main.getConfig().set("Warp." + warp.toLowerCase() + ".PITCH", pitch);
        main.getConfig().set("Warp." + warp.toLowerCase() + ".WORLD", world.getName());

        List<String> warps = main.getConfig().getStringList("Warps");
        if(!warps.contains(warp))
        {
            warps.add(warp);
        }
        main.getConfig().set("Warps",warps);

        main.saveConfig();
        main.reloadConfig();

    }
}
