package com.example.demo.domain.user.service;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import com.example.demo.domain.user.repository.UserRepository;
import javax.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final GameRoomRepository gameRoomRepository;
    private final UserRepository userRepository;
    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public Integer initUser(Long gameRoomId){
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId)
                .orElseThrow(() -> new RuntimeException("no game room"));
        if(gameRoom.getPeopleCount()<4){
            gameRoom.addUserCount();
            return gameRoom.getPeopleCount();

        }
        else {
            return -1;
        }
    }
    public void initGame(){
        //user card 분배
        //행동 버튼 상태,round, turn api 계층에 전달
    }

}
