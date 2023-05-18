package com.example.demo.domain.farm.entity;


import com.example.demo.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Farm {
    @Id
    @GeneratedValue
    @Column(name = "farm_id")
    private int id;
    @Column
    private List<Integer> building = new ArrayList<>(); //농장 빌딩 ->enum으로
    @Column
    private List<Integer> fence= new ArrayList<>(); //울타리

    @OneToOne(mappedBy = "farm")
    private User user;

}
