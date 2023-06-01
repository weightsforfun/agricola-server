package com.example.demo.domain.user;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class InitUserDto {
    private boolean enter;
    private List<Integer> jobCards;
    private List<Integer> subCards;
    private int turn;

}
