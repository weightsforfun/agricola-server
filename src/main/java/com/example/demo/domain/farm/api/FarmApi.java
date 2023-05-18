package com.example.demo.domain.farm.api;

import com.example.demo.global.dto.CommonReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FarmApi {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/farm/update")
    public void updateFarm(CommonReq request){
        //농장업데이트
        template.convertAndSend("/sub/game-room/"+request.getRoomId(),
                request.getUserId()+"th user's farm updated");
    }

}
