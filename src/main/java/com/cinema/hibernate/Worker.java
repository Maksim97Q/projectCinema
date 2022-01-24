package com.cinema.hibernate;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker")
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private int income;
    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL)
    private Ticket ticket;

    public Worker(Integer id, String name, Integer age, Integer income) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.income = income;
    }

    public Worker(String name, int age, int income) {
        this.name = name;
        this.age = age;
        this.income = income;
    }
}