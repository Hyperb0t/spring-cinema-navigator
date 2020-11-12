package ru.itis.springcinemanavigator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.springcinemanavigator.controllers.PurchaseRestController;
import ru.itis.springcinemanavigator.dao.UserRepository;
import ru.itis.springcinemanavigator.models.Seance;
import ru.itis.springcinemanavigator.models.User;


import java.time.Instant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SeancesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Seance>> {

    @Autowired
    private RepositoryEntityLinks links;
    @Autowired
    private UserRepository userRepository;

    @Override
    public EntityModel<Seance> process(EntityModel<Seance> model) {
        Seance seance = model.getContent();
        if (seance != null && seance.getStartTime().isAfter(Instant.now())) {
            model.add(linkTo(methodOn(PurchaseRestController.class)
                    .purchase(seance.getId(), 1, 1L)).withRel("purchase"));
        }
        return model;
    }
}
