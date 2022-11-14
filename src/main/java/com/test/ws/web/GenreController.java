package com.test.ws.web;

import com.test.ws.application.genre.GenreService;
import com.test.ws.web.dto.GenreDto;
import com.test.ws.web.mapper.WebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> findAll() {
        return genreService.findAll().stream()
                .map(WebMapper.I::toGenreDto)
                .collect(Collectors.toList());
    }
}
