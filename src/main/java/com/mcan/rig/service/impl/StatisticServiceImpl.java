package com.mcan.rig.service.impl;

import com.mcan.rig.persistance.entity.Order;
import com.mcan.rig.rest.response.MonthlyStatisticResponse;
import com.mcan.rig.service.OrderService;
import com.mcan.rig.service.StatisticService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {


    private final OrderService orderService;

    public StatisticServiceImpl (OrderService orderService) {this.orderService = orderService;}

    @Override
    public MonthlyStatisticResponse getStatistics (int month, int year) throws ParseException {
        MonthlyStatisticResponse response = new MonthlyStatisticResponse();
        Date startDate = getDateObject(month, year);
        Date endDate = DateUtils.addMonths(startDate, 1);
        List<Order> orders = orderService.findByDateInterval(startDate, endDate);
        Double totalPurchasedAmount = (double) 0;
        Long totalBookCount = 0L;
        Long totalOrderCount = Long.valueOf(orders.size());
        for (Order order : orders) {
            totalBookCount += order.getQuantity();
            totalPurchasedAmount += order.getTotalPrice();
        }
        response.setTotalBookCount(totalBookCount);
        response.setTotalOrderCount(totalOrderCount);
        response.setTotalPurchasedAmount(totalPurchasedAmount);
        response.setMonth(month);
        response.setYear(year);
        return response;
    }

    public Date getDateObject (Integer month, Integer year) throws ParseException {
        String dateString = "01/" + month.toString() + "/" + year.toString();
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }

}
