package ru.itis.springcinemanavigator.controllers.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.springcinemanavigator.dao.FilmRepository;
import ru.itis.springcinemanavigator.models.Film;

import java.util.Optional;

@Component
public class StringIdFilmConverter implements Converter<String, Film> {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public Film convert(String source) {
        Long id = Long.parseLong(source);
        Optional<Film> filmOptional = filmRepository.findById(id);
        if(!filmOptional.isPresent()) {
            throw new IllegalArgumentException("not found film with id " + id);
        }
        return filmOptional.get();
    }
}
