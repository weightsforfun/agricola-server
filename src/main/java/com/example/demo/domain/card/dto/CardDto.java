package com.example.demo.domain.card.dto;

import com.example.demo.global.dto.CommonDto;
import java.util.List;
import lombok.Getter;

@Getter
public class CardDto extends CommonDto {
    private List<List<Integer>> job;
    private List<List<Integer>> main;
    private List<List<Integer>> sub;

}
