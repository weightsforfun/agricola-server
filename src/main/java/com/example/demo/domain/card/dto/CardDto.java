package com.example.demo.domain.card.dto;

import com.example.demo.global.dto.CommonDto;
import java.util.List;
import lombok.Getter;

@Getter
public class CardDto extends CommonDto {
    private CardType cardType;//카드 종류
    private int cardIndex; //카드 번호

}
