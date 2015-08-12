package net.Andrewcpu.UTILS;

import net.Andrewcpu.UTILS.Commands.*;
import net.Andrewcpu.UTILS.Managers.PermissionManager;
import net.Andrewcpu.UTILS.Managers.Permissions.Group;
import net.Andrewcpu.UTILS.Managers.Permissions.PermissionPlayer;
import net.Andrewcpu.UTILS.Survey.Survey;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by andrewpstein on 8/11/15.
 */
public class Main extends JavaPlugin implements Listener {
    public static Main main = null;
    public List<PermissionPlayer> players = new ArrayList<>();
    public List<Group> groups = new ArrayList<>();
    public PermissionManager permissionManager = new PermissionManager();
    public final static int COOLDOWN = 3;
    public final static String SERVER_NAME = "Andrewcpu's Server";
    public List<UUID> godMode = new ArrayList<>();
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
        main = this;
        for(String s : getConfig().getStringList("Groups"))
        {
            groups.add(permissionManager.createGroup(s));
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers())
                {
                    addPermissionAttachment(p);
                    players.add(permissionManager.createPermissionPlayer(p.getUniqueId()));
                    permissionManager.getPlayer(p.getUniqueId()).applyPermissions();
                    p.setPlayerListName(ChatColor.translateAlternateColorCodes('&',getPlayerName(p)));
                }
            }
        }, 1);

        getCommand("fly").setExecutor(new Fly());
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("p").setExecutor(new Permissions());
        getCommand("teleport").setExecutor(new Teleport());
        getCommand("tp").setExecutor(new Teleport());
        getCommand("teleporthere").setExecutor(new TeleportHere());
        getCommand("tphere").setExecutor(new TeleportHere());
        getCommand("feed").setExecutor(new Feed());
        getCommand("heal").setExecutor(new Heal());
        getCommand("setwarp").setExecutor(new SetWarp());
        getCommand("warp").setExecutor(new Warp());
        getCommand("motd").setExecutor(new MOTD());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("god").setExecutor(new God());
        getCommand("choose").setExecutor(new Choose());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("give").setExecutor(new Give());
        getCommand("update").setExecutor(new Update());
        getCommand("ban").setExecutor(new Ban());
        getCommand("unban").setExecutor(new Unban());
        getCommand("kick").setExecutor(new Kick());
        HashMap<String,String> choices = new HashMap<>();
        choices.put("A","Pasta");
        choices.put("B","Watermelon");

        Survey survey = new Survey(1, choices, "Choose your favorite.");

        activeSurvey = survey;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers())
                {
                    if(getConfig().getStringList("Survey.Voted.ID" + activeSurvey.getID()).contains(p.getUniqueId().toString()))
                    {

                    }
                    else
                    {
                        activeSurvey.send(p);
                    }
                }
            }
        }, 0, 20 * 5 * 20);

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if(event.getPlayer().getLocation().getBlock().getType()== Material.STONE_PLATE)
        {
            if(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType()==Material.REDSTONE_BLOCK)
            {
                //Vector v = new Vector(event.getPlayer().getLocation().getDirection().getX(),1,event.getPlayer().getLocation().getDirection().getZ());
                event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().add(new Vector(0, .5, 0)).multiply(new Vector(20, 20, 20)));
            }
        }
        if(getBelowBlock(event.getPlayer()).getType()==Material.WOOL)
        {
            if(getBelowBlock(event.getPlayer()).getData()==(byte)4)
            {
                event.getPlayer().setVelocity(new Vector(0.25,0,0));
            }
            if(getBelowBlock(event.getPlayer()).getData()==(byte)5)
            {
                event.getPlayer().setVelocity(new Vector(-0.25,0,0));
            }
            if(getBelowBlock(event.getPlayer()).getData()==(byte)6)
            {
                event.getPlayer().setVelocity(new Vector(0,0,0.25));
            }
            if(getBelowBlock(event.getPlayer()).getData()==(byte)7)
            {
                event.getPlayer().setVelocity(new Vector(0,0,-0.25));
            }
        }
        if(event.getTo().getY()<=0)
        {
            event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
            event.getPlayer().sendMessage(ChatColor.GOLD + "Teleporting...");
        }

    }
    public Block getBelowBlock(Player p)
    {
        return p.getLocation().getBlock().getRelative(BlockFace.DOWN);
    }

    public  HashMap<UUID,PermissionAttachment> permissions = new HashMap<>();
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {

        if(getConfig().isSet("Ban." + event.getPlayer().getUniqueId().toString()))
        {
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                  event.getPlayer().kickPlayer(getConfig().getString("Ban." + event.getPlayer().getUniqueId().toString()));
                }
            }, 1);
        }

        if(!isSetup(event.getPlayer().getUniqueId()))
        {
            setupPlayer(event.getPlayer().getUniqueId());
        }
        addPermissionAttachment(event.getPlayer());
        loadPlayer(event.getPlayer());
        event.getPlayer().setPlayerListName(ChatColor.translateAlternateColorCodes('&', getPlayerName(event.getPlayer())));

        Bukkit.dispatchCommand(event.getPlayer(), "motd");
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        removePermissionAttachment(event.getPlayer());
        unloadPlayer(event.getPlayer());
    }
    @EventHandler
    public void onKick(PlayerKickEvent event)
    {
        removePermissionAttachment(event.getPlayer());
        unloadPlayer(event.getPlayer());
    }
    public void removePermissionAttachment(Player p)
    {
        permissions.remove(p.getUniqueId());
    }
    public void addPermissionAttachment(Player p)
    {
        permissions.put(p.getUniqueId(), p.addAttachment(this));
    }






    public void loadPlayer(Player p)
    {
        players.add(permissionManager.createPermissionPlayer(p.getUniqueId()));
    }
    public void unloadPlayer(Player p)
    {
        PermissionPlayer player = null;
        for(PermissionPlayer pl : players)
        {
            if(pl.getUuid()==p.getUniqueId())
            {
                player = pl;
            }
        }
        players.remove(player);
    }


    public void setupPlayer(UUID uuid)
    {
        List<String> permissions = new ArrayList<>();
        permissions.add("permissions.use");
        getConfig().set("Player." + uuid.toString()  + ".Permissions", permissions);
        saveConfig();
        reloadConfig();
    }
    public void setupGroup(String groupName)
    {
        List<String> permissions = new ArrayList<>();
        permissions.add("permissions.use");
        getConfig().set("Group." + groupName  + ".Permissions", permissions);
        saveConfig();
        reloadConfig();
    }

    public boolean isSetup(UUID uuid)
    {
        if(getConfig().isSet("Player." + uuid.toString()))
        {
            return true;
        }
        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        // event.setFormat(getRank(event.getPlayer()) + (event.getPlayer().isOp() ? ChatColor.RED : ChatColor.YELLOW) + getPlayerName(event.getPlayer()) + ChatColor.GRAY + ": " + getPlayerColor(event.getPlayer()) + event.getMessage());
        PermissionPlayer player  =permissionManager.getPlayer(event.getPlayer().getUniqueId());
        String prefix = player.getPrefix();
        String suffix = player.getSuffix();
        String color = player.getColor();

        if(player.getGroup()!=null)
        {
            if(prefix.equalsIgnoreCase("-null"))
            {
                prefix = player.getGroup().getPrefix();
            }
            if(suffix.equalsIgnoreCase("-null"))
            {
                suffix = player.getGroup().getSuffix();
            }
            if(color.equalsIgnoreCase("-null"))
            {
                color = player.getGroup().getColor();
            }
        }
        else
        {
            if(prefix.equalsIgnoreCase("-null"))
            {
                prefix = "";
            }
            if(suffix.equalsIgnoreCase("-null"))
            {
                suffix = "";
            }
            if(color.equalsIgnoreCase("-null"))
            {
                color = "";
            }
        }
        color = ChatColor.translateAlternateColorCodes('&', color);
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        suffix = ChatColor.translateAlternateColorCodes('&',suffix);
        event.setFormat(prefix + getPlayerName(event.getPlayer()) + suffix + ChatColor.GRAY + ": " + color + event.getMessage());
    }







    public String getPlayerName(Player p)
    {
        if(getConfig().isSet("Name." + p.getUniqueId().toString()))
        {
            return ChatColor.translateAlternateColorCodes('&', getConfig().getString("Name." + p.getUniqueId().toString()));
        }
        else
        {
            return p.getName();
        }


    }

    @EventHandler
    public void onProccess(PlayerCommandPreprocessEvent event)
    {
        String[] args = event.getMessage().split(" ");
        int x =0;
        HashMap<Integer,String> replace = new HashMap<>();
        for(String arg : args)
        {
            if(getConfig().isSet("Name." + arg))
            {
                replace.put(x,arg);
            }
            x++;
        }
        for(int i : replace.keySet())
        {
            args[i] = replace.get(i);
        }

        String msg = "";
        for(String s : args)
        {
            msg += s + " ";
        }

        event.setMessage(msg);


    }
    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            Player p = (Player)event.getEntity();
            if(godMode.contains(p.getUniqueId()))
            {
                event.setCancelled(true);
            }
        }
    }
    Survey activeSurvey = null;

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("result"))
        {
            for(String s : activeSurvey.getResult().keySet())
            {
                sender.sendMessage(ChatColor.GOLD + s + "" + ChatColor.GRAY + ": " + ChatColor.BLUE + activeSurvey.getChoice(s.toUpperCase()) +  " " +activeSurvey.getResult().get(s) + "%");
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setname")) { // If the player typed /basic then do the following...
            String player = "";
            String name = "";
            if(args.length==1)
            {
                player = sender.getName();
                name = args[0];
            }
            else if(args.length==2)
            {
                player = args[0];
                name = args[1];
            }
            getConfig().set("Name." + Bukkit.getPlayer(player).getUniqueId().toString(), name);
            saveConfig();
            reloadConfig();
            sender.sendMessage(ChatColor.RED + "Set " + ChatColor.YELLOW + player + ChatColor.RED + "'s IGN to " + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&',name));
            Bukkit.getPlayer(player).setPlayerListName(ChatColor.translateAlternateColorCodes('&', getPlayerName(Bukkit.getPlayer(player))));
            return true;
        }


        //If this has happened the function will return true.
        // If this hasn't happened the value of false will be returned.
        return false;
    }
}
