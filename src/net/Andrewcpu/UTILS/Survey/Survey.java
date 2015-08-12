package net.Andrewcpu.UTILS.Survey;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class Survey {
    private HashMap<String,String> letterMessage = new HashMap<>();
    private int id;
    private String question;
    private SurveyResult result = null;
    public Survey(int id, HashMap<String,String> letterMessage, String question)
    {
        this.question = question;
        this.id = id;
        this.letterMessage = letterMessage;
    }
    public HashMap<String,Double> getResult()
    {
        SurveyResult result = new SurveyResult(id, letterMessage.keySet());
        result.tally();
        return result.getPercentages();
    }
    public int getID()
    {
        return id;
    }
    public String getChoice(String s)
    {
        return letterMessage.get(s.toUpperCase());
    }
    public void send(Player p)
    {
        // /choose A ID
        String divider = ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "--------------------";
        p.sendMessage(divider);
        p.sendMessage(ChatColor.GOLD + question);
        for(String s : letterMessage.keySet())
        {
            TextComponent message = new TextComponent( ChatColor.GOLD + s.toUpperCase() + "" + ChatColor.GRAY + ": " + ChatColor.BLUE + letterMessage.get(s));
            message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/choose " + s.toUpperCase() + " " + id ));
            message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.BLUE + "Click to choose").create() ) );
            p.spigot().sendMessage(message);
        }
        p.sendMessage(divider);

    }
}
