package com.example.demo.domain.gameRoom.api;


import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.service.GameRoomService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/game-rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class GameRoomApi {
    private final SimpMessagingTemplate template;
    private final GameRoomService gameRoomService;

    @GetMapping()
    public List<GameRoom> findAllGameRooms(){
        List<GameRoom> gameRooms = gameRoomService.findAllGameRooms();

        return gameRooms;
    }
    @PostMapping()
    public Long createGameRoom(){
        Long gameRoomID = gameRoomService.createGameRoom();
        return gameRoomID;
    }
    @GetMapping("/{id}")
    public GameRoom findGameRoom(@PathVariable("id") Long id){
        //인원수 front 에서 체크해서 4명이면 애초에 못들어가게 막고
        //interceptor 에서도 동시접근하는 경우 생각해서 인원수 고려해서 막기
        GameRoom gameRoom = gameRoomService.findGameRoom(id);
        return gameRoom;
    }



}
