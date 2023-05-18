package com.example.demo.domain.resource.entity;


import com.example.demo.domain.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Resource {
    @GeneratedValue
    @Id
    @Column(name = "resource_id")
    private int id;

    @OneToOne(mappedBy = "resource")
    private User user;

    @Column
    private int soil;

    @Column
    private int weed;

    @Column
    private int wood;

}
