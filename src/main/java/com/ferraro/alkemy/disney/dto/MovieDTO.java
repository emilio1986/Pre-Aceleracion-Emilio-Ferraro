package com.ferraro.alkemy.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter


public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private Integer rating;
    private List<CharacterDTO> characters;
    private Long genreId;
    private String date;

}





