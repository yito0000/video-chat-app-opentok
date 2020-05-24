package com.yito0000.videochat.backend.api.datamodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoJson {

    private String userId;

    private String userName;

}
