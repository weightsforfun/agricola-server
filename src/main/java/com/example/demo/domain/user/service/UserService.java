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
    public boolean initUser(Long gameRoomId){
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId)
                .orElseThrow(() -> new RuntimeException("no game room"));
        if(gameRoom.checkCount()<4){
            gameRoom.addUserCount();
            //user 생성하고 턴부여
            //resource , farm 생성하고 build
            //user id 제공
            return true;

        } else if (gameRoom.checkCount()==4) {
            initGame();
            return true;
        } else{
            return false;
        }
    }
    public void initGame(){
        //user card 분배
        //행동 버튼 상태,round, turn api 계층에 전달
    }

}
