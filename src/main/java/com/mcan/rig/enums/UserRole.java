package com.mcan.rig.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    CUSTOMER("customer"),
    ADMIN("admin");

    private String value;
}
