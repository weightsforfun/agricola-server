package com.example.demo.domain.gameRoom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
public class GameRoom {
    @Id
    @GeneratedValue
    @Column(name = "gameRoom_id")
    private Long id; //추후 long으로 변경 repository계층 구현후

    //user 4명

    @Column
    private int round; //게임 라운드

    //순서
    @Column
    private int people; //현재 인원수
}
