package ru.itis.springcinemanavigator.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springcinemanavigator.models.Film;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {
}
