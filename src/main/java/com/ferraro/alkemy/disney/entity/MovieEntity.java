package com.ferraro.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter

@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)     //autoincrement
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    private LocalDate date;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @JoinColumn(name = "genre_id", insertable = false, updatable = false)

    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    //soft Delete
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "character_movie",                                                               // nombre de la tabla INTERMEDIA
            joinColumns = @JoinColumn(name = "movie_id"),                       //como joinea de este lado
            inverseJoinColumns = @JoinColumn(name = "character_id"))           // como joinea del otro lado(Icon)

    private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final MovieEntity other = (MovieEntity) obj;
        return other.id == this.id;

    }

    public void removeCharacter(CharacterEntity characterEntity) {
        this.characters.remove(characterEntity);
    }

    public void addCharacter(CharacterEntity characterEntity) {
        this.characters.add(characterEntity);
    }


}


