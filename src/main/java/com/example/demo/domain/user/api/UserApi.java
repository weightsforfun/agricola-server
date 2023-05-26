package com.example.demo.domain.user.api;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.global.config.websocketConfig.StompPrincipal;
import com.example.demo.global.dto.CommonDto;
import com.example.demo.global.dto.CommonReq;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Integer turn = userService.initUser(request.getRoomId());
        if(turn<4) {
            log.info(request.getRoomId().toString());
            template.convertAndSendToUser(principal.getName(),
                    "/sub/game-room/"+request.getRoomId()
                    , "OK"+" "+turn);
            log.info("working");


        }
        else if(turn==4){
            List<List<Integer>> turnArray= new ArrayList<>();
            for(int i=0;i<4;i++){
                turnArray.add(new ArrayList<>(Arrays.asList(i,2)));
            }
            CommonDto commonDto = CommonDto.builder()
                    .round(1)
                    .currentTurn(1)
                    .roomId(request.getRoomId())
                    .action(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
                    .turnArray(turnArray)

                    .build();
            log.info(request.getRoomId().toString());
            log.info(request.getContent().toString());
            template.convertAndSend("/sub/game-room/"+request.getRoomId()
                    ,commonDto);
            template.convertAndSendToUser(principal.getName(),"/sub/game-room/"+request.getRoomId(),"OK"+" "+turn);
            log.info("send turn");

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
