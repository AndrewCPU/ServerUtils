package net.Andrewcpu.UTILS.Commands;

import net.Andrewcpu.UTILS.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class JoinInventory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player)sender;
        p.teleport(p.getWorld().getSpawnLocation());
        Inventory i = Bukkit.createInventory(null, p.getInventory().getSize());
        ItemStack teleporter = new ItemStack(Material.DRAGON_EGG, 1);
        ItemMeta teleporterMeta = teleporter.getItemMeta();
        teleporterMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "|| " + ChatColor.GOLD + "Teleporter" + ChatColor.BLUE + "" + ChatColor.BOLD + "||");
        teleporter.setItemMeta(teleporterMeta);
        //
        ItemStack website = new ItemStack(Material.ANVIL, 1);
        ItemMeta websiteMeta = website.getItemMeta();
        websiteMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + Main.WEBSITE);
        website.setItemMeta(websiteMeta);




       // i.setItem(0,teleporter);
        //i.setItem(8, website);


        p.getInventory().setContents(i.getContents());
        p.getInventory().setItem(0,teleporter);
        p.getInventory().setItem(8,website);
        p.updateInventory();

        return true;
    }
}
