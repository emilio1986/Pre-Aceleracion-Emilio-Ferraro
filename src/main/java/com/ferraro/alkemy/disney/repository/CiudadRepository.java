package alkemy.Disney2.Disney2.repository;

import alkemy.Disney2.Disney2.entity.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//repository deben ser  Interfaz para que no sobrescriba  todos los metodos
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {
}
