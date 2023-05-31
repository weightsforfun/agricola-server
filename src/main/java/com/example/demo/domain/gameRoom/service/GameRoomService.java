package com.example.demo.domain.gameRoom.service;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GameRoomService {
    private final GameRoomRepository gameRoomRepository;

    public Long createGameRoom(){
        ArrayList<Integer> cards = new ArrayList<>();
        for(int i=1;i<29;i++){
            cards.add(i);
        }
        GameRoom gameRoom = GameRoom.builder()
                .round(1)
                .people(0)
                .currentTurn(1)
                .jobCards(cards)
                .subCards(cards)
                .build();
        GameRoom savedGameRoom = gameRoomRepository.save(gameRoom);
        List<Integer> jobCards = gameRoom.getJobCards();
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
