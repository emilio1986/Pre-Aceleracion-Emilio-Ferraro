package alkemy.Disney2.Disney2.service.impl;

import alkemy.Disney2.Disney2.dto.IconBasicDTO;
import alkemy.Disney2.Disney2.dto.IconDTO;
import alkemy.Disney2.Disney2.dto.IconFiltersDTO;
import alkemy.Disney2.Disney2.entity.IconEntity;
import alkemy.Disney2.Disney2.mapper.IconMapper;
import alkemy.Disney2.Disney2.repository.IconRepository;
import alkemy.Disney2.Disney2.repository.specifications.IconSpecification;
import alkemy.Disney2.Disney2.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IconServiceImpl implements IconService {

    //ESTE ESTA BIEN


    private IconRepository iconRepository;

    private IconMapper iconMapper;

    //private CiudadService ciudadService;

    private IconSpecification iconSpecification;


    @Autowired
    public IconServiceImpl(@Lazy IconRepository iconRepository, IconMapper iconMapper, IconSpecification iconSpecification) {
        this.iconRepository = iconRepository;
        this.iconMapper = iconMapper;
        //this.ciudadService = ciudadService;
        this.iconSpecification = iconSpecification;
    }

    @Override
    public IconDTO getDetailsById(Long id) {

        IconEntity entity = this.iconRepository.getById(id);
        IconDTO dtoIcons = this.iconMapper.iconEntity2DTO(entity, true);
        return dtoIcons;


    }


    @Override
    public List<IconBasicDTO> getAllB() {
        List<IconEntity> entities = this.iconRepository.findAll();
        List<IconBasicDTO> iconBasicDTOS = iconMapper.iconEntitySet2BasicDTOList(entities);
        return iconBasicDTOS;
    }


    @Override
    public List<IconDTO> getAll() {
        List<IconEntity> entities = this.iconRepository.findAll();
        List<IconDTO> iconDTOS = iconMapper.iconEntityList2DTOList(entities, false);
        return iconDTOS;
    }


    @Override
    public IconDTO save(IconDTO iconDTO) {

        IconEntity entity = iconMapper.iconDTO2Entity(iconDTO);
        IconEntity entidadGuardada = iconRepository.save(entity);
        IconDTO result;
        result = iconMapper.iconEntity2DTO(entidadGuardada, true);
        return result;

    }

    @Override
    public IconDTO update(Long id, IconDTO icon) {
        Optional<IconEntity> entity = this.iconRepository.findById(id);
        // if (!entity.isPresent()) {
        //   throw new ParamNotFound("Ciudad id invalido");
        //}
        this.iconMapper.iconEntityRefreshValues(entity.get(), icon);
        IconEntity updatedEntity = this.iconRepository.save(entity.get());
        IconDTO result = this.iconMapper.iconEntity2DTO(updatedEntity, true);
        return result;
    }


    @Override
    public void delete(Long id) {

        this.iconRepository.deleteById(id);

    }

    @Override
    public IconEntity getEntityById(Long idIcon) {
        return this.iconRepository.getById(idIcon);
    }

    @Override
    public List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        IconFiltersDTO filtersDTO = new IconFiltersDTO(name, date, cities, order);
        Set<IconEntity> entities = this.iconRepository.findAll(this.iconSpecification.getByFilters(filtersDTO));
        List<IconDTO> dtos = this.iconMapper.iconEntitySet2DTOList(entities, true);
        return dtos;
    }
}



