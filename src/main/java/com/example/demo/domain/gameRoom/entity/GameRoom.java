package com.example.demo.domain.gameRoom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameRoom {
    @Id
    @GeneratedValue
    @Column(name = "gameRoom_id")
    private Long id; //추후 long 으로 변경 repository 계층 구현후

    //user 4명

    @Column
    private int round; //게임 라운드

    //순서
    @Column
    private int people; //현재 인원수
    public boolean checkCount(){
        return this.people<4;
    }
    public void addUserCount(){
        this.people++;
    }
}
