package com.example.demo.weather.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
@ToString
public class DailyWeather {
    public static final int INVALID_ID = -1;

    @Getter
    private int id;
    private Temperature minTemperature;
    private Temperature maxTemperature;

    public static DailyWeather of(String row) {
        if (row != null && row.trim().length() > 0) {
            String[] rowParts = row.split(";");
            if (rowParts.length > 3 && isNumeric(rowParts[0])
                    && isNumeric(rowParts[1]) && isNumeric(rowParts[2])) {
                return new DailyWeather(Integer.parseInt(rowParts[0]),
                        Temperature.of(rowParts[1]), Temperature.of(rowParts[2]));
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("Invalid row: {}", row);
        }
        return ofError();
    }

    public static DailyWeather ofError() {
        return new DailyWeather(INVALID_ID, Temperature.of(Integer.MAX_VALUE), Temperature.of(0));
    }

    public Temperature delta() {
        return maxTemperature.minusFrom(minTemperature);
    }

    public boolean isLessDelta(DailyWeather dailyWeather) {
        if (dailyWeather != null && dailyWeather.valid()) {
            return delta().getValue() < dailyWeather.delta().getValue();
        }

        return true;
    }

    public boolean valid() {
        return getId() != INVALID_ID;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
