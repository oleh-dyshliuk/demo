package com.test.ws.application.film;

import com.test.ws.application.exceptions.FilmNotFoundException;
import com.test.ws.application.film.repository.FilmRepository;
import com.test.ws.application.mapper.ApplicationMapper;
import com.test.ws.models.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .map(ApplicationMapper.I::map)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    @Override
    public Film create(Film newFilm) {

        // any newFilm validation

        return ApplicationMapper.I.map(
                filmRepository.save(ApplicationMapper.I.map(newFilm))
        );
    }

    @Override
    public Film update(Film updatedFilm) {

        // any updatedFilm validation

        return ApplicationMapper.I.map(
                filmRepository.save(ApplicationMapper.I.map(updatedFilm))
        );
    }
}
