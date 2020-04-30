package ru.itis.springcinemanavigator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.springcinemanavigator.models.Order;
import ru.itis.springcinemanavigator.models.User;
import ru.itis.springcinemanavigator.security.UserDetailsImpl;
import ru.itis.springcinemanavigator.services.BonusService;
import ru.itis.springcinemanavigator.services.UserService;

@Controller
@RequestMapping("/success")
public class SuccessOrderController {

    @Autowired
    private BonusService bonusService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSuccessOrderPage(Model model, Authentication authentication) {
        if(model.asMap().get("successOrder")==null) {
            return "redirect:/search";
        }
        Order order = (Order) model.asMap().get("successOrder");
        Integer addBonuses = bonusService.getAddBonuses(order.getSeance().getPrice());
        User user = ((UserDetailsImpl)authentication.getPrincipal()).getUser();;
        userService.addBonusesToUser(user, addBonuses);
        model.addAttribute("order",order);
        model.addAttribute("addBonuses", addBonuses);
        return "successOrder";
    }
}
