package alkemy.Disney2.Disney2.dto;

//clase pelada que nos va a servir para manejar informacion

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContinenteDTO {

    private long id;
    private String imagen;
    private String denominacion;
    private List<CiudadDTO> ciudades;                   //Agregado ahora

    public void agregarCiudadDTO(CiudadDTO ciudadDTO) { //Agregado ahora

        this.ciudades.add(ciudadDTO);               //Agregado ahora
    }

    public void setCiudades(List<CiudadDTO> ciudadesDTO) {
    }

    public void addCiudad(CiudadDTO dtoCiudad) {
        this.ciudades.add(dtoCiudad);
    }
}
