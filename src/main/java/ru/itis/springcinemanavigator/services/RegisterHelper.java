package ru.itis.springcinemanavigator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itis.springcinemanavigator.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegisterHelper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<String> generateErrorList(Map<String,String> requestParams) {
        List<String> errorMessages = new LinkedList<>();
        if(!requestParams.get("email").matches("[a-z,0-9]+@[a-z]+\\.[a-z]+")) {
            errorMessages.add("wrong email");
        }
        else if(!requestParams.get("password").equals(requestParams.get("repassword"))) {
            errorMessages.add("passwords don't match");
        }
        else if(requestParams.get("password").length() < 5) {
            errorMessages.add("password is too short (less than 5 symbols)");
        }
        else if(requestParams.get("accept") == null ||!requestParams.get("accept").equals("on")) {
            errorMessages.add("please, accept the terms");
        }
        return errorMessages;
    }

    public String displayRegErrorPage(Model model, List<String> errorMessages, Map<String,String> requestParams) {
        model.addAttribute("message", errorMessages.stream().collect(Collectors.joining("\n")));
        model.addAttribute("emailValue", requestParams.get("email"));
        model.addAttribute("passwordValue", requestParams.get("password"));
        model.addAttribute("repasswordValue", requestParams.get("repassword"));
        return "register";
    }

    public User createUser(Map<String,String> requestParams) {
        return User.builder()
                .email(requestParams.get("email"))
                .password(passwordEncoder.encode(requestParams.get("password")))
                .role(User.Role.USER)
                .bonuses(0)
                .build();
    }
}
