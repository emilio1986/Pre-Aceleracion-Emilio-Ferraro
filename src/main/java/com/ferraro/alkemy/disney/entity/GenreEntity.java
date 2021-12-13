package com.ferraro.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "genre")
@Getter
@Setter


@SQLDelete(sql = "UPDATE genre SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)                    //autoincrement
    private Long id;

    private String image;

    private String name;

    // Soft delete
    private boolean deleted = Boolean.FALSE;


}
