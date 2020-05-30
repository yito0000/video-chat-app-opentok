package com.yito0000.videochat.backend.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yito0000.videochat.backend.api.converter.RoomJsonConverter;
import com.yito0000.videochat.backend.api.converter.UserConnectionJsonConverter;
import com.yito0000.videochat.backend.api.datamodel.RoomJson;
import com.yito0000.videochat.backend.api.datamodel.UserConnectionJson;
import com.yito0000.videochat.backend.domain.ChatUserEntity;
import com.yito0000.videochat.backend.domain.RoomEntity;
import com.yito0000.videochat.backend.domain.RoomId;
import com.yito0000.videochat.backend.exception.DriverException;
import com.yito0000.videochat.backend.service.ChatUserService;
import com.yito0000.videochat.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;
    private final ChatUserService chatUserService;

    @PostMapping(value = "/api/create/room", produces = MediaType.APPLICATION_JSON_VALUE)
    public String postJoinNewRoom(@RequestParam("userName") String userName, @RequestParam("roomName") String roomName) throws DriverException, JsonProcessingException {
        RoomEntity roomEntity = roomService.registerNewRoom(roomName);
        ChatUserEntity memberEntity = chatUserService.createChatUserInRoom(roomEntity, userName);
        UserConnectionJson memberJson = UserConnectionJsonConverter.convert(roomEntity, memberEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(memberJson);
    }

    @GetMapping(value = "/api/room/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRoom(@PathVariable("roomId") String pathValueRoomId) throws JsonProcessingException {
        RoomId roomId = new RoomId(pathValueRoomId);
        RoomEntity roomEntity = roomService.findById(roomId);
        List<ChatUserEntity> chatUserEntityList = chatUserService.findByRoomId(roomId);
        RoomJson roomJson = RoomJsonConverter.convert(roomEntity, chatUserEntityList);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(roomJson);
    }

    @PostMapping(value = "/api/room/{roomId}/create/connection", produces = MediaType.APPLICATION_JSON_VALUE)
    public String postJoinRoom(@PathVariable("roomId") String roomId, @RequestParam("userName") String userName) throws DriverException, JsonProcessingException {
        RoomEntity roomEntity = roomService.findById(new RoomId(roomId));
        ChatUserEntity memberEntity = chatUserService.createChatUserInRoom(roomEntity, userName);
        UserConnectionJson memberJson = UserConnectionJsonConverter.convert(roomEntity, memberEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(memberJson);
    }

}
