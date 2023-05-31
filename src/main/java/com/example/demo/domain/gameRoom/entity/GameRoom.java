package com.example.demo.domain.gameRoom.entity;

import com.example.demo.global.converter.StringToArrayConverter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
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
    private int currentTurn; //현재 순서

    @Column
    private int people; //현재 인원수
    @Convert(converter = StringToArrayConverter.class)
    private List<Integer> jobCards;// 나눠줄 직업카드
    @Convert(converter = StringToArrayConverter.class)
    private List<Integer> subCards;// 나눠줄 보조카드
    public int getPeopleCount(){
        return this.people;
    }
    public void addUserCount(){
        this.people++;
    }
    public int getCurrentTurn(){
        return (this.currentTurn%4);
    }
}
