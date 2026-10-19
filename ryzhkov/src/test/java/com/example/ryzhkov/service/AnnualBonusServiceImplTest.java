package com.example.ryzhkov.service;

import org.junit.jupiter.api.Test;
import com.example.ryzhkov.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);
        int daysInYear = java.time.LocalDate.now().isLeapYear() ? 366 : 365;
        double expected = salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusForManager() {
        Positions position = Positions.CTO;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 150000.00;
        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);
        int daysInYear = java.time.LocalDate.now().isLeapYear() ? 366 : 365;
        double expected = (salary * bonus * daysInYear * position.getPositionCoefficient() / workDays) / 4;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusForNonManagerThrowsException() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        assertThatThrownBy(() -> new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays))
                .isInstanceOf(IllegalArgumentException.class);
    }
}