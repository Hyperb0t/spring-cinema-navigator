package ru.itis.springcinemanavigator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.springcinemanavigator.models.User;
import ru.itis.springcinemanavigator.security.UserDetailsImpl;
import ru.itis.springcinemanavigator.services.UserService;

@Controller
@RequestMapping(value = "/cabinet", produces = "text/html;charset=utf-8")
public class CabinetController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getCabinetPage(Model model, Authentication authentication) {
        User user = ((UserDetailsImpl)authentication.getPrincipal()).getUser();
        model.addAttribute("userObject", user);
        model.addAttribute("orders", userService.getOrdersWithUser(user));
        return "cabinet";
    }
}
