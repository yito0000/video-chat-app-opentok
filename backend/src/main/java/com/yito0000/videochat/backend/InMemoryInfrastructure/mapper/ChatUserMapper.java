package com.yito0000.videochat.backend.InMemoryInfrastructure.mapper;

import com.yito0000.videochat.backend.InMemoryInfrastructure.datamodel.ChatUserData;
import com.yito0000.videochat.backend.domain.RoomId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ChatUserMapper {

    private List<ChatUserData> chatUserDataList = new ArrayList<>();

    public ChatUserData register(String name, String connectionId, String roomId) {

        String id = UUID.randomUUID().toString();
        ChatUserData memberData = new ChatUserData(id, name, connectionId, roomId);

        chatUserDataList.add(memberData);

        chatUserDataList.stream().forEach(data -> {
            log.info(data.toString());
        });

        return memberData;
    }

    public List<ChatUserData> findByRoomId(String roomId) {
        return chatUserDataList.stream().filter(chatUserData -> chatUserData.getRoomId().equals(roomId)).collect(Collectors.toList());
    }
}
