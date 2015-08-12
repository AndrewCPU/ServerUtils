package net.Andrewcpu.UTILS.Survey;

import net.Andrewcpu.UTILS.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by andrewpstein on 8/12/15.
 */
public class SurveyResult {
    private HashMap<String,Double> percentages = new HashMap<>();
    private int ID;
    private Set<String> choices;
    public SurveyResult(int ID, Set<String> choices)
    {
        this.choices = choices;
        this.ID = ID;
        tally();
    }
    public void tally()
    {
        Main main = Main.main;
        //List<String> choices = main.getConfig().getStringList("Survey.Choices.ID" + ID);
        HashMap<String,Integer> numbers = new HashMap<>();
        for(String s : choices)
        {
            numbers.put(s.toUpperCase(), main.getConfig().getInt("Survey.Scores.ID" + ID + "." + s.toUpperCase()));
        }
        int total = 0;
        for(int i : numbers.values())
        {
            total+=i;
        }
        for(String s : numbers.keySet())
        {
            double perc = numbers.get(s) / total;
            perc *=10;
            percentages.put(s,perc);
        }
    }

    public HashMap<String, Double> getPercentages() {
        return percentages;
    }
}
