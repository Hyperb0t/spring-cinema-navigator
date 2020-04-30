package ru.itis.springcinemanavigator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springcinemanavigator.models.Seance;
import ru.itis.springcinemanavigator.services.SearchSevice;
import ru.itis.springcinemanavigator.services.externalapi.FilmRecommendService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Autowired
    private SearchSevice searchSevice;

    @Autowired
    private FilmRecommendService filmRecommendService;

    @GetMapping
    public String getSearchPage(@RequestParam Map<String, String> attrs, Model model) {
        log.info("searching for: " + attrs.get("filmTitle"));
        if(!attrs.containsKey("filmTitle") || attrs.get("filmTitle").equals("")) {
            model.addAttribute("seances", searchSevice.findAllSeances(0,3));

        }
        else {
            List<Map<String,String>> recoms = filmRecommendService.getRecommendations(attrs.get("filmTitle"));
            log.info("found " + recoms.size() + " recommendataions");
            model.addAttribute("recommendations", recoms);
            model.addAttribute("seances", searchSevice.findSeancesByFilmTitle(attrs.get("filmTitle")));
        }
        return "search";
    }

    @ResponseBody
    @GetMapping("/rest/all")
    public List<Seance> restResultPage(@RequestParam("page") Integer pageNumber) {
        log.info("getting search page " + pageNumber);
        return searchSevice.findAllSeances(pageNumber,3);
    }
}
