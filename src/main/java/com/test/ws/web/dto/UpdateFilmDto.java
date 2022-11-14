package com.test.ws.web.dto;

import com.test.ws.application.film.repository.entity.CollectionEnum;
import lombok.Data;

@Data
public class UpdateFilmDto {

    private String name;

    private CollectionEnum collection;
}
