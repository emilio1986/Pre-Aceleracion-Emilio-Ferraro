package com.ferraro.alkemy.disney.service.impl;

import com.ferraro.alkemy.disney.dto.GenreDTO;
import com.ferraro.alkemy.disney.entity.GenreEntity;
import com.ferraro.alkemy.disney.mapper.GenreMapper;
import com.ferraro.alkemy.disney.repository.GenreRepository;
import com.ferraro.alkemy.disney.service.GenreService;
import com.ferraro.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class GenreServiceImpl implements GenreService {


    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieService ciuServ;


    @Autowired
    public GenreServiceImpl(@Lazy GenreRepository genreRepository, GenreMapper genreMapper, MovieService ciuServ) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
        this.ciuServ = ciuServ;

    }

    public GenreDTO save(GenreDTO dto) {                                           //PASS

        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2TDO(entitySaved, true);
        return result;

    }

    @Override
    public List<GenreDTO> getAllGenres() {                                            //PASS
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.continenteEntityList2DTOList(entities);
        return result;
    }


    public GenreDTO update(Long id, GenreDTO genre) {                             //PASS
        Optional<GenreEntity> oldEntity = Optional.of(this.genreRepository.getById(id));
        //excepcion??
        GenreEntity newEntity = genreMapper.genreDTO2Entity(genre);
        newEntity.setId(oldEntity.get().getId());
        GenreEntity entitySaved = genreRepository.save(newEntity);
        GenreDTO result = genreMapper.genreEntity2TDO(entitySaved, true);
        return result;
    }


    public void delete(Long id) {
        this.genreRepository.deleteById(id);
    }               //PASS


    @Override
    public GenreDTO getGenreById(Long id) {                                        //PASS
        GenreEntity entityGenre = this.genreRepository.getById(id);
        GenreDTO contDTO = this.genreMapper.genreEntity2TDO(entityGenre, true);
        return contDTO;
    }


    @Override
    public GenreDTO getDetailsById(Long id) {
        Optional<GenreEntity> entity = Optional.of(genreRepository.getById(id));
        GenreDTO contDTO = this.genreMapper.genreEntity2TDO(entity.get(), true);
        return contDTO;
    }


}
