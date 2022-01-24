package com.cinema.model;

import com.cinema.hibernate.Worker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int price;
    @Column
    private int place_number;
    @Column
    private String data;
//    @OneToOne
//    @JoinColumn
//    private Worker worker;

    public Ticket(int price, int place_number, String data) {
        this.price = price;
        this.place_number = place_number;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", place_number=" + place_number +
                ", data='" + data + '\'' +
                '}';
    }
}
