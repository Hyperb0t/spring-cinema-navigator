package ru.itis.springcinemanavigator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itis.springcinemanavigator.dao.SeanceRepository;
import ru.itis.springcinemanavigator.models.Seance;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Service
public class TimetableHelper {

    @Autowired
    private SeanceRepository seanceRepository;

    public String getTimetable(HttpServletRequest req, Model model) {
        Instant start;
        Instant end;
        if(req.getParameter("date") == null || req.getParameter("date").isEmpty()
                || !req.getParameter("date").matches("\\d{4}-\\d{2}-\\d{2}")) {
            if(req.getParameter("fromDate") != null && req.getParameter("toDate") != null
                    && !req.getParameter("fromDate").isEmpty() && !req.getParameter("toDate").isEmpty()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    start = format.parse(req.getParameter("fromDate")).toInstant();
                    end = format.parse(req.getParameter("toDate")).toInstant();
                }catch (ParseException e) {
                    throw new IllegalStateException(e);
                }
            }
            else {
                start = Instant.now();
                end = start.plusMillis(1000 * 60 * 60 * 24);
            }
        }
        else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                start = format.parse(req.getParameter("date")).toInstant();
                end = start.plusMillis(1000*60*60*24);
            }catch (ParseException e) {
                throw new IllegalStateException(e);
            }
        }

        List<Seance> seances = seanceRepository.findByStartTimeBetween(start, end);
        model.addAttribute("seances", seances);
        return "timetable";
    }
}
