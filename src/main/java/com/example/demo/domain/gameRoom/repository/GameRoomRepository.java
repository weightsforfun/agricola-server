package com.example.demo.domain.gameRoom.repository;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {

}
