package com.ferraro.alkemy.disney.repository;

import com.ferraro.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//repository deben ser  Interfaz para que no sobrescriba  todos los metodos
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
