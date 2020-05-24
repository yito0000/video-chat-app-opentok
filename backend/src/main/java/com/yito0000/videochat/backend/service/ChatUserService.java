package com.yito0000.videochat.backend.service;

import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.ChatUserEntity;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.domain.repository.RoomRepository;
import com.yito0000.videochat.backend.domain.repository.ChatUserRepository;
import com.yito0000.videochat.backend.exception.DriverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;

    public ChatUserEntity createChatUserInRoom(RoomEntity roomEntity, String memberName) throws DriverException {
        ChatUserEntity chatUserEntity = chatUserRepository.registerChatUser(roomEntity, memberName);
        return chatUserEntity;
    }

    public List<ChatUserEntity> findByRoomId(RoomId roomId) {
        return chatUserRepository.findByRoomId(roomId);
    }
}
