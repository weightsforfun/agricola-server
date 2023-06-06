package com.example.demo.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import com.example.demo.domain.user.InitUserDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    @DisplayName("없는 게임방 입장 시도시 RuntimeException")
    void noGameRoom() throws Exception
    {
        // given

        //mocking

        when(gameRoomRepository.findById((long)1)).thenThrow(new RuntimeException("no game room"));

        // when
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> userService.initUser((long)1));

        // then   Exception 이 발생했는지, Mock 객체가 몇번 불렸고 어떤 파라미터를 받았는지
        assertEquals(runtimeException.getMessage(),"no game room");
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

        // then   첫번째 유저가 순서를 1 로 받았는지, 카드 개수를 알맞게 받았는지.
        assertEquals(true,initUserDto.isEnter());
        assertEquals(1,initUserDto.getTurn());
        assertEquals(7,initUserDto.getJobCards().size());
    }
    @Test
    @DisplayName("카드 분배 수 체크")
    void cardEnroll() throws Exception
    {
        // given
        ArrayList<Integer> cards = new ArrayList<>();
        for(int i=1;i<29;i++){
            cards.add(i);
        }

        // when
        List<Integer> randomCardList= new ArrayList<>();
        for(int i=0;i<3;i++){
            randomCardList = userService.makeRandomCardList(cards);
        } // 3명의 유저가 들어왔을때 남은 게임방의 카드는 7장이어야 한다. (현재 1명당 7장씩 분배)
        // then
        assertEquals(7,cards.size());
        assertEquals(7,randomCardList.size());
    }
    @Test
    @DisplayName("카드 중복 여부 체크")
    void checkDuplicate() throws Exception
    {
        // given
        ArrayList<Integer> cards = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for(int i=1;i<29;i++){
            cards.add(i);
        }
        // when  7장씩 4개의 중복되지 않는 카드 배열을 만들어서 set 에 넣는다.
        for(int i=0;i<4;i++){
            List<Integer> cardList = userService.makeRandomCardList(cards);
            for (Integer integer : cardList) {
                set.add(integer);
            }
        }
        // then  중복되는 숫자가 없었다면 총 28개의 원소를 가져야 한다.
        assertEquals(28,set.size());
    }
    @Test
    @DisplayName("카드 소진시 에러")
    void noCardError() throws Exception
    {
        // given    이미 카드가 다 소진된 상태에서
        ArrayList<Integer> cards = new ArrayList<>();
        for(int i=1;i<29;i++){
            cards.add(i);
        }
        for(int i=0;i<4;i++){
            userService.makeRandomCardList(cards);
        }
        // when     또 카드분배를 요청하면 카드가 없다는 exception 이 나와야 한다.
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> userService.makeRandomCardList(cards));

        // then
        assertEquals(runtimeException.getMessage(),"no available card");
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
            userService.initUser((long) 1);
        }
        InitUserDto initUserDto = userService.initUser((long) 1);
        // then
        assertEquals(false,initUserDto.isEnter());
        assertEquals(0,gameRoom.getJobCards().size());
    }

}