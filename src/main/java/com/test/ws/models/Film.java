package com.test.ws.models;

import com.test.ws.application.film.repository.entity.CollectionEnum;
import lombok.Data;

import java.util.List;

@Data
public class Film {

    private Long id;

    private String name;

    private List<Genre> genres;

    private CollectionEnum collection;
}
