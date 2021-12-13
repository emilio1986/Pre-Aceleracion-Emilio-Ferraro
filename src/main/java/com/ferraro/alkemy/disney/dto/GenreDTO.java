package com.ferraro.alkemy.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenreDTO {

    private long id;
    private String image;
    private String name;
    private List<MovieDTO> movies;


    public void setMovies(List<MovieDTO> movies) {

    }

    // public void addCiudad(MovieDTO movie)
    ////  this.movies.add(movie);
    //}
}
