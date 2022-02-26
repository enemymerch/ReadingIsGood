package com.mcan.rig.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequest {
    private Long quantity;
    private Long bookId;
    private String username;
}
