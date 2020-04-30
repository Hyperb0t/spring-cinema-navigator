package ru.itis.springcinemanavigator.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springcinemanavigator.models.Seance;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Long> {

    List<Seance> findByFilmTitleLike(String filmTitle);

    List<Seance> findByStartTimeBetween(Instant start, Instant end);

    Page<Seance> findAll(Pageable pageable);
}
