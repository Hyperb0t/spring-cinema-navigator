package ru.itis.springcinemanavigator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.springcinemanavigator.dao.FilmRepository;
import ru.itis.springcinemanavigator.models.Film;

import java.util.Optional;

@Controller
@RequestMapping("/film")
@Slf4j
public class FilmInfoController {

    @Autowired
    FilmRepository filmRepository;

    @GetMapping
    public String getFilmInfoPage(@RequestParam("id")Film film, Model model) {
//        log.info("searching film with id: " + id);
//        Optional<Film> filmOptional = filmRepository.findById(id);
//        log.info("found: " + filmOptional.get());
//        if(filmOptional.isPresent()) {
//            model.addAttribute("film", filmOptional.get());
//        }
//        else {
//            throw new IllegalStateException("no film found with id + " + id);
//        }
        model.addAttribute("film", film);
        return "film_info";
    }
}
