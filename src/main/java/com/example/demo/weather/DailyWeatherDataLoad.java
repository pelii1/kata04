package com.example.demo.weather;

import com.example.demo.DataLoad;
import com.example.demo.weather.dto.DailyWeather;

public class DailyWeatherDataLoad implements DataLoad<DailyWeather> {
    @Override
    public DailyWeather convertFromString(String row) {
        return DailyWeather.of(row);
    }

    @Override
    public boolean valid(DailyWeather item) {
        return item.valid();
    }
}
