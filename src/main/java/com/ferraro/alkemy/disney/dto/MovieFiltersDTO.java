package com.ferraro.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {

    private String name;
    private Long genre;
    private String order;

    public boolean isAsc() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDesc() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }


}
