package com.yito0000.videochat.backend.InMemoryInfrastructure.repository;

import com.yito0000.videochat.backend.InMemoryInfrastructure.mapper.RoomDataMapper;
import com.yito0000.videochat.backend.InMemoryInfrastructure.datamodel.RoomData;
import com.yito0000.videochat.backend.domain.RoomConnectionId;
import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.domain.repository.RoomRepository;
import com.yito0000.videochat.backend.driver.OpenTokDriver;
import com.yito0000.videochat.backend.exception.DriverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InMemoryRoomRepository implements RoomRepository {

    private final OpenTokDriver openTokDriver;

    private final RoomDataMapper roomDataMapper;

    @Override
    public RoomEntity registerNewRoomEntity(String name) throws DriverException {

        int apiKey = openTokDriver.fetchApiKey();
        String sessionId = openTokDriver.createSessionId();
        RoomData roomData = roomDataMapper.registerRoomRecord(String.valueOf(apiKey), sessionId, name);

        RoomConnectionId roomConnectionId = new RoomConnectionId(roomData.getSessionId(), roomData.getApiKey());
        RoomId roomId = new RoomId(roomData.getRoomId());
        RoomEntity roomEntity = new RoomEntity(roomId, roomConnectionId, name);

        return roomEntity;
    }

    @Override
    public RoomEntity findById(RoomId roomId) {
        RoomData roomData = roomDataMapper.findById(roomId);

        RoomConnectionId roomConnectionId = new RoomConnectionId(roomData.getSessionId(), roomData.getApiKey());
        RoomEntity roomEntity = new RoomEntity(roomId, roomConnectionId, roomData.getName());

        return roomEntity;
    }
}
