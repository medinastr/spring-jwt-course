package com.medinastr.security01.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthoritiesEnum {

    READ("READ"),
    ADMIN("ADMIN");
    private final String authority;
}
