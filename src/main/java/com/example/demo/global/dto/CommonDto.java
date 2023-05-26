package com.example.demo.global.dto;


import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonDto {
    private Long roomId;
    private int round; //현재 라운드
    private List<Integer> action = new ArrayList<>(); //행동버튼 눌림 여부

    private int currentTurn ; // 변경된 순서
    private List<List<Integer>> turnArray; //[[turn,남은농부수],[turn,남은농부수],[turn,남은농부수]]

}
