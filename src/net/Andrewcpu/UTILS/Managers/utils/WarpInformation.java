package net.Andrewcpu.UTILS.Managers.utils;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class WarpInformation {
    private int x, y, z;
    private String world, name;

    public WarpInformation(int x, int y, int z, String world, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getWarpName() {
        return name;
    }

    public void setWarpName(String name) {
        this.name = name;
    }
}
