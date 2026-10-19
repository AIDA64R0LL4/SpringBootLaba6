package com.example.ryzhkov.service;

import com.example.ryzhkov.model.Positions;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = LocalDate.now().isLeapYear() ? 366 : 365;
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays) {
        if (positions == null || !positions.isManager()) {
            throw new IllegalArgumentException("Квартальная премия доступна только для управленцев (isManager = true)!");
        }
        int daysInYear = LocalDate.now().isLeapYear() ? 366 : 365;
        return (salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays) / 4;
    }
}