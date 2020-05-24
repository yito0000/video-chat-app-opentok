package com.yito0000.videochat.backend.InMemoryInfrastructure.mapper;

import com.yito0000.videochat.backend.InMemoryInfrastructure.datamodel.RoomData;
import com.yito0000.videochat.backend.domain.RoomId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class RoomDataMapper {

    private List<RoomData> roomDataList = new ArrayList<>();

    public RoomData registerRoomRecord(String apiKey, String sessionId, String name) {

        String roomId = UUID.randomUUID().toString();
        RoomData roomData = new RoomData(roomId, sessionId, apiKey, name);

        roomDataList.add(roomData);

        roomDataList.stream().forEach(data -> {
            log.info(data.toString());
        });

        return roomData;
    }

    public RoomData findById(RoomId roomId) {
        return roomDataList.stream()
                .filter(roomData -> roomData.getRoomId().equals(roomId.getValue()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
