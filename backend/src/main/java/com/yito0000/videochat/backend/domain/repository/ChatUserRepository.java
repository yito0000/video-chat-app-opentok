package com.yito0000.videochat.backend.domain.repository;

import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.ChatUserEntity;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.exception.DriverException;

import java.util.List;

public interface ChatUserRepository {

    ChatUserEntity registerChatUser(RoomEntity roomEntity, String userName) throws DriverException;

    List<ChatUserEntity> findByRoomId(RoomId roomId);
}
