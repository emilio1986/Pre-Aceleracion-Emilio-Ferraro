package alkemy.Disney2.Disney2.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "icon")
@Getter
@Setter
//Soft delete-> se convierte en una ctualizacion donde setea el campo deleted en true al id recibido.
@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id=?")
@Where(clause = "deleted = false") //-> con esta clausula identifico los que estan "borrados de los que no"
public class IconEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  //autoincremental
    private Long id;

    private String imagen;

    private String denominacion;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    private Long altura;

    private String historia;

    //Campo para el SOFT DELETE
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL)

    private List<CiudadEntity> ciudades = new ArrayList<>();

    public void addCiudad(CiudadEntity ciudad) {
        this.ciudades.add(ciudad);

    }

    public void removeCiudad(CiudadEntity ciudad) {

        this.ciudades.remove(ciudad);

    }

    public void setFechaCreacion(LocalDate unaFecha) {

        this.fechaCreacion = unaFecha;

    }


}
