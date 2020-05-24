package com.yito0000.videochat.backend.api.datamodel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomJson {

    private String roomId;

    private String roomName;

    private List<UserInfoJson> userInfoList;
}
