package com.ferraro.alkemy.disney.mapper;


import com.ferraro.alkemy.disney.dto.MovieDTO;
import com.ferraro.alkemy.disney.dto.GenreDTO;
import com.ferraro.alkemy.disney.entity.MovieEntity;
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
            //List<CiudadDTO> ciudadesDTO = this.ciudadMapper.continenteEntityList2DTO(entity.getCiudades(), true);
            //dto.setCiudades(ciudadesDTO);
        }
        return dto;
    }

    public List<MovieDTO> moviesEntityList2DTOList(List<MovieEntity> entities) {        //Agregado ahora
        List<MovieDTO> dtos = new ArrayList<MovieDTO>();                                           //Agregado ahora
        for (MovieEntity entity : entities) {                                                 //Agregado ahora
            //transformo la entityCiudad  a DtoCiudad por cada elem d la lista|             //Agregado ahora
            dtos.add(this.movieMapper.movieEntity2DTO(entity, true));         //Agregado ahora
        }
        return dtos; // devuelvo una lista de DTOS                                    //agah
    }


}
