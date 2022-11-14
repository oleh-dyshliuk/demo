package com.test.ws.web.dto;

import com.test.ws.application.film.repository.entity.CollectionEnum;
import lombok.Data;

import java.util.List;

@Data
public class NewFilmDto {

    private String name;

    private CollectionEnum collection;

    private List<String> genres;
}
