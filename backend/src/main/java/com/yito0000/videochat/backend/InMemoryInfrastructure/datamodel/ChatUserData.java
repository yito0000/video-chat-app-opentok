package com.yito0000.videochat.backend.InMemoryInfrastructure.datamodel;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ChatUserData {

    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    private final String connectionId;

    @Getter
    private final String roomId;

    public ChatUserData(String id, String name, String connectionId, String roomId) {
        this.id = id;
        this.name = name;
        this.connectionId = connectionId;
        this.roomId = roomId;
    }
}
