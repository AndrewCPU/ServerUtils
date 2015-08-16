package net.Andrewcpu.UTILS.Managers;

import net.Andrewcpu.UTILS.Managers.utils.WarpInformation;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class TeleporterManager {
    public static void openTeleporter(Player p)
    {
        Inventory i = Bukkit.createInventory(null, 36, ChatColor.BLUE + "" + ChatColor.BOLD + "|| " + ChatColor.GOLD + "Teleporter" + ChatColor.BLUE + "" + ChatColor.BOLD + "||");
        int num = 1;
        for(WarpInformation information : WarpManager.getAllWarps())
        {
            ItemStack item = new ItemStack(Material.CHEST, num);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + information.getWarpName().replaceAll("_", " "));
            item.setItemMeta(meta);
            i.addItem(item);
            num++;
        }
        p.openInventory(i);
    }
}
