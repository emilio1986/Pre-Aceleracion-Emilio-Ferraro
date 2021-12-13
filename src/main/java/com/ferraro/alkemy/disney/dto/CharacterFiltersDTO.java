package com.ferraro.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class CharacterFiltersDTO {

    private String name;
    private String age;
    private Set<Long> movies;
    private String order;


    public CharacterFiltersDTO(String name, String age, Set<Long> movies, String order) {
        this.name = name;
        this.movies = movies;
        this.age = age;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
