package com.yito0000.videochat.backend.api.converter;

import com.yito0000.videochat.backend.api.datamodel.RoomJson;
import com.yito0000.videochat.backend.api.datamodel.UserInfoJson;
import com.yito0000.videochat.backend.domain.ChatUserEntity;
import com.yito0000.videochat.backend.domain.RoomEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RoomJsonConverter {

    public static RoomJson convert(RoomEntity roomEntity, List<ChatUserEntity> chatUserEntityList) {
        return RoomJson.builder()
                .roomId(roomEntity.getRoomId().getValue())
                .roomName(roomEntity.getName())
                .userInfoList(chatUserEntityList.stream()
                        .map(chatUserEntity -> UserInfoJson.builder()
                                .userId(chatUserEntity.getChatUserId().getId())
                                .userName(chatUserEntity.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
