package com.yito0000.videochat.backend.domain;

import lombok.Getter;

public class RoomConnectionId {

    @Getter
    private final String connectionValue;

    @Getter
    private final String apiKey;

    public RoomConnectionId(String connectionValue, String apiKey) {
        this.connectionValue = connectionValue;
        this.apiKey = apiKey;
    }
}
