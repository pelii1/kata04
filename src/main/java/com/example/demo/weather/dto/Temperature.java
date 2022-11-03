package com.example.demo.weather.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Temperature {
    private int value;

    public static Temperature of(int value) {
        return new Temperature(value);
    }

    public static Temperature of(String value) {
        return new Temperature(Integer.parseInt(value));
    }

    private Temperature(int value) {
        this.value = value;
    }

    public Temperature minusFrom(Temperature temperature) {
        if (temperature != null) {
            return new Temperature(temperature.getValue() - getValue());
        }

        return new Temperature(getValue());
    }
}
