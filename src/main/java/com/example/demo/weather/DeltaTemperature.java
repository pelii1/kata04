package com.example.demo.weather;

import com.example.demo.Calculate;
import com.example.demo.weather.dto.DailyWeather;

public class DeltaTemperature implements Calculate<DailyWeather> {
    @Override
    public DailyWeather calculation(DailyWeather one, DailyWeather other) {
        return one.isLessDelta(other) ? one : other;
    }
}
