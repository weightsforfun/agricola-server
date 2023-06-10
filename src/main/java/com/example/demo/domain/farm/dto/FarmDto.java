package com.example.demo.domain.farm.dto;

import com.example.demo.global.dto.CommonDto;
import java.util.List;
import lombok.Getter;

@Getter
public class FarmDto extends CommonDto {
    private List<String> building;
    private List<List<Integer>> fence;
}
