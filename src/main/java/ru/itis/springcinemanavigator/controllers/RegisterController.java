package ru.itis.springcinemanavigator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.springcinemanavigator.services.RegisterHelper;
import ru.itis.springcinemanavigator.services.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private RegisterHelper registerHelper;
    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String registerUser(@RequestParam Map<String,String> requestParams, Model model) {
        List<String> errors = registerHelper.generateErrorList(requestParams);
        if(errors.isEmpty()) {
            userService.registerUser(registerHelper.createUser(requestParams));
            return "redirect:/login";
        }
        else {
            return registerHelper.displayRegErrorPage(model, errors, requestParams);
        }
    }
}
