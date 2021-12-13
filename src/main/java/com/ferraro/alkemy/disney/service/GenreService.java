package com.ferraro.alkemy.disney.service;

import com.ferraro.alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    void delete(Long id);

    GenreDTO getGenreById(Long id);

    GenreDTO update(Long id, GenreDTO continente);


    GenreDTO getDetailsById(Long id);


}
