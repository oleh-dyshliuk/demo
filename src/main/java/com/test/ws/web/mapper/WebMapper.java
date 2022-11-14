package com.test.ws.web.mapper;

import com.test.ws.models.Film;
import com.test.ws.models.Genre;
import com.test.ws.web.dto.ChangeCollectionDto;
import com.test.ws.web.dto.FilmDto;
import com.test.ws.web.dto.GenreDto;
import com.test.ws.web.dto.NewFilmDto;
import com.test.ws.web.dto.UpdateFilmDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, builder = @Builder(disableBuilder = true))
public interface WebMapper {

    WebMapper I = Mappers.getMapper(WebMapper.class);

    /**
     *
     * @param film
     * @return
     */
    @Mapping(target = "genres", source = "genres", qualifiedByName = "genresToStrings")
    FilmDto toFilmDto(Film film);

    @Named("genresToStrings")
    default List<String> genresToStrings(List<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param oldFilm
     * @param updateFilmDto
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Film update(@MappingTarget Film oldFilm, UpdateFilmDto updateFilmDto);

    /**
     *
     * @param oldFilm
     * @param changeCollectionDto
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "collection", source = "newCollection")
    Film update(@MappingTarget Film oldFilm, ChangeCollectionDto changeCollectionDto);

    /**
     *
     * @param genre
     * @return
     */
    GenreDto toGenreDto(Genre genre);

    /**
     *
     * @param newFilmDto
     * @param genres
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", source = "genres")
    Film toFilm(NewFilmDto newFilmDto, List<Genre> genres);
}
