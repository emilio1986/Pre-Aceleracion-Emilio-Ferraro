package alkemy.Disney2.Disney2.controller;

import alkemy.Disney2.Disney2.dto.CiudadBasicDTO;
import alkemy.Disney2.Disney2.dto.CiudadDTO;
import alkemy.Disney2.Disney2.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ciudades")
public class CiudadController {

@Autowired
    private CiudadService ciudadService; //Interfaz

    @Autowired
    public CiudadController(CiudadService ciudadService) {

        this.ciudadService = ciudadService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<CiudadBasicDTO>> getAll() {
        List<CiudadBasicDTO> ciudades = this.ciudadService.getAll();               //PASS
        return ResponseEntity.ok(ciudades);
    }


    @GetMapping("/{id}")                                                            //PASS
    public ResponseEntity<CiudadDTO> getDetailsById(@PathVariable Long id) {
        CiudadDTO ciudad = this.ciudadService.getDetailsById(id);
        return ResponseEntity.ok(ciudad);
    }


    @PostMapping
    public ResponseEntity<CiudadDTO> save(@RequestBody CiudadDTO ciudad) {
        CiudadDTO result = this.ciudadService.save(ciudad);
        return ResponseEntity.status((HttpStatus.CREATED)).body(result);           //PASS

    }

    @PostMapping("/{id}/icons/{idIcon}")
    public ResponseEntity<Void> addIcon(@PathVariable Long id, @PathVariable Long idIcon) {    //PASS
        this.ciudadService.addICon(id, idIcon);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @PutMapping("/{id}")                                                           //PASS
    public ResponseEntity<CiudadDTO> update(@PathVariable Long id, @RequestBody CiudadDTO ciudad) {
        CiudadDTO result = this.ciudadService.update(id, ciudad);
        return ResponseEntity.ok().body(result);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {                     //RESOLVER CASCADA CONTINENTE
        this.ciudadService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/icons/{idIcon}")                                           //CHEQUEAR
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long idIcon) {
        this.ciudadService.removeIcon(id, idIcon);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
