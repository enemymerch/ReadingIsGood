package com.mcan.rig.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerGetOrdersResponse extends BaseResponse {
    private String name;
    private String username;
    private List<OrderGetResponse> orders;
}
