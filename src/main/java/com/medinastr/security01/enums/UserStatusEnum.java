package com.medinastr.security01.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    ENABLE("ENABLE"),
    DISABLED("DISABLED"),
    DELETED("DELETED");
    private final String status;
}

