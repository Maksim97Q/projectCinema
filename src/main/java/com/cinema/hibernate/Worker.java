package com.cinema.hibernate;

import com.cinema.model.Ticket;
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
    private int income = 55;
//    @OneToOne(mappedBy = "worker")
//    private Ticket ticket;

    public Worker(String name, int age) {
        this.name = name;
        this.age = age;
    }
}