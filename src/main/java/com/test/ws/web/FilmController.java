package com.test.ws.web;

import com.test.ws.application.film.FilmService;
import com.test.ws.application.genre.GenreService;
import com.test.ws.models.Film;
import com.test.ws.models.Genre;
import com.test.ws.web.dto.ChangeCollectionDto;
import com.test.ws.web.dto.FilmDto;
import com.test.ws.web.dto.NewFilmDto;
import com.test.ws.web.dto.UpdateFilmDto;
import com.test.ws.web.mapper.WebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final GenreService genreService;

    @GetMapping("/{id}")
    public FilmDto find(@PathVariable Long id) {
        return WebMapper.I.toFilmDto(filmService.findById(id));
    }

    @PostMapping
    public FilmDto create(@RequestBody NewFilmDto newFilmDto) {
        List<Genre> genres = genreService.findAllOrCreate(newFilmDto.getGenres());

        return WebMapper.I.toFilmDto(
                filmService.create(WebMapper.I.toFilm(newFilmDto, genres))
        );
    }

    @PutMapping("/{id}")
    public FilmDto update(@PathVariable Long id, @RequestBody UpdateFilmDto updateFilmDto) {

        Film oldFilm = filmService.findById(id);
        Film updatedFilm = WebMapper.I.update(oldFilm, updateFilmDto);

        return WebMapper.I.toFilmDto(filmService.update(updatedFilm));
    }

    @PutMapping(value = "/{id}/collection")
    public FilmDto changeCollection(@PathVariable Long id, @RequestBody ChangeCollectionDto changeCollectionDto) {

        Film oldFilm = filmService.findById(id);
        Film updatedFilm = WebMapper.I.update(oldFilm, changeCollectionDto);

        return WebMapper.I.toFilmDto(filmService.update(updatedFilm));
    }
}
