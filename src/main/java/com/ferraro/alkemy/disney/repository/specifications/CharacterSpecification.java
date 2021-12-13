package com.ferraro.alkemy.disney.repository.specifications;

import com.ferraro.alkemy.disney.dto.CharacterFiltersDTO;
import com.ferraro.alkemy.disney.entity.CharacterEntity;
import com.ferraro.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (StringUtils.hasLength(filtersDTO.getAge())) {

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("age")),
                                "%" + filtersDTO.getAge().toLowerCase() + "%"
                        )
                );
            }


            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(filtersDTO.getMovies()));
            }


            query.distinct(true);       //delete  duplicates


            String orderByField = "name";
            query.orderBy(
                    filtersDTO.isASC() ?     //IF INLINE
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField)));


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
