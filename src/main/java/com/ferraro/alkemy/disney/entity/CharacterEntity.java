package com.ferraro.alkemy.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")          //fijar el mapper de characters
@Getter
@Setter

@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class CharacterEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)      //autoincrement
    private Long id;

    private String image;

    private String name;


    private Long weight;

    private Long age;

    private String history;

    // SOFT DELETE
    private boolean deleted = Boolean.FALSE;


    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)

    private List<MovieEntity> movies = new ArrayList<>();

    public void addMovie(MovieEntity movie) {
        this.movies.add(movie);

    }

    public void removeMovie(MovieEntity movie) {

        this.movies.remove(movie);

    }


}
