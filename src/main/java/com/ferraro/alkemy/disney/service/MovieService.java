package com.ferraro.alkemy.disney.service;

import com.ferraro.alkemy.disney.dto.MovieBasicDTO;
import com.ferraro.alkemy.disney.dto.MovieDTO;
import com.ferraro.alkemy.disney.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    MovieDTO getDetailsById(Long id);

    List<MovieDTO> getAll();

    MovieDTO save(MovieDTO movieDTO);

    MovieDTO update(Long id, MovieDTO ciudad);

    void addCharacter(Long id, Long idIcon);

    void removeCharacter(Long id, Long idIcon);

    void delete(Long id);

    MovieEntity getEntityById(Long idCiudad);

}
