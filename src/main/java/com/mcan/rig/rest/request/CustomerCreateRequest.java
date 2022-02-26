package com.mcan.rig.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
