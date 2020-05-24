package com.yito0000.videochat.backend.service;

import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.domain.repository.RoomRepository;
import com.yito0000.videochat.backend.exception.DriverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomEntity registerNewRoom(String roomName) throws DriverException {
        return roomRepository.registerNewRoomEntity(roomName);
    }

    public RoomEntity findById(RoomId roomId) {
        return roomRepository.findById(roomId);
    }
}
