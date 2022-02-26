package com.mcan.rig.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {

    private String name;
    private String author;
    private Long stock;
    private Double price;
}
