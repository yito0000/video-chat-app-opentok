package com.yito0000.videochat.backend.domain.repository;

import com.opentok.exception.OpenTokException;
import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.exception.DriverException;

public interface RoomRepository {

    RoomEntity registerNewRoomEntity(String name) throws DriverException;

    RoomEntity findById(RoomId roomId);
}
