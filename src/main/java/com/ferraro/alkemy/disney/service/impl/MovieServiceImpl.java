package com.ferraro.alkemy.disney.service.impl;

import com.ferraro.alkemy.disney.dto.MovieBasicDTO;
import com.ferraro.alkemy.disney.dto.MovieDTO;
import com.ferraro.alkemy.disney.entity.CharacterEntity;
import com.ferraro.alkemy.disney.entity.MovieEntity;
import com.ferraro.alkemy.disney.mapper.MovieMapper;
import com.ferraro.alkemy.disney.repository.MovieRepository;
import com.ferraro.alkemy.disney.service.CharacterService;
import com.ferraro.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {


    private MovieRepository movieRepository;


    private MovieMapper movieMapper;

    private CharacterService characterService;


    @Autowired
    public MovieServiceImpl(@Lazy MovieRepository movieRepository,
                            MovieMapper movieMapper,
                            CharacterService characterService

    ) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.characterService = characterService;

    }


    @Override
    public MovieDTO getDetailsById(Long id) {
        Optional<MovieEntity> entity = Optional.of(movieRepository.getById(id));
        MovieDTO movieDTO = this.movieMapper.movieEntity2DTO(entity.get(), true);
        return movieDTO;
    }


    @Override
    public MovieDTO save(MovieDTO dto) {

        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entidadGuardada = movieRepository.save(entity);

        MovieDTO result;
        result = movieMapper.movieEntity2DTO(true, entidadGuardada);
        return result;
    }

    @Override
    public List<MovieBasicDTO> getAll() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieBasicDTO> movieBasicDTOS = movieMapper.movieEntityList2BasicDTOList(entities);
        return movieBasicDTOS;
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movie) {
        Optional<MovieEntity> entity = this.movieRepository.findById(id);
        //if (!entity.isPresent()) {
        //throw new ParamNotFound("city id not valid");
        // }
        this.movieMapper.movieEntityRefreshValues(entity.get(), movie);
        MovieEntity updatedEntity = this.movieRepository.save(entity.get());
        MovieDTO result = this.movieMapper.movieEntity2DTO(updatedEntity, true);
        return result;
    }


    @Override
    public void addCharacter(Long id, Long idCharacter) {
        MovieEntity entity = this.movieRepository.getById(id);
        entity.getCharacters().size();
        CharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
        entity.addCharacter(characterEntity);
        this.movieRepository.save(entity);
    }


    public void removeCharacter(Long id, Long idIcon) {
        MovieEntity entity = this.movieRepository.getById(id);
        //entity.getCharacters().size();
        CharacterEntity characterEntity = this.characterService.getEntityById(idIcon);
        entity.removeCharacter(characterEntity);
        this.movieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public MovieEntity getEntityById(Long idMovie) {
        return this.movieRepository.getById(idMovie);
    }


}



