package com.ferraro.alkemy.disney.controller;

import com.ferraro.alkemy.disney.dto.MovieDTO;
import com.ferraro.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> ciudades = this.movieService.getAll();                                            //PASS
        return ResponseEntity.ok(ciudades);
    }


    @GetMapping("/{id}")                                                                                 //PASS
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {
        MovieDTO ciudad = this.movieService.getDetailsById(id);
        return ResponseEntity.ok(ciudad);
    }


    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO ciudad) {
        MovieDTO result = this.movieService.save(ciudad);
        return ResponseEntity.status((HttpStatus.CREATED)).body(result);                                 //PASS

    }

    @PostMapping("/{id}/characters/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {    //PASS
        this.movieService.addCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @PutMapping("/{id}")                                                                                  //PASS
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO ciudad) {
        MovieDTO result = this.movieService.update(id, ciudad);
        return ResponseEntity.ok().body(result);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/movies/{idCharacter}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long idCharacter) {
        this.movieService.removeCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
