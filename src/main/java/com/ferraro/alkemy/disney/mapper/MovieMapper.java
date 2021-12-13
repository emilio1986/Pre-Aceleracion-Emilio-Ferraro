package com.ferraro.alkemy.disney.mapper;

import com.ferraro.alkemy.disney.dto.CharacterDTO;
import com.ferraro.alkemy.disney.dto.MovieBasicDTO;
import com.ferraro.alkemy.disney.dto.MovieDTO;
import com.ferraro.alkemy.disney.entity.CharacterEntity;
import com.ferraro.alkemy.disney.entity.MovieEntity;
import com.ferraro.alkemy.disney.repository.CharacterRepository;
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
public class MovieMapper {


    private CharacterMapper characterMapper;
    private CharacterRepository characterRepository;

    @Autowired
    public MovieMapper(@Lazy CharacterMapper characterMapper, CharacterRepository characterRepository) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
    }


    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setDate(this.string2LocalDate(dto.getDate()));
        movieEntity.setRating(dto.getRating());
        movieEntity.setImage(dto.getImage());
        movieEntity.setGenreId(dto.getGenreId());
        return movieEntity;
    }


    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadIcons) {

        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setRating(entity.getRating());
        dto.setDate(entity.getDate().toString());
        dto.setGenreId(entity.getGenreId());
        if (loadIcons) {
            List<CharacterDTO> iconsDTO = this.characterMapper.movieEntityList2DTO(entity.getCharacters(), false);
            dto.setCharacters(iconsDTO);
        }
        return dto;

    }

    private LocalDate string2LocalDate(String stringDate) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, fmt);
        return date;
    }


    public Set<CharacterEntity> iconDTOList2Entity(List<CharacterDTO> dtos) {

        Set<CharacterEntity> entities = new HashSet<CharacterEntity>();
        for (CharacterDTO dto : dtos) {
            entities.add(this.iconDTO2Entity(dto));

        }
        return entities;
    }

    public CharacterEntity iconDTO2Entity(CharacterDTO dto) {

        return null;
    }


    public List<MovieDTO> movieEntitySet2TDOList(List<MovieEntity> movieList, boolean loadIcons) {
        List<MovieDTO> dtos = new ArrayList<MovieDTO>();
        for (MovieEntity entity : movieList) {
            dtos.add(this.movieEntity2DTO(entity, loadIcons));
        }
        return dtos;
    }


    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadIcons) {
        List<MovieDTO> dtos = new ArrayList<MovieDTO>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadIcons));
        }
        return dtos;
    }


    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {
        entity.setTitle(dto.getTitle());

        entity.setRating(dto.getRating());
        entity.setGenreId(dto.getGenreId());
        entity.setImage(dto.getImage());
    }

    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities) {
        List<MovieBasicDTO> dtos = new ArrayList<MovieBasicDTO>();
        MovieBasicDTO basicDTO;
        for (MovieEntity entity : entities) {
            basicDTO = new MovieBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setImageUrl(entity.getImage());
            basicDTO.setDate(entity.getDate().toString());
            dtos.add(basicDTO);
        }
        return dtos;
    }


    public MovieDTO movieEntity2DTO(boolean loadCharacters, MovieEntity savedEntity) {
        MovieDTO dto = new MovieDTO();

        dto.setId(savedEntity.getId());

        dto.setTitle(savedEntity.getTitle());

        dto.setRating(savedEntity.getRating());
        if (loadCharacters) {
            List<CharacterDTO> characterDTOS = this.characterMapper.characterEntitySet2DTOList(savedEntity.getCharacters(), loadCharacters);
            dto.setCharacters(characterDTOS);
        }
        dto.setImage(savedEntity.getImage());
        dto.setGenreId(savedEntity.getGenreId());

        return dto;
    }


    public List<MovieDTO> movieEntityList2DTO(List<MovieEntity> movies, boolean loadIcons) {

        List<MovieDTO> dtos = new ArrayList<MovieDTO>();
        for (MovieEntity entities : movies) {
            dtos.add(this.movieEntity2DTO(entities, loadIcons));
        }
        return dtos;

    }


}


