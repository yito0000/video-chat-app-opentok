package com.yito0000.videochat.backend.api.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserConnectionJson {

    private String roomId;

    private String apiKey;

    private String roomConnectionId;

    private String roomName;

    private UserInfoJson userInfo;

    private String userConnectionToken;

}
