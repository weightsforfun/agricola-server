package com.example.demo.domain.gameRoom.service;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GameRoomService {
    private final GameRoomRepository gameRoomRepository;

    public Long createGameRoom(){
        GameRoom gameRoom = GameRoom.builder()
                .round(1)
                .build();
        GameRoom savedGameRoom = gameRoomRepository.save(gameRoom);
        return savedGameRoom.getId();

    }
    public List<GameRoom> findAllGameRooms(){
        List<GameRoom> gameRoomList = gameRoomRepository.findAll();
        return gameRoomList;
    }
    public GameRoom findGameRoom(Long id){
        GameRoom gameRoom = gameRoomRepository.findById(id).orElseThrow(
                () -> new RuntimeException("no gameRoom"));
        return gameRoom;
    }

}
