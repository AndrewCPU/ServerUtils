package net.Andrewcpu.UTILS.Managers.utils.Message;

/**
 * Created by andrewpstein on 8/13/15.
 */
        import java.lang.reflect.Field;

        import net.md_5.bungee.api.ChatColor;

        import net.minecraft.server.v1_8_R3.IChatBaseComponent;
        import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
        import net.minecraft.server.v1_8_R3.PlayerConnection;
        import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
        import org.bukkit.entity.Player;

public class TabPlayer {

    Player p;

    public TabPlayer(Player p) {
        this.p = p;
    }

    public void sendTabTitle(String header, String footer)
    {
        if (header == null) {
            header = "";
        }
        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null) {
            footer = "";
        }
        footer = ChatColor.translateAlternateColorCodes('&', footer);

        header = header.replaceAll("%player%", p.getDisplayName());
        footer = footer.replaceAll("%player%", p.getDisplayName());

        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        try
        {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            connection.sendPacket(headerPacket);
        }
    }

}
