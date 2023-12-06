package com.example.demo;

import com.example.demo.league.DeltaGoal;
import com.example.demo.league.TeamDataLoad;
import com.example.demo.league.dto.Team;
import com.example.demo.weather.DailyWeatherDataLoad;
import com.example.demo.weather.DeltaTemperature;
import com.example.demo.weather.dto.DailyWeather;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class Kata04 {
    /**
     * yxxvxccvbcvb
     * @param args
     */
    public static void main(String... args) {
        FileOperate<DailyWeather> weatherFile = FileOperate.of(Path.of("src/main/resources/weather.dat"),
                new DeltaTemperature(), new DailyWeatherDataLoad());
        weatherFile.load();

        log.info("weatherFile: {}", weatherFile.calculate());

        log.info("Footbal.dat állományt megváltoztattam nem az eredeti!");
        FileOperate<Team> leaugeFile = FileOperate.of(Path.of("src/main/resources/football.dat"),
                new DeltaGoal(), new TeamDataLoad());
        leaugeFile.load();

        log.info("leagueFile: {}", leaugeFile.calculate());
    }
}
