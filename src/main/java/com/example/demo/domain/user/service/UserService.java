package com.example.demo.domain.user.service;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final GameRoomRepository gameRoomRepository;

    public boolean initUser(Long gameRoomId){
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId)
                .orElseThrow(() -> new RuntimeException("no game room"));
        if(gameRoom.checkCount()){
            gameRoom.addUserCount();
            return true;
        }else{
            return false;
        }
    }

}
