package com.example.demo.league;

import com.example.demo.DataLoad;
import com.example.demo.league.dto.Team;

public class TeamDataLoad implements DataLoad<Team> {
    @Override
    public Team convertFromString(String row) {
        return Team.of(row);
    }

    @Override
    public boolean valid(Team item) {
        return item.valid();
    }
}
