package com.luv2code.springbootecommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "")
@Data
public class State {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


}