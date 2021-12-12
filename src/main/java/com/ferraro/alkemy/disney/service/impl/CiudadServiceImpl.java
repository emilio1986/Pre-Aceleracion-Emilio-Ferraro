package alkemy.Disney2.Disney2.service.impl;

import alkemy.Disney2.Disney2.dto.CiudadBasicDTO;
import alkemy.Disney2.Disney2.dto.CiudadDTO;
import alkemy.Disney2.Disney2.entity.CiudadEntity;
import alkemy.Disney2.Disney2.entity.IconEntity;
import alkemy.Disney2.Disney2.mapper.CiudadMapper;
import alkemy.Disney2.Disney2.repository.CiudadRepository;
import alkemy.Disney2.Disney2.service.CiudadService;
import alkemy.Disney2.Disney2.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CiudadServiceImpl implements CiudadService {


    private CiudadRepository ciudadRepository;


    private CiudadMapper ciudadMapper;

    private IconService iconService;
   //  private ContinenteService continenteService;           //AGREGAR CIUDAD A CONT


    @Autowired
    public CiudadServiceImpl(@Lazy
                                     CiudadRepository ciudadRepository,
                             CiudadMapper ciudadMapper,
                             IconService iconService //ContinenteService continenteService

    ) {
        this.ciudadRepository = ciudadRepository;
        this.ciudadMapper = ciudadMapper;
        this.iconService = iconService;
        //this.continenteService = continenteService;

    }


    @Override
    public CiudadDTO getDetailsById(Long id) {
        Optional<CiudadEntity> entity = Optional.of(ciudadRepository.getById(id));
        CiudadDTO ciudadDTO = this.ciudadMapper.ciudadEntity2DTO(entity.get(), true);
        return ciudadDTO;
    }


    @Override
    public CiudadDTO save(CiudadDTO dto) {

        CiudadEntity entity = ciudadMapper.ciudadDTO2Entity(dto);
        CiudadEntity entidadGuardada = ciudadRepository.save(entity);

        CiudadDTO result;
        result = ciudadMapper.ciudadEntity2DTO(true, entidadGuardada);
        return result;
    }

    @Override
    public List<CiudadBasicDTO> getAll() {
        List<CiudadEntity> entities = ciudadRepository.findAll();
        List<CiudadBasicDTO> iconBasicDTOS = ciudadMapper.ciudadEntityList2BasicDTOList(entities);
        return iconBasicDTOS;
    }

    @Override
    public CiudadDTO update(Long id, CiudadDTO ciudad) {
        Optional<CiudadEntity> entity = this.ciudadRepository.findById(id);
        // if (!entity.isPresent()) {
        //   throw new ParamNotFound("city id not valid");
        //}
        this.ciudadMapper.ciudadEntityRefreshValues(entity.get(), ciudad);
        CiudadEntity updatedEntity = this.ciudadRepository.save(entity.get());
        CiudadDTO result = this.ciudadMapper.ciudadEntity2DTO(updatedEntity, true);
        return result;
    }


    @Override
    public void addICon(Long id, Long idIcon) {
        CiudadEntity entity = this.ciudadRepository.getById(id);
        entity.getIcons().size();
        IconEntity iconEntity = this.iconService.getEntityById(idIcon);
        entity.addIcon(iconEntity);
        this.ciudadRepository.save(entity);
    }


    public void removeIcon(Long id, Long idIcon) {
        //busco el id de la ciudad a borrar en la base
        CiudadEntity entity = this.ciudadRepository.getById(id);
        entity.getIcons().size();
        IconEntity iconEntity = this.iconService.getEntityById(idIcon);
        entity.removeIcon(iconEntity);
        this.ciudadRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        this.ciudadRepository.deleteById(id);
    }

    @Override
    public CiudadEntity getEntityById(Long idCiudad) {
        return this.ciudadRepository.getById(idCiudad);
    }


}



