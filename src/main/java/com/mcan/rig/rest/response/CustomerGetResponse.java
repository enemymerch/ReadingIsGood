package com.mcan.rig.rest.response;

import com.mcan.rig.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerGetResponse extends BaseResponse {

    private String name;
    private String username;
    private UserRole role;
}
