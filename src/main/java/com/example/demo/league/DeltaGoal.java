package com.example.demo.league;

import com.example.demo.Calculate;
import com.example.demo.league.dto.Team;

public class DeltaGoal implements Calculate<Team> {
    @Override
    public Team calculation(Team one, Team other) {
        return one.isLessDelta(other) ? one : other;
    }
}
