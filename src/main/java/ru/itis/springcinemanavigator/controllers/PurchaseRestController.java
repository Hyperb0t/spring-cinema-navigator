package ru.itis.springcinemanavigator.controllers;

import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.springcinemanavigator.dao.SeanceRepository;
import ru.itis.springcinemanavigator.dao.UserRepository;
import ru.itis.springcinemanavigator.models.Seance;
import ru.itis.springcinemanavigator.models.User;
import ru.itis.springcinemanavigator.services.PurchaseService;

import java.util.Optional;

@RestController
public class PurchaseRestController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/seances/{seance-id}/purchase", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> purchase(@PathVariable("seance-id") Long seanceId,
                                      @RequestParam("place") Integer place,
                                      @RequestParam("userId") Long userId) {
        Optional<Seance> seanceOptional = seanceRepository.findById(seanceId);
        User user = userRepository.findById(userId).get();
        if(seanceOptional.isPresent()) {
            return ResponseEntity.ok(new EntityModel<>(purchaseService.purchase(place, seanceOptional.get(), user)));
        }
        else {
            throw new IllegalArgumentException("seance with id " + seanceId + " not found");
        }
    }

}
