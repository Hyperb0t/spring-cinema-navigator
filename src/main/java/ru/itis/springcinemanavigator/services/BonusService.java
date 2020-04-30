package ru.itis.springcinemanavigator.services;

import org.springframework.stereotype.Service;

@Service
public class BonusService {

    private final float bonusPercentage = 0.1f;

    public Integer getAddBonuses(Integer orderPrice) {
        return Math.round(orderPrice*bonusPercentage);
    }
}
