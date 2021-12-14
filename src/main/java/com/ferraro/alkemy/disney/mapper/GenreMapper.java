package com.ferraro.alkemy.disney.mapper;


import com.ferraro.alkemy.disney.dto.GenreDTO;
import com.ferraro.alkemy.disney.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {


    private MovieMapper movieMapper;

    @Autowired
    public GenreMapper(
            MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }


    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());

        return genreEntity;
    }


    public List<GenreDTO> continenteEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreDTO> dtos = new ArrayList<GenreDTO>();
        for (GenreEntity entity : entities) {
            //transformo la entity a Dto por cada elem d la lista
            dtos.add(this.genreEntity2TDO(entity, true));
        }
        return dtos;
    }

    public GenreDTO genreEntity2TDO(GenreEntity entity, boolean loadCiudades) {
        GenreDTO dto = new GenreDTO();
        dto.setImage(entity.getImage());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (loadCiudades) {

        }
        return dto;
    }


}
