package com.yito0000.videochat.backend.domain;

import lombok.Getter;

public class RoomEntity {

    @Getter
    private final RoomId roomId;

    @Getter
    private final RoomConnectionId roomConnectionId;

    @Getter
    private final String name;

    public RoomEntity(RoomId roomId, RoomConnectionId roomConnectionId, String name) {
        this.roomId = roomId;
        this.roomConnectionId = roomConnectionId;
        this.name = name;
    }
}
