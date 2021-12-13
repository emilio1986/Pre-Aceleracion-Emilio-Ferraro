package com.ferraro.alkemy.disney.controller;

import com.ferraro.alkemy.disney.dto.CharacterBasicDTO;
import com.ferraro.alkemy.disney.dto.CharacterDTO;
import com.ferraro.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {


    private CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {

        this.characterService = characterService;
    }


    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO icon) {
        CharacterDTO result = this.characterService.save(icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO icon) {
        CharacterDTO result = this.characterService.update(id, icon);
        return ResponseEntity.ok().body(result);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {             //PASS
        this.characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/allB")                                      //  GET BASICS PASS
    public ResponseEntity<List<CharacterBasicDTO>> getAllB() {
        List<CharacterBasicDTO> icons = this.characterService.getAllB();
        return ResponseEntity.ok(icons);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
                                                                                 @RequestParam(required = false) String name,
                                                                                 @RequestParam(required = false) String date,
                                                                                 @RequestParam(required = false) Set<Long> cities,
                                                                                 @RequestParam(required = false, defaultValue = "ASC") String order) {
        List<CharacterDTO> icons = this.characterService.getByFilters(name, date, cities, order);
        return ResponseEntity.ok(icons);
    }

    //@DeleteMapping("/{id}/ciudad/{idCiudad}")
    //public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long idCiudad) {
    //  this.iconService.removeCiudad(id, idCiudad);
    //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    //}


    //@GetMapping
    //public ResponseEntity<List<IconBasicDTO>> getAll() {
    //List<IconBasicDTO> icons = this.iconService.getAllB();
    //return ResponseEntity.ok(icons);
    //}


    @GetMapping("/all")                                 //PASS
    ResponseEntity<List<CharacterDTO>> getAll() {
        List<CharacterDTO> icons = this.characterService.getAll();
        return ResponseEntity.ok(icons);
    }


    //tipo de solicitud
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {       //PASS
        CharacterDTO icon = this.characterService.getDetailsById(id);
        return ResponseEntity.ok(icon);
    }


}
