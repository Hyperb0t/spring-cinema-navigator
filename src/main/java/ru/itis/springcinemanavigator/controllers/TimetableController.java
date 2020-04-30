package ru.itis.springcinemanavigator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.springcinemanavigator.services.TimetableHelper;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableHelper timetableHelper;

    @GetMapping
    public String getTimetablePage(HttpServletRequest req, Model model) {
        return timetableHelper.getTimetable(req, model);
    }
}
