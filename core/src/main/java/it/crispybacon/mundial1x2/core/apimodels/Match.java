package it.crispybacon.mundial1x2.core.apimodels;

import java.util.Date;

/**
 * Created by Jameido on 15/06/2018.
 */
public class Match {
    public String _id;
    public int number;
    public Date date;
    public Team team1;
    public Team team2;
    public Integer score1;
    public Integer score2;
    public String group;
    public String matchDay;
    public String stadium;

    public Match() {
    }

    public int getNumber() {
        return number;
    }
}
