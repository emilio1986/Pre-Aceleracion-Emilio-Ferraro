package alkemy.Disney2.Disney2.controller;

import alkemy.Disney2.Disney2.dto.ContinenteDTO;
import alkemy.Disney2.Disney2.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("continentes")

public class ContinenteController {

    @Autowired
    private ContinenteService continenteService; //Interfaz

    //tipo de solicitud
    @PostMapping
    public ResponseEntity<ContinenteDTO> save(@RequestBody ContinenteDTO continente) {              //PASS
        ContinenteDTO continenteGuardado = continenteService.save(continente);
        return ResponseEntity.status(HttpStatus.CREATED).body(continenteGuardado);
    }

    @GetMapping
    public ResponseEntity<List<ContinenteDTO>> getAll() {

        List<ContinenteDTO> continentes = this.continenteService.getAllContinentes();                //PASS
        return ResponseEntity.ok().body(continentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContinenteDTO> getContById(@PathVariable Long id) {                     //PASS
        ContinenteDTO continente = this.continenteService.getContinenteById((id));
        return ResponseEntity.ok().body(continente);
    }

    @PutMapping("/{id}")                                                                          //PASS
    public ResponseEntity<ContinenteDTO> update(@PathVariable Long id, @RequestBody ContinenteDTO continente) {
        ContinenteDTO result = this.continenteService.update(id, continente);
        return ResponseEntity.ok(result);
    }

    //@PostMapping("/{id}/ciudades/{idCiudad}")
    //public ResponseEntity<Void> addIcon(@PathVariable Long id, @PathVariable Long idCiudad) {
      //  this.continenteService.addCiudad(id, idCiudad);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
    //}   NOVA

    @DeleteMapping("/{id}")                                                                     //PASS
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.continenteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}



