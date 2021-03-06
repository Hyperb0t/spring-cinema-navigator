package ru.itis.springcinemanavigator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(@RequestParam Map<String,String> requestParams, Model model) {
        if(requestParams.containsKey("error")) {
            model.addAttribute("message", "wrong credentials");
        }
        return "login";
    }

}
