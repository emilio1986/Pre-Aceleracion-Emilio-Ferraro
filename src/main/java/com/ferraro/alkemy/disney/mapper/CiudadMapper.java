package alkemy.Disney2.Disney2.mapper;

import alkemy.Disney2.Disney2.dto.CiudadBasicDTO;
import alkemy.Disney2.Disney2.dto.CiudadDTO;
import alkemy.Disney2.Disney2.dto.IconDTO;
import alkemy.Disney2.Disney2.entity.CiudadEntity;
import alkemy.Disney2.Disney2.entity.IconEntity;
import alkemy.Disney2.Disney2.repository.IconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CiudadMapper {


    private IconMapper iconMapper;
    private IconRepository iconRepository;

    @Autowired
    public CiudadMapper(@Lazy IconMapper iconMapper, IconRepository iconRepository) {
        this.iconMapper = iconMapper;
        this.iconRepository = iconRepository;
    }


    public CiudadEntity ciudadDTO2Entity(CiudadDTO dto) {
        CiudadEntity ciudadEntity = new CiudadEntity();
        ciudadEntity.setDenominacion(dto.getDenominacion());
        ciudadEntity.setCantHabitantes(dto.getCantHabitantes());
        ciudadEntity.setSuperficie(dto.getSuperficie());
        ciudadEntity.setImagen(dto.getImagen());
        ciudadEntity.setContinenteId(dto.getContinenteId());
        return ciudadEntity;
    }


    public CiudadDTO ciudadEntity2DTO(CiudadEntity entity, boolean loadIcons) {

        CiudadDTO dto = new CiudadDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        dto.setSuperficie(entity.getSuperficie());
        dto.setCantHabitantes(entity.getCantHabitantes());
        dto.setContinenteId(entity.getContinenteId());
        if (loadIcons) {
            List<IconDTO> iconsDTO = this.iconMapper.ciudadEntityList2DTO(entity.getIcons(), false);
            dto.setIcons(iconsDTO);
        }
        return dto;

    }

    private LocalDate string2LocalDate(String stringDate) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, fmt);
        return date;
    }


    public Set<IconEntity> iconDTOList2Entity(List<IconDTO> dtos) {

        Set<IconEntity> entities = new HashSet<IconEntity>();
        for (IconDTO dto : dtos) {
            entities.add(this.iconDTO2Entity(dto));

        }
        return entities;
    }

    public IconEntity iconDTO2Entity(IconDTO dto) {

        return null;
    }


    public List<CiudadDTO> ciudadEntitySet2TDOList(List<CiudadEntity> listaCiudad, boolean loadIcons) {
        List<CiudadDTO> dtos = new ArrayList<CiudadDTO>();
        for (CiudadEntity entity : listaCiudad) {
            dtos.add(this.ciudadEntity2DTO(entity, loadIcons));
        }
        return dtos;
    }

    //

    //Considerar Sets tb como param
    public List<CiudadDTO> ciudadEntityList2DTOList(List<CiudadEntity> entities, boolean loadIcons) {
        List<CiudadDTO> dtos = new ArrayList<CiudadDTO>();
        for (CiudadEntity entity : entities) {
            dtos.add(this.ciudadEntity2DTO(entity, loadIcons));
        }
        return dtos;
    }


    public void ciudadEntityRefreshValues(CiudadEntity entity, CiudadDTO dto) {
        entity.setDenominacion(dto.getDenominacion());

        entity.setSuperficie(dto.getSuperficie());
        entity.setContinenteId(dto.getContinenteId());
        entity.setImagen(dto.getImagen());
    }

    public List<CiudadBasicDTO> ciudadEntityList2BasicDTOList(List<CiudadEntity> entities) {
        List<CiudadBasicDTO> dtos = new ArrayList<CiudadBasicDTO>();
        CiudadBasicDTO basicDTO;
        for (CiudadEntity entity : entities) {
            basicDTO = new CiudadBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setTitle(entity.getDenominacion());
            basicDTO.setImageUrl(entity.getImagen());
            basicDTO.setCantHabitantes(entity.getCantHabitantes());
            dtos.add(basicDTO);
        }
        return dtos;
    }


    public CiudadDTO ciudadEntity2DTO(boolean loadIcons, CiudadEntity entidadGuardada) {
        CiudadDTO dto = new CiudadDTO();

        dto.setId(entidadGuardada.getId());

        dto.setDenominacion(entidadGuardada.getDenominacion());

        dto.setSuperficie(entidadGuardada.getSuperficie());
        if (loadIcons) {
            List<IconDTO> iconDTOS = this.iconMapper.iconEntitySet2DTOList(entidadGuardada.getIcons(), loadIcons);
            dto.setIcons(iconDTOS) ;
        }
        dto.setImagen(entidadGuardada.getImagen());
        dto.setContinenteId(entidadGuardada.getContinenteId());

        return dto;
    }




    public List<CiudadDTO> continenteEntityList2DTO(List<CiudadEntity> ciudades, boolean loadIcons) {

        List<CiudadDTO> dtos = new ArrayList<CiudadDTO>();
        for (CiudadEntity entities : ciudades) {
            dtos.add(this.ciudadEntity2DTO(entities, loadIcons));
        }
        return dtos;

    }

}


