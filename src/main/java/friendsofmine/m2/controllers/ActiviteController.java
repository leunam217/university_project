package friendsofmine.m2.controllers;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.services.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActiviteController {

    @Autowired
    ActiviteService activiteService;

    public void setActiviteService(ActiviteService activiteService) {
        this.activiteService=activiteService;
    }

    @RequestMapping("/activitesWithResponsable")
    public List<Activite> findAllActvitesWithResponsable() {
        return activiteService.findAllActivites();
    }
}
