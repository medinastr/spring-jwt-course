package com.medinastr.security01.model.dto.response;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ServerResponse<P> {

    private final String message;
    private final Integer status;
    private final String path;
    private final Long timestamp;
    private final P payload;

    public ServerResponse(String message, Integer status, String path, P payload) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = Instant.now().getEpochSecond();
        this.payload = payload;
    }
}
