package com.ferraro.alkemy.disney.mapper;

import com.ferraro.alkemy.disney.dto.CharacterBasicDTO;
import com.ferraro.alkemy.disney.dto.CharacterDTO;
import com.ferraro.alkemy.disney.dto.MovieDTO;
import com.ferraro.alkemy.disney.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    private MovieMapper movieMapper;

    @Autowired
    public CharacterMapper(@Lazy MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {

        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setHistory(dto.getHistory());
        return entity;
    }


    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {                             //pasar en false paraq corte la carga

        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setHistory(entity.getHistory());
        if (loadMovies) {

            List<MovieDTO> moviesDTO = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(moviesDTO);
        }
        return dto;

    }

    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO characterDTO) {

        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        //  entity.setFechaCreacion(this.string2LocalDate(characterDTO.getFechaCreacion()));
        // FALTA altura en BD-> entity.setAltura(iconDTO.getAltua());
        entity.setHistory(characterDTO.getHistory());
    }


    public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDTO> dtos) {

        Set<CharacterEntity> entities = new HashSet<>();
        for (CharacterDTO dto : dtos) {
            entities.add(this.characterDTO2Entity(dto));

        }
        return entities;
    }

    public List<CharacterDTO> characterEntitySet2TDOList(Collection<CharacterEntity> entities, boolean loadMovies) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }


    public List<CharacterDTO> movieEntityList2DTO(Set<CharacterEntity> icons, boolean loadCharacters) { //movieEntityListF6
        List<CharacterDTO> dtos = new ArrayList<CharacterDTO>();
        for (CharacterEntity entities : icons) {
            dtos.add(this.characterEntity2DTO(entities, loadCharacters));
        }
        return dtos;
    }

    public List<CharacterDTO> characterEntitySet2DTOList(Set<CharacterEntity> entities, boolean loadIcons) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, false));
        }
        return dtos;
    }

    public List<CharacterBasicDTO> characterEntitySet2BasicDTOList(Collection<CharacterEntity> entities) {    //" FUNCIONA" CON ICONBASIC
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;
        for (CharacterEntity entity : entities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setDenominacion(entity.getName());
            basicDTO.setImagen(entity.getImage());
            dtos.add(basicDTO);
        }
        return dtos;
    }


    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {    //" FUNCIONA" CON ICONBASIC

        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;
        for (CharacterEntity entity : entities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setDenominacion(entity.getName());
            basicDTO.setImagen(entity.getImage());
            dtos.add(basicDTO);
        }
        return dtos;
    }


    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean movies) {

        List<CharacterDTO> dtos = new ArrayList<>();
        CharacterDTO characterDTO;
        for (CharacterEntity entity : entities) {
            characterDTO = new CharacterDTO();
            characterDTO.setId(entity.getId());
            characterDTO.setName(entity.getName());
            characterDTO.setImage(entity.getImage());
            characterDTO.setAge(entity.getAge());
            characterDTO.setHistory(entity.getHistory());
            dtos.add(characterDTO);
        }
        return dtos;
    }
}


