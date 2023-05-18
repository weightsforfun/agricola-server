package com.example.demo.global.dto;


import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonRes {
    private final Long userId; //자원 업데이트 된 유저 id
    private final int round; //현재 라운드
    private final List<Long> action = new ArrayList<>(); //행동버튼 눌림 여부

    private final Long order ; // 변경된 순서

}
