package alkemy.Disney2.Disney2.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class IconDTO {

    private Long id;
    private String imagen;
    private String denominacion;
    private String fechaCreacion;
    private String historia;
    private List<CiudadDTO> ciudadades;


}
