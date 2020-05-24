package com.yito0000.videochat.backend.InMemoryInfrastructure.datamodel;

import lombok.*;

@ToString
public class RoomData {

    @Getter
    private final String roomId;

    @Getter
    private final String sessionId;

    @Getter
    private final String apiKey;

    @Getter
    private final String name;

    public RoomData(String roomId, String sessionId, String apiKey, String name) {
        this.roomId = roomId;
        this.sessionId = sessionId;
        this.apiKey  = apiKey;
        this.name = name;
    }
}
