package ru.itis.springcinemanavigator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.springcinemanavigator.dao.OrderRepository;
import ru.itis.springcinemanavigator.dao.SeanceRepository;
import ru.itis.springcinemanavigator.models.Order;
import ru.itis.springcinemanavigator.models.Seance;
import ru.itis.springcinemanavigator.models.User;
import ru.itis.springcinemanavigator.security.UserDetailsImpl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PurchaseService {

    private final int hallRows = 6;
    private final int hallColumns = 10;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    public String showPage(Long seanceId, Model model) throws IOException, ServletException {
        if(seanceId == null) {
            return "redirect:/search";
        }
        List<Order> orders = orderRepository.findBySeanceId(seanceId);
        boolean[][] displayPlaces = new boolean[hallRows][hallColumns];
        for(int i = 0; i < hallRows; i++) {
            for(int j = 0; j < hallColumns; j++) {
                displayPlaces[i][j] = isPlaceBusy(i*hallColumns + j, orders);
            }
        }
        Optional<Seance> seanceOptional  = seanceRepository.findById(seanceId);
        if(!seanceOptional.isPresent()) {
            throw new IllegalArgumentException("not found seance with id " + seanceId);
        }
        model.addAttribute("seance", seanceOptional.get());
        model.addAttribute("displayPlaces", displayPlaces);
        return "purchase";
    }

    private boolean isPlaceBusy(int place, List<Order> seanceOrders) {
        for(Order o : seanceOrders) {
            if(o.getPlace() == place)
                return true;
        }
        return false;
    }

    public String purchase(Map<String,String> attrs, Model model,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) throws IOException, ServletException {

        if(attrs.get("seanceId") == null) {
            return "redirect:/search";
        }
        Long seanceId = Long.parseLong(attrs.get("seanceId"));
        if(attrs.get("place") == null || attrs.get("payData") == null || attrs.get("payData").isEmpty()) {
            model.addAttribute("message", "Обязательно выберите место и укажите данные для оплаты");
            return showPage(seanceId, model);
        }
        String anonEmail = attrs.get("anonEmail");
        boolean anonEmailWrong = (anonEmail == null || anonEmail.isEmpty() || !anonEmail.matches("[a-z,0-9]+@[a-z]+\\.[a-z]+"));
        if(!authentication.isAuthenticated() && anonEmailWrong) {
            model.addAttribute("message", "Укажите правильный email");
            return showPage(seanceId, model);
        }
        User user = ((UserDetailsImpl)authentication.getPrincipal()).getUser();
        int place = Integer.parseInt(attrs.get("place"));
        Optional<Seance> seanceOptional = seanceRepository.findById(seanceId);
        if(!seanceOptional.isPresent()) {
            throw new IllegalArgumentException("not found seance with id " + seanceId);
        }
        Order order = Order.builder()
                .place(place)
                .seance(seanceRepository.findById(seanceId).get())
                .user(user)
                .datetime(Instant.now())
                .build();
        orderRepository.save(order);
        redirectAttributes.addFlashAttribute("successOrder", order);
        return "redirect:/success";
    }

    public Order purchase(int place, Seance seance, User user) {
        if(place < 0 || place >= hallRows*hallColumns) {
            throw new IllegalArgumentException("wrong place number: " + place);
        }
        Order order = Order.builder()
                .place(place)
                .seance(seance)
                .user(user)
                .datetime(Instant.now())
                .build();
        orderRepository.save(order);
        return order;
    }

}
