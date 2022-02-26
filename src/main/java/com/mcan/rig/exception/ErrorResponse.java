package com.mcan.rig.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String method;
    private String path;
    private Date timestamp;
}
