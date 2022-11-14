package com.test.ws.application.film;

import com.test.ws.models.Film;

public interface FilmService {

    Film findById(Long id);

    Film create(Film newFilm);

    Film update(Film updatedFilm);
}
