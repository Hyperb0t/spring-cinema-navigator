package ru.itis.springcinemanavigator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.springcinemanavigator.dao.SeanceRepository;
import ru.itis.springcinemanavigator.models.Seance;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SearchSevice {

    @Autowired
    private SeanceRepository seanceRepository;

    public List<Seance> findSeancesByFilmTitle(String filmTitle) {
        return seanceRepository.findByFilmTitleLike(filmTitle);
    }

    public List<Seance> findAllSeances() {
        return StreamSupport.stream(seanceRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public List<Seance> findAllSeances(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Seance> seances = seanceRepository.findAll(pageable);
        return seances.toList();
    }
}
