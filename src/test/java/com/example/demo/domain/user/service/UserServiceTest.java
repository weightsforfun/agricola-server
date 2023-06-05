package com.example.demo.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import com.example.demo.domain.user.InitUserDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    GameRoomRepository gameRoomRepository;

    @Test
    @DisplayName("없는 게임방 입장 시도시")
    void noGameRoom() throws Exception
    {
        // given

        //mocking

        when(gameRoomRepository.findById((long)1)).thenThrow(new RuntimeException("no gameRoom"));

        // when
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> userService.initUser((long)1));

        // then
        assertEquals(runtimeException.getMessage(),"no gameRoom");
        verify(gameRoomRepository,times(1)).findById((long) 1);
    }
    @Test
    @DisplayName("방 입장 성공")
    void enterSuccess() throws Exception
    {
        // given
        ArrayList<Integer> cards = new ArrayList<>();
        for(int i=1;i<29;i++){
            cards.add(i);
        }

        GameRoom gameRoom = GameRoom.builder()
                .id((long) 1)
                .round(1)
                .people(0)
                .currentTurn(1)
                .jobCards(cards)
                .subCards(cards)
                .build();
        //mocking
        when(gameRoomRepository.findById((long)1)).thenReturn(Optional.ofNullable(gameRoom));
        // when
        InitUserDto initUserDto = userService.initUser((long) 1);

        // then
        assertEquals(true,initUserDto.isEnter());
        assertEquals(1,initUserDto.getTurn());
        assertEquals(7,initUserDto.getJobCards().size());
    }
    @Test
    @DisplayName("카드 분배")
    void cardEnroll() throws Exception
    {
        // given
        ArrayList<Integer> cards = new ArrayList<>();
        for(int i=1;i<29;i++){
            cards.add(i);
        }

        // when

        for(int i=0;i<3;i++){
            List<Integer> integers = userService.makeRandomCardList(cards);
        }
        // then
        assertEquals(7,cards.size());
    }
    @Test
    @DisplayName("정원초과로 입장 실패")
    void enterFail() throws Exception
    {
        // given
        ArrayList<Integer> jobCards = new ArrayList<>();
        ArrayList<Integer> subCards = new ArrayList<>();
        for(int i=1;i<29;i++){
            jobCards.add(i);
            subCards.add(i);
        }


        GameRoom gameRoom = GameRoom.builder()
                .id((long) 1)
                .round(1)
                .people(0)
                .currentTurn(1)
                .jobCards(jobCards)
                .subCards(subCards)
                .build();
        // mocking
        when(gameRoomRepository.findById((long)1)).thenReturn(Optional.ofNullable(gameRoom));

        //when
        for(int i=0;i<4;i++){

            InitUserDto initUserDto2 = userService.initUser((long) 1);


        }
        InitUserDto initUserDto = userService.initUser((long) 1);
        // then
        assertEquals(false,initUserDto.isEnter());
    }

}