package com.example.demo.domain.user.entity;


import com.example.demo.domain.card.entity.Card;
import com.example.demo.domain.farm.entity.Farm;
import com.example.demo.domain.resource.entity.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @GeneratedValue
    @Id
    @Column(name="user_id")
    private Long id;

    @Column
    private int turn; //게임 내 유저 순서

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource; //농부 수도 자원에 포함




}
