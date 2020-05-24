package com.yito0000.videochat.backend.domain;

import lombok.Getter;

public class ChatUserId {

    @Getter
    private final String id;

    @Getter
    private final String connectionToken;

    public ChatUserId(String id, String connectionToken) {
        this.id = id;
        this.connectionToken = connectionToken;
    }
}
