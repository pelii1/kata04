package com.example.demo.league.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class Goal {
    private int value;

    public static Goal of(String value) {
        return new Goal(Integer.parseInt(value));
    }

    public static Goal of(int value) {
        return new Goal(value);
    }


    public Goal minusFrom(Goal other) {
        if (other == null) {
            return Goal.of(getValue());
        }

        return Goal.of(other.getValue() - getValue());
    }
}
