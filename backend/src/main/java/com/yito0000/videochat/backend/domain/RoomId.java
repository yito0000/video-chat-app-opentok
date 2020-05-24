package com.yito0000.videochat.backend.domain;

import lombok.Getter;

public class RoomId {

    @Getter
    private final String value;

    public RoomId(String value) {
        this.value = value;
    }
}
