package com.example.demo.domain.card.api;


import com.example.demo.domain.card.dto.CardDto;
import com.example.demo.global.dto.CommonReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CardApi {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/card/update")
    public void cardEnroll(CardDto cardDto){
        template.convertAndSend("/sub/game-room/"+cardDto.getRoomId()
                ,cardDto);

    }


}
