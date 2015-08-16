package net.Andrewcpu.UTILS.Managers.Permissions;

import net.Andrewcpu.UTILS.Main;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;
import java.util.UUID;

/**
 * Created by andrewpstein on 8/11/15.
 */
public class PermissionPlayer {
   // private String uuid.toString();
    private UUID uuid;
    private String prefix;
    private String suffix;
    private List<String> permissions;
    private String color;
    private Group group = null;

    public PermissionPlayer(UUID uuid, String prefix, String suffix, List<String> permissions, String color) {
        this.uuid = uuid;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permissions = permissions;
        this.color = color;
        applyPermissions();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        Main.main.getConfig().set("Player." + uuid.toString() + ".Group", group.getName());
        Main.main.saveConfig();
        Main.main.reloadConfig();
        applyPermissions();
//        ActionBarAPI.send(Bukkit.getPlayer(uuid), "Your group has been set to " + getGroup().getName());
    }



    public void applyPermissions()
    {
        for(String s : permissions)
        {
            getPermissionAttachment().setPermission(s, true);
        }
        if(getGroup()!=null)
        {
            for(String s : group.getPermissions())
            {
                if(!hasPermission(s))
                {
                    getPermissionAttachment().setPermission(s, true);
                }
            }
            for(Group g : getGroup().getInherited())
            {
                for(String s : g.getPermissions())
                {
                    if(!getPermissionAttachment().getPermissions().containsKey(s))
                    {
                        getPermissionAttachment().setPermission(s, true);
                    }
                }
            }
        }
    }


    public PermissionAttachment getPermissionAttachment()
    {
        return Main.main.permissions.get(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color = color;
        Main.main.getConfig().set("Player." + uuid.toString() + ".Color",getColor());
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
        Main.main.getConfig().set("Player." + uuid.toString() + ".Prefix",getPrefix());
        Main.main.saveConfig();
        Main.main.reloadConfig();
    }
    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
        Main.main.getConfig().set("Player." + uuid.toString() + ".Suffix",getSuffix());
        Main.main.saveConfig();
        Main.main.reloadConfig();
    }
    public void addPermission(String permission)
    {
        permissions.add(permission);
        Main.main.getConfig().set("Player." + uuid.toString() + ".Permissions",permissions);
        Main.main.saveConfig();
        Main.main.reloadConfig();
        applyPermissions();
    }
    public void removePermission(String permission)
    {
        permissions.remove(permission);
        Main.main.getConfig().set("Player." + uuid.toString() + ".Permissions",permissions);
        Main.main.saveConfig();
        Main.main.reloadConfig();
        applyPermissions();
    }
    public boolean hasPermission(String permission)
    {
        if(permissions.contains(permission))
        {
            return true;
        }
        return false;
    }

}
