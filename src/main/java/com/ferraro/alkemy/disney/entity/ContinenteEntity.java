package alkemy.Disney2.Disney2.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "continente")
@Getter
@Setter
//Soft delete-> se convierte en una ctualizacion donde setea el campo deleted en true al id recibido.

@SQLDelete(sql = "UPDATE continente SET deleted=true WHERE id=?")
@Where(clause = "deleted=false") //-> con esta clausula identifico los que estan "borrados de los que no"
public class ContinenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)                    //autoincremental
    private Long id;

    private String imagen;

    private String denominacion;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)     // 1 a n
    //@JoinColumn(name = "id", insertable = false, updatable = false) // solo lo uso para obtener(get) las ciudades

    //  private List<CiudadEntity> ciudades = new ArrayList<SINTIPO>();  // coleccion de ciudades que contiene el continente

    //campo Soft delete
    private boolean deleted = Boolean.FALSE;


}
