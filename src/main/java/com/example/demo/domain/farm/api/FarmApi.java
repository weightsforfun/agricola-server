package com.example.demo.domain.farm.api;

import com.example.demo.domain.farm.dto.FarmDto;
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
    public void updateFarm(FarmDto message){
        //농장업데이트
        template.convertAndSend("/sub/game-room/"+message.getRoomId(),
                message);
    }

}
