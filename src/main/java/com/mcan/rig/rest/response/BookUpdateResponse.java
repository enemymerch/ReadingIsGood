package com.mcan.rig.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateResponse extends BaseResponse{
    private String name;
    private String author;
    private Long stock;
    private Double price;
}
