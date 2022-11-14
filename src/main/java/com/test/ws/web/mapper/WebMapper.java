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
     * {@link Film} to {@link FilmDto}
     * (to show case with specific conversion logic, but its not the only way we can do this)
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

    /** here we are updating existing {@link Film} using properties from {@link UpdateFilmDto}
     * "id" and "genres" are ignored cause  {@link UpdateFilmDto}
     * and unmappedTargetPolicy(in {@link Mapper) annotation above this class) will throw error in other case while compiling
     *
     * @param oldFilm
     * @param updateFilmDto
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Film update(@MappingTarget Film oldFilm, UpdateFilmDto updateFilmDto);

    /**
     * here implemented simple setting property changeCollectionDto.newCollection to film.collection (line 65)
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
     * simple mapping without any specific logic
     *
     * @param genre
     * @return
     */
    GenreDto toGenreDto(Genre genre);

    /**
     * creating {@link Film} from {@link NewFilmDto} and {@link List<Genre>} as a result of runtime genres creating
     *
     * @param newFilmDto
     * @param genres
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", source = "genres")
    Film toFilm(NewFilmDto newFilmDto, List<Genre> genres);
}
