package com.ferraro.alkemy.disney.controller;

import com.ferraro.alkemy.disney.dto.GenreDTO;
import com.ferraro.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("continentes")

public class GenreController {

    @Autowired
    private GenreService genreService; //Interfaz

    //tipo de solicitud
    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO continente) {              //PASS
        GenreDTO savedGenre = genreService.save(continente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {

        List<GenreDTO> genres = this.genreService.getAllGenres();                //PASS
        return ResponseEntity.ok().body(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getContById(@PathVariable Long id) {                     //PASS
        GenreDTO genre = this.genreService.getGenreById((id));
        return ResponseEntity.ok().body(genre);
    }

    @PutMapping("/{id}")                                                                          //PASS
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO continente) {
        GenreDTO result = this.genreService.update(id, continente);
        return ResponseEntity.ok(result);
    }

    //@PostMapping("/{id}/ciudades/{idCiudad}")
    //public ResponseEntity<Void> addIcon(@PathVariable Long id, @PathVariable Long idCiudad) {
      //  this.continenteService.addCiudad(id, idCiudad);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
    //}   NOVA

    @DeleteMapping("/{id}")                                                                     //PASS
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}



