package com.example.demo.domain.resource.api;

import com.example.demo.domain.resource.dto.ResourceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ResourceApi {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/resource/update")
    public void updateResource(ResourceDto resourceDto){
        //자원 업데이트
        template.convertAndSend("/sub/game-room/"+resourceDto.getRoomId()
        ,resourceDto);
    }

}
