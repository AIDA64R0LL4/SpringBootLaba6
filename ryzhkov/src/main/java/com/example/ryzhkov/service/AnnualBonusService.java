package com.example.ryzhkov.service;

import com.example.ryzhkov.model.Positions;
import org.springframework.stereotype.Service;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays);
}