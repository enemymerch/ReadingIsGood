package com.mcan.rig.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateResponse extends BaseResponse {
    private Long quantity;
    private String customerUsername;
    private String bookName;
    private Double totalPrice;
}
