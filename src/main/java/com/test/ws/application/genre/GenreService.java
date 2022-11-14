package com.test.ws.application.genre;

import com.test.ws.models.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    List<Genre> findAllOrCreate(List<String> genres);
}
