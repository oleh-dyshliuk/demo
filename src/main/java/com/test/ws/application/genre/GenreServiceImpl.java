package com.test.ws.application.genre;

import com.test.ws.application.genre.repository.GenreRepository;
import com.test.ws.application.genre.repository.entity.GenreEntity;
import com.test.ws.application.mapper.ApplicationMapper;
import com.test.ws.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll().stream()
                .map(ApplicationMapper.I::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Genre> findAllOrCreate(List<String> genreNames) {

        List<GenreEntity> foundEntities = genreRepository.findAllByNameIn(genreNames);

        List<String> foundNames = foundEntities.stream()
                .map(GenreEntity::getName)
                .collect(Collectors.toList());

        List<GenreEntity> newGenres = genreNames.stream()
                .filter(name -> !foundNames.contains(name))
                .map(name -> GenreEntity.builder().name(name).build())
                .collect(Collectors.toList());

        if(!newGenres.isEmpty()){
            genreRepository.saveAll(newGenres);
        }

        List<GenreEntity> results = new ArrayList<>();
        results.addAll(foundEntities);
        results.addAll(newGenres);

        return ApplicationMapper.I.map(results);
    }
}
