package ru.itis.springcinemanavigator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.springcinemanavigator.services.PurchaseHelper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseHelper purchaseHelper;

    @GetMapping
    public String getPurchasePage(@RequestParam("seanceId")Long seanceId, Model model) throws IOException, ServletException {
        return purchaseHelper.showPage(seanceId, model);
    }

    @PostMapping
    public String purchase(@RequestParam Map<String,String> attrs, Model model,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) throws IOException, ServletException {
        return purchaseHelper.purchase(attrs, model, authentication, redirectAttributes);
    }
}
