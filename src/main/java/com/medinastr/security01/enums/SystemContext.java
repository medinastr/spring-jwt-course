package com.medinastr.security01.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemContext {

    WEB("WEB"),
    MOBILE("MOBILE");
    private final String context;
}
