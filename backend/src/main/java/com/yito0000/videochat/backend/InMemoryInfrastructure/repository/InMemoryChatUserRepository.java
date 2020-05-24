package com.yito0000.videochat.backend.InMemoryInfrastructure.repository;

import com.yito0000.videochat.backend.InMemoryInfrastructure.datamodel.ChatUserData;
import com.yito0000.videochat.backend.InMemoryInfrastructure.mapper.ChatUserMapper;
import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.ChatUserEntity;
import com.yito0000.videochat.backend.domain.ChatUserId;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.domain.repository.ChatUserRepository;
import com.yito0000.videochat.backend.driver.OpenTokDriver;
import com.yito0000.videochat.backend.exception.DriverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InMemoryChatUserRepository implements ChatUserRepository {

    private final OpenTokDriver openTokDriver;
    private final ChatUserMapper chatUserMapper;

    @Override
    public ChatUserEntity registerChatUser(RoomEntity roomEntity, String userName) throws DriverException {

        String memberConnectionId = openTokDriver.generateToken(roomEntity.getRoomConnectionId().getConnectionValue());
        ChatUserData chatUserData = chatUserMapper.register(userName, memberConnectionId, roomEntity.getRoomId().getValue());

        ChatUserId user = new ChatUserId(chatUserData.getId(), chatUserData.getConnectionId());
        ChatUserEntity entity = new ChatUserEntity(user, chatUserData.getName(), roomEntity.getRoomId());
        return entity;
    }

    @Override
    public List<ChatUserEntity> findByRoomId(RoomId roomId) {
        List<ChatUserData> chatUserDataList = chatUserMapper.findByRoomId(roomId.getValue());
        return chatUserDataList.stream().map(chatUserData -> {
            ChatUserId user = new ChatUserId(chatUserData.getId(), chatUserData.getConnectionId());
            ChatUserEntity entity = new ChatUserEntity(user, chatUserData.getName(), roomId);
            return entity;
        }).collect(Collectors.toList());
    }
}
