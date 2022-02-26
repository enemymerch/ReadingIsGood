package com.mcan.rig.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderGetResponse extends BaseResponse {

    private Long quantity;
    private String customerUsername;
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private Double totalPrice;
}
