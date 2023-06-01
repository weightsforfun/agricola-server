package com.example.demo.domain.user.service;

import com.example.demo.domain.gameRoom.entity.GameRoom;
import com.example.demo.domain.gameRoom.repository.GameRoomRepository;
import com.example.demo.domain.user.InitUserDto;
import com.example.demo.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final GameRoomRepository gameRoomRepository;
    private final UserRepository userRepository;
    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public InitUserDto initUser(Long gameRoomId){
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId)
                .orElseThrow(() -> new RuntimeException("no game room"));



        if(gameRoom.getPeopleCount()<4){
            List<Integer> jobCards = gameRoom.getJobCards();
            List<Integer> subCards = gameRoom.getSubCards();

            List<Integer> randomJobCards = makeRandomCardList(jobCards);
            List<Integer> randomSubCards = makeRandomCardList(subCards);



            gameRoom.addUserCount();
            InitUserDto initUserDto = InitUserDto.builder()
                    .enter(true)
                    .jobCards(randomJobCards)
                    .subCards(randomSubCards)
                    .turn(gameRoom.getPeople())
                    .build();

            return initUserDto;

        }
        else {
            InitUserDto initUserDto = InitUserDto.builder()
                    .enter(false)
                    .build();
            return initUserDto;
        }
    }

    private static List<Integer> makeRandomCardList(List<Integer> numbers){
        List<Integer> randomNumbers = getRandomNumbers(numbers, 7);

        return randomNumbers;
    }
    private static List<Integer> getRandomNumbers(List<Integer> numbers, int count) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(numbers.size());
            int randomNumber = numbers.get(randomIndex);

            randomNumbers.add(randomNumber);

            numbers.remove(randomIndex);

        }

        return randomNumbers;
    }




}
