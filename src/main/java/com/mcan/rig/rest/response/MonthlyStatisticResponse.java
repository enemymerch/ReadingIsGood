package com.mcan.rig.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyStatisticResponse {
    private Double totalPurchasedAmount;
    private Long totalBookCount;
    private Long totalOrderCount;
    private int month;
    private int year;
}
