package com.example.demo.domain.card.entity;

import com.example.demo.domain.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    @OneToOne(mappedBy = "card")
    private User user;

}
