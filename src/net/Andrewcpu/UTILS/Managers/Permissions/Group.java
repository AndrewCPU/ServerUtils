package net.Andrewcpu.UTILS.Managers.Permissions;

import net.Andrewcpu.UTILS.Main;

import java.util.List;

/**
 * Created by andrewpstein on 8/11/15.
 */
public class Group
{
    private String name;
    private String prefix;
    private String suffix;
    private String color;

    private List<String> permissions;


    public Group(String name, String prefix, String suffix, String color, List<String> permissions) {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.color = color;
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
        Main.main.getConfig().set("Group." + name + ".Permissions",permissions);
        Main.main.saveConfig();
        Main.main.reloadConfig();
        updatePermissions();
    }

    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color = color;
        Main.main.getConfig().set("Group." + name + ".Color",getColor());
        Main.main.saveConfig();
        Main.main.reloadConfig();
    }
    public String getPrefix()
    {
        return prefix;
    }
    public String getSuffix()
    {
        return suffix;
    }
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        Main.main.getConfig().set("Group." + name + ".Prefix",getPrefix());
        Main.main.saveConfig();
        Main.main.reloadConfig();
    }
    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
        Main.main.getConfig().set("Group." + name + ".Suffix",getSuffix());
        Main.main.saveConfig();
        Main.main.reloadConfig();
    }
    public void addPermission(String permission)
    {
        permissions.add(permission);
        Main.main.getConfig().set("Group." + name + ".Permissions",permissions);
        Main.main.saveConfig();
        Main.main.reloadConfig();
        updatePermissions();
    }
    public void removePermission(String permission)
    {
        permissions.remove(permission);
        Main.main.getConfig().set("Group." + name + ".Permissions",permissions);
        Main.main.saveConfig();
        Main.main.reloadConfig();
        updatePermissions();
    }
    public void updatePermissions()
    {
        for(PermissionPlayer p : Main.main.players)
        {
            if(p.getGroup()!=null)
            {
                if(p.getGroup()==this)
                {
                    p.applyPermissions();
                }
            }

        }
    }
    public boolean hasPermission(String permission)
    {
        if(permissions.contains(permission))
        {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
