package alkemy.Disney2.Disney2.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter


@NoArgsConstructor
public class CiudadBasicDTO {
    private Long id;
    private String title;
    private String imageUrl;
    private Long cantHabitantes;
}



