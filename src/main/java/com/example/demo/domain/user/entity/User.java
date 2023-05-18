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

@Entity
public class User {
    @GeneratedValue
    @Id
    @Column(name="user_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;




}
