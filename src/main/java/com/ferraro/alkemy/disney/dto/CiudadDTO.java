package alkemy.Disney2.Disney2.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter


public class CiudadDTO {

    private Long id;
    private String imagen;
    private String denominacion;
    private Long superficie;
    private List<IconDTO> icons;
    private Long continenteId ;
    private Long cantHabitantes;

}





