package com.example.demo.global.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor

public class CommonReq {
    private  Long roomId; //방 ID
    private  Long userId; //자원 업데이트 된 유저 id
    private  List<Long> action = new ArrayList<>(); //행동버튼 눌림 여부
    private  String content;// 각 req마다 다를 내용 임시로 common에 설정


}
