package alkemy.Disney2.Disney2.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ciudad")
@Getter
@Setter
//Soft delete-> se convierte en una actualizacion donde setea el campo deleted en true al id recibido.
@SQLDelete(sql = "UPDATE ciudad SET deleted = true WHERE id=?")
@Where(clause = "deleted = false") //-> con esta clausula identifico los que estan "borrados de los que no"
public class CiudadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //autoincremental
    private Long id;

    private String imagen;

    private String denominacion;

    @Column(name = "cant_habitantes")               //cuando el nombre es = al d la tabla NO va @column
    private Long cantHabitantes;                        //m2 aca estaba la linea

    @Column(name = "superficie")
    private Long superficie;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //CONSIDERAR QUITAR LA CASCADA POR EL BORRADO DE UNA CIUDAD Q PERTENECE A 1 CONT  // 1 a n
    @JoinColumn(name = "continente_id", insertable = false, updatable = false)

    private ContinenteEntity continente;

    @Column(name = "continente_id", nullable = false)
    private Long continenteId;                                      //defino la columna q no puede ser null y apunta a un cont(FK)

    //soft Delete
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "icon_ciudad",                                                               // nombre de la tabla INTERMEDIA
            joinColumns = @JoinColumn(name = "ciudad_id"),                  //como joinea de este lado
            inverseJoinColumns = @JoinColumn(name = "icon_id"))           // como joinea del otro lado(Icon)

    private Set<IconEntity> icons = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final CiudadEntity other = (CiudadEntity) obj;
        return other.id == this.id;

    }

    public void removeIcon(IconEntity iconEntity) {
        this.icons.remove(iconEntity);
    }

    public void addIcon(IconEntity iconEntity) {
        this.icons.add(iconEntity);
    }
}


