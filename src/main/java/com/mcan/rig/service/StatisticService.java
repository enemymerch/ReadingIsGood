package com.mcan.rig.service;

import com.mcan.rig.rest.response.MonthlyStatisticResponse;

import java.text.ParseException;

public interface StatisticService {
    MonthlyStatisticResponse getStatistics (int month, int year) throws ParseException;
}
