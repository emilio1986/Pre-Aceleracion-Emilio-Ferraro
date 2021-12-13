package com.ferraro.alkemy.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class CharacterDTO {

    private Long id;
    private String image;
    private String name;
    private Long age;
    private String history;
    private List<MovieDTO> movies;


}
