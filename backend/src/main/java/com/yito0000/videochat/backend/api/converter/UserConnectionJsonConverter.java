package com.yito0000.videochat.backend.api.converter;

import com.yito0000.videochat.backend.api.datamodel.UserConnectionJson;
import com.yito0000.videochat.backend.api.datamodel.UserInfoJson;
import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.ChatUserEntity;

public class UserConnectionJsonConverter {

    public static UserConnectionJson convert(RoomEntity roomEntity, ChatUserEntity chatUserEntity) {
        UserConnectionJson json = UserConnectionJson.builder()
                .roomId(chatUserEntity.getDestinationRoomId().getValue())
                .apiKey(roomEntity.getRoomConnectionId().getApiKey())
                .roomConnectionId(roomEntity.getRoomConnectionId().getConnectionValue())
                .roomName(roomEntity.getRoomId().getValue())
                .userInfo(UserInfoJson.builder()
                        .userId(chatUserEntity.getChatUserId().getId())
                        .userName(chatUserEntity.getName())
                        .build())
                .userConnectionToken(chatUserEntity.getChatUserId().getConnectionToken())
                .build();

        return json;
    }
}
