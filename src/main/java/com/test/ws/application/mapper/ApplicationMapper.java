package com.test.ws.application.mapper;

import com.test.ws.application.film.repository.entity.FilmEntity;
import com.test.ws.application.genre.repository.entity.GenreEntity;
import com.test.ws.models.Film;
import com.test.ws.models.Genre;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, builder = @Builder(disableBuilder = true))
public interface ApplicationMapper {

    ApplicationMapper I = Mappers.getMapper(ApplicationMapper.class);

    Film map(FilmEntity filmEntity);

    FilmEntity map(Film film);

    Genre map(GenreEntity genreEntity);

    List<Genre> map(List<GenreEntity> genreEntities);
}
