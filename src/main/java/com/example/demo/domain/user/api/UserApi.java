package com.example.demo.domain.user.api;

import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.global.config.websocketConfig.StompPrincipal;
import com.example.demo.global.dto.CommonReq;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserApi {
    private final SimpMessagingTemplate template;
    private final UserService userService;

    @MessageMapping(value = "/user/init")
    public void intiUser(StompPrincipal principal,CommonReq request){
        log.info(principal.getName());
        log.info("-------------------------");
        if(userService.initUser(request.getRoomId())) {
            log.info(request.getRoomId().toString());
            template.convertAndSendToUser(principal.getName(),
                    "/sub/game-room/"+request.getRoomId()
                    , "OK");

        }
        else{
            template.convertAndSendToUser(principal.getName(),
                    "/sub/game-room/" + request.getRoomId()
                    , "FULL");
        }

    }
    @MessageMapping("/user/test")
    public void test(CommonReq request){
        template.convertAndSend("/sub/game-room/"+request.getRoomId(),"hello");
    }
}
