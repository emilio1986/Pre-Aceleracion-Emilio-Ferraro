package com.ferraro.alkemy.disney.service;

import com.ferraro.alkemy.disney.dto.CiudadBasicDTO;
import com.ferraro.alkemy.disney.dto.CiudadDTO;
import com.ferraro.alkemy.disney.entity.CiudadEntity;

import java.util.List;

public interface CiudadService {

    CiudadDTO getDetailsById(Long id);

    List<CiudadBasicDTO> getAll();

    CiudadDTO save(CiudadDTO ciudadDTO);

    CiudadDTO update(Long id, CiudadDTO ciudad);

    void addICon(Long id, Long idIcon);

    void removeIcon(Long id, Long idIcon);

    void delete(Long id);

    CiudadEntity getEntityById(Long idCiudad);

}
