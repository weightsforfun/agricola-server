package com.example.demo.domain.user.api;

import com.example.demo.domain.user.InitUserDto;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.global.config.websocketConfig.StompPrincipal;
import com.example.demo.global.dto.CommonDto;
import com.example.demo.global.dto.MessageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserApi {
    private final SimpMessagingTemplate template;
    private final UserService userService;


    @MessageMapping(value = "/user/init")
    public void intiUser(StompPrincipal principal,CommonDto req){
        InitUserDto initUserDto = userService.initUser(req.getRoomId());
        if(initUserDto.isEnter() && initUserDto.getTurn()<4) {
            log.info(req.getRoomId().toString());
            template.convertAndSendToUser(principal.getName(),
                    "/sub/game-room/"+req.getRoomId()
                    , initUserDto);



        }
        else if(initUserDto.getTurn()==4){
            List<List<Integer>> turnArray= new ArrayList<>();//행동버튼 초기화
            for(int i=0;i<13;i++){
                turnArray.add(new ArrayList<>(Arrays.asList(0,3)));
            }
            CommonDto res = CommonDto.builder()
                    .messageType(MessageType.INIT)
                    .round(1)
                    .currentTurn(1)
                    .roomId(req.getRoomId())
                    .action(turnArray)
                    .farmer_count(new ArrayList<>(Arrays.asList(2,2,2,2)))
                    .build();



            template.convertAndSendToUser(principal.getName(),"/sub/game-room/"+req.getRoomId(),initUserDto);
            template.convertAndSend("/sub/game-room/"+req.getRoomId()
                    , res);
            log.info("send turn");

        }
        else{
            template.convertAndSendToUser(principal.getName(),
                    "/sub/game-room/" + req.getRoomId()
                    , "FULL");
        }

    }
    @MessageMapping("/user/test")
    public void test(CommonDto commonDto){
        template.convertAndSend("/sub/game-room/"+commonDto.getRoomId(),"hello");
    }
}
