package com.cinema.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String movie_title;
    @Column
    private String data;

    public Movie(String movie_title, String data) {
        this.movie_title = movie_title;
        this.data = data;
    }
}
