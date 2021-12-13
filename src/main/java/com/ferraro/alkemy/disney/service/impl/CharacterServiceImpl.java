package com.ferraro.alkemy.disney.service.impl;

import com.ferraro.alkemy.disney.dto.CharacterBasicDTO;
import com.ferraro.alkemy.disney.dto.CharacterDTO;
import com.ferraro.alkemy.disney.dto.CharacterFiltersDTO;
import com.ferraro.alkemy.disney.entity.CharacterEntity;
import com.ferraro.alkemy.disney.mapper.CharacterMapper;
import com.ferraro.alkemy.disney.repository.CharacterRepository;
import com.ferraro.alkemy.disney.repository.specifications.CharacterSpecification;
import com.ferraro.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {


    private CharacterRepository characterRepository;

    private CharacterMapper characterMapper;


    private CharacterSpecification characterSpecification;


    @Autowired
    public CharacterServiceImpl(@Lazy CharacterRepository characterRepository, CharacterMapper characterMapper, CharacterSpecification characterSpecification) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.characterSpecification = characterSpecification;
    }

    @Override
    public CharacterDTO getDetailsById(Long id) {

        CharacterEntity entity = this.characterRepository.getById(id);
        CharacterDTO dtoIcons = this.characterMapper.characterEntity2DTO(entity, true);
        return dtoIcons;


    }


    @Override
    public List<CharacterBasicDTO> getAllB() {
        List<CharacterEntity> entities = this.characterRepository.findAll();
        List<CharacterBasicDTO> characterBasicDTOS = characterMapper.characterEntitySet2BasicDTOList(entities);
        return characterBasicDTOS;
    }


    @Override
    public List<CharacterDTO> getAll() {
        List<CharacterEntity> entities = this.characterRepository.findAll();
        List<CharacterDTO> characterDTOS = characterMapper.characterEntityList2DTOList(entities, false);
        return characterDTOS;
    }


    @Override
    public CharacterDTO save(CharacterDTO characterDTO) {

        CharacterEntity entity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity savedEntity = characterRepository.save(entity);
        CharacterDTO result;
        result = characterMapper.characterEntity2DTO(savedEntity, true);
        return result;

    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO icon) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        // if (!entity.isPresent()) {
        //   throw new ParamNotFound("Ciudad id invalido");
        //}
        this.characterMapper.characterEntityRefreshValues(entity.get(), icon);
        CharacterEntity updatedEntity = this.characterRepository.save(entity.get());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(updatedEntity, true);
        return result;
    }

    @Override
    public void delete(Long id) {

        this.characterRepository.deleteById(id);

    }

    @Override
    public CharacterEntity getEntityById(Long idCharacter) {
        return this.characterRepository.getById(idCharacter
        );
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, date, cities, order);
        Set<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = this.characterMapper.characterEntitySet2DTOList(entities, true);
        return dtos;
    }
}



