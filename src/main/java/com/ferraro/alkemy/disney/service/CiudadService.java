package alkemy.Disney2.Disney2.service;

import alkemy.Disney2.Disney2.dto.CiudadBasicDTO;
import alkemy.Disney2.Disney2.dto.CiudadDTO;
import alkemy.Disney2.Disney2.entity.CiudadEntity;

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
