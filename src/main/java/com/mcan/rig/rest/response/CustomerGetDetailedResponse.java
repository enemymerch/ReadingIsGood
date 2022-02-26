package com.mcan.rig.rest.response;

import com.mcan.rig.enums.UserRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerGetDetailedResponse extends BaseResponse{

    private String firstName;
    private String lastName;
    private String username;
    private UserRole role;
}
