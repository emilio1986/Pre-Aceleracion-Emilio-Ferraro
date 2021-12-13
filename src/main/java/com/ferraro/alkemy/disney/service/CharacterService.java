package com.ferraro.alkemy.disney.service;

import com.ferraro.alkemy.disney.dto.CharacterBasicDTO;
import com.ferraro.alkemy.disney.dto.CharacterDTO;
import com.ferraro.alkemy.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO getDetailsById(Long id);

    List<CharacterBasicDTO> getAllB();

    List<CharacterDTO> getAll();

    CharacterDTO save(CharacterDTO characterDTO);

    CharacterDTO update(Long id, CharacterDTO icon);


    void delete(Long id);

    CharacterEntity getEntityById(Long idIcon);

    List<CharacterDTO> getByFilters(String name, String date, Set<Long> cities, String order);
}
