package com.mcan.rig.rest.controller;

import com.mcan.rig.rest.response.MonthlyStatisticResponse;
import com.mcan.rig.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController (StatisticService statisticService) {this.statisticService = statisticService;}

    @GetMapping
    public MonthlyStatisticResponse getMonthlyStatisticResponse (@RequestParam("month") int month, @RequestParam("year") int year) throws ParseException {
        return statisticService.getStatistics(month, year);
    }
}
