package com.yito0000.videochat.backend.domain;

import lombok.Getter;

public class ChatUserEntity {

    @Getter
    private final ChatUserId chatUserId;

    @Getter
    private final String name;

    @Getter
    private final RoomId destinationRoomId;

    public ChatUserEntity(ChatUserId chatUserId, String name, RoomId destinationRoomId) {
        this.chatUserId = chatUserId;
        this.name = name;
        this.destinationRoomId = destinationRoomId;
    }
}
