package com.test.ws.application.film.repository.entity;

import com.test.ws.application.genre.repository.entity.GenreEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table
@Entity
public class FilmEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private CollectionEnum collection;

    @ManyToMany
    private List<GenreEntity> genres;
}
