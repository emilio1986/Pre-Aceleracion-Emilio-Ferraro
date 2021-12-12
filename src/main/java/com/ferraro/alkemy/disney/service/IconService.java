package com.ferraro.alkemy.disney.service;

import com.ferraro.alkemy.disney.dto.IconBasicDTO;
import com.ferraro.alkemy.disney.dto.IconDTO;
import com.ferraro.alkemy.disney.entity.IconEntity;

import java.util.List;
import java.util.Set;

public interface IconService {

    IconDTO getDetailsById(Long id);

    List<IconBasicDTO> getAllB();

    List<IconDTO> getAll();

    IconDTO save(IconDTO iconDTO);

    IconDTO update(Long id, IconDTO icon);


    void delete(Long id);

    IconEntity getEntityById(Long idIcon);

    List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order);
}
