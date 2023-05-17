package com.example.demo.domain.gameRoom.api;


import com.example.demo.domain.gameRoom.entity.GameRoom;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/game-rooms")
public class GameRoomApi {

    @GetMapping()
    public List<GameRoom> findAllGameRooms(){
        ArrayList<GameRoom> gameRooms = new ArrayList<>(5);
        for (int i=0;i<5;i++){
            GameRoom gameRoom = GameRoom.builder().
                    id(i).
                    people(3)
                    .build();
            gameRooms.add(gameRoom);
        }

        return gameRooms;
    }
    @PostMapping()
    public String createGameRoom(){
        GameRoom gameRoom = GameRoom.builder()
                .people(3)
                .id(4)
                .build();
        return gameRoom.getId()+"is created";
    }




}
