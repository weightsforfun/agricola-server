package com.example.demo.global.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonReq {
    private final Long roomId; //방 ID
    private final Long userId; //자원 업데이트 된 유저 id
    private final List<Long> action = new ArrayList<>(); //행동버튼 눌림 여부
    private final String content;// 각 req마다 다를 내용 임시로 common에 설정


}
