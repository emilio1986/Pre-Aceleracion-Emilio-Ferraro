package alkemy.Disney2.Disney2.service;

import alkemy.Disney2.Disney2.dto.IconBasicDTO;
import alkemy.Disney2.Disney2.dto.IconDTO;
import alkemy.Disney2.Disney2.entity.IconEntity;

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
