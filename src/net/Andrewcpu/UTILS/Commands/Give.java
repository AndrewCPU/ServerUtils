package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Commands.utils.ItemNames;
import net.Andrewcpu.UTILS.Commands.utils.ItemWordNames;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Give implements CommandExecutor{
    public boolean isNumber(String s)
    {
        boolean isNumber = true;

        try {
            int x = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            isNumber = false;
        }


        return isNumber;
    }
    public void gave(Player p, ItemStack i)
    {
        p.sendMessage(ChatColor.YELLOW + "Given " + ChatColor.BLUE + p.getName() + " " + ChatColor.RED + i.getAmount() + " " + ItemNames.valueOf(i.getType().toString()).toString() + " (B" + i.getDurability() + ")");
    }
    public Material fromItemName(String itemName){
        return Material.getMaterial(ItemWordNames.getMaterialName(itemName));
    }
    public Material fromNiceName(String itemName){
        return Material.getMaterial(ItemNames.getMaterialName(itemName));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length==0)
        {
            //do help

        }
        Player p =  (Player)sender;
        if(args.length==1)
        {
            if(isNumber(args[0]))
            {
                ItemStack i  = new ItemStack(Material.getMaterial(Integer.parseInt(args[0])), 64);
                p.getInventory().addItem(i);
                p.updateInventory();
                gave(p,i);
                return true;
            }
            ItemStack i = new ItemStack(fromItemName(args[0]), 64);
            p.getInventory().addItem(i);
            p.updateInventory();
            gave(p,i);
            return true;
        }
        else if(args.length==2)
        {
            if(isPlayer(args[0]))
            {
                Player g = Bukkit.getPlayer(args[0]);
                if(isNumber(args[1]))
                {
                    ItemStack i  = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])), 64);
                    g.getInventory().addItem(i);
                    g.updateInventory();
                    gave(g,i);
                    return true;
                }
                ItemStack i = new ItemStack(fromItemName(args[1]), 64);
                g.getInventory().addItem(i);
                g.updateInventory();
                gave(g,i);
                return true;
            }
            else{
                if(isNumber(args[0]))
                {
                    if(isNumber(args[1]))
                    {
                        int id = Integer.parseInt(args[0]);
                        int amount = Integer.parseInt(args[1]);
                        ItemStack i= new ItemStack(Material.getMaterial(id),amount);
                        p.getInventory().addItem(i);
                        p.updateInventory();
                        gave(p,i);
                        return true;
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                        return true;
                    }
                }
            }
        }
        else if(args.length==3)
        {
            if(isPlayer(args[0]))
            {
                Player g = Bukkit.getPlayer(args[0]);
                if(isNumber(args[1]))
                {
                    if(isNumber(args[2]))
                    {
                        ItemStack i  = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])), Integer.parseInt(args[2]));
                        g.getInventory().addItem(i);
                        g.updateInventory();
                        gave(g,i);
                        return true;
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                        return true;
                    }

                }
                ItemStack i = new ItemStack(fromItemName(args[1]), Integer.parseInt(args[2]));
                g.getInventory().addItem(i);
                g.updateInventory();
                gave(g,i);
                return true;
            }
            else{
                Material mat;
                if(isNumber(args[0]))
                {
                    mat = Material.getMaterial(Integer.parseInt(args[0]));
                }
                else
                {
                    mat = fromItemName(args[0]);
                }
                if(isNumber(args[1]))
                {
                    if(isNumber(args[2]))
                    {

                        int amount = Integer.parseInt(args[1]);
                        int data = Integer.parseInt(args[2]);
                        ItemStack i= new ItemStack(mat,amount, (byte)data);
                        p.getInventory().addItem(i);
                        p.updateInventory();
                        gave(p,i);
                        return true;
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                        return true;
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                    return true;
                }
            }
        }
        else if(args.length==4)
        {
            if(isPlayer(args[0]))
            {
                Material mat = null;
                int id = -1;
                if(isNumber(args[1]))
                {
                     mat = Material.getMaterial(Integer.parseInt(args[1]));

                }
                else
                {
                    mat = fromItemName(args[1]);
                }
                if(isNumber(args[2]))
                {
                    int amount = Integer.parseInt(args[2]);
                    if(isNumber(args[3]))
                    {
                        int data = Integer.parseInt(args[3]);
                        ItemStack i = new ItemStack(mat,amount,(byte)data);
                        p.getInventory().addItem(i);
                        p.updateInventory();
                        gave(p,i);
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Invalid arguments.");
            }
        }



        return true;
    }
    public boolean isPlayer(String s)
    {
        return Bukkit.getPlayer(s)!=null;
    }
}
