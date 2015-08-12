package net.Andrewcpu.UTILS.Managers;

import net.Andrewcpu.UTILS.Managers.Permissions.Group;
import net.Andrewcpu.UTILS.Main;
import net.Andrewcpu.UTILS.Managers.Permissions.PermissionPlayer;

import java.util.List;
import java.util.UUID;

/**
 * Created by andrewpstein on 8/11/15.
 */
public class PermissionManager {
    public PermissionPlayer createPermissionPlayer(UUID uuid)
    {
        PermissionPlayer player = new PermissionPlayer(uuid,getPrefix(uuid),getSuffix(uuid),getPermissions(uuid), getColor(uuid));
        if(hasGroup(uuid))
        {
            player.setGroup(getGroup(getGroup(uuid)));
        }
        return player;
    }

    public Group createGroup(String groupName)
    {
        Group group = new Group(groupName, getPrefix(groupName),getSuffix(groupName),getColor(groupName),getPermissions(groupName));
        return group;
    }
    public String getPrefix(String groupName)
    {
        if(Main.main.getConfig().isSet("Group." + groupName + ".Prefix"))
        {
            return Main.main.getConfig().getString("Group." + groupName + ".Prefix");
        }
        return "";
    }
    public PermissionPlayer getPlayer(UUID uuid)
    {
        PermissionPlayer permissionPlayer = null;
        for(PermissionPlayer p : Main.main.players)
        {
            if(p.getUuid()==uuid)
            {
                permissionPlayer = p;
            }
        }
        return permissionPlayer;
    }
    public String getSuffix(String groupName)
    {
        if(Main.main.getConfig().isSet("Group." + groupName + ".Suffix"))
        {
            return Main.main.getConfig().getString("Group." + groupName + ".Suffix");
        }
        return "";
    }
    public String getColor(String groupName)
    {
        if(Main.main.getConfig().isSet("Group." + groupName + ".Color"))
        {
            return Main.main.getConfig().getString("Group." + groupName + ".Color");
        }
        return "";
    }
    public List<String> getPermissions(String groupName)
    {
        return Main.main.getConfig().getStringList("Group." + groupName+ ".Permissions");
    }
    public Group getGroup(String name)
    {
        Group group = null;
        for(Group g : Main.main.groups)
        {
            if(g.getName().equalsIgnoreCase(name))
            {
                group = g;
            }
        }
        return group;
    }
    public String getGroup(UUID uuid)
    {
        return Main.main.getConfig().getString("Player." + uuid.toString() + ".Group");
    }
    public boolean hasGroup(UUID uuid)
    {
        if(Main.main.getConfig().isSet("Player." + uuid.toString() + ".Group"))
        {
            return true;
        }
        return false;

    }
    public String getColor(UUID uuid)
    {
        if(Main.main.getConfig().isSet("Player." + uuid.toString() + ".Color"))
        {
            return Main.main.getConfig().getString("Player." + uuid.toString() + ".Color");
        }
        return "-null";
    }
    public List<String> getPermissions(UUID uuid)
    {
        return Main.main.getConfig().getStringList("Player." + uuid.toString() + ".Permissions");
    }
    public String getPrefix(UUID uuid)
    {
        if(Main.main.getConfig().isSet("Player." + uuid.toString() + ".Prefix"))
        {
            return Main.main.getConfig().getString("Player." + uuid.toString() + ".Prefix");
        }
        return "-null";
    }
    public String getSuffix(UUID uuid)
    {
        if(Main.main.getConfig().isSet("Player." + uuid.toString() + ".Suffix"))
        {
            return Main.main.getConfig().getString("Player." + uuid.toString() + ".Suffix");
        }
        return "-null";
    }
}
