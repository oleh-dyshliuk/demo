package com.test.ws.application.genre.repository;

import com.test.ws.application.genre.repository.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    List<GenreEntity> findAllByNameIn(List<String> names);
}
