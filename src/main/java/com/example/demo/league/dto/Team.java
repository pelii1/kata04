package com.example.demo.league.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
@ToString
public class Team {
    public static final String INVALID_NAME = "";
    @Getter
    private String name;
    private Goal opponentGoal;
    private Goal mainGoal;

    public static Team of(String row) {
        if (row != null && row.trim().length() > 0) {
            String[] rowParts = row.split(";");

            if (rowParts.length > 9 && StringUtils.hasText(rowParts[1])
                    && isNumeric(rowParts[6]) && isNumeric(rowParts[8])) {
                return new Team(rowParts[1], Goal.of(rowParts[6]), Goal.of(rowParts[8]));
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("Invalid row: {}", row);
        }

        return ofError();
    }

    public Goal delta() {
        return opponentGoal.minusFrom(mainGoal);
    }

    public boolean isLessDelta(Team other) {
        if (other != null && other.valid()) {
            return delta().getValue() < other.delta().getValue();
        }

        return true;
    }

    public boolean valid() {
        return !getName().equals(INVALID_NAME);
    }

    public static Team ofError() {
        return new Team(INVALID_NAME, Goal.of(0), Goal.of(0));
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
